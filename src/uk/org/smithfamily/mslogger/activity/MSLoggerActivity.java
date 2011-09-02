package uk.org.smithfamily.mslogger.activity;

import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.service.MSLoggerService;
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
	private MSLoggerService		service;
	private static final int	REQUEST_ENABLE_BT	= 0;
	private BroadcastReceiver	updateReceiver		= new Reciever();
	private IndicatorManager	indicatorManager;
	private ToggleButton		button;

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

	private final class LogButtonListener implements OnClickListener
	{
		private final ToggleButton	button;

		private LogButtonListener(ToggleButton button)
		{
			this.button = button;
		}

		@Override
		public void onClick(View arg0)
		{
			if (button.isChecked())
			{
				startService(new Intent(MSLoggerActivity.this, MSLoggerService.class));
				doBindService();
			}
			else
			{
				unbindService( mConnection);

				stopService(new Intent(MSLoggerActivity.this, MSLoggerService.class));
			}
		}
	}

	private final class Reciever extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			if (intent.getAction().equals(Megasquirt.CONNECTED))
			{
				indicatorManager.setDisabled(false);
			}

			if (intent.getAction().equals(Megasquirt.NEW_DATA))
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
	}

	private ServiceConnection	mConnection	= new MSServiceConnection();

	void doBindService()
	{
		bindService(new Intent(this, MSLoggerService.class), mConnection, Context.BIND_AUTO_CREATE);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ApplicationSettings.INSTANCE.initialise(this);
		indicatorManager = IndicatorManager.INSTANCE;

		setContentView(R.layout.display);
		indicatorManager.setDisabled(true);
		button = (ToggleButton) findViewById(R.id.toggleButton);
		button.setEnabled(MSLoggerService.isCreated());
		button.setOnClickListener(new LogButtonListener(button));

		IntentFilter connectedFilter = new IntentFilter(Megasquirt.CONNECTED);
		registerReceiver(updateReceiver, connectedFilter);
		IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
		registerReceiver(updateReceiver, dataFilter);
		IntentFilter msgFilter = new IntentFilter(ApplicationSettings.GENERAL_MESSAGE);
		registerReceiver(updateReceiver, msgFilter);

		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null)
		{
			return;
		}
		boolean bluetoothOK = mBluetoothAdapter.isEnabled();
		if (!bluetoothOK)
		{
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
		else
		{
			button.setEnabled(true);
		}
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
				if (channelName != null)
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
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
		case R.id.preferences:
			openPreferences();
			return true;
		case R.id.calibrate:
			openCalibrateTPS();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openCalibrateTPS()
	{
		Intent launchCalibrate = new Intent(this,CalibrateActivity.class);
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
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK)
		{
			if (button != null)
			{
				button.setEnabled(true);
			}
		}
	}

}
