package uk.org.smithfamily.mslogger.log;

import java.io.*;
import java.util.Date;

import android.os.Environment;
import android.text.format.DateFormat;

public enum DebugLogManager
{
    INSTANCE;

    private File       logFile;
    private FileWriter os;
    private String     absolutePath;

    private void createLogFile() throws IOException
    {
        File dir = new File(Environment.getExternalStorageDirectory(), "MSLogger");
        dir.mkdirs();

        if (logFile == null)
        {
            Date now = new Date();

            String fileName = DateFormat.format("yyyyMMddkkmmss", now).toString() + ".log";
            logFile = new File(dir, fileName);
        }
        absolutePath = logFile.getAbsolutePath();
        os = new FileWriter(logFile, true);

    }

    public void log(String s)
    {
        try
        {
            if (logFile == null || os == null)
                createLogFile();

            os.write(String.format("%tc:%s\n", System.currentTimeMillis(), s));
            os.flush();
        }
        catch (IOException e)
        {
            System.err.println("Could not write '" + s + "' to the log file : " + e.getLocalizedMessage());
        }
    }

    public void logException(Exception ex)
    {
        if (os == null)
        {
            try
            {
                createLogFile();
            }
            catch (IOException e)
            {
                System.err.println("Could not create the log file : " + e.getLocalizedMessage());
            }
        }
        PrintWriter pw = new PrintWriter(os);
        try
        {
            os.write(ex.getLocalizedMessage() + "\n");
        }
        catch (IOException e)
        {
        }
        ex.printStackTrace(pw);
        pw.close();
        try
        {
            os.flush();
        }
        catch (IOException e)
        {
        }
    }

    public String getAbsolutePath()
    {
        return absolutePath;
    }
}
