package uk.org.smithfamily.mslogger.ecuDef;

import java.io.*;
import java.util.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.*;
import android.content.Context;
import android.os.*;
import android.util.Log;

public class ECUFingerprint implements Runnable
{

    private static final String TAG         = "ECUFingerprint";
    public static final String  UNKNOWN     = "UNKNOWN";
    private UUID                RFCOMM_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private InputStream         mmInStream;
    private OutputStream        mmOutStream;
    private BluetoothSocket     socket;

    private boolean             located     = false;
    private Context             context;
    private Handler             handler;
    private BluetoothAdapter    adapter;

    public ECUFingerprint(Context c, Handler h, BluetoothAdapter mBluetoothAdapter)
    {
        this.adapter = mBluetoothAdapter;
        this.context = c;
        this.handler = h;
    }

    private void sendStatus(String msgStr)
    {
        Message msg = handler.obtainMessage(MSLoggerApplication.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(MSLoggerApplication.MSG_ID, msgStr);
        msg.setData(bundle);
        handler.sendMessage(msg);

    }

    @Override
    public void run()
    {
        Thread.currentThread().setName("ECUFingerprintThread");
        String fingerprint = UNKNOWN;
        while (!located)
        {

            String btAddr = ApplicationSettings.INSTANCE.getBluetoothMac();
            sendStatus("Attempting to connect");
            BluetoothDevice remote = adapter.getRemoteDevice(btAddr);
            try
            {
                socket = remote.createRfcommSocketToServiceRecord(RFCOMM_UUID);
            }
            catch (IOException e)
            {
                DebugLogManager.INSTANCE.logException(e);
                sendStatus("Connection failed");
                Log.e(TAG, "create() failed", e);
            }
            adapter.cancelDiscovery();
            try
            {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                socket.connect();
                sendStatus("Connected");
            }
            catch (IOException e)
            {
                sendStatus("Connection failed");

                // ErrorReporter.getInstance().handleException(e);
                DebugLogManager.INSTANCE.logException(e);
                delay(1000);

                // Close the socket
                try
                {
                    socket.close();
                }
                catch (IOException e2)
                {
                    // ErrorReporter.getInstance().handleException(e2);
                    Log.e(TAG, "unable to close() socket during connection failure", e2);
                }
            }

            try
            {
                fingerprint = getFingerprint(socket, remote);
                located = true;
            }
            catch (IOException e)
            {
                tearDown();
                delay(1000);
            }
        }

        Message msg = handler.obtainMessage(MSLoggerApplication.GOT_SIG, fingerprint);
        handler.sendMessage(msg);
        tearDown();

    }

    private void tearDown()
    {
        if (mmInStream != null)
        {
            try
            {
                mmInStream.close();
            }
            catch (IOException e)
            {
            }
            mmInStream = null;
        }
        if (mmOutStream != null)
        {
            try
            {
                mmOutStream.close();
            }
            catch (IOException e)
            {
            }
            mmOutStream = null;
        }

        if (socket != null)
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
            }
            socket = null;
        }
    }

    private String getFingerprint(BluetoothSocket socket, BluetoothDevice remote) throws IOException
    {
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        // sendMessage("Establishing connection");

        // Get the BluetoothSocket input and output streams
        try
        {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            Log.e(TAG, "temp sockets not created", e);
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;

        return fingerprint();

    }

    private String fingerprint() throws IOException
    {
        sendStatus("Probing the ECU");
        byte[] probeCommand1 = { 'Q' };
        byte[] probeCommand2 = { 'S' };
        byte[] bootCommand = { 'X' };
        int i = 0;
        String sig = UNKNOWN;

        while (i++ < 20)
        {
            byte[] response = writeAndRead(probeCommand1, 500);

            try
            {
                if (response != null && response.length > 1)
                {
                    sig = processResponse(response);
                }
                else
                {
                    response = writeAndRead(probeCommand2, 500);
                    if (response != null && response.length > 1)
                    {
                        sig = processResponse(response);
                    }
                }
                if (!UNKNOWN.equals(sig))
                    break;
            }
            catch (BootException e)
            {
                response = writeAndRead(bootCommand, 500);
            }
        }
        sendStatus(sig);
        return sig;
    }

    private byte[] writeAndRead(byte[] probeCommand1, int d) throws IOException
    {
        mmOutStream.flush();
        while (mmInStream.available() > 0)
        {
            mmInStream.read();
        }
        mmOutStream.write(probeCommand1);
        delay(d);

        if (mmInStream.available() == 0)
            return null;
        List<Byte> read = new ArrayList<Byte>();

        while (mmInStream.available() > 0)
        {
            byte b = (byte) mmInStream.read();
            read.add(b);
        }
        byte[] result = new byte[read.size()];
        int i = 0;
        for (Byte b : read)
        {
            result[i++] = b;
        }
        return result;
    }

    private void delay(int d)
    {
        try
        {
            Thread.sleep(d);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String processResponse(byte[] response) throws BootException
    {
        String result = new String(response);
        if (result.contains("Boot>"))
        {
            throw new BootException();
        }
        if (response == null)
            return UNKNOWN;
        
        if(response.length == 1 && response[0] != 20 )
            return UNKNOWN;
        
        if((response[0] != 'M' && response[0] != 'J')  || (response[1] != 'S' && response[1] != 'o' && response[1] != 'i'))
            return UNKNOWN;
        
        return result;
    }

    @SuppressWarnings("serial")
    private class BootException extends Exception
    {

    }
}
