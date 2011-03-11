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
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        super.onCreate(savedInstanceState);
        //Debug.startMethodTracing("Repo");
        Repository.getInstance().readInit(this);
        //Debug.stopMethodTracing();
        setContentView(R.layout.main);
        
        Button prefBtn = (Button) findViewById(R.id.Button01);
        prefBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                showPreferences();
            }
        });

        Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                showConnection();
            }
        });
    }

    private void showPreferences()
    {
        Intent settingsActivity = new Intent(this, PreferencesActivity.class);
        startActivity(settingsActivity);

    }

    private void showConnection()
    {
        Intent connectActivity = new Intent(this, ConnectActivity.class);
        startActivity(connectActivity);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Toast.makeText(this, "Just a test", Toast.LENGTH_SHORT).show();
        return true;
    }

    void setFont(String path, int res)
    {
        TextView name = (TextView) findViewById(res);
        Typeface font = Typeface.createFromAsset(this.getAssets(), path);

        name.setTypeface(font);

    }
}
