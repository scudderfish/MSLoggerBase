package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.BinaryFunc;

public class Fmod implements BinaryFunc
{

    public double apply(double x,double y)
    {
        return x % y;
    }

}
