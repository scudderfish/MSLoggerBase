package uk.org.smithfamily.mslogger.log;


public class FRDLogFile 
{
	private FRDLogFileHeader	header	= new FRDLogFileHeader(this);
	private FRDLogFileBody		body	= new FRDLogFileBody(this);

    public FRDLogFileHeader getHeader()
	{
		return header;
	}

	public FRDLogFileBody getBody()
	{
		return body;
	}
}
