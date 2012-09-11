package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.Dialog;
import android.content.Context;
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
    }
    
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.bt_ok)
        {        
            double reqFuel    = 0.0;
    
            double dispUnits = displacementUnits.getCheckedRadioButtonId() == R.id.rbCID ? 1.0 : 16.38706;
            double flowUnits = injectorFlowUnits.getCheckedRadioButtonId() == R.id.rbLbHr ? 1.0 : 10.5;
    
            double cid = Double.parseDouble(engineDisplacement.getText().toString()); // TODO validate between 1 and 25000
            cid = (int) (cid / dispUnits);
    
            double injectorFlowValue = Double.parseDouble(injectorFlow.getText().toString()); // TODO validate between 0 and 2000
            injectorFlowValue = injectorFlowValue / flowUnits;
    
            double afr = Double.parseDouble(airFuelRatio.getText().toString()); // TODO validate betwen 0 and 25
    
            Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
            
            int nCylinders = 0;
            
            if (ecu.isConstantExists("nCylinders"))
            {
                nCylinders = (int) ecu.getField("nCylinders"); 
            }
            else 
            {
                nCylinders = (int) ecu.getField("nCylinders1");
            }
    
            reqFuel = (36.0E6 * cid * 4.27793e-05) / (nCylinders * afr * injectorFlowValue) / 10.0;
            
            System.out.println("test " + engineDisplacement.getText());
            System.out.println("test " + numberOfCylinders.getText());
            System.out.println("test " + injectorFlow.getText());
            System.out.println("test " + airFuelRatio.getText());
            System.out.println("test " + displacementUnits.getCheckedRadioButtonId());
            System.out.println("test " + injectorFlowUnits.getCheckedRadioButtonId());
            System.out.println("test " + reqFuel);
        }
    }
}
