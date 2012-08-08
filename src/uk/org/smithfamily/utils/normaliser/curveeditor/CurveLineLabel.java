package uk.org.smithfamily.utils.normaliser.curveeditor;

public class CurveLineLabel extends CurveItem
{
    private String lineLabel;

    public CurveLineLabel(String lineLabel)
    {
        this.lineLabel = lineLabel;
    }
    
    public String getLineLabel()
    {
        return lineLabel;
    }

    public void setLineLabel(String lineLabel)
    {
        this.lineLabel = lineLabel;
    }
    
    public String toString()
    {
        return String.format("c.addLineLabel(\"%s\");",lineLabel);
    }
}
