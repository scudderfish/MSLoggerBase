package uk.org.smithfamily.utils.normaliser.tableeditor;

public class UpDownLabel extends TableItem
{

	private String down;
	private String up;

	public UpDownLabel(String up, String down)
	{
		this.up = up;
		this.down = down;
	}

	public String getDown()
	{
		return down;
	}

	public void setDown(String down)
	{
		this.down = down;
	}

	public String getUp()
	{
		return up;
	}

	public void setUp(String up)
	{
		this.up = up;
	}

	public String toString()
	{
	    return String.format("t.setUpDownLabel(\"%s\",\"%s\");",up,down);
	}
}
