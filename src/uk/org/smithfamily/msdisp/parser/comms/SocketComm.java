package uk.org.smithfamily.msdisp.parser.comms;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import uk.org.smithfamily.msdisp.parser.ByteString;
import uk.org.smithfamily.msdisp.parser.MsComm;

public class SocketComm extends MsComm
{
    private Socket            s;
    private InputStreamReader is;

    public SocketComm()
    {
        try
        {
            s = new Socket("10.0.2.2", 7893);
            is = new InputStreamReader(s.getInputStream());
        } catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int port()
    {
        // TODO Auto-generated method stub
        return super.port();
    }

    @Override
    public boolean read(ByteBuffer bytes, int nBytes)
    {
        is.read(buffer, offset, length)
        return super.read(bytes, nBytes);
    }

    @Override
    public void flush()
    {

    }

    @Override
    public boolean write(ByteString buf)
    {
        boolean ok = true;
        try
        {
            s.getOutputStream().write(buf.bytes());
        } catch (IOException e)
        {
            e.printStackTrace();
            ok = false;
        }
        return ok;

    }

}
