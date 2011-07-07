package uk.org.smithfamily.msparser;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import uk.org.smithfamily.msdisp.parser.Repository;
import uk.org.smithfamily.msdisp.parser.Symbol;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

public class MSControlService extends Service implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private static AtomicBoolean  initialiseStarted = new AtomicBoolean(false);
    protected static final String CONNECTED         = "uk.org.smithfamily.msparser.CONNECTED";
    protected static final String NEW_DATA          = "uk.org.smithfamily.msparser.NEW_DATA";
    private static final int      NOTIFICATION_ID   = 42;
    protected static final int    CLEAR_MESSAGES    = 0;
    private boolean               diediedie         = false;
    private NotificationManager   mNM;
    private final LocalBinder     mBinder           = new LocalBinder();
    private Handler               mHandler          = new Handler();
    private List<Symbol>          currentOutputs    = null;
    private Runnable              mUpdateTimeTask   = new Runnable()
                                                    {
                                                        public void run()
                                                        {
                                                            int delayTime = 100;
                                                            boolean connected = MsDatabase.getInstance().getRuntime();
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
                                                            if (!diediedie)
                                                                mHandler.postDelayed(this, delayTime);
                                                        }
                                                    };
    private BroadcastReceiver     updateReceiver    = new BroadcastReceiver()
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

        Notification nfc = new Notification(R.drawable.icon, null, System.currentTimeMillis());
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

    protected void startLogging()
    {
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);

    }

    @Override
    public void onCreate()
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        settings.registerOnSharedPreferenceChangeListener(this);

        startInitialisation();

        IntentFilter connectionFilter = new IntentFilter(MSControlService.CONNECTED);
        IntentFilter newDataFilter = new IntentFilter(MSControlService.NEW_DATA);
        registerReceiver(updateReceiver, connectionFilter);
        registerReceiver(updateReceiver, newDataFilter);

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
        diediedie=true;
        mHandler.removeCallbacks(mUpdateTimeTask);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        settings.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
        showNotification(CLEAR_MESSAGES);
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
