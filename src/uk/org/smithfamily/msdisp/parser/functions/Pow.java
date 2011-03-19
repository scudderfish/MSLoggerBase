package uk.org.smithfamily.msdisp.parser.functions;


public class Pow extends BinaryFunc
{

    public double apply(double x,double y)
    {
        return Math.pow(x, y);
    }

}
