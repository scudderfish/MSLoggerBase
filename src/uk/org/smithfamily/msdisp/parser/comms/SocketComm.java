package uk.org.smithfamily.msdisp.parser.comms;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import uk.org.smithfamily.msdisp.parser.MsComm;

public class SocketComm extends MsComm
{
    public Socket s;
    public SocketComm()
    {
        try
        {
            s = new Socket("192.168.32.101", 7893);
            os = s.getOutputStream();
            is = s.getInputStream();
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
    }

    @Override
    public int port()
    {
        return super.port();
    }

}
