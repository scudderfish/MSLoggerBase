package uk.org.smithfamily.utils.normaliser.tableeditor;

public class XBins extends TableItem
{

	private String readOnly;
	private String bins2;
	private String bins1;

	public XBins(String bins1, String bins2, String readonly)
	{
		this.bins1=bins1;
		this.bins2=bins2;
		this.readOnly=readonly;
	}

	public String getReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(String readOnly)
	{
		this.readOnly = readOnly;
	}

	public String getBins2()
	{
		return bins2;
	}

	public void setBins2(String bins2)
	{
		this.bins2 = bins2;
	}

	public String getBins1()
	{
		return bins1;
	}

	public void setBins1(String bins1)
	{
		this.bins1 = bins1;
	}

}
