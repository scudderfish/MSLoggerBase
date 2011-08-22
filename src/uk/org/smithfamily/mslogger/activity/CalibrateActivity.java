package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CalibrateActivity extends Activity
{
	private int			minTPS	= 256;
	private int			maxTPS	= 0;
	private TextView	minValView;
	private TextView	maxValView;
	private ProgressBar	tpsDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calibrate);

		Button button = (Button) findViewById(R.id.ConfirmButton);
		button.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				saveValues();
				finish();
			}
		});
		minValView = (TextView) findViewById(R.id.MinValue);
		maxValView = (TextView) findViewById(R.id.MaxValue);
		tpsDisplay = (ProgressBar) findViewById(R.id.TPSPosition);

		IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
		registerReceiver(new BroadcastReceiver()
		{

			@Override
			public void onReceive(Context context, Intent intent)
			{
				int currentTPS = ApplicationSettings.INSTANCE.getEcuDefinition().getCurrentTPS();
				updateWithRaw(currentTPS);
			}
		}, dataFilter);

	}

	private void updateWithRaw(int raw)
	{
		if (raw < minTPS)
		{
			minTPS = raw;
		}
		if (raw > maxTPS)
		{
			maxTPS = raw;
		}
		minValView.setText(Integer.toString(minTPS));
		maxValView.setText(Integer.toString(maxTPS));
		tpsDisplay.setMax(maxTPS - minTPS);
		tpsDisplay.setProgress(raw - minTPS);
	}

	private void saveValues()
	{
		// TODO Auto-generated method stub

	}

}
