package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.ParserStack;

public abstract class BinaryFunc extends Function
{
    @Override
    public int argCnt()
    {
        return 2;
    }
    @Override
    public void evaluate(ParserStack s)
    {
        double d2 = s.popD();
        double d1 = s.popD();
        double d = apply(d1,d2);
        s.push(d);
    }

    public abstract double apply(double x, double y);
}
