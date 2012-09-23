package uk.org.smithfamily.utils.normaliser.curveeditor;

public class CurveColumnLabel extends CurveItem
{
    private String xLabel;
    private String yLabel;

    public CurveColumnLabel(String xLabel, String yLabel)
    {
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }
    
    public String getxLabel()
    {
        return xLabel;
    }

    public void setxLabel(String xLabel)
    {
        this.xLabel = xLabel;
    }

    public String getyLabel()
    {
        return yLabel;
    }

    public void setyLabel(String yLabel)
    {
        this.yLabel = yLabel;
    }
    
    public String toString()
    {
        return String.format("c.setxLabel(\"%s\");c.setyLabel(\"%s\");",xLabel,yLabel);
    }
}
