package uk.org.smithfamily.mslogger.ecu.simulated;

import java.io.*;

public class MS1Simulator extends MSSimulator
{
    // Prepopulated FRD file from a previous run
    static final String fileName = "20110710095409.frd";

    private static final String TAG = "MS1Simulator";

    String signature = "MS1/Extra format 029y3 *********";

    private static final boolean simulateData = true;
    private static int cycleCounter = 0;

    @Override
    void process(final int b, final InputStream is, final OutputStream os) throws IOException
    {
        // Log.i(TAG, "received " + b);
        switch (b)
        {
        case 'S':
            speedLimitedWrite(os, signature.getBytes());
            break;

        case 'R':
            speedLimitedWrite(os, getNextPageOfVars());
            break;
        case 'P':
            final int pageIndex = is.read();
            final int fetch = is.read();
            if (fetch == 'V')
            {
                speedLimitedWrite(os, getFirmwarePage(getPageNo(pageIndex)));
            }
            break;
        // TODO: add page write processing
        }
    }

    private int getPageNo(final int pageIndex)
    {
        switch (pageIndex)
        {
        case 1:
            return 1;
        case 2:
            return 2;
        case 3:
            return 3;
        case 0:
            return 4;
        case 4:
            return 5;
        case 5:
            return 6;
        case 6:
            return 7;
        case 7:
            return 8;
        case 8:
            return 9;
        default:
            return 1;
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
        return "uk.org.smithfamily.mslogger.ecuDef.gen.Msns_extra29y.firmware";
    }

    @Override
    String getFRDFilename()
    {
        return fileName;
    }

    @Override
    int getBaudRate()
    {
        // Should really be 9600, but keep it nearer to what BT does.
        return 5000;
    }

    @Override
    int getFirmwarePageSize(final int pageNo)
    {
        // All MS1 pages are the same size
        return 189;
    }

    @Override
    protected byte[] getNextPageOfVars() throws IOException
    {
        cycleCounter++;
        final byte[] buffer = super.getNextPageOfVars();
        if (simulateData)
        {
            buffer[22] = (byte) (buffer[22] + ((byte) (cycleCounter % 32) - 3)); // RpmHiRes
            buffer[6] = (byte) (buffer[6] + ((cycleCounter % 17) - 2)); // Coolant
            buffer[9] = (byte) ((buffer[9] + (cycleCounter % 8)) - 4);// egoADC
            buffer[4] = (byte) ((buffer[4] + (cycleCounter % 10)) - 4);// mapADC
            buffer[5] = (byte) ((buffer[5] + (cycleCounter % 6)) - 4);// matADC
        }
        return buffer;
    }

}
