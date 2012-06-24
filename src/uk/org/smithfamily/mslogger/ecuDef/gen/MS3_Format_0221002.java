package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.MSUtils;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
/*
Fingerprint : e346026b1b7e52c09029675a20b1adcb
*/
public class MS3_Format_0221002 extends Megasquirt
{
    public MS3_Format_0221002(Context c)
    {
        super(c);
        refreshFlags();
    }
    @Override
    public void refreshFlags()
    {
        NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
        CYL_12_16_SUPPORT = isSet("CYL_12_16_SUPPORT");
        MPH = isSet("MPH");
        INTERNAL_LOG_FIELDS = isSet("INTERNAL_LOG_FIELDS");
        CELSIUS = isSet("CELSIUS");
        CAN_COMMANDS = isSet("CAN_COMMANDS");
        PW_4X = isSet("PW_4X");
        LAMBDA = isSet("LAMBDA");
        USE_CRC_DATA_CHECK = isSet("USE_CRC_DATA_CHECK");
        METRES = isSet("METRES");
        EXPANDED_CLT_TEMP = isSet("EXPANDED_CLT_TEMP");
    }
    byte[] queryCommand=new byte[]{'Q'};
    String signature="MS3 Format 0221.002\0";
    byte [] ochGetCommand = new byte[]{'A'};
    int ochBlockSize = 376;
    private Set<String> sigs = new HashSet<String>(Arrays.asList(new String[] { signature }));
//Flags
    public boolean NARROW_BAND_EGO;
    public boolean CYL_12_16_SUPPORT;
    public boolean MPH;
    public boolean INTERNAL_LOG_FIELDS;
    public boolean CELSIUS;
    public boolean CAN_COMMANDS;
    public boolean PW_4X;
    public boolean LAMBDA;
    public boolean USE_CRC_DATA_CHECK;
    public boolean METRES;
    public boolean EXPANDED_CLT_TEMP;
//Defaults
//Variables
    public int EAEFuelCorr1;
    public int EAEFuelCorr2;
    public double accDecEnrich;
    public double accelEnrich;
    public double accelx;
    public double accely;
    public double accelz;
    public int adc6;
    public int adc7;
    public double advance;
    public double afr1;
    public double afr10;
    public double afr11;
    public double afr12;
    public double afr13;
    public double afr14;
    public double afr15;
    public double afr16;
    public double afr1_old;
    public double afr1err;
    public double afr2;
    public double afr2_old;
    public double afr2err;
    public double afr3;
    public double afr4;
    public double afr5;
    public double afr6;
    public double afr7;
    public double afr8;
    public double afr9;
    public double afrload1;
    public double afrtgt1;
    public double afrtgt1raw;
    public double afrtgt2;
    public double afrtgt2raw;
    public double airCorrection;
    public int altDiv1;
    public int altDiv2;
    public double baroCorrection;
    public double barometer;
    public double batteryVoltage;
    public double boostbar;
    public int boostduty;
    public int boostduty2;
    public double boostpsig;
    public int canin1_8;
    public int canout1_8;
    public int canout9_16;
    public int canpwmin0;
    public int canpwmin1;
    public int canpwmin2;
    public int canpwmin3;
    public double coldAdvDeg;
    public double coolant;
    public int crank;
    public double cycleTime1;
    public double cycleTime2;
    public int deadValue;
    public int df_press;
    public int df_temp;
    public double dutyCycle1;
    public double dutyCycle2;
    public int duty_pwm_a;
    public int duty_pwm_b;
    public int duty_pwm_c;
    public int duty_pwm_d;
    public int duty_pwm_e;
    public int duty_pwm_f;
    public double dwell;
    public double dwell_trl;
    public double eaeload1;
    public double egoCorrection;
    public double egoCorrection1;
    public double egoCorrection2;
    public double egoV;
    public double egoV2;
    public double egoVoltage;
    public double egocor1;
    public double egocor10;
    public double egocor11;
    public double egocor12;
    public double egocor13;
    public double egocor14;
    public double egocor15;
    public double egocor16;
    public double egocor2;
    public double egocor3;
    public double egocor4;
    public double egocor5;
    public double egocor6;
    public double egocor7;
    public double egocor8;
    public double egocor9;
    public double egov1;
    public double egov10;
    public double egov11;
    public double egov12;
    public double egov13;
    public double egov14;
    public double egov15;
    public double egov16;
    public double egov2;
    public double egov3;
    public double egov4;
    public double egov5;
    public double egov6;
    public double egov7;
    public double egov8;
    public double egov9;
    public double egt1;
    public double egt10;
    public double egt11;
    public double egt12;
    public double egt13;
    public double egt14;
    public double egt15;
    public double egt16;
    public double egt2;
    public double egt3;
    public double egt4;
    public double egt5;
    public double egt6;
    public double egt7;
    public double egt8;
    public double egt9;
    public int engine;
    public int firing1;
    public int firing2;
    public double fuelComposition;
    public int fuelCorrection;
    public double fuelload;
    public double fuelload2;
    public int gammaEnrich;
    public int gear;
    public int iacstep;
    public double idleDC;
    public double ignload;
    public double ignload2;
    public int inj1;
    public int inj2;
    public double inj_timing_pri;
    public double inj_timing_sec;
    public double knock;
    public double knockRetard;
    public double lambda1;
    public double lambda2;
    public double lambda3;
    public double lambda4;
    public double lambda5;
    public double lambda6;
    public double lambda7;
    public double lambda8;
    public int looptime;
    public double maf;
    public double mafmap;
    public double map;
    public int mapDOT;
    public int mapaccaen;
    public int mapaccden;
    public double mat;
    public double n2o_addfuel;
    public double n2o_retard;
    public int nSquirts1;
    public int nSquirts2;
    public int nitrous1_duty;
    public int nitrous2_duty;
    public double nitrous_timer;
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
    public double pwma_load;
    public double pwmb_load;
    public double pwmc_load;
    public double pwmd_load;
    public double pwme_load;
    public double pwmf_load;
    public double pwseq1;
    public double pwseq10;
    public double pwseq11;
    public double pwseq12;
    public double pwseq13;
    public double pwseq14;
    public double pwseq15;
    public double pwseq16;
    public double pwseq2;
    public double pwseq3;
    public double pwseq4;
    public double pwseq5;
    public double pwseq6;
    public double pwseq7;
    public double pwseq8;
    public double pwseq9;
    public int ready;
    public int rpm;
    public double rpm100;
    public double rpmdot;
    public int sched1;
    public int sched2;
    public int sd_error;
    public int sd_filenum;
    public int sd_phase;
    public int sd_status;
    public int secl;
    public int seconds;
    public double sensor01;
    public double sensor02;
    public double sensor03;
    public double sensor04;
    public double sensor05;
    public double sensor06;
    public double sensor07;
    public double sensor08;
    public double sensor09;
    public double sensor10;
    public double sensor11;
    public double sensor12;
    public double sensor13;
    public double sensor14;
    public double sensor15;
    public double sensor16;
    public int squirt;
    public int ss1;
    public int ss2;
    public int startw;
    public int status1;
    public int status2;
    public int status3;
    public int status4;
    public int status5;
    public int status6;
    public int status7;
    public int stream_level;
    public int synccnt;
    public int syncreason;
    public double throttle;
    public double time;
    public int timing_err;
    public double tps;
    public int tpsADC;
    public double tpsDOT;
    public int tpsaccaen;
    public int tpsaccden;
    public int tpsfuelcut;
    public double veCurr;
    public double veCurr1;
    public double veCurr2;
    public double vss1;
    public double vss1dot;
    public double vss2;
    public double vss2dot;
    public double vvt_ang1;
    public double vvt_ang2;
    public double vvt_ang3;
    public double vvt_ang4;
    public double vvt_duty1;
    public double vvt_duty2;
    public double vvt_duty3;
    public double vvt_duty4;
    public double vvt_target1;
    public double vvt_target2;
    public double vvt_target3;
    public double vvt_target4;
    public double wallfuel1;
    public int wallfuel2;
    public int warmup;
    public int warmupEnrich;
    public int water_duty;
    public int wbo2_en1;
    public int wbo2_en2;

//Constants
    public int als_opt_sc;
    public double N2O2PWLo;
    public double vvt_max_ang4;
    public int sensor08LF;
    public int afrload;
    public int MAFOption_f;
    public double pwmidle_rpmdot_disablepid;
    public int n2o1n_pins;
    public int pwmidle_min_steps;
    public int opentime_opt4;
    public int opentime_opt3;
    public double injPwmT2;
    public int opentime_opt6;
    public int can_pwmout_tab;
    public int opentime_opt5;
    public int opentime_opt8;
    public int opentime_opt7;
    public int N2O2Rpm;
    public int IgnAlgorithm2;
    public int eaeload;
    public int MAFOption_t;
    public double idleve_load;
    public int opentime_opt2;
    public int opentime_opt1;
    public int alternate;
    public int boost_ctl_Ki2;
    public double gear4ratio;
    public double IC2ISR_tmask;
    public int testmode_0;
    public int fan_ctl_settings_acfan;
    public double knk_tadv;
    public int opentime_opta;
    public int opentime_optb;
    public int gear_can_offset;
    public int sensor04LF;
    public int shift_cut_on;
    public int sensor05_trans;
    public int RevLimTPSbypassRPM;
    public int opentime_optb_own;
    public double inj2Open6;
    public double inj2Open5;
    public double inj2Open4;
    public double inj2Open3;
    public double inj2Open2;
    public double inj2Open1;
    public int idleadvance_delay;
    public int boost_feats_launch;
    public int fanctl_settings_idleup;
    public double inj2Open7;
    public double launch_sft_deg3;
    public double inj2Open8;
    public int egt16port;
    public double knk_dtble_adv;
    public double shift_cut_add12;
    public int secondtrigopts1;
    public int secondtrigopts3;
    public int secondtrigopts2;
    public int f5_0_tsf;
    public int maxafr_opt1_led;
    public int accZcal1;
    public double N2OPWHi;
    public int accZcal2;
    public int f5_0_tss;
    public int flexFuel;
    public int opentime_optb_pwm;
    public int knk_option;
    public int egt_conf_shutdown;
    public double pwmidle_rpmdot_threshold;
    public int spk_config_trig2l;
    public double shift_cut_add23;
    public int sensor03_source;
    public int tc_opt_bank;
    public double inj2Opena;
    public double inj2Openb;
    public double sensor11_val0;
    public int egoCount;
    public int spk_config_campol;
    public double als_maxtime;
    public double testint;
    public double vvt_max_ang2;
    public double vvt_max_ang3;
    public int poll_tableports;
    public double vvt_max_ang1;
    public double tclu_delay;
    public int sensor12LF;
    public int boost_ctl_Kd2;
    public double als_iac_duty;
    public int water_freq_type;
    public int accYport;
    public int dwellmode;
    public int egomap10t;
    public int flats_arm;
    public int inj2PwmP2;
    public double N2O2PWHi;
    public int boost_vss;
    public double vvt_coldpos1;
    public double vvt_coldpos2;
    public int f5_0_tss_opt;
    public int opentime_opta_pwm;
    public int staged_second_param;
    public int boost_ctl_Kp;
    public int spk_mode3;
    public int sensor13_source;
    public int spk_mode0;
    public int ac_idleup_io_out;
    public double boost_ctl_lowerlimit2;
    public int enable_pollports_digout;
    public double sensor13_val0;
    public double tpsThresh;
    public int n2o2f_pins;
    public int sensor10_trans;
    public double vss1_can_scale;
    public double idleadvance_clt;
    public double N2OPWLo;
    public double boost_vss_tps;
    public int inj2PwmPd;
    public int log_style2_clg;
    public double sensor14_max;
    public int egt14port;
    public double gear1ratio;
    public int sequential;
    public int spk_conf2_gm;
    public int egomap12t;
    public int boost_ctl_Ki;
    public int boost_ctl_Kd;
    public int opentime2_opt16;
    public int opentime2_opt13;
    public int opentime2_opt12;
    public int boost_ctl_pins;
    public int opentime2_opt15;
    public int opentime2_opt14;
    public int N2O2RpmMax;
    public double idleve_clt;
    public int mapport_t;
    public double vvt_hold_duty;
    public int egomap11t;
    public int mapport_f;
    public int opentime2_opt11;
    public double inj2PwmT2;
    public double shift_cut_reshift;
    public double maxafr_en_time;
    public double EGOVtarget;
    public int spk_config_resetcam;
    public int egt6port;
    public int canpwm_pre;
    public int vvt_opt1_int;
    public int launch_var_on;
    public double fan_idleup_vss_offpoint;
    public int loadMult;
    public int vss2_pos;
    public int canadc_tab4;
    public int canadc_tab5;
    public double egt_time;
    public int canadc_tab2;
    public int canadc_tab3;
    public int canadc_tab1;
    public double als_maxclt;
    public double pwmidle_Kd;
    public int UNUSED_1_131;
    public int spk_conf2_dwell;
    public double dwelltime_trl;
    public int matmult;
    public double sensor08_val0;
    public int N2Oopt_3;
    public int N2Oopt_4;
    public double tc_maxvss;
    public int N2Oopt_2;
    public int sensor06LF;
    public int adcLF;
    public int dualfuel_sw2_ob;
    public int canadc_tab6;
    public int ac_idleup_cl_targetadder;
    public double Xknock0;
    public double fanctl_idleup_adder_duty;
    public int testop_fp;
    public double ReqFuel_alt;
    public double pwmidle_Ki;
    public double pwmidle_Kp;
    public int boost_ctl_settings_on2;
    public int als_opt_fuel;
    public double IAC_tinitial_step;
    public double N2OTps;
    public int engineType;
    public double vvt_test_duty;
    public int vvt_opt2_cam2pol;
    public int boost_ctl_pwm_scale;
    public int N2ORpm;
    public int knock_conf_num;
    public int pwmidle_min_rpm;
    public int inj2PwmPd2;
    public int fireb;
    public int firec;
    public int ac_idleup_settings;
    public int fired;
    public int firee;
    public int als_iac_steps;
    public int sensor12_source;
    public int firea;
    public int als_opt_fc;
    public int N2Oopt2_prog_freq;
    public int canpwm_clk;
    public double sensor11_max;
    public int hardware_injij;
    public int fireo;
    public int vvt_cam4tth1;
    public int firen;
    public int vvt_cam4tth2;
    public int firep;
    public int firek;
    public double ac_idleup_cl_lockout_mapadder;
    public double shift_cut_add34;
    public int injPwmPd2;
    public int firej;
    public int firem;
    public int firel;
    public int tsw_pin_s;
    public int fireg;
    public int firef;
    public int firei;
    public int fireh;
    public int vvt_opt2_pid;
    public int mapThresh;
    public int tsw_pin_f;
    public double clt0;
    public int fuelCorr0;
    public int fuelCorr1;
    public int smallpw2_opt1_master;
    public int opt142_rtc;
    public int launch_var_up;
    public int altcrank;
    public int vss2_an;
    public double shift_cut_add45;
    public int sensor10LF;
    public int sensor04_source;
    public int log_style_led;
    public int injPwmPd;
    public int opentime2_optb_pwm;
    public int gear_no;
    public double water_mat;
    public double inj2PwmT;
    public int vss1LF;
    public double water_map;
    public int inj2PwmP;
    public int hardware_fuel;
    public double shift_cut_add56;
    public int sensor06_trans;
    public int boost_ctl_pwm;
    public int sensor15_trans;
    public int egt11port;
    public int injPwmP;
    public int IACcrankpos;
    public int flashlock;
    public int shift_cut_in;
    public int feature3_n2oin;
    public int injPwmP2;
    public int iachometest;
    public double egoLimit;
    public double IACtstep;
    public double injPwmT;
    public int sensor07_trans;
    public double triggerOffset;
    public int egoRPM;
    public int tempUnits;
    public int staged_first_param;
    public int sensor09_source;
    public int egt10port;
    public int knk_pin_out;
    public int tacho_opt80;
    public int N2Oopt2_prog_time;
    public int N2Oopt2_prog;
    public int vvt_opt1_tst;
    public int knock_gain14;
    public int knock_gain13;
    public double IdleHyst;
    public int knock_gain16;
    public int knock_gain15;
    public int knock_gain10;
    public double map_freq1;
    public int knock_gain12;
    public int boost_ctl_pwm_scale2;
    public int knock_gain11;
    public double map_freq0;
    public int water_rpm;
    public double launch_sft_deg;
    public int log_style3_adc;
    public int tsw_pin_afr;
    public double pwmidle_min_duty;
    public int testinjPwmP;
    public double baro_lower;
    public int tclu_gearmin;
    public int IdleCtl_vss;
    public int knock_gain08;
    public int bcormult;
    public double tclu_mapmin;
    public int knock_gain09;
    public int water_pins_pump;
    public int knock_gain06;
    public int knock_gain07;
    public int knock_gain05;
    public int knock_gain04;
    public int knock_gain03;
    public int knock_gain02;
    public int knock_gain01;
    public int opentime2_optb_own;
    public int smallpw2_opt15;
    public int smallpw2_opt16;
    public int smallpw2_opt11;
    public int smallpw2_opt12;
    public int boost_ctl_pins_pwm2;
    public int smallpw2_opt13;
    public int smallpw2_opt14;
    public double sensor15_max;
    public double sensor07_max;
    public int dualfuel_opt_temp;
    public int fc_ego_delay;
    public int dbgtooth;
    public double shift_cut_time;
    public double sensor16_val0;
    public int No_Miss_Teeth;
    public int vvt_opt6_freq;
    public double testpw;
    public double tclu_tpsmin;
    public int can_poll_id_ports;
    public int knk_option_an;
    public int n2o1f_pins;
    public double sensor16_max;
    public double als_minclt;
    public int staged_primary_delay;
    public int timing_flags;
    public int enable_pwmout;
    public double pwmidle_dp_adder;
    public int fanctl_settings_pin;
    public double sensor12_val0;
    public int sensor15_source;
    public int sequential_angle_spec;
    public int idleadv_vsson;
    public int sensor07_source;
    public int canadc_off1;
    public int spk_conf2_ngc;
    public int dualfuel_press_sens;
    public int canadc_off3;
    public int canadc_off2;
    public int spk_conf2_tfi;
    public int canadc_off5;
    public double vss1_an_max;
    public int canadc_off4;
    public int canadc_off6;
    public int vvt_ctl_Kp_exh;
    public double ego0;
    public int staged_extended_opts_simult;
    public double sensor12_max;
    public double sensor04_val0;
    public int mapsample_opt1;
    public int IACcrankxt;
    public int testop_0;
    public int can_poll2_ego;
    public int launch_var_sof;
    public int opentime2_opta;
    public int boost_ctl_openduty;
    public int opentime2_optb;
    public int egt9port;
    public int tclu_opt_gear;
    public int sensor16_source;
    public int opentime_opt1_master;
    public int vvt_opt3_cam1;
    public double floodClear;
    public int fuelFreq0;
    public int egomap8;
    public int vvt_opt3_cam2;
    public int fuelFreq1;
    public int egomap7;
    public int egomap9;
    public int poll_offset_rtc;
    public double maxafr_ret_tps;
    public int tsw_pin_rf;
    public int testop_inj;
    public int egomap2;
    public int egomap1;
    public double fuelSpkDel0;
    public int egomap4;
    public int egomap3;
    public int vvt_opt3_cam3;
    public int egomap6;
    public int vvt_opt3_cam4;
    public double fuelSpkDel1;
    public int egomap5;
    public int pwmidle_close_delay;
    public int staged_pw1_0;
    public int f5_0_tsf_opt;
    public int vvt_opt2_cam4pol;
    public int vvt_ctl_Kd;
    public int vvt_ctl_Ki;
    public int sensor01LF;
    public int tpsProportion;
    public double sensor09_val0;
    public int tsw_pin_ob;
    public double knk_maxrtd;
    public int spk_conf2_dli;
    public int dualfuel_sw2_boosw;
    public int launch_opt_on;
    public int MapThreshXTD2;
    public double testinjPwmT;
    public int vvt_opt4_decode;
    public double N2Odel_flat;
    public int egt_conf_action;
    public int egt2port;
    public int idleve_vsson;
    public int vvt_ctl_Kp;
    public int vvt_ctl_Kd_exh;
    public int als_maxrpm;
    public int crankTolerance;
    public int user_value1;
    public int user_value2;
    public int dualfuel_sw2_crank;
    public double N2O2delay;
    public double fc_delay;
    public int egonum;
    public double als_maxtps;
    public int opentime2_opt8;
    public int pwm_opt_on_a;
    public int opentime2_opt7;
    public int pwm_opt_on_b;
    public int opentime2_opt2;
    public int opentime2_opt1;
    public double fixed_timing;
    public int opentime2_opt6;
    public int pwm_opt_on_c;
    public int opentime2_opt5;
    public int pwm_opt_on_d;
    public int opentime2_opt4;
    public int pwm_opt_on_e;
    public int opentime2_opt3;
    public int pwm_opt_on_f;
    public int N2Oopt_01;
    public double ac_idleup_vss_hyst;
    public int launch_var_low;
    public double stoich;
    public int pwmidlecranktaper;
    public int egomap13t;
    public int water_pins_valve;
    public double tclu_mapmax;
    public int maxafr_opt1_load;
    public int spkout_hi_lo;
    public double gear1v;
    public int opentime2_opt1_master;
    public int egt4port;
    public double launch_tps;
    public int pwmidle_shift_open_time;
    public int hardware_spk;
    public int inj_time_mask;
    public int pwm_opt2_b;
    public int idleve_on;
    public int pwm_opt2_a;
    public int testmodelock;
    public int pwm_opt2_f;
    public int hardware_cam;
    public int pwm_opt2_e;
    public double OverBoostKpa;
    public int pwm_opt2_d;
    public int pwm_opt2_c;
    public int poll_table_rtc;
    public int dualfuel_sw_stoich;
    public int egomap14t;
    public double water_tps;
    public int rtbaroport;
    public int fanctl_settings_on;
    public double N2Oholdon;
    public double gear0v;
    public int boost_ctl_Kp2;
    public int can_poll_digout_offset;
    public double map0;
    public int boost_ctl_closeduty2;
    public int egomult;
    public int NoiseFilterOpts2;
    public int NoiseFilterOpts3;
    public double sensor07_val0;
    public int knk_option_wind;
    public double tclu_tpsmax;
    public int vss1_pwmseq;
    public int can_poll_digin_offset;
    public int egomap15t;
    public double gear3v;
    public int staged_second_logic;
    public int aeEndPW;
    public double tss_kpa;
    public int IC2ISR_pmask;
    public int knk_hirpm;
    public double sensor14_val0;
    public int boost_ctl_openduty2;
    public int gear_port_an;
    public int idleadvance_rpm;
    public double inj2Open11;
    public double inj2Open15;
    public double inj2Open14;
    public double inj2Open13;
    public double inj2Open12;
    public double TPSOXLimit;
    public double inj2Open18;
    public double inj2Open17;
    public double gear2v;
    public double inj2Open16;
    public int egomap16t;
    public double baromax;
    public int vvt_opt5_add2;
    public int vvt_opt5_add1;
    public int iacpostest;
    public int maxafr_opt1_on;
    public int NoiseFilterOpts1;
    public double gear5v;
    public int pulseTolerance;
    public int IdleCtl;
    public int ego2port;
    public int testinjPwmPd;
    public int knkport_an;
    public double sensor01_max;
    public int launch_fcut_rpm;
    public int RevLimNormal1;
    public int RevLimNormal3;
    public int tacho_opt40;
    public int feature4_0igntrig;
    public int tclu_opt_vss;
    public int RevLimNormal2;
    public int sensor02_source;
    public double IACcoldtmp;
    public int poll_tablePWM;
    public double fc_tps;
    public double egtcal_temp0;
    public int loadStoich;
    public double egt_warn;
    public int launchlimopt;
    public int fanctl_opt2_engineoff;
    public double gear4v;
    public int feature3_1;
    public int fc_rpm;
    public int sensor14LF;
    public int pwmidle_dp_adder_steps;
    public double N2O2Angle;
    public int overboostcuty;
    public int overboostcutx;
    public int IACcoldxt;
    public int sensor04_trans;
    public int N2Oopt_pins;
    public double mat0;
    public double sensor04_max;
    public int water_pins_in_shut;
    public int launchcutx;
    public int tacho_opt3f;
    public int launchcuty;
    public int feature3_3;
    public double trigret_ang;
    public int ss1_pwmseq;
    public int feature4_0maxdwl;
    public int airden_scaling;
    public int can_ego_id;
    public int loadCombine;
    public int pwmidle_freq_pin3;
    public double injOpen7;
    public double injOpen8;
    public double injOpen5;
    public int algorithm2;
    public double injOpen6;
    public double injOpen3;
    public int als_out_pin;
    public double injOpen4;
    public double injOpen1;
    public double injOpen2;
    public int smallpw_opt11;
    public int smallpw_opt12;
    public int gear_method;
    public int boost_ctl_sensor2;
    public int smallpw_opt15;
    public int baroCorr;
    public int smallpw_opt16;
    public int smallpw_opt13;
    public int smallpw_opt14;
    public double aeTaperTime;
    public int pwmidle_freq_scale;
    public int egt5port;
    public int vvt_opt5_err;
    public double injOpenb;
    public double injOpena;
    public int RevLimCLTbased;
    public int launch_sft_lim3;
    public double sensor05_val0;
    public int knock_bpass;
    public int ss1LF;
    public double gear6v;
    public int sequential_trim_on_off;
    public int sensor09LF;
    public int boost_ctl_settings_cl;
    public int tclu_enablepin;
    public int sensor14_source;
    public double baro0;
    public int sensor07LF;
    public double ac_idleup_vss_offpoint;
    public int spk_conf2_oddodd;
    public int tpsMin;
    public double sensor09_max;
    public int ac_idleup_adder_steps;
    public int egoType;
    public double maxafr_ret_map;
    public int boost_feats_tsw;
    public int dualfuel_sw2_prime;
    public int testsel_inj;
    public int boost_ctl_pins2;
    public int feature4_0ftrig;
    public int dualfuel_sw_afr;
    public int cltmult;
    public int knkDirection;
    public int sensor03LF;
    public double ac_idleup_tps_hyst;
    public double egoTemp;
    public double tclu_vssmin;
    public double idleadvance_tps;
    public int RevLimNormal2_hyst;
    public int UNUSED_1_911;
    public int vssout_optunits;
    public double gear6ratio;
    public int vvt_opt2_use_hold_intake;
    public double vvt_onoff_ang;
    public int tpsLF;
    public int dualfuel_sw_wue;
    public int accZport;
    public int dualfuel_sec;
    public int mapLF;
    public double boost_ctl_clt_threshold;
    public double vss2_can_scale;
    public int boost_ctl_ms;
    public int ae_lorpm;
    public int staged_sec_size;
    public double fdratio1;
    public int knock_sens08;
    public int knock_sens09;
    public int ac_idleup_delay;
    public int knock_sens06;
    public double shift_cut_delay;
    public int knock_sens07;
    public int knock_sens04;
    public int pwmidle_open_steps;
    public int egoport14;
    public int knock_sens05;
    public int egoport13;
    public int knock_sens02;
    public int OverBoostOption;
    public int enable_pollADC;
    public int egoport16;
    public int knock_sens03;
    public int water_freq;
    public int egoport15;
    public int knock_sens01;
    public int IgnAlgorithm;
    public int vvt_cam2tth1;
    public int egoport10;
    public int egoport11;
    public int log_length;
    public int can_poll_id;
    public int egoport12;
    public double RevLimRtdAng;
    public int vvt_cam2tth2;
    public double als_pausetime;
    public int als_in_pin;
    public int dualfuel_opt_press;
    public int vss1_can_size;
    public int sensor03_trans;
    public int maxafr_en_rpm;
    public int hw_latency;
    public double OverBoostHyst;
    public int boost_launch_duty;
    public double launch_addfuel;
    public int dualfuel_opt_out;
    public int vvt_cam1tth1;
    public int vvt_cam1tth2;
    public int vss1_can_offset;
    public int sensor_temp;
    public int egt13port;
    public int AfrAlgorithm;
    public int vvt_opt2_cam1pol;
    public int feature4_0VEtblsize;
    public int spk_config_trig2;
    public double stoich_alt;
    public double vvt_min_ang3;
    public int log_style2_but;
    public double vvt_min_ang2;
    public double vvt_min_ang4;
    public int can_poll_id_rtc;
    public double sensor05_max;
    public int mycan_id;
    public int flexport;
    public double vvt_min_ang1;
    public int can_pwmout_id;
    public int launch_hrd_lim3;
    public int dualfuel_sw_fuel;
    public int pwm_onabove_d;
    public int pwm_onabove_e;
    public int pwm_onabove_b;
    public int pwm_onabove_c;
    public int log_style2_samp;
    public int pwm_onabove_f;
    public int knock_sens10;
    public int knock_sens11;
    public int knock_sens12;
    public int knock_sens13;
    public int pwm_onabove_a;
    public int knock_sens14;
    public int knkpull;
    public int knock_sens15;
    public int log_style_block;
    public int knock_sens16;
    public int rpmLF;
    public int asTolerance;
    public int egt3port;
    public double ITB_load_mappoint;
    public int vss2_can_offset;
    public double wheeldia1;
    public double wheeldia2;
    public int egoport8;
    public int egoport9;
    public int egoport6;
    public int tc_opt_method;
    public int egoport7;
    public double fan_idleup_vss_hyst;
    public int egoport1;
    public int enable_pollPWM;
    public int egoport4;
    public int sensor05_source;
    public int egoport5;
    public int egoport2;
    public int egoport3;
    public int pwmidle_engage_rpm_adder;
    public double gear3ratio;
    public int testinjcnt;
    public double crank_dwell;
    public int fc_rpm_lower;
    public int accXcal2;
    public int accXcal1;
    public int egt12port;
    public int feature3_tps;
    public int feature3_pw4x;
    public double log_int;
    public int sensor01_trans;
    public int n2o2n_pins;
    public double tc_mintps;
    public int AMCOption;
    public int sensor05LF;
    public int can_ego_offset;
    public int tc_opt_n2o;
    public int tpsMax;
    public int sensor11LF;
    public int accxyzLF;
    public int fanctl_idleup_delay;
    public int RevLimOption_spkcut;
    public int tsw_pin_stoich;
    public int opentime_opt11;
    public int opentime_opt15;
    public int testop_pwm;
    public int opentime_opt14;
    public int opentime_opt13;
    public int flats_sft;
    public int opentime_opt12;
    public int tc_opt_on;
    public int algorithm;
    public double mapmax;
    public int staged_param_1;
    public int staged_param_2;
    public int RevLimcutx;
    public int shift_cut_gear;
    public int opentime_opt16;
    public double knk_maxmap;
    public double ITB_load_idletpsthresh;
    public int RotarySplitModeFD;
    public int RevLimcuty;
    public double batt0;
    public int maxafr_ret_rpm;
    public double knk_trtd;
    public int fan_idleup_cl_targetadder;
    public double N2Odel_launch;
    public int spk_mode3_trim;
    public int boost_ctl_settings_invert;
    public int nInjectors;
    public int sensor08_source;
    public int egt1port;
    public double max_coil_dur;
    public double crank_timing;
    public double tc_minvss;
    public double dwellduty;
    public int sensor11_trans;
    public int egt_num;
    public int launch_opt_pins;
    public int vvt_cam3tth2;
    public int vvt_cam3tth1;
    public int dualfuel_sw2_smpw;
    public double N2OClt;
    public double staged_secondary_enrichment;
    public int ego_startdelay;
    public double N2OAngle;
    public int EAElagthresh;
    public int trig_init;
    public double sensor02_val0;
    public int accXport;
    public int tclu_brakepin;
    public int feature3_matase;
    public int OvrRunC;
    public double ac_idleup_tps_offpoint;
    public int testop_coil;
    public int canpwm_div;
    public int sensor01_source;
    public int idleve_rpm;
    public int boost_ctl_settings_cl2;
    public int als_pwm_duty;
    public int boost_ctl_pins_pwm;
    public int vss2LF;
    public double shift_cut_soldelay;
    public int vss2_can_size;
    public double egt_addfuel;
    public int pwm_opt_freq_f;
    public int pwm_opt_freq_d;
    public int RevLimRpm2;
    public int pwm_opt_freq_e;
    public int shift_cut_out;
    public int pwm_opt_freq_b;
    public double vssout_scale;
    public int pwm_opt_freq_c;
    public int reluctorteeth4;
    public double ff_tpw0;
    public double ff_tpw1;
    public int vss1_an;
    public int reluctorteeth1;
    public int reluctorteeth2;
    public int reluctorteeth3;
    public int enable_pollports_digin;
    public double ego_upper_bound;
    public int taeColdM;
    public int knock_conf_percyl;
    public double flats_deg;
    public int dualfuel_pin;
    public int tc_slipthresh;
    public int use_prediction;
    public double dwellAcc;
    public int shift_cut_auto;
    public int shift_cut_rpm45;
    public int taeColdA;
    public int sensor02_trans;
    public int tsf_rpm;
    public int vssdotLF;
    public int egomap4t;
    public double Miss_ang;
    public int egomap3t;
    public int opentime2_opta_pwm;
    public int pwm_opt_freq_a;
    public int pwmidleset_inv;
    public int crankingRPM;
    public int staged_transition_events;
    public int oldegoLimit;
    public int sensor06_source;
    public int shift_cut_rpm34;
    public int sensor08_trans;
    public double MAPOXLimit;
    public int EAElagsource;
    public double tsf_tps;
    public int EAElagRPMmax;
    public double TpsBypassCLTRevlim;
    public int vvt_opt2_use_hold_exhaust;
    public double idleadvance_load;
    public double max_spk_dur;
    public int pwm_offbelow_f;
    public int pwm_offbelow_e;
    public int pwm_offbelow_d;
    public int N2ORpmMax;
    public int als_opt_idle;
    public double gear5ratio;
    public double sensor06_max;
    public double fan_idleup_tps_offpoint;
    public int tc_enin;
    public double dwelltime;
    public int shift_cut_rpm23;
    public int egt8port;
    public int vvt_opt1_on;
    public double OddFireang;
    public int egomap6t;
    public double gear2ratio;
    public int ss_opt2;
    public double vss2_an_max;
    public int ss_opt1;
    public double injOpen15;
    public double injOpen14;
    public double injOpen13;
    public double injOpen12;
    public int vssout_opt;
    public double injOpen18;
    public int NoiseFilterOpts;
    public double fastIdleT;
    public double injOpen17;
    public double injOpen16;
    public int testmode;
    public int staged_hyst_2;
    public int enginesize;
    public double injOpen11;
    public int dualfuel_sw_on;
    public int shift_cut_rpm12;
    public int staged_hyst_1;
    public int pwm_offbelow_a;
    public int egomap5t;
    public int pwm_offbelow_b;
    public int pwm_offbelow_c;
    public double fanctl_offtemp;
    public int sensor09_trans;
    public double fc_kpa;
    public int als_minrpm;
    public int dualfuel_sw2_injp;
    public int pwmidle_targ_ramptime;
    public int egomap8t;
    public int egoAlgorithm;
    public int knock_int;
    public int dualfuel_sw_rf;
    public int ac_idleup_io_in;
    public double als_mintps;
    public int RevLimOption_retard;
    public int ICISR_pmask;
    public int dlyct;
    public double fanctl_ontemp;
    public int sensor12_trans;
    public int testsel_coil;
    public int feature4_0mindwl;
    public int staged_extended_opts_use_v3;
    public double OverBoostKpa2;
    public double sensor13_max;
    public int egomap7t;
    public int vvt_ctl_ms;
    public double sensor02_max;
    public int vss1_can_id;
    public double sensor03_val0;
    public double boost_ctl_lowerlimit;
    public double ac_idleup_adder_duty;
    public double shift_cut_tps;
    public double sensor10_val0;
    public double reqFuel;
    public int pwmidle_ms;
    public int staged_extended_opts_pw1off;
    public double sensor10_max;
    public int RotarySplitModeNeg;
    public int sensor02LF;
    public int nCylinders;
    public double ego_lower_bound;
    public double sensor06_val0;
    public int egt_conf_bank;
    public int pwm_opt_var_f;
    public int pwm_opt_var_e;
    public double battmax;
    public double sensor08_max;
    public int tss_rpm;
    public double map_sample_duration;
    public int pwm_opt_var_b;
    public int pwm_opt_var_a;
    public int pwm_opt_var_d;
    public int vvt_opt1_dir_intake;
    public int pwm_opt_var_c;
    public int dualfuel_sw_spk;
    public int EAEOption;
    public int tclu_outpin;
    public int can_pwmout_offset;
    public int als_opt_pwmout;
    public int egomap9t;
    public int knk_lorpm;
    public int pwmidle_shift_lower_rpm;
    public int ss2LF;
    public int staged_pri_size;
    public int egt15port;
    public int ss2_pwmseq;
    public int idleve_delay;
    public int smallpw_optb;
    public int smallpw2_opta;
    public int smallpw2_optb;
    public int dualfuel_pri;
    public int sensor13_trans;
    public int accYcal1;
    public int user_conf1;
    public double sensor15_val0;
    public int user_conf0;
    public int accYcal2;
    public double RevLimMaxRtd;
    public int spk_conf2_cam;
    public int spk_config_camcrank;
    public int launch_sft_lim;
    public int egoDelta;
    public int sensor15LF;
    public int egoKI;
    public int vvt_out3;
    public int vvt_out4;
    public int divider;
    public int egoKD;
    public double maxafr_spkcut_time;
    public int sensor13LF;
    public int vvt_out2;
    public int vvt_out1;
    public int launch_3step_in;
    public int sensor11_source;
    public double pwmidle_tps_threshold;
    public int knk_ndet;
    public int knkport;
    public int smallpw_opta;
    public int bcor0;
    public int feature3_dwell;
    public int egoKP;
    public int sensor16LF;
    public int staged_transition_on;
    public int vvt_opt1_dir_exhaust;
    public int mapport;
    public int MapThreshXTD;
    public double fc_clt;
    public int egoLF;
    public int tdePct;
    public int smallpw_opt1;
    public int smallpw2_opt1;
    public int smallpw_opt3;
    public int IACcoldpos;
    public int smallpw_opt2;
    public int boost_gear_switch;
    public int smallpw2_opt3;
    public int smallpw_opt5;
    public int smallpw2_opt2;
    public int smallpw_opt4;
    public double egt_max;
    public int smallpw2_opt5;
    public int smallpw_opt7;
    public int smallpw2_opt4;
    public int smallpw_opt6;
    public int smallpw2_opt7;
    public double taeTime;
    public int smallpw2_opt6;
    public int smallpw_opt8;
    public int launch_hrd_lim;
    public double pwmidle_closed_duty;
    public double testdwell;
    public int vvt_tth2;
    public int pwmidle_pid_wait_timer;
    public int vvt_tth1;
    public double baro_default;
    public int vvt_tth4;
    public int vvt_tth3;
    public int smallpw2_opt8;
    public int fanctl_idleup_adder_steps;
    public int iactestlock;
    public int IACStart;
    public int boost_ctl_settings_freq;
    public int no_skip_pulses;
    public double vvt_hold_duty_exh;
    public int dualfuel_opt_mode;
    public int injType;
    public int boost_feats_timed;
    public double sensor03_max;
    public double ICISR_tmask;
    public int canadc_opt5;
    public int canadc_opt4;
    public int canadc_opt6;
    public int vss_opt2;
    public int pwmidle_max_rpm;
    public int iactest;
    public int vss_opt1;
    public int idleadvance_on;
    public double egtcal_tempmax;
    public double pwmidle_decelload_threshold;
    public int dualfuel_temp_sens;
    public int IACminstep;
    public int log_style_on;
    public double tsf_kpa;
    public int boost_ctl_closeduty;
    public double pwmidle_open_duty;
    public int canadc_opt1;
    public int canadc_opt2;
    public int canadc_opt3;
    public int MAFOption;
    public int pwm_opt_load_f;
    public int pwm_opt_load_e;
    public int egomap10;
    public int pwm_opt_load_d;
    public int egomap11;
    public double maf_freq0;
    public int twoStroke;
    public int mapsample_percent;
    public int vvt_opt2_cam3pol;
    public double idleve_tps;
    public double maf_freq1;
    public double MAPOXMin;
    public int pwm_opt_load_b;
    public int egomap13;
    public int pwm_opt_load_c;
    public int egomap12;
    public int egomap15;
    public int pwm_opt_load_a;
    public int egomap14;
    public int egomap16;
    public int flats_hrd;
    public double Xknockmax;
    public int boost_ctl_settings_on;
    public int water_freq_on;
    public int vvt_opt5_vvt1;
    public int ae_hirpm;
    public int TC5_required_width;
    public double baro_upper;
    public int sensor14_trans;
    public int ICIgnCapture;
    public int vss1_pos;
    public int fueltemp1;
    public int secondtrigopts;
    public double tss_tps;
    public int vvt_opt5_vvt4;
    public int vvt_opt5_vvt2;
    public int vvt_opt5_vvt3;
    public int vss1_can_table;
    public int egomap2t;
    public double fan_idleup_tps_hyst;
    public int sensor10_source;
    public int dualfuel_sw_ase;
    public double vssdot_int;
    public int pwmidle_closed_steps;
    public int can_ego_table;
    public int triggerTeeth;
    public int shift_cut_rpm56;
    public int vvt_ctl_Ki_exh;
    public int vss2_pwmseq;
    public int canadc_id3;
    public int egt7port;
    public int canadc_id2;
    public int smallpw_opt1_master;
    public int canadc_id5;
    public int shift_cut_rpm;
    public int canadc_id4;
    public double maxafr_en_load;
    public double als_maxmat;
    public int sensor16_trans;
    public int canadc_id6;
    public int launch_opt_bank;
    public int pwmidle_freq_pin;
    public double ff_temp0;
    public double knk_step2;
    public int poll_offsetPWM;
    public double sensor01_val0;
    public double knk_step1;
    public double ff_temp1;
    public int canadc_id1;
    public int egomap1t;


