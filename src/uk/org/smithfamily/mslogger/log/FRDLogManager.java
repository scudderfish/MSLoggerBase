package uk.org.smithfamily.mslogger.log;

import java.io.*;
import java.util.Date;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import android.text.format.DateFormat;

public enum FRDLogManager
{
    INSTANCE;
    
      private FRDLogFile            frdLog       = new FRDLogFile();
    private FRDLogFileHeader        header       = frdLog.getHeader();
    private FRDLogFileBody          body         = frdLog.getBody();
    private FileOutputStream        os;
    private File                    logFile;
    private long                    startTime;

    public long getStartTime()
    {
        return startTime;
    }

    public void write(byte[] buffer) throws IOException
    {
        if (os == null)
        {
            startTime = System.currentTimeMillis();
            createLogFile();
            writeHeader();
        }
        body.addRecord(buffer);
        os.write(body.getCurrentRecord().getBytes());
        os.flush();
    }

    private void writeHeader() throws IOException
    {

        os.write(header.getHeaderRecord());
        os.flush();
    }

    private void createLogFile() throws FileNotFoundException
    {

        Date now = new Date();

        String fileName = DateFormat.format("yyyyMMddkkmmss", now).toString() + ".frd";

        logFile = new File(ApplicationSettings.INSTANCE.getDataDir(), fileName);

        os = new FileOutputStream(logFile);
    }

    public FRDLogFile getFRDLogFile()
    {

        return frdLog;
    }
}
