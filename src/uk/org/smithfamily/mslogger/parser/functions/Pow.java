package uk.org.smithfamily.mslogger.parser.functions;


public class Pow extends BinaryFunc
{

    public double apply(double x,double y)
    {
        return Math.pow(x, y);
    }

}
