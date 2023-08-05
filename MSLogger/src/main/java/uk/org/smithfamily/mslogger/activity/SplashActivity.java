package uk.org.smithfamily.mslogger.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import uk.org.smithfamily.mslogger.FileManager;
import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.MissionControl;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.log.DebugLog;
import uk.org.smithfamily.mslogger.log.DebugLogManager;


/**
 * Splash screen activity which also performs runtime permissions check.
 */
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int DELAY = 2000;
    private static final int PERMISSION_REQUEST = 1;
    private static final String LOGTAG = SplashActivity.class.getSimpleName();
    private final ActivityResultLauncher<Intent> chooseRootLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> checkRuntimePermissions());
    boolean mPermissionsNeverAskAgainChecked;
    private Handler mHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(LOGTAG, "onCreate");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);


        checkRuntimePermissions();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        final boolean finishing = isFinishing();
        System.out.printf("DESTROY SplashActivity isFinishing %b%n", finishing);
        if (mHandler != null) {
            // The handler only gets used to delay the splash screen if we don't need to ask
            // for permissions.
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    int getDelay() {
        return DELAY;
    }

    /**
     * As is required on Android M (6.0) onwards, check we have required runtime permissions.
     */
    private void checkRuntimePermissions() {
        DebugLog.d("checkRuntimePermissions");

        if(MSLoggerApplication.getInstance().getCheckRoot()){
            return;
        }
        if (!MissionControl.INSTANCE.haveWritableSAFRoot(this)) {
            Intent newIntent = new Intent(this, ChooseRootFolderActivity.class);
            chooseRootLauncher.launch(newIntent);

        } else if (!MissionControl.INSTANCE.hasPermissions(this)) {
            DebugLog.d("doesn't have permissions");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.permissions_description)
                    .setPositiveButton(R.string.ok, (dialog, which) -> {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(this, MissionControl.requiredPermissions, PERMISSION_REQUEST);
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {


//            if (!FileManager.INSTANCE.haveImportedOldMSLoggerFiles()) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage(R.string.import_old_files)
//                        .setPositiveButton("Browse", ((dialog, i) -> {
//                            dialog.dismiss();
//                            openDirectory();
//                        }));
//                AlertDialog alert = builder.create();
//                alert.show();

            DebugLog.d("has permissions");

            MSLoggerApplication.setStartupActivityHasRun();
            mHandler = new Handler();
            mHandler.postDelayed(this::progressToDashboard, getDelay());

        }
    }

    /**
     * Handle the result of requesting the permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean denied = false;

        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                denied = true;
                String permission = permissions[i];
                if (!shouldShowRequestPermissionRationale(permission)) {
                    mPermissionsNeverAskAgainChecked = true;
                }
            }
        }

        if (denied) {
            DialogFragment dialog = new PermissionsInformationDialogFragment();
            dialog.show(getSupportFragmentManager(), "PermissionsInformationDialogFragment");
        } else {
            FileManager.INSTANCE.permissionsGranted();
            MSLoggerApplication.setStartupActivityHasRun();
            mHandler = new Handler();
            mHandler.postDelayed(this::progressToDashboard, getDelay());
        }
    }

    /**
     * Handle dismissal of the PermissionsInformationDialogFragment
     */
    @SuppressWarnings("unused")
    public void onPermissionsInformationDialogFragmentDismissed() {
        if (mPermissionsNeverAskAgainChecked) {
            openApplicationSettings();
        } else {
            checkRuntimePermissions();
        }
    }

    /**
     * Cause the OS to show the Android settings for the MSLogger application, so that
     * the user can go into Permissions and enable the required permissions.
     * This is what ends up happening if the user has previously denied the permissions
     * and ticked 'don't ask again' on them.
     */
    private void openApplicationSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
        // Having to use System.exit(0) rather than finish() to get around problem where application
        // needs complete restart for grant of file system access to take effect. Otherwise, when
        // coming back to application after permissions enabled, file IO still fails.
        // https://stackoverflow.com/questions/32947638/
        System.exit(0);
    }

    /**
     * Start the dashboard Activity if all permissions are good.
     */
    private void progressToDashboard() {
        DebugLogManager.INSTANCE.log("Going to Dashboard",Log.DEBUG);
        //Do we divert via file import?
        Intent newIntent = new Intent();
        newIntent.setClass(this, MSLoggerActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(newIntent);
        finish();
    }
    @Override
    public void finish(){
        System.out.println("In FINISH!");
        super.finish();
    }
}
