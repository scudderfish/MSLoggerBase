package uk.org.smithfamily.mslogger.activity;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.*;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt.ConnectionState;
import uk.org.smithfamily.mslogger.log.*;
import uk.org.smithfamily.mslogger.service.MSLoggerService;
import uk.org.smithfamily.mslogger.widgets.*;
import android.app.*;
import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.content.SharedPreferences.Editor;
import android.content.pm.*;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.net.Uri;
import android.os.*;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.*;

import com.android.vending.licensing.*;

public class MSLoggerActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener, OnClickListener
{
	MSLoggerService					service;
	private static final int		REQUEST_ENABLE_BT	= 0;
	private BroadcastReceiver		updateReceiver		= new Reciever();
	private IndicatorManager		indicatorManager;
	TextView						messages;
	public boolean					connected;
	private boolean					receivedData		= false;
	private boolean					ready				= false;

	private MSGauge					gauge1;
	private MSGauge					gauge2;
	private MSGauge					gauge3;
	private MSGauge					gauge4;
	private MSGauge					gauge5;

	private ServiceConnection		mConnection			= new MSServiceConnection();
	private GestureDetector			gestureDetector;
	private boolean					gaugeEditEnabled;
	boolean							scrolling;
	private LinearLayout			layout;
	protected Dialog				mSplashDialog;
	private Handler					mHandler;
	private static final byte[]		SALT				= new byte[] { 124, 172 - 255, 82, 169 - 255, 179 - 255, 25, 173 - 255,
			157 - 255, 200 - 255, 245 - 255, 125, 60, 228 - 255, 80, 81, 45, 184 - 255, 54, 176 - 255, 217 - 255 };
	private static final String		BASE64_PUBLIC_KEY	= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA30H1+YM+Ddz3Qbdz4"
																+ "Q1utp2uq/DLH8bw3qWpef39tkal45kHrVRIaWMlrryUshj0JCTXbfoeQVvGTHEbzJw6BWiU3smf3pqwW36lBWOWYocqiWLWeME0qI"
																+ "tgVR3dYPEWD1AbBrCCyxn9mizZpHSGVCIxK7yTo8JxDIcZOMc4HUGRX0FYHPI837K+Ivg4NbJFuT21NHq0wEu8i/r5GHVXoW06QmR"
																+ "vNlFQQkvGHTiNlu9MbCFJlETBYUBm5cteeJMW/euOvHTIcAYKlB65JUdgBH6gAe88y5I8uTSUJyhmxCQ7SO8S/BnonzCncOmwdgSn"
																+ "mxMFMXMWMgKN1bsLlHiKUQIDAQAB";

	private LicenseCheckerCallback	mLicenseCheckerCallback;
	private LicenseChecker			mChecker;

	private class MSLoggerCheckerCallback implements LicenseCheckerCallback
	{
		public void allow()
		{
			if (isFinishing())
			{
				// Don't update UI if Activity is finishing.
				return;
			}
			// Should allow user access.
			displayResult("Excellent!");
		}

		public void dontAllow()
		{
			ApplicationSettings.INSTANCE.setAutoConnectOverride(false);
			ApplicationSettings.INSTANCE.setLoggingOverride(false);
			ApplicationSettings.INSTANCE.getEcuDefinition().stop();

			if (isFinishing())
			{
				// Don't update UI if Activity is finishing.
				return;
			}
			displayResult("Denied!");
			showDialog(0);
		}

		public void applicationError(ApplicationErrorCode errorCode)
		{
			if (isFinishing())
			{
				// Don't update UI if Activity is finishing.
				return;
			}
			// This is a polite way of saying the developer made a mistake
			// while setting up or calling the license checker library.
			// Please examine the error code and fix the error.
			System.out.println("Bork!");
		}
	}

	public class GaugeTouchListener implements OnTouchListener
	{

		private MSGauge	gauge;

		public GaugeTouchListener(MSGauge gauge)
		{
			this.gauge = gauge;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			// System.out.println(event);
			if (event.getAction() == MotionEvent.ACTION_DOWN)
			{
				String g3name = gauge3.getName();
				gauge3.initFromName(gauge.getName());
				gauge.initFromName(g3name);
				gauge.invalidate();
				gauge3.invalidate();
				return true;
			}
			return false;
		}

	}

