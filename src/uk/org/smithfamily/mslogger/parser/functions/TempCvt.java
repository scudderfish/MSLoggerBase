package uk.org.smithfamily.mslogger.parser.functions;

import uk.org.smithfamily.mslogger.parser.MsDatabase;
import uk.org.smithfamily.mslogger.parser.ParserStack;

public class TempCvt extends Function
{

    @Override
    public int argCnt()
    {
        return 1;
    }

    @Override
    public void evaluate(ParserStack s)
    {
        double t;
        t = s.popD();
        double temp = MsDatabase.INSTANCE.tempFromDb(t);
        s.push(temp);
    }

}
