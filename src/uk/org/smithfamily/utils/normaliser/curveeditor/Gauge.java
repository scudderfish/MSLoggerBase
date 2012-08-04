package uk.org.smithfamily.utils.normaliser.curveeditor;

public class Gauge extends CurveItem
{
    private String gauge;

    public Gauge(String gauge)
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
