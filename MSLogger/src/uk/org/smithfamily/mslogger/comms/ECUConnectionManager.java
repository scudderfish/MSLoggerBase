package uk.org.smithfamily.mslogger.comms;

import java.io.*;
import java.util.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.util.Log;

/**
 * Main connection class that wrap all the Bluetooth stuff that communicate with the Megasquirt
 */
public class ECUConnectionManager extends ConnectionManager
{
    // Private constructor prevents instantiation from other classes
    private ECUConnectionManager() { }

    /**
    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
    * or the first access to SingletonHolder.INSTANCE, not before.
    */
    private static class SingletonHolder { 
            public static final ECUConnectionManager INSTANCE = new ECUConnectionManager();
    }

    public static ECUConnectionManager getInstance() {
            return SingletonHolder.INSTANCE;
    }

    /**
     * Check if the application should auto-connect and automatically connect if it should
     * 
     * @throws IOException
     */
    protected synchronized void checkConnection() throws IOException
    {
    	DebugLogManager.INSTANCE.log("checkConnection()", Log.DEBUG);
        
        if (currentState == ConnectionState.STATE_DISCONNECTED)
        {
            boolean autoConnect = ApplicationSettings.INSTANCE.autoConnectable();
            if (autoConnect)
            {
                connect();
            }
            else
            {
                throw new IOException("Autoconnection not allowed");
            }
        }
        if (ownerThread != Thread.currentThread())
        {
            throw new IOException("Attempt to use from thread '" + Thread.currentThread().getName() + "' when owned by thread '" + ownerThread.getName());
        }
    }

    /**
     * Write a command to the Bluetooth stream
     * 
     * @param cmd       Command to be send
     * @param d         Delay to wait after sending command
     * @throws IOException
     */
    public synchronized void writeCommand(byte[] command, int d,boolean isCRC32) throws IOException
    {
        if (!conn.isConnected())
        {
            throw new IOException("Not connected");
        }

        if (isCRC32)
        {
        	command = CRC32ProtocolHandler.wrap(command);
        }

        DebugLogManager.INSTANCE.log("Writing", command, Log.DEBUG);
        
        if(command.length == 7 && command[0]=='r')
        {
            //MS2 hack
        	byte[] select = new byte[3];
        	byte[] range = new byte[4];
        	System.arraycopy(command, 0, select, 0 , 3);
        	System.arraycopy(command, 3, range, 0, 4);
        	this.mmOutStream.write(select);
        	delay(200);
        	this.mmOutStream.write(range);
        }
        else
        {
        	this.mmOutStream.write(command);
        }
        
        this.mmOutStream.flush();
        
        delay(d);
    }

    /**
     * Write a command to the Bluetooth stream and return the result
     * 
     * @param cmd       Command to be send
     * @param d         Delay to wait after sending command
     * @return
     * @throws IOException
     * @throws CRC32Exception 
     */
    public byte[] writeAndRead(byte[] cmd, int d,boolean isCRC32) throws IOException, CRC32Exception
    {
        checkConnection();
        writeCommand(cmd, d, isCRC32);

        byte[] result = readBytes(isCRC32);
        return result;
    }

    /**
     * Write a command to the Bluetooth stream and read the result
     * 
     * @param cmd       Command to be send
     * @param result    Result of the command sent by the Megasquirt
     * @param d         Delay to wait after sending command
     * @throws IOException
     * @throws CRC32Exception 
     */
    public void writeAndRead(byte[] cmd, byte[] result, int d,boolean isCRC32) throws IOException, CRC32Exception
    {
        checkConnection();
        writeCommand(cmd, d,isCRC32);

        readBytes(result,isCRC32);
    }

    /**
     * Read bytes available on Bluetooth stream
     * 
     * @param bytes
     * @throws IOException
     * @throws CRC32Exception 
     */
    public void readBytes(byte[] bytes,boolean isCRC32) throws IOException, CRC32Exception
    {
        TimerTask reaper = new Reaper(this);
        t.schedule(reaper, IO_TIMEOUT);
        
        int target = bytes.length;
        if (isCRC32)
        {
        	target += 7;
        }
        
        byte[] buffer = new byte[target];
        int read = 0;
        try
        {
            synchronized(this)
            {
                while (read < target)
                {
                    int numRead = mmInStream.read(buffer, read, target - read);
                    if (numRead == -1)
                    {
                        throw new IOException("end of stream attempting to read");
                    }
                    read += numRead;
                    DebugLogManager.INSTANCE.log("readBytes[] : target = "+target+" read so far :" +read, Log.DEBUG);
                }
            }
            reaper.cancel();
            DebugLogManager.INSTANCE.log("readBytes[]",buffer, Log.DEBUG);
            if (isCRC32)
            {
            	if (!CRC32ProtocolHandler.check(buffer))
            	{
            		throw new CRC32Exception("CRC32 check failed");
            	}
            	
            	byte[] actual = CRC32ProtocolHandler.unwrap(buffer);
            	System.arraycopy(actual, 0, bytes, 0, bytes.length);
            }
            else
            {
            	System.arraycopy(buffer, 0, bytes, 0, bytes.length);
            }
        }
        catch (IOException e)
        {
            if (timerTriggered)
            {
                DebugLogManager.INSTANCE.log("Time out reading from stream", Log.ERROR);
                throw e;
            }
        }
    }

    /**
     * Read bytes available on Bluetooth stream and return the resulting array
     * 
     * @return Array of bytes read from Bluetooth stream
     * @throws IOException
     * @throws CRC32Exception 
     */
    public byte[] readBytes(boolean isCRC32) throws IOException, CRC32Exception
    {
        List<Byte> read = new ArrayList<Byte>();

        synchronized(this)
        {
            while (mmInStream.available() > 0)
            {
                byte b = (byte) mmInStream.read();
                read.add(b);
            }
        }
        
        byte[] result = new byte[read.size()];
        int i = 0;
        for (Byte b : read)
        {
            result[i++] = b;
        }
                
        DebugLogManager.INSTANCE.log("readBytes", result, Log.DEBUG);
        
        if (isCRC32)
        {
        	if (!CRC32ProtocolHandler.check(result))
        	{
        		throw new CRC32Exception("CRC32 check failed");
        	}
        	result = CRC32ProtocolHandler.unwrap(result);
        }
        
        return result;
    }
}
