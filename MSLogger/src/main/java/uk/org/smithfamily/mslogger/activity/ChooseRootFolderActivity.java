package uk.org.smithfamily.mslogger.activity;

import static android.provider.DocumentsContract.EXTRA_INITIAL_URI;

import static uk.org.smithfamily.mslogger.MSLoggerApplication.ROOT_URI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.R;

public class ChooseRootFolderActivity extends AppCompatActivity {
    private final ActivityResultLauncher<Intent> requestStoragePermLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::requestStoragePermResult);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MSLoggerApplication.getInstance().setCheckRoot(true);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_choose_root_folder);

        Button actionButton = findViewById(R.id.CRFButton);

        actionButton.setOnClickListener(v -> selectFolder());

    }

    @SuppressLint("InlinedApi")
    private void selectFolder() {
        Uri treeUri = DocumentsContract.buildTreeDocumentUri("com.android.externalstorage.documents", "primary:MSLogger");

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.putExtra("android.content.extra.SHOW_ADVANCED", true).putExtra(EXTRA_INITIAL_URI, treeUri);
        intent.addFlags(  Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                        | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
        requestStoragePermLauncher.launch(intent);

    }
    private void requestStoragePermResult(final ActivityResult result) {
        // Return from the SAF picker
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            // Get Uri from Storage Access Framework
            Uri treeUri = result.getData().getData();
            if (treeUri != null) testRootFolder(treeUri);
        }
    }

    private void testRootFolder(Uri treeUri) {
        DocumentFile root = DocumentFile.fromTreeUri(this, treeUri);
        boolean goodRoot = root != null;
        if(goodRoot) {
            goodRoot = root.exists();
            goodRoot &= root.isDirectory();
            goodRoot &= root.canWrite();
        }
        if(!goodRoot) {
            Toast.makeText(this, "Please try another directory, MSLogger cannot use that one", Toast.LENGTH_LONG).show();

        } else if (takePersistableUriPermission(treeUri,Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION,this))
        {
            //AppPrefs.INSTANCE.setString(ROOT_URI,treeUri.toString());
            Intent returnIntent = new Intent(this, SplashActivity.class);

            returnIntent.putExtra("rootURI",treeUri.toString());
            setResult(Activity.RESULT_OK,returnIntent);
            MSLoggerApplication.getInstance().setCheckRoot(false);
            startActivity(returnIntent);
        }else {
            Toast.makeText(this, "Please try another directory, MSLogger cannot use that one", Toast.LENGTH_LONG).show();
        }
    }
    private static boolean takePersistableUriPermission(@NonNull Uri uri, int modeFlags,
                                                        @NonNull Context context) {
        try {
            context.getContentResolver().takePersistableUriPermission(uri, modeFlags);
            return true;
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

}