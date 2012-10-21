package uk.org.smithfamily.utils.normaliser.userdefined;

public class UserDefinedField extends UserDefinedItem
{

	private String label;
	private String name;
	private boolean displayOnly;
	private boolean commandButton;
	private String commandOnClose;

	public UserDefinedField(String label, String name, boolean displayOnly, boolean commandButton, String commandOnClose)
    {
        this.label = label;
        this.name = name;
        this.displayOnly = displayOnly;
        this.commandButton = commandButton;
        this.commandOnClose = commandOnClose;
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
	
    public void setDisplayOnly(boolean displayOnly)
    {
        this.displayOnly = displayOnly;
    }

    public String getCommandOnClose()
    {
        return commandOnClose;
    }

    public void setCommandOnClose(String commandOnClose)
    {
        this.commandOnClose = commandOnClose;
    }
    
	@Override
	public String toString()
	{
		return String.format("d.addField(new DialogField(\"%s\",\"%s\",%s,%s,\"%s\"));", label, name, displayOnly, commandButton, commandOnClose);
	}
}
