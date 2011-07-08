package uk.org.smithfamily.mslogger.parser.functions;


public class Log10 extends UnaryFunc
{

    public double apply(double d)
    {
        return Math.log10(d);
    }

}
