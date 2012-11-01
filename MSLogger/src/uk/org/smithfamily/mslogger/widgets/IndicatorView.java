package uk.org.smithfamily.mslogger.widgets;

import java.util.Observable;
import java.util.Observer;

import uk.org.smithfamily.mslogger.widgets.renderers.BarGraph;
import uk.org.smithfamily.mslogger.widgets.renderers.Gauge;
import uk.org.smithfamily.mslogger.widgets.renderers.NumericIndicator;
import uk.org.smithfamily.mslogger.widgets.renderers.Renderer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class IndicatorView extends SurfaceView implements SurfaceHolder.Callback
{
    private final IndicatorThread thread;
    private final Indicator indicator;
    public boolean isDirty;

    public IndicatorView(final Context context, final Indicator indicator)
    {
        super(context);
        this.indicator = indicator;
        getHolder().addCallback(this);
        thread = new IndicatorThread(getHolder(), context, this);
        setFocusable(true);
    }

    class SetDelayedValue implements Runnable
    {
        private final Indicator indicator;
        private final double value;
        private final long delay;

        SetDelayedValue(final Indicator i, final double value, final long delay)
        {
            this.indicator = i;
            this.value = value;
            this.delay = delay;
        }

        @Override
        public void run()
        {
            try
            {
                Thread.sleep(delay);
            }
            catch (final InterruptedException e)
            {
                // swallow
            }
            indicator.setValue(value);
        }
    }

    class IndicatorThread extends Thread implements Observer
    {
        private volatile boolean running = false;
        private final Context context;
        private final SurfaceHolder holder;
        @SuppressWarnings("unused")
        private final Resources resources;
        private final IndicatorView parentView;
        private final Indicator indicator;
        private Renderer renderer;
        private Indicator.DisplayType lastType;
        private final Object updateLock = new Object();

        public IndicatorThread(final SurfaceHolder holder, final Context context, final IndicatorView indicatorView)
        {
            this.holder = holder;
            this.context = context;
            this.parentView = indicatorView;
            this.indicator = parentView.getIndicator();
            this.resources = context.getResources();
            this.setName("IndicatorThread:" + indicator.getChannel());
            lastType = indicator.getDisplayType();
            renderer = createRenderer(lastType, context, indicatorView);

        }

        private Renderer createRenderer(final Indicator.DisplayType type, final Context context, final IndicatorView indicatorView)
        {
            final Renderer r;
            switch (lastType)
            {
            case GAUGE:
                r = new Gauge(indicatorView, context);
                break;
            case BAR:
                r = new BarGraph(indicatorView, context);
                break;
            case NUMERIC:
                r = new NumericIndicator(indicatorView, context);
                break;
            default:
                r = new Gauge(indicatorView, context);
                break;
            }
            return r;
        }

        public void setRunning(final boolean r)
        {
            running = r;
            isDirty = r;
        }

        @Override
        public void run()
        {
            Canvas c;
            indicator.addObserver(this);
            new Thread(new SetDelayedValue(indicator, indicator.getMax(), 100)).start();
            new Thread(new SetDelayedValue(indicator, indicator.getMin(), 1100)).start();

            while (running)
            {
                while (isDirty)
                {
                    c = null;
                    try
                    {
                        c = holder.lockCanvas();
                        synchronized (holder)
                        {
                            drawIndicator(c);
                        }
                    }
                    finally
                    {
                        if (c != null)
                        {
                            holder.unlockCanvasAndPost(c);
                        }
                    }
                }
                try
                {
                    synchronized (updateLock)
                    {
                        updateLock.wait();
                        isDirty = true;
                    }
                }
                catch (final InterruptedException e)
                {
                    // Swallow
                }
            }
            indicator.deleteObserver(this);
        }

        @Override
        public void update(final Observable observable, final Object data)
        {
            synchronized (updateLock)
            {
                if (lastType != indicator.getDisplayType())
                {
                    renderer = createRenderer(lastType, context, parentView);
                    lastType = indicator.getDisplayType();
                }
                renderer.setTargetValue(indicator.getValue());
                updateLock.notifyAll();
            }
        }

        public void drawIndicator(final Canvas c)
        {
            renderer.renderFrame(c);
            isDirty = renderer.updateAnimation();
        }

    }

    @Override
    public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height)
    {

    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder)
    {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder holder)
    {
        boolean retry = true;
        thread.setRunning(false);
        while (retry)
        {
            try
            {
                thread.join();
                retry = false;
            }
            catch (final InterruptedException e)
            {
                // we will try it again and again...
            }
        }

    }

    public Indicator getIndicator()
    {
        return indicator;
    }
}
