package uk.org.smithfamily.utils.normaliser.userdefined;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import uk.org.smithfamily.mslogger.ecuDef.Dialog;

public class DialogTracker
{
    private Map<String,Dialog> items = new HashMap<String,Dialog>();
    
    public void addItem(String dialog, Dialog x)
    {
        items.put(dialog, x);
    }
    
    public Dialog getDialog(String dialog)
    {
        return items.get(dialog);
    }
    
    public Set<Entry<String, Dialog>> getItems()
    {
        return items.entrySet();
    }
}
