package uk.org.smithfamily.msdisp.parser;

import uk.org.smithfamily.msdisp.parser.comms.SocketComm;

public class CommsFactory
{
    static final private CommsFactory instance = new CommsFactory();

    static public CommsFactory getInstance()
    {
        return instance;
    }

    private CommsFactory()
    {
    }
    public MsComm getComInstance()
    {
        return new SocketComm();
    }
}
