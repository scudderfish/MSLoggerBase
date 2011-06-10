package uk.org.smithfamily.msdisp.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class MsComm
{

    protected InputStream  is;
    protected OutputStream os;

    void setChunking(boolean _writeBlocks, int _interWriteDelay)
    {

    }

    public void setReadTimeouts(int _blockReadTimeout)
    {

    }

    public int port()
    {
        return 0;
    }

    public int rate()
    {
        return 0;
    }

    public void flush() throws IOException
    {
        os.flush();
    }

    public boolean read(byte[] bytes, int nBytes)
    {
        try
        {
            int bytesRead = 0;

            while (bytesRead < nBytes)
            {
                int result = is.read(bytes, bytesRead, nBytes - bytesRead);
                if (result == -1)
                    break;
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

    public String read(int nBytes)
    {
        StringBuffer bytes = new StringBuffer();

        try
        {
            int i = 0;
            while (i < nBytes && is.available() > 0)
            {
                int c = is.read();
                if (c == -1)
                    break;
                bytes.append((char) c);
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
