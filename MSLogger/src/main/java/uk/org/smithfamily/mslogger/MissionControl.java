package uk.org.smithfamily.mslogger;

import static uk.org.smithfamily.mslogger.MSLoggerApplication.ROOT_URI;

import android.content.Context;
import android.content.UriPermission;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.documentfile.provider.DocumentFile;

import java.util.List;

import uk.org.smithfamily.mslogger.log.DebugLog;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public enum MissionControl {
    INSTANCE;

    public static String[] requiredPermissions;

    public boolean CannotWriteSAFRoot(Context context) {
        System.out.println("Checking for WriteableSAFRoot");
        String appUri = ApplicationSettings.INSTANCE.getPref(ROOT_URI);
        if(appUri == null) {
            System.out.println("No appUri defined");
            return true;
        }
        Uri testUri = Uri.parse(appUri);
        if(testUri == null){
            System.out.println("Could not parse appUri");
            return true;
        }
        if(DocumentFile.isDocumentUri(context,testUri)){
            System.out.println("Uri is a document, needs to be a directory");
            return true;
        }
        List<UriPermission> list = MSLoggerApplication.getInstance().getContentResolver().getPersistedUriPermissions();
        for(UriPermission p : list) {
            final Uri uri = p.getUri();
            final boolean writePermission = p.isWritePermission();
            if(uri.equals(testUri) && !writePermission) {
                System.out.println("No write permission in UriPermission for testUri");
                return true;
            }
        }
        DocumentFile testFile = DocumentFile.fromTreeUri(context,testUri);
        if(testFile == null){
            System.out.println("Uri could not be parsed to a directory");
            return true;
        }
        if(!testFile.canRead()) {
            System.out.println("Can't read directory");
            return true;
        }
        if(! testFile.canWrite()){
            System.out.println("Can't write to directory");
            return true;
        }
        System.out.println("We have a writable SAF root");
        return false;
    }
    public boolean hasPermissions(Context c) {
        return hasPermissions(c,requiredPermissions);
    }
    /**
     * Convenience method to check whether multiple permissions are already granted.
     */
    private boolean hasPermissions(Context context, String... permissions) {
        if(CannotWriteSAFRoot(context)) {
            DebugLog.d("!haveWritableSAFRoot");
            return false;
        }
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                final int selfPermission = ActivityCompat.checkSelfPermission(context, permission);
                if (selfPermission != PackageManager.PERMISSION_GRANTED) {
                    DebugLog.d(String.format("Missing permission %s",permission));
                    return false;
                }
            }
        }
        FileManager.INSTANCE.permissionsGranted();
        return true;
    }

}
