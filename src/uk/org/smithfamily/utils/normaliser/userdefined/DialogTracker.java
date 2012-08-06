package uk.org.smithfamily.utils.normaliser.userdefined;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import uk.org.smithfamily.mslogger.ecuDef.MSDialog;

public class DialogTracker
{
    private Map<String,MSDialog> items = new HashMap<String,MSDialog>();
    
    public void addItem(String dialog, MSDialog x)
    {
        items.put(dialog, x);
    }
    
    public MSDialog getDialog(String dialog)
    {
        return items.get(dialog);
    }
    
    public Set<Entry<String, MSDialog>> getItems()
    {
        return items.entrySet();
    }
}
