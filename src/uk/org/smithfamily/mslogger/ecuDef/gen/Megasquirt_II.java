package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.*;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
/*
Fingerprint : 537bc13785850130f6eefcd63af83264
*/
public class Megasquirt_II extends Megasquirt
{
    public Megasquirt_II(Context c)
    {
        super(c);
        refreshFlags();
    }
    @Override
    public void refreshFlags()
    {
        NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
        CELSIUS = isSet("CELSIUS");
        LAMBDA = isSet("LAMBDA");
    }
    private Map<String,Double> fields = new HashMap<String,Double>();
    byte[] queryCommand=new byte[]{'Q'};
    String signature="MSII Rev 2.30000   \0";
    byte [] ochGetCommand = new byte[]{97,0,6};
    int ochBlockSize = 112;
//Flags
    public boolean NARROW_BAND_EGO;
    public boolean CELSIUS;
    public boolean LAMBDA;
//Defaults
//Variables
    public double accDecEnrich;
    public double accelEnrich;
    public double advance;
    public double afr1;
    public double afr2;
    public double afrtgt1;
    public double afrtgt2;
    public int airCorrection;
    public int altDiv1;
    public int altDiv2;
    public int baroCorrection;
    public double barometer;
    public double batteryVoltage;
    public double calcMAP;
    public double coldAdvDeg;
    public double coolant;
    public int crank;
    public double cycleTime1;
    public double cycleTime2;
    public int deadValue;
    public int deltaT;
    public double dutyCycle1;
    public double dutyCycle2;
    public double dwell;
    public double egoCorrection;
    public int egoCorrection1;
    public int egoCorrection2;
    public double egoVoltage;
    public int engine;
    public int firing1;
    public int firing2;
    public int fuelCorrection;
    public int gammaEnrich;
    public int iacstep;
    public int idleDC;
    public int inj1;
    public int inj2;
    public double knock;
    public double lambda1;
    public double lambda2;
    public int maf;
    public double map;
    public int mapDOT;
    public int mapaen;
    public double mat;
    public int nSquirts1;
    public int nSquirts2;
    public int port0;
    public int port1;
    public int port2;
    public int port3;
    public int port4;
    public int port5;
    public int port6;
    public int portStatus;
    public double pulseWidth;
    public double pulseWidth1;
    public double pulseWidth2;
    public int ready;
    public int rpm;
    public double rpm100;
    public int sched1;
    public int sched2;
    public int secl;
    public int seconds;
    public int squirt;
    public int startw;
    public double throttle;
    public double time;
    public double tps;
    public double tpsADC;
    public double tpsDOT;
    public int tpsaen;
    public int tpsden;
    public int tpsfuelcut;
    public double veCurr;
    public int veCurr1;
    public int veCurr2;
    public int warmup;
    public int warmupEnrich;
    public int wbo2_en1;
    public int wbo2_en2;

//Constants
    public int taeColdM;
    public double triggerOffset;
    public int egoRPM;
    public int tempUnits;
    public double dwellAcc;
    public double baro0;
    public int spkout_hi_lo;
    public double IdleHyst;
    public int tpsMin;
    public double primePulseHot;
    public int alpha_hirpm;
    public double taeColdA;
    public int knkLF;
    public double RevLimMaxRtd;
    public int egoType;
    public int egoDelta;
    public int alternate;
    public int egoKI;
    public int cltmult;
    public double crankCold;
    public int IACnaccstep;
    public int divider;
    public int egoKD;
    public int bcormult;
    public int crankingRPM;
    public int matmult;
    public double egoTemp;
    public double MAPOXLimit;
    public double egoTarget;
    public int asePctHot;
    public int tpsMax;
    public int aseCount;
    public int board_id_type0;
    public int bcor0;
    public int adcLF;
    public double map0;
    public int egomult;
    public int egoKP;
    public int egoKdelay2;
    public int egoKdelay1;
    public double max_spk_dur;
    public int PredOpt;
    public int egoLF;
    public int tdePct;
    public int algorithm;
    public int flexFuel;
    public double aeEndPW;
    public int IACcoldpos;
    public double mapmax;
    public int ICCrankTrigger;
    public double taeTime;
    public double batt0;
    public int Dtpred_Gain;
    public int egoCount;
    public int ICIgnOption;
    public double TPSOXLimit;
    public double fastIdleT;
    public int tpsLF;
    public double baromax;
    public double battFac;
    public int engineType;
    public int IgnAlpha;
    public int IACStart;
    public double ego0;
    public int nInjectors;
    public int no_skip_pulses;
    public int mapLF;
    public int injType;
    public double max_coil_dur;
    public int IACcrankxt;
    public int IdleCtl;
    public int pulseTolerance;
    public double injOpen;
    public int aseCountHot;
    public int BLMOption;
    public double IACcoldtmp;
    public int egoAlgorithm;
    public int dualTable;
    public int alpha_lorpm;
    public int fuelFreq0;
    public double floodClear;
    public int fuelFreq1;
    public int mapThresh;
    public double primePulse;
    public double clt0;
    public int twoStroke;
    public int fuelCorr0;
    public int fuelCorr1;
    public double knockmax;
    public int IACcoldxt;
    public double mat0;
    public int baud;
    public double tpsThresh;
    public double reqFuel;
    public int injPwmPd;
    public int tpsProportion;
    public int nCylinders;
    public int ICIgnCapture;
    public double battmax;
    public double IACaccstep;
    public int mafOption;
    public double knock0;
    public int asePct;
    public int RevLimOption;
    public int baroCorr;
    public int IACcrankpos;
    public int crankTolerance;
    public int injPwmP;
    public double aeTaperTime;
    public double crankHot;
    public int RevLimRpm2;
    public int asTolerance;
    public int rpmLF;
    public int RevLimRpm1;
    public int AfrAlpha;
    public double IACtstep;
    public int egoLimit;
    public int OddFire2;
    public double injPwmT;
    public double AFRTarget;


