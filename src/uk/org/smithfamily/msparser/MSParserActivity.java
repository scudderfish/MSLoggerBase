package uk.org.smithfamily.msparser;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import uk.org.smithfamily.msdisp.parser.Repository;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MSParserActivity extends Activity
{
    private boolean  logging         = false;
    private Button   logButton;
    private Handler  mHandler        = new Handler();

    private Runnable mUpdateTimeTask = new Runnable()
                                     {
                                         public void run()
                                         {
                                             boolean _connected = MsDatabase.getInstance().getRuntime();
                                             showConnection(_connected);
                                             mHandler.postDelayed(this, 100);
                                         }
                                     };

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

        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);

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

    private void showConnection(boolean connected)
    {

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
