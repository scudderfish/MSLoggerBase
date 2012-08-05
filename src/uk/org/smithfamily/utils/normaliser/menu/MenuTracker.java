package uk.org.smithfamily.utils.normaliser.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import uk.org.smithfamily.mslogger.ecuDef.MenuDefinition;

public class MenuTracker
{
    private Map<String,List<MenuDefinition>> items = new HashMap<String,List<MenuDefinition>>();
    
    public void addItem(String dialog, MenuDefinition x)
    {
        // Already have a menu for that dialog name, will add it to the list
        if (items.containsKey(dialog))
        {
            items.get(dialog).add(x);
        }
        // No menu for that dialog yet, creating a new list with the menu definition
        else 
        {
            List<MenuDefinition> m = new ArrayList<MenuDefinition>();
            m.add(x);
            
            items.put(dialog, m);
        }
    }

    /**
     * Get the last menu of the specific dialog
     * 
     * @param dialog
     * @return
     */
    public MenuDefinition getLast(String dialog)
    {
        return items.get(dialog).get(items.get(dialog).size() - 1);
    }
    
    public Set<Entry<String, List<MenuDefinition>>> getItems()
    {
        return items.entrySet();
    }

    @Override
    public String toString()
    {
        return "MenuTracker [items=" + items + "]";
    }
}
