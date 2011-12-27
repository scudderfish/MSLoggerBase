package uk.org.smithfamily.mslogger.log;

import java.io.FileInputStream;
import java.io.IOException;


public class FRDLogFileBody
{
	int	outpc=0;
	private FRDLogFileRecord currentRecord;
    private FRDLogFile parent;

	public FRDLogFileBody(FRDLogFile p)
	{
	    this.parent = p;
	}
	public void addRecord(byte[] ochBuffer)
	{
	    currentRecord = new FRDLogFileRecord(this,ochBuffer);
	}

	public FRDLogFileRecord getCurrentRecord()
	{
		return currentRecord;
	}

    public void read(FileInputStream is) throws IOException
    {
        currentRecord = new FRDLogFileRecord(this,is);
        
    }
    public FRDLogFile getParent()
    {
        return parent;
    }

}
