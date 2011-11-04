package uk.org.smithfamily.mslogger.comms;


public class LostCommsException extends Exception
{

	public LostCommsException(Exception e)
	{
		super(e);
	}

	public LostCommsException()
	{
		super();
	}

	public LostCommsException(String msg)
    {
	    super(msg);
    }

    /**
	 * 
	 */
	private static final long	serialVersionUID	= 7348822989257405034L;

}
