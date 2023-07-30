package uk.org.smithfamily.mslogger.dashboards;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import org.metalev.multitouch.controller.MultiTouchController;
import org.metalev.multitouch.controller.MultiTouchController.MultiTouchObjectCanvas;
import org.metalev.multitouch.controller.MultiTouchController.PointInfo;
import org.metalev.multitouch.controller.MultiTouchController.PositionAndScale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import uk.org.smithfamily.mslogger.DataManager;
import uk.org.smithfamily.mslogger.dialog.EditIndicatorDialog;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Location;

/**
 *
 */
public class DashboardView extends SurfaceView implements Observer, SurfaceHolder.Callback, MultiTouchObjectCanvas<DashboardElement>
{
    private final int position;
    private final Context context;
    private final List<DashboardElement> elements;
    private DashboardThread thread = null;
    private final DashboardViewPager parentPager;
    private final MultiTouchController<DashboardElement> multiTouchController;
    private DashboardElement editedItem;
    private final GestureDetector gestureScanner;
    private final Dashboard dashboard;

    /**
     *
     */
    class GestureProcessor extends GestureDetector.SimpleOnGestureListener
    {

        @Override
        public boolean onDoubleTap(final MotionEvent e)
        {
            final float px = e.getX();
            final float py = e.getY();
            final DashboardElement element = getElementAtCoOrds(px, py);

            // Double tap on an empty section of the dashboard, we're going to trigger the creation of a new element
            if (element == null)
            {
                addNewElement(px, py);
            }
            // Double tap on an existing indicator on the dash, we're going to trigger the edit dialog for the element
            else
            {
                editElement(element);
            }

            return super.onDoubleTap(e);
        }

    }
    public DashboardView(final Context context) {
        super(context);
        this.context=context;
        parentPager = null;
        dashboard = null;
        position=0;
        elements= Collections.emptyList();
        multiTouchController = new MultiTouchController<>(this);
        gestureScanner = new GestureDetector(context, new GestureProcessor());
    }

    public DashboardView(final Context context, final int position, final DashboardViewPager parent, final Dashboard dashboard)
    {
        super(context);
        this.context = context;
        this.position = position;
        this.parentPager = parent;
        this.dashboard = dashboard;
        elements = new ArrayList<>();
        getHolder().addCallback(this);

        // DataManager will ping when the ECU has finished a cycle
        DataManager.getInstance().addObserver(this);

        multiTouchController = new MultiTouchController<>(this);
        gestureScanner = new GestureDetector(context, new GestureProcessor());
    }

    /**
     * Edit an element from the DashboardView
     * 
     * @param element DashboardElement to edit
     */
    public void editElement(final DashboardElement element)
    {
        final Indicator indicator = element.getIndicator();

        final EditIndicatorDialog dialog = new EditIndicatorDialog(context, indicator);
        dialog.show();
        dialog.setDialogResult((indicator1, toRemove) -> {
            // User asked to delete this indicator from Remove button on the edit indicator dialog
            if (toRemove)
            {
                synchronized (elements)
                {
                    elements.remove(element);
                }

                boolean landscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

                dashboard.remove(indicator1, landscape);
            }
            else
            {
                element.checkPainterMatchesIndicator();
                element.scaleToParent(getWidth(), getHeight());
            }
            // Let the dash know it's dirty
            if (thread != null)
            {
                thread.invalidate();
            }
        });
    }

    /**
     * Add a new element to the DashboardView
     * 
     * @param px X where to add the element
     * @param py Y where to add the element
     */
    public void addNewElement(final float px, final float py)
    {
        // Create new Indicator
        final Indicator indicator = new Indicator();

        final DashboardView dv = this;

        // Open dialog and bind dialog result event
        final EditIndicatorDialog dialog = new EditIndicatorDialog(context, indicator);
        dialog.show();
        // Back from the dialog, lets add the element
        dialog.setDialogResult((indicator1, toRemove) -> {
            // Set position and scale for the element
            double left = px / dv.getWidth();
            double top = py / dv.getHeight();
            double right = left + 0.3; // 30% of the screen for the width
            double bottom = top + 0.3; // 30% of the screen for the height

            // Would go outside of screen, going to move left and right some
            if (right > 1.0)
            {
                final double diff = right - 1.0;

                right = 1.0;
                left -= diff;
            }
            // Would go outside of screen, going to move top and bottom some
            if (bottom > 1.0)
            {
                final double diff = bottom - 1.0;

                bottom = 1.0;
                top -= diff;
            }

            // Set location for the new indicator
            indicator1.setLocation(new Location(left, top, right, bottom));

            boolean landscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

            // Add indicator to current dashboard
            dashboard.add(indicator1, landscape);

            // Create dashboard element
            final DashboardElement element = new DashboardElement(context, indicator1, dv);

            // Add dashboard element to dashboard view
            synchronized (elements)
            {
                elements.add(element);
            }

            // Let the dash know it's dirty
            if (thread != null)
            {
                thread.invalidate();
            }
        });
    }

