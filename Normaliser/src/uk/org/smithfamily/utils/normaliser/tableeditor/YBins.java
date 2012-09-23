package uk.org.smithfamily.utils.normaliser.tableeditor;

public class YBins extends TableItem
{
    private String bins;
    private String outputChannel;
    private String readOnly;

    public YBins(String bins, String outputChannel, String readOnly)
    {
        this.bins = bins;
        this.outputChannel = outputChannel;
        this.readOnly = (readOnly == null ? "false" : readOnly.equals("readonly") ? "true" : "false");

    }

    public String getBins()
    {
        return bins;
    }

    public void setBins(String bins)
    {
        this.bins = bins;
    }

    public String getLabel()
    {
        return outputChannel;
    }

    public void setLabel(String label)
    {
        this.outputChannel = label;
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
        return String.format("t.setYBins(%s,\"%s\",%s);", bins, outputChannel, readOnly);
    }

}
