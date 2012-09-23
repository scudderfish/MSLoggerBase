package uk.org.smithfamily.mslogger.ecu.simulated;

import java.io.*;

import android.util.Log;

public class MS1Simulator extends MSSimulator
{

    //Prepopulated FRD file from a previous run
    static final String fileName = "2012-07-13_13.57.32.frd";
 
    private static final String TAG = "MS1Simulator";
    
    String signature = "MS1/Extra format 029y3 *********";

    @Override
    void process(int b, InputStream is, OutputStream os) throws IOException
    {
        Log.i(TAG, "received " + b);
        switch (b)
        {
        case 'S':
            speedLimitedWrite(os, signature.getBytes());
            break;

        case 'R':
            speedLimitedWrite(os, getNextPageOfVars());
            break;
        case 'P':
            int pageNo = is.read();
            int fetch = is.read();
            if (fetch == 'V')
            {
                speedLimitedWrite(os, getFirmwarePage(pageNo));
            }
            break;
            //TODO: add page write processing
        }
    }

    @Override
    String getSignature()
    {
        return signature;
    }

    @Override
    String getFirmwareFile()
    {
        return fileName;
    }

    @Override
    String getFRDFilename()
    {
        return "uk.org.smithfamily.mslogger.ecuDef.gen.Msns_extra29y.firmware";
    }

    @Override
    int getBaudRate()
    {  
        //Should really be 9600, but keep it nearer to what BT does.
        return 5000;
    }

    @Override
    int getFirmwarePageSize(int pageNo)
    {
        //All MS1 pages are the same size
        return 189;
    }

}
