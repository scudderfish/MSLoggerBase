package uk.org.smithfamily.mslogger.log;

import android.util.Log;

public class DebugLog {
    public static void d(String msg) {
        DebugLogManager.INSTANCE.log(msg, Log.DEBUG);
    }
}
