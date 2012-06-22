package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.MSUtils;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
/*
Fingerprint : a39287890a9edeefb627fb8d558d4fc9
*/
public class Megasquirt_II2886 extends Megasquirt
{
    public Megasquirt_II2886(Context c)
    {
        super(c);
        refreshFlags();
    }
    @Override
    public void refreshFlags()
    {
        n2o = isSet("n2o");
        NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
        CELSIUS = isSet("CELSIUS");
        LAMBDA = isSet("LAMBDA");
    }
    byte[] queryCommand=new byte[]{'Q'};
    String signature="MSII Rev 2.88600   \0";
    byte [] ochGetCommand = new byte[]{97,0,6};
    int ochBlockSize = 112;
    private Set<String> sigs = new HashSet<String>(Arrays.asList(new String[] { signature }));
//Flags
    public boolean n2o;
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
    public int amcUpdates;
    public int baroCorrection;
    public double barometer;
    public double batteryVoltage;
    public double calcMAP;
    public int cksum;
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
    public double egoV1;
    public double egoV2;
    public double egoVoltage;
    public int engine;
    public int firing1;
    public int firing2;
    public double fuelComposition;
    public int fuelCorrection;
    public int gammaEnrich;
    public int iacstep;
    public int idleDC;
    public int inj1;
    public int inj2;
    public double knock;
    public double knockRetard;
    public double kpaix;
    public double lambda1;
    public double lambda2;
    public double maf;
    public double map;
    public int mapDOT;
    public int mapaen;
    public double mat;
    public int nSquirts1;
    public int nSquirts2;
    public int ospare;
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
    public int spare1;
    public int spare10;
    public int spare2;
    public int spare4;
    public int spare5;
    public int spare6;
    public int spare7;
    public int spare8;
    public int spare9;
    public int squirt;
    public int startw;
    public int tachCount;
    public double throttle;
    public double time;
    public double tps;
    public double tpsADC;
    public int tpsDOT;
    public int tpsaen;
    public int tpsden;
    public int tpsfuelcut;
    public int trig_fix;
    public double veCurr;
    public int veCurr1;
    public int veCurr2;
    public int warmup;
    public int warmupEnrich;
    public int wbo2_en1;
    public int wbo2_en2;
    public int xTauFuelCorr1;
    public int xTauFuelCorr2;

//Constants
    public int taeColdM;
    public double triggerOffset;
    public double AFRStoich;
    public int egoRPM;
    public int tempUnits;
    public double dwellAcc;
    public double baro0;
    public int spkout_hi_lo;
    public double IdleHyst;
    public int tpsMin;
    public int MatRtdRPMHi;
    public double primePulseHot;
    public int Delay_Teeth;
    public int alpha_hirpm;
    public double taeColdA;
    public int knkLF;
    public int AFRMult;
    public double RevLimMaxRtd;
    public int egoType;
    public double AFRStoichWB;
    public double OddAng;
    public int egoDelta;
    public int alternate;
    public int VEIXOptn;
    public int egoKI;
    public int cltmult;
    public double crankCold;
    public int divider;
    public int egoKD;
    public int knkDirection;
    public double knk_tadv;
    public int bcormult;
    public int crankingRPM;
    public int matmult;
    public double egoTemp;
    public double MAPOXLimit;
    public int AMCOption;
    public double egoTarget;
    public int asePctHot;
    public int tpsMax;
    public int knk_ndet;
    public int AMCve_rpm;
    public int board_type;
    public int adcLF;
    public int bcor0;
    public double primePulseCold;
    public double map0;
    public double spkN2O;
    public int egomult;
    public int AMCdve;
    public int egoKP;
    public int InjStart;
    public int ASEHot;
    public int asePctCold;
    public int egoKdelay2;
    public int egoKdelay1;
    public double max_spk_dur;
    public int DualSpkOptn;
    public double knk_dtble_adv;
    public int PredOpt;
    public int No_Miss_Teeth;
    public int MapThreshXTD;
    public int MAFRPM2;
    public int MAFRPM1;
    public int beta;
    public int egoLF;
    public int tdePct;
    public int algorithm;
    public int flexFuel;
    public double aeEndPW;
    public int IACcoldpos;
    public int gamma;
    public double pwImpulse;
    public double mapmax;
    public int MapThreshXTA;
    public int ICCrankTrigger;
    public double taeTime;
    public int nImpulse;
    public int knk_hirpm;
    public int MAFDir;
    public double knk_maxmap;
    public double AMCve_map;
    public int mapdotSample;
    public int CWOption;
    public double batt0;
    public double knk_trtd;
    public int AMCramve_dt;
    public int Dtpred_Gain;
    public int knkOption;
    public int egoCount;
    public int ICIgnOption;
    public double TPSOXLimit;
    public double fastIdleT;
    public int tpsLF;
    public double baromax;
    public double battFac;
    public int trendmapLimit;
    public int IgnAlpha;
    public int IACStart;
    public double ego0;
    public int nInjectors;
    public int no_skip_pulses;
    public int mapLF;
    public int injType;
    public double ICISR_tmask;
    public double max_coil_dur;
    public int xTauOption;
    public int IACcrankxt;
    public int ae_lorpm;
    public int IdleCtl;
    public int pulseTolerance;
    public int MatRtdRPMLo;
    public int injTestMode;
    public int n2oEnrich;
    public int injTestSqrts;
    public int AMCupdate_thresh;
    public double injOpen;
    public int an_direction;
    public int aseCountHot;
    public int AMCT_thresh;
    public double IACcoldtmp;
    public int IACminstep;
    public int egoAlgorithm;
    public int ICISR_pmask;
    public int No_Skip_Teeth;
    public int dualTable;
    public int alpha_lorpm;
    public int vr_delay;
    public int acc_synchk;
    public int fuelFreq0;
    public double floodClear;
    public int MAFOption;
    public int fuelFreq1;
    public int mapThresh;
    public double clt0;
    public int twoStroke;
    public int aseCountCold;
    public double fuelSpkDel0;
    public int fuelCorr0;
    public int fuelCorr1;
    public double knockmax;
    public double fuelSpkDel1;
    public int IACcoldxt;
    public int tpsdotSample;
    public double mat0;
    public int ECUType;
    public int impulseSec;
    public int baud;
    public int tpsThresh;
    public double injTestPW;
    public int ae_hirpm;
    public double reqFuel;
    public double IACpwm_step;
    public int injPwmPd;
    public int tpsProportion;
    public int nCylinders;
    public int CID;
    public double knk_maxrtd;
    public int ICIgnCapture;
    public double battmax;
    public int MapThreshXTD2;
    public int alpha;
    public int mycan_id;
    public double IACaccstep;
    public double knock0;
    public int AMCNBurns;
    public int MAFpin;
    public int RevLimOption;
    public int AMCstep;
    public int baroCorr;
    public int IACcrankpos;
    public int crankTolerance;
    public int injPwmP;
    public double aeTaperTime;
    public double crankHot;
    public int triggerTeeth;
    public int RevLimRpm2;
    public int fet_delay;
    public double AFRStoichNB;
    public int asTolerance;
    public int rpmLF;
    public int RevLimRpm1;
    public int knk_lorpm;
    public int AfrAlpha;
    public double IACtstep;
    public int egoLimit;
    public double knk_step2;
    public double injTestOffTime;
    public double knk_step1;
    public int prime_delay;
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
        "xTauGauge1",
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
        afrtgt1 = (double)((MSUtils.getByte(ochBuffer,12) + 0.0) * 0.100);
        afrtgt2 = (double)((MSUtils.getByte(ochBuffer,13) + 0.0) * 0.100);
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
        fuelComposition = ((fuelCorrection - 100)*1.587);
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
        tpsDOT = (int)((MSUtils.getSignedWord(ochBuffer,58) + 0.0) * 1.000);
        mapDOT = (int)((MSUtils.getSignedWord(ochBuffer,60) + 0.0) * 1.000);
        dwell = (double)((MSUtils.getSignedWord(ochBuffer,62) + 0.0) * 0.100);
        maf = (double)((MSUtils.getSignedWord(ochBuffer,64) + 0.0) * 0.010);
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
        knockRetard = (double)((MSUtils.getByte(ochBuffer,71) + 0.0) * 0.100);
        xTauFuelCorr1 = (int)((MSUtils.getSignedWord(ochBuffer,72) + 0.0) * 1.000);
        egoV1 = (double)((MSUtils.getSignedWord(ochBuffer,74) + 0.0) * 0.010);
        egoV2 = (double)((MSUtils.getSignedWord(ochBuffer,76) + 0.0) * 0.010);
        amcUpdates = (int)((MSUtils.getSignedWord(ochBuffer,78) + 0.0) * 1.000);
        kpaix = (double)((MSUtils.getSignedWord(ochBuffer,80) + 0.0) * 0.100);
        xTauFuelCorr2 = (int)((MSUtils.getSignedWord(ochBuffer,82) + 0.0) * 1.000);
        spare1 = (int)((MSUtils.getSignedWord(ochBuffer,84) + 0.0) * 1.000);
        spare2 = (int)((MSUtils.getSignedWord(ochBuffer,86) + 0.0) * 1.000);
        trig_fix = (int)((MSUtils.getSignedWord(ochBuffer,88) + 0.0) * 1.000);
        spare4 = (int)((MSUtils.getSignedWord(ochBuffer,90) + 0.0) * 1.000);
        spare5 = (int)((MSUtils.getSignedWord(ochBuffer,92) + 0.0) * 1.000);
        spare6 = (int)((MSUtils.getSignedWord(ochBuffer,94) + 0.0) * 1.000);
        spare7 = (int)((MSUtils.getSignedWord(ochBuffer,96) + 0.0) * 1.000);
        spare8 = (int)((MSUtils.getSignedWord(ochBuffer,98) + 0.0) * 1.000);
        spare9 = (int)((MSUtils.getSignedWord(ochBuffer,100) + 0.0) * 1.000);
        spare10 = (int)((MSUtils.getSignedWord(ochBuffer,102) + 0.0) * 1.000);
        tachCount = (int)((MSUtils.getWord(ochBuffer,104) + 0.0) * 1.000);
        ospare = (int)((MSUtils.getByte(ochBuffer,106) + 0.0) * 1.000);
        cksum = (int)((MSUtils.getByte(ochBuffer,107) + 0.0) * 1.000);
        deltaT = (int)((MSUtils.getLong(ochBuffer,108) + 0.0) * 1.000);
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
        egoVoltage = (egoV1);
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
        b.append("MAP/baro").append("\t");
        b.append("MAF").append("\t");
        b.append("TP").append("\t");
        b.append("vBatt").append("\t");
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
        b.append("IAT").append("\t");
        b.append("CLT").append("\t");
        b.append("Engine").append("\t");
        b.append("Gego").append("\t");
        b.append("Gair").append("\t");
        b.append("Gwarm").append("\t");
        b.append("Gbaro").append("\t");
        b.append("Gammae").append("\t");
        b.append("AccelEnrich").append("\t");
        b.append("Gve").append("\t");
        b.append("PW").append("\t");
        b.append("DutyCycle1").append("\t");
        b.append("Gve2").append("\t");
        b.append("PW2").append("\t");
        b.append("DutyCycle2").append("\t");
        b.append("SparkAdv").append("\t");
        b.append("knockRet").append("\t");
        b.append("ColdAdv").append("\t");
        b.append("Dwell").append("\t");
        b.append("tpsDOT").append("\t");
        b.append("mapDOT").append("\t");
        b.append("IAC").append("\t");
        b.append("deltaT").append("\t");
        b.append("Trigger±").append("\t");
        b.append("tachCount").append("\t");
        b.append("E85fuelCorr").append("\t");
        b.append("Ethanol%").append("\t");
        b.append("AFRtrgt1").append("\t");
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
        b.append(round(kpaix)).append("\t");
        b.append(round(maf)).append("\t");
        b.append(throttle).append("\t");
        b.append(round(batteryVoltage)).append("\t");
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
        b.append(round(knockRetard)).append("\t");
        b.append(round(coldAdvDeg)).append("\t");
        b.append(round(dwell)).append("\t");
        b.append(tpsDOT).append("\t");
        b.append(mapDOT).append("\t");
        b.append(iacstep).append("\t");
        b.append(deltaT).append("\t");
        b.append(trig_fix).append("\t");
        b.append(tachCount).append("\t");
        b.append(fuelCorrection).append("\t");
        b.append(fuelComposition).append("\t");
        b.append(round(afrtgt1)).append("\t");
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("advdegGauge","advance",advance,"Ignition Advance","degrees",5,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("IACgauge","iacstep",iacstep,"IAC position","steps",0,255,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dwellGauge","dwell",dwell,"Dwell","mSec",0,10,0.5,1.0,6.0,8.0,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("PWMIdlegauge","idleDC",idleDC,"Idle PWM%","%",0,100,-1,-1,999,90,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelEnrichGauge","accDecEnrich",accDecEnrich,"Accel Enrich","%",50,150,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1Gauge","afr1",afr1,"Air:Fuel Ratio","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2Gauge","afr2",afr2,"Air:Fuel Ratio 2","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clockGauge","seconds",seconds,"Clock","Seconds",0,255,10,10,245,245,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge","deadValue",deadValue,"---","",0,1,-1,-1,2,2,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle1Gauge","dutyCycle1",dutyCycle1,"Duty Cycle 1","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle2Gauge","dutyCycle2",dutyCycle2,"Duty Cycle 2","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge","egoCorrection",egoCorrection,"EGO Correction","%",50,150,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge1","egoCorrection1",egoCorrection1,"EGO Correction 1","%",50,150,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge2","egoCorrection2",egoCorrection2,"EGO Correction 2","%",50,150,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge","egoVoltage",egoVoltage,"Exhaust Gas Oxygen","volts",0,1.0,0.2,0.3,0.7,0.8,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("kpabaroGauge","kpaix",kpaix,"Engine MAP/baro","kPa",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda1Gauge","lambda1",lambda1,"Lambda","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda2Gauge","lambda2",lambda2,"Lambda","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaEnrichGauge","gammaEnrich",gammaEnrich,"Gamma Enrichment","%",50,150,-1,-1,151,151,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge","map",map,"Engine MAP","kPa",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mafGauge","maf",maf,"MAF Air flow","g/sec",0,650,0,200,480,550,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("barometerGauge","barometer",barometer,"Barometer","kPa",60,120,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("calcMapGauge","calcMAP",calcMAP,"Estimated MAP","kPa",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth1Gauge","pulseWidth1",pulseWidth1,"Pulse Width 1","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth2Gauge","pulseWidth2",pulseWidth2,"Pulse Width 2","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tachometer","rpm",rpm,"Engine Speed","RPM",0,8000,300,500,5000,6000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("throttleGauge","throttle",throttle,"Throttle Position","%",0,100,-1,1,90,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deltaTGauge","deltaT",deltaT,"Timing Delta","uS",0,25000,0,0,25000,25000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge","veCurr1",veCurr1,"VE Current","%",0,120,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("voltMeter","batteryVoltage",batteryVoltage,"Battery Voltage","volts",7,21,9,10,16,17,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("warmupEnrichGauge","warmupEnrich",warmupEnrich,"Warmup Enrichment","%",100,150,-1,-1,101,105,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("xTauGauge1","xTauFuelCorr1",xTauFuelCorr1,"X-Tau Correction1","%",0,200,40,70,130,160,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("xTauGauge2","xTauFuelCorr2",xTauFuelCorr2,"X-Tau Correction2","%",0,200,40,70,130,160,0,0,45));
        if (CELSIUS)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","C",-40,120,-15,0,95,105,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Intake Air Temp","C",-40,110,-15,0,95,100,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","°F",-40,250,0,30,200,220,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Intake Air Temp","°F",-40,215,0,30,200,210,0,0,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("e85Gauge","fuelCorrection",fuelCorrection,"E85 Fuel Correction","%",100,200,99,99,164,170,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ethanolGas","fuelComposition",fuelComposition,"Ethanol Percentage","%",0,100,-1,-1,85,101,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("triggers±","trig_fix",trig_fix,"Triggers add/sub","",-200,200,-100,-10,10,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tachCount","tachCount",tachCount,"Number Tach Pulses","",0,1000,-1,-1,1000,1000,0,0,45));
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
        return 50;
    }
    @Override
    public int getInterWriteDelay()
    {
        return 0;
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
        pageBuffer = loadPage(1,0,994,null,new byte[]{114,0,4,0,0,3,-30});
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
        RevLimMaxRtd = (double)((MSUtils.getByte(pageBuffer,18) + 0.0) * 0.1);
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
        IACminstep = (int)((MSUtils.getByte(pageBuffer,550) + 0.0) * 1.0);
        IACpwm_step = (double)((MSUtils.getByte(pageBuffer,551) + 0.0) * 80.0);
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
        primePulseCold = (double)((MSUtils.getSignedWord(pageBuffer,566) + 0.0) * 0.1);
        primePulseHot = (double)((MSUtils.getSignedWord(pageBuffer,568) + 0.0) * 0.1);
        crankCold = (double)((MSUtils.getSignedWord(pageBuffer,570) + 0.0) * 0.1);
        crankHot = (double)((MSUtils.getSignedWord(pageBuffer,572) + 0.0) * 0.1);
        asePctCold = (int)((MSUtils.getSignedWord(pageBuffer,574) + 0.0) * 1.0);
        asePctHot = (int)((MSUtils.getSignedWord(pageBuffer,576) + 0.0) * 1.0);
        aseCountCold = (int)((MSUtils.getSignedWord(pageBuffer,578) + 0.0) * 1.0);
        aseCountHot = (int)((MSUtils.getSignedWord(pageBuffer,580) + 0.0) * 1.0);
        taeColdA = (double)((MSUtils.getByte(pageBuffer,582) + 0.0) * 0.1);
        taeColdM = (int)((MSUtils.getByte(pageBuffer,583) + 0.0) * 1.0);
        tpsThresh = (int)((MSUtils.getByte(pageBuffer,584) + 0.0) * 1.0);
        mapThresh = (int)((MSUtils.getByte(pageBuffer,585) + 0.0) * 1.0);
        taeTime = (double)((MSUtils.getByte(pageBuffer,586) + 0.0) * 0.1);
        tdePct = (int)((MSUtils.getByte(pageBuffer,587) + 0.0) * 1.0);
        floodClear = (double)((MSUtils.getSignedWord(pageBuffer,588) + 0.0) * 0.1);
        TPSOXLimit = (double)((MSUtils.getSignedWord(pageBuffer,590) + 0.0) * 0.1);
        tpsProportion = (int)((MSUtils.getByte(pageBuffer,592) + 0.0) * 1.0);
        baroCorr = MSUtils.getBits(pageBuffer,593,0,1,0);
        egoType = MSUtils.getBits(pageBuffer,594,0,2,0);
        egoCount = (int)((MSUtils.getByte(pageBuffer,595) + 0.0) * 1.0);
        egoDelta = (int)((MSUtils.getByte(pageBuffer,596) + 0.0) * 1.0);
        egoLimit = (int)((MSUtils.getByte(pageBuffer,597) + 0.0) * 1.0);
        if (NARROW_BAND_EGO)
        {
        egoTarget = (double)((MSUtils.getByte(pageBuffer,598) + 0.0) * 0.01);
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
        MAFOption = MSUtils.getBits(pageBuffer,600,0,1,0);
        MAFpin = MSUtils.getBits(pageBuffer,600,4,5,0);
        DualSpkOptn = MSUtils.getBits(pageBuffer,601,0,3,0);
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
        nInjectors = MSUtils.getBits(pageBuffer,619,0,4,0);
        OddAng = (double)((MSUtils.getSignedWord(pageBuffer,620) + 0.0) * 0.1);
        rpmLF = (int)((MSUtils.getByte(pageBuffer,622) + 0.0) * 1.0);
        mapLF = (int)((MSUtils.getByte(pageBuffer,623) + 0.0) * 1.0);
        tpsLF = (int)((MSUtils.getByte(pageBuffer,624) + 0.0) * 1.0);
        egoLF = (int)((MSUtils.getByte(pageBuffer,625) + 0.0) * 1.0);
        adcLF = (int)((MSUtils.getByte(pageBuffer,626) + 0.0) * 1.0);
        knkLF = (int)((MSUtils.getByte(pageBuffer,627) + 0.0) * 1.0);
        AMCOption = MSUtils.getBits(pageBuffer,628,0,1,0);
        dualTable = MSUtils.getBits(pageBuffer,629,0,0,0);
        algorithm = MSUtils.getBits(pageBuffer,630,0,1,0);
        an_direction = MSUtils.getBits(pageBuffer,630,4,5,0);
        IgnAlpha = (int)((MSUtils.getByte(pageBuffer,631) + 0.0) * 1.0);
        AfrAlpha = (int)((MSUtils.getByte(pageBuffer,632) + 0.0) * 1.0);
        AFRStoich = (double)((MSUtils.getByte(pageBuffer,633) + 0.0) * 0.1);
        AFRStoichWB = (double)((MSUtils.getByte(pageBuffer,633) + 0.0) * 0.1);
        AFRStoichNB = (double)((MSUtils.getByte(pageBuffer,633) + 0.0) * 0.01);
        alpha_lorpm = (int)((MSUtils.getSignedWord(pageBuffer,634) + 0.0) * 1.0);
        alpha_hirpm = (int)((MSUtils.getSignedWord(pageBuffer,636) + 0.0) * 1.0);
        baud = (int)((MSUtils.getLong(pageBuffer,734) + 0.0) * 1.0);
        MAPOXLimit = (double)((MSUtils.getSignedWord(pageBuffer,738) + 0.0) * 0.1);
        board_type = (int)((MSUtils.getByte(pageBuffer,740) + 0.0) * 1.0);
        mycan_id = (int)((MSUtils.getByte(pageBuffer,741) + 0.0) * 1.0);
        fet_delay = (int)((MSUtils.getByte(pageBuffer,742) + 0.0) * 1.0);
        vr_delay = (int)((MSUtils.getByte(pageBuffer,743) + 0.0) * 1.0);
        ECUType = (int)((MSUtils.getByte(pageBuffer,744) + 0.0) * 1.0);
        MapThreshXTD2 = (int)((MSUtils.getByte(pageBuffer,745) + 0.0) * 1.0);
        if (n2o)
        {
        n2oEnrich = (int)((MSUtils.getByte(pageBuffer,746) + 0.0) * 1.0);
        }
        else
        {
        pwImpulse = (double)((MSUtils.getByte(pageBuffer,746) + 0.0) * 0.1);
        }
        impulseSec = (int)((MSUtils.getByte(pageBuffer,747) + 0.0) * 1.0);
        nImpulse = (int)((MSUtils.getByte(pageBuffer,748) + 0.0) * 1.0);
        mapdotSample = (int)((MSUtils.getByte(pageBuffer,749) + 0.0) * 1.0);
        tpsdotSample = (int)((MSUtils.getByte(pageBuffer,750) + 0.0) * 1.0);
        ASEHot = MSUtils.getBits(pageBuffer,751,0,0,0);
        InjStart = (int)((MSUtils.getByte(pageBuffer,752) + 0.0) * 1.0);
        MapThreshXTD = (int)((MSUtils.getByte(pageBuffer,753) + 0.0) * 1.0);
        MapThreshXTA = (int)((MSUtils.getByte(pageBuffer,754) + 0.0) * 1.0);
        trendmapLimit = (int)((MSUtils.getByte(pageBuffer,755) + 0.0) * 1.0);
        aeTaperTime = (double)((MSUtils.getByte(pageBuffer,896) + 0.0) * 0.1);
        AFRMult = MSUtils.getBits(pageBuffer,897,0,1,0);
        aeEndPW = (double)((MSUtils.getSignedWord(pageBuffer,898) + 0.0) * 0.1);
        egoAlgorithm = MSUtils.getBits(pageBuffer,900,0,1,0);
        egoKP = (int)((MSUtils.getByte(pageBuffer,901) + 0.0) * 1.0);
        egoKI = (int)((MSUtils.getByte(pageBuffer,902) + 0.0) * 1.0);
        egoKD = (int)((MSUtils.getByte(pageBuffer,903) + 0.0) * 1.0);
        egoKdelay1 = (int)((MSUtils.getWord(pageBuffer,904) + 0.0) * 1.0);
        egoKdelay2 = (int)((MSUtils.getWord(pageBuffer,906) + 0.0) * 1.0);
        flexFuel = MSUtils.getBits(pageBuffer,908,0,0,0);
        prime_delay = (int)((MSUtils.getByte(pageBuffer,909) + 0.0) * 1.0);
        fuelFreq0 = (int)((MSUtils.getByte(pageBuffer,910) + 0.0) * 1.0);
        fuelFreq1 = (int)((MSUtils.getByte(pageBuffer,911) + 0.0) * 1.0);
        fuelCorr0 = (int)((MSUtils.getByte(pageBuffer,912) + 0.0) * 1.0);
        fuelCorr1 = (int)((MSUtils.getByte(pageBuffer,913) + 0.0) * 1.0);
        AMCstep = (int)((MSUtils.getByte(pageBuffer,914) + 0.0) * 1.0);
        AMCdve = (int)((MSUtils.getByte(pageBuffer,915) + 0.0) * 1.0);
        AMCve_rpm = (int)((MSUtils.getWord(pageBuffer,916) + 0.0) * 1.0);
        AMCve_map = (double)((MSUtils.getWord(pageBuffer,918) + 0.0) * 0.1);
        AMCramve_dt = (int)((MSUtils.getWord(pageBuffer,920) + 0.0) * 1.0);
        AMCT_thresh = (int)((MSUtils.getWord(pageBuffer,922) + 0.0) * 1.0);
        AMCupdate_thresh = (int)((MSUtils.getWord(pageBuffer,924) + 0.0) * 1.0);
        CWOption = MSUtils.getBits(pageBuffer,926,0,0,0);
        knkOption = MSUtils.getBits(pageBuffer,927,0,1,0);
        knkDirection = MSUtils.getBits(pageBuffer,927,4,4,0);
        knk_maxrtd = (double)((MSUtils.getByte(pageBuffer,928) + 0.0) * 0.1);
        knk_step1 = (double)((MSUtils.getByte(pageBuffer,929) + 0.0) * 0.1);
        knk_step2 = (double)((MSUtils.getByte(pageBuffer,930) + 0.0) * 0.1);
        knk_trtd = (double)((MSUtils.getByte(pageBuffer,931) + 0.0) * 0.1);
        knk_tadv = (double)((MSUtils.getByte(pageBuffer,932) + 0.0) * 0.1);
        knk_dtble_adv = (double)((MSUtils.getByte(pageBuffer,933) + 0.0) * 0.1);
        knk_ndet = (int)((MSUtils.getByte(pageBuffer,934) + 0.0) * 1.0);
        knk_maxmap = (double)((MSUtils.getWord(pageBuffer,936) + 0.0) * 0.1);
        knk_lorpm = (int)((MSUtils.getWord(pageBuffer,938) + 0.0) * 1.0);
        knk_hirpm = (int)((MSUtils.getWord(pageBuffer,940) + 0.0) * 1.0);
        triggerTeeth = (int)((MSUtils.getWord(pageBuffer,966) + 0.0) * 1.0);
        No_Miss_Teeth = (int)((MSUtils.getByte(pageBuffer,968) + 0.0) * 1.0);
        No_Skip_Teeth = (int)((MSUtils.getByte(pageBuffer,969) + 0.0) * 1.0);
        Delay_Teeth = (int)((MSUtils.getByte(pageBuffer,970) + 0.0) * 1.0);
        ICISR_tmask = (double)((MSUtils.getByte(pageBuffer,971) + 0.0) * 0.1);
        ICISR_pmask = (int)((MSUtils.getByte(pageBuffer,972) + 0.0) * 1.0);
        injTestMode = MSUtils.getBits(pageBuffer,973,0,1,0);
        ae_lorpm = (int)((MSUtils.getWord(pageBuffer,974) + 0.0) * 1.0);
        ae_hirpm = (int)((MSUtils.getWord(pageBuffer,976) + 0.0) * 1.0);
        fuelSpkDel0 = (double)((MSUtils.getSignedWord(pageBuffer,978) + 0.0) * 0.1);
        fuelSpkDel1 = (double)((MSUtils.getSignedWord(pageBuffer,980) + 0.0) * 0.1);
        injTestSqrts = (int)((MSUtils.getWord(pageBuffer,982) + 0.0) * 1.0);
        injTestPW = (double)((MSUtils.getWord(pageBuffer,984) + 0.0) * 0.0010);
        injTestOffTime = (double)((MSUtils.getWord(pageBuffer,986) + 0.0) * 0.1);
        CID = (int)((MSUtils.getWord(pageBuffer,988) + 0.0) * 1.0);
        alpha = (int)((MSUtils.getByte(pageBuffer,990) + 0.0) * 1.0);
        beta = (int)((MSUtils.getByte(pageBuffer,991) + 0.0) * 1.0);
        gamma = (int)((MSUtils.getByte(pageBuffer,992) + 0.0) * 1.0);
        acc_synchk = MSUtils.getBits(pageBuffer,993,0,1,0);
        pageBuffer = loadPage(2,0,946,null,new byte[]{114,0,5,0,0,3,-78});
        AMCNBurns = (int)((MSUtils.getWord(pageBuffer,576) + 0.0) * 1.0);
        if (CELSIUS)
        {
        }
        else
        {
        }
        xTauOption = MSUtils.getBits(pageBuffer,676,0,1,0);
        spkN2O = (double)((MSUtils.getByte(pageBuffer,677) + 0.0) * 0.1);
        if (CELSIUS)
        {
        }
        else
        {
        }
        MAFRPM1 = (int)((MSUtils.getWord(pageBuffer,936) + 0.0) * 1.0);
        MAFRPM2 = (int)((MSUtils.getWord(pageBuffer,938) + 0.0) * 1.0);
        MAFDir = MSUtils.getBits(pageBuffer,940,0,0,0);
        VEIXOptn = MSUtils.getBits(pageBuffer,941,0,0,0);
        MatRtdRPMHi = (int)((MSUtils.getWord(pageBuffer,942) + 0.0) * 1.0);
        MatRtdRPMLo = (int)((MSUtils.getWord(pageBuffer,944) + 0.0) * 1.0);
    }

}

