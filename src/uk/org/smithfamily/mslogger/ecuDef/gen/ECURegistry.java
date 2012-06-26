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
        registerEcu(GPIO_MShift_2109.class,"MShift 2.109       \0");
        registerEcu(MS2Extra310.class,"MS2Extra Serial310 \0");
        registerEcu(MS2Extra311mb_v12.class,"MS2Extra 311 mb_v12\0");
        registerEcu(MS2ExtraSerial312a.class,"MS2Extra Serial312a\0");
        registerEcu(MS2Extra_Rel_201.class,"MS2Extra Rel 2.0.0 \0");
        registerEcu(MS2Extra_Rev_102.class,"MS2Extra Rev 1.0.2 \0");
        registerEcu(MS3Format0095002_102.class,"MS3 Format 0095.002\0");
        registerEcu(MS3_Format_0221002.class,"MS3 Format 0221.002\0");
        registerEcu(MSExtra_format_hr_10.class,"MS/Extra format hr_10 **********");
        registerEcu(MSExtra_format_hr_11.class,"MS/Extra format hr_11b *********");
        registerEcu(MSExtra_format_hr_11d.class,"MS/Extra format hr_11d  ********");
        registerEcu(Megasquirt_I.class,"20");
        registerEcu(Megasquirt_II.class,"MSII Rev 2.30000   \0");
        registerEcu(Megasquirt_II286.class,"MSII Rev 2.88600   \0");
        registerEcu(Megasquirt_II288.class,"MSII Rev 2.88000   \0");
        registerEcu(Megasquirt_II2885.class,"MSII Rev 2.88500   \0");
        registerEcu(Megasquirt_II2886.class,"MSII Rev 2.88600   \0");
        registerEcu(Megasquirt_II_3760.class,"MSII Rev 3.76000   \0");
        registerEcu(Megasquirt_II_v2905.class,"MSII Rev 2.90500   \0");
        registerEcu(Megasquirt_I_B_G_20.class,"20");
        registerEcu(Megasquirt_I_B_G_20_30.class,"20");
        registerEcu(Megasquirt_iims2extra.class,"MS2Extra Serial310 \0");
        registerEcu(Ms2Extra303r.class,"MS2Extra Rel 3.0.3r\0");
        registerEcu(Ms2extra21p.class,"MS2Extra Rel 2.1.0p\0");
        registerEcu(Ms2extra21q.class,"MS2Extra Rel 2.1.0q\0");
        registerEcu(Ms2extra_210d.class,"MS2Extra Rel 2.1.0q\0");
        registerEcu(Ms2extra_303s.class,"MS2Extra Rel 3.0.3s\0");
        registerEcu(Ms2extra_303u.class,"MS2Extra Rel 3.0.3s\0");
        registerEcu(MS2Extra_SerialGS26.class,"MS2Extra SerialGS26\0");
        registerEcu(Ms3.class,"MS3 Format 0081.001\0");
        registerEcu(Ms3_pre11beta20.class,"MS3 Format 0233.003\0");
        registerEcu(Ms3_pre11rc1.class,"MS3 Format 0238.002\0");
        registerEcu(Msextra_hr.class,"MS/Extra format hr_10 **********");
        registerEcu(Msns_extra.class,"MSnS-extra format 027c *********");
        registerEcu(Msns_extra029q_29w.class,"MSnS-extra format 029q *********");
        registerEcu(Msns_extra29y.class,"MS1/Extra format 029y3 *********");
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
