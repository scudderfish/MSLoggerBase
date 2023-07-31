package uk.org.smithfamily.mslogger.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import uk.org.smithfamily.mslogger.DataManager;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.OutputChannel;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Indicator.DisplayType;
import uk.org.smithfamily.mslogger.widgets.Indicator.Orientation;
import uk.org.smithfamily.mslogger.widgets.IndicatorDefault;
import uk.org.smithfamily.mslogger.widgets.IndicatorDefaults;

/**
 * Dialog to change various gauge settings
 */
public class EditIndicatorDialog extends Dialog implements android.view.View.OnClickListener
{
    private final Indicator indicator;

    private final Map<String, String[]> channels = new HashMap<>();

    private OnEditIndicatorResult mDialogResult;

    public EditIndicatorDialog(final Context context, final Indicator indicator)
    {
        super(context);

        this.indicator = indicator;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editindicator);

        setTitle(R.string.edit_indicator_title);

        populateFields();

        prepareChannelSpinner();
        prepareOrientationSpinner();
        prepareTypeSpinner();

        final Button buttonOK = findViewById(R.id.editOK);
        final Button buttonReset = findViewById(R.id.editReset);
        final Button buttonRemove = findViewById(R.id.editRemove);

        buttonOK.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonRemove.setOnClickListener(this);
    }

    private void populateFields()
    {
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

    private void populateFields(final IndicatorDefault id)
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
        final Spinner channelSpinner = findViewById(R.id.spinnerChannel);

        final Map<String, OutputChannel> outputChannels = DataManager.getInstance().getOutputChannels();

        final List<String> titles = new ArrayList<>();

        for (final Entry<String, OutputChannel> entry : outputChannels.entrySet())
        {
            final IndicatorDefault id = IndicatorDefaults.defaults.get(entry.getKey());

            if (id == null)
            {
                DebugLogManager.INSTANCE.log("No default indicator for " + entry.getKey(), Log.WARN);
            }
            else
            {
                final String[] indicatorDetails = new String[2];
                indicatorDetails[0] = id.getChannel();
                indicatorDetails[1] = id.getTitle();

                channels.put(id.getChannel(), indicatorDetails);
                titles.add(id.getTitle());
            }
        }

        Collections.sort(titles);

        final ArrayAdapter<String> channelArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, titles);

        // Specify the layout to use when the list of choices appears
        channelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        channelSpinner.setAdapter(channelArrayAdapter);

        final String indicatorChannel = indicator.getChannel();

        int channelPosition = channelArrayAdapter.getPosition(Objects.requireNonNull(IndicatorDefaults.defaults.get(indicatorChannel)).getTitle());
        if (channelPosition == -1)
        {
            channelPosition = 0;
        }

        channelSpinner.setSelection(channelPosition);

        channelSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            boolean ignoreEvent = true;

            @Override
            public void onItemSelected(final AdapterView<?> arg0, final View arg1, final int arg2, final long arg3)
            {
                // First onItemSelected event of the spinner come from populating it, ignore it!
                if (ignoreEvent)
                {
                    ignoreEvent = false;
                }
                else
                {
                    final String name = findNameForTitle(channelSpinner.getSelectedItem().toString());

                    final IndicatorDefault id = IndicatorDefaults.defaults.get(name);

                    assert id != null;
                    populateFields(id);
                }
            }

            @Override
            public void onNothingSelected(final AdapterView<?> arg0)
            {
            }
        });

    }

    private String findNameForTitle(final String indicatorTitle)
    {
        for (final String[] value : channels.values())
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
        final Spinner orientationSpinner = findViewById(R.id.indicatorOrientation);

        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> orientationAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatororientation, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        orientationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        orientationSpinner.setAdapter(orientationAdapter);

        // Find the indicator to select
        final Orientation orientation = indicator.getOrientation();
        int selection = 0;

        if (orientation == Orientation.VERTICAL)
        {
            selection = orientationAdapter.getPosition(getContext().getString(R.string.vertical));
        }

        orientationSpinner.setSelection(selection);
    }

    /**
     * Prepare the indicator type spinner
     */
    private void prepareTypeSpinner()
    {
        final Spinner typeSpinner = findViewById(R.id.indicatorType);

        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.indicatortypes, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        typeSpinner.setAdapter(typeAdapter);

        // Find the indicator to select
        final DisplayType indicatorType = indicator.getDisplayType();
        int selection = 0;

        if (indicatorType == DisplayType.NUMERIC)
        {
            selection = typeAdapter.getPosition(getContext().getString(R.string.numeric_indicator));
        }
        else if (indicatorType == DisplayType.BAR)
        {
            selection = typeAdapter.getPosition(getContext().getString(R.string.bargraph));
        }
        else if (indicatorType == DisplayType.HISTOGRAM)
        {
            selection = typeAdapter.getPosition(getContext().getString(R.string.histogram));
        }

        // Select current indicator type
        typeSpinner.setSelection(selection);

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
        final LinearLayout orientationRow = findViewById(R.id.indicatorOrientationRow);
        final Spinner typeSpinner = findViewById(R.id.indicatorType);
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
    }

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
        final Spinner gaugeType = findViewById(R.id.indicatorType);
        final Spinner orientationSpinner = findViewById(R.id.indicatorOrientation);
        final Spinner channelSpinner = findViewById(R.id.spinnerChannel);

        final String selectedIndicatorType = gaugeType.getSelectedItem().toString();

        Orientation orientation = Orientation.VERTICAL;
        if (orientationSpinner.getSelectedItem().toString().equalsIgnoreCase("horizontal"))
        {
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
        final Object selectedItem = channelSpinner.getSelectedItem();
        indicator.setChannel(findChannelForTitle(selectedItem != null ?selectedItem.toString() : ""));
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

        mDialogResult.finish(indicator, false);

        dismiss();
    }

    /**
     * Ask to remove the indicator from the current dashboard
     */
    public void removeIndicator()
    {
        mDialogResult.finish(indicator, true);

        dismiss();
    }

    private String findChannelForTitle(final String indicatorTitle)
    {
        for (final Map.Entry<String, String[]> entry : channels.entrySet())
        {
            final String key = entry.getKey();
            final String[] value = entry.getValue();

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
        final IndicatorDefault id = IndicatorDefaults.defaults.get(indicator.getChannel());
        assert id != null;
        indicator.copyFrom(id);

        dismiss();
    }

    /**
     * Get the value of a specific view as an integer
     * 
     * @param id The ID of the view to get the value
     * @return Integer of the value
     */
    private int getValueI(final int id)
    {
        int val = 0;
        final View v = findViewById(id);
        if (v instanceof TextView)
        {
            final String txt = ((TextView) v).getText().toString();
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
    private double getValueD(final int id)
    {
        double val = 0;
        final View v = findViewById(id);
        if (v instanceof TextView)
        {
            final String txt = ((TextView) v).getText().toString();
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
    private String getValue(final int id)
    {
        String val = "";
        final View v = findViewById(id);
        if (v instanceof TextView)
        {
            val = ((TextView) v).getText().toString();
        }
        return val;
    }

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
        else if (which == R.id.editRemove)
        {
            removeIndicator();
        }
    }

    public void setDialogResult(final OnEditIndicatorResult dialogResult)
    {
        mDialogResult = dialogResult;
    }

    /**
     * Interface used to send the data back to the dialog's parent
     */
    public interface OnEditIndicatorResult
    {
        void finish(Indicator Indicator, boolean toRemove);
    }
}
