package uk.org.smithfamily.mslogger;

import java.io.File;

import uk.org.smithfamily.mslogger.comms.MsComm;
import uk.org.smithfamily.mslogger.comms.SerialComm;
import uk.org.smithfamily.mslogger.ecuDef.MS1Extra29y;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

public enum ApplicationSettings
{
	INSTANCE;
	
	public static final String GENERAL_MESSAGE="uk.org.smithfamily.mslogger.GENERAL_MESSAGE";
	public static final String MESSAGE="uk.org.smithfamily.mslogger.MESSAGE";
	private Context	context;
	private File	dataDir;
	private int	hertz;
	private SharedPreferences prefs;
	private Megasquirt ecuDefinition;
	private MsComm	comms;
	public void initialise(Context context)
	{
		this.context = context;
		
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

        dataDir = new File(Environment.getExternalStorageDirectory(), prefs.getString("DataDir", context.getString(R.string.app_name)));
        dataDir.mkdirs();
        this.hertz = prefs.getInt(context.getString(R.string.hertz), 10);
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
		if(ecuDefinition != null)
		{
			return ecuDefinition;
		}
		String ecuName = prefs.getString("mstype", "MS1Extra");
		if(ecuName.equals("MS1Extra"))
		{
			ecuDefinition = new MS1Extra29y(context);
		}
		return ecuDefinition;
	}
	public MsComm getComms()
	{
		return comms;
	}
}
