package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.UnaryFunc;

public class Log10 implements UnaryFunc
{

    public double apply(double d)
    {
        return Math.log10(d);
    }

}
