package uk.org.smithfamily.utils.normaliser.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class MenuTracker
{
    private TreeMap<String,List<MenuItem>> items = new TreeMap<String,List<MenuItem>>();
    
    public void addItem(String dialog, MenuItem x)
    {
        // Already have a menu for that dialog name, will add it to the list
        if (items.containsKey(dialog))
        {
            items.get(dialog).add(x);
        }
        // No menu for that dialog yet, creating a new list with the menu definition
        else 
        {
            List<MenuItem> m = new ArrayList<MenuItem>();
            m.add(x);
            
            items.put(dialog, m);
        }
    }

    /**
     * Get the last menu of the specific dialog
     * We cannot just take the last item because it might be a MenuPreProcessor
     * 
     * @param dialog
     * @return
     */
    public MenuItem getLast(String dialog)
    {
        int index = items.get(dialog).size() - 1;
        MenuItem menuItem = null;

        do
        {
            menuItem = items.get(dialog).get(index);
        }
        while (index-- > 0 && !(menuItem instanceof MenuDefinition));

        return menuItem;
    }
    
    public Set<Entry<String, List<MenuItem>>> getItems()
    {
        return items.entrySet();
    }

    @Override
    public String toString()
    {
        return "MenuTracker [items=" + items + "]";
    }
}
