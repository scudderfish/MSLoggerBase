package uk.org.smithfamily.msdisp.parser.comms;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import uk.org.smithfamily.msdisp.parser.MsComm;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class SerialComm extends MsComm
{
    UUID RFCOMM_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    protected BluetoothSocket sock;
 
    private String locateAdapter()
    {
        return "00:12:6F:03:BC:63";
    }

    boolean init()
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
            sock = remote.createRfcommSocketToServiceRecord(RFCOMM_UUID);
            sock.connect();
            is=sock.getInputStream();
            os=sock.getOutputStream();
            setConnected(true);
        }
        catch (IOException e)
        {
            Log.e("BT", "IOException", e);
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0)
        {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices)
            {
                // Add the name and address to an array adapter to show
                // in a ListView
                Log.v("BlueTooth Testing", device.getName() + "\n" + device.getAddress()+"\n");
            }
        }
        return true;
    }

    @Override
    protected boolean openDevice()
    {
        return init();
    }

    @Override
    protected boolean closeDevice(boolean force)
    {
        try
        {
            os.close();
            is.close();
            sock.close();
            setConnected(false);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return true;
    }
}
