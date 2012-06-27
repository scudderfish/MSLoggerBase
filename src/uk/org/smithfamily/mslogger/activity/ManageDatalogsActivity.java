package uk.org.smithfamily.mslogger.activity;

import java.io.File;
import java.io.FilenameFilter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Activity class used to manage datalogs
 */
public class ManageDatalogsActivity  extends ListActivity {
    
    private ListView datalogsList;  
    private TextView datalogsInfo;
    private ArrayAdapter<String> mDatalogsArrayAdapter;
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
        
        setTitle("Manage Datalogs");
        
        datalogsInfo = (TextView) findViewById(R.id.datalogs_info);
        
        view = (Button) findViewById(R.id.view);
        view.setVisibility(View.GONE);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Find selected datalog
                String datalog = "";
                String datalogDirectory = ApplicationSettings.INSTANCE.getDataDir().getAbsolutePath();
                for (int i = 0; i < datalogsList.getAdapter().getCount(); i++)
                {
                    if (datalogsList.isItemChecked(i))
                    {
                        datalog = datalogDirectory + "/" + datalogsList.getItemAtPosition(i).toString();
                        break;
                    }
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
                for (int i = 0; i < datalogsList.getAdapter().getCount(); i++)
                {
                    if (datalogsList.isItemChecked(i))
                    {
                        paths.add(datalogDirectory + "/" + datalogsList.getItemAtPosition(i).toString());
                    }
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
                for (int i = 0; i < datalogsList.getAdapter().getCount(); i++)
                {
                    if (datalogsList.isItemChecked(i))
                    {
                        datalogsToDelete.add(datalogsList.getItemAtPosition(i).toString());
                    }
                }
                
                for (String datalogFilename : datalogsToDelete)
                {                    
                    // Physically delete the file
                    File fileToDelete = new File(datalogDirectory + "/" + datalogFilename);
                    boolean deleteResult = fileToDelete.delete();
                    
                    if (!deleteResult)
                    {
                        DebugLogManager.INSTANCE.log("Couldn't delete " + datalogDirectory + "/" + datalogFilename,Log.ERROR);
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
        
        mDatalogsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice);        
        File datalogDirectory = ApplicationSettings.INSTANCE.getDataDir();

        class DatalogFilter implements FilenameFilter {
            public boolean accept(File dir, String name) {
                return name.endsWith(".msl");
            }
        }
        
        File[] datalogs = datalogDirectory.listFiles(new DatalogFilter());
        
        if (datalogs.length > 0)
        {                   
            long datalogsSize = 0;
            
            for (File datalog : datalogs)
            {                         
                mDatalogsArrayAdapter.add(datalog.getName());
                
                datalogsSize += datalog.length();
            }
                    
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
            view.setVisibility(View.GONE);
            sendByEmail.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }
    }
    
    /**
     * When an item in the listview (AKA datalog) is clicked
     * 
     * @param l
     * @param v
     * @param position
     * @param id
     */
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
        int nbChecked = 0;
        
        for (int i = 0; i < l.getAdapter().getCount(); i++)
        {
            if (l.isItemChecked(i))
            {
                nbChecked++;
            }
        }
        
        // If more then one datalog is checked, make send by email button visible
        if (nbChecked > 0) 
        {
            sendByEmail.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            
            if (nbChecked == 1)
            {
                view.setVisibility(View.VISIBLE);
            }
            else
            {
                view.setVisibility(View.GONE);
            }
        }
        else 
        {
            sendByEmail.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
    }

}
