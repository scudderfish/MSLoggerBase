package uk.org.smithfamily.mslogger.activity;

import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.SettingGroup;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class EditSettingsActivity extends Activity
{
    /**
     * 
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.editsettings);
        
        setTitle(R.string.edit_properties);;
        
        TableLayout table = (TableLayout)findViewById(R.id.settingsTable);
        
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        populateControls(table,ecu);

    }

    private void populateControls(TableLayout table, Megasquirt ecu)
    {
        List<SettingGroup> groups = ecu.getSettingGroups();
        
        for(SettingGroup g : groups)
        {
            LayoutParams lpSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            lpSpan.span = 2;
            
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(lpSpan);
            
            TextView label = new TextView(this);
            label.setText(g.getDescription());
            label.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            label.setPadding(0, 0, 0, 10);
            label.setLayoutParams(lpSpan);
            
            tableRow.addView(label);
            
            table.addView(tableRow);
        }
    }
    
}
