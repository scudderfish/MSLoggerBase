package uk.org.smithfamily.mslogger;

import android.content.Context;

import uk.org.smithfamily.mslogger.activity.SplashActivity;

public enum MissionControl {
    INSTANCE;

    public static String[] requiredPermissions;

    public boolean haveWritableSAFRoot(Context context) {
        return false;
    }

    public boolean hasPermissions(SplashActivity splashActivity) {
        
        return false;
    }
}
