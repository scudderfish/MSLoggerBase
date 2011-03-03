package uk.org.smithfamily.msparser;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.msparser.widgets.Gauge;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashActivity extends Activity
{
    List<Gauge> gauges = new ArrayList<Gauge>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        Button minBut = (Button) findViewById(R.id.MinButton);
        Button maxBut = (Button) findViewById(R.id.MaxButtob);
        gauges.add((Gauge) findViewById(R.id.RPMMeter));
        gauges.add((Gauge) findViewById(R.id.MAPMeter));
        gauges.add((Gauge) findViewById(R.id.AFRMeter));

        minBut.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {

                for (Gauge g : gauges)
                {
                    g.setValue(g.getScaleMinValue());
                }
            }
        });
        maxBut.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {

                for (Gauge g : gauges)
                {
                    g.setValue(g.getScaleMaxValue());
                }

            }
        });

    }

}
