package uk.org.smithfamily.msdisp.parser;



public class Expression
{
    private Symbol symbol;
    private String shellExpression;
    
    public Expression(Symbol s)
    {
        this.symbol = s;

        if (symbol.isExpr() && !"---".equals(symbol.exprText()))
        {
            shellExpression = processExpression();
        }
    }

   
    private String processExpression()
    {
        String retval = symbol._expr;
    
        return retval;
    }

   
    public void addExpr(Symbol s)
    {
        
        if (s.isExpr() && !"---".equals(s.exprText()))
        {
//            _exprs.addExpr(s.varIndex(), s.exprText(), s.exprFile(), s.exprLine());

        }
        System.out.println(s.toString());
    }

    public String getShellExpression()
    {
        return shellExpression;
    }

}
