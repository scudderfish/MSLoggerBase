package uk.org.smithfamily.mslogger.ecuDef;

public class SubMenuDefinition
{
    private String label = "";
    private String name = "";
    private String randomNumber = "0";
    
    public SubMenuDefinition(String name, String label, String randomNumber)
    {
        this.name = name;
        if (label != null) label = label.replace("&", "");
        this.label = label;
        this.randomNumber = randomNumber;
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

    public String generateCode()
    {
        return String.format("        m.addSubMenu(new SubMenuDefinition(\"%s\",\"%s\",\"%s\"));\n", name, label, randomNumber);
    }

    @Override
    public String toString()
    {
        return "SubMenuDefinition [label=" + label + ", name=" + name + ", randomNumber=" + randomNumber + "]";
    }
}
