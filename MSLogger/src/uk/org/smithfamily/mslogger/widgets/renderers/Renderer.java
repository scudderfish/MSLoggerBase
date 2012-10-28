package uk.org.smithfamily.mslogger.widgets.renderers;

import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.IndicatorView;
import uk.org.smithfamily.mslogger.widgets.Size;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;

public abstract class Renderer
{
    protected IndicatorView parent;
    protected final Indicator model;

    protected double currentValue = 0.0;
    protected double targetValue = 0.0;

    private final long FULL_SWEEP_TIME = 500;
    private final int MAX_FPS = 30;
    private final int DELAY_PER_FRAME = 1000 / MAX_FPS;
    private final double epsilon = 1e-5;
    private long lastPointerMoveTime;

    public Renderer(final IndicatorView parent, final Context c)
    {
        this.parent = parent;
        this.model = parent.getIndicator();
        init(c);
        lastPointerMoveTime = System.currentTimeMillis();
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
        if (Math.abs(targetValue - currentValue) > epsilon)
        {
            isDirty = true;

            final double range = model.getMax() - model.getMin();
            final double incPerMilli = range / FULL_SWEEP_TIME;

            final long currentTime = System.currentTimeMillis();
            final long delay = currentTime - lastPointerMoveTime;

            final double direction = Math.signum(targetValue - currentValue);

            final double delta = Math.abs(targetValue - currentValue);
            final double increment = Math.min(delta, delay * incPerMilli);

            currentValue += (increment * direction);
            lastPointerMoveTime = System.currentTimeMillis();

            if (delay < DELAY_PER_FRAME)
            {
                try
                {
                    Thread.sleep(DELAY_PER_FRAME - delay);
                }
                catch (final InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return isDirty;
    }

    public abstract String getType();

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
