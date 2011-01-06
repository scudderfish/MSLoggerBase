package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.UnaryFunc;

public class Sin implements UnaryFunc
{

    public double apply(double d)
    {
        return Math.sin(d);
    }

}
