package uk.org.smithfamily.mslogger.widgets;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import uk.org.smithfamily.mslogger.ApplicationSettings;

/**
 *
 */
public class GaugeDetails implements Externalizable
{
    private static final long serialVersionUID = 5603843897470844381L;
    private String            name;
    private String            channel;
    private String            title;
    private String            units;
    private double            min;
    private double            max;
    private double            loD;
    private double            loW;
    private double            hiW;
    private double            hiD;
    private double            offsetAngle;
    private int               vd;
    private int               ld;

    /**
     * 
     * @param oi
     */
    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        name = oi.readUTF();
        channel = oi.readUTF();
        title = oi.readUTF();
        units = oi.readUTF();
        min = oi.readDouble();
        max = oi.readDouble();
        loD = oi.readDouble();
        loW = oi.readDouble();
        hiW = oi.readDouble();
        hiD = oi.readDouble();
        offsetAngle = oi.readDouble();
        vd = oi.readInt();
        ld = oi.readInt();
    }

    /**
     * 
     * @param oo
     */
    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeUTF(name);
        oo.writeUTF(channel);
        oo.writeUTF(title);
        oo.writeUTF(units);
        oo.writeDouble(min);
        oo.writeDouble(max);
        oo.writeDouble(loD);
        oo.writeDouble(loW);
        oo.writeDouble(hiW);
        oo.writeDouble(hiD);
        oo.writeDouble(offsetAngle);
        oo.writeInt(vd);
        oo.writeInt(ld);
    }
    
    /**
     * 
     */
    public GaugeDetails()
    {
        
    }
    
    /**
     * 
     * @param name
     * @param channel
     * @param iniCheck
     * @param title
     * @param units
     * @param min
     * @param max
     * @param loD
     * @param loW
     * @param hiW
     * @param hiD
     * @param vd
     * @param ld
     * @param offsetAngle
     */
    public GaugeDetails(String name, String channel, double iniCheck, String title, String units, double min, double max, double loD, double loW,
            double hiW, double hiD, int vd, int ld, double offsetAngle)
    {
        this.name = name;
        this.channel = channel;
        this.title = title;
        this.units = units;
        this.min = ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "min", min);
        this.max = ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "max", max);
        this.loD = ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "loD", loD);
        this.loW = ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "loW", loW);
        this.hiW = ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "hiW", hiW);
        this.hiD = ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "hiD", hiD);
        this.vd = (int) ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "vd", vd);
        this.ld = (int) ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "ld", ld);
        this.offsetAngle = ApplicationSettings.INSTANCE.getGaugeSetting(name, title, "offsetAngle", offsetAngle);
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
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param channel
     */
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    /**
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @param min
     */
    public void setUnits(String units)
    {
        this.units = units;
    }

    /**
     * @param min
     */
    public void setMin(double min)
    {
        this.min = min;
    }

    /**
     * @param max
     */
    public void setMax(double max)
    {
        this.max = max;
    }

    /**
     * @param lowD
     */
    public void setLoD(double loD)
    {
        this.loD = loD;
    }

    /**
     * @param loW
     */
    public void setLoW(double loW)
    {
        this.loW = loW;
    }

    /**
     * @param hiW
     */
    public void setHiW(double hiW)
    {
        this.hiW = hiW;
    }

    /**
     * @param hiD
     */
    public void setHiD(double hiD)
    {
        this.hiD = hiD;
    }

    /**
     * @param vd
     */
    public void setVd(int vd)
    {
        this.vd = vd;
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
    @Override
    public String toString()
    {
        return "GaugeDetails [name=" + name + ", channel=" + channel + ", title=" + title + ", units=" + units + ", min=" + min
                + ", max=" + max + ", loD=" + loD + ", loW=" + loW + ", hiW=" + hiW + ", hiD=" + hiD + ", vd=" + vd + ", ld=" + ld
                + "]";
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

}
