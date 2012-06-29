package uk.org.smithfamily.mslogger.ecuDef.gen;

import java.io.IOException;
import java.util.*;

import android.content.Context;

import uk.org.smithfamily.mslogger.ecuDef.*;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
/*
Fingerprint : 235e7e4735ca1f942ae7b586e214b402
*/
@SuppressWarnings("unused")
public class GPIO_MShift_2109 extends Megasquirt
{
    public GPIO_MShift_2109(Context c)
    {
        super(c);
        refreshFlags();
    }
    @Override
    public void refreshFlags()
    {
        CAN_COMMANDS = isSet("CAN_COMMANDS");
        NOT_METRIC = isSet("NOT_METRIC");
    }
    private Map<String,Double> fields = new HashMap<String,Double>();
    byte[] queryCommand = new byte[]{'Q'};
    String signature = "MShift 2.109       \0";
    byte [] ochGetCommand = new byte[]{97,0,6};
    int ochBlockSize = 61;
//Flags
    private boolean CAN_COMMANDS;
    private boolean NOT_METRIC;
//Defaults
//Variables
    private int FWD;
    private double FuelAdj;
    private int IdleAdj;
    private double LOAD;
    private double LOAD_short;
    private int Output1;
    private int Output2;
    private int Output3;
    private int Output4;
    private int PC_duty;
    private double SpkAdj;
    private int SprAdj;
    private double VSS_Hz;
    private int WOT_flag;
    private int auto_mode;
    private double aux_volts;
    private int brake;
    private int cksum;
    private double clt;
    private int converter_slip;
    private int current_gear;
    private int dbug;
    private int deadValue;
    private int downbutton;
    private int downshift_request;
    private int engine_rpm;
    private int error;
    private int flashBurn;
    private double ic1_period;
    private int ic2_period;
    private int is_rpm;
    private double kmileage;
    private int linepressure;
    private int lock_TCC;
    private int manual_gear;
    private double mileage;
    private double odometer;
    private double os_rpm;
    private int secl;
    private int second2;
    private int seconds;
    private int solst;
    private int sp1;
    private int sp2;
    private double speedo;
    private int swA;
    private double swADC;
    private int swB;
    private double swBDC;
    private int swC;
    private double swCDC;
    private int swD;
    private int target_gear;
    private double time;
    private int upbutton;
    private int upshift_request;

//Constants
    private int In43;
    private int In44;
    private int Output5;
    private int In45;
    private int Output6;
    private int In46;
    private int sp2load_hyst;
    private int In41;
    private int In42;
    private int VSSsmoothFactor;
    private double gearR;
    private int can_var_rate;
    private int In47;
    private int In48;
    private int In49;
    private double sp1speed;
    private int LOADlongcount;
    private int Metric_Units;
    private int ic2_edge;
    private double noTCC_temp;
    private double FWD_factor;
    private double gear8;
    private double gear7;
    private int spare213;
    private double gear4;
    private int spare212;
    private double gear3;
    private int spare215;
    private int InputCaptureEdge;
    private double gear6;
    private int spare214;
    private double gear5;
    private int spare211;
    private int can_testout;
    private double gear2;
    private int spare210;
    private double gear1;
    private int FWD_cfg;
    private double IACAdjngear;
    private double PWM_enable;
    private int board_type;
    private double gear_hyst;
    private int shft_btn_polarity;
    private int trans_can_id;
    private int rpm_check;
    private int swBDC_OD;
    private int spareInputs;
    private int Adj_blk;
    private double gear6_retard;
    private int LED16;
    private int Out11;
    private int LED17;
    private int Out12;
    private int LED18;
    private int LED13;
    private int Out16;
    private int LED12;
    private int Out15;
    private int LED15;
    private int Out14;
    private int LED14;
    private int Out13;
    private int pulse_mile;
    private int Out19;
    private int LED11;
    private int Out18;
    private double axle_ratio;
    private int Out17;
    private int rpm_limit;
    private int In210;
    private int LEDN3;
    private int LEDN4;
    private int Out21;
    private int LEDR3alt;
    private int LED27;
    private int Out22;
    private int LED28;
    private int Out23;
    private int LED26;
    private int Out25;
    private int LED25;
    private int Out24;
    private int LEDN2;
    private int LED24;
    private int Out27;
    private int LEDN1;
    private int LED23;
    private int Out26;
    private int LED22;
    private int Out29;
    private int LED21;
    private int Out28;
    private int STCC_ULrpm;
    private double min_speed;
    private double noTCC_load;
    private double PC_PWM_Pd;
    private double idle_tps_adc;
    private int LED38;
    private int TMP_adc;
    private double hyst_enable_speed;
    private double gear8_retard;
    private int line_units;
    private int clutch22d;
    private int dither_durPC;
    private double TCC_PWM_Pd;
    private int PulseTol;
    private int clutch21u;
    private int LED31;
    private int LED32;
    private int LED33;
    private int LED34;
    private int LED35;
    private int LED36;
    private int LED37;
    private double gear2_retard;
    private int In19;
    private int In18;
    private int In17;
    private int In16;
    private int In15;
    private int In4_cfg;
    private int In14;
    private int mlever_mode;
    private int In13;
    private int In12;
    private double min_manual_speed;
    private int In11;
    private double gear5_retard;
    private int clutch23d;
    private int num_gears;
    private double AdvAdjngear;
    private int LEDR4alt;
    private double LUF_hiLoad;
    private int activate_xrate;
    private int clutch22u;
    private int LEDR2alt;
    private double sp2speed_hyst;
    private int LED43;
    private int stdin_cfg;
    private int LED44;
    private int LED41;
    private int LED42;
    private int trans_type;
    private int sp2load_cond;
    private int LED47;
    private int LED48;
    private int ic2_usage;
    private int LED45;
    private int LED46;
    private int In26;
    private int Out110;
    private int In25;
    private int In28;
    private int In27;
    private int dither_intPC;
    private int sp2gear_cond;
    private int In29;
    private int sp2speed_cond;
    private int In22;
    private int In21;
    private int In24;
    private int In23;
    private double PCneutral;
    private int Adj_varoffset;
    private int iss_divider;
    private int vss_mask;
    private double digital_threshold;
    private double trans_temp_limit;
    private int In39;
    private int clutch21d;
    private int In38;
    private int In37;
    private int clutch11d;
    private int LUF_loPWM;
    private int In36;
    private double fuel_density;
    private int In110;
    private double gear7_retard;
    private double LUF_loLoad;
    private int In31;
    private int In35;
    private int LUF_on;
    private int In34;
    private int In33;
    private int In32;
    private int vss_error_max;
    private double pressure_delay;
    private double TCC_counter;
    private int In310;
    private int num_teeth;
    private int refresh_int;
    private int LOADshortcount;
    private int LEDR1alt;
    private int sp1rpm_hyst;
    private double dwnshift_retard;
    private int error_check;
    private int clutch14u;
    private int clutch15d;
    private int clutch28u;
    private int sp1rpm;
    private double LUF_taper;
    private int kpa_hyst;
    private int clutch28d;
    private int Out410;
    private int clutch13u;
    private int LUF_off;
    private int Out510;
    private int sp2rpm;
    private int Out310;
    private double out12_pwm_on;
    private double upshift_retard;
    private int under_rev_limit;
    private int clutch14d;
    private double maxSpeed;
    private int WOT_dur;
    private int TMP_cfg;
    private int brake_ON_polarity;
    private int clutch27u;
    private int Out210;
    private int clutch12u;
    private int sp1rpm_cond;
    private int ms2varBLK;
    private int sp1gear;
    private int In410;
    private int Out610;
    private int ms2canID;
    private int clutch_enable;
    private int clutch13d;
    private double Tretard_load;
    private int CAN_enabled;
    private int load_type;
    private int iss_mask;
    private int STCC_gear;
    private int sp1load_cond;
    private int clutch11u;
    private double tire_diam;
    private int clutch12d;
    private int Input4;
    private int Input2;
    private int sp1speed_cond;
    private int Input3;
    private int shift_mode;
    private int Input1;
    private int minTCC_gear;
    private double mlever_v3;
    private double mlever_v2;
    private double inj_flow;
    private int sp1load_hyst;
    private double mlever_v1;
    private double out12_pwm_off;
    private int Out48;
    private int Out49;
    private int clutch25d;
    private int Out46;
    private int Out47;
    private int clutch18u;
    private double WOT_tps_threshold;
    private double open_time;
    private int Out41;
    private int nSquirts;
    private int Out45;
    private int STCC_rpm;
    private int out3dc;
    private int Out44;
    private int Out43;
    private int Out42;
    private int sp1load;
    private int clutch24u;
    private double max_shift_press;
    private double shift_delay;
    private int sp2load;
    private double sp2speed;
    private int refresh_dur;
    private int Out35;
    private int clutch24d;
    private int Out36;
    private int Out37;
    private int clutch17u;
    private int Out38;
    private int Out39;
    private double minTCCspeed;
    private double SOL32_PWM_Pd;
    private int stdout_cfg;
    private int sp2gear;
    private int Out32;
    private int Out31;
    private int Out34;
    private int Out33;
    private int clutch18d;
    private int clutch23u;
    private int sp1gear_cond;
    private int clutch27d;
    private double LOADmult;
    private int clutch16u;
    private int Out68;
    private int Out69;
    private int Out67;
    private double gear3_retard;
    private int sp2rpm_cond;
    private int Out66;
    private int Out65;
    private int Out64;
    private int Out63;
    private int PWM_enable1;
    private int Out62;
    private int PWM_enable2;
    private int Out61;
    private double shftr_accum;
    private int PWM_enable3;
    private int PWM_enable4;
    private int PWM_enable5;
    private int PWM_enable6;
    private int clutch17d;
    private int clutch26u;
    private double sp1speed_hyst;
    private int spare29;
    private double mlever_vP;
    private int spare28;
    private int spare27;
    private double mlever_vR;
    private int spare26;
    private int spare25;
    private int spare24;
    private int spare23;
    private double mlever_vN;
    private int spare22;
    private int clutch15u;
    private int spare21;
    private int TCC_PWM;
    private int LEDR3;
    private int LEDR4;
    private int clutch26d;
    private int Out57;
    private int LEDR1;
    private int Out58;
    private int LEDR2;
    private int Out59;
    private double gear4_retard;
    private int debounce;
    private int Out54;
    private int Out53;
    private int Out56;
    private int Out55;
    private int LUF_hiPWM;
    private int Out52;
    private int Out51;
    private double mlever_v8;
    private double mlever_v4;
    private int clutch25u;
    private double resetTime;
    private double mlever_v5;
    private int sp2rpm_hyst;
    private double mlever_v6;
    private double mlever_v7;
    private int clutch16d;


