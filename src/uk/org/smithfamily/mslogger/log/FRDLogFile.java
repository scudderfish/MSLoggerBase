package uk.org.smithfamily.mslogger.log;

/**
 * FRD log file class including an header and body
 * 
 * See http://www.efianalytics.com/TunerStudio/formattedRawDatalog.html
 */
public class FRDLogFile 
{
	private FRDLogFileHeader	header	= new FRDLogFileHeader(this);
	private FRDLogFileBody		body	= new FRDLogFileBody(this);

	/**
	 * @return An instance of FRD header
	 */
    public FRDLogFileHeader getHeader()
	{
		return header;
	}

    /**
     * @return An instance of FRD body
     */
	public FRDLogFileBody getBody()
	{
		return body;
	}
}
