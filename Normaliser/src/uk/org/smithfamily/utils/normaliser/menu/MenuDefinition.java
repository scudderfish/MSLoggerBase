package uk.org.smithfamily.utils.normaliser.menu;

import java.util.ArrayList;
import java.util.List;



public class MenuDefinition extends MenuItem
{
    private String dialog = "";
    private String label = "";
    private List<SubMenuDefinition> subMenus = new ArrayList<SubMenuDefinition>();
    
    public MenuDefinition(String dialog, String label)
    {
        this.dialog = dialog;
        if (label != null) label = label.replace("&", "");
        this.label = label;
    }

    public String getDialog()
    {
        return dialog;
    }

    public void setDialog(String dialog)
    {
        this.dialog = dialog;
    }
    
    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    @Override
    public String toString()
    {
        String output = String.format("m = new Menu(\"%s\",\"%s\");\n", dialog, label);
        
        for (SubMenuDefinition sub : subMenus)
        {
            output += sub.toString();
        }
        
        return output;
    }
}
