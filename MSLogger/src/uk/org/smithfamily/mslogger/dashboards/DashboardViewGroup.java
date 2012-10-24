package uk.org.smithfamily.mslogger.dashboards;

import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Location;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class DashboardViewGroup extends ViewGroup
{
    private int position;

    public DashboardViewGroup(Context context, int position)
    {
        super(context);
        this.position = position;
    }

    private int measuredHeight;
    private int measuredWidth;

    public void setDashboard(Dashboard d)
    {
        this.removeAllViews();
        for (Indicator i : d)
        {
            this.addView(i);
        }

    }

    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int width = r - l;
        int height = b - t;

        final int count = getChildCount();

        // Calculate the number of visible children.
        int visibleCount = 0;
        for (int i = 0; i < count; i++)
        {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE)
            {
                continue;
            }
            ++visibleCount;
        }

        if (visibleCount == 0)
        {
            return;
        }

        for (int i = 0; i < count; i++)
        {
            final Indicator child = (Indicator) getChildAt(i);
            if (child.getVisibility() == GONE)
            {
                continue;
            }

            Location loc = child.getLocation();

            int ctop = (int) (loc.getTop() * height);
            int cleft = (int) (loc.getLeft() * width);
            int cright = (int) (loc.getRight() * width);
            int cbottom = (int) (loc.getBottom() * height);
            child.layout(cleft, ctop, cright, cbottom);

        }
    }
}
