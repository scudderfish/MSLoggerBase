package uk.org.smithfamily.mslogger.ecuDef;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.GPSLocationManager;

/**
 * Megasquirt utilities class heavily used in the ECU definition Java class
 */
public enum MSUtils implements MSUtilsInterface
{
    INSTANCE;
    public int getLong(byte[] ochBuffer, int i)
    {
        return getWord(ochBuffer, i) * 65536 + getWord(ochBuffer, i + 2);
    }

    public int getWord(byte[] ochBuffer, int i)
    {
        return (ochBuffer[i] << 8) | getByte(ochBuffer,i + 1);
    }

    public int getByte(byte[] ochBuffer, int i)
    {
        return (int) ochBuffer[i] & 0xFF;
    }

    public int getSignedLong(byte[] ochBuffer, int i)
    {
        int x = getLong(ochBuffer, i);
        if (x > 2 << 32 - 1)
        {
            x = 2 << 32 - x;
        }
        return x;
    }

    public int getSignedByte(byte[] ochBuffer, int i)
    {
        int x = getByte(ochBuffer, i);
        if (x > 127)
        {
            x = 256 - x;
        }
        return x;
    }

    public int getSignedWord(byte[] ochBuffer, int i)
    {
        int x = getWord(ochBuffer, i);
        if (x > 32767)
        {
            x = 32768 - x;
        }
        return x;
    }
    
    public int getBits(byte[] pageBuffer, int i, int _bitLo, int _bitHi,int bitOffset)
    {
        int val = 0;
        byte b = pageBuffer[i];

        long mask = (long) ((1L << (_bitHi - _bitLo + 1)) - 1) << _bitLo;
        val = (int) ((b & mask) >> _bitLo) + bitOffset;

        return val;
    }
    
    /**
     * @return The latitude of the last known location
     */
    public double getLatitude()
    {
        return GPSLocationManager.INSTANCE.getLastKnownLocation().getLatitude();
    }
    
    /**
     * @return The longitude of the last known location
     */
    public double getLongitude()
    {
        return GPSLocationManager.INSTANCE.getLastKnownLocation().getLongitude();
    }
    
    /**
     * @return The speed of the last known location
     */
    public double getSpeed()
    {
        return GPSLocationManager.INSTANCE.getLastKnownLocation().getSpeed();
    }
    
    /**
     * @return The bearing of the last known location
     */
    public double getBearing()
    {
        return GPSLocationManager.INSTANCE.getLastKnownLocation().getBearing();
    }
    
    /**
     * @return The accuracy of the last known location
     */
    public double getAccuracy()
    {
        return GPSLocationManager.INSTANCE.getLastKnownLocation().getAccuracy();
    }
    
    /**
     * @return The time of the last known location
     */
    public long getTime()
    {
        return GPSLocationManager.INSTANCE.getLastKnownLocation().getTime();
    }

    /**
     * @return The location header used in datalog
     */
    public String getLocationLogHeader()
    {
    	String s = "Latitude\tLongitude\tSpeed (m/s)\tHeading\tAccuracy\tGPSTime\tGPS_Update";
    	if (ApplicationSettings.INSTANCE.isDeviceTimeEnabled()) 
    	{
    		s +="\tDeviceTime";
    	}
        return s;
    }
    
    /**
     * @return A datalog row of location information
     */
    public String getLocationLogRow()
    {
    	String s = getLatitude() + "\t" + 
    			   getLongitude() + "\t" +
    			   getSpeed() + "\t" +
    			   getBearing() + "\t" +
    			   getAccuracy() + "\t" +
    			   getTime() +"\t"+
    			   getGPSUpdate();

    	if (ApplicationSettings.INSTANCE.isDeviceTimeEnabled()) 
    	{
            long time = System.currentTimeMillis();
    		s +="\t"+time;
    	}
        return s;
    }

    private int getGPSUpdate()
    {
        return GPSLocationManager.INSTANCE.getFreshness();
    }
    
    /**
     * Take an MS command like this one 119,0,4,0,20,0,2,0,250 (to set cranking RPM to 250rpm on MS2Extra)
     * and convert it to byte array that can be used with Megasquirt.writeCommand()
     * 
     * @param command The command to convert to byte array
     * @return A byte array with every byte of the command
     */
    public byte[] commandStringtoByteArray(String command)
    {
        String[] split = command.split(",");
        
        byte[] bytes = new byte[split.length];
        
        for (int i = 0; i < split.length; i++)
        {
            bytes[i] = Byte.parseByte(split[i]);
        }
        
        return bytes;
    }
    
    /**
     * Round a double number to a specific number of decimals
     * 
     * @param number The number to round
     * @param decimals The number of decimals to keep
     * 
     * @return The rounded number
     */
    public double roundDouble(double number, int decimals)
    {
        double p = (double) Math.pow(10, decimals);
        number = number * p;
        double tmp = Math.round(number);
        return tmp / p;
    }
}
