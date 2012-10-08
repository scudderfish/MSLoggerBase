package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.TableEditor;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
    
    /**
     * 
     * @param context
     * @param tableEditor
     */
    public EditTableDialog(Context context, TableEditor tableEditor)
    {
        super(context);
        
        this.tableHelper = new TableHelper(context, tableEditor, true);
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
        tableHelper.writeChangesToEcu();
        dismiss();
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
