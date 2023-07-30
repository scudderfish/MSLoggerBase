package uk.org.smithfamily.mslogger.ecuDef;

import static uk.org.smithfamily.mslogger.MSLoggerApplication.CHANNEL_ID;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.DataManager;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.activity.MSLoggerActivity;
import uk.org.smithfamily.mslogger.comms.BTTimeoutException;
import uk.org.smithfamily.mslogger.comms.CRC32Exception;
import uk.org.smithfamily.mslogger.comms.ECUConnectionManager;
import uk.org.smithfamily.mslogger.ecuDef.gen.ECURegistry;
import uk.org.smithfamily.mslogger.log.DatalogManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.log.FRDLogManager;

/**
 * Abstract base class for all ECU implementations
 *
 * @author dgs
 *
 */
public class Megasquirt extends Service implements MSControllerInterface {
    private static final int MAX_QUEUE_SIZE = 10;
    BlockingQueue<InjectedCommand> injectionQueue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);

    private enum State {
        DISCONNECTED, CONNECTING, CONNECTED, LOGGING
    }

    private volatile State currentState = State.DISCONNECTED;

    private MSECUInterface ecuImplementation;

    public static final String CONNECTED = "CONNECTED";
    public static final String DISCONNECTED = "DISCONNECTED";
    public static final String NEW_DATA = "NEW_DATA";
    public static final String UNKNOWN_ECU = "UNKNOWN_ECU";
    public static final String UNKNOWN_ECU_BT = "UNKNOWN_ECU_BT";
    public static final String PROBE_ECU = "ECU_PROBED";
    public static final String INJECTED_COMMAND_RESULTS = "INJECTED_COMMAND_RESULTS";
    public static final String INJECTED_COMMAND_RESULT_ID = "INJECTED_COMMAND_RESULTS_ID";
    public static final String INJECTED_COMMAND_RESULT_DATA = "INJECTED_COMMAND_RESULTS_DATA";

    private static final String UNKNOWN = "UNKNOWN";
    private static final String LAST_SIG = "LAST_SIG";
    private static final String LAST_PROBE = "LAST_PROBE";
    private static final int NOTIFICATION_ID = 10;

    final DecimalFormat decimalFormat1dp = new DecimalFormat("#.0");

    private BroadcastReceiver yourReceiver;

    // Used by broadcast receiver
    public static final int CONTROLLER_COMMAND = 1;

    public static final int BURN_DATA = 10;

    private boolean constantsLoaded;
    private String trueSignature = "Unknown";
    private volatile ECUThread ecuThread;
    private static volatile ECUThread watch;
    private long logStart = 0;

    public class LocalBinder extends Binder {
        public Megasquirt getService() {
            return Megasquirt.this;
        }
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        DebugLogManager.INSTANCE.log("Megasquirt Received start id " + startId + ": " + intent, Log.VERBOSE);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public IBinder onBind(final Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients. See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    public void onCreate() {
        super.onCreate();

        final IntentFilter btChangedFilter = new IntentFilter();
        btChangedFilter.addAction(ApplicationSettings.BT_CHANGED);

        final IntentFilter injectCommandResultsFilter = new IntentFilter();
        injectCommandResultsFilter.addAction(Megasquirt.INJECTED_COMMAND_RESULTS);

        this.yourReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final String action = intent.getAction();

                assert action != null;
                if (action.equals(ApplicationSettings.BT_CHANGED)) {
                    DebugLogManager.INSTANCE.log("BT_CHANGED received", Log.VERBOSE);
                    stop();
                    start();
                } else if (action.equals(Megasquirt.INJECTED_COMMAND_RESULTS)) {
                    final int resultId = intent.getIntExtra(Megasquirt.INJECTED_COMMAND_RESULT_ID, 0);

                    if (resultId == Megasquirt.BURN_DATA) {// Wait til we get some data and flush it
                        try {
                            Thread.sleep(200);
                        } catch (final InterruptedException ignored) {
                        }
                    }
                }
            }
        };

        // Registers the receiver so that your service will listen for broadcasts
        this.registerReceiver(this.yourReceiver, btChangedFilter);
        this.registerReceiver(this.yourReceiver, injectCommandResultsFilter);

        final String lastSig = ApplicationSettings.INSTANCE.getPref(LAST_SIG);
        if (lastSig != null) {
            setImplementation(lastSig);
        }

        ApplicationSettings.INSTANCE.setEcu(this);
        start();

        //startForeground(NOTIFICATION_ID, null);
    }

    private void setState(final State s) {
        currentState = s;
        int msgId = R.string.disconnected_from_ms;
        switch (currentState) {
            case DISCONNECTED:
                break;
            case CONNECTING:
                msgId = R.string.connecting_to_ms;
                break;
            case CONNECTED:
                msgId = R.string.connected_to_ms;
                break;
            case LOGGING:
                msgId = R.string.logging;
                break;
            default:
                msgId = R.string.unknown;
                break;
        }

        {
            final CharSequence text = getText(R.string.app_name);
            final PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MSLoggerActivity.class), PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle(text)
                    .setContentText(getText(msgId))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true);
            NotificationManagerCompat nm = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            nm.notify(R.string.app_name, builder.build());

