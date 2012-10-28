package uk.org.smithfamily.mslogger.dashboards;

import java.util.List;

import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.IndicatorView;
import uk.org.smithfamily.mslogger.widgets.Location;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;

public class DashboardViewGroup extends ViewGroup
{
    private final int position;
    private final Context context;

    public DashboardViewGroup(final Context context, final int position)
    {
        super(context);
        this.context = context;
        this.position = position;
    }

    private int measuredHeight;
    private int measuredWidth;

    public void setDashboard(final Dashboard d)
    {
        this.removeAllViews();
        List<Indicator> indicators;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            indicators = d.getLandscape();
        }
        else
        {
            indicators = d.getPortrait();
        }
        for (final Indicator i : indicators)
        {
            final IndicatorView v = new IndicatorView(context, i);
            this.addView(v);
        }

    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec)
    {
        measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b)
    {
        final int width = r - l;
        final int height = b - t;

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
            final IndicatorView child = (IndicatorView) getChildAt(i);
            if (child.getVisibility() == GONE)
            {
                continue;
            }

            final Location loc = child.getIndicator().getLocation();

            final int ctop = (int) (loc.getTop() * height);
            final int cleft = (int) (loc.getLeft() * width);
            final int cright = (int) (loc.getRight() * width);
            final int cbottom = (int) (loc.getBottom() * height);
            child.layout(cleft, ctop, cright, cbottom);

        }
    }
}
