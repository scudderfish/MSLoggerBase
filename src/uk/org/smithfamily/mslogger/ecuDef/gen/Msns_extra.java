package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.*;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
/*
Fingerprint : d6eb0dad7f641d8d78c632f4315e2bc2
*/
@SuppressWarnings("unused")
public class Msns_extra extends Megasquirt
{
    public Msns_extra(Context c)
    {
        super(c);
        refreshFlags();
    }
    @Override
    public void refreshFlags()
    {
        INNOVATE_0_5_LINEAR = isSet("INNOVATE_0_5_LINEAR");
        DIYWB_NON_LINEAR = isSet("DIYWB_NON_LINEAR");
        NGK_AFX = isSet("NGK_AFX");
        CELSIUS = isSet("CELSIUS");
        MPXH6300A = isSet("MPXH6300A");
        DYNOJET_LINEAR = isSet("DYNOJET_LINEAR");
        ZEITRONIX_NON_LINEAR = isSet("ZEITRONIX_NON_LINEAR");
        TECHEDGE_LINEAR = isSet("TECHEDGE_LINEAR");
        TestCode = isSet("TestCode");
        WB_1_0_LINEAR = isSet("WB_1_0_LINEAR");
        NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
        FAST_WIDEBAND_O2 = isSet("FAST_WIDEBAND_O2");
        KPa = isSet("KPa");
        ALPHA_N = isSet("ALPHA_N");
        SPEED_DENSITY = isSet("SPEED_DENSITY");
        AEM_LINEAR = isSet("AEM_LINEAR");
        INNOVATE_1_2_LINEAR = isSet("INNOVATE_1_2_LINEAR");
        INNOVATE_LC1_DEFAULT = isSet("INNOVATE_LC1_DEFAULT");
        AIR_FLOW_METER = isSet("AIR_FLOW_METER");
        MPX4250 = isSet("MPX4250");
        AEM_NON_LINEAR = isSet("AEM_NON_LINEAR");
        WB_UNKNOWN = isSet("WB_UNKNOWN");
        MPXH6400A = isSet("MPXH6400A");
    }
    private Map<String,Double> fields = new HashMap<String,Double>();
    byte[] queryCommand=new byte[]{'S'};
    String signature="MSnS-extra format 027c *********";
    byte [] ochGetCommand = new byte[]{'R'};
    int ochBlockSize = 38;
//Flags
    private boolean INNOVATE_0_5_LINEAR;
    private boolean DIYWB_NON_LINEAR;
    private boolean NGK_AFX;
    private boolean CELSIUS;
    private boolean MPXH6300A;
    private boolean DYNOJET_LINEAR;
    private boolean ZEITRONIX_NON_LINEAR;
    private boolean TECHEDGE_LINEAR;
    private boolean TestCode;
    private boolean WB_1_0_LINEAR;
    private boolean NARROW_BAND_EGO;
    private boolean FAST_WIDEBAND_O2;
    private boolean KPa;
    private boolean ALPHA_N;
    private boolean SPEED_DENSITY;
    private boolean AEM_LINEAR;
    private boolean INNOVATE_1_2_LINEAR;
    private boolean INNOVATE_LC1_DEFAULT;
    private boolean AIR_FLOW_METER;
    private boolean MPX4250;
    private boolean AEM_NON_LINEAR;
    private boolean WB_UNKNOWN;
    private boolean MPXH6400A;
//Defaults
//Variables
    private double CCpHr;
    private double Cd;
    private int CltIatAng;
    private int CltIatAngle;
    private int CltIatDeg;
    private int Crr;
    private double DiffRa;
    private double GrTms;
    private int KnockAng;
    private int KnockAngle;
    private int KnockDeg;
    private double MAFVolts;
    private int Mass;
    private double OpenWidth;
    private double Radius;
    private double RpmHiRes;
    private int Speed;
    private double TargetAFR;
    private double TargetLambda;
    private int Timeroll;
    private double USgph;
    private double USmpg;
    private double XForce;
    private int XOffset;
    private double YForce;
    private int YOffset;
    private double accDecEnrich;
    private int accelEnrich;
    private double advSpark;
    private int advance;
    private double afr;
    private double afr2;
    private double afrTargetV;
    private int afrtarget;
    private int airCorrection;
    private int altDiv1;
    private int altDiv2;
    private int baroADC;
    private int baroCorrection;
    private double barometer;
    private int batADC;
    private double batteryVoltage;
    private double boost;
    private double boostVac;
    private int cltADC;
    private double coolant;
    private double cycleTime1;
    private double cycleTime2;
    private int deadValue;
    private double dutyCycle1;
    private double dutyCycle2;
    private double ego2Voltage;
    private int egoADC;
    private double egoCorrection;
    private int egoCorrection2;
    private double egoVoltage;
    private int egtADC;
    private double egttemp;
    private int engine;
    private int floodclear;
    private int fuelADC;
    private int fuelCC;
    private double fuelpress;
    private double fuelvolt;
    private int gammaEnrich;
    private double gph;
    private int iTime;
    private int iTimeX;
    private int iTimefull;
    private int idleDC;
    private double lambda;
    private double lambda2;
    private double map;
    private int mapADC;
    private double mapDOT;
    private double mapDOTTY;
    private double mat;
    private int matADC;
    private double mpg;
    private double mph;
    private double mphTemp;
    private int nSquirts1;
    private int nSquirts2;
    private int porta;
    private int portb;
    private int portc;
    private int portd;
    private double pulseWidth;
    private double pulseWidth1;
    private double pulseWidth2;
    private int rpm;
    private int rpm100;
    private int secl;
    private int squirt;
    private double squirtmul;
    private double squirts;
    private int stackL;
    private double test;
    private int test2;
    private double throttle;
    private double time;
    private int tpsADC;
    private double tpsDOT;
    private double tpsDOTTY;
    private int tpsLast;
    private double vacuum;
    private double veCurr;
    private int veCurr1;
    private int veCurr2;
    private int warmupEnrich;
    private int waterIlog;

//Constants
    private int whlsimcnt;
    private int fastIdleTemp;
    private int WaterIIat;
    private int TPSRPMTarg;
    private int DecEnrichment;
    private int out2src;
    private double injPwmT2;
    private double injPwmT1;
    private double RPMmS4;
    private double RPMmS3;
    private double RPMmS2;
    private double RPMmS1;
    private int DecelKPa;
    private double outaoffv;
    private int edismulti;
    private int AlwaysPrime;
    private int KnockKpa;
    private int outaoffs;
    private int baroCorr1;
    private double Nos2delay;
    private int baroCorr2;
    private int egoCount2;
    private int egoCount1;
    private double NosPWHi;
    private int LachTps;
    private double RPMRedLo;
    private int OvrBCutType;
    private int fastIdleT2;
    private int Out2UpLim;
    private int egoDelta1;
    private int egoDelta2;
    private int fastIdleT1;
    private int rpmk2;
    private int rpmk1;
    private int cltType2;
    private int cltType1;
    private double RPM2S;
    private int idlefreq;
    private int trig2ret;
    private double delay2rpm;
    private double irestorerpm;
    private double VEFixValue;
    private double LC_f_limangle;
    private int outcoffs;
    private double outcoffv;
    private int NosHiKpa;
    private int numteeth;
    private double TractVSSMax;
    private int out4FAN;
    private double RPM1S;
    private int SparkCutBNum;
    private int PrimeLate;
    private double VE3Delay;
    private int shiftUse;
    private int KPaOn;
    private double LC_f_slim;
    private int UseVE3;
    private int TPSTar;
    private double outdoffv;
    private double fastIdleRPM;
    private int outdoffs;
    private int hrd_disable;
    private double outeoffv;
    private int slowIdleTemp;
    private double OvrRunRpm;
    private int OvrRunTps;
    private int efanofftemp;
    private int outeoffs;
    private double RPM4S;
    private int out4lim;
    private int tdePct4;
    private double dwellrun;
    private int inj2cr;
    private int nInjectors2;
    private int nInjectors1;
    private int divider1;
    private int divider2;
    private int IdleAdvTPS;
    private int out1Hysis;
    private double NosPWLo;
    private double RPM3S;
    private int trig3ret;
    private double SoftRevLim;
    private int AfrTar;
    private double TractRet2;
    private double TractRet1;
    private double TractRet4;
    private int ScaleFac;
    private double TractRet3;
    private int TractNOS;
    private int engineType2;
    private int fidleUse;
    private double idlekickrpm;
    private int OvrRunKpa;
    private int engineType1;
    private double CrankAng;
    private int trig2;
    private int trig3;
    private double SoftLimMax;
    private int trig1;
    private int trig6;
    private int trig4;
    private int trig5;
    private int OvrRunTimr;
    private double MaxIatAdv;
    private int whlsim;
    private double taeTime4;
    private int tpsflood;
    private int hei7;
    private int twoStroke2;
    private double fixedsplit;
    private int twoStroke1;
    private int edis;
    private int IATBoost;
    private int egoLimit2;
    private int egoLimit1;
    private int matType1;
    private int matType2;
    private int tachconf;
    private int ikpamin2;
    private int ikpamin1;
    private double TractSlip;
    private double LachRpm;
    private int led19Use;
    private int spare3_177;
    private int spare3_178;
    private double mapThresh4;
    private int spare3_179;
    private double KPA300Corr;
    private int ignmode;
    private int idleclosed;
    private int TrigCrank;
    private int spare3_180;
    private int trig2fall;
    private int spare3_181;
    private double IdleAdvRPM;
    private int spare3_182;
    private int spare3_183;
    private int VEFixASE;
    private int spare3_184;
    private int wd_2trig;
    private double RPMThresh;
    private int ASETable8;
    private int ASETable7;
    private int ASEFixDe;
    private int IgHold;
    private int ASETable9;
    private double NosAngle;
    private int ASETable4;
    private int ASETable3;
    private int ASETable6;
    private double out3Timer;
    private int ASETable5;
    private int ASETable2;
    private int CltAdv;
    private int ASETable1;
    private int IgAdvTemp;
    private int HCutLCSpark;
    private double Nos2PWHi;
    private double ST2Delay;
    private int out3lim;
    private int ExFuelCrank;
    private int spkfop;
    private int idlePeriod2;
    private int AfrTar2;
    private int dualdizzy;
    private int AlphBaroCor;
    private int led18_2Use;
    private double N2Odel_launch;
    private int egoIgnCount;
    private int led18Use;
    private int HCutLCType;
    private double idleThresh;
    private int out1src;
    private int spkeop;
    private int taeColdM2;
    private int taeColdM4;
    private double egoSwitch2;
    private int IgInv;
    private double egoSwitch1;
    private int IatAdvKpa;
    private int Trig_plus;
    private double SoftLimRetard;
    private int AccDecEnrich;
    private int WaterInj;
    private int STTable2On;
    private int ASEFixTim;
    private double TractVSSMin;
    private int idlekickdc;
    private int rotary2;
    private double KnockSb;
    private double ShiftLower;
    private int TPSAFRPoint;
    private int tachconfdiv;
    private int KPaTarg60;
    private int ConsBarCor;
    private double BarroLow;
    private double LC_flatlim;
    private double NosRpm;
    private double slowIdleRPM;
    private double DecayAcPoint;
    private int crankpwip;
    private double RPMRedHi;
    private int DecayAccel;
    private double LC_flatsel;
    private int pwmidlewhen;
    private int OLoopEgo;
    private double HardRevLim;
    private int TwoLambda;
    private int NosLagSystem;
    private int bcUpdateRate;
    private double KnockRpm;
    private int OvrRunC;
    private int TractSC4;
    private int out4src;
    private double Nos2PWLo;
    private int trig5ret;
    private int KPaTarg40;
    private int TractSC2;
    private int TractSC3;
    private int ASEFixTemp;
    private int TractSC1;
    private int dwellcont;
    private int pwmIdle;
    private int IgAdvDeg;
    private double Nos2Angle;
    private int injPwmP2;
    private double IdleAdv;
    private int injPwmP1;
    private int TargetB1;
    private int EgoLim2;
    private int TargetB2;
    private int out1lim;
    private int TargetB3;
    private int TargetB4;
    private double FixAng;
    private int TpsOn;
    private double KnockBoost;
    private int idlekpaval2;
    private int idlekpaval1;
    private double ShiftUpper;
    private double OvrBKpa;
    private int neonCrank;
    private int IATCor6;
    private int KnockOn;
    private int IATCor7;
    private double CrankPWT9;
    private int IATCor4;
    private double CrankPWT8;
    private int IATCor5;
    private int out3sparkd;
    private double CrankPWT7;
    private int IATCor2;
    private double CrankPWT6;
    private int IATCor3;
    private double CrankPWT5;
    private double CrankPWT4;
    private int IATCor1;
    private double CrankPWT3;
    private double CrankPWT2;
    private int AFMUse;
    private double CrankPWT1;
    private double KnockRpmL;
    private int oddfire;
    private int AccelMapASE;
    private int KPA300Sens;
    private double PrimePulse;
    private int mapType1;
    private double dwellcrank;
    private int egoType1;
    private int egoType2;
    private int mapType2;
    private int IATCorTy;
    private int KPaTarg10;
    private int ASETable10;
    private int inj2g;
    private double dGain;
    private double KnockRet;
    private double LachDeg;
    private int NosTps;
    private int inj2t;
    private int DtNos;
    private int idle_dc_lo;
    private int IgRetDeg;
    private int efanontemp;
    private int idleperiod;
    private int outboffs;
    private int StgDelta;
    private double outboffv;
    private int RPMBAsedAE;
    private int NosClt;
    private int EgoLimKPa;
    private int TractCyc3;
    private int TractCyc4;
    private int TractCyc1;
    private int TractCyc2;
    private double reqFuel2;
    private int PrimeTBox;
    private double reqFuel1;
    private double pGain;
    private int nitrousUse;
    private double TractSlipH;
    private double egoRPM1;
    private double TractScale;
    private int wheel_old;
    private int trig1ret;
    private double battFac2;
    private double RPMAE1;
    private double battFac1;
    private double RPMAE2;
    private double BoostMaxKn;
    private double N2Odel_flat;
    private int IgRetTemp;
    private double egoRPM2;
    private int IStagedMode;
    private double TrigAng;
    private int StgTrans;
    private double StartWIKPa;
    private double RPMAE4;
    private double RPMAE3;
    private int miss2nd;
    private int injType1;
    private int injType2;
    private int OvrRunClt;
    private int KnockTim;
    private int rotaryFDign;
    private int x2use;
    private int KPaDotBoost;
    private int DecelBoost;
    private int IATCor;
    private int HCutType;
    private int bc_max_diff;
    private double taeColdA4;
    private int TractMode;
    private int IATCTemp2;
    private int nCylinders2;
    private int IATCTemp1;
    private int nCylinders1;
    private int IATCTemp4;
    private int IATCTemp3;
    private int IATCTemp6;
    private int IATCTemp5;
    private int IATCTemp7;
    private double WaterIRpm;
    private double primePulse2;
    private int idledashdc;
    private int onetwo;
    private double N2Oholdon;
    private double BarroHi;
    private int out2lim;
    private int NosLowKpa;
    private double Trim;
    private int InvOut2;
    private int InvOut1;
    private int Out1UpLim;
    private int aseIgnCount;
    private int egoTemp2;
    private int egoTemp1;
    private int BoostDir;
    private int TractionOn;
    private int IATBoostSt;
    private int Out3_Out1;
    private double outfoffv;
    private int outfoffs;
    private double LaunchLim;
    private int KPaTarg100;
    private int SparkCutBase;
    private int ASETime;
    private double Nos2Rpm;
    private int toy_dli;
    private int dwellduty50;
    private double mindischg;
    private int mapProportion4;
    private int trig4ret;
    private int trig6ret;
    private double NosRpmMax;
    private double KPA300BARO;
    private int out3src;
    private int BooTbl2Use;
    private int inj1g;
    private int launchUse;
    private int tfi;
    private int freqDiv;
    private int TPSBooIAT;
    private int ExtraFMult;
    private int boostUse;
    private int wheelon;
    private int mapSensor1;
    private int TractCySec;
    private double edismultirpm;
    private int led17Use;
    private double CrankPWT10;
    private int HCutSpark;
    private int msnsCyl;
    private int algorithm2;
    private int out2Hysis;
    private int algorithm1;
    private double injOpen1;
    private double injOpen2;
    private double KnockMax;
    private int KpaTPSTar;
    private double CrankRPM;
    private int hybridAN;
    private int taeIgnCount;
    private int VLaunch;
    private int idle_dc_hi;
    private int alternate2;
    private double tpsThresh4;
    private double KnockAdv;
    private int idlemindc;
    private double Nos2RpmMax;
    private int alternate1;
    private int AirCorAFM;


