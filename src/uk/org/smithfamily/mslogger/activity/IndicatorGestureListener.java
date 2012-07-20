package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.widgets.Indicator;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Class that is used for interaction with gauges cluster
 * 
 */
class IndicatorGestureListener extends GestureDetector.SimpleOnGestureListener
{
    private final MSLoggerActivity msLoggerActivity;
    private Indicator              gauge;

    /**
     * Constructor
     * 
     * @param msLoggerActivity
     * @param indicator
     */
    public IndicatorGestureListener(MSLoggerActivity msLoggerActivity, Indicator indicator)
    {
        this.msLoggerActivity = msLoggerActivity;
        this.gauge = indicator;
    }

    /**
     * Event triggered when double tapping on a gauge
     * 
     * @param e
     */
    @Override
    public boolean onDoubleTap(MotionEvent e)
    {
        EditGaugeDialog dialog = new EditGaugeDialog(this.msLoggerActivity, gauge, msLoggerActivity);
        dialog.show();

        return true;
    }
}
