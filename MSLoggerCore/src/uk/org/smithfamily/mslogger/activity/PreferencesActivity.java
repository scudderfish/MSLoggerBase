package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.os.Bundle;
import android.preference.*;
import android.preference.Preference.OnPreferenceChangeListener;

public class PreferencesActivity extends PreferenceActivity
{
    private class ECUPreferenceChangeListener implements OnPreferenceChangeListener
    {

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue)
        {
            
            resetECU();
            return true;
     
        }
        
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference p = this.getPreferenceManager().findPreference("temptype");
        p.setOnPreferenceChangeListener(new ECUPreferenceChangeListener());
        p = this.getPreferenceManager().findPreference("maptype");
        p.setOnPreferenceChangeListener(new ECUPreferenceChangeListener());
        p = this.getPreferenceManager().findPreference("egotype");
        p.setOnPreferenceChangeListener(new ECUPreferenceChangeListener());
    }

    protected void resetECU()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        ApplicationSettings.INSTANCE.refreshFlags();

    }
}
