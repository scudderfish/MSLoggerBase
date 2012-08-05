package uk.org.smithfamily.mslogger.ecuDef;

public class DialogField
{
    private String label = "";
    private String name = "";
    
    private String expression = "";
    private boolean displayOnly = false;
    
    public DialogField(String label, String name, String expression, boolean displayOnly)
    {
        this.label = label;
        this.name = name;
        this.expression = expression;
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
    
    public String getExpression() {
        return expression;
    }
    
    public void setExpression(String expression) {
        this.expression = expression;
    }
    
    public boolean isDisplayOnly() {
        return displayOnly;
    }
    
    public void setDisplayOnly(boolean displayOnly) {
        this.displayOnly = displayOnly;
    }

    public String generateCode()
    {
        return String.format("        d.addField(new DialogField(\"%s\",\"%s\",\"%s\",%s));\n", label, name, expression, displayOnly);
    }
    
    @Override
    public String toString()
    {
        return "DialogField [label=" + label + ", name=" + name + ", expression=" + expression + ", displayOnly=" + displayOnly + "]";
    }
}
