package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import android.os.Bundle;

/**
 * Activity used to know about the user's MAP sensor on his Megasquirt 
 */
public class MAPSelectActivity extends DialogPreferenceActivity
{

    /**
     * @param savedInstanceState
     */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState, R.xml.map_preferences);
	}
}
