package bsh.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import bsh.CallStack;
import bsh.Interpreter;

public class table
{
    static Map<String,List<Integer> > tables = new HashMap<String,List<Integer> >();

    
    public static int invoke(Interpreter env, CallStack callstack, int i1,String s2)
    {
        List<Integer> table = tables.get(s2);
        if(table == null)
        {
            table = new ArrayList<Integer>();
            MsDatabase.getInstance().readTable(s2, table);
            tables.put(s2, table);
        }
        return table.get(i1);
     }
}
