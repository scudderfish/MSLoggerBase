package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.widgets.BarMeter;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
import uk.org.smithfamily.mslogger.widgets.Histogram;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.MSGauge;
import uk.org.smithfamily.mslogger.widgets.NumericIndicator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Dialog to change various gauge settings
 */
public class EditGaugeDialog extends Dialog implements android.view.View.OnClickListener
{
    private MSLoggerActivity    mainActivity;
    private GaugeDetails        gd;
    private Indicator           gauge;
    private String              indicatorType;
    
    private Spinner             typeSpinner;
    private Spinner             orientationSpinner;

    /**
     * 
     * @param context
     * @param gauge
     */
    public EditGaugeDialog(Context context, Indicator gauge, MSLoggerActivity mainActivity)
    {
        super(context);
        this.gauge = gauge;
        this.gd = gauge.getDetails();
        
        this.indicatorType = gauge.getType();
        this.mainActivity = mainActivity;
    }

    /**
     * Called when the dialog is first created to initialise some value
     * 
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editgauge);

        setTitle("Edit Gauge Properties");

        setValue(R.id.editName, gd.getName());
        setValue(R.id.editChannel, gd.getChannel());
        setValue(R.id.editTitle, gd.getTitle());
        setValue(R.id.editUnits, gd.getUnits());
        setValue(R.id.editHi, Double.toString(gd.getMax()));
        setValue(R.id.editLo, Double.toString(gd.getMin()));
        setValue(R.id.editHiD, Double.toString(gd.getHiD()));
        setValue(R.id.editHiW, Double.toString(gd.getHiW()));
        setValue(R.id.editLoD, Double.toString(gd.getLoD()));
        setValue(R.id.editLoW, Double.toString(gd.getLoW()));
        setValue(R.id.editVD, Integer.toString(gd.getVd()));
        setValue(R.id.editLD, Integer.toString(gd.getLd()));
        setValue(R.id.editoffsetAngle, Double.toString(gd.getOffsetAngle()));
        
        orientationSpinner = (Spinner) findViewById(R.id.indicatorOrientation);
        
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> orientationAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatororientation, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        orientationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        // Apply the adapter to the spinner
        orientationSpinner.setAdapter(orientationAdapter);
        
        // Select currently selected orientation        
        for (int i = 0; i < Indicator.Orientation.values().length; i++)
        {
            if (orientationAdapter.getItem(i).toString().toLowerCase().equals(gd.getOrientation().toLowerCase()))
            {
                orientationSpinner.setSelection(i);
                break;
            }
        }
        
        typeSpinner = (Spinner) findViewById(R.id.indicatorType);
        
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatortypes, android.R.layout.simple_spinner_item);
       
        // Specify the layout to use when the list of choices appears
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        // Apply the adapter to the spinner
        typeSpinner.setAdapter(typeAdapter);
        
        // Select current gauge type
        typeSpinner.setSelection(typeAdapter.getPosition(indicatorType));
        
        typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                displayOrientationSpinnerIfNeeded();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
        
        Button buttonOK = (Button) findViewById(R.id.editOK);
        Button buttonReset = (Button) findViewById(R.id.editReset);
        Button buttonCancel = (Button) findViewById(R.id.editCancel);

        buttonOK.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }
    
    /**
     *
     */    
    private void displayOrientationSpinnerIfNeeded()
    {
        TableRow orientationRow = (TableRow) findViewById(R.id.indicatorOrientationRow);

        // Display orientation spinner for bar graph
        if (typeSpinner.getSelectedItem().toString().equals(getContext().getString(R.string.bargraph)))
        {                        
            orientationRow.setVisibility(View.VISIBLE);
        }
        else
        {
            orientationRow.setVisibility(View.GONE);
        }
    }

    /**
     * Helper function to set the value of a view based on an ID
     * 
     * @param id        ID of the view
     * @param value     Value to set it to
     */
    private void setValue(int id, String value)
    {
        View v = findViewById(id);
        if (v instanceof TextView)
        {
            ((TextView) v).setText(value);
        }
    }

