package uk.org.smithfamily.mslogger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import uk.org.smithfamily.mslogger.ecuDef.DataSource;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.OutputChannel;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class DataManager extends Observable
{
    private Map<String, OutputChannel> dataChannels = new HashMap<String, OutputChannel>();
    private static DataManager instance = new DataManager();

    private DataManager()
    {

        IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
        ApplicationSettings.INSTANCE.getContext().registerReceiver(mReceiver, dataFilter);

    }

    public static DataManager getInstance()
    {
        return instance;
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            setChanged();
            notifyObservers();
        }
    };

    public void addOutputChannel(OutputChannel o)
    {
        dataChannels.put(o.getName(), o);
    }

    public double getField(String channelName)
    {
        
        double value = 0;
        OutputChannel oc = dataChannels.get(channelName);
        if(oc == null)
        {
            return value;
        }
        DataSource source = oc.getSource();
        Class<?> c = source.getClass();
        try
        {
            Field f = c.getDeclaredField(channelName);
            value = f.getDouble(source);
        }
        catch (Exception e)
        {
            DebugLogManager.INSTANCE.log("Failed to get value for " + channelName, Log.ERROR);
        }
        return value;
    }

}
