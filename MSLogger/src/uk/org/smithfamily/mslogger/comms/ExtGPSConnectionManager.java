package uk.org.smithfamily.mslogger.comms;

// Licensed under LGPL http://www.gnu.org/copyleft/lesser.html

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Main connection class that wrap all the Bluetooth stuff that communicate with a External GPS
 */
public class ExtGPSConnectionManager extends ConnectionManager
{

    // Private constructor prevents instantiation from other classes
    private ExtGPSConnectionManager()
    {
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance() or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder
    {
        public static final ExtGPSConnectionManager INSTANCE = new ExtGPSConnectionManager();
    }

    public static ExtGPSConnectionManager getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String getInstanceName()
    {
        return "ExtGPSConnectionManager";
    }

    public InputStream getInputStream()
    {
        return mmInStream;
    }

    public OutputStream getOutputStream()
    {
        return mmOutStream;
    }

}
