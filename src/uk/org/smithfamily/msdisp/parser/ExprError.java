package uk.org.smithfamily.msdisp.parser;

public class ExprError extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 8002030497709681453L;
    String                    file;
    int                       lineNumber;
    String                    tok;
    String                    str;
    String                    title;
    boolean                   found;

    public ExprError()
    {
        file = null;
        lineNumber = 0;
        found = false;
        tok = "";
        str = "";
        title = "";
    }

    void setFile(String f)
    {
        file = f;
    }

    void setTitle(String t)
    {
        title = t;
    }

    void setLine(int l)
    {
        lineNumber = l;
    }

    void reset(char c)
    {
        tok = "" + c;
    }

    void reset(String s)
    {
        tok = s;
    }

    String errorString()
    {
        return str;
    }

    char append(char c)
    {
        tok += c;
        return c;
    }

    int backup()
    {
        int len = tok.length();
        //tok = tok.substring(0, len - 2);
        return len;
    }

    void display()
    {
        System.out.println(str);
    }

    void Error(String Msg) throws ExprError
    {
        found = true;
        if (lineNumber == 0)
            str = "Error: " + Msg + "'" + tok + "'";
        else
            str = file + ":" + lineNumber + ":" + Msg + " " + tok;
        ;
        throw this;
    }

    @Override
    public String toString()
    {
        return "ExprError [file=" + file + ", lineNumber=" + lineNumber + ", tok=" + tok + ", str=" + str + ", title=" + title
                + ", found=" + found + "]";
    }
}
