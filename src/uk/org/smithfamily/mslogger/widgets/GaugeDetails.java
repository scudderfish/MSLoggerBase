package uk.org.smithfamily.mslogger.widgets;

import java.io.Serializable;

import uk.org.smithfamily.mslogger.ApplicationSettings;

public class GaugeDetails implements Serializable
{
    private static final long serialVersionUID = 5603843897470844381L;
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
    private int    vd;
    private int    ld;

    public GaugeDetails(String name, String channel, String title, String units, double min, double max, double loD, double loW,
            double hiW, double hiD, int vd, int ld)
    {
        this.name = name;
        this.channel = channel;
        this.title = title;
        this.units = units;
        this.min = ApplicationSettings.INSTANCE.getGaugeSetting(name,title,"min",min);
        this.max = ApplicationSettings.INSTANCE.getGaugeSetting(name,title,"max",max);
        this.loD = ApplicationSettings.INSTANCE.getGaugeSetting(name,title,"loD",loD);
        this.loW = ApplicationSettings.INSTANCE.getGaugeSetting(name,title,"loW",loW);
        this.hiW =ApplicationSettings.INSTANCE.getGaugeSetting(name,title, "hiW",hiW);
        this.hiD = ApplicationSettings.INSTANCE.getGaugeSetting(name,title,"hiD",hiD);
        this.vd = (int) ApplicationSettings.INSTANCE.getGaugeSetting(name,title,"vd",vd);
        this.ld = (int) ApplicationSettings.INSTANCE.getGaugeSetting(name,title,"ld",ld);
    }

    public String getName()
    {
        return name;
    }

    public String getChannel()
    {
        return channel;
    }

    public String getTitle()
    {
        return title;
    }

    public String getUnits()
    {
        return units;
    }

    public double getMin()
    {
        return min;
    }

    public double getMax()
    {
        return max;
    }

    public double getLoD()
    {
        return loD;
    }

    public double getLoW()
    {
        return loW;
    }

    public double getHiW()
    {
        return hiW;
    }

    public double getHiD()
    {
        return hiD;
    }

    public int getVd()
    {
        return vd;
    }

    public int getLd()
    {
        return ld;
    }

	public void setName(String name)
	{
		this.name = name;
	}

	public void setChannel(String channel)
	{
		this.channel = channel;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setUnits(String units)
	{
		this.units = units;
	}

	public void setMin(double min)
	{
		this.min = min;
	}

	public void setMax(double max)
	{
		this.max = max;
	}

	public void setLoD(double loD)
	{
		this.loD = loD;
	}

	public void setLoW(double loW)
	{
		this.loW = loW;
	}

	public void setHiW(double hiW)
	{
		this.hiW = hiW;
	}

	public void setHiD(double hiD)
	{
		this.hiD = hiD;
	}

	public void setVd(int vd)
	{
		this.vd = vd;
	}

	public void setLd(int ld)
	{
		this.ld = ld;
	}

	@Override
	public String toString()
	{
		return "GaugeDetails [name=" + name + ", channel=" + channel + ", title=" + title + ", units=" + units + ", min=" + min
				+ ", max=" + max + ", loD=" + loD + ", loW=" + loW + ", hiW=" + hiW + ", hiD=" + hiD + ", vd=" + vd + ", ld=" + ld
				+ "]";
	}

}
