package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import android.os.Bundle;

public class EGOSelectActivity extends DialogPreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState,R.xml.ego_preferences);
    }

}
