package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.ParserStack;

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
