package uk.org.smithfamily.msdisp.parser.functions;


public class Floor extends UnaryFunc
{

    public double apply(double d)
    {
        return Math.floor(d);
    }

}
