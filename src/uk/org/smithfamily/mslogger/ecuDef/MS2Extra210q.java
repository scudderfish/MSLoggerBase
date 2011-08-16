package uk.org.smithfamily.mslogger.ecuDef;

import android.content.Context;
import uk.org.smithfamily.mslogger.comms.LostCommsException;

public class MS2Extra210q extends Megasquirt
{

    private byte[] ochCommand;
    private byte[] sigCommand;

    public MS2Extra210q(Context c)
    {
        super(c);
    }

    @Override
    public String getSignature()
    {
        return "MS2Extra Rel 2.1.0q";
    }

    @Override
    public byte[] getOchCommand()
    {
        return ochCommand;
    }

    @Override
    public byte[] getSigCommand()
    {
        return sigCommand;
    }

    @Override
    public void loadConstants() throws LostCommsException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void calculate(byte[] ochBuffer) throws LostCommsException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public String getLogHeader()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLogRow()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getBlockSize()
    {
        return 145;
    }

    @Override
    public int getPageActivationDelay()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getInterWriteDelay()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
