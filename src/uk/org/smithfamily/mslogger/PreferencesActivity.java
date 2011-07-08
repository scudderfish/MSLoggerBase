package uk.org.smithfamily.mslogger;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import uk.org.smithfamily.mslogger.R;

public class PreferencesActivity extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