    @Override
    public void surfaceChanged(@NonNull final SurfaceHolder holder, final int format, final int width, final int height)
    {

    }

    @Override
    public void surfaceCreated(@NonNull final SurfaceHolder holder)
    {
        thread = new DashboardThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(@NonNull final SurfaceHolder holder)
    {
        if (thread != null)
        {
            boolean retry = true;
            thread.setRunning(false);
            while (retry)
            {
                try
                {
                    thread.interrupt();
                    thread.invalidate();
                    thread.join();
                    retry = false;
                }
                catch (final InterruptedException e)
                {
                    // we will try it again and again...
                }
            }
            thread = null;
        }
    }

    public void setDashboard(final Dashboard d)
    {
        synchronized (elements)
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
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec)
    {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public void update(final Observable observable, final Object data)
    {
        // Delegate the update
        if (thread != null)
        {
            thread.update(observable, data);
        }
    }

    /**
     *
     */
    class DashboardThread extends Thread implements Observer
    {
        private volatile boolean running = false;
        private final SurfaceHolder holder;
        private final Object updateLock = new Object();
        private volatile boolean isDirty;
        private long lastUpdate = System.currentTimeMillis();

        public DashboardThread(final SurfaceHolder holder, final DashboardView parent)
        {
            this.holder = holder;
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
                // Only bother updating if this is the displayed page
                while (running && isDirty && (parentPager.getCurrentItem() == position))
                {
                    c = null;
                    try
                    {
                        if (running)
                        {
                            c = holder.lockCanvas();
                            if ((c != null) && running)
                            {
                                // Clear the background
                                c.drawARGB(255, 0, 0, 0);
                                drawIndicators(c);
                            }
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
                    int MAX_FPS = 50;
                    int DELAY_PER_FRAME = 1000 / MAX_FPS;
                    if (delay < DELAY_PER_FRAME)
                    {
                        // We're running faster than the desired framerate, so throttle back
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
                        if (running)
                        {
                            updateLock.wait();
                        }
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
            synchronized (elements)
            {
                for (final DashboardElement i : elements)
                {
                    i.setTargetValue();
                }
            }
            invalidate();
        }

        public void invalidate()
        {
            synchronized (updateLock)
            {
                isDirty = true;
                updateLock.notifyAll();
            }

        }

        public void drawIndicators(final Canvas c)
        {
            isDirty = false;
            boolean indicatorDirty = false;
            synchronized (elements)
            {
                for (final DashboardElement i : elements)
                {
                    indicatorDirty |= i.updateAnimation();
                    i.renderFrame(c);
                    isDirty |= indicatorDirty;
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
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
        synchronized (elements)
        {
            for (final DashboardElement i : elements)
            {
                if (i.contains(px, py))
                {
                    found = i;
                }
            }
            if (found != null)
            {
                // This little bit of trickery pushes the touched element to the end of the list,
                // which in turn makes it last to be drawn, so it gets pulled to the front.
                elements.remove(found);
                elements.add(found);
            }
        }
        return found;
    }

    @Override
    public void getPositionAndScale(final DashboardElement e, final PositionAndScale objPosAndScaleOut)
    {
        final boolean isotropic = e.getPainter().isIsotropic();
        final float centreX = e.getCentreX();
        final float centreY = e.getCentreY();
        final float scale = e.getScale();
        final float scaleX = e.getScaleX();
        final float scaleY = e.getScaleY();
        final float angle = (float) e.getAngle();
        objPosAndScaleOut.set(centreX, centreY, true, scale, !isotropic, scaleX, scaleY, true, angle);
    }

    @Override
    public boolean setPositionAndScale(final DashboardElement obj, final PositionAndScale newObjPosAndScale, final PointInfo touchPoint)
    {
        final boolean b = obj.setPos(newObjPosAndScale);
        if (b)
        {
            thread.invalidate();
        }
        return b;
    }

    @Override
    public void selectObject(final DashboardElement obj, final PointInfo touchPoint)
    {
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

    /**
     * 
     * 
     * @param w Width
     * @param h Height
     * @param oldw Old width
     * @param oldh Old height
     */
    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        synchronized (elements)
        {
            for (final DashboardElement i : elements)
            {
                i.scaleToParent(w, h);
            }
        }

    }
}
