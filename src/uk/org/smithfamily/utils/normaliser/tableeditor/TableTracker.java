package uk.org.smithfamily.utils.normaliser.tableeditor;

import java.util.ArrayList;
import java.util.List;

public class TableTracker
{
    private String name;
    private List<TableItem> items = new ArrayList<TableItem>();
    private int interestingItemCount = 0;
    private int curlyBracketCount = 0;

    public void addItem(TableItem x)
    {
        items.add(x);

        if (!(x instanceof PreProcessor))
        {
            interestingItemCount++;
        } else
        {
            if (x.toString().contains("{"))
            {
                curlyBracketCount++;
            }
            if (x.toString().contains("}"))
            {
                curlyBracketCount--;
            }
        }
    }

    public List<TableItem> getItems()
    {
        return items;
    }

    public boolean isDefinitionCompleted()
    {
        return (interestingItemCount >= 4 && curlyBracketCount == 0);
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
