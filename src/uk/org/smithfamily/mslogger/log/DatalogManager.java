package uk.org.smithfamily.mslogger.log;

import java.io.*;
import java.util.Date;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.text.format.DateFormat;

/**
 *
 */
public enum DatalogManager
{
	INSTANCE;

	PrintWriter		writer			= null;
	private String	fileName		= null;
	private String	absolutePath	= null;
	private int		markCounter		= 0;
	private long   logStart = System.currentTimeMillis();

	/**
	 * 
	 * @return
	 */
	public synchronized String getAbsolutePath()
	{
		return absolutePath;
	}

	/**
	 * 
	 * @return
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
	 * 
	 * @param logRow
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.println(logRow);
	}

	/**
	 * 
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
	 * 
	 */
	public void close()
	{
		stopLog();
	}

	/**
	 * 
	 * @param msg
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
	 * 
	 */
	public void mark()
	{
		mark("Manual");
	}

	/**
	 * 
	 * @return
	 */
    public long getLogStart()
    {
        return logStart;
    }
}
