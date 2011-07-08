package uk.org.smithfamily.msparser;

import java.util.List;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import uk.org.smithfamily.msdisp.parser.Symbol;
import uk.org.smithfamily.msparser.widgets.Indicator;
import uk.org.smithfamily.msparser.widgets.IndicatorManager;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MSParserActivity extends Activity
{

    protected MSControlService mBoundService;
    private boolean            mIsBound;
    private BroadcastReceiver  updateReceiver = new BroadcastReceiver()
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
                                                      if(intent.getAction().equals(MsDatabase.GENERAL_MESSAGE))
                                                      {
                                                          String msg = intent.getStringExtra(MsDatabase.MESSAGE);
                                                          TextView v = (TextView) findViewById(R.id.messages);
                                                          v.setText(msg);
                                                      }
                                                  }

                                              };
    private IndicatorManager   indicatorManager;

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
        IntentFilter msgFilter = new IntentFilter(MsDatabase.GENERAL_MESSAGE);
        registerReceiver(updateReceiver,msgFilter);
        

    }

    protected void processData()
    {
        if (mIsBound)
        {
            List<Symbol> syms = mBoundService.getCurrentData();
            indicatorManager = IndicatorManager.getInstance();
            for (Symbol sym : syms)
            {
                String symbolName = sym.name();
                List<Indicator> indicators;
                if ((indicators = indicatorManager.getIndicators(symbolName)) != null)
                {

                    float value = (float) sym.getValue();
                    for (Indicator i : indicators)
                    {
                        i.setCurrentValue(value);
                    }
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

}
