package uk.org.smithfamily.mslogger.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import uk.org.smithfamily.mslogger.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;

public enum DebugLogManager
{
    INSTANCE;
 
    private File       logFile;
    private FileWriter os;

 
    private void createLogFile(Context context) throws IOException
    {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        File dir = new File(Environment.getExternalStorageDirectory(),prefs.getString("DataDir", context.getString(R.string.app_name)));
        dir.mkdirs();

        Date now = new Date();

        String fileName = DateFormat.format("yyyyMMddkkmmss", now).toString() + ".log";

        logFile = new File(dir, fileName);

        os = new FileWriter(logFile, true);

    }

    public void log(String s,Context context)
    {
        try
        {
            if (logFile == null)
                createLogFile(context);

            os.write(System.currentTimeMillis() + ":" + s+"\n");
            os.flush();
        }
        catch (IOException e)
        {
            System.err.println("Could not write '" + s + "' to the log file : " + e.getLocalizedMessage());
        }
    }

}
