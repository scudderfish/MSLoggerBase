package uk.org.smithfamily.utils.normaliser.tableeditor;

public class XBins extends TableItem
{

	private String readOnly;
	private String label;
	private String bins1;

	public XBins(String bins1, String label, String readonly)
	{
		this.bins1 = bins1;
		this.label = label;
		this.readOnly = (readonly == null ? "false" : readonly.equals("readonly") ? "true" : "false");
	}

	public String getReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(String readOnly)
	{
		this.readOnly = readOnly;
	}

	public String label()
	{
		return label;
	}

	public void label(String label)
	{
		this.label = label;
	}

	public String getBins1()
	{
		return bins1;
	}

	public void setBins1(String bins1)
	{
		this.bins1 = bins1;
	}

	@Override
	public String toString()
	{
	    return String.format("t.setXBins(%s,\"%s\",%s);", bins1, label, readOnly);
	}
}
