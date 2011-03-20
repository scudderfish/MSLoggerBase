package uk.org.smithfamily.msdisp.parser;

import uk.org.smithfamily.msdisp.parser.Lexer.Token;
import uk.org.smithfamily.msdisp.parser.MemoryItem.op;
import uk.org.smithfamily.msdisp.parser.functions.Function;

public class Parser
{
    ExprError e =  new ExprError();
    Lexer     l;
    CodeGen   c;

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

    void parse() throws ExprError
    {
        l.GetToken();
        Assignment();
        if (l.CurrentTok != Token.TermTok)
            e.Error("Extra stuff after expression");
        c.Gen1Op(op.HALTop);
    }

    void parse(int destAddr) throws ExprError
    {
        l.GetToken();
        Expression();
        if (l.CurrentTok != Token.TermTok)
            e.Error("Extra stuff after expression");
        c.Gen2Op(MemoryItem.op.POPAop, destAddr);
        c.Gen1Op(op.HALTop);

    }

    void Assignment() throws ExprError
    {
        if (l.CurrentTok != Token.VariableTok)
            e.Error("Expected variable on left of assigment");
        int variable = l.CurrentVar;
        l.GetToken();
        if (l.CurrentTok != Token.EqualTok)
            e.Error("Expected assignment operator");
        l.GetToken();
        Expression();
        c.Gen2Op(op.POPAop, variable);
    }

    void Expression() throws ExprError
    {
        // ?: conditional
        LogTerm();
        if (l.CurrentTok == Token.CondTok)
        {
            l.GetToken(); // Past the '?'
            int patchC = c.GenCond();
            Expression();
            if (l.CurrentTok != Token.ColonTok)
                e.Error("Expected : in conditional operator");
            l.GetToken(); // Past the ':'
            int patchB = c.GenBranch();
            c.Patch(patchC);
            Expression();
            c.Patch(patchB);
        }
    }

    void ArithTerm() throws ExprError
    {
        ArithFactor();
        while (l.CurrentTok == Token.MultTok || l.CurrentTok == Token.DivTok || l.CurrentTok == Token.ModTok)
        {
            Token Operator = l.CurrentTok;
            l.GetToken(); // Scan past multiplicative operator
            ArithFactor();
            switch (Operator)
            {
            case MultTok:
                c.Gen1Op(op.MULTop);
                break;
            case DivTok:
                c.Gen1Op(op.DIVop);
                break;
            case ModTok:
                c.Gen1Op(op.MODop);
                break;
            default:
                e.Error("Invalid arithmetic multiplicative operator");
            }
        }
    }

    void Primitive() throws ExprError
    {
        Function TheFunction;
        int nArgs;

        Token Operator = Token.ErrorTok;
        if (l.CurrentTok == Token.LNotTok || l.CurrentTok == Token.MinusTok || l.CurrentTok == Token.BNotTok) {
           Operator = l.CurrentTok;
           l.GetToken();
        }

        switch (l.CurrentTok) {
           case VariableTok:
              c.Gen2Op(op.PUSHAop, l.CurrentVar);
              l.GetToken();
              break;

           case IntTok:
              c.Gen2Op(op.PUSHIop, l.CurrentInt);
              l.GetToken();
              break;

           case DblTok:
              c.Gen2Op(op.PUSHDop, l.CurrentDbl);
              l.GetToken();
              break;

           case StrTok:
              c.GenStr(op.PUSHSop, l.CurrentStr);
              l.GetToken();
              break;

           case ConstantTok:
              c.Gen2Op(op.PUSHYop, l.CurrentSym);
              l.GetToken();
              break;

           case FunctionTok:
           {
              TheFunction = l.CurrentFnc;
              nArgs       = l.fnArgCount;
              l.GetToken();                         // Scan past the function name

              for (int iArg = 0; iArg < nArgs; iArg++) {
                 if (iArg == 0      && l.CurrentTok != Token.LParenTok) e.Error("Expected left parenthesis after function");
                 l.GetToken();                       // Scan past left paren or comma
                 Expression();                       // Parse the expression
                 if (iArg < nArgs-1 && l.CurrentTok != Token.CommaTok ) e.Error("Expected comma after function argument");
              }

              if (nArgs > 0) {
                 if (l.CurrentTok != Token.RParenTok) e.Error("Expected right parenthesis");
                 l.GetToken();                          // Eat the right paren
              }

              c.Gen2Op(op.FUNCop, TheFunction);
           }
           break;

           default:
              e.Error("Invalid primitive");
              break;
        }

        if (Operator != Token.ErrorTok) {
           if (Operator == Token.MinusTok) c.Gen1Op(op.NEGop);
           if (Operator == Token.LNotTok ) c.Gen1Op(op.LNOTop);
           if (Operator == Token.BNotTok ) c.Gen1Op(op.BNOTop);
        }
    }

