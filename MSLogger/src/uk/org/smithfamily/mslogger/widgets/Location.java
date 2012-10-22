package uk.org.smithfamily.mslogger.widgets;

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
public class Location
{
    private double left, top, right, bottom;

    public Location(double left, double top, double right, double bottom)
    {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
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

    @Override
    public String toString()
    {
        return String.format("Location [left=%s, top=%s, right=%s, bottom=%s]", left, top, right, bottom);
    }
}