    private String[] defaultGauges = {
        "RpmHiResGauge",
        "cltGauge",
        "pulseWidth1Gauge",
        "dutyCycle1Gauge",
        "mapGauge",
        "matGauge",
        "afrGauge",
        "advanceGauge"
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
        barometer = (table(baroADC, "kpafactor4250.inc"));
        map = ((mapADC + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        barometer = (table(baroADC, "kpafactor4250.inc"));
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
        veCurr = (veCurr1);
        pulseWidth = (pulseWidth1);
        YOffset = (182);
        XOffset = (187);
        YForce = ((egtADC > YOffset + 1 ) ?  (egtADC  - YOffset) * 0.04464 : egtADC < YOffset - 1 ? (egtADC  - YOffset) * 0.04464 : 0);
        XForce = ((fuelADC > XOffset + 1 ) ?  (fuelADC  - XOffset) * 0.04464 : fuelADC < XOffset - 1 ? (fuelADC  - XOffset) * 0.04464 : 0);
        test = (mapDOT);
        test2 = (egtADC);
        iTimefull = ((iTimeX*65536)+ iTime);
        RpmHiRes = ((iTimefull > 0 ) ?  (60000000 *(2.0-twoStroke1)) / (iTimefull * nCylinders1) : 0);
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
        
        if (TestCode)
        {
        b.append("Water Inj").append("\t");
        b.append("TimeRoll").append("\t");
        }
        else
        {
        b.append("Gve2").append("\t");
        b.append("PW2").append("\t");
        }
        b.append("DutyCycle1").append("\t");
        b.append("DutyCycle2").append("\t");
        b.append("Spark Angle").append("\t");
        b.append("EGT").append("\t");
        b.append("Knock").append("\t");
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
        b.append(veCurr1).append("\t");
        b.append(round(pulseWidth1)).append("\t");
        
        if (TestCode)
        {
        b.append(waterIlog).append("\t");
        b.append(Timeroll).append("\t");
        }
        else
        {
        b.append(veCurr2).append("\t");
        b.append(round(pulseWidth2)).append("\t");
        }
        b.append(dutyCycle1).append("\t");
        b.append(dutyCycle2).append("\t");
        b.append(advSpark).append("\t");
        b.append(egttemp).append("\t");
        b.append(KnockDeg).append("\t");
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("baroADCGauge","barometer",barometer,"Barometer ADC","",0,255,-1,-1,256,256,0,0,45));
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
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge","deadValue",deadValue,"---","",0,1,-1,-1,2,2,0,0,45));
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
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("advanceGauge","advSpark",advSpark,"Spark Advance","deg BTDC",50,-10,0,0,35,45,0,0,45));
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
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambdaGauge2","lambda2",lambda2,"Lambda2","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afrGauge2","afr2",afr2,"Air:Fuel Ratio2","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vacuumGauge","vacuum",vacuum,"Engine Vacuum","in-HG",0,30,0,0,30,30,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("boostgauge","boost",boost,"Engine Boost","PSIG",0,20,0,0,15,20,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("VacBooGauge","boostVac",boostVac,"Engine Vac Boost","in-HG/PSIG",-30,30,-30,-30,30,30,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("TargetAFRGauge","TargetAFR",TargetAFR,"Target AFR","AFR",10,19.4,0,0,20,20,2,2,45));
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
        pageBuffer = loadPage(1,0,200,new byte[]{80,1},new byte[]{86});
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
        injType1 = MSUtils.getBits(pageBuffer,182,3,3,0);
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
        pageBuffer = loadPage(2,0,200,new byte[]{80,2},new byte[]{86});
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
        injType2 = MSUtils.getBits(pageBuffer,182,3,3,0);
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
        taeColdM2 = (int)((MSUtils.getByte(pageBuffer,188) + 0.0) * 1.0);
        egoCount2 = (int)((MSUtils.getByte(pageBuffer,190) + 0.0) * 1.0);
        TwoLambda = MSUtils.getBits(pageBuffer,191,0,0,0);
        pageBuffer = loadPage(3,0,200,new byte[]{80,3},new byte[]{86});
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
        spare3_177 = (int)((MSUtils.getByte(pageBuffer,177) + 0.0) * 1.0);
        spare3_178 = (int)((MSUtils.getByte(pageBuffer,178) + 0.0) * 1.0);
        spare3_179 = (int)((MSUtils.getByte(pageBuffer,179) + 0.0) * 1.0);
        spare3_180 = (int)((MSUtils.getByte(pageBuffer,180) + 0.0) * 1.0);
        spare3_181 = (int)((MSUtils.getByte(pageBuffer,181) + 0.0) * 1.0);
        spare3_182 = (int)((MSUtils.getByte(pageBuffer,182) + 0.0) * 1.0);
        spare3_183 = (int)((MSUtils.getByte(pageBuffer,183) + 0.0) * 1.0);
        spare3_184 = (int)((MSUtils.getByte(pageBuffer,184) + 0.0) * 1.0);
        pageBuffer = loadPage(4,0,200,new byte[]{80,0},new byte[]{86});
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
        egoIgnCount = MSUtils.getBits(pageBuffer,11,1,1,0);
        whlsim = MSUtils.getBits(pageBuffer,11,2,2,0);
        taeIgnCount = MSUtils.getBits(pageBuffer,11,3,3,0);
        rotaryFDign = MSUtils.getBits(pageBuffer,11,4,4,0);
        hybridAN = MSUtils.getBits(pageBuffer,11,5,5,0);
        inj2cr = MSUtils.getBits(pageBuffer,11,6,6,0);
        boostUse = MSUtils.getBits(pageBuffer,12,0,0,0);
        shiftUse = MSUtils.getBits(pageBuffer,12,1,1,0);
        launchUse = MSUtils.getBits(pageBuffer,12,2,2,0);
        pwmIdle = MSUtils.getBits(pageBuffer,12,3,3,0);
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
        trig2fall = MSUtils.getBits(pageBuffer,33,0,0,0);
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
        if (CELSIUS)
        {
        fastIdleTemp = (int)((MSUtils.getByte(pageBuffer,34) + -72.0) * 0.555);
        slowIdleTemp = (int)((MSUtils.getByte(pageBuffer,35) + -72.0) * 0.555);
        }
        else
        {
        fastIdleTemp = (int)((MSUtils.getByte(pageBuffer,34) + -40.0) * 1.0);
        slowIdleTemp = (int)((MSUtils.getByte(pageBuffer,35) + -40.0) * 1.0);
        }
        fastIdleRPM = (double)((MSUtils.getByte(pageBuffer,36) + 0.0) * 10.0);
        slowIdleRPM = (double)((MSUtils.getByte(pageBuffer,37) + 0.0) * 10.0);
        idleThresh = (double)((MSUtils.getByte(pageBuffer,38) + -26.0) * 0.502);
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
        N2Odel_launch = (double)((MSUtils.getByte(pageBuffer,172) + 0.0) * 0.01);
        N2Odel_flat = (double)((MSUtils.getByte(pageBuffer,173) + 0.0) * 0.01);
        N2Oholdon = (double)((MSUtils.getByte(pageBuffer,174) + 0.0) * 0.01);
        numteeth = (int)((MSUtils.getByte(pageBuffer,90) + 0.0) * 1.0);
        trig1 = (int)((MSUtils.getByte(pageBuffer,25) + 0.0) * 1.0);
        trig2 = (int)((MSUtils.getByte(pageBuffer,26) + 0.0) * 1.0);
        trig3 = (int)((MSUtils.getByte(pageBuffer,27) + 0.0) * 1.0);
        trig4 = (int)((MSUtils.getByte(pageBuffer,28) + 0.0) * 1.0);
        trig1ret = (int)((MSUtils.getByte(pageBuffer,29) + 0.0) * 1.0);
        trig2ret = (int)((MSUtils.getByte(pageBuffer,30) + 0.0) * 1.0);
        trig3ret = (int)((MSUtils.getByte(pageBuffer,31) + 0.0) * 1.0);
        trig4ret = (int)((MSUtils.getByte(pageBuffer,32) + 0.0) * 1.0);
        trig5 = (int)((MSUtils.getByte(pageBuffer,160) + 0.0) * 1.0);
        trig6 = (int)((MSUtils.getByte(pageBuffer,161) + 0.0) * 1.0);
        trig5ret = (int)((MSUtils.getByte(pageBuffer,162) + 0.0) * 1.0);
        trig6ret = (int)((MSUtils.getByte(pageBuffer,163) + 0.0) * 1.0);
        miss2nd = MSUtils.getBits(pageBuffer,66,0,0,0);
        dualdizzy = MSUtils.getBits(pageBuffer,92,4,4,0);
        OvrRunC = MSUtils.getBits(pageBuffer,66,6,6,0);
        OvrRunRpm = (double)((MSUtils.getByte(pageBuffer,71) + 0.0) * 100.0);
        if (CELSIUS)
        {
        OvrRunClt = (int)((MSUtils.getByte(pageBuffer,197) + -72.0) * 0.555);
        }
        else
        {
        OvrRunClt = (int)((MSUtils.getByte(pageBuffer,197) + -40.0) * 1.0);
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
        DecEnrichment = MSUtils.getBits(pageBuffer,116,5,5,0);
        KPaDotBoost = MSUtils.getBits(pageBuffer,66,4,4,0);
        DecayAccel = MSUtils.getBits(pageBuffer,116,6,6,0);
        DecayAcPoint = (double)((MSUtils.getByte(pageBuffer,198) + 0.0) * 0.1);
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
        AlphBaroCor = MSUtils.getBits(pageBuffer,199,3,3,0);
        AFMUse = MSUtils.getBits(pageBuffer,199,5,5,0);
        AirCorAFM = MSUtils.getBits(pageBuffer,199,6,6,0);
        ConsBarCor = MSUtils.getBits(pageBuffer,199,7,7,0);
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
        hrd_disable = MSUtils.getBits(pageBuffer,92,2,2,0);
        wheel_old = MSUtils.getBits(pageBuffer,92,3,3,0);
        dwellcont = MSUtils.getBits(pageBuffer,109,1,1,0);
        dwellcrank = (double)((MSUtils.getByte(pageBuffer,110) + 0.0) * 0.1);
        dwellrun = (double)((MSUtils.getByte(pageBuffer,111) + 0.0) * 0.1);
        mindischg = (double)((MSUtils.getByte(pageBuffer,143) + 0.0) * 0.1);
        idle_dc_lo = (int)((MSUtils.getByte(pageBuffer,122) + 0.0) * 1.0);
        idle_dc_hi = (int)((MSUtils.getByte(pageBuffer,149) + 0.0) * 1.0);
        idleperiod = (int)((MSUtils.getByte(pageBuffer,144) + 0.0) * 1.0);
        idlekickrpm = (double)((MSUtils.getByte(pageBuffer,145) + 0.0) * 100.0);
        idlekickdc = (int)((MSUtils.getByte(pageBuffer,146) + 0.0) * 1.0);
        idledashdc = (int)((MSUtils.getByte(pageBuffer,147) + 0.0) * 1.0);
        idlemindc = (int)((MSUtils.getByte(pageBuffer,148) + 0.0) * 1.0);
        idlekpaval1 = (int)((MSUtils.getByte(pageBuffer,150) + 0.0) * 1.0);
        idlekpaval2 = (int)((MSUtils.getByte(pageBuffer,151) + 0.0) * 1.0);
        ikpamin1 = (int)((MSUtils.getByte(pageBuffer,152) + 0.0) * 1.0);
        ikpamin2 = (int)((MSUtils.getByte(pageBuffer,153) + 0.0) * 1.0);
        idlefreq = (int)((MSUtils.getByte(pageBuffer,154) + 0.0) * 1.0);
        delay2rpm = (double)((MSUtils.getByte(pageBuffer,155) + 0.0) * 100.0);
        idlePeriod2 = (int)((MSUtils.getByte(pageBuffer,156) + 0.0) * 1.0);
        irestorerpm = (double)((MSUtils.getByte(pageBuffer,157) + 0.0) * 100.0);
        idleclosed = (int)((MSUtils.getByte(pageBuffer,158) + 0.0) * 1.0);
        tachconf = MSUtils.getBits(pageBuffer,159,0,2,0);
        tachconfdiv = MSUtils.getBits(pageBuffer,159,7,7,0);
        pwmidlewhen = MSUtils.getBits(pageBuffer,109,6,7,0);
        if (MPXH6300A)
        {
        bc_max_diff = (int)((MSUtils.getByte(pageBuffer,125) + 1.53) * 1.213675);
        DecelKPa = (int)((MSUtils.getByte(pageBuffer,129) + 1.53) * 1.213675);
        }
        else if (MPXH6400A)
        {
        bc_max_diff = (int)((MSUtils.getByte(pageBuffer,125) + 2.147) * 1.6197783);
        DecelKPa = (int)((MSUtils.getByte(pageBuffer,129) + 2.147) * 1.6197783);
        }
        else
        {
        bc_max_diff = (int)((MSUtils.getByte(pageBuffer,125) + 0.0) * 1.0);
        DecelKPa = (int)((MSUtils.getByte(pageBuffer,129) + 0.0) * 1.0);
        }
        DecelBoost = MSUtils.getBits(pageBuffer,92,7,7,0);
        KPA300Sens = MSUtils.getBits(pageBuffer,116,0,1,0);
        KPA300BARO = (double)((MSUtils.getByte(pageBuffer,131) + 0.0) * 0.39216);
        KPA300Corr = (double)((MSUtils.getByte(pageBuffer,175) + 0.0) * 0.39216);
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
        taeColdA4 = (double)((MSUtils.getByte(pageBuffer,192) + 0.0) * 0.1);
        tpsThresh4 = (double)((MSUtils.getByte(pageBuffer,193) + 0.0) * 0.1953125);
        taeTime4 = (double)((MSUtils.getByte(pageBuffer,194) + 0.0) * 0.1);
        tdePct4 = (int)((MSUtils.getByte(pageBuffer,195) + 0.0) * 1.0);
        taeColdM4 = (int)((MSUtils.getByte(pageBuffer,196) + 0.0) * 1.0);
        mapProportion4 = MSUtils.getBits(pageBuffer,66,7,7,0);
        AccelMapASE = MSUtils.getBits(pageBuffer,199,2,2,0);
        RPMBAsedAE = MSUtils.getBits(pageBuffer,199,4,4,0);
        RPMAE1 = (double)((MSUtils.getByte(pageBuffer,164) + 0.0) * 100.0);
        RPMAE2 = (double)((MSUtils.getByte(pageBuffer,165) + 0.0) * 100.0);
        RPMAE3 = (double)((MSUtils.getByte(pageBuffer,166) + 0.0) * 100.0);
        RPMAE4 = (double)((MSUtils.getByte(pageBuffer,167) + 0.0) * 100.0);
        RPMmS1 = (double)((MSUtils.getByte(pageBuffer,168) + 0.0) * 0.1);
        RPMmS2 = (double)((MSUtils.getByte(pageBuffer,169) + 0.0) * 0.1);
        RPMmS3 = (double)((MSUtils.getByte(pageBuffer,170) + 0.0) * 0.1);
        RPMmS4 = (double)((MSUtils.getByte(pageBuffer,171) + 0.0) * 0.1);
        pageBuffer = loadPage(5,0,200,new byte[]{80,4},new byte[]{86});
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
        CrankPWT1 = (double)((MSUtils.getByte(pageBuffer,183) + 1.0) * 0.1);
        CrankPWT2 = (double)((MSUtils.getByte(pageBuffer,184) + 1.0) * 0.1);
        CrankPWT3 = (double)((MSUtils.getByte(pageBuffer,185) + 1.0) * 0.1);
        CrankPWT4 = (double)((MSUtils.getByte(pageBuffer,186) + 1.0) * 0.1);
        CrankPWT5 = (double)((MSUtils.getByte(pageBuffer,187) + 1.0) * 0.1);
        CrankPWT6 = (double)((MSUtils.getByte(pageBuffer,188) + 1.0) * 0.1);
        CrankPWT7 = (double)((MSUtils.getByte(pageBuffer,189) + 1.0) * 0.1);
        CrankPWT8 = (double)((MSUtils.getByte(pageBuffer,190) + 1.0) * 0.1);
        CrankPWT9 = (double)((MSUtils.getByte(pageBuffer,191) + 1.0) * 0.1);
        CrankPWT10 = (double)((MSUtils.getByte(pageBuffer,192) + 1.0) * 0.1);
        AlwaysPrime = MSUtils.getBits(pageBuffer,193,0,0,0);
        PrimeLate = MSUtils.getBits(pageBuffer,193,1,2,0);
        PrimeTBox = MSUtils.getBits(pageBuffer,193,3,3,0);
        crankpwip = MSUtils.getBits(pageBuffer,193,4,5,0);
        ExFuelCrank = MSUtils.getBits(pageBuffer,193,6,6,0);
        CrankRPM = (double)((MSUtils.getByte(pageBuffer,194) + 0.0) * 100.0);
        tpsflood = (int)((MSUtils.getByte(pageBuffer,195) + 0.0) * 1.0);
        PrimePulse = (double)((MSUtils.getByte(pageBuffer,196) + 0.0) * 0.1);
        ExtraFMult = (int)((MSUtils.getByte(pageBuffer,197) + 0.0) * 1.0);
        pageBuffer = loadPage(6,0,200,new byte[]{80,5},new byte[]{86});
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
        IATCor = MSUtils.getBits(pageBuffer,179,3,3,0);
        IATCorTy = MSUtils.getBits(pageBuffer,179,4,4,0);
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
        IATCor1 = (int)((MSUtils.getByte(pageBuffer,183) + 0.0) * 1.0);
        IATCor2 = (int)((MSUtils.getByte(pageBuffer,184) + 0.0) * 1.0);
        IATCor3 = (int)((MSUtils.getByte(pageBuffer,185) + 0.0) * 1.0);
        IATCor4 = (int)((MSUtils.getByte(pageBuffer,186) + 0.0) * 1.0);
        IATCor5 = (int)((MSUtils.getByte(pageBuffer,187) + 0.0) * 1.0);
        IATCor6 = (int)((MSUtils.getByte(pageBuffer,188) + 0.0) * 1.0);
        IATCor7 = (int)((MSUtils.getByte(pageBuffer,189) + 0.0) * 1.0);
        RPMRedHi = (double)((MSUtils.getByte(pageBuffer,190) + 0.0) * 100.0);
        RPMRedLo = (double)((MSUtils.getByte(pageBuffer,191) + 0.0) * 100.0);
        if (CELSIUS)
        {
        IATCTemp1 = (int)((MSUtils.getByte(pageBuffer,192) + -72.0) * 0.555);
        IATCTemp2 = (int)((MSUtils.getByte(pageBuffer,193) + -72.0) * 0.555);
        IATCTemp3 = (int)((MSUtils.getByte(pageBuffer,194) + -72.0) * 0.555);
        IATCTemp4 = (int)((MSUtils.getByte(pageBuffer,195) + -72.0) * 0.555);
        IATCTemp5 = (int)((MSUtils.getByte(pageBuffer,196) + -72.0) * 0.555);
        IATCTemp6 = (int)((MSUtils.getByte(pageBuffer,197) + -72.0) * 0.555);
        IATCTemp7 = (int)((MSUtils.getByte(pageBuffer,198) + -72.0) * 0.555);
        }
        else
        {
        IATCTemp1 = (int)((MSUtils.getByte(pageBuffer,192) + -40.0) * 1.0);
        IATCTemp2 = (int)((MSUtils.getByte(pageBuffer,193) + -40.0) * 1.0);
        IATCTemp3 = (int)((MSUtils.getByte(pageBuffer,194) + -40.0) * 1.0);
        IATCTemp4 = (int)((MSUtils.getByte(pageBuffer,195) + -40.0) * 1.0);
        IATCTemp5 = (int)((MSUtils.getByte(pageBuffer,196) + -40.0) * 1.0);
        IATCTemp6 = (int)((MSUtils.getByte(pageBuffer,197) + -40.0) * 1.0);
        IATCTemp7 = (int)((MSUtils.getByte(pageBuffer,198) + -40.0) * 1.0);
        }
        pageBuffer = loadPage(7,0,200,new byte[]{80,6},new byte[]{86});
        if (NARROW_BAND_EGO)
        {
        }
        else if (WB_1_0_LINEAR)
        {
        }
        else if (WB_UNKNOWN)
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
        pageBuffer = loadPage(8,0,200,new byte[]{80,7},new byte[]{86});
        if (MPXH6300A)
        {
        }
        else if (MPXH6400A)
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
        rotary2 = MSUtils.getBits(pageBuffer,192,0,0,0);
        fixedsplit = (double)((MSUtils.getByte(pageBuffer,193) + -28.4) * 0.352);
    }
    @Override
    public DataPacket getDataPacket()
    {
        fields.put("CCpHr",(double)CCpHr);
        fields.put("Cd",(double)Cd);
        fields.put("CltIatAng",(double)CltIatAng);
        fields.put("CltIatAngle",(double)CltIatAngle);
        fields.put("CltIatDeg",(double)CltIatDeg);
        fields.put("Crr",(double)Crr);
        fields.put("DiffRa",(double)DiffRa);
        fields.put("GrTms",(double)GrTms);
        fields.put("KnockAng",(double)KnockAng);
        fields.put("KnockAngle",(double)KnockAngle);
        fields.put("KnockDeg",(double)KnockDeg);
        fields.put("MAFVolts",(double)MAFVolts);
        fields.put("Mass",(double)Mass);
        fields.put("OpenWidth",(double)OpenWidth);
        fields.put("Radius",(double)Radius);
        fields.put("RpmHiRes",(double)RpmHiRes);
        fields.put("Speed",(double)Speed);
        fields.put("TargetAFR",(double)TargetAFR);
        fields.put("TargetLambda",(double)TargetLambda);
        fields.put("Timeroll",(double)Timeroll);
        fields.put("USgph",(double)USgph);
        fields.put("USmpg",(double)USmpg);
        fields.put("XForce",(double)XForce);
        fields.put("XOffset",(double)XOffset);
        fields.put("YForce",(double)YForce);
        fields.put("YOffset",(double)YOffset);
        fields.put("accDecEnrich",(double)accDecEnrich);
        fields.put("accelEnrich",(double)accelEnrich);
        fields.put("advSpark",(double)advSpark);
        fields.put("advance",(double)advance);
        fields.put("afr",(double)afr);
        fields.put("afr2",(double)afr2);
        fields.put("afrTargetV",(double)afrTargetV);
        fields.put("afrtarget",(double)afrtarget);
        fields.put("airCorrection",(double)airCorrection);
        fields.put("altDiv1",(double)altDiv1);
        fields.put("altDiv2",(double)altDiv2);
        fields.put("baroADC",(double)baroADC);
        fields.put("baroCorrection",(double)baroCorrection);
        fields.put("barometer",(double)barometer);
        fields.put("batADC",(double)batADC);
        fields.put("batteryVoltage",(double)batteryVoltage);
        fields.put("boost",(double)boost);
        fields.put("boostVac",(double)boostVac);
        fields.put("cltADC",(double)cltADC);
        fields.put("coolant",(double)coolant);
        fields.put("cycleTime1",(double)cycleTime1);
        fields.put("cycleTime2",(double)cycleTime2);
        fields.put("deadValue",(double)deadValue);
        fields.put("dutyCycle1",(double)dutyCycle1);
        fields.put("dutyCycle2",(double)dutyCycle2);
        fields.put("ego2Voltage",(double)ego2Voltage);
        fields.put("egoADC",(double)egoADC);
        fields.put("egoCorrection",(double)egoCorrection);
        fields.put("egoCorrection2",(double)egoCorrection2);
        fields.put("egoVoltage",(double)egoVoltage);
        fields.put("egtADC",(double)egtADC);
        fields.put("egttemp",(double)egttemp);
        fields.put("engine",(double)engine);
        fields.put("floodclear",(double)floodclear);
        fields.put("fuelADC",(double)fuelADC);
        fields.put("fuelCC",(double)fuelCC);
        fields.put("fuelpress",(double)fuelpress);
        fields.put("fuelvolt",(double)fuelvolt);
        fields.put("gammaEnrich",(double)gammaEnrich);
        fields.put("gph",(double)gph);
        fields.put("iTime",(double)iTime);
        fields.put("iTimeX",(double)iTimeX);
        fields.put("iTimefull",(double)iTimefull);
        fields.put("idleDC",(double)idleDC);
        fields.put("lambda",(double)lambda);
        fields.put("lambda2",(double)lambda2);
        fields.put("map",(double)map);
        fields.put("mapADC",(double)mapADC);
        fields.put("mapDOT",(double)mapDOT);
        fields.put("mapDOTTY",(double)mapDOTTY);
        fields.put("mat",(double)mat);
        fields.put("matADC",(double)matADC);
        fields.put("mpg",(double)mpg);
        fields.put("mph",(double)mph);
        fields.put("mphTemp",(double)mphTemp);
        fields.put("nSquirts1",(double)nSquirts1);
        fields.put("nSquirts2",(double)nSquirts2);
        fields.put("porta",(double)porta);
        fields.put("portb",(double)portb);
        fields.put("portc",(double)portc);
        fields.put("portd",(double)portd);
        fields.put("pulseWidth",(double)pulseWidth);
        fields.put("pulseWidth1",(double)pulseWidth1);
        fields.put("pulseWidth2",(double)pulseWidth2);
        fields.put("rpm",(double)rpm);
        fields.put("rpm100",(double)rpm100);
        fields.put("secl",(double)secl);
        fields.put("squirt",(double)squirt);
        fields.put("squirtmul",(double)squirtmul);
        fields.put("squirts",(double)squirts);
        fields.put("stackL",(double)stackL);
        fields.put("test",(double)test);
        fields.put("test2",(double)test2);
        fields.put("throttle",(double)throttle);
        fields.put("time",(double)time);
        fields.put("tpsADC",(double)tpsADC);
        fields.put("tpsDOT",(double)tpsDOT);
        fields.put("tpsDOTTY",(double)tpsDOTTY);
        fields.put("tpsLast",(double)tpsLast);
        fields.put("vacuum",(double)vacuum);
        fields.put("veCurr",(double)veCurr);
        fields.put("veCurr1",(double)veCurr1);
        fields.put("veCurr2",(double)veCurr2);
        fields.put("warmupEnrich",(double)warmupEnrich);
        fields.put("waterIlog",(double)waterIlog);
        return new DataPacket(fields,ochBuffer);
    };

}