    void ArithFactor() throws ExprError
    {
        if (l.CurrentTok != Token.LParenTok)
        {
            Primitive();
        }
        else
        {
            l.GetToken(); // Scan past left paren
            Expression(); // Parse the expression
            if (l.CurrentTok != Token.RParenTok)
            {
                e.Error("Expected right parenthesis");
            }
            else
            {
                l.GetToken(); // Eat the right paren
            }
        }
    }

    void ArithExpression() throws ExprError
    {
        ArithTerm();
        while (l.CurrentTok == Token.PlusTok || l.CurrentTok == Token.MinusTok)
        {
            Token Operator = l.CurrentTok;
            l.GetToken(); // Scan past operator token
            ArithTerm();
            if (Operator == Token.PlusTok)
                c.Gen1Op(op.PLUSop);
            else
                c.Gen1Op(op.MINUSop);
        }

    }

    void Shift() throws ExprError
    {
        ArithExpression();
        while (l.CurrentTok == Token.LShiftTok || l.CurrentTok == Token.RShiftTok)
        {
            Token Operator = l.CurrentTok;
            l.GetToken();
            ArithExpression();
            if (Operator == Token.LShiftTok)
                c.Gen1Op(op.LSHFop);
            else
                c.Gen1Op(op.RSHFop);
        }

    }

    void Relation() throws ExprError
    {
        Shift();
        while (l.CurrentTok == Token.LtTok
            || l.CurrentTok == Token.LeTok
            || l.CurrentTok == Token.GtTok
            || l.CurrentTok == Token.GeTok
            || l.CurrentTok == Token.EqTok
            || l.CurrentTok == Token.NeTok) {
           Token Operator = l.CurrentTok;
           l.GetToken();
           Shift();
           switch(Operator) {
              case LtTok: c.Gen1Op(op.LTop); break;
              case LeTok: c.Gen1Op(op.LEop); break;
              case GtTok: c.Gen1Op(op.GTop); break;
              case GeTok: c.Gen1Op(op.GEop); break;
              case EqTok: c.Gen1Op(op.EQop); break;
              case NeTok: c.Gen1Op(op.NEop); break;
              default: e.Error("Invalid relational operator");
           }
        }
    }

    void BitFactor() throws ExprError
    {
        Relation();
        while (l.CurrentTok == Token.BAndTok || l.CurrentTok == Token.BXorTok) {
           Token Operator = l.CurrentTok;
           l.GetToken();
           Relation();
           if (Operator == Token.BAndTok) c.Gen1Op(op.BANDop);
           else                            c.Gen1Op(op.BXORop);
        }
    }

    void BitTerm() throws ExprError
    {
        BitFactor();
        while (l.CurrentTok == Token.BOrTok) {
           l.GetToken();
           BitFactor();
           c.Gen1Op(op.BORop);
        }
    }

    void LogFactor() throws ExprError
    {
        BitTerm();
        while (l.CurrentTok == Token.LAndTok) {
           l.GetToken();
           BitTerm();
           c.Gen1Op(op.LANDop); // Should be a short-circuit operator ???
        }
    }

    void LogTerm() throws ExprError
    {
        LogFactor();
        while (l.CurrentTok == Token.LOrTok) {
           l.GetToken();
           LogFactor();
           c.Gen1Op(op.LORop); // Should be a short-circuit operator ???
        }
    }

}
