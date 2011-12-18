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

public class ECUFingerprint implements Runnable
{

    public static final String  UNKNOWN = "UNKNOWN";
    
    private boolean             located = false;
    private Handler             handler;
    private BluetoothAdapter    adapter;

    public ECUFingerprint(Handler h, BluetoothAdapter mBluetoothAdapter)
    {
        this.adapter = mBluetoothAdapter;
        this.handler = h;
    }


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
                Connection.INSTANCE.tearDown();
                delay(1000);
            }
        }
 //       conn.tearDown();
        Message msg = handler.obtainMessage(MSLoggerApplication.GOT_SIG, fingerprint);
        handler.sendMessage(msg);
        
    }

    private String getFingerprint() throws IOException
    {
   
        return fingerprint();

    }
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
            byte[] response = Connection.INSTANCE.writeAndRead(probeCommand1, 500);

            try
            {
                if (response != null && response.length > 1)
                {
                    sig = processResponse(response);
                }
                else
                {
                    response = Connection.INSTANCE.writeAndRead(probeCommand2, 500);
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
                response = Connection.INSTANCE.writeAndRead(bootCommand, 500);
            }
        }
        sendStatus(sig);
        return sig;
    }

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

    public static String processResponse(byte[] response) throws BootException
    {
        String result = new String(response);
        if (result.contains("Boot>"))
        {
            throw new BootException();
        }
        if (response == null)
            return UNKNOWN;

        if (response.length == 1 && response[0] != 20)
            return UNKNOWN;

        if(response.length <= 1)
            return UNKNOWN;
        
        if ((response[0] != 'M' && response[0] != 'J') || (response[1] != 'S' && response[1] != 'o' && response[1] != 'i'))
            return UNKNOWN;

        return result;
    }
}
