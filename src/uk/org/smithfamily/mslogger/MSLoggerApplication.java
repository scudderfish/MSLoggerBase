package uk.org.smithfamily.mslogger;

import android.app.Application;

/**
 * Hook class to allow initialisation actions.  Originally used to initialise ACRA and Bugsense
 * 
 */
public class MSLoggerApplication extends Application
{
    public static final int GOT_SIG = 1052;
    public static final int REQUEST_CONNECT_DEVICE = 1053;
    public static final int MESSAGE_TOAST = 1054;
    public static final String MSG_ID = "msgId";
    public static final int PROBE_ECU = 1055;
    public static final int COMMS_ERROR = 1056;
    
    /**
     * Do application initialisation work
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
        ApplicationSettings.INSTANCE.initialise(this);
    }
}
