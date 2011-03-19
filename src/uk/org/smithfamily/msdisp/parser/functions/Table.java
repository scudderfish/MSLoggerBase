package uk.org.smithfamily.msdisp.parser.functions;

import java.util.*;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import uk.org.smithfamily.msdisp.parser.ParserStack;

public class Table extends Function
{
    static Map<String,List<Integer> > tables = new HashMap<String,List<Integer> >();

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
    
        List<Integer> table = tables.get(s2);
        if(table == null)
        {
            table = new ArrayList<Integer>();
            MsDatabase.getInstance().readTable(s2, table);
            tables.put(s2, table);
        }
        s.push(table.get(i1));
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
