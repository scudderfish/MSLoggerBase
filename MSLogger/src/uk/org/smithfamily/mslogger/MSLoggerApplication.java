package uk.org.smithfamily.mslogger;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

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

    private Megasquirt boundService;

    private ServiceConnection mConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service. Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            boundService = ((Megasquirt.LocalBinder) service).getService();

            // Tell the user about this for our demo.
            //Toast.makeText(Binding.this, R.string.local_service_connected, Toast.LENGTH_SHORT).show();
        }

        public void onServiceDisconnected(ComponentName className)
        {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            boundService = null;
            //Toast.makeText(Binding.this, R.string.local_service_disconnected, Toast.LENGTH_SHORT).show();
        }
    };
    private boolean mIsBound;

    /**
     * Do application initialisation work
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
        ApplicationSettings.INSTANCE.initialise(this);
        doBindService();
    }


    void doBindService()
    {
        // Establish a connection with the service. We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        Intent serviceIntent = new Intent(this, Megasquirt.class);
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
}