	private final class MSServiceConnection implements ServiceConnection
	{

		public void onServiceConnected(ComponentName className, IBinder binder)
		{
			service = ((MSLoggerService.MSLoggerBinder) binder).getService();
		}

		public void onServiceDisconnected(ComponentName className)
		{
			service = null;
		}
	}

	private final class Reciever extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			Log.i(ApplicationSettings.TAG, "Received :" + intent.getAction());
			if (intent.getAction().equals(Megasquirt.CONNECTED))
			{
				DebugLogManager.INSTANCE.log(intent.getAction());
				indicatorManager.setDisabled(false);
				if (ApplicationSettings.INSTANCE.shouldBeLogging())
				{
					service.startLogging();
				}
			}
			if (intent.getAction().equals(Megasquirt.CONNECTION_LOST))
			{
				indicatorManager.setDisabled(true);
				if (ApplicationSettings.INSTANCE.shouldBeLogging())
				{
					DatalogManager.INSTANCE.mark("Connection Lost");
				}
				DebugLogManager.INSTANCE.log(intent.getAction());
			}

			if (intent.getAction().equals(Megasquirt.NEW_DATA))
			{
				processData();
				receivedData = true;
			}
			if (intent.getAction().equals(ApplicationSettings.GENERAL_MESSAGE))
			{
				String msg = intent.getStringExtra(ApplicationSettings.MESSAGE);

				messages.setText(msg);
				Log.i(ApplicationSettings.TAG, "Message : " + msg);
				DebugLogManager.INSTANCE.log("Message : " + msg);

			}
		}
	}

	synchronized void doBindService()
	{
		startService(new Intent(MSLoggerActivity.this, MSLoggerService.class));
		bindService(new Intent(this, MSLoggerService.class), mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onStop()
	{
		super.onStop();
		saveGauges();
	}

	private void checkBTDeviceSet()
	{
		if (!ApplicationSettings.INSTANCE.btDeviceSelected())
		{
			Toast.makeText(this, R.string.please_select, Toast.LENGTH_SHORT);
		}
	}

	private void saveGauges()
	{
		if(!(gauge1 != null && gauge2 != null && gauge3 != null && gauge4 != null && gauge5 != null)
		)
		{
			findGauges();
		}
		if (gauge1 != null && gauge2 != null && gauge3 != null && gauge4 != null && gauge5 != null)
		{
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
			Editor editor = prefs.edit();
			editor.putString("gauge1", gauge1.getName());
			editor.putString("gauge2", gauge2.getName());
			editor.putString("gauge3", gauge3.getName());
			editor.putString("gauge4", gauge4.getName());
			editor.putString("gauge5", gauge5.getName());
			editor.commit();
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		mHandler = new Handler();


		showSplashScreen();

		setContentView(R.layout.displaygauge);
		messages = (TextView) findViewById(R.id.messages);

		PreferenceManager.getDefaultSharedPreferences(MSLoggerActivity.this).registerOnSharedPreferenceChangeListener(
				MSLoggerActivity.this);
		ApplicationSettings.INSTANCE.setDefaultAdapter(BluetoothAdapter.getDefaultAdapter());
		GPSLocationManager.INSTANCE.start();
		ApplicationSettings.INSTANCE.setAutoConnectOverride(null);
		indicatorManager = IndicatorManager.INSTANCE;
		indicatorManager.setDisabled(true);

		if (!ready)
			new InitTask().execute((Void) null);

	}

	private class InitTask extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);
			initGauges();
			registerMessages();

			if (testBluetooth())
			{
				doBindService();
			}
			checkBTDeviceSet();
			ready = true;
			removeSplashScreen();

		}

		@Override
		protected Void doInBackground(Void... params)
		{

			String deviceId = Secure.getString(getContentResolver(), Secure.ANDROID_ID);

			mLicenseCheckerCallback = new MSLoggerCheckerCallback();
			mChecker = new LicenseChecker(MSLoggerActivity.this, new ServerManagedPolicy(MSLoggerActivity.this, new AESObfuscator(
					SALT, getPackageName(), deviceId)), BASE64_PUBLIC_KEY);

			doCheck();
			loadGauges();

			return null;
		}

	}

	private void showSplashScreen()
	{
		mSplashDialog = new Dialog(this, R.style.SplashScreen);
		mSplashDialog.setContentView(R.layout.splashscreen);
		mSplashDialog.setCancelable(false);
		mSplashDialog.show();

		// Set Runnable to remove splash screen just in case
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				removeSplashScreen();
			}
		}, 3000);

	}

	protected void removeSplashScreen()
	{
		if (mSplashDialog != null)
		{
			mSplashDialog.dismiss();
			mSplashDialog = null;
		}

	}

	private void doCheck()
	{
		setProgressBarIndeterminateVisibility(true);
		mChecker.checkAccess(mLicenseCheckerCallback);
	}

	private void displayResult(final String result)
	{
		mHandler.post(new Runnable()
		{
			public void run()
			{
				setProgressBarIndeterminateVisibility(false);
			}
		});
	}

	private class MarkListener implements OnTouchListener
	{
		private LinearLayout	layout;

		public MarkListener(LinearLayout layout)
		{
			this.layout = layout;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			if (service != null && service.isLogging() && event.getAction() == MotionEvent.ACTION_DOWN)
			{
				layout.setBackgroundColor(Color.BLUE);
				layout.invalidate();
				return true;
			}
			if (event.getAction() == MotionEvent.ACTION_UP)
			{
				layout.setBackgroundColor(Color.BLACK);
				layout.invalidate();
				DatalogManager.INSTANCE.mark("Manual");
				return true;
			}
			return false;
		}

	}

	private void loadGauges()
	{
		Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
		GaugeRegister.INSTANCE.flush();
		ecu.initGauges();
	}

	private void initGauges()
	{
		layout = (LinearLayout) (findViewById(R.id.layout));
		findGauges();
		Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
		String[] defaultGauges = ecu.defaultGauges();
		gauge1.initFromName(ApplicationSettings.INSTANCE.getOrSetPref("gauge1", defaultGauges[0]));
		gauge2.initFromName(ApplicationSettings.INSTANCE.getOrSetPref("gauge2", defaultGauges[1]));
		gauge3.initFromName(ApplicationSettings.INSTANCE.getOrSetPref("gauge3", defaultGauges[2]));
		gauge4.initFromName(ApplicationSettings.INSTANCE.getOrSetPref("gauge4", defaultGauges[3]));
		gauge5.initFromName(ApplicationSettings.INSTANCE.getOrSetPref("gauge5", defaultGauges[4]));

		if (gaugeEditEnabled)
		{
			gauge1.setOnTouchListener(new GaugeTouchListener(gauge1));
			gauge2.setOnTouchListener(new GaugeTouchListener(gauge2));
			gauge4.setOnTouchListener(new GaugeTouchListener(gauge4));
			gauge5.setOnTouchListener(new GaugeTouchListener(gauge5));

			gestureDetector = new GestureDetector(new RotationDetector(this, gauge3));
			OnTouchListener gestureListener = new View.OnTouchListener()
			{
				public boolean onTouch(View v, MotionEvent event)
				{
					if (gestureDetector.onTouchEvent(event))
					{
						return true;
					}

					if (event.getAction() == MotionEvent.ACTION_UP)
					{
						if (scrolling)
						{
							scrolling = false;
							GaugeDetails gd = gauge3.getDetails();
							GaugeRegister.INSTANCE.persistDetails(gd);
						}
					}

					return false;
				}
			};
			gauge3.setOnClickListener(MSLoggerActivity.this);
			gauge3.setOnTouchListener(gestureListener);
		}
		else
		{
			MarkListener l = new MarkListener(layout);
			setTouchListeners(l);
		}
		gauge1.invalidate();
		gauge2.invalidate();
		gauge3.invalidate();
		gauge4.invalidate();
		gauge5.invalidate();

	}

	private void findGauges()
	{
		gauge1 = (MSGauge) findViewById(R.id.g1);
		gauge2 = (MSGauge) findViewById(R.id.g2);
		gauge3 = (MSGauge) findViewById(R.id.g3);
		gauge4 = (MSGauge) findViewById(R.id.g4);
		gauge5 = (MSGauge) findViewById(R.id.g5);
	}

	private void setTouchListeners(MarkListener l)
	{
		gauge1.setOnTouchListener(l);
		gauge2.setOnTouchListener(l);
		gauge3.setOnTouchListener(l);
		gauge4.setOnTouchListener(l);
		gauge5.setOnTouchListener(l);
	}

	private boolean testBluetooth()
	{
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null)
		{
			return false;
		}
		boolean bluetoothOK = mBluetoothAdapter.isEnabled();
		if (!bluetoothOK)
		{
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			return false;
		}
		return true;
	}

	private void registerMessages()
	{
		IntentFilter connectedFilter = new IntentFilter(Megasquirt.CONNECTED);
		registerReceiver(updateReceiver, connectedFilter);
		IntentFilter disconnectedFilter = new IntentFilter(Megasquirt.DISCONNECTED);
		registerReceiver(updateReceiver, disconnectedFilter);
		IntentFilter connectionLostFilter = new IntentFilter(Megasquirt.CONNECTION_LOST);
		registerReceiver(updateReceiver, connectionLostFilter);
		IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
		registerReceiver(updateReceiver, dataFilter);
		IntentFilter msgFilter = new IntentFilter(ApplicationSettings.GENERAL_MESSAGE);
		registerReceiver(updateReceiver, msgFilter);
	}

	protected void processData()
	{
		List<Indicator> indicators;
		if ((indicators = indicatorManager.getIndicators()) != null)
		{
			indicatorManager.setDisabled(false);
			for (Indicator i : indicators)
			{
				String channelName = i.getChannel();
				if (channelName != null && service != null)
				{
					double value = service.getValue(channelName);
					i.setCurrentValue(value);
				}
				else
				{
					i.setCurrentValue(0);
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		MenuItem editItem = menu.findItem(R.id.gaugeEditing);
		if (gaugeEditEnabled)
		{
			editItem.setTitle(R.string.DisableGaugeEdit);
		}
		else
		{
			editItem.setTitle(R.string.EnableGaugeEdit);
		}
		MenuItem connectionItem = menu.findItem(R.id.forceConnection);
		if (ApplicationSettings.INSTANCE.getEcuDefinition().getCurrentState() != Megasquirt.ConnectionState.STATE_NONE)
		{
			connectionItem.setTitle(R.string.disconnect);
		}
		else
		{
			connectionItem.setTitle(R.string.connect);
		}

		MenuItem loggingItem = menu.findItem(R.id.forceLogging);
		if (ApplicationSettings.INSTANCE.shouldBeLogging())
		{
			loggingItem.setTitle(R.string.stop_logging);
		}
		else
		{
			loggingItem.setTitle(R.string.start_logging);
		}

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		checkBTDeviceSet();
		// Handle item selection
		switch (item.getItemId())
		{
		case R.id.preferences:
			openPreferences();
			return true;
		case R.id.calibrate:
			openCalibrateTPS();
			return true;
		case R.id.about:
			showAbout();
			return true;
		case R.id.gaugeEditing:
			toggleEditing();
			return true;
		case R.id.forceConnection:
			toggleConnection();
			return true;
		case R.id.quit:
			quit();
			return true;
		case R.id.forceLogging:
			toggleLogging();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void toggleEditing()
	{
		if (gaugeEditEnabled)
		{
			saveGauges();
		}
		gaugeEditEnabled = !gaugeEditEnabled;
		initGauges();
	}

	private void toggleLogging()
	{
		boolean shouldBeLogging = ApplicationSettings.INSTANCE.shouldBeLogging();
		if (shouldBeLogging)
		{
			service.stopLogging();
			sendLogs();
		}
		else
		{
			service.startLogging();
		}
		ApplicationSettings.INSTANCE.setLoggingOverride(!shouldBeLogging);
	}

	private void sendLogs()
	{
		if (ApplicationSettings.INSTANCE.emailEnabled())
		{
			DatalogManager.INSTANCE.close();
			FRDLogManager.INSTANCE.close();
			List<String> paths = new ArrayList<String>();
			paths.add(DatalogManager.INSTANCE.getAbsolutePath());
			paths.add(FRDLogManager.INSTANCE.getAbsolutePath());
			paths.add(DebugLogManager.INSTANCE.getAbsolutePath());
			String emailText = "Logfiles generated by MSLogger.\n\n\nhttps://bitbucket.org/scudderfish/mslogger";

			String subject = String.format("MSLogger files %tc", System.currentTimeMillis());
			EmailManager.email(this, ApplicationSettings.INSTANCE.getEmailDestination(), null, subject, emailText, paths);
		}

	}

	private void quit()
	{
		ApplicationSettings.INSTANCE.setAutoConnectOverride(false);
		ApplicationSettings.INSTANCE.getEcuDefinition().stop();
		resetConnection();
		sendLogs();
		this.finish();
	}

	private void toggleConnection()

	{
		if (ApplicationSettings.INSTANCE.getEcuDefinition().isRunning())
		{
			ApplicationSettings.INSTANCE.getEcuDefinition().stop();
			ApplicationSettings.INSTANCE.setAutoConnectOverride(false);
		}
		else
		{
			ApplicationSettings.INSTANCE.getEcuDefinition().start();
			ApplicationSettings.INSTANCE.setAutoConnectOverride(true);
		}
	}

	private void showAbout()
	{
		Dialog dialog = new Dialog(this);

		dialog.setContentView(R.layout.about);

		TextView text = (TextView) dialog.findViewById(R.id.text);
		String title = "";
		try
		{
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
			ApplicationInfo ai;
			ai = pInfo.applicationInfo;
			final String applicationName = (String) (ai != null ? getPackageManager().getApplicationLabel(ai) : "(unknown)");
			title = applicationName + " " + pInfo.versionName;
		}
		catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		dialog.setTitle(title);

		text.setText("An application to log information from Megasquirt ECUs.\n\nThanks to:\nPieter Corts\nMatthew Robson\nMartin Walton");
		ImageView image = (ImageView) dialog.findViewById(R.id.image);
		image.setImageResource(R.drawable.injector);

		dialog.show();
	}

	private void openCalibrateTPS()
	{
		Intent launchCalibrate = new Intent(this, CalibrateActivity.class);
		startActivity(launchCalibrate);
	}

	private void openPreferences()
	{
		Intent launchPrefs = new Intent(this, PreferencesActivity.class);
		startActivity(launchPrefs);
	}

	@Override
	protected void onDestroy()
	{
		GPSLocationManager.INSTANCE.stop();
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK)
		{
			doBindService();
		}
	}

	synchronized private void resetConnection()
	{
		DebugLogManager.INSTANCE.log("resetConnection()");

		connected = false;
		receivedData = false;
		service.stopLogging();

		indicatorManager.setDisabled(true);

		try
		{
			unbindService(mConnection);
			stopService(new Intent(MSLoggerActivity.this, MSLoggerService.class));
		}
		catch (Exception e)
		{

		}
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key)
	{
		if (!ready)
		{
			return;
		}
		if (key.startsWith("gauge"))
		{
			initGauges();
		}
		if (ApplicationSettings.INSTANCE.btDeviceSelected())
		{
			ConnectionState currentState = ApplicationSettings.INSTANCE.getEcuDefinition().getCurrentState();
			if (currentState == Megasquirt.ConnectionState.STATE_NONE)
			{
				if (service == null)
				{
					doBindService();
				}
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub

	}

	protected Dialog onCreateDialog(int id)
	{
		// We have only one dialog.
		return new AlertDialog.Builder(this).setTitle(R.string.unlicensed_dialog_title).setMessage(R.string.unlicensed_dialog_body)
				.setPositiveButton(R.string.buy_button, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://market.android.com/details?id="
								+ getPackageName()));
						startActivity(marketIntent);
					}
				}).setNegativeButton(R.string.quit_button, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						finish();
					}
				}).create();
	}
}
