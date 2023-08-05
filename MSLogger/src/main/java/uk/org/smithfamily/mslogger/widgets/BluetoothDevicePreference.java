package uk.org.smithfamily.mslogger.widgets;

import java.util.Set;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;
import android.preference.ListPreference;
import android.util.AttributeSet;

import androidx.core.app.ActivityCompat;

/**
 * Borrowed from http://stackoverflow.com/questions/2936919/is-it-possible-to-load-listpreference-items-from-an-adapter
 */
public class BluetoothDevicePreference extends ListPreference {
    /**
     *
     */
    public BluetoothDevicePreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
        if (bta != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
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

    /**
     *
     */
    public BluetoothDevicePreference(Context context)
    {
        this(context, null);
    }
}
