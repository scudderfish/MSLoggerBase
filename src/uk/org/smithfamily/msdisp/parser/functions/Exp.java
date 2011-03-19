package uk.org.smithfamily.msdisp.parser.functions;


public class Exp extends UnaryFunc
{

    public double apply(double d)
    {
        return Math.exp(d);
    }

}
