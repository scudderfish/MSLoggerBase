package uk.org.smithfamily.mslogger.activity;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import uk.org.smithfamily.mslogger.R;

/**
 * Dialog to inform the user about the need to allow requested permissions.
 */
public class PermissionsInformationDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage(R.string.permissions_information)
                .setPositiveButton(R.string.ok, (dialog, id) ->System.exit(0));
        return builder.create();
    }
}
