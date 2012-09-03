package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.CurveEditor;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
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
    private Megasquirt ecu;
    
    /**
     * 
     * @param context
     * @param curve
     */
    public EditCurveDialog(Context context, CurveEditor curveEditor)
    {
        super(context);

        this.curveHelper = new CurveHelper(context, curveEditor);
        this.ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
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
        Constant xBinsConstant = ecu.getConstantByName(curveEditor.getxBinsName());
        Constant yBinsConstant = ecu.getConstantByName(curveEditor.getyBinsName());
        
        if (xBinsConstant.isModified())
        {
            System.out.println("Constant \"" + xBinsConstant.getName() + "\" was modified, need to write change to ECU");
            
            xBinsConstant.setModified(false);
        }
        
        if (yBinsConstant.isModified())
        {
            System.out.println("Constant \"" + yBinsConstant.getName() + "\" was modified, need to write change to ECU");
            
            yBinsConstant.setModified(false);
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
