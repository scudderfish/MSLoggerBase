package uk.org.smithfamily.mslogger.comms;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class BTSocketFactory
{
	private static UUID	RFCOMM_UUID	= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	public static BluetoothSocket getSocket(BluetoothDevice device) throws Exception
	{
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
				//That didn't work, lets switch it off for the next go around
				ApplicationSettings.INSTANCE.setBTWorkaround(false);
			}
		}
		else
		{
			try
			{
				tmp = device.createRfcommSocketToServiceRecord(RFCOMM_UUID);
			}
			catch (IOException e)
			{
				// That didn't work, let's try the work around
				DebugLogManager.INSTANCE.logException(e);

				try
				{
					tmp = createWorkaroundSocket(device);
					// That did work, let's make it permanment
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

	private static BluetoothSocket createWorkaroundSocket(BluetoothDevice device) throws Exception
	{
		Method m = device.getClass().getMethod("createRfcommSocket", new Class[] { int.class });
		BluetoothSocket tmp = (BluetoothSocket) m.invoke(device, Integer.valueOf(1));
		return tmp;
	}

}
