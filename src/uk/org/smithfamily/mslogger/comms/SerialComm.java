package uk.org.smithfamily.mslogger.comms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;

public class SerialComm extends MsComm
{
    private static final String TAG = "SerialComm";
    private InputStream         is;
    private OutputStream        os;

    enum ConnectionState
    {
        STATE_NONE, STATE_LISTEN, STATE_CONNECTING, STATE_CONNECTED
    };

    private ConnectionState   currentState = ConnectionState.STATE_NONE;

    private UUID              RFCOMM_UUID  = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    protected BluetoothSocket sock;

    private BluetoothAdapter  mAdapter;
    private ConnectThread     mConnectThread;
    
    private boolean           connected    = false;
    private boolean           writeBlocks  = false;

    protected void sendMessage(String msg)
    {
        Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.GENERAL_MESSAGE);
        broadcast.putExtra(ApplicationSettings.MESSAGE, msg);
        ApplicationSettings.INSTANCE.getContext().sendBroadcast(broadcast);

    }

    private String locateAdapter()
    {
        return ApplicationSettings.INSTANCE.getBluetoothMac();
    }

    public SerialComm()
    {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        currentState = ConnectionState.STATE_NONE;

    }

    @Override
    protected synchronized boolean openDevice()
    {
        return init();
    }

    public boolean init()
    {
        String btAddr = locateAdapter();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice remote = mBluetoothAdapter.getRemoteDevice(btAddr);
        return connect(remote);
    }

    synchronized boolean connect(BluetoothDevice device)
    {
        // Cancel any thread attempting to make a connection
        if (currentState == ConnectionState.STATE_CONNECTING)
        {
            if (mConnectThread != null)
            {
                mConnectThread.cancel();
                mConnectThread = null;
            }
        }

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(device);
        mConnectThread.start();
        setState(ConnectionState.STATE_CONNECTING);
        return true;
    }

    private void setState(ConnectionState state)
    {
        this.currentState = state;
    }

    /**
     * This thread runs while attempting to make an outgoing connection with a device. It runs straight through; the connection either succeeds or fails.
     */
    private class ConnectThread extends Thread
    {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device)
        {
            mmDevice = device;
            BluetoothSocket tmp = null;

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try
            {
                tmp = device.createRfcommSocketToServiceRecord(RFCOMM_UUID);
            }
            catch (IOException e)
            {
                Log.e(TAG, "create() failed", e);
            }
            mmSocket = tmp;
        }

        public void run()
        {
            Log.i(TAG, "BEGIN mConnectThread");
            setName("ConnectThread");

            // Always cancel discovery because it will slow down a connection
            mAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket
            try
            {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                mmSocket.connect();
            }
            catch (IOException e)
            {
                connectionFailed();
                // Close the socket
                try
                {
                    mmSocket.close();
                }
                catch (IOException e2)
                {
                    Log.e(TAG, "unable to close() socket during connection failure", e2);
                }
                // Start the service over to restart listening mode
                return;
            }

            // Reset the ConnectThread because we're done
            synchronized (SerialComm.this)
            {
                mConnectThread = null;
            }

            // Start the connected thread
            connected(mmSocket, mmDevice);
        }

        public void cancel()
        {
            try
            {
                mmSocket.close();
            }
            catch (IOException e)
            {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    private void connected(BluetoothSocket socket, BluetoothDevice device)
    {

        // Cancel the thread that completed the connection
        if (mConnectThread != null)
        {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the BluetoothSocket input and output streams
        try
        {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        }
        catch (IOException e)
        {
            Log.e(TAG, "temp sockets not created", e);
        }

        is = tmpIn;
        os = tmpOut;

        // Cancel any thread currently running a connection
        /*
         * if (mConnectedThread != null) { mConnectedThread.cancel(); mConnectedThread = null; }
         * 
         * // Start the thread to manage the connection and perform transmissions mConnectedThread = new ConnectedThread(socket); mConnectedThread.start();
         */
        // Send the name of the connected device back to the UI Activity
        sendMessage("Connected to " + device.getName());

        setState(ConnectionState.STATE_CONNECTED);
        if (is == null || os == null)
        {
            connectionFailed();
        }
    }

    /**
     * Indicate that the connection attempt failed and notify the UI Activity.
     */
    private void connectionFailed()
    {
        setState(ConnectionState.STATE_LISTEN);

        // Send a failure message back to the Activity
        sendMessage("Unable to connect device");
    }

    /**
     * Indicate that the connection was lost and notify the UI Activity.
     */
    private void connectionLost()
    {
        setState(ConnectionState.STATE_LISTEN);

        // Send a failure message back to the Activity

        sendMessage("Device connection was lost");
    }

    @Override
    protected boolean closeDevice(boolean force)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public synchronized void flush() throws LostCommsException
    {
        // TODO Auto-generated method stub

    }

    private synchronized void testConnection() throws LostCommsException
    {
        if (!connected && !openDevice())
            throw new LostCommsException();
    }

    public synchronized void read(byte[] bytes) throws LostCommsException
    {
        testConnection();
        try
        {
            int nBytes = bytes.length;
            int bytesRead = 0;
            byte[] buffer = new byte[bytes.length];
            // System.out.println("Want to read " + bytes.length + " is has " +
            // is.available());
            Log.d(ApplicationSettings.TAG, "Need to read " + bytes.length + " bytes");
            while (bytesRead < nBytes)
            {
                // @SuppressWarnings("unused")
                // int available = is.available();

                int result = is.read(buffer, bytesRead, nBytes - bytesRead);
                if (result == -1)
                    break;

                bytesRead += result;
                // Log.d(ApplicationSettings.TAG,"Read "+result+" bytes "+(bytes.length-bytesRead)+" to go.");
            }

            synchronized (bytes)
            {
                System.arraycopy(buffer, 0, bytes, 0, bytes.length);
            }
        }
        catch (IOException e)
        {
            throw new LostCommsException(e);
        }
        finally
        {
        }
    }

    public synchronized void write(byte[] buf)
    {
        if (!connected && !openDevice())
            return;
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

        }
        catch (IOException e)
        {
            Log.e(ApplicationSettings.TAG, "MsComm.write()", e);
            close();
        }
        return;
    }

    public synchronized String read(int nBytes)
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
            Log.e(ApplicationSettings.TAG, "MsComm.read()", e);
            close();
            return "";
        }

    }

}
