package uk.org.smithfamily.mslogger.comms;

public class BTTimeoutException extends Exception
{
    private static final long serialVersionUID = -3210559164907080047L;

    public BTTimeoutException(final String msg)
    {
        super(msg);
    }

}
