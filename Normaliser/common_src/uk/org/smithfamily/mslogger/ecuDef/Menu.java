package uk.org.smithfamily.mslogger.ecuDef;

import java.util.ArrayList;
import java.util.List;

public class Menu
{
    private String dialog = "";
    private String label = "";
    private List<SubMenu> subMenus = new ArrayList<SubMenu>();
    
    public Menu(String dialog, String label)
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
    
    public void addSubMenu(SubMenu subMenu)
    {
        this.subMenus.add(subMenu);
    }

    public List<SubMenu> getSubMenus()
    {
        return subMenus;
    }

    public SubMenu getSubMenuAt(int pos)
    {
        return subMenus.get(pos);
    }

    @Override
    public String toString()
    {
        return "Menu [dialog=" + dialog + ", label=" + label + ", subMenus=" + subMenus + "]";
    }
}
