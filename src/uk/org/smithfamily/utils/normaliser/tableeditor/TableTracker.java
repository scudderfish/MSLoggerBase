package uk.org.smithfamily.utils.normaliser.tableeditor;

import java.util.ArrayList;
import java.util.List;

public class TableTracker
{
    String          name;
    List<TableItem> items = new ArrayList<TableItem>();
    private boolean definitionCompleted;

    public void addItem(TableItem x)
    {
        items.add(x);
        if (x instanceof GridOrient)
        {
            this.definitionCompleted = true;
        }
    }

    public List<TableItem> getItems()
    {
        return items;
    }

    public boolean isDefinitionCompleted()
    {
        return definitionCompleted;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
