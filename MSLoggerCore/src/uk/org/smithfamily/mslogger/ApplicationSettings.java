package uk.org.smithfamily.mslogger;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import uk.org.smithfamily.mslogger.ecuDef.*;
import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.preference.PreferenceManager;

public enum ApplicationSettings implements SharedPreferences.OnSharedPreferenceChangeListener
{
    INSTANCE;

    public static final String      MISSING_VALUE       = "f741d5b0-fbee-11e0-be50-0800200c9a66";
    public static final String      GENERAL_MESSAGE     = "uk.org.smithfamily.mslogger.GENERAL_MESSAGE";
    public static final String      MESSAGE             = "uk.org.smithfamily.mslogger.MESSAGE";
    public static final String      ECU_CHANGED         = "uk.org.smithfamily.mslogger.ECU_CHANGED";
    public static final String      TAG                 = "uk.org.smithfamily.mslogger";
    private Context                 context;
    private File                    dataDir;
    private int                     hertz;
    private SharedPreferences       prefs;
    private Megasquirt              ecuDefinition;
    private String                  bluetoothMac;
    private Boolean                 autoConnectOverride = null;

    private Map<String, Boolean>    settings            = new HashMap<String, Boolean>();
    private Boolean                 loggingOverride     = null;
    private BluetoothAdapter        defaultAdapter;
    private boolean                 writable;
    private Map<String, Megasquirt> sigMap;

    public void initialise(Context context)
    {
        this.context = context;
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.registerOnSharedPreferenceChangeListener(this);
        
        dataDir = new File(Environment.getExternalStorageDirectory(), prefs.getString("DataDir", context.getString(R.string.app_name)));
        dataDir.mkdirs();
        this.hertz = prefs.getInt(context.getString(R.string.hertz), 20);
        sigMap = new HashMap<String, Megasquirt>();

        registerEcu(new ZZGPIO_MShift_2110(context));
        registerEcu(new ZZJimstim21(context));
        registerEcu(new ZZMegasquirt_I_BG_20_30(context));
        registerEcu(new ZZMegasquirt_II_3760(context));
        registerEcu(new ZZMegasquirt_II_v2905(context));
        registerEcu(new ZZMegasquirt_II286(context));
        registerEcu(new ZZMS2Extra_Rel_201(context));
        registerEcu(new ZZMS2Extra_Rev_102(context));
        registerEcu(new ZZMS2Extra210(context));
        registerEcu(new ZZMS2Extra310(context));
        registerEcu(new ZZMS2Extra311mb_v12(context));
        registerEcu(new ZZMS2ExtraSerial312(context));
        registerEcu(new ZZMS3_Format_0221002(context));
        registerEcu(new ZZMS3_Format_0095002_102(context));
        registerEcu(new ZZMS3_Pre11_alpha15(context));
        registerEcu(new ZZMSExtra_format_hr_10(context));
        registerEcu(new ZZMSExtra_format_hr_11(context));
        registerEcu(new ZZMSExtra_format_hr_11d(context));
        registerEcu(new ZZMS1Extra29y(context));
    }

    private void registerEcu(Megasquirt ecu)
    {
        Set<String> sigs = ecu.getSignature();
        for (String sig : sigs)
        {
            sigMap.put(sig, ecu);
        }
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
        return ecuDefinition;
    }

    public synchronized String getBluetoothMac()
    {
        bluetoothMac = prefs.getString("bluetooth_mac", MISSING_VALUE);
        return bluetoothMac;
    }

    public synchronized void setBluetoothMac(String m)
    {
        Editor editor = prefs.edit();
        editor.putString("bluetooth_mac", m);
        editor.commit();
    }

    // This method mimics the C style preprocessor of INI files
    public boolean isSet(String name)
    {
        Boolean result = settings.get(name);
        if (result != null)
            return result;
        boolean val = false;

        String tempType = prefs.getString("temptype", MISSING_VALUE);
        if (!val && tempType.equals(name))
            val = true;

        if (!val && prefs.getString("mstype", MISSING_VALUE).equals(name))
            val = true;

        if (!val && prefs.getString("maptype", MISSING_VALUE).equals(name))
            val = true;

        if (!val && prefs.getString("egotype", MISSING_VALUE).equals(name))
            val = true;

        settings.put(name, val);
        return val;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        bluetoothMac = null;
        settings = new HashMap<String, Boolean>();
    }

    public boolean btDeviceSelected()
    {
        return !MISSING_VALUE.equals(getBluetoothMac());
    }

    public boolean emailEnabled()
    {
        return prefs.getBoolean("autoemail_enabled", false);
    }

    public String getEmailDestination()
    {
        return prefs.getString("autoemail_target", "");
    }

    public boolean autoConnectable()
    {
        boolean autoconnectpref = prefs.getBoolean("autoconnect", true);
        boolean btDeviceSelected = btDeviceSelected();
        return (autoConnectOverride == null || autoConnectOverride == true) && btDeviceSelected && autoconnectpref;
    }

    public String getPref(String name)
    {
    	String value = prefs.getString(name, MISSING_VALUE);
        if (value.equals(MISSING_VALUE))
        {
        	value = null;
        }
        return value;
    }
    public String getOrSetPref(String name, String def)
    {
        String value = prefs.getString(name, MISSING_VALUE);
        if (value.equals(MISSING_VALUE))
        {
            setPref(name,def);
            value = def;
        }
        return value;
    }
    public void setPref(String name,String value)
    {
        Editor editor = prefs.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public double getGaugeSetting(String gaugeName, String title, String var, double def)
    {
        return def;
    }

    public boolean isAutoConnectOverride()
    {
        return autoConnectOverride;
    }

    public void setAutoConnectOverride(Boolean autoConnectOverride)
    {
        this.autoConnectOverride = autoConnectOverride;
    }

    public boolean shouldBeLogging()
    {
        boolean shouldBeLogging = prefs.getBoolean("autolog", true);
        return (loggingOverride == null || loggingOverride == true) && shouldBeLogging;
    }

    public void setLoggingOverride(boolean b)
    {
        this.loggingOverride = b;

    }

    public BluetoothAdapter getDefaultAdapter()
    {
        return defaultAdapter;
    }

    public void setDefaultAdapter(BluetoothAdapter defaultAdapter)
    {
        this.defaultAdapter = defaultAdapter;
    }

    public void setWritable(boolean cardOK)
    {
        this.writable = cardOK;
    }

    public boolean isWritable()
    {
        return this.writable;
    }

    public Megasquirt getEcuForSig(String sig)
    {
        return sigMap.get(sig);
    }

    public void setEcu(Megasquirt ecu)
    {
        ecuDefinition = ecu;

        Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.ECU_CHANGED);
        context.sendBroadcast(broadcast);
    }

    public void refreshFlags()
    {
        settings.clear();
        
    }
    public boolean isBTWorkaround()
    {
    	return prefs.getBoolean("workaround", false);
    }
    public void setBTWorkaround(boolean state)
    {
        Editor editor = prefs.edit();
        editor.putBoolean("workaround", state);
        editor.commit();

    }

    public void resetECUs()
    {
        for(Entry<String, Megasquirt> e : sigMap.entrySet())
        {
            e.getValue().reset();
        }
        
    }
}
