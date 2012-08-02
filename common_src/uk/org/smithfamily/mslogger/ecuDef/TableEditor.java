package uk.org.smithfamily.mslogger.ecuDef;

public class TableEditor
{

    private int        page;
    private String     label;
    private String     map3DName;
    private String     name;
    private double[]   xBins;
    private String     xLabel;
    private boolean    xReadOnly;
    private double[]   yBins;
    private String     yLabel;
    private boolean    yReadOnly;
    private double[][] zBins;
    private double     height;
    private String     upLabel;
    private String     downLabel;

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

    public void setXBins(double[] bins, String label, boolean b)
    {
        this.xBins = bins;
        this.xLabel = label;
        this.xReadOnly = b;
    }

    public void setYBins(double[] bins, String label, boolean b)
    {
        this.yBins = bins;
        this.yLabel = label;
        this.yReadOnly = b;

    }

    public void setZBins(double[][] bins)
    {
        this.zBins = bins;

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

    public double[] getxBins()
    {
        return xBins;
    }

    public String getxLabel()
    {
        return xLabel;
    }

    public boolean isxReadOnly()
    {
        return xReadOnly;
    }

    public double[] getyBins()
    {
        return yBins;
    }

    public String getyLabel()
    {
        return yLabel;
    }

    public boolean isyReadOnly()
    {
        return yReadOnly;
    }

    public double[][] getzBins()
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

}
