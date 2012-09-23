package uk.org.smithfamily.mslogger.widgets;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;



/**
 *
 */
public class GaugeDetails implements Externalizable
{
    private static final long serialVersionUID = 5603843897470844381L;
    private String            type;
    private String            orientation;
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
        type = oi.readUTF();
        orientation = oi.readUTF();
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
        oo.writeUTF(type);
        oo.writeUTF(orientation);
    }
    
    /**
     * 
     */
    public GaugeDetails()
    {
        
    }
    
    /**
     * 
     * @param name      The name of the gauge
     * @param channel   The channel of the gauge
     * @param iniCheck  unused
     * @param title     The main title of the gauge
     * @param units     The units of the gauge
     * @param min       The minimum value that will be dispalyed
     * @param max       The maximum value that will be dispalyed
     * @param loD
     * @param loW
     * @param hiW
     * @param hiD
     * @param vd
     * @param ld
     * @param offsetAngle   The offset angle of the scale on the gauge
     */
    public GaugeDetails(String type, String orientation, String name, String channel, double iniCheck, String title, String units, double min, double max, 
            double loD, double loW, double hiW, double hiD, int vd, int ld, double offsetAngle)
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
    public void setType(String type)
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
    public void setOrientation(String orientation)
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
        return "GaugeDetails [type=" + type + ", orientation=" + orientation + ", name=" + name + ", channel=" + channel + ", title=" + title + ", units="
                + units + ", min=" + min + ", max=" + max + ", loD=" + loD + ", loW=" + loW + ", hiW=" + hiW + ", hiD=" + hiD + ", vd=" + vd + ", ld=" + ld
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
