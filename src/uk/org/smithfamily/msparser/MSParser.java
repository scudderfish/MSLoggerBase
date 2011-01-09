package uk.org.smithfamily.msparser;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MSParser extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setFont("fonts/ziska.ttf",R.id.text);
    }

    void setFont(String path, int res)
    {
        TextView name = (TextView) findViewById(res);
        Typeface font = Typeface.createFromAsset(this.getAssets(), path);

        name.setTypeface(font);

    }
}