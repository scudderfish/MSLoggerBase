package uk.org.smithfamily.utils.normaliser.curveeditor;

public class CurveGauge extends CurveItem
{
    private String gauge;

    public CurveGauge(String gauge)
    {
        this.gauge = gauge;
    }
    
    public String getGauge()
    {
        return gauge;
    }

    public void setGauge(String gauge)
    {
        this.gauge = gauge;
    }
    
    public String toString()
    {
        return String.format("c.setGauge(\"%s\");",gauge);
    }
}
