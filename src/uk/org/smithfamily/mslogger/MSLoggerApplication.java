package uk.org.smithfamily.mslogger;

import org.acra.*;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

// "http://www.bugsense.com/api/acra?api_key=fd6951fe"
@ReportsCrashes(formKey = "dDZmcjZfU0xVVTN6b2ZtTkdjM2pGY1E6MQ", logcatArguments = { "-t", "200", "-v", "time" }, 
        mode = ReportingInteractionMode.TOAST, forceCloseDialogAfterToast = false,                                                                                                          // false
resToastText = R.string.crash_toast_text)
public class MSLoggerApplication extends Application
{
    @Override
    public void onCreate()
    {
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
        super.onCreate();
    }
}
