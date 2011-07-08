package uk.org.smithfamily.mslogger.parser.functions;

import uk.org.smithfamily.mslogger.parser.ParserStack;

public abstract class Function
{
    private String name;

    public abstract int argCnt();
    
    public abstract void evaluate(ParserStack s);

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
}
