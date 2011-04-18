package uk.org.smithfamily.msdisp.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression
{
    private Symbol symbol;
    private String shellExpression;

    public Expression(Symbol s)
    {
        this.symbol = s;

        if (!"---".equals(symbol.exprText()))
        {
            String source = s.exprText();
            source = replaceBinary(source);
            source = intToBool(source);
            
            source = s._name+ " = ("+source+")";
            
            shellExpression = source;
        }
    }


    public String getShellExpression()
    {
        return shellExpression;
    }

    private static String replaceBinary(String source)
    {
        Pattern p = Pattern.compile("(.*)0b([01]*)(.*)");

        Matcher m = p.matcher(source);
        StringBuffer sb = new StringBuffer();
        if (m.find())
        {
            sb.append(m.group(1)).append(getInt(m.group(2))).append(m.group(3));
        }
        else
        {
            return source;
        }
        String result = sb.toString();

        m = p.matcher(result);
        if (m.find())
            return replaceBinary(result);
        return result;
    }

    private static String getInt(String group)
    {
        String result = "" + Integer.parseInt(group, 2);
        return result;
    }

    private static String intToBool(String source)
    {
        Pattern p = Pattern.compile("(\\(.*\\))(.*\\?.*)");

        Matcher m = p.matcher(source);
        StringBuffer sb = new StringBuffer();
        if (m.find())
        {
            sb.append("( ").append(m.group(1)).append(" != 0 ) ").append(m.group(2));
        }
        else
        {
            return source;
        }
        String result = sb.toString();

        m = p.matcher(result);

        return result;

    }

}
