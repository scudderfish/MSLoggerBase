package uk.org.smithfamily.mslogger.activity;

import com.android.debug.hv.ViewServer;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.dialog.TableHelper;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.TableEditor;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class EditTableActivity extends Activity implements android.view.View.OnClickListener
{
    private TableHelper tableHelper;
    private TableEditor tableEditor;
    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ViewServer.get(this).addWindow(this);
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        this.tableEditor = ecu.getTableEditorByName("veTable1Tbl");
        this.tableHelper = new TableHelper(this, tableEditor, true);
        
        setContentView(R.layout.edittable);
     
        setTitle("Edit " + tableEditor.getLabel());
        
        LinearLayout ll = (LinearLayout) findViewById(R.id.mainTable);
        ll.addView(tableHelper.getLayout(), 0);
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(this);
        
        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    
    /**
     * Burn the change to the ECU
     */
    private void burnToECU()
    {
        if (tableHelper.isModified()) 
        {
            System.out.println("Table " + tableEditor.getName() + "was modified, need to write change to ECU");
        }
    }
    
    /**
     * Triggered when one of the two bottoms button are clicked ("Burn" and "Cancel")
     * 
     * @param v The view that was clicked on
     */
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.burn)
        {
            burnToECU();
        }
        else if (which == R.id.cancel)
        {
            finish();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }
}
