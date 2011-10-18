package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;

public class MS2Extra310 extends Megasquirt
{
	private static final String MS2_EXTRA_SERIAL310 = "MS2Extra Serial310 ";
    private byte[]	ochCommand	= { 65 };
	private byte[]	sigCommand	= { 81 };

	public MS2Extra310(Context c)
	{
		super(c);
		NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
		LAMBDA = isSet("LAMBDA");
		EGTFULL = isSet("EGTFULL");
		CELSIUS = isSet("CELSIUS");
	}

	int				seconds;
	int				pulseWidth1;
	int				pulseWidth2;
	int				rpm;
	int				advance;
	int				squirt;
	int				firing1;
	int				firing2;
	int				sched1;
	int				inj1;
	int				sched2;
	int				inj2;
	int				engine;
	int				ready;
	int				crank;
	int				startw;
	int				warmup;
	int				tpsaccaen;
	int				tpsaccden;
	int				mapaccaen;
	int				mapaccden;
	int				afrtgt1;
	int				afrtgt2;
	int				wbo2_en1;
	int				wbo2_en2;
	int				barometer;
	int				map;
	int				mat;
	int				coolant;
	int				tps;
	int				batteryVoltage;
	int				afr1;
	int				afr2;
	int				knock;
	int				egoCorrection1;
	int				egoCorrection2;
	int				airCorrection;
	int				warmupEnrich;
	int				accelEnrich;
	int				tpsfuelcut;
	int				baroCorrection;
	int				gammaEnrich;
	int				veCurr1;
	int				veCurr2;
	int				iacstep;
	int				idleDC;
	int				coldAdvDeg;
	int				tpsDOT;
	int				mapDOT;
	int				dwell;
	int				mafmap;
	int				fuelload;
	int				fuelCorrection;
	int				portStatus;
	int				port0;
	int				port1;
	int				port2;
	int				port3;
	int				port4;
	int				port5;
	int				port6;
	int				knockRetard;
	int				EAEFuelCorr1;
	int				egoV;
	int				egoV2;
	int				status1;
	int				status2;
	int				status3;
	int				status4;
	int				looptime;
	int				status5;
	int				tpsADC;
	int				fuelload2;
	int				ignload;
	int				ignload2;
	int				synccnt;
	int				timing_err;
	int				deltaT;
	int				wallfuel1;
	int				gpioadc0;
	int				gpioadc1;
	int				gpioadc2;
	int				gpioadc3;
	int				gpioadc4;
	int				gpioadc5;
	int				gpioadc6;
	int				gpioadc7;
	int				gpiopwmin0;
	int				gpiopwmin1;
	int				gpiopwmin2;
	int				gpiopwmin3;
	int				adc6;
	int				adc7;
	int				wallfuel2;
	int				EAEFuelCorr2;
	int				boostduty;
	int				syncreason;
	int				user0;
	int				inj_adv1;
	int				inj_adv2;
	int				pulseWidth3;
	int				pulseWidth4;
	int				vetrim1curr;
	int				vetrim2curr;
	int				vetrim3curr;
	int				vetrim4curr;
	int				maf;
	int				eaeload1;
	int				afrload1;
	int				rpmdot;
	int				gpioport0;
	int				gpioport1;
	int				gpioport2;
	// Flags
	private boolean	NARROW_BAND_EGO;
	private boolean	LAMBDA;
	private boolean	EGTFULL;
	private boolean	CELSIUS;
	// Runtime calcs
	int				accDecEnrich;
	double			time;
	double			rpm100;
	int				altDiv1;
	int				altDiv2;
	double			cycleTime1;
	int				nSquirts1;
	double			dutyCycle1;
	double			cycleTime2;
	int				nSquirts2;
	double			dutyCycle2;
	int				egoVoltage;
	int				lambda1;
	double			egt6temp;
	double			egt7temp;
	int				egoCorrection;
	// Runtime constants
	int				alternate;
	private double	twoStroke;
	private int		nCylinders;
	private int		divider;
    private Set<String> sigs = new HashSet<String>(Arrays.asList(new String[]{MS2_EXTRA_SERIAL310}));

	@Override
	public Set<String> getSignature()
	{
		return sigs ;
	}

	@Override
	public byte[] getOchCommand()
	{
		return ochCommand;
	}

	@Override
	public byte[] getSigCommand()
	{
		return sigCommand;
	}

