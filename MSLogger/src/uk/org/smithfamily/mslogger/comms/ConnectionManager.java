package uk.org.smithfamily.mslogger.comms;

import java.io.*;
import java.util.*;

import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.os.*;
import android.util.Log;

/**
 * Main connection class that wrap all the Bluetooth stuff for communications
 */
abstract class ConnectionManager
{
    static class Reaper extends TimerTask
    {
        ConnectionManager parent;

        Reaper(ConnectionManager p)
        {
            this.parent = p;
        }

        @Override
        public void run()
        {
            parent.timerTriggered = true;
            parent.tearDown();
        }

    }

    protected Connection conn;
    protected InputStream mmInStream;
    protected OutputStream mmOutStream;
    protected Handler handler;
    protected static final long IO_TIMEOUT = 5000;
    volatile boolean timerTriggered = false;
    Timer t = new Timer();

    public enum ConnectionState
    {
        STATE_DISCONNECTED, STATE_CONNECTING, STATE_CONNECTED
    };

    protected volatile ConnectionState currentState = ConnectionState.STATE_DISCONNECTED;
    
    /**
     * Get the instance name
     * 
     * @return
     */
    public String getInstanceName()
    {
        return "ConnectionManager";
    }

    /**
     * Get the current Bluetooth connection state
     * 
     * @return
     */
    public ConnectionState getCurrentState()
    {
        return currentState;
    }

    /**
     * Get the handler
     * 
     * @return
     */
    public Handler getHandler()
    {
        return handler;
    }

    /**
     * Set the handler
     * 
     * @param handler
     */
    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    /**
     * Initialise the Bluetooth connection
     * 
     * @param handler
     * 
     * @param btAddr
     * @param adapter
     * @param h
     */
    public synchronized void init(Handler handler, String addr)
    {
        this.handler = handler;
        if (conn == null)
            conn = ConnectionFactory.INSTANCE.getConnection();
        conn.init(addr);
        if (currentState != ConnectionState.STATE_DISCONNECTED && !conn.isInitialised())
        {
            tearDown();
            setState(ConnectionState.STATE_DISCONNECTED);
        }
        timerTriggered = false;
    }

    /**
     * Set the current state of the Bluetooth connection
     * 
     * @param state
     */
    private void setState(ConnectionState state)
    {
        currentState = state;
        DebugLogManager.INSTANCE.log(getInstanceName()+".setState " + state, Log.DEBUG);
    }

