package uk.org.smithfamily.msdisp.parser.comms;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import uk.org.smithfamily.msdisp.parser.MsComm;

public class SocketComm extends MsComm
{
    private Socket            s;
    private InputStreamReader is;
    private OutputStream os;

    public SocketComm()
    {
        try
        {
            s = new Socket("10.0.2.2", 7893);
            os = s.getOutputStream();
            is = new InputStreamReader(s.getInputStream());
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

    @Override
    public boolean read(byte[] bytes, int nBytes)
    {
        try
        {
            int bytesRead=0;
            
            while (bytesRead < nBytes) {
                int result = s.getInputStream().read(bytes, bytesRead, nBytes - bytesRead);
                if (result == -1) break;
                bytesRead += result;
            }
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void flush()
    {

    }

    @Override
    public boolean write(byte[] buf)
    {
        boolean ok = true;
        try
        {
            os.write(buf);
            os.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            ok = false;
        }
        return ok;

    }

    @Override
    public String read(int nBytes)
    {
        StringBuffer bytes=new StringBuffer();
        
        try
        {
            int i = 0;
            while (i < nBytes && is.ready())
            {
                int c = is.read();
                if (c == -1)
                    break;
                bytes.append((char)c);
            }
            return bytes.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }

    }

}
