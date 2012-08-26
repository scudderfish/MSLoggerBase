package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 *
 */
public class DialogHelper
{
    
    /**
     *
     * @param context
     * @param cell
     */
    public static void verifyOutOfBoundValue(Context context, Constant constant, final EditText cell)
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
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
