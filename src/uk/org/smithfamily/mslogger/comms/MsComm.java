package uk.org.smithfamily.mslogger.comms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

public abstract class MsComm extends Observable
{

	protected InputStream	is;
	protected OutputStream	os;
	private boolean			connected			= false;
	private boolean			writeBlocks			= false;
	private int				interWriteDelay		= 0;
	private int				totalReadTimeout	= 0;
	protected long			lastComms			= System.currentTimeMillis();
	protected long			throttleWait		= 0;

	protected abstract boolean openDevice();

	protected abstract boolean closeDevice(boolean force);

	protected void throttle()
	{
		return;
		/*
		 * long now = System.currentTimeMillis(); if ((lastComms + throttleWait)
		 * <= now) { lastComms = now; return; } long duration = now - lastComms
		 * + throttleWait; try { Thread.sleep(duration); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	protected MsComm()
	{
	}

	public void setChunking(boolean _writeBlocks, int _interWriteDelay)
	{
		writeBlocks = _writeBlocks;
		interWriteDelay = _interWriteDelay;
		throttleWait = interWriteDelay;
	}

	public void setReadTimeouts(int _blockReadTimeout)
	{
		totalReadTimeout = _blockReadTimeout;
	}

	public int port()
	{
		return 0;
	}

	public int rate()
	{
		return 0;
	}

	public void flush()
	{
		if (!connected)
			return;
		try
		{
			os.flush();
			while (is.available() > 0)
			{
				is.read();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public boolean read(byte[] bytes)
	{
		if (!connected && !openDevice())
			return false;
		try
		{
			int nBytes = bytes.length;
			throttle();
			int bytesRead = 0;
			byte[] buffer = new byte[bytes.length];

			while (bytesRead < nBytes)
			{
				int result = is.read(buffer, bytesRead, nBytes - bytesRead);
				if (result == -1)
					break;
				bytesRead += result;
			}
			synchronized (bytes)
			{
				System.arraycopy(buffer, 0, bytes, 0, bytes.length);
			}
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			close();
			return false;
		}
	}

	public boolean write(byte[] buf)
	{
		if (!connected && !openDevice())
			return false;

		boolean ok = true;
		try
		{
			throttle();

			if (writeBlocks)
			{
				os.write(buf);
			}
			else
			{
				for (int n = 0; n < buf.length; n++)
				{
					os.write(buf[n]);
					throttle();
				}
			}
			os.flush();

		}
		catch (IOException e)
		{
			e.printStackTrace();
			close();
			ok = false;
		}
		return ok;

	}

	public String read(int nBytes)
	{
		if (!connected && !openDevice())
			return null;

		StringBuffer bytes = new StringBuffer();

		try
		{
			int i = 0;
			while (i < nBytes && is.available() > 0)
			{
				int c = is.read();
				if (c == -1)
					break;
				bytes.append((char) c);
			}
			return bytes.toString();
		}
		catch (IOException e)
		{

			e.printStackTrace();
			close();
			return "";
		}

	}

	public boolean isConnected()
	{
		return connected;
	}

	public void setConnected(boolean connected)
	{
		this.connected = connected;
		notifyObservers();
	}

	public InputStream getIs()
	{
		return is;
	}

	public OutputStream getOs()
	{
		return os;
	}

	protected void open()
	{
		openDevice();
	}

	protected void close()
	{
		closeDevice(true);
	}

	public boolean openConnection()
	{
		open();
		return isConnected();
	}

	public String getSignature(byte[] sigCommand)
	{
		String lastVal = "NotConnected";
		if (!openConnection())
			return lastVal;

		String sig = "NotGotItYet";
		for (int x = 0; x < 5 && !lastVal.equals(sig); x++)
		{
			lastVal = sig;
			write(sigCommand);
			sig = read(63);
		}
		return sig;
	}

}
