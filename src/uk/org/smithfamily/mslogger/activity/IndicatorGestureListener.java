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
    private Indicator              indicator;
    private int                    indicatorIndex;

    /**
     * Constructor
     * 
     * @param msLoggerActivity
     * @param indicator
     */
    public IndicatorGestureListener(MSLoggerActivity msLoggerActivity, Indicator indicator, int indicatorIndex)
    {
        this.msLoggerActivity = msLoggerActivity;
        this.indicator = indicator;
        this.indicatorIndex = indicatorIndex;
    }

    /**
     * Event triggered when double tapping on a gauge
     * 
     * @param e
     */
    @Override
    public boolean onDoubleTap(MotionEvent e)
    {
        EditGaugeDialog dialog = new EditGaugeDialog(this.msLoggerActivity, indicator, indicatorIndex, msLoggerActivity);
        dialog.show();

        return true;
    }
}
