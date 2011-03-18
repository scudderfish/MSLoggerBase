package uk.org.smithfamily.msdisp.parser;

import uk.org.smithfamily.msdisp.parser.MemoryItem.op;

public class Parser
{
    ExprError e;
    Lexer     l;
    CodeGen   c;

    void Primitive()
    {
    }

    void ArithFactor()
    {
    }

    void ArithTerm()
    {
    }

    void ArithExpression()
    {
    }

    void Shift()
    {
    }

    void Relation()
    {
    }

    void BitFactor()
    {
    }

    void BitTerm()
    {
    }

    void LogFactor()
    {
    }

    void LogTerm()
    {
    }

    void Expression()
    {
    }

    void Assignment()
    {
    }

    public Parser(String expr, CodeGen cg, String file, int lineNo)
    {
        l = new Lexer(e);
        c = cg;
        e.setFile(file);
        e.setLine(lineNo);
        e.setTitle("MegaTune 'ini-file' Parsing Error");
        l.Init(expr);
        c.append();
        c.GenStr(op.DBGSop, expr);
    }

    void parse()
    {
    }

    void parse(int destAddr) throws ExprError
    {
    }
}
