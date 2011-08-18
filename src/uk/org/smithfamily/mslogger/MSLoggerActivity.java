package uk.org.smithfamily.mslogger;

import java.util.List;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.IndicatorManager;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MSLoggerActivity extends Activity
{
	private static final int	REQUEST_ENABLE_BT	= 0;
	private BroadcastReceiver	updateReceiver		= new Reciever();
	private IndicatorManager	indicatorManager;
	private boolean				bluetoothOK			= false;
	private Megasquirt			ecuDefinition;

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
				ApplicationSettings.INSTANCE.getEcuDefinition().startLogging();
			}
			else
			{
				ApplicationSettings.INSTANCE.getEcuDefinition().stopLogging();
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
				final ToggleButton button = (ToggleButton) findViewById(R.id.toggleButton);
				button.setChecked(false);
				button.setOnClickListener(new LogButtonListener(button));
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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		ApplicationSettings.INSTANCE.initialise(this);
		indicatorManager = IndicatorManager.INSTANCE;
		ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		indicatorManager.setDisabled(true);

		super.onCreate(savedInstanceState);

		IntentFilter connectedFilter = new IntentFilter(Megasquirt.CONNECTED);
		registerReceiver(updateReceiver, connectedFilter);
		IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
		registerReceiver(updateReceiver, dataFilter);
		IntentFilter msgFilter = new IntentFilter(ApplicationSettings.GENERAL_MESSAGE);
		registerReceiver(updateReceiver, msgFilter);

		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null)
		{
			bluetoothOK = false;
			return;
		}
		bluetoothOK = mBluetoothAdapter.isEnabled();
		if (!bluetoothOK)
		{
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
		else
		{
			ecuDefinition.start();
		}
	}

	protected void processData()
	{
		List<Indicator> indicators;
		if ((indicators = indicatorManager.getIndicators()) != null)
		{
			for (Indicator i : indicators)
			{
				String channelName = i.getChannel();
				if (channelName != null)
				{
					double value = ecuDefinition.getValue(channelName);
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
		ecuDefinition.stop();
		super.onDestroy();
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
				ecuDefinition.start();
			}
		}
	}

}
