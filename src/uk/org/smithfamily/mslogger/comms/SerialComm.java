package uk.org.smithfamily.mslogger.comms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class SerialComm extends MsComm
{
    UUID                      RFCOMM_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    protected BluetoothSocket sock;

    private String locateAdapter()
    {
        return ApplicationSettings.INSTANCE.getBluetoothMac();
    }

    synchronized boolean init()
    {
        closeDevice(true);
        String btAddr = locateAdapter();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice remote = mBluetoothAdapter.getRemoteDevice(btAddr);
        try
        {
            mBluetoothAdapter.cancelDiscovery();
            Method m = remote.getClass().getMethod("createRfcommSocket", new Class[] { int.class });
            BluetoothSocket tmpSock = (BluetoothSocket) m.invoke(remote, Integer.valueOf(1));
            // sock = remote.createRfcommSocketToServiceRecord(RFCOMM_UUID);
            tmpSock.connect();
            InputStream tmpIs = tmpSock.getInputStream();
            OutputStream tmpOs = tmpSock.getOutputStream();
            setConnected(true);
            sock = tmpSock;
            is = tmpIs;
            os = tmpOs;
        }
        catch (IOException e)
        {
            Log.e("BT", "IOException", e);
            DebugLogManager.INSTANCE.logException(e);

            return false;
        }
        catch (Exception e)
        {
            DebugLogManager.INSTANCE.logException(e);
            return false;
        }
        finally
        {
            if (!isConnected())
            {
                closeDevice(true);
            }
        }
        return true;
    }

    @Override
    protected synchronized boolean openDevice()
    {
        return init();
    }

    @Override
    protected synchronized boolean closeDevice(boolean force)
    {
        close(os);
        os = null;
        close(is);
        is = null;
        close(sock);
        sock = null;
        return true;
    }

    private void close(BluetoothSocket sock2)
    {
        try
        {
            if (sock2 != null)
            {
                sock2.close();
            }
        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
    }

    private void close(InputStream is)
    {
        try
        {
            if (is != null)
                is.close();
        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }

    }

    private void close(OutputStream os)
    {
        try
        {
            if (os != null)
                os.close();
        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }

    }
}
