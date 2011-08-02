package uk.org.smithfamily.mslogger;

import java.util.List;

import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.IndicatorManager;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.os.Bundle;
import android.os.IBinder;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MSLoggerActivity extends Activity
{

	private static final int	REQUEST_ENABLE_BT	= 0;
	protected MSControlService	mBoundService;
	private boolean				mIsBound;
	private BroadcastReceiver	updateReceiver		= new BroadcastReceiver()
													{

														@Override
														public void onReceive(Context context, Intent intent)
														{
															if (intent.getAction().equals(MSControlService.CONNECTED))
															{
																setContentView(R.layout.display);
																final ToggleButton button = (ToggleButton) findViewById(R.id.toggleButton);
																button.setChecked(mBoundService.isLogging());
																button.setOnClickListener(new OnClickListener()
																{

																	@Override
																	public void onClick(View arg0)
																	{
																		if (button.isChecked())
																		{
																			mBoundService.startLogging();
																		}
																		else
																		{
																			mBoundService.stopLogging();
																		}
																	}
																});

															}
															if (intent.getAction().equals(MSControlService.NEW_DATA))
															{
																processData();
															}
															if (intent.getAction().equals(ApplicationSettings.GENERAL_MESSAGE))
															{
																String msg = intent.getStringExtra(ApplicationSettings.MESSAGE);
																TextView v = (TextView) findViewById(R.id.messages);
																v.setText(msg);
															}
														}

													};
	private IndicatorManager	indicatorManager;
	private boolean				bluetoothOK			= false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disconnected);

		super.onCreate(savedInstanceState);
		doBindService();

		IntentFilter connectedFilter = new IntentFilter(MSControlService.CONNECTED);
		registerReceiver(updateReceiver, connectedFilter);
		IntentFilter dataFilter = new IntentFilter(MSControlService.NEW_DATA);
		registerReceiver(updateReceiver, dataFilter);
		IntentFilter msgFilter = new IntentFilter(ApplicationSettings.GENERAL_MESSAGE);
		registerReceiver(updateReceiver, msgFilter);
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null)
		{
			// Device does not support Bluetooth
		}
		bluetoothOK = mBluetoothAdapter.isEnabled();
		if (!bluetoothOK)
		{
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}

	}

	protected void processData()
	{
		if (mIsBound)
		{
			indicatorManager = IndicatorManager.INSTANCE;
			List<Indicator> indicators;
			if ((indicators = indicatorManager.getIndicators()) != null)
			{
				for (Indicator i : indicators)
				{
					float value = ApplicationSettings.INSTANCE.getEcuDefinition().getValue(i.getChannel());
					i.setCurrentValue(value);
				}
			}
		}
	}

	void doBindService()
	{
		Intent intent = new Intent(this, MSControlService.class);
		startService(intent);
		ServiceConnection connection = new ServiceConnection()
		{

			@Override
			public void onServiceConnected(ComponentName name, IBinder service)
			{
				mBoundService = ((MSControlService.LocalBinder) service).getService();
				mIsBound = true;
			}

			@Override
			public void onServiceDisconnected(ComponentName name)
			{
				mBoundService = null;
				mIsBound = false;
			}
		};

		bindService(intent, connection, 0);
	}

	void doUnbindService()
	{

		mBoundService.stopLogging();
		mBoundService.stopSelf();
		mIsBound = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
		case R.id.preferences:
			openPreferences();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openPreferences()
	{
		Intent launchPrefs = new Intent(this, PreferencesActivity.class);
		startActivity(launchPrefs);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		doUnbindService();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_ENABLE_BT)
		{
			if (resultCode == RESULT_OK)
			{
				bluetoothOK = true;
			}
		}
	}

}
