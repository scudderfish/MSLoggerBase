package uk.org.smithfamily.mslogger.views;

import java.io.IOException;
import java.util.List;

import uk.org.smithfamily.mslogger.dashboards.Dashboard;
import uk.org.smithfamily.mslogger.dashboards.DashboardIO;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Location;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class DashboardViewGroup extends ViewGroup
{
    private Paint backgroundPaint;

    public DashboardViewGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public DashboardViewGroup(Context context)
    {
        super(context);
        init(context);
    }

    public DashboardViewGroup(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        init(context);
    }

    private void init(Context context)
    {
        setWillNotDraw(false);
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.CYAN);

        DashboardIO p = new DashboardIO();
        try
        {
            List<Dashboard> d = p.parse();
            List<Indicator> indicators = d.get(0);
            for (Indicator i : indicators)
            {
                this.addView(i);
            }

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

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

            int ctop = resolve(loc.getTop(), t, height, width);
            int cleft = resolve(loc.getLeft(), l, height, width);
            int cwidth = resolve(loc.getWidth(), r, height, width);
            int cheight = resolve(loc.getHeight(), b, height, width);
            child.layout(cleft, ctop, cleft+cwidth, ctop+cheight);

        }
    }

    private int resolve(double ratio, int l, int height, int width)
    {
        int max = Math.max(height, width);
        int min = Math.min(height, width);

        if (ratio < 0)
        {
            return (int) (-ratio * max);
        }
        else
        {
            return (int) (ratio * min);
        }
    }
}
