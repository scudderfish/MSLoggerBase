package uk.org.smithfamily.mslogger.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */
public abstract class Indicator extends View
{
    public static final String DEAD_GAUGE_NAME = "deadGauge";
    private String             name            = DEAD_GAUGE_NAME;
    private String             title           = "RPM";
    private String             channel         = "rpm";
    private String             units           = "";
    private double             min             = 0;
    private double             max             = 7000;
    private double             lowD            = 0;
    private double             lowW            = 0;
    private double             hiW             = 5000;
    private double             hiD             = 7000;
    private int                vd              = 0;
    private int                ld              = 0;
    private double             value           = 2500;
    private boolean            disabled        = false;
    private double             offsetAngle     = 45;

    private GaugeDetails deadGauge = new GaugeDetails(DEAD_GAUGE_NAME, "deadValue",getValue(), "---", "", 0, 1, -1, -1, 2, 2, 0, 0, offsetAngle);
    public double getMin()
    {
        return min;
    }

    public void setMin(double min)
    {
        this.min = min;
    }

    public double getMax()
    {
        return max;
    }

    public void setMax(double max)
    {
        this.max = max;
    }

    public double getLowD()
    {
        return lowD;
    }

    public void setLowD(double lowD)
    {
        this.lowD = lowD;
    }

    public double getLowW()
    {
        return lowW;
    }

    public void setLowW(double lowW)
    {
        this.lowW = lowW;
    }

    public double getHiW()
    {
        return hiW;
    }

    public void setHiW(double hiW)
    {
        this.hiW = hiW;
    }

    public double getHiD()
    {
        return hiD;
    }

    public void setHiD(double hiD)
    {
        this.hiD = hiD;
    }

    public int getVd()
    {
        return vd;
    }

    public void setVd(int vd)
    {
        this.vd = vd;
    }

    public int getLd()
    {
        return ld;
    }

    public void setLd(int ld)
    {
        this.ld = ld;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public static String getDeadGaugeName()
    {
        return DEAD_GAUGE_NAME;
    }

    public String getTitle()
    {
        return title;
    }

    public String getUnits()
    {
        return units;
    }

    public Indicator(Context context)
    {
        super(context);
    }

    public Indicator(Context c, AttributeSet s)
    {
        super(c, s);
    }

    public Indicator(Context context, AttributeSet attr, int defaultStyles)
    {
        super(context, attr, defaultStyles);
    }

    public void initFromName(String orSetPref)
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setUnits(String units)
    {
        this.units = units;
    }

    public GaugeDetails getDetails()
    {
        GaugeDetails gd = new GaugeDetails(name, channel, value, title, units, min, max, lowD, lowW, hiW, hiD, vd, ld, offsetAngle);

        return gd;
    }

    public void initFromGD(GaugeDetails gd)
    {
        name = gd.getName();
        title = gd.getTitle();
        channel = gd.getChannel();
        units = gd.getUnits();
        min = gd.getMin();
        max = gd.getMax();
        lowD = gd.getLoD();

        lowW = gd.getLoW();
        hiW = gd.getHiW();

        hiD = gd.getHiD();
        vd = gd.getVd();
        ld = gd.getLd();
        offsetAngle = gd.getOffsetAngle();
        value = (max - min) / 2.0;
    }

    public double getOffsetAngle()
    {
        return offsetAngle;
    }

    public void setOffsetAngle(double offsetAngle)
    {
        this.offsetAngle = offsetAngle;
    }

    public GaugeDetails getDeadGauge()
    {
        return deadGauge;
    }

    public void setDeadGauge(GaugeDetails deadGauge)
    {
        this.deadGauge = deadGauge;
    }

}
