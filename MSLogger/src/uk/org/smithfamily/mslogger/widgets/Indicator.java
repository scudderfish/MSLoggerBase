package uk.org.smithfamily.mslogger.widgets;

import java.util.Observable;
import java.util.Observer;

import uk.org.smithfamily.mslogger.DataManager;
import uk.org.smithfamily.mslogger.utils.Copyable;
import uk.org.smithfamily.mslogger.widgets.renderers.BarGraph;
import uk.org.smithfamily.mslogger.widgets.renderers.Gauge;
import uk.org.smithfamily.mslogger.widgets.renderers.NumericIndicator;
import uk.org.smithfamily.mslogger.widgets.renderers.Renderer;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */
public class Indicator extends View implements Observer, Copyable<Indicator>
{
    public enum Orientation
    {
        HORIZONTAL, VERTICAL
    }

    public enum DisplayType
    {
        NUMERIC, BAR, GAUGE
    }

    private String title = "RPM";
    private String channel = "rpm";
    private String units = "";
    private double min = 0;
    private double max = 7000;
    private double lowD = 0;
    private double lowW = 0;
    private double hiW = 5000;
    private double hiD = 7000;
    private int vd = 0;
    private int ld = 0;
    private double value = 2500;
    private boolean disabled = true;
    private double offsetAngle = 45;

    private Location location;
    private DisplayType type;
    private Renderer renderer;
    private double epsilon=1e-5;

    public Indicator(Context c)
    {
        this(c, null);
    }

    public Indicator(Context c, AttributeSet s)
    {
        this(c, s, 0);
    }

    public Indicator(Context context, AttributeSet attr, int defaultStyles)
    {
        super(context, attr, defaultStyles);
        setDisplayType(DisplayType.GAUGE);
        DataManager.getInstance().addObserver(this);
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        IndicatorManager.INSTANCE.registerIndicator(this);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();

        IndicatorManager.INSTANCE.deregisterIndicator(this);
    }

    @Override
    public void draw(Canvas canvas)
    {
        renderer.paint(canvas);
    }

    @Override
    public void update(Observable observable, Object data)
    {
        disabled = false;
        double newValue = DataManager.getInstance().getField(channel);
        this.setValue(newValue);
        invalidate();
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
        return this.disabled;
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

    public void setOrientation(Orientation orientation)
    {
    }

    public Orientation getOrientation()
    {
        return Orientation.VERTICAL;
    }

    public double getOffsetAngle()
    {
        return offsetAngle;
    }

    public void setOffsetAngle(double offsetAngle)
    {
        this.offsetAngle = offsetAngle;
    }

    public String getType()
    {
        return "Indicator";
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public void setDisplayType(DisplayType type)
    {
        this.type = type;
        switch (type)
        {
        case BAR:
            renderer = new BarGraph(this, getContext());
            break;

        case GAUGE:
            renderer = new Gauge(this, getContext());
            break;

        case NUMERIC:
            renderer = new NumericIndicator(this, getContext());
            break;

        }
    }

    public DisplayType getDisplayType()
    {
        return type;
    }

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
        if (Math.abs(value - this.value) > epsilon)
        {
            this.value = value;
            invalidate();
        }
        else
        {
            this.value = value;
            
        }
    }

    public String getTitle()
    {
        return title;
    }

    public String getUnits()
    {
        return units;
    }

    @Override
    public Indicator copy()
    {
        Indicator copy = createForCopy();
        copyTo(copy);
        return copy;
    }

    @Override
    public Indicator createForCopy()
    {
        return new Indicator(this.getContext());
    }

    @Override
    public void copyTo(Indicator dest)
    {
        dest.title = title;
        dest.channel = channel;
        dest.min = min;
        dest.max = max;
        dest.lowD = lowD;
        dest.lowW = lowW;
        dest.hiD = hiD;
        dest.hiW = hiW;
        dest.vd = vd;
        dest.ld = ld;
        dest.value = value;
        dest.disabled = disabled;
        dest.offsetAngle = offsetAngle;
        dest.location = location.copy();
        dest.type = type;
        dest.renderer = renderer;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        renderer.onSizeChanged(w, h, oldw, oldh);
    }
}
