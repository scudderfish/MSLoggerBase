package uk.org.smithfamily.msdisp.parser.functions;


public class Log10 extends UnaryFunc
{

    public double apply(double d)
    {
        return Math.log10(d);
    }

}
