package uk.org.smithfamily.mslogger;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Map;
import java.util.Map.Entry;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DebugLogManager;

/**
 * Hook class to allow initialisation actions. Originally used to initialise ACRA and Bugsense
 * 
 */
public class MSLoggerApplication extends Application
{
    public static final int GOT_SIG = 1052;
    public static final int REQUEST_CONNECT_DEVICE = 1053;
    public static final int MESSAGE_TOAST = 1054;
    public static final String MSG_ID = "msgId";
    public static final int COMMS_ERROR = 1056;
    public static final int NO_BLUETOOTH = 1057;
    public static final int UNKNOWN_ECU = 1058;
    public static final String CHANNEL_ID = "SLCI";

    private Megasquirt boundService;

    private final ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(final ComponentName className, final IBinder service)
        {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service. Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            boundService = ((Megasquirt.LocalBinder) service).getService();

            // Tell the user about this for our demo.
            // Toast.makeText(Binding.this, R.string.local_service_connected, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(final ComponentName className)
        {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            boundService = null;
            // Toast.makeText(Binding.this, R.string.local_service_disconnected, Toast.LENGTH_SHORT).show();
        }
    };
    private boolean mIsBound;
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "SpeedyLogger";//getString(R.string.channel_name);
            String description = "desc";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Do application initialisation work
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
        ApplicationSettings.INSTANCE.initialise(this);

        DebugLogManager.INSTANCE.log(getPackageName(), Log.DEBUG);
        try
        {
            final String app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
            DebugLogManager.INSTANCE.log(app_ver, Log.ASSERT);
        }
        catch (final NameNotFoundException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
        createNotificationChannel();

        dumpPreferences();

        doBindService();
    }

    void doBindService()
    {
        // Establish a connection with the service. We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        final Intent serviceIntent = new Intent(this, Megasquirt.class);
        bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if (mIsBound)
        {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();

        doUnbindService();
    }

    /**
     * Dump the user preference into the log file for easier debugging
     */
    private void dumpPreferences()
    {
        final SharedPreferences prefsManager = PreferenceManager.getDefaultSharedPreferences(this);
        final Map<String, ?> prefs = prefsManager.getAll();
        for (final Entry<String, ?> entry : prefs.entrySet())
        {
            DebugLogManager.INSTANCE.log("Preference:" + entry.getKey() + ":" + entry.getValue(), Log.ASSERT);
        }
    }

}
