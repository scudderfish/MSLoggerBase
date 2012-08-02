package uk.org.smithfamily.utils.normaliser.tableeditor;

public class PreProcessor extends TableItem
{

	private String text;

	public PreProcessor(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public String toString()
	{
	    return text;
	}
}
