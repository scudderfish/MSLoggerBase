package uk.org.smithfamily.mslogger.comms;

import java.util.Observable;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import android.util.Log;

public abstract class MsComm extends Observable
{
    private int    interWriteDelay = 0;
    protected long lastComms       = System.currentTimeMillis();

    protected abstract boolean openDevice();

    protected abstract boolean closeDevice(boolean force);

    public abstract void write(byte[] cmd) throws LostCommsException;

    public abstract void read(byte[] buff) throws LostCommsException;

    public abstract String read(int len);

    protected synchronized void throttle()
    {

        long now = System.currentTimeMillis();
        if ((lastComms + interWriteDelay) <= now)
        {
            lastComms = now;
            return;
        }
        long duration = now - lastComms + interWriteDelay;
        try
        {
            Thread.sleep(duration);
        }
        catch (InterruptedException e)
        {
            Log.e(ApplicationSettings.TAG, "throttle()", e);
        }

    }

    protected MsComm()
    {
    }

    public synchronized void open()
    {
        openDevice();
    }

    public synchronized void close()
    {
        closeDevice(true);
    }

    public synchronized String getSignature(byte[] sigCommand) throws LostCommsException
    {
        String lastVal = "NotConnected";

        String sig = "NotGotItYet";
        for (int x = 0; x < 10 && !lastVal.equals(sig); x++)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            lastVal = sig;
            write(sigCommand);
            sig = read(63);
        }
        return sig;
    }

    public synchronized void setInterWriteDelay(int interWriteDelay)
    {
        this.interWriteDelay = interWriteDelay;
    }

}
