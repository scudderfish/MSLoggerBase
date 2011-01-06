package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.UnaryFunc;

public class Floor implements UnaryFunc
{

    public double apply(double d)
    {
        return Math.floor(d);
    }

}
