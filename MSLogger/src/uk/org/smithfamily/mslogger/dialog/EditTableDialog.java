package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.TableEditor;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;

/**
 *
 */
public class EditTableDialog extends Dialog implements android.view.View.OnClickListener
{    
    private TableHelper tableHelper;
    
    private Megasquirt ecu;
    
    /**
     * 
     * @param context
     * @param tableEditor
     */
    public EditTableDialog(Context context, TableEditor tableEditor)
    {
        super(context);
        
        this.tableHelper = new TableHelper(context, tableEditor, true);
        
        this.ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
    }
    
    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.edittable);
     
        setTitle("Edit " + tableHelper.getTableEditor().getLabel());
        
        HorizontalScrollView ll = (HorizontalScrollView) findViewById(R.id.horizontalView);
        ll.addView(tableHelper.getLayout());
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(this);
        
        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    
    /**
     * Burn the change to the ECU
     */
    private void burnToECU()
    {
        // Make sure table has been modified
        if (tableHelper.isModified()) 
        {
            TableEditor tableEditor = tableHelper.getTableEditor();
            
            DebugLogManager.INSTANCE.log("Table " + tableEditor.getName() + " was modified, need to write change to ECU", Log.DEBUG);
            
            // Get table constant
            Constant tableConstant = ecu.getConstantByName(tableEditor.getzBins());
            
            ecu.writeConstant(tableConstant);
            
            dismiss();
        }
    }
    
    /**
     * Triggered when one of the two bottoms button are clicked ("Burn" and "Cancel")
     * 
     * @param v The view that was clicked on
     */
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.burn)
        {
            burnToECU();
        }
        else if (which == R.id.cancel)
        {
            cancel();
        }
    }
}
