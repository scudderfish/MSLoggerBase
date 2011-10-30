package uk.org.smithfamily.mslogger;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

// "http://www.bugsense.com/api/acra?api_key=fd6951fe"
// https://docs.google.com/spreadsheet/viewform?formkey=dGFDbHRzU1M1UFJvTTVKNlZDLTlhSGc6MQ
@ReportsCrashes(formKey = "",formUri="http://www.bugsense.com/api/acra?api_key=fd6951fe")
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
