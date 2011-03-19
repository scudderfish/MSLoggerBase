package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.ParserStack;

public class Table extends Function
{

    @Override
    public int argCnt()
    {
        return 2;
    }

    @Override
    public void evaluate(ParserStack s)
    {
        int   i1;
        String s2;
        s2=s.popS();
        i1=s.popI();
        
        /*
        tableMapIter tbl = tables.find(s2);
        if (tbl != tables.end()) 
        {
           s.push((*tbl).second[i1]);
        }
        else 
        {
           tables[s2] = xferFunc(s2);
           s.push(tables[s2][i1]);
        }
        */

    }

}
