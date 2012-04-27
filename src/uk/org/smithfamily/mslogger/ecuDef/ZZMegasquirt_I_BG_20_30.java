package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;

/*
 * Fingerprint : e7920fb8b9f6a62fbbe37f083dc9d318
 */
public class ZZMegasquirt_I_BG_20_30 extends Megasquirt
{
    public ZZMegasquirt_I_BG_20_30(Context c)
    {
        super(c);
        refreshFlags();
    }

    @Override
    public void refreshFlags()
    {
        DIYWB_NON_LINEAR = isSet("DIYWB_NON_LINEAR");
        INNOVATE_0_5_LINEAR = isSet("INNOVATE_0_5_LINEAR");
        NGK_AFX = isSet("NGK_AFX");
        CELSIUS = isSet("CELSIUS");
        DYNOJET_LINEAR = isSet("DYNOJET_LINEAR");
        ZEITRONIX_NON_LINEAR = isSet("ZEITRONIX_NON_LINEAR");
        TECHEDGE_LINEAR = isSet("TECHEDGE_LINEAR");
        WB_1_0_LINEAR = isSet("WB_1_0_LINEAR");
        NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
        FAST_WIDEBAND_O2 = isSet("FAST_WIDEBAND_O2");
        ALPHA_N = isSet("ALPHA_N");
        SPEED_DENSITY = isSet("SPEED_DENSITY");
        AEM_LINEAR = isSet("AEM_LINEAR");
        INNOVATE_1_2_LINEAR = isSet("INNOVATE_1_2_LINEAR");
        MPX4250 = isSet("MPX4250");
        AIR_FLOW_METER = isSet("AIR_FLOW_METER");
        INNOVATE_LC1_DEFAULT = isSet("INNOVATE_LC1_DEFAULT");
        AEM_NON_LINEAR = isSet("AEM_NON_LINEAR");
        WB_UNKNOWN = isSet("WB_UNKNOWN");
    }

    byte[]              queryCommand  = new byte[] { 'Q' };

    byte[]              ochGetCommand = new byte[] { 'A' };
    int                 ochBlockSize  = 22;
    byte[]              signature     = { 20 };
    private Set<String> sigs          = new HashSet<String>(Arrays.asList(new String[] { new String(signature) }));
    // Flags
    boolean             DIYWB_NON_LINEAR;
    boolean             INNOVATE_0_5_LINEAR;
    boolean             NGK_AFX;
    boolean             CELSIUS;
    boolean             DYNOJET_LINEAR;
    boolean             ZEITRONIX_NON_LINEAR;
    boolean             TECHEDGE_LINEAR;
    boolean             WB_1_0_LINEAR;
    boolean             NARROW_BAND_EGO;
    boolean             FAST_WIDEBAND_O2;
    boolean             ALPHA_N;
    boolean             SPEED_DENSITY;
    boolean             AEM_LINEAR;
    boolean             INNOVATE_1_2_LINEAR;
    boolean             MPX4250;
    boolean             AIR_FLOW_METER;
    boolean             INNOVATE_LC1_DEFAULT;
    boolean             AEM_NON_LINEAR;
    boolean             WB_UNKNOWN;
    // Runtime vars
    int                 egoADC;
    int                 matADC;
    int                 cltADC;
    int                 tpsADC;
    int                 engine;
    int                 batADC;
    int                 gammaEnrich;
    int                 baroADC;
    int                 accelEnrich;
    int                 secl;
    double              pulseWidth;
    int                 airCorrection;
    int                 squirt;
    double              veCurr;
    double              egoCorrection;
    int                 mapADC;
    int                 warmupEnrich;
    int                 baroCorrection;
    int                 rpm100;

    // eval vars
    double              accDecEnrich;
    double              coolant;
    int                 afrtarget;
    double              TargetAFR;
    double              afr;
    double              throttle;
    int                 altDiv;
    int                 deadValue;
    double              mat;
    double              batteryVoltage;
    double              dutyCycle;
    double              lambda;
    double              time;
    int                 nSquirts;
    double              egoVoltage;
    double              cycleTime;
    int                 idleDC;
    double              map;
    int                 rpm;
    double              barometer;
    int                 afrTargetV;
    double              TargetLambda;

