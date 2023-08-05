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

    @Override
    protected synchronized void checkConnection() throws IOException
    {
        if (currentState == ConnectionState.STATE_DISCONNECTED)
        {
            connect();
        }
    }

    public synchronized void writeCommand(byte[] command, final int d, final boolean isCRC32) throws IOException
    {
        if (!conn.isConnected())
        {
            throw new IOException("Not connected");
        }
        // drain();
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

    public byte[] writeAndRead(final byte[] cmd, final int d, final boolean isCRC32) throws IOException, CRC32Exception
    {
        checkConnection();
        writeCommand(cmd, d, isCRC32);

        return readBytes(isCRC32);
    }

    public void writeAndRead(final byte[] cmd, final byte[] result, final int d, final boolean isCRC32) throws IOException, CRC32Exception, BTTimeoutException
    {
        checkConnection();
        writeCommand(cmd, d, isCRC32);

        readBytes(result, isCRC32);
    }

    public void readBytes(final byte[] bytes, final boolean isCRC32) throws IOException, CRC32Exception, BTTimeoutException
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
        final int toRead = buffer.length;
        int available;
        int remainder = toRead;
        final long startTime = System.currentTimeMillis();
        final long deadline = startTime + IO_TIMEOUT;
        synchronized (this)
        {
            while ((remainder > 0) && (System.currentTimeMillis() < deadline))
            {
                available = mmInStream.available();
                if (available > 0)
                {
                    final int numRead = mmInStream.read(buffer, read, Math.min(available, remainder));
                    if (numRead == -1)
                    {
                        throw new IOException("EOF!");
                    }
                    read += numRead;
                    remainder -= numRead;
                }
                else
                {
                    delay(10);
                }
            }
            if (read < toRead)
            {
                throw new BTTimeoutException(String.format("Timeout! : toRead = %d,  read = %d", toRead, read));
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

    public byte[] readBytes(final boolean isCRC32) throws IOException, CRC32Exception
    {
        final List<Byte> read = new ArrayList<>();

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
