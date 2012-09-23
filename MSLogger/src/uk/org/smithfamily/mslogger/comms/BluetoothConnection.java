package uk.org.smithfamily.mslogger.comms;

import java.io.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.*;
import android.util.Log;

public enum BluetoothConnection implements Connection
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
        DebugLogManager.INSTANCE.log("BluetoothConnection()", Log.DEBUG);
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void init()
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.init()", Log.DEBUG);
        btAddr = ApplicationSettings.INSTANCE.getBluetoothMac();
    }

    @Override
    public boolean isInitialised()
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.isInitialised()", Log.DEBUG);
        
        return socket != null;
    }

    @Override
    public void connect() throws IOException
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.connect()", Log.DEBUG);
        
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
        DebugLogManager.INSTANCE.log("BluetoothConnection.disconnect()", Log.DEBUG);
        
        tearDown();
    }

    @Override
    public void switchSettings()
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.switchSettings()", Log.DEBUG);
        
        ApplicationSettings.INSTANCE.setBTWorkaround(!ApplicationSettings.INSTANCE.isBTWorkaround());

    }

    @Override
    public InputStream getInputStream() throws IOException
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.getInputStream()", Log.DEBUG);
        
        return this.mmInStream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.getOutputStream()", Log.DEBUG);
        
        return this.mmOutStream;
    }

  
    @Override
    public void tearDown()
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.tearDown()", Log.DEBUG);
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
        DebugLogManager.INSTANCE.log("BluetoothConnection.isConnected()", Log.DEBUG);
        
        return (socket != null && mmInStream != null && mmOutStream != null);
    }

    @Override
    public boolean connectionPossible()
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.connectionPossible()", Log.DEBUG);
        
        return adapter != null;
    }

    @Override
    public boolean connectionEnabled()
    {
        DebugLogManager.INSTANCE.log("BluetoothConnection.connectionEnabled()", Log.DEBUG);
        
        return adapter.isEnabled();
    }

}
