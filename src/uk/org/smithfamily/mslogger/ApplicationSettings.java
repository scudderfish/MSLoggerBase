package uk.org.smithfamily.mslogger;

import java.io.File;

import uk.org.smithfamily.mslogger.comms.MsComm;
import uk.org.smithfamily.mslogger.comms.SerialComm;
import uk.org.smithfamily.mslogger.ecuDef.MS1Extra29y;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

public enum ApplicationSettings
{
    INSTANCE;

    private static final String NONE_SELECTED   = "NONE_SELECTED";
    public static final String  GENERAL_MESSAGE = "uk.org.smithfamily.mslogger.GENERAL_MESSAGE";
    public static final String  MESSAGE         = "uk.org.smithfamily.mslogger.MESSAGE";
    public static final String  TAG             = "uk.org.smithfamily.mslogger";
    private Context             context;
    private File                dataDir;
    private int                 hertz;
    private SharedPreferences   prefs;
    private Megasquirt          ecuDefinition;
    private MsComm              comms;
    private String              bluetoothMac;
    private Handler             handler;

    public void initialise(Context context, Handler handler)
    {
        this.context = context;
        this.handler = handler;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        dataDir = new File(Environment.getExternalStorageDirectory(), prefs.getString("DataDir",
                context.getString(R.string.app_name)));
        dataDir.mkdirs();
        this.hertz = prefs.getInt(context.getString(R.string.hertz), 20);

        comms = new SerialComm();

    }

    public File getDataDir()
    {
        return dataDir;
    }

    public Context getContext()
    {
        return context;
    }

    public int getHertz()
    {
        return hertz;
    }

    public synchronized Megasquirt getEcuDefinition()
    {
        if (ecuDefinition != null)
        {
            return ecuDefinition;
        }
        String ecuName = prefs.getString("mstype", "MS1Extra");
        if (ecuName.equals("MS1Extra"))
        {
            ecuDefinition = new MS1Extra29y(context);
        }
        return ecuDefinition;
    }

    public synchronized String getBluetoothMac()
    {
        if (bluetoothMac != null)
        {
            return bluetoothMac;
        }
        bluetoothMac = prefs.getString("bluetooth_mac", NONE_SELECTED);
        if (NONE_SELECTED.equals(bluetoothMac))
        {
            Toast.makeText(context, "Please select your serial Bluetooth dongle", Toast.LENGTH_LONG).show();
        }
        return bluetoothMac;
    }

    public MsComm getComms()
    {
        return comms;
    }

    // This method mimics the C style preprocessor of INI files
    public boolean isSet(String name)
    {
        if (prefs.getString("mstype", "NotThisOne").equals(name))
            return true;

        if (prefs.getString("maptype", "NotThisOne").equals(name))
            return true;

        if (prefs.getString("egotype", "NotThisOne").equals(name))
            return true;

        return false;
    }
}
