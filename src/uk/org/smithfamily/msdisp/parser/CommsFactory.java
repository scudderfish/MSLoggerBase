package uk.org.smithfamily.msdisp.parser;

import uk.org.smithfamily.msdisp.parser.comms.SocketComm;

public class CommsFactory
{
    static final private CommsFactory instance = new CommsFactory();
    static final private MsComm comm = new SocketComm();

    static public CommsFactory getInstance()
    {
        return instance;
    }

    private CommsFactory()
    {
    }
    public MsComm getComInstance()
    {
        return comm;
    }

    public String probe()
    {
        String response = "MS1/Extra format 029y3 *********";
        
        
        
        
        return response;
        
    
    }
}
