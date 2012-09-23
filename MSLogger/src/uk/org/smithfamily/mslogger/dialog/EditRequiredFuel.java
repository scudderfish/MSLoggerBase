package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditRequiredFuel extends Dialog implements android.view.View.OnClickListener
{
    private TextView engineDisplacement;
    private TextView numberOfCylinders;
    private TextView injectorFlow;
    private TextView airFuelRatio;
    
    private RadioGroup displacementUnits;
    private RadioGroup injectorFlowUnits;
    
    private OnReqFuelResult mDialogResult;
    
    public EditRequiredFuel(Context context)
    {
        super(context);
    }
    
    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.required_fuel_calculator);
     
        setTitle(R.string.required_fuel_calculator);
        
        engineDisplacement = (TextView) findViewById(R.id.engineDisplacement);
        numberOfCylinders = (TextView) findViewById(R.id.numberOfCylinders);
        injectorFlow = (TextView) findViewById(R.id.injectorFlow);
        airFuelRatio = (TextView) findViewById(R.id.airFuelRatio);
        
        displacementUnits = (RadioGroup) findViewById(R.id.displacementUnits);
        injectorFlowUnits = (RadioGroup) findViewById(R.id.injectorFlowUnits);
        
        Button calculate = (Button) findViewById(R.id.bt_ok);
        calculate.setOnClickListener(this);
        
        Button cancel = (Button) findViewById(R.id.bt_cancel);
        cancel.setOnClickListener(this);
    }
    
    /**
     * Check if a value is between the specified min/max value. Display an alert dialog if it's not.
     * 
     * @param value Current value
     * @param minBound Minimum allowed
     * @param maxBound Maximum allowed
     */
    private boolean showRequiredFuelOutOfBounds(double value, double minBound, double maxBound, String name)
    {
        if (value < minBound || value > maxBound)
        {            
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(name + " should be between " + minBound + " and " + maxBound + ". You currently have it set to " + value)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Out of bound required fuel parameter")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id){}
                    });
            
            AlertDialog alert = builder.create();
            alert.show();
            
            return true;
        }
        
        return false;
    }
    
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.bt_ok)
        {        
            double reqFuel = 0.0;
            double dReqFuel = 0.0;
    
            double dispUnits = displacementUnits.getCheckedRadioButtonId() == R.id.rbCID ? 1.0 : 16.38706;
            double flowUnits = injectorFlowUnits.getCheckedRadioButtonId() == R.id.rbLbHr ? 1.0 : 10.5;
    
            int errorsCount = 0;
            boolean error = false;
            
            // Engine displacement
            double cid = 0;
            try 
            {
                cid = Double.parseDouble(engineDisplacement.getText().toString());
            }
            catch (NumberFormatException e){}
            
            error = showRequiredFuelOutOfBounds(cid, 1, 25000, "Engine displacement");
            if (error)
            {
                errorsCount++;
            }
            cid = (int) (cid / dispUnits);
    
            // Injectors flow
            double injectorFlowValue = 0;
            try
            {
                injectorFlowValue = Double.parseDouble(injectorFlow.getText().toString());
            }
            catch (NumberFormatException e){}
            
            error = showRequiredFuelOutOfBounds(injectorFlowValue, 0, 2000, "Injectors flow");
            if (error)
            {
                errorsCount++;
            }
            
            injectorFlowValue = injectorFlowValue / flowUnits;
            
            // Air/Fuel ratio
            double afr = 0;
            try
            {
                afr = Double.parseDouble(airFuelRatio.getText().toString());
            }
            catch (NumberFormatException e){}
            
            error = showRequiredFuelOutOfBounds(injectorFlowValue, 0, 25, "Air/Fuel Ratio");
            if (error)
            {
                errorsCount++;
            }
            
            // Only close dialog if there was no error
            if (errorsCount == 0) 
            {
                Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
                
                int nCylinders = (int) Integer.parseInt(numberOfCylinders.getText().toString());
                int divider = ecu.getDivider();
                int nInjectors = ecu.getInjectorsCount();
                double injectorStaging = ecu.getInjectorStating() + 1;
                
                reqFuel = (36.0E6 * cid * 4.27793e-05) / (nCylinders * afr * injectorFlowValue) / 10.0;
                dReqFuel = reqFuel * (injectorStaging * divider) / nInjectors;
                
                // Populate this so the parent dialog can get that info
                mDialogResult.finish(ecu.roundDouble(reqFuel, 1), ecu.roundDouble(dReqFuel, 1));
                dismiss();
            }
        }
        else if (which == R.id.bt_cancel)
        {
            cancel();
        }
    }
    
    /**
     * 
     * @param dialogResult
     */
    public void setDialogResult(OnReqFuelResult dialogResult)
    {
        mDialogResult = dialogResult;
    }

    /**
     *
     */
    public interface OnReqFuelResult
    {
       void finish(double reqFuel, double dReqFuel);
    }
}
