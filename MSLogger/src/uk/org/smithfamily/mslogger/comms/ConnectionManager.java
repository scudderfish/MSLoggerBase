package uk.org.smithfamily.mslogger.comms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.os.*;
import android.util.Log;

/**
 * Main connection class that wrap all the Bluetooth stuff for communications
 */
abstract class ConnectionManager
{
    protected Connection conn;
    protected InputStream mmInStream;
    protected OutputStream mmOutStream;
    protected Handler handler;
    protected static final long IO_TIMEOUT = 500;

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
    public void setHandler(final Handler handler)
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
    public synchronized void init(final Handler handler, final String addr)
    {
        this.handler = handler;
        if (conn == null)
        {
            conn = ConnectionFactory.INSTANCE.getConnection();
        }
        conn.init(addr);
        if ((currentState != ConnectionState.STATE_DISCONNECTED) && !conn.isInitialised())
        {
            tearDown();
            setState(ConnectionState.STATE_DISCONNECTED);
        }
    }

    /**
     * Set the current state of the Bluetooth connection
     * 
     * @param state
     */
    private void setState(final ConnectionState state)
    {
        currentState = state;
        DebugLogManager.INSTANCE.log(getInstanceName() + ".setState " + state, Log.DEBUG);
    }

    /**
     * Connect to the Bluetooth device
     * 
     * 
     * @throws IOException
     */
    public synchronized void connect() throws IOException
    {
        DebugLogManager.INSTANCE.log(getInstanceName() + ".connect() : Current state " + currentState, Log.DEBUG);

        if (currentState != ConnectionState.STATE_DISCONNECTED)
        {
            return;
        }
        setState(ConnectionState.STATE_CONNECTING);

        try
        {
            DebugLogManager.INSTANCE.log(getInstanceName() + ".connect() : Attempting connection", Log.DEBUG);

            conn.connect();
        }
        catch (final IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            delay(1000);

            try
            {
                conn.disconnect();
            }
            catch (final Exception e1)
            {
                DebugLogManager.INSTANCE.logException(e1);
            }
            conn.switchSettings();
            try
            {
                conn.connect();
            }
            catch (final IOException e1)
            {
                // that didn't work, switch back and throw
                DebugLogManager.INSTANCE.logException(e1);
                conn.switchSettings();
                throw e1;
            }
        }
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        DebugLogManager.INSTANCE.log(getInstanceName() + ".connect() : Establishing connection", Log.DEBUG);

        // Get the BluetoothSocket input and output streams
        try
        {
            tmpIn = conn.getInputStream();
            tmpOut = conn.getOutputStream();
        }
        catch (final IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
        if ((mmInStream != null) && (mmOutStream != null))
        {
            setState(ConnectionState.STATE_CONNECTED);
            DebugLogManager.INSTANCE.log(getInstanceName() + ".connect() : Current state " + currentState, Log.DEBUG);
        }
        else
        {
            DebugLogManager.INSTANCE.log(getInstanceName() + " Failed to complete connection", Log.ERROR);
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
    protected void delay(final int d)
    {
        try
        {
            Thread.sleep(d);
        }
        catch (final InterruptedException e)
        {
            DebugLogManager.INSTANCE.log(getInstanceName() + " Sleep was interrupted", Log.ERROR);
        }
    }

    /**
     * Close input and output Bluetooth streams and socket
     */
    public void tearDown()
    {
        if (conn != null)
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
    public synchronized void writeData(final byte[] data) throws IOException
    {
        checkConnection();
        if (!conn.isConnected())
        {
            throw new IOException("Not connected");
        }

        DebugLogManager.INSTANCE.log(getInstanceName() + ".writeData ", data, Log.VERBOSE);

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
    public byte[] writeAndRead(final byte[] data) throws IOException
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
        final List<Byte> read = new ArrayList<Byte>();

        synchronized (this)
        {
            while (mmInStream.available() > 0)
            {
                final byte b = (byte) mmInStream.read();
                read.add(b);
            }
        }

        final byte[] result = new byte[read.size()];
        int i = 0;
        for (final Byte b : read)
        {
            result[i++] = b;
        }

        DebugLogManager.INSTANCE.log(getInstanceName() + ".readBytes", result, Log.VERBOSE);

        return result;
    }

    /**
     * Flush all data on the Bluetooth stream by reading until nothing is available to read
     * 
     * @throws IOException
     */
    public synchronized void flushAll() throws IOException
    {
        DebugLogManager.INSTANCE.log(getInstanceName() + ".flushAll()", Log.DEBUG);
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
    public void sendStatus(final String msgStr)
    {
        DebugLogManager.INSTANCE.log(getInstanceName() + ".sendStatus " + msgStr, Log.DEBUG);

        if (handler != null)
        {
            final Message msg = handler.obtainMessage(MSLoggerApplication.MESSAGE_TOAST);
            final Bundle bundle = new Bundle();
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
        DebugLogManager.INSTANCE.log(getInstanceName() + ".disconnect()", Log.DEBUG);

        tearDown();
    }
}
