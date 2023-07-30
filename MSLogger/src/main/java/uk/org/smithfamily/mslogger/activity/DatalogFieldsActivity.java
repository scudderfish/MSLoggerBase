package uk.org.smithfamily.mslogger.activity;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Activity that can be launched when viewing a datalog to select the datalog fields to view
 */
public class DatalogFieldsActivity extends Activity implements OnClickListener
{
    private ListView datalogsList;
    private ArrayAdapter<String> mDatalogsArrayAdapter;
    
    private EditText filterFields;

    public static final int BACK_FROM_DATALOG_FIELDS = 1;
    
    /**
     * Initialise the activity and all its items
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalog_fields);
        
        setTitle("Select datalog fields");

        Button btnOk = findViewById(R.id.ok);
        btnOk.setOnClickListener(this);

        Button btnCancel = findViewById(R.id.cancel);
        btnCancel.setOnClickListener(this);
        
        filterFields = findViewById(R.id.filterFields);
        filterFields.addTextChangedListener(filterTextWatcher);
        
        fillDatalogFieldsList();
    }
    
    /**
     * Make sure we remove the text changed listener when the activity is destroyed
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        filterFields.removeTextChangedListener(filterTextWatcher);
    }
    
    /**
     * Text watcher that will monitor the edit text and apply the filter to the list
     */
    private final TextWatcher filterTextWatcher = new TextWatcher()
    {
        @Override
        public void afterTextChanged(Editable s)
        {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            mDatalogsArrayAdapter.getFilter().filter(s);
        }
    };
    
    private void fillDatalogFieldsList()
    {
        datalogsList = findViewById(android.R.id.list);
        datalogsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        datalogsList.setItemsCanFocus(true);
        
        mDatalogsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice);
        Bundle b = getIntent().getExtras();
        String[] datalogFields = b.getStringArray("datalog_fields");
               
        String[] selectedDatalogFields = ApplicationSettings.INSTANCE.getDatalogFields();
        
        for (String datalogField : datalogFields)
        {                       
            mDatalogsArrayAdapter.add(datalogField);
        }
 
        datalogsList.setAdapter(mDatalogsArrayAdapter);

        // Check the currently selected datalog fields
        for (int i = 0; i < datalogsList.getAdapter().getCount(); i++)
        {
            for (String selectedDatalogField : selectedDatalogFields) {
                if (selectedDatalogField.equals(datalogsList.getItemAtPosition(i).toString())) {
                    datalogsList.setItemChecked(i, true);
                }
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        if (which == R.id.ok)
        {
            // Build array of datalog fields name to save in application settings
            List<String> datalogFields = new ArrayList<>();
            
            for (int i = 0; i < datalogsList.getAdapter().getCount(); i++)
            {
                // Time should always be there
                if (datalogsList.isItemChecked(i) || datalogsList.getItemAtPosition(i).toString().equals("Time"))
                {
                    datalogFields.add(datalogsList.getItemAtPosition(i).toString());
                }
            }
            
            ApplicationSettings.INSTANCE.setDatalogFields(datalogFields.toArray(new String[0]));

            if (getParent() == null)
            {
                setResult(BACK_FROM_DATALOG_FIELDS);
            }
            else
            {
                getParent().setResult(BACK_FROM_DATALOG_FIELDS);
            }
            
            finish();
        }
        else if (which == R.id.cancel)
        {
            finish();
        }
    }
}
