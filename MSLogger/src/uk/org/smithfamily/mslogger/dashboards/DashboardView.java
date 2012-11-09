package uk.org.smithfamily.mslogger.dashboards;

import java.util.*;

import org.metalev.multitouch.controller.*;
import org.metalev.multitouch.controller.MultiTouchController.MultiTouchObjectCanvas;
import org.metalev.multitouch.controller.MultiTouchController.PointInfo;
import org.metalev.multitouch.controller.MultiTouchController.PositionAndScale;

import uk.org.smithfamily.mslogger.DataManager;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.*;

public class DashboardView extends SurfaceView implements Observer, SurfaceHolder.Callback, MultiTouchObjectCanvas<DashboardElement>
{
    // private static final String TAG = "DashboardView";
    private final int position;
    private final Context context;
    private final List<DashboardElement> elements;
    private final DashboardThread thread;
    private final int MAX_FPS = 60;
    private final int DELAY_PER_FRAME = 1000 / MAX_FPS;
    private int measuredHeight;
    private int measuredWidth;
    private final DashboardViewPager parentPager;
    private final MultiTouchController<DashboardElement> multiTouchController;
    private PointInfo selectedPosition;
    private DashboardElement editedItem;
    private final GestureDetector gestureScanner;

    class GestureProcessor extends GestureDetector.SimpleOnGestureListener
    {

        @Override
        public boolean onDoubleTap(final MotionEvent e)
        {

            final float px = e.getX();
            final float py = e.getY();
            final DashboardElement element = getElementAtCoOrds(px, py);

            if (element != null)
            {
                editElement(element);
            }
            else
            {
                addNewElement();
            }

            return super.onDoubleTap(e);
        }

    }

    public DashboardView(final Context context, final int position, final DashboardViewPager parent)
    {
        super(context);
        this.context = context;
        this.position = position;
        this.parentPager = parent;
        elements = new ArrayList<DashboardElement>();
        getHolder().addCallback(this);
        thread = new DashboardThread(getHolder(), context, this);

        // DataManager will ping when the ECU has finished a cycle
        DataManager.getInstance().addObserver(this);

        multiTouchController = new MultiTouchController<DashboardElement>(this);
        gestureScanner = new GestureDetector(context, new GestureProcessor());
    }

    public void editElement(final DashboardElement element)
    {
        // TODO Auto-generated method stub

    }

    public void addNewElement()
    {
        // TODO Auto-generated method stub

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
        elements.clear();
        List<Indicator> indicatorsFromDash;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            indicatorsFromDash = d.getLandscape();
        }
        else
        {
            indicatorsFromDash = d.getPortrait();
        }
        for (final Indicator i : indicatorsFromDash)
        {
            elements.add(new DashboardElement(context, i, this));
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
                while ((parentPager.getCurrentItem() == position))
                {
                    c = null;
                    try
                    {
                        c = holder.lockCanvas();

                        c.drawARGB(255, 127, 127, 127);
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
                for (final DashboardElement i : elements)
                {
                    i.setTargetValue();
                }
                updateLock.notifyAll();
            }
        }

        public void drawIndicators(final Canvas c)
        {
            isDirty = false;
            boolean indicatorDirty = false;
            for (final DashboardElement i : elements)
            {
                indicatorDirty |= i.updateAnimation();
                i.renderFrame(c);
                isDirty |= indicatorDirty;
            }
        }
    }

    /** Pass touch events to the MT controller if the gestureScanner doesn't like it */
    @Override
    public boolean onTouchEvent(final MotionEvent event)
    {
        if (gestureScanner.onTouchEvent(event))
        {
            return true;
        }
        else
        {
            return multiTouchController.onTouchEvent(event);
        }
    }

    @Override
    public DashboardElement getDraggableObjectAtPoint(final PointInfo touchPoint)
    {
        final float px = touchPoint.getX();
        final float py = touchPoint.getY();
        return getElementAtCoOrds(px, py);

    }

    private DashboardElement getElementAtCoOrds(final float px, final float py)
    {
        DashboardElement found = null;
        for (final DashboardElement i : elements)
        {
            if (i.contains(px, py))
            {
                found = i;
            }
        }
        return found;
    }

    @Override
    public void getPositionAndScale(final DashboardElement e, final PositionAndScale objPosAndScaleOut)
    {
        objPosAndScaleOut.set(e.getCentreX(), e.getCentreY(), true, e.getScale(), false, e.getScale(), e.getScale(), false, 0);
    }

    @Override
    public boolean setPositionAndScale(final DashboardElement obj, final PositionAndScale newObjPosAndScale, final PointInfo touchPoint)
    {
        return obj.setPos(newObjPosAndScale);
    }

    @Override
    public void selectObject(final DashboardElement obj, final PointInfo touchPoint)
    {
        selectedPosition = touchPoint;
        if (obj != null)
        {
            if (editedItem == null)
            {
                editedItem = obj;
                editedItem.editStart();
            }
        }
        else
        {
            if (editedItem != null)
            {
                editedItem.editFinished();
                editedItem = null;
            }
        }
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh)
    {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        for (final DashboardElement i : elements)
        {
            i.scaleToParent(w, h);
        }

    }
}
