package uk.org.smithfamily.mslogger.ecuDef;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.content.res.AssetManager;

public enum TableManager
{
	INSTANCE;

	private Map<String, List<Integer>>	tables	= new HashMap<String, List<Integer>>();

	public synchronized void flushTable(String name)
	{
		tables.remove(name);
	}

	public synchronized int table(int i1, String name)
	{
		List<Integer> table = tables.get(name);
		if (table == null)
		{
			table = new ArrayList<Integer>();
			readTable(name, table);
			tables.put(name, table);
		}
		return table.get(i1);

	}

	private void readTable(String fileName, List<Integer> values)
	{
		values.clear();
		Pattern p = Pattern.compile("\\s*[Dd][BbWw]\\s*(\\d*).*");

		String assetFileName = "tables" + File.separator + fileName;
		File  override=new File(ApplicationSettings.INSTANCE.getDataDir(),fileName);
		AssetManager assetManager = ApplicationSettings.INSTANCE.getContext().getResources().getAssets();

		BufferedReader input = null;
		try
		{
			try
			{
				InputStream data = null;
				if(override.canRead())
				{
					data = new FileInputStream(override);
				}
				else
				{
					data = assetManager.open(assetFileName);
				}
				
				input = new BufferedReader(new InputStreamReader(data));
				String line;

				while ((line = input.readLine()) != null)
				{
					Matcher matcher = p.matcher(line);
					if (matcher.matches())
					{
						String num = matcher.group(1);

						if (num != null)
						{
							values.add(Integer.valueOf(num));
						}
					}
				}

			}
			finally
			{
				if (input != null)
					input.close();
			}

		}
		catch (IOException e)
		{
			DebugLogManager.INSTANCE.logException(e);
		}
	}

}
