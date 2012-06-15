package uk.org.smithfamily.mslogger.ecuDef;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.comms.Connection;
import uk.org.smithfamily.mslogger.log.DatalogManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.log.FRDLogManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Log;

/**
 * 
 * @author Seb
 *
 */
public abstract class Megasquirt
{
    static Timer               connectionWatcher = new Timer("ConnectionWatcher", true);

    private boolean            simulated         = false;

    protected BluetoothSocket  sock;

    private BluetoothAdapter   mAdapter;

    public static final String NEW_DATA          = "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.NEW_DATA";

    public static final String CONNECTED         = "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.CONNECTED";
    public static final String DISCONNECTED      = "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.DISCONNECTED";
    public static final String TAG               = ApplicationSettings.TAG;

    protected Context          context;

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

    public abstract void refreshFlags();

    private boolean            logging;
    private boolean            constantsLoaded;
    private String             trueSignature = "Unknown";
    private ECUThread          ecuThread;
    private volatile boolean   running;

    private byte[]             ochBuffer;

    private RebroadcastHandler handler;

    /**
     * 
     * @param i1
     * @param name
     * @return
     */
    protected int table(int i1, String name)
    {
        return TableManager.INSTANCE.table(i1, name);
    }

    /**
     * 
     */
    public synchronized void toggleConnection()
    {
        if (!running)
        {
            ApplicationSettings.INSTANCE.setAutoConnectOverride(true);
            start();
        }
        else
        {
            ApplicationSettings.INSTANCE.setAutoConnectOverride(false);
            stop();
        }
    }

    /**
     * 
     * @return
     */
    public boolean isLogging()
    {
        return logging;
    }

    /**
     * 
     * @param t
     * @return
     */
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

    /**
     * 
     */
    public synchronized void start()
    {
        DebugLogManager.INSTANCE.log("Megasquirt.start()", Log.INFO);

        if (ecuThread == null)
        {
            ecuThread = new ECUThread();
            ecuThread.start();
        }
    }

    /**
     * 
     */
    public synchronized void stop()
    {
        if (ecuThread != null)
        {
            ecuThread.halt();
            ecuThread = null;
        }
        running = false;
        DebugLogManager.INSTANCE.log("Megasquirt.stop()", Log.INFO);
        // disconnect();
        sendMessage("");

    }

    /**
     * 
     */
    public void reset()
    {
        refreshFlags();
        constantsLoaded = false;
        running = false;
    }

    /**
     * 
     */
    private void logValues()
    {

        if (!isLogging())
        {
            return;
        }
        try
        {
            FRDLogManager.INSTANCE.write(ochBuffer);
            DatalogManager.INSTANCE.write(getLogRow());

        }
        catch (IOException e)
        {
            // ErrorReporter.getInstance().handleException(e);
            DebugLogManager.INSTANCE.logException(e);

            Log.e(ApplicationSettings.TAG, "Megasquirt.logValues()", e);
        }
    }

    /**
     * 
     */
    private void disconnect()
    {
        if (simulated)
            return;
        DebugLogManager.INSTANCE.log("Disconnect",Log.INFO);
        
        Connection.INSTANCE.disconnect();
        DatalogManager.INSTANCE.mark("Disconnected");
        FRDLogManager.INSTANCE.close();
        DatalogManager.INSTANCE.close();
        broadcast(DISCONNECTED);
    }

