package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import android.os.Bundle;

public class MAPSelectActivity extends DialogPreferenceActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState, R.xml.map_preferences);
	}
}
