package uk.org.smithfamily.mslogger.parser;

public class MsCommThread extends Thread
{
	private volatile boolean	running;
	private byte[]				ochBuffer;
	private byte[]				readCommand;
	private MsComm				parent;
	private int					targetHertz;
	private int					readIndex	= 0;
	private int failCount = 0;

	public MsCommThread(byte[] readCommand, byte[] ochBuffer, MsComm msComm, int targetHertz)
	{
		this.readCommand = readCommand;
		this.ochBuffer = ochBuffer;
		this.parent = msComm;
		this.targetHertz = targetHertz;
	}

	public void setRunning(boolean b)
	{
		running = b;
	}

	@Override
	public void run()
	{
		running = true;
		while (running)
		{
			long time = System.currentTimeMillis();
			parent.write(readCommand);
			synchronized (ochBuffer)
			{
				parent.read(ochBuffer, ochBuffer.length);
				readIndex++;
			}
			ochBuffer.notifyAll();
			long now = System.currentTimeMillis();
			long delay = (now - time) - (1000 / targetHertz);
			if (delay > 0)
			{
				try
				{
					failCount = 0;
					Thread.sleep(delay);
				}
				catch (InterruptedException e)
				{
				}
			}
			else
			{
				failCount++;
				if(failCount >= 3)
				{
					failCount=0;
					targetHertz--;
				}
			}
		}

	}

	public int getReadIndex()
	{
		return readIndex;
	}

}
