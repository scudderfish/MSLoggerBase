package uk.org.smithfamily.mslogger.ecuDef;

import java.util.ArrayList;
import java.util.List;


public class MenuDefinition
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
    
    public void addSubMenu(SubMenuDefinition subMenu)
    {
        this.subMenus.add(subMenu);
    }

    public List<SubMenuDefinition> getSubMenus()
    {
        return subMenus;
    }

    public SubMenuDefinition getSubMenuAt(int pos)
    {
        return subMenus.get(pos);
    }

    public String generateCode()
    {
        String output = String.format("m = new MenuDefinition(\"%s\",\"%s\");\n", dialog, label);
        
        for (SubMenuDefinition sub : subMenus)
        {
            output += sub.generateCode();
        }
        
        return output;        
    }

    @Override
    public String toString()
    {
        return "MenuDefinition [dialog=" + dialog + ", label=" + label + ", subMenus=" + subMenus + "]";
    }
}
