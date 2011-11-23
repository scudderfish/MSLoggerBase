package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;

/*
 Fingerprint : d44e05718501fda3fe65959d26f80249
 */
public class ZZMS2Extra311mb_v12 extends Megasquirt
{
	public ZZMS2Extra311mb_v12(Context c)
	{
		super(c);
		NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
		EGTFULL = isSet("EGTFULL");
		CELSIUS = isSet("CELSIUS");
		MICROSQUIRT_FULL = isSet("MICROSQUIRT_FULL");
		CAN_COMMANDS = isSet("CAN_COMMANDS");
		MICROSQUIRT_MODULE = isSet("MICROSQUIRT_MODULE");
		LAMBDA = isSet("LAMBDA");
		INI_VERSION_2 = isSet("INI_VERSION_2");
		USE_CRC_DATA_CHECK = isSet("USE_CRC_DATA_CHECK");
		EXPANDED_CLT_TEMP = isSet("EXPANDED_CLT_TEMP");
	}

	byte[]				queryCommand	= new byte[] { 'Q' };
	String				signature		= "MS2Extra 311 mb_v12\0";
	byte[]				ochGetCommand	= new byte[] { 'A' };
	int					ochBlockSize	= 169;
	private Set<String>	sigs			= new HashSet<String>(Arrays.asList(new String[] { signature }));
	// Flags
	boolean				NARROW_BAND_EGO;
	boolean				EGTFULL;
	boolean				CELSIUS;
	boolean				MICROSQUIRT_FULL;
	boolean				CAN_COMMANDS;
	boolean				MICROSQUIRT_MODULE;
	boolean				LAMBDA;
	boolean				INI_VERSION_2;
	boolean				USE_CRC_DATA_CHECK;
	boolean				EXPANDED_CLT_TEMP;
	// Runtime vars
	double				tpsDOT;
	int					portStatus;
	double				ignload2;
	int					ready;
	int					wallfuel1;
	int					synccnt;
	int					wallfuel2;
	double				knock;
	double				afr2;
	double				afr1;
	double				EAEFuelCorr2;
	double				EAEFuelCorr1;
	int					rpm;
	int					boostduty;
	double				coolant;
	double				afrtgt2;
	int					tpsADC;
	int					engine;
	int					tpsaccaen;
	double				afrtgt1;
	double				looptime;
	int					tpsaccden;
	int					crank;
	int					status1;
	int					inj2;
	int					inj1;
	int					port0;
	int					port4;
	int					port3;
	int					port2;
	int					port1;
	int					gpioadc0;
	double				maf;
	int					port6;
	int					port5;
	double				eaeload1;
	double				map;
	double				egoV;
	double				vetrim3curr;
	int					warmup;
	int					firing1;
	double				mat;
	int					firing2;
	double				vetrim1curr;
	double				timing_err;
	int					gpioadc1;
	int					gpioadc2;
	int					gpioadc3;
	int					gpioadc4;
	int					gpioadc5;
	int					gpioadc6;
	int					gpioadc7;
	double				egoV2;
	int					user0;
	int					seconds;
	int					startw;
	int					warmupEnrich;
	double				dwell;
	double				gammaEnrich;
	double				egoCorrection1;
	double				egoCorrection2;
	int					mapaccden;
	double				vetrim2curr;
	double				fuelload;
	int					mapDOT;
	double				barometer;
	double				vetrim4curr;
	int					wbo2_en2;
	double				coldAdvDeg;
	int					gpioport2;
	int					gpioport1;
	int					gpioport0;
	double				rpmdot;
	int					wbo2_en1;
	int					status5;
	int					status4;
	int					status3;
	int					status2;
	double				baroCorrection;
	int					tpsfuelcut;
	int					mafmap;
	double				pulseWidth4;
	double				pulseWidth3;
	double				pulseWidth2;
	double				pulseWidth1;
	double				veCurr1;
	double				veCurr2;
	double				batteryVoltage;
	double				fuelload2;
	int					gpiopwmin0;
	int					syncreason;
	double				ignload;
	int					gpiopwmin1;
	int					gpiopwmin2;
	int					gpiopwmin3;
	double				inj_adv2;
	double				inj_adv1;
	int					iacstep;
	double				accelEnrich;
	double				knockRetard;
	int					adc7;
	int					adc6;
	double				airCorrection;
	int					squirt;
	int					deltaT;
	int					sched2;
	double				idleDC;
	int					sched1;
	int					fuelCorrection;
	double				afrload1;
	double				advance;
	double				tps;
	int					mapaccaen;

	// eval vars
	double				accDecEnrich;
	double				egt6temp;
	int					nSquirts2;
	int					nSquirts1;
	double				dutyCycle2;
	double				throttle;
	double				dutyCycle1;
	int					deadValue;
	double				lambda2;
	double				lambda1;
	int					secl;
	double				pulseWidth;
	double				egt7temp;
	double				cycleTime1;
	double				cycleTime2;
	int					altDiv1;
	double				time;
	double				egoCorrection;
	double				veCurr;
	int					altDiv2;
	double				egoVoltage;
	double				rpm100;

	// Constants
	double				N2O2PWLo;
	int					boost_ctl_settings_cl;
	int					afrload;
	double				baro0;
	double				pwmidle_rpmdot_disablepid;
	int					spk_conf2_oddodd;
	int					pwmidle_min_steps;
	int					tpsMin;
	double				injPwmT2;
	int					IgnAlgorithm2;
	int					N2O2Rpm;
	int					egoType;
	int					eaeload;
	int					alternate;
	int					poll_offsetADC;
	int					feature4_0ftrig;
	int					boost_ctl_settings_remote;
	int					cltmult;
	double				IC2ISR_tmask;
	int					knkDirection;
	double				knk_tadv;
	double				egoTemp;
	int					RevLimTPSbypassRPM;
	int					board_type;
	int					idleadvance_delay;
	int					userlevelbits;
	double				idleadvance_tps;
	double				knk_dtble_adv;
	int					secondtrigopts1;
	int					secondtrigopts3;
	int					secondtrigopts2;
	int					f5_0_tsf;
	double				N2OPWHi;
	int					flexFuel;
	int					tsf_remote_port;
	int					f5_0_tss;
	int					knk_option;
	double				pwmidle_rpmdot_threshold;
	int					spk_config_trig2l;
	int					Dtpred_Gain;
	int					egoCount;
	int					tpsLF;
	int					mapLF;
	double				testint;
	int					poll_tableports;
	int					ae_lorpm;
	int					boost_ctl_ms;
	int					staged_sec_size;
	int					pwmidle_open_steps;
	int					enable_pollADC;
	int					OverBoostOption;
	int					dwellmode;
	int					IgnAlgorithm;
	int					flats_arm;
	double				N2O2PWHi;
	int					can_poll_id;
	int					pwmidle_rpm_hyst;
	int					dualTable;
	int					f5_0_tss_opt;
	int					userlevel;
	int					egoport;
	int					staged_second_param;
	int					spk_mode3;
	int					boost_ctl_Kp;
	int					usevetrim;
	int					spk_mode0;
	double				hw_latency;
	double				OverBoostHyst;
	int					injctl;
	int					port_generic;
	double				tpsThresh;
	double				idleadvance_clt;
	double				N2OPWLo;
	int					AfrAlgorithm;
	int					spk_config_trig2;
	int					feature4_0VEtblsize;
	int					can_poll;
	int					spk_conf2_gm;
	int					boost_ctl_Ki;
	int					mycan_id;
	int					flexport;
	int					boost_ctl_Kd;
	int					boost_ctl_pins;
	int					pwmidle_pid_stallrpm;
	int					N2O2RpmMax;
	int					knkpull;
	int					asTolerance;
	int					rpmLF;
	int					seq_inj;
	double				pwmidle_pid_duty_tolerance;
	double				AFRTarget;
	int					knkport_remote;
	int					pwmidle_pid_steps_tolerance;
	int					poll_tableADC;
	int					enable_pollPWM;
	int					spk_config_resetcam;
	int					pwmidle_engage_rpm_adder;
	int					feature4_0vanos;
	double				injstagedadv1;
	int					testinjcnt;
	double				injstagedadv2;
	double				injstagedadv3;
	double				crank_dwell;
	int					spk_config_spka;
	int					fc_rpm_lower;
	int					loadMult;
	int					pwmidle_freq;
	int					ports_dir;
	double				pwmidle_Kd;
	int					matmult;
	int					N2Oopt_3;
	int					AMCOption;
	int					N2Oopt_2;
	int					tpsMax;
	int					adcLF;
	int					flexFuelRemote;
	int					extrainj;
	int					testop_fp;
	int					testop_pwm;
	int					flats_sft;
	int					algorithm;
	int					pwmidle_port;
	double				mapmax;
	int					staged_param_1;
	double				pwmidle_Ki;
	int					staged_param_2;
	int					RevLimcutx;
	double				knk_maxmap;
	int					RevLimcuty;
	double				pwmidle_Kp;
	int					RotarySplitModeFD;
	int					injusetable;
	double				batt0;
	double				knk_trtd;
	double				IAC_tinitial_step;
	double				N2OTps;
	double				N2Odel_launch;
	int					tss_remote_port;
	int					N20remote_input;
	double				battFac;
	int					engineType;
	int					nInjectors;
	int					boost_ctl_settings_invert;
	int					N2ORpm;
	int					pwmidle_min_rpm;
	double				max_coil_dur;
	double				crank_timing;
	double				dwellduty;
	int					port3_type;
	int					mapsample_window;
	double				injOpen;
	int					launch_opt_pins;
	int					injPwmPd2;
	double				N2OClt;
	int					mapThresh;
	int					tsf_remote;
	double				clt0;
	double				staged_secondary_enrichment;
	int					boost_ctl_remote;
	int					fuelCorr0;
	double				N2OAngle;
	int					injtimingmode;
	int					fuelCorr1;
	int					altcrank;
	int					EAElagthresh;
	int					baud;
	int					trig_init;
	int					injPwmPd;
	int					OvrRunC;
	int					testop_coil;
	int					injPwmP;
	int					RevLimRpm2;
	int					test_addinj;
	int					injPwmP2;
	int					iachometest;
	double				egoLimit;
	double				IACtstep;
	int					hybrid_rpm;
	double				injPwmT;
	int					port1_type;
	int					taeColdM;
	double				triggerOffset;
	double				flats_deg;
	int					egoRPM;
	int					tempUnits;
	int					staged_first_param;
	double				dwellAcc;
	int					use_prediction;
	int					tacho_opt80;
	double				IdleHyst;
	double				taeColdA;
	int					tsf_rpm;
	double				launch_sft_deg;
	double				Miss_ang;
	int					testinjPwmP;
	double				pwmidle_min_duty;
	double				baro_lower;
	int					bcormult;
	int					pwmidleset_inv;
	int					crankingRPM;
	int					oldegoLimit;
	int					staged_transition_events;
	double				MAPOXLimit;
	double				tsf_tps;
	int					EAElagsource;
	int					AMCve_rpm;
	int					EAElagRPMmax;
	double				TpsBypassCLTRevlim;
	double				idleadvance_load;
	double				max_spk_dur;
	int					fc_ego_delay;
	int					N2ORpmMax;
	int					No_Miss_Teeth;
	int					remotePWMfreq;
	double				testpw;
	int					EGO_wait_secs;
	double				dwelltime;
	double				OddFireang;
	int					staged_primary_delay;
	int					timing_flags;
	double				pwmidle_dp_adder;
	int					N20remote_outport;
	int					can_poll_slave;
	int					AMCramve_dt;
	int					spk_conf2_ngc;
	int					spk_conf2_tfi;
	double				fastIdleT;
	int					NoiseFilterOpts;
	double				injadv1;
	int					remotePWMprescale;
	int					testmode;
	double				ego0;
	int					staged_hyst_2;
	double				injadv3;
	double				injadv2;
	int					staged_hyst_1;
	double				mapsample_angle;
	int					mapsample_opt1;
	int					IACcrankxt;
	double				fc_kpa;
	int					boost_ctl_openduty;
	int					pwmidle_targ_ramptime;
	int					egoAlgorithm;
	int					ICISR_pmask;
	int					fuelFreq0;
	double				floodClear;
	int					fuelFreq1;
	int					testop_inj;
	int					hybrid_hyst;
	double				fuelSpkDel0;
	int					feature4_0mindwl;
	double				fuelSpkDel1;
	int					pwmidle_close_delay;
	int					f5_0_tsf_opt;
	double				reqFuel;
	int					pwmidle_ms;
	int					tpsProportion;
	int					RotarySplitModeNeg;
	int					nCylinders;
	double				knk_maxrtd;
	int					CID;
	int					spk_conf2_dli;
	double				battFac4;
	int					launch_opt_on;
	double				battFac3;
	double				battmax;
	double				battFac2;
	double				testinjPwmT;
	int					tss_rpm;
	double				N2Odel_flat;
	int					RevLimOption;
	int					AMCstep;
	int					crankTolerance;
	int					user_value1;
	int					user_value2;
	double				N2O2delay;
	double				fc_delay;
	int					RotarySplitModeOn;
	int					RotarySplitModeRX8;
	int					EAEOption;
	int					poll_offsetports;
	int					flexportRemote;
	int					ego_calib_selection;
	int					knk_lorpm;
	double				fixed_timing;
	int					pwmidle_shift_lower_rpm;
	int					staged_pri_size;
	int					N2Oopt_01;
	int					N20remote_output;
	double				stoich;
	int					pwmidlecranktaper;
	int					injdualvalue;
	int					spkout_hi_lo;
	int					user_conf1;
	int					user_conf0;
	double				RevLimMaxRtd;
	int					spk_conf2_cam;
	int					spk_config_camcrank;
	double				launch_tps;
	int					pwmidle_shift_open_time;
	int					launch_sft_lim;
	int					N2Oopt_pins2;
	int					inj_time_mask;
	double				egoDelta;
	int					testmodelock;
	double				OverBoostKpa;
	int					egoKI;
	int					divider;
	int					egoKD;
	int					knk_remote;
	int					rtbaroport;
	double				N2Oholdon;
	int					knk_ndet;
	int					knkport;
	double				pwmidle_tps_threshold;
	int					bcor0;
	double				map0;
	int					egomult;
	int					AMCdve;
	int					egoKP;
	int					staged_transition_on;
	int					egoKdelay2;
	int					NoiseFilterOpts2;
	int					egoKdelay1;
	int					NoiseFilterOpts3;
	int					PredOpt;
	double				fc_clt;
	int					egoLF;
	int					tdePct;
	int					staged_second_logic;
	double				aeEndPW;
	int					IACcoldpos;
	double				injcrankadv2;
	double				injcrankadv1;
	double				taeTime;
	double				tss_kpa;
	int					IC2ISR_pmask;
	int					knk_hirpm;
	double				AMCve_map;
	int					launch_hrd_lim;
	int					idleadvance_rpm;
	double				pwmidle_closed_duty;
	double				testdwell;
	int					pwmidle_pid_wait_timer;
	double				baro_default;
	int					port2_type;
	double				TPSOXLimit;
	int					f5_0_ego_acc;
	int					iactestlock;
	double				baromax;
	int					IACStart;
	int					no_skip_pulses;
	int					boost_ctl_settings_freq;
	int					tss_remote;
	int					iacpostest;
	int					injType;
	double				ICISR_tmask;
	int					NoiseFilterOpts1;
	int					IdleCtl;
	int					pulseTolerance;
	int					iactest;
	int					pwmidle_max_rpm;
	int					idleadvance_on;
	int					ego2port;
	int					testinjPwmPd;
	double				pwmidle_decelload_threshold;
	int					AMCupdate_thresh;
	int					RevLimNormal1;
	int					feature4_0igntrig;
	int					tacho_opt40;
	int					RevLimNormal2;
	int					AMCT_thresh;
	int					poll_tablePWM;
	double				IACcoldtmp;
	int					IACminstep;
	double				fc_tps;
	double				tsf_kpa;
	int					loadStoich;
	double				pwmidle_open_duty;
	int					boost_ctl_closeduty;
	int					launchlimopt;
	int					MAFOption;
	int					fc_rpm;
	int					feature3_1;
	int					twoStroke;
	int					overboostcuty;
	int					pwmidle_dp_adder_steps;
	double				N2O2Angle;
	int					IACcoldxt;
	int					overboostcutx;
	double				MAPOXMin;
	double				mat0;
	int					N2Oopt_pins;
	int					tacho_opt3f;
	int					launchcutx;
	double				trigret_ang;
	int					feature3_3;
	int					launchcuty;
	int					flats_hrd;
	int					feature4_0maxdwl;
	int					boost_ctl_settings_on;
	int					airden_scaling;
	int					ae_hirpm;
	double				TC5_required_width;
	double				baro_upper;
	int					enable_pollports;
	int					loadCombine;
	int					ICIgnCapture;
	int					secondtrigopts;
	double				tss_tps;
	int					algorithm2;
	double				injOpen3;
	double				injOpen4;
	double				injOpen2;
	int					baroCorr;
	double				aeTaperTime;
	int					pwmidle_closed_steps;
	int					triggerTeeth;
	int					RevLimCLTbased;
	int					N20remote_inport;
	double				knk_step2;
	int					poll_offsetPWM;
	double				knk_step1;
	int					pwmidleset_hz;

