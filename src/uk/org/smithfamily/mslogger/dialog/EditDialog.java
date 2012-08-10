package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.DialogField;
import uk.org.smithfamily.mslogger.ecuDef.DialogPanel;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class EditDialog extends Dialog implements android.view.View.OnClickListener
{
    private MSDialog dialog;
    private LinearLayout content;
    private Megasquirt ecu;
    
    /**
     * 
     * @param context
     * @param dialog
     */
    public EditDialog(Context context, MSDialog dialog)
    {
        super(context);
        
        this.ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        this.dialog = dialog;
    }
    
    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.editdialog);
        
        content = (LinearLayout) findViewById(R.id.content);
        
        setTitle(dialog.getLabel());
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(this);
        
        drawDialogFields(dialog);
    }
    
    /**
     * Recursive function that will draw fields and panels of a dialog.
     * It's called recursively until all fields and panels were drawn.
     * 
     * @param dialog
     */
    public void drawDialogFields(MSDialog dialog)
    {
        TableLayout tl = new TableLayout(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        
        content.addView(tl);
        
        TableRow tableRow;
        
        // For each dialog field, add a row in the table layout
        for (DialogField df : dialog.getFieldsList())
        {
            tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(lp);
            
            TextView label = new TextView(getContext());
            label.setText(df.getLabel());
            tableRow.addView(label);
            
            // For empty label or empty field name, we just insert an empty text view as second column of the row
            if (df.getLabel().equals("") || df.getName().equals("null"))
            {
                label = new TextView(getContext());
                tableRow.addView(label);
            }
            else 
            { 
                //Constant constant = ecu.getConstantByName(df.getName());
                
                EditText edit = new EditText(getContext());
                edit.setText(String.valueOf(ecu.getField(df.getName())));
                edit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                edit.setSingleLine(true);
                edit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                edit.setPadding(8, 5, 8, 5);
                
                // Field is ready only
                if (df.isDisplayOnly())
                {
                    edit.setEnabled(false);
                }
                
                tableRow.addView(edit);
            }
            
            tl.addView(tableRow);
        }        
        
        // For each dialog panel, add a layout to the dialog
        for (DialogPanel dp : dialog.getPanelsList())
        {
            MSDialog dialogPanel = ecu.getDialogByName(dp.getName());
            
            if (dialogPanel != null)
            {
              drawDialogFields(dialogPanel);
            }
            else
            {
              // Maybe it's a curve panel
              //
              // CurveEditor curvePanel = ecu.getCurveEditorByName(dp.getName());
              // if (curvePanel)
              // {
              // 
              // }
              //
            }
        }
    }
    
    /**
     * @param v
     */
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.burn)
        {
            
        }
        else if (which == R.id.cancel)
        {
            cancel();
        }
    }
}
