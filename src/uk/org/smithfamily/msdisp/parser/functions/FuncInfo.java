package uk.org.smithfamily.msdisp.parser.functions;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.msdisp.parser.ParserStack;

public class FuncInfo
{
    public enum argType
    {
        nonArg, strArg, intArg, dblArg
    };

    public static final int notaFunc = -1;
    private static int      nextId   = 0;
    String                  name;
    Function                  fn;
    int                     id;
    int                     nArgs;

    List<argType>           args     = new ArrayList<argType>();

    FuncInfo()
    {
        fn = null;
        id = notaFunc;
        nArgs = 0;
    }

    FuncInfo(String pname, Function pfn, int pnArgs)
    {
        fn = pfn;
        id = nextId++;
        nArgs = pnArgs;
        name = pname;
    }

    void addArg(int iArg, argType at)
    {
        args.add(iArg, at);
    }

    void eval(ParserStack s) throws FunctionException
    {
        if (fn != null)
            fn.evaluate(s);
        else
            throw new FunctionException(this);
    }

}
