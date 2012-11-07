package uk.org.smithfamily.mslogger.dialog;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.comms.MS3SDCard;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DatalogRow;
import uk.org.smithfamily.mslogger.log.DatalogRowAdapter;
import uk.org.smithfamily.mslogger.log.DatalogRowAdapterCallback;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    
    private BroadcastReceiver yourReceiver;
    
    private MS3SDCard sdCard;
    
    public SDCardConsole(Context context)
    {
        super(context);
        
        this.sdCard = new MS3SDCard();
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
        
        final IntentFilter injectCommandResultsFilter = new IntentFilter();
        injectCommandResultsFilter.addAction(Megasquirt.INJECTED_COMMAND_RESULTS);
        
        sdCard.status();
        
        this.yourReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String action = intent.getAction();
                
                if (action.equals(Megasquirt.INJECTED_COMMAND_RESULTS))
                {
                    int resultId = intent.getIntExtra(Megasquirt.INJECTED_COMMAND_RESULT_ID, 0);
                    byte result[] = intent.getByteArrayExtra(Megasquirt.INJECTED_COMMAND_RESULT_DATA);
                    
                    switch (resultId)
                    {
                    case Megasquirt.MS3_SD_CARD_STATUS_READ:
                        System.out.println("sd card status read: " + Arrays.toString(result));
                        break;
                    case Megasquirt.MS3_SD_CARD_STATUS_WRITE:
                        System.out.println("sd card status write: " + Arrays.toString(result));
                        break;
                    case Megasquirt.MS3_SD_CARD_READ_DIRECTORY_WRITE:
                        break;
                    case Megasquirt.MS3_SD_CARD_READ_DIRECTORY_READ:
                        break;
                    case Megasquirt.MS3_SD_CARD_READ_STREAM:
                        break;
                    default:
                        break;
                    }
                }
            }
        };
        
        // Registers the receiver so that your service will listen for broadcasts
        getContext().registerReceiver(this.yourReceiver, injectCommandResultsFilter);
        
        fillDatalogsListView();
    }
    
    /**
     * Unregister the broadcast receiver when the dialog is closed
     */
    public void onStop()
    {
        getContext().unregisterReceiver(this.yourReceiver);
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
