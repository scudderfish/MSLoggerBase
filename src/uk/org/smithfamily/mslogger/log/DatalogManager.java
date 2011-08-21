package uk.org.smithfamily.mslogger.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.text.format.DateFormat;

public enum DatalogManager
{
	INSTANCE;

	PrintWriter		writer		= null;
	private String	fileName	= null;

	public synchronized String getFilename()
	{
		if (fileName == null)
		{
			fileName = DateFormat.format("yyyyMMddkkmmss", new Date()).toString() + ".msl";
		}
		return fileName;
	}

	public void write(String logRow)
	{
		if (writer == null)
		{

			File logFile = new File(ApplicationSettings.INSTANCE.getDataDir(), getFilename());
			try
			{
				writer = new PrintWriter(logFile);
				Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();
				String signature = ecuDefinition.getSignature();
				writer.println("\"" + signature + "\"");
				writer.println(ecuDefinition.getLogHeader());
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.println(logRow);
	}

	public void stopLog()
	{
		writer.close();
		writer = null;
		fileName = null;
	}

}
