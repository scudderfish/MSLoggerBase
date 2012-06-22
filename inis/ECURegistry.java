package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;

public enum ECURegistry
{
    INSTANCE;
    
    private Map<String,Class<? extends Megasquirt> > ecus = new HashMap<String,Class<? extends Megasquirt> >();
    private Map<String,Megasquirt> instances = new HashMap<String,Megasquirt>();
    
    ECURegistry()
    {
<LIST>
    }

    private void registerEcu(Class<? extends Megasquirt> cls, String sig)
    {
        ecus.put(sig, cls);
    }
    
    public synchronized Megasquirt getECU(String sig,Context ctx)
    {
        Megasquirt ecu = instances.get(sig);
        if(ecu != null)
        {
            return ecu;
        }
        Class<? extends Megasquirt> c = ecus.get(sig);
        
        try
        {
            Constructor<? extends Megasquirt> constructor = c.getConstructor(Context.class);
            ecu = constructor.newInstance(ctx);
            instances.put(sig, ecu);
        }
        catch (Exception e)
        {
            // EEP!
            e.printStackTrace();
        }
        
        return ecu;
    }
}
