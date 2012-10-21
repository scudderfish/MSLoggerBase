package uk.org.smithfamily.mslogger.ecuDef;

public class DialogField
{
    private String label = "";
    private String name = "";
    
    private boolean displayOnly = false;
    private boolean commandButton = false;
    
    private String commandOnClose;
    
    public DialogField(String label, String name, boolean displayOnly, boolean commandButton, String commandOnClose)
    {
        this.label = label;
        this.name = name;
        this.displayOnly = displayOnly;
        this.commandButton = commandButton;
        this.commandOnClose = commandOnClose;
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
    
    public boolean isCommandButton()
    {
        return commandButton;
    }

    public void setCommandButton(boolean commandButton)
    {
        this.commandButton = commandButton;
    }

    public String getCommandOnClose()
    {
        return commandOnClose;
    }

    public void setCommandOnClose(String commandOnClose)
    {
        this.commandOnClose = commandOnClose;
    }
    
    public String generateCode()
    {
        return String.format("        d.addField(new DialogField(\"%s\",\"%s\",%s,%s,\"%s\"));\n", label, name, displayOnly, commandButton, commandOnClose);
    }
    
    @Override
    public String toString()
    {
        return "DialogField [label=" + label + ", name=" + name + ", displayOnly=" + displayOnly + ", commandButton=" + commandButton + ", commandOnClose=" + commandOnClose + "]";
    }
}
