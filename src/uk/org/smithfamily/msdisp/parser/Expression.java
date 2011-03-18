package uk.org.smithfamily.msdisp.parser;

import java.util.List;

public class Expression
{

    private List<Double> _och   = null;
    private CodeGen      _code;
    static boolean       inEval = false;

    void setOutputBuffer(List<Double> _userVar)
    {
        _och = _userVar;
    }

    void addExpr(int destVar, String expr, String file, int lineNo)
    {
        try
        {
            // DiagPrint("Adding %3d %s\n", destVar, expr);
            _code.checkPoint();
            Parser p = new Parser(expr, _code, file, lineNo);
            p.parse(destVar);
        }
        catch (ExprError e)
        {
            _code.recover();
            e.display();
        }

    }

    void recalc()
    {
        if (_och == null)
            return;

        if (!inEval)
        {
            inEval = true;
            try
            {
                _code.Evaluate(_och);
            }
            catch (ExprError e)
            {
                e.display();
            }
            inEval = false;
        }
    }

}
