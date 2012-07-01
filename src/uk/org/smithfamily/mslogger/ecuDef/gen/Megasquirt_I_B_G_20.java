package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.*;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
/*
Fingerprint : 10fb6c32e0e433db99dae59eb43cf2f7
*/
@SuppressWarnings("unused")
public class Megasquirt_I_B_G_20 extends Megasquirt
{
    public Megasquirt_I_B_G_20(Context c)
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
    private Map<String,Double> fields = new HashMap<String,Double>();
    byte[] queryCommand = new byte[]{'Q'};
    String signature = ""+(byte)20;
    byte [] ochGetCommand = new byte[]{'A'};
    int ochBlockSize = 22;
//Flags
    public boolean DIYWB_NON_LINEAR;
    public boolean INNOVATE_0_5_LINEAR;
    public boolean NGK_AFX;
    public boolean CELSIUS;
    public boolean DYNOJET_LINEAR;
    public boolean ZEITRONIX_NON_LINEAR;
    public boolean TECHEDGE_LINEAR;
    public boolean WB_1_0_LINEAR;
    public boolean NARROW_BAND_EGO;
    public boolean FAST_WIDEBAND_O2;
    public boolean ALPHA_N;
    public boolean SPEED_DENSITY;
    public boolean AEM_LINEAR;
    public boolean INNOVATE_1_2_LINEAR;
    public boolean MPX4250;
    public boolean AIR_FLOW_METER;
    public boolean INNOVATE_LC1_DEFAULT;
    public boolean AEM_NON_LINEAR;
    public boolean WB_UNKNOWN;
//Defaults
//Variables
    public double TargetAFR;
    public double TargetLambda;
    public double accDecEnrich;
    public int accelEnrich;
    public double afr;
    public double afrTargetV;
    public int afrtarget;
    public int airCorrection;
    public int altDiv;
    public int baroADC;
    public int baroCorrection;
    public double barometer;
    public int batADC;
    public double batteryVoltage;
    public int blank1;
    public int blank2;
    public int blank3;
    public int cltADC;
    public double coolant;
    public double cycleTime;
    public int deadValue;
    public double dutyCycle;
    public int egoADC;
    public double egoCorrection;
    public double egoVoltage;
    public int engine;
    public int gammaEnrich;
    public double idleDC;
    public double lambda;
    public double map;
    public int mapADC;
    public double mat;
    public int matADC;
    public int nSquirts;
    public double pulseWidth;
    public int rpm;
    public int rpm100;
    public int secl;
    public int squirt;
    public double throttle;
    public double time;
    public int tpsADC;
    public double veCurr;
    public int warmupEnrich;

//Constants
    public int taeColdM;
    public double egoRPM;
    public double reqFuel;
    public int nCylinders;
    public int tdePct;
    public int algorithm;
    public double injOpen;
    public double taeColdA;
    public double taeTime;
    public int egoType;
    public double injOCfuel;
    public int cltType;
    public double egoSwitch;
    public int asePct;
    public int mapType;
    public int egoDelta;
    public int alternate;
    public int baroCorr;
    public int injPwmP;
    public double primePulse;
    public double crankCold;
    public int divider;
    public int twoStroke;
    public int egoCount;
    public double crankHot;
    public int fastIdleT;
    public int engineType;
    public int egoTemp;
    public double battFac;
    public int nInjectors;
    public int egoLimit;
    public int aseCount;
    public int rpmk;
    public double tpsThresh;
    public int injType;
    public double injPwmT;
    public int matType;


