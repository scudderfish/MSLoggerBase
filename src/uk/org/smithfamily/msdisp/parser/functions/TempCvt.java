package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import uk.org.smithfamily.msdisp.parser.ParserStack;

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
        double temp = MsDatabase.getInstance().tempFromDb(t);
        s.push(temp);
    }

}
