package uk.org.smithfamily.utils.normaliser.curveeditor;

import java.util.ArrayList;
import java.util.List;

public class CurveTracker
{
    private String          name;
    private List<CurveItem> items                = new ArrayList<CurveItem>();
    private int             interestingItemCount = 0;
    private int             curlyBracketCount    = 0;

    public void addItem(CurveItem x)
    {
        items.add(x);

        if (!(x instanceof CurvePreProcessor))
        {
            interestingItemCount++;
        }
        else
        {
            if(x.toString().contains("{"))
            {
                curlyBracketCount++;
            }
            if(x.toString().contains("}"))
            {
                curlyBracketCount--;
            }
        }
    }

    public List<CurveItem> getItems()
    {
        return items;
    }

    public boolean isDefinitionCompleted()
    {
        return (interestingItemCount >= 5 && curlyBracketCount == 0);
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
