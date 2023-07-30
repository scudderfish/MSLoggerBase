package uk.org.smithfamily.mslogger.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow.LayoutParams;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Constant;

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
                    .setPositiveButton("OK", (dialog, id) -> cell.requestFocus());
            
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    





    /**
     * Build a custom layout panel and return it
     * 
     * @param panelName The panel of the panel to build
     * 
     * @return The relative layout instance with the custom components inside
     */
    public static RelativeLayout getCustomPanel(Context context, String panelName)
    {
        if (panelName.equals("dataLogFieldSelector"))
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);       
            @SuppressLint("InflateParams") LinearLayout datalogFieldSelectorLayout = (LinearLayout) inflater.inflate(R.layout.sd_card_datalog_field_selector, null);
            
            // Wrap into relative layout
            RelativeLayout containerPanelLayout = new RelativeLayout(context);
            
            RelativeLayout.LayoutParams tlp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            containerPanelLayout.setLayoutParams(tlp);
            containerPanelLayout.addView(datalogFieldSelectorLayout);
            
            Button selectAll = datalogFieldSelectorLayout.findViewById(R.id.select_all);
            selectAll.setOnClickListener(v -> {

            });
            
            Button deselectAll = datalogFieldSelectorLayout.findViewById(R.id.deselect_all);
            deselectAll.setOnClickListener(v -> {

            });
            
            Button selectOne = datalogFieldSelectorLayout.findViewById(R.id.select_one);
            selectOne.setOnClickListener(v -> {

            });
            
            Button deselectOne = datalogFieldSelectorLayout.findViewById(R.id.deselect_one);
            deselectOne.setOnClickListener(v -> {

            });
            
            return containerPanelLayout;
        }
        
        return null;
    }
}
