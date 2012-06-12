package uk.org.smithfamily.mslogger.widgets;

//Borrowed from http://stackoverflow.com/questions/2936919/is-it-possible-to-load-listpreference-items-from-an-adapter

import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;

public class BluetoothDevicePreference extends ListPreference
{
    public BluetoothDevicePreference(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
        if (bta != null)
        {
	        Set<BluetoothDevice> pairedDevices = bta.getBondedDevices();
	        CharSequence[] entries = new CharSequence[pairedDevices.size()];
	        CharSequence[] entryValues = new CharSequence[pairedDevices.size()];
	        int i = 0;
	        for (BluetoothDevice dev : pairedDevices)
	        {
	            entries[i] = dev.getName();
	            entryValues[i] = dev.getAddress();
	            i++;
	        }
	        setEntries(entries);
	        setEntryValues(entryValues);
        }
    }

    public BluetoothDevicePreference(Context context)
    {
        this(context, null);
    }
}
