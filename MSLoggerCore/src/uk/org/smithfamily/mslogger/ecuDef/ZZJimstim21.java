package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;

/*
 * Fingerprint : 38702dc563d6f78682413d299ba0a3c7
 */
public class ZZJimstim21 extends Megasquirt
{
    public ZZJimstim21(Context c)
    {
        super(c);
    }
    public void refreshFlags()
    {
        
    }
    byte[]              queryCommand  = new byte[] { 'Q' };
    String              signature     = "JimStim format V2.0.3 *********";
    byte[]              ochGetCommand = new byte[] { 'A' };
    int                 ochBlockSize  = 8;
    private Set<String> sigs          = new HashSet<String>(Arrays.asList(new String[] { signature }));
    // Flags
    // Runtime vars
    int                 rtcoarse;
    int                 rtscale;
    int                 rpm;
    int                 secl;
    int                 rtfine;
    int                 rtmode;

    // eval vars
    double              time;
    int                 deadValue;
    int                 myrpm;
    int                 batteryVoltage;

    // Constants
    int                 rpm_in;
    int                 baud;
    int                 invert2;
    int                 invert1;
    int                 mode;

    private String[]    defaultGauges = { "rpmGauge", "modeGauge", "scaleGauge", "coarseGauge", "fineGauge", "myrpmGauge" };

    @Override
    public void calculate(byte[] ochBuffer) throws IOException
    {
        deadValue = (0);
        secl = (int) ((MSUtils.getByte(ochBuffer, 0) + 0.000) * 1.000);
        rpm = (int) ((MSUtils.getWord(ochBuffer, 1) + 0.000) * 1.000);
        rtmode = (int) ((MSUtils.getByte(ochBuffer, 3) + 0.000) * 1.000);
        rtscale = (int) ((MSUtils.getWord(ochBuffer, 4) + 0.000) * 1.000);
        rtcoarse = (int) ((MSUtils.getByte(ochBuffer, 6) + 0.000) * 1.000);
        rtfine = (int) ((MSUtils.getByte(ochBuffer, 7) + 0.000) * 1.000);
        batteryVoltage = (12);
        time = (timeNow());
        myrpm = ((rtcoarse / 4) * 256 + (2 * rtfine));
    }

    @Override
    public String getLogHeader()
    {
        StringBuffer b = new StringBuffer();
        b.append("Time").append("\t");
        b.append("SecL").append("\t");
        b.append("RPM").append("\t");
        b.append("Mode").append("\t");
        b.append("Scaling").append("\t");
        b.append("CoarseADC").append("\t");
        b.append("FineADC").append("\t");
        b.append("Commanded_RPM").append("\t");
        b.append(MSUtils.getLocationLogHeader());
        return b.toString();
    }

    @Override
    public String getLogRow()
    {
        StringBuffer b = new StringBuffer();
        b.append(time).append("\t");
        b.append(secl).append("\t");
        b.append(rpm).append("\t");
        b.append(rtmode).append("\t");
        b.append(rtscale).append("\t");
        b.append(rtcoarse).append("\t");
        b.append(rtfine).append("\t");
        b.append(myrpm).append("\t");
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge", "deadValue", deadValue, "---", "", 0, 1, -1, -1, 2, 2, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("voltMeter", "batteryVoltage", batteryVoltage, "Battery Voltage", "volts", 7, 21, 8, 9, 15, 16, 2, 2,
                0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("seclGauge", "secl", secl, "Secs", "s", 0, 255, 255, 0, 255, 255, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("rpmGauge", "rpm", rpm, "Simulated speed", "RPM", 0, 8000, 300, 600, 3000, 5000, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("modeGauge", "rtmode", rtmode, "Wheel mode", "", 0, 255, 0, 20, 200, 245, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("scaleGauge", "rtscale", rtscale, "speed scaling", "", 0, 65535, 0, 20, 65000, 65000, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("coarseGauge", "rtcoarse", rtcoarse, "coarse adc", "", 0, 255, 0, 20, 255, 255, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fineGauge", "rtfine", rtfine, "fine adc", "", 0, 255, 0, 20, 255, 255, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("myrpmGauge", "myrpm", myrpm, "Commanded speed", "RPM", 0, 8000, 300, 600, 3000, 5000, 0, 0, 0));

    }

    @Override
    public Set<String> getSignature()
    {
        return sigs;
    }

    @Override
    public byte[] getOchCommand()
    {

        return this.ochGetCommand;
    }

    @Override
    public byte[] getSigCommand()
    {
        return this.queryCommand;
    }

    @Override
    public int getBlockSize()
    {
        return this.ochBlockSize;
    }

    @Override
    public int getSigSize()
    {
        return signature.length();
    }

    @Override
    public int getPageActivationDelay()
    {
        return 100;
    }

    @Override
    public int getInterWriteDelay()
    {
        return 300;
    }

    @Override
    public int getCurrentTPS()
    {

        return (int) 0;
    }

    @Override
    public String[] defaultGauges()
    {
        return defaultGauges;
    }

    @Override
    public void loadConstants(boolean simulated)
    {
        byte[] pageBuffer = null;
        pageBuffer = loadPage(1, 0, 64, null, new byte[] { 86 });
        mode = MSUtils.getBits(pageBuffer, 0, 0, 5, 0);
        rpm_in = (int) ((MSUtils.getWord(pageBuffer, 1) + 0.0) * 1.0);
        baud = MSUtils.getBits(pageBuffer, 3, 0, 1, 0);
        invert2 = MSUtils.getBits(pageBuffer, 3, 6, 6, 0);
        invert1 = MSUtils.getBits(pageBuffer, 3, 7, 7, 0);
    }

}
