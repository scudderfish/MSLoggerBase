package uk.org.smithfamily.msdisp.parser.functions;

import uk.org.smithfamily.msdisp.parser.ParserStack;

public abstract class Function
{
    public abstract int argCnt();
    
    public abstract void evaluate(ParserStack s);
}
