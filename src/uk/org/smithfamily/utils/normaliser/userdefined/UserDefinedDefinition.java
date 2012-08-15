package uk.org.smithfamily.utils.normaliser.userdefined;

public class UserDefinedDefinition extends UserDefinedItem
{

	private String name;
	private String label;

	public UserDefinedDefinition(UserDefinedTracker parent,String name,String label)
	{
		this.name = name;
		this.label = label;
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
	
	@Override
	public String toString()
	{
        return String.format("d = new MSDialog(\"%s\",\"%s\"); dialogs.put(\"%s\",d);",name,label,name);
	}
}
