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
    private float parentH;
    private float parentW;
    private float originalWidth;
    private float originalHeight;
    private float scale;

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

        final float left = (float) (l.getLeft(w));
        final float right = (float) (l.getRight(w));
        final float top = (float) (l.getTop(h));
        final float bottom = (float) (l.getBottom(h));

        final float centreX = (float) ((right + left) / 2.0);
        final float centreY = (float) ((top + bottom) / 2.0);
        painter.setPos(left, top, right, bottom, centreX, centreY, 1, 1, 0);
        this.parentW = w;
        this.parentH = h;

        originalWidth = painter.getWidth();
        originalHeight = painter.getHeight();
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
        Location l = indicator.getLocation();
        final float width = originalWidth;
        final float height = originalHeight;
        float ws = (width / 2) * scaleX;
        float hs = (height / 2) * scaleY;
        ws = Math.max(ws, parentW / 20f);
        hs = Math.max(hs, parentH / 20f);
        final float newleft = centerX - ws, newtop = centerY - hs, newright = centerX + ws, newbottom = centerY + hs;
        final float left = newleft;
        final float top = newtop;
        final float right = newright;
        final float bottom = newbottom;
        this.scale = (scaleX + scaleY) / 2.0f;

        if ((newleft < 0) || (newtop < 0) || (newright > parentW) || (newbottom > parentH))
        {
            return false;
        }
        painter.setPos(newleft, newtop, newright, newbottom, centerX, centerY, scaleX, scaleY, angle);
        l = new Location(left / parentW, top / parentH, right / parentW, bottom / parentH);
        indicator.setLocation(l);
        return true;
    }

    public boolean contains(final float scrnX, final float scrnY)
    {
        final Location l = indicator.getLocation();

        return ((scrnX >= l.getLeft(parentW)) && (scrnX <= l.getRight(parentW)) && (scrnY >= l.getTop(parentH)) && (scrnY <= l.getBottom(parentH)));
    }

    public float getCentreY()
    {
        final Location l = indicator.getLocation();

        return l.getCentreY(parentH);
    }

    public float getCentreX()
    {
        final Location l = indicator.getLocation();

        return l.getCentreX(parentW);
    }

    public float getScale()
    {
        return scale;
    }

    public void editStart()
    {
        // TODO Auto-generated method stub

    }

    public void editFinished()
    {
        // TODO Auto-generated method stub

    }
}
