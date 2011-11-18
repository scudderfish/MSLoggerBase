package uk.org.smithfamily.mslogger;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import uk.org.smithfamily.mslogger.service.MSLoggerService;
import android.app.Application;
import android.content.Intent;

// "http://www.bugsense.com/api/acra?api_key=fd6951fe"
// https://docs.google.com/spreadsheet/viewform?formkey=dGFDbHRzU1M1UFJvTTVKNlZDLTlhSGc6MQ
@ReportsCrashes(formKey = "",formUri="http://www.bugsense.com/api/acra?api_key=fd6951fe")
public class MSLoggerApplication extends Application
{
    public static final int GOT_SIG = 1052;
    public static final int REQUEST_CONNECT_DEVICE = 1053;
    public static final int MESSAGE_TOAST = 1054;
    public static final String MSG_ID = "msgId";
    public static final int PROBE_ECU = 1055;
    
    @Override
    public void onCreate()
    {
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
        super.onCreate();
        ApplicationSettings.INSTANCE.initialise(this);
        startService(new Intent(this, MSLoggerService.class));
        
    }

    @Override
    public void onTerminate()
    {
        // TODO Auto-generated method stub
        super.onTerminate();
        stopService(new Intent(this, MSLoggerService.class));
    }
}
