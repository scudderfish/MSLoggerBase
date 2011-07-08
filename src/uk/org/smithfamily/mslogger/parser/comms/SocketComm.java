package uk.org.smithfamily.mslogger.parser.comms;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import uk.org.smithfamily.mslogger.parser.MsComm;

public class SocketComm extends MsComm
{
    public Socket s;
    public SocketComm()
    {
    }

    @Override
    public int port()
    {
        return super.port();
    }

    @Override
    protected boolean openDevice()
    {
        try
        {
            s = new Socket("192.168.32.101", 7893);
            os = s.getOutputStream();
            is = s.getInputStream();
            setConnected(true);
        }
        catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return !isConnected();
    }

}
