package uk.org.smithfamily.mslogger.ecuDef;

public class DialogPanel
{
    private String name = "";
    private String orientation = "";
    
    public DialogPanel(String name, String orientation)
    {
        this.name = name;
        this.orientation = orientation;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getOrientation() {
        return orientation;
    }

    public String generateCode()
    {
        return String.format("        d.addPanel(new DialogPanel(\"%s\",\"%s\"));\n", name, orientation);
    }
    
    @Override
    public String toString()
    {
        return "DialogPanel [name=" + name + ", orientation=" + orientation + "]";
    }
}
