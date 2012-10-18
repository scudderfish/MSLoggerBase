package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.CurveEditor;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 *
 */
public class EditCurveDialog extends Dialog implements android.view.View.OnClickListener
{
    private CurveHelper curveHelper;
    private CurveEditor curveEditor;
    
    /**
     * 
     * @param context
     * @param curve
     */
    public EditCurveDialog(Context context, CurveEditor curveEditor)
    {
        super(context);

        this.curveHelper = new CurveHelper(context, curveEditor, true);
        this.curveEditor = curveEditor;
    }
    
    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.editcurve);
        
        setTitle("Edit " + curveEditor.getLabel());
        
        LinearLayout ll = (LinearLayout) findViewById(R.id.mainCurve);
        ll.addView(curveHelper.getLayout(), 0);
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonClose = (Button) findViewById(R.id.close);
        buttonClose.setOnClickListener(this);
        
        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    
    /**
     * Burn the change to the ECU
     */
    private void burnToECU()
    {
        curveHelper.writeChangesToEcu();
        
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
        else if (which == R.id.close)
        {
            cancel();
        }
    }
}
