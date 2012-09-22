package uk.org.smithfamily.mslogger.activity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.SettingGroup;
import uk.org.smithfamily.mslogger.ecuDef.SettingGroup.SettingOption;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class EditSettingsActivity extends Activity implements OnItemSelectedListener
{
    private static final String[] STATES       = { "Enabled", "Disabled" };

    private Set<String>           settingFlags = new HashSet<String>();

    /**
     * 
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editsettings);

        setTitle(R.string.edit_properties);

        TableLayout table = (TableLayout) findViewById(R.id.settingsTable);

        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();

        populateControls(table, ecu);
        populateFlags(table, ecu);
    }

    /**
     * Add the drop downs for any SettingGroup entries
     * 
     * @param table
     * @param ecu
     */
    private void populateControls(TableLayout table, Megasquirt ecu)
    {
        List<SettingGroup> groups = ecu.getSettingGroups();

        for (SettingGroup g : groups)
        {
            LayoutParams groupHeadingSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            LayoutParams controls = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            groupHeadingSpan.span = 2;
            controls.span = 2;

            // Create the header row
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(groupHeadingSpan);

            TextView label = new TextView(this);
            label.setText(g.getDescription());
            label.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            label.setPadding(0, 0, 0, 10);
            label.setLayoutParams(groupHeadingSpan);

            tableRow.addView(label);

            table.addView(tableRow);
            tableRow = new TableRow(this);
            tableRow.setLayoutParams(controls);

            // Create the drop down, and add all the options
            Spinner control = new Spinner(this);
            SettingsAdapter adapter = new SettingsAdapter(this, android.R.layout.simple_spinner_item);
            for (SettingOption o : g.getOptions())
            {
                settingFlags.add(o.getFlag());
                adapter.add(o);
            }
            control.setAdapter(adapter);
            control.setLayoutParams(controls);
            int position = adapter.find(ApplicationSettings.INSTANCE.getPref(g.getName()));
            if (position != -1)
            {
                control.setSelection(position);
            }
            control.setOnItemSelectedListener(this);
            tableRow.addView(control);
            table.addView(tableRow);
        }
    }

    /**
     * Create drop downs for any random flags we found parsing the INI file
     * 
     * @param table
     * @param ecu
     */
    private void populateFlags(TableLayout table, Megasquirt ecu)
    {
        LayoutParams groupHeadingSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams controls = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        groupHeadingSpan.span = 2;
        controls.span = 1;

        // Header row
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(groupHeadingSpan);

        TextView label = new TextView(this);
        label.setText(R.string.other_flags);
        label.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        label.setPadding(0, 0, 0, 10);
        label.setLayoutParams(groupHeadingSpan);

        tableRow.addView(label);
        table.addView(tableRow);
        String[] flags = ecu.getControlFlags();
        Arrays.sort(flags, String.CASE_INSENSITIVE_ORDER);

        for (String flag : flags)
        {
            if (!settingFlags.contains(flag))
            {
                tableRow = new TableRow(this);
                tableRow.setLayoutParams(controls);

                // Label the flag
                label = new TextView(this);
                label.setText(flag);
                label.setTextAppearance(this, android.R.style.TextAppearance_Small);
                // label.setPadding(0, 0, 0, 10);
                tableRow.addView(label);

                // Enabled/Disabled choice
                Spinner control = new Spinner(this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, STATES);

                control.setAdapter(adapter);
                control.setLayoutParams(controls);
                tableRow.addView(control);
                table.addView(tableRow);
            }
        }
    }

    class SettingsAdapter extends ArrayAdapter<SettingOption>
    {
        public SettingsAdapter(Context context, int textViewResourceId)
        {
            super(context, textViewResourceId);
        }

        public int find(String name)
        {

            for (int i = 0; i < this.getCount(); i++)
            {
                if (this.getItem(i).getFlag().equals(name))
                {
                    return i;
                }
            }
            return -1;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
    {
        SettingOption o = (SettingOption) parent.getSelectedItem();
        String groupName = o.getGroup().getName();
        String value = o.getFlag();
        ApplicationSettings.INSTANCE.setPref(groupName, value);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
    }

}
