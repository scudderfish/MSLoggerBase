package uk.org.smithfamily.mslogger.widgets;

import java.io.*;
import java.util.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.util.Log;

/**
 * Enum that contains all the indicators details for the MegaSquirt firmware
 */
public enum GaugeRegister implements GaugeRegisterInterface
{
    INSTANCE;
    //Map of guages as defined by the ECU
    private Map<String, GaugeDetails> details       = new HashMap<String, GaugeDetails>();
    //Map of gauges actually used.  Prefer defn from disk to ECU
    private Map<String, GaugeDetails> instantiated  = new HashMap<String, GaugeDetails>();
    private static final String       GAUGE_DETAILS = "PersistedGauges";

    /**
     * Reset all gauges to their default state contained in the firmware INI file specifications
     */
    public void resetAll()
    {
        File dir = new File(ApplicationSettings.INSTANCE.getDataDir(), GAUGE_DETAILS);
        
        if (dir.isDirectory())
        {
            File[] fGaugeDetails = dir.listFiles();
            
            for (File gaugeDetails : fGaugeDetails)
            {
                boolean deleteResult = gaugeDetails.delete();
                if (!deleteResult)
                {
                    DebugLogManager.INSTANCE.log("Couldn't delete " + gaugeDetails.getPath(),Log.ERROR);
                }
            }
        }
        instantiated.clear();
        details.clear();
        Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();
        if (ecuDefinition != null)
        {
            ecuDefinition.initGauges();
        }
    }

    /**
     * Add an indicator to te list
     * 
     * @param gaugeDetails All the indicator details
     */
    public void addGauge(GaugeDetails gaugeDetails)
    {
        if (details.containsKey(gaugeDetails.getName()))
            return;
     
        DebugLogManager.INSTANCE.log("Adding gauge : " + gaugeDetails,Log.INFO);

        details.put(gaugeDetails.getName(), gaugeDetails);
    }

    /**
     * Reset the specific indicator name to firmware INI file specifications
     * 
     * @param name The name of the indicator to reset
     */
    public void reset(String name)
    {
        GaugeDetails gd = details.get(name);
        if (gd != null)
        {
            File store = getFileStore(name);
            if (store != null)
            {
                boolean deleteResult = store.delete();
                if (!deleteResult)
                {
                    DebugLogManager.INSTANCE.log("Couldn't delete " + store.getPath(),Log.ERROR);
                }
            }
            instantiated.remove(name);
            details.remove(name);

            Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();
            if (ecuDefinition != null)
            {
                ecuDefinition.initGauges();
            }
        }
    }

    /**
     * Get details for a specific indicator.  
     * If it is a gauge we have already instantiated, return that.
     * If the gauge exists on disk, return that
     * Else return the gauge as defined by the ECU
     * 
     * @param name Name of the indicator to get details for
     * @return An instance of GaugeDetails
     */
    public GaugeDetails getGaugeDetails(String name)
    {
        GaugeDetails result = instantiated.get(name);
        if(result != null)
        {
            return result;
        }
        
        GaugeDetails tmp = loadDetails(name);
        if (tmp != null)
        {
            result = tmp;
            
        }
        else
        {
            result = details.get(name);
            
        }
        instantiated.put(name, result);
        
        return result;
    }

    /**
     * @return All the indicators names
     */
    public Set<String> getGaugeNames()
    {
        return details.keySet();
    }

    /**
     * Flush all the gauge details from the hash map
     */
    public void flush()
    {
        details = new HashMap<String, GaugeDetails>();
    }

    /**
     * Load indicator details from file
     * 
     * @param gd The GaugeDetails to get details from
     * @return An instance of GaugeDetails
     */
    private GaugeDetails loadDetails(String name)
    {
        try
        {
            File input = getFileStore(name);

            // Make sure file exists before trying to read it
            if (input.isFile()) {
                // Read object using ObjectInputStream
                ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(input));
    
                // Read an object
                Object obj = objIn.readObject();
                
                // Close the stream
                objIn.close();
                
                if (obj instanceof GaugeDetails)
                {    
                    return (GaugeDetails) obj;
                }
                else
                {
                    return null;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {

        }
        
        return null;
    }

    /**
     * Return a file handle on the file store where the indicator details are
     * 
     * @param gd GaugeDetails to get file store for
     * @return An handle on the file store
     */
    private File getFileStore(String gaugeName)
    {
        String name = getStoreName(gaugeName);
        File dir = new File(ApplicationSettings.INSTANCE.getDataDir(), GAUGE_DETAILS);
        
        if (!dir.exists()) 
        {
            boolean mkDirs = dir.mkdirs();
            if (!mkDirs)
            {
                DebugLogManager.INSTANCE.log("Unable to create directory " + GAUGE_DETAILS + " at " + ApplicationSettings.INSTANCE.getDataDir(), Log.ERROR);
            }
        }
        
        File input = new File(dir, name);
        return input;
    }

    /**
     * Save the current gauge details into a file
     * 
     * @param gd The GaugeDetails to save details for
     */
    public void persistDetails(GaugeDetails gd)
    {
        try
        {
            File dir = new File(ApplicationSettings.INSTANCE.getDataDir(), GAUGE_DETAILS);
           
            if (!dir.exists()) 
            {
                boolean mkDirs = dir.mkdirs();
                if (!mkDirs)
                {
                    DebugLogManager.INSTANCE.log("Unable to create directory " + GAUGE_DETAILS + " at " + ApplicationSettings.INSTANCE.getDataDir(), Log.ERROR);
                }
            }
            
            File output = new File(dir, getStoreName(gd.getName()));
            FileOutputStream fOut = new FileOutputStream(output);
            ObjectOutputStream objOut = new ObjectOutputStream(fOut);

            objOut.writeObject(gd);
            details.put(gd.getName(), gd);
            
            objOut.close();
        }
        catch (IOException e)
        {

        }
    }

    /**
     * Get the file name where the indicator details are stored
     * 
     * @param gd GaugeDetails to get the file name of
     * @return The file name for the specified indicator details
     */
    private String getStoreName(String gaugeName)
    {
        return ApplicationSettings.INSTANCE.getEcuDefinition().getClass().getName() + "." + gaugeName;
    }
}
