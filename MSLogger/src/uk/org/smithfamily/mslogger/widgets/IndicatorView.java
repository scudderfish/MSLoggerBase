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

    class IndicatorThread extends Thread implements Observer
    {
        private volatile boolean running = false;
        private final Context context;
        private final SurfaceHolder holder;
        private final Resources resources;
        private final IndicatorView parentView;
        private final Indicator indicator;
        private Renderer renderer;
        private final Object updateLock = new Object();

        public IndicatorThread(final SurfaceHolder holder, final Context context, final IndicatorView indicatorView)
        {
            this.holder = holder;
            this.context = context;
            this.parentView = indicatorView;
            this.indicator = parentView.getIndicator();
            this.resources = context.getResources();
            this.setName("IndicatorThread:" + indicator.getChannel());
            switch (indicator.getDisplayType())
            {
            case GAUGE:
                renderer = new Gauge(indicatorView, context);
                break;
            case BAR:
                renderer = new BarGraph(indicatorView, context);
                break;
            case NUMERIC:
                renderer = new NumericIndicator(indicatorView, context);
                break;
            default:
                renderer = new Gauge(indicatorView, context);
                break;
            }
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            indicator.deleteObserver(this);
        }

        @Override
        public void update(final Observable observable, final Object data)
        {
            synchronized (updateLock)
            {
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