	private String[]	defaultGauges	= { "tachometer", "throttleGauge", "pulseWidth1Gauge", "pulseWidth2Gauge", "advdegGauge",
			"fuelloadGauge", "egoVGauge", "lambda1Gauge", "afr1Gauge", "injadv2Gauge" };

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
		tpsaccaen = MSUtils.getBits(ochBuffer, 11, 4, 4, 0);
		tpsaccden = MSUtils.getBits(ochBuffer, 11, 5, 5, 0);
		mapaccaen = MSUtils.getBits(ochBuffer, 11, 6, 6, 0);
		mapaccden = MSUtils.getBits(ochBuffer, 11, 7, 7, 0);
		afrtgt1 = (double) ((MSUtils.getByte(ochBuffer, 12) + 0.0) * 0.1);
		afrtgt2 = (double) ((MSUtils.getByte(ochBuffer, 13) + 0.0) * 0.1);
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
		egoCorrection1 = (double) ((MSUtils.getSignedWord(ochBuffer, 34) + 0.0) * 0.010);
		egoCorrection = ((egoCorrection1 + egoCorrection2) / 2);
		egoCorrection2 = (double) ((MSUtils.getSignedWord(ochBuffer, 36) + 0.0) * 0.010);
		airCorrection = (double) ((MSUtils.getSignedWord(ochBuffer, 38) + 0.0) * 0.010);
		warmupEnrich = (int) ((MSUtils.getSignedWord(ochBuffer, 40) + 0.0) * 1.000);
		accelEnrich = (double) ((MSUtils.getSignedWord(ochBuffer, 42) + 0.0) * 0.100);
		tpsfuelcut = (int) ((MSUtils.getSignedWord(ochBuffer, 44) + 0.0) * 1.000);
		baroCorrection = (double) ((MSUtils.getSignedWord(ochBuffer, 46) + 0.0) * 0.010);
		gammaEnrich = (double) ((MSUtils.getSignedWord(ochBuffer, 48) + 0.0) * 0.010);
		veCurr1 = (double) ((MSUtils.getSignedWord(ochBuffer, 50) + 0.0) * 0.0100);
		veCurr2 = (double) ((MSUtils.getSignedWord(ochBuffer, 52) + 0.0) * 0.0100);
		veCurr = (veCurr1);
		iacstep = (int) ((MSUtils.getSignedWord(ochBuffer, 54) + 0.0) * 1.000);
		idleDC = (double) ((MSUtils.getSignedWord(ochBuffer, 54) + 0.0) * 0.39063);
		coldAdvDeg = (double) ((MSUtils.getSignedWord(ochBuffer, 56) + 0.0) * 0.100);
		tpsDOT = (double) ((MSUtils.getSignedWord(ochBuffer, 58) + 0.0) * 0.100);
		mapDOT = (int) ((MSUtils.getSignedWord(ochBuffer, 60) + 0.0) * 1.000);
		dwell = (double) ((MSUtils.getWord(ochBuffer, 62) + 0.0) * 0.0666);
		mafmap = (int) ((MSUtils.getSignedWord(ochBuffer, 64) + 0.0) * 1.000);
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
		EAEFuelCorr1 = (double) ((MSUtils.getWord(ochBuffer, 72) + 0.0) * 0.1);
		egoV = (double) ((MSUtils.getSignedWord(ochBuffer, 74) + 0.0) * 0.01);
		egoV2 = (double) ((MSUtils.getSignedWord(ochBuffer, 76) + 0.0) * 0.01);
		status1 = (int) ((MSUtils.getByte(ochBuffer, 78) + 0.0) * 1.0);
		status2 = (int) ((MSUtils.getByte(ochBuffer, 79) + 0.0) * 1.0);
		status3 = (int) ((MSUtils.getByte(ochBuffer, 80) + 0.0) * 1.0);
		status4 = (int) ((MSUtils.getByte(ochBuffer, 81) + 0.0) * 1.0);
		looptime = (double) ((MSUtils.getWord(ochBuffer, 82) + 0.0) * 0.6667);
		status5 = (int) ((MSUtils.getWord(ochBuffer, 84) + 0) * 1);
		tpsADC = (int) ((MSUtils.getWord(ochBuffer, 86) + 0) * 1);
		fuelload2 = (double) ((MSUtils.getSignedWord(ochBuffer, 88) + 0.0) * 0.100);
		ignload = (double) ((MSUtils.getSignedWord(ochBuffer, 90) + 0.0) * 0.100);
		ignload2 = (double) ((MSUtils.getSignedWord(ochBuffer, 92) + 0.0) * 0.100);
		synccnt = (int) ((MSUtils.getByte(ochBuffer, 94) + 0) * 1);
		timing_err = (double) ((MSUtils.getSignedByte(ochBuffer, 95) + 0) * 0.1);
		deltaT = (int) ((MSUtils.getSignedLong(ochBuffer, 96) + 0.0) * 1.000);
		wallfuel1 = (int) ((MSUtils.getLong(ochBuffer, 100) + 0.0) * 1.000);
		gpioadc0 = (int) ((MSUtils.getWord(ochBuffer, 104) + 0.0) * 1.000);
		gpioadc1 = (int) ((MSUtils.getWord(ochBuffer, 106) + 0.0) * 1.000);
		gpioadc2 = (int) ((MSUtils.getWord(ochBuffer, 108) + 0.0) * 1.000);
		gpioadc3 = (int) ((MSUtils.getWord(ochBuffer, 110) + 0.0) * 1.000);
		gpioadc4 = (int) ((MSUtils.getWord(ochBuffer, 112) + 0.0) * 1.000);
		gpioadc5 = (int) ((MSUtils.getWord(ochBuffer, 114) + 0.0) * 1.000);
		gpioadc6 = (int) ((MSUtils.getWord(ochBuffer, 116) + 0.0) * 1.000);
		gpioadc7 = (int) ((MSUtils.getWord(ochBuffer, 118) + 0.0) * 1.000);
		gpiopwmin0 = (int) ((MSUtils.getWord(ochBuffer, 120) + 0.0) * 1.000);
		gpiopwmin1 = (int) ((MSUtils.getWord(ochBuffer, 122) + 0.0) * 1.000);
		gpiopwmin2 = (int) ((MSUtils.getWord(ochBuffer, 124) + 0.0) * 1.000);
		gpiopwmin3 = (int) ((MSUtils.getWord(ochBuffer, 126) + 0.0) * 1.000);
		adc6 = (int) ((MSUtils.getWord(ochBuffer, 128) + 0.0) * 1);
		adc7 = (int) ((MSUtils.getWord(ochBuffer, 130) + 0.0) * 1);
		wallfuel2 = (int) ((MSUtils.getLong(ochBuffer, 132) + 0.0) * 1.000);
		EAEFuelCorr2 = (double) ((MSUtils.getWord(ochBuffer, 136) + 0.0) * 0.1);
		boostduty = (int) ((MSUtils.getByte(ochBuffer, 138) + 0.0) * 1.0);
		syncreason = (int) ((MSUtils.getByte(ochBuffer, 139) + 0.0) * 1.0);
		user0 = (int) ((MSUtils.getWord(ochBuffer, 140) + 0.0) * 1.0);
		inj_adv1 = (double) ((MSUtils.getSignedWord(ochBuffer, 142) + 0.0) * 0.100);
		inj_adv2 = (double) ((MSUtils.getSignedWord(ochBuffer, 144) + 0.0) * 0.100);
		pulseWidth3 = (double) ((MSUtils.getWord(ochBuffer, 146) + 0.0) * 0.000666);
		pulseWidth4 = (double) ((MSUtils.getWord(ochBuffer, 148) + 0.0) * 0.000666);
		vetrim1curr = (double) ((MSUtils.getSignedWord(ochBuffer, 150) + 10240.0) * 0.00976562500);
		vetrim2curr = (double) ((MSUtils.getSignedWord(ochBuffer, 152) + 10240.0) * 0.00976562500);
		vetrim3curr = (double) ((MSUtils.getSignedWord(ochBuffer, 154) + 10240.0) * 0.00976562500);
		vetrim4curr = (double) ((MSUtils.getSignedWord(ochBuffer, 156) + 10240.0) * 0.00976562500);
		maf = (double) ((MSUtils.getWord(ochBuffer, 158) + 0.0) * 0.010);
		eaeload1 = (double) ((MSUtils.getSignedWord(ochBuffer, 160) + 0.0) * 0.1);
		afrload1 = (double) ((MSUtils.getSignedWord(ochBuffer, 162) + 0.0) * 0.1);
		rpmdot = (double) ((MSUtils.getSignedWord(ochBuffer, 164) + 0.0) * 10);
		gpioport0 = (int) ((MSUtils.getByte(ochBuffer, 166) + 0.0) * 1.000);
		gpioport1 = (int) ((MSUtils.getByte(ochBuffer, 167) + 0.0) * 1.000);
		gpioport2 = (int) ((MSUtils.getByte(ochBuffer, 168) + 0.0) * 1.000);
		accDecEnrich = (((accelEnrich + (tpsaccden) != 0) ? tpsfuelcut : 100));
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
			egoVoltage = (egoV);
		}
		else if (LAMBDA)
		{
			egoVoltage = (lambda1);
		}
		else
		{
			egoVoltage = (afr1);
		}
		if (EGTFULL)
		{
			if (CELSIUS)
			{
				egt6temp = (adc6 * 1.222);
				egt7temp = (adc7 * 1.222);
			}
			else
			{
				egt6temp = ((adc6 * 2.200) + 32);
				egt7temp = ((adc7 * 2.200) + 32);
			}
		}
		else
		{
			if (CELSIUS)
			{
				egt6temp = (adc6 * 0.956);
				egt7temp = (adc7 * 0.956);
			}
			else
			{
				egt6temp = ((adc6 * 1.721) + 32);
				egt7temp = ((adc7 * 1.721) + 32);
			}
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
		b.append("Batt V").append("\t");
		b.append("deltaT").append("\t");
		b.append("WallFuel1").append("\t");
		b.append("WallFuel2").append("\t");
		b.append("EAE1 %").append("\t");
		b.append("EAE2 %").append("\t");
		b.append("Load").append("\t");
		b.append("Secondary Load").append("\t");
		b.append("Ign load").append("\t");
		b.append("Secondary Ign Load").append("\t");
		b.append("EAE Load").append("\t");
		b.append("AFR Load").append("\t");
		b.append("EGT 6 temp").append("\t");
		b.append("EGT 7 temp").append("\t");
		b.append("gpioadc0").append("\t");
		b.append("gpioadc1").append("\t");
		b.append("gpioadc2").append("\t");
		b.append("gpioadc3").append("\t");
		b.append("gpioadc4").append("\t");
		b.append("gpioadc5").append("\t");
		b.append("gpioadc6").append("\t");
		b.append("gpioadc7").append("\t");
		b.append("status1").append("\t");
		b.append("status2").append("\t");
		b.append("status3").append("\t");
		b.append("status4").append("\t");
		b.append("status5").append("\t");
		b.append("timing err%").append("\t");
		b.append("AFR Target 1").append("\t");
		b.append("Boost Duty").append("\t");
		b.append("PWM Idle Duty").append("\t");
		b.append("Lost sync count").append("\t");
		b.append("Lost sync reason").append("\t");
		b.append("InjTiming1").append("\t");
		b.append("InjTiming2").append("\t");
		b.append("PW3").append("\t");
		b.append("PW4").append("\t");
		b.append("VE Trim 1").append("\t");
		b.append("VE Trim 2").append("\t");
		b.append("VE Trim 3").append("\t");
		b.append("VE Trim 4").append("\t");
		b.append("RPMdot").append("\t");
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
		b.append(round(airCorrection)).append("\t");
		b.append(warmupEnrich).append("\t");
		b.append(round(baroCorrection)).append("\t");
		b.append(round(gammaEnrich)).append("\t");
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
		b.append(round(batteryVoltage)).append("\t");
		b.append(deltaT).append("\t");
		b.append(wallfuel1).append("\t");
		b.append(wallfuel2).append("\t");
		b.append(round(EAEFuelCorr1)).append("\t");
		b.append(round(EAEFuelCorr2)).append("\t");
		b.append(round(fuelload)).append("\t");
		b.append(round(fuelload2)).append("\t");
		b.append(round(ignload)).append("\t");
		b.append(round(ignload2)).append("\t");
		b.append(round(eaeload1)).append("\t");
		b.append(round(afrload1)).append("\t");
		b.append(egt6temp).append("\t");
		b.append(egt7temp).append("\t");
		b.append(gpioadc0).append("\t");
		b.append(gpioadc1).append("\t");
		b.append(gpioadc2).append("\t");
		b.append(gpioadc3).append("\t");
		b.append(gpioadc4).append("\t");
		b.append(gpioadc5).append("\t");
		b.append(gpioadc6).append("\t");
		b.append(gpioadc7).append("\t");
		b.append(status1).append("\t");
		b.append(status2).append("\t");
		b.append(status3).append("\t");
		b.append(status4).append("\t");
		b.append(status5).append("\t");
		b.append(round(timing_err)).append("\t");
		b.append(round(afrtgt1)).append("\t");
		b.append(boostduty).append("\t");
		b.append(round(idleDC)).append("\t");
		b.append(synccnt).append("\t");
		b.append(syncreason).append("\t");
		b.append(round(inj_adv1)).append("\t");
		b.append(round(inj_adv2)).append("\t");
		b.append(round(pulseWidth3)).append("\t");
		b.append(round(pulseWidth4)).append("\t");
		b.append(round(vetrim1curr)).append("\t");
		b.append(round(vetrim2curr)).append("\t");
		b.append(round(vetrim3curr)).append("\t");
		b.append(round(vetrim4curr)).append("\t");
		b.append(round(rpmdot)).append("\t");
		b.append(MSUtils.getLocationLogRow());
		return b.toString();
	}

	@Override
	public void initGauges()
	{
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("IACgauge", "iacstep", iacstep, "IAC position", "steps", 0, 255, -1, -1,
				999, 999, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dwellGauge", "dwell", dwell, "Dwell", "mSec", 0, 10, 0.5, 1.0, 6.0, 8.0,
				1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("PWMIdlegauge", "idleDC", idleDC, "Idle PWM%", "%", 0, 100, -1, -1, 999,
				90, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth1Gauge", "pulseWidth1", pulseWidth1, "Pulse Width 1", "mSec",
				0, 25.5, 1.0, 1.2, 20, 25, 3, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth2Gauge", "pulseWidth2", pulseWidth2, "Pulse Width 2", "mSec",
				0, 25.5, 1.0, 1.2, 20, 25, 3, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth3Gauge", "pulseWidth3", pulseWidth3, "Pulse Width 3", "mSec",
				0, 25.5, 1.0, 1.2, 20, 25, 3, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pulseWidth4Gauge", "pulseWidth4", pulseWidth4, "Pulse Width 4", "mSec",
				0, 25.5, 1.0, 1.2, 20, 25, 3, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("advdegGauge", "advance", advance, "Ignition Advance", "degrees", 0, 50,
				-1, -1, 999, 999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle1Gauge", "dutyCycle1", dutyCycle1, "Duty Cycle 1", "%", 0, 100,
				-1, -1, 85, 90, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("dutyCycle2Gauge", "dutyCycle2", dutyCycle2, "Duty Cycle 2", "%", 0, 100,
				-1, -1, 85, 90, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("boostdutyGauge", "boostduty", boostduty, "Boost Duty", "%", 0, 100, -1,
				-1, 100, 100, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("injadv1Gauge", "inj_adv1", inj_adv1, "Injection Timing 1", "degrees",
				-360, 360, -999, -999, 999, 999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("injadv2Gauge", "inj_adv2", inj_adv2, "Injection Timing 2", "degrees",
				-360, 360, -999, -999, 999, 999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("accelEnrichGauge", "accDecEnrich", accDecEnrich, "Accel Enrich", "%", 50,
				150, -1, -1, 999, 999, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clockGauge", "seconds", seconds, "Clock", "Seconds", 0, 65535, 10, 10,
				65535, 65535, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaEnrichGauge", "gammaEnrich", gammaEnrich, "Gamma Enrichment", "%",
				50, 150, -1, -1, 151, 151, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gammaairGauge", "airCorrection", airCorrection, "Gair/aircor", "%", 50,
				150, -1, -1, 151, 151, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("WFGauge1", "wallfuel1", wallfuel1, "Fuel on the walls 1", "", 0,
				40000000, 0, 0, 40000000, 40000000, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("WFGauge2", "wallfuel2", wallfuel2, "Fuel on the walls 2", "", 0,
				40000000, 0, 0, 40000000, 40000000, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("EAEGauge1", "EAEFuelCorr1", EAEFuelCorr1, "EAE Fuel Correction 1", "%",
				0, 200, 40, 70, 130, 160, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("EAEGauge2", "EAEFuelCorr2", EAEFuelCorr2, "EAE Fuel Correction 2", "%",
				0, 200, 40, 70, 130, 160, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vetrimGauge1", "vetrim1curr", vetrim1curr, "VE Trim 1", "%", 87, 113,
				-999, -999, 999, 999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vetrimGauge2", "vetrim2curr", vetrim2curr, "VE Trim 2", "%", 87, 113,
				-999, -999, 999, 999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vetrimGauge3", "vetrim3curr", vetrim3curr, "VE Trim 3", "%", 87, 113,
				-999, -999, 999, 999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("vetrimGauge4", "vetrim4curr", vetrim4curr, "VE Trim 4", "%", 87, 113,
				-999, -999, 999, 999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lostsyncGauge", "synccnt", synccnt, "Lost sync counter", "", 0, 255, 255,
				255, 255, 255, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("syncreasonGauge", "syncreason", syncreason, "Lost sync reason", "", 0,
				255, 255, 255, 255, 255, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("user0Gauge", "user0", user0, "User defined", "", 0, 65535, 65535, 65535,
				65535, 65535, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge1", "veCurr1", veCurr1, "VE Current1", "%", 0, 120, -1, -1, 999,
				999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("veGauge2", "veCurr2", veCurr2, "VE2 Current", "%", 0, 120, -1, -1, 999,
				999, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("warmupEnrichGauge", "warmupEnrich", warmupEnrich, "Warmup Enrichment",
				"%", 100, 150, -1, -1, 101, 105, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("rpmdot", "rpmdot", rpmdot, "rpmdot", "rpm/sec", -15000, 15000, 65535,
				65535, 65535, 65535, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tpsdot", "tpsDOT", tpsDOT, "tpsdot", "%/sec", -15000, 15000, 65535,
				65535, 65535, 65535, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapdot", "mapDOT", mapDOT, "mapdot", "kPa/sec", -15000, 15000, 65535,
				65535, 65535, 65535, 0, 0, 0));
		if (CELSIUS)
		{
			if (EXPANDED_CLT_TEMP)
			{
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge", "coolant", coolant, "Coolant Temp", "°C", -40, 230,
						-100, -100, 170, 200, 0, 0, 0));
			}
			else
			{
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge", "coolant", coolant, "Coolant Temp", "°C", -40, 150,
						-100, -100, 95, 105, 0, 0, 0));
			}
			GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge", "mat", mat, "Manifold Air Temp", "°C", -40, 110, -15, 0,
					95, 100, 0, 0, 0));
		}
		else
		{
			if (EXPANDED_CLT_TEMP)
			{
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge", "coolant", coolant, "Coolant Temp", "°F", -40, 450,
						-100, -100, 350, 400, 0, 0, 0));
			}
			else
			{
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("cltGauge", "coolant", coolant, "Coolant Temp", "°F", -40, 300,
						-100, -100, 200, 220, 0, 0, 0));
			}
			GaugeRegister.INSTANCE.addGauge(new GaugeDetails("matGauge", "mat", mat, "Manifold Air Temp", "°F", -40, 215, 0, 30,
					200, 210, 0, 0, 0));
		}
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("voltMeter", "batteryVoltage", batteryVoltage, "Battery Voltage", "volts",
				7, 21, 8, 9, 15, 16, 2, 2, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tachometer", "rpm", rpm, "Engine Speed", "RPM", 0, 8000, 300, 600, 3000,
				5000, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("throttleGauge", "throttle", throttle, "Throttle Position", "%", 0, 100,
				-1, 1, 90, 100, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mapGauge", "map", map, "Engine MAP", "kPa", 0, 255, 0, 20, 200, 245, 1,
				0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("barometerGauge", "barometer", barometer, "Barometer", "kPa", 60, 120, 0,
				20, 200, 245, 1, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelloadGauge", "fuelload", fuelload, "Fuel Load", "%", 0, 255, 0, 20,
				200, 245, 1, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("fuelload2Gauge", "fuelload2", fuelload2, "Secondary Fuel Load", "%", 0,
				255, 0, 20, 200, 245, 1, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ignloadGauge", "ignload", ignload, "Ign Load", "%", 0, 255, 0, 20, 200,
				245, 1, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("ignload2Gauge", "ignload2", ignload2, "Secondary Ign Load", "%", 0, 255,
				0, 20, 200, 245, 1, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("eaeloadGauge", "eaeload1", eaeload1, "EAE load", "%", 0, 255, 0, 20, 200,
				245, 1, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afrloadGauge", "afrload1", afrload1, "AFR load", "%", 0, 255, 0, 20, 200,
				245, 1, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mafGauge", "maf", maf, "Mass Air Flow", "g/sec", 0, 650, 0, 200, 480,
				550, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("mafMapGauge", "mafmap", mafmap, "MAFMAP", "kPa", 0, 400, -1, -1, 999,
				999, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gpioadc0Gauge", "gpioadc0", gpioadc0, "GPIO ADC 0", "", 0, 1023, 1023,
				1023, 1023, 1023, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gpioadc1Gauge", "gpioadc1", gpioadc1, "GPIO ADC 1", "", 0, 1023, 1023,
				1023, 1023, 1023, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gpioadc2Gauge", "gpioadc2", gpioadc2, "GPIO ADC 2", "", 0, 1023, 1023,
				1023, 1023, 1023, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gpioadc3Gauge", "gpioadc3", gpioadc3, "GPIO ADC 3", "", 0, 1023, 1023,
				1023, 1023, 1023, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gpioadc4Gauge", "gpioadc4", gpioadc4, "GPIO ADC 4", "", 0, 1023, 1023,
				1023, 1023, 1023, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gpioadc5Gauge", "gpioadc5", gpioadc5, "GPIO ADC 5", "", 0, 1023, 1023,
				1023, 1023, 1023, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gpioadc6Gauge", "gpioadc6", gpioadc6, "GPIO ADC 6", "", 0, 1023, 1023,
				1023, 1023, 1023, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("gpioadc7Gauge", "gpioadc7", gpioadc7, "GPIO ADC 7", "", 0, 1023, 1023,
				1023, 1023, 1023, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("adc6Gauge", "adc6", adc6, "ADC 6", "", 0, 1023, 1023, 1023, 1023, 1023,
				0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("adc7Gauge", "adc7", adc7, "ADC 7", "", 0, 1023, 1023, 1023, 1023, 1023,
				0, 0, 0));
		if (CELSIUS)
		{
			if (EGTFULL)
			{
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge6", "egt6temp", egt6temp, "EGT", "C", 0, 1250, 0, 0,
						1250, 1250, 1, 1, 0));
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge7", "egt7temp", egt7temp, "EGT", "C", 0, 1250, 0, 0,
						1250, 1250, 1, 1, 0));
			}
			else
			{
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge6", "egt6temp", egt6temp, "EGT", "C", 0, 1000, 0, 0,
						1000, 1000, 1, 1, 0));
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge7", "egt7temp", egt7temp, "EGT", "C", 0, 1000, 0, 0,
						1000, 1000, 1, 1, 0));
			}
		}
		else
		{
			if (EGTFULL)
			{
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge6", "egt6temp", egt6temp, "EGT", "F", 0, 2280, 0, 0,
						2280, 2280, 1, 1, 0));
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge7", "egt7temp", egt7temp, "EGT", "F", 0, 2280, 0, 0,
						2280, 2280, 1, 1, 0));
			}
			else
			{
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge6", "egt6temp", egt6temp, "EGT", "F", 0, 1830, 0, 0,
						1830, 1830, 1, 1, 0));
				GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egtGauge7", "egt7temp", egt7temp, "EGT", "F", 0, 1830, 0, 0,
						1830, 1830, 1, 1, 0));
			}
		}
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr1Gauge", "afr1", afr1, "Air:Fuel Ratio", "", 10, 19.4, 12, 13, 15, 16,
				2, 2, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("afr2Gauge", "afr2", afr2, "Air:Fuel Ratio2", "", 10, 19.4, 12, 13, 15,
				16, 2, 2, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge", "egoCorrection", egoCorrection, "EGO Correction", "%", 50,
				150, 90, 99, 101, 110, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge1", "egoCorrection1", egoCorrection1, "EGO Correction 1",
				"%", 50, 150, 90, 99, 101, 110, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoCorrGauge2", "egoCorrection2", egoCorrection2, "EGO Correction 2",
				"%", 50, 150, 90, 99, 101, 110, 1, 1, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoGauge", "egoVoltage", egoVoltage, "Exhaust Gas Oxygen", "volts", 0,
				1.0, 0.2, 0.3, 0.7, 0.8, 2, 2, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoVGauge", "egoV", egoV, "Exhaust Gas Oxygen", "volts", 0, 5, 5, 5, 5,
				5, 5, 2, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("egoV2Gauge", "egoV2", egoV2, "Exhaust Gas Oxygen2", "volts", 0, 5, 5, 5,
				5, 5, 5, 2, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda1Gauge", "lambda1", lambda1, "Lambda", "", 0.5, 1.5, 0.5, 0.7, 2,
				1.1, 2, 2, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("lambda2Gauge", "lambda2", lambda2, "Lambda", "", 0.5, 1.5, 0.5, 0.7, 2,
				1.1, 2, 2, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status1Gauge", "status1", status1, "Status 1", "", 0, 255, 255, 255, 255,
				255, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status2Gauge", "status2", status2, "Status 2", "", 0, 255, 255, 255, 255,
				255, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status3Gauge", "status3", status3, "Status 3", "", 0, 255, 255, 255, 255,
				255, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status4Gauge", "status4", status4, "Status 4", "", 0, 255, 255, 255, 255,
				255, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("status5Gauge", "status5", status5, "Status 5", "", 0, 65535, 65535,
				65535, 65535, 65535, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("looptimeGauge", "looptime", looptime, "Mainloop time", "us", 0, 65535,
				255, 255, 255, 255, 0, 0, 0));
		GaugeRegister.INSTANCE.addGauge(new GaugeDetails("deadGauge", "deadValue", deadValue, "---", "", 0, 1, -1, -1, 2, 2, 0, 0,
				0));

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
		if (CAN_COMMANDS)
		{
		}
		else
		{
		}
		if (USE_CRC_DATA_CHECK)
		{
		}
		pageBuffer = loadPage(1, 0, 1024, null, new byte[] { 114, 0, 4, 0, 0, 4, 0 });
		nCylinders = MSUtils.getBits(pageBuffer, 0, 0, 4, 0);
		no_skip_pulses = (int) ((MSUtils.getByte(pageBuffer, 1) + 0.0) * 1.0);
		ICIgnCapture = MSUtils.getBits(pageBuffer, 2, 0, 0, 0);
		engineType = MSUtils.getBits(pageBuffer, 2, 3, 3, 0);
		spkout_hi_lo = MSUtils.getBits(pageBuffer, 2, 4, 4, 0);
		injctl = MSUtils.getBits(pageBuffer, 2, 5, 5, 0);
		max_coil_dur = (double) ((MSUtils.getByte(pageBuffer, 4) + 0.0) * 0.0666);
		max_spk_dur = (double) ((MSUtils.getByte(pageBuffer, 5) + 0.0) * 0.0666);
		dwellAcc = (double) ((MSUtils.getByte(pageBuffer, 6) + 0.0) * 0.0666);
		PredOpt = MSUtils.getBits(pageBuffer, 19, 0, 1, 0);
		crankingRPM = (int) ((MSUtils.getSignedWord(pageBuffer, 20) + 0.0) * 1.0);
		triggerOffset = (double) ((MSUtils.getSignedWord(pageBuffer, 42) + 0.0) * 0.1);
		TpsBypassCLTRevlim = (double) ((MSUtils.getSignedWord(pageBuffer, 44) + 0.0) * 0.1);
		RevLimRpm2 = (int) ((MSUtils.getSignedWord(pageBuffer, 46) + 0.0) * 1.0);
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
		Dtpred_Gain = (int) ((MSUtils.getSignedWord(pageBuffer, 542) + 0.0) * 1.0);
		crankTolerance = (int) ((MSUtils.getByte(pageBuffer, 544) + 0.0) * 1.0);
		asTolerance = (int) ((MSUtils.getByte(pageBuffer, 545) + 0.0) * 1.0);
		pulseTolerance = (int) ((MSUtils.getByte(pageBuffer, 546) + 0.0) * 1.0);
		if (MICROSQUIRT_FULL)
		{
			IdleCtl = MSUtils.getBits(pageBuffer, 547, 0, 3, 0);
		}
		else
		{
			IdleCtl = MSUtils.getBits(pageBuffer, 547, 0, 3, 0);
		}
		IACtstep = (double) ((MSUtils.getByte(pageBuffer, 548) + 0.0) * 0.128);
		IAC_tinitial_step = (double) ((MSUtils.getByte(pageBuffer, 549) + 0.0) * 0.128);
		IACminstep = (int) ((MSUtils.getByte(pageBuffer, 550) + 0.0) * 1.0);
		dwellduty = (double) ((MSUtils.getByte(pageBuffer, 551) + 0.0) * 0.39);
		IACStart = (int) ((MSUtils.getSignedWord(pageBuffer, 552) + 0.0) * 1.0);
		if (CELSIUS)
		{
			if (EXPANDED_CLT_TEMP)
			{
				IdleHyst = (double) ((MSUtils.getSignedWord(pageBuffer, 554) + 0.0) * 0.05555);
			}
			else
			{
				IdleHyst = (double) ((MSUtils.getSignedWord(pageBuffer, 554) + 0.0) * 0.05555);
			}
		}
		else
		{
			if (EXPANDED_CLT_TEMP)
			{
				IdleHyst = (double) ((MSUtils.getSignedWord(pageBuffer, 554) + 0.0) * 0.1);
			}
			else
			{
				IdleHyst = (double) ((MSUtils.getSignedWord(pageBuffer, 554) + 0.0) * 0.1);
			}
		}
		IACcrankxt = (int) ((MSUtils.getSignedWord(pageBuffer, 558) + 0.0) * 1.0);
		if (CELSIUS)
		{
			if (EXPANDED_CLT_TEMP)
			{
				IACcoldtmp = (double) ((MSUtils.getSignedWord(pageBuffer, 560) + -320.0) * 0.05555);
			}
			else
			{
				IACcoldtmp = (double) ((MSUtils.getSignedWord(pageBuffer, 560) + -320.0) * 0.05555);
			}
		}
		else
		{
			if (EXPANDED_CLT_TEMP)
			{
				IACcoldtmp = (double) ((MSUtils.getSignedWord(pageBuffer, 560) + 0.0) * 0.1);
			}
			else
			{
				IACcoldtmp = (double) ((MSUtils.getSignedWord(pageBuffer, 560) + 0.0) * 0.1);
			}
		}
		IACcoldpos = (int) ((MSUtils.getSignedWord(pageBuffer, 562) + 0.0) * 1.0);
		IACcoldxt = (int) ((MSUtils.getSignedWord(pageBuffer, 564) + 0.0) * 1.0);
		injOpen = (double) ((MSUtils.getWord(pageBuffer, 566) + 0.0) * 0.0010);
		battFac = (double) ((MSUtils.getWord(pageBuffer, 568) + 0.0) * 1.66667E-4);
		OverBoostOption = MSUtils.getBits(pageBuffer, 570, 0, 1, 0);
		OverBoostKpa = (double) ((MSUtils.getSignedWord(pageBuffer, 571) + 0.0) * 0.1);
		OverBoostHyst = (double) ((MSUtils.getSignedWord(pageBuffer, 573) + 0.0) * 0.1);
		overboostcutx = (int) ((MSUtils.getByte(pageBuffer, 575) + 0.0) * 1.0);
		overboostcuty = (int) ((MSUtils.getByte(pageBuffer, 576) + 0.0) * 1.0);
		secondtrigopts = MSUtils.getBits(pageBuffer, 577, 0, 0, 0);
		secondtrigopts1 = MSUtils.getBits(pageBuffer, 577, 1, 1, 0);
		secondtrigopts2 = MSUtils.getBits(pageBuffer, 577, 2, 2, 0);
		secondtrigopts3 = MSUtils.getBits(pageBuffer, 577, 3, 3, 0);
		tpsThresh = (double) ((MSUtils.getSignedWord(pageBuffer, 578) + 0.0) * 0.1);
		mapThresh = (int) ((MSUtils.getSignedWord(pageBuffer, 580) + 0.0) * 1.0);
		taeColdA = (double) ((MSUtils.getByte(pageBuffer, 582) + 0.0) * 0.1);
		taeColdM = (int) ((MSUtils.getByte(pageBuffer, 583) + 0.0) * 1.0);
		mapsample_angle = (double) ((MSUtils.getSignedWord(pageBuffer, 584) + 0.0) * 0.1);
		taeTime = (double) ((MSUtils.getByte(pageBuffer, 586) + 0.0) * 0.1);
		tdePct = (int) ((MSUtils.getByte(pageBuffer, 587) + 0.0) * 1.0);
		floodClear = (double) ((MSUtils.getSignedWord(pageBuffer, 588) + 0.0) * 0.1);
		TPSOXLimit = (double) ((MSUtils.getSignedWord(pageBuffer, 590) + 0.0) * 0.1);
		tpsProportion = (int) ((MSUtils.getByte(pageBuffer, 592) + 0.0) * 1.0);
		baroCorr = MSUtils.getBits(pageBuffer, 593, 0, 1, 0);
		egoType = MSUtils.getBits(pageBuffer, 594, 0, 2, 0);
		egoCount = (int) ((MSUtils.getByte(pageBuffer, 595) + 0.0) * 1.0);
		egoDelta = (double) ((MSUtils.getByte(pageBuffer, 596) + 0.0) * 0.1);
		oldegoLimit = (int) ((MSUtils.getByte(pageBuffer, 597) + 0.0) * 1.0);
		if (LAMBDA)
		{
			AFRTarget = (double) ((MSUtils.getByte(pageBuffer, 598) + 0.0) * 0.006803);
		}
		else
		{
			AFRTarget = (double) ((MSUtils.getByte(pageBuffer, 598) + 0.0) * 0.1);
		}
		tempUnits = MSUtils.getBits(pageBuffer, 599, 0, 0, 0);
		MAFOption = MSUtils.getBits(pageBuffer, 600, 4, 5, 0);
		mapsample_opt1 = MSUtils.getBits(pageBuffer, 601, 0, 1, 0);
		if (CELSIUS)
		{
			if (EXPANDED_CLT_TEMP)
			{
				fastIdleT = (double) ((MSUtils.getSignedWord(pageBuffer, 602) + -320.0) * 0.05555);
				egoTemp = (double) ((MSUtils.getSignedWord(pageBuffer, 604) + -320.0) * 0.05555);
			}
			else
			{
				fastIdleT = (double) ((MSUtils.getSignedWord(pageBuffer, 602) + -320.0) * 0.05555);
				egoTemp = (double) ((MSUtils.getSignedWord(pageBuffer, 604) + -320.0) * 0.05555);
			}
		}
		else
		{
			if (EXPANDED_CLT_TEMP)
			{
				fastIdleT = (double) ((MSUtils.getSignedWord(pageBuffer, 602) + 0.0) * 0.1);
				egoTemp = (double) ((MSUtils.getSignedWord(pageBuffer, 604) + 0.0) * 0.1);
			}
			else
			{
				fastIdleT = (double) ((MSUtils.getSignedWord(pageBuffer, 602) + 0.0) * 0.1);
				egoTemp = (double) ((MSUtils.getSignedWord(pageBuffer, 604) + 0.0) * 0.1);
			}
		}
		egoRPM = (int) ((MSUtils.getSignedWord(pageBuffer, 606) + 0.0) * 1.0);
		reqFuel = (double) ((MSUtils.getWord(pageBuffer, 608) + 0.0) * 0.0010);
		divider = (int) ((MSUtils.getByte(pageBuffer, 610) + 0.0) * 1.0);
		alternate = MSUtils.getBits(pageBuffer, 611, 0, 0, 0);
		altcrank = MSUtils.getBits(pageBuffer, 611, 1, 1, 0);
		injPwmT = (double) ((MSUtils.getByte(pageBuffer, 613) + 0.128) * 0.128);
		injPwmPd = (int) ((MSUtils.getByte(pageBuffer, 614) + 0.0) * 1.0);
		injPwmP = (int) ((MSUtils.getByte(pageBuffer, 615) + 0.0) * 1.0);
		twoStroke = MSUtils.getBits(pageBuffer, 617, 0, 0, 0);
		injType = MSUtils.getBits(pageBuffer, 618, 0, 0, 0);
		nInjectors = MSUtils.getBits(pageBuffer, 619, 0, 3, 0);
		OddFireang = (double) ((MSUtils.getWord(pageBuffer, 620) + 0.0) * 0.1);
		rpmLF = (int) ((MSUtils.getByte(pageBuffer, 622) + 0.0) * 1.0);
		mapLF = (int) ((MSUtils.getByte(pageBuffer, 623) + 0.0) * 1.0);
		tpsLF = (int) ((MSUtils.getByte(pageBuffer, 624) + 0.0) * 1.0);
		egoLF = (int) ((MSUtils.getByte(pageBuffer, 625) + 0.0) * 1.0);
		adcLF = (int) ((MSUtils.getByte(pageBuffer, 626) + 0.0) * 1.0);
		AMCOption = MSUtils.getBits(pageBuffer, 628, 0, 1, 0);
		dualTable = MSUtils.getBits(pageBuffer, 629, 0, 0, 0);
		algorithm = MSUtils.getBits(pageBuffer, 630, 0, 2, 0);
		algorithm2 = MSUtils.getBits(pageBuffer, 630, 4, 6, 0);
		IgnAlgorithm = MSUtils.getBits(pageBuffer, 631, 0, 2, 0);
		IgnAlgorithm2 = MSUtils.getBits(pageBuffer, 631, 4, 6, 0);
		AfrAlgorithm = (int) ((MSUtils.getByte(pageBuffer, 632) + 0.0) * 1.0);
		dwelltime = (double) ((MSUtils.getByte(pageBuffer, 633) + 0.0) * 0.0666);
		trigret_ang = (double) ((MSUtils.getWord(pageBuffer, 634) + 0.0) * 0.1);
		RevLimOption = MSUtils.getBits(pageBuffer, 636, 0, 2, 0);
		RevLimCLTbased = MSUtils.getBits(pageBuffer, 636, 3, 3, 0);
		RevLimMaxRtd = (double) ((MSUtils.getByte(pageBuffer, 637) + 0.0) * 0.1);
		injPwmT2 = (double) ((MSUtils.getByte(pageBuffer, 638) + 0.128) * 0.128);
		injPwmPd2 = (int) ((MSUtils.getByte(pageBuffer, 639) + 0.0) * 1.0);
		injPwmP2 = (int) ((MSUtils.getByte(pageBuffer, 640) + 0.0) * 1.0);
		injOpen2 = (double) ((MSUtils.getWord(pageBuffer, 641) + 0.0) * 0.0010);
		battFac2 = (double) ((MSUtils.getWord(pageBuffer, 643) + 0.0) * 1.66667E-4);
		baro_upper = (double) ((MSUtils.getSignedWord(pageBuffer, 646) + 0.0) * 0.1);
		baro_lower = (double) ((MSUtils.getSignedWord(pageBuffer, 648) + 0.0) * 0.1);
		baro_default = (double) ((MSUtils.getSignedWord(pageBuffer, 650) + 0.0) * 0.1);
		RevLimTPSbypassRPM = (int) ((MSUtils.getSignedWord(pageBuffer, 652) + 0.0) * 1.0);
		RevLimNormal1 = (int) ((MSUtils.getSignedWord(pageBuffer, 654) + 0.0) * 1.0);
		RevLimNormal2 = (int) ((MSUtils.getSignedWord(pageBuffer, 656) + 0.0) * 1.0);
		TC5_required_width = (double) ((MSUtils.getWord(pageBuffer, 658) + 0.0) * 0.66667);
		egoLimit = (double) ((MSUtils.getSignedWord(pageBuffer, 660) + 0.0) * 0.01);
		stoich = (double) ((MSUtils.getSignedWord(pageBuffer, 662) + 0.0) * 0.1);
		enable_pollADC = MSUtils.getBits(pageBuffer, 664, 0, 0, 0);
		enable_pollPWM = MSUtils.getBits(pageBuffer, 664, 1, 1, 0);
		enable_pollports = MSUtils.getBits(pageBuffer, 664, 2, 2, 0);
		poll_tableADC = (int) ((MSUtils.getByte(pageBuffer, 665) + 0.0) * 1.0);
		poll_tablePWM = (int) ((MSUtils.getByte(pageBuffer, 666) + 0.0) * 1.0);
		poll_tableports = (int) ((MSUtils.getByte(pageBuffer, 667) + 0.0) * 1.0);
		poll_offsetADC = (int) ((MSUtils.getSignedWord(pageBuffer, 668) + 0.0) * 1.0);
		poll_offsetPWM = (int) ((MSUtils.getSignedWord(pageBuffer, 670) + 0.0) * 1.0);
		poll_offsetports = (int) ((MSUtils.getSignedWord(pageBuffer, 672) + 0.0) * 1.0);
		ports_dir = MSUtils.getBits(pageBuffer, 674, 0, 2, 0);
		port1_type = MSUtils.getBits(pageBuffer, 674, 4, 4, 0);
		port2_type = MSUtils.getBits(pageBuffer, 674, 5, 5, 0);
		port3_type = MSUtils.getBits(pageBuffer, 674, 6, 6, 0);
		port_generic = MSUtils.getBits(pageBuffer, 675, 0, 1, 0);
		CID = (int) ((MSUtils.getWord(pageBuffer, 676) + 0.0) * 1.0);
		remotePWMfreq = (int) ((MSUtils.getByte(pageBuffer, 678) + 0.0) * 1.0);
		remotePWMprescale = (int) ((MSUtils.getByte(pageBuffer, 679) + 0.0) * 1.0);
		hw_latency = (double) ((MSUtils.getByte(pageBuffer, 732) + 0.0) * 0.66667);
		loadCombine = MSUtils.getBits(pageBuffer, 733, 0, 0, 0);
		loadMult = MSUtils.getBits(pageBuffer, 733, 2, 2, 0);
		loadStoich = MSUtils.getBits(pageBuffer, 733, 3, 3, 0);
		baud = (int) ((MSUtils.getLong(pageBuffer, 734) + 0.0) * 1.0);
		MAPOXLimit = (double) ((MSUtils.getSignedWord(pageBuffer, 738) + 0.0) * 0.1);
		board_type = MSUtils.getBits(pageBuffer, 740, 0, 7, 0);
		mycan_id = (int) ((MSUtils.getByte(pageBuffer, 741) + 0.0) * 1.0);
		mapsample_window = (int) ((MSUtils.getByte(pageBuffer, 750) + 0.0) * 1.0);
		can_poll = MSUtils.getBits(pageBuffer, 751, 0, 3, 0);
		can_poll_id = MSUtils.getBits(pageBuffer, 752, 0, 3, 0);
		can_poll_slave = MSUtils.getBits(pageBuffer, 752, 7, 7, 0);
		MAPOXMin = (double) ((MSUtils.getSignedWord(pageBuffer, 753) + 0.0) * 0.1);
		aeTaperTime = (double) ((MSUtils.getByte(pageBuffer, 896) + 0.0) * 0.1);
		aeEndPW = (double) ((MSUtils.getSignedWord(pageBuffer, 898) + 0.0) * 0.1);
		egoAlgorithm = MSUtils.getBits(pageBuffer, 900, 0, 1, 0);
		egoKP = (int) ((MSUtils.getByte(pageBuffer, 901) + 0.0) * 1.0);
		egoKI = (int) ((MSUtils.getByte(pageBuffer, 902) + 0.0) * 1.0);
		egoKD = (int) ((MSUtils.getByte(pageBuffer, 903) + 0.0) * 1.0);
		egoKdelay1 = (int) ((MSUtils.getWord(pageBuffer, 904) + 0.0) * 1.0);
		egoKdelay2 = (int) ((MSUtils.getWord(pageBuffer, 906) + 0.0) * 1.0);
		flexFuel = MSUtils.getBits(pageBuffer, 908, 0, 0, 0);
		flexFuelRemote = MSUtils.getBits(pageBuffer, 908, 1, 1, 0);
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
		knkport = MSUtils.getBits(pageBuffer, 927, 2, 2, 0);
		knk_remote = MSUtils.getBits(pageBuffer, 927, 3, 3, 0);
		knkDirection = MSUtils.getBits(pageBuffer, 927, 4, 4, 0);
		knkpull = MSUtils.getBits(pageBuffer, 927, 5, 6, 0);
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
		Miss_ang = (double) ((MSUtils.getWord(pageBuffer, 969) + 0.0) * 0.1);
		ICISR_tmask = (double) ((MSUtils.getByte(pageBuffer, 971) + 0.0) * 0.1);
		ICISR_pmask = (int) ((MSUtils.getByte(pageBuffer, 972) + 0.0) * 1.0);
		knkport_remote = MSUtils.getBits(pageBuffer, 973, 0, 2, 0);
		ae_lorpm = (int) ((MSUtils.getWord(pageBuffer, 974) + 0.0) * 1.0);
		ae_hirpm = (int) ((MSUtils.getWord(pageBuffer, 976) + 0.0) * 1.0);
		fuelSpkDel0 = (double) ((MSUtils.getSignedWord(pageBuffer, 978) + 0.0) * 0.1);
		fuelSpkDel1 = (double) ((MSUtils.getSignedWord(pageBuffer, 980) + 0.0) * 0.1);
		IC2ISR_tmask = (double) ((MSUtils.getByte(pageBuffer, 982) + 0.0) * 0.1);
		IC2ISR_pmask = (int) ((MSUtils.getByte(pageBuffer, 983) + 0.0) * 1.0);
		NoiseFilterOpts = MSUtils.getBits(pageBuffer, 984, 0, 0, 0);
		NoiseFilterOpts1 = MSUtils.getBits(pageBuffer, 984, 1, 1, 0);
		NoiseFilterOpts2 = MSUtils.getBits(pageBuffer, 984, 2, 2, 0);
		NoiseFilterOpts3 = MSUtils.getBits(pageBuffer, 984, 3, 3, 0);
		afrload = MSUtils.getBits(pageBuffer, 985, 0, 2, 0);
		eaeload = MSUtils.getBits(pageBuffer, 985, 4, 6, 0);
		spk_conf2_gm = MSUtils.getBits(pageBuffer, 987, 0, 0, 0);
		spk_conf2_tfi = MSUtils.getBits(pageBuffer, 987, 1, 2, 0);
		spk_conf2_cam = MSUtils.getBits(pageBuffer, 987, 3, 3, 0);
		spk_conf2_oddodd = MSUtils.getBits(pageBuffer, 987, 4, 4, 0);
		spk_conf2_ngc = MSUtils.getBits(pageBuffer, 987, 6, 6, 0);
		spk_conf2_dli = MSUtils.getBits(pageBuffer, 987, 7, 7, 0);
		spk_config_spka = MSUtils.getBits(pageBuffer, 988, 0, 0, 0);
		spk_config_camcrank = MSUtils.getBits(pageBuffer, 988, 1, 1, 0);
		spk_config_trig2 = MSUtils.getBits(pageBuffer, 988, 2, 3, 0);
		spk_config_trig2l = MSUtils.getBits(pageBuffer, 988, 4, 5, 0);
		spk_config_resetcam = MSUtils.getBits(pageBuffer, 988, 6, 7, 0);
		spk_mode0 = MSUtils.getBits(pageBuffer, 989, 0, 5, 0);
		spk_mode3 = MSUtils.getBits(pageBuffer, 989, 6, 7, 0);
		userlevel = (int) ((MSUtils.getByte(pageBuffer, 990) + 0.0) * 1.0);
		userlevelbits = MSUtils.getBits(pageBuffer, 990, 6, 7, 0);
		if (MICROSQUIRT_FULL)
		{
			rtbaroport = MSUtils.getBits(pageBuffer, 991, 0, 3, 0);
			ego2port = MSUtils.getBits(pageBuffer, 992, 0, 3, 0);
			egoport = MSUtils.getBits(pageBuffer, 993, 0, 3, 0);
			flexport = MSUtils.getBits(pageBuffer, 994, 0, 0, 0);
		}
		else
		{
			if (MICROSQUIRT_MODULE)
			{
				rtbaroport = MSUtils.getBits(pageBuffer, 991, 0, 3, 0);
				ego2port = MSUtils.getBits(pageBuffer, 992, 0, 3, 0);
				egoport = MSUtils.getBits(pageBuffer, 993, 0, 3, 0);
				flexport = MSUtils.getBits(pageBuffer, 994, 0, 0, 0);
			}
			else
			{
				rtbaroport = MSUtils.getBits(pageBuffer, 991, 0, 3, 0);
				ego2port = MSUtils.getBits(pageBuffer, 992, 0, 3, 0);
				egoport = MSUtils.getBits(pageBuffer, 993, 0, 3, 0);
				flexport = MSUtils.getBits(pageBuffer, 994, 0, 0, 0);
			}
		}
		flexportRemote = MSUtils.getBits(pageBuffer, 994, 1, 2, 0);
		RevLimcutx = (int) ((MSUtils.getByte(pageBuffer, 995) + 0.0) * 1.0);
		RevLimcuty = (int) ((MSUtils.getByte(pageBuffer, 996) + 0.0) * 1.0);
		feature4_0igntrig = MSUtils.getBits(pageBuffer, 997, 1, 1, 0);
		feature4_0VEtblsize = MSUtils.getBits(pageBuffer, 997, 2, 2, 0);
		feature4_0maxdwl = MSUtils.getBits(pageBuffer, 997, 3, 3, 0);
		feature4_0ftrig = MSUtils.getBits(pageBuffer, 997, 4, 5, 0);
		feature4_0mindwl = MSUtils.getBits(pageBuffer, 997, 6, 6, 0);
		feature4_0vanos = MSUtils.getBits(pageBuffer, 997, 7, 7, 0);
		timing_flags = MSUtils.getBits(pageBuffer, 1018, 0, 0, 0);
		use_prediction = MSUtils.getBits(pageBuffer, 1018, 1, 1, 0);
		crank_dwell = (double) ((MSUtils.getByte(pageBuffer, 1019) + 0.0) * 0.0666);
		crank_timing = (double) ((MSUtils.getSignedWord(pageBuffer, 1020) + 0.0) * 0.1);
		fixed_timing = (double) ((MSUtils.getSignedWord(pageBuffer, 1022) + 0.0) * 0.1);
		pageBuffer = loadPage(2, 1024, 1024, null, new byte[] { 114, 0, 5, 4, 0, 4, 0 });
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
		pwmidle_ms = (int) ((MSUtils.getWord(pageBuffer, 32) + 0.0) * 1.0);
		pwmidle_close_delay = (int) ((MSUtils.getByte(pageBuffer, 34) + 0.0) * 1.0);
		pwmidle_open_duty = (double) ((MSUtils.getByte(pageBuffer, 35) + 0.0) * 0.39063);
		pwmidle_open_steps = (int) ((MSUtils.getByte(pageBuffer, 35) + 0.0) * 1.0);
		pwmidle_closed_duty = (double) ((MSUtils.getByte(pageBuffer, 36) + 0.0) * 0.39063);
		pwmidle_closed_steps = (int) ((MSUtils.getByte(pageBuffer, 36) + 0.0) * 1.0);
		pwmidle_pid_wait_timer = (int) ((MSUtils.getByte(pageBuffer, 37) + 0.0) * 1.0);
		pwmidle_min_duty = (double) ((MSUtils.getByte(pageBuffer, 38) + 0.0) * 0.39063);
		pwmidle_min_steps = (int) ((MSUtils.getByte(pageBuffer, 38) + 0.0) * 1.0);
		pwmidle_engage_rpm_adder = (int) ((MSUtils.getWord(pageBuffer, 39) + 0.0) * 1.0);
		pwmidle_tps_threshold = (double) ((MSUtils.getWord(pageBuffer, 41) + 0.0) * 0.1);
		pwmidle_dp_adder = (double) ((MSUtils.getByte(pageBuffer, 43) + 0.0) * 0.39063);
		pwmidle_dp_adder_steps = (int) ((MSUtils.getByte(pageBuffer, 43) + 0.0) * 1.0);
		pwmidle_rpmdot_threshold = (double) ((MSUtils.getWord(pageBuffer, 44) + 0.0) * 10.0);
		pwmidle_decelload_threshold = (double) ((MSUtils.getWord(pageBuffer, 46) + 0.0) * 0.1);
		pwmidle_Kp = (double) ((MSUtils.getWord(pageBuffer, 48) + 0.0) * 0.1);
		pwmidle_Ki = (double) ((MSUtils.getWord(pageBuffer, 50) + 0.0) * 0.1);
		pwmidle_Kd = (double) ((MSUtils.getWord(pageBuffer, 52) + 0.0) * 0.1);
		pwmidle_freq = (int) ((MSUtils.getByte(pageBuffer, 54) + 0.0) * 1.0);
		pwmidle_min_rpm = (int) ((MSUtils.getWord(pageBuffer, 55) + 0.0) * 1.0);
		pwmidle_max_rpm = (int) ((MSUtils.getWord(pageBuffer, 57) + 0.0) * 1.0);
		pwmidle_targ_ramptime = (int) ((MSUtils.getByte(pageBuffer, 59) + 0.0) * 1.0);
		pwmidle_rpmdot_disablepid = (double) ((MSUtils.getSignedWord(pageBuffer, 60) + 0.0) * 10.0);
		pwmidle_port = MSUtils.getBits(pageBuffer, 62, 0, 1, 0);
		pwmidle_rpm_hyst = (int) ((MSUtils.getWord(pageBuffer, 63) + 0.0) * 1.0);
		pwmidle_pid_duty_tolerance = (double) ((MSUtils.getByte(pageBuffer, 65) + 0.0) * 0.39063);
		pwmidle_pid_steps_tolerance = (int) ((MSUtils.getByte(pageBuffer, 65) + 0.0) * 1.0);
		pwmidle_pid_stallrpm = (int) ((MSUtils.getByte(pageBuffer, 66) + 0.0) * 1.0);
		boost_ctl_settings_freq = MSUtils.getBits(pageBuffer, 67, 0, 2, 0);
		boost_ctl_settings_on = MSUtils.getBits(pageBuffer, 67, 3, 3, 0);
		boost_ctl_settings_cl = MSUtils.getBits(pageBuffer, 67, 4, 4, 0);
		boost_ctl_settings_invert = MSUtils.getBits(pageBuffer, 67, 5, 5, 0);
		boost_ctl_settings_remote = MSUtils.getBits(pageBuffer, 67, 6, 6, 0);
		if (MICROSQUIRT_FULL)
		{
			boost_ctl_pins = MSUtils.getBits(pageBuffer, 68, 0, 3, 0);
		}
		else
		{
			if (MICROSQUIRT_MODULE)
			{
				boost_ctl_pins = MSUtils.getBits(pageBuffer, 68, 0, 3, 0);
			}
			else
			{
				boost_ctl_pins = MSUtils.getBits(pageBuffer, 68, 0, 3, 0);
			}
		}
		boost_ctl_remote = MSUtils.getBits(pageBuffer, 68, 4, 5, 0);
		boost_ctl_Kp = (int) ((MSUtils.getByte(pageBuffer, 69) + 0.0) * 1.0);
		boost_ctl_Ki = (int) ((MSUtils.getByte(pageBuffer, 70) + 0.0) * 1.0);
		boost_ctl_Kd = (int) ((MSUtils.getByte(pageBuffer, 71) + 0.0) * 1.0);
		boost_ctl_closeduty = (int) ((MSUtils.getByte(pageBuffer, 72) + 0.0) * 1.0);
		boost_ctl_openduty = (int) ((MSUtils.getByte(pageBuffer, 73) + 0.0) * 1.0);
		boost_ctl_ms = (int) ((MSUtils.getWord(pageBuffer, 74) + 0.0) * 1.0);
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
		tsf_rpm = (int) ((MSUtils.getWord(pageBuffer, 766) + 0.0) * 1.0);
		tsf_kpa = (double) ((MSUtils.getSignedWord(pageBuffer, 768) + 0.0) * 0.1);
		tsf_tps = (double) ((MSUtils.getSignedWord(pageBuffer, 770) + 0.0) * 0.1);
		tss_rpm = (int) ((MSUtils.getWord(pageBuffer, 772) + 0.0) * 1.0);
		tss_kpa = (double) ((MSUtils.getSignedWord(pageBuffer, 774) + 0.0) * 0.1);
		tss_tps = (double) ((MSUtils.getSignedWord(pageBuffer, 776) + 0.0) * 0.1);
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
		airden_scaling = (int) ((MSUtils.getByte(pageBuffer, 826) + 0.0) * 1.0);
		tsf_remote = MSUtils.getBits(pageBuffer, 827, 0, 0, 0);
		tsf_remote_port = MSUtils.getBits(pageBuffer, 827, 1, 3, 0);
		tss_remote = MSUtils.getBits(pageBuffer, 827, 4, 4, 0);
		tss_remote_port = MSUtils.getBits(pageBuffer, 827, 5, 7, 0);
		OvrRunC = MSUtils.getBits(pageBuffer, 828, 0, 0, 0);
		f5_0_tsf = MSUtils.getBits(pageBuffer, 828, 1, 1, 0);
		if (MICROSQUIRT_FULL)
		{
			f5_0_tsf_opt = MSUtils.getBits(pageBuffer, 828, 2, 3, 0);
			f5_0_tss_opt = MSUtils.getBits(pageBuffer, 828, 5, 6, 0);
		}
		else
		{
			f5_0_tsf_opt = MSUtils.getBits(pageBuffer, 828, 2, 3, 0);
			f5_0_tss_opt = MSUtils.getBits(pageBuffer, 828, 5, 6, 0);
		}
		f5_0_tss = MSUtils.getBits(pageBuffer, 828, 4, 4, 0);
		f5_0_ego_acc = MSUtils.getBits(pageBuffer, 828, 7, 7, 0);
		EGO_wait_secs = (int) ((MSUtils.getByte(pageBuffer, 829) + 0.0) * 1.0);
		pwmidlecranktaper = (int) ((MSUtils.getByte(pageBuffer, 831) + 0.0) * 1.0);
		pwmidleset_hz = MSUtils.getBits(pageBuffer, 832, 0, 0, 0);
		pwmidleset_inv = MSUtils.getBits(pageBuffer, 832, 1, 1, 0);
		fc_rpm = (int) ((MSUtils.getWord(pageBuffer, 833) + 0.0) * 1.0);
		fc_kpa = (double) ((MSUtils.getSignedWord(pageBuffer, 835) + 0.0) * 0.1);
		fc_tps = (double) ((MSUtils.getSignedWord(pageBuffer, 837) + 0.0) * 0.1);
		if (CELSIUS)
		{
			if (EXPANDED_CLT_TEMP)
			{
				fc_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 839) + -320.0) * 0.05555);
			}
			else
			{
				fc_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 839) + -320.0) * 0.05555);
			}
		}
		else
		{
			if (EXPANDED_CLT_TEMP)
			{
				fc_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 839) + 0.0) * 0.1);
			}
			else
			{
				fc_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 839) + 0.0) * 0.1);
			}
		}
		fc_delay = (double) ((MSUtils.getByte(pageBuffer, 841) + 0.0) * 0.1);
		tacho_opt80 = MSUtils.getBits(pageBuffer, 842, 7, 7, 0);
		tacho_opt40 = MSUtils.getBits(pageBuffer, 842, 6, 6, 0);
		if (MICROSQUIRT_FULL)
		{
			tacho_opt3f = MSUtils.getBits(pageBuffer, 842, 0, 3, 0);
		}
		else
		{
			if (MICROSQUIRT_MODULE)
			{
				tacho_opt3f = MSUtils.getBits(pageBuffer, 842, 0, 3, 0);
			}
			else
			{
				tacho_opt3f = MSUtils.getBits(pageBuffer, 842, 0, 3, 0);
			}
		}
		EAElagsource = MSUtils.getBits(pageBuffer, 843, 0, 0, 0);
		EAElagthresh = (int) ((MSUtils.getSignedWord(pageBuffer, 844) + 0.0) * 1.0);
		EAElagRPMmax = (int) ((MSUtils.getWord(pageBuffer, 846) + 0.0) * 1.0);
		fc_ego_delay = (int) ((MSUtils.getByte(pageBuffer, 848) + 0.0) * 1.0);
		fc_rpm_lower = (int) ((MSUtils.getWord(pageBuffer, 849) + 0.0) * 1.0);
		pwmidle_shift_lower_rpm = (int) ((MSUtils.getWord(pageBuffer, 851) + 0.0) * 1.0);
		pwmidle_shift_open_time = (int) ((MSUtils.getByte(pageBuffer, 853) + 0.0) * 1.0);
		pageBuffer = loadPage(3, 2048, 1024, null, new byte[] { 114, 0, 49, 8, 0, 4, 0 });
		feature3_1 = MSUtils.getBits(pageBuffer, 672, 1, 1, 0);
		feature3_3 = MSUtils.getBits(pageBuffer, 672, 3, 3, 0);
		launch_opt_on = MSUtils.getBits(pageBuffer, 673, 6, 7, 0);
		if (MICROSQUIRT_FULL)
		{
			launch_opt_pins = MSUtils.getBits(pageBuffer, 673, 0, 3, 0);
		}
		else
		{
			if (MICROSQUIRT_MODULE)
			{
				launch_opt_pins = MSUtils.getBits(pageBuffer, 673, 0, 3, 0);
			}
			else
			{
				launch_opt_pins = MSUtils.getBits(pageBuffer, 673, 0, 3, 0);
			}
		}
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
		staged_first_param = MSUtils.getBits(pageBuffer, 697, 0, 2, 0);
		staged_second_param = MSUtils.getBits(pageBuffer, 697, 3, 5, 0);
		staged_transition_on = MSUtils.getBits(pageBuffer, 697, 6, 6, 0);
		staged_second_logic = MSUtils.getBits(pageBuffer, 697, 7, 7, 0);
		staged_transition_events = (int) ((MSUtils.getByte(pageBuffer, 698) + 0.0) * 1.0);
		staged_param_1 = (int) ((MSUtils.getWord(pageBuffer, 699) + 0.0) * 1.0);
		staged_param_2 = (int) ((MSUtils.getWord(pageBuffer, 701) + 0.0) * 1.0);
		staged_hyst_1 = (int) ((MSUtils.getWord(pageBuffer, 703) + 0.0) * 1.0);
		staged_hyst_2 = (int) ((MSUtils.getWord(pageBuffer, 705) + 0.0) * 1.0);
		N2Oopt_01 = MSUtils.getBits(pageBuffer, 707, 0, 1, 0);
		N2Oopt_2 = MSUtils.getBits(pageBuffer, 707, 2, 2, 0);
		N2Oopt_3 = MSUtils.getBits(pageBuffer, 707, 3, 3, 0);
		if (MICROSQUIRT_FULL)
		{
			N2Oopt_pins = MSUtils.getBits(pageBuffer, 707, 4, 6, 0);
			N2Oopt_pins2 = MSUtils.getBits(pageBuffer, 707, 7, 7, 0);
		}
		else
		{
			if (MICROSQUIRT_MODULE)
			{
				N2Oopt_pins = MSUtils.getBits(pageBuffer, 707, 4, 6, 0);
				N2Oopt_pins2 = MSUtils.getBits(pageBuffer, 707, 7, 7, 0);
			}
			else
			{
				N2Oopt_pins = MSUtils.getBits(pageBuffer, 707, 4, 6, 0);
				N2Oopt_pins2 = MSUtils.getBits(pageBuffer, 707, 7, 7, 0);
			}
		}
		N2ORpm = (int) ((MSUtils.getWord(pageBuffer, 708) + 0.0) * 1.0);
		N2ORpmMax = (int) ((MSUtils.getWord(pageBuffer, 710) + 0.0) * 1.0);
		N2OTps = (double) ((MSUtils.getSignedWord(pageBuffer, 712) + 0.0) * 0.1);
		if (CELSIUS)
		{
			if (EXPANDED_CLT_TEMP)
			{
				N2OClt = (double) ((MSUtils.getSignedWord(pageBuffer, 714) + -320.0) * 0.05555);
			}
			else
			{
				N2OClt = (double) ((MSUtils.getSignedWord(pageBuffer, 714) + -320.0) * 0.05555);
			}
		}
		else
		{
			if (EXPANDED_CLT_TEMP)
			{
				N2OClt = (double) ((MSUtils.getSignedWord(pageBuffer, 714) + 0.0) * 0.1);
			}
			else
			{
				N2OClt = (double) ((MSUtils.getSignedWord(pageBuffer, 714) + 0.0) * 0.1);
			}
		}
		N2OAngle = (double) ((MSUtils.getSignedWord(pageBuffer, 716) + 0.0) * 0.1);
		N2OPWLo = (double) ((MSUtils.getWord(pageBuffer, 718) + 0.0) * 0.0010);
		N2OPWHi = (double) ((MSUtils.getWord(pageBuffer, 720) + 0.0) * 0.0010);
		N2Odel_launch = (double) ((MSUtils.getByte(pageBuffer, 722) + 0.0) * 0.01);
		N2Odel_flat = (double) ((MSUtils.getByte(pageBuffer, 723) + 0.0) * 0.01);
		N2Oholdon = (double) ((MSUtils.getByte(pageBuffer, 724) + 0.0) * 0.01);
		N2O2Rpm = (int) ((MSUtils.getWord(pageBuffer, 725) + 0.0) * 1.0);
		N2O2RpmMax = (int) ((MSUtils.getWord(pageBuffer, 727) + 0.0) * 1.0);
		N2O2delay = (double) ((MSUtils.getByte(pageBuffer, 729) + 0.0) * 0.01);
		N2O2Angle = (double) ((MSUtils.getSignedWord(pageBuffer, 730) + 0.0) * 0.1);
		N2O2PWLo = (double) ((MSUtils.getWord(pageBuffer, 732) + 0.0) * 6.66E-4);
		N2O2PWHi = (double) ((MSUtils.getWord(pageBuffer, 734) + 0.0) * 6.66E-4);
		user_value1 = (int) ((MSUtils.getWord(pageBuffer, 736) + 0.0) * 1.0);
		user_value2 = (int) ((MSUtils.getWord(pageBuffer, 738) + 0.0) * 1.0);
		user_conf0 = MSUtils.getBits(pageBuffer, 740, 0, 0, 0);
		user_conf1 = MSUtils.getBits(pageBuffer, 740, 1, 2, 0);
		staged_secondary_enrichment = (double) ((MSUtils.getWord(pageBuffer, 741) + 0.0) * 0.0010);
		N20remote_input = MSUtils.getBits(pageBuffer, 803, 0, 0, 0);
		N20remote_output = MSUtils.getBits(pageBuffer, 803, 1, 1, 0);
		N20remote_inport = MSUtils.getBits(pageBuffer, 803, 2, 4, 0);
		N20remote_outport = MSUtils.getBits(pageBuffer, 803, 5, 7, 0);
		RotarySplitModeFD = MSUtils.getBits(pageBuffer, 967, 0, 0, 0);
		RotarySplitModeNeg = MSUtils.getBits(pageBuffer, 967, 1, 1, 0);
		RotarySplitModeRX8 = MSUtils.getBits(pageBuffer, 967, 2, 2, 0);
		RotarySplitModeOn = MSUtils.getBits(pageBuffer, 967, 5, 5, 0);
		staged_primary_delay = (int) ((MSUtils.getByte(pageBuffer, 985) + 0.0) * 1.0);
		trig_init = (int) ((MSUtils.getByte(pageBuffer, 1003) + 0.0) * 1.0);
		inj_time_mask = (int) ((MSUtils.getByte(pageBuffer, 1004) + 0.0) * 1.0);
		pageBuffer = loadPage(4, 3072, 1024, null, new byte[] { 114, 0, 8, 12, 0, 4, 0 });
		if (INI_VERSION_2)
		{
			testmodelock = (int) ((MSUtils.getWord(pageBuffer, 0) + 0.0) * 1.0);
		}
		else
		{
			testmodelock = (int) ((MSUtils.getWord(pageBuffer, 0) + 0.0) * 1.0);
		}
		if (MICROSQUIRT_FULL)
		{
			testop_coil = MSUtils.getBits(pageBuffer, 2, 0, 2, 0);
		}
		else
		{
			if (MICROSQUIRT_MODULE)
			{
				testop_coil = MSUtils.getBits(pageBuffer, 2, 0, 2, 0);
			}
			else
			{
				testop_coil = MSUtils.getBits(pageBuffer, 2, 0, 2, 0);
			}
		}
		testop_fp = MSUtils.getBits(pageBuffer, 2, 4, 4, 0);
		testop_inj = MSUtils.getBits(pageBuffer, 2, 5, 6, 0);
		testop_pwm = MSUtils.getBits(pageBuffer, 2, 7, 7, 0);
		testdwell = (double) ((MSUtils.getByte(pageBuffer, 3) + 0.0) * 0.1);
		testint = (double) ((MSUtils.getWord(pageBuffer, 4) + 0.0) * 0.128);
		testmode = MSUtils.getBits(pageBuffer, 6, 0, 1, 0);
		test_addinj = MSUtils.getBits(pageBuffer, 6, 4, 5, 0);
		testpw = (double) ((MSUtils.getWord(pageBuffer, 7) + 0.0) * 6.66E-4);
		testinjcnt = (int) ((MSUtils.getWord(pageBuffer, 9) + 0.0) * 1.0);
		testinjPwmT = (double) ((MSUtils.getByte(pageBuffer, 347) + 0.128) * 0.128);
		testinjPwmPd = (int) ((MSUtils.getByte(pageBuffer, 348) + 0.0) * 1.0);
		testinjPwmP = (int) ((MSUtils.getByte(pageBuffer, 349) + 0.0) * 1.0);
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
		iacpostest = (int) ((MSUtils.getWord(pageBuffer, 382) + 0.0) * 1.0);
		iachometest = (int) ((MSUtils.getWord(pageBuffer, 384) + 0.0) * 1.0);
		iactest = MSUtils.getBits(pageBuffer, 386, 0, 0, 0);
		iactestlock = MSUtils.getBits(pageBuffer, 386, 1, 1, 0);
		idleadvance_on = MSUtils.getBits(pageBuffer, 387, 0, 0, 0);
		idleadvance_tps = (double) ((MSUtils.getSignedWord(pageBuffer, 388) + 0.0) * 0.1);
		idleadvance_rpm = (int) ((MSUtils.getSignedWord(pageBuffer, 390) + 0.0) * 1.0);
		idleadvance_load = (double) ((MSUtils.getSignedWord(pageBuffer, 392) + 0.0) * 0.1);
		if (CELSIUS)
		{
			if (EXPANDED_CLT_TEMP)
			{
				idleadvance_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 394) + -320.0) * 0.05555);
			}
			else
			{
				idleadvance_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 394) + -320.0) * 0.05555);
			}
		}
		else
		{
			if (EXPANDED_CLT_TEMP)
			{
				idleadvance_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 394) + 0.0) * 0.1);
			}
			else
			{
				idleadvance_clt = (double) ((MSUtils.getSignedWord(pageBuffer, 394) + 0.0) * 0.1);
			}
		}
		idleadvance_delay = (int) ((MSUtils.getByte(pageBuffer, 396) + 0.0) * 1.0);
		seq_inj = MSUtils.getBits(pageBuffer, 415, 0, 1, 0);
		if (MICROSQUIRT_FULL)
		{
			extrainj = MSUtils.getBits(pageBuffer, 415, 2, 2, 0);
		}
		else
		{
			extrainj = MSUtils.getBits(pageBuffer, 415, 2, 2, 0);
		}
		injdualvalue = MSUtils.getBits(pageBuffer, 415, 3, 3, 0);
		usevetrim = MSUtils.getBits(pageBuffer, 415, 4, 4, 0);
		injusetable = MSUtils.getBits(pageBuffer, 415, 5, 5, 0);
		injtimingmode = MSUtils.getBits(pageBuffer, 415, 6, 7, 0);
		injadv1 = (double) ((MSUtils.getSignedWord(pageBuffer, 416) + 0.0) * 0.1);
		injadv2 = (double) ((MSUtils.getSignedWord(pageBuffer, 418) + 0.0) * 0.1);
		injadv3 = (double) ((MSUtils.getSignedWord(pageBuffer, 420) + 0.0) * 0.1);
		injstagedadv1 = (double) ((MSUtils.getSignedWord(pageBuffer, 422) + 0.0) * 0.1);
		injstagedadv2 = (double) ((MSUtils.getSignedWord(pageBuffer, 424) + 0.0) * 0.1);
		injstagedadv3 = (double) ((MSUtils.getSignedWord(pageBuffer, 426) + 0.0) * 0.1);
		injcrankadv1 = (double) ((MSUtils.getSignedWord(pageBuffer, 620) + 0.0) * 0.1);
		injcrankadv2 = (double) ((MSUtils.getSignedWord(pageBuffer, 622) + 0.0) * 0.1);
		hybrid_rpm = (int) ((MSUtils.getWord(pageBuffer, 624) + 0.0) * 1.0);
		hybrid_hyst = (int) ((MSUtils.getWord(pageBuffer, 626) + 0.0) * 1.0);
		injOpen3 = (double) ((MSUtils.getWord(pageBuffer, 628) + 0.0) * 0.0010);
		battFac3 = (double) ((MSUtils.getWord(pageBuffer, 630) + 0.0) * 1.66667E-4);
		injOpen4 = (double) ((MSUtils.getWord(pageBuffer, 632) + 0.0) * 0.0010);
		battFac4 = (double) ((MSUtils.getWord(pageBuffer, 634) + 0.0) * 1.66667E-4);
		ego_calib_selection = (int) ((MSUtils.getByte(pageBuffer, 1016) + 0.0) * 1.0);
	}

}
