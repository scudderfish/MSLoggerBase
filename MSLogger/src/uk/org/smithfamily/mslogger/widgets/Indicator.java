package uk.org.smithfamily.mslogger.widgets;

import java.util.Observable;
import java.util.Observer;

import uk.org.smithfamily.mslogger.DataManager;
import uk.org.smithfamily.mslogger.utils.Copyable;
import uk.org.smithfamily.mslogger.widgets.renderers.Renderer;

/**
 * This class is a bean that holds the information necessary to model an Indicator
 */
public class Indicator extends Observable implements Observer, Copyable<Indicator>
{
    public enum Orientation
    {
        HORIZONTAL, VERTICAL
    }

    public enum DisplayType
    {
        NUMERIC, BAR, GAUGE, HISTOGRAM
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
    private double value = 0;
    private boolean disabled = true;
    private double offsetAngle = 45;

    private Location location;
    private DisplayType type;
    private Renderer renderer;
    private int left;
    private int top;
    private int right;
    private Object bottom;

    public Indicator()
    {
        setDisplayType(DisplayType.GAUGE);
        DataManager.getInstance().addObserver(this);
    }

    @Override
    public void update(final Observable observable, final Object data)
    {
        disabled = false;
        final double newValue = DataManager.getInstance().getField(channel);
        this.setValue(newValue);
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(final String channel)
    {
        this.channel = channel;
        updateWatchers();
    }

    public boolean isDisabled()
    {
        return this.disabled;
    }

    public void setDisabled(final boolean disabled)
    {
        this.disabled = disabled;
        updateWatchers();
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public void setUnits(final String units)
    {
        this.units = units;
        updateWatchers();
    }

    public void setOrientation(final Orientation orientation)
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

    public void setOffsetAngle(final double offsetAngle)
    {
        this.offsetAngle = offsetAngle;
        updateWatchers();

    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(final Location location)
    {
        this.location = location;
        updateWatchers();
    }

    public void setDisplayType(final DisplayType type)
    {
        this.type = type;
        updateWatchers();
    }

    public DisplayType getDisplayType()
    {
        return type;
    }

    public double getMin()
    {
        return min;
    }

    public void setMin(final double min)
    {
        this.min = min;
        updateWatchers();
    }

    public double getMax()
    {
        return max;
    }

    public void setMax(final double max)
    {
        this.max = max;
        updateWatchers();
    }

    public double getLowD()
    {
        return lowD;
    }

    public void setLowD(final double lowD)
    {
        this.lowD = lowD;
        updateWatchers();
    }

    public double getLowW()
    {
        return lowW;
    }

    public void setLowW(final double lowW)
    {
        this.lowW = lowW;
        updateWatchers();
    }

    public double getHiW()
    {
        return hiW;
    }

    public void setHiW(final double hiW)
    {
        this.hiW = hiW;
        updateWatchers();
    }

    public double getHiD()
    {
        return hiD;
    }

    public void setHiD(final double hiD)
    {
        this.hiD = hiD;
        updateWatchers();
    }

    public int getVd()
    {
        return vd;
    }

    public void setVd(final int vd)
    {
        this.vd = vd;
        updateWatchers();
    }

    public int getLd()
    {
        return ld;
    }

    public void setLd(final int ld)
    {
        this.ld = ld;
        updateWatchers();
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(final double value)
    {
        this.value = value;
        updateWatchers();

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
        final Indicator copy = createForCopy();
        copyTo(copy);
        return copy;
    }

    @Override
    public Indicator createForCopy()
    {
        return new Indicator();
    }

    @Override
    public void copyTo(final Indicator dest)
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

    private void updateWatchers()
    {
        setChanged();
        notifyObservers();
    }

    public void setLayout(final int cleft, final int ctop, final int cright, final int cbottom)
    {
        this.left = cleft;
        this.top = ctop;
        this.right = cright;
        this.bottom = cbottom;
    }

    public synchronized Renderer getRenderer()
    {
        return renderer;
    }

    public synchronized void setRenderer(final Renderer renderer)
    {
        this.renderer = renderer;
    }

    public int getLeft()
    {
        return left;
    }

    public int getTop()
    {
        return top;
    }

    public int getRight()
    {
        return right;
    }

    public Object getBottom()
    {
        return bottom;
    }

    public void setType(final DisplayType type)
    {
        this.type = type;
    }
}
