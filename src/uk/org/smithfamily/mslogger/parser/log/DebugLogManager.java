package uk.org.smithfamily.mslogger.parser.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import android.text.format.DateFormat;

public class DebugLogManager
{
    private static final DebugLogManager instance = new DebugLogManager();

    public static DebugLogManager getInstance()
    {
        return instance;
    }

    private File       logFile;
    private FileWriter os;

    private DebugLogManager()
    {

    }

    private void createLogFile() throws IOException
    {
        File dir = new File("/sdcard/MSParser");
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
