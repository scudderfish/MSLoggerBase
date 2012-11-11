package uk.org.smithfamily.mslogger.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import uk.org.smithfamily.mslogger.DataManager;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.dashboards.DashboardElement;
import uk.org.smithfamily.mslogger.ecuDef.OutputChannel;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Indicator.DisplayType;
import uk.org.smithfamily.mslogger.widgets.Indicator.Orientation;
import uk.org.smithfamily.mslogger.widgets.IndicatorDefault;
import uk.org.smithfamily.mslogger.widgets.IndicatorDefaults;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Dialog to change various gauge settings
 */
public class EditIndicatorDialog extends Dialog implements android.view.View.OnClickListener
{
    private final DashboardElement element;

    private final Map<String, String[]> channels = new HashMap<String, String[]>();

    private OnEditIndicatorResult mDialogResult;
    
    /**
     * 
     * @param context
     * @param indicator
     */
    public EditIndicatorDialog(final Context context, final DashboardElement element)
    {
        super(context);

        this.element = element;
    }

    /**
     * Called when the dialog is first created to initialise some value
     * 
     * @param savedInstanceState
     */
    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editgauge);

        setTitle(R.string.edit_gauge_title);

        populateFields();

        prepareChannelSpinner();
        prepareOrientationSpinner();
        prepareTypeSpinner();
        
        final Button buttonOK = (Button) findViewById(R.id.editOK);
        final Button buttonReset = (Button) findViewById(R.id.editReset);
        final Button buttonCancel = (Button) findViewById(R.id.editCancel);

        buttonOK.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    /**
     * Fill up all the indicator details fields
     * 
     * @param gd GaugeDetails to populate the fields with
     */
    private void populateFields()
    {
        Indicator indicator = element.getIndicator();
        
        setValue(R.id.editTitle, indicator.getTitle());
        setValue(R.id.editUnits, indicator.getUnits());
        setValue(R.id.editHi, Double.toString(indicator.getMax()));
        setValue(R.id.editLo, Double.toString(indicator.getMin()));
        setValue(R.id.editHiD, Double.toString(indicator.getHiD()));
        setValue(R.id.editHiW, Double.toString(indicator.getHiW()));
        setValue(R.id.editLoD, Double.toString(indicator.getLowD()));
        setValue(R.id.editLoW, Double.toString(indicator.getLowW()));
        setValue(R.id.editVD, Integer.toString(indicator.getVd()));
        setValue(R.id.editLD, Integer.toString(indicator.getLd()));
        setValue(R.id.editoffsetAngle, Double.toString(indicator.getOffsetAngle()));
    }
    
    private void populateFields(IndicatorDefault id)
    {        
        setValue(R.id.editTitle, id.getTitle());
        setValue(R.id.editUnits, id.getUnits());
        setValue(R.id.editHi, Double.toString(id.getMax()));
        setValue(R.id.editLo, Double.toString(id.getMin()));
        setValue(R.id.editHiD, Double.toString(id.getHiD()));
        setValue(R.id.editHiW, Double.toString(id.getHiW()));
        setValue(R.id.editLoD, Double.toString(id.getLoD()));
        setValue(R.id.editLoW, Double.toString(id.getLoW()));
        setValue(R.id.editVD, Integer.toString(id.getVd()));
        setValue(R.id.editLD, Integer.toString(id.getLd()));
        setValue(R.id.editoffsetAngle, Double.toString(45));
    }

    /**
     * Prepare the channel spinner
     */
    private void prepareChannelSpinner()
    {
        final Spinner channelSpinner = (Spinner) findViewById(R.id.spinnerChannel);
        
        Map<String, OutputChannel> outputChannels = DataManager.getInstance().getOutputChannels();

        List<String> titles = new ArrayList<String>();

        for (Entry<String, OutputChannel> entry : outputChannels.entrySet())
        {
            IndicatorDefault id = IndicatorDefaults.defaults.get(entry.getKey());

            if (id == null)
            {
                DebugLogManager.INSTANCE.log("No default indicator for " + entry.getKey(), Log.WARN);
            }
            else
            {
                String[] indicatorDetails = new String[2];
                indicatorDetails[0] = id.getChannel();
                indicatorDetails[1] = id.getTitle();
    
                channels.put(id.getChannel(), indicatorDetails);
                titles.add(id.getTitle());
            }
        }
        
        Collections.sort(titles);

        ArrayAdapter<String> channelArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, titles);

        // Specify the layout to use when the list of choices appears
        channelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        channelSpinner.setAdapter(channelArrayAdapter);
        
        String indicatorChannel = element.getIndicator().getChannel();
        
        int channelPosition = channelArrayAdapter.getPosition(IndicatorDefaults.defaults.get(indicatorChannel).getTitle());
        if (channelPosition == -1)
        {
            channelPosition = 0;
        }
        
        channelSpinner.setSelection(channelPosition);

        channelSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            boolean ignoreEvent = true;
            
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                // First onItemSelected event of the spinner come from populating it, ignore it!
                if (ignoreEvent)
                {
                    ignoreEvent = false;
                }
                else 
                {
                    String name = findNameForTitle(channelSpinner.getSelectedItem().toString());
    
                    IndicatorDefault id = IndicatorDefaults.defaults.get(name);
                    
                    populateFields(id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });
         
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
     * Prepare the orientation spinner
     */
    private void prepareOrientationSpinner()
    {
        Spinner orientationSpinner = (Spinner) findViewById(R.id.indicatorOrientation);

        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> orientationAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatororientation, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        orientationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        orientationSpinner.setAdapter(orientationAdapter);
        
        // Select currently selected orientation
        int orientationPosition = orientationAdapter.getPosition(element.getIndicator().getOrientation().name().toLowerCase());
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
        DisplayType indicatorType = element.getIndicator().getDisplayType();
        
        Spinner typeSpinner = (Spinner) findViewById(R.id.indicatorType);

        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatortypes, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        typeSpinner.setAdapter(typeAdapter);

        // Select current gauge type
        typeSpinner.setSelection(typeAdapter.getPosition(indicatorType.toString()));

        typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(final AdapterView<?> parentView, final View selectedItemView, final int position, final long id)
            {
                adjustFieldsForIndicatorType();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parentView)
            {
            }
        });
    }

    /**
     * Depending on the indicator type, decide if specific fields should be displayed or not
     */
    private void adjustFieldsForIndicatorType()
    {
        final TableRow orientationRow = (TableRow) findViewById(R.id.indicatorOrientationRow);
        final Spinner typeSpinner = (Spinner) findViewById(R.id.indicatorType);
        final String selectedType = typeSpinner.getSelectedItem().toString();

        // Display orientation spinner for bar graph
        if (selectedType.equals(getContext().getString(R.string.bargraph)))
        {
            orientationRow.setVisibility(View.VISIBLE);
        }
        else
        {
            orientationRow.setVisibility(View.GONE);
        }

        final TableRow indicatorDetailsNumber = (TableRow) findViewById(R.id.indicatorDetailsNumber);

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
    private void setValue(final int id, final String value)
    {
        final View v = findViewById(id);
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
        final Spinner gaugeType = (Spinner) findViewById(R.id.indicatorType); 
        final Spinner orientationSpinner = (Spinner) findViewById(R.id.indicatorOrientation);
        final Spinner channelSpinner = (Spinner) findViewById(R.id.spinnerChannel);
        
        final String selectedIndicatorType = gaugeType.getSelectedItem().toString();
        
        Indicator indicator = element.getIndicator();
        
        Orientation orientation = Orientation.VERTICAL;
        if (orientationSpinner.getSelectedItem().toString().equals("horizontal")) {
            orientation = Orientation.HORIZONTAL;
        }
        
        DisplayType displayType = DisplayType.GAUGE;        
        if (selectedIndicatorType.equals(getContext().getString(R.string.numeric_indicator)))
        {
            displayType = DisplayType.NUMERIC;
        }
        else if (selectedIndicatorType.equals(getContext().getString(R.string.bargraph)))
        {
            displayType = DisplayType.BAR;
        }
        else if (selectedIndicatorType.equals(getContext().getString(R.string.histogram)))
        {
            displayType = DisplayType.HISTOGRAM;
        }

        indicator.setDisplayType(displayType);
        indicator.setOrientation(orientation);
        indicator.setChannel(findChannelForTitle(channelSpinner.getSelectedItem().toString()));
        indicator.setTitle(getValue(R.id.editTitle));
        indicator.setUnits(getValue(R.id.editUnits));
        indicator.setMax(getValueD(R.id.editHi));
        indicator.setMin(getValueD(R.id.editLo));
        indicator.setHiD(getValueD(R.id.editHiD));
        indicator.setHiW(getValueD(R.id.editHiW));
        indicator.setLowD(getValueD(R.id.editLoD));
        indicator.setLowW(getValueD(R.id.editLoW));
        indicator.setVd(getValueI(R.id.editVD));
        indicator.setLd(getValueI(R.id.editLD));
        indicator.setOffsetAngle(getValueD(R.id.editoffsetAngle));

        mDialogResult.finish(element);
        
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
    
    /**
     * Reset indicators details to the firmware default
     */
    public void resetDetails()
    {
        IndicatorDefault id = IndicatorDefaults.defaults.get(element.getIndicator().getChannel());
        element.getIndicator().copyFrom(id);
        
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
    public void onClick(final View v)
    {
        final int which = v.getId();

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
    
    /**
     * Used by the parent to set a new OnEditIndicatorResult to the dialog
     * 
     * @param dialogResult
     */
    public void setDialogResult(OnEditIndicatorResult dialogResult)
    {
        mDialogResult = dialogResult;
    }

    /**
     * Interface used to send the data back to the dialog's parent
     */
    public interface OnEditIndicatorResult
    {
       void finish(DashboardElement element);
    }
}
