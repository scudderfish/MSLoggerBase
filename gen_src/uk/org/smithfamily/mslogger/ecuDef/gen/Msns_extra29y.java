package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.MSUtils;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
/*
Fingerprint : a7d4cde2efa0e21b1e8c1815855b304f
*/
public class Msns_extra29y extends Megasquirt
{
    public Msns_extra29y(Context c)
    {
        super(c);
        refreshFlags();
    }
    @Override
    public void refreshFlags()
    {
        NGK_AFX = isSet("NGK_AFX");
        MPXH6300A = isSet("MPXH6300A");
        ZEITRONIX_NON_LINEAR = isSet("ZEITRONIX_NON_LINEAR");
        WB_1_0_LINEAR = isSet("WB_1_0_LINEAR");
        MEMPAGES = isSet("MEMPAGES");
        FAST_WIDEBAND_O2 = isSet("FAST_WIDEBAND_O2");
        SPEED_DENSITY = isSet("SPEED_DENSITY");
        AEM_LINEAR = isSet("AEM_LINEAR");
        INNOVATE_LC1_DEFAULT = isSet("INNOVATE_LC1_DEFAULT");
        DIYWB_NON_LINEAR = isSet("DIYWB_NON_LINEAR");
        INNOVATE_0_5_LINEAR = isSet("INNOVATE_0_5_LINEAR");
        CELSIUS = isSet("CELSIUS");
        DYNOJET_LINEAR = isSet("DYNOJET_LINEAR");
        TECHEDGE_LINEAR = isSet("TECHEDGE_LINEAR");
        LOGPAGES = isSet("LOGPAGES");
        NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
        KPa = isSet("KPa");
        ALPHA_N = isSet("ALPHA_N");
        MSLVV_COMPATIBLE = isSet("MSLVV_COMPATIBLE");
        INNOVATE_1_2_LINEAR = isSet("INNOVATE_1_2_LINEAR");
        MPX4250 = isSet("MPX4250");
        AIR_FLOW_METER = isSet("AIR_FLOW_METER");
        AEM_NON_LINEAR = isSet("AEM_NON_LINEAR");
        WB_UNKNOWN = isSet("WB_UNKNOWN");
        MPXH6400A = isSet("MPXH6400A");
    }
    byte[] queryCommand=new byte[]{'S'};
    String signature="MS1/Extra format 029y3 *********";
    byte [] ochGetCommand = new byte[]{'R'};
    int ochBlockSize = 39;
    private Set<String> sigs = new HashSet<String>(Arrays.asList(new String[] { signature }));
//Flags
    boolean NGK_AFX;
    boolean MPXH6300A;
    boolean ZEITRONIX_NON_LINEAR;
    boolean WB_1_0_LINEAR;
    boolean MEMPAGES;
    boolean FAST_WIDEBAND_O2;
    boolean SPEED_DENSITY;
    boolean AEM_LINEAR;
    boolean INNOVATE_LC1_DEFAULT;
    boolean DIYWB_NON_LINEAR;
    boolean INNOVATE_0_5_LINEAR;
    boolean CELSIUS;
    boolean DYNOJET_LINEAR;
    boolean TECHEDGE_LINEAR;
    boolean LOGPAGES;
    boolean NARROW_BAND_EGO;
    boolean KPa;
    boolean ALPHA_N;
    boolean MSLVV_COMPATIBLE;
    boolean INNOVATE_1_2_LINEAR;
    boolean MPX4250;
    boolean AIR_FLOW_METER;
    boolean AEM_NON_LINEAR;
    boolean WB_UNKNOWN;
    boolean MPXH6400A;
//Runtime vars
    int egoADC;
    int fuelADC;
    int afrtarget;
    int cltADC;
    int matADC;
    int batADC;
    int stackL;
    double pulseWidth2;
    double pulseWidth1;
    int gammaEnrich;
    int KnockAngle;
    int egoCorrection2;
    int egtADC;
    int veCurr1;
    int veCurr2;
    int tpsLast;
    int CltIatAngle;
    double bcDC;
    int iTime;
    double egoCorrection;
    int rpm100;
    int portb;
    int porta;
    int portd;
    int portc;
    int tpsADC;
    int iTimeX;
    int engine;
    int baroADC;
    int secl;
    int accelEnrich;
    int airCorrection;
    int squirt;
    int mapADC;
    int idleDC;
    int warmupEnrich;
    int baroCorrection;
    int advance;

//eval vars
    double tpsDOT;
    double accDecEnrich;
    double mph;
    double mpg;
    double XForce;
    double afr;
    int YOffset;
    double throttle;
    double dutyCycle2;
    double test;
    double dutyCycle1;
    int XOffset;
    double Open_Time2;
    int altDiv1;
    double RpmHitmp;
    double lambda;
    double time;
    double afr2;
    int altDiv2;
    double Open_Time1;
    double Cd;
    int rpm;
    int Timeroll;
    double mapDOT;
    double barometer;
    double dutyCy2Real;
    double TargetLambda;
    double YForce;
    double advSpark;
    double coolant;
    double dutyCy1Real;
    int nSquirts2;
    double TargetAFR;
    int nSquirts1;
    double RpmHiRes;
    double CCpHr;
    int nosActive1;
    int test2;
    double gph;
    double USgph;
    double USmpg;
    int Mass;
    double squirts;
    double fuelvolt;
    double mphTemp;
    double boost;
    double mapDOTTY;
    double OpenWidth;
    double ego2Voltage;
    int deadValue;
    double DiffRa;
    double pulseWidth;
    int fuelCC;
    double batteryVoltage;
    double GrTms;
    double boostVac;
    double cycleTime1;
    double cycleTime2;
    double tpsDOTTY;
    double veCurr;
    int CltIatAng;
    int KnockDeg;
    int waterIlog;
    int floodclear;
    int KnockAng;
    double map;
    int Crr;
    double vacuum;
    double fuelpress;
    int CltIatDeg;
    double lambda2;
    double mat;
    int Speed;
    int iTimefull;
    double Radius;
    double egoVoltage;
    double MAFVolts;
    double egttemp;
    double squirtmul;
    int InjectorRating1;
    int InjectorRating2;
    double afrTargetV;

//Constants
    int whlsimcnt;
    int fastIdleTemp;
    int WaterIIat;
    int TPSRPMTarg;
    int out2src;
    double injPwmT2;
    double injPwmT1;
    double RPMmS4;
    double RPMmS3;
    double RPMmS2;
    double RPMmS1;
    int DecelKPa;
    double outaoffv;
    int edismulti;
    int AlwaysPrime;
    int KnockKpa;
    int baroCorr1;
    int outaoffs;
    double Nos2delay;
    int baroCorr2;
    int egoCount1;
    double NosPWHi;
    int LachTps;
    double RPMRedLo;
    int OvrBCutType;
    int fastIdleT2;
    int Out2UpLim;
    int egoDelta1;
    int egoDelta2;
    int fastIdleT1;
    int rpmk2;
    double ictlrpm2;
    int rpmk1;
    double ictlrpm1;
    int cltType2;
    int cltType1;
    double RPM2S;
    int idlefreq;
    int trig2ret;
    double irestorerpm;
    double VEFixValue;
    double LC_f_limangle;
    int outcoffs;
    int NosHiKpa;
    double outcoffv;
    int numteeth;
    double TractVSSMax;
    int out4FAN;
    double RPM1S;
    int SparkCutBNum;
    int PrimeLate;
    double VE3Delay;
    int shiftUse;
    int KPaOn;
    int Pambient;
    double LC_f_slim;
    int UseVE3;
    int TPSTar;
    double outdoffv;
    double fastIdleRPM;
    int latency;
    int outdoffs;
    double outeoffv;
    int slowIdleTemp;
    double OvrRunRpm;
    int OvrRunTps;
    int efanofftemp;
    double RPM4S;
    int outeoffs;
    int out4lim;
    int StagedMAP2nd;
    int tdePct4;
    double dwellrun;
    int inj2cr;
    int nInjectors2;
    int nInjectors1;
    int divider1;
    int divider2;
    int IdleAdvTPS;
    int out1Hysis;
    double NosPWLo;
    double RPM3S;
    int trig3ret;
    double SoftRevLim;
    int AfrTar;
    double TractRet2;
    double TractRet1;
    double TractRet4;
    int ScaleFac;
    double TractRet3;
    int TractNOS;
    int engineType2;
    int fidleUse;
    int OvrRunKpa;
    int engineType1;
    double CrankAng;
    int trig2;
    int trig3;
    double SoftLimMax;
    int trig1;
    int trig6;
    int trig4;
    int trig5;
    int OvrRunTimr;
    double MaxIatAdv;
    int whlsim;
    int tpsflood;
    double taeTime4;
    int hei7;
    int twoStroke2;
    double fixedsplit;
    int twoStroke1;
    int edis;
    int IATBoost;
    int egoLimit2;
    int egoLimit1;
    int matType1;
    int matType2;
    int tachconf;
    double TractSlip;
    double LachRpm;
    int led19Use;
    double mapThresh4;
    int ignmode;
    int TrigCrank;
    int trig2fall;
    double IdleAdvRPM;
    int spare3_182;
    int spare3_183;
    int VEFixASE;
    int spare3_184;
    int wd_2trig;
    double RPMThresh;
    int ASETable8;
    int ASETable7;
    int ASEFixDe;
    int IgHold;
    int ASETable9;
    double NosAngle;
    int ASETable4;
    int ASETable3;
    int ASETable6;
    double out3Timer;
    int ASETable5;
    int ASETable2;
    int CltAdv;
    int ASETable1;
    int IgAdvTemp;
    int HCutLCSpark;
    int falsetrig_dis;
    double ST2Delay;
    double Nos2PWHi;
    int ExFuelCrank;
    int out3lim;
    int spkfop;
    int idlePeriod2;
    int AfrTar2;
    int dualdizzy;
    int AlphBaroCor;
    int led18_2Use;
    int StgCycles;
    double N2Odel_launch;
    int egoIgnCount;
    int led18Use;
    int HCutLCType;
    int idleThresh;
    int spkeop;
    int out1src;
    int taeColdM4;
    double egoSwitch2;
    int IgInv;
    double egoSwitch1;
    int IatAdvKpa;
    int Trig_plus;
    double SoftLimRetard;
    int AccDecEnrich;
    int WaterInj;
    int idledelayclock;
    int STTable2On;
    int ASEFixTim;
    double TractVSSMin;
    int rotary2;
    double KnockSb;
    double ShiftLower;
    int TPSAFRPoint;
    int tachconfdiv;
    int KPaTarg60;
    int ConsBarCor;
    double BarroLow;
    double LC_flatlim;
    double Ideadbnd;
    double NosRpm;
    double slowIdleRPM;
    int crankpwip;
    double DecayAcPoint;
    double RPMRedHi;
    int DecayAccel;
    double LC_flatsel;
    int pwmidlewhen;
    int OLoopEgo;
    double HardRevLim;
    int TwoLambda;
    int NosLagSystem;
    int bcUpdateRate;
    double KnockRpm;
    int OvrRunC;
    int TractSC4;
    int out4src;
    double Nos2PWLo;
    int trig5ret;
    int KPaTarg40;
    int TractSC2;
    int TractSC3;
    int ASEFixTemp;
    int TractSC1;
    int dwellcont;
    int pwmIdle;
    int IgAdvDeg;
    int injPwmP2;
    double IdleAdv;
    double Nos2Angle;
    int injPwmP1;
    int TargetB1;
    int EgoLim2;
    int TargetB2;
    int out1lim;
    int TargetB3;
    int TargetB4;
    double FixAng;
    int TpsOn;
    double KnockBoost;
    double ShiftUpper;
    double OvrBKpa;
    int neonCrank;
    int KnockOn;
    double CrankPWT9;
    double CrankPWT8;
    int out3sparkd;
    double CrankPWT7;
    double CrankPWT6;
    double CrankPWT5;
    double CrankPWT4;
    double CrankPWT3;
    double CrankPWT2;
    int AFMUse;
    double CrankPWT1;
    double KnockRpmL;
    int oddfire;
    int AccelMapASE;
    double PrimePulse;
    int mapType1;
    double dwellcrank;
    int egoType1;
    int egoType2;
    int mapType2;
    int IATCorTy;
    int KPaTarg10;
    int ASETable10;
    int inj2g;
    double dGain;
    double KnockRet;
    double LachDeg;
    int NosTps;
    int inj2t;
    int idleclosedc;
    int DtNos;
    int idle_dc_lo;
    int IgRetDeg;
    int efanontemp;
    int idleperiod;
    int StgDelta;
    int outboffs;
    int RPMBAsedAE;
    double outboffv;
    int NosClt;
    int EgoLimKPa;
    int TractCyc3;
    int TractCyc4;
    int TractCyc1;
    int TractCyc2;
    double reqFuel2;
    int PrimeTBox;
    double reqFuel1;
    double pGain;
    int nitrousUse;
    double TractSlipH;
    double egoRPM1;
    int StagedAnd;
    double TractScale;
    int wheel_old;
    int trig1ret;
    double battFac2;
    double RPMAE1;
    double battFac1;
    double RPMAE2;
    double BoostMaxKn;
    double N2Odel_flat;
    int IgRetTemp;
    double egoRPM2;
    int IStagedMode;
    double TrigAng;
    int StgTrans;
    int Stg2ndParmDlt;
    double StartWIKPa;
    double RPMAE4;
    double RPMAE3;
    int miss2nd;
    int OvrRunClt;
    int KnockTim;
    int rotaryFDign;
    int x2use;
    int KPaDotBoost;
    int DecelBoost;
    int IATCor;
    int HCutType;
    int bc_max_diff;
    double taeColdA4;
    int TractMode;
    int nCylinders2;
    int nCylinders1;
    int IdleAdvCLT;
    double WaterIRpm;
    double primePulse2;
    int idledashdc;
    int onetwo;
    double N2Oholdon;
    double BarroHi;
    int out2lim;
    int NosLowKpa;
    double Trim;
    int InvOut2;
    int InvOut1;
    int Out1UpLim;
    int aseIgnCount;
    int egoTemp2;
    int egoTemp1;
    int BoostDir;
    int TractionOn;
    int IATBoostSt;
    int Out3_Out1;
    int IdleDelayTime;
    double outfoffv;
    int outfoffs;
    double LaunchLim;
    int SparkCutBase;
    int KPaTarg100;
    int ASETime;
    double Nos2Rpm;
    int toy_dli;
    int dwellduty50;
    double mindischg;
    int mapProportion4;
    int trig4ret;
    int trig6ret;
    double NosRpmMax;
    int out3src;
    int BooTbl2Use;
    int Idashdelay;
    int inj1g;
    int launchUse;
    int tfi;
    int idlestartclk;
    int freqDiv;
    int idlecrankdc;
    int TPSBooIAT;
    int ExtraFMult;
    int boostUse;
    int wheelon;
    int mapSensor1;
    int TractCySec;
    double edismultirpm;
    int Stg2ndParmKPA;
    int led17Use;
    double CrankPWT10;
    int HCutSpark;
    int msnsCyl;
    int algorithm2;
    int out2Hysis;
    int algorithm1;
    double injOpen1;
    double injOpen2;
    double CrankRPM;
    double KnockMax;
    int KpaTPSTar;
    int hybridAN;
    int taeIgnCount;
    int VLaunch;
    int idle_dc_hi;
    int alternate2;
    double tpsThresh4;
    double KnockAdv;
    int idlemindc;
    int alternate1;
    int AirCorAFM;
    double Nos2RpmMax;


