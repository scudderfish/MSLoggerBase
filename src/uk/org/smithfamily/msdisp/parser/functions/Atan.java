package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.UnaryFunc;

public class Atan implements UnaryFunc
{

    public double apply(double d)
    {
        return Math.atan(d);
    }

}
