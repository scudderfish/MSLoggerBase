package uk.org.smithfamily.mslogger;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import uk.org.smithfamily.mslogger.parser.MsDatabase;
import uk.org.smithfamily.mslogger.parser.Repository;
import uk.org.smithfamily.mslogger.parser.Symbol;
import uk.org.smithfamily.mslogger.parser.log.DebugLogManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

public class MSControlService extends Service implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private static final DebugLogManager dblog             = DebugLogManager.getInstance();
    private static AtomicBoolean         initialiseStarted = new AtomicBoolean(false);
    protected static final String        CONNECTED         = "uk.org.smithfamily.mslogger.CONNECTED";
    protected static final String        NEW_DATA          = "uk.org.smithfamily.mslogger.NEW_DATA";
    private static final int             NOTIFICATION_ID   = 42;
    protected static final int           CLEAR_MESSAGES    = 0;
    
    private boolean                      logging           = false;
    private NotificationManager          mNM;
    private final LocalBinder            mBinder           = new LocalBinder();
    private Handler                      mHandler          = new Handler();
    private List<Symbol>                 currentOutputs    = null;
    private Runnable                     mUpdateTimeTask   = new Runnable()
                                                           {
                                                               public void run()
                                                               {
                                                                   // Debug.startMethodTracing("MSService");
                                                                   int delayTime = 100;
                                                                   long start = System.currentTimeMillis();
                                                                   boolean connected = MsDatabase.getInstance().calculateRuntime();
                                                                   dblog.log("calculateRuntime() : "
                                                                           + (System.currentTimeMillis() - start));
                                                                   if (connected)
                                                                   {
                                                                       handleData();
                                                                       Intent broadcast = new Intent();
                                                                       broadcast.setAction(NEW_DATA);
                                                                       // broadcast.putExtra(LOCATION, location);
                                                                       sendBroadcast(broadcast);
                                                                   }
                                                                   else
                                                                   {
                                                                       delayTime = 1000;
                                                                   }
                                                                   // Debug.stopMethodTracing();
                                                                   if (logging)
                                                                       mHandler.postDelayed(this, delayTime);
                                                               }
                                                           };

                                                           
    /*                                                       
     private BroadcastReceiver            updateReceiver    = new BroadcastReceiver()
                                                           {

                                                               @Override
                                                               public void onReceive(Context context, Intent intent)
                                                               {
                                                                   if (intent.getAction().equals(MSControlService.CONNECTED))
                                                                   {
                                                                       startLogging();
                                                                   }
                                                               }

                                                           };
*/
    public class LocalBinder extends Binder
    {
        MSControlService getService()
        {
            return MSControlService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0)
    {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Display a notification about us starting. We put an icon in the
        // status bar.
        // showNotification(R.string.location_service_started);

        return mBinder;
        // interface.

    }
    public boolean isLogging()
    {
        return logging;
    }

    private void showNotification(int msg)
    {
        if (mNM == null)
            return;

        mNM.cancelAll();
        if (msg == CLEAR_MESSAGES)
        {
            return;
        }
        // What happens when the notification item is clicked
        Intent contentIntent = new Intent(this, MSParserActivity.class);

        PendingIntent pending = PendingIntent.getActivity(getBaseContext(), 0, contentIntent,
                android.content.Intent.FLAG_ACTIVITY_NEW_TASK);

        Notification nfc = new Notification(R.drawable.injector, null, System.currentTimeMillis());
        nfc.flags |= Notification.FLAG_ONGOING_EVENT;

        String contentText = getString(msg);

        nfc.setLatestEventInfo(getBaseContext(), getString(msg), contentText, pending);

        mNM.notify(NOTIFICATION_ID, nfc);
    }

    protected void handleData()
    {
        currentOutputs = MsDatabase.getInstance().cDesc.getOutputChannels();
    }

    public List<Symbol> getCurrentData()
    {
        return currentOutputs;
    }

    public void startLogging()
    {
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);
        logging = true;

    }

    public void stopLogging()
    {
        mHandler.removeCallbacks(mUpdateTimeTask);
        logging = false;
    }

    @Override
    public void onCreate()
    {
        dblog.log("MSControlService:onCreate()");
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        settings.registerOnSharedPreferenceChangeListener(this);

        startInitialisation();

        /*
        IntentFilter connectionFilter = new IntentFilter(MSControlService.CONNECTED);
        IntentFilter newDataFilter = new IntentFilter(MSControlService.NEW_DATA);
        registerReceiver(updateReceiver, connectionFilter);
        registerReceiver(updateReceiver, newDataFilter);
*/
    }

    private void startInitialisation()
    {
        if (initialiseStarted.getAndSet(true))
        {
            return;
        }

        new Thread(new Runnable()
        {
            public void run()
            {
                showNotification(R.string.connecting);
                Looper.prepare();
                while (Repository.getInstance().readInit(MSControlService.this) == false)
                {
                    showNotification(R.string.cannot_connect);
                    try
                    {
                        Thread.sleep(5000);

                    }
                    catch (InterruptedException e)
                    {
                    }
                    showNotification(R.string.connecting);
                }

                Intent broadcast = new Intent();
                broadcast.setAction(CONNECTED);
                // broadcast.putExtra(LOCATION, location);
                sendBroadcast(broadcast);
                showNotification(R.string.connected);

            }
        }).start();

    }

    @Override
    public void onDestroy()
    {
        stopLogging();
        dblog.log("MSControlService:onDestroy()");
        mHandler.removeCallbacks(mUpdateTimeTask);
        showNotification(CLEAR_MESSAGES);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        settings.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i("LocationService", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
    }
}
