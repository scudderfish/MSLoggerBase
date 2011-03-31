package uk.org.smithfamily.msdisp.parser;

import java.util.ArrayList;
import java.util.List;

import bsh.Interpreter;

public class Expression
{
    private Interpreter interpreter = new Interpreter();
    private List<Double> _och   = null;
    static boolean       inEval = false;
    private List<String> functionDefinitions = new ArrayList<String>();
    private List<String> functions = new ArrayList<String>();
    void setOutputBuffer(List<Double> _userVar)
    {
        _och = _userVar;
    }

    void addExpr(String name, String expr, String file, int lineNo)
    {
        String processedExpression = processExpression(expr);
        String functionName = "func"+name+"()";
        processedExpression=functionName+"{"+processedExpression+"};";
        functions.add(functionName+";");
        functionDefinitions.add(processedExpression);

    }

    private String processExpression(String expr)
    {
        String retval = expr;
    
        return retval;
    }

    void recalc()
    {
        if (_och == null)
            return;

        if (!inEval)
        {
            inEval = true;
/*            try
            {
                _code.Evaluate(_och);
            }
            catch (ExprError e)
            {
                e.display();
            }
*/            inEval = false;
        }
    }

    public void addExpr(Symbol s)
    {
        System.out.println(s.toString());
    }

}
