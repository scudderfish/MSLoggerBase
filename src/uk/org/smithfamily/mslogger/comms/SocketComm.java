package uk.org.smithfamily.mslogger.comms;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import android.util.Log;


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
            is = new BufferedInputStream(s.getInputStream());
            setConnected(true);
        }
        catch (UnknownHostException e)
        {
            Log.e(ApplicationSettings.TAG,"SocketComm.openDevice()",e);
        }
        catch (IOException e)
        {
            Log.e(ApplicationSettings.TAG,"SocketComm.openDevice()",e);
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
            Log.e(ApplicationSettings.TAG,"SocketComm.closeDevice()",e);
        }
        return !isConnected();
    }

}
