package uk.org.smithfamily.mslogger.views;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.widgets.Gauge;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
import uk.org.smithfamily.mslogger.widgets.Indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class DashboardViewGroup extends ViewGroup
{
    List<Indicator> indicators = new ArrayList<Indicator>();
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
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.CYAN);

        Gauge g = new Gauge(context);
        //g.initFromName("rpm");
        this.addView(g);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);

        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int width = r - l;
        int height = b - t;
        int numChildren = getChildCount();

        for(int i = 0; i < numChildren; i++)
        {
            View v = getChildAt(i);
            v.layout(l, t, r, b);
        }
    }
}
