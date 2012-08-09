package uk.org.smithfamily.utils.normaliser.userdefined;

public class UserDefinedPanel extends UserDefinedItem
{

	private String name;
	private String orientation;

	public UserDefinedPanel(String name, String orientation)
    {
        this.name = name;
        this.orientation = orientation;
    }

	public String getName()
	{
		return name;
	}

	public String getOrientation()
	{
		return orientation;
	}
}
