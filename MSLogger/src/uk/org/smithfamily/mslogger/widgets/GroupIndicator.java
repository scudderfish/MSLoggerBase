package uk.org.smithfamily.mslogger.widgets;

import java.util.LinkedHashMap;

import android.content.Context;
import android.util.AttributeSet;

public abstract class GroupIndicator extends Indicator
{
    private LinkedHashMap<String, Double> gaugeNameValuePairs = new LinkedHashMap<String, Double>();

    /**
     * @param context
     */
    public GroupIndicator(Context context)
    {
        super(context);

        // TODO decide how to resolve the default possible options here!?
        gaugeNameValuePairs.put("tachometer", 0.0);
        gaugeNameValuePairs.put("throttleGauge", 0.0);
        gaugeNameValuePairs.put("voltMeter", 0.0);
        gaugeNameValuePairs.put("cltGauge", 0.0);
        gaugeNameValuePairs.put("mapGauge", 0.0);
        gaugeNameValuePairs.put("advdegGauge", 0.0);
        gaugeNameValuePairs.put("looptimeGauge", 0.0);
        gaugeNameValuePairs.put("matGauge", 0.0);
        gaugeNameValuePairs.put("afr1Gauge", 0.0);
        gaugeNameValuePairs.put("veGauge1", 0.0);
    }

    /**
     * @param c
     * @param s
     */
    public GroupIndicator(Context c, AttributeSet s)
    {
        super(c, s);
    }

    /**
     * @param context
     * @param attr
     * @param defaultStyles
     */
    public GroupIndicator(Context context, AttributeSet attr, int defaultStyles)
    {
        super(context, attr, defaultStyles);
    }

    public Iterable<String> getGaugeNames()
    {
        return gaugeNameValuePairs.keySet();
    }

    public void setValue(double value, String gaugeName)
    {
        gaugeNameValuePairs.put(gaugeName, value);
        invalidate();
    }

    public double getValue(String gaugeName)
    {
        Double value = gaugeNameValuePairs.get(gaugeName);
        if (value != null)
        {
            return value.doubleValue();
        }
        return 666;
    }

    public int numOfGauges()
    {
        return gaugeNameValuePairs.size();
    }
}
