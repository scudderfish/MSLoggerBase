package uk.org.smithfamily.mslogger.dashboards;

import org.metalev.multitouch.controller.MultiTouchController.PositionAndScale;

import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Location;
import uk.org.smithfamily.mslogger.widgets.renderers.*;
import android.content.Context;
import android.graphics.Canvas;

/**
 * I couldn't decide if an Indicator should own it's Painter or if the Painter should own the Indicator. Ultimately neither owns the other, so this
 * has both.
 * 
 * @author dgs
 * 
 */
public class DashboardElement
{
    private final Indicator indicator;
    private Painter painter;
    private final DashboardView parent;
    private final Context context;
    private float left;
    private float top;
    private float right;
    private float bottom;
    private int parentH;
    private int parentW;

    public DashboardElement(final Context c, final Indicator i, final DashboardView parent)
    {
        this.context = c;
        this.indicator = i;
        this.parent = parent;
        checkPainterMatchesIndicator();
    }

    public void scaleToParent(final int w, final int h)
    {
        final Location l = indicator.getLocation();

        left = (float) (l.getLeft() * w);
        right = (float) (l.getRight() * w);
        top = (float) (l.getTop() * h);
        bottom = (float) (l.getBottom() * h);

        final float centreX = (float) ((right + left) / 2.0);
        final float centreY = (float) ((top + bottom) / 2.0);
        painter.setPos(left, top, right, bottom, centreX, centreY, 1, 1, 0);
        this.parentW = w;
        this.parentH = h;
    }

    private void checkPainterMatchesIndicator()
    {
        if ((painter == null) || (painter.getType() != indicator.getDisplayType()))
        {
            createPainter();
        }
    }

    private void createPainter()
    {
        final Painter r;
        switch (indicator.getDisplayType())
        {
        case GAUGE:
            r = new Gauge(parent, indicator, context);
            break;
        case BAR:
            r = new BarGraph(parent, indicator, context);
            break;
        case NUMERIC:
            r = new NumericIndicator(parent, indicator, context);
            break;
        default:
            r = new Gauge(parent, indicator, context);
            break;
        }
        painter = r;
    }

    public Painter getPainter()
    {
        checkPainterMatchesIndicator();
        return painter;
    }

    public void setTargetValue()
    {
        painter.setTargetValue(indicator.getValue());
    }

    public boolean updateAnimation()
    {
        return painter.updateAnimation();
    }

    public void renderFrame(final Canvas c)
    {
        painter.renderFrame(c);
    }

    /** Set the position and scale of an image in screen coordinates */
    public boolean setPos(final PositionAndScale newImgPosAndScale)
    {
        return setPos(newImgPosAndScale.getXOff(), newImgPosAndScale.getYOff(), newImgPosAndScale.getScale(), newImgPosAndScale.getScale(), newImgPosAndScale.getAngle());
    }

    /** Set the position and scale of an image in screen coordinates */
    private boolean setPos(final float centerX, final float centerY, final float scaleX, final float scaleY, final float angle)
    {
        final float width = getWidth();
        final float height = getHeight();
        final float ws = (width / 2) * scaleX;
        final float hs = (height / 2) * scaleY;
        final float newleft = centerX - ws, newtop = centerY - hs, newright = centerX + ws, newbottom = centerY + hs;
        this.left = newleft;
        this.top = newtop;
        this.right = newright;
        this.bottom = newbottom;

        painter.setPos(newleft, newtop, newright, newbottom, centerX, centerY, scaleX, scaleY, angle);
        final Location l = new Location(left / parentW, top / parentH, right / parentW, bottom / parentH);
        indicator.setLocation(l);
        return true;
    }

    private float getWidth()
    {
        return right - left;
    }

    private float getHeight()
    {
        return bottom - top;
    }

    public boolean contains(final float scrnX, final float scrnY)
    {
        return ((scrnX >= left) && (scrnX <= right) && (scrnY >= top) && (scrnY <= bottom));
    }

    public float getCentreY()
    {
        return (float) ((left + right) / 2.0);
    }

    public float getCentreX()
    {
        return (float) ((top + bottom) / 2.0);
    }

}
