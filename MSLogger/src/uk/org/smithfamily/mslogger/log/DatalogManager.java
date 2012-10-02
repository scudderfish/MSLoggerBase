package uk.org.smithfamily.mslogger.log;

import java.io.*;
import java.util.Date;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.text.format.DateFormat;

/**
 * Class that write a datalog in .msl format
 */
public enum DatalogManager
{
    INSTANCE;

    PrintWriter       writer          = null;
    private String    fileName        = null;
    private String    absolutePath    = null;
    private int       markCounter     = 0;
    private long      logStart        = System.currentTimeMillis();

    /**
     * @return The absolute path of the data log
     */
    public synchronized String getAbsolutePath()
    {
        return absolutePath;
    }

    /**
     * @return The filename of the datalog
     */
    public synchronized String getFilename()
    {
        if (fileName == null)
        {
            fileName = DateFormat.format("yyyy-MM-dd_kk.mm.ss", new Date()).toString() + ".msl";
        }
        
        return fileName;
    }

    /**
     * Append a row at the end of the data log
     * 
     * @param logRow   The row to write in the file
     */
    public synchronized void write(String logRow)
    {
        if (!ApplicationSettings.INSTANCE.isWritable())
        {
            return;
        }
        
        if (writer == null)
        {
            File logFile = new File(ApplicationSettings.INSTANCE.getDataDir(), getFilename());
            absolutePath = logFile.getAbsolutePath();
            
            try
            {
                if (!logFile.exists())
                { 
                    writer = new PrintWriter(new FileWriter(logFile));
                    Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();
                    
                    String signature = ecuDefinition.getTrueSignature();
                    writer.println("\"" + signature + "\"");
                    
                    String captureDate = "Capture Date: " + new Date().toString();
                    writer.println("\"" + captureDate + "\"");
                    
                    writer.println(ecuDefinition.getLogHeader());
                    markCounter = 1;
                    logStart = System.currentTimeMillis();
                }
                else
                {
                    writer = new PrintWriter(new FileWriter(logFile,true));
                }
            }
            catch (IOException e)
            {
                if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.logException(e);
            }
        }
        
        writer.println(logRow);
    }

    /**
     * Stop logging by closing the file writer
     */
    public synchronized void stopLog()
    {
        if (writer != null)
        {
            writer.flush();
            writer.close();
        }
        
        writer = null;
    }

    /**
     * Alias to stopLog()
     */
    public void close()
    {
        stopLog();
    }

    /**
     * Mark the datalog with the specified message
     * 
     * @param msg  The mark message
     */
    public synchronized void mark(String msg)
    {
        if (!ApplicationSettings.INSTANCE.isWritable())
        {
            return;
        }
        
        if (writer != null)
        {
            write(String.format("MARK  %03d - %s - %tc", markCounter++, msg, System.currentTimeMillis()));
            if (markCounter > 999)
            {
                markCounter = 1;
            }
        }
    }

    /**
     * Mark the datalog
     */
    public void mark()
    {
        mark("Manual");
    }

    /**
     * @return Milliseconds when the log was created
     */
    public long getLogStart()
    {
        return logStart;
    }
}
