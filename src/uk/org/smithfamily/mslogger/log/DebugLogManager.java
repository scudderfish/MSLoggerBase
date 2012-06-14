package uk.org.smithfamily.mslogger.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import uk.org.smithfamily.mslogger.ApplicationSettings;
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
        if (!ApplicationSettings.INSTANCE.isWritable())
        {
            return;
        }

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

    public synchronized void log(String s, int logLevel)
    {
        if (!checkLogLevel(logLevel))
        {
            return;

        }
        if (!ApplicationSettings.INSTANCE.isWritable())
        {
            return;
        }

        try
        {
            if (logFile == null || os == null)
                createLogFile();

            long now = System.currentTimeMillis();
            os.write(String.format("%tc:%tL:%s:%s\n", now, now, Thread.currentThread().getName(), s));
            os.flush();
        }
        catch (IOException e)
        {
            System.err.println("Could not write '" + s + "' to the log file : " + e.getLocalizedMessage());
        }
    }

    private boolean checkLogLevel(int logLevel)
    {
        return (ApplicationSettings.INSTANCE.getLoggingLevel() <= logLevel);
    }

    public synchronized void logException(Exception ex)
    {
        if (!ApplicationSettings.INSTANCE.isWritable())
        {
            return;
        }

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

    public void log(String msg, byte[] result, int logLevel)
    {
        if (!checkLogLevel(logLevel))
        {
            return;

        }
        StringBuffer b = new StringBuffer(msg);
        for (int i = 0; i < result.length; i++)
        {
            b.append(String.format(" %02x", result[i]));
        }
        log(b.toString(), logLevel);
    }
}
