package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

public class PreferencesActivity extends PreferenceActivity
{
    public  static final String DIRTY = "uk.org.smithfamily.mslogger.activity.PreferencesActivity.DIRTY";
    private Boolean ecuDirty = false;

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
        ecuDirty = false;
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference p = this.getPreferenceManager().findPreference("temptype");
        p.setOnPreferenceChangeListener(new ECUPreferenceChangeListener());
        p = this.getPreferenceManager().findPreference("maptype");
        p.setOnPreferenceChangeListener(new ECUPreferenceChangeListener());
        p = this.getPreferenceManager().findPreference("egotype");
        p.setOnPreferenceChangeListener(new ECUPreferenceChangeListener());
    }

    @Override
    public void finish()
    {
        Intent intent = new Intent();
        intent.putExtra(DIRTY, ecuDirty);

        // Set result and finish this Activity
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }

    protected void resetECU()
    {
        ApplicationSettings.INSTANCE.refreshFlags();
        ecuDirty = true;
    }
}
