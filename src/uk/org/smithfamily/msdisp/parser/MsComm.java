package uk.org.smithfamily.msdisp.parser;

import android.app.Notification;
import android.app.NotificationManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;

import uk.org.smithfamily.msparser.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
public abstract class MsComm extends Observable
{

    protected InputStream  is;
    protected OutputStream os;
    private boolean connected = false;

    protected abstract boolean openDevice();
    protected abstract boolean closeDevice(boolean force);
    private NotificationManager mNM;

    protected MsComm()
    {
    }
 
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
        if(!connected) return;
        os.flush();
    }

    public boolean read(byte[] bytes, int nBytes)
    {
        if(!connected && !openDevice()) return false;
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
            close();
            return false;
        }
    }

    public boolean write(byte[] buf)
    {
        if(!connected && !openDevice()) return false;

        boolean ok = true;
        try
        {
            os.write(buf);
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
        if(!connected && !openDevice()) return null;
        
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
    protected void notifyMsg(int res)
    {
        if(res == 0)
        {
            mNM.cancelAll();
            return;
        }
        Context context = MsDatabase.getInstance().getContext();
        if(context == null)
        {
            return;
        }
        
        if(mNM == null)
        {
            mNM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        }
        CharSequence text = context.getText(res);
        Notification notification = new Notification(R.drawable.injector,text,System.currentTimeMillis());
        
        mNM.notify(res, notification );
    }
    protected void open()
    {
        openDevice();
        notifyMsg(R.string.connected);
    }
    protected void close()
    {
        closeDevice(true);
        notifyMsg(R.string.disconnected);
    }
}
