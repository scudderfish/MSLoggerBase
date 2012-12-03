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
    private float scaleY;
    private float scaleX;

    /**
     * 
     * @param c
     * @param i
     * @param parent
     */
    public DashboardElement(final Context c, final Indicator i, final DashboardView parent)
    {
        this.context = c;
        this.indicator = i;
        this.parent = parent;

        checkPainterMatchesIndicator();
        scaleToParent(parent.getWidth(), parent.getHeight());
    }

    /**
     * Scale the dashboard element according to its parent (A dashboard view)
     * 
     * @param w The width of the parent
     * @param h The height of the parent
     */
    public void scaleToParent(final int w, final int h)
    {
        if ((w == 0) || (h == 0))
        {
            return;
        }
        Location l = indicator.getLocation();

        final float left = (float) (l.getLeft(w));
        float right = (float) (l.getRight(w));
        final float top = (float) (l.getTop(h));
        float bottom = (float) (l.getBottom(h));

        // Work out where the middle is as that is what MTC really wants to deal in
        final float centreX = (float) ((right + left) / 2.0);
        final float centreY = (float) ((top + bottom) / 2.0);
        painter.setPos(left, top, right, bottom, centreX, centreY, 1, 1, 0);
        this.parentW = w;
        this.parentH = h;

        originalWidth = painter.getWidth();
        originalHeight = painter.getHeight();

        right = left + originalWidth;
        bottom = top + originalHeight;
        l = new Location(left / w, top / h, right / w, bottom / h);
        indicator.setLocation(l);
    }

    /**
     * Create a new painter if none already exists or when the display type of the indicator changed
     */
    public void checkPainterMatchesIndicator()
    {
        if ((painter == null) || (painter.getType() != indicator.getDisplayType()))
        {
            createPainter();
        }
    }

    /**
     * Create a Painter object for the dashboard element
     */
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
        case HISTOGRAM:
            r = new Histogram(parent, indicator, context);
            break;
        default:
            r = new Gauge(parent, indicator, context);
            break;
        }
        painter = r;
    }

    /**
     * @return The Indicator object of the dashboard element
     */
    public Indicator getIndicator()
    {
        return indicator;
    }

    /**
     * @return The Painter object of the dashboard element
     */
    public Painter getPainter()
    {
        checkPainterMatchesIndicator();
        return painter;
    }

    /**
     * 
     */
    public void setTargetValue()
    {
        painter.setTargetValue(indicator.getValue());
    }

    /**
     * 
     * @return
     */
    public boolean updateAnimation()
    {
        return painter.updateAnimation();
    }

    /**
     * 
     * @param c
     */
    public void renderFrame(final Canvas c)
    {
        painter.renderFrame(c);
    }

    /**
     * Set the position and scale of an image in screen coordinates
     * 
     * @param newImgPosAndScale
     * @return
     */
    public boolean setPos(final PositionAndScale newImgPosAndScale)
    {
        return setPos(newImgPosAndScale.getXOff(), newImgPosAndScale.getYOff(), newImgPosAndScale.getScale(), newImgPosAndScale.getScaleX(), newImgPosAndScale.getScaleY(), newImgPosAndScale.getAngle());
    }

    /**
     * Set the position and scale of an image in screen coordinates
     * 
     * @param centreX
     * @param centreY
     * @param scaleX
     * @param scaleY
     * @param angle
     * @return
     */
    private boolean setPos(float centreX, float centreY, final float scale, float scaleX, float scaleY, final float angle)
    {
        Location l = indicator.getLocation();
        final float width = originalWidth;
        final float height = originalHeight;
        if (painter.isIsotropic())
        {
            scaleX = scale;
            scaleY = scale;
        }
        float ws = (width / 2) * scaleX;
        float hs = (height / 2) * scaleY;

        // Try to ensure we don't vanish into a pixel
        ws = Math.max(ws, parentW / 20f);
        hs = Math.max(hs, parentH / 20f);

        float left = centreX - ws;
        float top = centreY - hs;
        float right = centreX + ws;
        float bottom = centreY + hs;

        if (left < 0)
        {
            centreX -= left;
            right -= left;
            left = 0;
        }

        if (top < 0)
        {
            centreY -= top;
            bottom -= top;
            top = 0;
        }
        if (right > parentW)
        {
            centreX -= (right - parentW);
            left -= (right - parentW);
            right = parentW;
        }
        if (bottom > parentH)
        {
            centreY -= (bottom - parentH);
            top -= (bottom - parentH);
            bottom = parentH;
        }

        this.scale = (scaleX + scaleY) / 2.0f;
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        painter.setPos(left, top, right, bottom, centreX, centreY, scaleX, scaleY, angle);
        l = new Location(left / parentW, top / parentH, right / parentW, bottom / parentH);
        indicator.setLocation(l);
        return true;
    }

    /**
     * 
     * @param scrnX
     * @param scrnY
     * @return
     */
    public boolean contains(final float scrnX, final float scrnY)
    {
        final Location l = indicator.getLocation();

        return ((scrnX >= l.getLeft(parentW)) && (scrnX <= l.getRight(parentW)) && (scrnY >= l.getTop(parentH)) && (scrnY <= l.getBottom(parentH)));
    }

    /**
     * @return
     */
    public float getCentreY()
    {
        final Location l = indicator.getLocation();

        return l.getCentreY(parentH);
    }

    /**
     * @return
     */
    public float getCentreX()
    {
        final Location l = indicator.getLocation();

        return l.getCentreX(parentW);
    }

    /**
     * @return
     */
    public float getScale()
    {
        return scale;
    }

    /**
     * 
     */
    public void editStart()
    {
        // TODO Auto-generated method stub

    }

    /**
     * 
     */
    public void editFinished()
    {
        // TODO Auto-generated method stub

    }

    public double getAngle()
    {
        return indicator.getOffsetAngle();
    }

    public float getScaleX()
    {
        return scaleX;
    }

    public float getScaleY()
    {
        return scaleY;
    }
}
