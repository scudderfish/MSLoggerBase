package uk.org.smithfamily.mslogger.ecuDef;

public class DialogField
{
    private String label = "";
    private String name = "";
    
    private boolean displayOnly = false;
    
    public DialogField(String label, String name, boolean displayOnly)
    {
        this.label = label;
        this.name = name;
        this.displayOnly = displayOnly;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isDisplayOnly() {
        return displayOnly;
    }
    
    public void setDisplayOnly(boolean displayOnly) {
        this.displayOnly = displayOnly;
    }

    public String generateCode()
    {
        return String.format("        d.addField(new DialogField(\"%s\",\"%s\",%s));\n", label, name, displayOnly);
    }
    
    @Override
    public String toString()
    {
        return "DialogField [label=" + label + ", name=" + name + ", displayOnly=" + displayOnly + "]";
    }
}
