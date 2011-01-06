package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.BinaryFunc;

public class Atan2 implements BinaryFunc
{

    public double apply(double x,double y)
    {
        return Math.atan2(x,y);
    }

}
