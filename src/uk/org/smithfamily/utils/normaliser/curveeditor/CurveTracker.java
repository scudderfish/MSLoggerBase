package uk.org.smithfamily.utils.normaliser.curveeditor;

import java.util.ArrayList;
import java.util.List;

public class CurveTracker
{
    String          name;
    List<CurveItem> items = new ArrayList<CurveItem>();
    private boolean definitionCompleted;

    public void addItem(CurveItem x)
    {
        items.add(x);

        // TODO: There might be a gauge and/or one or multiple lineLabel after yBins, so we don't necessarily want to stop there
        if (x instanceof YAxis)
        {
            this.definitionCompleted = true;
        }
    }

    public List<CurveItem> getItems()
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
