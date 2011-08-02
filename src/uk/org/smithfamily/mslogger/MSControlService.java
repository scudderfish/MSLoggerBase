package uk.org.smithfamily.mslogger;

import java.io.File;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.app.*;
import android.content.*;
import android.location.LocationManager;
import android.os.*;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import bsh.commands.LocationController;

public class MSControlService extends Service implements SharedPreferences.OnSharedPreferenceChangeListener
{
	private static final DebugLogManager	dblog				= DebugLogManager.INSTANCE;
	private static AtomicBoolean			initialiseStarted	= new AtomicBoolean(false);
	protected static final String			CONNECTED			= "uk.org.smithfamily.mslogger.CONNECTED";
	protected static final String			NEW_DATA			= "uk.org.smithfamily.mslogger.NEW_DATA";
	private static final int				NOTIFICATION_ID		= 42;
	protected static final int				CLEAR_MESSAGES		= 0;

	private boolean							logging				= false;
	private NotificationManager				mNM;
	private final LocalBinder				mBinder				= new LocalBinder();
	private LocationManager					gpsLocationManager;
	private Thread							controller;
	private Megasquirt						ecu;
	private BroadcastReceiver				updateReceiver;

	/*
	 * private BroadcastReceiver updateReceiver = new BroadcastReceiver() {
	 * 
	 * @Override public void onReceive(Context context, Intent intent) { if
	 * (intent.getAction().equals(MSControlService.CONNECTED)) { startLogging();
	 * } }
	 * 
	 * };
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
		gpsLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		gpsLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, LocationController.INSTANCE);

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
		Intent contentIntent = new Intent(this, MSLoggerActivity.class);

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
	}

	public void startLogging()
	{
		Date now = new Date();

		String fileName = DateFormat.format("yyyyMMddkkmmss", now).toString() + ".msl";
		File logFile = new File(ApplicationSettings.INSTANCE.getDataDir(), fileName);
		// Datalog.INSTANCE.open(logFile);

		logging = true;

	}

	public void stopLogging()
	{
		logging = false;
		// Datalog.INSTANCE.close();
	}

	@Override
	public void onCreate()
	{
		dblog.log("MSControlService:onCreate()", this);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		settings.registerOnSharedPreferenceChangeListener(this);

		startInitialisation();
		IntentFilter newDataFilter = new IntentFilter(MSControlService.NEW_DATA);
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
				ApplicationSettings.INSTANCE.initialise(MSControlService.this);
				ecu = ApplicationSettings.INSTANCE.getEcuDefinition();

				while (!ecu.initialised())
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
				controller = new Thread(ecu);
				controller.setDaemon(true);
				controller.start();
			}

		}).start();

	}

	@Override
	public void onDestroy()
	{
		if (controller != null)
		{
			ecu.setRunning(false);
		}
		stopLogging();
		this.updateReceiver=null;	
		dblog.log("MSControlService:onDestroy()", this);
		showNotification(CLEAR_MESSAGES);
		gpsLocationManager.removeUpdates(LocationController.INSTANCE);

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
