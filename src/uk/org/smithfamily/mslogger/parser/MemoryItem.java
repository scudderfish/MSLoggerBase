package uk.org.smithfamily.mslogger.parser;

import uk.org.smithfamily.mslogger.parser.functions.Function;

class MemoryItem
{
    public enum op
    {
        HALTop, DBGSop, LSHFop, RSHFop, PLUSop, MINUSop, MULTop, DIVop, MODop, NEGop, PUSHAop, PUSHIop, PUSHDop, PUSHSop, PUSHYop, POPAop, FUNCop, LTop, LEop, GTop, GEop, EQop, NEop, BANDop, BXORop, BORop, BNOTop, LNOTop, LANDop, LORop, BNEop, BRAop
    };

    public enum Type
    {
        OperatorType, IntegerType, DoubleType, StringType, SymbolType, FunctionType
    };

    private Symbol   symbol;
    private Type     type;
    private int      intVal;
    private double   doubleVal;
    private String   stringVal;
    private op       opVal;
    private Function function;

    @Override
    public String toString()
    {
        String str = "MemoryItem [type=" + type + ", value=";
        switch (type)
        {
        case IntegerType:
            str += intVal;
            break;

        case OperatorType:
            str += opVal.name();
            break;
        case DoubleType:
            str += doubleVal;
            break;
        case StringType:
            str += "\"" + stringVal + "\"";
            break;
        case SymbolType:
            str += symbol.toString();
            break;
        case FunctionType:
            str += function.getName();
            break;
        }
        return str + "]";
    }

    public MemoryItem(int i)
    {
        this.type = Type.IntegerType;
        this.intVal = i;
    }

    public MemoryItem(op o)
    {
        this.type = Type.OperatorType;
        this.opVal = o;
    }

    public MemoryItem(double theOperand)
    {
        this.type = Type.DoubleType;
        this.doubleVal = theOperand;
    }

    public MemoryItem(String s)
    {
        this.type = Type.StringType;
        stringVal = s;
    }

    public MemoryItem(Symbol s)
    {
        this.type = Type.SymbolType;
        symbol = s;
    }

    public MemoryItem(Function f)
    {
        this.type = Type.FunctionType;
        function = f;
    }

    public op getOpVal()
    {
        return opVal;
    }

    public String getStringVal()
    {

        return stringVal;
    }

    public int getIntVal()
    {
        return intVal;
    }

    public double getDoubleVal()
    {
        return doubleVal;
    }

    public Symbol getSymValue()
    {
        return symbol;
    }
}