package uk.org.smithfamily.mslogger.widgets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import uk.org.smithfamily.mslogger.ApplicationSettings;

public enum GaugeRegister
{
	INSTANCE;
	private Map<String, GaugeDetails>	details			= new HashMap<String, GaugeDetails>();
	private static final String			GAUGE_DETAILS	= "gaugedetails";

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

			ApplicationSettings.INSTANCE.getEcuDefinition().initGauges();
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
		catch (IOException e)
		{
		}
		catch (ClassNotFoundException e)
		{

		}
		return null;

	}

	private File getFileStore(GaugeDetails gd)
	{
		String name = ApplicationSettings.INSTANCE.getEcuDefinition().getClass().getName()+"."+gd.getName();
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
			File output = new File(dir, gd.getName());
			FileOutputStream f_out = new FileOutputStream(output);
			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

			obj_out.writeObject(gd);
		}
		catch (IOException e)
		{

		}
	}
}