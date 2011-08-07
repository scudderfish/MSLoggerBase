package uk.org.smithfamily.mslogger.ecuDef;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.comms.MsComm;
import uk.org.smithfamily.mslogger.log.FRDLogManager;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;

public abstract class Megasquirt implements Runnable
{
	public static final String	NEW_DATA	= "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.NEW_DATA";

	public static final String	CONNECTED	= "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.CONNECTED";

	protected Context			context;

	public abstract String getSignature();

	public abstract byte[] getOchCommand();

	public abstract byte[] getSigCommand();

	public abstract void loadConstants();

	public abstract void calculate(byte[] ochBuffer);

	public abstract String getLogHeader();

	public abstract String getLogRow();

	public abstract int getBlockSize();

	private long						lastTime	= System.currentTimeMillis();

	private volatile boolean			running		= false;
	protected MsComm					comm;
	private byte[]						ochBuffer;

	private boolean	logging;

	public void setRunning(boolean r)
	{
		running = r;
	}

	public void run()
	{
		running = true;
		comm = ApplicationSettings.INSTANCE.getComms();
		comm.openConnection();
		
		if(connected())
		{
		    broadcastConnected();
			loadConstants();
		}
		while (running)
		{
			if (connected())
			{
				comm.flush();
				getRuntimeVars();
				calculateValues();
				logValues();
				broadcastNewData();
				throttle();
			}
			else
			{
				sendMessage("Cannot connect to Megasquirt");
				delay(5000);
				if(connected())
		        {
		            broadcastConnected();
		            loadConstants();
		        }
			}
		}
		disconnect();
	
	}

	private void logValues()
	{
		if(!logging)
		{
			return;
		}
		try
		{
			FRDLogManager.INSTANCE.write(ochBuffer);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void disconnect()
	{
		comm.flush();
		comm.close();

	}

	private boolean connected()
	{
		return comm.isConnected();
	}

	private void sendMessage(String msg)
	{
		Intent broadcast = new Intent();
		broadcast.setAction(ApplicationSettings.GENERAL_MESSAGE);
		broadcast.putExtra(ApplicationSettings.MESSAGE, msg);
		context.sendBroadcast(broadcast);

	}

	private void delay(long pauseTime)
	{
		try
		{
			Thread.sleep(pauseTime);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

	}

	private void throttle()
	{
		long now = System.currentTimeMillis();
		long diff = now - lastTime;
		long pauseTime = (1000 / ApplicationSettings.INSTANCE.getHertz()) - diff;
		if (pauseTime > 0)
		{
			delay(pauseTime);
		}

	}

    private void broadcastConnected()
    {
        Intent broadcast = new Intent();
        broadcast.setAction(CONNECTED);
        // broadcast.putExtra(LOCATION, location);
        context.sendBroadcast(broadcast);
        sendMessage("Connected to "+this.getSignature());

    }

	private void broadcastNewData()
	{
		Intent broadcast = new Intent();
		broadcast.setAction(NEW_DATA);
		// broadcast.putExtra(LOCATION, location);
		context.sendBroadcast(broadcast);

	}

	private void getRuntimeVars()
	{
	    if(ochBuffer == null)
	    {
	        ochBuffer = new byte[this.getBlockSize()];
	    }
		comm.write(this.getOchCommand());
		comm.read(ochBuffer);
	}

	private void calculateValues()
	{
		calculate(ochBuffer);
	}

	public Megasquirt(Context c)
	{
		this.context = c;

	}

	public long number(String s)
	{

		// Prefixes: % = binary, $ = hexadecimal, ! = decimal.
		// Suffixes: Q = binary, H = hexadecimal, T = decimal.

		int base = 10;
		char ch = s.charAt(0);

		switch (ch)
		{
		case '%':
			base = 2;
			s = ' ' + s.substring(1);
			break;
		case '$':
			base = 16;
			s = ' ' + s.substring(1);
			break;
		case '!':
			base = 10;
			s = ' ' + s.substring(1);
			break;

		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
			ch = s.charAt(s.length() - 1);
			switch (ch)
			{
			case 'Q':
			case 'q':
				base = 2;
				break;
			case 'H':
			case 'h':
				base = 16;
				break;
			case 'T':
			case 't':
				base = 10;
				break;

			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
				// Everthing is ok so far.
				break;

			default:
				throw new NumberFormatException(s);

			}
			break;
		default:
			throw new NumberFormatException(s);

		}

		long n = Long.valueOf(s, base);
		return n;
	}

	public int numberW(String s)
	{
		long nn = number(s);
		if (nn > 65536 || nn < 0)
			throw new NumberFormatException(s);
		return (int) nn;

	}

	public int numberB(String s)
	{
		long nn = number(s);
		if (nn > 255 || nn < 0)
			throw new NumberFormatException(s);

		return (int) nn;
	}

	protected int getWord(byte[] ochBuffer, int i)
	{

		return (ochBuffer[i] & 0xFF) * 256 + ochBuffer[i + 1] & 0xFF;
	}

	protected int getByte(byte[] ochBuffer, int i)
	{
		return (int) ochBuffer[i] & 0xFF;
	}

	protected long timeNow()
	{
		return System.currentTimeMillis();
	}

	protected double tempCvt(int t)
	{
		return (t - 32.0) * 5.0 / 9.0;
	}


	public boolean initialised()
	{
		comm = ApplicationSettings.INSTANCE.getComms();
		ochBuffer = new byte[this.getBlockSize()];
		boolean connected = comm.openConnection();

		if (connected)
		{
			String sig = comm.getSignature(this.getSigCommand());
			connected = getSignature().equals(sig);
		}

		return connected;

	}

	public float getValue(String channel)
	{
		float value = 0;
		Class<?> c = this.getClass();
		try
		{
			Field f = c.getField(channel);
			value = f.getFloat(this);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	public void startLogging()
	{
		if(logging)
		{
			return;
		}
		Date now = new Date();

		String fileName = DateFormat.format("yyyyMMddkkmmss", now).toString() + ".msl";
		File logFile = new File(ApplicationSettings.INSTANCE.getDataDir(), fileName);
		// Datalog.INSTANCE.open(logFile);

		logging = true;

	}

	public void stopLogging()
	{
		logging = false;
	}

	protected int getBits(byte[] pageBuffer, int i, int _bitLo, int _bitHi)
	{
		int val = 0;
		byte b = pageBuffer[i];
	
		long mask = ((1 << (_bitHi - _bitLo + 1)) - 1) << _bitLo;
		val = (int) ((b & mask) >> _bitLo);
	
		return val;
	}

}
