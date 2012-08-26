package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ecuDef.Constant;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * Helper class used for dialogs
 */
public class DialogHelper
{
    
    /**
     * This function verify if the user entered value is in range for the specifing constant
     * Show an alert dialog if it's not with indication to the user on how to fix it
     *
     * @param context The context the function was called
     * @param constant The constant to look for min and max value
     * @param cell The cell the user was typing in
     */
    public static void verifyOutOfBoundValue(Context context, Constant constant, final EditText cell)
    {
        
        double currentValue = Double.parseDouble(cell.getText().toString());
        
        double minValue = Double.parseDouble(constant.getLow());
        double maxValue = Double.parseDouble(constant.getHigh());
        
        String message = "";
        
        if (currentValue < minValue)
        {
            message = "Constant " + constant.getName() + " value is lower then the minimum allowed (" + currentValue + " < " + minValue + ")";
        }
        
        if (currentValue > maxValue)
        {
            message = "Constant " + constant.getName() + " value is higher then the maximum allowed (" + currentValue + " > " + maxValue + ")";
        }
        
        if (!message.equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Value is out of bound")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            cell.requestFocus();
                        }
                    });
            
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