    private String[] defaultGauges = {
        "tachometer",
        "throttleGauge",
        "pulseWidth1Gauge",
        "cltGauge",
        "advdegGauge",
        "fuelloadGauge",
        "egoGauge",
        "lambda1Gauge",
        "afr1Gauge",
        "matGauge"
    };
	@Override
	public void calculate(byte[] ochBuffer) throws IOException
    {
        deadValue = (0);
        if (CAN_COMMANDS)
        {
        }
        else
        {
        }
        seconds = (int)((MSUtils.getWord(ochBuffer,0) + 0.0) * 1.000);
        secl = (seconds % 256);
        if (PW_4X)
        {
        pulseWidth1 = (double)((MSUtils.getWord(ochBuffer,2) + 0.0) * 0.004);
        pulseWidth2 = (double)((MSUtils.getWord(ochBuffer,4) + 0.0) * 0.004);
        }
        else
        {
        pulseWidth1 = (double)((MSUtils.getWord(ochBuffer,2) + 0.0) * 0.001);
        pulseWidth2 = (double)((MSUtils.getWord(ochBuffer,4) + 0.0) * 0.001);
        }
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
        tpsaccaen = MSUtils.getBits(ochBuffer,11,4,4,0);
        tpsaccden = MSUtils.getBits(ochBuffer,11,5,5,0);
        mapaccaen = MSUtils.getBits(ochBuffer,11,6,6,0);
        mapaccden = MSUtils.getBits(ochBuffer,11,7,7,0);
        if (NARROW_BAND_EGO)
        {
        afrtgt1 = (double)((MSUtils.getByte(ochBuffer,12) + 0.0) * 0.00489);
        afrtgt2 = (double)((MSUtils.getByte(ochBuffer,13) + 0.0) * 0.00489);
        }
        else if (LAMBDA)
        {
        afrtgt1raw = (double)((MSUtils.getByte(ochBuffer,12) + 0.0) * 0.1);
        afrtgt2raw = (double)((MSUtils.getByte(ochBuffer,13) + 0.0) * 0.1);
        }
        else
        {
        afrtgt1 = (double)((MSUtils.getByte(ochBuffer,12) + 0.0) * 0.1);
        afrtgt2 = (double)((MSUtils.getByte(ochBuffer,13) + 0.0) * 0.1);
        }
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
        throttle = (tps);
        batteryVoltage = (double)((MSUtils.getSignedWord(ochBuffer,26) + 0.0) * 0.100);
        afr1_old = (double)((MSUtils.getSignedWord(ochBuffer,28) + 0.0) * 0.100);
        afr2_old = (double)((MSUtils.getSignedWord(ochBuffer,30) + 0.0) * 0.100);
        knock = (double)((MSUtils.getSignedWord(ochBuffer,32) + 0.0) * 0.100);
        egoCorrection1 = (double)((MSUtils.getSignedWord(ochBuffer,34) + 0.0) * 0.1000);
        egoCorrection = (( egoCorrection1 + egoCorrection2) / 2);
        egoCorrection2 = (double)((MSUtils.getSignedWord(ochBuffer,36) + 0.0) * 0.1000);
        airCorrection = (double)((MSUtils.getSignedWord(ochBuffer,38) + 0.0) * 0.1000);
        warmupEnrich = (int)((MSUtils.getSignedWord(ochBuffer,40) + 0.0) * 1.000);
        accelEnrich = (double)((MSUtils.getSignedWord(ochBuffer,42) + 0.0) * 0.100);
        tpsfuelcut = (int)((MSUtils.getSignedWord(ochBuffer,44) + 0.0) * 1.000);
        baroCorrection = (double)((MSUtils.getSignedWord(ochBuffer,46) + 0.0) * 0.1000);
        gammaEnrich = (int)((MSUtils.getSignedWord(ochBuffer,48) + 0.0) * 1.000);
        veCurr1 = (double)((MSUtils.getSignedWord(ochBuffer,50) + 0.0) * 0.1000);
        veCurr2 = (double)((MSUtils.getSignedWord(ochBuffer,52) + 0.0) * 0.1000);
        veCurr = (veCurr1);
        iacstep = (int)((MSUtils.getSignedWord(ochBuffer,54) + 0.0) * 1.000);
        idleDC = (double)((MSUtils.getSignedWord(ochBuffer,54) + 0.0) * 0.39063);
        coldAdvDeg = (double)((MSUtils.getSignedWord(ochBuffer,56) + 0.0) * 0.100);
        tpsDOT = (double)((MSUtils.getSignedWord(ochBuffer,58) + 0.0) * 0.100);
        mapDOT = (int)((MSUtils.getSignedWord(ochBuffer,60) + 0.0) * 1.000);
        dwell = (double)((MSUtils.getWord(ochBuffer,62) + 0.0) * 0.1000);
        mafmap = (double)((MSUtils.getSignedWord(ochBuffer,64) + 0.0) * 0.1000);
        fuelload = (double)((MSUtils.getSignedWord(ochBuffer,66) + 0.0) * 0.100);
        fuelCorrection = (int)((MSUtils.getSignedWord(ochBuffer,68) + 0.0) * 1.000);
        portStatus = (int)((MSUtils.getByte(ochBuffer,70) + 0.0) * 1.000);
        port0 = MSUtils.getBits(ochBuffer,70,0,0,0);
        port1 = MSUtils.getBits(ochBuffer,70,1,1,0);
        port2 = MSUtils.getBits(ochBuffer,70,2,2,0);
        port3 = MSUtils.getBits(ochBuffer,70,3,3,0);
        port4 = MSUtils.getBits(ochBuffer,70,4,4,0);
        port5 = MSUtils.getBits(ochBuffer,70,5,5,0);
        port6 = MSUtils.getBits(ochBuffer,70,6,6,0);
        knockRetard = (double)((MSUtils.getByte(ochBuffer,71) + 0.0) * 0.1);
        EAEFuelCorr1 = (int)((MSUtils.getWord(ochBuffer,72) + 0.0) * 1.0);
        egoV = (double)((MSUtils.getSignedWord(ochBuffer,74) + 0.0) * 0.01);
        egoV2 = (double)((MSUtils.getSignedWord(ochBuffer,76) + 0.0) * 0.01);
        status1 = (int)((MSUtils.getByte(ochBuffer,78) + 0.0) * 1.0);
        status2 = (int)((MSUtils.getByte(ochBuffer,79) + 0.0) * 1.0);
        status3 = (int)((MSUtils.getByte(ochBuffer,80) + 0.0) * 1.0);
        status4 = (int)((MSUtils.getByte(ochBuffer,81) + 0.0) * 1.0);
        looptime = (int)((MSUtils.getWord(ochBuffer,82) + 0.0) * 1.0);
        status5 = (int)((MSUtils.getWord(ochBuffer,84) + 0) * 1);
        tpsADC = (int)((MSUtils.getWord(ochBuffer,86) + 0) * 1);
        fuelload2 = (double)((MSUtils.getWord(ochBuffer,88) + 0.0) * 0.100);
        ignload = (double)((MSUtils.getSignedWord(ochBuffer,90) + 0.0) * 0.100);
        ignload2 = (double)((MSUtils.getSignedWord(ochBuffer,92) + 0.0) * 0.100);
        synccnt = (int)((MSUtils.getByte(ochBuffer,94) + 0) * 1);
        timing_err = (int)((MSUtils.getSignedByte(ochBuffer,95) + 0) * 1);
        wallfuel1 = (double)((MSUtils.getLong(ochBuffer,96) + 0.0) * 0.010);
        wallfuel2 = (int)((MSUtils.getLong(ochBuffer,100) + 0.0) * 1.000);
        sensor01 = (double)((MSUtils.getSignedWord(ochBuffer,104) + 0.0) * 0.1000);
        sensor02 = (double)((MSUtils.getSignedWord(ochBuffer,106) + 0.0) * 0.1000);
        sensor03 = (double)((MSUtils.getSignedWord(ochBuffer,108) + 0.0) * 0.1000);
        sensor04 = (double)((MSUtils.getSignedWord(ochBuffer,110) + 0.0) * 0.1000);
        sensor05 = (double)((MSUtils.getSignedWord(ochBuffer,112) + 0.0) * 0.1000);
        sensor06 = (double)((MSUtils.getSignedWord(ochBuffer,114) + 0.0) * 0.1000);
        sensor07 = (double)((MSUtils.getSignedWord(ochBuffer,116) + 0.0) * 0.1000);
        sensor08 = (double)((MSUtils.getSignedWord(ochBuffer,118) + 0.0) * 0.1000);
        sensor09 = (double)((MSUtils.getSignedWord(ochBuffer,120) + 0.0) * 0.1000);
        sensor10 = (double)((MSUtils.getSignedWord(ochBuffer,122) + 0.0) * 0.1000);
        sensor11 = (double)((MSUtils.getSignedWord(ochBuffer,124) + 0.0) * 0.1000);
        sensor12 = (double)((MSUtils.getSignedWord(ochBuffer,126) + 0.0) * 0.1000);
        sensor13 = (double)((MSUtils.getSignedWord(ochBuffer,128) + 0.0) * 0.1000);
        sensor14 = (double)((MSUtils.getSignedWord(ochBuffer,130) + 0.0) * 0.1000);
        sensor15 = (double)((MSUtils.getSignedWord(ochBuffer,132) + 0.0) * 0.1000);
        sensor16 = (double)((MSUtils.getSignedWord(ochBuffer,134) + 0.0) * 0.1000);
        canin1_8 = (int)((MSUtils.getByte(ochBuffer,136) + 0.0) * 1.000);
        canout1_8 = (int)((MSUtils.getByte(ochBuffer,137) + 0.0) * 1.000);
        canout9_16 = (int)((MSUtils.getByte(ochBuffer,138) + 0.0) * 1.000);
        boostduty = (int)((MSUtils.getByte(ochBuffer,139) + 0.0) * 1.0);
        if (PW_4X)
        {
        n2o_addfuel = (double)((MSUtils.getSignedWord(ochBuffer,140) + 0) * 0.004);
        }
        else
        {
        n2o_addfuel = (double)((MSUtils.getSignedWord(ochBuffer,140) + 0) * 0.001);
        }
        n2o_retard = (double)((MSUtils.getSignedWord(ochBuffer,142) + 0) * 0.1);
        if (PW_4X)
        {
        pwseq1 = (double)((MSUtils.getWord(ochBuffer,144) + 0) * 0.004);
        pwseq2 = (double)((MSUtils.getWord(ochBuffer,146) + 0) * 0.004);
        pwseq3 = (double)((MSUtils.getWord(ochBuffer,148) + 0) * 0.004);
        pwseq4 = (double)((MSUtils.getWord(ochBuffer,150) + 0) * 0.004);
        pwseq5 = (double)((MSUtils.getWord(ochBuffer,152) + 0) * 0.004);
        pwseq6 = (double)((MSUtils.getWord(ochBuffer,154) + 0) * 0.004);
        pwseq7 = (double)((MSUtils.getWord(ochBuffer,156) + 0) * 0.004);
        pwseq8 = (double)((MSUtils.getWord(ochBuffer,158) + 0) * 0.004);
        pwseq9 = (double)((MSUtils.getWord(ochBuffer,160) + 0) * 0.004);
        pwseq10 = (double)((MSUtils.getWord(ochBuffer,162) + 0) * 0.004);
        pwseq11 = (double)((MSUtils.getWord(ochBuffer,164) + 0) * 0.004);
        pwseq12 = (double)((MSUtils.getWord(ochBuffer,166) + 0) * 0.004);
        pwseq13 = (double)((MSUtils.getWord(ochBuffer,168) + 0) * 0.004);
        pwseq14 = (double)((MSUtils.getWord(ochBuffer,170) + 0) * 0.004);
        pwseq15 = (double)((MSUtils.getWord(ochBuffer,172) + 0) * 0.004);
        pwseq16 = (double)((MSUtils.getWord(ochBuffer,174) + 0) * 0.004);
        }
        else
        {
        pwseq1 = (double)((MSUtils.getWord(ochBuffer,144) + 0) * 0.001);
        pwseq2 = (double)((MSUtils.getWord(ochBuffer,146) + 0) * 0.001);
        pwseq3 = (double)((MSUtils.getWord(ochBuffer,148) + 0) * 0.001);
        pwseq4 = (double)((MSUtils.getWord(ochBuffer,150) + 0) * 0.001);
        pwseq5 = (double)((MSUtils.getWord(ochBuffer,152) + 0) * 0.001);
        pwseq6 = (double)((MSUtils.getWord(ochBuffer,154) + 0) * 0.001);
        pwseq7 = (double)((MSUtils.getWord(ochBuffer,156) + 0) * 0.001);
        pwseq8 = (double)((MSUtils.getWord(ochBuffer,158) + 0) * 0.001);
        pwseq9 = (double)((MSUtils.getWord(ochBuffer,160) + 0) * 0.001);
        pwseq10 = (double)((MSUtils.getWord(ochBuffer,162) + 0) * 0.001);
        pwseq11 = (double)((MSUtils.getWord(ochBuffer,164) + 0) * 0.001);
        pwseq12 = (double)((MSUtils.getWord(ochBuffer,166) + 0) * 0.001);
        pwseq13 = (double)((MSUtils.getWord(ochBuffer,168) + 0) * 0.001);
        pwseq14 = (double)((MSUtils.getWord(ochBuffer,170) + 0) * 0.001);
        pwseq15 = (double)((MSUtils.getWord(ochBuffer,172) + 0) * 0.001);
        pwseq16 = (double)((MSUtils.getWord(ochBuffer,174) + 0) * 0.001);
        }
        nitrous1_duty = (int)((MSUtils.getByte(ochBuffer,176) + 0) * 1);
        nitrous2_duty = (int)((MSUtils.getByte(ochBuffer,177) + 0) * 1);
        if (CELSIUS)
        {
        egt1 = (double)((MSUtils.getSignedWord(ochBuffer,178) + -320) * 0.05555);
        egt2 = (double)((MSUtils.getSignedWord(ochBuffer,180) + -320) * 0.05555);
        egt3 = (double)((MSUtils.getSignedWord(ochBuffer,182) + -320) * 0.05555);
        egt4 = (double)((MSUtils.getSignedWord(ochBuffer,184) + -320) * 0.05555);
        egt5 = (double)((MSUtils.getSignedWord(ochBuffer,186) + -320) * 0.05555);
        egt6 = (double)((MSUtils.getSignedWord(ochBuffer,188) + -320) * 0.05555);
        egt7 = (double)((MSUtils.getSignedWord(ochBuffer,190) + -320) * 0.05555);
        egt8 = (double)((MSUtils.getSignedWord(ochBuffer,192) + -320) * 0.05555);
        egt9 = (double)((MSUtils.getSignedWord(ochBuffer,194) + -320) * 0.05555);
        egt10 = (double)((MSUtils.getSignedWord(ochBuffer,196) + -320) * 0.05555);
        egt11 = (double)((MSUtils.getSignedWord(ochBuffer,198) + -320) * 0.05555);
        egt12 = (double)((MSUtils.getSignedWord(ochBuffer,200) + -320) * 0.05555);
        egt13 = (double)((MSUtils.getSignedWord(ochBuffer,202) + -320) * 0.05555);
        egt14 = (double)((MSUtils.getSignedWord(ochBuffer,204) + -320) * 0.05555);
        egt15 = (double)((MSUtils.getSignedWord(ochBuffer,206) + -320) * 0.05555);
        egt16 = (double)((MSUtils.getSignedWord(ochBuffer,208) + -320) * 0.05555);
        }
        else
        {
        egt1 = (double)((MSUtils.getSignedWord(ochBuffer,178) + 0) * 0.1);
        egt2 = (double)((MSUtils.getSignedWord(ochBuffer,180) + 0) * 0.1);
        egt3 = (double)((MSUtils.getSignedWord(ochBuffer,182) + 0) * 0.1);
        egt4 = (double)((MSUtils.getSignedWord(ochBuffer,184) + 0) * 0.1);
        egt5 = (double)((MSUtils.getSignedWord(ochBuffer,186) + 0) * 0.1);
        egt6 = (double)((MSUtils.getSignedWord(ochBuffer,188) + 0) * 0.1);
        egt7 = (double)((MSUtils.getSignedWord(ochBuffer,190) + 0) * 0.1);
        egt8 = (double)((MSUtils.getSignedWord(ochBuffer,192) + 0) * 0.1);
        egt9 = (double)((MSUtils.getSignedWord(ochBuffer,194) + 0) * 0.1);
        egt10 = (double)((MSUtils.getSignedWord(ochBuffer,196) + 0) * 0.1);
        egt11 = (double)((MSUtils.getSignedWord(ochBuffer,198) + 0) * 0.1);
        egt12 = (double)((MSUtils.getSignedWord(ochBuffer,200) + 0) * 0.1);
        egt13 = (double)((MSUtils.getSignedWord(ochBuffer,202) + 0) * 0.1);
        egt14 = (double)((MSUtils.getSignedWord(ochBuffer,204) + 0) * 0.1);
        egt15 = (double)((MSUtils.getSignedWord(ochBuffer,206) + 0) * 0.1);
        egt16 = (double)((MSUtils.getSignedWord(ochBuffer,208) + 0) * 0.1);
        }
        maf = (double)((MSUtils.getWord(ochBuffer,210) + 0.0) * 0.010);
        canpwmin0 = (int)((MSUtils.getWord(ochBuffer,212) + 0.0) * 1.000);
        canpwmin1 = (int)((MSUtils.getWord(ochBuffer,214) + 0.0) * 1.000);
        canpwmin2 = (int)((MSUtils.getWord(ochBuffer,216) + 0.0) * 1.000);
        canpwmin3 = (int)((MSUtils.getWord(ochBuffer,218) + 0.0) * 1.000);
        adc6 = (int)((MSUtils.getWord(ochBuffer,220) + 0.0) * 1);
        adc7 = (int)((MSUtils.getWord(ochBuffer,222) + 0.0) * 1);
        EAEFuelCorr2 = (int)((MSUtils.getWord(ochBuffer,224) + 0.0) * 1.0);
        syncreason = (int)((MSUtils.getByte(ochBuffer,226) + 0.0) * 1.0);
        sd_status = (int)((MSUtils.getByte(ochBuffer,227) + 0.0) * 1.0);
        eaeload1 = (double)((MSUtils.getSignedWord(ochBuffer,228) + 0.0) * 0.1000);
        afrload1 = (double)((MSUtils.getSignedWord(ochBuffer,230) + 0.0) * 0.1000);
        gear = (int)((MSUtils.getByte(ochBuffer,232) + 0) * 1);
        status6 = (int)((MSUtils.getByte(ochBuffer,233) + 0.0) * 1.0);
        rpmdot = (double)((MSUtils.getSignedWord(ochBuffer,234) + 0) * 10);
        vss1dot = (double)((MSUtils.getSignedWord(ochBuffer,236) + 0) * 0.1);
        vss2dot = (double)((MSUtils.getSignedWord(ochBuffer,238) + 0) * 0.1);
        accelx = (double)((MSUtils.getSignedWord(ochBuffer,240) + 0) * 0.001);
        accely = (double)((MSUtils.getSignedWord(ochBuffer,242) + 0) * 0.001);
        accelz = (double)((MSUtils.getSignedWord(ochBuffer,244) + 0) * 0.001);
        duty_pwm_a = (int)((MSUtils.getByte(ochBuffer,246) + 0) * 1);
        duty_pwm_b = (int)((MSUtils.getByte(ochBuffer,247) + 0) * 1);
        duty_pwm_c = (int)((MSUtils.getByte(ochBuffer,248) + 0) * 1);
        duty_pwm_d = (int)((MSUtils.getByte(ochBuffer,249) + 0) * 1);
        duty_pwm_e = (int)((MSUtils.getByte(ochBuffer,250) + 0) * 1);
        duty_pwm_f = (int)((MSUtils.getByte(ochBuffer,251) + 0) * 1);
        afr1 = (double)((MSUtils.getByte(ochBuffer,252) + 0.0) * 0.1);
        afr2 = (double)((MSUtils.getByte(ochBuffer,253) + 0.0) * 0.1);
        afr3 = (double)((MSUtils.getByte(ochBuffer,254) + 0.0) * 0.1);
        afr4 = (double)((MSUtils.getByte(ochBuffer,255) + 0.0) * 0.1);
        afr5 = (double)((MSUtils.getByte(ochBuffer,256) + 0.0) * 0.1);
        afr6 = (double)((MSUtils.getByte(ochBuffer,257) + 0.0) * 0.1);
        afr7 = (double)((MSUtils.getByte(ochBuffer,258) + 0.0) * 0.1);
        afr8 = (double)((MSUtils.getByte(ochBuffer,259) + 0.0) * 0.1);
        afr9 = (double)((MSUtils.getByte(ochBuffer,260) + 0.0) * 0.1);
        afr10 = (double)((MSUtils.getByte(ochBuffer,261) + 0.0) * 0.1);
        afr11 = (double)((MSUtils.getByte(ochBuffer,262) + 0.0) * 0.1);
        afr12 = (double)((MSUtils.getByte(ochBuffer,263) + 0.0) * 0.1);
        afr13 = (double)((MSUtils.getByte(ochBuffer,264) + 0.0) * 0.1);
        afr14 = (double)((MSUtils.getByte(ochBuffer,265) + 0.0) * 0.1);
        afr15 = (double)((MSUtils.getByte(ochBuffer,266) + 0.0) * 0.1);
        afr16 = (double)((MSUtils.getByte(ochBuffer,267) + 0.0) * 0.1);
        egov1 = (double)((MSUtils.getWord(ochBuffer,268) + 0.0) * 0.00489);
        egov2 = (double)((MSUtils.getWord(ochBuffer,270) + 0.0) * 0.00489);
        egov3 = (double)((MSUtils.getWord(ochBuffer,272) + 0.0) * 0.00489);
        egov4 = (double)((MSUtils.getWord(ochBuffer,274) + 0.0) * 0.00489);
        egov5 = (double)((MSUtils.getWord(ochBuffer,276) + 0.0) * 0.00489);
        egov6 = (double)((MSUtils.getWord(ochBuffer,278) + 0.0) * 0.00489);
        egov7 = (double)((MSUtils.getWord(ochBuffer,280) + 0.0) * 0.00489);
        egov8 = (double)((MSUtils.getWord(ochBuffer,282) + 0.0) * 0.00489);
        egov9 = (double)((MSUtils.getWord(ochBuffer,284) + 0.0) * 0.00489);
        egov10 = (double)((MSUtils.getWord(ochBuffer,286) + 0.0) * 0.00489);
        egov11 = (double)((MSUtils.getWord(ochBuffer,288) + 0.0) * 0.00489);
        egov12 = (double)((MSUtils.getWord(ochBuffer,290) + 0.0) * 0.00489);
        egov13 = (double)((MSUtils.getWord(ochBuffer,292) + 0.0) * 0.00489);
        egov14 = (double)((MSUtils.getWord(ochBuffer,294) + 0.0) * 0.00489);
        egov15 = (double)((MSUtils.getWord(ochBuffer,296) + 0.0) * 0.00489);
        egov16 = (double)((MSUtils.getWord(ochBuffer,298) + 0.0) * 0.00489);
        egocor1 = (double)((MSUtils.getSignedWord(ochBuffer,300) + 0.0) * 0.1000);
        egocor2 = (double)((MSUtils.getSignedWord(ochBuffer,302) + 0.0) * 0.1000);
        egocor3 = (double)((MSUtils.getSignedWord(ochBuffer,304) + 0.0) * 0.1000);
        egocor4 = (double)((MSUtils.getSignedWord(ochBuffer,306) + 0.0) * 0.1000);
        egocor5 = (double)((MSUtils.getSignedWord(ochBuffer,308) + 0.0) * 0.1000);
        egocor6 = (double)((MSUtils.getSignedWord(ochBuffer,310) + 0.0) * 0.1000);
        egocor7 = (double)((MSUtils.getSignedWord(ochBuffer,312) + 0.0) * 0.1000);
        egocor8 = (double)((MSUtils.getSignedWord(ochBuffer,314) + 0.0) * 0.1000);
        egocor9 = (double)((MSUtils.getSignedWord(ochBuffer,316) + 0.0) * 0.1000);
        egocor10 = (double)((MSUtils.getSignedWord(ochBuffer,318) + 0.0) * 0.1000);
        egocor11 = (double)((MSUtils.getSignedWord(ochBuffer,320) + 0.0) * 0.1000);
        egocor12 = (double)((MSUtils.getSignedWord(ochBuffer,322) + 0.0) * 0.1000);
        egocor13 = (double)((MSUtils.getSignedWord(ochBuffer,324) + 0.0) * 0.1000);
        egocor14 = (double)((MSUtils.getSignedWord(ochBuffer,326) + 0.0) * 0.1000);
        egocor15 = (double)((MSUtils.getSignedWord(ochBuffer,328) + 0.0) * 0.1000);
        egocor16 = (double)((MSUtils.getSignedWord(ochBuffer,330) + 0.0) * 0.1000);
        stream_level = (int)((MSUtils.getByte(ochBuffer,332) + 0) * 1);
        water_duty = (int)((MSUtils.getByte(ochBuffer,333) + 0) * 1);
        dwell_trl = (double)((MSUtils.getWord(ochBuffer,334) + 0.0) * 0.1000);
        if (MPH)
        {
        vss1 = (double)((MSUtils.getSignedWord(ochBuffer,336) + 0.0) * 0.22369);
        vss2 = (double)((MSUtils.getSignedWord(ochBuffer,338) + 0.0) * 0.22369);
        }
        else
        {
        vss1 = (double)((MSUtils.getSignedWord(ochBuffer,336) + 0.0) * 0.3600);
        vss2 = (double)((MSUtils.getSignedWord(ochBuffer,338) + 0.0) * 0.3600);
        }
        ss1 = (int)((MSUtils.getSignedWord(ochBuffer,340) + 0.0) * 1.000);
        ss2 = (int)((MSUtils.getSignedWord(ochBuffer,342) + 0.0) * 1.000);
        nitrous_timer = (double)((MSUtils.getWord(ochBuffer,344) + 0) * 0.001);
        sd_filenum = (int)((MSUtils.getWord(ochBuffer,346) + 0) * 1);
        sd_error = (int)((MSUtils.getByte(ochBuffer,348) + 0) * 1);
        sd_phase = (int)((MSUtils.getByte(ochBuffer,349) + 0) * 1);
        boostduty2 = (int)((MSUtils.getByte(ochBuffer,350) + 0.0) * 1.0);
        status7 = (int)((MSUtils.getByte(ochBuffer,351) + 0.0) * 1.0);
        vvt_ang1 = (double)((MSUtils.getSignedWord(ochBuffer,352) + 0.0) * 0.100);
        vvt_ang2 = (double)((MSUtils.getSignedWord(ochBuffer,354) + 0.0) * 0.100);
        vvt_ang3 = (double)((MSUtils.getSignedWord(ochBuffer,356) + 0.0) * 0.100);
        vvt_ang4 = (double)((MSUtils.getSignedWord(ochBuffer,358) + 0.0) * 0.100);
        inj_timing_pri = (double)((MSUtils.getSignedWord(ochBuffer,360) + 0.0) * 0.100);
        inj_timing_sec = (double)((MSUtils.getSignedWord(ochBuffer,362) + 0.0) * 0.100);
        vvt_target1 = (double)((MSUtils.getSignedWord(ochBuffer,364) + 0.0) * 0.100);
        vvt_target2 = (double)((MSUtils.getSignedWord(ochBuffer,366) + 0.0) * 0.100);
        vvt_target3 = (double)((MSUtils.getSignedWord(ochBuffer,368) + 0.0) * 0.100);
        vvt_target4 = (double)((MSUtils.getSignedWord(ochBuffer,370) + 0.0) * 0.100);
        vvt_duty1 = (double)((MSUtils.getByte(ochBuffer,372) + 0.0) * 0.39063);
        vvt_duty2 = (double)((MSUtils.getByte(ochBuffer,373) + 0.0) * 0.39063);
        vvt_duty3 = (double)((MSUtils.getByte(ochBuffer,374) + 0.0) * 0.39063);
        vvt_duty4 = (double)((MSUtils.getByte(ochBuffer,375) + 0.0) * 0.39063);
        accDecEnrich = (((accelEnrich + (tpsaccden ) != 0 ) ?  tpsfuelcut : 100));
        time = (timeNow());
        rpm100 = (rpm / 100.0);
        altDiv1 = (((alternate ) != 0 ) ?  2 : 1);
        altDiv2 = (((alternate ) != 0 ) ?  2 : 1);
        cycleTime1 = (60000.0 / rpm * (2.0-(twoStroke&1)));
        nSquirts1 = (nCylinders/divider);
        dutyCycle1 = (100.0*nSquirts1/altDiv1*pulseWidth1/cycleTime1);
        cycleTime2 = (60000.0 / rpm * (2.0-(twoStroke&1)));
        nSquirts2 = (nCylinders/divider);
        dutyCycle2 = (100.0*nSquirts2/altDiv2*pulseWidth2/cycleTime2);
        if (NARROW_BAND_EGO)
        {
        egoVoltage = (egoV);
        afr1err = (egov1 - afrtgt1);
        afr2err = (egov2 - afrtgt2);
        }
        else if (LAMBDA)
        {
        lambda1 = (afr1 / stoich);
        lambda2 = (afr2 / stoich);
        lambda3 = (afr3 / stoich);
        lambda4 = (afr4 / stoich);
        lambda5 = (afr5 / stoich);
        lambda6 = (afr6 / stoich);
        lambda7 = (afr7 / stoich);
        lambda8 = (afr8 / stoich);
        egoVoltage = (lambda1);
        afrtgt1 = (afrtgt1raw / stoich * (egoType == 2 ? 1 : 0));
        afrtgt2 = (afrtgt2raw / stoich * (egoType == 2 ? 1 : 0));
        afr1err = ((afr1 - afrtgt1raw) / stoich * (egoType == 2 ? 1 : 0));
        afr2err = ((afr2 - afrtgt2raw) / stoich * (egoType == 2 ? 1 : 0));
        }
        else
        {
        egoVoltage = (afr1);
        afr1err = (afr1 - afrtgt1);
        afr2err = (afr2 - afrtgt2);
        }
        fuelComposition = ((fuelCorrection - 100)*1.587);
        pwma_load = ((map * (pwm_opt_load_a == 1 ? 1 : 0)) + (map*100/(barometer+(100*(barometer==0 ? 1 : 0))) * (pwm_opt_load_a == 2 ? 1 : 0)) + (tps * (pwm_opt_load_a == 3 ? 1 : 0)) + (mafmap * (pwm_opt_load_a == 4 ? 1 : 0)) + (coolant * (pwm_opt_load_a == 5 ? 1 : 0)) + (mat * (pwm_opt_load_a == 7 ? 1 : 0)));
        pwmb_load = ((map * (pwm_opt_load_b == 1 ? 1 : 0)) + (map*100/(barometer+(100*(barometer==0 ? 1 : 0))) * (pwm_opt_load_b == 2 ? 1 : 0)) + (tps * (pwm_opt_load_b == 3 ? 1 : 0)) + (mafmap * (pwm_opt_load_b == 4 ? 1 : 0)) + (coolant * (pwm_opt_load_b == 5 ? 1 : 0)) + (mat * (pwm_opt_load_b == 7 ? 1 : 0)));
        pwmc_load = ((map * (pwm_opt_load_c == 1 ? 1 : 0)) + (map*100/(barometer+(100*(barometer==0 ? 1 : 0))) * (pwm_opt_load_c == 2 ? 1 : 0)) + (tps * (pwm_opt_load_c == 3 ? 1 : 0)) + (mafmap * (pwm_opt_load_c == 4 ? 1 : 0)) + (coolant * (pwm_opt_load_c == 5 ? 1 : 0)) + (mat * (pwm_opt_load_c == 7 ? 1 : 0)));
        pwmd_load = ((map * (pwm_opt_load_d == 1 ? 1 : 0)) + (map*100/(barometer+(100*(barometer==0 ? 1 : 0))) * (pwm_opt_load_d == 2 ? 1 : 0)) + (tps * (pwm_opt_load_d == 3 ? 1 : 0)) + (mafmap * (pwm_opt_load_d == 4 ? 1 : 0)) + (coolant * (pwm_opt_load_d == 5 ? 1 : 0)) + (mat * (pwm_opt_load_d == 7 ? 1 : 0)));
        pwme_load = ((map * (pwm_opt_load_e == 1 ? 1 : 0)) + (map*100/(barometer+(100*(barometer==0 ? 1 : 0))) * (pwm_opt_load_e == 2 ? 1 : 0)) + (tps * (pwm_opt_load_e == 3 ? 1 : 0)) + (mafmap * (pwm_opt_load_e == 4 ? 1 : 0)) + (coolant * (pwm_opt_load_e == 5 ? 1 : 0)) + (mat * (pwm_opt_load_e == 7 ? 1 : 0)));
        pwmf_load = ((map * (pwm_opt_load_f == 1 ? 1 : 0)) + (map*100/(barometer+(100*(barometer==0 ? 1 : 0))) * (pwm_opt_load_f == 2 ? 1 : 0)) + (tps * (pwm_opt_load_f == 3 ? 1 : 0)) + (mafmap * (pwm_opt_load_f == 4 ? 1 : 0)) + (coolant * (pwm_opt_load_f == 5 ? 1 : 0)) + (mat * (pwm_opt_load_f == 7 ? 1 : 0)));
        df_temp = (0);
        df_press = (0);
        boostbar = ((map - barometer) / 101.33);
        boostpsig = ((map - barometer) * 0.1450);
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
        b.append("O2 #2").append("\t");
        b.append("O2 #3").append("\t");
        b.append("O2 #4").append("\t");
        b.append("O2 #5").append("\t");
        b.append("O2 #6").append("\t");
        b.append("O2 #7").append("\t");
        b.append("O2 #8").append("\t");
        }
        else if (LAMBDA)
        {
        b.append("Lambda").append("\t");
        b.append("Lambda2").append("\t");
        b.append("Lambda3").append("\t");
        b.append("Lambda4").append("\t");
        b.append("Lambda5").append("\t");
        b.append("Lambda6").append("\t");
        b.append("Lambda7").append("\t");
        b.append("Lambda8").append("\t");
        }
        else
        {
        b.append("AFR").append("\t");
        b.append("AFR2").append("\t");
        b.append("AFR3").append("\t");
        b.append("AFR4").append("\t");
        b.append("AFR5").append("\t");
        b.append("AFR6").append("\t");
        b.append("AFR7").append("\t");
        b.append("AFR8").append("\t");
        }
        b.append("MAT").append("\t");
        b.append("CLT").append("\t");
        b.append("Engine").append("\t");
        b.append("Batt V").append("\t");
        b.append("egocor_Gego1").append("\t");
        b.append("egocor_Gego2").append("\t");
        b.append("egocor_Gego3").append("\t");
        b.append("egocor_Gego4").append("\t");
        b.append("egocor_Gego5").append("\t");
        b.append("egocor_Gego6").append("\t");
        b.append("egocor_Gego7").append("\t");
        b.append("egocor_Gego8").append("\t");
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
        b.append("Seq PW1").append("\t");
        b.append("Seq PW2").append("\t");
        b.append("Seq PW3").append("\t");
        b.append("Seq PW4").append("\t");
        b.append("Seq PW5").append("\t");
        b.append("Seq PW6").append("\t");
        b.append("Seq PW7").append("\t");
        b.append("Seq PW8").append("\t");
        if (CYL_12_16_SUPPORT)
        {
        b.append("Seq PW9").append("\t");
        b.append("Seq PW10").append("\t");
        b.append("Seq PW11").append("\t");
        b.append("Seq PW12").append("\t");
        b.append("Seq PW13").append("\t");
        b.append("Seq PW14").append("\t");
        b.append("Seq PW15").append("\t");
        b.append("Seq PW16").append("\t");
        }
        b.append("SparkAdv").append("\t");
        b.append("knockRet").append("\t");
        b.append("knockin").append("\t");
        b.append("ColdAdv").append("\t");
        b.append("Dwell").append("\t");
        b.append("IACstep").append("\t");
        b.append("PWM Idle Duty").append("\t");
        b.append("Boost Duty").append("\t");
        if (NARROW_BAND_EGO)
        {
        b.append("EgoV 1 Target").append("\t");
        b.append("EgoV 2 Target").append("\t");
        b.append("EgoV 1 Error").append("\t");
        b.append("EgoV 2 Error").append("\t");
        }
        else if (LAMBDA)
        {
        b.append("Lambda 1 Target").append("\t");
        b.append("Lambda 2 Target").append("\t");
        b.append("Lambda 1 Error").append("\t");
        b.append("Lambda 2 Error").append("\t");
        }
        else
        {
        b.append("AFR 1 Target").append("\t");
        b.append("AFR 2 Target").append("\t");
        b.append("AFR 1 Error").append("\t");
        b.append("AFR 2 Error").append("\t");
        }
        b.append("tpsDOT").append("\t");
        b.append("mapDOT").append("\t");
        b.append("rpmdot").append("\t");
        b.append("WallFuel1").append("\t");
        b.append("WallFuel2").append("\t");
        b.append("EAE1 %").append("\t");
        b.append("EAE2 %").append("\t");
        b.append("Load").append("\t");
        b.append("Secondary Load").append("\t");
        b.append("Ign load").append("\t");
        b.append("Secondary Ign Load").append("\t");
        b.append("EAE load").append("\t");
        b.append("AFR load").append("\t");
        b.append("EGT 1 temp").append("\t");
        b.append("EGT 2 temp").append("\t");
        b.append("EGT 3 temp").append("\t");
        b.append("EGT 4 temp").append("\t");
        b.append("EGT 5 temp").append("\t");
        b.append("EGT 6 temp").append("\t");
        b.append("EGT 7 temp").append("\t");
        b.append("EGT 8 temp").append("\t");
        b.append("EGT 9 temp").append("\t");
        b.append("EGT 10 temp").append("\t");
        b.append("EGT 11 temp").append("\t");
        b.append("EGT 12 temp").append("\t");
        b.append("EGT 13 temp").append("\t");
        b.append("EGT 14 temp").append("\t");
        b.append("EGT 15 temp").append("\t");
        b.append("EGT 16 temp").append("\t");
        b.append("sensor01").append("\t");
        b.append("sensor02").append("\t");
        b.append("sensor03").append("\t");
        b.append("sensor04").append("\t");
        b.append("sensor05").append("\t");
        b.append("sensor06").append("\t");
        b.append("sensor07").append("\t");
        b.append("sensor08").append("\t");
        b.append("sensor09").append("\t");
        b.append("sensor10").append("\t");
        b.append("sensor11").append("\t");
        b.append("sensor12").append("\t");
        b.append("sensor13").append("\t");
        b.append("sensor14").append("\t");
        b.append("sensor15").append("\t");
        b.append("sensor16").append("\t");
        b.append("canpwmin0").append("\t");
        b.append("canpwmin1").append("\t");
        b.append("canpwmin2").append("\t");
        b.append("canpwmin3").append("\t");
        b.append("timing err%").append("\t");
        b.append("Lost sync count").append("\t");
        b.append("Lost sync reason").append("\t");
        if (MPH)
        {
        b.append("mph 1").append("\t");
        b.append("mph 2").append("\t");
        }
        else
        {
        b.append("kph 1").append("\t");
        b.append("kph 2").append("\t");
        }
        b.append("Shaft rpm 1").append("\t");
        b.append("Shaft rpm 2").append("\t");
        b.append("Nitrous added fuel").append("\t");
        b.append("Nitrous timing retard by").append("\t");
        b.append("Nitrous 1 duty").append("\t");
        b.append("Nitrous 2 duty").append("\t");
        b.append("Nitrous Timer").append("\t");
        b.append("SDcard file number").append("\t");
        b.append("SDcard phase").append("\t");
        b.append("SDcard error").append("\t");
        b.append("Gear").append("\t");
        b.append("vss1dot").append("\t");
        b.append("vss2dot").append("\t");
        b.append("accel X").append("\t");
        b.append("accel Y").append("\t");
        b.append("accel Z").append("\t");
        b.append("Generic PWM A duty").append("\t");
        b.append("Generic PWM B duty").append("\t");
        b.append("Generic PWM C duty").append("\t");
        b.append("Generic PWM D duty").append("\t");
        b.append("Generic PWM E duty").append("\t");
        b.append("Generic PWM F duty").append("\t");
        b.append("status1").append("\t");
        b.append("status2").append("\t");
        b.append("status3").append("\t");
        b.append("status4").append("\t");
        b.append("status5").append("\t");
        b.append("VVT angle 1").append("\t");
        b.append("VVT angle 2").append("\t");
        b.append("VVT angle 3").append("\t");
        b.append("VVT angle 4").append("\t");
        b.append("VVT target 1").append("\t");
        b.append("VVT target 2").append("\t");
        b.append("VVT target 3").append("\t");
        b.append("VVT target 4").append("\t");
        b.append("VVT duty 1").append("\t");
        b.append("VVT duty 2").append("\t");
        b.append("VVT duty 3").append("\t");
        b.append("VVT duty 4").append("\t");
        if (INTERNAL_LOG_FIELDS)
        {
        }
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
        b.append(round(egov1)).append("\t");
        b.append(round(egov2)).append("\t");
        b.append(round(egov3)).append("\t");
        b.append(round(egov4)).append("\t");
        b.append(round(egov5)).append("\t");
        b.append(round(egov6)).append("\t");
        b.append(round(egov7)).append("\t");
        b.append(round(egov8)).append("\t");
        }
        else if (LAMBDA)
        {
        b.append(lambda1).append("\t");
        b.append(lambda2).append("\t");
        b.append(lambda3).append("\t");
        b.append(lambda4).append("\t");
        b.append(lambda5).append("\t");
        b.append(lambda6).append("\t");
        b.append(lambda7).append("\t");
        b.append(lambda8).append("\t");
        }
        else
        {
        b.append(round(afr1)).append("\t");
        b.append(round(afr2)).append("\t");
        b.append(round(afr3)).append("\t");
        b.append(round(afr4)).append("\t");
        b.append(round(afr5)).append("\t");
        b.append(round(afr6)).append("\t");
        b.append(round(afr7)).append("\t");
        b.append(round(afr8)).append("\t");
        }
        b.append(round(mat)).append("\t");
        b.append(round(coolant)).append("\t");
        b.append(engine).append("\t");
        b.append(round(batteryVoltage)).append("\t");
        b.append(round(egocor1)).append("\t");
        b.append(round(egocor2)).append("\t");
        b.append(round(egocor3)).append("\t");
        b.append(round(egocor4)).append("\t");
        b.append(round(egocor5)).append("\t");
        b.append(round(egocor6)).append("\t");
        b.append(round(egocor7)).append("\t");
        b.append(round(egocor8)).append("\t");
        b.append(round(airCorrection)).append("\t");
        b.append(warmupEnrich).append("\t");
        b.append(round(baroCorrection)).append("\t");
        b.append(gammaEnrich).append("\t");
        b.append(accDecEnrich).append("\t");
        b.append(round(veCurr1)).append("\t");
        b.append(round(pulseWidth1)).append("\t");
        b.append(dutyCycle1).append("\t");
        b.append(round(veCurr2)).append("\t");
        b.append(round(pulseWidth2)).append("\t");
        b.append(dutyCycle2).append("\t");
        b.append(round(pwseq1)).append("\t");
        b.append(round(pwseq2)).append("\t");
        b.append(round(pwseq3)).append("\t");
        b.append(round(pwseq4)).append("\t");
        b.append(round(pwseq5)).append("\t");
        b.append(round(pwseq6)).append("\t");
        b.append(round(pwseq7)).append("\t");
        b.append(round(pwseq8)).append("\t");
        if (CYL_12_16_SUPPORT)
        {
        b.append(round(pwseq9)).append("\t");
        b.append(round(pwseq10)).append("\t");
        b.append(round(pwseq11)).append("\t");
        b.append(round(pwseq12)).append("\t");
        b.append(round(pwseq13)).append("\t");
        b.append(round(pwseq14)).append("\t");
        b.append(round(pwseq15)).append("\t");
        b.append(round(pwseq16)).append("\t");
        }
        b.append(round(advance)).append("\t");
        b.append(round(knockRetard)).append("\t");
        b.append(round(knock)).append("\t");
        b.append(round(coldAdvDeg)).append("\t");
        b.append(round(dwell)).append("\t");
        b.append(iacstep).append("\t");
        b.append(round(idleDC)).append("\t");
        b.append(boostduty).append("\t");
        if (NARROW_BAND_EGO)
        {
        b.append(round(afrtgt1)).append("\t");
        b.append(round(afrtgt2)).append("\t");
        b.append(afr1err).append("\t");
        b.append(afr2err).append("\t");
        }
        else if (LAMBDA)
        {
        b.append(round(afrtgt1)).append("\t");
        b.append(round(afrtgt2)).append("\t");
        b.append(afr1err).append("\t");
        b.append(afr2err).append("\t");
        }
        else
        {
        b.append(round(afrtgt1)).append("\t");
        b.append(round(afrtgt2)).append("\t");
        b.append(afr1err).append("\t");
        b.append(afr2err).append("\t");
        }
        b.append(round(tpsDOT)).append("\t");
        b.append(mapDOT).append("\t");
        b.append(round(rpmdot)).append("\t");
        b.append(round(wallfuel1)).append("\t");
        b.append(wallfuel2).append("\t");
        b.append(EAEFuelCorr1).append("\t");
        b.append(EAEFuelCorr2).append("\t");
        b.append(round(fuelload)).append("\t");
        b.append(round(fuelload2)).append("\t");
        b.append(round(ignload)).append("\t");
        b.append(round(ignload2)).append("\t");
        b.append(round(eaeload1)).append("\t");
        b.append(round(afrload1)).append("\t");
        b.append(round(egt1)).append("\t");
        b.append(round(egt2)).append("\t");
        b.append(round(egt3)).append("\t");
        b.append(round(egt4)).append("\t");
        b.append(round(egt5)).append("\t");
        b.append(round(egt6)).append("\t");
        b.append(round(egt7)).append("\t");
        b.append(round(egt8)).append("\t");
        b.append(round(egt9)).append("\t");
        b.append(round(egt10)).append("\t");
        b.append(round(egt11)).append("\t");
        b.append(round(egt12)).append("\t");
        b.append(round(egt13)).append("\t");
        b.append(round(egt14)).append("\t");
        b.append(round(egt15)).append("\t");
        b.append(round(egt16)).append("\t");
        b.append(round(sensor01)).append("\t");
        b.append(round(sensor02)).append("\t");
        b.append(round(sensor03)).append("\t");
        b.append(round(sensor04)).append("\t");
        b.append(round(sensor05)).append("\t");
        b.append(round(sensor06)).append("\t");
        b.append(round(sensor07)).append("\t");
        b.append(round(sensor08)).append("\t");
        b.append(round(sensor09)).append("\t");
        b.append(round(sensor10)).append("\t");
        b.append(round(sensor11)).append("\t");
        b.append(round(sensor12)).append("\t");
        b.append(round(sensor13)).append("\t");
        b.append(round(sensor14)).append("\t");
        b.append(round(sensor15)).append("\t");
        b.append(round(sensor16)).append("\t");
        b.append(canpwmin0).append("\t");
        b.append(canpwmin1).append("\t");
        b.append(canpwmin2).append("\t");
        b.append(canpwmin3).append("\t");
        b.append(timing_err).append("\t");
        b.append(synccnt).append("\t");
        b.append(syncreason).append("\t");
        if (MPH)
        {
        b.append(round(vss1)).append("\t");
        b.append(round(vss2)).append("\t");
        }
        else
        {
        b.append(round(vss1)).append("\t");
        b.append(round(vss2)).append("\t");
        }
        b.append(ss1).append("\t");
        b.append(ss2).append("\t");
        b.append(round(n2o_addfuel)).append("\t");
        b.append(round(n2o_retard)).append("\t");
        b.append(nitrous1_duty).append("\t");
        b.append(nitrous2_duty).append("\t");
        b.append(round(nitrous_timer)).append("\t");
        b.append(sd_filenum).append("\t");
        b.append(sd_phase).append("\t");
        b.append(sd_error).append("\t");
        b.append(gear).append("\t");
        b.append(round(vss1dot)).append("\t");
        b.append(round(vss2dot)).append("\t");
        b.append(round(accelx)).append("\t");
        b.append(round(accely)).append("\t");
        b.append(round(accelz)).append("\t");
        b.append(duty_pwm_a).append("\t");
        b.append(duty_pwm_b).append("\t");
        b.append(duty_pwm_c).append("\t");
        b.append(duty_pwm_d).append("\t");
        b.append(duty_pwm_e).append("\t");
        b.append(duty_pwm_f).append("\t");
        b.append(status1).append("\t");
        b.append(status2).append("\t");
        b.append(status3).append("\t");
        b.append(status4).append("\t");
        b.append(status5).append("\t");
        b.append(round(vvt_ang1)).append("\t");
        b.append(round(vvt_ang2)).append("\t");
        b.append(round(vvt_ang3)).append("\t");
        b.append(round(vvt_ang4)).append("\t");
        b.append(round(vvt_target1)).append("\t");
        b.append(round(vvt_target2)).append("\t");
        b.append(round(vvt_target3)).append("\t");
        b.append(round(vvt_target4)).append("\t");
        b.append(round(vvt_duty1)).append("\t");
        b.append(round(vvt_duty2)).append("\t");
        b.append(round(vvt_duty3)).append("\t");
        b.append(round(vvt_duty4)).append("\t");
        if (INTERNAL_LOG_FIELDS)
        {
        }
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth1Gauge","pulseWidth1",pulseWidth1,"Pulse Width 1","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth2Gauge","pulseWidth2",pulseWidth2,"Pulse Width 2","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq1Gauge","pwseq1",pwseq1,"Pulse Width Seq 1","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq2Gauge","pwseq2",pwseq2,"Pulse Width Seq 2","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq3Gauge","pwseq3",pwseq3,"Pulse Width Seq 3","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq4Gauge","pwseq4",pwseq4,"Pulse Width Seq 4","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq5Gauge","pwseq5",pwseq5,"Pulse Width Seq 5","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq6Gauge","pwseq6",pwseq6,"Pulse Width Seq 6","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq7Gauge","pwseq7",pwseq7,"Pulse Width Seq 7","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq8Gauge","pwseq8",pwseq8,"Pulse Width Seq 8","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        if (CYL_12_16_SUPPORT)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq9Gauge","pwseq9",pwseq9,"Pulse Width Seq 9","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq10Gauge","pwseq10",pwseq10,"Pulse Width Seq 10","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq11Gauge","pwseq11",pwseq11,"Pulse Width Seq 11","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq12Gauge","pwseq12",pwseq12,"Pulse Width Seq 12","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq13Gauge","pwseq13",pwseq13,"Pulse Width Seq 13","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq14Gauge","pwseq14",pwseq14,"Pulse Width Seq 14","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq15Gauge","pwseq15",pwseq15,"Pulse Width Seq 15","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq16Gauge","pwseq16",pwseq16,"Pulse Width Seq 16","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq9Gauge","pwseq9",pwseq9,"Pulse Width V3 inj1","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidthSeq10Gauge","pwseq10",pwseq10,"Pulse Width V3 inj2","mSec",0,25.5,1.0,1.2,20,25,3,1,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("IACgauge","iacstep",iacstep,"IAC position","steps",0,255,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dwellGauge","dwell",dwell,"Dwell","mSec",0,10,0.5,1.0,6.0,8.0,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dwelltrlGauge","dwell_trl",dwell_trl,"Dwell (Trailing)","mSec",0,10,0.5,1.0,6.0,8.0,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("PWMIdlegauge","idleDC",idleDC,"Idle PWM%","%",0,100,-1,-1,999,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("advdegGauge","advance",advance,"Ignition Advance","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle1Gauge","dutyCycle1",dutyCycle1,"Duty Cycle 1","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle2Gauge","dutyCycle2",dutyCycle2,"Duty Cycle 2","%",0,100,-1,-1,85,90,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("boostdutyGauge","boostduty",boostduty,"Boost Duty","%",0,100,-1,-1,100,100,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("boostdutyGauge2","boostduty2",boostduty2,"Boost Duty 2","%",0,100,-1,-1,100,100,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("injtimingpriGauge","inj_timing_pri",inj_timing_pri,"Injector timing (pri)","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("injtimingsecGauge","inj_timing_sec",inj_timing_sec,"Injector timing (sec)","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("nitrous_addfuel","n2o_addfuel",n2o_addfuel,"Nitrous added fuel","ms",-5,20,20,20,20,20,3,3,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("nitrous_retard","n2o_retard",n2o_retard,"Nitrous retard","deg",-5,30,30,30,30,30,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("nitrous1_duty","nitrous1_duty",nitrous1_duty,"Nitrous 1 Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("nitrous2_duty","nitrous2_duty",nitrous2_duty,"Nitrous 2 Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("nitrous_timer","nitrous_timer",nitrous_timer,"Nitrous Timer","s",0,15,15,15,15,15,3,3,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("water_duty","water_duty",water_duty,"Water Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("duty_pwm_a","duty_pwm_a",duty_pwm_a,"Generic PWM A Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("duty_pwm_b","duty_pwm_b",duty_pwm_b,"Generic PWM B Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("duty_pwm_c","duty_pwm_c",duty_pwm_c,"Generic PWM C Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("duty_pwm_d","duty_pwm_d",duty_pwm_d,"Generic PWM D Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("duty_pwm_e","duty_pwm_e",duty_pwm_e,"Generic PWM E Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("duty_pwm_f","duty_pwm_f",duty_pwm_f,"Generic PWM F Duty","%",0,100,100,100,100,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvt_duty1","vvt_duty1",vvt_duty1,"VVT 1 duty","%",0,100,100,100,100,100,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvt_duty2","vvt_duty2",vvt_duty2,"VVT 2 duty","%",0,100,100,100,100,100,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvt_duty3","vvt_duty3",vvt_duty3,"VVT 3 duty","%",0,100,100,100,100,100,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvt_duty4","vvt_duty4",vvt_duty4,"VVT 4 duty","%",0,100,100,100,100,100,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelEnrichGauge","accDecEnrich",accDecEnrich,"Accel Enrich","%",50,150,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clockGauge","seconds",seconds,"Clock","Seconds",0,65535,10,10,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaEnrichGauge","gammaEnrich",gammaEnrich,"Gamma Enrichment","%",50,150,-1,-1,151,151,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaairGauge","airCorrection",airCorrection,"Gair/aircor","%",50,150,-1,-1,151,151,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Gwarmgauge","warmupEnrich",warmupEnrich,"Gwarm","%",50,150,-1,-1,151,151,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Gbarogauge","baroCorrection",baroCorrection,"Gbaro","%",50,150,-1,-1,151,151,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("WFGauge1","wallfuel1",wallfuel1,"Fuel on the walls 1","",0,40000000,0,0,40000000,40000000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("WFGauge2","wallfuel2",wallfuel2,"Fuel on the walls 2","",0,40000000,0,0,40000000,40000000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("EAEGauge1","EAEFuelCorr1",EAEFuelCorr1,"EAE Fuel Correction 1","%",0,200,40,70,130,160,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("EAEGauge2","EAEFuelCorr2",EAEFuelCorr2,"EAE Fuel Correction 2","%",0,200,40,70,130,160,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelcorr","fuelCorrection",fuelCorrection,"E85 Fuel Correction","%",100,200,99,99,164,170,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelcompsn","fuelComposition",fuelComposition,"Ethanol Percentage","%",0,100,-1,-1,85,101,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lostsyncGauge","synccnt",synccnt,"Lost sync counter","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("syncreasonGauge","syncreason",syncreason,"Lost sync reason","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge1","veCurr1",veCurr1,"VE Current1","%",0,120,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge2","veCurr2",veCurr2,"VE2 Current","%",0,120,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("warmupEnrichGauge","warmupEnrich",warmupEnrich,"Warmup Enrichment","%",100,150,-1,-1,101,105,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vss1dot","vss1dot",vss1dot,"VSS1 Acceleration","ms-2",-25,25,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vss2dot","vss2dot",vss2dot,"VSS2 Acceleration","ms-2",-25,25,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("rpmdot","rpmdot",rpmdot,"rpmdot","rpm/sec",-15000,15000,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tpsdot","tpsDOT",tpsDOT,"tpsdot","%/sec",-15000,15000,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapdot","mapDOT",mapDOT,"mapdot","kPa/sec",-15000,15000,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvttargGauge1","vvt_target1",vvt_target1,"VVT 1 target","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvttargGauge2","vvt_target2",vvt_target2,"VVT 2 target","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvttargGauge3","vvt_target3",vvt_target3,"VVT 3 target","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvttargGauge4","vvt_target4",vvt_target4,"VVT 4 target","degrees",0,50,-1,-1,999,999,1,1,45));
        if (CELSIUS)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge1","egt1",egt1,"EGT 01","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge2","egt2",egt2,"EGT 02","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge3","egt3",egt3,"EGT 03","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge4","egt4",egt4,"EGT 04","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge5","egt5",egt5,"EGT 05","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge6","egt6",egt6,"EGT 06","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge7","egt7",egt7,"EGT 07","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge8","egt8",egt8,"EGT 08","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge9","egt9",egt9,"EGT 09","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge10","egt10",egt10,"EGT 10","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge11","egt11",egt11,"EGT 11","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge12","egt12",egt12,"EGT 12","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge13","egt13",egt13,"EGT 13","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge14","egt14",egt14,"EGT 14","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge15","egt15",egt15,"EGT 15","C",0,1250,0,0,1250,1250,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge16","egt16",egt16,"EGT 16","C",0,1250,0,0,1250,1250,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge1","egt1",egt1,"EGT 01","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge2","egt2",egt2,"EGT 02","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge3","egt3",egt3,"EGT 03","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge4","egt4",egt4,"EGT 04","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge5","egt5",egt5,"EGT 05","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge6","egt6",egt6,"EGT 06","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge7","egt7",egt7,"EGT 07","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge8","egt8",egt8,"EGT 08","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge9","egt9",egt9,"EGT 09","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge10","egt10",egt10,"EGT 10","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge11","egt11",egt11,"EGT 11","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge12","egt12",egt12,"EGT 12","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge13","egt13",egt13,"EGT 13","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge14","egt14",egt14,"EGT 14","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge15","egt15",egt15,"EGT 15","F",0,2280,0,0,2280,2280,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge16","egt16",egt16,"EGT 16","F",0,2280,0,0,2280,2280,0,0,45));
        }
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","°C",-40,230,-100,-100,170,200,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","°C",-40,150,-100,-100,95,105,0,0,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Manifold Air Temp","°C",-40,110,-15,0,95,100,0,0,45));
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","°F",-40,450,-100,-100,350,400,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge","coolant",coolant,"Coolant Temp","°F",-40,300,-100,-100,200,220,0,0,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge","mat",mat,"Manifold Air Temp","°F",-40,215,0,30,200,210,0,0,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("voltMeter","batteryVoltage",batteryVoltage,"Battery Voltage","volts",7,21,8,9,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tachometer","rpm",rpm,"Engine Speed","RPM",0,8000,300,600,3000,5000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("throttleGauge","throttle",throttle,"Throttle Position","%",0,100,-1,1,90,100,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge","map",map,"Engine MAP","kPa",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("barometerGauge","barometer",barometer,"Barometer","kPa",60,120,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelloadGauge","fuelload",fuelload,"Fuel Load","%",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelload2Gauge","fuelload2",fuelload2,"Secondary Fuel Load","%",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ignloadGauge","ignload",ignload,"Ign Load","%",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ignload2Gauge","ignload2",ignload2,"Secondary Ign Load","%",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("eaeloadGauge","eaeload1",eaeload1,"EAE Load","%",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afrloadGauge","afrload1",afrload1,"AFR Load","%",0,255,0,20,200,245,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mafGauge","maf",maf,"Mass Air Flow","g/sec",0,650,0,200,480,550,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mafMapGauge","mafmap",mafmap,"MAFMAP","kPa",0,400,-1,-1,999,999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("boostbarGauge","boostbar",boostbar,"Boost","bar",-1,3,-1,-1,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("boostpsigGauge","boostpsig",boostpsig,"Boost","psig",-14.7,21,-15,-15,30,30,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("knockinGauge","knock",knock,"Knock Input","%",0,100.0,-1,-1,100.0,100.0,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("adc6Gauge","adc6",adc6,"ADC 6","",0,1023,1023,1023,1023,1023,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("adc7Gauge","adc7",adc7,"ADC 7","",0,1023,1023,1023,1023,1023,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("stream_levelGauge","stream_level",stream_level,"Stream Level","",0,128,128,128,128,128,0,0,45));
        if (MPH)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vehiclespeed1","vss1",vss1,"Vehicle Speed 1","MPH",0,200,1000,1000,1000,1000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vehiclespeed2","vss2",vss2,"Vehicle Speed 2","MPH",0,200,1000,1000,1000,1000,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vehiclespeed1","vss1",vss1,"Vehicle Speed 1","KPH",0,300,1600,1600,1600,1600,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vehiclespeed2","vss2",vss2,"Vehicle Speed 2","KPH",0,300,1600,1600,1600,1600,0,0,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("shaftspeed1","ss1",ss1,"Shaft Speed 1","RPM",0,10000,10000,10000,10000,10000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("shaftspeed2","ss2",ss2,"Shaft Speed 2","RPM",0,10000,10000,10000,10000,10000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gear","gear",gear,"Gear","",-1,6,6,6,6,6,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelx","accelx",accelx,"Accel X","ms-2",-59,59,59,59,59,59,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accely","accely",accely,"Accel Y","ms-2",-59,59,59,59,59,59,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelz","accelz",accelz,"Accel Z","ms-2",-59,59,59,59,59,59,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvtGauge1","vvt_ang1",vvt_ang1,"VVT 1 angle","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvtGauge2","vvt_ang2",vvt_ang2,"VVT 2 angle","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvtGauge3","vvt_ang3",vvt_ang3,"VVT 3 angle","degrees",0,50,-1,-1,999,999,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vvtGauge4","vvt_ang4",vvt_ang4,"VVT 4 angle","degrees",0,50,-1,-1,999,999,1,1,45));
        if (NARROW_BAND_EGO)
        {
        }
        else if (LAMBDA)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda1Gauge","lambda1",lambda1,"Lambda 1","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda2Gauge","lambda2",lambda2,"Lambda 2","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda3Gauge","lambda3",lambda3,"Lambda 3","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda4Gauge","lambda4",lambda4,"Lambda 4","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda5Gauge","lambda5",lambda5,"Lambda 5","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda6Gauge","lambda6",lambda6,"Lambda 6","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda7Gauge","lambda7",lambda7,"Lambda 7","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda8Gauge","lambda8",lambda8,"Lambda 8","",0.5,1.5,0.5,0.7,2,1.1,2,2,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1Gauge","afr1",afr1,"Air:Fuel Ratio 1","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2Gauge","afr2",afr2,"Air:Fuel Ratio 2","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr3Gauge","afr3",afr3,"Air:Fuel Ratio 3","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr4Gauge","afr4",afr4,"Air:Fuel Ratio 4","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr5Gauge","afr5",afr5,"Air:Fuel Ratio 5","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr6Gauge","afr6",afr6,"Air:Fuel Ratio 6","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr7Gauge","afr7",afr7,"Air:Fuel Ratio 7","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr8Gauge","afr8",afr8,"Air:Fuel Ratio 8","",10,19.4,12,13,15,16,2,2,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV1Gauge","egov1",egov1,"Exhaust Gas Oxygen 1","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV2Gauge","egov2",egov2,"Exhaust Gas Oxygen 2","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV3Gauge","egov3",egov3,"Exhaust Gas Oxygen 3","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV4Gauge","egov4",egov4,"Exhaust Gas Oxygen 4","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV5Gauge","egov5",egov5,"Exhaust Gas Oxygen 5","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV6Gauge","egov6",egov6,"Exhaust Gas Oxygen 6","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV7Gauge","egov7",egov7,"Exhaust Gas Oxygen 7","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV8Gauge","egov8",egov8,"Exhaust Gas Oxygen 8","volts",0,5,5,5,5,5,2,2,45));
        if (NARROW_BAND_EGO)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1targetGauge","afrtgt1",afrtgt1,"EgoV 1 Target","",0,1,20,20,20,20,3,3,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2targetGauge","afrtgt2",afrtgt2,"EgoV 2 Target","",0,1,20,20,20,20,3,3,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1errGauge","afr1err",afr1err,"EgoV 1 Error","",-1,+1,12,13,15,16,3,3,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2errGauge","afr2err",afr2err,"EgoV 2 Error","",-1,+1,12,13,15,16,3,3,45));
        }
        else if (LAMBDA)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1targetGauge","afrtgt1",afrtgt1,"Lambda 1 Target","",0.5,1.5,20,20,20,20,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2targetGauge","afrtgt2",afrtgt2,"Lambda 2 Target","",0.5,1.5,20,20,20,20,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1errGauge","afr1err",afr1err,"Lambda 1 Error","",-2,+2,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2errGauge","afr2err",afr2err,"Lambda 2 Error","",-2,+2,12,13,15,16,2,2,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1targetGauge","afrtgt1",afrtgt1,"AFR 1 Target","",10,19.4,20,20,20,20,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2targetGauge","afrtgt2",afrtgt2,"AFR 2 Target","",10,19.4,20,20,20,20,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1errGauge","afr1err",afr1err,"AFR 1 Error","",-5,+5,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2errGauge","afr2err",afr2err,"AFR 2 Error","",-5,+5,12,13,15,16,2,2,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge1","egocor1",egocor1,"EGO Correction 1","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge2","egocor2",egocor2,"EGO Correction 2","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge3","egocor3",egocor3,"EGO Correction 3","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge4","egocor4",egocor4,"EGO Correction 4","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge5","egocor5",egocor5,"EGO Correction 5","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge6","egocor6",egocor6,"EGO Correction 6","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge7","egocor7",egocor7,"EGO Correction 7","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge8","egocor8",egocor8,"EGO Correction 8","%",50,150,90,99,101,110,1,1,45));
        if (CYL_12_16_SUPPORT)
        {
        if (NARROW_BAND_EGO)
        {
        }
        else if (LAMBDA)
        {
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr9Gauge","afr9",afr9,"Air:Fuel Ratio 9","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr10Gauge","afr10",afr10,"Air:Fuel Ratio 10","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr11Gauge","afr11",afr11,"Air:Fuel Ratio 11","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr12Gauge","afr12",afr12,"Air:Fuel Ratio 12","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr13Gauge","afr13",afr13,"Air:Fuel Ratio 13","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr14Gauge","afr14",afr14,"Air:Fuel Ratio 14","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr15Gauge","afr15",afr15,"Air:Fuel Ratio 15","",10,19.4,12,13,15,16,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr16Gauge","afr16",afr16,"Air:Fuel Ratio 16","",10,19.4,12,13,15,16,2,2,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV9Gauge","egov9",egov9,"Exhaust Gas Oxygen 9","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV10Gauge","egov10",egov10,"Exhaust Gas Oxygen 10","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV11Gauge","egov11",egov11,"Exhaust Gas Oxygen 11","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV12Gauge","egov12",egov12,"Exhaust Gas Oxygen 12","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV13Gauge","egov13",egov13,"Exhaust Gas Oxygen 13","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV14Gauge","egov14",egov14,"Exhaust Gas Oxygen 14","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV15Gauge","egov15",egov15,"Exhaust Gas Oxygen 15","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV16Gauge","egov16",egov16,"Exhaust Gas Oxygen 16","volts",0,5,5,5,5,5,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge9","egocor9",egocor9,"EGO Correction 9","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge10","egocor10",egocor10,"EGO Correction 10","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge11","egocor11",egocor11,"EGO Correction 11","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge12","egocor12",egocor12,"EGO Correction 12","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge13","egocor13",egocor13,"EGO Correction 13","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge14","egocor14",egocor14,"EGO Correction 14","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge15","egocor15",egocor15,"EGO Correction 15","%",50,150,90,99,101,110,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge16","egocor16",egocor16,"EGO Correction 16","%",50,150,90,99,101,110,1,1,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor01Gauge","sensor01",sensor01,"Sensor 01","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor02Gauge","sensor02",sensor02,"Sensor 02","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor03Gauge","sensor03",sensor03,"Sensor 03","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor04Gauge","sensor04",sensor04,"Sensor 04","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor05Gauge","sensor05",sensor05,"Sensor 05","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor06Gauge","sensor06",sensor06,"Sensor 06","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor07Gauge","sensor07",sensor07,"Sensor 07","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor08Gauge","sensor08",sensor08,"Sensor 08","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor09Gauge","sensor09",sensor09,"Sensor 09","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor10Gauge","sensor10",sensor10,"Sensor 10","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor11Gauge","sensor11",sensor11,"Sensor 11","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor12Gauge","sensor12",sensor12,"Sensor 12","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor13Gauge","sensor13",sensor13,"Sensor 13","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor14Gauge","sensor14",sensor14,"Sensor 14","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor15Gauge","sensor15",sensor15,"Sensor 15","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sensor16Gauge","sensor16",sensor16,"Sensor 16","",-3000,3000,-3277,-3277,3277,3277,1,1,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("canin1_8Gauge","canin1_8",canin1_8,"CAN inputs 1-8","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("canout1_8Gauge","canout1_8",canout1_8,"CAN outputs 1-8","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("canout9_16Gauge","canout9_16",canout9_16,"CAN outputs 9-16","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("canpwmin0Gauge","canpwmin0",canpwmin0,"CAN PWMin 0","",0,65535,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("canpwmin1Gauge","canpwmin1",canpwmin1,"CAN PWMin 1","",0,65535,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("canpwmin2Gauge","canpwmin2",canpwmin2,"CAN PWMin 2","",0,65535,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("canpwmin3Gauge","canpwmin3",canpwmin3,"CAN PWMin 3","",0,65535,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status1Gauge","status1",status1,"Status 1","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status2Gauge","status2",status2,"Status 2","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status3Gauge","status3",status3,"Status 3","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status4Gauge","status4",status4,"Status 4","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status5Gauge","status5",status5,"Status 5","",0,65535,65535,65535,65535,65535,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("looptimeGauge","looptime",looptime,"Mainloop time","us",0,10000,-1,-1,1000,6000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sd_filenum","sd_filenum",sd_filenum,"SDcard file number","",0,9999,9999,9999,9999,9999,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sd_error","sd_error",sd_error,"SDcard error","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sd_status","sd_status",sd_status,"SDcard status","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("sd_phase","sd_phase",sd_phase,"SDcard phase","",0,255,255,255,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge","deadValue",deadValue,"---","",0,1,-1,-1,2,2,0,0,45));
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
        return 1;
    }
    @Override
    public int getInterWriteDelay()
    {
        return 1;
    }
    @Override
    public boolean isCRC32Protocol()
    {
        return true;
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
        if (USE_CRC_DATA_CHECK)
        {
        }
        pageBuffer = loadPage(1,0,1024,null,new byte[]{114,0,4,0,0,4,0});
        nCylinders = MSUtils.getBits(pageBuffer,0,0,4,0);
        no_skip_pulses = (int)((MSUtils.getByte(pageBuffer,1) + 0.0) * 1.0);
        ICIgnCapture = MSUtils.getBits(pageBuffer,2,0,0,0);
        engineType = MSUtils.getBits(pageBuffer,2,3,3,0);
        spkout_hi_lo = MSUtils.getBits(pageBuffer,2,4,4,0);
        max_coil_dur = (double)((MSUtils.getByte(pageBuffer,3) + 0.0) * 0.1);
        max_spk_dur = (double)((MSUtils.getByte(pageBuffer,4) + 0.0) * 0.1);
        dwellAcc = (double)((MSUtils.getByte(pageBuffer,5) + 0.0) * 0.1);
        crankingRPM = (int)((MSUtils.getSignedWord(pageBuffer,6) + 0.0) * 1.0);
        triggerOffset = (double)((MSUtils.getSignedWord(pageBuffer,8) + 0.0) * 0.1);
        TpsBypassCLTRevlim = (double)((MSUtils.getSignedWord(pageBuffer,10) + 0.0) * 0.1);
        RevLimRpm2 = (int)((MSUtils.getSignedWord(pageBuffer,12) + 0.0) * 1.0);
        map0 = (double)((MSUtils.getSignedWord(pageBuffer,14) + 0.0) * 0.1);
        mapmax = (double)((MSUtils.getSignedWord(pageBuffer,16) + 0.0) * 0.1);
        if (CELSIUS)
        {
        clt0 = (double)((MSUtils.getSignedWord(pageBuffer,18) + -320.0) * 0.05555);
        cltmult = (int)((MSUtils.getSignedWord(pageBuffer,20) + 0.0) * 1.0);
        mat0 = (double)((MSUtils.getSignedWord(pageBuffer,22) + -320.0) * 0.05555);
        }
        else
        {
        clt0 = (double)((MSUtils.getSignedWord(pageBuffer,18) + 0.0) * 0.1);
        cltmult = (int)((MSUtils.getSignedWord(pageBuffer,20) + 0.0) * 1.0);
        mat0 = (double)((MSUtils.getSignedWord(pageBuffer,22) + 0.0) * 0.1);
        }
        matmult = (int)((MSUtils.getSignedWord(pageBuffer,24) + 0.0) * 1.0);
        tpsMin = (int)((MSUtils.getSignedWord(pageBuffer,26) + 0.0) * 1.0);
        tpsMax = (int)((MSUtils.getSignedWord(pageBuffer,28) + 0.0) * 1.0);
        batt0 = (double)((MSUtils.getSignedWord(pageBuffer,30) + 0.0) * 0.1);
        battmax = (double)((MSUtils.getSignedWord(pageBuffer,32) + 0.0) * 0.1);
        ego0 = (double)((MSUtils.getSignedWord(pageBuffer,34) + 0.0) * 0.1);
        egomult = (int)((MSUtils.getSignedWord(pageBuffer,36) + 0.0) * 1.0);
        baro0 = (double)((MSUtils.getSignedWord(pageBuffer,38) + 0.0) * 0.1);
        baromax = (double)((MSUtils.getSignedWord(pageBuffer,40) + 0.0) * 0.1);
        bcor0 = (int)((MSUtils.getSignedWord(pageBuffer,42) + 0.0) * 1.0);
        bcormult = (int)((MSUtils.getSignedWord(pageBuffer,44) + 0.0) * 1.0);
        Xknock0 = (double)((MSUtils.getSignedWord(pageBuffer,46) + 0.0) * 0.01);
        Xknockmax = (double)((MSUtils.getSignedWord(pageBuffer,48) + 0.0) * 0.01);
        crankTolerance = (int)((MSUtils.getByte(pageBuffer,50) + 0.0) * 1.0);
        asTolerance = (int)((MSUtils.getByte(pageBuffer,51) + 0.0) * 1.0);
        pulseTolerance = (int)((MSUtils.getByte(pageBuffer,52) + 0.0) * 1.0);
        IdleCtl = MSUtils.getBits(pageBuffer,53,0,3,0);
        IdleCtl_vss = MSUtils.getBits(pageBuffer,53,4,4,0);
        IACtstep = (double)((MSUtils.getByte(pageBuffer,54) + 0.0) * 0.128);
        IAC_tinitial_step = (double)((MSUtils.getByte(pageBuffer,55) + 0.0) * 0.128);
        IACminstep = (int)((MSUtils.getByte(pageBuffer,56) + 0.0) * 1.0);
        dwellduty = (double)((MSUtils.getByte(pageBuffer,57) + 0.0) * 0.39);
        IACStart = (int)((MSUtils.getSignedWord(pageBuffer,58) + 0.0) * 1.0);
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        IdleHyst = (double)((MSUtils.getSignedWord(pageBuffer,60) + 0.0) * 0.05555);
        IACcoldtmp = (double)((MSUtils.getSignedWord(pageBuffer,66) + -320.0) * 0.05555);
        }
        else
        {
        IdleHyst = (double)((MSUtils.getSignedWord(pageBuffer,60) + 0.0) * 0.05555);
        IACcoldtmp = (double)((MSUtils.getSignedWord(pageBuffer,66) + -320.0) * 0.05555);
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        IdleHyst = (double)((MSUtils.getSignedWord(pageBuffer,60) + 0.0) * 0.1);
        IACcoldtmp = (double)((MSUtils.getSignedWord(pageBuffer,66) + 0.0) * 0.1);
        }
        else
        {
        IdleHyst = (double)((MSUtils.getSignedWord(pageBuffer,60) + 0.0) * 0.1);
        IACcoldtmp = (double)((MSUtils.getSignedWord(pageBuffer,66) + 0.0) * 0.1);
        }
        }
        IACcrankpos = (int)((MSUtils.getSignedWord(pageBuffer,62) + 0.0) * 1.0);
        IACcrankxt = (int)((MSUtils.getSignedWord(pageBuffer,64) + 0.0) * 1.0);
        IACcoldpos = (int)((MSUtils.getSignedWord(pageBuffer,68) + 0.0) * 1.0);
        IACcoldxt = (int)((MSUtils.getSignedWord(pageBuffer,70) + 0.0) * 1.0);
        OverBoostKpa2 = (double)((MSUtils.getSignedWord(pageBuffer,72) + 0.0) * 0.1);
        fc_rpm_lower = (int)((MSUtils.getWord(pageBuffer,74) + 0.0) * 1.0);
        OverBoostOption = MSUtils.getBits(pageBuffer,76,0,1,0);
        hardware_spk = MSUtils.getBits(pageBuffer,77,0,2,0);
        hardware_cam = MSUtils.getBits(pageBuffer,77,3,3,0);
        hardware_fuel = MSUtils.getBits(pageBuffer,77,4,4,0);
        hardware_injij = MSUtils.getBits(pageBuffer,77,5,5,0);
        OverBoostKpa = (double)((MSUtils.getSignedWord(pageBuffer,78) + 0.0) * 0.1);
        OverBoostHyst = (double)((MSUtils.getSignedWord(pageBuffer,80) + 0.0) * 0.1);
        overboostcutx = (int)((MSUtils.getByte(pageBuffer,82) + 0.0) * 1.0);
        overboostcuty = (int)((MSUtils.getByte(pageBuffer,83) + 0.0) * 1.0);
        tpsThresh = (double)((MSUtils.getSignedWord(pageBuffer,84) + 0.0) * 0.1);
        mapThresh = (int)((MSUtils.getSignedWord(pageBuffer,86) + 0.0) * 1.0);
        taeColdA = (int)((MSUtils.getByte(pageBuffer,88) + 0.0) * 1.0);
        taeColdM = (int)((MSUtils.getByte(pageBuffer,89) + 0.0) * 1.0);
        taeTime = (double)((MSUtils.getByte(pageBuffer,90) + 0.0) * 0.1);
        tdePct = (int)((MSUtils.getByte(pageBuffer,91) + 0.0) * 1.0);
        floodClear = (double)((MSUtils.getSignedWord(pageBuffer,92) + 0.0) * 0.1);
        TPSOXLimit = (double)((MSUtils.getSignedWord(pageBuffer,94) + 0.0) * 0.1);
        tpsProportion = (int)((MSUtils.getByte(pageBuffer,96) + 0.0) * 1.0);
        baroCorr = MSUtils.getBits(pageBuffer,97,0,1,0);
        egoType = MSUtils.getBits(pageBuffer,98,0,1,0);
        egoCount = (int)((MSUtils.getByte(pageBuffer,99) + 0.0) * 1.0);
        egoDelta = (int)((MSUtils.getByte(pageBuffer,100) + 0.0) * 1.0);
        oldegoLimit = (int)((MSUtils.getByte(pageBuffer,101) + 0.0) * 1.0);
        EGOVtarget = (double)((MSUtils.getByte(pageBuffer,102) + 0.0) * 0.00489);
        tempUnits = MSUtils.getBits(pageBuffer,103,0,0,0);
        egonum = (int)((MSUtils.getByte(pageBuffer,104) + 0.0) * 1.0);
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        fastIdleT = (double)((MSUtils.getSignedWord(pageBuffer,106) + -320.0) * 0.05555);
        egoTemp = (double)((MSUtils.getSignedWord(pageBuffer,108) + -320.0) * 0.05555);
        }
        else
        {
        fastIdleT = (double)((MSUtils.getSignedWord(pageBuffer,106) + -320.0) * 0.05555);
        egoTemp = (double)((MSUtils.getSignedWord(pageBuffer,108) + -320.0) * 0.05555);
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        fastIdleT = (double)((MSUtils.getSignedWord(pageBuffer,106) + 0.0) * 0.1);
        egoTemp = (double)((MSUtils.getSignedWord(pageBuffer,108) + 0.0) * 0.1);
        }
        else
        {
        fastIdleT = (double)((MSUtils.getSignedWord(pageBuffer,106) + 0.0) * 0.1);
        egoTemp = (double)((MSUtils.getSignedWord(pageBuffer,108) + 0.0) * 0.1);
        }
        }
        egoRPM = (int)((MSUtils.getSignedWord(pageBuffer,110) + 0.0) * 1.0);
        if (PW_4X)
        {
        reqFuel = (double)((MSUtils.getWord(pageBuffer,112) + 0.0) * 0.0040);
        }
        else
        {
        reqFuel = (double)((MSUtils.getWord(pageBuffer,112) + 0.0) * 0.0010);
        }
        divider = (int)((MSUtils.getByte(pageBuffer,114) + 0.0) * 1.0);
        alternate = MSUtils.getBits(pageBuffer,115,0,0,0);
        altcrank = MSUtils.getBits(pageBuffer,115,1,1,0);
        injPwmT = (double)((MSUtils.getByte(pageBuffer,116) + 0.128) * 0.128);
        injPwmPd = (int)((MSUtils.getByte(pageBuffer,117) + 0.0) * 1.0);
        injPwmP = (int)((MSUtils.getByte(pageBuffer,118) + 0.0) * 1.0);
        twoStroke = MSUtils.getBits(pageBuffer,119,0,1,0);
        injType = MSUtils.getBits(pageBuffer,120,0,0,0);
        nInjectors = MSUtils.getBits(pageBuffer,121,0,4,0);
        OddFireang = (double)((MSUtils.getWord(pageBuffer,122) + 0.0) * 0.1);
        rpmLF = (int)((MSUtils.getByte(pageBuffer,124) + 0.0) * 1.0);
        mapLF = (int)((MSUtils.getByte(pageBuffer,125) + 0.0) * 1.0);
        tpsLF = (int)((MSUtils.getByte(pageBuffer,126) + 0.0) * 1.0);
        egoLF = (int)((MSUtils.getByte(pageBuffer,127) + 0.0) * 1.0);
        adcLF = (int)((MSUtils.getByte(pageBuffer,128) + 0.0) * 1.0);
        knk_pin_out = MSUtils.getBits(pageBuffer,129,0,4,0);
        AMCOption = MSUtils.getBits(pageBuffer,130,0,1,0);
        UNUSED_1_131 = MSUtils.getBits(pageBuffer,131,0,0,0);
        algorithm = MSUtils.getBits(pageBuffer,132,0,2,0);
        algorithm2 = MSUtils.getBits(pageBuffer,132,4,6,0);
        IgnAlgorithm = MSUtils.getBits(pageBuffer,133,0,2,0);
        IgnAlgorithm2 = MSUtils.getBits(pageBuffer,133,4,6,0);
        AfrAlgorithm = (int)((MSUtils.getByte(pageBuffer,134) + 0.0) * 1.0);
        dwelltime = (double)((MSUtils.getByte(pageBuffer,135) + 0.0) * 0.1);
        trigret_ang = (double)((MSUtils.getWord(pageBuffer,136) + 0.0) * 0.1);
        RevLimOption_retard = MSUtils.getBits(pageBuffer,138,0,1,0);
        RevLimOption_spkcut = MSUtils.getBits(pageBuffer,138,2,2,0);
        RevLimCLTbased = MSUtils.getBits(pageBuffer,138,3,3,0);
        RevLimMaxRtd = (double)((MSUtils.getByte(pageBuffer,139) + 0.0) * 0.1);
        ego_startdelay = (int)((MSUtils.getByte(pageBuffer,140) + 0.0) * 1.0);
        can_poll2_ego = MSUtils.getBits(pageBuffer,141,0,0,0);
        opt142_rtc = MSUtils.getBits(pageBuffer,142,0,1,0);
        injPwmT2 = (double)((MSUtils.getByte(pageBuffer,143) + 0.128) * 0.128);
        injPwmPd2 = (int)((MSUtils.getByte(pageBuffer,144) + 0.0) * 1.0);
        injPwmP2 = (int)((MSUtils.getByte(pageBuffer,145) + 0.0) * 1.0);
        can_ego_id = (int)((MSUtils.getByte(pageBuffer,146) + 0.0) * 1.0);
        can_ego_table = (int)((MSUtils.getByte(pageBuffer,147) + 0.0) * 1.0);
        can_ego_offset = (int)((MSUtils.getWord(pageBuffer,148) + 0.0) * 1.0);
        baro_upper = (double)((MSUtils.getSignedWord(pageBuffer,150) + 0.0) * 0.1);
        baro_lower = (double)((MSUtils.getSignedWord(pageBuffer,152) + 0.0) * 0.1);
        baro_default = (double)((MSUtils.getSignedWord(pageBuffer,154) + 0.0) * 0.1);
        RevLimTPSbypassRPM = (int)((MSUtils.getSignedWord(pageBuffer,156) + 0.0) * 1.0);
        RevLimNormal1 = (int)((MSUtils.getSignedWord(pageBuffer,158) + 0.0) * 1.0);
        RevLimNormal2 = (int)((MSUtils.getSignedWord(pageBuffer,160) + 0.0) * 1.0);
        hw_latency = (int)((MSUtils.getByte(pageBuffer,162) + 0.0) * 1.0);
        loadCombine = MSUtils.getBits(pageBuffer,163,0,0,0);
        loadMult = MSUtils.getBits(pageBuffer,163,2,2,0);
        loadStoich = MSUtils.getBits(pageBuffer,163,3,3,0);
        MAPOXLimit = (double)((MSUtils.getSignedWord(pageBuffer,168) + 0.0) * 0.1);
        can_poll_id_rtc = (int)((MSUtils.getByte(pageBuffer,170) + 0.0) * 1.0);
        mycan_id = (int)((MSUtils.getByte(pageBuffer,171) + 0.0) * 1.0);
        mapsample_percent = (int)((MSUtils.getByte(pageBuffer,172) + 0.0) * 1.0);
        can_poll_id_ports = (int)((MSUtils.getByte(pageBuffer,173) + 0.0) * 1.0);
        can_poll_id = (int)((MSUtils.getByte(pageBuffer,174) + 0.0) * 1.0);
        aeTaperTime = (double)((MSUtils.getByte(pageBuffer,175) + 0.0) * 0.1);
        aeEndPW = (int)((MSUtils.getSignedWord(pageBuffer,176) + 0.0) * 1.0);
        egoAlgorithm = MSUtils.getBits(pageBuffer,178,0,1,0);
        egoKP = (int)((MSUtils.getByte(pageBuffer,179) + 0.0) * 1.0);
        egoKI = (int)((MSUtils.getByte(pageBuffer,180) + 0.0) * 1.0);
        egoKD = (int)((MSUtils.getByte(pageBuffer,181) + 0.0) * 1.0);
        if (MPH)
        {
        ac_idleup_vss_offpoint = (double)((MSUtils.getWord(pageBuffer,182) + 0.0) * 0.22369);
        ac_idleup_vss_hyst = (double)((MSUtils.getWord(pageBuffer,184) + 0.0) * 0.22369);
        }
        else
        {
        ac_idleup_vss_offpoint = (double)((MSUtils.getWord(pageBuffer,182) + 0.0) * 0.36);
        ac_idleup_vss_hyst = (double)((MSUtils.getWord(pageBuffer,184) + 0.0) * 0.36);
        }
        flexFuel = MSUtils.getBits(pageBuffer,186,0,0,0);
        flexport = MSUtils.getBits(pageBuffer,186,1,2,0);
        fuelFreq0 = (int)((MSUtils.getByte(pageBuffer,187) + 0.0) * 1.0);
        fuelFreq1 = (int)((MSUtils.getByte(pageBuffer,188) + 0.0) * 1.0);
        fuelCorr0 = (int)((MSUtils.getByte(pageBuffer,189) + 0.0) * 1.0);
        fuelCorr1 = (int)((MSUtils.getByte(pageBuffer,190) + 0.0) * 1.0);
        dwellmode = MSUtils.getBits(pageBuffer,191,0,1,0);
        pwmidle_shift_lower_rpm = (int)((MSUtils.getWord(pageBuffer,192) + 0.0) * 1.0);
        ac_idleup_tps_offpoint = (double)((MSUtils.getSignedWord(pageBuffer,194) + 0.0) * 0.1);
        ac_idleup_tps_hyst = (double)((MSUtils.getSignedWord(pageBuffer,196) + 0.0) * 0.1);
        fan_idleup_tps_offpoint = (double)((MSUtils.getSignedWord(pageBuffer,198) + 0.0) * 0.1);
        fan_idleup_tps_hyst = (double)((MSUtils.getSignedWord(pageBuffer,200) + 0.0) * 0.1);
        if (MPH)
        {
        fan_idleup_vss_offpoint = (double)((MSUtils.getWord(pageBuffer,202) + 0.0) * 0.22369);
        }
        else
        {
        fan_idleup_vss_offpoint = (double)((MSUtils.getWord(pageBuffer,202) + 0.0) * 0.36);
        }
        knk_option = MSUtils.getBits(pageBuffer,204,0,1,0);
        knk_option_an = MSUtils.getBits(pageBuffer,204,2,3,0);
        knkDirection = MSUtils.getBits(pageBuffer,204,4,4,0);
        knkpull = MSUtils.getBits(pageBuffer,204,5,6,0);
        knk_option_wind = MSUtils.getBits(pageBuffer,204,7,7,0);
        knk_maxrtd = (double)((MSUtils.getByte(pageBuffer,205) + 0.0) * 0.1);
        knk_step1 = (double)((MSUtils.getByte(pageBuffer,206) + 0.0) * 0.1);
        knk_step2 = (double)((MSUtils.getByte(pageBuffer,207) + 0.0) * 0.1);
        knk_trtd = (double)((MSUtils.getByte(pageBuffer,208) + 0.0) * 0.1);
        knk_tadv = (double)((MSUtils.getByte(pageBuffer,209) + 0.0) * 0.1);
        knk_dtble_adv = (double)((MSUtils.getByte(pageBuffer,210) + 0.0) * 0.1);
        knk_ndet = (int)((MSUtils.getByte(pageBuffer,211) + 0.0) * 1.0);
        EAEOption = MSUtils.getBits(pageBuffer,212,0,2,0);
        knkport = MSUtils.getBits(pageBuffer,213,0,3,0);
        knk_maxmap = (double)((MSUtils.getWord(pageBuffer,214) + 0.0) * 0.1);
        knk_lorpm = (int)((MSUtils.getWord(pageBuffer,216) + 0.0) * 1.0);
        knk_hirpm = (int)((MSUtils.getWord(pageBuffer,218) + 0.0) * 1.0);
        triggerTeeth = (int)((MSUtils.getWord(pageBuffer,220) + 0.0) * 1.0);
        No_Miss_Teeth = (int)((MSUtils.getByte(pageBuffer,222) + 0.0) * 1.0);
        pwmidle_shift_open_time = (int)((MSUtils.getByte(pageBuffer,223) + 0.0) * 1.0);
        Miss_ang = (double)((MSUtils.getWord(pageBuffer,224) + 0.0) * 0.1);
        ICISR_tmask = (double)((MSUtils.getByte(pageBuffer,226) + 0.0) * 0.1);
        ICISR_pmask = (int)((MSUtils.getByte(pageBuffer,227) + 0.0) * 1.0);
        ae_lorpm = (int)((MSUtils.getWord(pageBuffer,228) + 0.0) * 1.0);
        ae_hirpm = (int)((MSUtils.getWord(pageBuffer,230) + 0.0) * 1.0);
        fuelSpkDel0 = (double)((MSUtils.getSignedWord(pageBuffer,232) + 0.0) * 0.1);
        fuelSpkDel1 = (double)((MSUtils.getSignedWord(pageBuffer,234) + 0.0) * 0.1);
        spk_conf2_gm = MSUtils.getBits(pageBuffer,236,0,0,0);
        spk_conf2_tfi = MSUtils.getBits(pageBuffer,236,1,2,0);
        spk_conf2_cam = MSUtils.getBits(pageBuffer,236,3,3,0);
        spk_conf2_oddodd = MSUtils.getBits(pageBuffer,236,4,4,0);
        spk_conf2_dwell = MSUtils.getBits(pageBuffer,236,5,5,0);
        spk_conf2_ngc = MSUtils.getBits(pageBuffer,236,6,6,0);
        spk_conf2_dli = MSUtils.getBits(pageBuffer,236,7,7,0);
        spk_config_campol = MSUtils.getBits(pageBuffer,237,0,0,0);
        spk_config_camcrank = MSUtils.getBits(pageBuffer,237,1,1,0);
        spk_config_trig2 = MSUtils.getBits(pageBuffer,237,2,3,0);
        spk_config_trig2l = MSUtils.getBits(pageBuffer,237,4,5,0);
        spk_config_resetcam = MSUtils.getBits(pageBuffer,237,6,7,0);
        spk_mode0 = MSUtils.getBits(pageBuffer,238,0,5,0);
        spk_mode3_trim = MSUtils.getBits(pageBuffer,239,0,0,0);
        spk_mode3 = MSUtils.getBits(pageBuffer,239,5,7,0);
        rtbaroport = MSUtils.getBits(pageBuffer,240,0,4,0);
        ego2port = MSUtils.getBits(pageBuffer,241,0,4,0);
        mapport = MSUtils.getBits(pageBuffer,242,0,4,0);
        mapport_t = MSUtils.getBits(pageBuffer,242,5,5,0);
        mapport_f = MSUtils.getBits(pageBuffer,242,6,7,0);
        knkport_an = MSUtils.getBits(pageBuffer,243,0,2,0);
        RevLimcutx = (int)((MSUtils.getByte(pageBuffer,244) + 0.0) * 1.0);
        RevLimcuty = (int)((MSUtils.getByte(pageBuffer,245) + 0.0) * 1.0);
        feature4_0igntrig = MSUtils.getBits(pageBuffer,246,1,1,0);
        feature4_0VEtblsize = MSUtils.getBits(pageBuffer,246,2,2,0);
        feature4_0maxdwl = MSUtils.getBits(pageBuffer,246,3,3,0);
        feature4_0ftrig = MSUtils.getBits(pageBuffer,246,4,5,0);
        feature4_0mindwl = MSUtils.getBits(pageBuffer,246,6,6,0);
        timing_flags = MSUtils.getBits(pageBuffer,247,0,0,0);
        use_prediction = MSUtils.getBits(pageBuffer,247,1,1,0);
        crank_dwell = (double)((MSUtils.getByte(pageBuffer,248) + 0.0) * 0.1);
        tsw_pin_f = MSUtils.getBits(pageBuffer,249,0,4,0);
        crank_timing = (double)((MSUtils.getSignedWord(pageBuffer,250) + 0.0) * 0.1);
        fixed_timing = (double)((MSUtils.getSignedWord(pageBuffer,252) + 0.0) * 0.1);
        tsf_rpm = (int)((MSUtils.getWord(pageBuffer,254) + 0.0) * 1.0);
        tsf_kpa = (double)((MSUtils.getSignedWord(pageBuffer,256) + 0.0) * 0.1);
        tsf_tps = (double)((MSUtils.getSignedWord(pageBuffer,258) + 0.0) * 0.1);
        tss_rpm = (int)((MSUtils.getWord(pageBuffer,260) + 0.0) * 1.0);
        tss_kpa = (double)((MSUtils.getSignedWord(pageBuffer,262) + 0.0) * 0.1);
        tss_tps = (double)((MSUtils.getSignedWord(pageBuffer,264) + 0.0) * 0.1);
        OvrRunC = MSUtils.getBits(pageBuffer,266,0,0,0);
        f5_0_tsf = MSUtils.getBits(pageBuffer,266,1,1,0);
        f5_0_tsf_opt = MSUtils.getBits(pageBuffer,266,2,3,0);
        f5_0_tss = MSUtils.getBits(pageBuffer,266,4,4,0);
        f5_0_tss_opt = MSUtils.getBits(pageBuffer,266,5,6,0);
        tsw_pin_s = MSUtils.getBits(pageBuffer,267,0,4,0);
        pwmidlecranktaper = (int)((MSUtils.getByte(pageBuffer,268) + 0.0) * 1.0);
        fc_rpm = (int)((MSUtils.getWord(pageBuffer,270) + 0.0) * 1.0);
        fc_kpa = (double)((MSUtils.getSignedWord(pageBuffer,272) + 0.0) * 0.1);
        fc_tps = (double)((MSUtils.getSignedWord(pageBuffer,274) + 0.0) * 0.1);
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        fc_clt = (double)((MSUtils.getSignedWord(pageBuffer,276) + -320.0) * 0.05555);
        }
        else
        {
        fc_clt = (double)((MSUtils.getSignedWord(pageBuffer,276) + -320.0) * 0.05555);
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        fc_clt = (double)((MSUtils.getSignedWord(pageBuffer,276) + 0.0) * 0.1);
        }
        else
        {
        fc_clt = (double)((MSUtils.getSignedWord(pageBuffer,276) + 0.0) * 0.1);
        }
        }
        fc_delay = (double)((MSUtils.getByte(pageBuffer,278) + 0.0) * 0.1);
        tacho_opt80 = MSUtils.getBits(pageBuffer,279,7,7,0);
        tacho_opt40 = MSUtils.getBits(pageBuffer,279,6,6,0);
        tacho_opt3f = MSUtils.getBits(pageBuffer,279,0,4,0);
        fc_ego_delay = (int)((MSUtils.getByte(pageBuffer,280) + 0.0) * 1.0);
        EAElagsource = MSUtils.getBits(pageBuffer,281,0,0,0);
        EAElagthresh = (int)((MSUtils.getSignedWord(pageBuffer,282) + 0.0) * 1.0);
        EAElagRPMmax = (int)((MSUtils.getWord(pageBuffer,284) + 0.0) * 1.0);
        feature3_tps = MSUtils.getBits(pageBuffer,286,0,0,0);
        feature3_1 = MSUtils.getBits(pageBuffer,286,1,1,0);
        feature3_3 = MSUtils.getBits(pageBuffer,286,3,3,0);
        feature3_pw4x = MSUtils.getBits(pageBuffer,286,4,4,0);
        feature3_n2oin = MSUtils.getBits(pageBuffer,286,5,5,0);
        feature3_matase = MSUtils.getBits(pageBuffer,286,6,6,0);
        feature3_dwell = MSUtils.getBits(pageBuffer,286,7,7,0);
        launch_opt_bank = MSUtils.getBits(pageBuffer,287,4,5,0);
        launch_opt_on = MSUtils.getBits(pageBuffer,287,6,7,0);
        launch_sft_lim = (int)((MSUtils.getSignedWord(pageBuffer,288) + 0.0) * 1.0);
        launch_sft_deg = (double)((MSUtils.getSignedWord(pageBuffer,290) + 0.0) * 0.1);
        launch_hrd_lim = (int)((MSUtils.getSignedWord(pageBuffer,292) + 0.0) * 1.0);
        launch_tps = (double)((MSUtils.getSignedWord(pageBuffer,294) + 0.0) * 0.1);
        launchlimopt = MSUtils.getBits(pageBuffer,296,0,1,0);
        launchcutx = (int)((MSUtils.getByte(pageBuffer,297) + 0.0) * 1.0);
        launchcuty = (int)((MSUtils.getByte(pageBuffer,298) + 0.0) * 1.0);
        launch_opt_pins = MSUtils.getBits(pageBuffer,299,0,4,0);
        flats_arm = (int)((MSUtils.getSignedWord(pageBuffer,300) + 0.0) * 1.0);
        flats_sft = (int)((MSUtils.getSignedWord(pageBuffer,302) + 0.0) * 1.0);
        flats_deg = (double)((MSUtils.getSignedWord(pageBuffer,304) + 0.0) * 0.1);
        flats_hrd = (int)((MSUtils.getSignedWord(pageBuffer,306) + 0.0) * 1.0);
        staged_pri_size = (int)((MSUtils.getWord(pageBuffer,308) + 0.0) * 1.0);
        staged_sec_size = (int)((MSUtils.getWord(pageBuffer,310) + 0.0) * 1.0);
        staged_first_param = MSUtils.getBits(pageBuffer,312,0,2,0);
        staged_second_param = MSUtils.getBits(pageBuffer,312,3,5,0);
        staged_transition_on = MSUtils.getBits(pageBuffer,312,6,6,0);
        staged_second_logic = MSUtils.getBits(pageBuffer,312,7,7,0);
        staged_pw1_0 = MSUtils.getBits(pageBuffer,312,7,7,0);
        staged_transition_events = (int)((MSUtils.getByte(pageBuffer,313) + 0.0) * 1.0);
        staged_param_1 = (int)((MSUtils.getSignedWord(pageBuffer,314) + 0.0) * 1.0);
        staged_param_2 = (int)((MSUtils.getSignedWord(pageBuffer,316) + 0.0) * 1.0);
        staged_hyst_1 = (int)((MSUtils.getSignedWord(pageBuffer,318) + 0.0) * 1.0);
        staged_hyst_2 = (int)((MSUtils.getSignedWord(pageBuffer,320) + 0.0) * 1.0);
        N2Oopt_01 = MSUtils.getBits(pageBuffer,322,0,1,0);
        N2Oopt_2 = MSUtils.getBits(pageBuffer,322,2,2,0);
        N2Oopt_3 = MSUtils.getBits(pageBuffer,322,3,3,0);
        N2Oopt_4 = MSUtils.getBits(pageBuffer,322,4,4,0);
        N2Oopt2_prog = MSUtils.getBits(pageBuffer,323,0,0,0);
        N2Oopt2_prog_time = MSUtils.getBits(pageBuffer,323,1,1,0);
        N2Oopt2_prog_freq = MSUtils.getBits(pageBuffer,323,2,4,0);
        N2ORpm = (int)((MSUtils.getWord(pageBuffer,324) + 0.0) * 1.0);
        N2ORpmMax = (int)((MSUtils.getWord(pageBuffer,326) + 0.0) * 1.0);
        N2OTps = (double)((MSUtils.getSignedWord(pageBuffer,328) + 0.0) * 0.1);
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        N2OClt = (double)((MSUtils.getSignedWord(pageBuffer,330) + -320.0) * 0.05555);
        }
        else
        {
        N2OClt = (double)((MSUtils.getSignedWord(pageBuffer,330) + -320.0) * 0.05555);
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        N2OClt = (double)((MSUtils.getSignedWord(pageBuffer,330) + 0.0) * 0.1);
        }
        else
        {
        N2OClt = (double)((MSUtils.getSignedWord(pageBuffer,330) + 0.0) * 0.1);
        }
        }
        N2OAngle = (double)((MSUtils.getSignedWord(pageBuffer,332) + 0.0) * 0.1);
        if (PW_4X)
        {
        N2OPWLo = (double)((MSUtils.getSignedWord(pageBuffer,334) + 0.0) * 0.0040);
        N2OPWHi = (double)((MSUtils.getSignedWord(pageBuffer,336) + 0.0) * 0.0040);
        }
        else
        {
        N2OPWLo = (double)((MSUtils.getSignedWord(pageBuffer,334) + 0.0) * 0.0010);
        N2OPWHi = (double)((MSUtils.getSignedWord(pageBuffer,336) + 0.0) * 0.0010);
        }
        N2Odel_launch = (double)((MSUtils.getByte(pageBuffer,338) + 0.0) * 0.01);
        N2Odel_flat = (double)((MSUtils.getByte(pageBuffer,339) + 0.0) * 0.01);
        N2Oholdon = (double)((MSUtils.getByte(pageBuffer,340) + 0.0) * 0.01);
        N2O2delay = (double)((MSUtils.getByte(pageBuffer,341) + 0.0) * 0.01);
        N2O2Rpm = (int)((MSUtils.getWord(pageBuffer,342) + 0.0) * 1.0);
        N2O2RpmMax = (int)((MSUtils.getWord(pageBuffer,344) + 0.0) * 1.0);
        N2O2Angle = (double)((MSUtils.getSignedWord(pageBuffer,346) + 0.0) * 0.1);
        if (PW_4X)
        {
        N2O2PWLo = (double)((MSUtils.getSignedWord(pageBuffer,348) + 0.0) * 0.0040);
        N2O2PWHi = (double)((MSUtils.getSignedWord(pageBuffer,350) + 0.0) * 0.0040);
        }
        else
        {
        N2O2PWLo = (double)((MSUtils.getSignedWord(pageBuffer,348) + 0.0) * 0.0010);
        N2O2PWHi = (double)((MSUtils.getSignedWord(pageBuffer,350) + 0.0) * 0.0010);
        }
        RotarySplitModeFD = MSUtils.getBits(pageBuffer,352,0,0,0);
        RotarySplitModeNeg = MSUtils.getBits(pageBuffer,352,1,1,0);
        dlyct = (int)((MSUtils.getByte(pageBuffer,353) + 0.0) * 1.0);
        dwelltime_trl = (double)((MSUtils.getByte(pageBuffer,354) + 0.0) * 0.1);
        N2Oopt_pins = MSUtils.getBits(pageBuffer,355,0,4,0);
        RevLimRtdAng = (double)((MSUtils.getSignedWord(pageBuffer,356) + 0.0) * 0.1);
        RevLimNormal3 = (int)((MSUtils.getSignedWord(pageBuffer,358) + 0.0) * 1.0);
        RevLimNormal2_hyst = (int)((MSUtils.getSignedWord(pageBuffer,360) + 0.0) * 1.0);
        pwmidleset_inv = MSUtils.getBits(pageBuffer,362,3,3,0);
        trig_init = (int)((MSUtils.getByte(pageBuffer,363) + 0.0) * 1.0);
        pwmidle_ms = (int)((MSUtils.getWord(pageBuffer,364) + 0.0) * 1.0);
        pwmidle_close_delay = (int)((MSUtils.getByte(pageBuffer,366) + 0.0) * 1.0);
        pwmidle_open_duty = (double)((MSUtils.getByte(pageBuffer,367) + 0.0) * 0.39063);
        pwmidle_open_steps = (int)((MSUtils.getByte(pageBuffer,367) + 0.0) * 1.0);
        pwmidle_closed_duty = (double)((MSUtils.getByte(pageBuffer,368) + 0.0) * 0.39063);
        pwmidle_closed_steps = (int)((MSUtils.getByte(pageBuffer,368) + 0.0) * 1.0);
        pwmidle_pid_wait_timer = (int)((MSUtils.getByte(pageBuffer,369) + 0.0) * 1.0);
        pwmidle_min_duty = (double)((MSUtils.getByte(pageBuffer,370) + 0.0) * 0.39063);
        pwmidle_min_steps = (int)((MSUtils.getByte(pageBuffer,370) + 0.0) * 1.0);
        pwmidle_engage_rpm_adder = (int)((MSUtils.getWord(pageBuffer,371) + 0.0) * 1.0);
        pwmidle_tps_threshold = (double)((MSUtils.getWord(pageBuffer,373) + 0.0) * 0.1);
        pwmidle_dp_adder = (double)((MSUtils.getByte(pageBuffer,375) + 0.0) * 0.39063);
        pwmidle_dp_adder_steps = (int)((MSUtils.getByte(pageBuffer,375) + 0.0) * 1.0);
        pwmidle_rpmdot_threshold = (double)((MSUtils.getWord(pageBuffer,376) + 0.0) * 10.0);
        pwmidle_decelload_threshold = (double)((MSUtils.getWord(pageBuffer,378) + 0.0) * 0.1);
        pwmidle_Kp = (double)((MSUtils.getWord(pageBuffer,380) + 0.0) * 0.1);
        pwmidle_Ki = (double)((MSUtils.getWord(pageBuffer,382) + 0.0) * 0.1);
        pwmidle_Kd = (double)((MSUtils.getWord(pageBuffer,384) + 0.0) * 0.1);
        pwmidle_freq_scale = MSUtils.getBits(pageBuffer,386,0,3,0);
        pwmidle_freq_pin = MSUtils.getBits(pageBuffer,386,5,5,0);
        pwmidle_freq_pin3 = MSUtils.getBits(pageBuffer,386,6,7,0);
        boost_ctl_settings_freq = MSUtils.getBits(pageBuffer,387,0,2,0);
        boost_ctl_settings_on = MSUtils.getBits(pageBuffer,387,3,3,0);
        boost_ctl_settings_cl = MSUtils.getBits(pageBuffer,387,4,4,0);
        boost_ctl_settings_invert = MSUtils.getBits(pageBuffer,387,5,5,0);
        boost_ctl_pins_pwm = MSUtils.getBits(pageBuffer,388,5,7,0);
        boost_ctl_pins = MSUtils.getBits(pageBuffer,388,0,4,0);
        boost_ctl_Kp = (int)((MSUtils.getByte(pageBuffer,389) + 0.0) * 1.0);
        boost_ctl_Ki = (int)((MSUtils.getByte(pageBuffer,390) + 0.0) * 1.0);
        boost_ctl_Kd = (int)((MSUtils.getByte(pageBuffer,391) + 0.0) * 1.0);
        boost_ctl_closeduty = (int)((MSUtils.getByte(pageBuffer,392) + 0.0) * 1.0);
        boost_ctl_openduty = (int)((MSUtils.getByte(pageBuffer,393) + 0.0) * 1.0);
        boost_ctl_ms = (int)((MSUtils.getWord(pageBuffer,394) + 0.0) * 1.0);
        boost_ctl_pwm_scale = MSUtils.getBits(pageBuffer,396,0,3,0);
        boost_ctl_pwm = MSUtils.getBits(pageBuffer,396,4,5,0);
        NoiseFilterOpts = MSUtils.getBits(pageBuffer,397,0,0,0);
        NoiseFilterOpts1 = MSUtils.getBits(pageBuffer,397,1,1,0);
        NoiseFilterOpts2 = MSUtils.getBits(pageBuffer,397,2,2,0);
        NoiseFilterOpts3 = MSUtils.getBits(pageBuffer,397,3,3,0);
        pwmidle_min_rpm = (int)((MSUtils.getWord(pageBuffer,398) + 0.0) * 1.0);
        pwmidle_max_rpm = (int)((MSUtils.getWord(pageBuffer,400) + 0.0) * 1.0);
        pwmidle_targ_ramptime = (int)((MSUtils.getByte(pageBuffer,402) + 0.0) * 1.0);
        inj_time_mask = (int)((MSUtils.getByte(pageBuffer,403) + 0.0) * 1.0);
        secondtrigopts = MSUtils.getBits(pageBuffer,428,0,0,0);
        secondtrigopts1 = MSUtils.getBits(pageBuffer,428,1,1,0);
        secondtrigopts2 = MSUtils.getBits(pageBuffer,428,2,2,0);
        secondtrigopts3 = MSUtils.getBits(pageBuffer,428,3,3,0);
        TC5_required_width = (int)((MSUtils.getWord(pageBuffer,429) + 0.0) * 1.0);
        egoLimit = (double)((MSUtils.getSignedWord(pageBuffer,431) + 0.0) * 0.1);
        stoich = (double)((MSUtils.getSignedWord(pageBuffer,433) + 0.0) * 0.1);
        MAPOXMin = (double)((MSUtils.getSignedWord(pageBuffer,435) + 0.0) * 0.1);
        IC2ISR_tmask = (double)((MSUtils.getByte(pageBuffer,437) + 0.0) * 0.1);
        IC2ISR_pmask = (int)((MSUtils.getByte(pageBuffer,438) + 0.0) * 1.0);
        afrload = MSUtils.getBits(pageBuffer,439,0,2,0);
        eaeload = MSUtils.getBits(pageBuffer,439,4,6,0);
        airden_scaling = (int)((MSUtils.getByte(pageBuffer,440) + 0.0) * 1.0);
        if (MPH)
        {
        fan_idleup_vss_hyst = (double)((MSUtils.getWord(pageBuffer,441) + 0.0) * 0.22369);
        }
        else
        {
        fan_idleup_vss_hyst = (double)((MSUtils.getWord(pageBuffer,441) + 0.0) * 0.36);
        }
        log_style_led = MSUtils.getBits(pageBuffer,443,0,5,0);
        if (PW_4X)
        {
        staged_secondary_enrichment = (double)((MSUtils.getWord(pageBuffer,455) + 0.0) * 0.0040);
        }
        else
        {
        staged_secondary_enrichment = (double)((MSUtils.getWord(pageBuffer,455) + 0.0) * 0.0010);
        }
        staged_primary_delay = (int)((MSUtils.getByte(pageBuffer,457) + 0.0) * 1.0);
        idleadvance_on = MSUtils.getBits(pageBuffer,459,0,0,0);
        idleve_on = MSUtils.getBits(pageBuffer,459,1,1,0);
        idleve_vsson = MSUtils.getBits(pageBuffer,459,2,3,0);
        idleadv_vsson = MSUtils.getBits(pageBuffer,459,4,5,0);
        idleadvance_tps = (double)((MSUtils.getSignedWord(pageBuffer,460) + 0.0) * 0.1);
        idleadvance_rpm = (int)((MSUtils.getSignedWord(pageBuffer,462) + 0.0) * 1.0);
        idleadvance_load = (double)((MSUtils.getSignedWord(pageBuffer,464) + 0.0) * 0.1);
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        idleadvance_clt = (double)((MSUtils.getSignedWord(pageBuffer,466) + -320.0) * 0.05555);
        }
        else
        {
        idleadvance_clt = (double)((MSUtils.getSignedWord(pageBuffer,466) + -320.0) * 0.05555);
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        idleadvance_clt = (double)((MSUtils.getSignedWord(pageBuffer,466) + 0.0) * 0.1);
        }
        else
        {
        idleadvance_clt = (double)((MSUtils.getSignedWord(pageBuffer,466) + 0.0) * 0.1);
        }
        }
        idleadvance_delay = (int)((MSUtils.getByte(pageBuffer,468) + 0.0) * 1.0);
        log_style2_but = MSUtils.getBits(pageBuffer,485,0,4,0);
        log_style_block = MSUtils.getBits(pageBuffer,486,0,1,0);
        log_style_on = MSUtils.getBits(pageBuffer,486,6,7,0);
        log_style2_samp = MSUtils.getBits(pageBuffer,487,3,4,0);
        log_style2_clg = MSUtils.getBits(pageBuffer,487,5,5,0);
        log_style3_adc = MSUtils.getBits(pageBuffer,488,0,2,0);
        log_length = (int)((MSUtils.getByte(pageBuffer,489) + 0.0) * 1.0);
        log_int = (double)((MSUtils.getWord(pageBuffer,490) + 0.0) * 0.128);
        firea = (int)((MSUtils.getByte(pageBuffer,684) + 0.0) * 1.0);
        fireb = (int)((MSUtils.getByte(pageBuffer,685) + 0.0) * 1.0);
        firec = (int)((MSUtils.getByte(pageBuffer,686) + 0.0) * 1.0);
        fired = (int)((MSUtils.getByte(pageBuffer,687) + 0.0) * 1.0);
        firee = (int)((MSUtils.getByte(pageBuffer,688) + 0.0) * 1.0);
        firef = (int)((MSUtils.getByte(pageBuffer,689) + 0.0) * 1.0);
        fireg = (int)((MSUtils.getByte(pageBuffer,690) + 0.0) * 1.0);
        fireh = (int)((MSUtils.getByte(pageBuffer,691) + 0.0) * 1.0);
        firei = (int)((MSUtils.getByte(pageBuffer,692) + 0.0) * 1.0);
        firej = (int)((MSUtils.getByte(pageBuffer,693) + 0.0) * 1.0);
        firek = (int)((MSUtils.getByte(pageBuffer,694) + 0.0) * 1.0);
        firel = (int)((MSUtils.getByte(pageBuffer,695) + 0.0) * 1.0);
        firem = (int)((MSUtils.getByte(pageBuffer,696) + 0.0) * 1.0);
        firen = (int)((MSUtils.getByte(pageBuffer,697) + 0.0) * 1.0);
        fireo = (int)((MSUtils.getByte(pageBuffer,698) + 0.0) * 1.0);
        firep = (int)((MSUtils.getByte(pageBuffer,699) + 0.0) * 1.0);
        sequential = MSUtils.getBits(pageBuffer,700,0,1,0);
        sequential_angle_spec = MSUtils.getBits(pageBuffer,700,2,4,0);
        sequential_trim_on_off = MSUtils.getBits(pageBuffer,700,5,5,0);
        boost_launch_duty = (int)((MSUtils.getByte(pageBuffer,701) + 0.0) * 1.0);
        boost_feats_tsw = MSUtils.getBits(pageBuffer,704,0,4,0);
        boost_feats_launch = MSUtils.getBits(pageBuffer,704,5,5,0);
        boost_feats_timed = MSUtils.getBits(pageBuffer,704,6,6,0);
        launch_3step_in = MSUtils.getBits(pageBuffer,705,0,4,0);
        launch_var_low = (int)((MSUtils.getWord(pageBuffer,706) + 0.0) * 1.0);
        launch_var_up = (int)((MSUtils.getWord(pageBuffer,708) + 0.0) * 1.0);
        launch_var_sof = (int)((MSUtils.getWord(pageBuffer,710) + 0.0) * 1.0);
        launch_sft_lim3 = (int)((MSUtils.getSignedWord(pageBuffer,712) + 0.0) * 1.0);
        launch_sft_deg3 = (double)((MSUtils.getSignedWord(pageBuffer,714) + 0.0) * 0.1);
        launch_hrd_lim3 = (int)((MSUtils.getSignedWord(pageBuffer,716) + 0.0) * 1.0);
        map_sample_duration = (double)((MSUtils.getSignedWord(pageBuffer,718) + 0.0) * 0.1);
        opentime_opt1_master = MSUtils.getBits(pageBuffer,720,7,7,0);
        opentime_opt1 = MSUtils.getBits(pageBuffer,720,0,1,0);
        opentime_opt2 = MSUtils.getBits(pageBuffer,721,0,1,0);
        opentime_opt3 = MSUtils.getBits(pageBuffer,722,0,1,0);
        opentime_opt4 = MSUtils.getBits(pageBuffer,723,0,1,0);
        opentime_opt5 = MSUtils.getBits(pageBuffer,724,0,1,0);
        opentime_opt6 = MSUtils.getBits(pageBuffer,725,0,1,0);
        opentime_opt7 = MSUtils.getBits(pageBuffer,726,0,1,0);
        opentime_opt8 = MSUtils.getBits(pageBuffer,727,0,1,0);
        opentime_opta = MSUtils.getBits(pageBuffer,728,0,1,0);
        opentime_opta_pwm = MSUtils.getBits(pageBuffer,728,4,4,0);
        opentime_optb = MSUtils.getBits(pageBuffer,729,0,1,0);
        opentime_optb_pwm = MSUtils.getBits(pageBuffer,729,4,4,0);
        opentime_optb_own = MSUtils.getBits(pageBuffer,729,5,5,0);
        opentime_opt11 = MSUtils.getBits(pageBuffer,730,0,1,0);
        opentime_opt12 = MSUtils.getBits(pageBuffer,731,0,1,0);
        opentime_opt13 = MSUtils.getBits(pageBuffer,732,0,1,0);
        opentime_opt14 = MSUtils.getBits(pageBuffer,733,0,1,0);
        opentime_opt15 = MSUtils.getBits(pageBuffer,734,0,1,0);
        opentime_opt16 = MSUtils.getBits(pageBuffer,735,0,1,0);
        smallpw_opt1_master = MSUtils.getBits(pageBuffer,738,7,7,0);
        smallpw_opt1 = MSUtils.getBits(pageBuffer,738,0,1,0);
        smallpw_opt2 = MSUtils.getBits(pageBuffer,739,0,1,0);
        smallpw_opt3 = MSUtils.getBits(pageBuffer,740,0,1,0);
        smallpw_opt4 = MSUtils.getBits(pageBuffer,741,0,1,0);
        smallpw_opt5 = MSUtils.getBits(pageBuffer,742,0,1,0);
        smallpw_opt6 = MSUtils.getBits(pageBuffer,743,0,1,0);
        smallpw_opt7 = MSUtils.getBits(pageBuffer,744,0,1,0);
        smallpw_opt8 = MSUtils.getBits(pageBuffer,745,0,1,0);
        smallpw_opta = MSUtils.getBits(pageBuffer,746,0,1,0);
        smallpw_optb = MSUtils.getBits(pageBuffer,747,0,1,0);
        smallpw_opt11 = MSUtils.getBits(pageBuffer,748,0,1,0);
        smallpw_opt12 = MSUtils.getBits(pageBuffer,749,0,1,0);
        smallpw_opt13 = MSUtils.getBits(pageBuffer,750,0,1,0);
        smallpw_opt14 = MSUtils.getBits(pageBuffer,751,0,1,0);
        smallpw_opt15 = MSUtils.getBits(pageBuffer,752,0,1,0);
        smallpw_opt16 = MSUtils.getBits(pageBuffer,753,0,1,0);
        maxafr_opt1_on = MSUtils.getBits(pageBuffer,756,0,0,0);
        maxafr_opt1_load = MSUtils.getBits(pageBuffer,756,1,2,0);
        maxafr_opt1_led = MSUtils.getBits(pageBuffer,757,0,5,0);
        maxafr_en_load = (double)((MSUtils.getWord(pageBuffer,758) + 0.0) * 0.1);
        maxafr_en_rpm = (int)((MSUtils.getWord(pageBuffer,760) + 0.0) * 1.0);
        maxafr_en_time = (double)((MSUtils.getWord(pageBuffer,762) + 0.0) * 0.0010);
        maxafr_spkcut_time = (double)((MSUtils.getWord(pageBuffer,764) + 0.0) * 0.0010);
        maxafr_ret_tps = (double)((MSUtils.getWord(pageBuffer,766) + 0.0) * 0.1);
        maxafr_ret_map = (double)((MSUtils.getWord(pageBuffer,768) + 0.0) * 0.1);
        maxafr_ret_rpm = (int)((MSUtils.getWord(pageBuffer,770) + 0.0) * 1.0);
        if (PW_4X)
        {
        launch_addfuel = (double)((MSUtils.getSignedWord(pageBuffer,772) + 0.0) * 0.0040);
        }
        else
        {
        launch_addfuel = (double)((MSUtils.getSignedWord(pageBuffer,772) + 0.0) * 0.0010);
        }
        if (METRES)
        {
        wheeldia1 = (double)((MSUtils.getWord(pageBuffer,774) + 0.0) * 0.0010);
        wheeldia2 = (double)((MSUtils.getWord(pageBuffer,778) + 0.0) * 0.0010);
        }
        else
        {
        wheeldia1 = (double)((MSUtils.getWord(pageBuffer,774) + 0.0) * 0.03937);
        wheeldia2 = (double)((MSUtils.getWord(pageBuffer,778) + 0.0) * 0.03937);
        }
        fdratio1 = (double)((MSUtils.getWord(pageBuffer,776) + 0.0) * 0.01);
        vss1_pos = MSUtils.getBits(pageBuffer,780,0,1,0);
        vss2_pos = MSUtils.getBits(pageBuffer,780,2,3,0);
        launch_var_on = MSUtils.getBits(pageBuffer,781,0,4,0);
        reluctorteeth1 = (int)((MSUtils.getByte(pageBuffer,782) + 0.0) * 1.0);
        reluctorteeth2 = (int)((MSUtils.getByte(pageBuffer,783) + 0.0) * 1.0);
        vss_opt1 = MSUtils.getBits(pageBuffer,784,0,3,0);
        vss_opt2 = MSUtils.getBits(pageBuffer,784,4,7,0);
        vss1_an = MSUtils.getBits(pageBuffer,785,0,4,0);
        vss1_can_id = (int)((MSUtils.getByte(pageBuffer,786) + 0.0) * 1.0);
        tsw_pin_ob = MSUtils.getBits(pageBuffer,787,0,4,0);
        vss1_can_offset = (int)((MSUtils.getWord(pageBuffer,788) + 0.0) * 1.0);
        vss2_can_offset = (int)((MSUtils.getWord(pageBuffer,790) + 0.0) * 1.0);
        MapThreshXTD = (int)((MSUtils.getByte(pageBuffer,792) + 0.0) * 1.0);
        MapThreshXTD2 = (int)((MSUtils.getByte(pageBuffer,793) + 0.0) * 1.0);
        reluctorteeth3 = (int)((MSUtils.getByte(pageBuffer,794) + 0.0) * 1.0);
        reluctorteeth4 = (int)((MSUtils.getByte(pageBuffer,795) + 0.0) * 1.0);
        ss_opt1 = MSUtils.getBits(pageBuffer,796,0,3,0);
        ss_opt2 = MSUtils.getBits(pageBuffer,796,4,7,0);
        vss2_an = MSUtils.getBits(pageBuffer,797,0,4,0);
        ss1_pwmseq = (int)((MSUtils.getByte(pageBuffer,798) + 0.0) * 1.0);
        ss2_pwmseq = (int)((MSUtils.getByte(pageBuffer,799) + 0.0) * 1.0);
        gear_can_offset = (int)((MSUtils.getWord(pageBuffer,800) + 0.0) * 1.0);
        mapsample_opt1 = MSUtils.getBits(pageBuffer,802,0,1,0);
        n2o1n_pins = MSUtils.getBits(pageBuffer,804,0,4,0);
        n2o1f_pins = MSUtils.getBits(pageBuffer,805,0,4,0);
        n2o2n_pins = MSUtils.getBits(pageBuffer,806,0,4,0);
        n2o2f_pins = MSUtils.getBits(pageBuffer,807,0,4,0);
        if (MPH)
        {
        vss1_an_max = (double)((MSUtils.getWord(pageBuffer,808) + 0.0) * 0.22369);
        vss2_an_max = (double)((MSUtils.getWord(pageBuffer,810) + 0.0) * 0.22369);
        }
        else
        {
        vss1_an_max = (double)((MSUtils.getWord(pageBuffer,808) + 0.0) * 0.36);
        vss2_an_max = (double)((MSUtils.getWord(pageBuffer,810) + 0.0) * 0.36);
        }
        tsw_pin_rf = MSUtils.getBits(pageBuffer,812,0,4,0);
        tsw_pin_afr = MSUtils.getBits(pageBuffer,813,0,4,0);
        tsw_pin_stoich = MSUtils.getBits(pageBuffer,814,0,4,0);
        boost_vss = MSUtils.getBits(pageBuffer,815,0,1,0);
        if (PW_4X)
        {
        ReqFuel_alt = (double)((MSUtils.getWord(pageBuffer,816) + 0.0) * 0.0040);
        }
        else
        {
        ReqFuel_alt = (double)((MSUtils.getWord(pageBuffer,816) + 0.0) * 0.0010);
        }
        stoich_alt = (double)((MSUtils.getSignedWord(pageBuffer,818) + 0.0) * 0.1);
        water_pins_pump = MSUtils.getBits(pageBuffer,820,0,4,0);
        water_pins_valve = MSUtils.getBits(pageBuffer,821,0,4,0);
        water_pins_in_shut = MSUtils.getBits(pageBuffer,822,0,4,0);
        water_freq = MSUtils.getBits(pageBuffer,823,0,2,0);
        water_freq_on = MSUtils.getBits(pageBuffer,823,4,4,0);
        water_freq_type = MSUtils.getBits(pageBuffer,823,5,6,0);
        boost_vss_tps = (double)((MSUtils.getWord(pageBuffer,824) + 0.0) * 0.1);
        water_tps = (double)((MSUtils.getWord(pageBuffer,826) + 0.0) * 0.1);
        water_rpm = (int)((MSUtils.getWord(pageBuffer,828) + 0.0) * 1.0);
        water_map = (double)((MSUtils.getWord(pageBuffer,830) + 0.0) * 0.1);
        if (CELSIUS)
        {
        water_mat = (double)((MSUtils.getSignedWord(pageBuffer,832) + -320.0) * 0.05555);
        }
        else
        {
        water_mat = (double)((MSUtils.getSignedWord(pageBuffer,832) + 0.0) * 0.1);
        }
        pwmidle_rpmdot_disablepid = (double)((MSUtils.getSignedWord(pageBuffer,834) + 0.0) * 10.0);
        boost_ctl_lowerlimit = (double)((MSUtils.getSignedWord(pageBuffer,836) + 0.0) * 0.1);
        enable_pollADC = MSUtils.getBits(pageBuffer,838,0,0,0);
        enable_pollPWM = MSUtils.getBits(pageBuffer,838,1,2,0);
        enable_pollports_digin = MSUtils.getBits(pageBuffer,838,3,3,0);
        enable_pollports_digout = MSUtils.getBits(pageBuffer,838,4,5,0);
        enable_pwmout = MSUtils.getBits(pageBuffer,838,6,6,0);
        poll_table_rtc = (int)((MSUtils.getByte(pageBuffer,839) + 0.0) * 1.0);
        poll_tablePWM = (int)((MSUtils.getByte(pageBuffer,840) + 0.0) * 1.0);
        poll_tableports = (int)((MSUtils.getByte(pageBuffer,841) + 0.0) * 1.0);
        poll_offset_rtc = (int)((MSUtils.getSignedWord(pageBuffer,842) + 0.0) * 1.0);
        poll_offsetPWM = (int)((MSUtils.getSignedWord(pageBuffer,844) + 0.0) * 1.0);
        can_poll_digin_offset = (int)((MSUtils.getSignedWord(pageBuffer,846) + 0.0) * 1.0);
        can_poll_digout_offset = (int)((MSUtils.getSignedWord(pageBuffer,848) + 0.0) * 1.0);
        egt_num = MSUtils.getBits(pageBuffer,850,0,4,0);
        accXport = MSUtils.getBits(pageBuffer,851,0,4,0);
        accYport = MSUtils.getBits(pageBuffer,852,0,4,0);
        accZport = MSUtils.getBits(pageBuffer,853,0,4,0);
        accXcal1 = (int)((MSUtils.getSignedWord(pageBuffer,854) + 0.0) * 1.0);
        accXcal2 = (int)((MSUtils.getSignedWord(pageBuffer,856) + 0.0) * 1.0);
        accYcal1 = (int)((MSUtils.getSignedWord(pageBuffer,858) + 0.0) * 1.0);
        accYcal2 = (int)((MSUtils.getSignedWord(pageBuffer,860) + 0.0) * 1.0);
        accZcal1 = (int)((MSUtils.getSignedWord(pageBuffer,862) + 0.0) * 1.0);
        accZcal2 = (int)((MSUtils.getSignedWord(pageBuffer,864) + 0.0) * 1.0);
        accxyzLF = (int)((MSUtils.getByte(pageBuffer,866) + 0.0) * 1.0);
        egt1port = MSUtils.getBits(pageBuffer,867,0,4,0);
        egt2port = MSUtils.getBits(pageBuffer,868,0,4,0);
        egt3port = MSUtils.getBits(pageBuffer,869,0,4,0);
        egt4port = MSUtils.getBits(pageBuffer,870,0,4,0);
        egt5port = MSUtils.getBits(pageBuffer,871,0,4,0);
        egt6port = MSUtils.getBits(pageBuffer,872,0,4,0);
        egt7port = MSUtils.getBits(pageBuffer,873,0,4,0);
        egt8port = MSUtils.getBits(pageBuffer,874,0,4,0);
        egt9port = MSUtils.getBits(pageBuffer,875,0,4,0);
        egt10port = MSUtils.getBits(pageBuffer,876,0,4,0);
        egt11port = MSUtils.getBits(pageBuffer,877,0,4,0);
        egt12port = MSUtils.getBits(pageBuffer,878,0,4,0);
        egt13port = MSUtils.getBits(pageBuffer,879,0,4,0);
        egt14port = MSUtils.getBits(pageBuffer,880,0,4,0);
        egt15port = MSUtils.getBits(pageBuffer,881,0,4,0);
        egt16port = MSUtils.getBits(pageBuffer,882,0,4,0);
        egt_conf_action = MSUtils.getBits(pageBuffer,883,0,0,0);
        egt_conf_shutdown = MSUtils.getBits(pageBuffer,883,1,1,0);
        egt_conf_bank = MSUtils.getBits(pageBuffer,883,2,3,0);
        if (CELSIUS)
        {
        egtcal_temp0 = (double)((MSUtils.getSignedWord(pageBuffer,884) + -320.0) * 0.05555);
        egtcal_tempmax = (double)((MSUtils.getSignedWord(pageBuffer,886) + -320.0) * 0.05555);
        egt_warn = (double)((MSUtils.getSignedWord(pageBuffer,888) + -320.0) * 0.05555);
        egt_max = (double)((MSUtils.getSignedWord(pageBuffer,890) + -320.0) * 0.05555);
        }
        else
        {
        egtcal_temp0 = (double)((MSUtils.getSignedWord(pageBuffer,884) + 0.0) * 0.1);
        egtcal_tempmax = (double)((MSUtils.getSignedWord(pageBuffer,886) + 0.0) * 0.1);
        egt_warn = (double)((MSUtils.getSignedWord(pageBuffer,888) + 0.0) * 0.1);
        egt_max = (double)((MSUtils.getSignedWord(pageBuffer,890) + 0.0) * 0.1);
        }
        egt_time = (double)((MSUtils.getWord(pageBuffer,892) + 0.0) * 0.0010);
        vss1_can_scale = (double)((MSUtils.getWord(pageBuffer,894) + 0.0) * 0.1);
        vss2_can_scale = (double)((MSUtils.getWord(pageBuffer,896) + 0.0) * 0.1);
        vss1_pwmseq = (int)((MSUtils.getByte(pageBuffer,898) + 0.0) * 1.0);
        vss2_pwmseq = (int)((MSUtils.getByte(pageBuffer,899) + 0.0) * 1.0);
        MAFOption = MSUtils.getBits(pageBuffer,900,0,4,0);
        MAFOption_t = MSUtils.getBits(pageBuffer,900,5,5,0);
        MAFOption_f = MSUtils.getBits(pageBuffer,900,6,7,0);
        enginesize = (int)((MSUtils.getWord(pageBuffer,901) + 0.0) * 1.0);
        vssout_opt = MSUtils.getBits(pageBuffer,903,0,4,0);
        vssout_optunits = MSUtils.getBits(pageBuffer,903,6,7,0);
        vssout_scale = (double)((MSUtils.getWord(pageBuffer,904) + 0.0) * 0.1);
        vss1_can_size = MSUtils.getBits(pageBuffer,906,0,0,0);
        vss2_can_size = MSUtils.getBits(pageBuffer,906,1,1,0);
        canpwm_clk = (int)((MSUtils.getByte(pageBuffer,907) + 0.0) * 1.0);
        canpwm_pre = (int)((MSUtils.getByte(pageBuffer,908) + 0.0) * 1.0);
        canpwm_div = (int)((MSUtils.getByte(pageBuffer,909) + 0.0) * 1.0);
        vss1_can_table = (int)((MSUtils.getByte(pageBuffer,910) + 0.0) * 1.0);
        UNUSED_1_911 = (int)((MSUtils.getByte(pageBuffer,911) + 0.0) * 1.0);
        vss1LF = (int)((MSUtils.getByte(pageBuffer,912) + 0.0) * 1.0);
        vss2LF = (int)((MSUtils.getByte(pageBuffer,913) + 0.0) * 1.0);
        ss1LF = (int)((MSUtils.getByte(pageBuffer,914) + 0.0) * 1.0);
        ss2LF = (int)((MSUtils.getByte(pageBuffer,915) + 0.0) * 1.0);
        if (PW_4X)
        {
        egt_addfuel = (double)((MSUtils.getWord(pageBuffer,916) + 0.0) * 0.0040);
        }
        else
        {
        egt_addfuel = (double)((MSUtils.getWord(pageBuffer,916) + 0.0) * 0.0010);
        }
        launch_fcut_rpm = (int)((MSUtils.getWord(pageBuffer,918) + 0.0) * 1.0);
        gear1ratio = (double)((MSUtils.getWord(pageBuffer,920) + 0.0) * 0.01);
        gear2ratio = (double)((MSUtils.getWord(pageBuffer,922) + 0.0) * 0.01);
        gear3ratio = (double)((MSUtils.getWord(pageBuffer,924) + 0.0) * 0.01);
        gear4ratio = (double)((MSUtils.getWord(pageBuffer,926) + 0.0) * 0.01);
        gear5ratio = (double)((MSUtils.getWord(pageBuffer,928) + 0.0) * 0.01);
        gear6ratio = (double)((MSUtils.getWord(pageBuffer,930) + 0.0) * 0.01);
        gear_method = MSUtils.getBits(pageBuffer,932,0,1,0);
        gear_port_an = MSUtils.getBits(pageBuffer,933,0,4,0);
        gear0v = (double)((MSUtils.getWord(pageBuffer,934) + 0.0) * 0.01);
        gear1v = (double)((MSUtils.getWord(pageBuffer,936) + 0.0) * 0.01);
        gear2v = (double)((MSUtils.getWord(pageBuffer,938) + 0.0) * 0.01);
        gear3v = (double)((MSUtils.getWord(pageBuffer,940) + 0.0) * 0.01);
        gear4v = (double)((MSUtils.getWord(pageBuffer,942) + 0.0) * 0.01);
        gear5v = (double)((MSUtils.getWord(pageBuffer,944) + 0.0) * 0.01);
        gear6v = (double)((MSUtils.getWord(pageBuffer,946) + 0.0) * 0.01);
        gear_no = (int)((MSUtils.getByte(pageBuffer,948) + 0.0) * 1.0);
        vssdotLF = (int)((MSUtils.getByte(pageBuffer,949) + 0.0) * 1.0);
        vssdot_int = (double)((MSUtils.getByte(pageBuffer,950) + 0.0) * 10.0);
        ac_idleup_io_in = MSUtils.getBits(pageBuffer,951,0,4,0);
        ac_idleup_settings = MSUtils.getBits(pageBuffer,951,7,7,0);
        ac_idleup_io_out = MSUtils.getBits(pageBuffer,952,0,5,0);
        ac_idleup_delay = (int)((MSUtils.getWord(pageBuffer,953) + 0.0) * 1.0);
        ac_idleup_adder_duty = (double)((MSUtils.getByte(pageBuffer,955) + 0.0) * 0.39063);
        ac_idleup_adder_steps = (int)((MSUtils.getByte(pageBuffer,955) + 0.0) * 1.0);
        fanctl_settings_on = MSUtils.getBits(pageBuffer,956,7,7,0);
        fanctl_settings_idleup = MSUtils.getBits(pageBuffer,956,6,6,0);
        fanctl_settings_pin = MSUtils.getBits(pageBuffer,956,0,5,0);
        fanctl_idleup_delay = (int)((MSUtils.getWord(pageBuffer,957) + 0.0) * 1.0);
        fanctl_idleup_adder_duty = (double)((MSUtils.getByte(pageBuffer,959) + 0.0) * 0.39063);
        fanctl_idleup_adder_steps = (int)((MSUtils.getByte(pageBuffer,959) + 0.0) * 1.0);
        if (CELSIUS)
        {
        fanctl_ontemp = (double)((MSUtils.getSignedWord(pageBuffer,960) + -320.0) * 0.05555);
        fanctl_offtemp = (double)((MSUtils.getSignedWord(pageBuffer,962) + -320.0) * 0.05555);
        }
        else
        {
        fanctl_ontemp = (double)((MSUtils.getSignedWord(pageBuffer,960) + 0.0) * 0.1);
        fanctl_offtemp = (double)((MSUtils.getSignedWord(pageBuffer,962) + 0.0) * 0.1);
        }
        canadc_opt1 = MSUtils.getBits(pageBuffer,964,0,0,0);
        canadc_opt2 = MSUtils.getBits(pageBuffer,964,1,1,0);
        canadc_opt3 = MSUtils.getBits(pageBuffer,964,2,2,0);
        canadc_opt4 = MSUtils.getBits(pageBuffer,964,3,3,0);
        canadc_opt5 = MSUtils.getBits(pageBuffer,964,4,4,0);
        canadc_opt6 = MSUtils.getBits(pageBuffer,964,5,5,0);
        fanctl_opt2_engineoff = MSUtils.getBits(pageBuffer,965,0,0,0);
        canadc_id1 = (int)((MSUtils.getByte(pageBuffer,966) + 0.0) * 1.0);
        canadc_id2 = (int)((MSUtils.getByte(pageBuffer,967) + 0.0) * 1.0);
        canadc_id3 = (int)((MSUtils.getByte(pageBuffer,968) + 0.0) * 1.0);
        canadc_id4 = (int)((MSUtils.getByte(pageBuffer,969) + 0.0) * 1.0);
        canadc_id5 = (int)((MSUtils.getByte(pageBuffer,970) + 0.0) * 1.0);
        canadc_id6 = (int)((MSUtils.getByte(pageBuffer,971) + 0.0) * 1.0);
        canadc_tab1 = (int)((MSUtils.getByte(pageBuffer,972) + 0.0) * 1.0);
        canadc_tab2 = (int)((MSUtils.getByte(pageBuffer,973) + 0.0) * 1.0);
        canadc_tab3 = (int)((MSUtils.getByte(pageBuffer,974) + 0.0) * 1.0);
        canadc_tab4 = (int)((MSUtils.getByte(pageBuffer,975) + 0.0) * 1.0);
        canadc_tab5 = (int)((MSUtils.getByte(pageBuffer,976) + 0.0) * 1.0);
        canadc_tab6 = (int)((MSUtils.getByte(pageBuffer,977) + 0.0) * 1.0);
        canadc_off1 = (int)((MSUtils.getWord(pageBuffer,978) + 0.0) * 1.0);
        canadc_off2 = (int)((MSUtils.getWord(pageBuffer,980) + 0.0) * 1.0);
        canadc_off3 = (int)((MSUtils.getWord(pageBuffer,982) + 0.0) * 1.0);
        canadc_off4 = (int)((MSUtils.getWord(pageBuffer,984) + 0.0) * 1.0);
        canadc_off5 = (int)((MSUtils.getWord(pageBuffer,986) + 0.0) * 1.0);
        canadc_off6 = (int)((MSUtils.getWord(pageBuffer,988) + 0.0) * 1.0);
        ac_idleup_cl_targetadder = (int)((MSUtils.getSignedWord(pageBuffer,990) + 0.0) * 1.0);
        fan_idleup_cl_targetadder = (int)((MSUtils.getSignedWord(pageBuffer,992) + 0.0) * 1.0);
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        boost_ctl_clt_threshold = (double)((MSUtils.getSignedWord(pageBuffer,994) + -320.0) * 0.05555);
        }
        else
        {
        boost_ctl_clt_threshold = (double)((MSUtils.getSignedWord(pageBuffer,994) + -320.0) * 0.05555);
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        boost_ctl_clt_threshold = (double)((MSUtils.getSignedWord(pageBuffer,994) + 0.0) * 0.1);
        }
        else
        {
        boost_ctl_clt_threshold = (double)((MSUtils.getSignedWord(pageBuffer,994) + 0.0) * 0.1);
        }
        }
        fan_ctl_settings_acfan = MSUtils.getBits(pageBuffer,996,0,0,0);
        if (LAMBDA)
        {
        ego_upper_bound = (double)((MSUtils.getByte(pageBuffer,997) + 0.0) * 0.006803);
        ego_lower_bound = (double)((MSUtils.getByte(pageBuffer,998) + 0.0) * 0.006803);
        }
        else
        {
        ego_upper_bound = (double)((MSUtils.getByte(pageBuffer,997) + 0.0) * 0.1);
        ego_lower_bound = (double)((MSUtils.getByte(pageBuffer,998) + 0.0) * 0.1);
        }
        boost_gear_switch = (int)((MSUtils.getByte(pageBuffer,1008) + 0.0) * 1.0);
        staged_extended_opts_use_v3 = MSUtils.getBits(pageBuffer,1009,0,0,0);
        staged_extended_opts_simult = MSUtils.getBits(pageBuffer,1009,1,1,0);
        staged_extended_opts_pw1off = MSUtils.getBits(pageBuffer,1009,2,2,0);
        can_pwmout_id = (int)((MSUtils.getByte(pageBuffer,1010) + 0.0) * 1.0);
        can_pwmout_tab = (int)((MSUtils.getByte(pageBuffer,1011) + 0.0) * 1.0);
        can_pwmout_offset = (int)((MSUtils.getSignedWord(pageBuffer,1012) + 0.0) * 1.0);
        idleve_tps = (double)((MSUtils.getSignedWord(pageBuffer,1014) + 0.0) * 0.1);
        idleve_rpm = (int)((MSUtils.getWord(pageBuffer,1016) + 0.0) * 1.0);
        idleve_load = (double)((MSUtils.getSignedWord(pageBuffer,1018) + 0.0) * 0.1);
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        idleve_clt = (double)((MSUtils.getSignedWord(pageBuffer,1020) + -320.0) * 0.05555);
        }
        else
        {
        idleve_clt = (double)((MSUtils.getSignedWord(pageBuffer,1020) + -320.0) * 0.05555);
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        idleve_clt = (double)((MSUtils.getSignedWord(pageBuffer,1020) + 0.0) * 0.1);
        }
        else
        {
        idleve_clt = (double)((MSUtils.getSignedWord(pageBuffer,1020) + 0.0) * 0.1);
        }
        }
        idleve_delay = (int)((MSUtils.getByte(pageBuffer,1022) + 0.0) * 1.0);
        ac_idleup_cl_lockout_mapadder = (double)((MSUtils.getByte(pageBuffer,1023) + 0.0) * 0.1);
        pageBuffer = loadPage(2,0,1024,null,new byte[]{114,0,5,0,0,4,0});
        testmodelock = (int)((MSUtils.getWord(pageBuffer,0) + 0.0) * 1.0);
        testop_0 = (int)((MSUtils.getByte(pageBuffer,0) + 0.0) * 1.0);
        testop_coil = MSUtils.getBits(pageBuffer,2,0,1,0);
        testop_fp = MSUtils.getBits(pageBuffer,2,4,4,0);
        testop_inj = MSUtils.getBits(pageBuffer,2,5,6,0);
        testop_pwm = MSUtils.getBits(pageBuffer,2,7,7,0);
        testdwell = (double)((MSUtils.getByte(pageBuffer,3) + 0.0) * 0.1);
        testint = (double)((MSUtils.getWord(pageBuffer,4) + 0.0) * 0.128);
        testmode_0 = (int)((MSUtils.getByte(pageBuffer,6) + 0.0) * 1.0);
        testmode = MSUtils.getBits(pageBuffer,6,0,1,0);
        if (PW_4X)
        {
        testpw = (double)((MSUtils.getWord(pageBuffer,7) + 0.0) * 0.0040);
        }
        else
        {
        testpw = (double)((MSUtils.getWord(pageBuffer,7) + 0.0) * 0.0010);
        }
        testinjcnt = (int)((MSUtils.getWord(pageBuffer,9) + 0.0) * 1.0);
        testinjPwmT = (double)((MSUtils.getByte(pageBuffer,11) + 0.128) * 0.128);
        testinjPwmPd = (int)((MSUtils.getByte(pageBuffer,12) + 0.0) * 1.0);
        testinjPwmP = (int)((MSUtils.getByte(pageBuffer,13) + 0.0) * 1.0);
        testsel_inj = MSUtils.getBits(pageBuffer,14,0,3,0);
        testsel_coil = MSUtils.getBits(pageBuffer,14,4,7,0);
        iacpostest = (int)((MSUtils.getWord(pageBuffer,15) + 0.0) * 1.0);
        dbgtooth = (int)((MSUtils.getByte(pageBuffer,17) + 0.0) * 1.0);
        iachometest = (int)((MSUtils.getWord(pageBuffer,18) + 0.0) * 1.0);
        iactest = MSUtils.getBits(pageBuffer,20,0,0,0);
        iactestlock = MSUtils.getBits(pageBuffer,20,1,1,0);
        flashlock = MSUtils.getBits(pageBuffer,21,0,0,0);
        boost_ctl_settings_on2 = MSUtils.getBits(pageBuffer,22,3,3,0);
        boost_ctl_settings_cl2 = MSUtils.getBits(pageBuffer,22,4,4,0);
        boost_ctl_pins_pwm2 = MSUtils.getBits(pageBuffer,23,5,7,0);
        boost_ctl_pins2 = MSUtils.getBits(pageBuffer,23,0,4,0);
        boost_ctl_Kp2 = (int)((MSUtils.getByte(pageBuffer,24) + 0.0) * 1.0);
        boost_ctl_Ki2 = (int)((MSUtils.getByte(pageBuffer,25) + 0.0) * 1.0);
        boost_ctl_Kd2 = (int)((MSUtils.getByte(pageBuffer,26) + 0.0) * 1.0);
        boost_ctl_closeduty2 = (int)((MSUtils.getByte(pageBuffer,27) + 0.0) * 1.0);
        boost_ctl_openduty2 = (int)((MSUtils.getByte(pageBuffer,28) + 0.0) * 1.0);
        boost_ctl_pwm_scale2 = MSUtils.getBits(pageBuffer,29,0,3,0);
        boost_ctl_lowerlimit2 = (double)((MSUtils.getSignedWord(pageBuffer,30) + 0.0) * 0.1);
        boost_ctl_sensor2 = MSUtils.getBits(pageBuffer,32,0,3,0);
        egoport1 = MSUtils.getBits(pageBuffer,40,0,4,0);
        egoport2 = MSUtils.getBits(pageBuffer,41,0,4,0);
        egoport3 = MSUtils.getBits(pageBuffer,42,0,4,0);
        egoport4 = MSUtils.getBits(pageBuffer,43,0,4,0);
        egoport5 = MSUtils.getBits(pageBuffer,44,0,4,0);
        egoport6 = MSUtils.getBits(pageBuffer,45,0,4,0);
        egoport7 = MSUtils.getBits(pageBuffer,46,0,4,0);
        egoport8 = MSUtils.getBits(pageBuffer,47,0,4,0);
        egoport9 = MSUtils.getBits(pageBuffer,48,0,4,0);
        egoport10 = MSUtils.getBits(pageBuffer,49,0,4,0);
        egoport11 = MSUtils.getBits(pageBuffer,50,0,4,0);
        egoport12 = MSUtils.getBits(pageBuffer,51,0,4,0);
        egoport13 = MSUtils.getBits(pageBuffer,52,0,4,0);
        egoport14 = MSUtils.getBits(pageBuffer,53,0,4,0);
        egoport15 = MSUtils.getBits(pageBuffer,54,0,4,0);
        egoport16 = MSUtils.getBits(pageBuffer,55,0,4,0);
        egomap1 = MSUtils.getBits(pageBuffer,56,0,3,0);
        egomap2 = MSUtils.getBits(pageBuffer,57,0,2,0);
        egomap3 = MSUtils.getBits(pageBuffer,58,0,2,0);
        egomap4 = MSUtils.getBits(pageBuffer,59,0,2,0);
        egomap5 = MSUtils.getBits(pageBuffer,60,0,2,0);
        egomap6 = MSUtils.getBits(pageBuffer,61,0,2,0);
        egomap7 = MSUtils.getBits(pageBuffer,62,0,2,0);
        egomap8 = MSUtils.getBits(pageBuffer,63,0,2,0);
        egomap9 = MSUtils.getBits(pageBuffer,64,0,2,0);
        egomap10 = MSUtils.getBits(pageBuffer,65,0,2,0);
        egomap11 = MSUtils.getBits(pageBuffer,66,0,2,0);
        egomap12 = MSUtils.getBits(pageBuffer,67,0,2,0);
        egomap13 = MSUtils.getBits(pageBuffer,68,0,2,0);
        egomap14 = MSUtils.getBits(pageBuffer,69,0,2,0);
        egomap15 = MSUtils.getBits(pageBuffer,70,0,2,0);
        egomap16 = MSUtils.getBits(pageBuffer,71,0,2,0);
        egomap1t = MSUtils.getBits(pageBuffer,56,4,4,0);
        egomap2t = MSUtils.getBits(pageBuffer,57,4,4,0);
        egomap3t = MSUtils.getBits(pageBuffer,58,4,4,0);
        egomap4t = MSUtils.getBits(pageBuffer,59,4,4,0);
        egomap5t = MSUtils.getBits(pageBuffer,60,4,4,0);
        egomap6t = MSUtils.getBits(pageBuffer,61,4,4,0);
        egomap7t = MSUtils.getBits(pageBuffer,62,4,4,0);
        egomap8t = MSUtils.getBits(pageBuffer,63,4,4,0);
        egomap9t = MSUtils.getBits(pageBuffer,64,4,4,0);
        egomap10t = MSUtils.getBits(pageBuffer,65,4,4,0);
        egomap11t = MSUtils.getBits(pageBuffer,66,4,4,0);
        egomap12t = MSUtils.getBits(pageBuffer,67,4,4,0);
        egomap13t = MSUtils.getBits(pageBuffer,68,4,4,0);
        egomap14t = MSUtils.getBits(pageBuffer,69,4,4,0);
        egomap15t = MSUtils.getBits(pageBuffer,70,4,4,0);
        egomap16t = MSUtils.getBits(pageBuffer,71,4,4,0);
        tc_opt_on = MSUtils.getBits(pageBuffer,72,0,0,0);
        tc_opt_method = MSUtils.getBits(pageBuffer,72,1,2,0);
        tc_opt_n2o = MSUtils.getBits(pageBuffer,72,5,5,0);
        tc_opt_bank = MSUtils.getBits(pageBuffer,72,6,7,0);
        tc_slipthresh = (int)((MSUtils.getByte(pageBuffer,73) + 0.0) * 1.0);
        if (MPH)
        {
        tc_minvss = (double)((MSUtils.getWord(pageBuffer,74) + 0.0) * 0.22369);
        tc_maxvss = (double)((MSUtils.getWord(pageBuffer,76) + 0.0) * 0.22369);
        }
        else
        {
        tc_minvss = (double)((MSUtils.getWord(pageBuffer,74) + 0.0) * 0.36);
        tc_maxvss = (double)((MSUtils.getWord(pageBuffer,76) + 0.0) * 0.36);
        }
        tc_mintps = (double)((MSUtils.getSignedWord(pageBuffer,78) + 0.0) * 0.1);
        tc_enin = MSUtils.getBits(pageBuffer,80,0,4,0);
        knock_bpass = MSUtils.getBits(pageBuffer,81,0,5,0);
        knock_conf_num = MSUtils.getBits(pageBuffer,82,0,0,0);
        knock_conf_percyl = MSUtils.getBits(pageBuffer,82,7,7,0);
        knock_int = MSUtils.getBits(pageBuffer,83,0,4,0);
        ff_tpw0 = (double)((MSUtils.getByte(pageBuffer,84) + 0.0) * 0.05);
        ff_tpw1 = (double)((MSUtils.getByte(pageBuffer,85) + 0.0) * 0.05);
        if (CELSIUS)
        {
        ff_temp0 = (double)((MSUtils.getSignedWord(pageBuffer,86) + -320.0) * 0.05555);
        ff_temp1 = (double)((MSUtils.getSignedWord(pageBuffer,88) + -320.0) * 0.05555);
        }
        else
        {
        ff_temp0 = (double)((MSUtils.getSignedWord(pageBuffer,86) + 0.0) * 0.1);
        ff_temp1 = (double)((MSUtils.getSignedWord(pageBuffer,88) + 0.0) * 0.1);
        }
        fueltemp1 = MSUtils.getBits(pageBuffer,90,0,4,0);
        maf_freq0 = (double)((MSUtils.getWord(pageBuffer,92) + 0.0) * 0.2);
        maf_freq1 = (double)((MSUtils.getWord(pageBuffer,94) + 0.0) * 0.2);
        map_freq0 = (double)((MSUtils.getWord(pageBuffer,96) + 0.0) * 0.2);
        map_freq1 = (double)((MSUtils.getWord(pageBuffer,98) + 0.0) * 0.2);
        knock_gain01 = MSUtils.getBits(pageBuffer,100,0,5,0);
        knock_gain02 = MSUtils.getBits(pageBuffer,101,0,5,0);
        knock_gain03 = MSUtils.getBits(pageBuffer,102,0,5,0);
        knock_gain04 = MSUtils.getBits(pageBuffer,103,0,5,0);
        knock_gain05 = MSUtils.getBits(pageBuffer,104,0,5,0);
        knock_gain06 = MSUtils.getBits(pageBuffer,105,0,5,0);
        knock_gain07 = MSUtils.getBits(pageBuffer,106,0,5,0);
        knock_gain08 = MSUtils.getBits(pageBuffer,107,0,5,0);
        knock_gain09 = MSUtils.getBits(pageBuffer,108,0,5,0);
        knock_gain10 = MSUtils.getBits(pageBuffer,109,0,5,0);
        knock_gain11 = MSUtils.getBits(pageBuffer,110,0,5,0);
        knock_gain12 = MSUtils.getBits(pageBuffer,111,0,5,0);
        knock_gain13 = MSUtils.getBits(pageBuffer,112,0,5,0);
        knock_gain14 = MSUtils.getBits(pageBuffer,113,0,5,0);
        knock_gain15 = MSUtils.getBits(pageBuffer,114,0,5,0);
        knock_gain16 = MSUtils.getBits(pageBuffer,115,0,5,0);
        knock_sens01 = MSUtils.getBits(pageBuffer,116,0,0,0);
        knock_sens02 = MSUtils.getBits(pageBuffer,117,0,0,0);
        knock_sens03 = MSUtils.getBits(pageBuffer,118,0,0,0);
        knock_sens04 = MSUtils.getBits(pageBuffer,119,0,0,0);
        knock_sens05 = MSUtils.getBits(pageBuffer,120,0,0,0);
        knock_sens06 = MSUtils.getBits(pageBuffer,121,0,0,0);
        knock_sens07 = MSUtils.getBits(pageBuffer,122,0,0,0);
        knock_sens08 = MSUtils.getBits(pageBuffer,123,0,0,0);
        knock_sens09 = MSUtils.getBits(pageBuffer,124,0,0,0);
        knock_sens10 = MSUtils.getBits(pageBuffer,125,0,0,0);
        knock_sens11 = MSUtils.getBits(pageBuffer,126,0,0,0);
        knock_sens12 = MSUtils.getBits(pageBuffer,127,0,0,0);
        knock_sens13 = MSUtils.getBits(pageBuffer,128,0,0,0);
        knock_sens14 = MSUtils.getBits(pageBuffer,129,0,0,0);
        knock_sens15 = MSUtils.getBits(pageBuffer,130,0,0,0);
        knock_sens16 = MSUtils.getBits(pageBuffer,131,0,0,0);
        sensor01_source = MSUtils.getBits(pageBuffer,662,0,4,0);
        sensor02_source = MSUtils.getBits(pageBuffer,663,0,4,0);
        sensor03_source = MSUtils.getBits(pageBuffer,664,0,4,0);
        sensor04_source = MSUtils.getBits(pageBuffer,665,0,4,0);
        sensor05_source = MSUtils.getBits(pageBuffer,666,0,4,0);
        sensor06_source = MSUtils.getBits(pageBuffer,667,0,4,0);
        sensor07_source = MSUtils.getBits(pageBuffer,668,0,4,0);
        sensor08_source = MSUtils.getBits(pageBuffer,669,0,4,0);
        sensor09_source = MSUtils.getBits(pageBuffer,670,0,4,0);
        sensor10_source = MSUtils.getBits(pageBuffer,671,0,4,0);
        sensor11_source = MSUtils.getBits(pageBuffer,672,0,4,0);
        sensor12_source = MSUtils.getBits(pageBuffer,673,0,4,0);
        sensor13_source = MSUtils.getBits(pageBuffer,674,0,4,0);
        sensor14_source = MSUtils.getBits(pageBuffer,675,0,4,0);
        sensor15_source = MSUtils.getBits(pageBuffer,676,0,4,0);
        sensor16_source = MSUtils.getBits(pageBuffer,677,0,4,0);
        sensor01_trans = MSUtils.getBits(pageBuffer,678,0,2,0);
        sensor02_trans = MSUtils.getBits(pageBuffer,679,0,2,0);
        sensor03_trans = MSUtils.getBits(pageBuffer,680,0,2,0);
        sensor04_trans = MSUtils.getBits(pageBuffer,681,0,2,0);
        sensor05_trans = MSUtils.getBits(pageBuffer,682,0,2,0);
        sensor06_trans = MSUtils.getBits(pageBuffer,683,0,2,0);
        sensor07_trans = MSUtils.getBits(pageBuffer,684,0,2,0);
        sensor08_trans = MSUtils.getBits(pageBuffer,685,0,2,0);
        sensor09_trans = MSUtils.getBits(pageBuffer,686,0,2,0);
        sensor10_trans = MSUtils.getBits(pageBuffer,687,0,2,0);
        sensor11_trans = MSUtils.getBits(pageBuffer,688,0,2,0);
        sensor12_trans = MSUtils.getBits(pageBuffer,689,0,2,0);
        sensor13_trans = MSUtils.getBits(pageBuffer,690,0,2,0);
        sensor14_trans = MSUtils.getBits(pageBuffer,691,0,2,0);
        sensor15_trans = MSUtils.getBits(pageBuffer,692,0,2,0);
        sensor16_trans = MSUtils.getBits(pageBuffer,693,0,2,0);
        sensor01_val0 = (double)((MSUtils.getSignedWord(pageBuffer,694) + 0.0) * 0.1);
        sensor02_val0 = (double)((MSUtils.getSignedWord(pageBuffer,696) + 0.0) * 0.1);
        sensor03_val0 = (double)((MSUtils.getSignedWord(pageBuffer,698) + 0.0) * 0.1);
        sensor04_val0 = (double)((MSUtils.getSignedWord(pageBuffer,700) + 0.0) * 0.1);
        sensor05_val0 = (double)((MSUtils.getSignedWord(pageBuffer,702) + 0.0) * 0.1);
        sensor06_val0 = (double)((MSUtils.getSignedWord(pageBuffer,704) + 0.0) * 0.1);
        sensor07_val0 = (double)((MSUtils.getSignedWord(pageBuffer,706) + 0.0) * 0.1);
        sensor08_val0 = (double)((MSUtils.getSignedWord(pageBuffer,708) + 0.0) * 0.1);
        sensor09_val0 = (double)((MSUtils.getSignedWord(pageBuffer,710) + 0.0) * 0.1);
        sensor10_val0 = (double)((MSUtils.getSignedWord(pageBuffer,712) + 0.0) * 0.1);
        sensor11_val0 = (double)((MSUtils.getSignedWord(pageBuffer,714) + 0.0) * 0.1);
        sensor12_val0 = (double)((MSUtils.getSignedWord(pageBuffer,716) + 0.0) * 0.1);
        sensor13_val0 = (double)((MSUtils.getSignedWord(pageBuffer,718) + 0.0) * 0.1);
        sensor14_val0 = (double)((MSUtils.getSignedWord(pageBuffer,720) + 0.0) * 0.1);
        sensor15_val0 = (double)((MSUtils.getSignedWord(pageBuffer,722) + 0.0) * 0.1);
        sensor16_val0 = (double)((MSUtils.getSignedWord(pageBuffer,724) + 0.0) * 0.1);
        sensor01_max = (double)((MSUtils.getSignedWord(pageBuffer,726) + 0.0) * 0.1);
        sensor02_max = (double)((MSUtils.getSignedWord(pageBuffer,728) + 0.0) * 0.1);
        sensor03_max = (double)((MSUtils.getSignedWord(pageBuffer,730) + 0.0) * 0.1);
        sensor04_max = (double)((MSUtils.getSignedWord(pageBuffer,732) + 0.0) * 0.1);
        sensor05_max = (double)((MSUtils.getSignedWord(pageBuffer,734) + 0.0) * 0.1);
        sensor06_max = (double)((MSUtils.getSignedWord(pageBuffer,736) + 0.0) * 0.1);
        sensor07_max = (double)((MSUtils.getSignedWord(pageBuffer,738) + 0.0) * 0.1);
        sensor08_max = (double)((MSUtils.getSignedWord(pageBuffer,740) + 0.0) * 0.1);
        sensor09_max = (double)((MSUtils.getSignedWord(pageBuffer,742) + 0.0) * 0.1);
        sensor10_max = (double)((MSUtils.getSignedWord(pageBuffer,744) + 0.0) * 0.1);
        sensor11_max = (double)((MSUtils.getSignedWord(pageBuffer,746) + 0.0) * 0.1);
        sensor12_max = (double)((MSUtils.getSignedWord(pageBuffer,748) + 0.0) * 0.1);
        sensor13_max = (double)((MSUtils.getSignedWord(pageBuffer,750) + 0.0) * 0.1);
        sensor14_max = (double)((MSUtils.getSignedWord(pageBuffer,752) + 0.0) * 0.1);
        sensor15_max = (double)((MSUtils.getSignedWord(pageBuffer,754) + 0.0) * 0.1);
        sensor16_max = (double)((MSUtils.getSignedWord(pageBuffer,756) + 0.0) * 0.1);
        sensor01LF = (int)((MSUtils.getByte(pageBuffer,758) + 0.0) * 1.0);
        sensor02LF = (int)((MSUtils.getByte(pageBuffer,759) + 0.0) * 1.0);
        sensor03LF = (int)((MSUtils.getByte(pageBuffer,760) + 0.0) * 1.0);
        sensor04LF = (int)((MSUtils.getByte(pageBuffer,761) + 0.0) * 1.0);
        sensor05LF = (int)((MSUtils.getByte(pageBuffer,762) + 0.0) * 1.0);
        sensor06LF = (int)((MSUtils.getByte(pageBuffer,763) + 0.0) * 1.0);
        sensor07LF = (int)((MSUtils.getByte(pageBuffer,764) + 0.0) * 1.0);
        sensor08LF = (int)((MSUtils.getByte(pageBuffer,765) + 0.0) * 1.0);
        sensor09LF = (int)((MSUtils.getByte(pageBuffer,766) + 0.0) * 1.0);
        sensor10LF = (int)((MSUtils.getByte(pageBuffer,767) + 0.0) * 1.0);
        sensor11LF = (int)((MSUtils.getByte(pageBuffer,768) + 0.0) * 1.0);
        sensor12LF = (int)((MSUtils.getByte(pageBuffer,769) + 0.0) * 1.0);
        sensor13LF = (int)((MSUtils.getByte(pageBuffer,770) + 0.0) * 1.0);
        sensor14LF = (int)((MSUtils.getByte(pageBuffer,771) + 0.0) * 1.0);
        sensor15LF = (int)((MSUtils.getByte(pageBuffer,772) + 0.0) * 1.0);
        sensor16LF = (int)((MSUtils.getByte(pageBuffer,773) + 0.0) * 1.0);
        sensor_temp = MSUtils.getBits(pageBuffer,774,0,0,0);
        if (PW_4X)
        {
        injOpen1 = (double)((MSUtils.getWord(pageBuffer,776) + 0.0) * 0.0040);
        injOpen2 = (double)((MSUtils.getWord(pageBuffer,778) + 0.0) * 0.0040);
        injOpen3 = (double)((MSUtils.getWord(pageBuffer,780) + 0.0) * 0.0040);
        injOpen4 = (double)((MSUtils.getWord(pageBuffer,782) + 0.0) * 0.0040);
        injOpen5 = (double)((MSUtils.getWord(pageBuffer,784) + 0.0) * 0.0040);
        injOpen6 = (double)((MSUtils.getWord(pageBuffer,786) + 0.0) * 0.0040);
        injOpen7 = (double)((MSUtils.getWord(pageBuffer,788) + 0.0) * 0.0040);
        injOpen8 = (double)((MSUtils.getWord(pageBuffer,790) + 0.0) * 0.0040);
        injOpena = (double)((MSUtils.getWord(pageBuffer,792) + 0.0) * 0.0040);
        injOpenb = (double)((MSUtils.getWord(pageBuffer,794) + 0.0) * 0.0040);
        injOpen11 = (double)((MSUtils.getWord(pageBuffer,796) + 0.0) * 0.0040);
        injOpen12 = (double)((MSUtils.getWord(pageBuffer,798) + 0.0) * 0.0040);
        injOpen13 = (double)((MSUtils.getWord(pageBuffer,800) + 0.0) * 0.0040);
        injOpen14 = (double)((MSUtils.getWord(pageBuffer,802) + 0.0) * 0.0040);
        injOpen15 = (double)((MSUtils.getWord(pageBuffer,804) + 0.0) * 0.0040);
        injOpen16 = (double)((MSUtils.getWord(pageBuffer,806) + 0.0) * 0.0040);
        injOpen17 = (double)((MSUtils.getWord(pageBuffer,808) + 0.0) * 0.0040);
        injOpen18 = (double)((MSUtils.getWord(pageBuffer,810) + 0.0) * 0.0040);
        }
        else
        {
        injOpen1 = (double)((MSUtils.getWord(pageBuffer,776) + 0.0) * 0.0010);
        injOpen2 = (double)((MSUtils.getWord(pageBuffer,778) + 0.0) * 0.0010);
        injOpen3 = (double)((MSUtils.getWord(pageBuffer,780) + 0.0) * 0.0010);
        injOpen4 = (double)((MSUtils.getWord(pageBuffer,782) + 0.0) * 0.0010);
        injOpen5 = (double)((MSUtils.getWord(pageBuffer,784) + 0.0) * 0.0010);
        injOpen6 = (double)((MSUtils.getWord(pageBuffer,786) + 0.0) * 0.0010);
        injOpen7 = (double)((MSUtils.getWord(pageBuffer,788) + 0.0) * 0.0010);
        injOpen8 = (double)((MSUtils.getWord(pageBuffer,790) + 0.0) * 0.0010);
        injOpena = (double)((MSUtils.getWord(pageBuffer,792) + 0.0) * 0.0010);
        injOpenb = (double)((MSUtils.getWord(pageBuffer,794) + 0.0) * 0.0010);
        injOpen11 = (double)((MSUtils.getWord(pageBuffer,796) + 0.0) * 0.0010);
        injOpen12 = (double)((MSUtils.getWord(pageBuffer,798) + 0.0) * 0.0010);
        injOpen13 = (double)((MSUtils.getWord(pageBuffer,800) + 0.0) * 0.0010);
        injOpen14 = (double)((MSUtils.getWord(pageBuffer,802) + 0.0) * 0.0010);
        injOpen15 = (double)((MSUtils.getWord(pageBuffer,804) + 0.0) * 0.0010);
        injOpen16 = (double)((MSUtils.getWord(pageBuffer,806) + 0.0) * 0.0010);
        injOpen17 = (double)((MSUtils.getWord(pageBuffer,808) + 0.0) * 0.0010);
        injOpen18 = (double)((MSUtils.getWord(pageBuffer,810) + 0.0) * 0.0010);
        }
        shift_cut_on = MSUtils.getBits(pageBuffer,812,0,0,0);
        shift_cut_auto = MSUtils.getBits(pageBuffer,812,1,1,0);
        shift_cut_gear = MSUtils.getBits(pageBuffer,812,2,2,0);
        shift_cut_in = MSUtils.getBits(pageBuffer,813,0,4,0);
        shift_cut_out = MSUtils.getBits(pageBuffer,814,0,4,0);
        shift_cut_rpm = (int)((MSUtils.getWord(pageBuffer,815) + 0.0) * 1.0);
        shift_cut_tps = (double)((MSUtils.getWord(pageBuffer,817) + 0.0) * 0.1);
        shift_cut_delay = (double)((MSUtils.getByte(pageBuffer,819) + 0.0) * 0.01);
        shift_cut_time = (double)((MSUtils.getByte(pageBuffer,820) + 0.0) * 0.01);
        shift_cut_add12 = (double)((MSUtils.getByte(pageBuffer,821) + 0.0) * 0.01);
        shift_cut_add23 = (double)((MSUtils.getByte(pageBuffer,822) + 0.0) * 0.01);
        shift_cut_add34 = (double)((MSUtils.getByte(pageBuffer,823) + 0.0) * 0.01);
        shift_cut_add45 = (double)((MSUtils.getByte(pageBuffer,824) + 0.0) * 0.01);
        shift_cut_add56 = (double)((MSUtils.getByte(pageBuffer,825) + 0.0) * 0.01);
        shift_cut_soldelay = (double)((MSUtils.getByte(pageBuffer,826) + 0.0) * 0.01);
        shift_cut_rpm12 = (int)((MSUtils.getWord(pageBuffer,827) + 0.0) * 1.0);
        shift_cut_rpm23 = (int)((MSUtils.getWord(pageBuffer,829) + 0.0) * 1.0);
        shift_cut_rpm34 = (int)((MSUtils.getWord(pageBuffer,831) + 0.0) * 1.0);
        shift_cut_rpm45 = (int)((MSUtils.getWord(pageBuffer,833) + 0.0) * 1.0);
        shift_cut_rpm56 = (int)((MSUtils.getWord(pageBuffer,835) + 0.0) * 1.0);
        shift_cut_reshift = (double)((MSUtils.getByte(pageBuffer,837) + 0.0) * 0.01);
        pwm_opt_on_a = MSUtils.getBits(pageBuffer,838,0,0,0);
        pwm_opt_var_a = MSUtils.getBits(pageBuffer,838,1,1,0);
        pwm_opt_freq_a = MSUtils.getBits(pageBuffer,838,2,4,0);
        pwm_opt_load_a = MSUtils.getBits(pageBuffer,838,5,7,0);
        pwm_opt_on_b = MSUtils.getBits(pageBuffer,839,0,0,0);
        pwm_opt_var_b = MSUtils.getBits(pageBuffer,839,1,1,0);
        pwm_opt_freq_b = MSUtils.getBits(pageBuffer,839,2,4,0);
        pwm_opt_load_b = MSUtils.getBits(pageBuffer,839,5,7,0);
        pwm_opt_on_c = MSUtils.getBits(pageBuffer,840,0,0,0);
        pwm_opt_var_c = MSUtils.getBits(pageBuffer,840,1,1,0);
        pwm_opt_freq_c = MSUtils.getBits(pageBuffer,840,2,4,0);
        pwm_opt_load_c = MSUtils.getBits(pageBuffer,840,5,7,0);
        pwm_opt_on_d = MSUtils.getBits(pageBuffer,841,0,0,0);
        pwm_opt_var_d = MSUtils.getBits(pageBuffer,841,1,1,0);
        pwm_opt_freq_d = MSUtils.getBits(pageBuffer,841,2,4,0);
        pwm_opt_load_d = MSUtils.getBits(pageBuffer,841,5,7,0);
        pwm_opt_on_e = MSUtils.getBits(pageBuffer,842,0,0,0);
        pwm_opt_var_e = MSUtils.getBits(pageBuffer,842,1,1,0);
        pwm_opt_freq_e = MSUtils.getBits(pageBuffer,842,2,4,0);
        pwm_opt_load_e = MSUtils.getBits(pageBuffer,842,5,7,0);
        pwm_opt_on_f = MSUtils.getBits(pageBuffer,843,0,0,0);
        pwm_opt_var_f = MSUtils.getBits(pageBuffer,843,1,1,0);
        pwm_opt_freq_f = MSUtils.getBits(pageBuffer,843,2,4,0);
        pwm_opt_load_f = MSUtils.getBits(pageBuffer,843,5,7,0);
        pwm_opt2_a = MSUtils.getBits(pageBuffer,844,0,5,0);
        pwm_opt2_b = MSUtils.getBits(pageBuffer,845,0,5,0);
        pwm_opt2_c = MSUtils.getBits(pageBuffer,846,0,5,0);
        pwm_opt2_d = MSUtils.getBits(pageBuffer,847,0,5,0);
        pwm_opt2_e = MSUtils.getBits(pageBuffer,848,0,5,0);
        pwm_opt2_f = MSUtils.getBits(pageBuffer,849,0,5,0);
        pwm_onabove_a = (int)((MSUtils.getByte(pageBuffer,850) + 0.0) * 1.0);
        pwm_onabove_b = (int)((MSUtils.getByte(pageBuffer,851) + 0.0) * 1.0);
        pwm_onabove_c = (int)((MSUtils.getByte(pageBuffer,852) + 0.0) * 1.0);
        pwm_onabove_d = (int)((MSUtils.getByte(pageBuffer,853) + 0.0) * 1.0);
        pwm_onabove_e = (int)((MSUtils.getByte(pageBuffer,854) + 0.0) * 1.0);
        pwm_onabove_f = (int)((MSUtils.getByte(pageBuffer,855) + 0.0) * 1.0);
        pwm_offbelow_a = (int)((MSUtils.getByte(pageBuffer,856) + 0.0) * 1.0);
        pwm_offbelow_b = (int)((MSUtils.getByte(pageBuffer,857) + 0.0) * 1.0);
        pwm_offbelow_c = (int)((MSUtils.getByte(pageBuffer,858) + 0.0) * 1.0);
        pwm_offbelow_d = (int)((MSUtils.getByte(pageBuffer,859) + 0.0) * 1.0);
        pwm_offbelow_e = (int)((MSUtils.getByte(pageBuffer,860) + 0.0) * 1.0);
        pwm_offbelow_f = (int)((MSUtils.getByte(pageBuffer,861) + 0.0) * 1.0);
        dualfuel_sw_on = MSUtils.getBits(pageBuffer,862,0,0,0);
        dualfuel_sw_fuel = MSUtils.getBits(pageBuffer,862,1,1,0);
        dualfuel_sw_spk = MSUtils.getBits(pageBuffer,862,2,2,0);
        dualfuel_sw_afr = MSUtils.getBits(pageBuffer,862,3,3,0);
        dualfuel_sw_rf = MSUtils.getBits(pageBuffer,862,4,4,0);
        dualfuel_sw_stoich = MSUtils.getBits(pageBuffer,862,5,5,0);
        dualfuel_sw_wue = MSUtils.getBits(pageBuffer,862,6,6,0);
        dualfuel_sw_ase = MSUtils.getBits(pageBuffer,862,7,7,0);
        dualfuel_sw2_prime = MSUtils.getBits(pageBuffer,863,0,0,0);
        dualfuel_sw2_crank = MSUtils.getBits(pageBuffer,863,1,1,0);
        dualfuel_sw2_injp = MSUtils.getBits(pageBuffer,863,2,2,0);
        dualfuel_sw2_smpw = MSUtils.getBits(pageBuffer,863,3,3,0);
        dualfuel_sw2_ob = MSUtils.getBits(pageBuffer,863,4,4,0);
        dualfuel_sw2_boosw = MSUtils.getBits(pageBuffer,863,5,5,0);
        opentime2_opt1_master = MSUtils.getBits(pageBuffer,864,7,7,0);
        opentime2_opt1 = MSUtils.getBits(pageBuffer,864,0,1,0);
        opentime2_opt2 = MSUtils.getBits(pageBuffer,865,0,1,0);
        opentime2_opt3 = MSUtils.getBits(pageBuffer,866,0,1,0);
        opentime2_opt4 = MSUtils.getBits(pageBuffer,867,0,1,0);
        opentime2_opt5 = MSUtils.getBits(pageBuffer,868,0,1,0);
        opentime2_opt6 = MSUtils.getBits(pageBuffer,869,0,1,0);
        opentime2_opt7 = MSUtils.getBits(pageBuffer,870,0,1,0);
        opentime2_opt8 = MSUtils.getBits(pageBuffer,871,0,1,0);
        opentime2_opta = MSUtils.getBits(pageBuffer,872,0,1,0);
        opentime2_opta_pwm = MSUtils.getBits(pageBuffer,872,4,4,0);
        opentime2_optb = MSUtils.getBits(pageBuffer,873,0,1,0);
        opentime2_optb_pwm = MSUtils.getBits(pageBuffer,873,4,4,0);
        opentime2_optb_own = MSUtils.getBits(pageBuffer,873,5,5,0);
        opentime2_opt11 = MSUtils.getBits(pageBuffer,874,0,1,0);
        opentime2_opt12 = MSUtils.getBits(pageBuffer,875,0,1,0);
        opentime2_opt13 = MSUtils.getBits(pageBuffer,876,0,1,0);
        opentime2_opt14 = MSUtils.getBits(pageBuffer,877,0,1,0);
        opentime2_opt15 = MSUtils.getBits(pageBuffer,878,0,1,0);
        opentime2_opt16 = MSUtils.getBits(pageBuffer,879,0,1,0);
        smallpw2_opt1_master = MSUtils.getBits(pageBuffer,864,7,7,0);
        smallpw2_opt1 = MSUtils.getBits(pageBuffer,882,0,1,0);
        smallpw2_opt2 = MSUtils.getBits(pageBuffer,883,0,1,0);
        smallpw2_opt3 = MSUtils.getBits(pageBuffer,884,0,1,0);
        smallpw2_opt4 = MSUtils.getBits(pageBuffer,885,0,1,0);
        smallpw2_opt5 = MSUtils.getBits(pageBuffer,886,0,1,0);
        smallpw2_opt6 = MSUtils.getBits(pageBuffer,887,0,1,0);
        smallpw2_opt7 = MSUtils.getBits(pageBuffer,888,0,1,0);
        smallpw2_opt8 = MSUtils.getBits(pageBuffer,889,0,1,0);
        smallpw2_opta = MSUtils.getBits(pageBuffer,890,0,1,0);
        smallpw2_optb = MSUtils.getBits(pageBuffer,891,0,1,0);
        smallpw2_opt11 = MSUtils.getBits(pageBuffer,892,0,1,0);
        smallpw2_opt12 = MSUtils.getBits(pageBuffer,893,0,1,0);
        smallpw2_opt13 = MSUtils.getBits(pageBuffer,894,0,1,0);
        smallpw2_opt14 = MSUtils.getBits(pageBuffer,895,0,1,0);
        smallpw2_opt15 = MSUtils.getBits(pageBuffer,896,0,1,0);
        smallpw2_opt16 = MSUtils.getBits(pageBuffer,897,0,1,0);
        dualfuel_pin = MSUtils.getBits(pageBuffer,900,0,4,0);
        dualfuel_opt_temp = MSUtils.getBits(pageBuffer,901,0,0,0);
        dualfuel_opt_press = MSUtils.getBits(pageBuffer,901,1,1,0);
        dualfuel_opt_mode = MSUtils.getBits(pageBuffer,901,2,2,0);
        dualfuel_opt_out = MSUtils.getBits(pageBuffer,901,3,3,0);
        if (PW_4X)
        {
        inj2Open1 = (double)((MSUtils.getWord(pageBuffer,902) + 0.0) * 0.0040);
        inj2Open2 = (double)((MSUtils.getWord(pageBuffer,904) + 0.0) * 0.0040);
        inj2Open3 = (double)((MSUtils.getWord(pageBuffer,906) + 0.0) * 0.0040);
        inj2Open4 = (double)((MSUtils.getWord(pageBuffer,908) + 0.0) * 0.0040);
        inj2Open5 = (double)((MSUtils.getWord(pageBuffer,910) + 0.0) * 0.0040);
        inj2Open6 = (double)((MSUtils.getWord(pageBuffer,912) + 0.0) * 0.0040);
        inj2Open7 = (double)((MSUtils.getWord(pageBuffer,914) + 0.0) * 0.0040);
        inj2Open8 = (double)((MSUtils.getWord(pageBuffer,916) + 0.0) * 0.0040);
        inj2Opena = (double)((MSUtils.getWord(pageBuffer,918) + 0.0) * 0.0040);
        inj2Openb = (double)((MSUtils.getWord(pageBuffer,920) + 0.0) * 0.0040);
        inj2Open11 = (double)((MSUtils.getWord(pageBuffer,922) + 0.0) * 0.0040);
        inj2Open12 = (double)((MSUtils.getWord(pageBuffer,924) + 0.0) * 0.0040);
        inj2Open13 = (double)((MSUtils.getWord(pageBuffer,926) + 0.0) * 0.0040);
        inj2Open14 = (double)((MSUtils.getWord(pageBuffer,928) + 0.0) * 0.0040);
        inj2Open15 = (double)((MSUtils.getWord(pageBuffer,930) + 0.0) * 0.0040);
        inj2Open16 = (double)((MSUtils.getWord(pageBuffer,932) + 0.0) * 0.0040);
        inj2Open17 = (double)((MSUtils.getWord(pageBuffer,934) + 0.0) * 0.0040);
        inj2Open18 = (double)((MSUtils.getWord(pageBuffer,936) + 0.0) * 0.0040);
        }
        else
        {
        inj2Open1 = (double)((MSUtils.getWord(pageBuffer,902) + 0.0) * 0.0010);
        inj2Open2 = (double)((MSUtils.getWord(pageBuffer,904) + 0.0) * 0.0010);
        inj2Open3 = (double)((MSUtils.getWord(pageBuffer,906) + 0.0) * 0.0010);
        inj2Open4 = (double)((MSUtils.getWord(pageBuffer,908) + 0.0) * 0.0010);
        inj2Open5 = (double)((MSUtils.getWord(pageBuffer,910) + 0.0) * 0.0010);
        inj2Open6 = (double)((MSUtils.getWord(pageBuffer,912) + 0.0) * 0.0010);
        inj2Open7 = (double)((MSUtils.getWord(pageBuffer,914) + 0.0) * 0.0010);
        inj2Open8 = (double)((MSUtils.getWord(pageBuffer,916) + 0.0) * 0.0010);
        inj2Opena = (double)((MSUtils.getWord(pageBuffer,918) + 0.0) * 0.0010);
        inj2Openb = (double)((MSUtils.getWord(pageBuffer,920) + 0.0) * 0.0010);
        inj2Open11 = (double)((MSUtils.getWord(pageBuffer,922) + 0.0) * 0.0010);
        inj2Open12 = (double)((MSUtils.getWord(pageBuffer,924) + 0.0) * 0.0010);
        inj2Open13 = (double)((MSUtils.getWord(pageBuffer,926) + 0.0) * 0.0010);
        inj2Open14 = (double)((MSUtils.getWord(pageBuffer,928) + 0.0) * 0.0010);
        inj2Open15 = (double)((MSUtils.getWord(pageBuffer,930) + 0.0) * 0.0010);
        inj2Open16 = (double)((MSUtils.getWord(pageBuffer,932) + 0.0) * 0.0010);
        inj2Open17 = (double)((MSUtils.getWord(pageBuffer,934) + 0.0) * 0.0010);
        inj2Open18 = (double)((MSUtils.getWord(pageBuffer,936) + 0.0) * 0.0010);
        }
        inj2PwmT = (double)((MSUtils.getByte(pageBuffer,938) + 0.128) * 0.128);
        inj2PwmPd = (int)((MSUtils.getByte(pageBuffer,939) + 0.0) * 1.0);
        inj2PwmP = (int)((MSUtils.getByte(pageBuffer,940) + 0.0) * 1.0);
        inj2PwmT2 = (double)((MSUtils.getByte(pageBuffer,941) + 0.128) * 0.128);
        inj2PwmPd2 = (int)((MSUtils.getByte(pageBuffer,942) + 0.0) * 1.0);
        inj2PwmP2 = (int)((MSUtils.getByte(pageBuffer,943) + 0.0) * 1.0);
        dualfuel_temp_sens = MSUtils.getBits(pageBuffer,944,0,3,0);
        dualfuel_press_sens = MSUtils.getBits(pageBuffer,945,0,3,0);
        dualfuel_pri = MSUtils.getBits(pageBuffer,954,0,1,0);
        dualfuel_sec = MSUtils.getBits(pageBuffer,955,0,1,0);
        ITB_load_mappoint = (double)((MSUtils.getSignedWord(pageBuffer,956) + 0.0) * 0.1);
        ITB_load_idletpsthresh = (double)((MSUtils.getSignedWord(pageBuffer,958) + 0.0) * 0.1);
        pageBuffer = loadPage(3,0,1024,null,new byte[]{114,0,8,0,0,4,0});
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
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        user_value1 = (int)((MSUtils.getWord(pageBuffer,958) + 0.0) * 1.0);
        user_value2 = (int)((MSUtils.getWord(pageBuffer,960) + 0.0) * 1.0);
        user_conf0 = MSUtils.getBits(pageBuffer,962,0,0,0);
        user_conf1 = MSUtils.getBits(pageBuffer,962,1,2,0);
        pageBuffer = loadPage(4,0,1024,null,new byte[]{114,0,9,0,0,4,0});
        if (LAMBDA)
        {
        }
        else
        {
        }
        pageBuffer = loadPage(5,0,1024,null,new byte[]{114,0,10,0,0,4,0});
        if (MPH)
        {
        }
        else
        {
        }
        if (LAMBDA)
        {
        }
        else
        {
        }
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        if (PW_4X)
        {
        }
        else
        {
        }
        pageBuffer = loadPage(6,0,1024,null,new byte[]{114,0,11,0,0,4,0});
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        if (PW_4X)
        {
        }
        else
        {
        }
        pageBuffer = loadPage(10,0,1024,null,new byte[]{114,0,19,0,0,4,0});
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        if (MPH)
        {
        }
        else
        {
        }
        pageBuffer = loadPage(11,0,1024,null,new byte[]{114,0,21,0,0,4,0});
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        if (CELSIUS)
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        else
        {
        if (EXPANDED_CLT_TEMP)
        {
        }
        else
        {
        }
        }
        pageBuffer = loadPage(14,0,1024,null,new byte[]{114,0,24,0,0,4,0});
        als_opt_fc = MSUtils.getBits(pageBuffer,336,0,0,0);
        als_opt_sc = MSUtils.getBits(pageBuffer,336,1,1,0);
        als_opt_idle = MSUtils.getBits(pageBuffer,336,2,2,0);
        als_opt_fuel = MSUtils.getBits(pageBuffer,336,3,3,0);
        als_opt_pwmout = MSUtils.getBits(pageBuffer,336,4,4,0);
        als_maxtime = (double)((MSUtils.getByte(pageBuffer,337) + 0.0) * 0.1);
        als_mintps = (double)((MSUtils.getSignedWord(pageBuffer,338) + 0.0) * 0.1);
        als_maxtps = (double)((MSUtils.getSignedWord(pageBuffer,340) + 0.0) * 0.1);
        als_iac_duty = (double)((MSUtils.getByte(pageBuffer,342) + 0.0) * 0.39063);
        als_iac_steps = (int)((MSUtils.getByte(pageBuffer,342) + 0.0) * 1.0);
        als_in_pin = MSUtils.getBits(pageBuffer,343,0,4,0);
        als_out_pin = MSUtils.getBits(pageBuffer,344,0,5,0);
        als_pwm_duty = (int)((MSUtils.getByte(pageBuffer,345) + 0.0) * 1.0);
        als_pausetime = (double)((MSUtils.getByte(pageBuffer,346) + 0.0) * 0.1);
        if (CELSIUS)
        {
        als_minclt = (double)((MSUtils.getSignedWord(pageBuffer,348) + -320.0) * 0.05555);
        als_maxclt = (double)((MSUtils.getSignedWord(pageBuffer,350) + -320.0) * 0.05555);
        }
        else
        {
        als_minclt = (double)((MSUtils.getSignedWord(pageBuffer,348) + 0.0) * 0.1);
        als_maxclt = (double)((MSUtils.getSignedWord(pageBuffer,350) + 0.0) * 0.1);
        }
        als_minrpm = (int)((MSUtils.getWord(pageBuffer,592) + 0.0) * 1.0);
        als_maxrpm = (int)((MSUtils.getWord(pageBuffer,594) + 0.0) * 1.0);
        if (CELSIUS)
        {
        als_maxmat = (double)((MSUtils.getSignedWord(pageBuffer,596) + -320.0) * 0.05555);
        }
        else
        {
        als_maxmat = (double)((MSUtils.getSignedWord(pageBuffer,596) + 0.0) * 0.1);
        }
        vvt_onoff_ang = (double)((MSUtils.getSignedWord(pageBuffer,598) + 0.0) * 0.1);
        vvt_opt1_on = MSUtils.getBits(pageBuffer,600,0,1,0);
        vvt_opt1_dir_intake = MSUtils.getBits(pageBuffer,600,2,2,0);
        vvt_opt1_int = MSUtils.getBits(pageBuffer,600,3,3,0);
        vvt_opt1_tst = MSUtils.getBits(pageBuffer,600,4,6,0);
        vvt_opt1_dir_exhaust = MSUtils.getBits(pageBuffer,600,7,7,0);
        vvt_hold_duty = (double)((MSUtils.getByte(pageBuffer,601) + 0.0) * 0.394);
        vvt_out1 = MSUtils.getBits(pageBuffer,602,0,3,0);
        vvt_out2 = MSUtils.getBits(pageBuffer,603,0,3,0);
        vvt_out3 = MSUtils.getBits(pageBuffer,604,0,3,0);
        vvt_out4 = MSUtils.getBits(pageBuffer,605,0,3,0);
        vvt_ctl_ms = (int)((MSUtils.getWord(pageBuffer,606) + 0.0) * 1.0);
        vvt_ctl_Kp = (int)((MSUtils.getByte(pageBuffer,608) + 0.0) * 1.0);
        vvt_ctl_Ki = (int)((MSUtils.getByte(pageBuffer,609) + 0.0) * 1.0);
        vvt_ctl_Kd = (int)((MSUtils.getByte(pageBuffer,610) + 0.0) * 1.0);
        vvt_test_duty = (double)((MSUtils.getByte(pageBuffer,611) + 0.0) * 0.39063);
        vvt_min_ang1 = (double)((MSUtils.getSignedWord(pageBuffer,612) + 0.0) * 0.1);
        vvt_min_ang2 = (double)((MSUtils.getSignedWord(pageBuffer,614) + 0.0) * 0.1);
        vvt_min_ang3 = (double)((MSUtils.getSignedWord(pageBuffer,616) + 0.0) * 0.1);
        vvt_min_ang4 = (double)((MSUtils.getSignedWord(pageBuffer,618) + 0.0) * 0.1);
        vvt_max_ang1 = (double)((MSUtils.getSignedWord(pageBuffer,620) + 0.0) * 0.1);
        vvt_max_ang2 = (double)((MSUtils.getSignedWord(pageBuffer,622) + 0.0) * 0.1);
        vvt_max_ang3 = (double)((MSUtils.getSignedWord(pageBuffer,624) + 0.0) * 0.1);
        vvt_max_ang4 = (double)((MSUtils.getSignedWord(pageBuffer,626) + 0.0) * 0.1);
        vvt_opt2_pid = MSUtils.getBits(pageBuffer,628,0,0,0);
        vvt_opt2_use_hold_intake = MSUtils.getBits(pageBuffer,628,1,1,0);
        vvt_opt2_use_hold_exhaust = MSUtils.getBits(pageBuffer,628,2,2,0);
        vvt_opt2_cam2pol = MSUtils.getBits(pageBuffer,628,4,4,0);
        vvt_opt2_cam3pol = MSUtils.getBits(pageBuffer,628,5,5,0);
        vvt_opt2_cam4pol = MSUtils.getBits(pageBuffer,628,6,6,0);
        vvt_opt2_cam1pol = MSUtils.getBits(pageBuffer,628,7,7,0);
        vvt_opt3_cam2 = MSUtils.getBits(pageBuffer,629,0,1,0);
        vvt_opt3_cam3 = MSUtils.getBits(pageBuffer,629,2,3,0);
        vvt_opt3_cam4 = MSUtils.getBits(pageBuffer,629,4,5,0);
        vvt_opt3_cam1 = MSUtils.getBits(pageBuffer,629,6,6,0);
        vvt_tth1 = (int)((MSUtils.getByte(pageBuffer,918) + 0.0) * 1.0);
        vvt_tth2 = (int)((MSUtils.getByte(pageBuffer,919) + 0.0) * 1.0);
        vvt_tth3 = (int)((MSUtils.getByte(pageBuffer,920) + 0.0) * 1.0);
        vvt_tth4 = (int)((MSUtils.getByte(pageBuffer,921) + 0.0) * 1.0);
        vvt_coldpos1 = (double)((MSUtils.getSignedWord(pageBuffer,922) + 0.0) * 0.1);
        vvt_coldpos2 = (double)((MSUtils.getSignedWord(pageBuffer,924) + 0.0) * 0.1);
        tclu_opt_vss = MSUtils.getBits(pageBuffer,926,0,1,0);
        tclu_opt_gear = MSUtils.getBits(pageBuffer,926,2,2,0);
        tclu_brakepin = MSUtils.getBits(pageBuffer,927,0,4,0);
        tclu_enablepin = MSUtils.getBits(pageBuffer,928,0,4,0);
        tclu_outpin = MSUtils.getBits(pageBuffer,929,0,5,0);
        tclu_gearmin = (int)((MSUtils.getByte(pageBuffer,930) + 0.0) * 1.0);
        tclu_delay = (double)((MSUtils.getByte(pageBuffer,931) + 0.0) * 0.1);
        if (MPH)
        {
        tclu_vssmin = (double)((MSUtils.getWord(pageBuffer,932) + 0.0) * 0.22369);
        }
        else
        {
        tclu_vssmin = (double)((MSUtils.getWord(pageBuffer,932) + 0.0) * 0.36);
        }
        tclu_tpsmin = (double)((MSUtils.getSignedWord(pageBuffer,934) + 0.0) * 0.1);
        tclu_tpsmax = (double)((MSUtils.getSignedWord(pageBuffer,936) + 0.0) * 0.1);
        tclu_mapmin = (double)((MSUtils.getWord(pageBuffer,938) + 0.0) * 0.1);
        tclu_mapmax = (double)((MSUtils.getWord(pageBuffer,940) + 0.0) * 0.1);
        vvt_opt4_decode = MSUtils.getBits(pageBuffer,942,0,1,0);
        vvt_opt5_add1 = MSUtils.getBits(pageBuffer,943,0,1,0);
        vvt_opt5_add2 = MSUtils.getBits(pageBuffer,943,2,2,0);
        vvt_opt5_err = MSUtils.getBits(pageBuffer,943,3,3,0);
        vvt_opt5_vvt1 = MSUtils.getBits(pageBuffer,943,4,4,0);
        vvt_opt5_vvt2 = MSUtils.getBits(pageBuffer,943,5,5,0);
        vvt_opt5_vvt3 = MSUtils.getBits(pageBuffer,943,6,6,0);
        vvt_opt5_vvt4 = MSUtils.getBits(pageBuffer,943,7,7,0);
        vvt_hold_duty_exh = (double)((MSUtils.getByte(pageBuffer,944) + 0.0) * 0.394);
        vvt_ctl_Kp_exh = (int)((MSUtils.getByte(pageBuffer,945) + 0.0) * 1.0);
        vvt_ctl_Ki_exh = (int)((MSUtils.getByte(pageBuffer,946) + 0.0) * 1.0);
        vvt_ctl_Kd_exh = (int)((MSUtils.getByte(pageBuffer,947) + 0.0) * 1.0);
        vvt_cam1tth1 = (int)((MSUtils.getByte(pageBuffer,948) + 0.0) * 1.0);
        vvt_cam1tth2 = (int)((MSUtils.getByte(pageBuffer,949) + 0.0) * 1.0);
        vvt_cam2tth1 = (int)((MSUtils.getByte(pageBuffer,950) + 0.0) * 1.0);
        vvt_cam2tth2 = (int)((MSUtils.getByte(pageBuffer,951) + 0.0) * 1.0);
        vvt_cam3tth1 = (int)((MSUtils.getByte(pageBuffer,952) + 0.0) * 1.0);
        vvt_cam3tth2 = (int)((MSUtils.getByte(pageBuffer,953) + 0.0) * 1.0);
        vvt_cam4tth1 = (int)((MSUtils.getByte(pageBuffer,954) + 0.0) * 1.0);
        vvt_cam4tth2 = (int)((MSUtils.getByte(pageBuffer,955) + 0.0) * 1.0);
        vvt_opt6_freq = MSUtils.getBits(pageBuffer,956,0,3,0);
    }

}

