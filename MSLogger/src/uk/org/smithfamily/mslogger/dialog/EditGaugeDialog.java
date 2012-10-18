package uk.org.smithfamily.mslogger.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.activity.MSLoggerActivity;
import uk.org.smithfamily.mslogger.widgets.BarGraph;
import uk.org.smithfamily.mslogger.widgets.Gauge;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
import uk.org.smithfamily.mslogger.widgets.Histogram;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.NumericIndicator;
import uk.org.smithfamily.mslogger.widgets.TableIndicator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
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
    private MSLoggerActivity mainActivity;
    private GaugeDetails gd;
    private Indicator indicator;
    private int indicatorIndex;
    private String indicatorType;

    private Map<String, String[]> channels = new HashMap<String, String[]>();

    private Spinner channelSpinner;
    private Spinner indicatorPositionSpinner;

    private Spinner typeSpinner;
    private Spinner orientationSpinner;

    /**
     * 
     * @param context
     * @param indicator
     */
    public EditGaugeDialog(Context context, Indicator indicator, int indicatorIndex, MSLoggerActivity mainActivity)
    {
        super(context);

        this.indicator = indicator;
        this.gd = indicator.getDetails();

        this.indicatorType = indicator.getType();
        this.indicatorIndex = indicatorIndex;
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

        setTitle(R.string.edit_gauge_title);

        populateFieldsForGaugeDetails(gd);

        prepareChannelSpinner();
        prepareOrientationSpinner();
        prepareTypeSpinner();
        prepareIndicatorPositionSpinner();
        
        Button buttonOK = (Button) findViewById(R.id.editOK);
        Button buttonReset = (Button) findViewById(R.id.editReset);
        Button buttonCancel = (Button) findViewById(R.id.editCancel);

        buttonOK.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    /**
     * Fill up all the indicator details fields
     * 
     * @param gd GaugeDetails to populate the fields with
     */
    private void populateFieldsForGaugeDetails(GaugeDetails gd)
    {
        setValue(R.id.editName, gd.getName());
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
    }

    /**
     * Prepare the channel spinner
     */
    private void prepareChannelSpinner()
    {
        channelSpinner = (Spinner) findViewById(R.id.spinnerChannel);

        Set<String> names = GaugeRegister.INSTANCE.getGaugeNames();
        List<String> titles = new ArrayList<String>();

        for (String name : names)
        {
            GaugeDetails gd = GaugeRegister.INSTANCE.getGaugeDetails(name);

            String[] indicatorDetails = new String[2];
            indicatorDetails[0] = gd.getName();
            indicatorDetails[1] = gd.getTitle();

            channels.put(gd.getChannel(), indicatorDetails);
            titles.add(gd.getTitle());
        }

        Collections.sort(titles);

        ArrayAdapter<String> channelArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, titles);

        // Specify the layout to use when the list of choices appears
        channelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        channelSpinner.setAdapter(channelArrayAdapter);

        int channelPosition = channelArrayAdapter.getPosition(gd.getTitle());
        if (channelPosition == -1)
        {
            channelPosition = 0;
        }
        
        channelSpinner.setSelection(channelPosition);

        channelSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                String name = findNameForTitle(channelSpinner.getSelectedItem().toString());

                GaugeDetails newIndicatorDetails = GaugeRegister.INSTANCE.getGaugeDetails(name);

                populateFieldsForGaugeDetails(newIndicatorDetails);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });
    }

    /**
     * Prepare the orientation spinner
     */
    private void prepareOrientationSpinner()
    {
        orientationSpinner = (Spinner) findViewById(R.id.indicatorOrientation);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> orientationAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatororientation, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        orientationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        orientationSpinner.setAdapter(orientationAdapter);

        // Select currently selected orientation
        int orientationPosition = orientationAdapter.getPosition(gd.getOrientation().toLowerCase());
        if (orientationPosition == -1)
        {
            orientationPosition = 0;
        }
        
        orientationSpinner.setSelection(orientationPosition);
    }

    /**
     * Prepare the indicator type spinner
     */
    private void prepareTypeSpinner()
    {
        typeSpinner = (Spinner) findViewById(R.id.indicatorType);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatortypes, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        typeSpinner.setAdapter(typeAdapter);

        // Select current gauge type
        typeSpinner.setSelection(typeAdapter.getPosition(indicatorType));

        typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                adjustFieldsForIndicatorType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
            }
        });
    }
    
    /**
     * Prepare the indicator position spinner
     */
    private void prepareIndicatorPositionSpinner()
    {
        indicatorPositionSpinner = (Spinner) findViewById(R.id.spinnerIndicatorPosition);
        
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> positionAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatorpositions, android.R.layout.simple_spinner_item);
    
        // Specify the layout to use when the list of choices appears
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        // Apply the adapter to the spinner
        indicatorPositionSpinner.setAdapter(positionAdapter);
    }    

    /**
     * Depending on the indicator type, decide if specific fields should be displayed or not
     */
    private void adjustFieldsForIndicatorType()
    {
        TableRow orientationRow = (TableRow) findViewById(R.id.indicatorOrientationRow);

        String selectedType = typeSpinner.getSelectedItem().toString();
        
        // Display orientation spinner for bar graph
        if (selectedType.equals(getContext().getString(R.string.bargraph)))
        {
            orientationRow.setVisibility(View.VISIBLE);
        }
        else
        {
            orientationRow.setVisibility(View.GONE);
        }
        
        TableRow indicatorDetailsNumber = (TableRow) findViewById(R.id.indicatorDetailsNumber);
        
        // Display multiple indicators choses for table indicator
        if (selectedType.equals(getContext().getString(R.string.table_indicator)))
        {
            indicatorDetailsNumber.setVisibility(View.VISIBLE);
        }
        else
        {
            indicatorDetailsNumber.setVisibility(View.GONE);
        }
    }

    /**
     * Helper function to set the value of a view based on an ID
     * 
     * @param id ID of the view
     * @param value Value to set it to
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
            Indicator newIndicator = null;

            // Gauge
            if (selectedIndicatorType.equals(getContext().getString(R.string.gauge)))
            {
                newIndicator = new Gauge(getContext());
            }
            // Histogram
            else if (selectedIndicatorType.equals(getContext().getString(R.string.histogram)))
            {
                newIndicator = new Histogram(getContext());
            }
            // Bar Graph
            else if (selectedIndicatorType.equals(getContext().getString(R.string.bargraph)))
            {
                newIndicator = new BarGraph(getContext());
            }
            // Numeric Indicator
            else if (selectedIndicatorType.equals(getContext().getString(R.string.numeric_indicator)))
            {
                newIndicator = new NumericIndicator(getContext());
            }
            // Table Indicator
            else if (selectedIndicatorType.equals(getContext().getString(R.string.table_indicator)))
            {
                newIndicator = new TableIndicator(getContext());
            }

            if (newIndicator != null)
            {
                LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 1f);
                newIndicator.setLayoutParams(params);

                newIndicator.setFocusable(true);
                newIndicator.setFocusableInTouchMode(true);

                ViewGroup parentIndicatorView = (ViewGroup) indicator.getParent();
                int index = parentIndicatorView.indexOfChild(indicator);
                parentIndicatorView.removeViewAt(index);

                parentIndicatorView.addView(newIndicator, index);

                newIndicator.setId(indicator.getId());
                newIndicator.initFromName(gd.getName());

//                mainActivity.replaceIndicator(newIndicator, indicatorIndex);
//                mainActivity.bindIndicatorsEditEventsToIndex(indicatorIndex);

                indicator = newIndicator;
            }
        }

        gd.setType(selectedIndicatorType);
        gd.setOrientation(orientationSpinner.getSelectedItem().toString());
        gd.setName(getValue(R.id.editName));
        gd.setChannel(findChannelForTitle(channelSpinner.getSelectedItem().toString()));
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

        indicator.initFromGD(gd);
        indicator.invalidate();

        dismiss();
    }

    /**
     * 
     * @param value
     * @return
     */
    private String findChannelForTitle(String indicatorTitle)
    {
        for (Map.Entry<String, String[]> entry : channels.entrySet())
        {
            String key = entry.getKey();
            String[] value = entry.getValue();

            if (value[1].equals(indicatorTitle))
            {
                return key;
            }
        }

        return "";
    }

    private String findNameForTitle(String indicatorTitle)
    {
        for (String[] value : channels.values())
        {
            if (value[1].equals(indicatorTitle))
            {
                return value[0];
            }
        }

        return "";
    }

    /**
     * Reset gauge details to the firmware default
     */
    public void resetDetails()
    {
        GaugeRegister.INSTANCE.reset(gd.getName());
        gd = GaugeRegister.INSTANCE.getGaugeDetails(gd.getName());
        indicator.initFromGD(gd);
        indicator.invalidate();
        dismiss();
    }

    /**
     * Get the value of a specific view as an integer
     * 
     * @param id The ID of the view to get the value
     * @return Integer of the value
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
     * @param id The ID of the view to get the value
     * @return Double of the value
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
     * @param id The ID of the view to get the value
     * @return Double of the value
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
