package uk.org.smithfamily.utils.normaliser.tableeditor;

public class ZBins extends TableItem
{

	private String bins;

	public ZBins(String bins)
	{
		this.bins=bins;
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
        return String.format("t.setZBins(%s);", bins);
    }

}
