package uk.org.smithfamily.mslogger.parser.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import uk.org.smithfamily.mslogger.parser.Repository;

public class FRDLogFile implements Iterable<FRDLogFileRecord>
{
	private FRDLogFileHeader	header	= new FRDLogFileHeader(this);
	private FRDLogFileBody		body	= new FRDLogFileBody(this);
    private File logFile;
    private FileInputStream is;

   

    public FRDLogFileHeader getHeader()
	{
		return header;
	}

	public FRDLogFileBody getBody()
	{
		return body;
	}

	public void open(String fileName) throws IOException
	{

	    FRDLogManager.INSTANCE.setState(FRDLogManager.State.Reading);
	    File dir = new File("/sdcard"+Repository.INSTANCE.getDataDir());

        logFile = new File(dir, fileName);

        is = new FileInputStream(logFile);
        
        header = new FRDLogFileHeader(this,is);
	}

	@Override
	public Iterator<FRDLogFileRecord> iterator()
	{
		return new Iterator<FRDLogFileRecord>()
		{

			@Override
			public boolean hasNext()
			{
				
				try
                {
                    boolean b = is.available()>0;
                    if(!b)
                    {
                        FRDLogManager.INSTANCE.setState(FRDLogManager.State.Logging);
                    }
                    return b;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return false;
                }
			}

			@Override
			public FRDLogFileRecord next()
			{
			    try
                {
                    body.read(is);
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
				return body.getCurrentRecord();
			}

			@Override
			public void remove()
			{
				// TODO Auto-generated method stub

			}
		};
	}
}
