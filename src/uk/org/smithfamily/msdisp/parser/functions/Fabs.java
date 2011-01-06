package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.UnaryFunc;

public class Fabs implements UnaryFunc
{

    public double apply(double d)
    {
        return Math.abs(d);
    }

}
