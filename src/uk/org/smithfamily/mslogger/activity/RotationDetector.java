package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.widgets.MSGauge;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * 
 * 
 */
class RotationDetector extends GestureDetector.SimpleOnGestureListener
{
    private final MSLoggerActivity msLoggerActivity;
    private MSGauge gauge;
    private float   dragStartDeg;

    /**
     * 
     * @param msLoggerActivity
     * @param gauge
     */
    public RotationDetector(MSLoggerActivity msLoggerActivity, MSGauge gauge)
    {
        this.msLoggerActivity = msLoggerActivity;
        this.gauge = gauge;
    }

    /**
     * 
     * @param x
     * @param y
     * @return
     */
    private float positionToAngle(float x, float y)
    {
        float radius = PointF.length((x - 0.5f), (y - 0.5f));
        if (radius < 0.1f || radius > 0.5f)
        { // ignore center and out of bounds events
            return Float.NaN;
        }
        else
        {
            return (float) Math.toDegrees(Math.atan2(x - 0.5f, y - 0.5f));
        }
    }

    /**
     * 
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        return false;
    }

    /**
     * 
     * @param e
     */
    @Override
    public boolean onDoubleTap(MotionEvent e)
    {
        EditGaugeDialog dialog = new EditGaugeDialog(this.msLoggerActivity, gauge);
        dialog.show();

        return true;
    }

    /**
     * 
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {

        if (!this.msLoggerActivity.scrolling)
        {
            float x = e1.getX() / ((float) gauge.getWidth());
            float y = e1.getY() / ((float) gauge.getHeight());

            dragStartDeg = positionToAngle(x, y);

            this.msLoggerActivity.scrolling = !Float.isNaN(dragStartDeg);
            return this.msLoggerActivity.scrolling;
        }
        float currentDeg = positionToAngle(e2.getX() / gauge.getWidth(), e2.getY() / gauge.getHeight());

        gauge.setOffsetAngle(dragStartDeg - currentDeg);
        gauge.invalidate();

        this.msLoggerActivity.scrolling = true;
        return true;

    }

}