    private String[] defaultGauges = {
        "tachometer",
        "throttleGauge",
        "pulseWidth1Gauge",
        "egoGauge",
        "lambda1Gauge",
        "afr1Gauge",
        "mapGauge",
        "IACgauge",
        "PWMIdlegauge",
        "matGauge",
        "advdegGauge",
        "dwellGauge"
    };
	@Override
	public void calculate(byte[] ochBuffer) throws IOException
    {
        deadValue = (0);
        seconds = (int)((MSUtils.getWord(ochBuffer,0) + 0.0) * 1.000);
        secl = (seconds % 256);
        pulseWidth1 = (double)((MSUtils.getWord(ochBuffer,2) + 0.0) * 0.001);
        pulseWidth2 = (double)((MSUtils.getWord(ochBuffer,4) + 0.0) * 0.001);
        pulseWidth = (pulseWidth1);
        rpm = (int)((MSUtils.getWord(ochBuffer,6) + 0.0) * 1.000);
        advance = (double)((MSUtils.getSignedWord(ochBuffer,8) + 0.0) * 0.100);
        squirt = (int)((MSUtils.getByte(ochBuffer,10) + 0.0) * 1.000);
        firing1 = MSUtils.getBits(ochBuffer,10,0,0,0);
        firing2 = MSUtils.getBits(ochBuffer,10,1,1,0);
        sched1 = MSUtils.getBits(ochBuffer,10,2,2,0);
        inj1 = MSUtils.getBits(ochBuffer,10,3,3,0);
        sched2 = MSUtils.getBits(ochBuffer,10,4,4,0);
        inj2 = MSUtils.getBits(ochBuffer,10,5,5,0);
        engine = (int)((MSUtils.getByte(ochBuffer,11) + 0.0) * 1.000);
        ready = MSUtils.getBits(ochBuffer,11,0,0,0);
        crank = MSUtils.getBits(ochBuffer,11,1,1,0);
        startw = MSUtils.getBits(ochBuffer,11,2,2,0);
        warmup = MSUtils.getBits(ochBuffer,11,3,3,0);
        tpsaen = MSUtils.getBits(ochBuffer,11,4,4,0);
        tpsden = MSUtils.getBits(ochBuffer,11,5,5,0);
        mapaen = MSUtils.getBits(ochBuffer,11,6,6,0);
        afrtgt1 = (double)((MSUtils.getByte(ochBuffer,12) + 0.0) * 10.00);
        afrtgt2 = (double)((MSUtils.getByte(ochBuffer,13) + 0.0) * 10.00);
        wbo2_en1 = (int)((MSUtils.getByte(ochBuffer,14) + 0.0) * 1.000);
        wbo2_en2 = (int)((MSUtils.getByte(ochBuffer,15) + 0.0) * 1.000);
        barometer = (double)((MSUtils.getSignedWord(ochBuffer,16) + 0.0) * 0.100);
        map = (double)((MSUtils.getSignedWord(ochBuffer,18) + 0.0) * 0.100);
        if (CELSIUS)
        {
        mat = (double)((MSUtils.getSignedWord(ochBuffer,20) + -320.0) * 0.05555);
        coolant = (double)((MSUtils.getSignedWord(ochBuffer,22) + -320.0) * 0.05555);
        }
        else
        {
        mat = (double)((MSUtils.getSignedWord(ochBuffer,20) + 0.0) * 0.100);
        coolant = (double)((MSUtils.getSignedWord(ochBuffer,22) + 0.0) * 0.100);
        }
        tps = (double)((MSUtils.getSignedWord(ochBuffer,24) + 0.0) * 0.100);
        tpsADC = (tps*10.23);
        throttle = (tps);
        batteryVoltage = (double)((MSUtils.getSignedWord(ochBuffer,26) + 0.0) * 0.100);
        afr1 = (double)((MSUtils.getSignedWord(ochBuffer,28) + 0.0) * 0.100);
        afr2 = (double)((MSUtils.getSignedWord(ochBuffer,30) + 0.0) * 0.100);
        lambda1 = (afr1 / 14.7);
        lambda2 = (afr2 / 14.7);
        knock = (double)((MSUtils.getSignedWord(ochBuffer,32) + 0.0) * 0.100);
        egoCorrection1 = (int)((MSUtils.getSignedWord(ochBuffer,34) + 0.0) * 1.000);
        egoCorrection = (( egoCorrection1 + egoCorrection2) / 2);
        egoCorrection2 = (int)((MSUtils.getSignedWord(ochBuffer,36) + 0.0) * 1.000);
        airCorrection = (int)((MSUtils.getSignedWord(ochBuffer,38) + 0.0) * 1.000);
        warmupEnrich = (int)((MSUtils.getSignedWord(ochBuffer,40) + 0.0) * 1.000);
        accelEnrich = (double)((MSUtils.getSignedWord(ochBuffer,42) + 0.0) * 0.100);
        tpsfuelcut = (int)((MSUtils.getSignedWord(ochBuffer,44) + 0.0) * 1.000);
        baroCorrection = (int)((MSUtils.getSignedWord(ochBuffer,46) + 0.0) * 1.000);
        gammaEnrich = (int)((MSUtils.getSignedWord(ochBuffer,48) + 0.0) * 1.000);
        veCurr1 = (int)((MSUtils.getSignedWord(ochBuffer,50) + 0.0) * 1.000);
        veCurr2 = (int)((MSUtils.getSignedWord(ochBuffer,52) + 0.0) * 1.000);
        veCurr = (veCurr1);
        iacstep = (int)((MSUtils.getSignedWord(ochBuffer,54) + 0.0) * 1.000);
        idleDC = (iacstep);
        coldAdvDeg = (double)((MSUtils.getSignedWord(ochBuffer,56) + 0.0) * 0.100);
        tpsDOT = (double)((MSUtils.getSignedWord(ochBuffer,58) + 0.0) * 0.100);
        mapDOT = (int)((MSUtils.getSignedWord(ochBuffer,60) + 0.0) * 1.000);
        dwell = (double)((MSUtils.getSignedWord(ochBuffer,62) + 0.0) * 0.100);
        maf = (int)((MSUtils.getSignedWord(ochBuffer,64) + 0.0) * 1.000);
        calcMAP = (double)((MSUtils.getSignedWord(ochBuffer,66) + 0.0) * 0.100);
        fuelCorrection = (int)((MSUtils.getSignedWord(ochBuffer,68) + 0.0) * 1.000);
        portStatus = (int)((MSUtils.getByte(ochBuffer,70) + 0.0) * 1.000);
        port0 = MSUtils.getBits(ochBuffer,70,0,0,0);
        port1 = MSUtils.getBits(ochBuffer,70,1,1,0);
        port2 = MSUtils.getBits(ochBuffer,70,2,2,0);
        port3 = MSUtils.getBits(ochBuffer,70,3,3,0);
        port4 = MSUtils.getBits(ochBuffer,70,4,4,0);
        port5 = MSUtils.getBits(ochBuffer,70,5,5,0);
        port6 = MSUtils.getBits(ochBuffer,70,6,6,0);
        deltaT = (int)((MSUtils.getSignedLong(ochBuffer,108) + 0.0) * 1.000);
        accDecEnrich = (((accelEnrich + (tpsden ) != 0 ) ?  tpsfuelcut : 100));
        time = (timeNow());
        rpm100 = (rpm / 100.0);
        altDiv1 = (((alternate ) != 0 ) ?  2 : 1);
        altDiv2 = (((alternate ) != 0 ) ?  2 : 1);
        cycleTime1 = (60000.0 / rpm * (2.0-twoStroke));
        nSquirts1 = (nCylinders/divider);
        dutyCycle1 = (100.0*nSquirts1/altDiv1*pulseWidth1/cycleTime1);
        cycleTime2 = (60000.0 / rpm * (2.0-twoStroke));
        nSquirts2 = (nCylinders/divider);
        dutyCycle2 = (100.0*nSquirts2/altDiv2*pulseWidth2/cycleTime2);
        if (NARROW_BAND_EGO)
        {
        egoVoltage = (1.0-afr1*0.04883);
        }
        else if (LAMBDA)
        {
        egoVoltage = (lambda1);
        }
        else
        {
        egoVoltage = (afr1);
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
        if (NARROW_BAND_EGO)
        {
        b.append("O2").append("\t");
        }
        else if (LAMBDA)
        {
        b.append("Lambda").append("\t");
        }
        else
        {
        b.append("AFR").append("\t");
        }
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
        b.append("DutyCycle1").append("\t");
        b.append("Gve2").append("\t");
        b.append("PW2").append("\t");
        b.append("DutyCycle2").append("\t");
        b.append("SparkAdv").append("\t");
        b.append("ColdAdv").append("\t");
        b.append("Dwell").append("\t");
        b.append("tpsDOT").append("\t");
        b.append("mapDOT").append("\t");
        b.append("IACstep").append("\t");
        b.append("deltaT").append("\t");
        b.append(MSUtils.getLocationLogHeader());
        return b.toString();
    }

    @Override
    public String getLogRow()
    {
        StringBuffer b = new StringBuffer();
        b.append(time).append("\t");
        b.append(seconds).append("\t");
        b.append(rpm).append("\t");
        b.append(round(map)).append("\t");
        b.append(throttle).append("\t");
        if (NARROW_BAND_EGO)
        {
        b.append(egoVoltage).append("\t");
        }
        else if (LAMBDA)
        {
        b.append(lambda1).append("\t");
        }
        else
        {
        b.append(round(afr1)).append("\t");
        }
        b.append(round(mat)).append("\t");
        b.append(round(coolant)).append("\t");
        b.append(engine).append("\t");
        b.append(egoCorrection).append("\t");
        b.append(airCorrection).append("\t");
        b.append(warmupEnrich).append("\t");
        b.append(baroCorrection).append("\t");
        b.append(gammaEnrich).append("\t");
        b.append(accDecEnrich).append("\t");
        b.append(veCurr1).append("\t");
        b.append(round(pulseWidth1)).append("\t");
        b.append(dutyCycle1).append("\t");
        b.append(veCurr2).append("\t");
        b.append(round(pulseWidth2)).append("\t");
        b.append(dutyCycle2).append("\t");
        b.append(round(advance)).append("\t");
        b.append(round(coldAdvDeg)).append("\t");
        b.append(round(dwell)).append("\t");
        b.append(round(tpsDOT)).append("\t");
        b.append(mapDOT).append("\t");
        b.append(iacstep).append("\t");
        b.append(deltaT).append("\t");
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("advdegGauge","advance",advance,"Ignition Advance","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("IACgauge","iacstep",iacstep,"IAC position","steps",0,255,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dwellGauge","dwell",dwell,"Dwell","mSec",0,10,0.5,1.0,6.0,8.0,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("PWMIdlegauge","idleDC",idleDC,"Idle PWM%","%",0,100,-1,-1,999,90,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelEnrichGauge","accDecEnrich",accDecEnrich,"Accel Enrich","%",50,150,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1Gauge","afr1",afr1,"Air:Fuel Ratio","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2Gauge","afr2",afr2,"Air:Fuel Ratio","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clockGauge","seconds",seconds,"Clock","Seconds",0,255,10,10,245,245,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge","deadValue",deadValue,"---","",0,1,-1,-1,2,2,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle1Gauge","dutyCycle1",dutyCycle1,"Duty Cycle 1","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle2Gauge","dutyCycle2",dutyCycle2,"Duty Cycle 2","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge","egoCorrection",egoCorrection,"EGO Correction","%",50,150,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge1","egoCorrection1",egoCorrection1,"EGO Correction 1","%",50,150,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge2","egoCorrection2",egoCorrection2,"EGO Correction 2","%",50,150,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge","egoVoltage",egoVoltage,"Exhaust Gas Oxygen","volts",0,1.0,0.2,0.3,0.7,0.8,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda1Gauge","lambda1",lambda1,"Lambda","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda2Gauge","lambda2",lambda2,"Lambda","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaEnrichGauge","gammaEnrich",gammaEnrich,"Gamma Enrichment","%",50,150,-1,-1,151,151,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge","map",map,"Engine MAP","kPa",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("calcMapGauge","calcMAP",calcMAP,"Estimated MAP","kPa",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth1Gauge","pulseWidth1",pulseWidth1,"Pulse Width 1","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth2Gauge","pulseWidth2",pulseWidth2,"Pulse Width 2","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tachometer","rpm",rpm,"Engine Speed","RPM",0,8000,300,600,3000,5000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("throttleGauge","throttle",throttle,"Throttle Position","%",0,100,-1,1,90,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge","veCurr1",veCurr1,"VE Current","%",0,120,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("voltMeter","batteryVoltage",batteryVoltage,"Battery Voltage","volts",7,21,8,9,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("warmupEnrichGauge","warmupEnrich",warmupEnrich,"Warmup Enrichment","%",100,150,-1,-1,101,105,0,0,45));
        if (CELSIUS)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","°C",-40,120,-15,0,95,105,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Manifold Air Temp","°C",-40,110,-15,0,95,100,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","°F",-40,250,0,30,200,220,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Manifold Air Temp","°F",-40,215,0,30,200,210,0,0,45));
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
        return 50;
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
        return 0;
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
        pageBuffer = loadPage(1,0,914,null,new byte[]{114,0,4,0,0,3,-110});
        nCylinders = MSUtils.getBits(pageBuffer,0,0,3,0);
        no_skip_pulses = (int)((MSUtils.getByte(pageBuffer,1) + 0.0) * 1.0);
        ICIgnCapture = MSUtils.getBits(pageBuffer,2,0,0,0);
        ICCrankTrigger = MSUtils.getBits(pageBuffer,2,1,2,0);
        ICIgnOption = MSUtils.getBits(pageBuffer,2,4,5,0);
        spkout_hi_lo = MSUtils.getBits(pageBuffer,3,0,0,0);
        max_coil_dur = (double)((MSUtils.getByte(pageBuffer,4) + 0.0) * 0.1);
        max_spk_dur = (double)((MSUtils.getByte(pageBuffer,5) + 0.0) * 0.1);
        dwellAcc = (double)((MSUtils.getByte(pageBuffer,6) + 0.0) * 0.1);
        RevLimOption = MSUtils.getBits(pageBuffer,17,0,1,0);
        RevLimMaxRtd = (double)((MSUtils.getSignedByte(pageBuffer,18) + 0.0) * 0.1);
        PredOpt = MSUtils.getBits(pageBuffer,19,0,1,0);
        crankingRPM = (int)((MSUtils.getSignedWord(pageBuffer,20) + 0.0) * 1.0);
        triggerOffset = (double)((MSUtils.getSignedWord(pageBuffer,42) + 0.0) * 0.1);
        RevLimRpm1 = (int)((MSUtils.getSignedWord(pageBuffer,44) + 0.0) * 1.0);
        RevLimRpm2 = (int)((MSUtils.getSignedWord(pageBuffer,46) + 0.0) * 1.0);
        if (LAMBDA)
        {
        }
        else
        {
        }
        if (CELSIUS)
        {
        }
        else
        {
        }
        map0 = (double)((MSUtils.getSignedWord(pageBuffer,506) + 0.0) * 0.1);
        mapmax = (double)((MSUtils.getSignedWord(pageBuffer,508) + 0.0) * 0.1);
        if (CELSIUS)
        {
        clt0 = (double)((MSUtils.getSignedWord(pageBuffer,510) + -320.0) * 0.05555);
        cltmult = (int)((MSUtils.getSignedWord(pageBuffer,512) + 0.0) * 1.0);
        mat0 = (double)((MSUtils.getSignedWord(pageBuffer,514) + -320.0) * 0.05555);
        }
        else
        {
        clt0 = (double)((MSUtils.getSignedWord(pageBuffer,510) + 0.0) * 0.1);
        cltmult = (int)((MSUtils.getSignedWord(pageBuffer,512) + 0.0) * 1.0);
        mat0 = (double)((MSUtils.getSignedWord(pageBuffer,514) + 0.0) * 0.1);
        }
        matmult = (int)((MSUtils.getSignedWord(pageBuffer,516) + 0.0) * 1.0);
        tpsMin = (int)((MSUtils.getSignedWord(pageBuffer,518) + 0.0) * 1.0);
        tpsMax = (int)((MSUtils.getSignedWord(pageBuffer,520) + 0.0) * 1.0);
        batt0 = (double)((MSUtils.getSignedWord(pageBuffer,522) + 0.0) * 0.1);
        battmax = (double)((MSUtils.getSignedWord(pageBuffer,524) + 0.0) * 0.1);
        ego0 = (double)((MSUtils.getSignedWord(pageBuffer,526) + 0.0) * 0.1);
        egomult = (int)((MSUtils.getSignedWord(pageBuffer,528) + 0.0) * 1.0);
        baro0 = (double)((MSUtils.getSignedWord(pageBuffer,530) + 0.0) * 0.1);
        baromax = (double)((MSUtils.getSignedWord(pageBuffer,532) + 0.0) * 0.1);
        bcor0 = (int)((MSUtils.getSignedWord(pageBuffer,534) + 0.0) * 1.0);
        bcormult = (int)((MSUtils.getSignedWord(pageBuffer,536) + 0.0) * 1.0);
        knock0 = (double)((MSUtils.getSignedWord(pageBuffer,538) + 0.0) * 0.01);
        knockmax = (double)((MSUtils.getSignedWord(pageBuffer,540) + 0.0) * 0.01);
        Dtpred_Gain = (int)((MSUtils.getSignedWord(pageBuffer,542) + 0.0) * 1.0);
        crankTolerance = (int)((MSUtils.getByte(pageBuffer,544) + 0.0) * 1.0);
        asTolerance = (int)((MSUtils.getByte(pageBuffer,545) + 0.0) * 1.0);
        pulseTolerance = (int)((MSUtils.getByte(pageBuffer,546) + 0.0) * 1.0);
        IdleCtl = MSUtils.getBits(pageBuffer,547,0,2,0);
        IACtstep = (double)((MSUtils.getByte(pageBuffer,548) + 0.0) * 0.1);
        IACaccstep = (double)((MSUtils.getByte(pageBuffer,549) + 0.0) * 0.1);
        IACnaccstep = (int)((MSUtils.getByte(pageBuffer,550) + 0.0) * 1.0);
        IACStart = (int)((MSUtils.getSignedWord(pageBuffer,552) + 0.0) * 1.0);
        if (CELSIUS)
        {
        IdleHyst = (double)((MSUtils.getSignedWord(pageBuffer,554) + -320.0) * 0.05555);
        }
        else
        {
        IdleHyst = (double)((MSUtils.getSignedWord(pageBuffer,554) + 0.0) * 0.1);
        }
        IACcrankpos = (int)((MSUtils.getSignedWord(pageBuffer,556) + 0.0) * 1.0);
        IACcrankxt = (int)((MSUtils.getSignedWord(pageBuffer,558) + 0.0) * 1.0);
        if (CELSIUS)
        {
        IACcoldtmp = (double)((MSUtils.getSignedWord(pageBuffer,560) + -320.0) * 0.05555);
        }
        else
        {
        IACcoldtmp = (double)((MSUtils.getSignedWord(pageBuffer,560) + 0.0) * 0.1);
        }
        IACcoldpos = (int)((MSUtils.getSignedWord(pageBuffer,562) + 0.0) * 1.0);
        IACcoldxt = (int)((MSUtils.getSignedWord(pageBuffer,564) + 0.0) * 1.0);
        primePulse = (double)((MSUtils.getSignedWord(pageBuffer,566) + 0.0) * 0.1);
        primePulseHot = (double)((MSUtils.getSignedWord(pageBuffer,568) + 0.0) * 0.1);
        crankCold = (double)((MSUtils.getSignedWord(pageBuffer,570) + 0.0) * 0.1);
        crankHot = (double)((MSUtils.getSignedWord(pageBuffer,572) + 0.0) * 0.1);
        asePct = (int)((MSUtils.getSignedWord(pageBuffer,574) + 0.0) * 1.0);
        asePctHot = (int)((MSUtils.getSignedWord(pageBuffer,576) + 0.0) * 1.0);
        aseCount = (int)((MSUtils.getSignedWord(pageBuffer,578) + 0.0) * 1.0);
        aseCountHot = (int)((MSUtils.getSignedWord(pageBuffer,580) + 0.0) * 1.0);
        taeColdA = (double)((MSUtils.getByte(pageBuffer,582) + 0.0) * 0.1);
        taeColdM = (int)((MSUtils.getByte(pageBuffer,583) + 0.0) * 1.0);
        tpsThresh = (double)((MSUtils.getByte(pageBuffer,584) + 0.0) * 0.1);
        mapThresh = (int)((MSUtils.getByte(pageBuffer,585) + 0.0) * 1.0);
        taeTime = (double)((MSUtils.getByte(pageBuffer,586) + 0.0) * 0.1);
        tdePct = (int)((MSUtils.getByte(pageBuffer,587) + 0.0) * 1.0);
        floodClear = (double)((MSUtils.getSignedWord(pageBuffer,588) + 0.0) * 0.1);
        TPSOXLimit = (double)((MSUtils.getSignedWord(pageBuffer,590) + 0.0) * 0.1);
        tpsProportion = (int)((MSUtils.getByte(pageBuffer,592) + 0.0) * 1.0);
        baroCorr = MSUtils.getBits(pageBuffer,593,0,1,0);
        egoType = MSUtils.getBits(pageBuffer,594,0,1,0);
        egoCount = (int)((MSUtils.getByte(pageBuffer,595) + 0.0) * 1.0);
        egoDelta = (int)((MSUtils.getByte(pageBuffer,596) + 0.0) * 1.0);
        egoLimit = (int)((MSUtils.getByte(pageBuffer,597) + 0.0) * 1.0);
        if (NARROW_BAND_EGO)
        {
        egoTarget = (double)((MSUtils.getByte(pageBuffer,598) + -204.8) * -0.004883);
        }
        else if (LAMBDA)
        {
        AFRTarget = (double)((MSUtils.getByte(pageBuffer,598) + 0.0) * 0.006803);
        }
        else
        {
        AFRTarget = (double)((MSUtils.getByte(pageBuffer,598) + 0.0) * 0.1);
        }
        tempUnits = MSUtils.getBits(pageBuffer,599,0,0,0);
        mafOption = MSUtils.getBits(pageBuffer,600,0,7,0);
        if (CELSIUS)
        {
        fastIdleT = (double)((MSUtils.getSignedWord(pageBuffer,602) + -320.0) * 0.05555);
        egoTemp = (double)((MSUtils.getSignedWord(pageBuffer,604) + -320.0) * 0.05555);
        }
        else
        {
        fastIdleT = (double)((MSUtils.getSignedWord(pageBuffer,602) + 0.0) * 0.1);
        egoTemp = (double)((MSUtils.getSignedWord(pageBuffer,604) + 0.0) * 0.1);
        }
        egoRPM = (int)((MSUtils.getSignedWord(pageBuffer,606) + 0.0) * 1.0);
        reqFuel = (double)((MSUtils.getWord(pageBuffer,608) + 0.0) * 0.0010);
        divider = (int)((MSUtils.getByte(pageBuffer,610) + 0.0) * 1.0);
        alternate = MSUtils.getBits(pageBuffer,611,0,0,0);
        injOpen = (double)((MSUtils.getByte(pageBuffer,612) + 0.0) * 0.1);
        injPwmT = (double)((MSUtils.getByte(pageBuffer,613) + 0.0) * 0.1);
        injPwmPd = (int)((MSUtils.getByte(pageBuffer,614) + 0.0) * 1.0);
        injPwmP = (int)((MSUtils.getByte(pageBuffer,615) + 0.0) * 1.0);
        battFac = (double)((MSUtils.getByte(pageBuffer,616) + 0.0) * 0.0166667);
        twoStroke = MSUtils.getBits(pageBuffer,617,0,0,0);
        injType = MSUtils.getBits(pageBuffer,618,0,0,0);
        nInjectors = MSUtils.getBits(pageBuffer,619,0,3,0);
        engineType = MSUtils.getBits(pageBuffer,620,0,0,0);
        OddFire2 = (int)((MSUtils.getByte(pageBuffer,621) + 0.0) * 1.0);
        rpmLF = (int)((MSUtils.getByte(pageBuffer,622) + 0.0) * 1.0);
        mapLF = (int)((MSUtils.getByte(pageBuffer,623) + 0.0) * 1.0);
        tpsLF = (int)((MSUtils.getByte(pageBuffer,624) + 0.0) * 1.0);
        egoLF = (int)((MSUtils.getByte(pageBuffer,625) + 0.0) * 1.0);
        adcLF = (int)((MSUtils.getByte(pageBuffer,626) + 0.0) * 1.0);
        knkLF = (int)((MSUtils.getByte(pageBuffer,627) + 0.0) * 1.0);
        BLMOption = MSUtils.getBits(pageBuffer,628,0,0,0);
        dualTable = MSUtils.getBits(pageBuffer,629,0,0,0);
        algorithm = MSUtils.getBits(pageBuffer,630,0,1,0);
        IgnAlpha = (int)((MSUtils.getByte(pageBuffer,631) + 0.0) * 1.0);
        AfrAlpha = (int)((MSUtils.getByte(pageBuffer,632) + 0.0) * 1.0);
        alpha_lorpm = (int)((MSUtils.getSignedWord(pageBuffer,634) + 0.0) * 1.0);
        alpha_hirpm = (int)((MSUtils.getSignedWord(pageBuffer,636) + 0.0) * 1.0);
        baud = (int)((MSUtils.getLong(pageBuffer,734) + 0.0) * 1.0);
        MAPOXLimit = (double)((MSUtils.getSignedWord(pageBuffer,738) + 0.0) * 0.1);
        board_id_type0 = MSUtils.getBits(pageBuffer,740,0,7,0);
        aeTaperTime = (double)((MSUtils.getByte(pageBuffer,896) + 0.0) * 0.1);
        aeEndPW = (double)((MSUtils.getSignedWord(pageBuffer,898) + 0.0) * 0.1);
        egoAlgorithm = MSUtils.getBits(pageBuffer,900,0,1,0);
        egoKP = (int)((MSUtils.getByte(pageBuffer,901) + 0.0) * 1.0);
        egoKI = (int)((MSUtils.getByte(pageBuffer,902) + 0.0) * 1.0);
        egoKD = (int)((MSUtils.getByte(pageBuffer,903) + 0.0) * 1.0);
        egoKdelay1 = (int)((MSUtils.getWord(pageBuffer,904) + 0.0) * 1.0);
        egoKdelay2 = (int)((MSUtils.getWord(pageBuffer,906) + 0.0) * 1.0);
        flexFuel = MSUtils.getBits(pageBuffer,908,0,0,0);
        fuelFreq0 = (int)((MSUtils.getByte(pageBuffer,910) + 0.0) * 1.0);
        fuelFreq1 = (int)((MSUtils.getByte(pageBuffer,911) + 0.0) * 1.0);
        fuelCorr0 = (int)((MSUtils.getByte(pageBuffer,912) + 0.0) * 1.0);
        fuelCorr1 = (int)((MSUtils.getByte(pageBuffer,913) + 0.0) * 1.0);
    }
    @Override
    public DataPacket getDataPacket()
    {
        fields.put("accDecEnrich",(double)accDecEnrich);
        fields.put("accelEnrich",(double)accelEnrich);
        fields.put("advance",(double)advance);
        fields.put("afr1",(double)afr1);
        fields.put("afr2",(double)afr2);
        fields.put("afrtgt1",(double)afrtgt1);
        fields.put("afrtgt2",(double)afrtgt2);
        fields.put("airCorrection",(double)airCorrection);
        fields.put("altDiv1",(double)altDiv1);
        fields.put("altDiv2",(double)altDiv2);
        fields.put("baroCorrection",(double)baroCorrection);
        fields.put("barometer",(double)barometer);
        fields.put("batteryVoltage",(double)batteryVoltage);
        fields.put("calcMAP",(double)calcMAP);
        fields.put("coldAdvDeg",(double)coldAdvDeg);
        fields.put("coolant",(double)coolant);
        fields.put("crank",(double)crank);
        fields.put("cycleTime1",(double)cycleTime1);
        fields.put("cycleTime2",(double)cycleTime2);
        fields.put("deadValue",(double)deadValue);
        fields.put("deltaT",(double)deltaT);
        fields.put("dutyCycle1",(double)dutyCycle1);
        fields.put("dutyCycle2",(double)dutyCycle2);
        fields.put("dwell",(double)dwell);
        fields.put("egoCorrection",(double)egoCorrection);
        fields.put("egoCorrection1",(double)egoCorrection1);
        fields.put("egoCorrection2",(double)egoCorrection2);
        fields.put("egoVoltage",(double)egoVoltage);
        fields.put("engine",(double)engine);
        fields.put("firing1",(double)firing1);
        fields.put("firing2",(double)firing2);
        fields.put("fuelCorrection",(double)fuelCorrection);
        fields.put("gammaEnrich",(double)gammaEnrich);
        fields.put("iacstep",(double)iacstep);
        fields.put("idleDC",(double)idleDC);
        fields.put("inj1",(double)inj1);
        fields.put("inj2",(double)inj2);
        fields.put("knock",(double)knock);
        fields.put("lambda1",(double)lambda1);
        fields.put("lambda2",(double)lambda2);
        fields.put("maf",(double)maf);
        fields.put("map",(double)map);
        fields.put("mapDOT",(double)mapDOT);
        fields.put("mapaen",(double)mapaen);
        fields.put("mat",(double)mat);
        fields.put("nSquirts1",(double)nSquirts1);
        fields.put("nSquirts2",(double)nSquirts2);
        fields.put("port0",(double)port0);
        fields.put("port1",(double)port1);
        fields.put("port2",(double)port2);
        fields.put("port3",(double)port3);
        fields.put("port4",(double)port4);
        fields.put("port5",(double)port5);
        fields.put("port6",(double)port6);
        fields.put("portStatus",(double)portStatus);
        fields.put("pulseWidth",(double)pulseWidth);
        fields.put("pulseWidth1",(double)pulseWidth1);
        fields.put("pulseWidth2",(double)pulseWidth2);
        fields.put("ready",(double)ready);
        fields.put("rpm",(double)rpm);
        fields.put("rpm100",(double)rpm100);
        fields.put("sched1",(double)sched1);
        fields.put("sched2",(double)sched2);
        fields.put("secl",(double)secl);
        fields.put("seconds",(double)seconds);
        fields.put("squirt",(double)squirt);
        fields.put("startw",(double)startw);
        fields.put("throttle",(double)throttle);
        fields.put("time",(double)time);
        fields.put("tps",(double)tps);
        fields.put("tpsADC",(double)tpsADC);
        fields.put("tpsDOT",(double)tpsDOT);
        fields.put("tpsaen",(double)tpsaen);
        fields.put("tpsden",(double)tpsden);
        fields.put("tpsfuelcut",(double)tpsfuelcut);
        fields.put("veCurr",(double)veCurr);
        fields.put("veCurr1",(double)veCurr1);
        fields.put("veCurr2",(double)veCurr2);
        fields.put("warmup",(double)warmup);
        fields.put("warmupEnrich",(double)warmupEnrich);
        fields.put("wbo2_en1",(double)wbo2_en1);
        fields.put("wbo2_en2",(double)wbo2_en2);
        return new DataPacket(fields,ochBuffer);
    };

}

