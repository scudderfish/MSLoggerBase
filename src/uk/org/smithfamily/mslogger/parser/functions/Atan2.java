package uk.org.smithfamily.mslogger.parser.functions;


public class Atan2 extends BinaryFunc
{

    public double apply(double x,double y)
    {
        return Math.atan2(x,y);
    }

}
