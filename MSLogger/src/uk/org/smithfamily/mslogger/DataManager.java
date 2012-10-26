package uk.org.smithfamily.mslogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.OutputChannel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

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
        value = oc.getValue();
        return value;
    }

}
