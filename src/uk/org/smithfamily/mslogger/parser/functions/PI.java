package uk.org.smithfamily.mslogger.parser.functions;

import uk.org.smithfamily.mslogger.parser.ParserStack;

public class PI extends Function
{

    @Override
    public int argCnt()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void evaluate(ParserStack s)
    {
        s.push(Math.PI);
    }

}
