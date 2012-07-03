package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.comms.Connection;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Probe the ECU to figure out what it is
 */
public class ECUFingerprint implements Runnable
{

    public static final String  UNKNOWN = "UNKNOWN";
    
    private boolean             located = false;
    private Handler             handler;
    private BluetoothAdapter    adapter;

    /**
     * 
     * @param h
     * @param mBluetoothAdapter
     */
    public ECUFingerprint(Handler h, BluetoothAdapter mBluetoothAdapter)
    {
        this.adapter = mBluetoothAdapter;
        this.handler = h;
    }


    /**
     * 
     */
    @Override
    public void run()
    {
        Thread.currentThread().setName("ECUFingerprintThread");
        String fingerprint = UNKNOWN;
        DebugLogManager.INSTANCE.log("Starting fingerprint", Log.INFO);
        String btAddr = ApplicationSettings.INSTANCE.getBluetoothMac();
        
        while (!located)
        {
            Connection.INSTANCE.init(btAddr, adapter,handler);
            try
            {
                fingerprint = getFingerprint();
                located = true;
            }
            catch (IOException e)
            {
                DebugLogManager.INSTANCE.logException(e, Log.ERROR);
                Connection.INSTANCE.tearDown();
                delay(1000);
            }
        }
 //       conn.tearDown();
        Message msg = handler.obtainMessage(MSLoggerApplication.GOT_SIG, fingerprint);
        handler.sendMessage(msg);
        
    }

    /**
     * 
     * @return
     * @throws IOException
     */
    private String getFingerprint() throws IOException
    {   
        return fingerprint();
    }
    
    /**
     * 
     * @param msgStr
     */
    private void sendStatus(String msgStr)
    {
        DebugLogManager.INSTANCE.log(msgStr, Log.INFO);

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
     * Get a signature from the ECU.  This is complicated by different firmwares responding to different commands
     * @return
     * @throws IOException
     */
    private String fingerprint() throws IOException
    {
        sendStatus("Probing the ECU");
        byte[] probeCommand1 = { 'Q' };
        byte[] probeCommand2 = { 'S' };
        byte[] bootCommand = { 'X' };
        int i = 0;
        String sig = UNKNOWN;

        // IF we don't get it in 20 goes, we're not talking to a Megasquirt
        while (i++ < 20)
        {
            byte[] response = Connection.INSTANCE.writeAndRead(probeCommand1, 500, false);

            try
            {
                if (response != null && response.length > 1)
                {
                    sig = processResponse(response);
                }
                else
                {
                    response = Connection.INSTANCE.writeAndRead(probeCommand2, 500, false);
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
                /* My ECU also occasionally goes to a Boot> prompt on start up (dodgy electrics) so if we see that, force 
                * the ECU to start.
                */
                response = Connection.INSTANCE.writeAndRead(bootCommand, 500,false);
            }
        }
        sendStatus(sig);
        return sig;
    }

    /**
     * 
     * @param d
     */
    private void delay(int d)
    {
        try
        {
            Thread.sleep(d);
        }
        catch (InterruptedException e)
        {
            // Swallow
        }
    }

    /**
     * Attempt to figure out the data we got back from the device
     * @param response
     * @return
     * @throws BootException
     */
    public static String processResponse(byte[] response) throws BootException
    {
        String result = new String(response);
        if (result.contains("Boot>"))
        {
            throw new BootException();
        }
        
        if (response == null)
            return UNKNOWN;

        // Early ECUs only respond with one byte
        if (response.length == 1 && response[0] != 20)
            return UNKNOWN;

        if (response.length <= 1)
            return UNKNOWN;
        
        // Examine the first few bytes and see if it smells of one of the things an MS may say to us.
        if ((response[0] != 'M' && response[0] != 'J') || (response[1] != 'S' && response[1] != 'o' && response[1] != 'i'))
            return UNKNOWN;

        // Looks like we have a Megasquirt
        return result;
    }
}
