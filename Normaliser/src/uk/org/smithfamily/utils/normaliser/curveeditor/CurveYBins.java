package uk.org.smithfamily.utils.normaliser.curveeditor;

public class CurveYBins extends CurveItem
{
    private String bins;

    public CurveYBins(String bins)
    {
        this.bins = bins;
    }

    public String getBins()
    {
        return bins;
    }

    public void setBins(String bins)
    {
        this.bins = bins;
    }
    
    @Override
    public String toString()
    {
        return String.format("c.setYBins(%s,\"%s\");", bins, bins);
    }
}