    private String[] defaultGauges = {
        "speedo",
        "odometer",
        "tach",
        "FuelEfficiency",
        "current_gear",
        "trans_temp",
        "pcsol",
        "load"
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
        if (NOT_METRIC)
        {
        speedo = (double)((MSUtils.getWord(ochBuffer,2) + 0.0) * 0.100);
        odometer = (double)((MSUtils.getLong(ochBuffer,4) + 0.0) * 0.001);
        }
        else
        {
        speedo = (double)((MSUtils.getWord(ochBuffer,2) + 0.0) * 0.100);
        odometer = (double)((MSUtils.getLong(ochBuffer,4) + 0.0) * 0.001);
        }
        auto_mode = (int)((MSUtils.getByte(ochBuffer,8) + 0.0) * 1.000);
        upshift_request = (int)((MSUtils.getByte(ochBuffer,9) + 0.0) * 1.000);
        downshift_request = (int)((MSUtils.getByte(ochBuffer,10) + 0.0) * 1.000);
        manual_gear = (int)((MSUtils.getSignedByte(ochBuffer,11) + 0.0) * 1.000);
        current_gear = (int)((MSUtils.getSignedByte(ochBuffer,12) + 0.0) * 1.000);
        target_gear = (int)((MSUtils.getSignedByte(ochBuffer,13) + 0.0) * 1.000);
        engine_rpm = (int)((MSUtils.getWord(ochBuffer,14) + 0.0) * 1.000);
        error = (int)((MSUtils.getByte(ochBuffer,16) + 0.0) * 1.000);
        LOAD = (double)((MSUtils.getSignedWord(ochBuffer,17) + 0.0) * 0.100);
        if (NOT_METRIC)
        {
        clt = (double)((MSUtils.getSignedWord(ochBuffer,19) + 0.0) * 0.100);
        }
        else
        {
        clt = (double)((MSUtils.getSignedWord(ochBuffer,19) + 0.0) * 0.100);
        }
        linepressure = (int)((MSUtils.getWord(ochBuffer,21) + 0.0) * 1.000);
        lock_TCC = (int)((MSUtils.getByte(ochBuffer,23) + 0.0) * 1.000);
        brake = (int)((MSUtils.getByte(ochBuffer,24) + 0.0) * 1.000);
        LOAD_short = (double)((MSUtils.getWord(ochBuffer,25) + 0.0) * 0.100);
        aux_volts = (double)((MSUtils.getWord(ochBuffer,27) + 0.0) * 0.001);
        PC_duty = (int)((MSUtils.getByte(ochBuffer,29) + 0.0) * 1.000);
        converter_slip = (int)((MSUtils.getByte(ochBuffer,30) + 0.0) * 1.000);
        dbug = (int)((MSUtils.getWord(ochBuffer,31) + 0.0) * 1.000);
        upbutton = (int)((MSUtils.getByte(ochBuffer,33) + 0.0) * 1.000);
        downbutton = (int)((MSUtils.getByte(ochBuffer,34) + 0.0) * 1.000);
        sp1 = (int)((MSUtils.getByte(ochBuffer,35) + 0.0) * 1.000);
        sp2 = (int)((MSUtils.getByte(ochBuffer,36) + 0.0) * 1.000);
        swADC = (double)((MSUtils.getWord(ochBuffer,37) + 0.0) * 0.00488);
        swBDC = (double)((MSUtils.getWord(ochBuffer,39) + 0.0) * 0.00488);
        swCDC = (double)((MSUtils.getWord(ochBuffer,41) + 0.0) * 0.00488);
        FWD = (int)((MSUtils.getByte(ochBuffer,43) + 0.0) * 1.000);
        solst = (int)((MSUtils.getByte(ochBuffer,44) + 0.0) * 1.000);
        Output1 = MSUtils.getBits(ochBuffer,44,0,0,0);
        Output2 = MSUtils.getBits(ochBuffer,44,1,1,0);
        Output3 = MSUtils.getBits(ochBuffer,44,2,2,0);
        Output4 = MSUtils.getBits(ochBuffer,44,3,3,0);
        mileage = (double)((MSUtils.getWord(ochBuffer,45) + 0.0) * 0.010);
        cksum = (int)((MSUtils.getByte(ochBuffer,47) + 0.0) * 1.000);
        FuelAdj = (double)((MSUtils.getSignedWord(ochBuffer,48) + 0.0) * 0.100);
        SpkAdj = (double)((MSUtils.getSignedWord(ochBuffer,50) + 0.0) * 0.100);
        IdleAdj = (int)((MSUtils.getSignedWord(ochBuffer,52) + 0.0) * 1.000);
        SprAdj = (int)((MSUtils.getSignedWord(ochBuffer,54) + 0.0) * 1.000);
        swA = MSUtils.getBits(ochBuffer,56,0,0,0);
        swB = MSUtils.getBits(ochBuffer,56,1,1,0);
        swC = MSUtils.getBits(ochBuffer,56,2,2,0);
        swD = MSUtils.getBits(ochBuffer,56,3,3,0);
        flashBurn = MSUtils.getBits(ochBuffer,57,0,0,0);
        is_rpm = (int)((MSUtils.getWord(ochBuffer,58) + 0.0) * 1.000);
        WOT_flag = (int)((MSUtils.getByte(ochBuffer,60) + 0.0) * 1.000);
        secl = ((timeNow() % 256) - (timeNow() % 1));
        second2 = (seconds % 256);
        time = (timeNow());
        kmileage = (235.2/mileage);
        if (NOT_METRIC)
        {
        os_rpm = (((speedo*1.467)/((tire_diam/12)*3.1416)) * 60 * axle_ratio);
        }
        else
        {
        os_rpm = (((speedo*0.893)/(((tire_diam/2.54)/12)*3.1416)) * 60 * axle_ratio);
        }
        VSS_Hz = ((os_rpm * num_teeth)/60);
        ic1_period = (1/VSS_Hz);
        ic2_period = (60/(is_rpm * iss_divider ));
    }
    @Override
    public String getLogHeader()
    {
        StringBuffer b = new StringBuffer();
        b.append("Time").append("\t");
        b.append("Seconds").append("\t");
        b.append("Mode").append("\t");
        b.append("Speed").append("\t");
        b.append("RPM").append("\t");
        if (NOT_METRIC)
        {
        b.append("miles").append("\t");
        }
        else
        {
        b.append("kms").append("\t");
        }
        b.append("load").append("\t");
        b.append("WOT").append("\t");
        b.append("mpg").append("\t");
        b.append("swADC").append("\t");
        b.append("swBDC").append("\t");
        b.append("swCDC").append("\t");
        b.append("swD").append("\t");
        b.append("soln").append("\t");
        b.append("UP").append("\t");
        b.append("DWN").append("\t");
        b.append("cGear").append("\t");
        b.append("tGear").append("\t");
        b.append("mGear").append("\t");
        b.append("TCC").append("\t");
        b.append("Brake").append("\t");
        b.append("OS").append("\t");
        b.append("IS").append("\t");
        b.append("slip").append("\t");
        if (NOT_METRIC)
        {
        b.append("degF").append("\t");
        }
        else
        {
        b.append("degC").append("\t");
        }
        b.append("line").append("\t");
        b.append("auxCH").append("\t");
        b.append("Error").append("\t");
        b.append("PC%").append("\t");
        b.append("Sp1").append("\t");
        b.append("Sp2").append("\t");
        b.append("sLoad").append("\t");
        b.append("dbug").append("\t");
        b.append("burn").append("\t");
        b.append("4WD").append("\t");
        b.append(MSUtils.getLocationLogHeader());
        return b.toString();
    }

