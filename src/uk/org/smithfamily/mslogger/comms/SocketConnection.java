package uk.org.smithfamily.mslogger.comms;

import java.io.*;

import uk.org.smithfamily.mslogger.ecu.simulated.MS1Simulator;

public enum SocketConnection implements IConnection
{
    INSTANCE;

    private MS1Simulator sim = new MS1Simulator();
    @Override
    public void init()
    {
        sim.init();
        sim.startRunning();
        
    }

    @Override
    public boolean isInitialised()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void connect() throws IOException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void disconnect() throws IOException
    {
        sim.stopRunning();
        
    }

    @Override
    public void switchSettings()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public InputStream getInputStream() throws IOException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OutputStream getOutputStream() throws IOException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void tearDown()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isConnected()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean connectionPossible()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean connectionEnabled()
    {
        // TODO Auto-generated method stub
        return false;
    }
}
