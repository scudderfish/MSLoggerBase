package uk.org.smithfamily.mslogger.ecu.simulated;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MS1Simulator implements Runnable
{

    public static final int SERVERPORT = 1052;
    private ServerSocket serverSocket;
    private volatile boolean running = false;
    String signature = "MS1/Extra format 029y3 *********";
    
    @Override
    public void run()
    {
       
        running = true;
        while (running)
        {
            try
            {
                Socket client = serverSocket.accept();
            
                InputStream is = client.getInputStream();
                OutputStream os = client.getOutputStream();
            
                while(running)
                {
                    int b = is.read();
                    process(b,is,os);
                }
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private void process(int b, InputStream is, OutputStream os) throws IOException
    {
        switch(b)
        {
        case 'S':
            os.write(signature.getBytes());
            break;
            
        case 'R':
            os.write(getNextPageOfVars());
            break;
        case 80:
            int pageNo = is.read();
            int fetch = is.read();
            if(fetch==86)
            {
                os.write(getFirmwarePage(pageNo));
            }
            break;
        }
        
    }

    private byte[] getFirmwarePage(int pageNo)
    {
        // TODO Auto-generated method stub
        return null;
    }

    private byte[] getNextPageOfVars()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void init()
    {
        try
        {
            serverSocket = new ServerSocket(SERVERPORT);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void startRunning()
    {
        // TODO Auto-generated method stub

    }

    public void stopRunning()
    {
        // TODO Auto-generated method stub

    }

}