    private String[] defaultGauges = {
        "tachometer",
        "throttleGauge",
        "tpsADCGauge",
        "pulseWidthGauge",
        "dutyCycleGauge",
        "mapGauge",
        "matGauge",
        "BGpw2Gauge",
        "BGdc2Gauge",
        "cltGauge",
        "gammaEnrichGauge"
    };
	@Override
	public void calculate(byte[] ochBuffer)
    {
        deadValue = (0);
        secl = (int)((MSUtils.getByte(ochBuffer,0) + 0.000) * 1.000);
        squirt = (int)((MSUtils.getByte(ochBuffer,1) + 0.000) * 1.000);
        engine = (int)((MSUtils.getByte(ochBuffer,2) + 0.000) * 1.000);
        baroADC = (int)((MSUtils.getByte(ochBuffer,3) + 0.000) * 1.000);
        mapADC = (int)((MSUtils.getByte(ochBuffer,4) + 0.000) * 1.000);
        matADC = (int)((MSUtils.getByte(ochBuffer,5) + 0.000) * 1.000);
        cltADC = (int)((MSUtils.getByte(ochBuffer,6) + 0.000) * 1.000);
        tpsADC = (int)((MSUtils.getByte(ochBuffer,7) + 0.000) * 1.000);
        batADC = (int)((MSUtils.getByte(ochBuffer,8) + 0.000) * 1.000);
        egoADC = (int)((MSUtils.getByte(ochBuffer,9) + 0.000) * 1.000);
        egoCorrection = (int)((MSUtils.getByte(ochBuffer,10) + 0.000) * 1.000);
        airCorrection = (int)((MSUtils.getByte(ochBuffer,11) + 0.000) * 1.000);
        warmupEnrich = (int)((MSUtils.getByte(ochBuffer,12) + 0.000) * 1.000);
        rpm100 = (int)((MSUtils.getByte(ochBuffer,13) + 0.000) * 1.000);
        pulseWidth = (double)((MSUtils.getByte(ochBuffer,14) + 0.000) * 0.100);
        accelEnrich = (int)((MSUtils.getByte(ochBuffer,15) + 0.000) * 1.000);
        baroCorrection = (int)((MSUtils.getByte(ochBuffer,16) + 0.000) * 1.000);
        gammaEnrich = (int)((MSUtils.getByte(ochBuffer,17) + 0.000) * 1.000);
        veCurr = (int)((MSUtils.getByte(ochBuffer,18) + 0.000) * 1.000);
        blank1 = (int)((MSUtils.getByte(ochBuffer,19) + 0.0) * 1.0);
        blank2 = (int)((MSUtils.getByte(ochBuffer,20) + 0.0) * 1.0);
        blank3 = (int)((MSUtils.getByte(ochBuffer,21) + 0.0) * 1.0);
        accDecEnrich = (((accelEnrich + ((engine & 32) ) != 0 ) ?  tdePct : 100));
        try
        {
            batteryVoltage = (batADC / 255.0 * 30.0);
        }
        catch (ArithmeticException e) {
            batteryVoltage = 0;
        }
        coolant = (tempCvt(table(cltADC, "thermfactor.inc")-40));
        try
        {
            egoVoltage = (egoADC / 255.0 * 5.0);
        }
        catch (ArithmeticException e) {
            egoVoltage = 0;
        }
        mat = (tempCvt(table(matADC, "matfactor.inc")-40));
        rpm = (rpm100*100);
        time = (timeNow());
        afrtarget = (0);
        if (NARROW_BAND_EGO)
        {
        try
        {
            afr = (table(egoADC,    "NBafr100.inc") / 100.0);
        }
        catch (ArithmeticException e) {
            afr = 0;
        }
        try
        {
            lambda = (afr / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        try
        {
            TargetAFR = (table(afrtarget, "NBafr100.inc") / 100.0);
        }
        catch (ArithmeticException e) {
            TargetAFR = 0;
        }
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (WB_1_0_LINEAR)
        {
        try
        {
            lambda = (1.5 - 5.0 * egoADC/255.0);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        try
        {
            afr = (1.5 - 5.0 * egoADC/255.0  * 14.7);
        }
        catch (ArithmeticException e) {
            afr = 0;
        }
        try
        {
            TargetLambda = (1.5 - 5.0 * afrtarget/255.0);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        TargetAFR = (TargetLambda * 14.7);
        }
        else if (WB_UNKNOWN)
        {
        afr = (egoADC * 0.01961);
        try
        {
            lambda = (afr / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        TargetAFR = (afrtarget * 0.01961);
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (DIYWB_NON_LINEAR)
        {
        try
        {
            lambda = (table(egoADC, "WBlambda100MOT.inc") / 100.0);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        try
        {
            afr = ((table(egoADC, "WBlambda100MOT.inc") / 100.0 ) * 14.7);
        }
        catch (ArithmeticException e) {
            afr = 0;
        }
        try
        {
            TargetLambda = (table(afrtarget,"WBlambda100MOT.inc") / 100.0);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        TargetAFR = (TargetLambda * 14.7);
        }
        else if (DYNOJET_LINEAR)
        {
        afr = (egoADC    * 0.031373 + 10);
        try
        {
            lambda = (afr / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        TargetAFR = (afrtarget * 0.031373 + 10);
        }
        else if (TECHEDGE_LINEAR)
        {
        afr = (egoADC    * 0.039216 + 9);
        try
        {
            lambda = (afr / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        TargetAFR = (afrtarget * 0.039216 + 9);
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (INNOVATE_1_2_LINEAR)
        {
        afr = (egoADC    * 0.1961);
        try
        {
            lambda = (afr       / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        TargetAFR = (afrtarget * 0.1961);
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (INNOVATE_0_5_LINEAR)
        {
        afr = (10 + (egoADC    * 0.039216));
        try
        {
            lambda = (afr       / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        TargetAFR = (10 + (afrtarget * 0.039216));
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (INNOVATE_LC1_DEFAULT)
        {
        afr = (7.35 + (egoADC    * 0.0589804));
        try
        {
            lambda = (afr       / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        try
        {
            TargetLambda = (afrtarget/255.0 + 0.5);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        TargetAFR = (TargetLambda * 14.7);
        }
        else if (ZEITRONIX_NON_LINEAR)
        {
        afr = (table(egoVoltage,   "zeitronix.inc"));
        try
        {
            lambda = (afr          / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        try
        {
            afrTargetV = (afrtarget*5/255);
        }
        catch (ArithmeticException e) {
            afrTargetV = 0;
        }
        TargetAFR = (table( afrTargetV ,"zeitronix.inc"));
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (AEM_LINEAR)
        {
        afr = (9.72 + (egoADC    * 0.038666));
        try
        {
            lambda = (afr       / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        TargetAFR = (9.72 + (afrtarget * 0.038666 ));
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (AEM_NON_LINEAR)
        {
        afr = (8.44350 + (egoADC    * (0.012541 + egoADC    * (0.000192111 + egoADC    * (-0.00000138363 + egoADC    * 0.00000000442977)))));
        try
        {
            lambda = (afr       / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        TargetAFR = (8.44350 + (afrtarget * (0.0102541 + afrtarget * (0.000192111 + afrtarget * (-0.00000138363 + afrtarget * 0.00000000442977)))));
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (NGK_AFX)
        {
        afr = ((egoADC * 0.0270592) + 9);
        try
        {
            lambda = (afr       / 14.7);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        TargetAFR = ((afrtarget * 0.0270592) + 9);
        try
        {
            TargetLambda = (TargetAFR / 14.7);
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        else if (FAST_WIDEBAND_O2)
        {
        try
        {
            lambda = (egoADC / 51);
        }
        catch (ArithmeticException e) {
            lambda = 0;
        }
        afr = (lambda * 14.7);
        try
        {
            TargetAFR = ((afrtarget / 51) * 14.7);
        }
        catch (ArithmeticException e) {
            TargetAFR = 0;
        }
        try
        {
            TargetLambda = ((afrtarget / 51));
        }
        catch (ArithmeticException e) {
            TargetLambda = 0;
        }
        }
        if (MPX4250)
        {
        barometer = (table(baroADC, "kpafactor4250.inc"));
        map = (table(mapADC,  "kpafactor4250.inc"));
        }
        else
        {
        barometer = (table(baroADC, "kpafactor4115.inc"));
        map = (table(mapADC,  "kpafactor4115.inc"));
        }
        throttle = (table(tpsADC,  "throttlefactor.inc"));
        idleDC = (((coolant < fastIdleT)) ?  1 : 0 * 100);
        altDiv = (((alternate ) != 0 ) ?  2 : 1);
        try
        {
            cycleTime = (60000.0 / rpm * (2.0-twoStroke));
        }
        catch (ArithmeticException e) {
            cycleTime = 0;
        }
        try
        {
            nSquirts = (nCylinders/divider);
        }
        catch (ArithmeticException e) {
            nSquirts = 0;
        }
        try
        {
            dutyCycle = (100.0*nSquirts/altDiv*pulseWidth/cycleTime);
        }
        catch (ArithmeticException e) {
            dutyCycle = 0;
        }
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
        b.append(blank2).append("\t");
        b.append(round(pulseWidth)).append("\t");
        b.append(dutyCycle).append("\t");
        b.append(dutyCycle).append("\t");
        b.append(blank1).append("\t");
        b.append(blank2).append("\t");
        b.append(blank3).append("\t");
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("baroADCGauge","baroADC",baroADC,"Barometer ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapADCGauge","mapADC",mapADC,"MAP ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matADCGauge","matADC",matADC,"MAT ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltADCGauge","cltADC",cltADC,"CLT ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tpsADCGauge","tpsADC",tpsADC,"TPS ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("batADCGauge","batADC",batADC,"BAT ADC","",0,255,-1,-1,256,256,0,0,45));
        if (NARROW_BAND_EGO)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge","egoVoltage",egoVoltage,"Exhaust Gas Oxygen","volts",0,1.0,0.2,0.3,0.6,0.8,2,2,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge","egoVoltage",egoVoltage,"Exhaust Gas Oxygen","volts",0.5,4.5,0.0,0.0,5.0,5.0,2,2,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambdaGauge","lambda",lambda,"Lambda","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelEnrichGauge","accDecEnrich",accDecEnrich,"Accel Enrich","%",50,150,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afrGauge","afr",afr,"Air:Fuel Ratio","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clockGauge","secl",secl,"Clock","Seconds",0,255,10,10,245,245,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge","deadValue",deadValue,"---","",0,1,-1,-1,2,2,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycleGauge","dutyCycle",dutyCycle,"Duty Cycle","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge","egoCorrection",egoCorrection,"EGO Correction","%",50,150,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaEnrichGauge","gammaEnrich",gammaEnrich,"Gamma Enrichment","%",50,150,-1,-1,151,151,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge","map",map,"Engine MAP","kPa",0,255,0,20,200,245,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthGauge","pulseWidth",pulseWidth,"Pulse Width","mSec",0,25.5,1.0,1.2,20,25,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tachometer","rpm",rpm,"Engine Speed","RPM",0,8000,300,600,3000,5000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("throttleGauge","throttle",throttle,"Throttle Position","%TPS",0,100,-1,1,90,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge","veCurr",veCurr,"VE Current","%",0,120,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("voltMeter","batteryVoltage",batteryVoltage,"Battery Voltage","volts",7,21,8,9,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("warmupEnrichGauge","warmupEnrich",warmupEnrich,"Warmup Enrichment","%",100,150,-1,-1,101,105,0,0,45));
        if (CELSIUS)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","%TEMP",-40,120,-15,0,95,105,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Manifold Air Temp","%TEMP",-40,110,-15,0,95,100,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","%TEMP",-40,250,0,30,200,220,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Manifold Air Temp","%TEMP",-40,215,0,30,200,210,0,0,45));
        }
    }

    @Override
    public String getSignature()
    {
        return signature;
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
        return 10;
    }
    @Override
    public int getInterWriteDelay()
    {
        return 0;
    }
    @Override
    public boolean isCRC32Protocol()
    {
        return false;
    }
    @Override
    public int getCurrentTPS()
    {
        return (int)tpsADC;
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
        pageBuffer = loadPage(1,0,125,null,new byte[]{86});
        crankCold = (double)((MSUtils.getByte(pageBuffer,64) + 0.0) * 0.1);
        crankHot = (double)((MSUtils.getByte(pageBuffer,65) + 0.0) * 0.1);
        asePct = (int)((MSUtils.getByte(pageBuffer,66) + 0.0) * 1.0);
        aseCount = (int)((MSUtils.getByte(pageBuffer,67) + 0.0) * 1.0);
        taeColdA = (double)((MSUtils.getByte(pageBuffer,82) + 0.0) * 0.1);
        tpsThresh = (double)((MSUtils.getByte(pageBuffer,83) + 0.0) * 0.1953125);
        taeTime = (double)((MSUtils.getByte(pageBuffer,84) + 0.0) * 0.1);
        tdePct = (int)((MSUtils.getByte(pageBuffer,85) + 0.0) * 1.0);
        if (CELSIUS)
        {
        egoTemp = (int)((MSUtils.getByte(pageBuffer,86) + -72.0) * 0.555);
        }
        else
        {
        egoTemp = (int)((MSUtils.getByte(pageBuffer,86) + -40.0) * 1.0);
        }
        egoCount = (int)((MSUtils.getByte(pageBuffer,87) + 0.0) * 1.0);
        egoDelta = (int)((MSUtils.getByte(pageBuffer,88) + 0.0) * 1.0);
        egoLimit = (int)((MSUtils.getByte(pageBuffer,89) + 0.0) * 1.0);
        reqFuel = (double)((MSUtils.getByte(pageBuffer,90) + 0.0) * 0.1);
        divider = (int)((MSUtils.getByte(pageBuffer,91) + 0.0) * 1.0);
        alternate = MSUtils.getBits(pageBuffer,92,0,0,0);
        injOpen = (double)((MSUtils.getByte(pageBuffer,93) + 0.0) * 0.1);
        injOCfuel = (double)((MSUtils.getByte(pageBuffer,94) + 0.0) * 0.1);
        injPwmP = (int)((MSUtils.getByte(pageBuffer,95) + 0.0) * 1.0);
        injPwmT = (double)((MSUtils.getByte(pageBuffer,96) + 0.0) * 0.1);
        battFac = (double)((MSUtils.getByte(pageBuffer,97) + 0.0) * 0.0166667);
        rpmk = (int)((MSUtils.getWord(pageBuffer,98) + 0.0) * 1.0);
        if (SPEED_DENSITY)
        {
        }
        else if (ALPHA_N)
        {
        }
        else if (AIR_FLOW_METER)
        {
        
        
        }
        mapType = MSUtils.getBits(pageBuffer,116,0,1,0);
        twoStroke = MSUtils.getBits(pageBuffer,116,2,2,0);
        injType = MSUtils.getBits(pageBuffer,116,3,3,0);
        nCylinders = MSUtils.getBits(pageBuffer,116,4,7,1);
        cltType = MSUtils.getBits(pageBuffer,117,0,1,0);
        matType = MSUtils.getBits(pageBuffer,117,2,3,0);
        nInjectors = MSUtils.getBits(pageBuffer,117,4,7,1);
        engineType = MSUtils.getBits(pageBuffer,118,0,0,0);
        egoType = MSUtils.getBits(pageBuffer,118,1,1,0);
        algorithm = MSUtils.getBits(pageBuffer,118,2,2,0);
        baroCorr = MSUtils.getBits(pageBuffer,118,3,3,0);
        primePulse = (double)((MSUtils.getByte(pageBuffer,119) + 0.0) * 0.1);
        egoRPM = (double)((MSUtils.getByte(pageBuffer,120) + 0.0) * 100.0);
        if (CELSIUS)
        {
        fastIdleT = (int)((MSUtils.getByte(pageBuffer,121) + -72.0) * 0.555);
        }
        else
        {
        fastIdleT = (int)((MSUtils.getByte(pageBuffer,121) + -40.0) * 1.0);
        }
        egoSwitch = (double)((MSUtils.getByte(pageBuffer,122) + 0.0) * 0.0196);
        taeColdM = (int)((MSUtils.getByte(pageBuffer,123) + 0.0) * 1.0);
    }

}

