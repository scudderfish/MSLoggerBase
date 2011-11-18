

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public abstract class Megasquirt
{
	public abstract Set<String> getSignature();

	public abstract byte[] getOchCommand();

	public abstract byte[] getSigCommand();

	public abstract void loadConstants(boolean simulated) throws IOException;

	public abstract void calculate(byte[] ochBuffer) throws IOException;

	public abstract String getLogHeader();

	public abstract String getLogRow();

	public abstract int getBlockSize();

	public abstract int getSigSize();

	public abstract int getPageActivationDelay();

	public abstract int getInterWriteDelay();

	public abstract int getCurrentTPS();

	public abstract void initGauges();

	public abstract String[] defaultGauges();

	private byte[]	ochBuffer;

	private boolean	logging;
	private boolean	constantsLoaded;
	private boolean	signatureChecked;
	private String	trueSignature	= "Unknown";

	private boolean	running;

	private Context	context;

	protected int table(int i1, String name)
	{
		return 1;
	}

	protected double tempCvt(int t)
	{
		if (isSet("CELSIUS"))
		{
			return (t - 32.0) * 5.0 / 9.0;
		}
		else
		{
			return t;
		}
	}

	public Megasquirt(Context c)
	{
		this.context = c;
	}

	protected double timeNow()
	{
		return (System.currentTimeMillis()) / 1000.0;
	}
	protected double round(double v)
	{
		return Math.floor(v * 100 + .5) / 100;
	}

	protected boolean isSet(String name)
	{
		return true;
	}
	protected void getPage(byte[] pageBuffer, byte[] pageSelectCommand, byte[] pageReadCommand) throws IOException
	{
	}

	public String getTrueSignature()
	{
		return trueSignature;
	}

	public boolean isRunning()
	{
		return running;
	}
}
