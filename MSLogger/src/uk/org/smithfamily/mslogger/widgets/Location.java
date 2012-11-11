package uk.org.smithfamily.mslogger.widgets;

import uk.org.smithfamily.mslogger.utils.Copyable;

/**
 * Represents a display independentish location for an Indicator.
 * 
 * Specified as double values for left,top, width, height as expressed as ratios.
 * 
 * For positive values they are relative to the shortest side of the display
 * 
 * For negative values they are relative to the longest side of the display
 * 
 * @author dgs
 * 
 */
public class Location implements Copyable<Location>
{
    private final double left, top, right, bottom;

    public Location(final double left, final double top, final double right, final double bottom)
    {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public double getHeight()
    {
        return bottom - top;
    }

    public double getWidth()
    {
        return right - left;
    }

    public double getLeft()
    {
        return left;
    }

    public double getTop()
    {
        return top;
    }

    public double getRight()
    {
        return right;
    }

    public double getBottom()
    {
        return bottom;
    }

    public double getLeft(final float screenWidth)
    {
        return left * screenWidth;
    }

    public double getRight(final float screenWidth)
    {
        return right * screenWidth;
    }

    public double getTop(final float screenHeight)
    {
        return top * screenHeight;
    }

    public double getBottom(final float screenHeight)
    {
        return bottom * screenHeight;
    }

    public double getWidth(final float screenWidth)
    {
        return getWidth() * screenWidth;
    }

    public double getHeight(final float screenHeight)
    {
        return getHeight() * screenHeight;
    }

    @Override
    public String toString()
    {
        return String.format("Location [left=%s, top=%s, right=%s, bottom=%s]", left, top, right, bottom);
    }

    @Override
    public Location copy()
    {
        final Location copy = createForCopy();
        copyTo(copy);
        return copy;
    }

    @Override
    public Location createForCopy()
    {
        return new Location(left, top, right, bottom);
    }

    @Override
    public void copyTo(final Location dest)
    {
    }

    public float getCentreY(final float parentH)
    {
        return (float) (parentH * ((top + bottom) / 2.0));
    }

    public float getCentreX(final float parentW)
    {
        return (float) (parentW * ((left + right) / 2.0));
    }
}
