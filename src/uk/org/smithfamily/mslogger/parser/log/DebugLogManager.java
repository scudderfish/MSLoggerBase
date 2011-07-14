package uk.org.smithfamily.mslogger.parser.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import uk.org.smithfamily.mslogger.parser.Repository;

import android.text.format.DateFormat;

public enum DebugLogManager
{
    INSTANCE;
 
    private File       logFile;
    private FileWriter os;

 
    private void createLogFile() throws IOException
    {
        File dir = new File("/sdcard/"+Repository.INSTANCE.getDataDir());
        dir.mkdirs();

        Date now = new Date();

        String fileName = DateFormat.format("yyyyMMddkkmmss", now).toString() + ".log";

        logFile = new File(dir, fileName);

        os = new FileWriter(logFile, true);

    }

    public void log(String s)
    {
        try
        {
            if (logFile == null)
                createLogFile();

            os.write(System.currentTimeMillis() + ":" + s+"\n");
            os.flush();
        }
        catch (IOException e)
        {
            System.err.println("Could not write '" + s + "' to the log file : " + e.getLocalizedMessage());
        }
    }

}
