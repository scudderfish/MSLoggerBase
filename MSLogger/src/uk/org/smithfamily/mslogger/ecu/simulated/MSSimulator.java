package uk.org.smithfamily.mslogger.ecu.simulated;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.*;

/**
 * Base class for any ECU simulators
 * 
 */
public abstract class MSSimulator implements Runnable
{

    public static final int SERVERPORT = 1052;
    // Approx allowing for start/stop bits etc
    private static final double BAUD_PER_BYTE = 10;
    // The log file we're going to play back
    FRDLogFile frdFile;
    Thread simThread = null;
    ServerSocket serverSocket;
    long lastWrite = System.currentTimeMillis();

    private volatile boolean running = false;

    public boolean isRunning()
    {
        return running;
    }

    @Override
    public void run()
    {

        running = true;
        while (running)
        {
            try
            {
                final Socket client = serverSocket.accept();

                // Get the streams...
                final InputStream is = client.getInputStream();
                final OutputStream os = client.getOutputStream();

                // And process them
                while (running)
                {
                    final int b = is.read();
                    process(b, is, os);
                    os.flush();
                }
            }
            catch (final IOException e)
            {
                // Something borked. Drop the connection and try again
                DebugLogManager.INSTANCE.logException(e);
            }

        }
    }

    /**
     * Pull a page of firmware from a previously saved file
     * 
     * @param pageNo
     * @return
     */
    protected byte[] getFirmwarePage(final int pageNo)
    {
        final byte[] page = new byte[getFirmwarePageSize(pageNo)];

        final File firmwareFile = new File(ApplicationSettings.INSTANCE.getDataDir(), getFirmwareFile());
        try
        {
            final FileInputStream r = new FileInputStream(firmwareFile);
            r.skip((pageNo - 1) * getFirmwarePageSize(pageNo));
            r.read(page);
            r.close();
        }
        catch (final IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }

        return page;
    }

    /**
     * Pull from the previously loaded list, the next set of runtime vars
     * 
     * @return
     * @throws IOException
     */
    protected byte[] getNextPageOfVars() throws IOException
    {
        final FRDLogFileRecord nextRecord = frdFile.getNextRecord();
        final byte[] vars = nextRecord.getOchBuffer();

        return vars;
    }

    /**
     * Construct the server socket ready for connection
     */
    public void init()
    {
        try
        {
            serverSocket = new ServerSocket(SERVERPORT);
        }
        catch (final IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }

    }

    /**
     * Load up the FRD file and launch the simulator thread
     * 
     * @throws IOException
     */
    public synchronized void startRunning() throws IOException
    {
        final File logFile = new File(ApplicationSettings.INSTANCE.getDataDir(), getFRDFilename());
        final FileInputStream fis = new FileInputStream(logFile);
        frdFile = FRDLogManager.INSTANCE.loadFile(fis);
        fis.close();
        simThread = new Thread(this);
        simThread.setDaemon(true);
        simThread.setName("SIM:" + getSignature());
        simThread.start();
    }

    /**
     * Shut down the simulator thread
     * 
     * @throws IOException
     */
    public synchronized void stopRunning() throws IOException
    {
        if (simThread != null)
        {
            running = false;
            simThread.interrupt();
            simThread = null;
        }
    }

    /**
     * Default baud rate. Should probably be lower to simulate BT being a bit rubbish
     * 
     * @return
     */
    int getBaudRate()
    {
        return 11520;
    }

    /**
     * Attempt to feed data back at a rate approximating the baud rate
     * 
     * @param os
     * @param data
     * @throws IOException
     */
    void speedLimitedWrite(final OutputStream os, final byte[] data) throws IOException
    {
        final int dataSize = data.length;

        final double msPerByte = (1000.0 / getBaudRate()) * BAUD_PER_BYTE;

        final long delay = (long) (msPerByte * dataSize);

        try
        {
            Thread.sleep(delay);
        }
        catch (final InterruptedException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
        os.write(data);
    }

    /**
     * Given a command byte, process it (and any other relevant bytes) and fire the result back up the OutputStream
     * 
     * @param b
     * @param is
     * @param os
     * @throws IOException
     */
    abstract void process(int b, InputStream is, OutputStream os) throws IOException;

    /**
     * The signature of this simulated ECU
     * 
     * @return
     */
    abstract String getSignature();

    /**
     * Location of the file containing a dump of the firmware
     * 
     * @return
     */
    abstract String getFirmwareFile();

    /**
     * Return the size of a given page of the firmware
     * 
     * @param pageNo
     * @return
     */
    abstract int getFirmwarePageSize(int pageNo);

    /**
     * return the name of the FRD log file to play back
     * 
     * @return
     */
    abstract String getFRDFilename();

}
