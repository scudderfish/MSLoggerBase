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
    private double left, top, width, height;

    public Location(double left, double top, double width, double height)
    {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public double getLeft()
    {
        return left;
    }

    public double getTop()
    {
        return top;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    @Override
    public String toString()
    {
        return String.format("Location [left=%s, top=%s, width=%s, height=%s]", left, top, width, height);
    }
}
