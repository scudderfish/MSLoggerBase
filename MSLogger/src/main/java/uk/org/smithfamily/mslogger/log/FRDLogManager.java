package uk.org.smithfamily.mslogger.log;

import android.text.format.DateFormat;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import uk.org.smithfamily.mslogger.ApplicationSettings;

public enum FRDLogManager
{
    INSTANCE;
    private final FRDLogFile           frdLog = new FRDLogFile();
    private final FRDLogFileHeader     header = frdLog.getHeader();
    private final FRDLogFileBody       body   = frdLog.getBody();
    private BufferedOutputStream os;
    private File                 logFile;
    private String absolutePath;
    private long                 startTime;

    public long getStartTime()
    {
        return startTime;
    }

    public void write(byte[] buffer) throws IOException
    {
		if (ApplicationSettings.INSTANCE.isReadOnly())
		{
			return;
		}		

        if (os == null)
        {
            startTime = System.currentTimeMillis();
            createLogFile();
            writeHeader();
        }
        body.addRecord(buffer);
        os.write(body.getCurrentRecord().getBytes());
    }

    private void writeHeader() throws IOException
    {
		if (ApplicationSettings.INSTANCE.isReadOnly())
		{
			return;
		}

        os.write(header.getHeaderRecord());
    }

    private void createLogFile() throws FileNotFoundException
    {
		if (ApplicationSettings.INSTANCE.isReadOnly())
		{
			return;
		}

        Date now = new Date();

        String fileName = DateFormat.format("yyyy-MM-dd_kk.mm.ss", now).toString() + ".frd";

        logFile = new File(ApplicationSettings.INSTANCE.getDataDir(), fileName);
        absolutePath = logFile.getAbsolutePath();
        os = new BufferedOutputStream(new FileOutputStream(logFile));
    }

    public FRDLogFile getFRDLogFile()
    {
        return frdLog;
    }

    public void close()
    {
        try
        {
            if (os != null)
            {
                os.flush();
                os.close();
            }
        }
        catch (IOException e)
        {
            Log.e(ApplicationSettings.TAG,"FRDLogManager.close()",e);
        }

        os = null;
        logFile = null;
    }

    public void stopLog()
    {
        close();
    }

	public String getAbsolutePath()
	{
		return absolutePath;
	}
	
	public FRDLogFile loadFile(FileInputStream fis) throws IOException
	{

        return new FRDLogFile(fis);
	}
}
