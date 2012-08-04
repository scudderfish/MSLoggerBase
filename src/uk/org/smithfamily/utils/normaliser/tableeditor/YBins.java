package uk.org.smithfamily.utils.normaliser.tableeditor;

public class YBins extends TableItem
{

	private String bins1;
	private String label;
	private String readOnly;

	public YBins(String bins1, String label, String readonly)
	{
		this.bins1 = bins1;
		this.label = label;
        this.readOnly = (readonly == null ? "false" : readonly.equals("readonly") ? "true" : "false");
	
	}

	public String getBins1()
	{
		return bins1;
	}

	public void setBins1(String bins1)
	{
		this.bins1 = bins1;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(String readOnly)
	{
		this.readOnly = readOnly;
	}
    @Override
    public String toString()
    {
        return String.format("t.setYBins(%s,\"%s\",%s);", bins1,label,readOnly);
    }

}
