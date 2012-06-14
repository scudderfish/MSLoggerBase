package uk.org.smithfamily.mslogger.widgets;

import java.io.*;
import java.util.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.util.Log;

public enum GaugeRegister
{
    INSTANCE;
    private Map<String, GaugeDetails> details       = new HashMap<String, GaugeDetails>();
    private static final String       GAUGE_DETAILS = "gaugedetails";

    public void resetAll()
    {
        for (String name : details.keySet())
        {
            GaugeDetails gd = details.get(name);
            if (gd != null)
            {
                File store = getFileStore(gd);
                if (store != null)
                {
                    store.delete();
                }
            }
        }
        details.clear();
        Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();
        if (ecuDefinition != null)
        {
            ecuDefinition.initGauges();
        }
    }

    public void addGauge(GaugeDetails gaugeDetails)
    {
        if (details.containsKey(gaugeDetails.getName()))
            return;

        GaugeDetails tmp = loadDetails(gaugeDetails);
        if (tmp != null)
        {
            gaugeDetails = tmp;
        }
        else
        {
            persistDetails(gaugeDetails);
        }
        DebugLogManager.INSTANCE.log("Adding gauge : " + gaugeDetails,Log.INFO);

        details.put(gaugeDetails.getName(), gaugeDetails);
    }

    public void reset(String nme)
    {
        GaugeDetails gd = details.get(nme);
        if (gd != null)
        {
            File store = getFileStore(gd);
            if (store != null)
            {
                store.delete();
            }
            details.remove(nme);

            Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();
            if (ecuDefinition != null)
            {
                ecuDefinition.initGauges();
            }
        }
    }

    public GaugeDetails getGaugeDetails(String nme)
    {
        return details.get(nme);
    }

    public Set<String> getGaugeNames()
    {
        return details.keySet();
    }

    public void flush()
    {
        details = new HashMap<String, GaugeDetails>();
    }

    private GaugeDetails loadDetails(GaugeDetails gd)
    {
        try
        {
            File input = getFileStore(gd);

            // Make sure file exists before trying to read it
            if (input.isFile()) {            
                // Read from disk using FileInputStream.
                FileInputStream f_in = new FileInputStream(input);
    
                // Read object using ObjectInputStream.
                ObjectInputStream obj_in = new ObjectInputStream(f_in);
    
                // Read an object.
                Object obj = obj_in.readObject();
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

    private File getFileStore(GaugeDetails gd)
    {
        String name = getStoreName(gd);
        File dir = new File(ApplicationSettings.INSTANCE.getDataDir(), GAUGE_DETAILS);
        dir.mkdirs();
        File input = new File(dir, name);
        return input;
    }

    public void persistDetails(GaugeDetails gd)
    {
        try
        {
            File dir = new File(ApplicationSettings.INSTANCE.getDataDir(), GAUGE_DETAILS);
            dir.mkdirs();
            File output = new File(dir, getStoreName(gd));
            FileOutputStream f_out = new FileOutputStream(output);
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

            obj_out.writeObject(gd);
            details.put(gd.getName(), gd);
        }
        catch (IOException e)
        {

        }
    }

    private String getStoreName(GaugeDetails gd)
    {
        return ApplicationSettings.INSTANCE.getEcuDefinition().getClass().getName() + "." + gd.getName();
    }
}
