package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import android.os.Bundle;

/**
 * Prompt the user to get his email to send datalogs
 */
public class EmailSelectActivity extends DialogPreferenceActivity
{
    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState,R.xml.email_preferences);
    }

}
