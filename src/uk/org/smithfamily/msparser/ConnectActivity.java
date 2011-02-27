package uk.org.smithfamily.msparser;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.*;
import android.content.*;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

class Receiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND.equals(action))
        {
            // Get the BluetoothDevice object from the Intent
            BluetoothDevice device = intent
                    .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            // Add the name and address to an array
            // adapter to show in a ListView
            Log.v("BlueTooth Testing",
                    device.getName() + "\n" + device.getAddress());
        }
    }

}

public class ConnectActivity extends Activity
{
    UUID RFCOMM_UUID =UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    /** Called when the activity is first created. */
    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new Receiver();


    
    @Override
    protected void onDestroy()
    {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences prefs = getSharedPreferences("connectivity",MODE_PRIVATE);
        String btAddr = prefs.getString("btadapter", null);
        if(btAddr == null)
        {
            btAddr = locateAdapter();
        }
        if(btAddr != null)
        {
            Editor prefsEdit = prefs.edit();
            prefsEdit.putString("btadpater", btAddr);
        }
        
        if(testConnection())
        {
            finish();
        }
        setContentView(R.layout.main);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        //IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        
        if (!mBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0);
        } else
        {
           
           // registerReceiver(mReceiver, filter); // Don't forget to unregister
                                                 // during onDestroy
            //mBluetoothAdapter.startDiscovery();

            BluetoothDevice remote = mBluetoothAdapter.getRemoteDevice(btAddr);
            try
            {
                BluetoothSocket sock = remote.createRfcommSocketToServiceRecord(RFCOMM_UUID);
                sock.connect();
                sock.close();
            } catch (IOException e)
            {
                Log.e("BT", "IOException", e);
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
                    .getBondedDevices();
            // If there are paired devices
            if (pairedDevices.size() > 0)
            {
                // Loop through paired devices
                for (BluetoothDevice device : pairedDevices)
                {
                    // Add the name and address to an array adapter to show
                    // in a ListView
                    Log.v("BlueTooth Testing",
                            device.getName() + "\n" + device.getAddress());
                }
            }
        }
        super.onCreate(savedInstanceState);
        
    }



    private boolean testConnection()
    {
        // TODO Auto-generated method stub
        return false;
    }



    private String locateAdapter()
    {
        return "00:12:6F:03:BC:63";
    }
}
