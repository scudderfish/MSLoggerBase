package uk.org.smithfamily.utils.normaliser.userdefined;

public class UserDefinedField extends UserDefinedItem
{

	private String label;
	private String name;
	private String expression;
	private boolean displayOnly;

	public UserDefinedField(String label, String name, String expression, boolean displayOnly)
    {
        this.label = label;
        this.name = name;
        if (expression == null)
        {
        	expression = "true";
        }
        this.expression = expression;
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

	public String getExpression()
	{
		return expression;
	}

	public boolean isDisplayOnly()
	{
		return displayOnly;
	}
	
	@Override
	public String toString()
	{
		return String.format("d.addField(new DialogField(\"%s\",\"%s\",\"%s\",%s));", label, name, expression, displayOnly);
	}
}
