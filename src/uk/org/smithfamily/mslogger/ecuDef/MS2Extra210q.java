package uk.org.smithfamily.mslogger.ecuDef;

import uk.org.smithfamily.mslogger.comms.LostCommsException;
import android.content.Context;

public class MS2Extra210q extends Megasquirt
{

	private byte[]	ochCommand	= { 65 };
	private byte[]	sigCommand	= { 81 };

	// Runtime vars
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
	int				maf;
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
	int				gpioport0;
	int				gpioport1;
	int				gpioport2;
	int				psaccaen;
	int				psaccden;
	int				ps;
	int				psfuelcut;
	int				psDOT;
	int				psADC;
	int				iming_err;

	// Expressions
	int				secl;
	int				throttle;
	int				pulseWidth;
	double			lambda1;
	double			lambda2;
	int				egoCorrection;
	int				veCurr;
	Object			accDecEnrich;
	long			time;
	double			rpm100;
	double			altDiv1;
	double			cycleTime1;
	double			nSquirts1;
	double			dutyCycle1;
	double			cycleTime2;
	double			nSquirts2;
	double			dutyCycle2;
	double			egoVoltage;
	double			egt6temp;
	double			egt7temp;
	double			altDiv2;

	// Constants
	int				alternate;
	double			twoStroke;
	int				nCylinders;
	int				divider;

	public MS2Extra210q(Context c)
	{
		super(c);
	}

