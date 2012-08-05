package uk.org.smithfamily.mslogger.ecuDef;

public class SubMenuDefinition
{
    private String label = "";
    private String name = "";
    private String randomNumber = "0";
    private String expression = "";
    
    public SubMenuDefinition(String label, String name, String randomNumber, String expression)
    {
        this.label = label;
        this.name = name;
    }
    
    public String getLabel()
    {
        return label;
    }
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public String getRandomNumber()
    {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber)
    {
        this.randomNumber = randomNumber;
    }

    public String getExpression()
    {
        return expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public String generateCode()
    {
        return String.format("        m.addSubMenu(new SubMenuDefinition(\"%s\",\"%s\",\"%s\",\"%s\"));\n", label, name, randomNumber, expression);
    }

    @Override
    public String toString()
    {
        return "SubMenuDefinition [label=" + label + ", name=" + name + ", randomNumber=" + randomNumber + ", expression=" + expression + "]";
    }
}
