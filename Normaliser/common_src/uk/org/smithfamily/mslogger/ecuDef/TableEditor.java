package uk.org.smithfamily.mslogger.ecuDef;

import java.util.Arrays;

public class TableEditor
{

    private int        page;
    private String     label;
    private String     map3DName;
    private String     name;
    private int[]   xBins;
    private String     xOutputChannel;
    private boolean    xReadOnly;
    private int[]   yBins;
    private String     yOutputChannel;
    private boolean    yReadOnly;
    private String     zBins;
    private double     height;
    private String     upLabel;
    private String     downLabel;
	private int        xOrient;
	private int        yOrient;
	private int        zOrient;

    public TableEditor(String name, String map3DName, String label, int page)
    {
        this.name = name;
        this.map3DName = map3DName;
        this.label = label;
        this.page = page;
    }

    public int getPage()
    {
        return page;
    }

    public String getLabel()
    {
        return label;
    }

    public String getMap3DName()
    {
        return map3DName;
    }

    public String getName()
    {
        return name;
    }

    public void setXBins(int[] bins, String outputChannel, boolean b)
    {
        this.xBins = bins;
        this.xOutputChannel = outputChannel;
        this.xReadOnly = b;
    }

    public void setYBins(int[] bins, String outputChannel, boolean b)
    {
        this.yBins = bins;
        this.yOutputChannel = outputChannel;
        this.yReadOnly = b;
    }

    public void setZBins(String binsName)
    {
        this.zBins = binsName;

    }

    public void setHeight(double d)
    {
        this.height = d;
    }

    public void setUpDownLabel(String up, String down)
    {
        this.upLabel = up;
        this.downLabel = down;
    }

    public int[] getxBins()
    {
        return xBins;
    }

    public String getxOutputChannel()
    {
        return xOutputChannel;
    }

    public boolean isxReadOnly()
    {
        return xReadOnly;
    }

    public int[] getyBins()
    {
        return yBins;
    }

    public String getyOutputChannel()
    {
        return yOutputChannel;
    }

    public boolean isyReadOnly()
    {
        return yReadOnly;
    }

    public String getzBins()
    {
        return zBins;
    }

    public double getHeight()
    {
        return height;
    }

    public String getUpLabel()
    {
        return upLabel;
    }

    public String getDownLabel()
    {
        return downLabel;
    }

	public void setGridOrient(int xOrient, int yOrient, int zOrient)
	{
		this.xOrient = xOrient;
		this.yOrient = yOrient;
		this.zOrient = zOrient;
	}

	public int getxOrient()
	{
		return xOrient;
	}

	public int getyOrient()
	{
		return yOrient;
	}

	public int getzOrient()
	{
		return zOrient;
	}

    @Override
    public String toString()
    {
        return "TableEditor [page=" + page + ", label=" + label + ", map3DName=" + map3DName + ", name=" + name + ", xBins=" + Arrays.toString(xBins) + ", xOutputChannel=" + xOutputChannel + ", xReadOnly=" + xReadOnly + ", yBins=" + Arrays.toString(yBins)
                + ", yOutputChannel=" + yOutputChannel + ", yReadOnly=" + yReadOnly + ", zBins=" + zBins + ", height=" + height + ", upLabel=" + upLabel + ", downLabel=" + downLabel + ", xOrient=" + xOrient + ", yOrient=" + yOrient + ", zOrient="
                + zOrient + "]";
    }

}
