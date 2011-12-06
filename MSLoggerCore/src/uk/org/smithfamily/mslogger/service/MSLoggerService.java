package uk.org.smithfamily.mslogger.service;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.activity.MSLoggerActivity;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DatalogManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.app.*;
import android.content.*;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MSLoggerService extends Service
{
    private BroadcastReceiver      updateReceiver        = new Reciever();

    public boolean isLogging()
	{
		return logging;
	}

	private static final int MSLOGGERSERVICE_ID = 0;

    public class MSLoggerBinder extends Binder
    {
        public MSLoggerService getService()
        {
            return MSLoggerService.this;
        }
    }

    private final IBinder mBinder = new MSLoggerBinder();
	private boolean	logging;
    private boolean isConnected;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0)
    {
        return mBinder;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        IntentFilter ecuFilter = new IntentFilter(ApplicationSettings.ECU_CHANGED);
        registerReceiver(updateReceiver, ecuFilter);
        IntentFilter connectedFilter = new IntentFilter(Megasquirt.CONNECTED);
        registerReceiver(updateReceiver, connectedFilter);
        IntentFilter disconnectedFilter = new IntentFilter(Megasquirt.DISCONNECTED);
        registerReceiver(updateReceiver, disconnectedFilter);

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        disconnect();
        unregisterReceiver(updateReceiver);
    }

    public double getValue(String channelName)
    {
        return ApplicationSettings.INSTANCE.getEcuDefinition().getValue(channelName);
    }

    public void connect()
    {
        Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();

        if(ecuDefinition != null)
        {
            ecuDefinition.start();
            isConnected = true;
        }
        
    }
    public void disconnect()
    {
        Toast.makeText(this, R.string.disconnecting_from_ms, Toast.LENGTH_LONG).show();
        Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();

        if(ecuDefinition != null)
        {
            ecuDefinition.stop();
            isConnected = false;
        }
        removeNotification();
    }

    private void showNotification()
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);

        int icon = R.drawable.icon;
        long when = System.currentTimeMillis();

        Notification notification = new Notification(icon, getString(R.string.mslogger_is_running), when);

        Context context = getApplicationContext();

        Intent notificationIntent = new Intent(this, MSLoggerActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification.setLatestEventInfo(context, getString(R.string.mslogger_is_running),
                getString(R.string.logging_to, DatalogManager.INSTANCE.getFilename()), contentIntent);

        mNotificationManager.notify(MSLOGGERSERVICE_ID, notification);
    }

    private void removeNotification()
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        mNotificationManager.cancelAll();
    }

    public void stopLogging()
    {
        logging = false;
        ApplicationSettings.INSTANCE.getEcuDefinition().stopLogging();
        removeNotification();
    }

    public void startLogging()
    {
        showNotification();

        ApplicationSettings.INSTANCE.getEcuDefinition().startLogging();
        logging = true;
    }

    public void connectToECU()
    {
        ApplicationSettings.INSTANCE.getEcuDefinition().initialiseConnection();
    }

    public void toggleConnection()
    {
       if(isConnected)
       {
           disconnect();
           ApplicationSettings.INSTANCE.setAutoConnectOverride(false);
       }
       else
       {
           connect();
           ApplicationSettings.INSTANCE.setAutoConnectOverride(true);
       }
    }

    private final class Reciever extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            DebugLogManager.INSTANCE.log("Service: "+action);
            if (action.equals(ApplicationSettings.ECU_CHANGED))
            {
                ApplicationSettings.INSTANCE.getEcuDefinition().start();
            }
            if (action.equals(Megasquirt.CONNECTED))
            {
                isConnected=true;
            }
            if (action.equals(Megasquirt.DISCONNECTED))
            {
                isConnected = false;
            }
        }
    }
}