	@Override
	public void loadConstants(boolean simulated) throws IOException
	{
		if (simulated)
		{
			alternate = 1;
			twoStroke = 0;
			nCylinders = 8;
			divider = 1;
		}
		else
		{
			byte[] pageBuffer = new byte[1024];

			byte[] selectPage1 = { 114, 0, 4, 0, 0, 4, 0 };

			getPage(pageBuffer, selectPage1, null);
			alternate = MSUtils.getBits(pageBuffer, 611, 0, 0);
			twoStroke = MSUtils.getBits(pageBuffer, 617, 0, 0);
			nCylinders = MSUtils.getBits(pageBuffer, 0, 0, 4);
			divider = MSUtils.getByte(pageBuffer, 610);
		}
	}

	@Override
	public void calculate(byte[] ochBuffer) throws IOException
	{
		setupRuntime(ochBuffer);

		accDecEnrich = (accelEnrich + ((tpsaccden != 0) ? tpsfuelcut : 100));
		time = (timeNow());
		rpm100 = (rpm / 100.0);

		altDiv1 = ((alternate != 0) ? 2 : 1);
		altDiv2 = ((alternate != 0) ? 2 : 1);

		cycleTime1 = (60000.0 / rpm * (2.0 - twoStroke));
		nSquirts1 = (nCylinders / divider);
		dutyCycle1 = (100.0 * nSquirts1 / altDiv1 * pulseWidth1 / cycleTime1);

		cycleTime2 = (60000.0 / rpm * (2.0 - twoStroke));
		nSquirts2 = (nCylinders / divider);
		dutyCycle2 = (100.0 * nSquirts2 / altDiv2 * pulseWidth2 / cycleTime2);
		egoCorrection = (egoCorrection1 + egoCorrection2) / 2;

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

		// note these conversions are based on the AD595CQ datasheet for ANSI
		// thermocouples.
		// European thermocouples may require a different calculation
		// The K type thermocouple output is not precisely linear, so these
		// calculations are an approximation.
		// 0degC is close enough to 0V
		// With the 15K/10K circuit. 1250degC would apply 5.01V to the adc and
		// result in '1025ADC counts' if that was possible
		// So temp = adc/1025 * 1250 or adc * 1.222
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
		// ; normal 0-1000 range
		// ; With the 10K/10K circuit. 1000degC would apply 5.10V to the adc and
		// result in '1044ADC counts' if that was possible
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

	void setupRuntime(byte[] ochBuffer)
	{
		seconds = MSUtils.getWord(ochBuffer, 0);
		pulseWidth1 = MSUtils.getWord(ochBuffer, 2);
		pulseWidth2 = MSUtils.getWord(ochBuffer, 4);

		rpm = MSUtils.getWord(ochBuffer, 6);
		advance = MSUtils.getSignedWord(ochBuffer, 8);

		squirt = MSUtils.getByte(ochBuffer, 10);
		firing1 = MSUtils.getBits(ochBuffer, 10, 0, 0);
		firing2 = MSUtils.getBits(ochBuffer, 10, 1, 1);
		sched1 = MSUtils.getBits(ochBuffer, 10, 2, 2);
		inj1 = MSUtils.getBits(ochBuffer, 10, 3, 3);
		sched2 = MSUtils.getBits(ochBuffer, 10, 4, 4);
		inj2 = MSUtils.getBits(ochBuffer, 10, 5, 5);

		engine = MSUtils.getByte(ochBuffer, 11);
		ready = MSUtils.getBits(ochBuffer, 11, 0, 0);
		crank = MSUtils.getBits(ochBuffer, 11, 1, 1);
		startw = MSUtils.getBits(ochBuffer, 11, 2, 2);
		warmup = MSUtils.getBits(ochBuffer, 11, 3, 3);
		tpsaccaen = MSUtils.getBits(ochBuffer, 11, 4, 4);
		tpsaccden = MSUtils.getBits(ochBuffer, 11, 5, 5);
		mapaccaen = MSUtils.getBits(ochBuffer, 11, 6, 6);
		mapaccden = MSUtils.getBits(ochBuffer, 11, 7, 7);

		afrtgt1 = MSUtils.getByte(ochBuffer, 12);
		afrtgt2 = MSUtils.getByte(ochBuffer, 13);

		wbo2_en1 = MSUtils.getByte(ochBuffer, 14);
		wbo2_en2 = MSUtils.getByte(ochBuffer, 15);

		barometer = MSUtils.getSignedWord(ochBuffer, 16);
		map = MSUtils.getSignedWord(ochBuffer, 18);
		mat = MSUtils.getSignedWord(ochBuffer, 20);
		coolant = MSUtils.getSignedWord(ochBuffer, 22);
		tps = MSUtils.getSignedWord(ochBuffer, 24);
		batteryVoltage = MSUtils.getSignedWord(ochBuffer, 26);
		afr1 = MSUtils.getSignedWord(ochBuffer, 28);
		afr2 = MSUtils.getSignedWord(ochBuffer, 30);
		knock = MSUtils.getSignedWord(ochBuffer, 32);

		egoCorrection1 = MSUtils.getSignedWord(ochBuffer, 34);
		egoCorrection2 = MSUtils.getSignedWord(ochBuffer, 36);
		airCorrection = MSUtils.getSignedWord(ochBuffer, 38);
		warmupEnrich = MSUtils.getSignedWord(ochBuffer, 40);

		accelEnrich = MSUtils.getSignedWord(ochBuffer, 42);
		tpsfuelcut = MSUtils.getSignedWord(ochBuffer, 44);
		baroCorrection = MSUtils.getSignedWord(ochBuffer, 46);
		gammaEnrich = MSUtils.getSignedWord(ochBuffer, 48);

		veCurr1 = MSUtils.getSignedWord(ochBuffer, 50);
		veCurr2 = MSUtils.getSignedWord(ochBuffer, 52);
		iacstep = MSUtils.getSignedWord(ochBuffer, 54);
		idleDC = MSUtils.getSignedWord(ochBuffer, 54);
		coldAdvDeg = MSUtils.getSignedWord(ochBuffer, 56);
		tpsDOT = MSUtils.getSignedWord(ochBuffer, 58);
		mapDOT = MSUtils.getSignedWord(ochBuffer, 60);
		dwell = MSUtils.getWord(ochBuffer, 62);
		mafmap = MSUtils.getSignedWord(ochBuffer, 64);
		fuelload = MSUtils.getSignedWord(ochBuffer, 66);
		fuelCorrection = MSUtils.getSignedWord(ochBuffer, 68);

		portStatus = MSUtils.getByte(ochBuffer, 70);
		port0 = MSUtils.getBits(ochBuffer, 70, 0, 0);
		port1 = MSUtils.getBits(ochBuffer, 70, 1, 1);
		port2 = MSUtils.getBits(ochBuffer, 70, 2, 2);
		port3 = MSUtils.getBits(ochBuffer, 70, 3, 3);
		port4 = MSUtils.getBits(ochBuffer, 70, 4, 4);
		port5 = MSUtils.getBits(ochBuffer, 70, 5, 5);
		port6 = MSUtils.getBits(ochBuffer, 70, 6, 6);

		knockRetard = MSUtils.getByte(ochBuffer, 71);
		EAEFuelCorr1 = MSUtils.getWord(ochBuffer, 72);
		egoV = MSUtils.getSignedWord(ochBuffer, 74);
		egoV2 = MSUtils.getSignedWord(ochBuffer, 76);
		status1 = MSUtils.getByte(ochBuffer, 78);
		status2 = MSUtils.getByte(ochBuffer, 79);
		status3 = MSUtils.getByte(ochBuffer, 80);
		status4 = MSUtils.getByte(ochBuffer, 81);
		looptime = MSUtils.getWord(ochBuffer, 82);
		status5 = MSUtils.getWord(ochBuffer, 84);
		tpsADC = MSUtils.getWord(ochBuffer, 86);
		fuelload2 = MSUtils.getSignedWord(ochBuffer, 88);
		ignload = MSUtils.getSignedWord(ochBuffer, 90);
		ignload2 = MSUtils.getSignedWord(ochBuffer, 92);
		synccnt = MSUtils.getByte(ochBuffer, 94);
		timing_err = MSUtils.getSignedByte(ochBuffer, 95);
		deltaT = MSUtils.getSignedLong(ochBuffer, 96);
		wallfuel1 = MSUtils.getLong(ochBuffer, 100);

		gpioadc0 = MSUtils.getWord(ochBuffer, 104);
		gpioadc1 = MSUtils.getWord(ochBuffer, 106);
		gpioadc2 = MSUtils.getWord(ochBuffer, 108);
		gpioadc3 = MSUtils.getWord(ochBuffer, 110);
		gpioadc4 = MSUtils.getWord(ochBuffer, 112);
		gpioadc5 = MSUtils.getWord(ochBuffer, 114);
		gpioadc6 = MSUtils.getWord(ochBuffer, 116);
		gpioadc7 = MSUtils.getWord(ochBuffer, 118);

		gpiopwmin0 = MSUtils.getWord(ochBuffer, 120);
		gpiopwmin1 = MSUtils.getWord(ochBuffer, 122);
		gpiopwmin2 = MSUtils.getWord(ochBuffer, 124);
		gpiopwmin3 = MSUtils.getWord(ochBuffer, 126);
		adc6 = MSUtils.getWord(ochBuffer, 128);
		adc7 = MSUtils.getWord(ochBuffer, 130);
		wallfuel2 = MSUtils.getLong(ochBuffer, 132);
		EAEFuelCorr2 = MSUtils.getWord(ochBuffer, 136);
		boostduty = MSUtils.getByte(ochBuffer, 138);
		syncreason = MSUtils.getByte(ochBuffer, 139);
		user0 = MSUtils.getWord(ochBuffer, 140);

		inj_adv1 = MSUtils.getSignedWord(ochBuffer, 142);
		inj_adv2 = MSUtils.getSignedWord(ochBuffer, 144);
		pulseWidth3 = MSUtils.getWord(ochBuffer, 146);
		pulseWidth4 = MSUtils.getWord(ochBuffer, 148);
		vetrim1curr = MSUtils.getSignedWord(ochBuffer, 150);
		vetrim2curr = MSUtils.getSignedWord(ochBuffer, 152);
		vetrim3curr = MSUtils.getSignedWord(ochBuffer, 154);
		vetrim4curr = MSUtils.getSignedWord(ochBuffer, 156);
		maf = MSUtils.getWord(ochBuffer, 158);
		eaeload1 = MSUtils.getSignedWord(ochBuffer, 160);
		afrload1 = MSUtils.getSignedWord(ochBuffer, 162);
		rpmdot = MSUtils.getSignedWord(ochBuffer, 164);

		gpioport0 = MSUtils.getByte(ochBuffer, 166);
		gpioport1 = MSUtils.getByte(ochBuffer, 167);
		gpioport2 = MSUtils.getByte(ochBuffer, 168);

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
		b.append("RPMdot").append(MSUtils.getLocationLogHeader());

		return b.toString();
	}

	@Override
	public String getLogRow()
	{
		StringBuffer b = new StringBuffer();
		b.append(time).append("\t");
		b.append(seconds).append("\t");
		b.append(rpm).append("\t");
		b.append(map).append("\t");
		b.append(tps).append("\t");
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
			b.append(afr1).append("\t");
		}

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
		b.append(pulseWidth1).append("\t");
		b.append(dutyCycle1).append("\t");

		b.append(veCurr2).append("\t");
		b.append(pulseWidth2).append("\t");
		b.append(dutyCycle2).append("\t");

		b.append(advance).append("\t");
		b.append(knockRetard).append("\t");
		b.append(coldAdvDeg).append("\t");
		b.append(dwell).append("\t");
		b.append(tpsDOT).append("\t");
		b.append(mapDOT).append("\t");
		b.append(iacstep).append("\t");

		b.append(batteryVoltage).append("\t");

		b.append(deltaT).append("\t");
		b.append(wallfuel1).append("\t");
		b.append(wallfuel2).append("\t");
		b.append(EAEFuelCorr1).append("\t");
		b.append(EAEFuelCorr2).append("\t");
		b.append(fuelload).append("\t");
		b.append(fuelload2).append("\t");
		b.append(ignload).append("\t");
		b.append(ignload2).append("\t");
		b.append(eaeload1).append("\t");
		b.append(afrload1).append("\t");
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
		b.append(timing_err).append("\t");
		b.append(afrtgt1).append("\t");
		b.append(boostduty).append("\t");
		b.append(idleDC).append("\t");
		b.append(synccnt).append("\t");
		b.append(syncreason).append("\t");

		b.append(inj_adv1).append("\t");
		b.append(inj_adv2).append("\t");
		b.append(pulseWidth3).append("\t");
		b.append(pulseWidth4).append("\t");
		b.append(vetrim1curr).append("\t");
		b.append(vetrim2curr).append("\t");
		b.append(vetrim3curr).append("\t");
		b.append(vetrim4curr).append("\t");
		b.append(rpmdot).append(MSUtils.getLocationLogRow());
		return b.toString();
	}

	@Override
	public int getBlockSize()
	{
		return 169;
	}

	@Override
	public int getPageActivationDelay()
	{
		return 50;
	}

	@Override
	public int getInterWriteDelay()
	{
		return 5;
	}

	@Override
	public int getCurrentTPS()
	{
		return tpsADC;
	}

    @Override
    public int getSigSize()
    {
        return MS2_EXTRA_SERIAL310.length();
    }

}
