package uk.org.smithfamily.mslogger.ecuDef;

public class GaugeDetails
{
    private String type;
    private String orientation;
    private String name;
    private String channel;
    private String title;
    private String units;
    private double min;
    private double max;
    private double loD;
    private double loW;
    private double hiW;
    private double hiD;
    private double offsetAngle;
    private int vd;
    private int ld;

    /**
     * 
     * @param name The name of the gauge
     * @param channel The channel of the gauge
     * @param iniCheck unused
     * @param title The main title of the gauge
     * @param units The units of the gauge
     * @param min The minimum value that will be dispalyed
     * @param max The maximum value that will be dispalyed
     * @param loD
     * @param loW
     * @param hiW
     * @param hiD
     * @param vd
     * @param ld
     * @param offsetAngle The offset angle of the scale on the gauge
     */
    public GaugeDetails(final String type, final String orientation, final String name, final String channel, final double iniCheck, final String title, final String units, final double min, final double max, final double loD, final double loW,
            final double hiW, final double hiD, final int vd, final int ld, final double offsetAngle)
    {
        this.type = type;
        this.orientation = orientation;
        this.name = name;
        this.channel = channel;
        this.title = title;
        this.units = units;
        this.min = min;
        this.max = max;
        this.loD = loD;
        this.loW = loW;
        this.hiW = hiW;
        this.hiD = hiD;
        this.vd = vd;
        this.ld = ld;
        this.offsetAngle = offsetAngle;

    }

    /**
     * @return
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param
     */
    public void setType(final String type)
    {
        this.type = type;
    }

    /**
     * @return
     */
    public String getOrientation()
    {
        return orientation;
    }

    /**
     * @param
     */
    public void setOrientation(final String orientation)
    {
        this.orientation = orientation;
    }

    /**
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return
     */
    public String getChannel()
    {
        return channel;
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
     * @return
     */
    public double getMin()
    {
        return min;
    }

    /**
     * @return
     */
    public double getMax()
    {
        return max;
    }

    /**
     * @return
     */
    public double getLoD()
    {
        return loD;
    }

    /**
     * @return
     */
    public double getLoW()
    {
        return loW;
    }

    /**
     * @return
     */
    public double getHiW()
    {
        return hiW;
    }

    /**
     * @return
     */
    public double getHiD()
    {
        return hiD;
    }

    /**
     * @return
     */
    public int getVd()
    {
        return vd;
    }

    /**
     * @return
     */
    public int getLd()
    {
        return ld;
    }

    /**
     * @param name
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @param channel
     */
    public void setChannel(final String channel)
    {
        this.channel = channel;
    }

    /**
     * @param title
     */
    public void setTitle(final String title)
    {
        this.title = title;
    }

    /**
     * @param min
     */
    public void setUnits(final String units)
    {
        this.units = units;
    }

    /**
     * @param min
     */
    public void setMin(final double min)
    {
        this.min = min;
    }

    /**
     * @param max
     */
    public void setMax(final double max)
    {
        this.max = max;
    }

    /**
     * @param lowD
     */
    public void setLoD(final double loD)
    {
        this.loD = loD;
    }

    /**
     * @param loW
     */
    public void setLoW(final double loW)
    {
        this.loW = loW;
    }

    /**
     * @param hiW
     */
    public void setHiW(final double hiW)
    {
        this.hiW = hiW;
    }

    /**
     * @param hiD
     */
    public void setHiD(final double hiD)
    {
        this.hiD = hiD;
    }

    /**
     * @param vd
     */
    public void setVd(final int vd)
    {
        this.vd = vd;
    }

    /**
     * @param ld
     */
    public void setLd(final int ld)
    {
        this.ld = ld;
    }

    /**
     * @return
     */
    @Override
    public String toString()
    {
        return "GaugeDetails [type=" + type + ", orientation=" + orientation + ", name=" + name + ", channel=" + channel + ", title=" + title + ", units=" + units + ", min=" + min + ", max=" + max + ", loD=" + loD + ", loW=" + loW + ", hiW=" + hiW
                + ", hiD=" + hiD + ", vd=" + vd + ", ld=" + ld + "]";
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
    public void setOffsetAngle(final double offsetAngle)
    {
        this.offsetAngle = offsetAngle;
    }

}
