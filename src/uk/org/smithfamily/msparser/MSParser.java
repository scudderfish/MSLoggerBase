package uk.org.smithfamily.msparser;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

public class MSParser extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // super.onCreate(savedInstanceState);
        // Repository.getInstance().readInit(this);
        // setContentView(R.layout.main);
        // setFont("fonts/ziska.ttf", R.id.text);
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
