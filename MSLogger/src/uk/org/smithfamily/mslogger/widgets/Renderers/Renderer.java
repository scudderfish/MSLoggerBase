package uk.org.smithfamily.mslogger.widgets.Renderers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Size;

public abstract class Renderer
{
    protected Indicator parent;

    public Renderer(Indicator parent,Context c)
    {
        this.parent = parent;
        init(c);
    }

    protected Resources getResources()
    {
        return parent.getResources();
    }

    protected abstract void init(Context c);

    public abstract void paint(Canvas canvas);

    public abstract String getType();

    /**
     * 
     * @return
     */
    protected int getFgColour()
    {
        int c = Color.WHITE;
        
        if (parent.isDisabled())
        {
            return Color.DKGRAY;
        }
        
        if (parent.getValue() > parent.getLowW() && parent.getValue() < parent.getHiW())
        {
            return Color.WHITE;
        }
        else if (parent.getValue() <= parent.getLowW() || parent.getValue() >= parent.getHiW())
        {
            c = Color.YELLOW;
        }
        if (parent.getValue() <= parent.getLowD() || parent.getValue() >= parent.getHiD())
        {
            c = Color.RED;
        }
        
        return c;
    }

    /**
     * Get the size this renderer wants to be given the maximum size it is allowed to be
     * @param measuredWidth
     * @param measuredHeight
     * @return
     */
    public Size getSize(int measuredWidth, int measuredHeight)
    {
        return new Size(measuredWidth,measuredHeight);
    }
    
}
