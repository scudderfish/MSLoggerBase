package uk.org.smithfamily.mslogger.widgets;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;

/**
 *
 */
public abstract class Indicator extends View
{
    public enum Orientation
    {
        HORIZONTAL, VERTICAL
    }    
    
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
    private GestureDetector    gestureDetector;

    private GaugeDetails deadGauge = new GaugeDetails("Gauge","",DEAD_GAUGE_NAME, "deadValue",getValue(), "---", "", 0, 1, -1, -1, 2, 2, 0, 0, offsetAngle);
    
    /**
     * @return
     */
    public double getMin()
    {
        return min;
    }

    /**
     * @param min
     */
    public void setMin(double min)
    {
        this.min = min;
    }

    /**
     * @return
     */
    public double getMax()
    {
        return max;
    }

    /**
     * @param max
     */
    public void setMax(double max)
    {
        this.max = max;
    }

    /**
     * @return
     */
    public double getLowD()
    {
        return lowD;
    }
    
    /**
     * @param lowD
     */
    public void setLowD(double lowD)
    {
        this.lowD = lowD;
    }

    /**
     * @return
     */
    public double getLowW()
    {
        return lowW;
    }

    /**
     * @param lowW
     */
    public void setLowW(double lowW)
    {
        this.lowW = lowW;
    }

    /**
     * @return
     */
    public double getHiW()
    {
        return hiW;
    }

    /**
     * @param hiW
     */
    public void setHiW(double hiW)
    {
        this.hiW = hiW;
    }

    /**
     * @return
     */
    public double getHiD()
    {
        return hiD;
    }

    /**
     * @param hiD
     */
    public void setHiD(double hiD)
    {
        this.hiD = hiD;
    }

    /**
     * @return
     */
    public int getVd()
    {
        return vd;
    }

    /**
     * @param vd
     */
    public void setVd(int vd)
    {
        this.vd = vd;
    }

    /**
     * @return
     */
    public int getLd()
    {
        return ld;
    }

    /**
     * @param ld
     */
    public void setLd(int ld)
    {
        this.ld = ld;
    }

    /**
     * @return
     */
    public double getValue()
    {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(double value)
    {
        this.value = value;
        invalidate();
    }

    /**
     * @return
     */
    public static String getDeadGaugeName()
    {
        return DEAD_GAUGE_NAME;
    }

    /**
     * @return
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @return
     */
    public String getUnits()
    {
        return units;
    }

    /**
     * @param context
     */
    public Indicator(Context context)
    {
        super(context);
    }

    /**
     * @param c
     * @param s
     */
    public Indicator(Context c, AttributeSet s)
    {
        super(c, s);
    }

    /**
     * @param context
     * @param attr
     * @param defaultStyles
     */
    public Indicator(Context context, AttributeSet attr, int defaultStyles)
    {
        super(context, attr, defaultStyles);
    }

    /**
     * 
     * @param nme
     */
    public void initFromName(String nme)
    {
        GaugeDetails gd = GaugeRegister.INSTANCE.getGaugeDetails(nme);
        if (gd == null)
        {   
            if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("Can't find gauge : " + nme,Log.ERROR);
            gd = getDeadGauge();
        }
        initFromGD(gd);
    }    

    /**
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return
     */
    public String getChannel()
    {
        return channel;
    }

    /**
     * @param channel
     */
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    /**
     * @return
     */
    public boolean isDisabled()
    {
        return this.disabled;
    }

    /**
     * @param disabled
     */
    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    /**
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @param units
     */
    public void setUnits(String units)
    {
        this.units = units;
    }

    /**
     * @return
     */
    public GaugeDetails getDetails()
    {
        String orientation = "";
        if (getOrientation() == Orientation.HORIZONTAL)
        {
            orientation = "horizontal";
        }
        else 
        {
            orientation = "vertical";
        }
        
        GaugeDetails gd = new GaugeDetails(getType(),orientation,name, channel, value, title, units, min, max, lowD, lowW, hiW, hiD, vd, ld, offsetAngle);

        return gd;
    }

    /**
     * @param gd
     */
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
        value = min; // Default value is min
        
        String orientation = gd.getOrientation();
        if (orientation.toLowerCase().equals("horizontal"))
        {
            setOrientation(Orientation.HORIZONTAL);
        }
        else
        {
            setOrientation(Orientation.VERTICAL);
        }        
    }
    
    /**
     * @param
     */
    public void setOrientation(Orientation orientation){}
    
    /**
     * @return
     */
    public Orientation getOrientation()
    {
        return Orientation.VERTICAL;
    }
    
    /**
     * @return
     */
    public double getOffsetAngle()
    {
        return offsetAngle;
    }

    /**
     * @param offsetAngle
     */
    public void setOffsetAngle(double offsetAngle)
    {
        this.offsetAngle = offsetAngle;
    }

    /**
     * @return
     */
    public GaugeDetails getDeadGauge()
    {
        return deadGauge;
    }

    /**
     * @param deadGauge
     */
    public void setDeadGauge(GaugeDetails deadGauge)
    {
        this.deadGauge = deadGauge;
    }
    
    /**
     * @return
     */
    public GestureDetector getGestureDetector()
    {
        return gestureDetector;
    }

    /**
     * @param gestureDetector
     */
    public void setGestureDetector(GestureDetector gestureDetector)
    {
        this.gestureDetector = gestureDetector;
    }
    
    public abstract String getType();

    @Override
    public String toString()
    {
        return "Indicator [name=" + name + ", title=" + title + ", channel=" + channel + ", units=" + units + ", min=" + min + ", max=" + max + ", lowD=" + lowD + ", lowW=" + lowW + ", hiW=" + hiW + ", hiD=" + hiD + ", vd=" + vd + ", ld=" + ld
                + ", value=" + value + ", disabled=" + disabled + ", offsetAngle=" + offsetAngle + "]";
    }
}
