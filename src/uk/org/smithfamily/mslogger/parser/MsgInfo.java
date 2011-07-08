package uk.org.smithfamily.mslogger.parser;

import uk.org.smithfamily.mslogger.parser.log.DebugLogManager;
import android.util.Log;

public class MsgInfo
{

    public static final int mWarning = 1;
    public static final int mInfo = 2;
    public static final int mAlert = 0;
    public static int mError;
    public static int mExit;
    public int lineNo;
    public int ssize;
    public int level;
    public String fileName;

    public MsgInfo(String fileName, int depth)
    {
        
    }

    public void send(int mwarning2, String string)
    {

        DebugLogManager.getInstance().log(string);
        
    }

}
