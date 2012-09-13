package uk.org.smithfamily.mslogger.dialog;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class EditPropertiesDialog extends Dialog
{
    private Set<String> specialFlags = new HashSet<String>(Arrays.asList(new String[] { "ALTERNATE_BURST_MODE_SETTINGS",
            "AUTOMARK_LOGGING_FEATURES", "CAN_COMMANDS", "CYL_12_16_SUPPORT", "EGTFULL", "EXPANDED_CLT_TEMP", "INI_VERSION_2",
            "INTERNAL_LOG_FIELDS", "LOGPAGES", "MEMPAGES", "METRES", "MICROSQUIRT", "MICROSQUIRT_FULL", "MICROSQUIRT_MODULE",
            "MPH", "MSLVV_COMPATIBLE", "PW_4X", "TRIGLOG", "USE_CRC_DATA_CHECK" }));
    private Context     context;
    private String[] values={"True","False"};
    private ArrayAdapter<String> valuesList;
    public EditPropertiesDialog(Context context)
    {
        super(context);
        this.context = context;
        valuesList = new ArrayAdapter<String>(context,R.layout.editproperties,values);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editproperties);

        setTitle(R.string.edit_properties);

        TableLayout tableOfControls = (TableLayout) findViewById(R.id.scrollTableProps);

        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();

        String[] controlFlags = ecu.getControlFlags();

        for (String controlFlag : controlFlags)
        {
            if (specialFlags.contains(controlFlag))
            {
                addControl(tableOfControls, controlFlag);
            }
        }

    }

    private void addControl(TableLayout tableOfControls, String controlFlag)
    {
        TextView label = new TextView(context);
        label.setText(controlFlag);

        TableRow tableRow = new TableRow(context);
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        tableRow.setLayoutParams(lp);

        tableRow.addView(label);
        Spinner spinner = new Spinner(context);
        spinner.setAdapter(valuesList);
        
        tableRow.addView(spinner);
        tableOfControls.addView(tableRow, lp);
    }

}