    private String[] defaultGauges = {
        "RpmHiResGauge",
        "cltGauge",
        "pulseWidth1Gauge",
        "dutyCycle1Gauge",
        "mapGauge",
        "matGauge",
        "ego2Gauge",
        "afrGauge",
        "advanceGauge",
        "tpsADCGauge"
    };
	@Override
	public void calculate(byte[] ochBuffer) throws IOException
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
        pulseWidth1 = (double)((MSUtils.getByte(ochBuffer,14) + 0.000) * 0.100);
        accelEnrich = (int)((MSUtils.getByte(ochBuffer,15) + 0.000) * 1.000);
        baroCorrection = (int)((MSUtils.getByte(ochBuffer,16) + 0.000) * 1.000);
        gammaEnrich = (int)((MSUtils.getByte(ochBuffer,17) + 0.000) * 1.000);
        veCurr1 = (int)((MSUtils.getByte(ochBuffer,18) + 0.000) * 1.000);
        pulseWidth2 = (double)((MSUtils.getByte(ochBuffer,19) + 0.000) * 0.100);
        veCurr2 = (int)((MSUtils.getByte(ochBuffer,20) + 0.000) * 1.000);
        idleDC = (int)((MSUtils.getByte(ochBuffer,21) + 0.000) * 1.000);
        iTime = (int)((MSUtils.getWord(ochBuffer,22) + 0.000) * 1.000);
        advance = (int)((MSUtils.getByte(ochBuffer,24) + 0.000) * 1.000);
        afrtarget = (int)((MSUtils.getByte(ochBuffer,25) + 0.000) * 1.000);
        fuelADC = (int)((MSUtils.getByte(ochBuffer,26) + 0.000) * 1.000);
        egtADC = (int)((MSUtils.getByte(ochBuffer,27) + 0.000) * 1.000);
        CltIatAngle = (int)((MSUtils.getByte(ochBuffer,28) + 0.000) * 1.000);
        KnockAngle = (int)((MSUtils.getByte(ochBuffer,29) + 0.000) * 1.000);
        egoCorrection2 = (int)((MSUtils.getByte(ochBuffer,30) + 0.000) * 1.000);
        porta = (int)((MSUtils.getByte(ochBuffer,31) + 0) * 1.000);
        portb = (int)((MSUtils.getByte(ochBuffer,32) + 0) * 1.000);
        portc = (int)((MSUtils.getByte(ochBuffer,33) + 0) * 1.000);
        portd = (int)((MSUtils.getByte(ochBuffer,34) + 0) * 1.000);
        stackL = (int)((MSUtils.getByte(ochBuffer,35) + 0) * 1.000);
        tpsLast = (int)((MSUtils.getByte(ochBuffer,36) + 0) * 1.000);
        iTimeX = (int)((MSUtils.getByte(ochBuffer,37) + 0.000) * 1.000);
        bcDC = (double)((MSUtils.getByte(ochBuffer,38) + 0.000) * 0.3922);
        accDecEnrich = ((((engine & 32) ) != 0 ) ?  100 : ((pulseWidth-injOpen1) / (pulseWidth-(accelEnrich / 10)-injOpen1) * 100));
        batteryVoltage = (batADC / 255.0 * 30.0);
        coolant = (tempCvt(table(cltADC, "thermfactor.inc")-40));
        egoVoltage = (egoADC / 255.0 * 5.0);
        ego2Voltage = (fuelADC / 255.0 * 5.0);
        mat = (tempCvt(table(matADC, "matfactor.inc")-40));
        rpm = (rpm100*100);
        time = (timeNow());
        if (CELSIUS)
        {
        egttemp = (egtADC * 3.90625);
        }
        else
        {
        egttemp = (egtADC * 7.15625);
        }
        if (NARROW_BAND_EGO)
        {
        afr2 = (table(fuelADC, "NBafr100.inc") / 100.0);
        lambda2 = (afr2    / 14.7);
        }
        else if (ZEITRONIX_NON_LINEAR)
        {
        lambda2 = (table(fuelADC, "WBafr100Zeit.inc") / 100.0);
        afr2 = (lambda * 14.7);
        }
        else if (INNOVATE_LC1_DEFAULT)
        {
        lambda2 = (fuelADC/255.0 + 0.5);
        afr2 = (lambda * 14.7);
        }
        else if (FAST_WIDEBAND_O2)
        {
        lambda2 = (fuelADC / 51);
        afr2 = (lambda2 * 14.7);
        }
        else
        {
        lambda2 = (table(fuelADC, "WBlambda100MOT.inc") / 100.0);
        afr2 = (lambda2 * 14.7);
        }
        if (NARROW_BAND_EGO)
        {
        afr = (table(egoADC,    "NBafr100.inc") / 100.0);
        lambda = (afr / 14.7);
        TargetAFR = (table(afrtarget, "NBafr100.inc") / 100.0);
        TargetLambda = (TargetAFR / 14.7);
        }
        else if (WB_1_0_LINEAR)
        {
        lambda = (1.5 - 5.0 * egoADC/255.0);
        afr = (1.5 - 5.0 * egoADC/255.0  * 14.7);
        TargetLambda = (1.5 - 5.0 * afrtarget/255.0);
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
        afr = ((table(egoADC, "WBlambda100MOT.inc") / 100.0 ) * 14.7);
        TargetLambda = (table(afrtarget,"WBlambda100MOT.inc") / 100.0);
        TargetAFR = (TargetLambda * 14.7);
        }
        else if (DYNOJET_LINEAR)
        {
        afr = (egoADC    * 0.031373 + 10);
        lambda = (afr / 14.7);
        TargetLambda = (TargetAFR / 14.7);
        TargetAFR = (afrtarget * 0.031373 + 10);
        }
        else if (TECHEDGE_LINEAR)
        {
        afr = (egoADC    * 0.039216 + 9);
        lambda = (afr / 14.7);
        TargetAFR = (afrtarget * 0.039216 + 9);
        TargetLambda = (TargetAFR / 14.7);
        }
        else if (INNOVATE_1_2_LINEAR)
        {
        afr = (egoADC    * 0.1961);
        lambda = (afr       / 14.7);
        TargetAFR = (afrtarget * 0.1961);
        TargetLambda = (TargetAFR / 14.7);
        }
        else if (INNOVATE_0_5_LINEAR)
        {
        afr = (10 + (egoADC    * 0.039216));
        lambda = (afr       / 14.7);
        TargetAFR = (10 + (afrtarget * 0.039216));
        TargetLambda = (TargetAFR / 14.7);
        }
        else if (INNOVATE_LC1_DEFAULT)
        {
        afr = (7.35 + (egoADC    * 0.0589804));
        lambda = (afr       / 14.7);
        TargetLambda = (afrtarget/255.0 + 0.5);
        TargetAFR = (TargetLambda * 14.7);
        }
        else if (ZEITRONIX_NON_LINEAR)
        {
        afr = (table(egoVoltage,   "zeitronix.inc"));
        lambda = (afr          / 14.7);
        afrTargetV = (afrtarget*5/255);
        TargetAFR = (table( afrTargetV ,"zeitronix.inc"));
        TargetLambda = (TargetAFR / 14.7);
        }
        else if (AEM_LINEAR)
        {
        afr = (9.72 + (egoADC    * 0.038666));
        lambda = (afr       / 14.7);
        TargetAFR = (9.72 + (afrtarget * 0.038666 ));
        TargetLambda = (TargetAFR / 14.7);
        }
        else if (AEM_NON_LINEAR)
        {
        afr = (8.44350 + (egoADC    * (0.012541 + egoADC    * (0.000192111 + egoADC    * (-0.00000138363 + egoADC    * 0.00000000442977)))));
        lambda = (afr       / 14.7);
        TargetAFR = (8.44350 + (afrtarget * (0.0102541 + afrtarget * (0.000192111 + afrtarget * (-0.00000138363 + afrtarget * 0.00000000442977)))));
        TargetLambda = (TargetAFR / 14.7);
        }
        else if (NGK_AFX)
        {
        afr = ((egoADC * 0.0270592) + 9);
        lambda = (afr       / 14.7);
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
        if (MPXH6300A)
        {
        barometer = ((baroADC + 1.53) * 1.213675);
        map = ((mapADC + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        barometer = ((baroADC + 2.147) * 1.6197783);
        map = ((mapADC + 2.147) * 1.6197783);
        }
        else if (MPX4250)
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
        advSpark = ((advance * 0.352)-10);
        KnockAng = ((KnockAngle * 90 / 256));
        KnockDeg = (-KnockAng);
        CltIatAng = (CltIatAngle * 90 / 256);
        CltIatDeg = ((CltIatAng < 45) ?  CltIatAng: -90 + CltIatAng);
        fuelvolt = ((fuelADC < 1 ) ?  0.0 : fuelADC * (5/255) - 0.5);
        fuelpress = ((fuelADC < 1 ) ?  0.0 : fuelvolt / 0.04 +1);
        altDiv1 = (((alternate1 ) != 0 ) ?  2 : 1);
        altDiv2 = (((alternate2 ) != 0 ) ?  2 : 1);
        cycleTime1 = ((rpm < 100 ) ?  0 : 60000.0 / rpm * (2.0-twoStroke1));
        nSquirts1 = (nCylinders1/divider1);
        dutyCycle1 = ((rpm < 100 ) ?  0 : 100.0*nSquirts1/altDiv1*pulseWidth1/cycleTime1);
        cycleTime2 = ((rpm < 100 ) ?  0 : 60000.0 / rpm * (2.0-twoStroke2));
        nSquirts2 = (nCylinders2/divider2);
        dutyCycle2 = ((rpm < 100 ) ?  0 : 100.0*nSquirts2/altDiv2*pulseWidth2/cycleTime2);
        Open_Time1 = (1.0);
        Open_Time2 = (1.0);
        InjectorRating1 = (100);
        InjectorRating2 = (100);
        dutyCy1Real = ((rpm < 100 ) ?  0 : InjectorRating1*nSquirts1/altDiv1*(pulseWidth1-Open_Time1)/cycleTime1);
        dutyCy2Real = ((rpm < 100 ) ?  0 : InjectorRating2*nSquirts2/altDiv2*(pulseWidth2-Open_Time2)/cycleTime2);
        veCurr = (veCurr1);
        pulseWidth = (pulseWidth1);
        YOffset = (182);
        XOffset = (187);
        YForce = ((egtADC > YOffset + 1 ) ?  (egtADC  - YOffset) * 0.04464 : egtADC < YOffset - 1 ? (egtADC  - YOffset) * 0.04464 : 0);
        XForce = ((fuelADC > XOffset + 1 ) ?  (fuelADC  - XOffset) * 0.04464 : fuelADC < XOffset - 1 ? (fuelADC  - XOffset) * 0.04464 : 0);
        test = (mapDOT);
        test2 = (egtADC);
        iTimefull = ((iTimeX*65536)+ iTime);
        RpmHitmp = ((iTimefull > 0 ) ?  (60000000 *(2.0-twoStroke1)) / (iTimefull * nCylinders1) : 0);
        RpmHiRes = ((RpmHitmp > 20 ) ?  RpmHitmp : 0);
        vacuum = ((barometer-map)*0.2953007);
        boost = ((map < barometer ) ?  0.0 : (map-barometer)*0.1450377);
        boostVac = ((map < barometer ) ?  -vacuum : (map-barometer)*0.1450377);
        Speed = (70);
        fuelCC = (2168);
        squirtmul = ((divider1 < 2 ) ?  2 : divider1 < 3 ? 1 : divider1 < 5 ? 0.5 : divider1 < 9 ? 0.25 : divider1 < 17 ? 0.125 : 1);
        squirts = ((alternate1 > 0 ) ?  rpm100 / 0.6 * squirtmul : rpm100 * 2 * squirtmul / 0.6);
        OpenWidth = (injOpen1 * 0.1);
        CCpHr = ((pulseWidth < 1 ) ?  1 : (fuelCC/60) * ((pulseWidth - OpenWidth)/1000) * squirts * 3600);
        mphTemp = ((fuelADC < 1 ) ?  0 : (fuelADC * Speed) /127.5);
        mph = ((mphTemp < 1 ) ?  0 : mphTemp);
        USgph = (CCpHr / 3785);
        USmpg = ((fuelADC < 1 ) ?  0 : mph < 1 ? 0 : mph / USgph);
        gph = (CCpHr / 4546);
        mpg = ((fuelADC < 1 ) ?  0 : mph < 1 ? 0 : mph / gph);
        Cd = (0.33);
        Mass = (1050);
        Crr = (18);
        Radius = (16.15);
        GrTms = (0.88);
        DiffRa = (3.31);
        floodclear = ((tpsADC > 200 ) ?  1 : 0);
        tpsDOTTY = (((mapProportion4 ) != 0 ) ?  0 : (tpsADC - tpsLast) * 0.19);
        mapDOTTY = (((mapProportion4 ) != 0 ) ?  (mapADC - tpsLast) / 0.1 : 0);
        tpsDOT = ((tpsDOTTY < 0 ) ?  0 : tpsDOTTY);
        mapDOT = ((mapDOTTY < 0 ) ?  0 : mapDOTTY);
        Timeroll = (portc & 4);
        waterIlog = (porta & 16);
        MAFVolts = (fuelADC * 0.0196078);
        nosActive1 = ((((portd & 2) ) != 0 ) ?  0 : 1);
    }
    @Override
    public String getLogHeader()
    {
        StringBuffer b = new StringBuffer();
        
        if (MSLVV_COMPATIBLE)
        {
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
        }
        else
        {
        b.append("Time").append("\t");
        b.append("SecL").append("\t");
        b.append("RPM/100").append("\t");
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
        b.append("idleDC").append("\t");
        b.append("BCDuty3").append("\t");
        b.append("Spark Angle").append("\t");
        b.append("EGT").append("\t");
        b.append("Fuel Press").append("\t");
        b.append("Knock").append("\t");
        b.append("RPM").append("\t");
        b.append("barometer").append("\t");
        b.append("porta").append("\t");
        b.append("portb").append("\t");
        b.append("portc").append("\t");
        b.append("portd").append("\t");
        b.append("NOS On").append("\t");
        b.append("batt V").append("\t");
        }
        b.append(MSUtils.getLocationLogHeader());
        return b.toString();
    }

    @Override
    public String getLogRow()
    {
        StringBuffer b = new StringBuffer();
        
        if (MSLVV_COMPATIBLE)
        {
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
        b.append(veCurr1).append("\t");
        b.append(round(pulseWidth1)).append("\t");
        b.append(veCurr2).append("\t");
        b.append(round(pulseWidth2)).append("\t");
        b.append(dutyCycle1).append("\t");
        b.append(dutyCycle2).append("\t");
        b.append(round(pulseWidth2)).append("\t");
        b.append(veCurr2).append("\t");
        b.append(idleDC).append("\t");
        }
        else
        {
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
        b.append(veCurr1).append("\t");
        b.append(round(pulseWidth1)).append("\t");
        b.append(veCurr2).append("\t");
        b.append(round(pulseWidth2)).append("\t");
        b.append(dutyCycle1).append("\t");
        b.append(dutyCycle2).append("\t");
        b.append(idleDC).append("\t");
        b.append(round(bcDC)).append("\t");
        b.append(advSpark).append("\t");
        b.append(egttemp).append("\t");
        b.append(fuelpress).append("\t");
        b.append(KnockDeg).append("\t");
        b.append(RpmHiRes).append("\t");
        b.append(barometer).append("\t");
        b.append(porta).append("\t");
        b.append(portb).append("\t");
        b.append(portc).append("\t");
        b.append(portd).append("\t");
        b.append(nosActive1).append("\t");
        b.append(batteryVoltage).append("\t");
        }
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge","deadValue",deadValue,"---","",0,1,-1,-1,2,2,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("baroADCGauge","baroADC",baroADC,"Barometer ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapADCGauge","mapADC",mapADC,"MAP ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matADCGauge","matADC",matADC,"MAT ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltADCGauge","cltADC",cltADC,"CLT ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tpsADCGauge","tpsADC",tpsADC,"TPS ADC","",0,255,-1,-1,256,256,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("batADCGauge","batADC",batADC,"BAT ADC","",0,255,-1,-1,256,256,0,0,45));
        if (NARROW_BAND_EGO)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge","egoVoltage",egoVoltage,"Rear Bank O2 Voltage","volts",0,1.0,0.2,0.3,0.6,0.8,2,2,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge","egoVoltage",egoVoltage,"Rear Bank O2 Voltage","volts",0.5,4.5,0.0,0.0,5.0,5.0,2,2,45));
        }
        if (NARROW_BAND_EGO)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ego2Gauge","ego2Voltage",ego2Voltage,"Front Bank O2 Voltage","volts",0,1.0,0.2,0.3,0.6,0.8,2,2,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ego2Gauge","ego2Voltage",ego2Voltage,"Front Bank O2 Voltage","volts",0.5,4.5,0.0,0.0,5.0,5.0,2,2,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambdaGauge","lambda",lambda,"Lambda","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelEnrichGauge","accDecEnrich",accDecEnrich,"AE Driven Fuel Pct","Pct",100,200,0,0,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afrGauge","afr",afr,"Air:Fuel Ratio","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clockGauge","secl",secl,"Clock","Seconds",0,255,10,10,245,245,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle1Gauge","dutyCycle1",dutyCycle1,"Duty Cycle 1","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle2Gauge","dutyCycle2",dutyCycle2,"Duty Cycle 2","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge","egoCorrection",egoCorrection,"O2 Driven Correction (GEGO)","%",80,120,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaEnrichGauge","gammaEnrich",gammaEnrich,"Gamma Enrichment","%",50,150,-1,-1,151,151,0,0,45));
        if (MPXH6300A)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge","map",map,"Engine MAP","kPa",0,304,0,20,250,275,0,0,45));
        }
        else if (MPXH6400A)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge","map",map,"Engine MAP","kPa",0,400,0,20,250,275,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge","map",map,"Engine MAP","kPa",0,255,0,20,200,245,0,0,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("baroGauge","barometer",barometer,"Barometer","kPa",60,110,0,20,200,245,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("barocorrGauge","baroCorrection",baroCorrection,"baro correction","%",0,120,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth1Gauge","pulseWidth1",pulseWidth1,"Pulse Width 1","mSec",0,25.5,1.0,1.2,20,25,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth2Gauge","pulseWidth2",pulseWidth2,"Pulse Width 2","mSec",0,25.5,1.0,1.2,20,25,1,1,45));
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
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","%TEMP",-40,250,0,0,200,220,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Manifold Air Temp","%TEMP",-40,215,0,0,200,210,0,0,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("advanceGauge","advSpark",advSpark,"Spark Advance","deg BTDC",50,-10,0,0,35,45,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("stackGauge","stackL",stackL,"CPU stack","bytes",240,200,0,0,254,254,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelGauge","fuelpress",fuelpress,"Fuel Pressure","lb/in",0,80,30,40,60,65,1,1,45));
        if (CELSIUS)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge","egttemp",egttemp,"EGT","C",0,1000,0,0,1450,1480,1,1,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge","egttemp",egttemp,"EGT","F",0,2200,0,0,1450,1480,1,1,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge2","egoCorrection2",egoCorrection2,"EGO Correction2","%",50,150,90,99,101,110,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("testGauge","test",test,"TEST","",0,255,255,255,0,0,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("test2Gauge","test2",test2,"TEST2","",0,255,255,255,0,0,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("AD6Gauge","fuelADC",fuelADC,"AD6 raw","",0,255,255,255,0,0,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("AD7Gauge","egtADC",egtADC,"AD7 raw","",0,255,255,255,0,0,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambdaGauge2","lambda2",lambda2,"Lambda2","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afrGauge2","afr2",afr2,"Air:Fuel Ratio2","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vacuumGauge","vacuum",vacuum,"Engine Vacuum","in-HG",0,30,0,0,30,30,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("boostgauge","boost",boost,"Engine Boost","PSIG",0,20,0,0,15,20,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("VacBooGauge","boostVac",boostVac,"Engine Vac Boost","in-HG/PSIG",-30,30,-30,-30,30,30,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("TargetAFRGauge","TargetAFR",TargetAFR,"Target AFR","AFR",10,19.4,0,0,20,20,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("RealDutyGauge1","dutyCy1Real",dutyCy1Real,"Fuel Delivery 1","Flow",0,75,-1,-1,75,75,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("RealDutyGauge2","dutyCy2Real",dutyCy2Real,"Fuel Delivery 2","Flow",0,75,-1,-1,75,75,1,1,45));
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
        
        
        if (MEMPAGES)
        {
        }
        else if (LOGPAGES)
        {
        }
        else
        {
        }
        pageBuffer = loadPage(1,0,189,new byte[]{80,1},new byte[]{86});
        if (SPEED_DENSITY)
        {
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        }
        else if (AIR_FLOW_METER)
        {
        }
        else if (ALPHA_N)
        {
        }
        if (CELSIUS)
        {
        egoTemp1 = (int)((MSUtils.getByte(pageBuffer,144) + -72.0) * 0.555);
        }
        else
        {
        egoTemp1 = (int)((MSUtils.getByte(pageBuffer,144) + -40.0) * 1.0);
        }
        egoCount1 = (int)((MSUtils.getByte(pageBuffer,145) + 0.0) * 1.0);
        egoDelta1 = (int)((MSUtils.getByte(pageBuffer,146) + 0.0) * 1.0);
        egoLimit1 = (int)((MSUtils.getByte(pageBuffer,147) + 0.0) * 1.0);
        reqFuel1 = (double)((MSUtils.getByte(pageBuffer,148) + 0.0) * 0.1);
        divider1 = (int)((MSUtils.getByte(pageBuffer,149) + 0.0) * 1.0);
        alternate1 = MSUtils.getBits(pageBuffer,150,0,0,0);
        injOpen1 = (double)((MSUtils.getByte(pageBuffer,151) + 0.0) * 0.1);
        injPwmP1 = (int)((MSUtils.getByte(pageBuffer,153) + 0.0) * 1.0);
        injPwmT1 = (double)((MSUtils.getByte(pageBuffer,154) + 0.0) * 0.1);
        battFac1 = (double)((MSUtils.getByte(pageBuffer,155) + 0.0) * 0.0166667);
        rpmk1 = (int)((MSUtils.getWord(pageBuffer,156) + 0.0) * 1.0);
        mapType1 = MSUtils.getBits(pageBuffer,182,0,1,0);
        mapSensor1 = MSUtils.getBits(pageBuffer,182,0,0,0);
        twoStroke1 = MSUtils.getBits(pageBuffer,182,2,2,0);
        nCylinders1 = MSUtils.getBits(pageBuffer,182,4,7,1);
        cltType1 = MSUtils.getBits(pageBuffer,183,0,1,0);
        matType1 = MSUtils.getBits(pageBuffer,183,2,3,0);
        nInjectors1 = MSUtils.getBits(pageBuffer,183,4,7,1);
        engineType1 = MSUtils.getBits(pageBuffer,184,0,0,0);
        egoType1 = MSUtils.getBits(pageBuffer,184,1,1,0);
        algorithm1 = MSUtils.getBits(pageBuffer,184,2,2,0);
        baroCorr1 = MSUtils.getBits(pageBuffer,184,3,3,0);
        egoRPM1 = (double)((MSUtils.getByte(pageBuffer,185) + 0.0) * 100.0);
        if (CELSIUS)
        {
        fastIdleT1 = (int)((MSUtils.getByte(pageBuffer,186) + -72.0) * 0.555);
        }
        else
        {
        fastIdleT1 = (int)((MSUtils.getByte(pageBuffer,186) + -40.0) * 1.0);
        }
        egoSwitch1 = (double)((MSUtils.getByte(pageBuffer,187) + 0.0) * 0.0196);
        egoIgnCount = MSUtils.getBits(pageBuffer,188,0,0,0);
        pageBuffer = loadPage(2,0,189,new byte[]{80,2},new byte[]{86});
        if (SPEED_DENSITY)
        {
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        }
        else if (AIR_FLOW_METER)
        {
        }
        else if (ALPHA_N)
        {
        }
        if (CELSIUS)
        {
        egoTemp2 = (int)((MSUtils.getByte(pageBuffer,144) + -72.0) * 0.555);
        }
        else
        {
        egoTemp2 = (int)((MSUtils.getByte(pageBuffer,144) + -40.0) * 1.0);
        }
        egoDelta2 = (int)((MSUtils.getByte(pageBuffer,146) + 0.0) * 1.0);
        egoLimit2 = (int)((MSUtils.getByte(pageBuffer,147) + 0.0) * 1.0);
        reqFuel2 = (double)((MSUtils.getByte(pageBuffer,148) + 0.0) * 0.1);
        divider2 = (int)((MSUtils.getByte(pageBuffer,149) + 0.0) * 1.0);
        alternate2 = MSUtils.getBits(pageBuffer,150,0,0,0);
        injOpen2 = (double)((MSUtils.getByte(pageBuffer,151) + 0.0) * 0.1);
        injPwmP2 = (int)((MSUtils.getByte(pageBuffer,153) + 0.0) * 1.0);
        injPwmT2 = (double)((MSUtils.getByte(pageBuffer,154) + 0.0) * 0.1);
        battFac2 = (double)((MSUtils.getByte(pageBuffer,155) + 0.0) * 0.0166667);
        rpmk2 = (int)((MSUtils.getWord(pageBuffer,156) + 0.0) * 1.0);
        mapType2 = MSUtils.getBits(pageBuffer,182,0,1,0);
        twoStroke2 = MSUtils.getBits(pageBuffer,182,2,2,0);
        nCylinders2 = MSUtils.getBits(pageBuffer,182,4,7,1);
        cltType2 = MSUtils.getBits(pageBuffer,183,0,1,0);
        matType2 = MSUtils.getBits(pageBuffer,183,2,3,0);
        nInjectors2 = MSUtils.getBits(pageBuffer,183,4,7,1);
        engineType2 = MSUtils.getBits(pageBuffer,184,0,0,0);
        egoType2 = MSUtils.getBits(pageBuffer,184,1,1,0);
        algorithm2 = MSUtils.getBits(pageBuffer,184,2,2,0);
        baroCorr2 = MSUtils.getBits(pageBuffer,184,3,3,0);
        primePulse2 = (double)((MSUtils.getByte(pageBuffer,185) + 0.0) * 0.1);
        egoRPM2 = (double)((MSUtils.getByte(pageBuffer,185) + 0.0) * 100.0);
        if (CELSIUS)
        {
        fastIdleT2 = (int)((MSUtils.getByte(pageBuffer,186) + -72.0) * 0.555);
        }
        else
        {
        fastIdleT2 = (int)((MSUtils.getByte(pageBuffer,186) + -40.0) * 1.0);
        }
        egoSwitch2 = (double)((MSUtils.getByte(pageBuffer,187) + 0.0) * 0.0196);
        TwoLambda = MSUtils.getBits(pageBuffer,188,0,0,0);
        pageBuffer = loadPage(3,0,189,new byte[]{80,3},new byte[]{86});
        if (SPEED_DENSITY)
        {
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        }
        else if (AIR_FLOW_METER)
        {
        }
        else if (ALPHA_N)
        {
        }
        TrigAng = (double)((MSUtils.getByte(pageBuffer,168) + 0.0) * 0.352);
        FixAng = (double)((MSUtils.getByte(pageBuffer,169) + -28.4) * 0.352);
        Trim = (double)((MSUtils.getSignedByte(pageBuffer,170) + 0.0) * 0.352);
        CrankAng = (double)((MSUtils.getByte(pageBuffer,171) + -28.4) * 0.352);
        IgHold = (int)((MSUtils.getByte(pageBuffer,172) + 0.0) * 1.0);
        Trig_plus = MSUtils.getBits(pageBuffer,173,0,1,0);
        TrigCrank = MSUtils.getBits(pageBuffer,173,2,2,0);
        IgInv = MSUtils.getBits(pageBuffer,173,3,3,0);
        oddfire = MSUtils.getBits(pageBuffer,173,4,4,0);
        IdleAdv = (double)((MSUtils.getByte(pageBuffer,174) + -28.4) * 0.352);
        IdleAdvTPS = (int)((MSUtils.getByte(pageBuffer,175) + 0.0) * 1.0);
        IdleAdvRPM = (double)((MSUtils.getByte(pageBuffer,176) + 0.0) * 100.0);
        if (CELSIUS)
        {
        IdleAdvCLT = (int)((MSUtils.getByte(pageBuffer,177) + -72.0) * 0.555);
        }
        else
        {
        IdleAdvCLT = (int)((MSUtils.getByte(pageBuffer,177) + -40.0) * 1.0);
        }
        IdleDelayTime = (int)((MSUtils.getByte(pageBuffer,178) + 0.0) * 1.0);
        StgCycles = (int)((MSUtils.getByte(pageBuffer,179) + 0.0) * 1.0);
        if (MPXH6300A)
        {
        Stg2ndParmKPA = (int)((MSUtils.getByte(pageBuffer,180) + 1.53) * 1.213675);
        Stg2ndParmDlt = (int)((MSUtils.getByte(pageBuffer,181) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        Stg2ndParmKPA = (int)((MSUtils.getByte(pageBuffer,180) + 2.147) * 1.6197783);
        Stg2ndParmDlt = (int)((MSUtils.getByte(pageBuffer,181) + 2.147) * 1.6197783);
        }
        else
        {
        Stg2ndParmKPA = (int)((MSUtils.getByte(pageBuffer,180) + 0.0) * 1.0);
        Stg2ndParmDlt = (int)((MSUtils.getByte(pageBuffer,181) + 0.0) * 1.0);
        }
        spare3_182 = (int)((MSUtils.getByte(pageBuffer,182) + 0.0) * 1.0);
        spare3_183 = (int)((MSUtils.getByte(pageBuffer,183) + 0.0) * 1.0);
        spare3_184 = (int)((MSUtils.getByte(pageBuffer,184) + 0.0) * 1.0);
        pageBuffer = loadPage(4,0,189,new byte[]{80,0},new byte[]{86});
        msnsCyl = MSUtils.getBits(pageBuffer,0,0,0,0);
        neonCrank = MSUtils.getBits(pageBuffer,0,1,1,0);
        wheelon = MSUtils.getBits(pageBuffer,0,2,2,0);
        onetwo = MSUtils.getBits(pageBuffer,0,3,3,0);
        edis = MSUtils.getBits(pageBuffer,0,4,5,0);
        tfi = MSUtils.getBits(pageBuffer,0,6,6,0);
        hei7 = MSUtils.getBits(pageBuffer,0,7,7,0);
        ignmode = (int)((MSUtils.getByte(pageBuffer,0) + 0.0) * 1.0);
        fidleUse = MSUtils.getBits(pageBuffer,1,0,0,0);
        led17Use = MSUtils.getBits(pageBuffer,1,1,1,0);
        led18Use = MSUtils.getBits(pageBuffer,1,2,3,0);
        led18_2Use = MSUtils.getBits(pageBuffer,1,3,3,0);
        led19Use = MSUtils.getBits(pageBuffer,1,4,4,0);
        x2use = MSUtils.getBits(pageBuffer,1,5,5,0);
        toy_dli = MSUtils.getBits(pageBuffer,1,7,7,0);
        spkeop = MSUtils.getBits(pageBuffer,116,3,3,0);
        spkfop = MSUtils.getBits(pageBuffer,116,4,4,0);
        SoftRevLim = (double)((MSUtils.getByte(pageBuffer,2) + 0.0) * 100.0);
        SoftLimRetard = (double)((MSUtils.getByte(pageBuffer,3) + -28.4) * 0.352);
        SoftLimMax = (double)((MSUtils.getByte(pageBuffer,4) + 0.0) * 0.1);
        HardRevLim = (double)((MSUtils.getByte(pageBuffer,6) + 0.0) * 100.0);
        out1lim = (int)((MSUtils.getByte(pageBuffer,7) + 0.0) * 1.0);
        out1src = MSUtils.getBits(pageBuffer,8,0,4,0);
        out2lim = (int)((MSUtils.getByte(pageBuffer,9) + 0.0) * 1.0);
        out2src = MSUtils.getBits(pageBuffer,10,0,4,0);
        out1Hysis = (int)((MSUtils.getByte(pageBuffer,126) + 0.0) * 1.0);
        out2Hysis = (int)((MSUtils.getByte(pageBuffer,127) + 0.0) * 1.0);
        out3lim = (int)((MSUtils.getByte(pageBuffer,133) + 0.0) * 1.0);
        out3src = MSUtils.getBits(pageBuffer,132,0,4,0);
        out3Timer = (double)((MSUtils.getByte(pageBuffer,134) + 0.0) * 0.1);
        Out3_Out1 = MSUtils.getBits(pageBuffer,116,7,7,0);
        out4lim = (int)((MSUtils.getByte(pageBuffer,139) + 0.0) * 1.0);
        out4src = MSUtils.getBits(pageBuffer,138,0,4,0);
        out4FAN = MSUtils.getBits(pageBuffer,1,6,6,0);
        wd_2trig = MSUtils.getBits(pageBuffer,11,0,0,0);
        whlsim = MSUtils.getBits(pageBuffer,11,2,2,0);
        taeIgnCount = MSUtils.getBits(pageBuffer,11,3,3,0);
        rotaryFDign = MSUtils.getBits(pageBuffer,11,4,4,0);
        hybridAN = MSUtils.getBits(pageBuffer,11,5,5,0);
        inj2cr = MSUtils.getBits(pageBuffer,11,6,6,0);
        boostUse = MSUtils.getBits(pageBuffer,12,0,0,0);
        shiftUse = MSUtils.getBits(pageBuffer,12,1,1,0);
        launchUse = MSUtils.getBits(pageBuffer,12,2,2,0);
        out3sparkd = MSUtils.getBits(pageBuffer,12,4,4,0);
        dwellduty50 = MSUtils.getBits(pageBuffer,12,5,6,0);
        whlsimcnt = (int)((MSUtils.getByte(pageBuffer,13) + 0.0) * 1.0);
        freqDiv = MSUtils.getBits(pageBuffer,14,0,2,0);
        bcUpdateRate = (int)((MSUtils.getByte(pageBuffer,15) + 0.0) * 1.0);
        pGain = (double)((MSUtils.getByte(pageBuffer,16) + 0.0) * 0.3922);
        dGain = (double)((MSUtils.getByte(pageBuffer,17) + 0.0) * 0.3922);
        ShiftLower = (double)((MSUtils.getByte(pageBuffer,18) + 0.0) * 100.0);
        ShiftUpper = (double)((MSUtils.getByte(pageBuffer,19) + 0.0) * 100.0);
        LaunchLim = (double)((MSUtils.getByte(pageBuffer,20) + 0.0) * 100.0);
        edismultirpm = (double)((MSUtils.getByte(pageBuffer,21) + 0.0) * 100.0);
        BoostDir = MSUtils.getBits(pageBuffer,92,6,6,0);
        inj1g = MSUtils.getBits(pageBuffer,33,5,5,0);
        inj2t = MSUtils.getBits(pageBuffer,33,4,4,0);
        inj2g = MSUtils.getBits(pageBuffer,33,6,6,0);
        trig2fall = MSUtils.getBits(pageBuffer,33,0,1,0);
        latency = (int)((MSUtils.getByte(pageBuffer,34) + 0.0) * 1.0);
        if (MPXH6300A)
        {
        KPaTarg10 = (int)((MSUtils.getByte(pageBuffer,29) + 1.53) * 1.213675);
        KPaTarg40 = (int)((MSUtils.getByte(pageBuffer,30) + 1.53) * 1.213675);
        KPaTarg60 = (int)((MSUtils.getByte(pageBuffer,31) + 1.53) * 1.213675);
        KPaTarg100 = (int)((MSUtils.getByte(pageBuffer,32) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        KPaTarg10 = (int)((MSUtils.getByte(pageBuffer,29) + 2.147) * 1.6197783);
        KPaTarg40 = (int)((MSUtils.getByte(pageBuffer,30) + 2.147) * 1.6197783);
        KPaTarg60 = (int)((MSUtils.getByte(pageBuffer,31) + 2.147) * 1.6197783);
        KPaTarg100 = (int)((MSUtils.getByte(pageBuffer,32) + 2.147) * 1.6197783);
        }
        else
        {
        KPaTarg10 = (int)((MSUtils.getByte(pageBuffer,29) + 0.0) * 1.0);
        KPaTarg40 = (int)((MSUtils.getByte(pageBuffer,30) + 0.0) * 1.0);
        KPaTarg60 = (int)((MSUtils.getByte(pageBuffer,31) + 0.0) * 1.0);
        KPaTarg100 = (int)((MSUtils.getByte(pageBuffer,32) + 0.0) * 1.0);
        }
        TPSRPMTarg = MSUtils.getBits(pageBuffer,92,7,7,0);
        TargetB1 = (int)((MSUtils.getByte(pageBuffer,25) + 0.0) * 1.0);
        TargetB2 = (int)((MSUtils.getByte(pageBuffer,26) + 0.0) * 1.0);
        TargetB3 = (int)((MSUtils.getByte(pageBuffer,27) + 0.0) * 1.0);
        TargetB4 = (int)((MSUtils.getByte(pageBuffer,28) + 0.0) * 1.0);
        AfrTar = MSUtils.getBits(pageBuffer,46,7,7,0);
        AfrTar2 = MSUtils.getBits(pageBuffer,92,1,1,0);
        if (MPXH6300A)
        {
        EgoLimKPa = (int)((MSUtils.getByte(pageBuffer,39) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        EgoLimKPa = (int)((MSUtils.getByte(pageBuffer,39) + 2.147) * 1.6197783);
        }
        else
        {
        EgoLimKPa = (int)((MSUtils.getByte(pageBuffer,39) + 0.0) * 1.0);
        }
        EgoLim2 = (int)((MSUtils.getByte(pageBuffer,40) + 0.0) * 1.0);
        KpaTPSTar = MSUtils.getBits(pageBuffer,109,4,4,0);
        TPSTar = MSUtils.getBits(pageBuffer,109,5,5,0);
        TPSAFRPoint = (int)((MSUtils.getByte(pageBuffer,121) + 0.0) * 1.0);
        LachTps = (int)((MSUtils.getByte(pageBuffer,41) + 0.0) * 1.0);
        LachDeg = (double)((MSUtils.getByte(pageBuffer,42) + -28.4) * 0.352);
        LachRpm = (double)((MSUtils.getByte(pageBuffer,43) + 0.0) * 100.0);
        VLaunch = MSUtils.getBits(pageBuffer,46,1,1,0);
        LC_flatsel = (double)((MSUtils.getByte(pageBuffer,124) + 0.0) * 100.0);
        LC_flatlim = (double)((MSUtils.getByte(pageBuffer,128) + 0.0) * 100.0);
        LC_f_slim = (double)((MSUtils.getByte(pageBuffer,140) + 0.0) * 100.0);
        LC_f_limangle = (double)((MSUtils.getByte(pageBuffer,141) + -28.4) * 0.352);
        if (MPXH6300A)
        {
        OvrBKpa = (double)((MSUtils.getByte(pageBuffer,44) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        OvrBKpa = (double)((MSUtils.getByte(pageBuffer,44) + 2.147) * 1.6197783);
        }
        else if (KPa)
        {
        OvrBKpa = (double)((MSUtils.getByte(pageBuffer,44) + 0.0) * 1.0);
        }
        else
        {
        OvrBKpa = (double)((MSUtils.getByte(pageBuffer,44) + -100.0) * 0.147);
        }
        CltAdv = MSUtils.getBits(pageBuffer,46,2,2,0);
        if (CELSIUS)
        {
        IgAdvDeg = (int)((MSUtils.getByte(pageBuffer,48) + 0.0) * 0.555);
        IgAdvTemp = (int)((MSUtils.getByte(pageBuffer,47) + -72.0) * 0.555);
        IgRetDeg = (int)((MSUtils.getByte(pageBuffer,50) + 0.0) * 0.555);
        IgRetTemp = (int)((MSUtils.getByte(pageBuffer,52) + -72.0) * 0.555);
        }
        else
        {
        IgAdvDeg = (int)((MSUtils.getByte(pageBuffer,48) + 0.0) * 1.0);
        IgAdvTemp = (int)((MSUtils.getByte(pageBuffer,47) + -40.0) * 1.0);
        IgRetDeg = (int)((MSUtils.getByte(pageBuffer,50) + 0.0) * 1.0);
        IgRetTemp = (int)((MSUtils.getByte(pageBuffer,52) + -40.0) * 1.0);
        }
        MaxIatAdv = (double)((MSUtils.getByte(pageBuffer,49) + 0.0) * 0.352);
        if (MPXH6300A)
        {
        IatAdvKpa = (int)((MSUtils.getByte(pageBuffer,51) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        IatAdvKpa = (int)((MSUtils.getByte(pageBuffer,51) + 2.147) * 1.6197783);
        }
        else
        {
        IatAdvKpa = (int)((MSUtils.getByte(pageBuffer,51) + 0.0) * 1.0);
        }
        KnockOn = MSUtils.getBits(pageBuffer,46,6,6,0);
        KnockRpm = (double)((MSUtils.getByte(pageBuffer,53) + 0.0) * 100.0);
        if (MPXH6300A)
        {
        KnockKpa = (int)((MSUtils.getByte(pageBuffer,55) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        KnockKpa = (int)((MSUtils.getByte(pageBuffer,55) + 2.147) * 1.6197783);
        }
        else
        {
        KnockKpa = (int)((MSUtils.getByte(pageBuffer,55) + 0.0) * 1.0);
        }
        KnockRpmL = (double)((MSUtils.getByte(pageBuffer,54) + 0.0) * 100.0);
        KnockRet = (double)((MSUtils.getByte(pageBuffer,56) + 0.0) * 0.352);
        KnockSb = (double)((MSUtils.getByte(pageBuffer,57) + 0.0) * 0.352);
        KnockMax = (double)((MSUtils.getByte(pageBuffer,59) + 0.0) * 0.352);
        KnockAdv = (double)((MSUtils.getByte(pageBuffer,58) + 0.0) * 0.352);
        KnockTim = (int)((MSUtils.getByte(pageBuffer,60) + 0.0) * 1.0);
        if (MPXH6300A)
        {
        KnockBoost = (double)((MSUtils.getByte(pageBuffer,107) + 1.53) * 1.213675);
        BoostMaxKn = (double)((MSUtils.getByte(pageBuffer,108) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        KnockBoost = (double)((MSUtils.getByte(pageBuffer,107) + 2.147) * 1.6197783);
        BoostMaxKn = (double)((MSUtils.getByte(pageBuffer,108) + 2.147) * 1.6197783);
        }
        else if (KPa)
        {
        KnockBoost = (double)((MSUtils.getByte(pageBuffer,107) + 0.0) * 1.0);
        BoostMaxKn = (double)((MSUtils.getByte(pageBuffer,108) + 0.0) * 1.0);
        }
        else
        {
        KnockBoost = (double)((MSUtils.getByte(pageBuffer,107) + 0.0) * 0.147);
        BoostMaxKn = (double)((MSUtils.getByte(pageBuffer,108) + 0.0) * 0.147);
        }
        WaterInj = MSUtils.getBits(pageBuffer,46,3,3,0);
        WaterIRpm = (double)((MSUtils.getByte(pageBuffer,63) + 0.0) * 100.0);
        if (MPXH6300A)
        {
        StartWIKPa = (double)((MSUtils.getByte(pageBuffer,62) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        StartWIKPa = (double)((MSUtils.getByte(pageBuffer,62) + 2.147) * 1.6197783);
        }
        else if (KPa)
        {
        StartWIKPa = (double)((MSUtils.getByte(pageBuffer,62) + 0.0) * 1.0);
        }
        else
        {
        StartWIKPa = (double)((MSUtils.getByte(pageBuffer,62) + -100.0) * 0.147);
        }
        if (CELSIUS)
        {
        WaterIIat = (int)((MSUtils.getByte(pageBuffer,61) + -72.0) * 0.555);
        }
        else
        {
        WaterIIat = (int)((MSUtils.getByte(pageBuffer,61) + -40.0) * 1.0);
        }
        OLoopEgo = MSUtils.getBits(pageBuffer,46,0,0,0);
        if (MPXH6300A)
        {
        KPaOn = (int)((MSUtils.getByte(pageBuffer,64) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        KPaOn = (int)((MSUtils.getByte(pageBuffer,64) + 2.147) * 1.6197783);
        }
        else
        {
        KPaOn = (int)((MSUtils.getByte(pageBuffer,64) + 0.0) * 1.0);
        }
        TpsOn = (int)((MSUtils.getByte(pageBuffer,65) + 0.0) * 1.0);
        HCutType = MSUtils.getBits(pageBuffer,46,4,5,0);
        HCutSpark = (int)((MSUtils.getByte(pageBuffer,45) + 0.0) * 1.0);
        HCutLCType = MSUtils.getBits(pageBuffer,76,0,1,0);
        HCutLCSpark = (int)((MSUtils.getByte(pageBuffer,77) + 0.0) * 1.0);
        SparkCutBase = (int)((MSUtils.getByte(pageBuffer,78) + -1.0) * 1.0);
        OvrBCutType = MSUtils.getBits(pageBuffer,76,4,5,0);
        SparkCutBNum = (int)((MSUtils.getByte(pageBuffer,84) + 0.0) * 1.0);
        InvOut1 = MSUtils.getBits(pageBuffer,66,1,1,0);
        InvOut2 = MSUtils.getBits(pageBuffer,66,2,2,0);
        Out1UpLim = (int)((MSUtils.getByte(pageBuffer,88) + 0.0) * 1.0);
        Out2UpLim = (int)((MSUtils.getByte(pageBuffer,89) + 0.0) * 1.0);
        edismulti = MSUtils.getBits(pageBuffer,66,3,3,0);
        NosRpm = (double)((MSUtils.getByte(pageBuffer,23) + 0.0) * 100.0);
        NosTps = (int)((MSUtils.getByte(pageBuffer,67) + 0.0) * 1.0);
        if (CELSIUS)
        {
        NosClt = (int)((MSUtils.getByte(pageBuffer,22) + -72.0) * 0.555);
        }
        else
        {
        NosClt = (int)((MSUtils.getByte(pageBuffer,22) + -40.0) * 1.0);
        }
        NosAngle = (double)((MSUtils.getByte(pageBuffer,68) + 0.0) * 0.352);
        NosPWLo = (double)((MSUtils.getByte(pageBuffer,69) + 0.0) * 0.1);
        NosPWHi = (double)((MSUtils.getByte(pageBuffer,70) + 0.0) * 0.1);
        nitrousUse = MSUtils.getBits(pageBuffer,11,7,7,0);
        NosRpmMax = (double)((MSUtils.getByte(pageBuffer,24) + 0.0) * 100.0);
        DtNos = MSUtils.getBits(pageBuffer,66,5,5,0);
        NosLagSystem = MSUtils.getBits(pageBuffer,76,6,6,0);
        if (MPXH6300A)
        {
        NosLowKpa = (int)((MSUtils.getByte(pageBuffer,85) + 1.53) * 1.213675);
        NosHiKpa = (int)((MSUtils.getByte(pageBuffer,86) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        NosLowKpa = (int)((MSUtils.getByte(pageBuffer,85) + 2.147) * 1.6197783);
        NosHiKpa = (int)((MSUtils.getByte(pageBuffer,86) + 2.147) * 1.6197783);
        }
        else
        {
        NosLowKpa = (int)((MSUtils.getByte(pageBuffer,85) + 0.0) * 1.0);
        NosHiKpa = (int)((MSUtils.getByte(pageBuffer,86) + 0.0) * 1.0);
        }
        N2Odel_launch = (double)((MSUtils.getByte(pageBuffer,157) + 0.0) * 0.01);
        N2Odel_flat = (double)((MSUtils.getByte(pageBuffer,158) + 0.0) * 0.01);
        N2Oholdon = (double)((MSUtils.getByte(pageBuffer,159) + 0.0) * 0.01);
        numteeth = (int)((MSUtils.getByte(pageBuffer,90) + 0.0) * 1.0);
        trig1 = (int)((MSUtils.getByte(pageBuffer,25) + 0.0) * 1.0);
        trig2 = (int)((MSUtils.getByte(pageBuffer,26) + 0.0) * 1.0);
        trig3 = (int)((MSUtils.getByte(pageBuffer,27) + 0.0) * 1.0);
        trig4 = (int)((MSUtils.getByte(pageBuffer,28) + 0.0) * 1.0);
        trig1ret = (int)((MSUtils.getByte(pageBuffer,29) + 0.0) * 1.0);
        trig2ret = (int)((MSUtils.getByte(pageBuffer,30) + 0.0) * 1.0);
        trig3ret = (int)((MSUtils.getByte(pageBuffer,31) + 0.0) * 1.0);
        trig4ret = (int)((MSUtils.getByte(pageBuffer,32) + 0.0) * 1.0);
        trig5 = (int)((MSUtils.getByte(pageBuffer,145) + 0.0) * 1.0);
        trig6 = (int)((MSUtils.getByte(pageBuffer,146) + 0.0) * 1.0);
        trig5ret = (int)((MSUtils.getByte(pageBuffer,147) + 0.0) * 1.0);
        trig6ret = (int)((MSUtils.getByte(pageBuffer,148) + 0.0) * 1.0);
        miss2nd = MSUtils.getBits(pageBuffer,66,0,0,0);
        dualdizzy = MSUtils.getBits(pageBuffer,92,4,4,0);
        OvrRunC = MSUtils.getBits(pageBuffer,66,6,6,0);
        OvrRunRpm = (double)((MSUtils.getByte(pageBuffer,71) + 0.0) * 100.0);
        if (CELSIUS)
        {
        OvrRunClt = (int)((MSUtils.getByte(pageBuffer,182) + -72.0) * 0.555);
        }
        else
        {
        OvrRunClt = (int)((MSUtils.getByte(pageBuffer,182) + -40.0) * 1.0);
        }
        if (MPXH6300A)
        {
        OvrRunKpa = (int)((MSUtils.getByte(pageBuffer,72) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        OvrRunKpa = (int)((MSUtils.getByte(pageBuffer,72) + 2.147) * 1.6197783);
        }
        else
        {
        OvrRunKpa = (int)((MSUtils.getByte(pageBuffer,72) + 0.0) * 1.0);
        }
        OvrRunTps = (int)((MSUtils.getByte(pageBuffer,73) + 0.0) * 1.0);
        OvrRunTimr = (int)((MSUtils.getByte(pageBuffer,130) + 0.0) * 1.0);
        AccDecEnrich = MSUtils.getBits(pageBuffer,66,7,7,0);
        KPaDotBoost = MSUtils.getBits(pageBuffer,66,4,4,0);
        DecayAccel = MSUtils.getBits(pageBuffer,116,6,6,0);
        DecayAcPoint = (double)((MSUtils.getByte(pageBuffer,183) + 0.0) * 0.1);
        if (CELSIUS)
        {
        efanontemp = (int)((MSUtils.getByte(pageBuffer,74) + -72.0) * 0.555);
        efanofftemp = (int)((MSUtils.getByte(pageBuffer,75) + -72.0) * 0.555);
        }
        else
        {
        efanontemp = (int)((MSUtils.getByte(pageBuffer,74) + -40.0) * 1.0);
        efanofftemp = (int)((MSUtils.getByte(pageBuffer,75) + -40.0) * 1.0);
        }
        IStagedMode = MSUtils.getBits(pageBuffer,76,2,3,0);
        ScaleFac = (int)((MSUtils.getByte(pageBuffer,79) + 0.0) * 1.0);
        StgTrans = (int)((MSUtils.getByte(pageBuffer,80) + 0.0) * 1.0);
        StgDelta = (int)((MSUtils.getByte(pageBuffer,81) + 0.0) * 1.0);
        StagedMAP2nd = MSUtils.getBits(pageBuffer,109,6,6,0);
        StagedAnd = MSUtils.getBits(pageBuffer,109,7,7,0);
        if (MPXH6300A)
        {
        BarroHi = (double)((MSUtils.getByte(pageBuffer,82) + 1.53) * 1.213675);
        BarroLow = (double)((MSUtils.getByte(pageBuffer,83) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        BarroHi = (double)((MSUtils.getByte(pageBuffer,82) + 2.147) * 1.6197783);
        BarroLow = (double)((MSUtils.getByte(pageBuffer,83) + 2.147) * 1.6197783);
        }
        else if (MPX4250)
        {
        BarroHi = (double)((MSUtils.getByte(pageBuffer,82) + 8.0) * 0.9837);
        BarroLow = (double)((MSUtils.getByte(pageBuffer,83) + 8.0) * 0.9837);
        }
        else
        {
        BarroHi = (double)((MSUtils.getByte(pageBuffer,82) + 10.0) * 0.433);
        BarroLow = (double)((MSUtils.getByte(pageBuffer,83) + 10.0) * 0.433);
        }
        AlphBaroCor = MSUtils.getBits(pageBuffer,184,3,3,0);
        AFMUse = MSUtils.getBits(pageBuffer,184,5,5,0);
        AirCorAFM = MSUtils.getBits(pageBuffer,184,6,6,0);
        ConsBarCor = MSUtils.getBits(pageBuffer,184,7,7,0);
        STTable2On = MSUtils.getBits(pageBuffer,76,7,7,0);
        ST2Delay = (double)((MSUtils.getByte(pageBuffer,87) + 0.0) * 0.1);
        UseVE3 = MSUtils.getBits(pageBuffer,92,0,0,0);
        VE3Delay = (double)((MSUtils.getByte(pageBuffer,93) + 0.0) * 0.1);
        TractionOn = MSUtils.getBits(pageBuffer,92,5,5,0);
        RPMThresh = (double)((MSUtils.getByte(pageBuffer,98) + 0.0) * 1000.0);
        RPM1S = (double)((MSUtils.getByte(pageBuffer,94) + 0.0) * 0.1);
        RPM2S = (double)((MSUtils.getByte(pageBuffer,95) + 0.0) * 0.1);
        RPM3S = (double)((MSUtils.getByte(pageBuffer,96) + 0.0) * 0.1);
        RPM4S = (double)((MSUtils.getByte(pageBuffer,97) + 0.0) * 0.1);
        TractRet1 = (double)((MSUtils.getByte(pageBuffer,99) + 0.0) * 0.352);
        TractRet2 = (double)((MSUtils.getByte(pageBuffer,100) + 0.0) * 0.352);
        TractRet3 = (double)((MSUtils.getByte(pageBuffer,101) + 0.0) * 0.352);
        TractRet4 = (double)((MSUtils.getByte(pageBuffer,102) + 0.0) * 0.352);
        TractSC1 = (int)((MSUtils.getByte(pageBuffer,103) + 0.0) * 1.0);
        TractSC2 = (int)((MSUtils.getByte(pageBuffer,104) + 0.0) * 1.0);
        TractSC3 = (int)((MSUtils.getByte(pageBuffer,105) + 0.0) * 1.0);
        TractSC4 = (int)((MSUtils.getByte(pageBuffer,106) + 0.0) * 1.0);
        TractNOS = MSUtils.getBits(pageBuffer,109,0,0,0);
        TractCyc1 = (int)((MSUtils.getByte(pageBuffer,112) + 0.0) * 1.0);
        TractCyc2 = (int)((MSUtils.getByte(pageBuffer,113) + 0.0) * 1.0);
        TractCyc3 = (int)((MSUtils.getByte(pageBuffer,114) + 0.0) * 1.0);
        TractCyc4 = (int)((MSUtils.getByte(pageBuffer,115) + 0.0) * 1.0);
        TractCySec = MSUtils.getBits(pageBuffer,109,2,2,0);
        TractScale = (double)((MSUtils.getByte(pageBuffer,119) + 0.0) * 0.39216);
        TractSlip = (double)((MSUtils.getByte(pageBuffer,120) + 0.0) * 0.39216);
        TractVSSMax = (double)((MSUtils.getByte(pageBuffer,118) + 0.0) * 0.0196);
        TractVSSMin = (double)((MSUtils.getByte(pageBuffer,117) + 0.0) * 0.0196);
        TractMode = MSUtils.getBits(pageBuffer,109,3,3,0);
        TractSlipH = (double)((MSUtils.getByte(pageBuffer,123) + 0.0) * 0.39216);
        falsetrig_dis = MSUtils.getBits(pageBuffer,92,2,2,0);
        wheel_old = MSUtils.getBits(pageBuffer,92,3,3,0);
        dwellcont = MSUtils.getBits(pageBuffer,109,1,1,0);
        dwellcrank = (double)((MSUtils.getByte(pageBuffer,110) + 0.0) * 0.1);
        dwellrun = (double)((MSUtils.getByte(pageBuffer,111) + 0.0) * 0.1);
        mindischg = (double)((MSUtils.getByte(pageBuffer,143) + 0.0) * 0.1);
        tachconf = MSUtils.getBits(pageBuffer,144,0,2,0);
        tachconfdiv = MSUtils.getBits(pageBuffer,144,7,7,0);
        if (MPXH6300A)
        {
        bc_max_diff = (int)((MSUtils.getByte(pageBuffer,125) + 1.53) * 1.213675);
        DecelKPa = (int)((MSUtils.getByte(pageBuffer,129) + 1.53) * 1.213675);
        Pambient = (int)((MSUtils.getByte(pageBuffer,185) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        bc_max_diff = (int)((MSUtils.getByte(pageBuffer,125) + 2.147) * 1.6197783);
        DecelKPa = (int)((MSUtils.getByte(pageBuffer,129) + 2.147) * 1.6197783);
        Pambient = (int)((MSUtils.getByte(pageBuffer,185) + 2.147) * 1.6197783);
        }
        else
        {
        bc_max_diff = (int)((MSUtils.getByte(pageBuffer,125) + 0.0) * 1.0);
        DecelKPa = (int)((MSUtils.getByte(pageBuffer,129) + 0.0) * 1.0);
        Pambient = (int)((MSUtils.getByte(pageBuffer,185) + 0.0) * 1.0);
        }
        DecelBoost = MSUtils.getBits(pageBuffer,92,7,7,0);
        if (CELSIUS)
        {
        IATBoostSt = (int)((MSUtils.getByte(pageBuffer,135) + -72.0) * 0.555);
        IATBoost = (int)((MSUtils.getByte(pageBuffer,136) + 0.0) * 0.555);
        TPSBooIAT = (int)((MSUtils.getByte(pageBuffer,137) + 0.0) * 1.0);
        }
        else
        {
        IATBoostSt = (int)((MSUtils.getByte(pageBuffer,135) + -40.0) * 1.0);
        IATBoost = (int)((MSUtils.getByte(pageBuffer,136) + 0.0) * 1.0);
        TPSBooIAT = (int)((MSUtils.getByte(pageBuffer,137) + 0.0) * 1.0);
        }
        BooTbl2Use = MSUtils.getBits(pageBuffer,116,2,2,0);
        mapThresh4 = (double)((MSUtils.getByte(pageBuffer,91) + 0.0) * 10.0);
        taeColdA4 = (double)((MSUtils.getByte(pageBuffer,177) + 0.0) * 0.1);
        tpsThresh4 = (double)((MSUtils.getByte(pageBuffer,178) + 0.0) * 0.1953125);
        taeTime4 = (double)((MSUtils.getByte(pageBuffer,179) + 0.0) * 0.1);
        tdePct4 = (int)((MSUtils.getByte(pageBuffer,180) + 0.0) * 1.0);
        taeColdM4 = (int)((MSUtils.getByte(pageBuffer,181) + 0.0) * 1.0);
        mapProportion4 = MSUtils.getBits(pageBuffer,66,7,7,0);
        AccelMapASE = MSUtils.getBits(pageBuffer,184,2,2,0);
        RPMBAsedAE = MSUtils.getBits(pageBuffer,184,4,4,0);
        RPMAE1 = (double)((MSUtils.getByte(pageBuffer,152) + 0.0) * 100.0);
        RPMAE2 = (double)((MSUtils.getByte(pageBuffer,151) + 0.0) * 100.0);
        RPMAE3 = (double)((MSUtils.getByte(pageBuffer,150) + 0.0) * 100.0);
        RPMAE4 = (double)((MSUtils.getByte(pageBuffer,149) + 0.0) * 100.0);
        RPMmS1 = (double)((MSUtils.getByte(pageBuffer,153) + 0.0) * 0.1);
        RPMmS2 = (double)((MSUtils.getByte(pageBuffer,154) + 0.0) * 0.1);
        RPMmS3 = (double)((MSUtils.getByte(pageBuffer,155) + 0.0) * 0.1);
        RPMmS4 = (double)((MSUtils.getByte(pageBuffer,156) + 0.0) * 0.1);
        pageBuffer = loadPage(5,0,189,new byte[]{80,4},new byte[]{86});
        if (SPEED_DENSITY)
        {
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        }
        else if (AIR_FLOW_METER)
        {
        }
        else if (ALPHA_N)
        {
        }
        pageBuffer = loadPage(6,0,189,new byte[]{80,5},new byte[]{86});
        if (SPEED_DENSITY)
        {
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        }
        else if (AIR_FLOW_METER)
        {
        }
        else if (ALPHA_N)
        {
        }
        ASETable1 = (int)((MSUtils.getByte(pageBuffer,168) + 0.0) * 1.0);
        ASETable2 = (int)((MSUtils.getByte(pageBuffer,169) + 0.0) * 1.0);
        ASETable3 = (int)((MSUtils.getByte(pageBuffer,170) + 0.0) * 1.0);
        ASETable4 = (int)((MSUtils.getByte(pageBuffer,171) + 0.0) * 1.0);
        ASETable5 = (int)((MSUtils.getByte(pageBuffer,172) + 0.0) * 1.0);
        ASETable6 = (int)((MSUtils.getByte(pageBuffer,173) + 0.0) * 1.0);
        ASETable7 = (int)((MSUtils.getByte(pageBuffer,174) + 0.0) * 1.0);
        ASETable8 = (int)((MSUtils.getByte(pageBuffer,175) + 0.0) * 1.0);
        ASETable9 = (int)((MSUtils.getByte(pageBuffer,176) + 0.0) * 1.0);
        ASETable10 = (int)((MSUtils.getByte(pageBuffer,177) + 0.0) * 1.0);
        ASETime = (int)((MSUtils.getByte(pageBuffer,178) + 0.0) * 1.0);
        aseIgnCount = MSUtils.getBits(pageBuffer,179,0,0,0);
        ASEFixDe = MSUtils.getBits(pageBuffer,179,1,1,0);
        ASEFixTim = (int)((MSUtils.getByte(pageBuffer,180) + 0.0) * 1.0);
        if (CELSIUS)
        {
        ASEFixTemp = (int)((MSUtils.getByte(pageBuffer,181) + -72.0) * 0.555);
        }
        else
        {
        ASEFixTemp = (int)((MSUtils.getByte(pageBuffer,181) + -40.0) * 1.0);
        }
        VEFixASE = MSUtils.getBits(pageBuffer,179,2,2,0);
        if (MPXH6300A)
        {
        VEFixValue = (double)((MSUtils.getByte(pageBuffer,182) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        VEFixValue = (double)((MSUtils.getByte(pageBuffer,182) + 2.147) * 1.6197783);
        }
        else
        {
        VEFixValue = (double)((MSUtils.getByte(pageBuffer,182) + 9.0) * 0.965);
        }
        if (NARROW_BAND_EGO)
        {
        }
        else if (WB_1_0_LINEAR)
        {
        }
        else if (WB_UNKNOWN)
        {
        }
        else if (AEM_LINEAR)
        {
        }
        else if (AEM_NON_LINEAR)
        {
        }
        else if (DIYWB_NON_LINEAR)
        {
        }
        else if (DYNOJET_LINEAR)
        {
        }
        else if (TECHEDGE_LINEAR)
        {
        }
        else if (INNOVATE_1_2_LINEAR)
        {
        }
        else if (INNOVATE_0_5_LINEAR)
        {
        }
        else if (INNOVATE_LC1_DEFAULT)
        {
        }
        else if (FAST_WIDEBAND_O2)
        {
        }
        else if (ZEITRONIX_NON_LINEAR)
        {
        }
        else
        {
        
        
        }
        if (SPEED_DENSITY)
        {
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        }
        else if (AIR_FLOW_METER)
        {
        }
        else if (ALPHA_N)
        {
        }
        if (NARROW_BAND_EGO)
        {
        }
        else if (WB_1_0_LINEAR)
        {
        }
        else if (WB_UNKNOWN)
        {
        }
        else if (AEM_LINEAR)
        {
        }
        else if (AEM_NON_LINEAR)
        {
        }
        else if (DIYWB_NON_LINEAR)
        {
        }
        else if (DYNOJET_LINEAR)
        {
        }
        else if (TECHEDGE_LINEAR)
        {
        }
        else if (INNOVATE_1_2_LINEAR)
        {
        }
        else if (INNOVATE_0_5_LINEAR)
        {
        }
        else if (INNOVATE_LC1_DEFAULT)
        {
        }
        else if (FAST_WIDEBAND_O2)
        {
        }
        else if (ZEITRONIX_NON_LINEAR)
        {
        }
        else
        {
        
        
        }
        if (SPEED_DENSITY)
        {
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        }
        else if (AIR_FLOW_METER)
        {
        }
        else if (ALPHA_N)
        {
        }
        Nos2Rpm = (double)((MSUtils.getByte(pageBuffer,160) + 0.0) * 100.0);
        Nos2RpmMax = (double)((MSUtils.getByte(pageBuffer,161) + 0.0) * 100.0);
        Nos2delay = (double)((MSUtils.getByte(pageBuffer,162) + 0.0) * 0.01);
        Nos2Angle = (double)((MSUtils.getByte(pageBuffer,163) + 0.0) * 0.352);
        Nos2PWLo = (double)((MSUtils.getByte(pageBuffer,164) + 0.0) * 0.1);
        Nos2PWHi = (double)((MSUtils.getByte(pageBuffer,165) + 0.0) * 0.1);
        outaoffs = MSUtils.getBits(pageBuffer,166,0,2,0);
        outaoffv = (double)((MSUtils.getByte(pageBuffer,167) + 0.0) * 0.352);
        outboffs = MSUtils.getBits(pageBuffer,168,0,2,0);
        outboffv = (double)((MSUtils.getByte(pageBuffer,169) + 0.0) * 0.352);
        outcoffs = MSUtils.getBits(pageBuffer,170,0,2,0);
        outcoffv = (double)((MSUtils.getByte(pageBuffer,171) + 0.0) * 0.352);
        outdoffs = MSUtils.getBits(pageBuffer,172,0,2,0);
        outdoffv = (double)((MSUtils.getByte(pageBuffer,173) + 0.0) * 0.352);
        outeoffs = MSUtils.getBits(pageBuffer,174,0,2,0);
        outeoffv = (double)((MSUtils.getByte(pageBuffer,175) + 0.0) * 0.352);
        outfoffs = MSUtils.getBits(pageBuffer,176,0,2,0);
        outfoffv = (double)((MSUtils.getByte(pageBuffer,177) + 0.0) * 0.352);
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        idle_dc_lo = (int)((MSUtils.getByte(pageBuffer,0) + 0.0) * 1.0);
        idleperiod = (int)((MSUtils.getByte(pageBuffer,1) + 0.0) * 1.0);
        idlecrankdc = (int)((MSUtils.getByte(pageBuffer,2) + 0.0) * 1.0);
        idledelayclock = (int)((MSUtils.getByte(pageBuffer,3) + 0.0) * 1.0);
        idledashdc = (int)((MSUtils.getByte(pageBuffer,4) + 0.0) * 1.0);
        idlemindc = (int)((MSUtils.getByte(pageBuffer,5) + 0.0) * 1.0);
        idle_dc_hi = (int)((MSUtils.getByte(pageBuffer,6) + 0.0) * 1.0);
        ictlrpm1 = (double)((MSUtils.getByte(pageBuffer,7) + 0.0) * 10.0);
        ictlrpm2 = (double)((MSUtils.getByte(pageBuffer,8) + 0.0) * 10.0);
        Ideadbnd = (double)((MSUtils.getByte(pageBuffer,9) + 0.0) * 10.0);
        Idashdelay = (int)((MSUtils.getByte(pageBuffer,10) + 0.0) * 1.0);
        idlefreq = (int)((MSUtils.getByte(pageBuffer,11) + 0.0) * 1.0);
        idlestartclk = (int)((MSUtils.getByte(pageBuffer,12) + 0.0) * 1.0);
        idlePeriod2 = (int)((MSUtils.getByte(pageBuffer,13) + 0.0) * 1.0);
        irestorerpm = (double)((MSUtils.getByte(pageBuffer,14) + 0.0) * 10.0);
        idleclosedc = (int)((MSUtils.getByte(pageBuffer,15) + 0.0) * 1.0);
        pwmIdle = MSUtils.getBits(pageBuffer,16,0,0,0);
        pwmidlewhen = MSUtils.getBits(pageBuffer,16,1,2,0);
        IATCor = MSUtils.getBits(pageBuffer,16,3,3,0);
        IATCorTy = MSUtils.getBits(pageBuffer,16,4,4,0);
        if (CELSIUS)
        {
        fastIdleTemp = (int)((MSUtils.getByte(pageBuffer,17) + -72.0) * 0.555);
        slowIdleTemp = (int)((MSUtils.getByte(pageBuffer,18) + -72.0) * 0.555);
        }
        else
        {
        fastIdleTemp = (int)((MSUtils.getByte(pageBuffer,17) + -40.0) * 1.0);
        slowIdleTemp = (int)((MSUtils.getByte(pageBuffer,18) + -40.0) * 1.0);
        }
        fastIdleRPM = (double)((MSUtils.getByte(pageBuffer,19) + 0.0) * 10.0);
        slowIdleRPM = (double)((MSUtils.getByte(pageBuffer,20) + 0.0) * 10.0);
        idleThresh = (int)((MSUtils.getByte(pageBuffer,21) + 0.0) * 1.0);
        CrankPWT1 = (double)((MSUtils.getByte(pageBuffer,32) + 1.0) * 0.1);
        CrankPWT2 = (double)((MSUtils.getByte(pageBuffer,33) + 1.0) * 0.1);
        CrankPWT3 = (double)((MSUtils.getByte(pageBuffer,34) + 1.0) * 0.1);
        CrankPWT4 = (double)((MSUtils.getByte(pageBuffer,35) + 1.0) * 0.1);
        CrankPWT5 = (double)((MSUtils.getByte(pageBuffer,36) + 1.0) * 0.1);
        CrankPWT6 = (double)((MSUtils.getByte(pageBuffer,37) + 1.0) * 0.1);
        CrankPWT7 = (double)((MSUtils.getByte(pageBuffer,38) + 1.0) * 0.1);
        CrankPWT8 = (double)((MSUtils.getByte(pageBuffer,39) + 1.0) * 0.1);
        CrankPWT9 = (double)((MSUtils.getByte(pageBuffer,40) + 1.0) * 0.1);
        CrankPWT10 = (double)((MSUtils.getByte(pageBuffer,41) + 1.0) * 0.1);
        AlwaysPrime = MSUtils.getBits(pageBuffer,42,0,0,0);
        PrimeLate = MSUtils.getBits(pageBuffer,42,1,2,0);
        PrimeTBox = MSUtils.getBits(pageBuffer,42,3,3,0);
        crankpwip = MSUtils.getBits(pageBuffer,42,4,5,0);
        ExFuelCrank = MSUtils.getBits(pageBuffer,42,6,6,0);
        CrankRPM = (double)((MSUtils.getByte(pageBuffer,43) + 0.0) * 100.0);
        tpsflood = (int)((MSUtils.getByte(pageBuffer,44) + 0.0) * 1.0);
        PrimePulse = (double)((MSUtils.getByte(pageBuffer,45) + 0.0) * 0.1);
        ExtraFMult = (int)((MSUtils.getByte(pageBuffer,46) + 0.0) * 1.0);
        RPMRedLo = (double)((MSUtils.getByte(pageBuffer,54) + 0.0) * 100.0);
        RPMRedHi = (double)((MSUtils.getByte(pageBuffer,55) + 0.0) * 100.0);
        if (CELSIUS)
        {
        }
        else
        {
        }
        if (SPEED_DENSITY)
        {
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
        {
        }
        else
        {
        }
        }
        else if (AIR_FLOW_METER)
        {
        }
        else if (ALPHA_N)
        {
        }
        rotary2 = MSUtils.getBits(pageBuffer,111,0,0,0);
        fixedsplit = (double)((MSUtils.getByte(pageBuffer,112) + -28.4) * 0.352);
    }

}

