package uk.org.smithfamily.msdisp.parser;

public class StackEntry
{
    public enum entType
    {
        intV, dblV, strV
    };

    entType t;
    double  d;
    int     i;
    String  s;

    public StackEntry(int i)
    {
        this.i = i;
        t = entType.intV;
    }

    public StackEntry(double d)
    {
        this.d = d;
        t = entType.dblV;
    }

    public StackEntry(String s)
    {
        this.s = s;
        t = entType.strV;
    }

    public double AsDouble()
    {
        return d;
    }
    public  entType getType()
    {
        return t;
    }

    @Override
    public String toString()
    {
        return "StackEntry [t=" + t + ", d=" + d + ", i=" + i + ", s=" + s + "]";
    }
}
