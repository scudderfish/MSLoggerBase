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
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MSLoggerActivity extends Activity
{
    private MSLoggerService   service;
    private static final int  REQUEST_ENABLE_BT = 0;
    private BroadcastReceiver updateReceiver    = new Reciever();
    private IndicatorManager  indicatorManager;
    private ToggleButton      connectButton;
    public boolean            connected;

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
        private ToggleButton button;

        private LogButtonListener(ToggleButton button)
        {
            this.button = button;
        }

        @Override
        public void onClick(View arg0)
        {
            if (service != null)
            {
                if (button.isChecked())
                {
                    service.startLogging();
                }
                else
                {
                    service.stopLogging();
                }
            }
        }

    }

    private final class ConnectButtonListener implements OnClickListener
    {
        private final ToggleButton button;

        private ConnectButtonListener(ToggleButton button)
        {
            this.button = button;
        }

        @Override
        public void onClick(View arg0)
        {
            logButton.setChecked(false);

            if (button.isChecked())
            {
                startService(new Intent(MSLoggerActivity.this, MSLoggerService.class));
                doBindService();
                connected = true;
            }
            else
            {
                connected = false;
                service.stopLogging();
                logButton.setChecked(false);
                logButton.setEnabled(false);
                unbindService(mConnection);

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
            if (intent.getAction().equals(Megasquirt.DISCONNECTED))
            {
                indicatorManager.setDisabled(true);
                logButton.setChecked(false);
                logButton.setEnabled(false);
                connectButton.setChecked(false);
                connectButton.setEnabled(true);
            }
            
            if (intent.getAction().equals(Megasquirt.NEW_DATA))
            {
                logButton.setEnabled(connected);
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

    private ServiceConnection mConnection = new MSServiceConnection();
    private ToggleButton      logButton;

    void doBindService()
    {
        bindService(new Intent(this, MSLoggerService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        indicatorManager = IndicatorManager.INSTANCE;

        setContentView(R.layout.display);
        indicatorManager.setDisabled(true);
        connectButton = (ToggleButton) findViewById(R.id.connectButton);
        connectButton.setEnabled(MSLoggerService.isCreated());
        connectButton.setOnClickListener(new ConnectButtonListener(connectButton));

        logButton = (ToggleButton) findViewById(R.id.logButton);
        logButton.setEnabled(MSLoggerService.isCreated());
        logButton.setOnClickListener(new LogButtonListener(logButton));

        IntentFilter connectedFilter = new IntentFilter(Megasquirt.CONNECTED);
        registerReceiver(updateReceiver, connectedFilter);
        IntentFilter disconnectedFilter = new IntentFilter(Megasquirt.DISCONNECTED);
        registerReceiver(updateReceiver, disconnectedFilter);
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
            connectButton.setEnabled(true);
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
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK)
        {
            if (connectButton != null)
            {
                connectButton.setEnabled(true);
            }
        }
    }

}
