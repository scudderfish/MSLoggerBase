package uk.org.smithfamily.mslogger.comms;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class SerialComm extends MsComm
{
    UUID                      RFCOMM_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    protected BluetoothSocket sock;

    private String locateAdapter()
    {
        return ApplicationSettings.INSTANCE.getBluetoothMac();
    }

    synchronized boolean init()
    {
        String btAddr = locateAdapter();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        // registerReceiver(mReceiver, filter); // Don't forget to unregister
        // during onDestroy
        // mBluetoothAdapter.startDiscovery();
        BluetoothDevice remote = mBluetoothAdapter.getRemoteDevice(btAddr);
        try
        {
            mBluetoothAdapter.cancelDiscovery();
            Method m = remote.getClass().getMethod("createRfcommSocket", new Class[] { int.class });
            sock = (BluetoothSocket) m.invoke(remote, Integer.valueOf(1));
            // sock = remote.createRfcommSocketToServiceRecord(RFCOMM_UUID);
            sock.connect();
            is = new BufferedInputStream(sock.getInputStream());
            os = sock.getOutputStream();
            setConnected(true);
        }
        catch (IOException e)
        {
            Log.e("BT", "IOException", e);
            DebugLogManager.INSTANCE.logException(e);
            return false;
        }
        catch (SecurityException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            return false;
        }
        catch (NoSuchMethodException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            return false;
        }
        catch (IllegalArgumentException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            return false;
        }
        catch (IllegalAccessException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            return false;
        }
        catch (InvocationTargetException e)
        {
            DebugLogManager.INSTANCE.logException(e);
            return false;
        }
        return true;
    }

    @Override
    protected synchronized boolean openDevice()
    {
        return init();
    }

    @Override
    protected synchronized boolean closeDevice(boolean force)
    {
        try
        {
            if (os != null)
            {
                os.close();
            }
            if (is != null)
            {
                is.close();
            }
            if (sock != null)
            {
                sock.close();
            }
            setConnected(false);
        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }

        return true;
    }
}
