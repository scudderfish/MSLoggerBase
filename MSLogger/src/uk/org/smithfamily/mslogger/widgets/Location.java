package uk.org.smithfamily.mslogger.widgets;

public class Location
{
    private double left, top, width, height;

    public Location(double d, double e, double f, double g)
    {
        super();
        this.left = d;
        this.top = e;
        this.width = f;
        this.height = g;
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
}
