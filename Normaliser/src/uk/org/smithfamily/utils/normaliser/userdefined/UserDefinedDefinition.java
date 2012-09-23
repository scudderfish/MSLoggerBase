package uk.org.smithfamily.utils.normaliser.userdefined;

public class UserDefinedDefinition extends UserDefinedItem
{

	private String name;
	private String label;
	private String axis;

	public UserDefinedDefinition(UserDefinedTracker parent, String name, String label, String axis)
	{
		this.name = name;
		this.label = label;
		this.axis = axis;
		parent.setName(name);
	}

	public String getName()
	{
		return name;
	}

	public String getLabel()
	{
		return label;
	}
	
	public String getAxis()
	{
	    return axis;
	}
	
	@Override
	public String toString()
	{
        return String.format("d = new MSDialog(\"%s\",\"%s\",\"%s\"); dialogs.put(\"%s\",d);", name, label, axis, name);
	}
}
