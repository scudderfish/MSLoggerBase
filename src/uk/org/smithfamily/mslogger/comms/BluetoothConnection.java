package uk.org.smithfamily.mslogger.comms;

import java.io.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.*;
import android.util.Log;

public enum BluetoothConnection implements IConnection
{
    INSTANCE;
    private String btAddr;
    private BluetoothAdapter adapter;
    private BluetoothDevice remote;
    private BluetoothSocket socket;

    private InputStream mmInStream;
    private OutputStream mmOutStream;

    BluetoothConnection()
    {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void init()
    {
        btAddr = ApplicationSettings.INSTANCE.getBluetoothMac();
    }

    @Override
    public boolean isInitialised()
    {
        
        return socket != null;
    }

    @Override
    public void connect() throws IOException
    {
        try
        {
            remote = adapter.getRemoteDevice(btAddr);
            socket = BTSocketFactory.getSocket(remote);
        } catch (Exception e)
        {
            DebugLogManager.INSTANCE.log("Failed to get a socket!", Log.ERROR);
            throw new IOException(e.getLocalizedMessage());
        }
        adapter.cancelDiscovery();
        socket.connect();
        mmInStream = socket.getInputStream();
        mmOutStream = socket.getOutputStream();
    }

    @Override
    public void disconnect() throws IOException
    {
        tearDown();
    }

    @Override
    public void switchSettings()
    {
        ApplicationSettings.INSTANCE.setBTWorkaround(!ApplicationSettings.INSTANCE.isBTWorkaround());

    }

    @Override
    public InputStream getInputStream() throws IOException
    {
        return this.mmInStream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException
    {
        return this.mmOutStream;
    }

  
    @Override
    public void tearDown()
    {
        DebugLogManager.INSTANCE.log("tearDown()", Log.DEBUG);
        if (mmInStream != null)
        {
            try
            {
                mmInStream.close();
            } catch (IOException e)
            {
            }
            mmInStream = null;
        }
        if (mmOutStream != null)
        {
            try
            {
                mmOutStream.close();
            } catch (IOException e)
            {
            }
            mmOutStream = null;
        }

        if (socket != null)
        {
            try
            {
                DebugLogManager.INSTANCE.log("ECUFingerprint teardown socket close()", Log.DEBUG);

                socket.close();
            } catch (IOException e)
            {
            }
            socket = null;
        }

    }

    @Override
    public boolean isConnected()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean connectionPossible()
    {
        return adapter != null;
    }

    @Override
    public boolean connectionEnabled()
    {
        return adapter.isEnabled();
    }

}
