package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.MSUtils;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;

/*
 * Fingerprint : 5788bf57e354029eb72fbcddf432ce52
 */
public class ZZMS2Extra_Rev_102 extends Megasquirt
{
    public ZZMS2Extra_Rev_102(Context c)
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
        TRIGLOG = isSet("TRIGLOG");
    }

    byte[]              queryCommand  = new byte[] { 'Q' };
    String              signature     = "MS2Extra Rev 1.0.2 \0";
    byte[]              ochGetCommand = new byte[] { 'A' };
    int                 ochBlockSize  = 114;
    private Set<String> sigs          = new HashSet<String>(Arrays.asList(new String[] { signature }));
    // Flags
    boolean             NARROW_BAND_EGO;
    boolean             CELSIUS;
    boolean             LAMBDA;
    boolean             TRIGLOG;
    // Runtime vars
    double              tpsDOT;
    double              dwell;
    int                 portStatus;
    int                 gammaEnrich;
    int                 egoCorrection1;
    int                 egoCorrection2;
    int                 sync_status;
    double              ignload2;
    int                 ready;
    double              afr2;
    double              knock;
    double              afr1;
    int                 tpsden;
    int                 rpm;
    double              fuelload;
    double              barometer;
    int                 mapDOT;
    int                 wbo2_en2;
    double              coolant;
    double              coldAdvDeg;
    int                 tpsADC;
    double              afrtgt2;
    int                 engine;
    double              afrtgt1;
    int                 mapaen;
    double              looptime;
    int                 wbo2_en1;
    int                 status5;
    int                 status4;
    int                 status3;
    int                 status2;
    int                 tpsaen;
    int                 tpsfuelcut;
    int                 baroCorrection;
    int                 crank;
    int                 status1;
    double              pulseWidth2;
    double              pulseWidth1;
    int                 inj2;
    int                 inj1;
    double              veCurr1;
    int                 port0;
    double              batteryVoltage;
    double              veCurr2;
    double              fuelload2;
    int                 port4;
    int                 port3;
    int                 port2;
    int                 port1;
    int                 wallfuel;
    double              ignload;
    int                 port6;
    int                 maf;
    int                 port5;
    double              map;
    double              egoV;
    int                 sync_error;
    int                 warmup;
    int                 firing1;
    int                 firing2;
    double              mat;
    int                 iacstep;
    double              accelEnrich;
    double              knockRetard;
    int                 airCorrection;
    int                 squirt;
    int                 deltaT;
    double              egoV2;
    int                 seconds;
    int                 sched2;
    int                 EAEFuelCorr;
    int                 startw;
    int                 sched1;
    int                 warmupEnrich;
    int                 fuelCorrection;
    double              advance;
    double              tps;

    // eval vars
    double              accDecEnrich;
    int                 nSquirts2;
    int                 nSquirts1;
    double              dutyCycle2;
    double              throttle;
    double              dutyCycle1;
    int                 deadValue;
    double              lambda2;
    double              lambda1;
    int                 secl;
    double              pulseWidth;
    double              cycleTime1;
    double              cycleTime2;
    int                 altDiv1;
    double              time;
    int                 altDiv2;
    double              egoCorrection;
    double              veCurr;
    double              egoVoltage;
    int                 idleDC;
    double              rpm100;

    // Constants
    int                 taeColdM;
    double              triggerOffset;
    double              flats_deg;
    int                 egoRPM;
    int                 tempUnits;
    int                 staged_first_param;
    double              dwellAcc;
    int                 use_prediction;
    double              baro0;
    int                 tacho_opt80;
    int                 tpsMin;
    double              IdleHyst;
    double              taeColdA;
    int                 IgnAlgorithm2;
    int                 egoType;
    double              launch_sft_deg;
    int                 alternate;
    int                 Miss_ang;
    double              EAEmapdotmax;
    int                 feature4_0ftrig;
    int                 cltmult;
    int                 knkDirection;
    int                 bcormult;
    double              knk_tadv;
    int                 pwmidleset_inv;
    int                 crankingRPM;
    int                 staged_transition_events;
    double              egoTemp;
    double              MAPOXLimit;
    double              egoTarget;
    int                 AMCve_rpm;
    int                 board_id_type0;
    int                 userlevelbits;
    double              max_spk_dur;
    double              knk_dtble_adv;
    int                 No_Miss_Teeth;
    double              testpw;
    int                 flexFuel;
    double              dwelltime;
    int                 ICCrankTrigger;
    int                 knk_option;
    int                 timing_flags;
    int                 spk_config_trig2l;
    int                 Dtpred_Gain;
    int                 AMCramve_dt;
    int                 egoCount;
    int                 NoiseFilterOpts;
    double              fastIdleT;
    int                 tpsLF;
    int                 testmode;
    double              ego0;
    int                 staged_hyst_2;
    int                 mapLF;
    double              testint;
    int                 staged_hyst_1;
    int                 IACcrankxt;
    int                 ae_lorpm;
    int                 staged_sec_size;
    double              fc_kpa;
    int                 IgnAlgorithm;
    int                 dwellmode;
    int                 flats_arm;
    int                 egoAlgorithm;
    int                 spk_mode5;
    int                 dualTable;
    int                 ICISR_pmask;
    double              floodClear;
    int                 fuelFreq0;
    int                 userlevel;
    int                 fuelFreq1;
    int                 staged_second_param;
    int                 spk_mode3;
    int                 spk_mode0;
    int                 testop_inj;
    double              hw_latency;
    double              fuelSpkDel0;
    double              knockmax;
    double              fuelSpkDel1;
    int                 pwmidlecranking;
    double              tpsThresh;
    int                 AfrAlgorithm;
    int                 spk_config_trig2;
    double              reqFuel;
    int                 tpsProportion;
    int                 RotarySplitModeNeg;
    int                 nCylinders;
    double              knk_maxrtd;
    int                 launch_opt_on;
    double              battmax;
    int                 flexport;
    double              IACaccstep;
    double              knock0;
    int                 RevLimOption;
    int                 spk_config_sig;
    int                 AMCstep;
    int                 crankTolerance;
    double              fc_delay;
    int                 EAEOption;
    int                 rpmLF;
    int                 asTolerance;
    int                 knk_lorpm;
    double              fixed_timing;
    int                 staged_pri_size;
    double              AFRTarget;
    int                 pwmidlecranktaper;
    int                 spkout_hi_lo;
    int                 knkLF;
    double              RevLimMaxRtd;
    int                 spk_config_camcrank;
    double              launch_tps;
    int                 testinjcnt;
    int                 launch_sft_lim;
    double              crank_dwell;
    int                 spk_config_spka;
    int                 loadMult;
    int                 egoDelta;
    int                 testmodelock;
    int                 egoKI;
    int                 IACnaccstep;
    int                 divider;
    int                 egoKD;
    int                 rtbaroport;
    int                 matmult;
    int                 AMCOption;
    int                 tpsMax;
    int                 knk_ndet;
    int                 knkport;
    int                 adcLF;
    int                 bcor0;
    double              map0;
    int                 egomult;
    int                 egoKP;
    int                 AMCdve;
    int                 staged_transition_on;
    int                 egoKdelay2;
    int                 testop_fp;
    int                 egoKdelay1;
    int                 flats_sft;
    int                 PredOpt;
    double              fc_clt;
    int                 egoLF;
    int                 tdePct;
    int                 staged_second_logic;
    int                 algorithm;
    int                 IACcoldpos;
    double              aeEndPW;
    double              mapmax;
    int                 staged_param_1;
    double              taeTime;
    int                 staged_param_2;
    int                 knk_hirpm;
    int                 RevLimcutx;
    double              AMCve_map;
    double              knk_maxmap;
    int                 launch_hrd_lim;
    int                 RotarySplitModeFD;
    int                 RevLimcuty;
    double              testdwell;
    double              batt0;
    double              knk_trtd;
    double              TPSOXLimit;
    int                 engineType;
    double              battFac;
    double              baromax;
    int                 nInjectors;
    int                 IACStart;
    int                 no_skip_pulses;
    int                 iacpostest;
    int                 injType;
    double              ICISR_tmask;
    double              max_coil_dur;
    double              crank_timing;
    int                 pulseTolerance;
    int                 IdleCtl;
    double              dwellduty;
    int                 ego2port;
    int                 AMCupdate_thresh;
    double              injOpen;
    int                 tacho_opt40;
    double              IACcoldtmp;
    int                 AMCT_thresh;
    int                 launch_opt_pins;
    double              fc_tps;
    int                 launchlimopt;
    int                 feature3_0;
    int                 mapThresh;
    int                 fc_rpm;
    double              clt0;
    int                 twoStroke;
    int                 fuelCorr0;
    int                 fuelCorr1;
    int                 IACcoldxt;
    int                 altcrank;
    double              mat0;
    int                 baud;
    int                 tacho_opt3f;
    int                 launchcutx;
    int                 launchcuty;
    int                 flats_hrd;
    int                 ae_hirpm;
    int                 injPwmPd;
    int                 OvrRunC;
    int                 testop_coil;
    int                 loadCombine;
    int                 ICIgnCapture;
    int                 algorithm2;
    int                 mafOption;
    int                 baroCorr;
    int                 injPwmP;
    int                 IACcrankpos;
    double              aeTaperTime;
    int                 triggerTeeth;
    int                 RevLimRpm2;
    int                 RevLimRpm1;
    int                 egoLimit;
    double              IACtstep;
    int                 OddFire2;
    double              knk_step2;
    double              injPwmT;
    double              knk_step1;
    int                 pwmidleset_hz;

    private String[]    defaultGauges = { "tachometer", "EAEGauge", "pulseWidth1Gauge", "cltGauge", "advdegGauge", "fuelloadGauge", "afr1Gauge", "matGauge" };

    @Override
    public void calculate(byte[] ochBuffer) throws IOException
    {
        deadValue = (0);
        seconds = (int) ((MSUtils.getWord(ochBuffer, 0) + 0.0) * 1.000);
        secl = (seconds % 256);
        pulseWidth1 = (double) ((MSUtils.getWord(ochBuffer, 2) + 0.0) * 0.000666);
        pulseWidth2 = (double) ((MSUtils.getWord(ochBuffer, 4) + 0.0) * 0.000666);
        pulseWidth = (pulseWidth1);
        rpm = (int) ((MSUtils.getWord(ochBuffer, 6) + 0.0) * 1.000);
        advance = (double) ((MSUtils.getSignedWord(ochBuffer, 8) + 0.0) * 0.100);
        squirt = (int) ((MSUtils.getByte(ochBuffer, 10) + 0.0) * 1.000);
        firing1 = MSUtils.getBits(ochBuffer, 10, 0, 0, 0);
        firing2 = MSUtils.getBits(ochBuffer, 10, 1, 1, 0);
        sched1 = MSUtils.getBits(ochBuffer, 10, 2, 2, 0);
        inj1 = MSUtils.getBits(ochBuffer, 10, 3, 3, 0);
        sched2 = MSUtils.getBits(ochBuffer, 10, 4, 4, 0);
        inj2 = MSUtils.getBits(ochBuffer, 10, 5, 5, 0);
        engine = (int) ((MSUtils.getByte(ochBuffer, 11) + 0.0) * 1.000);
        ready = MSUtils.getBits(ochBuffer, 11, 0, 0, 0);
        crank = MSUtils.getBits(ochBuffer, 11, 1, 1, 0);
        startw = MSUtils.getBits(ochBuffer, 11, 2, 2, 0);
        warmup = MSUtils.getBits(ochBuffer, 11, 3, 3, 0);
        tpsaen = MSUtils.getBits(ochBuffer, 11, 4, 4, 0);
        tpsden = MSUtils.getBits(ochBuffer, 11, 5, 5, 0);
        mapaen = MSUtils.getBits(ochBuffer, 11, 6, 6, 0);
        afrtgt1 = (double) ((MSUtils.getByte(ochBuffer, 12) + 0.0) * 10.00);
        afrtgt2 = (double) ((MSUtils.getByte(ochBuffer, 13) + 0.0) * 10.00);
        wbo2_en1 = (int) ((MSUtils.getByte(ochBuffer, 14) + 0.0) * 1.000);
        wbo2_en2 = (int) ((MSUtils.getByte(ochBuffer, 15) + 0.0) * 1.000);
        barometer = (double) ((MSUtils.getSignedWord(ochBuffer, 16) + 0.0) * 0.100);
        map = (double) ((MSUtils.getSignedWord(ochBuffer, 18) + 0.0) * 0.100);
        if (CELSIUS)
        {
            mat = (double) ((MSUtils.getSignedWord(ochBuffer, 20) + -320.0) * 0.05555);
            coolant = (double) ((MSUtils.getSignedWord(ochBuffer, 22) + -320.0) * 0.05555);
        }
        else
        {
            mat = (double) ((MSUtils.getSignedWord(ochBuffer, 20) + 0.0) * 0.100);
            coolant = (double) ((MSUtils.getSignedWord(ochBuffer, 22) + 0.0) * 0.100);
        }
        tps = (double) ((MSUtils.getSignedWord(ochBuffer, 24) + 0.0) * 0.100);
        throttle = (tps);
        batteryVoltage = (double) ((MSUtils.getSignedWord(ochBuffer, 26) + 0.0) * 0.100);
        afr1 = (double) ((MSUtils.getSignedWord(ochBuffer, 28) + 0.0) * 0.100);
        afr2 = (double) ((MSUtils.getSignedWord(ochBuffer, 30) + 0.0) * 0.100);
        lambda1 = (afr1 / 14.7);
        lambda2 = (afr2 / 14.7);
        knock = (double) ((MSUtils.getSignedWord(ochBuffer, 32) + 0.0) * 0.100);
        egoCorrection1 = (int) ((MSUtils.getSignedWord(ochBuffer, 34) + 0.0) * 1.000);
        egoCorrection = ((egoCorrection1 + egoCorrection2) / 2);
        egoCorrection2 = (int) ((MSUtils.getSignedWord(ochBuffer, 36) + 0.0) * 1.000);
        airCorrection = (int) ((MSUtils.getSignedWord(ochBuffer, 38) + 0.0) * 1.000);
        warmupEnrich = (int) ((MSUtils.getSignedWord(ochBuffer, 40) + 0.0) * 1.000);
        accelEnrich = (double) ((MSUtils.getSignedWord(ochBuffer, 42) + 0.0) * 0.100);
        tpsfuelcut = (int) ((MSUtils.getSignedWord(ochBuffer, 44) + 0.0) * 1.000);
        baroCorrection = (int) ((MSUtils.getSignedWord(ochBuffer, 46) + 0.0) * 1.000);
        gammaEnrich = (int) ((MSUtils.getSignedWord(ochBuffer, 48) + 0.0) * 1.000);
        veCurr1 = (double) ((MSUtils.getSignedWord(ochBuffer, 50) + 0.0) * 0.1000);
        veCurr2 = (double) ((MSUtils.getSignedWord(ochBuffer, 52) + 0.0) * 0.1000);
        veCurr = (veCurr1);
        iacstep = (int) ((MSUtils.getSignedWord(ochBuffer, 54) + 0.0) * 1.000);
        idleDC = (iacstep);
        coldAdvDeg = (double) ((MSUtils.getSignedWord(ochBuffer, 56) + 0.0) * 0.100);
        tpsDOT = (double) ((MSUtils.getSignedWord(ochBuffer, 58) + 0.0) * 0.100);
        mapDOT = (int) ((MSUtils.getSignedWord(ochBuffer, 60) + 0.0) * 1.000);
        dwell = (double) ((MSUtils.getWord(ochBuffer, 62) + 0.0) * 0.0666);
        maf = (int) ((MSUtils.getSignedWord(ochBuffer, 64) + 0.0) * 1.000);
        fuelload = (double) ((MSUtils.getSignedWord(ochBuffer, 66) + 0.0) * 0.100);
        fuelCorrection = (int) ((MSUtils.getSignedWord(ochBuffer, 68) + 0.0) * 1.000);
        portStatus = (int) ((MSUtils.getByte(ochBuffer, 70) + 0.0) * 1.000);
        port0 = MSUtils.getBits(ochBuffer, 70, 0, 0, 0);
        port1 = MSUtils.getBits(ochBuffer, 70, 1, 1, 0);
        port2 = MSUtils.getBits(ochBuffer, 70, 2, 2, 0);
        port3 = MSUtils.getBits(ochBuffer, 70, 3, 3, 0);
        port4 = MSUtils.getBits(ochBuffer, 70, 4, 4, 0);
        port5 = MSUtils.getBits(ochBuffer, 70, 5, 5, 0);
        port6 = MSUtils.getBits(ochBuffer, 70, 6, 6, 0);
        knockRetard = (double) ((MSUtils.getByte(ochBuffer, 71) + 0.0) * 0.1);
        EAEFuelCorr = (int) ((MSUtils.getWord(ochBuffer, 72) + 0.0) * 1.0);
        egoV = (double) ((MSUtils.getSignedWord(ochBuffer, 74) + 0.0) * 0.01);
        egoV2 = (double) ((MSUtils.getSignedWord(ochBuffer, 76) + 0.0) * 0.01);
        status1 = (int) ((MSUtils.getByte(ochBuffer, 78) + 0.0) * 1.0);
        status2 = (int) ((MSUtils.getByte(ochBuffer, 79) + 0.0) * 1.0);
        status3 = (int) ((MSUtils.getByte(ochBuffer, 80) + 0.0) * 1.0);
        status4 = (int) ((MSUtils.getByte(ochBuffer, 81) + 0.0) * 1.0);
        looptime = (double) ((MSUtils.getWord(ochBuffer, 82) + 0.0) * 0.6667);
        status5 = (int) ((MSUtils.getWord(ochBuffer, 84) + 0) * 1);
        tpsADC = (int) ((MSUtils.getWord(ochBuffer, 86) + 0) * 1);
        fuelload2 = (double) ((MSUtils.getWord(ochBuffer, 88) + 0.0) * 0.100);
        ignload = (double) ((MSUtils.getWord(ochBuffer, 90) + 0.0) * 0.100);
        ignload2 = (double) ((MSUtils.getWord(ochBuffer, 92) + 0.0) * 0.100);
        sync_error = MSUtils.getBits(ochBuffer, 104, 0, 0, 0);
        sync_status = MSUtils.getBits(ochBuffer, 104, 1, 1, 0);
        deltaT = (int) ((MSUtils.getSignedLong(ochBuffer, 106) + 0.0) * 1.000);
        wallfuel = (int) ((MSUtils.getLong(ochBuffer, 110) + 0.0) * 1.000);
        accDecEnrich = (((accelEnrich + (tpsden) != 0) ? tpsfuelcut : 100));
        time = (timeNow());
        rpm100 = (rpm / 100.0);
        altDiv1 = (((alternate) != 0) ? 2 : 1);
        altDiv2 = (((alternate) != 0) ? 2 : 1);
        cycleTime1 = (60000.0 / rpm * (2.0 - twoStroke));
        nSquirts1 = (nCylinders / divider);
        dutyCycle1 = (100.0 * nSquirts1 / altDiv1 * pulseWidth1 / cycleTime1);
        cycleTime2 = (60000.0 / rpm * (2.0 - twoStroke));
        nSquirts2 = (nCylinders / divider);
        dutyCycle2 = (100.0 * nSquirts2 / altDiv2 * pulseWidth2 / cycleTime2);
        if (NARROW_BAND_EGO)
        {
            egoVoltage = (1.0 - afr1 * 0.04883);
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
        b.append("knockRet").append("\t");
        b.append("ColdAdv").append("\t");
        b.append("Dwell").append("\t");
        b.append("tpsDOT").append("\t");
        b.append("mapDOT").append("\t");
        b.append("IACstep").append("\t");
        b.append("deltaT").append("\t");
        b.append("status5").append("\t");
        b.append("WallFuel").append("\t");
        b.append("EAE %").append("\t");
        b.append("Load").append("\t");
        b.append("Secondary Load").append("\t");
        b.append("Ign load").append("\t");
        b.append("Secondary Ign Load").append("\t");
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
        b.append(round(veCurr1)).append("\t");
        b.append(round(pulseWidth1)).append("\t");
        b.append(dutyCycle1).append("\t");
        b.append(round(veCurr2)).append("\t");
        b.append(round(pulseWidth2)).append("\t");
        b.append(dutyCycle2).append("\t");
        b.append(round(advance)).append("\t");
        b.append(round(knockRetard)).append("\t");
        b.append(round(coldAdvDeg)).append("\t");
        b.append(round(dwell)).append("\t");
        b.append(round(tpsDOT)).append("\t");
        b.append(mapDOT).append("\t");
        b.append(iacstep).append("\t");
        b.append(deltaT).append("\t");
        b.append(status5).append("\t");
        b.append(wallfuel).append("\t");
        b.append(EAEFuelCorr).append("\t");
        b.append(round(fuelload)).append("\t");
        b.append(round(fuelload2)).append("\t");
        b.append(round(ignload)).append("\t");
        b.append(round(ignload2)).append("\t");
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("advdegGauge", "advance", advance, "Ignition Advance", "degrees", 0, 50, -1, -1, 999, 999, 1, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("IACgauge", "iacstep", iacstep, "IAC position", "steps", 0, 255, -1, -1, 999, 999, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dwellGauge", "dwell", dwell, "Dwell", "mSec", 0, 10, 0.5, 1.0, 6.0, 8.0, 1, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("PWMIdlegauge", "idleDC", idleDC, "Idle PWM%", "%", 0, 100, -1, -1, 999, 90, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelEnrichGauge", "accDecEnrich", accDecEnrich, "Accel Enrich", "%", 50, 150, -1, -1, 999, 999, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1Gauge", "afr1", afr1, "Air:Fuel Ratio", "", 10, 19.4, 12, 13, 15, 16, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2Gauge", "afr2", afr2, "Air:Fuel Ratio2", "", 10, 19.4, 12, 13, 15, 16, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clockGauge", "seconds", seconds, "Clock", "Seconds", 0, 255, 10, 10, 245, 245, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge", "deadValue", deadValue, "---", "", 0, 1, -1, -1, 2, 2, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle1Gauge", "dutyCycle1", dutyCycle1, "Duty Cycle 1", "%", 0, 100, -1, -1, 85, 90, 1, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle2Gauge", "dutyCycle2", dutyCycle2, "Duty Cycle 2", "%", 0, 100, -1, -1, 85, 90, 1, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge", "egoCorrection", egoCorrection, "EGO Correction", "%", 50, 150, 90, 99, 101, 110, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge1", "egoCorrection1", egoCorrection1, "EGO Correction 1", "%", 50, 150, 90, 99, 101, 110, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge2", "egoCorrection2", egoCorrection2, "EGO Correction 2", "%", 50, 150, 90, 99, 101, 110, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge", "egoVoltage", egoVoltage, "Exhaust Gas Oxygen", "volts", 0, 1.0, 0.2, 0.3, 0.7, 0.8, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoVGauge", "egoV", egoV, "Exhaust Gas Oxygen", "volts", 0, 5, 5, 5, 5, 5, 5, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV2Gauge", "egoV2", egoV2, "Exhaust Gas Oxygen2", "volts", 0, 5, 5, 5, 5, 5, 5, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda1Gauge", "lambda1", lambda1, "Lambda", "", 0.5, 1.5, 0.5, 0.7, 2, 1.1, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda2Gauge", "lambda2", lambda2, "Lambda", "", 0.5, 1.5, 0.5, 0.7, 2, 1.1, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaEnrichGauge", "gammaEnrich", gammaEnrich, "Gamma Enrichment", "%", 50, 150, -1, -1, 151, 151, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge", "map", map, "Engine MAP", "kPa", 0, 255, 0, 20, 200, 245, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("barometerGauge", "barometer", barometer, "Barometer", "kPa", 60, 120, 0, 20, 200, 245, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelloadGauge", "fuelload", fuelload, "Fuel Load", "%", 0, 255, 0, 20, 200, 245, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelload2Gauge", "fuelload2", fuelload2, "Secondary Fuel Load", "%", 0, 255, 0, 20, 200, 245, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ignloadGauge", "ignload", ignload, "Ign Load", "%", 0, 255, 0, 20, 200, 245, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ignload2Gauge", "ignload2", ignload2, "Secondary Ign Load", "%", 0, 255, 0, 20, 200, 245, 1, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth1Gauge", "pulseWidth1", pulseWidth1, "Pulse Width 1", "mSec", 0, 25.5, 1.0, 1.2, 20, 25, 3, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth2Gauge", "pulseWidth2", pulseWidth2, "Pulse Width 2", "mSec", 0, 25.5, 1.0, 1.2, 20, 25, 3, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tachometer", "rpm", rpm, "Engine Speed", "RPM", 0, 8000, 300, 600, 3000, 5000, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("throttleGauge", "throttle", throttle, "Throttle Position", "%", 0, 100, -1, 1, 90, 100, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge", "veCurr1", veCurr1, "VE Current", "%", 0, 120, -1, -1, 999, 999, 1, 1, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("voltMeter", "batteryVoltage", batteryVoltage, "Battery Voltage", "volts", 7, 21, 8, 9, 15, 16, 2, 2, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("warmupEnrichGauge", "warmupEnrich", warmupEnrich, "Warmup Enrichment", "%", 100, 150, -1, -1, 101, 105, 0, 0, 0));
        if (CELSIUS)
        {
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge", "coolant", coolant, "Coolant Temp", "¡C", -40, 120, -15, 0, 95, 105, 0, 0, 0));
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge", "mat", mat, "Manifold Air Temp", "¡C", -40, 110, -15, 0, 95, 100, 0, 0, 0));
        }
        else
        {
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge", "coolant", coolant, "Coolant Temp", "¡F", -40, 250, 0, 30, 200, 220, 0, 0, 0));
            GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge", "mat", mat, "Manifold Air Temp", "¡F", -40, 215, 0, 30, 200, 210, 0, 0, 0));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status1Gauge", "status1", status1, "Status 1", "", 0, 255, 255, 255, 255, 255, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status2Gauge", "status2", status2, "Status 2", "", 0, 255, 255, 255, 255, 255, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status3Gauge", "status3", status3, "Status 3", "", 0, 255, 255, 255, 255, 255, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status4Gauge", "status4", status4, "Status 4", "", 0, 255, 255, 255, 255, 255, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status5Gauge", "status5", status5, "Status 5", "", 0, 65535, 65535, 65535, 65535, 65535, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("looptimeGauge", "looptime", looptime, "Mainloop time", "us", 0, 65535, 255, 255, 255, 255, 0, 0, 0));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("EAEGauge", "EAEFuelCorr", EAEFuelCorr, "EAE Fuel Correction", "%", 0, 200, 40, 70, 130, 160, 0, 0, 0));

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
        return 100;
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

        if (TRIGLOG)
        {
        }
        else
        {
        }
        pageBuffer = loadPage(1, 0, 1024, null, new byte[] { 114, 0, 4, 0, 0, 4, 0 });
        nCylinders = MSUtils.getBits(pageBuffer, 0, 0, 3, 0);
        no_skip_pulses = (int) ((MSUtils.getByte(pageBuffer, 1) + 0.0) * 1.0);
        ICIgnCapture = MSUtils.getBits(pageBuffer, 2, 0, 0, 0);
        ICCrankTrigger = MSUtils.getBits(pageBuffer, 2, 1, 2, 0);
        spkout_hi_lo = MSUtils.getBits(pageBuffer, 3, 0, 0, 0);
        max_coil_dur = (double) ((MSUtils.getByte(pageBuffer, 4) + 0.0) * 0.0666);
        max_spk_dur = (double) ((MSUtils.getByte(pageBuffer, 5) + 0.0) * 0.0666);
        dwellAcc = (double) ((MSUtils.getByte(pageBuffer, 6) + 0.0) * 0.0666);
        RevLimOption = MSUtils.getBits(pageBuffer, 17, 0, 2, 0);
        RevLimMaxRtd = (double) ((MSUtils.getSignedByte(pageBuffer, 18) + 0.0) * 0.1);
        PredOpt = MSUtils.getBits(pageBuffer, 19, 0, 1, 0);
        crankingRPM = (int) ((MSUtils.getSignedWord(pageBuffer, 20) + 0.0) * 1.0);
        triggerOffset = (double) ((MSUtils.getSignedWord(pageBuffer, 42) + 0.0) * 0.1);
        RevLimRpm1 = (int) ((MSUtils.getSignedWord(pageBuffer, 44) + 0.0) * 1.0);
        RevLimRpm2 = (int) ((MSUtils.getSignedWord(pageBuffer, 46) + 0.0) * 1.0);
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
        map0 = (double) ((MSUtils.getSignedWord(pageBuffer, 506) + 0.0) * 0.1);
        mapmax = (double) ((MSUtils.getSignedWord(pageBuffer, 508) + 0.0) * 0.1);
        if (CELSIUS)
        {
            clt0 = (double) ((MSUtils.getSignedWord(pageBuffer, 510) + -320.0) * 0.05555);
            cltmult = (int) ((MSUtils.getSignedWord(pageBuffer, 512) + 0.0) * 1.0);
            mat0 = (double) ((MSUtils.getSignedWord(pageBuffer, 514) + -320.0) * 0.05555);
        }
        else
        {
            clt0 = (double) ((MSUtils.getSignedWord(pageBuffer, 510) + 0.0) * 0.1);
            cltmult = (int) ((MSUtils.getSignedWord(pageBuffer, 512) + 0.0) * 1.0);
            mat0 = (double) ((MSUtils.getSignedWord(pageBuffer, 514) + 0.0) * 0.1);
        }
        matmult = (int) ((MSUtils.getSignedWord(pageBuffer, 516) + 0.0) * 1.0);
        tpsMin = (int) ((MSUtils.getSignedWord(pageBuffer, 518) + 0.0) * 1.0);
        tpsMax = (int) ((MSUtils.getSignedWord(pageBuffer, 520) + 0.0) * 1.0);
        batt0 = (double) ((MSUtils.getSignedWord(pageBuffer, 522) + 0.0) * 0.1);
        battmax = (double) ((MSUtils.getSignedWord(pageBuffer, 524) + 0.0) * 0.1);
        ego0 = (double) ((MSUtils.getSignedWord(pageBuffer, 526) + 0.0) * 0.1);
        egomult = (int) ((MSUtils.getSignedWord(pageBuffer, 528) + 0.0) * 1.0);
        baro0 = (double) ((MSUtils.getSignedWord(pageBuffer, 530) + 0.0) * 0.1);
        baromax = (double) ((MSUtils.getSignedWord(pageBuffer, 532) + 0.0) * 0.1);
        bcor0 = (int) ((MSUtils.getSignedWord(pageBuffer, 534) + 0.0) * 1.0);
        bcormult = (int) ((MSUtils.getSignedWord(pageBuffer, 536) + 0.0) * 1.0);
        knock0 = (double) ((MSUtils.getSignedWord(pageBuffer, 538) + 0.0) * 0.01);
        knockmax = (double) ((MSUtils.getSignedWord(pageBuffer, 540) + 0.0) * 0.01);
        Dtpred_Gain = (int) ((MSUtils.getSignedWord(pageBuffer, 542) + 0.0) * 1.0);
        crankTolerance = (int) ((MSUtils.getByte(pageBuffer, 544) + 0.0) * 1.0);
        asTolerance = (int) ((MSUtils.getByte(pageBuffer, 545) + 0.0) * 1.0);
        pulseTolerance = (int) ((MSUtils.getByte(pageBuffer, 546) + 0.0) * 1.0);
        IdleCtl = MSUtils.getBits(pageBuffer, 547, 0, 2, 0);
        IACtstep = (double) ((MSUtils.getByte(pageBuffer, 548) + 0.0) * 0.128);
        IACaccstep = (double) ((MSUtils.getByte(pageBuffer, 549) + 0.0) * 0.1);
        IACnaccstep = (int) ((MSUtils.getByte(pageBuffer, 550) + 0.0) * 1.0);
        dwellduty = (double) ((MSUtils.getByte(pageBuffer, 551) + 0.0) * 0.39);
        IACStart = (int) ((MSUtils.getSignedWord(pageBuffer, 552) + 0.0) * 1.0);
        if (CELSIUS)
        {
            IdleHyst = (double) ((MSUtils.getSignedWord(pageBuffer, 554) + -320.0) * 0.05555);
        }
        else
        {
            IdleHyst = (double) ((MSUtils.getSignedWord(pageBuffer, 554) + 0.0) * 0.1);
        }
        IACcrankpos = (int) ((MSUtils.getSignedWord(pageBuffer, 556) + 0.0) * 1.0);
        IACcrankxt = (int) ((MSUtils.getSignedWord(pageBuffer, 558) + 0.0) * 1.0);
        if (CELSIUS)
        {
            IACcoldtmp = (double) ((MSUtils.getSignedWord(pageBuffer, 560) + -320.0) * 0.05555);
        }
        else
        {
            IACcoldtmp = (double) ((MSUtils.getSignedWord(pageBuffer, 560) + 0.0) * 0.1);
        }
        IACcoldpos = (int) ((MSUtils.getSignedWord(pageBuffer, 562) + 0.0) * 1.0);
        IACcoldxt = (int) ((MSUtils.getSignedWord(pageBuffer, 564) + 0.0) * 1.0);
        taeColdA = (double) ((MSUtils.getByte(pageBuffer, 582) + 0.0) * 0.1);
        taeColdM = (int) ((MSUtils.getByte(pageBuffer, 583) + 0.0) * 1.0);
        tpsThresh = (double) ((MSUtils.getByte(pageBuffer, 584) + 0.0) * 0.1);
        mapThresh = (int) ((MSUtils.getByte(pageBuffer, 585) + 0.0) * 1.0);
        taeTime = (double) ((MSUtils.getByte(pageBuffer, 586) + 0.0) * 0.1);
        tdePct = (int) ((MSUtils.getByte(pageBuffer, 587) + 0.0) * 1.0);
        floodClear = (double) ((MSUtils.getSignedWord(pageBuffer, 588) + 0.0) * 0.1);
        TPSOXLimit = (double) ((MSUtils.getSignedWord(pageBuffer, 590) + 0.0) * 0.1);
        tpsProportion = (int) ((MSUtils.getByte(pageBuffer, 592) + 0.0) * 1.0);
        baroCorr = MSUtils.getBits(pageBuffer, 593, 0, 1, 0);
        egoType = MSUtils.getBits(pageBuffer, 594, 0, 2, 0);
        egoCount = (int) ((MSUtils.getByte(pageBuffer, 595) + 0.0) * 1.0);
        egoDelta = (int) ((MSUtils.getByte(pageBuffer, 596) + 0.0) * 1.0);
        egoLimit = (int) ((MSUtils.getByte(pageBuffer, 597) + 0.0) * 1.0);
        if (NARROW_BAND_EGO)
        {
            egoTarget = (double) ((MSUtils.getByte(pageBuffer, 598) + -204.8) * -0.004883);
        }
        else if (LAMBDA)
        {
            AFRTarget = (double) ((MSUtils.getByte(pageBuffer, 598) + 0.0) * 0.006803);
        }
        else
        {
            AFRTarget = (double) ((MSUtils.getByte(pageBuffer, 598) + 0.0) * 0.1);
        }
        tempUnits = MSUtils.getBits(pageBuffer, 599, 0, 0, 0);
        mafOption = MSUtils.getBits(pageBuffer, 600, 0, 7, 0);
        if (CELSIUS)
        {
            fastIdleT = (double) ((MSUtils.getSignedWord(pageBuffer, 602) + -320.0) * 0.05555);
            egoTemp = (double) ((MSUtils.getSignedWord(pageBuffer, 604) + -320.0) * 0.05555);
        }
        else
        {
            fastIdleT = (double) ((MSUtils.getSignedWord(pageBuffer, 602) + 0.0) * 0.1);
            egoTemp = (double) ((MSUtils.getSignedWord(pageBuffer, 604) + 0.0) * 0.1);
        }
        egoRPM = (int) ((MSUtils.getSignedWord(pageBuffer, 606) + 0.0) * 1.0);
        reqFuel = (double) ((MSUtils.getWord(pageBuffer, 608) + 0.0) * 0.0010);
        divider = (int) ((MSUtils.getByte(pageBuffer, 610) + 0.0) * 1.0);
        alternate = MSUtils.getBits(pageBuffer, 611, 0, 0, 0);
        altcrank = MSUtils.getBits(pageBuffer, 611, 1, 1, 0);
        injOpen = (double) ((MSUtils.getByte(pageBuffer, 612) + 0.0) * 0.0666);
        injPwmT = (double) ((MSUtils.getByte(pageBuffer, 613) + 0.0) * 0.1);
        injPwmPd = (int) ((MSUtils.getByte(pageBuffer, 614) + 0.0) * 1.0);
        injPwmP = (int) ((MSUtils.getByte(pageBuffer, 615) + 0.0) * 1.0);
        battFac = (double) ((MSUtils.getByte(pageBuffer, 616) + 0.0) * 0.0166667);
        twoStroke = MSUtils.getBits(pageBuffer, 617, 0, 0, 0);
        injType = MSUtils.getBits(pageBuffer, 618, 0, 0, 0);
        nInjectors = MSUtils.getBits(pageBuffer, 619, 0, 3, 0);
        engineType = MSUtils.getBits(pageBuffer, 620, 0, 0, 0);
        OddFire2 = (int) ((MSUtils.getByte(pageBuffer, 621) + 0.0) * 1.0);
        rpmLF = (int) ((MSUtils.getByte(pageBuffer, 622) + 0.0) * 1.0);
        mapLF = (int) ((MSUtils.getByte(pageBuffer, 623) + 0.0) * 1.0);
        tpsLF = (int) ((MSUtils.getByte(pageBuffer, 624) + 0.0) * 1.0);
        egoLF = (int) ((MSUtils.getByte(pageBuffer, 625) + 0.0) * 1.0);
        adcLF = (int) ((MSUtils.getByte(pageBuffer, 626) + 0.0) * 1.0);
        knkLF = (int) ((MSUtils.getByte(pageBuffer, 627) + 0.0) * 1.0);
        AMCOption = MSUtils.getBits(pageBuffer, 628, 0, 1, 0);
        dualTable = MSUtils.getBits(pageBuffer, 629, 0, 0, 0);
        algorithm = MSUtils.getBits(pageBuffer, 630, 0, 1, 0);
        algorithm2 = MSUtils.getBits(pageBuffer, 630, 4, 5, 0);
        IgnAlgorithm = MSUtils.getBits(pageBuffer, 631, 0, 1, 0);
        IgnAlgorithm2 = MSUtils.getBits(pageBuffer, 631, 4, 5, 0);
        AfrAlgorithm = (int) ((MSUtils.getByte(pageBuffer, 632) + 0.0) * 1.0);
        dwelltime = (double) ((MSUtils.getByte(pageBuffer, 633) + 0.0) * 0.1);
        hw_latency = (double) ((MSUtils.getByte(pageBuffer, 732) + 0.0) * 0.66667);
        loadCombine = MSUtils.getBits(pageBuffer, 733, 0, 0, 0);
        loadMult = MSUtils.getBits(pageBuffer, 733, 2, 2, 0);
        baud = (int) ((MSUtils.getLong(pageBuffer, 734) + 0.0) * 1.0);
        MAPOXLimit = (double) ((MSUtils.getSignedWord(pageBuffer, 738) + 0.0) * 0.1);
        board_id_type0 = MSUtils.getBits(pageBuffer, 740, 0, 7, 0);
        aeTaperTime = (double) ((MSUtils.getByte(pageBuffer, 896) + 0.0) * 0.1);
        aeEndPW = (double) ((MSUtils.getSignedWord(pageBuffer, 898) + 0.0) * 0.1);
        egoAlgorithm = MSUtils.getBits(pageBuffer, 900, 0, 1, 0);
        egoKP = (int) ((MSUtils.getByte(pageBuffer, 901) + 0.0) * 1.0);
        egoKI = (int) ((MSUtils.getByte(pageBuffer, 902) + 0.0) * 1.0);
        egoKD = (int) ((MSUtils.getByte(pageBuffer, 903) + 0.0) * 1.0);
        egoKdelay1 = (int) ((MSUtils.getWord(pageBuffer, 904) + 0.0) * 1.0);
        egoKdelay2 = (int) ((MSUtils.getWord(pageBuffer, 906) + 0.0) * 1.0);
        flexFuel = MSUtils.getBits(pageBuffer, 908, 0, 0, 0);
        fuelFreq0 = (int) ((MSUtils.getByte(pageBuffer, 909) + 0.0) * 1.0);
        fuelFreq1 = (int) ((MSUtils.getByte(pageBuffer, 910) + 0.0) * 1.0);
        fuelCorr0 = (int) ((MSUtils.getByte(pageBuffer, 911) + 0.0) * 1.0);
        fuelCorr1 = (int) ((MSUtils.getByte(pageBuffer, 912) + 0.0) * 1.0);
        dwellmode = MSUtils.getBits(pageBuffer, 913, 0, 1, 0);
        AMCstep = (int) ((MSUtils.getByte(pageBuffer, 914) + 0.0) * 1.0);
        AMCdve = (int) ((MSUtils.getByte(pageBuffer, 915) + 0.0) * 1.0);
        AMCve_rpm = (int) ((MSUtils.getWord(pageBuffer, 916) + 0.0) * 1.0);
        AMCve_map = (double) ((MSUtils.getWord(pageBuffer, 918) + 0.0) * 0.1);
        AMCramve_dt = (int) ((MSUtils.getWord(pageBuffer, 920) + 0.0) * 1.0);
        AMCT_thresh = (int) ((MSUtils.getWord(pageBuffer, 922) + 0.0) * 1.0);
        AMCupdate_thresh = (int) ((MSUtils.getWord(pageBuffer, 924) + 0.0) * 1.0);
        knk_option = MSUtils.getBits(pageBuffer, 927, 0, 1, 0);
        knkDirection = MSUtils.getBits(pageBuffer, 927, 4, 4, 0);
        knk_maxrtd = (double) ((MSUtils.getByte(pageBuffer, 928) + 0.0) * 0.1);
        knk_step1 = (double) ((MSUtils.getByte(pageBuffer, 929) + 0.0) * 0.1);
        knk_step2 = (double) ((MSUtils.getByte(pageBuffer, 930) + 0.0) * 0.1);
        knk_trtd = (double) ((MSUtils.getByte(pageBuffer, 931) + 0.0) * 0.1);
        knk_tadv = (double) ((MSUtils.getByte(pageBuffer, 932) + 0.0) * 0.1);
        knk_dtble_adv = (double) ((MSUtils.getByte(pageBuffer, 933) + 0.0) * 0.1);
        knk_ndet = (int) ((MSUtils.getByte(pageBuffer, 934) + 0.0) * 1.0);
        EAEOption = MSUtils.getBits(pageBuffer, 935, 0, 1, 0);
        knk_maxmap = (double) ((MSUtils.getWord(pageBuffer, 936) + 0.0) * 0.1);
        knk_lorpm = (int) ((MSUtils.getWord(pageBuffer, 938) + 0.0) * 1.0);
        knk_hirpm = (int) ((MSUtils.getWord(pageBuffer, 940) + 0.0) * 1.0);
        triggerTeeth = (int) ((MSUtils.getWord(pageBuffer, 966) + 0.0) * 1.0);
        No_Miss_Teeth = (int) ((MSUtils.getByte(pageBuffer, 968) + 0.0) * 1.0);
        Miss_ang = (int) ((MSUtils.getWord(pageBuffer, 969) + 0.0) * 1.0);
        ICISR_tmask = (double) ((MSUtils.getByte(pageBuffer, 971) + 0.0) * 0.128);
        ICISR_pmask = (int) ((MSUtils.getByte(pageBuffer, 972) + 0.0) * 1.0);
        ae_lorpm = (int) ((MSUtils.getWord(pageBuffer, 974) + 0.0) * 1.0);
        ae_hirpm = (int) ((MSUtils.getWord(pageBuffer, 976) + 0.0) * 1.0);
        fuelSpkDel0 = (double) ((MSUtils.getSignedWord(pageBuffer, 978) + 0.0) * 0.1);
        fuelSpkDel1 = (double) ((MSUtils.getSignedWord(pageBuffer, 980) + 0.0) * 0.1);
        spk_config_spka = MSUtils.getBits(pageBuffer, 988, 0, 0, 0);
        spk_config_camcrank = MSUtils.getBits(pageBuffer, 988, 1, 1, 0);
        spk_config_trig2 = MSUtils.getBits(pageBuffer, 988, 2, 3, 0);
        spk_config_trig2l = MSUtils.getBits(pageBuffer, 988, 4, 5, 0);
        spk_config_sig = MSUtils.getBits(pageBuffer, 988, 7, 7, 0);
        spk_mode0 = MSUtils.getBits(pageBuffer, 989, 0, 2, 0);
        spk_mode3 = MSUtils.getBits(pageBuffer, 989, 3, 4, 0);
        spk_mode5 = MSUtils.getBits(pageBuffer, 989, 5, 5, 0);
        userlevel = (int) ((MSUtils.getByte(pageBuffer, 990) + 0.0) * 1.0);
        userlevelbits = MSUtils.getBits(pageBuffer, 990, 6, 7, 0);
        rtbaroport = MSUtils.getBits(pageBuffer, 991, 0, 3, 0);
        ego2port = MSUtils.getBits(pageBuffer, 992, 0, 3, 0);
        knkport = MSUtils.getBits(pageBuffer, 993, 0, 2, 0);
        flexport = MSUtils.getBits(pageBuffer, 994, 0, 0, 0);
        RevLimcutx = (int) ((MSUtils.getByte(pageBuffer, 995) + 0.0) * 1.0);
        RevLimcuty = (int) ((MSUtils.getByte(pageBuffer, 996) + 0.0) * 1.0);
        feature4_0ftrig = MSUtils.getBits(pageBuffer, 997, 0, 0, 0);
        timing_flags = MSUtils.getBits(pageBuffer, 1018, 0, 0, 0);
        use_prediction = MSUtils.getBits(pageBuffer, 1018, 1, 1, 0);
        crank_dwell = (double) ((MSUtils.getByte(pageBuffer, 1019) + 0.0) * 0.0666);
        crank_timing = (double) ((MSUtils.getSignedWord(pageBuffer, 1020) + 0.0) * 0.1);
        fixed_timing = (double) ((MSUtils.getSignedWord(pageBuffer, 1022) + 0.0) * 0.1);
        pageBuffer = loadPage(2, 0, 1024, null, new byte[] { 114, 0, 5, 0, 0, 4, 0 });
        if (CELSIUS)
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
        if (CELSIUS)
        {
        }
        else
        {
        }
        OvrRunC = MSUtils.getBits(pageBuffer, 1000, 0, 0, 0);
        pwmidlecranking = (int) ((MSUtils.getByte(pageBuffer, 1002) + 0.0) * 1.0);
        pwmidlecranktaper = (int) ((MSUtils.getByte(pageBuffer, 1003) + 0.0) * 1.0);
        pwmidleset_hz = MSUtils.getBits(pageBuffer, 1004, 0, 0, 0);
        pwmidleset_inv = MSUtils.getBits(pageBuffer, 1004, 1, 1, 0);
        fc_rpm = (int) ((MSUtils.getWord(pageBuffer, 1005) + 0.0) * 1.0);
        fc_kpa = (double) ((MSUtils.getSignedWord(pageBuffer, 1007) + 0.0) * 0.1);
        fc_tps = (double) ((MSUtils.getSignedWord(pageBuffer, 1009) + 0.0) * 0.1);
        if (CELSIUS)
        {
            fc_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 1011) + -320.0) * 0.05555);
        }
        else
        {
            fc_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 1011) + 0.0) * 0.1);
        }
        fc_delay = (double) ((MSUtils.getByte(pageBuffer, 1013) + 0.0) * 0.1);
        tacho_opt80 = MSUtils.getBits(pageBuffer, 1014, 7, 7, 0);
        tacho_opt40 = MSUtils.getBits(pageBuffer, 1014, 6, 6, 0);
        tacho_opt3f = MSUtils.getBits(pageBuffer, 1014, 0, 2, 0);
        pageBuffer = loadPage(3, 0, 1024, null, new byte[] { 114, 0, 7, 0, 0, 4, 0 });
        feature3_0 = MSUtils.getBits(pageBuffer, 672, 0, 0, 0);
        launch_opt_on = MSUtils.getBits(pageBuffer, 673, 6, 7, 0);
        launch_opt_pins = MSUtils.getBits(pageBuffer, 673, 0, 3, 0);
        launch_sft_lim = (int) ((MSUtils.getSignedWord(pageBuffer, 674) + 0.0) * 1.0);
        launch_sft_deg = (double) ((MSUtils.getSignedWord(pageBuffer, 676) + 0.0) * 0.1);
        launch_hrd_lim = (int) ((MSUtils.getSignedWord(pageBuffer, 678) + 0.0) * 1.0);
        launch_tps = (double) ((MSUtils.getSignedWord(pageBuffer, 680) + 0.0) * 0.1);
        launchlimopt = MSUtils.getBits(pageBuffer, 682, 0, 1, 0);
        launchcutx = (int) ((MSUtils.getByte(pageBuffer, 683) + 0.0) * 1.0);
        launchcuty = (int) ((MSUtils.getByte(pageBuffer, 684) + 0.0) * 1.0);
        flats_arm = (int) ((MSUtils.getSignedWord(pageBuffer, 685) + 0.0) * 1.0);
        flats_sft = (int) ((MSUtils.getSignedWord(pageBuffer, 687) + 0.0) * 1.0);
        flats_deg = (double) ((MSUtils.getSignedWord(pageBuffer, 689) + 0.0) * 0.1);
        flats_hrd = (int) ((MSUtils.getSignedWord(pageBuffer, 691) + 0.0) * 1.0);
        staged_pri_size = (int) ((MSUtils.getWord(pageBuffer, 693) + 0.0) * 1.0);
        staged_sec_size = (int) ((MSUtils.getWord(pageBuffer, 695) + 0.0) * 1.0);
        staged_first_param = MSUtils.getBits(pageBuffer, 697, 0, 1, 0);
        staged_second_param = MSUtils.getBits(pageBuffer, 697, 2, 3, 0);
        staged_transition_on = MSUtils.getBits(pageBuffer, 697, 4, 4, 0);
        staged_second_logic = MSUtils.getBits(pageBuffer, 697, 5, 5, 0);
        staged_transition_events = (int) ((MSUtils.getByte(pageBuffer, 698) + 0.0) * 1.0);
        staged_param_1 = (int) ((MSUtils.getWord(pageBuffer, 699) + 0.0) * 1.0);
        staged_param_2 = (int) ((MSUtils.getWord(pageBuffer, 701) + 0.0) * 1.0);
        staged_hyst_1 = (int) ((MSUtils.getWord(pageBuffer, 703) + 0.0) * 1.0);
        staged_hyst_2 = (int) ((MSUtils.getWord(pageBuffer, 705) + 0.0) * 1.0);
        iacpostest = (int) ((MSUtils.getWord(pageBuffer, 767) + 0.0) * 1.0);
        EAEmapdotmax = (double) ((MSUtils.getWord(pageBuffer, 805) + 0.0) * 0.1);
        RotarySplitModeFD = MSUtils.getBits(pageBuffer, 967, 0, 0, 0);
        RotarySplitModeNeg = MSUtils.getBits(pageBuffer, 967, 1, 1, 0);
        NoiseFilterOpts = MSUtils.getBits(pageBuffer, 984, 0, 0, 0);
        pageBuffer = loadPage(4, 0, 1024, null, new byte[] { 114, 0, 8, 0, 0, 4, 0 });
        testmodelock = (int) ((MSUtils.getWord(pageBuffer, 0) + 0.0) * 1.0);
        testop_coil = MSUtils.getBits(pageBuffer, 2, 0, 1, 0);
        testop_fp = MSUtils.getBits(pageBuffer, 2, 4, 4, 0);
        testop_inj = MSUtils.getBits(pageBuffer, 2, 5, 6, 0);
        testdwell = (double) ((MSUtils.getByte(pageBuffer, 3) + 0.0) * 0.1);
        testint = (double) ((MSUtils.getWord(pageBuffer, 4) + 0.0) * 0.128);
        testmode = MSUtils.getBits(pageBuffer, 6, 0, 1, 0);
        testpw = (double) ((MSUtils.getWord(pageBuffer, 7) + 0.0) * 6.66E-4);
        testinjcnt = (int) ((MSUtils.getWord(pageBuffer, 9) + 0.0) * 1.0);
    }

}
