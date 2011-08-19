package uk.org.smithfamily.mslogger.comms;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import uk.org.smithfamily.mslogger.log.DebugLogManager;


public class SocketComm extends MsComm
{
    public Socket s;
    public SocketComm()
    {
    }
 
    @Override
    protected boolean openDevice()
    {
    	if(isConnected())
    		return true;
        try
        {
            s = new Socket("192.168.1.192", 7893);
            os = s.getOutputStream();
            is = s.getInputStream();
            setConnected(true);
        }
        catch (UnknownHostException e)
        {
			DebugLogManager.INSTANCE.logException(e);
        }
        catch (IOException e)
        {
			DebugLogManager.INSTANCE.logException(e);
        }
        return isConnected();
    }

    @Override
    protected boolean closeDevice(boolean force)
    {
        try
        {
            is.close();
            os.close();
            s.close();
            setConnected(false);
        }
        catch (IOException e)
        {
			DebugLogManager.INSTANCE.logException(e);
        }
        return !isConnected();
    }

}
