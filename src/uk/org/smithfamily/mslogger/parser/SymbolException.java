package uk.org.smithfamily.mslogger.parser;

public class SymbolException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 7564030539522283690L;
    private String symbol;

    public SymbolException(String msg, String ss)
    {
        super(msg);
        this.symbol = ss;
    }

    public Object getSymbol()
    {
        return symbol;
    }

}
