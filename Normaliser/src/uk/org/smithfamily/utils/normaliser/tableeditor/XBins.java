package uk.org.smithfamily.utils.normaliser.tableeditor;

public class XBins extends TableItem
{
	private String readOnly;
	private String outputChannel;
	private String bins;

	public XBins(String bins, String outputChannel, String readOnly)
	{
		this.bins = bins;
		this.outputChannel = outputChannel;
		this.readOnly = (readOnly == null ? "false" : readOnly.equals("readonly") ? "true" : "false");
	}

	public String getReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(String readOnly)
	{
		this.readOnly = readOnly;
	}

	public String getOutputChannel()
	{
		return outputChannel;
	}

	public void setOutputChannel(String outputChannel)
	{
		this.outputChannel = outputChannel;
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
	    return String.format("t.setXBins(%s,\"%s\",%s);", bins, outputChannel, readOnly);
	}
}