    @Override
    public String getLogRow()
    {
        StringBuffer b = new StringBuffer();
        b.append(time).append("\t");
        b.append(seconds).append("\t");
        b.append(auto_mode).append("\t");
        b.append(round(speedo)).append("\t");
        b.append(engine_rpm).append("\t");
        if (NOT_METRIC)
        {
        b.append(round(odometer)).append("\t");
        }
        else
        {
        b.append(round(odometer)).append("\t");
        }
        b.append(round(LOAD)).append("\t");
        b.append(WOT_flag).append("\t");
        b.append(round(mileage)).append("\t");
        b.append(round(swADC)).append("\t");
        b.append(round(swBDC)).append("\t");
        b.append(round(swCDC)).append("\t");
        b.append(swD).append("\t");
        b.append(solst).append("\t");
        b.append(upbutton).append("\t");
        b.append(downbutton).append("\t");
        b.append(current_gear).append("\t");
        b.append(target_gear).append("\t");
        b.append(manual_gear).append("\t");
        b.append(lock_TCC).append("\t");
        b.append(brake).append("\t");
        b.append(os_rpm).append("\t");
        b.append(is_rpm).append("\t");
        b.append(converter_slip).append("\t");
        if (NOT_METRIC)
        {
        b.append(round(clt)).append("\t");
        }
        else
        {
        b.append(round(clt)).append("\t");
        }
        b.append(linepressure).append("\t");
        b.append(round(aux_volts)).append("\t");
        b.append(error).append("\t");
        b.append(PC_duty).append("\t");
        b.append(sp1).append("\t");
        b.append(sp2).append("\t");
        b.append(round(LOAD_short)).append("\t");
        b.append(dbug).append("\t");
        b.append(flashBurn).append("\t");
        b.append(FWD).append("\t");
        b.append(MSUtils.getLocationLogRow());
        return b.toString();
    }

