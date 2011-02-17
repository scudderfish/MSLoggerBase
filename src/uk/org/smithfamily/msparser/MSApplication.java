package uk.org.smithfamily.msparser;

import uk.org.smithfamily.msdisp.parser.INIController;
import android.app.Application;
import android.content.Context;

public class MSApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Context c = this.getApplicationContext();
        INIController.getInstance().initialise(c);
    }
}
