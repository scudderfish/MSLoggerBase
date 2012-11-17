package uk.org.smithfamily.mslogger.log;

import java.io.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import android.os.Environment;
import android.util.Log;

/**
 * Class that is used to help debugging. Will log the specified log level to a log file that can be sent by users to developers
 */
public enum DebugLogManager
{
    INSTANCE;

    private final static boolean ALWAYS_LOG_TO_FILE = false;
    private File logFile;
    private FileWriter os;
    private String absolutePath;
    private final int MAX_LOG_FILE_SIZE_THRESHOLD = 5120; // (1024 * 5)B = 5120KB = 5MB

    /**
     * Create the log file where the log will be saved
     * 
     * @throws IOException
     */
    private void createLogFile() throws IOException
    {
        if (!ApplicationSettings.INSTANCE.isWritable())
        {
            return;
        }

        final File dir = new File(Environment.getExternalStorageDirectory(), "MSLogger");
        dir.mkdirs();

        boolean append = true;

        if (logFile == null)
        {
            final String fileName = "debugLog.txt";
            logFile = new File(dir, fileName);
        }

        // If log file have reached threshold, don't append to it, overwrite it instead
        if (logFile.length() >= MAX_LOG_FILE_SIZE_THRESHOLD)
        {
            append = false;
        }

        absolutePath = logFile.getAbsolutePath();
        os = new FileWriter(logFile, append);
    }

    /**
     * Main function to write in the log file
     * 
     * @param s The log to write
     * @param logLevel The level of log
     */
    public void log(final String s, final int logLevel)
    {
        // Make sure the user want to save a log of that level
        if (!checkLogLevel(logLevel))
        {
            return;
        }
        // Make sure we have write permission
        if (!ApplicationSettings.INSTANCE.isWritable())
        {
            return;
        }
        Log.println(logLevel, "MSLogger", s);
        if (ALWAYS_LOG_TO_FILE || (logLevel <= Log.DEBUG))
        {
            try
            {
                if ((logFile == null) || (os == null))
                {
                    createLogFile();
                }

                final long now = System.currentTimeMillis();
                synchronized (os)
                {
                    os.write(String.format("%tc:%tL:%s:%s%n", now, now, Thread.currentThread().getName(), s));
                    os.flush();
                }
            }
            catch (final IOException e)
            {
                System.err.println("Could not write '" + s + "' to the log file : " + e.getLocalizedMessage());
            }
        }
    }

    /**
     * Check if the user preference is set to accept the specified log level
     * 
     * @param logLevel The specified log level of the user
     * @return true if log is accepted, false otherwise
     */
    public static boolean checkLogLevel(final int logLevel)
    {
        return (ALWAYS_LOG_TO_FILE || (ApplicationSettings.INSTANCE.getLoggingLevel() <= logLevel));
    }

    /**
     * Log exception into the log file
     * 
     * @param ex The exception to log
     */
    public void logException(final Exception ex)
    {
        // Make sure we have write permission
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
            catch (final IOException e)
            {
                System.err.println("Could not create the log file : " + e.getLocalizedMessage());
            }
        }
        synchronized (os)
        {
            final PrintWriter pw = new PrintWriter(os);
            try
            {
                final long now = System.currentTimeMillis();
                os.write(String.format("%tc:%tL:%s:%s%n", now, now, Thread.currentThread().getName(), ex.getLocalizedMessage()));
            }
            catch (final IOException e)
            {
            }
            ex.printStackTrace(pw);
            try
            {
                if (os != null)
                {
                    os.flush();
                }
            }
            catch (final IOException e)
            {
            }
        }
    }

    /**
     * @return The absolute path to the log file
     */
    public String getAbsolutePath()
    {
        return absolutePath;
    }

    /**
     * This helper function can be used to log a bytes array to log, prefixed by the specified message
     * 
     * @param msg Log to be saved
     * @param result Bytes to be saved after the log
     * @param logLevel The log level
     */
    public void log(final String msg, final byte[] result, final int logLevel)
    {
        if (!checkLogLevel(logLevel))
        {
            return;
        }

        final StringBuffer b = new StringBuffer(msg).append("\n");
        StringBuffer text = new StringBuffer();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < result.length; i++)
        {
            final char c = (char) result[i];
            hex.append(String.format(" %02x", result[i]));
            if ((c >= 32) && (c <= 127))
            {
                text.append(c);
            }
            else
            {
                text.append('.');
            }
            if (((i + 1) % 40) == 0)
            {
                b.append(hex).append(" ").append(text).append("\n");
                text = new StringBuffer();
                hex = new StringBuffer();
            }
        }
        if (text.length() > 0)
        {
            b.append(String.format("%1$-120s %2$s\n", hex.toString(), text.toString()));
        }
        log(b.toString(), logLevel);
    }
}