    /**
     * Connect to the Bluetooth device
     * 
     * 
     * @throws IOException
     */
    public synchronized void connect() throws IOException
    {
        DebugLogManager.INSTANCE.log(getInstanceName()+".connect() : Current state " + currentState, Log.DEBUG);

        if (currentState != ConnectionState.STATE_DISCONNECTED)
        {
            return;
        }
        setState(ConnectionState.STATE_CONNECTING);

        try
        {
            DebugLogManager.INSTANCE.log(getInstanceName()+".connect() : Attempting connection", Log.DEBUG);

            conn.connect();
        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            delay(1000);

            try
            {
                conn.disconnect();
            }
            catch (Exception e1)
            {
                DebugLogManager.INSTANCE.logException(e1);
            }
            conn.switchSettings();
            try
            {
                conn.connect();
            }
            catch (IOException e1)
            {
                // that didn't work, switch back and throw
                DebugLogManager.INSTANCE.logException(e1);
                conn.switchSettings();
                throw e1;
            }
        }
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        DebugLogManager.INSTANCE.log(getInstanceName()+".connect() : Establishing connection", Log.DEBUG);

        // Get the BluetoothSocket input and output streams
        try
        {
            tmpIn = conn.getInputStream();
            tmpOut = conn.getOutputStream();
        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
        if (mmInStream != null && mmOutStream != null)
        {
            setState(ConnectionState.STATE_CONNECTED);
            DebugLogManager.INSTANCE.log(getInstanceName()+".connect() : Current state " + currentState, Log.DEBUG);

        }
        else
        {
            DebugLogManager.INSTANCE.log(getInstanceName()+" Failed to complete connection", Log.ERROR);
            setState(ConnectionState.STATE_DISCONNECTED);
            tearDown();
        }
        return;
    }

    /**
     * Check if connected and automatically connect if not
     * 
     * @throws IOException
     */
    protected synchronized void checkConnection() throws IOException
    {
        if (currentState == ConnectionState.STATE_DISCONNECTED)
        {
            connect();
        }
    }

    /**
     * Make the connection thread sleep
     * 
     * @param d Delay
     */
    protected void delay(int d)
    {
        DebugLogManager.INSTANCE.log(getInstanceName()+".delay(" + d + "ms)", Log.VERBOSE);
        try
        {
            Thread.sleep(d);
        }
        catch (InterruptedException e)
        {
            DebugLogManager.INSTANCE.log(getInstanceName()+" Sleep was interrupted", Log.ERROR);
        }
    }

    /**
     * Close input and output Bluetooth streams and socket
     */
    public synchronized void tearDown()
    {
        if(conn != null)
        {
            conn.tearDown();
            conn = null;
        }
        setState(ConnectionState.STATE_DISCONNECTED);
    }

    /**
     * Write data to the Bluetooth stream
     * 
     * @param data Data byte[] to send
     * @throws IOException
     */
    public synchronized void writeData(byte[] data) throws IOException
    {
        checkConnection();
        if (!conn.isConnected())
        {
            throw new IOException("Not connected");
        }

        DebugLogManager.INSTANCE.log(getInstanceName()+".writeData ", data, Log.VERBOSE);

        this.mmOutStream.write(data);

        this.mmOutStream.flush();
    }

    /**
     * Write data to the Bluetooth stream and return the result
     * 
     * @param data Data byte[] to send
     * @return
     * @throws IOException
     */
    public byte[] writeAndRead(byte[] data) throws IOException
    {
        checkConnection();
        writeData(data);
        return readBytes();
    }

    /**
     * Read bytes available on Bluetooth stream and return the resulting array
     * 
     * @return Array of bytes read from Bluetooth stream
     * @throws IOException
     */
    public byte[] readBytes() throws IOException
    {
        List<Byte> read = new ArrayList<Byte>();

        synchronized (this)
        {
            while (mmInStream.available() > 0)
            {
                byte b = (byte) mmInStream.read();
                read.add(b);
            }
        }

        byte[] result = new byte[read.size()];
        int i = 0;
        for (Byte b : read)
        {
            result[i++] = b;
        }

        DebugLogManager.INSTANCE.log(getInstanceName()+".readBytes", result, Log.VERBOSE);

        return result;
    }

    /**
     * Flush all data on the Bluetooth stream by reading until nothing is available to read
     * 
     * @throws IOException
     */
    public synchronized void flushAll() throws IOException
    {
        DebugLogManager.INSTANCE.log(getInstanceName()+".flushAll()", Log.DEBUG);
        checkConnection();

        mmOutStream.flush();
        while (mmInStream.available() > 0)
        {
            mmInStream.read();
        }
    }

    /**
     * Send status to the application that appear in the status bar
     * 
     * @param msgStr Message to be broadcasted
     */
    public void sendStatus(String msgStr)
    {
        DebugLogManager.INSTANCE.log(getInstanceName()+".sendStatus "+msgStr, Log.DEBUG);

        if (handler != null)
        {
            Message msg = handler.obtainMessage(MSLoggerApplication.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString(MSLoggerApplication.MSG_ID, msgStr);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
    }

    /**
     * Disconnect the current Bluetooth connection
     */
    public synchronized void disconnect()
    {
        DebugLogManager.INSTANCE.log(getInstanceName()+".disconnect()", Log.DEBUG);

        tearDown();
    }
}
