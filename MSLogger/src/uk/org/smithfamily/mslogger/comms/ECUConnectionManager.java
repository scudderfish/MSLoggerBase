package uk.org.smithfamily.mslogger.comms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.util.Log;

/**
 * Main connection class that wrap all the Bluetooth stuff that communicate with the Megasquirt
 */
public class ECUConnectionManager extends ConnectionManager
{

    // Private constructor prevents instantiation from other classes
    private ECUConnectionManager()
    {

    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance() or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder
    {
        public static final ECUConnectionManager INSTANCE = new ECUConnectionManager();
    }

    public static ECUConnectionManager getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String getInstanceName()
    {
        return "ECUConnectionManager";
    }

    /**
     * Check if the application should auto-connect and automatically connect if it should
     * 
     * @throws IOException
     */
    @Override
    protected synchronized void checkConnection() throws IOException
    {
        if (currentState == ConnectionState.STATE_DISCONNECTED)
        {
            connect();
        }
    }

    /**
     * Write a command to the Bluetooth stream
     * 
     * @param cmd Command to be send
     * @param d Delay to wait after sending command
     * @throws IOException
     */
    public synchronized void writeCommand(byte[] command, final int d, final boolean isCRC32) throws IOException
    {
        if (!conn.isConnected())
        {
            throw new IOException("Not connected");
        }

        if (isCRC32)
        {
            command = CRC32ProtocolHandler.wrap(command);
        }

        DebugLogManager.INSTANCE.log("Writing", command, Log.VERBOSE);

        if ((command.length == 7) && ((command[0] == 'r') || (command[0] == 'w') || (command[0] == 'e')))
        {
            // MS2 hack
            final byte[] select = new byte[3];
            final byte[] range = new byte[4];
            System.arraycopy(command, 0, select, 0, 3);
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
     * @param cmd Command to be send
     * @param d Delay to wait after sending command
     * @return
     * @throws IOException
     * @throws CRC32Exception
     */
    public byte[] writeAndRead(final byte[] cmd, final int d, final boolean isCRC32) throws IOException, CRC32Exception
    {
        checkConnection();
        writeCommand(cmd, d, isCRC32);

        final byte[] result = readBytes(isCRC32);
        return result;
    }

    /**
     * Write a command to the Bluetooth stream and read the result
     * 
     * @param cmd Command to be send
     * @param result Result of the command sent by the Megasquirt
     * @param d Delay to wait after sending command
     * @throws IOException
     * @throws CRC32Exception
     */
    public void writeAndRead(final byte[] cmd, final byte[] result, final int d, final boolean isCRC32) throws IOException, CRC32Exception
    {
        checkConnection();
        writeCommand(cmd, d, isCRC32);

        readBytes(result, isCRC32);
    }

    /**
     * Read bytes available on Bluetooth stream
     * 
     * @param bytes
     * @throws IOException
     * @throws CRC32Exception
     */
    public void readBytes(final byte[] bytes, final boolean isCRC32) throws IOException, CRC32Exception
    {
        final boolean logit = DebugLogManager.checkLogLevel(Log.VERBOSE);
        int target = bytes.length;
        byte[] buffer = bytes;
        if (isCRC32)
        {
            target += CRC32ProtocolHandler.getValidationLength();
            buffer = new byte[target];
        }

        int read = 0;
        int available = 0;
        final long readStart = System.currentTimeMillis();
        synchronized (this)
        {
            do
            {
                available = mmInStream.available();

                final long now = System.currentTimeMillis();

                if ((now - readStart) > IO_TIMEOUT)
                {
                    throw new IOException(String.format("IO Timeout! available %d", available));
                }

                if (available < target)
                {
                    delay(20);
                }
            } while (available < target);

            final int numRead = mmInStream.read(buffer, read, target - read);
            if (numRead == -1)
            {
                throw new IOException("End of stream attempting to read");
            }
            read += numRead;

            if (logit)
            {
                DebugLogManager.INSTANCE.log("readBytes[] : target = " + target + " read so far :" + read, Log.VERBOSE);

            }
        }

        if (logit)
        {
            DebugLogManager.INSTANCE.log("readBytes[]", buffer, Log.VERBOSE);
        }
        if (isCRC32)
        {
            if (!CRC32ProtocolHandler.check(buffer))
            {
                throw new CRC32Exception("CRC32 check failed");
            }

            final byte[] actual = CRC32ProtocolHandler.unwrap(buffer);
            System.arraycopy(actual, 0, bytes, 0, bytes.length);
        }
    }

    /**
     * Read bytes available on Bluetooth stream and return the resulting array
     * 
     * @return Array of bytes read from Bluetooth stream
     * @throws IOException
     * @throws CRC32Exception
     */
    public byte[] readBytes(final boolean isCRC32) throws IOException, CRC32Exception
    {
        final List<Byte> read = new ArrayList<Byte>();

        synchronized (this)
        {
            while (mmInStream.available() > 0)
            {
                final byte b = (byte) mmInStream.read();
                read.add(b);
            }
        }

        byte[] result = new byte[read.size()];
        int i = 0;
        for (final Byte b : read)
        {
            result[i++] = b;
        }

        DebugLogManager.INSTANCE.log("readBytes", result, Log.VERBOSE);

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
