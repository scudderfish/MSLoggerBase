package uk.org.smithfamily.mslogger.activity;

import java.io.File;
import java.io.FilenameFilter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.log.DatalogRow;
import uk.org.smithfamily.mslogger.log.DatalogRowAdapter;
import uk.org.smithfamily.mslogger.log.DatalogRowAdapterCallback;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.log.EmailManager;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Activity class used to manage datalogs
 */
public class ManageDatalogsActivity  extends ListActivity
{
    
    private ListView datalogsList;  
    private TextView datalogsInfo;
    private DatalogRowAdapter mDatalogsArrayAdapter;
    private Button view;
    private Button sendByEmail;
    private Button delete;
    
    /**
     * Creation of the activity, fill the datalogs listview with the current datalog on the device
     * 
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.managedatalogs);
        
        setTitle(R.string.manage_datalogs_title);
        
        datalogsInfo = (TextView) findViewById(R.id.datalogs_info);
        
        view = (Button) findViewById(R.id.view);
        view.setVisibility(View.GONE);
        view.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Find selected datalog
                String datalog = "";
                String datalogDirectory = ApplicationSettings.INSTANCE.getDataDir().getAbsolutePath();
                
                List<DatalogRow> selectedRows = ((DatalogRowAdapter) datalogsList.getAdapter()).getAllSelected();
                if (selectedRows.size() > 0)
                {
                    DatalogRow firstSelectedRow = selectedRows.get(0);
                    datalog = datalogDirectory + "/" + firstSelectedRow.getDatalogName();
                }
                
                Intent launchViewDatalog = new Intent(ManageDatalogsActivity.this, ViewDatalogActivity.class);
                
                Bundle b = new Bundle();
                b.putString("datalog",datalog);
                launchViewDatalog.putExtras(b);
                
                startActivity(launchViewDatalog);
            }
        });
        
        sendByEmail = (Button) findViewById(R.id.send_by_email);
        sendByEmail.setVisibility(View.GONE);
        sendByEmail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                List<String> paths = new ArrayList<String>();
                
                // Create array of datalogs path to send
                String datalogDirectory = ApplicationSettings.INSTANCE.getDataDir().getAbsolutePath();
                
                List<DatalogRow> selectedRows = ((DatalogRowAdapter) datalogsList.getAdapter()).getAllSelected();
                for (DatalogRow datalogRow : selectedRows)
                {
                    paths.add(datalogDirectory + "/" + datalogRow.getDatalogName());
                }
                
                String emailText = getString(R.string.email_body);

                String subject = String.format(getString(R.string.email_subject), System.currentTimeMillis());
                EmailManager.email(ManageDatalogsActivity.this, ApplicationSettings.INSTANCE.getEmailDestination(), null, subject, emailText, paths);       
            }
        });
        
        delete = (Button) findViewById(R.id.delete);
        delete.setVisibility(View.GONE);
        delete.setOnClickListener(new OnClickListener () {
            @Override
            public void onClick(View v) {
                // Create array of datalogs path to delete
                List<String> datalogsToDelete = new ArrayList<String>();
                
                String datalogDirectory = ApplicationSettings.INSTANCE.getDataDir().getAbsolutePath();
                
                List<DatalogRow> selectedRows = ((DatalogRowAdapter) datalogsList.getAdapter()).getAllSelected();
                for (DatalogRow datalogRow : selectedRows)
                {
                    datalogsToDelete.add(datalogDirectory +  "/" + datalogRow.getDatalogName());
                }
                
                for (String datalogFilepath : datalogsToDelete)
                {                    
                    // Physically delete the file
                    File fileToDelete = new File(datalogFilepath);
                    boolean deleteResult = fileToDelete.delete();
                    
                    if (!deleteResult)
                    {
                        DebugLogManager.INSTANCE.log("Couldn't delete " + datalogFilepath,Log.ERROR);
                    }
                }
                                
                // Clear the adapter
                mDatalogsArrayAdapter.clear();
                
                // Refill datalogs listview
                fillDatalogsListView();
            }            
        });
        
        fillDatalogsListView();
    }
    
    /**
     * Prepare the listview for datalogs
     */
    private void fillDatalogsListView() 
    {       
        datalogsList = (ListView) findViewById(android.R.id.list);
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
            long datalogsSize = 0;
            
            for (File datalog : datalogs)
            {
                DatalogRow datalogRow = new DatalogRow();
                
                datalogRow.setDatalogName(datalog.getName());
                datalogRow.setDatalogSize("Size: " + Formatter.formatFileSize(this, datalog.length()));
                
                datalogRows.add(datalogRow);
                
                datalogsSize += datalog.length();
            }

            mDatalogsArrayAdapter = new DatalogRowAdapter(this, datalogRows);    
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
            
            // Datalogs stats
            StatFs stat = new StatFs(ApplicationSettings.INSTANCE.getDataDir().getPath());

            BigInteger blockCount = BigInteger.valueOf(stat.getBlockCount());
            BigInteger blockSize = BigInteger.valueOf(stat.getBlockSize());
            
            long totalSize = blockCount.multiply(blockSize).longValue();
            String datalogsSizeFormatted = Formatter.formatFileSize(this, datalogsSize);
            String internalSizeFormatted = Formatter.formatFileSize(this, totalSize);
            
            String datalogText = "datalog";
            String datalogUpperText = "Datalog";
            if (datalogs.length == 0 || datalogs.length > 1)
            {
                datalogText += "s";
                datalogUpperText += "s";
            }
            
            datalogsInfo.setText("Currently " + datalogs.length + " " + datalogText + " / " + datalogUpperText + " size: " 
                                              + datalogsSizeFormatted + " / " + internalSizeFormatted);
        }
        // No datalog found, showing message instead
        else
        {            
            datalogsInfo.setText(R.string.no_datalog_found);
            
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
        sendByEmail.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);
        
        if (getCountDatalogsChecked() == 1)
        {
            view.setVisibility(View.VISIBLE);
        }
        else
        {
            view.setVisibility(View.GONE);
        }
    }
   
    /**
     * Hide the bottoms buttons
     */ 
    private void hideBottomButtons()
    {
        view.setVisibility(View.GONE);
        sendByEmail.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);
    }
}
