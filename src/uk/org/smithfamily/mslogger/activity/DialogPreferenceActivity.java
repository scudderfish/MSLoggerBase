package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class DialogPreferenceActivity extends PreferenceActivity
{

	protected void addButton()
	{
		LinearLayout v = new LinearLayout(this);
		Button b = new Button(this);
		b.setText(R.string.bt_ok);
		b.setGravity(Gravity.CENTER_HORIZONTAL);
		LayoutParams p = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		b.setLayoutParams(p);
		b.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		v.addView(b);
		this.getListView().addFooterView(v);
	}

	public void onCreate(Bundle savedInstanceState, int preferences)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(preferences);
		addButton();
	}

}
