package uk.org.smithfamily.mslogger.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.TableManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;

@SuppressLint("DefaultLocale")
public class CalibrateActivity extends Activity
{
	private static final String	THROTTLEFACTOR_INC	= "throttlefactor.inc";

	private static final String	HEADER	=          
		"; MSLogger-generated Linear Throttle Calibration\n" +
        "; Written on %s\n" +
        ";\n" +
        ";   Low ADC = %d    High ADC = %d\n" +
        ";\n" +
        "THROTTLEFACTOR:\n" +
        "\t\t\t; ADC" ;

	private int			minTPS	= 256;
	private int			maxTPS	= 0;
	private TextView	minValView;
	private TextView	curValView;
	private TextView	maxValView;
	private ProgressBar	tpsDisplay;
	
    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            int currentTPS = ApplicationSettings.INSTANCE.getEcuDefinition().getCurrentTPS();
            updateWithRaw(currentTPS);
        }
    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calibrate);

		Button button = findViewById(R.id.ConfirmButton);
		button.setOnClickListener(v -> {
			saveValues();
			finish();
		});
		minValView = findViewById(R.id.MinValue);
		curValView = findViewById(R.id.CurrentValue);
		maxValView = findViewById(R.id.MaxValue);
		tpsDisplay = findViewById(R.id.TPSPosition);

		IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
		this.registerReceiver(mReceiver, dataFilter);
	}
	
	/**
	 * 
	 */
	@Override
	protected void onDestroy()
	{
	    super.onDestroy();
	    
	    this.unregisterReceiver(mReceiver);
	}

	@SuppressLint("SetTextI18n")
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
		curValView.setText(Integer.toString(raw));
		maxValView.setText(Integer.toString(maxTPS));
		tpsDisplay.setMax(Math.abs(maxTPS - minTPS));
		tpsDisplay.setProgress(Math.abs(raw - minTPS));
	}

	/**
	 * 
	 */
	private void saveValues()
	{
		File dataFile = new File(ApplicationSettings.INSTANCE.getDataDir(),THROTTLEFACTOR_INC);
		
		try
		{
			PrintWriter pw = new PrintWriter(new FileWriter(dataFile));
			String now = SimpleDateFormat.getDateTimeInstance().format(new Date());
			String header = String.format(HEADER,now,minTPS,maxTPS);
			
			pw.println(header);
			
			for (int x = 0; x < 256; x++)
			{
				pw.println(String.format("\tDB\t%3dT\t; %3d", getPercentage(x),x));
			}
			pw.close();
			TableManager.INSTANCE.flushTable(THROTTLEFACTOR_INC);
		}
		catch (IOException e)
		{
			DebugLogManager.INSTANCE.logException(e);
		}
	}

	private int getPercentage(int x)
	{
		if (x <= minTPS)
		{
			return 0;
		}
		if (x >= maxTPS)
		{
			return 100;
		}
		
		return (x - minTPS) * 100 / (maxTPS - minTPS);
	}
}
