package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.acra.ErrorReporter;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DatalogManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.log.FRDLogManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public abstract class Megasquirt
{
    public int      dispRPM;
    public double   dispMAP;
    public double   dispAFR;
    public double   dispCLT;
    public double   dispIAT;
    public double   dispADV;

    private boolean simulated = false;

    private int     errorCount = 0;

    enum ConnectionState
    {
        STATE_NONE, STATE_LISTEN, STATE_CONNECTING, STATE_CONNECTED
    };

    private ConnectionState    currentState = ConnectionState.STATE_NONE;

    private UUID               RFCOMM_UUID  = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    protected BluetoothSocket  sock;

    private BluetoothAdapter   mAdapter;
    private ConnectThread      mConnectThread;
    private ConnectedThread    mConnectedThread;

    public static final String NEW_DATA     = "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.NEW_DATA";

    public static final String CONNECTED    = "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.CONNECTED";
    public static final String DISCONNECTED = "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.DISCONNECTED";
    public static final String TAG          = ApplicationSettings.TAG;

    protected Context          context;

    public abstract Set<String> getSignature();

    public abstract byte[] getOchCommand();

    public abstract byte[] getSigCommand();

    public abstract void loadConstants(boolean simulated) throws IOException;

    public abstract void calculate(byte[] ochBuffer) throws IOException;

    public abstract String getLogHeader();

    public abstract String getLogRow();

    public abstract int getBlockSize();

    public abstract int getSigSize();
    
    public abstract int getPageActivationDelay();

    public abstract int getInterWriteDelay();

    public abstract int getCurrentTPS();

    public abstract void mapDispValues();
    
    public abstract void initGauges();
    public abstract String[] defaultGauges();

    private long    lastTime      = System.currentTimeMillis();

    private long    logStart      = lastTime;
    private byte[]  ochBuffer;

    private boolean running;
    private boolean logging;
    private boolean constantsLoaded;
    private boolean signatureChecked;
    private String  trueSignature = "Unknown";

    protected int table(int i1, String name)
    {
        return TableManager.INSTANCE.table(i1, name);
    }

    protected double tempCvt(int t)
    {
        if (isSet("CELSIUS"))
        {
            return (t - 32.0) * 5.0 / 9.0;
        }
        else
        {
            return t;
        }
    }

    public void start()
    {
        DebugLogManager.INSTANCE.log("Megasquirt.start()");

        ochBuffer = new byte[this.getBlockSize()];
        constantsLoaded = false;
        signatureChecked = false;
        initialiseConnection();

    }

    public void stop()
    {
        DebugLogManager.INSTANCE.log("Megasquirt.stop()");
        disconnect();
        sendMessage("");
    }

    public void initialiseConnection()
    {
        // Cancel any thread attempting to make a connection
        if (mConnectThread != null)
        {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null)
        {
            mConnectedThread.cancelConnection();
            mConnectedThread = null;
        }
        setState(ConnectionState.STATE_LISTEN);

        String btAddr = ApplicationSettings.INSTANCE.getBluetoothMac();
        mAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothDevice remote = mAdapter.getRemoteDevice(btAddr);

        connect(remote);
    }

    private void logValues()
    {

        if (!logging)
        {
            return;
        }
        try
        {
            FRDLogManager.INSTANCE.write(ochBuffer);
            DatalogManager.INSTANCE.write(getLogRow());

        }
        catch (IOException e)
        {
 //           ErrorReporter.getInstance().handleException(e);
            DebugLogManager.INSTANCE.logException(e);

            Log.e(ApplicationSettings.TAG, "Megasquirt.logValues()", e);
        }
    }

    private void disconnect()
    {
        if (simulated)
            return;
        if (mConnectedThread != null)
        {
            mConnectedThread.cancelConnection();
        }
        FRDLogManager.INSTANCE.close();
        DatalogManager.INSTANCE.close();

    }

    protected void sendMessage(String msg)
    {
        Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.GENERAL_MESSAGE);
        broadcast.putExtra(ApplicationSettings.MESSAGE, msg);
        context.sendBroadcast(broadcast);

    }

    private void delay(long pauseTime)
    {
        try
        {
            Thread.sleep(pauseTime);
        }
        catch (InterruptedException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            Log.e(ApplicationSettings.TAG, "Megasquirt.delay()", e);
        }

    }

    private void broadcast(String action)
    {
        Intent broadcast = new Intent();
        broadcast.setAction(action);
        // broadcast.putExtra(LOCATION, location);
        context.sendBroadcast(broadcast);

    }

    public Megasquirt(Context c)
    {
        this.context = c;
    }

    public boolean initialised()
    {

        boolean connected = false;
        return connected;

    }

    protected double timeNow()
    {
        return (System.currentTimeMillis() - logStart) / 1000.0;
    }

    public double getValue(String channel)
    {
        // Short circuit the obvious ones
        if ("dispRPM".equals(channel))
        {
            return dispRPM;
        }

        if ("dispMAP".equals(channel))
        {
            return dispMAP;
        }
        if ("dispAFR".equals(channel))
        {
            return dispAFR;
        }
        if ("dispCLT".equals(channel))
        {
            return dispCLT;
        }
        if ("dispIAT".equals(channel))
        {
            return dispIAT;
        }
        if ("dispADV".equals(channel))
        {
            return dispADV;
        }

        double value = 0;
        Class<?> c = this.getClass();
        try
        {
            Field f = c.getDeclaredField(channel);
            value = f.getDouble(this);
        }
        catch (Exception e)
        {
            DebugLogManager.INSTANCE.log("Failed to get value for " + channel);
            Log.e(ApplicationSettings.TAG, "Megasquirt.getValue()", e);
        }
        return value;
    }

    public void startLogging()
    {
        logging = true;
        logStart = System.currentTimeMillis();
        DebugLogManager.INSTANCE.log("startLogging()");

    }

    public void stopLogging()
    {
        DebugLogManager.INSTANCE.log("stopLogging()");
        logging = false;
        DatalogManager.INSTANCE.close();
        FRDLogManager.INSTANCE.close();
    }

    protected double round(double v)
    {
        return Math.floor(v * 100 + .5) / 100;
    }

    protected boolean isSet(String name)
    {
        return ApplicationSettings.INSTANCE.isSet(name);
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
//                ErrorReporter.getInstance().handleException(e);
                DebugLogManager.INSTANCE.logException(e);

                Log.e(TAG, "create() failed", e);
            }
            mmSocket = tmp;
        }

        public void run()
        {
            if(mmSocket == null)
            {
                sendMessage("No connection!");
                return;
            }
            Log.i(TAG, "BEGIN mConnectThread");
            setName("ConnectThread");
            sendMessage("Starting connection");

            
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
//                ErrorReporter.getInstance().handleException(e);
                DebugLogManager.INSTANCE.logException(e);
                connectionFailed();
                // Close the socket
                try
                {
                    mmSocket.close();
                }
                catch (IOException e2)
                {
//                    ErrorReporter.getInstance().handleException(e2);
                    Log.e(TAG, "unable to close() socket during connection failure", e2);
                }
                // Start the service over to restart listening mode
                return;
            }

            // Reset the ConnectThread because we're done
            synchronized (Megasquirt.this)
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
//                ErrorReporter.getInstance().handleException(e);

                DebugLogManager.INSTANCE.logException(e);
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    private void setState(ConnectionState state)
    {
        this.currentState = state;
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

    private void connected(BluetoothSocket socket, BluetoothDevice device)
    {

        // Cancel the thread that completed the connection
        if (mConnectThread != null)
        {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread currently running a connection

        if (mConnectedThread != null)
        {
            mConnectedThread.cancelConnection();
            mConnectedThread = null;
        }

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        // Send the name of the connected device back to the UI Activity
        sendMessage("Connected to " + device.getName());

        setState(ConnectionState.STATE_CONNECTED);
    }

    /**
     * Indicate that the connection attempt failed and notify the UI Activity.
     */
    private void connectionFailed()
    {
        setState(ConnectionState.STATE_LISTEN);

        // Send a failure message back to the Activity
        sendMessage("Unable to connect device");
        broadcast(DISCONNECTED);
    }

    /**
     * Indicate that the connection was lost and notify the UI Activity.
     */
    private void connectionLost()
    {
        setState(ConnectionState.STATE_LISTEN);

        // Send a failure message back to the Activity

        sendMessage("Device connection was lost");
        broadcast(DISCONNECTED);
        if (++errorCount < 3 && running)
        {
            initialiseConnection();
        }
        else
        {
            errorCount = 0;
    }
    }

    /**
     * This thread runs during a connection with a remote device. It handles all incoming and outgoing transmissions.
     */
    private class ConnectedThread extends Thread
    {
        private final BluetoothSocket mmSocket;
        private final InputStream     mmInStream;
        private final OutputStream    mmOutStream;
        Timer                         t = new Timer("IOTimer", true);
		private boolean	timerTriggered;

        public ConnectedThread(BluetoothSocket socket)
        {
            Log.d(TAG, "create ConnectedThread");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            sendMessage("Establishing connection");

            // Get the BluetoothSocket input and output streams
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            }
            catch (IOException e)
            {
                ErrorReporter.getInstance().handleException(e);
                DebugLogManager.INSTANCE.logException(e);
                Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            running = true;
            Log.i(TAG, "BEGIN mConnectedThread");
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            try
            {
                flush();

                if (!verifySignature())
                {
                    connectionLost();
                    return;
                }
                if (!constantsLoaded)
                {
                    loadConstants(simulated);
                    constantsLoaded = true;
                }
                while (true)
                {
                    getRuntimeVars();
                    calculateValues();
                    logValues();
                    mapDispValues();
                    broadcast(NEW_DATA);
                    // If we've got this far, reset the error counter
                    errorCount = 0;
                }
            }
            catch (IOException e)
            {
                if (running)
                {
                    ErrorReporter.getInstance().handleException(e);
                }
                DebugLogManager.INSTANCE.logException(e);
                Log.e(TAG, "disconnected", e);
                connectionLost();
            }
            if (t != null)
            {
                t.cancel();
                t = null;
            }
        }

        private void flush() throws IOException
        {
            mmOutStream.flush();
            try
            {
                Thread.sleep(getPageActivationDelay());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            while (mmInStream.available() > 0)
            {
                mmInStream.read();
            }
        }

        private boolean verifySignature() throws IOException
        {
            boolean verified = false;
            String msSig = null;
            if (simulated || signatureChecked)
            {
                verified = true;
            }
            else
            {
                byte[] sigCommand = getSigCommand();
                sendMessage("Verifying MS");
                Set<String> signatures = Megasquirt.this.getSignature();
                
                msSig = getSignature(sigCommand,Megasquirt.this.getSigSize());
                verified = signatures.contains(msSig);
            }
            if (verified)
            {
                sendMessage("Connected to " + msSig);
                trueSignature = msSig;
                signatureChecked = true;
                broadcast(CONNECTED);
            }
            else
            {
                sendMessage("Signature error! " + msSig);
            }
            return verified;
        }

        private void getRuntimeVars() throws IOException
        {
            // Debug.startMethodTracing("getRuntimeVars");
            if (simulated)
            {
                MSSimulator.INSTANCE.getNextRTV(ochBuffer);
                return;
            }
            write(getOchCommand());
            read(ochBuffer);
            // Debug.stopMethodTracing();
        }

        private void calculateValues() throws IOException
        {
            calculate(ochBuffer);
        }

        private void write(byte[] command) throws IOException
        {
            this.mmOutStream.write(command);
            try
            {
                Thread.sleep(getInterWriteDelay());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        private void read(byte[] bytes) throws IOException
        {
        	timerTriggered = false;
        	TimerTask cancelTask = new TimerTask()
            {
                @Override
                public void run()
                {
                	timerTriggered = true;
                    cancelConnection();
                }
            };

            t.schedule(cancelTask, 1000);

            int nBytes = bytes.length;
            int bytesRead = 0;
            byte[] buffer = new byte[bytes.length];
            while (bytesRead < nBytes)
            {

            	try
            	{
	                int result = mmInStream.read(buffer, bytesRead, nBytes - bytesRead);
	                if (result == -1)
	                    break;
	
	                bytesRead += result;
            	}
            	catch(IOException e)
            	{
            		if(timerTriggered)
            		{
            			DebugLogManager.INSTANCE.log("read timeout occured : read "+bytesRead+" : expected "+nBytes);
            		}
            		throw e;
            	}
            }

            synchronized (bytes)
            {
                System.arraycopy(buffer, 0, bytes, 0, bytes.length);
            }
            cancelTask.cancel();
            t.purge();
        }

        private String getSignature(byte[] sigCommand, int i) throws IOException
        {
            String sig1 = "NoSigReadYet";
            String sig2 = "Not here";
            byte[] buf = new byte[i];
            do
            {
                write(sigCommand);

                sig1 = sig2;
                read(buf);
                sig2 = new String(buf);
                flush();
            }
            while (!sig1.equals(sig2));
            return sig1;
        }

        public void cancelConnection()
        {
            running = false;
            try
            {
                mmSocket.close();
            }
            catch (IOException e)
            {
//                ErrorReporter.getInstance().handleException(e);
                DebugLogManager.INSTANCE.logException(e);
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    protected void getPage(byte[] pageBuffer, byte[] pageSelectCommand, byte[] pageReadCommand) throws IOException
    {
        if (mConnectedThread == null)
        {
            throw new IOException("Not connected!");
        }

        mConnectedThread.write(pageSelectCommand);
        delay(getPageActivationDelay());
        if (pageReadCommand != null)
        {
            mConnectedThread.write(pageReadCommand);
        }
        delay(getPageActivationDelay());
        mConnectedThread.read(pageBuffer);
    }

    public String getTrueSignature()
    {
        return trueSignature;
    }
}
