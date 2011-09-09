package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.comms.LostCommsException;
import uk.org.smithfamily.mslogger.comms.MsComm;
import uk.org.smithfamily.mslogger.log.DatalogManager;
import uk.org.smithfamily.mslogger.log.FRDLogManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public abstract class Megasquirt implements Runnable
{
    private boolean            simulated = false;
    private boolean initialised = false;

    public static final String NEW_DATA  = "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.NEW_DATA";

    public static final String CONNECTED = "uk.org.smithfamily.mslogger.ecuDef.Megasquirt.CONNECTED";

    protected Context          context;

    public abstract String getSignature();

    public abstract byte[] getOchCommand();

    public abstract byte[] getSigCommand();

    public abstract void loadConstants(boolean simulated) throws LostCommsException;

    public abstract void calculate(byte[] ochBuffer) throws LostCommsException;

    public abstract String getLogHeader();

    public abstract String getLogRow();

    public abstract int getBlockSize();

    public abstract int getPageActivationDelay();

    public abstract int getInterWriteDelay();

    public abstract int getCurrentTPS();

    private long             lastTime = System.currentTimeMillis();

    private long             logStart = lastTime;
    private volatile boolean running  = false;
    private MsComm           comm;
    private byte[]           ochBuffer;

    private boolean          logging;
    private int              counter  = 0;
    private Handler handler;
    
    private void start()
    {
        try
        {
            initialiseConnection();
            verifySignature();
            loadConstantsWithTimeout();
            logStart = System.currentTimeMillis();
            initialised = true;
            running = true;
        }
        catch (LostCommsException e)
        {
            handleLostConnection(e);
        }
        
    }

    public void stop()
    {
        handler.removeCallbacks(this);
        running = false;
        disconnect();
        initialised = false;
        sendMessage("");
    }

    public void setRunning(boolean r)
    {
        running = r;
    }

    public void run()
    {

        try
        {
            if(!initialised)
            {
                start();
            }
//            Log.d(ApplicationSettings.TAG,"run 1");

            flushComms();
//            Log.d(ApplicationSettings.TAG,"run 2");
            getRuntimeVars();
//            Log.d(ApplicationSettings.TAG,"run 3");
            calculateValues();
//            Log.d(ApplicationSettings.TAG,"run 4");
            logValues();
 //           Log.d(ApplicationSettings.TAG,"run 5");
            broadcastNewData();
//            Log.d(ApplicationSettings.TAG,"run 6");
            if(running)
                handler.postDelayed(this, 1000/ApplicationSettings.INSTANCE.getHertz());
//            Log.d(ApplicationSettings.TAG,"run 7");
            sendMessage("Data " + (counter++));
//            Log.d(ApplicationSettings.TAG,"run 8");
        }
        catch (LostCommsException e)
        {
            handleLostConnection(e);
        }
    }

    private void flushComms() throws LostCommsException
    {
        if (simulated)
            return;

        comm.flush();
    }

    private void handleLostConnection(LostCommsException e)
    {
        sendMessage("Lost connection to Megasquirt : " + e.getLocalizedMessage());
        initialised = false;
        if(running)
        {
            handler.postDelayed(this, 5000);
        }
            
    }

    private void verifySignature() throws LostCommsException
    {
        boolean connected = false;
        String msSig = null;
        if (simulated)
        {
            connected = true;
        }
        else
        {
            byte[] sigCommand = this.getSigCommand();
            sendMessage("Verifying MS");
            msSig = comm.getSignature(sigCommand);
            String signature = getSignature();
            connected = signature.equals(msSig);
        }
        if (connected)
        {
            broadcastConnected();
        }
        else
        {
            sendMessage("Signature error! " + msSig);
        }
    }

    private void initialiseConnection()
    {
        comm = ApplicationSettings.INSTANCE.getComms();
        comm.setInterWriteDelay(getInterWriteDelay());
        ochBuffer = new byte[this.getBlockSize()];

        if (!simulated)
        {
            comm.close();
            comm.openConnection();
        }

    }

    private void loadConstantsWithTimeout() throws LostCommsException
    {
        sendMessage("Loading constants...");

        boolean constantsLoaded = false;
        int counter = 0;
        do
        {
            try
            {
                loadConstants(simulated);
                sendMessage("Constants loaded");
                constantsLoaded = true;
            }

            catch (Exception e)
            {
                counter++;
                sendMessage("Failed to load constants : " + counter);
                constantsLoaded = false;
                comm.close();
                comm.openConnection();
                comm.flush();
            }

        }
        while (!constantsLoaded);
    }

    private void logValues()
    {

        if (!logging)
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
            Log.e(ApplicationSettings.TAG,"Megasquirt.logValues()",e);
        }
    }

    private void disconnect()
    {
        if (simulated)
            return;
        try
        {
            comm.flush();
            comm.close();

            FRDLogManager.INSTANCE.close();
            DatalogManager.INSTANCE.close();

        }
        catch (LostCommsException e)
        {
            Log.e(ApplicationSettings.TAG,"Megasquirt.disconnect()",e);
        }

    }

    protected void sendMessage(String msg)
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
            Log.e(ApplicationSettings.TAG,"Megasquirt.delay()",e);
        }

    }

    private void broadcastConnected()
    {
        Intent broadcast = new Intent();
        broadcast.setAction(CONNECTED);
        // broadcast.putExtra(LOCATION, location);
        context.sendBroadcast(broadcast);
        sendMessage("Connected to " + this.getSignature());

    }

    private void broadcastNewData()
    {
        Intent broadcast = new Intent();
        broadcast.setAction(NEW_DATA);
        // broadcast.putExtra(LOCATION, location);
        context.sendBroadcast(broadcast);

    }

    private void getRuntimeVars() throws LostCommsException
    {
//        Debug.startMethodTracing("getRuntimeVars");
        if (simulated)
        {
            MSSimulator.INSTANCE.getNextRTV(ochBuffer);
            return;
        }
//        DebugLogManager.INSTANCE.log("1", context,false);
        comm.write(this.getOchCommand());//
//        DebugLogManager.INSTANCE.log("2", context,false);
        comm.readWithTimeout(ochBuffer, 1, TimeUnit.SECONDS);
//        DebugLogManager.INSTANCE.log("3", context,true);
//        Debug.stopMethodTracing();
    }

    private void calculateValues() throws LostCommsException
    {
        calculate(ochBuffer);
    }

    public Megasquirt(Context c, Handler handler)
    {
        this.context = c;
        this.handler = handler;

    }

    protected int getLong(byte[] ochBuffer, int i)
    {
        return getWord(ochBuffer, i) * 65536 + getWord(ochBuffer, i + 2);
    }

    protected int getWord(byte[] ochBuffer, int i)
    {

        return (ochBuffer[i] & 0xFF) * 256 + ochBuffer[i + 1] & 0xFF;
    }

    protected int getByte(byte[] ochBuffer, int i)
    {
        return (int) ochBuffer[i] & 0xFF;
    }

    protected int getSignedLong(byte[] ochBuffer, int i)
    {
        int x = getLong(ochBuffer, i);
        if (x > 2 << 32 - 1)
        {
            x = 2 << 32 - x;
        }
        return x;
    }

    protected int getSignedByte(byte[] ochBuffer, int i)
    {

        int x = getByte(ochBuffer, i);
        if (x > 127)
        {
            x = 256 - x;
        }
        return x;
    }

    protected int getSignedWord(byte[] ochBuffer, int i)
    {
        int x = getWord(ochBuffer, i);
        if (x > 32767)
        {
            x = 32768 - x;
        }
        return x;
    }

    protected double timeNow()
    {
        return (System.currentTimeMillis() - logStart) / 1000.0;
    }

    protected double tempCvt(int t)
    {
        return (t - 32.0) * 5.0 / 9.0;
    }

    public boolean initialised()
    {

        boolean connected = false;
        return connected;

    }

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
            Log.e(ApplicationSettings.TAG,"Megasquirt.geetValue()",e);
        }
        return value;
    }

    public void startLogging()
    {
        logging = true;

    }

    public void stopLogging()
    {
        logging = false;
        DatalogManager.INSTANCE.close();
        FRDLogManager.INSTANCE.close();
    }

    protected int getBits(byte[] pageBuffer, int i, int _bitLo, int _bitHi)
    {
        int val = 0;
        byte b = pageBuffer[i];

        long mask = ((1 << (_bitHi - _bitLo + 1)) - 1) << _bitLo;
        val = (int) ((b & mask) >> _bitLo);

        return val;
    }

    protected void getPage(byte[] pageBuffer, byte[] pageSelectCommand, byte[] pageReadCommand) throws LostCommsException
    {
        comm.flush();
        comm.write(pageSelectCommand);
        delay(getPageActivationDelay());
        if (pageReadCommand != null)
        {
            comm.write(pageReadCommand);
        }
        delay(getPageActivationDelay());
        comm.readWithTimeout(pageBuffer, 1, TimeUnit.SECONDS);
    }

    protected double round(double v)
    {
        return Math.floor(v * 100 + .5) / 100;
    }

    protected boolean isSet(String name)
    {
        return ApplicationSettings.INSTANCE.isSet(name);
    }
}
