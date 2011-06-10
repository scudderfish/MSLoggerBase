package uk.org.smithfamily.msdisp.parser;

import uk.org.smithfamily.msdisp.parser.comms.SerialComm;

public class CommsFactory
{
    static final private CommsFactory instance = new CommsFactory();
    static final private MsComm comm = new SerialComm();

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
