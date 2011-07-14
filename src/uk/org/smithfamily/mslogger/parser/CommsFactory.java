package uk.org.smithfamily.mslogger.parser;

import uk.org.smithfamily.mslogger.parser.comms.SerialComm;

public enum CommsFactory
{
    INSTANCE;
    static private MsComm comm = new SerialComm();
    
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
