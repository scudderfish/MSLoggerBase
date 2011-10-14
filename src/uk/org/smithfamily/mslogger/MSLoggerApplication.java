package uk.org.smithfamily.mslogger;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

@ReportsCrashes(formUri = "http://www.bugsense.com/api/acra?api_key=fd6951fe", formKey = "")
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
