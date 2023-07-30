package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import android.os.Bundle;

@SuppressWarnings("deprecation")
public class EmailSelectActivity extends DialogPreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState, R.xml.email_preferences);
    }

}
