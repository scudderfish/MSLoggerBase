package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.util.HashMap;
import java.util.Map;

import uk.org.smithfamily.mslogger.ecuDef.MSECUInterface;

public enum ECURegistry
{
    INSTANCE;
    
    private Map<String,Class<? extends MSECUInterface> > ecus = new HashMap<String,Class<? extends MSECUInterface> >();
    
    ECURegistry()
    {
<LIST>
    }

	private void registerEcu(Class<? extends MSECUInterface> cls, String sig)
	{
		ecus.put(sig, cls);
	}
	public Class<? extends MSECUInterface> findEcu(String sig)
	{
		Class<? extends MSECUInterface> ecu = ecus.get(sig);
		if (ecu != null)
		{
			return ecu;
		}
		for (int i = sig.length() - 1; i > sig.length() / 2 && i > 3
				&& ecu == null; i--)
		{
			String fuzzySig = sig.substring(0, i);
			for (String classSig : ecus.keySet())
			{
				if (classSig.startsWith(fuzzySig))
				{
					ecu = ecus.get(classSig);
				}
			}
		}
		return ecu;
	}
}
