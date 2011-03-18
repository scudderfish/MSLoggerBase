package uk.org.smithfamily.msdisp.parser;

import java.util.Stack;

import uk.org.smithfamily.msdisp.parser.StackEntry.entType;

public class ParserStack
{
    Exception                 e;
    private Stack<StackEntry> stack = new Stack<StackEntry>();

    public ParserStack(ExprError e2)
    {
       e = e2;
    }

    public int topI()
    {
        return stack.peek().i;
    }

    public double topD()
    {
        return stack.peek().d;
    }

    public String topS()
    {
        return stack.peek().s;
    }

    void push(double d)
    {
        stack.push(new StackEntry(d));
    }

    void push(int i)
    {
        stack.push(new StackEntry(i));
    }

    void push(String s)
    {
        stack.push(new StackEntry(s));
    }

    String popS()
    {
        StackEntry top = stack.pop();
        if (top.t != entType.strV)
        {
            e = new Exception("Top of stack is not a string :" + top.t);
        }
        return top.s;
    }

    int popI()
    {
        StackEntry top = stack.pop();
        switch (top.t)
        {
        case intV:
            return top.i;

        case dblV:
            return (int) top.d;

        default:
            e = new Exception("Top of stack is not an int :" + top.t);
            return 0;
        }
    }

    double popD()
    {
        StackEntry top = stack.pop();
        switch (top.t)
        {
        case intV:
            return top.i;

        case dblV:
            return top.d;

        default:
            e = new Exception("Top of stack is not a double :" + top.t);
            return 0;
        }
    }

    void toDouble(StackEntry x)
    {
        if (x.t == entType.dblV)
            return;

        if(x.t == entType.intV)
        {
            x.t=entType.dblV;
            x.d=x.i;
        }
        e = new Exception("Can't convert string to double");
    }

    void apply(UnaryFunc f)
    {
        StackEntry top = stack.pop();
        toDouble(top);
        stack.push(new StackEntry(f.apply(top.d)));
    }
    void apply(BinaryFunc f)
    {
        StackEntry y = stack.pop();
        StackEntry x = stack.pop();
        toDouble(x);
        toDouble(y);
        stack.push(new StackEntry(f.apply(x.d,y.d)));
    }

    public boolean topIs(entType strv)
    {
    return stack.peek().getType() == strv; 
    }

    public int getSize()
    {
        return stack.size();
    }
}
