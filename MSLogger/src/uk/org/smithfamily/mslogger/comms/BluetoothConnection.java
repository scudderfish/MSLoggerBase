package uk.org.smithfamily.mslogger.comms;

import java.io.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.*;
import android.util.Log;

public class BluetoothConnection implements Connection
{
    private String btAddr;
    private BluetoothAdapter adapter;
    private BluetoothDevice remote;
    private BluetoothSocket socket;

    private InputStream mmInStream;
    private OutputStream mmOutStream;

    BluetoothConnection()
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection()", Log.DEBUG);
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void init(String addr)
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.init()", Log.DEBUG);
        btAddr = addr;
    }

    @Override
    public boolean isInitialised()
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.isInitialised()", Log.DEBUG);
        
        return socket != null;
    }

    @Override
    public void connect() throws IOException
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.connect()", Log.DEBUG);
        
        try
        {
            remote = adapter.getRemoteDevice(btAddr);
            socket = BTSocketFactory.getSocket(remote);
        } catch (Exception e)
        {
            if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("Failed to get a socket!", Log.ERROR);
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
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.disconnect()", Log.DEBUG);
        
        tearDown();
    }

    @Override
    public void switchSettings()
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.switchSettings()", Log.DEBUG);
        
        ApplicationSettings.INSTANCE.setBTWorkaround(!ApplicationSettings.INSTANCE.isBTWorkaround());

    }

    @Override
    public InputStream getInputStream() throws IOException
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.getInputStream()", Log.DEBUG);
        
        return this.mmInStream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.getOutputStream()", Log.DEBUG);
        
        return this.mmOutStream;
    }

  
    @Override
    public void tearDown()
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.tearDown()", Log.DEBUG);
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
                if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("ECUFingerprint teardown socket close()", Log.DEBUG);

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
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.isConnected()", Log.DEBUG);
        
        return (socket != null && mmInStream != null && mmOutStream != null);
    }

    @Override
    public boolean connectionPossible()
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.connectionPossible()", Log.DEBUG);
        
        return adapter != null;
    }

    @Override
    public boolean connectionEnabled()
    {
        if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("BluetoothConnection.connectionEnabled()", Log.DEBUG);
        
        return adapter.isEnabled();
    }

}
