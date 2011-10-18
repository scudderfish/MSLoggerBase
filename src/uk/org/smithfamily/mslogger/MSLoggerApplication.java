package uk.org.smithfamily.mslogger;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.*;

import android.app.Application;

// "http://www.bugsense.com/api/acra?api_key=fd6951fe"
//https://docs.google.com/spreadsheet/viewform?formkey=dGFDbHRzU1M1UFJvTTVKNlZDLTlhSGc6MQ
@ReportsCrashes(//formKey = "dGFDbHRzU1M1UFJvTTVKNlZDLTlhSGc6MQ",
            formKey="",
            mailTo="dave.g.smith@gmail.com",
            customReportContent = { ReportField.APP_VERSION_NAME, 
                        ReportField.ANDROID_VERSION, 
                        ReportField.PHONE_MODEL, 
                        ReportField.CUSTOM_DATA, 
                        ReportField.STACK_TRACE, 
                        ReportField.LOGCAT,
                        ReportField.DEVICE_FEATURES,
                        ReportField.RADIOLOG,
                        ReportField.SETTINGS_SYSTEM,
                        ReportField.SHARED_PREFERENCES}, 
            logcatArguments = { "-t", "200", "-v", "time" }, 
            mode = ReportingInteractionMode.TOAST, 
            forceCloseDialogAfterToast = true, 
            resToastText = R.string.crash_toast_text)
public class MSLoggerApplication extends Application
{
    @Override
    public void onCreate()
    {
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
        super.onCreate();
        ApplicationSettings.INSTANCE.initialise(this);
    }
}