	@Override
	public String getSignature()
	{
		return "MS2Extra Rel 2.1.0q";
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
	public void loadConstants(boolean simulated) throws LostCommsException
	{

		if (simulated)
		{
			alternate=1;
			twoStroke=0;
			nCylinders=8;
			divider=1;
		}
		else
		{
			byte[] pageBuffer = new byte[1024];

			byte[] selectPage1 = { 114, 0, 4, 0, 0, 4, 0 };

			getPage(pageBuffer, selectPage1, null);
			alternate = getBits(pageBuffer, 611, 0, 0);
			twoStroke = getBits(pageBuffer, 617, 0, 0);
			nCylinders = getBits(pageBuffer, 0, 0, 3);
			divider = getByte(pageBuffer, 610);
		}
	}

	@Override
	public void calculate(byte[] ochBuffer) throws LostCommsException
	{
		setupRuntime(ochBuffer);
		throttle = tps;
		secl = seconds % 256;// , "s" ; For runtime screen.
		pulseWidth = pulseWidth1;// , "s" ; For runtime screen.
		lambda1 = afr1 / 14.7;// , "Lambda"
		lambda2 = afr2 / 14.7;// , "Lambda"
		egoCorrection = (egoCorrection1 + egoCorrection2) / 2;// , "%" ; Alias
																// for old
																// gauges.
		veCurr = veCurr1;// , "%" ; For runtime display.
		accDecEnrich = accelEnrich + ((tpsaccden != 0) ? tpsfuelcut : 100);// ,
																			// "%"
		time = System.currentTimeMillis();// , "s"
		rpm100 = rpm / 100.0;//

		altDiv1 = (alternate != 0) ? 2 : 1;//
		altDiv2 = (alternate != 0) ? 2 : 1;//

		cycleTime1 = 60000.0 / rpm * (2.0 - twoStroke);// , "ms"
		nSquirts1 = nCylinders / divider;//
		dutyCycle1 = 100.0 * nSquirts1 / altDiv1 * pulseWidth1 / cycleTime1;// ,
																			// "%"

		cycleTime2 = 60000.0 / rpm * (2.0 - twoStroke);// , "ms"
		nSquirts2 = nCylinders / divider;//
		dutyCycle2 = 100.0 * nSquirts2 / altDiv2 * pulseWidth2 / cycleTime2;// ,
																			// "%"

		if (isSet("NARROW_BAND_EGO"))
		{
			egoVoltage = 1.0 - (afr1 * 0.04883);// , "V" ; For LED bars...

		}
		else if (isSet("LAMBDA"))
		{
			egoVoltage = lambda1;// , "Lambda" ; For LED bars...
		}
		else
		{
			egoVoltage = afr1;// , "AFR" ; For LED bars...

		}
		if (isSet("EGTFULL"))
		{
			if (isSet("CELSIUS"))
			{
				egt6temp = adc6 * 1.222;// ; Setup for converting 0-5.01V = 0 -
										// 1250C

				egt7temp = adc7 * 1.222;// ; Setup for converting 0-5.01V = 0 -
										// 1250C
			}
			else
			{
				egt6temp = (adc6 * 2.200) + 32;// ; Setup for converting 0-5.01V
												// = 32 - 2282F
				egt7temp = (adc7 * 2.200) + 32;// ; Setup for converting 0-5.01V
												// = 32 - 2282F
			}
		}
		else
		// normal 0-1000 range
		// With the 10K/10K circuit. 1000degC would apply 5.10V to the adc and
		// result in '1044ADC counts' if that was possible
		{
			if (isSet("CELSIUS"))
			{
				egt6temp = adc6 * 0.956;// ; Setup for converting 0-5.10V = 0 -
										// 1000C
				egt7temp = adc7 * 0.956;// ; Setup for converting 0-5.10V = 0 -
										// 1000C
			}
			else
			{
				egt6temp = (adc6 * 1.721) + 32;// ; Setup for converting 0-5.10V
												// = 32 - 1832F

				egt7temp = (adc7 * 1.721) + 32;// ; Setup for converting 0-5.10V
												// = 32 - 1832F
			}
		}

	}

	private void setupRuntime(byte[] ochBuffer)
	{
		seconds = getWord(ochBuffer, 0);
		pulseWidth1 = getWord(ochBuffer, 2);
		pulseWidth2 = getWord(ochBuffer, 4);

		rpm = getWord(ochBuffer, 6);
		advance = getSignedWord(ochBuffer, 8);

		squirt = getByte(ochBuffer, 10);
		firing1 = getBits(ochBuffer, 10, 0, 0);
		firing2 = getBits(ochBuffer, 10, 1, 1);
		sched1 = getBits(ochBuffer, 10, 2, 2);
		inj1 = getBits(ochBuffer, 10, 3, 3);
		sched2 = getBits(ochBuffer, 10, 4, 4);
		inj2 = getBits(ochBuffer, 10, 5, 5);

		engine = getByte(ochBuffer, 11);
		ready = getBits(ochBuffer, 11, 0, 0);
		crank = getBits(ochBuffer, 11, 1, 1);
		startw = getBits(ochBuffer, 11, 2, 2);
		warmup = getBits(ochBuffer, 11, 3, 3);
		psaccaen = getBits(ochBuffer, 11, 4, 4);
		psaccden = getBits(ochBuffer, 11, 5, 5);
		mapaccaen = getBits(ochBuffer, 11, 6, 6);
		mapaccden = getBits(ochBuffer, 11, 7, 7);

		afrtgt1 = getByte(ochBuffer, 12);
		afrtgt2 = getByte(ochBuffer, 13);

		wbo2_en1 = getByte(ochBuffer, 14);
		wbo2_en2 = getByte(ochBuffer, 15);

		barometer = getSignedWord(ochBuffer, 16);
		map = getSignedWord(ochBuffer, 18);
		mat = getSignedWord(ochBuffer, 20);
		coolant = getSignedWord(ochBuffer, 22);
		mat = getSignedWord(ochBuffer, 20);
		coolant = getSignedWord(ochBuffer, 22);
		ps = getSignedWord(ochBuffer, 24);
		batteryVoltage = getSignedWord(ochBuffer, 26);
		afr1 = getSignedWord(ochBuffer, 28);
		afr2 = getSignedWord(ochBuffer, 30);
		knock = getSignedWord(ochBuffer, 32);

		egoCorrection1 = getSignedWord(ochBuffer, 34);
		egoCorrection2 = getSignedWord(ochBuffer, 36);
		airCorrection = getSignedWord(ochBuffer, 38);
		warmupEnrich = getSignedWord(ochBuffer, 40);

		accelEnrich = getSignedWord(ochBuffer, 42);
		psfuelcut = getSignedWord(ochBuffer, 44);
		baroCorrection = getSignedWord(ochBuffer, 46);
		gammaEnrich = getSignedWord(ochBuffer, 48);

		veCurr1 = getSignedWord(ochBuffer, 50);
		veCurr2 = getSignedWord(ochBuffer, 52);
		iacstep = getSignedWord(ochBuffer, 54);
		idleDC = getSignedWord(ochBuffer, 54);
		coldAdvDeg = getSignedWord(ochBuffer, 56);
		psDOT = getSignedWord(ochBuffer, 58);
		mapDOT = getSignedWord(ochBuffer, 60);
		dwell = getWord(ochBuffer, 62);
		maf = getSignedWord(ochBuffer, 64);
		fuelload = getSignedWord(ochBuffer, 66);
		fuelCorrection = getSignedWord(ochBuffer, 68);

		portStatus = getByte(ochBuffer, 70);
		port0 = getBits(ochBuffer, 70, 0, 0);
		port1 = getBits(ochBuffer, 70, 1, 1);
		port2 = getBits(ochBuffer, 70, 2, 2);
		port3 = getBits(ochBuffer, 70, 3, 3);
		port4 = getBits(ochBuffer, 70, 4, 4);
		port5 = getBits(ochBuffer, 70, 5, 5);
		port6 = getBits(ochBuffer, 70, 6, 6);

		knockRetard = getByte(ochBuffer, 71);
		EAEFuelCorr1 = getWord(ochBuffer, 72);
		egoV = getSignedWord(ochBuffer, 74);
		egoV2 = getSignedWord(ochBuffer, 76);
		status1 = getByte(ochBuffer, 78);
		status2 = getByte(ochBuffer, 79);
		status3 = getByte(ochBuffer, 80);
		status4 = getByte(ochBuffer, 81);
		looptime = getWord(ochBuffer, 82);
		status5 = getWord(ochBuffer, 84);
		psADC = getWord(ochBuffer, 86);
		fuelload2 = getWord(ochBuffer, 88);
		ignload = getWord(ochBuffer, 90);
		ignload2 = getWord(ochBuffer, 92);
		synccnt = getByte(ochBuffer, 94);
		iming_err = getSignedByte(ochBuffer, 95);
		deltaT = getSignedLong(ochBuffer, 96);
		wallfuel1 = getLong(ochBuffer, 100);

		gpioadc0 = getWord(ochBuffer, 104);
		gpioadc1 = getWord(ochBuffer, 106);
		gpioadc2 = getWord(ochBuffer, 108);
		gpioadc3 = getWord(ochBuffer, 110);
		gpioadc4 = getWord(ochBuffer, 112);
		gpioadc5 = getWord(ochBuffer, 114);
		gpioadc6 = getWord(ochBuffer, 116);
		gpioadc7 = getWord(ochBuffer, 118);

		gpiopwmin0 = getWord(ochBuffer, 120);
		gpiopwmin1 = getWord(ochBuffer, 122);
		gpiopwmin2 = getWord(ochBuffer, 124);
		gpiopwmin3 = getWord(ochBuffer, 126);
		adc6 = getWord(ochBuffer, 128);
		adc7 = getWord(ochBuffer, 130);
		wallfuel2 = getLong(ochBuffer, 132);
		EAEFuelCorr2 = getWord(ochBuffer, 136);
		boostduty = getByte(ochBuffer, 138);
		syncreason = getByte(ochBuffer, 139);
		user0 = getWord(ochBuffer, 140);
		gpioport0 = getByte(ochBuffer, 142);
		gpioport1 = getByte(ochBuffer, 143);
		gpioport2 = getByte(ochBuffer, 144);
	}

	@Override
	public String getLogHeader()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogRow()
	{
		StringBuffer b = new StringBuffer();
		b.append(time).append('\t');// , "Time", float, "%.3f"
		b.append(seconds).append('\t');// , "SecL", int, "%d"
		b.append(rpm).append('\t');// , "RPM", int, "%d"
		b.append(map).append('\t');// , "MAP", float, "%.1f"
		b.append(throttle).append('\t');// , "TP", int, "%d"
		if (isSet("NARROW_BAND_EGO"))
		{
			b.append(egoVoltage).append('\t');// , "O2", float, "%.3f"
		}
		else if (isSet("LAMBDA"))
		{
			b.append(lambda1).append('\t');// , "Lambda", float, "%.3f"
		}
		else
		{
			b.append(afr1).append('\t');// , "AFR", float, "%.2f"
		}

		b.append(mat).append('\t');// , "MAT", float, "%.1f"
		b.append(coolant).append('\t');// , "CLT", float, "%.1f"
		b.append(engine).append('\t');// , "Engine", int, "%d"

		b.append(egoCorrection).append('\t');// , "Gego", int, "%d"
		b.append(airCorrection).append('\t');// , "Gair", int, "%d"
		b.append(warmupEnrich).append('\t');// , "Gwarm", int, "%d"
		b.append(baroCorrection).append('\t');// , "Gbaro", int, "%d"
		b.append(gammaEnrich).append('\t');// , "Gammae", int, "%d"
		b.append(accDecEnrich).append('\t');// , "TPSacc", int, "%d"

		b.append(veCurr1).append('\t');// , "Gve", int, "%d"
		b.append(pulseWidth1).append('\t');// , "PW", float, "%.3f"
		b.append(dutyCycle1).append('\t');// , "DutyCycle1", float, "%.1f"

		b.append(veCurr2).append('\t');// , "Gve2", int, "%d"
		b.append(pulseWidth2).append('\t');// , "PW2", float, "%.3f"
		b.append(dutyCycle2).append('\t');// , "DutyCycle2", float, "%.1f"

		b.append(advance).append('\t');// , "SparkAdv", float, "%.1f"
		b.append(knockRetard).append('\t');// , "knockRet", float, "%.1f"
		b.append(coldAdvDeg).append('\t');// , "ColdAdv", float, "%.1f"
		b.append(dwell).append('\t');// , "Dwell", float, "%.2f"
		b.append(tpsDOT).append('\t');// , "tpsDOT", float, "%.1f"
		b.append(mapDOT).append('\t');// , "mapDOT", float, "%.1f"
		b.append(iacstep).append('\t');// , "IACstep", int, "%d"

		b.append(batteryVoltage).append('\t');// , "Batt V", float, "%.1f"

		b.append(deltaT).append('\t');// , "deltaT", float, "%.0f"
		b.append(wallfuel1).append('\t');// , "WallFuel1", int, "%d"
		b.append(wallfuel2).append('\t');// , "WallFuel2", int, "%d"
		b.append(EAEFuelCorr1).append('\t');// , "EAE1 %", int, "%d"
		b.append(EAEFuelCorr2).append('\t');// , "EAE2 %", int, "%d"
		b.append(fuelload).append('\t');// , "Load", float, "%.1f"
		b.append(fuelload2).append('\t');// , "Secondary Load", float, "%.1f"
		b.append(ignload).append('\t');// , "Ign load", float, "%.1f"
		b.append(ignload2).append('\t');// , "Secondary Ign Load", float, "%.1f"
		b.append(egt6temp).append('\t');// , "EGT 6 temp", int, "%d"
		b.append(egt7temp).append('\t');// , "EGT 7 temp", int, "%d"

		b.append(gpioadc0).append('\t');// , "gpioadc0", int, "%d"
		b.append(gpioadc1).append('\t');// , "gpioadc1", int, "%d"
		b.append(gpioadc2).append('\t');// , "gpioadc2", int, "%d"
		b.append(gpioadc3).append('\t');// , "gpioadc3", int, "%d"

		b.append(status1).append('\t');// , "status1", int, "%d"
		b.append(status2).append('\t');// , "status2", int, "%d"
		b.append(status3).append('\t');// , "status3", int, "%d"
		b.append(status4).append('\t');// , "status4", int, "%d"
		b.append(status5).append('\t');// , "status5", int, "%d"
		b.append(timing_err).append('\t');// , "timing err%", float, "%.1f"
		b.append(afrtgt1).append('\t');// , "AFR Target 1", float, "%.1f"
		b.append(boostduty).append('\t');// , "Boost Duty", int, "%d"
		b.append(idleDC).append('\t');// , "PWM Idle Duty", float, "%.1f"
		b.append(synccnt).append('\t');// , "Lost sync count", int, "%d"
		b.append(syncreason).append('\t');// , "Lost sync reason", int, "%d"

		return b.toString();
	}

	@Override
	public int getBlockSize()
	{
		return 145;
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

}
