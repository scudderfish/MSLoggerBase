package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CalibrateActivity extends Activity
{
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

	}

	private void saveValues()
	{
		// TODO Auto-generated method stub

	}

}
