package uk.org.smithfamily.mslogger.parser.functions;

import java.util.*;

public class Functions
{
    static Map<String, Function> funcMap = new HashMap<String, Function>();
    private static List<Function> functionList=new ArrayList<Function>();

    public static Function id(String name)
    {
        return funcMap.get(name);
    }
    public static Function fromId(int i)
    {
        return functionList.get(i);
    }
    
    static void add(String name,Function implementation,FuncInfo.argType[] argTypes)
    {
        implementation.setName(name);
        functionList.add(implementation);
        funcMap.put(name,implementation);
    }
/*
       addOne("asin",      ev_asin,      1, funcInfo::dblArg);
      addOne("atan",      ev_atan,      1, funcInfo::dblArg);
      addOne("atan2",     ev_atan2,     2, funcInfo::dblArg, funcInfo::dblArg);
      addOne("ceil",      ev_ceil,      1, funcInfo::dblArg);
//    addOne("const",     ev_const,     1, funcInfo::intArg);
      addOne("cos",       ev_cos,       1, funcInfo::dblArg);
      addOne("exp",       ev_exp,       1, funcInfo::dblArg);
      addOne("fabs",      ev_fabs,      1, funcInfo::dblArg);
      addOne("floor",     ev_floor,     1, funcInfo::dblArg);
      addOne("fmod",      ev_fmod,      2, funcInfo::dblArg, funcInfo::dblArg);
      addOne("itable",    ev_itable,    2, funcInfo::intArg, funcInfo::strArg);
      addOne("log",       ev_log,       1, funcInfo::dblArg);
      addOne("log10",     ev_log10,     1, funcInfo::dblArg);
      addOne("pow",       ev_pow,       2, funcInfo::dblArg, funcInfo::dblArg);
      addOne("sin",       ev_sin,       1, funcInfo::dblArg);
      addOne("sqrt",      ev_sqrt,      1, funcInfo::dblArg);
      addOne("table",     ev_table,     2, funcInfo::intArg, funcInfo::strArg);
      addOne("tan",       ev_tan,       1, funcInfo::dblArg);
      addOne("tempCvt",   ev_tempCvt,   1, funcInfo::dblArg);
      addOne("timeNow",   ev_timeNow,   0);
      addOne("vexInterp", ev_vexInterp, 4, funcInfo::intArg, funcInfo::intArg, funcInfo::intArg, funcInfo::strArg);

      
 */
    static
    {
        add("PI", new PI(),null);
        add("acos",new Acos(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("asin",new Asin(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("atan2",new Atan2(),new FuncInfo.argType[]{FuncInfo.argType.dblArg,FuncInfo.argType.dblArg});
        add("ceil",new Ceil(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("cos",new Cos(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("exp",new Exp(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("fabs",new Fabs(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("floor",new Floor(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("fmod",new Fmod(),new FuncInfo.argType[]{FuncInfo.argType.dblArg,FuncInfo.argType.dblArg});
        add("itable",new Itable(),new FuncInfo.argType[]{FuncInfo.argType.dblArg,FuncInfo.argType.strArg});
        add("log",new Log(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("log10",new Log10(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("pow",new Pow(),new FuncInfo.argType[]{FuncInfo.argType.dblArg,FuncInfo.argType.dblArg});
        add("sin",new Sin(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("sqrt",new Sqrt(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("table",new Table(),new FuncInfo.argType[]{FuncInfo.argType.dblArg,FuncInfo.argType.strArg});
        add("tan",new Tan(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("tempCvt",new TempCvt(),new FuncInfo.argType[]{FuncInfo.argType.dblArg});
        add("timeNow", new TimeNow(),null);
        add("vexInterp", new VexInterp(),new FuncInfo.argType[]{FuncInfo.argType.intArg,FuncInfo.argType.intArg,FuncInfo.argType.intArg,FuncInfo.argType.strArg});

    
    
    
    
    }
}
