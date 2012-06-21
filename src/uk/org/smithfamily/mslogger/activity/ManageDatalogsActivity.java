package uk.org.smithfamily.mslogger.activity;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.log.EmailManager;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
                // Create array of datalogs path to send
                String datalogDirectory = ApplicationSettings.INSTANCE.getDataDir().getAbsolutePath();
                for (int i = 0; i < datalogsList.getAdapter().getCount(); i++)
                {
                    if (datalogsList.isItemChecked(i))
                    {
                        // Physically delete the file
                        File fileToDelete = new File(datalogDirectory + "/" + datalogsList.getItemAtPosition(i).toString());
                        fileToDelete.delete();
                        
                        // Clear the adapter
                        mDatalogsArrayAdapter.clear();
                        
                        // Refill datalogs listview
                        fillDatalogsListView();
                    }
                }
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
        
        TextView noDatalogMessage = (TextView) findViewById(R.id.no_datalog_found);
        
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
            System.out.println("hehe2");
            for (File datalog : datalogs)
            {                         
                mDatalogsArrayAdapter.add(datalog.getName());
            }
                    
            datalogsList.setAdapter(mDatalogsArrayAdapter);
            
            noDatalogMessage.setVisibility(View.GONE);
            datalogsList.setVisibility(View.VISIBLE);
        }
        // No datalog found, showing message instead
        else
        {            
            noDatalogMessage.setVisibility(View.VISIBLE);
            datalogsList.setVisibility(View.GONE);
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