    @Override
    public void initGauges()
    {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("auxVolts","aux_volts",aux_volts,"Aux. Channel Volts","Volts",0,5,0,0,5,5,3,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("clock","second2",second2,"Clock","seconds",0,255,0,0,253,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Converter_slip","converter_slip",converter_slip,"Conv. Slip","%",0,200,-1,-1,200,200,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("DShaft_RPM","os_rpm",os_rpm,"Driveshaft RPM","",0,6000,-1,-1,5000,5500,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Error","error",error,"Status","",0,255,-1,-1,255,255,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Input_Shaft_RPM","is_rpm",is_rpm,"Input Shaft RPM","",0,9000,-1,-1,6000,6500,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Manual_gear","manual_gear",manual_gear,"Manual Gear","",-1,4,-1,-1,5,5,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Target_gear","target_gear",target_gear,"Target Gear","",1,4,-1,-1,5,5,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("FuelEfficiency","mileage",mileage,"Fuel Efficiency","mpg",0,50,5,10,99,99,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("FuelConsumption","kmileage",kmileage,"Fuel EConsumpt.","l/100km",0,25,2,3,20,24,2,2,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("current_gear","current_gear",current_gear,"Current Gear","",-1,4,-1,-1,5,5,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("load","LOAD",LOAD,"Load","kpa",0,200,-1,-1,250,250,1,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("line_pressure","linepressure",linepressure,"Line Pressure","psi",0,500,50,100,400,480,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("odometer","odometer",odometer,"Trip Odometer","miles",0,100,-1,-1,255,255,2,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Output_Shaft_RPM","os_rpm",os_rpm,"Output Shaft RPM","",0,6000,-1,-1,3000,4500,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("pcsol","PC_duty",PC_duty,"Pressure Control","100-PWM%",0,100,-1,-1,101,101,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Input1","swADC",swADC,"Input1","volts",0,5,0,0,5,5,3,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Input2","swBDC",swBDC,"Input2","volts",0,5,0,0,5,5,3,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("Input3","swCDC",swCDC,"Input3","volts",0,5,0,0,5,5,3,0,45));
        if (NOT_METRIC)
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("speedo","speedo",speedo,"Speed","mph",0,200,-1,-1,160,180,0,0,45));
        }
        else
        {
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("speedo","speedo",speedo,"Speed","kph",0,300,-1,-1,200,270,0,0,45));
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("tach","engine_rpm",engine_rpm,"Tach","rpm",0,8000,-1,-1,6000,6500,0,0,45));
        if (NOT_METRIC)
        {
        }
        else
        {
        }
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("solst","solst",solst,"Solenoids","",0,4,-1,-1,10,10,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("VSS_errors","dbug",dbug,"VSS Errors","",0,1000,0,-1,1000,1000,0,0,45));
        GaugeRegister.INSTANCE.addGauge(new GaugeDetails("load_short","LOAD_short",LOAD_short,"Short Load","kpa",0,200,-1,-1,250,250,1,0,45));
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
        return 250;
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
        if (CAN_COMMANDS)
        {
        }
        pageBuffer = loadPage(1,0,633,null,new byte[]{114,1,4,0,0,2,121});
        InputCaptureEdge = MSUtils.getBits(pageBuffer,0,0,0,0);
        if (NOT_METRIC)
        {
        }
        else
        {
        }
        if (NOT_METRIC)
        {
        }
        else
        {
        }
        gearR = (double)((MSUtils.getWord(pageBuffer,69) + 0.0) * 0.0010);
        gear1 = (double)((MSUtils.getWord(pageBuffer,71) + 0.0) * 0.0010);
        gear2 = (double)((MSUtils.getWord(pageBuffer,73) + 0.0) * 0.0010);
        gear3 = (double)((MSUtils.getWord(pageBuffer,75) + 0.0) * 0.0010);
        gear4 = (double)((MSUtils.getWord(pageBuffer,77) + 0.0) * 0.0010);
        gear5 = (double)((MSUtils.getWord(pageBuffer,79) + 0.0) * 0.0010);
        gear6 = (double)((MSUtils.getWord(pageBuffer,81) + 0.0) * 0.0010);
        gear7 = (double)((MSUtils.getWord(pageBuffer,83) + 0.0) * 0.0010);
        gear8 = (double)((MSUtils.getWord(pageBuffer,85) + 0.0) * 0.0010);
        CAN_enabled = MSUtils.getBits(pageBuffer,87,0,0,0);
        load_type = MSUtils.getBits(pageBuffer,88,0,0,0);
        if (NOT_METRIC)
        {
        noTCC_temp = (double)((MSUtils.getSignedWord(pageBuffer,89) + 0.0) * 0.1);
        }
        else
        {
        noTCC_temp = (double)((MSUtils.getSignedWord(pageBuffer,89) + 0.0) * 0.1);
        }
        minTCC_gear = (int)((MSUtils.getSignedWord(pageBuffer,91) + 0.0) * 1.0);
        noTCC_load = (double)((MSUtils.getWord(pageBuffer,93) + 0.0) * 0.1);
        LUF_taper = (double)((MSUtils.getByte(pageBuffer,95) + 0.0) * 0.1);
        PulseTol = (int)((MSUtils.getByte(pageBuffer,96) + 0.0) * 1.0);
        axle_ratio = (double)((MSUtils.getWord(pageBuffer,97) + 0.0) * 0.0010);
        if (NOT_METRIC)
        {
        tire_diam = (double)((MSUtils.getWord(pageBuffer,99) + 0.0) * 0.01);
        }
        else
        {
        tire_diam = (double)((MSUtils.getWord(pageBuffer,99) + 0.0) * 0.01);
        }
        under_rev_limit = (int)((MSUtils.getWord(pageBuffer,101) + 0.0) * 1.0);
        trans_type = (int)((MSUtils.getByte(pageBuffer,103) + 0.0) * 1.0);
        shift_mode = (int)((MSUtils.getByte(pageBuffer,104) + 0.0) * 1.0);
        Metric_Units = MSUtils.getBits(pageBuffer,105,0,0,0);
        error_check = MSUtils.getBits(pageBuffer,106,0,0,0);
        rpm_limit = (int)((MSUtils.getWord(pageBuffer,107) + 0.0) * 1.0);
        debounce = (int)((MSUtils.getByte(pageBuffer,109) + 0.0) * 1.0);
        num_teeth = (int)((MSUtils.getByte(pageBuffer,110) + 0.0) * 1.0);
        TCC_PWM_Pd = (double)((MSUtils.getWord(pageBuffer,111) + 0.0) * 0.0010);
        PC_PWM_Pd = (double)((MSUtils.getWord(pageBuffer,113) + 0.0) * 0.0010);
        SOL32_PWM_Pd = (double)((MSUtils.getWord(pageBuffer,115) + 0.0) * 0.0010);
        ms2canID = (int)((MSUtils.getByte(pageBuffer,118) + 0.0) * 1.0);
        ms2varBLK = (int)((MSUtils.getByte(pageBuffer,119) + 0.0) * 1.0);
        PCneutral = (double)((MSUtils.getByte(pageBuffer,120) + -100.0) * -1.0);
        board_type = (int)((MSUtils.getByte(pageBuffer,125) + 0.0) * 1.0);
        trans_can_id = (int)((MSUtils.getByte(pageBuffer,126) + 0.0) * 1.0);
        can_var_rate = (int)((MSUtils.getWord(pageBuffer,127) + 0.0) * 1.0);
        if (NOT_METRIC)
        {
        trans_temp_limit = (double)((MSUtils.getWord(pageBuffer,417) + 0.0) * 0.1);
        }
        else
        {
        trans_temp_limit = (double)((MSUtils.getWord(pageBuffer,417) + 0.0) * 0.1);
        }
        pulse_mile = (int)((MSUtils.getWord(pageBuffer,419) + 0.0) * 1.0);
        rpm_check = MSUtils.getBits(pageBuffer,421,0,0,0);
        if (NOT_METRIC)
        {
        gear_hyst = (double)((MSUtils.getByte(pageBuffer,422) + 0.0) * 0.1);
        }
        else
        {
        gear_hyst = (double)((MSUtils.getByte(pageBuffer,422) + 0.0) * 0.1);
        }
        kpa_hyst = (int)((MSUtils.getByte(pageBuffer,423) + 0.0) * 1.0);
        max_shift_press = (double)((MSUtils.getByte(pageBuffer,424) + -100.0) * -1.0);
        pressure_delay = (double)((MSUtils.getWord(pageBuffer,425) + 0.0) * 8.0);
        shift_delay = (double)((MSUtils.getWord(pageBuffer,427) + 0.0) * 8.0);
        LOADshortcount = (int)((MSUtils.getWord(pageBuffer,429) + 0.0) * 1.0);
        LOADlongcount = (int)((MSUtils.getWord(pageBuffer,431) + 0.0) * 1.0);
        LOADmult = (double)((MSUtils.getSignedWord(pageBuffer,433) + 0.0) * 0.01);
        if (NOT_METRIC)
        {
        minTCCspeed = (double)((MSUtils.getWord(pageBuffer,437) + 0.0) * 0.1);
        }
        else
        {
        minTCCspeed = (double)((MSUtils.getWord(pageBuffer,437) + 0.0) * 0.1);
        }
        Output1 = (int)((MSUtils.getWord(pageBuffer,449) + 0.0) * 1.0);
        Out11 = MSUtils.getBits(pageBuffer,449,0,0,0);
        Out12 = MSUtils.getBits(pageBuffer,449,1,1,0);
        Out13 = MSUtils.getBits(pageBuffer,449,2,2,0);
        Out14 = MSUtils.getBits(pageBuffer,449,3,3,0);
        Out15 = MSUtils.getBits(pageBuffer,449,4,4,0);
        Out16 = MSUtils.getBits(pageBuffer,449,5,5,0);
        Out17 = MSUtils.getBits(pageBuffer,449,6,6,0);
        Out18 = MSUtils.getBits(pageBuffer,449,7,7,0);
        Out19 = MSUtils.getBits(pageBuffer,449,8,8,0);
        Out110 = MSUtils.getBits(pageBuffer,449,9,9,0);
        Output2 = (int)((MSUtils.getWord(pageBuffer,451) + 0.0) * 1.0);
        Out21 = MSUtils.getBits(pageBuffer,451,0,0,0);
        Out22 = MSUtils.getBits(pageBuffer,451,1,1,0);
        Out23 = MSUtils.getBits(pageBuffer,451,2,2,0);
        Out24 = MSUtils.getBits(pageBuffer,451,3,3,0);
        Out25 = MSUtils.getBits(pageBuffer,451,4,4,0);
        Out26 = MSUtils.getBits(pageBuffer,451,5,5,0);
        Out27 = MSUtils.getBits(pageBuffer,451,6,6,0);
        Out28 = MSUtils.getBits(pageBuffer,451,7,7,0);
        Out29 = MSUtils.getBits(pageBuffer,451,8,8,0);
        Out210 = MSUtils.getBits(pageBuffer,451,9,9,0);
        Output3 = (int)((MSUtils.getWord(pageBuffer,453) + 0.0) * 1.0);
        Out31 = MSUtils.getBits(pageBuffer,453,0,0,0);
        Out32 = MSUtils.getBits(pageBuffer,453,1,1,0);
        Out33 = MSUtils.getBits(pageBuffer,453,2,2,0);
        Out34 = MSUtils.getBits(pageBuffer,453,3,3,0);
        Out35 = MSUtils.getBits(pageBuffer,453,4,4,0);
        Out36 = MSUtils.getBits(pageBuffer,453,5,5,0);
        Out37 = MSUtils.getBits(pageBuffer,453,6,6,0);
        Out38 = MSUtils.getBits(pageBuffer,453,7,7,0);
        Out39 = MSUtils.getBits(pageBuffer,453,8,8,0);
        Out310 = MSUtils.getBits(pageBuffer,453,9,9,0);
        Output4 = (int)((MSUtils.getWord(pageBuffer,455) + 0.0) * 1.0);
        Out41 = MSUtils.getBits(pageBuffer,455,0,0,0);
        Out42 = MSUtils.getBits(pageBuffer,455,1,1,0);
        Out43 = MSUtils.getBits(pageBuffer,455,2,2,0);
        Out44 = MSUtils.getBits(pageBuffer,455,3,3,0);
        Out45 = MSUtils.getBits(pageBuffer,455,4,4,0);
        Out46 = MSUtils.getBits(pageBuffer,455,5,5,0);
        Out47 = MSUtils.getBits(pageBuffer,455,6,6,0);
        Out48 = MSUtils.getBits(pageBuffer,455,7,7,0);
        Out49 = MSUtils.getBits(pageBuffer,455,8,8,0);
        Out410 = MSUtils.getBits(pageBuffer,455,9,9,0);
        out3dc = (int)((MSUtils.getByte(pageBuffer,457) + 0.0) * 1.0);
        if (NOT_METRIC)
        {
        sp1speed = (double)((MSUtils.getWord(pageBuffer,458) + 0.0) * 0.1);
        sp1speed_hyst = (double)((MSUtils.getWord(pageBuffer,460) + 0.0) * 0.1);
        }
        else
        {
        sp1speed = (double)((MSUtils.getWord(pageBuffer,458) + 0.0) * 0.1);
        sp1speed_hyst = (double)((MSUtils.getWord(pageBuffer,460) + 0.0) * 0.1);
        }
        sp1speed_cond = MSUtils.getBits(pageBuffer,462,0,1,0);
        sp1rpm = (int)((MSUtils.getWord(pageBuffer,463) + 0.0) * 1.0);
        sp1rpm_hyst = (int)((MSUtils.getWord(pageBuffer,465) + 0.0) * 1.0);
        sp1rpm_cond = MSUtils.getBits(pageBuffer,467,0,1,0);
        sp1load = (int)((MSUtils.getWord(pageBuffer,468) + 0.0) * 1.0);
        sp1load_hyst = (int)((MSUtils.getWord(pageBuffer,470) + 0.0) * 1.0);
        sp1load_cond = MSUtils.getBits(pageBuffer,472,0,1,0);
        sp1gear = (int)((MSUtils.getByte(pageBuffer,473) + 0.0) * 1.0);
        sp1gear_cond = MSUtils.getBits(pageBuffer,474,0,1,0);
        if (NOT_METRIC)
        {
        sp2speed = (double)((MSUtils.getWord(pageBuffer,475) + 0.0) * 0.1);
        sp2speed_hyst = (double)((MSUtils.getWord(pageBuffer,477) + 0.0) * 0.1);
        }
        else
        {
        sp2speed = (double)((MSUtils.getWord(pageBuffer,475) + 0.0) * 0.1);
        sp2speed_hyst = (double)((MSUtils.getWord(pageBuffer,477) + 0.0) * 0.1);
        }
        sp2speed_cond = MSUtils.getBits(pageBuffer,479,0,1,0);
        sp2rpm = (int)((MSUtils.getWord(pageBuffer,480) + 0.0) * 1.0);
        sp2rpm_hyst = (int)((MSUtils.getWord(pageBuffer,482) + 0.0) * 1.0);
        sp2rpm_cond = MSUtils.getBits(pageBuffer,492,0,1,0);
        sp2load = (int)((MSUtils.getWord(pageBuffer,485) + 0.0) * 1.0);
        sp2load_hyst = (int)((MSUtils.getWord(pageBuffer,487) + 0.0) * 1.0);
        sp2load_cond = MSUtils.getBits(pageBuffer,489,0,1,0);
        sp2gear = (int)((MSUtils.getByte(pageBuffer,498) + 0.0) * 1.0);
        sp2gear_cond = MSUtils.getBits(pageBuffer,491,0,1,0);
        Input1 = (int)((MSUtils.getWord(pageBuffer,492) + 0.0) * 1.0);
        In11 = MSUtils.getBits(pageBuffer,492,0,0,0);
        In12 = MSUtils.getBits(pageBuffer,492,1,1,0);
        In13 = MSUtils.getBits(pageBuffer,492,2,2,0);
        In14 = MSUtils.getBits(pageBuffer,492,3,3,0);
        In15 = MSUtils.getBits(pageBuffer,492,4,4,0);
        In16 = MSUtils.getBits(pageBuffer,492,5,5,0);
        In17 = MSUtils.getBits(pageBuffer,492,6,6,0);
        In18 = MSUtils.getBits(pageBuffer,492,7,7,0);
        In19 = MSUtils.getBits(pageBuffer,492,8,8,0);
        In110 = MSUtils.getBits(pageBuffer,492,9,9,0);
        Input2 = (int)((MSUtils.getWord(pageBuffer,494) + 0.0) * 1.0);
        In21 = MSUtils.getBits(pageBuffer,494,0,0,0);
        In22 = MSUtils.getBits(pageBuffer,494,1,1,0);
        In23 = MSUtils.getBits(pageBuffer,494,2,2,0);
        In24 = MSUtils.getBits(pageBuffer,494,3,3,0);
        In25 = MSUtils.getBits(pageBuffer,494,4,4,0);
        In26 = MSUtils.getBits(pageBuffer,494,5,5,0);
        In27 = MSUtils.getBits(pageBuffer,494,6,6,0);
        In28 = MSUtils.getBits(pageBuffer,494,7,7,0);
        In29 = MSUtils.getBits(pageBuffer,494,8,8,0);
        In210 = MSUtils.getBits(pageBuffer,494,9,9,0);
        Input3 = (int)((MSUtils.getWord(pageBuffer,496) + 0.0) * 1.0);
        In31 = MSUtils.getBits(pageBuffer,496,0,0,0);
        In32 = MSUtils.getBits(pageBuffer,496,1,1,0);
        In33 = MSUtils.getBits(pageBuffer,496,2,2,0);
        In34 = MSUtils.getBits(pageBuffer,496,3,3,0);
        In35 = MSUtils.getBits(pageBuffer,496,4,4,0);
        In36 = MSUtils.getBits(pageBuffer,496,5,5,0);
        In37 = MSUtils.getBits(pageBuffer,496,6,6,0);
        In38 = MSUtils.getBits(pageBuffer,496,7,7,0);
        In39 = MSUtils.getBits(pageBuffer,496,8,8,0);
        In310 = MSUtils.getBits(pageBuffer,496,9,9,0);
        Input4 = (int)((MSUtils.getWord(pageBuffer,498) + 0.0) * 1.0);
        In41 = MSUtils.getBits(pageBuffer,498,0,0,0);
        In42 = MSUtils.getBits(pageBuffer,498,1,1,0);
        In43 = MSUtils.getBits(pageBuffer,498,2,2,0);
        In44 = MSUtils.getBits(pageBuffer,498,3,3,0);
        In45 = MSUtils.getBits(pageBuffer,498,4,4,0);
        In46 = MSUtils.getBits(pageBuffer,498,5,5,0);
        In47 = MSUtils.getBits(pageBuffer,498,6,6,0);
        In48 = MSUtils.getBits(pageBuffer,498,7,7,0);
        In49 = MSUtils.getBits(pageBuffer,498,8,8,0);
        In410 = MSUtils.getBits(pageBuffer,498,9,9,0);
        mlever_mode = MSUtils.getBits(pageBuffer,500,0,0,0);
        mlever_vP = (double)((MSUtils.getWord(pageBuffer,501) + 0.0) * 0.00489);
        mlever_vR = (double)((MSUtils.getWord(pageBuffer,503) + 0.0) * 0.00489);
        mlever_vN = (double)((MSUtils.getWord(pageBuffer,505) + 0.0) * 0.00489);
        mlever_v4 = (double)((MSUtils.getWord(pageBuffer,507) + 0.0) * 0.00489);
        mlever_v3 = (double)((MSUtils.getWord(pageBuffer,509) + 0.0) * 0.00489);
        mlever_v2 = (double)((MSUtils.getWord(pageBuffer,511) + 0.0) * 0.00489);
        mlever_v1 = (double)((MSUtils.getWord(pageBuffer,513) + 0.0) * 0.00489);
        swBDC_OD = MSUtils.getBits(pageBuffer,515,0,0,0);
        clutch_enable = MSUtils.getBits(pageBuffer,516,0,1,0);
        clutch11u = MSUtils.getBits(pageBuffer,517,0,0,0);
        clutch12u = MSUtils.getBits(pageBuffer,517,1,1,0);
        clutch13u = MSUtils.getBits(pageBuffer,517,2,2,0);
        clutch14u = MSUtils.getBits(pageBuffer,517,3,3,0);
        clutch15u = MSUtils.getBits(pageBuffer,517,4,4,0);
        clutch16u = MSUtils.getBits(pageBuffer,517,5,5,0);
        clutch17u = MSUtils.getBits(pageBuffer,517,6,6,0);
        clutch18u = MSUtils.getBits(pageBuffer,517,7,7,0);
        clutch21u = MSUtils.getBits(pageBuffer,518,0,0,0);
        clutch22u = MSUtils.getBits(pageBuffer,518,1,1,0);
        clutch23u = MSUtils.getBits(pageBuffer,518,2,2,0);
        clutch24u = MSUtils.getBits(pageBuffer,518,3,3,0);
        clutch25u = MSUtils.getBits(pageBuffer,518,4,4,0);
        clutch26u = MSUtils.getBits(pageBuffer,518,5,5,0);
        clutch27u = MSUtils.getBits(pageBuffer,518,6,6,0);
        clutch28u = MSUtils.getBits(pageBuffer,518,7,7,0);
        clutch11d = MSUtils.getBits(pageBuffer,519,0,0,0);
        clutch12d = MSUtils.getBits(pageBuffer,519,1,1,0);
        clutch13d = MSUtils.getBits(pageBuffer,519,2,2,0);
        clutch14d = MSUtils.getBits(pageBuffer,519,3,3,0);
        clutch15d = MSUtils.getBits(pageBuffer,519,4,4,0);
        clutch16d = MSUtils.getBits(pageBuffer,519,5,5,0);
        clutch17d = MSUtils.getBits(pageBuffer,519,6,6,0);
        clutch18d = MSUtils.getBits(pageBuffer,519,7,7,0);
        clutch21d = MSUtils.getBits(pageBuffer,520,0,0,0);
        clutch22d = MSUtils.getBits(pageBuffer,520,1,1,0);
        clutch23d = MSUtils.getBits(pageBuffer,520,2,2,0);
        clutch24d = MSUtils.getBits(pageBuffer,520,3,3,0);
        clutch25d = MSUtils.getBits(pageBuffer,520,4,4,0);
        clutch26d = MSUtils.getBits(pageBuffer,520,5,5,0);
        clutch27d = MSUtils.getBits(pageBuffer,520,6,6,0);
        clutch28d = MSUtils.getBits(pageBuffer,520,7,7,0);
        FWD_factor = (double)((MSUtils.getWord(pageBuffer,521) + 0.0) * 0.0010);
        upshift_retard = (double)((MSUtils.getSignedWord(pageBuffer,523) + 0.0) * -0.1);
        dwnshift_retard = (double)((MSUtils.getSignedWord(pageBuffer,525) + 0.0) * -0.1);
        gear2_retard = (double)((MSUtils.getSignedWord(pageBuffer,527) + 0.0) * -0.1);
        gear3_retard = (double)((MSUtils.getSignedWord(pageBuffer,529) + 0.0) * -0.1);
        gear4_retard = (double)((MSUtils.getSignedWord(pageBuffer,531) + 0.0) * -0.1);
        Tretard_load = (double)((MSUtils.getWord(pageBuffer,533) + 0.0) * 0.1);
        LUF_off = (int)((MSUtils.getByte(pageBuffer,535) + 0.0) * 1.0);
        LUF_loLoad = (double)((MSUtils.getWord(pageBuffer,536) + 0.0) * 0.1);
        LUF_hiLoad = (double)((MSUtils.getWord(pageBuffer,538) + 0.0) * 0.1);
        LUF_loPWM = (int)((MSUtils.getByte(pageBuffer,540) + 0.0) * 1.0);
        LUF_hiPWM = (int)((MSUtils.getByte(pageBuffer,541) + 0.0) * 1.0);
        digital_threshold = (double)((MSUtils.getWord(pageBuffer,542) + 0.0) * 0.00489);
        shftr_accum = (double)((MSUtils.getWord(pageBuffer,544) + 0.0) * 0.1);
        if (NOT_METRIC)
        {
        hyst_enable_speed = (double)((MSUtils.getWord(pageBuffer,546) + 0.0) * 0.1);
        }
        else
        {
        hyst_enable_speed = (double)((MSUtils.getWord(pageBuffer,546) + 0.0) * 0.1);
        }
        vss_mask = (int)((MSUtils.getByte(pageBuffer,548) + 0.0) * 1.0);
        stdin_cfg = (int)((MSUtils.getByte(pageBuffer,549) + 0.0) * 1.0);
        FWD_cfg = MSUtils.getBits(pageBuffer,549,0,0,0);
        TMP_cfg = MSUtils.getBits(pageBuffer,549,1,1,0);
        TMP_adc = MSUtils.getBits(pageBuffer,549,2,2,0);
        In4_cfg = MSUtils.getBits(pageBuffer,549,3,3,0);
        LUF_on = (int)((MSUtils.getByte(pageBuffer,550) + 0.0) * 1.0);
        if (NOT_METRIC)
        {
        min_speed = (double)((MSUtils.getByte(pageBuffer,551) + 0.0) * 0.1);
        }
        else
        {
        min_speed = (double)((MSUtils.getByte(pageBuffer,551) + 0.0) * 0.1);
        }
        vss_error_max = (int)((MSUtils.getWord(pageBuffer,552) + 0.0) * 1.0);
        line_units = MSUtils.getBits(pageBuffer,554,0,1,0);
        inj_flow = (double)((MSUtils.getWord(pageBuffer,555) + 0.0) * 0.01);
        open_time = (double)((MSUtils.getByte(pageBuffer,557) + 0.0) * 0.01);
        fuel_density = (double)((MSUtils.getWord(pageBuffer,558) + 0.0) * 0.0010);
        nSquirts = (int)((MSUtils.getByte(pageBuffer,560) + 0.0) * 1.0);
        refresh_int = (int)((MSUtils.getWord(pageBuffer,561) + 0.0) * 1.0);
        refresh_dur = (int)((MSUtils.getWord(pageBuffer,563) + 0.0) * 1.0);
        dither_intPC = (int)((MSUtils.getWord(pageBuffer,565) + 0.0) * 1.0);
        dither_durPC = (int)((MSUtils.getWord(pageBuffer,567) + 0.0) * 1.0);
        out12_pwm_off = (double)((MSUtils.getByte(pageBuffer,569) + 0.0) * 0.128);
        out12_pwm_on = (double)((MSUtils.getByte(pageBuffer,570) + 0.0) * 0.128);
        brake_ON_polarity = MSUtils.getBits(pageBuffer,571,0,0,0);
        WOT_tps_threshold = (double)((MSUtils.getWord(pageBuffer,572) + 0.0) * 0.0976);
        iss_mask = (int)((MSUtils.getByte(pageBuffer,574) + 0.0) * 1.0);
        ic2_usage = MSUtils.getBits(pageBuffer,575,0,1,0);
        ic2_edge = MSUtils.getBits(pageBuffer,575,7,7,0);
        iss_divider = (int)((MSUtils.getByte(pageBuffer,576) + 0.0) * 1.0);
        activate_xrate = (int)((MSUtils.getByte(pageBuffer,577) + 0.0) * 1.0);
        num_gears = (int)((MSUtils.getByte(pageBuffer,578) + 0.0) * 1.0);
        gear5_retard = (double)((MSUtils.getSignedWord(pageBuffer,579) + 0.0) * -0.1);
        gear6_retard = (double)((MSUtils.getSignedWord(pageBuffer,581) + 0.0) * -0.1);
        gear7_retard = (double)((MSUtils.getSignedWord(pageBuffer,583) + 0.0) * -0.1);
        gear8_retard = (double)((MSUtils.getSignedWord(pageBuffer,585) + 0.0) * -0.1);
        mlever_v5 = (double)((MSUtils.getWord(pageBuffer,587) + 0.0) * 0.00489);
        mlever_v6 = (double)((MSUtils.getWord(pageBuffer,589) + 0.0) * 0.00489);
        mlever_v7 = (double)((MSUtils.getWord(pageBuffer,591) + 0.0) * 0.00489);
        mlever_v8 = (double)((MSUtils.getWord(pageBuffer,593) + 0.0) * 0.00489);
        Adj_blk = (int)((MSUtils.getByte(pageBuffer,595) + 0.0) * 1.0);
        Adj_varoffset = (int)((MSUtils.getWord(pageBuffer,596) + 0.0) * 1.0);
        stdout_cfg = MSUtils.getBits(pageBuffer,598,0,0,0);
        LED11 = MSUtils.getBits(pageBuffer,599,0,0,0);
        LED12 = MSUtils.getBits(pageBuffer,599,1,1,0);
        LED13 = MSUtils.getBits(pageBuffer,599,2,2,0);
        LED14 = MSUtils.getBits(pageBuffer,599,3,3,0);
        LED15 = MSUtils.getBits(pageBuffer,599,4,4,0);
        LED16 = MSUtils.getBits(pageBuffer,599,5,5,0);
        LED17 = MSUtils.getBits(pageBuffer,599,6,6,0);
        LED18 = MSUtils.getBits(pageBuffer,599,7,7,0);
        LED21 = MSUtils.getBits(pageBuffer,600,0,0,0);
        LED22 = MSUtils.getBits(pageBuffer,600,1,1,0);
        LED23 = MSUtils.getBits(pageBuffer,600,2,2,0);
        LED24 = MSUtils.getBits(pageBuffer,600,3,3,0);
        LED25 = MSUtils.getBits(pageBuffer,600,4,4,0);
        LED26 = MSUtils.getBits(pageBuffer,600,5,5,0);
        LED27 = MSUtils.getBits(pageBuffer,600,6,6,0);
        LED28 = MSUtils.getBits(pageBuffer,601,7,7,0);
        LED31 = MSUtils.getBits(pageBuffer,601,0,0,0);
        LED32 = MSUtils.getBits(pageBuffer,601,1,1,0);
        LED33 = MSUtils.getBits(pageBuffer,601,2,2,0);
        LED34 = MSUtils.getBits(pageBuffer,601,3,3,0);
        LED35 = MSUtils.getBits(pageBuffer,601,4,4,0);
        LED36 = MSUtils.getBits(pageBuffer,601,5,5,0);
        LED37 = MSUtils.getBits(pageBuffer,601,6,6,0);
        LED38 = MSUtils.getBits(pageBuffer,601,7,7,0);
        LED41 = MSUtils.getBits(pageBuffer,602,0,0,0);
        LED42 = MSUtils.getBits(pageBuffer,602,1,1,0);
        LED43 = MSUtils.getBits(pageBuffer,602,2,2,0);
        LED44 = MSUtils.getBits(pageBuffer,602,3,3,0);
        LED45 = MSUtils.getBits(pageBuffer,602,4,4,0);
        LED46 = MSUtils.getBits(pageBuffer,602,5,5,0);
        LED47 = MSUtils.getBits(pageBuffer,602,6,6,0);
        LED48 = MSUtils.getBits(pageBuffer,602,7,7,0);
        LEDN1 = MSUtils.getBits(pageBuffer,603,0,0,0);
        LEDN2 = MSUtils.getBits(pageBuffer,603,1,1,0);
        LEDN3 = MSUtils.getBits(pageBuffer,603,2,2,0);
        LEDN4 = MSUtils.getBits(pageBuffer,603,3,3,0);
        LEDR1 = MSUtils.getBits(pageBuffer,604,0,0,0);
        LEDR2 = MSUtils.getBits(pageBuffer,604,1,1,0);
        LEDR3 = MSUtils.getBits(pageBuffer,604,2,2,0);
        LEDR4 = MSUtils.getBits(pageBuffer,604,3,3,0);
        LEDR1alt = MSUtils.getBits(pageBuffer,604,4,4,0);
        LEDR2alt = MSUtils.getBits(pageBuffer,604,5,5,0);
        LEDR3alt = MSUtils.getBits(pageBuffer,604,6,6,0);
        LEDR4alt = MSUtils.getBits(pageBuffer,604,7,7,0);
        IACAdjngear = (double)((MSUtils.getSignedWord(pageBuffer,605) + 0.0) * -1.0);
        AdvAdjngear = (double)((MSUtils.getSignedWord(pageBuffer,607) + 0.0) * 0.1);
        idle_tps_adc = (double)((MSUtils.getWord(pageBuffer,609) + 0.0) * 0.0976);
        can_testout = MSUtils.getBits(pageBuffer,611,0,0,0);
        if (NOT_METRIC)
        {
        min_manual_speed = (double)((MSUtils.getSignedWord(pageBuffer,612) + 0.0) * 0.1);
        }
        else
        {
        min_manual_speed = (double)((MSUtils.getSignedWord(pageBuffer,612) + 0.0) * 0.1);
        }
        shft_btn_polarity = MSUtils.getBits(pageBuffer,614,0,0,0);
        TCC_PWM = MSUtils.getBits(pageBuffer,615,0,0,0);
        spareInputs = MSUtils.getBits(pageBuffer,616,0,1,0);
        Output5 = (int)((MSUtils.getWord(pageBuffer,617) + 0.0) * 1.0);
        Out51 = MSUtils.getBits(pageBuffer,617,0,0,0);
        Out52 = MSUtils.getBits(pageBuffer,617,1,1,0);
        Out53 = MSUtils.getBits(pageBuffer,617,2,2,0);
        Out54 = MSUtils.getBits(pageBuffer,617,3,3,0);
        Out55 = MSUtils.getBits(pageBuffer,617,4,4,0);
        Out56 = MSUtils.getBits(pageBuffer,617,5,5,0);
        Out57 = MSUtils.getBits(pageBuffer,617,6,6,0);
        Out58 = MSUtils.getBits(pageBuffer,617,7,7,0);
        Out59 = MSUtils.getBits(pageBuffer,617,8,8,0);
        Out510 = MSUtils.getBits(pageBuffer,617,9,9,0);
        Output6 = (int)((MSUtils.getWord(pageBuffer,619) + 0.0) * 1.0);
        Out61 = MSUtils.getBits(pageBuffer,619,0,0,0);
        Out62 = MSUtils.getBits(pageBuffer,619,1,1,0);
        Out63 = MSUtils.getBits(pageBuffer,619,2,2,0);
        Out64 = MSUtils.getBits(pageBuffer,619,3,3,0);
        Out65 = MSUtils.getBits(pageBuffer,619,4,4,0);
        Out66 = MSUtils.getBits(pageBuffer,619,5,5,0);
        Out67 = MSUtils.getBits(pageBuffer,619,6,6,0);
        Out68 = MSUtils.getBits(pageBuffer,619,7,7,0);
        Out69 = MSUtils.getBits(pageBuffer,619,8,8,0);
        Out610 = MSUtils.getBits(pageBuffer,619,9,9,0);
        PWM_enable = (double)((MSUtils.getByte(pageBuffer,621) + 1.0) * 0.0);
        PWM_enable1 = MSUtils.getBits(pageBuffer,621,0,0,0);
        PWM_enable2 = MSUtils.getBits(pageBuffer,621,1,1,0);
        PWM_enable3 = MSUtils.getBits(pageBuffer,621,2,2,0);
        PWM_enable4 = MSUtils.getBits(pageBuffer,621,3,3,0);
        PWM_enable5 = MSUtils.getBits(pageBuffer,621,4,4,0);
        PWM_enable6 = MSUtils.getBits(pageBuffer,621,5,5,0);
        if (NOT_METRIC)
        {
        maxSpeed = (double)((MSUtils.getSignedWord(pageBuffer,622) + 0.0) * 0.1);
        }
        else
        {
        maxSpeed = (double)((MSUtils.getSignedWord(pageBuffer,622) + 0.0) * 0.1);
        }
        resetTime = (double)((MSUtils.getByte(pageBuffer,624) + 0.0) * 0.1);
        TCC_counter = (double)((MSUtils.getByte(pageBuffer,625) + 0.0) * 0.1);
        STCC_rpm = (int)((MSUtils.getWord(pageBuffer,626) + 0.0) * 1.0);
        STCC_gear = (int)((MSUtils.getByte(pageBuffer,628) + 0.0) * 1.0);
        STCC_ULrpm = (int)((MSUtils.getWord(pageBuffer,629) + 0.0) * 1.0);
        VSSsmoothFactor = (int)((MSUtils.getByte(pageBuffer,631) + 0.0) * 1.0);
        WOT_dur = (int)((MSUtils.getByte(pageBuffer,632) + 0.0) * 1.0);
        pageBuffer = loadPage(2,0,30,null,new byte[]{114,1,5,0,0,0,30});
        spare21 = (int)((MSUtils.getSignedWord(pageBuffer,0) + 0.0) * 1.0);
        spare22 = (int)((MSUtils.getSignedWord(pageBuffer,2) + 0.0) * 1.0);
        spare23 = (int)((MSUtils.getSignedWord(pageBuffer,4) + 0.0) * 1.0);
        spare24 = (int)((MSUtils.getSignedWord(pageBuffer,6) + 0.0) * 1.0);
        spare25 = (int)((MSUtils.getSignedWord(pageBuffer,8) + 0.0) * 1.0);
        spare26 = (int)((MSUtils.getSignedWord(pageBuffer,10) + 0.0) * 1.0);
        spare27 = (int)((MSUtils.getSignedWord(pageBuffer,12) + 0.0) * 1.0);
        spare28 = (int)((MSUtils.getSignedWord(pageBuffer,14) + 0.0) * 1.0);
        spare29 = (int)((MSUtils.getSignedWord(pageBuffer,16) + 0.0) * 1.0);
        spare210 = (int)((MSUtils.getSignedWord(pageBuffer,18) + 0.0) * 1.0);
        spare211 = (int)((MSUtils.getSignedWord(pageBuffer,20) + 0.0) * 1.0);
        spare212 = (int)((MSUtils.getSignedWord(pageBuffer,22) + 0.0) * 1.0);
        spare213 = (int)((MSUtils.getSignedWord(pageBuffer,24) + 0.0) * 1.0);
        spare214 = (int)((MSUtils.getSignedWord(pageBuffer,26) + 0.0) * 1.0);
        spare215 = (int)((MSUtils.getSignedWord(pageBuffer,28) + 0.0) * 1.0);
    }
    @Override
    public DataPacket getDataPacket()
    {
        fields.put("FWD",(double)FWD);
        fields.put("FuelAdj",(double)FuelAdj);
        fields.put("IdleAdj",(double)IdleAdj);
        fields.put("LOAD",(double)LOAD);
        fields.put("LOAD_short",(double)LOAD_short);
        fields.put("Output1",(double)Output1);
        fields.put("Output2",(double)Output2);
        fields.put("Output3",(double)Output3);
        fields.put("Output4",(double)Output4);
        fields.put("PC_duty",(double)PC_duty);
        fields.put("SpkAdj",(double)SpkAdj);
        fields.put("SprAdj",(double)SprAdj);
        fields.put("VSS_Hz",(double)VSS_Hz);
        fields.put("WOT_flag",(double)WOT_flag);
        fields.put("auto_mode",(double)auto_mode);
        fields.put("aux_volts",(double)aux_volts);
        fields.put("brake",(double)brake);
        fields.put("cksum",(double)cksum);
        fields.put("clt",(double)clt);
        fields.put("converter_slip",(double)converter_slip);
        fields.put("current_gear",(double)current_gear);
        fields.put("dbug",(double)dbug);
        fields.put("deadValue",(double)deadValue);
        fields.put("downbutton",(double)downbutton);
        fields.put("downshift_request",(double)downshift_request);
        fields.put("engine_rpm",(double)engine_rpm);
        fields.put("error",(double)error);
        fields.put("flashBurn",(double)flashBurn);
        fields.put("ic1_period",(double)ic1_period);
        fields.put("ic2_period",(double)ic2_period);
        fields.put("is_rpm",(double)is_rpm);
        fields.put("kmileage",(double)kmileage);
        fields.put("linepressure",(double)linepressure);
        fields.put("lock_TCC",(double)lock_TCC);
        fields.put("manual_gear",(double)manual_gear);
        fields.put("mileage",(double)mileage);
        fields.put("odometer",(double)odometer);
        fields.put("os_rpm",(double)os_rpm);
        fields.put("secl",(double)secl);
        fields.put("second2",(double)second2);
        fields.put("seconds",(double)seconds);
        fields.put("solst",(double)solst);
        fields.put("sp1",(double)sp1);
        fields.put("sp2",(double)sp2);
        fields.put("speedo",(double)speedo);
        fields.put("swA",(double)swA);
        fields.put("swADC",(double)swADC);
        fields.put("swB",(double)swB);
        fields.put("swBDC",(double)swBDC);
        fields.put("swC",(double)swC);
        fields.put("swCDC",(double)swCDC);
        fields.put("swD",(double)swD);
        fields.put("target_gear",(double)target_gear);
        fields.put("time",(double)time);
        fields.put("upbutton",(double)upbutton);
        fields.put("upshift_request",(double)upshift_request);
        return new DataPacket(fields,ochBuffer);
    };

}

