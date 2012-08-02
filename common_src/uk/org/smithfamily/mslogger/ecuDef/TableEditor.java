package uk.org.smithfamily.mslogger.ecuDef;

public class TableEditor
{

    private int    page;
    private String label;
    private String map3DName;
    private String name;

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

    public void setXBins(double[] rpmBins1, int rpm, boolean b)
    {
        // TODO Auto-generated method stub
        
    }

    public void setYBins(double[] mapBins1, double map, boolean b)
    {
        // TODO Auto-generated method stub
        
    }

    public void setZBins(double[][] veBins1)
    {
        // TODO Auto-generated method stub
        
    }

    public void setHeight(double d)
    {
        // TODO Auto-generated method stub
        
    }

    public void setUpDownLabel(String string, String string2)
    {
        // TODO Auto-generated method stub
        
    }

}