    // Constants
    int                 taeColdM;
    double              egoRPM;
    double              reqFuel;
    double              taeBins1;
    double              taeBins2;
    int                 nCylinders;
    double              taeBins3;
    int                 tdePct;
    int                 algorithm;
    double              injOpen;
    double              taeColdA;
    double              taeBins4;
    double              taeTime;
    int                 egoType;
    double              injOCfuel;
    int                 cltType;
    double              egoSwitch;
    int                 mapType;
    int                 asePct;
    int                 egoDelta;
    int                 alternate;
    int                 baroCorr;
    int                 injPwmP;
    double              primePulse;
    double              crankCold;
    int                 twoStroke;
    int                 divider;
    double              crankHot;
    int                 egoCount;
    int                 fastIdleT;
    int                 engineType;
    int                 egoTemp;
    double              battFac;
    int                 nInjectors;
    int                 egoLimit;
    int                 aseCount;
    int                 rpmk;
    int                 injType;
    double              tpsThresh;
    double              injPwmT;
    int                 matType;

    private String[]    defaultGauges = { "tachometer", "throttleGauge", "tpsADCGauge", "pulseWidthGauge", "dutyCycleGauge", "mapGauge", "matGauge", "cltGauge", "gammaEnrichGauge" };

    @Override
    public void calculate(byte[] ochBuffer) throws IOException
    {
        deadValue = (0);
        secl = (int) ((MSUtils.getByte(ochBuffer, 0) + 0.000) * 1.000);
        squirt = (int) ((MSUtils.getByte(ochBuffer, 1) + 0.000) * 1.000);
        engine = (int) ((MSUtils.getByte(ochBuffer, 2) + 0.000) * 1.000);
        baroADC = (int) ((MSUtils.getByte(ochBuffer, 3) + 0.000) * 1.000);
        mapADC = (int) ((MSUtils.getByte(ochBuffer, 4) + 0.000) * 1.000);
        matADC = (int) ((MSUtils.getByte(ochBuffer, 5) + 0.000) * 1.000);
        cltADC = (int) ((MSUtils.getByte(ochBuffer, 6) + 0.000) * 1.000);
        tpsADC = (int) ((MSUtils.getByte(ochBuffer, 7) + 0.000) * 1.000);
        batADC = (int) ((MSUtils.getByte(ochBuffer, 8) + 0.000) * 1.000);
        egoADC = (int) ((MSUtils.getByte(ochBuffer, 9) + 0.000) * 1.000);
        egoCorrection = (int) ((MSUtils.getByte(ochBuffer, 10) + 0.000) * 1.000);
        airCorrection = (int) ((MSUtils.getByte(ochBuffer, 11) + 0.000) * 1.000);
        warmupEnrich = (int) ((MSUtils.getByte(ochBuffer, 12) + 0.000) * 1.000);
        rpm100 = (int) ((MSUtils.getByte(ochBuffer, 13) + 0.000) * 1.000);
        pulseWidth = (double) ((MSUtils.getByte(ochBuffer, 14) + 0.000) * 0.100);
        accelEnrich = (int) ((MSUtils.getByte(ochBuffer, 15) + 0.000) * 1.000);
        baroCorrection = (int) ((MSUtils.getByte(ochBuffer, 16) + 0.000) * 1.000);
        gammaEnrich = (int) ((MSUtils.getByte(ochBuffer, 17) + 0.000) * 1.000);
        veCurr = (int) ((MSUtils.getByte(ochBuffer, 18) + 0.000) * 1.000);
        accDecEnrich = (((accelEnrich + ((engine & 32)) != 0) ? tdePct : 100));
        batteryVoltage = (batADC / 255.0 * 30.0);
        coolant = (tempCvt(table(cltADC, "thermfactor.inc") - 40));
        egoVoltage = (egoADC / 255.0 * 5.0);
        mat = (tempCvt(table(matADC, "matfactor.inc") - 40));
        rpm = (rpm100 * 100);
        time = (timeNow());
        afrtarget = (0);
        if (NARROW_BAND_EGO)
        {
            afr = (table(egoADC, "NBafr100.inc") / 100.0);
            lambda = (afr / 14.7);
            TargetAFR = (table(afrtarget, "NBafr100.inc") / 100.0);
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (WB_1_0_LINEAR)
        {
            lambda = (1.5 - 5.0 * egoADC / 255.0);
            afr = (1.5 - 5.0 * egoADC / 255.0 * 14.7);
            TargetLambda = (1.5 - 5.0 * afrtarget / 255.0);
            TargetAFR = (TargetLambda * 14.7);
        }
        else if (WB_UNKNOWN)
        {
            afr = (egoADC * 0.01961);
            lambda = (afr / 14.7);
            TargetAFR = (afrtarget * 0.01961);
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (DIYWB_NON_LINEAR)
        {
            lambda = (table(egoADC, "WBlambda100MOT.inc") / 100.0);
            afr = ((table(egoADC, "WBlambda100MOT.inc") / 100.0) * 14.7);
            TargetLambda = (table(afrtarget, "WBlambda100MOT.inc") / 100.0);
            TargetAFR = (TargetLambda * 14.7);
        }
        else if (DYNOJET_LINEAR)
        {
            afr = (egoADC * 0.031373 + 10);
            lambda = (afr / 14.7);
            TargetLambda = (TargetAFR / 14.7);
            TargetAFR = (afrtarget * 0.031373 + 10);
        }
        else if (TECHEDGE_LINEAR)
        {
            afr = (egoADC * 0.039216 + 9);
            lambda = (afr / 14.7);
            TargetAFR = (afrtarget * 0.039216 + 9);
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (INNOVATE_1_2_LINEAR)
        {
            afr = (egoADC * 0.1961);
            lambda = (afr / 14.7);
            TargetAFR = (afrtarget * 0.1961);
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (INNOVATE_0_5_LINEAR)
        {
            afr = (10 + (egoADC * 0.039216));
            lambda = (afr / 14.7);
            TargetAFR = (10 + (afrtarget * 0.039216));
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (INNOVATE_LC1_DEFAULT)
        {
            afr = (7.35 + (egoADC * 0.0589804));
            lambda = (afr / 14.7);
            TargetLambda = (afrtarget / 255.0 + 0.5);
            TargetAFR = (TargetLambda * 14.7);
        }
        else if (ZEITRONIX_NON_LINEAR)
        {
            afr = (table((int) egoVoltage, "zeitronix.inc"));
            lambda = (afr / 14.7);
            afrTargetV = (afrtarget * 5 / 255);
            TargetAFR = (table(afrTargetV, "zeitronix.inc"));
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (AEM_LINEAR)
        {
            afr = (9.72 + (egoADC * 0.038666));
            lambda = (afr / 14.7);
            TargetAFR = (9.72 + (afrtarget * 0.038666));
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (AEM_NON_LINEAR)
        {
            afr = (8.44350 + (egoADC * (0.012541 + egoADC * (0.000192111 + egoADC * (-0.00000138363 + egoADC * 0.00000000442977)))));
            lambda = (afr / 14.7);
            TargetAFR = (8.44350 + (afrtarget * (0.0102541 + afrtarget * (0.000192111 + afrtarget * (-0.00000138363 + afrtarget * 0.00000000442977)))));
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (NGK_AFX)
        {
            afr = ((egoADC * 0.0270592) + 9);
            lambda = (afr / 14.7);
            TargetAFR = ((afrtarget * 0.0270592) + 9);
            TargetLambda = (TargetAFR / 14.7);
        }
        else if (FAST_WIDEBAND_O2)
        {
            lambda = (egoADC / 51);
            afr = (lambda * 14.7);
            TargetAFR = ((afrtarget / 51) * 14.7);
            TargetLambda = ((afrtarget / 51));
        }
        if (MPX4250)
        {
            barometer = (table(baroADC, "kpafactor4250.inc"));
            map = (table(mapADC, "kpafactor4250.inc"));
        }
        else
        {
            barometer = (table(baroADC, "kpafactor4115.inc"));
            map = (table(mapADC, "kpafactor4115.inc"));
        }
        throttle = (table(tpsADC, "throttlefactor.inc"));
        idleDC = ((coolant < fastIdleT ? 1 : 0) * 100);
        altDiv = (((alternate) != 0) ? 2 : 1);
        cycleTime = (60000.0 / rpm * (2.0 - twoStroke));
        nSquirts = (nCylinders / divider);
        dutyCycle = (100.0 * nSquirts / altDiv * pulseWidth / cycleTime);
    }

    @Override
    public String getLogHeader()
    {
        StringBuffer b = new StringBuffer();
        b.append("Time").append("\t");
        b.append("SecL").append("\t");
        b.append("RPM").append("\t");
        b.append("MAP").append("\t");
        b.append("TP").append("\t");
        b.append("O2").append("\t");
        b.append("MAT").append("\t");
        b.append("CLT").append("\t");
        b.append("Engine").append("\t");
        b.append("Gego").append("\t");
        b.append("Gair").append("\t");
        b.append("Gwarm").append("\t");
        b.append("Gbaro").append("\t");
        b.append("Gammae").append("\t");
        b.append("TPSacc").append("\t");
        b.append("Gve").append("\t");
        b.append("PW").append("\t");
        b.append("Gve2").append("\t");
        b.append("PW2").append("\t");
        b.append("DutyCycle1").append("\t");
        b.append("DutyCycle2").append("\t");
        b.append("UserData1").append("\t");
        b.append("UserData2").append("\t");
        b.append("UserData3").append("\t");
        b.append(MSUtils.getLocationLogHeader());
        return b.toString();
    }

    @Override
    public String getLogRow()
    {
        StringBuffer b = new StringBuffer();
        b.append(time).append("\t");
        b.append(secl).append("\t");
        b.append(rpm100).append("\t");
        b.append(map).append("\t");
        b.append(throttle).append("\t");
        b.append(egoVoltage).append("\t");
        b.append(mat).append("\t");
        b.append(coolant).append("\t");
        b.append(engine).append("\t");
        b.append(egoCorrection).append("\t");
        b.append(airCorrection).append("\t");
        b.append(warmupEnrich).append("\t");
        b.append(baroCorrection).append("\t");
        b.append(gammaEnrich).append("\t");
        b.append(accDecEnrich).append("\t");
        b.append(veCurr).append("\t");
        b.append(round(pulseWidth)).append("\t");
        b.append("").append("\t");
        b.append(round(pulseWidth)).append("\t");
        b.append(dutyCycle).append("\t");
        b.append(dutyCycle).append("\t");
        b.append("").append("\t");
        b.append("").append("\t");
        b.append("").append("\t");
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("baroADCGauge", "baroADC", baroADC, "Barometer ADC", "", 0, 255, -1, -1, 256, 256, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapADCGauge", "mapADC", mapADC, "MAP ADC", "", 0, 255, -1, -1, 256, 256, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matADCGauge", "matADC", matADC, "MAT ADC", "", 0, 255, -1, -1, 256, 256, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltADCGauge", "cltADC", cltADC, "CLT ADC", "", 0, 255, -1, -1, 256, 256, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tpsADCGauge", "tpsADC", tpsADC, "TPS ADC", "", 0, 255, -1, -1, 256, 256, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("batADCGauge", "batADC", batADC, "BAT ADC", "", 0, 255, -1, -1, 256, 256, 0, 0, 0));
        if (NARROW_BAND_EGO)
        {
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge", "egoVoltage", egoVoltage, "Exhaust Gas Oxygen", "volts", 0, 1.0, 0.2, 0.3, 0.6, 0.8, 2, 2, 0));
        }
        else
        {
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge", "egoVoltage", egoVoltage, "Exhaust Gas Oxygen", "volts", 0.5, 4.5, 0.0, 0.0, 5.0, 5.0, 2, 2, 0));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambdaGauge", "lambda", lambda, "Lambda", "", 0.5, 1.5, 0.5, 0.7, 2, 1.1, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelEnrichGauge", "accDecEnrich", accDecEnrich, "Accel Enrich", "%", 50, 150, -1, -1, 999, 999, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afrGauge", "afr", afr, "Air:Fuel Ratio", "", 10, 19.4, 12, 13, 15, 16, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clockGauge", "secl", secl, "Clock", "Seconds", 0, 255, 10, 10, 245, 245, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge", "deadValue", deadValue, "---", "", 0, 1, -1, -1, 2, 2, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycleGauge", "dutyCycle", dutyCycle, "Duty Cycle", "%", 0, 100, -1, -1, 85, 90, 1, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge", "egoCorrection", egoCorrection, "EGO Correction", "%", 50, 150, 90, 99, 101, 110, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaEnrichGauge", "gammaEnrich", gammaEnrich, "Gamma Enrichment", "%", 50, 150, -1, -1, 151, 151, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge", "map", map, "Engine MAP", "kPa", 0, 255, 0, 20, 200, 245, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthGauge", "pulseWidth", pulseWidth, "Pulse Width", "mSec", 0, 25.5, 1.0, 1.2, 20, 25, 1, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tachometer", "rpm", rpm, "Engine Speed", "RPM", 0, 8000, 300, 600, 3000, 5000, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("throttleGauge", "throttle", throttle, "Throttle Position", "%TPS", 0, 100, -1, 1, 90, 100, 0, 0, 0));

        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge", "veCurr", veCurr, "VE Current", "%", 0, 120, -1, -1, 999, 999, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("voltMeter", "batteryVoltage", batteryVoltage, "Battery Voltage", "volts", 7, 21, 8, 9, 15, 16, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("warmupEnrichGauge", "warmupEnrich", warmupEnrich, "Warmup Enrichment", "%", 100, 150, -1, -1, 101, 105, 0, 0, 0));
        if (CELSIUS)
        {
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge", "coolant", coolant, "Coolant Temp", "%TEMP", -40, 120, -15, 0, 95, 105, 0, 0, 0));
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge", "mat", mat, "Manifold Air Temp", "%TEMP", -40, 110, -15, 0, 95, 100, 0, 0, 0));
        }
        else
        {
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge", "coolant", coolant, "Coolant Temp", "%TEMP", -40, 250, 0, 30, 200, 220, 0, 0, 0));
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge", "mat", mat, "Manifold Air Temp", "%TEMP", -40, 215, 0, 30, 200, 210, 0, 0, 0));
        }

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
        return 1;
    }

    @Override
    public int getPageActivationDelay()
    {
        return 10;
    }

    @Override
    public int getInterWriteDelay()
    {
        return 200;
    }

    @Override
    public int getCurrentTPS()
    {

        return (int) tpsADC;
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
        pageBuffer = loadPage(1, 0, 125, null, new byte[] { 118 });
        crankCold = (double) ((MSUtils.getByte(pageBuffer, 64) + 0.0) * 0.1);
        crankHot = (double) ((MSUtils.getByte(pageBuffer, 65) + 0.0) * 0.1);
        asePct = (int) ((MSUtils.getByte(pageBuffer, 66) + 0.0) * 1.0);
        aseCount = (int) ((MSUtils.getByte(pageBuffer, 67) + 0.0) * 1.0);
        taeBins1 = (double) ((MSUtils.getByte(pageBuffer, 78) + 0.0) * 0.1);
        taeBins2 = (double) ((MSUtils.getByte(pageBuffer, 79) + 0.0) * 0.1);
        taeBins3 = (double) ((MSUtils.getByte(pageBuffer, 80) + 0.0) * 0.1);
        taeBins4 = (double) ((MSUtils.getByte(pageBuffer, 81) + 0.0) * 0.1);
        taeColdA = (double) ((MSUtils.getByte(pageBuffer, 82) + 0.0) * 0.1);
        tpsThresh = (double) ((MSUtils.getByte(pageBuffer, 83) + 0.0) * 0.1953125);
        taeTime = (double) ((MSUtils.getByte(pageBuffer, 84) + 0.0) * 0.1);
        tdePct = (int) ((MSUtils.getByte(pageBuffer, 85) + 0.0) * 1.0);
        if (CELSIUS)
        {
            egoTemp = (int) ((MSUtils.getByte(pageBuffer, 86) + -72.0) * 0.555);
        }
        else
        {
            egoTemp = (int) ((MSUtils.getByte(pageBuffer, 86) + -40.0) * 1.0);
        }
        egoCount = (int) ((MSUtils.getByte(pageBuffer, 87) + 0.0) * 1.0);
        egoDelta = (int) ((MSUtils.getByte(pageBuffer, 88) + 0.0) * 1.0);
        egoLimit = (int) ((MSUtils.getByte(pageBuffer, 89) + 0.0) * 1.0);
        reqFuel = (double) ((MSUtils.getByte(pageBuffer, 90) + 0.0) * 0.1);
        divider = (int) ((MSUtils.getByte(pageBuffer, 91) + 0.0) * 1.0);
        alternate = MSUtils.getBits(pageBuffer, 92, 0, 0, 0);
        injOpen = (double) ((MSUtils.getByte(pageBuffer, 93) + 0.0) * 0.1);
        injOCfuel = (double) ((MSUtils.getByte(pageBuffer, 94) + 0.0) * 0.1);
        injPwmP = (int) ((MSUtils.getByte(pageBuffer, 95) + 0.0) * 1.0);
        injPwmT = (double) ((MSUtils.getByte(pageBuffer, 96) + 0.0) * 0.1);
        battFac = (double) ((MSUtils.getByte(pageBuffer, 97) + 0.0) * 0.0166667);
        rpmk = (int) ((MSUtils.getWord(pageBuffer, 98) + 0.0) * 1.0);
        if (SPEED_DENSITY)
        {
        }
        else if (ALPHA_N)
        {
        }
        else if (AIR_FLOW_METER)
        {

        }
        mapType = MSUtils.getBits(pageBuffer, 116, 0, 1, 0);
        twoStroke = MSUtils.getBits(pageBuffer, 116, 2, 2, 0);
        injType = MSUtils.getBits(pageBuffer, 116, 3, 3, 0);
        nCylinders = MSUtils.getBits(pageBuffer, 116, 4, 7, 1);
        cltType = MSUtils.getBits(pageBuffer, 117, 0, 1, 0);
        matType = MSUtils.getBits(pageBuffer, 117, 2, 3, 0);
        nInjectors = MSUtils.getBits(pageBuffer, 117, 4, 7, 1);
        engineType = MSUtils.getBits(pageBuffer, 118, 0, 0, 0);
        egoType = MSUtils.getBits(pageBuffer, 118, 1, 1, 0);
        algorithm = MSUtils.getBits(pageBuffer, 118, 2, 2, 0);
        baroCorr = MSUtils.getBits(pageBuffer, 118, 3, 3, 0);
        primePulse = (double) ((MSUtils.getByte(pageBuffer, 119) + 0.0) * 0.1);
        egoRPM = (double) ((MSUtils.getByte(pageBuffer, 120) + 0.0) * 100.0);
        if (CELSIUS)
        {
            fastIdleT = (int) ((MSUtils.getByte(pageBuffer, 121) + -72.0) * 0.555);
        }
        else
        {
            fastIdleT = (int) ((MSUtils.getByte(pageBuffer, 121) + -40.0) * 1.0);
        }
        egoSwitch = (double) ((MSUtils.getByte(pageBuffer, 122) + 0.0) * 0.0196);
        taeColdM = (int) ((MSUtils.getByte(pageBuffer, 123) + 0.0) * 1.0);
    }

}
