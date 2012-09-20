package uk.org.smithfamily.mslogger.activity;

import java.util.List;

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
    /**
     * 
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editsettings);

        setTitle(R.string.edit_properties);
        ;

        TableLayout table = (TableLayout) findViewById(R.id.settingsTable);

        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();

        populateControls(table, ecu);

    }

    private void populateControls(TableLayout table, Megasquirt ecu)
    {
        List<SettingGroup> groups = ecu.getSettingGroups();

        for (SettingGroup g : groups)
        {
            LayoutParams groupHeadingSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            LayoutParams controls = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            // groupHeadingSpan.span = 2;

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

            Spinner control = new Spinner(this);
            SettingsAdapter adapter = new SettingsAdapter(this, android.R.layout.simple_spinner_item);
            for (SettingOption o : g.getOptions())
            {
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
        // TODO Auto-generated method stub

    }

}
