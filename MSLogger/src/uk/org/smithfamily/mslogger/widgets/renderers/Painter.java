package uk.org.smithfamily.mslogger.widgets.renderers;

import uk.org.smithfamily.mslogger.dashboards.DashboardView;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Size;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

public abstract class Painter
{
    protected DashboardView parent;
    protected final Indicator model;

    protected double currentValue = 0.0;
    protected double targetValue = 0.0;

    private final long FULL_SWEEP_TIME = 1000;
    private final double epsilon = 1e-5;
    private long lastPointerMoveTime = 0;
    private int dirtyCount = 0;
    private final RenderStats stats = new RenderStats();

    public Painter(final DashboardView parent, final Indicator model, final Context c)
    {
        this.parent = parent;
        this.model = model;
        init(c);
        lastPointerMoveTime = 0;
    }

    public void setTargetValue(final double value)
    {
        targetValue = value;

    }

    protected Resources getResources()
    {
        return parent.getResources();
    }

    protected abstract void init(Context c);

    public abstract void renderFrame(Canvas canvas);

    public boolean updateAnimation()
    {
        boolean isDirty = false;
        if (offTarget())
        {
            isDirty = true;
            dirtyCount++;
            final double range = model.getMax() - model.getMin();
            final double incPerMilli = range / FULL_SWEEP_TIME;

            final long currentTime = System.currentTimeMillis();
            if (lastPointerMoveTime == 0)
            {
                lastPointerMoveTime = currentTime;
            }
            long delay = currentTime - lastPointerMoveTime;
            if (delay == 0)
            {
                delay = 1;
            }
            final double direction = Math.signum(targetValue - currentValue);

            final double delta = Math.abs(targetValue - currentValue);
            final double increment = Math.min(delta, delay * incPerMilli);

            currentValue += (increment * direction);
            lastPointerMoveTime = System.currentTimeMillis();
            stats.updateStats(delay, delta);
            if (!offTarget())
            { // We are done
                lastPointerMoveTime = 0;
                stats.updateDirtyCount(dirtyCount);
                dirtyCount = 0;
            }

        }
        return isDirty;
    }

    public boolean offTarget()
    {
        return Math.abs(targetValue - currentValue) > epsilon;
    }

    public abstract Indicator.DisplayType getType();

    /**
     * 
     * @return
     */
    protected int getFgColour()
    {
        int c = Color.WHITE;

        if (model.isDisabled())
        {
            return Color.DKGRAY;
        }

        final double value = model.getValue();
        final double lowWarning = model.getLowW();
        final double highWarning = model.getHiW();
        final double lowDanger = model.getLowD();
        final double hiDanger = model.getHiD();

        if ((value > lowWarning) && (value < highWarning))
        {
            return Color.WHITE;
        }
        else if ((value <= lowWarning) || (value >= highWarning))
        {
            c = Color.YELLOW;
        }
        if ((value <= lowDanger) || (value >= hiDanger))
        {
            c = Color.RED;
        }

        return c;
    }

    /**
     * Get the size this renderer wants to be given the maximum size it is allowed to be
     * 
     * @param measuredWidth
     * @param measuredHeight
     * @return
     */
    public Size getSize(final int measuredWidth, final int measuredHeight)
    {
        return new Size(measuredWidth, measuredHeight);
    }

    public void onSizeChanged(final int w, final int h, final int oldw, final int oldh)
    {
        // Override if you care about this
    }

}

class RenderStats
{
    private static final long STATS_DELAY = 1000;
    int minDirty = 0;
    int maxDirty = 0;
    float avgDirty = 0;
    int dCounter = 0;
    int sCounter = 0;
    long minDelay = 0;
    long maxDelay = 0;
    float avgDelay = 0;
    double minDelta = +1e100;
    double maxDelta = -1e100;
    double avgDelta = 0;
    long lastOutput = System.currentTimeMillis();

    public void updateStats(final long delay, final double delta)
    {
        sCounter++;

        minDelay = Math.min(minDelay, delay);
        maxDelay = Math.max(maxDelay, delay);
        avgDelay = avgDelay + ((delay - avgDelay) / sCounter);
        minDelta = Math.min(minDelta, delta);
        maxDelta = Math.max(maxDelta, delta);
        avgDelta = avgDelta + ((delta - avgDelta) / sCounter);

        final long now = System.currentTimeMillis();
        if ((now - lastOutput) > STATS_DELAY)
        {
            lastOutput = now;
            Log.i("RenderStats",
                    String.format("minDelay=%d maxDelay=%d avgDelay=%.2f minDelta=%.2f maxDelta=%.2f avgDelta=%.2f minDirty=%d maxDirty=%d avgDirty=%.2f", minDelay, maxDelay, avgDelay, minDelta, maxDelta, avgDelta, minDirty, maxDirty, avgDirty));
        }
        Log.d("RenderStatsFull", String.format("delta=%.2f", delta));
    }

    public void updateDirtyCount(final int dirtyCount)
    {
        minDirty = Math.min(minDirty, dirtyCount);
        maxDirty = Math.max(maxDirty, dirtyCount);
        avgDirty = avgDirty + ((dirtyCount - avgDirty) / (++dCounter));
    }

}
