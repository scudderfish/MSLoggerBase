package uk.org.smithfamily.mslogger;

import java.util.*;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.OutputChannel;
import android.content.*;

public class DataManager extends Observable
{
    private final Map<String, OutputChannel> dataChannels = new HashMap<String, OutputChannel>();
    private static DataManager instance = new DataManager();

    private DataManager()
    {
        final IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
        ApplicationSettings.INSTANCE.getContext().registerReceiver(mReceiver, dataFilter);
    }

    public static DataManager getInstance()
    {
        return instance;
    }

    public void tickle()
    {
        setChanged();
        notifyObservers();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(final Context context, final Intent intent)
        {
            tickle();
        }
    };
    
    public Map<String, OutputChannel> getOutputChannels()
    {
        return dataChannels;
    }

    public void addOutputChannel(final OutputChannel o)
    {
        dataChannels.put(o.getName(), o);
    }

    public double getField(final String channelName)
    {

        double value = 0;
        final OutputChannel oc = dataChannels.get(channelName);
        if (oc == null)
        {
            return value;
        }
        value = oc.getValue();
        return value;
    }

}
