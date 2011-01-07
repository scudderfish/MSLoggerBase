package uk.org.smithfamily.msdisp.parser.ve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vex
{
    private static final String   VE_TABLE            = "VE Table";

    private static final String   VE_TABLE_LOAD_RANGE = "VE Table Load Range";

    private static final String   VE_TABLE_RPM_RANGE  = "VE Table RPM Range";

    private static final String   TRANSLATE           = "Translate";

    private static final String   SCALE               = "Scale";

    private static final String   PAGE                = "Page";

    private static final String   TIME                = "Time: ";

    private static final String   DATE                = "Date: ";

    private static final String   USER_COMMENT        = "UserComment: ";

    private static final String   USER_REV            = "UserRev:";

    private Map<Integer, VeTable> pages               = new HashMap<Integer, VeTable>();

    public enum errorStatus
    {
        noError, openError, readError, writeError, closeError, nonVexError, missingBracketError, missingLeftParenError, missingRightParenError, tableSizeError, rowIndexError, missingEqError
    };

    File           vexFile;

    double         userRev;
    String         userComment;
    String         userDate;
    String         userTime;

    private String loadType;

    double userRev()
    {
        return userRev;
    }

    void userRev(double ur)
    {
        userRev = ur;
    }

    String userComment()
    {
        return userComment;
    }

    void userComment(String uc)
    {
        userComment = uc;
    }

    String date()
    {
        return userDate;
    }

    void date(String d)
    {
        userDate = d;
    }

    String time()
    {
        return userTime;
    }

    void time(String t)
    {
        userTime = t;
    }

    errorStatus read(String fileName)
    {
        BufferedReader buf = null;
        try
        {
            buf = new BufferedReader(new FileReader(fileName));

            String lineBuffer = buf.readLine();
            if (lineBuffer == null)
            {
                return errorStatus.readError;
            }
            if (!lineBuffer.startsWith("EVEME 1.0"))
                return errorStatus.nonVexError;

            Integer nR = 0;
            Integer nL = 0;
            ArrayList<Double> r = null;
            ArrayList<Double> l = null;
            ArrayList<Double> v = null;

            VeTable page = null;
            while ((lineBuffer = buf.readLine()) != null)
            {
                if (lineBuffer.startsWith(USER_REV))
                {
                    userRev = Double.valueOf(lineBuffer.substring(USER_REV.length()));
                }
                if (lineBuffer.startsWith(USER_COMMENT))
                    ;
                {
                    userComment = lineBuffer.substring(USER_COMMENT.length());
                }
                if (lineBuffer.startsWith(DATE))
                {
                    date(lineBuffer.substring(DATE.length()));
                }
                if (lineBuffer.startsWith(TIME))
                {
                    time(lineBuffer.substring(TIME.length()));
                }
                if (lineBuffer.startsWith(PAGE))
                {
                    int pageNum = Integer.parseInt(lineBuffer.substring(TIME.length()));
                    page = newPage(pageNum);
                }
                if (lineBuffer.startsWith(SCALE) && page != null)
                {
                    page.setScale(Double.parseDouble(lineBuffer.substring(SCALE.length())));
                }
                if (lineBuffer.startsWith(TRANSLATE) && page != null)
                {
                    page.setTranslate(Double.parseDouble(lineBuffer.substring(TRANSLATE.length())));
                }
                if (lineBuffer.startsWith(VE_TABLE_RPM_RANGE))
                {
                    nR = bracketedNumber(lineBuffer.substring(VE_TABLE_RPM_RANGE.length()));
                    if (nR == null)
                        return errorStatus.missingBracketError;
                    r = new ArrayList<Double>(nR);
                    errorStatus s = readTable(buf, r, nR, 1);
                    if (s != errorStatus.noError)
                        return s;
                }
                if (lineBuffer.startsWith(VE_TABLE_LOAD_RANGE))
                {
                    int left = lineBuffer.indexOf('(');
                    if (left < 0)
                        return errorStatus.missingLeftParenError;
                    int right = lineBuffer.indexOf(')', left);
                    if (right < 0)
                        return errorStatus.missingRightParenError;
                    loadType = lineBuffer.substring(VE_TABLE_LOAD_RANGE.length(), left - 1);
                    nL = bracketedNumber(lineBuffer.substring(right + 1));
                    if (nL == null)
                        return errorStatus.missingBracketError;
                    l = new ArrayList<Double>(nL);
                    errorStatus s = readTable(buf, l, nL, 1);
                    if (s != errorStatus.noError)
                        return s;
                }
                if (lineBuffer.startsWith(VE_TABLE))
                {
                    if (nR == 0 || nL == 0)
                        return errorStatus.tableSizeError;
                    lineBuffer = buf.readLine();

                    v = new ArrayList<Double>(nR * nL);
                    errorStatus s = readTable(buf, v, nL, nR);
                    if (s != errorStatus.noError)
                        return s;
                }

                if (r != null && l != null && v != null && r.size() > 0 && l.size() > 0 && v.size() > 0)
                {
                    if (page == null)
                        page = newPage(0);
                    page.loadType = loadType;
                    page.setSize(nR, nL, r, l, v);
                    page = null;
                    r = l = v = null;
                }

            }
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (buf != null)
                try
                {
                    buf.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return errorStatus.noError;
    }

    private errorStatus readTable(BufferedReader buf,ArrayList<Double> results, Integer rows, int cols) throws IOException
    {
        errorStatus state = errorStatus.noError;
        
        for (int r = 0; r < rows; r++) 
        {
            String line = buf.readLine();
            if (line == null)
                return errorStatus.readError;
            
            Integer rVal = bracketedNumber(line);
            if (rVal == null || rVal != r) return errorStatus.rowIndexError; // Row index in file is not in order.

            int eq = line.indexOf('=');
            if(eq < 0) return errorStatus.missingEqError;
            
            String numberLine = line.substring(eq + 1);
            String[] numbers = numberLine.split("\\s,");
            int c = 0;
            for(String number : numbers)
            {
                if(number == null || number.trim().equals(""))
                    continue;
                results.add(r*cols+c,Double.valueOf(number));
                c++;
            }
         }
        
        
        
        return state;

    }

    private Integer bracketedNumber(String s)
    {
        Integer result = null;
        int left = s.indexOf('[');
        int right = s.indexOf(']');
        if(left < 0 || right < 0) return null;
        String num = s.substring(left+1, right-1);
        try
        {
            result = Integer.parseInt(num);
        } catch (NumberFormatException e)
        {
            result = null;
        }
       
        return result;
    }

    private VeTable newPage(int pageNum)
    {
        VeTable page = pages.get(pageNum);
        if (page == null)
        {
            page = new VeTable();
            pages.put(pageNum, page);
        }
        return page;
    }

}
