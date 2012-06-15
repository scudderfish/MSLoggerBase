package uk.org.smithfamily.mslogger.log;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 */
public class FRDLogFileBody
{
	int	outpc = 0;
	private FRDLogFileRecord currentRecord;
    private FRDLogFile parent;

    /**
     * 
     * @param p
     */
	public FRDLogFileBody(FRDLogFile p)
	{
	    this.parent = p;
	}
	
	/**
	 * 
	 * @param ochBuffer
	 */
	public void addRecord(byte[] ochBuffer)
	{
	    currentRecord = new FRDLogFileRecord(this,ochBuffer);
	}

	/**
	 * 
	 * @return
	 */
	public FRDLogFileRecord getCurrentRecord()
	{
		return currentRecord;
	}

	/**
	 * 
	 * @param is
	 * @throws IOException
	 */
    public void read(FileInputStream is) throws IOException
    {
        currentRecord = new FRDLogFileRecord(this,is);
        
    }
    
    /**
     * 
     * @return
     */
    public FRDLogFile getParent()
    {
        return parent;
    }

}