    /**
     * 
     * @param msg
     */
    protected void sendMessage(String msg)
    {
        Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.GENERAL_MESSAGE);
        broadcast.putExtra(ApplicationSettings.MESSAGE, msg);
        context.sendBroadcast(broadcast);
    }

    /**
     * 
     * @param action
     */
    private void broadcast(String action)
    {
        Intent broadcast = new Intent();
        broadcast.setAction(action);
        // broadcast.putExtra(LOCATION, location);
        context.sendBroadcast(broadcast);

    }

    /**
     * 
     * @param c
     */
    public Megasquirt(Context c)
    {
        this.context = c;
        handler = new RebroadcastHandler(this);
    }

    /**
     * 
     * @return
     */
    protected double timeNow()
    {
        return (System.currentTimeMillis() - DatalogManager.INSTANCE.getLogStart()) / 1000.0;
    }

    /**
     * 
     * @param channel
     * @return
     */
    public double getValue(String channel)
    {

        double value = 0;
        Class<?> c = this.getClass();
        try
        {
            Field f = c.getDeclaredField(channel);
            value = f.getDouble(this);
        }
        catch (Exception e)
        {
            DebugLogManager.INSTANCE.log("Failed to get value for " + channel, Log.ERROR);
            Log.e(ApplicationSettings.TAG, "Megasquirt.getValue()", e);
        }
        return value;
    }

    /**
     * 
     */
    public void startLogging()
    {
        logging = true;
        DebugLogManager.INSTANCE.log("startLogging()", Log.INFO);

    }

    /**
     * 
     */
    public void stopLogging()
    {
        DebugLogManager.INSTANCE.log("stopLogging()", Log.INFO);
        logging = false;
        FRDLogManager.INSTANCE.close();
        DatalogManager.INSTANCE.close();
    }

    /**
     * 
     * @param v
     * @return
     */
    protected double round(double v)
    {
        return Math.floor(v * 100 + .5) / 100;
    }

    /**
     * 
     * @param name
     * @return
     */
    protected boolean isSet(String name)
    {
        return ApplicationSettings.INSTANCE.isSet(name);
    }

    /**
     *
     */
    private class RebroadcastHandler extends Handler
    {
        private Megasquirt ecu;

        /**
         * 
         * @param ecu
         */
        public RebroadcastHandler(Megasquirt ecu)
        {
            this.ecu = ecu;
        }

        /**
         * 
         * @param m
         */
        @Override
        public void handleMessage(Message m)
        {
            super.handleMessage(m);
            Bundle b = m.getData();
            String msg = b.getString(MSLoggerApplication.MSG_ID);
            ecu.sendMessage(msg);
        }
    }

    /**
     *
     */
    private class ECUThread extends Thread
    {

        /**
         * 
         */
        public ECUThread()
        {
            Set<String> sigs = Megasquirt.this.getSignature();
            setName("ECUThread:" + sigs.iterator().next());
        }

        /**
         * 
         */
        public void initialiseConnection()
        {
            ochBuffer = new byte[Megasquirt.this.getBlockSize()];

            if (!ApplicationSettings.INSTANCE.btDeviceSelected())
            {
                sendMessage("Bluetooth device not selected");
                return;
            }
            mAdapter = ApplicationSettings.INSTANCE.getDefaultAdapter();
            if (mAdapter == null)
            {
                sendMessage("Could not find the default Bluetooth adapter!");
                return;
            }
            String btAddr = ApplicationSettings.INSTANCE.getBluetoothMac();

            sendMessage("Launching connection");
            Connection.INSTANCE.init(btAddr, mAdapter, handler);

        }

        /**
         * 
         */
        public void run()
        {
            sendMessage("");
            DebugLogManager.INSTANCE.log("BEGIN connectedThread", Log.INFO);
            initialiseConnection();
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e1)
            {

                e1.printStackTrace();
            }
            try
            {
                Connection.INSTANCE.connect();
                Connection.INSTANCE.flushAll();

                if (!verifySignature())
                {
                    DebugLogManager.INSTANCE.log("!verifySignature()", Log.DEBUG);

                    Connection.INSTANCE.disconnect();
                    return;
                }
                ApplicationSettings.INSTANCE.refreshFlags();
                refreshFlags();
                if (!constantsLoaded)
                {
                    loadConstants(simulated);
                    constantsLoaded = true;
                }
                running = true;
                while (running)
                {
                    getRuntimeVars();
                    calculateValues();
                    logValues();
                    broadcast(NEW_DATA);
                }
            }
            catch (IOException e)
            {
                DebugLogManager.INSTANCE.logException(e);
            }
            catch (ArithmeticException e)
            {
                DebugLogManager.INSTANCE.logException(e);
                constantsLoaded = false;
            }
            catch (RuntimeException t)
            {
                DebugLogManager.INSTANCE.logException(t);
                throw (t);
            }
            disconnect();

        }

        /**
         * 
         */
        public void halt()
        {
            running = false;
        }

        /**
         * 
         * @return
         * @throws IOException
         */
        private boolean verifySignature() throws IOException
        {
            boolean verified = false;
            String msSig = null;
            if (simulated)
            {
                verified = true;
            }
            else
            {
                byte[] sigCommand = getSigCommand();
                sendMessage("Verifying MS");
                Set<String> signatures = Megasquirt.this.getSignature();

                msSig = getSignature(sigCommand);
                verified = signatures.contains(msSig);
                if (verified)
                {
                    trueSignature = msSig;
                }
            }
            if (verified)
            {
                sendMessage("Connected to " + trueSignature);
                broadcast(CONNECTED);
            }
            else
            {
                sendMessage("Signature error! " + msSig);
            }
            return verified;
        }

        /**
         * 
         * @throws IOException
         */
        private void getRuntimeVars() throws IOException
        {
            // Debug.startMethodTracing("getRuntimeVars");
            if (simulated)
            {
                MSSimulator.INSTANCE.getNextRTV(ochBuffer);
                return;
            }
            int d = getInterWriteDelay();
            Connection.INSTANCE.writeAndRead(getOchCommand(), ochBuffer, d);
            // Debug.stopMethodTracing();
        }

        /**
         * 
         * @throws IOException
         */
        private void calculateValues() throws IOException
        {
            calculate(ochBuffer);
        }

        /**
         * 
         * @param pageBuffer
         * @param pageSelectCommand
         * @param pageReadCommand
         * @throws IOException
         */
        protected void getPage(byte[] pageBuffer, byte[] pageSelectCommand, byte[] pageReadCommand) throws IOException
        {

            Connection.INSTANCE.flushAll();
            int delay = getPageActivationDelay();
            if (pageSelectCommand != null)
            {
                Connection.INSTANCE.writeCommand(pageSelectCommand, delay);
            }
            if (pageReadCommand != null)
            {
                Connection.INSTANCE.writeCommand(pageReadCommand, delay);
            }
            Connection.INSTANCE.readBytes(pageBuffer);
        }

        /**
         * 
         * @param sigCommand
         * @return
         * @throws IOException
         */
        private String getSignature(byte[] sigCommand) throws IOException
        {
            String sig1 = "NoSigReadYet";
            String sig2 = "Not here";
            DebugLogManager.INSTANCE.log("getSignature()", Log.DEBUG);
            int d = getInterWriteDelay();
            do
            {
                byte[] buf = Connection.INSTANCE.writeAndRead(sigCommand, d);
                if (!sig2.equals(ECUFingerprint.UNKNOWN))
                {
                    sig1 = sig2;
                }
                try
                {
                    sig2 = new String(ECUFingerprint.processResponse(buf));
                }
                catch (BootException e)
                {
                    return "ECU needs a reboot!";
                }
                DebugLogManager.INSTANCE.log("Got a signature of " + sig2, Log.INFO);

                Connection.INSTANCE.flushAll();
            }
            while (!sig1.equals(sig2));
            return sig1;
        }

    }

    /**
     * 
     * @return
     */
    public String getTrueSignature()
    {
        return trueSignature;
    }

    /**
     * 
     * @return
     */
    public boolean isRunning()
    {
        return running;
    }

    /**
     * 
     * @param pageNo
     * @param pageOffset
     * @param pageSize
     * @param select
     * @param read
     * @return
     */
    protected byte[] loadPage(int pageNo, int pageOffset, int pageSize, byte[] select, byte[] read)
    {

        byte[] buffer = new byte[pageSize];
        try
        {
            sendMessage("Loading constants from page " + pageNo);
            getPage(buffer, select, read);
            savePage(pageNo, buffer);
            sendMessage("Constants loaded from page " + pageNo);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            DebugLogManager.INSTANCE.logException(e);
            sendMessage("Error loading constants from page " + pageNo);
        }
        return buffer;
    }

    /**
     * 
     * @param buffer
     * @param select
     * @param read
     * @throws IOException
     */
    private void getPage(byte[] buffer, byte[] select, byte[] read) throws IOException
    {
        ecuThread.getPage(buffer, select, read);
    }

    /**
     * 
     * @param pageNo
     * @param buffer
     */
    private void savePage(int pageNo, byte[] buffer)
    {

        try
        {
            File dir = new File(Environment.getExternalStorageDirectory(), "MSLogger");
            dir.mkdirs();

            String fileName = this.getClass().getName() + ".firmware";
            File outputFile = new File(dir, fileName);
            BufferedOutputStream out = null;
            try
            {
                boolean append = !(pageNo == 1);
                out = new BufferedOutputStream(new FileOutputStream(outputFile, append));
                DebugLogManager.INSTANCE.log("Saving page " + pageNo + " append=" + append, Log.INFO);
                out.write(buffer);
            }
            finally
            {
                if (out != null)
                {
                    out.flush();
                    out.close();
                }
            }
        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
    }

}