//            final Notification notification = new Notification(R.drawable.icon, text, System.currentTimeMillis());
//            notification.setLatestEventInfo(this, getText(msgId), text, contentIntent);
//            notifications.notify(NOTIFICATION_ID, notification);
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Do not forget to unregister the receiver!!!
        this.unregisterReceiver(this.yourReceiver);
    }

    /**
     * Shortcut function to access data tables. Makes the INI->Java translation a little simpler
     * 
     * @param i1 index into table
     * @param name table name
     * @return value from table
     */
    protected int table(final int i1, final String name)
    {
        return TableManager.INSTANCE.table(i1, name);
    }

    @Override
    public int table(final double d1, final String name)
    {
        return table((int) d1, name);
    }

    /**
     * Add a command for the ECUThread to process when it can
     *
     */
    public void injectCommand(final InjectedCommand command)
    {
        injectionQueue.add(command);
    }

    /**
     * @return true if we're connected to an ECU, false otherwise
     */
    public boolean isConnected()
    {
        return (currentState == State.CONNECTED) || (currentState == State.LOGGING);
    }

    /**
     * @return true if we're data logging the ECU realtime stream, false otherwise
     */
    public boolean isLogging()
    {
        return currentState == State.LOGGING;
    }

    /**
     * Temperature unit conversion function
     * 
     * @param t temp in F
     * @return temp in C if CELSIUS is set, in F otherwise
     */
    @Override
    public double tempCvt(final double t)
    {
        if (isSet("CELSIUS"))
        {
            return ((t - 32.0) * 5.0) / 9.0;
        }
        else
        {
            return t;
        }
    }

    /**
     * Launch the ECU thread
     */
    public synchronized void start()
    {
        DebugLogManager.INSTANCE.log("Megasquirt.start()", Log.INFO);

        if (ApplicationSettings.INSTANCE.getECUBluetoothMac().equals(ApplicationSettings.MISSING_VALUE))
        {
            broadcast(UNKNOWN_ECU_BT);
        }
        else
        {
            setState(State.DISCONNECTED);
            ecuThread = new ECUThread();
            ecuThread.setPriority(Thread.MAX_PRIORITY);
            ecuThread.start();
        }
    }

    /**
     * Shut down the ECU thread
     */
    public synchronized void stop()
    {
        DebugLogManager.INSTANCE.log("Megasquirt.stop()", Log.INFO);

        ecuThread = null;

        setState(State.DISCONNECTED);
        broadcast(DISCONNECTED);
    }

    /**
     * Revert to initial state
     */
    public void reset()
    {
        ecuImplementation.refreshFlags();
        constantsLoaded = false;
    }

    /**
     * Output the current values to be logged
     */
    private void logValues(final byte[] buffer)
    {
        if (!isLogging())
        {
            return;
        }
        try
        {
            FRDLogManager.INSTANCE.write(buffer);
            DatalogManager.INSTANCE.write(ecuImplementation.getLogRow());

        }
        catch (final IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
    }

    /**
     * Shutdown the data connection to the MS
     */
    private void disconnect()
    {
        DebugLogManager.INSTANCE.log("Disconnect", Log.INFO);

        ECUConnectionManager.getInstance().disconnect();
        DatalogManager.INSTANCE.mark("Disconnected");
        FRDLogManager.INSTANCE.close();
        DatalogManager.INSTANCE.close();
        broadcast(DISCONNECTED);
    }

    /**
     * Send a message to the user
     * 
     * @param msg Message to be sent
     */
    protected void sendMessage(final String msg)
    {
        broadcast(msg);
    }

    protected void sendToastMessage(final String msg)
    {
        final Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.TOAST);
        broadcast.putExtra(ApplicationSettings.TOAST_MESSAGE, msg);
        sendBroadcast(broadcast);
    }

    /**
     * Send the reads per second to be displayed on the screen
     * 
     * @param RPS the current reads per second value
     */
    private void sendRPS(final double RPS)
    {

        final Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.RPS_MESSAGE);
        broadcast.putExtra(ApplicationSettings.RPS, decimalFormat1dp.format(RPS));
        sendBroadcast(broadcast);
    }

    /**
     * Send a status update to the rest of the application
     *
     */

    private void broadcast(final String data)
    {
        DebugLogManager.INSTANCE.log("Megasquirt.broadcast(" + ApplicationSettings.GENERAL_MESSAGE + "," + data + ")", Log.VERBOSE);
        final Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.GENERAL_MESSAGE);
        broadcast.putExtra(ApplicationSettings.MESSAGE, data);
        sendBroadcast(broadcast);
    }

    private void broadcast()
    {
        final Intent broadcast = new Intent();
        broadcast.setAction(NEW_DATA);

        sendBroadcast(broadcast);

    }

    /**
     * How long have we been running?
     *
     */
    @Override
    public int timeNow()
    {
        if (logStart == 0)
        {
            logStart = DatalogManager.INSTANCE.getLogStart();
        }
        final long runTime = System.currentTimeMillis() - logStart;
        final double timeNow = runTime / 1000.0;

        return (int) timeNow;
    }

    /**
     * Flag the logging process to happen
     */
    public void startLogging()
    {
        if (currentState == State.CONNECTED)
        {
            currentState = State.LOGGING;
            DebugLogManager.INSTANCE.log("startLogging()", Log.INFO);
        }
    }

    /**
     * Stop the logging process
     */
    public void stopLogging()
    {
        if (currentState == State.LOGGING)
        {
            DebugLogManager.INSTANCE.log("stopLogging()", Log.INFO);
            currentState = State.CONNECTED;
            FRDLogManager.INSTANCE.close();
            DatalogManager.INSTANCE.close();
        }
    }

    /**
     * Take a wild stab at what this does.
     *
     */
    @Override
    public double round(final double v)
    {
        return Math.floor((v * 100) + .5) / 100;
    }

    /**
     * Returns if a flag has been set in the application
     *
     */
    @Override
    public boolean isSet(final String name)
    {
        return ApplicationSettings.INSTANCE.isSet(name);
    }

    /**
     * The thread that handles all communications with the ECU. This must be done in it's own thread as Android gets very picky about unresponsive UI
     * threads
     */
    private class ECUThread extends Thread
    {
        int interWriteDelay;

        private class CalculationThread extends Thread
        {
            private volatile boolean running = true;

            public void halt()
            {
                DebugLogManager.INSTANCE.log("CalculationThread.halt()", Log.INFO);

                running = false;
            }

            @Override
            public void run()
            {
                this.setName("CalculationThread");
                try
                {
                    while (running)
                    {
                        final byte[] buffer = handshake.get();
                        if (ecuImplementation != null)
                        {
                            ecuImplementation.calculate(buffer);
                            logValues(buffer);
                            broadcast();
                        }
                    }
                }
                catch (final InterruptedException e)
                {
                    // Swallow, we're on our way out.
                }
            }

        }

        class Handshake
        {
            private byte[] buffer;

            public void put(final byte[] buf)
            {
                buffer = buf;
                synchronized (this)
                {
                    notify();
                }
            }

            public byte[] get() throws InterruptedException
            {
                synchronized (this)
                {
                    wait();
                }
                return buffer;
            }
        }

        Handshake handshake = new Handshake();
        CalculationThread calculationThread = new CalculationThread();

        /**
         * 
         */
        public ECUThread()
        {
            if (watch != null)
            {
                DebugLogManager.INSTANCE.log("Attempting to create second connection!", Log.ASSERT);
            }
            watch = this;
            final String name = "ECUThread:" + System.currentTimeMillis();
            setName(name);
            DebugLogManager.INSTANCE.log("Creating ECUThread named " + name, Log.VERBOSE);

            interWriteDelay = Integer.parseInt(ApplicationSettings.INSTANCE.getOrSetPref("iwd", "0"));
            calculationThread.start();

        }

        /**
         * Kick the connection off
         */
        public void initialiseConnection()
        {
            final String btAddress = ApplicationSettings.INSTANCE.getECUBluetoothMac();
            ECUConnectionManager.getInstance().init(null, btAddress);
        }

        /**
         * The main loop of the connection to the ECU
         */
        @Override
        public void run()
        {
            try
            {
                setState(Megasquirt.State.CONNECTING);
                sendMessage("Starting connection");
                DebugLogManager.INSTANCE.log("BEGIN connectedThread", Log.INFO);
                initialiseConnection();

                delay(500);

                try
                {
                    ECUConnectionManager.getInstance().flushAll();
                    initialiseImplementation();
                    /*
                     * Make sure we have calculated runtime vars at least once before refreshing flags. The reason is that the refreshFlags() function
                     * also trigger the creation of menus/dialogs/tables/curves/etc that use variables such as {clthighlim} in curves that need to
                     * have their value assigned before being used.
                     */
                    try
                    {
                        final byte[] bufferRV = getRuntimeVars();
                        ecuImplementation.calculate(bufferRV);
                    }
                    catch (final CRC32Exception e)
                    {
                        DebugLogManager.INSTANCE.logException(e);
                    }
                    catch (final BTTimeoutException e)
                    {
                        DebugLogManager.INSTANCE.logException(e);
                    }

                    // Make sure everyone agrees on what flags are set
                    ApplicationSettings.INSTANCE.refreshFlags();
                    ecuImplementation.refreshFlags();

                    if (!constantsLoaded)
                    {
                        // Only do this once so reconnects are quicker
                        ecuImplementation.loadConstants(false);
                        constantsLoaded = true;

                    }
                    sendMessage("Connected to " + getTrueSignature());
                    setState(Megasquirt.State.CONNECTED);
                    long lastRpsTime = System.currentTimeMillis();
                    double readCounter = 0;

                    // This is the actual work. Outside influences will toggle 'running' when we want this to stop
                    while ((currentState == Megasquirt.State.CONNECTED) || (currentState == Megasquirt.State.LOGGING))
                    {
                        try
                        {
                            if (injectionQueue.peek() != null)
                            {
                                for (final InjectedCommand i : injectionQueue)
                                {
                                    processCommand(i);
                                }

                                injectionQueue.clear();
                            }
                            final byte[] buffer = getRuntimeVars();
                            handshake.put(buffer);
                        }
                        catch (final CRC32Exception e)
                        {
                            DatalogManager.INSTANCE.mark(e.getLocalizedMessage());
                            DebugLogManager.INSTANCE.logException(e);
                        }
                        catch (final BTTimeoutException e)
                        {
                            DatalogManager.INSTANCE.mark(e.getLocalizedMessage());
                            DebugLogManager.INSTANCE.logException(e);
                            
                            // Get out of the loop, we're going to disconnect
                            break;
                        }
                        catch (final IOException e)
                        {
                            DatalogManager.INSTANCE.mark(e.getLocalizedMessage());
                            DebugLogManager.INSTANCE.logException(e);
                            initialiseConnection();
                            ECUConnectionManager.getInstance().connect();
                        }
                        readCounter++;

                        final long delay = System.currentTimeMillis() - lastRpsTime;
                        if (delay > 1000)
                        {
                            final double RPS = (readCounter / delay) * 1000;
                            readCounter = 0;
                            lastRpsTime = System.currentTimeMillis();

                            if (RPS > 0)
                            {
                                sendRPS(RPS);
                            }
                        }

                    }
                }
                catch (final IOException e)
                {
                    DebugLogManager.INSTANCE.logException(e);
                }
                catch (final CRC32Exception e)
                {
                    DebugLogManager.INSTANCE.logException(e);
                }
                catch (final ArithmeticException e)
                {
                    // If we get a maths error, we probably have loaded duff constants and hit a divide by zero
                    // force the constants to reload in case it was just a bad data read
                    DebugLogManager.INSTANCE.logException(e);
                    constantsLoaded = false;
                }
                catch (final RuntimeException t)
                {
                    DebugLogManager.INSTANCE.logException(t);
                    throw (t);
                }
                // We're on our way out, so drop the connection
                disconnect();
            }
            finally
            {
                calculationThread.halt();
                calculationThread.interrupt();
                watch = null;
            }
        }

        private void processCommand(final InjectedCommand i) throws IOException
        {
            ECUConnectionManager.getInstance().writeCommand(i.getCommand(), i.getDelay(), ecuImplementation.isCRC32Protocol());

            // If we want to get the result back
            if (i.isReturnResult())
            {
                final Intent broadcast = new Intent();
                broadcast.setAction(INJECTED_COMMAND_RESULTS);

                final byte[] result = ECUConnectionManager.getInstance().readBytes();

                broadcast.putExtra(INJECTED_COMMAND_RESULT_ID, i.getResultId());
                broadcast.putExtra(INJECTED_COMMAND_RESULT_DATA, result);

                sendBroadcast(broadcast);
            }
        }

        private void initialiseImplementation() throws IOException, CRC32Exception
        {
            sendMessage("Checking your ECU");
            final String signature = getSignature();

            setImplementation(signature);
        }

        private String getSignature() throws IOException, CRC32Exception
        {
            final byte[] bootCommand = { 'X' };
            final String lastSuccessfulProbeCommand = ApplicationSettings.INSTANCE.getPref(LAST_PROBE);
            final String lastSig = ApplicationSettings.INSTANCE.getPref(LAST_SIG);

            if ((lastSuccessfulProbeCommand != null) && (lastSig != null))
            {
                final byte[] probe = lastSuccessfulProbeCommand.getBytes();
                // We need to loop as a BT adapter can pump crap into the MS at the start which confuses the poor thing.
                for (int i = 0; i < 3; i++)
                {
                    byte[] response = ECUConnectionManager.getInstance().writeAndRead(probe, 50, false);
                    try
                    {
                        final String sig = processResponse(response);
                        if (lastSig.equals(sig))
                        {
                            return sig;
                        }
                    }
                    catch (final BootException e)
                    {
                        ECUConnectionManager.getInstance().writeAndRead(bootCommand, 500, false);
                    }
                }
            }
            final String probeCommand1 = "Q";
            final String probeCommand2 = "S";
            String probeUsed;
            int i = 0;
            String sig = UNKNOWN;

            // IF we don't get it in 20 goes, we're not talking to a Megasquirt
            while (i++ < 20)
            {
                probeUsed = probeCommand1;
                byte[] response = ECUConnectionManager.getInstance().writeAndRead(probeUsed.getBytes(), 500, false);

                try
                {
                    if ((response != null) && (response.length > 1))
                    {
                        sig = processResponse(response);
                    }
                    else
                    {
                        probeUsed = probeCommand2;
                        response = ECUConnectionManager.getInstance().writeAndRead(probeUsed.getBytes(), 500, false);
                        if ((response != null) && (response.length > 1))
                        {
                            sig = processResponse(response);
                        }
                    }
                    if (!UNKNOWN.equals(sig))
                    {
                        ApplicationSettings.INSTANCE.setPref(LAST_PROBE, probeUsed);
                        ApplicationSettings.INSTANCE.setPref(LAST_SIG, sig);
                        ECUConnectionManager.getInstance().flushAll();
                        break;
                    }
                }
                catch (final BootException e)
                {
                    /*
                     * My ECU also occasionally goes to a Boot> prompt on start up (dodgy electrics) so if we see that, force the ECU to start.
                     */
                    ECUConnectionManager.getInstance().writeAndRead(bootCommand, 500, false);
                }
            }

            return sig;
        }

        /**
         * Attempt to figure out the data we got back from the device
         *
         */
        private String processResponse(final byte[] response) throws BootException
        {
            final String result = new String(response);
            trueSignature = result;
            if (result.contains("Boot>"))
            {
                throw new BootException();
            }

            if (response == null)
            {
                return UNKNOWN;
            }

            // Early ECUs only respond with one byte
            if ((response.length == 1) && (response[0] != 20))
            {
                return UNKNOWN;
            }

            if (response.length <= 1)
            {
                return UNKNOWN;
            }

            // Examine the first few bytes and see if it smells of one of the things an MS may say to us.
            if (((response[0] != 'M') && (response[0] != 'J')) || ((response[1] != 'S') && (response[1] != 'o') && (response[1] != 'i')))
            {
                return UNKNOWN;
            }

            // Looks like we have a Megasquirt
            return result;
        }

        /**
         * Get the current variables from the ECU
         *
         */
        private byte[] getRuntimeVars() throws IOException, CRC32Exception, BTTimeoutException
        {
            if (interWriteDelay > 0)
            {
                delay(interWriteDelay);
            }
            final byte[] buffer = new byte[ecuImplementation.getBlockSize()];

            final int delay = interWriteDelay == 0 ? ecuImplementation.getInterWriteDelay() : interWriteDelay;
            ECUConnectionManager.getInstance().writeAndRead(ecuImplementation.getOchCommand(), buffer, delay, ecuImplementation.isCRC32Protocol());
            return buffer;
        }

        private void delay(final int delay)
        {
            try
            {
                Thread.sleep(delay);
            }
            catch (final InterruptedException e)
            {
                DebugLogManager.INSTANCE.logException(e);
            }
        }

        /**
         * Read a page of constants from the ECU into a byte buffer. MS1 uses a select/read combo, MS2 just does a read
         *
         */
        protected void getPage(final byte[] pageBuffer, final byte[] pageSelectCommand, final byte[] pageReadCommand) throws IOException, CRC32Exception
        {
            ECUConnectionManager.getInstance().flushAll();
            for (int i = 1; i <= 5; i++)
            {
                final int delay = ecuImplementation.getPageActivationDelay() * i;
                if (pageSelectCommand != null)
                {
                    ECUConnectionManager.getInstance().writeCommand(pageSelectCommand, delay, ecuImplementation.isCRC32Protocol());
                }
                if (pageReadCommand != null)
                {
                    ECUConnectionManager.getInstance().writeCommand(pageReadCommand, delay, ecuImplementation.isCRC32Protocol());
                }
                try
                {
                    ECUConnectionManager.getInstance().readBytes(pageBuffer, ecuImplementation.isCRC32Protocol());
                    return;
                }
                catch (final BTTimeoutException e)
                {
                    DebugLogManager.INSTANCE.logException(e);
                }
            }
        }
    }

    /**
     *
     */
    public String getTrueSignature()
    {
        return trueSignature;
    }

    /**
     * helper method for subclasses
     *
     */
    @Override
    public byte[] loadPage(final int pageNo, final int pageOffset, final int pageSize, final byte[] select, final byte[] read)
    {

        final byte[] buffer = new byte[pageSize];
        try
        {
            sendMessage("Loading constants from page " + pageNo);
            getPage(buffer, select, read);
            savePage(pageNo, buffer);
            sendMessage("Constants loaded from page " + pageNo);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
            DebugLogManager.INSTANCE.logException(e);
            sendMessage("Error loading constants from page " + pageNo);
        }
        catch (final CRC32Exception e)
        {
            e.printStackTrace();
            DebugLogManager.INSTANCE.logException(e);
            sendMessage("Error loading constants from page " + pageNo);
        }
        return buffer;
    }

    /**
     *
     */
    private void getPage(final byte[] buffer, final byte[] select, final byte[] read) throws IOException, CRC32Exception
    {
        ecuThread.getPage(buffer, select, read);
    }

    /**
     * Dumps a loaded page to SD card for analysis
     *
     */
    private void savePage(final int pageNo, final byte[] buffer)
    {

        try
        {
            final File dir = new File(Environment.getExternalStorageDirectory(), "MSLogger");

            if (!dir.exists())
            {
                final boolean mkDirs = dir.mkdirs();
                if (!mkDirs)
                {
                    DebugLogManager.INSTANCE.log("Unable to create directory MSLogger at " + Environment.getExternalStorageDirectory(), Log.ERROR);
                }
            }

            final String fileName = ecuImplementation.getClass().getName() + ".firmware";
            final File outputFile = new File(dir, fileName);
            BufferedOutputStream out = null;
            try
            {
                final boolean append = !(pageNo == 1);
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
        catch (final IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
    }





    public void setField(final String channelName, final int value)
    {
        final Class<?> c = ecuImplementation.getClass();

        try
        {
            final Field f = c.getDeclaredField(channelName);
            f.setInt(ecuImplementation, value);
        }
        catch (final NoSuchFieldException e)
        {
            DebugLogManager.INSTANCE.log("Failed to set value to " + value + " for " + channelName + ", no such field", Log.ERROR);
        }
        catch (final IllegalArgumentException e)
        {
            DebugLogManager.INSTANCE.log("Failed to set value to " + value + " for " + channelName + ", illegal argument", Log.ERROR);
        }
        catch (final IllegalAccessException e)
        {
            DebugLogManager.INSTANCE.log("Failed to set value to " + value + " for " + channelName + ", illegal access", Log.ERROR);
        }
    }



    /**
     * Load a byte array contained in pageBuffer from the specified offset and width
     * 
     * @param pageBuffer The buffer where the byte array is located
     * @param offset The offset where the byte array is located
     * @param width The width of the byte array
     * @param height The height of the byte array
     * @param signed Is the data signed ?
     *
     */
    @Override
    public int[][] loadByteArray(final byte[] pageBuffer, final int offset, final int width, final int height, final boolean signed)
    {
        final int[][] destination = new int[width][height];
        int index = offset;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                final int value = signed ? MSUtils.INSTANCE.getSignedByte(pageBuffer, index) : MSUtils.INSTANCE.getByte(pageBuffer, index);
                destination[x][y] = value;
                index = index + 1;
            }
        }
        return destination;
    }

    /**
     * Load a byte vector contained in pageBuffer from the specified offset and width
     * 
     * @param pageBuffer The buffer where the byte vector is located
     * @param offset The offset where the byte vector is located
     * @param width The width of the byte vector
     * @param signed Is the data signed ?
     *
     */
    @Override
    public int[] loadByteVector(final byte[] pageBuffer, final int offset, final int width, final boolean signed)
    {
        final int[] destination = new int[width];
        int index = offset;
        for (int x = 0; x < width; x++)
        {
            final int value = signed ? MSUtils.INSTANCE.getSignedByte(pageBuffer, index) : MSUtils.INSTANCE.getByte(pageBuffer, index);
            destination[x] = value;
            index = index + 1;
        }

        return destination;
    }

    /**
     * Load a word array contained in pageBuffer from the specified offset and width
     * 
     * @param pageBuffer The buffer where the word array is located
     * @param offset The offset where the word array is located
     * @param width The width of the word array
     * @param height The height of the word array
     * @param signed Is the data signed ?
     *
     */
    @Override
    public int[][] loadWordArray(final byte[] pageBuffer, final int offset, final int width, final int height, final boolean signed)
    {
        final int[][] destination = new int[width][height];
        int index = offset;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                final int value = signed ? MSUtils.INSTANCE.getSignedWord(pageBuffer, index) : MSUtils.INSTANCE.getWord(pageBuffer, index);
                destination[x][y] = value;
                index = index + 2;
            }
        }

        return destination;
    }

    /**
     * Load a word vector contained in pageBuffer from the specified offset and width
     * 
     * @param pageBuffer The buffer where the word vector is located
     * @param offset The offset where the word vector is located
     * @param width The width of the word vector
     * @param signed Is the data signed ?
     *
     */
    @Override
    public int[] loadWordVector(final byte[] pageBuffer, final int offset, final int width, final boolean signed)
    {
        final int[] destination = new int[width];
        int index = offset;
        for (int x = 0; x < width; x++)
        {
            final int value = signed ? MSUtils.INSTANCE.getSignedWord(pageBuffer, index) : MSUtils.INSTANCE.getWord(pageBuffer, index);
            destination[x] = value;
            index = index + 2;
        }

        return destination;

    }

    /**
     * Helper function to know if a constant name exists
     * 
     * @param name The name of the constant
     * @return true if the constant exists, false otherwise
     */
    public boolean isConstantExists(final String name)
    {
        return MSECUInterface.constants.containsKey(name);
    }

    /**
     * Get a constant from the ECU class
     * 
     * @param name The name of the constant
     * @return The constant object
     */
    public Constant getConstantByName(final String name)
    {
        return MSECUInterface.constants.get(name);
    }

    /**
     * Get an output channel from the ECU class
     * 
     * @param name The name of the output channel
     * @return The output channel object
     */
    public OutputChannel getOutputChannelByName(final String name)
    {
        return MSECUInterface.outputChannels.get(name);
    }

    /**
     * Get a list of menus from the ECU class
     * 
     * @param name The name of the menu tree
     * @return A list of menus object
     */
    public List<Menu> getMenusForDialog(final String name)
    {
        return MSECUInterface.menus.get(name);
    }

    /**
     * Get a dialog from the ECU class
     * 
     * @param name The name of the dialog object
     * @return The dialog object
     */
    public MSDialog getDialogByName(final String name)
    {
        return MSECUInterface.dialogs.get(name);
    }


    public int getBlockSize()
    {
        return ecuImplementation.getBlockSize();
    }

    public int getCurrentTPS()
    {
        return ecuImplementation.getCurrentTPS();
    }
    public String getLogHeader()
    {
        return ecuImplementation.getLogHeader();
    }
    public void refreshFlags()
    {
        ecuImplementation.refreshFlags();
    }



    public String[] getControlFlags()
    {
        return ecuImplementation.getControlFlags();
    }


    public List<SettingGroup> getSettingGroups()
    {
        ecuImplementation.createSettingGroups();
        return ecuImplementation.getSettingGroups();
    }

    public Map<String, String> getControllerCommands()
    {
        ecuImplementation.createControllerCommands();
        return ecuImplementation.getControllerCommands();
    }


    public int getCylindersCount()
    {
        return (int) (isConstantExists("nCylinders") ? getField("nCylinders") : getField("nCylinders1"));
    }

    /**
     * @return Return the current ECU divider
     */
    public int getDivider()
    {
        return (int) (isConstantExists("divider") ? getField("divider") : getField("divider1"));
    }
    public int getInjectorStating()
    {
        return (int) (isConstantExists("alternate") ? getField("alternate") : getField("alternate1"));
    }

    public double getField(final String channelName)
    {
        double value = 0;
        final Class<?> c = ecuImplementation.getClass();
        try
        {
            final Field f = c.getDeclaredField(channelName);
            value = f.getDouble(ecuImplementation);
        }
        catch (final Exception e)
        {
            DebugLogManager.INSTANCE.log("Failed to get value for " + channelName, Log.ERROR);
        }
        return value;
    }

    private void setImplementation(final String signature)
    {
        final Class<? extends MSECUInterface> ecuClass = ECURegistry.INSTANCE.findEcu(signature);

        if ((ecuImplementation != null) && ecuImplementation.getClass().equals(ecuClass))
        {
            broadcast(PROBE_ECU);
            return;
        }

        Constructor<? extends MSECUInterface> constructor;
        try
        {
            constructor = ecuClass.getConstructor(MSControllerInterface.class, MSUtilsInterface.class);

            ecuImplementation = constructor.newInstance(Megasquirt.this, MSUtils.INSTANCE);

            if (!signature.equals(ecuImplementation.getSignature()))
            {
                trueSignature = ecuImplementation.getSignature();

                final String msg = "Got unsupported signature from Megasquirt \"" + trueSignature + "\" but found a similar supported signature \"" + signature + "\"";

                sendToastMessage(msg);
                DebugLogManager.INSTANCE.log(msg, Log.INFO);
            }
            sendMessage("Found " + trueSignature);

        }
        catch (final Exception e)
        {
            DebugLogManager.INSTANCE.logException(e);
            broadcast(UNKNOWN_ECU);
        }
        broadcast(PROBE_ECU);
    }

    @Override
    public void registerOutputChannel(final OutputChannel o)
    {
        DataManager.getInstance().addOutputChannel(o);

    }

}
