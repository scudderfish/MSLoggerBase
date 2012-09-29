package uk.org.smithfamily.mslogger.comms;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

/**
 * Bluetooth class that create the socket with the Bluetooth device
 */
public class BTSocketFactory
{
	private static UUID	RFCOMM_UUID	= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	/**
	 * Create a socket to the current specified Bluetooth device and return it
	 * 
	 * @param device   The Bluetooth device
	 * @return
	 * @throws Exception
	 */
	public static BluetoothSocket getSocket(BluetoothDevice device) throws Exception
	{
		DebugLogManager.INSTANCE.log("getSocket()", Log.DEBUG);
        
		BluetoothSocket tmp = null;
		if (ApplicationSettings.INSTANCE.isBTWorkaround())
		{
			try
			{
				tmp = createWorkaroundSocket(device);
			}
			catch (Exception e)
			{
				DebugLogManager.INSTANCE.logException(e);
				// That didn't work, lets switch it off for the next go around
				ApplicationSettings.INSTANCE.setBTWorkaround(false);
			}
		}
		else
		{
			try
			{
				DebugLogManager.INSTANCE.log("getSocket().standard", Log.DEBUG);
		        
				tmp = device.createRfcommSocketToServiceRecord(RFCOMM_UUID);
			}
			catch (IOException e)
			{
				// That didn't work, let's try the work around
				DebugLogManager.INSTANCE.logException(e);

				try
				{
					tmp = createWorkaroundSocket(device);

					ApplicationSettings.INSTANCE.setBTWorkaround(true);
				}
				catch (Exception e1)
				{
					// We're boned, nothing works
					DebugLogManager.INSTANCE.logException(e);
					throw e1;
				}
			}
		}
		return tmp;
	}

	/**
	 * Create a work around socket when the first method to create the Bluetooth socket doesn't work
	 * See http://stackoverflow.com/questions/3397071/service-discovery-failed-exception-using-bluetooth-on-android
	 * 
	 * @param device   The Bluetooth device
	 * @return
	 * @throws Exception
	 */
	private static BluetoothSocket createWorkaroundSocket(BluetoothDevice device) throws Exception
	{
		DebugLogManager.INSTANCE.log("createWorkaroundSocket()", Log.DEBUG);
        
		Method m = device.getClass().getMethod("createRfcommSocket", new Class[] { int.class });
		BluetoothSocket tmp = (BluetoothSocket) m.invoke(device, Integer.valueOf(1));
		return tmp;
	}

}
