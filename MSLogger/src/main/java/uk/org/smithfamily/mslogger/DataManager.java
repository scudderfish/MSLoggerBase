package uk.org.smithfamily.mslogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.OutputChannel;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;

public class DataManager extends Observable
{
    private final Map<String, OutputChannel> dataChannels = new HashMap<>();
    private static final DataManager instance = new DataManager();
    private boolean disabled = true;

    private DataManager()
    {
        final IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
        final IntentFilter connectionFilter = new IntentFilter(Megasquirt.CONNECTED);
        final IntentFilter disconnectionFilter = new IntentFilter(Megasquirt.DISCONNECTED);
        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final String action = intent.getAction();
                switch (action) {
                    case Megasquirt.NEW_DATA:
                        disabled = false;
                        break;
                    case Megasquirt.CONNECTED:
                        disabled = false;
                        break;
                    case Megasquirt.DISCONNECTED:
                        disabled = true;
                        break;
                }
                tickle();
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ApplicationSettings.INSTANCE.getContext().registerReceiver(mReceiver, dataFilter,Context.RECEIVER_NOT_EXPORTED);
            ApplicationSettings.INSTANCE.getContext().registerReceiver(mReceiver, connectionFilter,Context.RECEIVER_NOT_EXPORTED);
            ApplicationSettings.INSTANCE.getContext().registerReceiver(mReceiver, disconnectionFilter,Context.RECEIVER_NOT_EXPORTED);
        }else {
            ApplicationSettings.INSTANCE.getContext().registerReceiver(mReceiver, dataFilter);
            ApplicationSettings.INSTANCE.getContext().registerReceiver(mReceiver, connectionFilter);
            ApplicationSettings.INSTANCE.getContext().registerReceiver(mReceiver, disconnectionFilter);
        }
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
            DebugLogManager.INSTANCE.log("Invalid output channel " + channelName, Log.DEBUG);
            return value;
        }
        value = oc.getValue();
        return value;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

}
