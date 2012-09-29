package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.comms.CRC32Exception;
import uk.org.smithfamily.mslogger.comms.ECUConnectionManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.os.*;
import android.util.Log;

/**
 * Probe the ECU to figure out what it is
 */
public class ECUFingerprint implements Runnable
{
    public static final String  UNKNOWN = "UNKNOWN";

    private static final int MAX_ERROR = 5;
    
    private boolean             located = false;
    private Handler             handler;
    private int errorCount = 0;
 
    /**
     * 
     * @param h
     * @param mBluetoothAdapter
     */
    public ECUFingerprint(Handler h)
    {
        this.handler = h;
    }

    /**
     * 
     */
    @Override
    public void run()
    {
        Thread.currentThread().setName("ECUFingerprintThread:" + System.currentTimeMillis());
        String fingerprint = UNKNOWN;
        DebugLogManager.INSTANCE.log("Starting fingerprint", Log.INFO);
        
        while (!located  && errorCount < MAX_ERROR)
        {
            ECUConnectionManager.getInstance().init(handler,ApplicationSettings.INSTANCE.getBluetoothMac());
            try
            {
                fingerprint = getFingerprint();
                located = true;
            }
            catch (IOException e)
            {
                DebugLogManager.INSTANCE.logException(e);
                ECUConnectionManager.getInstance().tearDown();
                delay(1000);
                errorCount++;
            }
            catch (CRC32Exception e)
            {
                DebugLogManager.INSTANCE.logException(e);
                ECUConnectionManager.getInstance().tearDown();
                delay(1000);
            }
        }

        Message msg;
        
        if(errorCount < MAX_ERROR)
        { 
            msg = handler.obtainMessage(MSLoggerApplication.GOT_SIG, fingerprint);
            located = true;
        }
        else
        {
            msg = handler.obtainMessage(MSLoggerApplication.COMMS_ERROR);
        }
        handler.sendMessage(msg);
    }

    /**
     * 
     * @return
     * @throws IOException
     * @throws CRC32Exception 
     */
    private String getFingerprint() throws IOException, CRC32Exception
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
     * @throws CRC32Exception 
     */
    private String fingerprint() throws IOException, CRC32Exception
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
            byte[] response = ECUConnectionManager.getInstance().writeAndRead(probeCommand1, 500, false);

            try
            {
                if (response != null && response.length > 1)
                {
                    sig = processResponse(response);
                }
                else
                {
                    response = ECUConnectionManager.getInstance().writeAndRead(probeCommand2, 500, false);
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
                response = ECUConnectionManager.getInstance().writeAndRead(bootCommand, 500,false);
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
