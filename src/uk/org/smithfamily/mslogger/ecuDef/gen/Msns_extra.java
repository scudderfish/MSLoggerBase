package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.MSUtils;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
/*
Fingerprint : d6eb0dad7f641d8d78c632f4315e2bc2
*/
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
    byte[] queryCommand=new byte[]{'S'};
    String signature="MSnS-extra format 027c *********";
    byte [] ochGetCommand = new byte[]{'R'};
    int ochBlockSize = 38;
    private Set<String> sigs = new HashSet<String>(Arrays.asList(new String[] { signature }));
//Flags
    public boolean INNOVATE_0_5_LINEAR;
    public boolean DIYWB_NON_LINEAR;
    public boolean NGK_AFX;
    public boolean CELSIUS;
    public boolean MPXH6300A;
    public boolean DYNOJET_LINEAR;
    public boolean ZEITRONIX_NON_LINEAR;
    public boolean TECHEDGE_LINEAR;
    public boolean TestCode;
    public boolean WB_1_0_LINEAR;
    public boolean NARROW_BAND_EGO;
    public boolean FAST_WIDEBAND_O2;
    public boolean KPa;
    public boolean ALPHA_N;
    public boolean SPEED_DENSITY;
    public boolean AEM_LINEAR;
    public boolean INNOVATE_1_2_LINEAR;
    public boolean INNOVATE_LC1_DEFAULT;
    public boolean AIR_FLOW_METER;
    public boolean MPX4250;
    public boolean AEM_NON_LINEAR;
    public boolean WB_UNKNOWN;
    public boolean MPXH6400A;
//Defaults
//Variables
    public double CCpHr;
    public double Cd;
    public int CltIatAng;
    public int CltIatAngle;
    public int CltIatDeg;
    public int Crr;
    public double DiffRa;
    public double GrTms;
    public int KnockAng;
    public int KnockAngle;
    public int KnockDeg;
    public double MAFVolts;
    public int Mass;
    public double OpenWidth;
    public double Radius;
    public double RpmHiRes;
    public int Speed;
    public double TargetAFR;
    public double TargetLambda;
    public int Timeroll;
    public double USgph;
    public double USmpg;
    public double XForce;
    public int XOffset;
    public double YForce;
    public int YOffset;
    public double accDecEnrich;
    public int accelEnrich;
    public double advSpark;
    public int advance;
    public double afr;
    public double afr2;
    public double afrTargetV;
    public int afrtarget;
    public int airCorrection;
    public int altDiv1;
    public int altDiv2;
    public int baroADC;
    public int baroCorrection;
    public double barometer;
    public int batADC;
    public double batteryVoltage;
    public double boost;
    public double boostVac;
    public int cltADC;
    public double coolant;
    public double cycleTime1;
    public double cycleTime2;
    public int deadValue;
    public double dutyCycle1;
    public double dutyCycle2;
    public double ego2Voltage;
    public int egoADC;
    public double egoCorrection;
    public int egoCorrection2;
    public double egoVoltage;
    public int egtADC;
    public double egttemp;
    public int engine;
    public int floodclear;
    public int fuelADC;
    public int fuelCC;
    public double fuelpress;
    public double fuelvolt;
    public int gammaEnrich;
    public double gph;
    public int iTime;
    public int iTimeX;
    public int iTimefull;
    public int idleDC;
    public double lambda;
    public double lambda2;
    public double map;
    public int mapADC;
    public double mapDOT;
    public double mapDOTTY;
    public double mat;
    public int matADC;
    public double mpg;
    public double mph;
    public double mphTemp;
    public int nSquirts1;
    public int nSquirts2;
    public int porta;
    public int portb;
    public int portc;
    public int portd;
    public double pulseWidth;
    public double pulseWidth1;
    public double pulseWidth2;
    public int rpm;
    public int rpm100;
    public int secl;
    public int squirt;
    public double squirtmul;
    public double squirts;
    public int stackL;
    public double test;
    public int test2;
    public double throttle;
    public double time;
    public int tpsADC;
    public double tpsDOT;
    public double tpsDOTTY;
    public int tpsLast;
    public double vacuum;
    public double veCurr;
    public int veCurr1;
    public int veCurr2;
    public int warmupEnrich;
    public int waterIlog;