    /**
     * Save gauge details
     */
    public void saveDetails()
    {
        Spinner gaugeType = (Spinner) findViewById(R.id.indicatorType);
        
        String selectedIndicatorType = gaugeType.getSelectedItem().toString();
        
        // Gauge type changed, need to rebuild the view
        if (!selectedIndicatorType.equals(indicatorType))
        {            
            LinearLayout mainLayout = (LinearLayout) mainActivity.findViewById(R.id.mainLinearLayout);
            LinearLayout mainLayoutLand = (LinearLayout) mainActivity.findViewById(R.id.mainLinearLayoutLand);
            
            if (mainLayout != null)
            {
                mainLayout.removeViewAt(0);
            }
            
            if (mainLayoutLand != null)
            {
                mainLayoutLand.removeViewAt(0);
            }
            
            Indicator indicator = null;
            
            // Gauge
            if (selectedIndicatorType.equals(getContext().getString(R.string.gauge)))
            {
                indicator = new MSGauge(getContext());
            }
            // Histogram
            else if (selectedIndicatorType.equals(getContext().getString(R.string.histogram)))
            {
                indicator = new Histogram(getContext()); 
            }
            // Bar Graph
            else if (selectedIndicatorType.equals(getContext().getString(R.string.bargraph)))
            {
                indicator = new BarMeter(getContext());
            }
            // Numeric Indicator
            else if (selectedIndicatorType.equals(getContext().getString(R.string.numeric_indicator)))
            {
                indicator = new NumericIndicator(getContext());
            }

            if (indicator != null)
            {
                indicator.setId(R.id.g3);
                indicator.initFromName(gd.getName());
                
                mainActivity.setIndicator3(indicator);
                
                if (mainLayout != null)
                {
                    mainLayout.addView(indicator);
                }
                
                if (mainLayoutLand != null)
                {
                    mainLayoutLand.addView(indicator);
                }
                
                mainActivity.initIndicator3Events();
            }
        }
        
        gd.setType(selectedIndicatorType);
        gd.setOrientation(orientationSpinner.getSelectedItem().toString());
        gd.setName(getValue(R.id.editName));
        gd.setChannel(getValue(R.id.editChannel));
        gd.setTitle(getValue(R.id.editTitle));
        gd.setUnits(getValue(R.id.editUnits));
        gd.setMax(getValueD(R.id.editHi));
        gd.setMin(getValueD(R.id.editLo));
        gd.setHiD(getValueD(R.id.editHiD));
        gd.setHiW(getValueD(R.id.editHiW));
        gd.setLoD(getValueD(R.id.editLoD));
        gd.setLoW(getValueD(R.id.editLoW));
        gd.setVd(getValueI(R.id.editVD));
        gd.setLd(getValueI(R.id.editLD));
        gd.setOffsetAngle(getValueD(R.id.editoffsetAngle));
        
        GaugeRegister.INSTANCE.persistDetails(gd);
        
        gauge.initFromGD(gd);
        gauge.invalidate();
        
        dismiss();
    }

    /**
     * Reset gauge details to the firmware default
     */
    public void resetDetails()
    {
        GaugeRegister.INSTANCE.reset(gd.getName());
        gd = GaugeRegister.INSTANCE.getGaugeDetails(gd.getName());
        gauge.initFromGD(gd);
        gauge.invalidate();
        dismiss();
    }

    /**
     * Get the value of a specific view as an integer
     * 
     * @param id    The ID of the view to get the value
     * @return      Integer of the value
     */
    private int getValueI(int id)
    {
        int val = 0;
        View v = findViewById(id);
        if (v instanceof TextView)
        {
            String txt = ((TextView) v).getText().toString();
            val = Integer.parseInt(txt);
        }
        return val;
    }

    /**
     * Get the value of a specific view as an double
     * 
     * @param id    The ID of the view to get the value
     * @return      Double of the value
     */
    private double getValueD(int id)
    {
        double val = 0;
        View v = findViewById(id);
        if (v instanceof TextView)
        {
            String txt = ((TextView) v).getText().toString();
            val = Double.parseDouble(txt);
        }
        return val;
    }

    /**
     * Get the value of a specific view as a string
     * 
     * @param id    The ID of the view to get the value
     * @return      Double of the value
     */
    private String getValue(int id)
    {
        String val = "";
        View v = findViewById(id);
        if (v instanceof TextView)
        {
            String txt = ((TextView) v).getText().toString();
            val = txt;
        }
        return val;
    }

    /**
     * Triggered when the bottom buttons of the dialog are clicked
     * 
     * @param v
     */
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.editOK)
        {
            saveDetails();
        }
        else if (which == R.id.editReset)
        {
            resetDetails();
        }
        else
        {
            cancel();
        }
    }

}
