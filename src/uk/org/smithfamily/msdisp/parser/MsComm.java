package uk.org.smithfamily.msdisp.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

public abstract class MsComm extends Observable
{

    protected InputStream  is;
    protected OutputStream os;
    private boolean        connected        = false;
    private boolean        writeBlocks      = false;
    private int            interWriteDelay  = 0;
    private int            totalReadTimeout = 0;
    protected long         lastComms        = System.currentTimeMillis();
    protected long         throttleWait     = 0;

    protected abstract boolean openDevice();

    protected abstract boolean closeDevice(boolean force);

    protected void throttle()
    {
        long now = System.currentTimeMillis();
        if ((lastComms + throttleWait) <= now)
        {
            lastComms = now;
            return;
        }
        long duration = now - lastComms + throttleWait;
        try
        {
            Thread.sleep(duration);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected MsComm()
    {
    }

    void setChunking(boolean _writeBlocks, int _interWriteDelay)
    {
        writeBlocks = _writeBlocks;
        interWriteDelay = _interWriteDelay;
        throttleWait = interWriteDelay;
    }

    public void setReadTimeouts(int _blockReadTimeout)
    {
        totalReadTimeout = _blockReadTimeout;
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
        if (!connected)
            return;
        os.flush();
    }

    public boolean read(byte[] bytes, int nBytes)
    {
        if (!connected && !openDevice())
            return false;
        try
        {
            throttle();
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
            close();
            return false;
        }
    }

    public boolean write(byte[] buf)
    {
        if (!connected && !openDevice())
            return false;

        boolean ok = true;
        try
        {
            throttle();

            if (writeBlocks)
            {
                os.write(buf);
            }
            else
            {
                for (int n = 0; n < buf.length; n++)
                {
                    os.write(buf[n]);
                    throttle();
                }
            }
            os.flush();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            close();
            ok = false;
        }
        return ok;

    }

    public String read(int nBytes)
    {
        if (!connected && !openDevice())
            return null;

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
            close();
            return "";
        }

    }

    public boolean isConnected()
    {
        return connected;
    }

    public void setConnected(boolean connected)
    {
        this.connected = connected;
        notifyObservers();
    }

    public InputStream getIs()
    {
        return is;
    }

    public OutputStream getOs()
    {
        return os;
    }

    protected void open()
    {
        openDevice();
    }

    protected void close()
    {
        closeDevice(true);
    }

    public boolean openConnection()
    {
        open();
        return isConnected();
    }
}
