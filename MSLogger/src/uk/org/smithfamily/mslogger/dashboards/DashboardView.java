package uk.org.smithfamily.mslogger.dashboards;

import java.util.*;

import uk.org.smithfamily.mslogger.DataManager;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Location;
import uk.org.smithfamily.mslogger.widgets.renderers.*;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DashboardView extends SurfaceView implements Observer, SurfaceHolder.Callback
{
    private final int position;
    private final Context context;
    private final List<Indicator> indicators;
    private final DashboardThread thread;
    private final int MAX_FPS = 60;
    private final int DELAY_PER_FRAME = 1000 / MAX_FPS;
    private int measuredHeight;
    private int measuredWidth;

    public DashboardView(final Context context, final int position)
    {
        super(context);
        this.context = context;
        this.position = position;
        indicators = new ArrayList<Indicator>();
        getHolder().addCallback(this);
        thread = new DashboardThread(getHolder(), context, this);

        // DataManager will ping when the ECU has finished a cycle
        DataManager.getInstance().addObserver(this);
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
                thread.interrupt();
                thread.join();
                retry = false;
            }
            catch (final InterruptedException e)
            {
                // we will try it again and again...
            }
        }

    }

    public void setDashboard(final Dashboard d)
    {
        indicators.clear();
        List<Indicator> indicatorsFromDash;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            indicatorsFromDash = d.getLandscape();
        }
        else
        {
            indicatorsFromDash = d.getPortrait();
        }
        indicators.addAll(indicatorsFromDash);
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

        for (final Indicator i : indicators)
        {
            final Location loc = i.getLocation();

            final int ctop = (int) (loc.getTop() * height);
            final int cleft = (int) (loc.getLeft() * width);
            final int cright = (int) (loc.getRight() * width);
            final int cbottom = (int) (loc.getBottom() * height);
            i.setLayout(cleft, ctop, cright, cbottom);
        }
    }

    @Override
    public void update(final Observable observable, final Object data)
    {
        // Delegate the update
        thread.update(observable, data);

    }

    class DashboardThread extends Thread implements Observer
    {
        private volatile boolean running = false;
        private final Context context;
        private final SurfaceHolder holder;
        @SuppressWarnings("unused")
        private final Resources resources;
        private final Object updateLock = new Object();
        private final DashboardView parent;
        private boolean isDirty;
        private long lastUpdate = System.currentTimeMillis();

        public DashboardThread(final SurfaceHolder holder, final Context context, final DashboardView parent)
        {
            this.holder = holder;
            this.context = context;
            this.parent = parent;
            this.resources = context.getResources();
            this.setName("DashboardThread" + parent.position);
        }

        private Renderer createRenderer(final Indicator i, final Context context, final DashboardView dashboardView)
        {
            final Renderer r;
            switch (i.getDisplayType())
            {
            case GAUGE:
                r = new Gauge(dashboardView, i, context);
                break;
            case BAR:
                r = new BarGraph(dashboardView, i, context);
                break;
            case NUMERIC:
                r = new NumericIndicator(dashboardView, i, context);
                break;
            default:
                r = new Gauge(dashboardView, i, context);
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
                            drawIndicators(c);
                        }
                    }
                    finally
                    {
                        if (c != null)
                        {
                            holder.unlockCanvasAndPost(c);
                        }
                    }
                    final long now = System.currentTimeMillis();
                    final long delay = now - lastUpdate;
                    if (delay < DELAY_PER_FRAME)
                    {
                        final long inducedDelay = DELAY_PER_FRAME - delay;
                        try
                        {
                            Thread.sleep(inducedDelay);
                        }
                        catch (final InterruptedException e)
                        {
                            // Swallow
                        }
                    }
                    lastUpdate = System.currentTimeMillis();

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
        }

        @Override
        public void update(final Observable observable, final Object data)
        {
            synchronized (updateLock)
            {
                for (final Indicator i : indicators)
                {
                    manageRenderer(i);
                    i.getRenderer().setTargetValue(i.getValue());
                }
                updateLock.notifyAll();
            }
        }

        private boolean manageRenderer(final Indicator i)
        {
            if ((i.getRenderer() == null) || (i.getRenderer().getType() != i.getDisplayType()))
            {
                i.setRenderer(createRenderer(i, context, parent));
                return true;
            }
            return false;
        }

        public void drawIndicators(final Canvas c)
        {
            isDirty = false;
            boolean indicatorDirty = false;
            for (final Indicator i : parent.getIndicators())
            {
                indicatorDirty = manageRenderer(i);
                final Renderer r = i.getRenderer();
                indicatorDirty |= r.updateAnimation();
                if (indicatorDirty)
                {
                    r.renderFrame(c);
                }
                isDirty |= indicatorDirty;
            }
        }
    }

    public List<Indicator> getIndicators()
    {
        return indicators;
    }

}