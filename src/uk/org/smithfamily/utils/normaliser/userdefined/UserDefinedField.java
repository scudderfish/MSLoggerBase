package uk.org.smithfamily.utils.normaliser.userdefined;

public class UserDefinedField extends UserDefinedItem
{

	private String label;
	private String name;
	private boolean displayOnly;

	public UserDefinedField(String label, String name, boolean displayOnly)
    {
        this.label = label;
        this.name = name;
        this.displayOnly = displayOnly;
    }

	public String getLabel()
	{
		return label;
	}

	public String getName()
	{
		return name;
	}

	public boolean isDisplayOnly()
	{
		return displayOnly;
	}
	
	@Override
	public String toString()
	{
		return String.format("d.addField(new DialogField(\"%s\",\"%s\",%s));", label, name, displayOnly);
	}
}
