package uk.org.smithfamily.mslogger.ecuDef;

public class SubMenu
{
    private String label = "";
    private String name = "";
    private String randomNumber = "0";
    
    public SubMenu(String name, String label, String randomNumber)
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

    @Override
    public String toString()
    {
        return "SubMenu [label=" + label + ", name=" + name + ", randomNumber=" + randomNumber + "]";
    }
}
