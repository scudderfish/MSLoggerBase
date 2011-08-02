package uk.org.smithfamily.mslogger.log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


public class FRDLogFile implements Iterable<FRDLogFileRecord>
{
	private FRDLogFileHeader	header	= new FRDLogFileHeader(this);
	private FRDLogFileBody		body	= new FRDLogFileBody(this);
    private FileInputStream is;

   

    public FRDLogFileHeader getHeader()
	{
		return header;
	}

	public FRDLogFileBody getBody()
	{
		return body;
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
