package uk.org.smithfamily.mslogger.parser.functions;

import uk.org.smithfamily.mslogger.parser.ParserStack;

public abstract class UnaryFunc extends Function
{
    @Override
    public void evaluate(ParserStack s)
    {
        double d = s.popD();
        d = apply(d);
        s.push(d);
    }

    @Override
    public int argCnt()
    {
        return 1;
    }

    public abstract double apply(double d);
}
