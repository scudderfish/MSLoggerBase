package uk.org.smithfamily.mslogger.dialog;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.log.DatalogRow;
import uk.org.smithfamily.mslogger.log.DatalogRowAdapter;
import uk.org.smithfamily.mslogger.log.DatalogRowAdapterCallback;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * SD Card console for MS3
 */
public class SDCardConsole extends Dialog implements android.view.View.OnClickListener
{
    private Button buttonView;
    private Button buttonDownload;
    private Button buttonDelete;
    
    private ListView datalogsList;
    private DatalogRowAdapter mDatalogsArrayAdapter;
    
    public SDCardConsole(Context context)
    {
        super(context);
    }
    
    /**
     * Constructor
     * 
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.sd_card_console);
        
        setTitle(R.string.ms3_sd_console);
        
        buttonView = (Button) findViewById(R.id.view);
        buttonView.setOnClickListener(this);
        
        buttonDownload = (Button) findViewById(R.id.download);
        buttonDownload.setOnClickListener(this);
        
        buttonDelete = (Button) findViewById(R.id.delete);
        buttonDelete.setOnClickListener(this);
        
        datalogsList = (ListView) findViewById(android.R.id.list);
        
        fillDatalogsListView();
    }
    
    /**
     * TODO: Get from SD card of MS3 instead of android device
     * 
     * Prepare the listview for datalogs
     */
    private void fillDatalogsListView() 
    {       
        datalogsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        datalogsList.setItemsCanFocus(true);
        
        File datalogDirectory = ApplicationSettings.INSTANCE.getDataDir();

        class DatalogFilter implements FilenameFilter
        {
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".msl");
            }
        }
        
        File[] datalogs = datalogDirectory.listFiles(new DatalogFilter());
        
        if (datalogs.length > 0)
        {
            ArrayList<DatalogRow> datalogRows = new ArrayList<DatalogRow>();
         
            for (File datalog : datalogs)
            {
                DatalogRow datalogRow = new DatalogRow();
                
                datalogRow.setDatalogName(datalog.getName());
                datalogRow.setDatalogSize("Size: " + Formatter.formatFileSize(getContext(), datalog.length()));
                
                datalogRows.add(datalogRow);
            }

            mDatalogsArrayAdapter = new DatalogRowAdapter(getContext(), datalogRows);
            mDatalogsArrayAdapter.setCallback(new DatalogRowAdapterCallback()
            {
                public void onDatalogSelected()
                {
                    // If more then one datalog is checked, make send by email button visible
                    if (getCountDatalogsChecked() > 0)
                    {
                        showBottomButtons();
                    }
                    else
                    {
                        hideBottomButtons();
                    }
                }
            });
            
            datalogsList.setAdapter(mDatalogsArrayAdapter);
            
            datalogsList.setVisibility(View.VISIBLE);
            
            showBottomButtons();
        }
        // No datalog found, showing message instead
        else
        {
            
            datalogsList.setVisibility(View.GONE);
            
            // Make the three bottom buttons dissapear too
            hideBottomButtons();
        }
    }
    
    /**
     * @return The number of checked datalogs
     */
    private int getCountDatalogsChecked()
    {
        int nbChecked = 0;
        
        for (int i = 0; i < datalogsList.getAdapter().getCount(); i++)
        {
            if (((DatalogRowAdapter) datalogsList.getAdapter()).isItemSelected(i))
            {
                nbChecked++;
            }
        }
        
        return nbChecked;
    }
    
    /**
     * Show the bottom buttons
     */ 
    private void showBottomButtons()
    {
        int datalogCount = getCountDatalogsChecked();
        
        if (datalogCount >= 1)
        {
            buttonDelete.setVisibility(View.VISIBLE);
        }
        else
        {
            buttonDelete.setVisibility(View.GONE);
        }
        
        if (datalogCount == 1)
        {
            buttonView.setVisibility(View.VISIBLE);
            buttonDownload.setVisibility(View.VISIBLE);
        }
        else
        {
            buttonView.setVisibility(View.GONE);
            buttonDownload.setVisibility(View.GONE);
        }
    }
   
    /**
     * Hide the bottoms buttons
     */ 
    private void hideBottomButtons()
    {
        buttonView.setVisibility(View.GONE);
        buttonDownload.setVisibility(View.GONE);
        buttonDelete.setVisibility(View.GONE);
    }
    
    /**
     * @param v The view that was clicked on
     */
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.view)
        {
            
        }
        else if (which == R.id.download)
        {
            
        }
        else if (which == R.id.delete)
        {
            
        }
    }
}
