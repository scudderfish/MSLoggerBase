package uk.org.smithfamily.msparser;

import uk.org.smithfamily.msdisp.parser.Repository;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MSParserActivity extends Activity
{
    private boolean logging = false;
    private Button  logButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        super.onCreate(savedInstanceState);
        // Debug.startMethodTracing("Repo");
        Repository.getInstance().readInit(this);
        // Debug.stopMethodTracing();
        setContentView(R.layout.main);

        Button logButton = (Button) findViewById(R.id.LoggingBtn);
        if (logButton != null)
        {
            logButton.setOnClickListener(new OnClickListener()
            {
                public void onClick(View v)
                {
                    toggleLogging();
                }

            });
        }
    }

    private void toggleLogging()
    {
        logging = !logging;

        if (!logging)
        {
            logButton.setText(R.string.start_logging);
        }

        else
        {
            logButton.setText(R.string.stop_logging);
        }
    }

}
