package uk.org.smithfamily.msdisp.parser.comms;

import android.bluetooth.BluetoothAdapter;

public class SerialComm
{

    public SerialComm()
    {

    }

    boolean init()
    {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null)
        {
            // Device does not support Bluetooth
            return false;
        }
        return true;
    }
}
