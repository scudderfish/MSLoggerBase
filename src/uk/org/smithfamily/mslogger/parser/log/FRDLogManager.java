package uk.org.smithfamily.mslogger.parser.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import uk.org.smithfamily.mslogger.parser.MsDatabase;
import uk.org.smithfamily.mslogger.parser.Repository;
import android.text.format.DateFormat;

public enum FRDLogManager
{
    INSTANCE;
    
    public enum State
    {
        Logging, Reading
    };

    private static final MsDatabase mdb          = MsDatabase.INSTANCE;
    private FRDLogFile              frdLog       = new FRDLogFile();
    private FRDLogFileHeader        header       = frdLog.getHeader();
    private FRDLogFileBody          body         = frdLog.getBody();
    private FileOutputStream        os;
    private File                    logFile;
    private long                    startTime;
    private State                   currentState = State.Logging;

    public long getStartTime()
    {
        return startTime;
    }

    public void write() throws IOException
    {
        if (currentState == State.Reading)
        {
            throw new IOException("Can't log whilst reading!");
        }
        if (os == null)
        {
            startTime = System.currentTimeMillis();
            createLogFile();
            writeHeader();
        }
        body.addRecord(mdb.cDesc.ochBuffer());
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
        File dir = new File("/sdcard/" + Repository.INSTANCE.getDataDir());
        dir.mkdirs();

        Date now = new Date();

        String fileName = DateFormat.format("yyyyMMddkkmmss", now).toString() + ".frd";

        logFile = new File(dir, fileName);

        os = new FileOutputStream(logFile);
    }

    public FRDLogFile getFRDLogFile()
    {

        return frdLog;
    }

    public void setState(State state)
    {
        currentState = state;
        
    }
}