//Constants
    public int whlsimcnt;
    public int fastIdleTemp;
    public int WaterIIat;
    public int TPSRPMTarg;
    public int DecEnrichment;
    public int out2src;
    public double injPwmT2;
    public double injPwmT1;
    public double RPMmS4;
    public double RPMmS3;
    public double RPMmS2;
    public double RPMmS1;
    public int DecelKPa;
    public double outaoffv;
    public int edismulti;
    public int AlwaysPrime;
    public int KnockKpa;
    public int outaoffs;
    public int baroCorr1;
    public double Nos2delay;
    public int baroCorr2;
    public int egoCount2;
    public int egoCount1;
    public double NosPWHi;
    public int LachTps;
    public double RPMRedLo;
    public int OvrBCutType;
    public int fastIdleT2;
    public int Out2UpLim;
    public int egoDelta1;
    public int egoDelta2;
    public int fastIdleT1;
    public int rpmk2;
    public int rpmk1;
    public int cltType2;
    public int cltType1;
    public double RPM2S;
    public int idlefreq;
    public int trig2ret;
    public double delay2rpm;
    public double irestorerpm;
    public double VEFixValue;
    public double LC_f_limangle;
    public int outcoffs;
    public double outcoffv;
    public int NosHiKpa;
    public int numteeth;
    public double TractVSSMax;
    public int out4FAN;
    public double RPM1S;
    public int SparkCutBNum;
    public int PrimeLate;
    public double VE3Delay;
    public int shiftUse;
    public int KPaOn;
    public double LC_f_slim;
    public int UseVE3;
    public int TPSTar;
    public double outdoffv;
    public double fastIdleRPM;
    public int outdoffs;
    public int hrd_disable;
    public double outeoffv;
    public int slowIdleTemp;
    public double OvrRunRpm;
    public int OvrRunTps;
    public int efanofftemp;
    public int outeoffs;
    public double RPM4S;
    public int out4lim;
    public int tdePct4;
    public double dwellrun;
    public int inj2cr;
    public int nInjectors2;
    public int nInjectors1;
    public int divider1;
    public int divider2;
    public int IdleAdvTPS;
    public int out1Hysis;
    public double NosPWLo;
    public double RPM3S;
    public int trig3ret;
    public double SoftRevLim;
    public int AfrTar;
    public double TractRet2;
    public double TractRet1;
    public double TractRet4;
    public int ScaleFac;
    public double TractRet3;
    public int TractNOS;
    public int engineType2;
    public int fidleUse;
    public double idlekickrpm;
    public int OvrRunKpa;
    public int engineType1;
    public double CrankAng;
    public int trig2;
    public int trig3;
    public double SoftLimMax;
    public int trig1;
    public int trig6;
    public int trig4;
    public int trig5;
    public int OvrRunTimr;
    public double MaxIatAdv;
    public int whlsim;
    public double taeTime4;
    public int tpsflood;
    public int hei7;
    public int twoStroke2;
    public double fixedsplit;
    public int twoStroke1;
    public int edis;
    public int IATBoost;
    public int egoLimit2;
    public int egoLimit1;
    public int matType1;
    public int matType2;
    public int tachconf;
    public int ikpamin2;
    public int ikpamin1;
    public double TractSlip;
    public double LachRpm;
    public int led19Use;
    public int spare3_177;
    public int spare3_178;
    public double mapThresh4;
    public int spare3_179;
    public double KPA300Corr;
    public int ignmode;
    public int idleclosed;
    public int TrigCrank;
    public int spare3_180;
    public int trig2fall;
    public int spare3_181;
    public double IdleAdvRPM;
    public int spare3_182;
    public int spare3_183;
    public int VEFixASE;
    public int spare3_184;
    public int wd_2trig;
    public double RPMThresh;
    public int ASETable8;
    public int ASETable7;
    public int ASEFixDe;
    public int IgHold;
    public int ASETable9;
    public double NosAngle;
    public int ASETable4;
    public int ASETable3;
    public int ASETable6;
    public double out3Timer;
    public int ASETable5;
    public int ASETable2;
    public int CltAdv;
    public int ASETable1;
    public int IgAdvTemp;
    public int HCutLCSpark;
    public double Nos2PWHi;
    public double ST2Delay;
    public int out3lim;
    public int ExFuelCrank;
    public int spkfop;
    public int idlePeriod2;
    public int AfrTar2;
    public int dualdizzy;
    public int AlphBaroCor;
    public int led18_2Use;
    public double N2Odel_launch;
    public int egoIgnCount;
    public int led18Use;
    public int HCutLCType;
    public double idleThresh;
    public int out1src;
    public int spkeop;
    public int taeColdM2;
    public int taeColdM4;
    public double egoSwitch2;
    public int IgInv;
    public double egoSwitch1;
    public int IatAdvKpa;
    public int Trig_plus;
    public double SoftLimRetard;
    public int AccDecEnrich;
    public int WaterInj;
    public int STTable2On;
    public int ASEFixTim;
    public double TractVSSMin;
    public int idlekickdc;
    public int rotary2;
    public double KnockSb;
    public double ShiftLower;
    public int TPSAFRPoint;
    public int tachconfdiv;
    public int KPaTarg60;
    public int ConsBarCor;
    public double BarroLow;
    public double LC_flatlim;
    public double NosRpm;
    public double slowIdleRPM;
    public double DecayAcPoint;
    public int crankpwip;
    public double RPMRedHi;
    public int DecayAccel;
    public double LC_flatsel;
    public int pwmidlewhen;
    public int OLoopEgo;
    public double HardRevLim;
    public int TwoLambda;
    public int NosLagSystem;
    public int bcUpdateRate;
    public double KnockRpm;
    public int OvrRunC;
    public int TractSC4;
    public int out4src;
    public double Nos2PWLo;
    public int trig5ret;
    public int KPaTarg40;
    public int TractSC2;
    public int TractSC3;
    public int ASEFixTemp;
    public int TractSC1;
    public int dwellcont;
    public int pwmIdle;
    public int IgAdvDeg;
    public double Nos2Angle;
    public int injPwmP2;
    public double IdleAdv;
    public int injPwmP1;
    public int TargetB1;
    public int EgoLim2;
    public int TargetB2;
    public int out1lim;
    public int TargetB3;
    public int TargetB4;
    public double FixAng;
    public int TpsOn;
    public double KnockBoost;
    public int idlekpaval2;
    public int idlekpaval1;
    public double ShiftUpper;
    public double OvrBKpa;
    public int neonCrank;
    public int IATCor6;
    public int KnockOn;
    public int IATCor7;
    public double CrankPWT9;
    public int IATCor4;
    public double CrankPWT8;
    public int IATCor5;
    public int out3sparkd;
    public double CrankPWT7;
    public int IATCor2;
    public double CrankPWT6;
    public int IATCor3;
    public double CrankPWT5;
    public double CrankPWT4;
    public int IATCor1;
    public double CrankPWT3;
    public double CrankPWT2;
    public int AFMUse;
    public double CrankPWT1;
    public double KnockRpmL;
    public int oddfire;
    public int AccelMapASE;
    public int KPA300Sens;
    public double PrimePulse;
    public int mapType1;
    public double dwellcrank;
    public int egoType1;
    public int egoType2;
    public int mapType2;
    public int IATCorTy;
    public int KPaTarg10;
    public int ASETable10;
    public int inj2g;
    public double dGain;
    public double KnockRet;
    public double LachDeg;
    public int NosTps;
    public int inj2t;
    public int DtNos;
    public int idle_dc_lo;
    public int IgRetDeg;
    public int efanontemp;
    public int idleperiod;
    public int outboffs;
    public int StgDelta;
    public double outboffv;
    public int RPMBAsedAE;
    public int NosClt;
    public int EgoLimKPa;
    public int TractCyc3;
    public int TractCyc4;
    public int TractCyc1;
    public int TractCyc2;
    public double reqFuel2;
    public int PrimeTBox;
    public double reqFuel1;
    public double pGain;
    public int nitrousUse;
    public double TractSlipH;
    public double egoRPM1;
    public double TractScale;
    public int wheel_old;
    public int trig1ret;
    public double battFac2;
    public double RPMAE1;
    public double battFac1;
    public double RPMAE2;
    public double BoostMaxKn;
    public double N2Odel_flat;
    public int IgRetTemp;
    public double egoRPM2;
    public int IStagedMode;
    public double TrigAng;
    public int StgTrans;
    public double StartWIKPa;
    public double RPMAE4;
    public double RPMAE3;
    public int miss2nd;
    public int injType1;
    public int injType2;
    public int OvrRunClt;
    public int KnockTim;
    public int rotaryFDign;
    public int x2use;
    public int KPaDotBoost;
    public int DecelBoost;
    public int IATCor;
    public int HCutType;
    public int bc_max_diff;
    public double taeColdA4;
    public int TractMode;
    public int IATCTemp2;
    public int nCylinders2;
    public int IATCTemp1;
    public int nCylinders1;
    public int IATCTemp4;
    public int IATCTemp3;
    public int IATCTemp6;
    public int IATCTemp5;
    public int IATCTemp7;
    public double WaterIRpm;
    public double primePulse2;
    public int idledashdc;
    public int onetwo;
    public double N2Oholdon;
    public double BarroHi;
    public int out2lim;
    public int NosLowKpa;
    public double Trim;
    public int InvOut2;
    public int InvOut1;
    public int Out1UpLim;
    public int aseIgnCount;
    public int egoTemp2;
    public int egoTemp1;
    public int BoostDir;
    public int TractionOn;
    public int IATBoostSt;
    public int Out3_Out1;
    public double outfoffv;
    public int outfoffs;
    public double LaunchLim;
    public int KPaTarg100;
    public int SparkCutBase;
    public int ASETime;
    public double Nos2Rpm;
    public int toy_dli;
    public int dwellduty50;
    public double mindischg;
    public int mapProportion4;
    public int trig4ret;
    public int trig6ret;
    public double NosRpmMax;
    public double KPA300BARO;
    public int out3src;
    public int BooTbl2Use;
    public int inj1g;
    public int launchUse;
    public int tfi;
    public int freqDiv;
    public int TPSBooIAT;
    public int ExtraFMult;
    public int boostUse;
    public int wheelon;
    public int mapSensor1;
    public int TractCySec;
    public double edismultirpm;
    public int led17Use;
    public double CrankPWT10;
    public int HCutSpark;
    public int msnsCyl;
    public int algorithm2;
    public int out2Hysis;
    public int algorithm1;
    public double injOpen1;
    public double injOpen2;
    public double KnockMax;
    public int KpaTPSTar;
    public double CrankRPM;
    public int hybridAN;
    public int taeIgnCount;
    public int VLaunch;
    public int idle_dc_hi;
    public int alternate2;
    public double tpsThresh4;
    public double KnockAdv;
    public int idlemindc;
    public double Nos2RpmMax;
    public int alternate1;
    public int AirCorAFM;


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

}

