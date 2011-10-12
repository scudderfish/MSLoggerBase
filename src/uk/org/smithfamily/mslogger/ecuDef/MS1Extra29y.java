package uk.org.smithfamily.mslogger.ecuDef;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import android.content.Context;
import android.util.Log;

public class MS1Extra29y extends Megasquirt
{

	// Page constants START
	int					alternate1;
	int					alternate2;
	int					twoStroke1;
	int					nCylinders1;
	int					divider1;
	int					twoStroke2;
	int					nCylinders2;
	int					divider2;
	// Page constants END

	// Runtime vars START
	int					secl;
	int					squirt;
	int					engine;
	int					baroADC;
	int					mapADC;
	int					matADC;
	int					cltADC;
	int					tpsADC;
	int					batADC;
	int					egoADC;
	int					egoCorrection;
	int					airCorrection;
	int					warmupEnrich;
	int					rpm100;
	int					pulseWidth1;
	int					accelEnrich;
	int					baroCorrection;
	int					gammaEnrich;
	int					veCurr1;
	int					pulseWidth2;
	int					veCurr2;
	int					idleDC;
	int					iTime;
	int					advance;
	int					afrtarget;
	int					fuelADC;
	int					egtADC;
	int					CltIatAngle;
	int					KnockAngle;
	int					egoCorrection2;
	int					porta;
	int					portb;
	int					portc;
	int					portd;
	int					stackL;
	int					tpsLast;
	int					iTimeX;
	int					bcDC;
	// Runtime vars END

	// Eqns store START
	int					injOpen1;
	double				boost;
	int					accDecEnrich;
	double				batteryVoltage;
	double				coolant;
	double				egoVoltage;
	double				ego2Voltage;
	double				mat;
	int					rpm;
	double				time;
	double				egttemp;
	double				lambda2;
	double				afr2;
	int					barometer;
	double				map;
	int					throttle;
	double				advSpark;
	int					KnockAng;
	int					KnockDeg;
	int					CltIatAng;
	double				fuelvolt;
	double				fuelpress;
	int					altDiv1;
	int					altDiv2;
	double				cycleTime1;
	int					nSquirts1;
	double				dutyCycle1;
	double				cycleTime2;
	int					nSquirts2;
	double				dutyCycle2;
	int					veCurr;
	int					pulseWidth;
	int					iTimefull;
	double				RpmHitmp;
	double				RpmHiRes;
	double				vacuum;
	double				boostVac;
	int					floodclear;
	byte[]				sigCommand	= { 83 };																					// 'S'
	byte[]				ochCommand	= { 82 };																					// 'R'

	boolean				CELSIUS;
	boolean				NARROW_BAND_EGO;
	boolean				ZEITRONIX_NON_LINEAR;
	boolean				INNOVATE_LC1_DEFAULT;
	boolean				MPXH6300A;
	boolean				MPXH6400A;
	boolean				MPX4250;

	private Set<String>	sigs		= new HashSet<String>(Arrays.asList(new String[] { "MS1/Extra format 029y3 *********" }));

	@Override
	public Set<String> getSignature()
	{
		return sigs;
	}

	public MS1Extra29y(Context c)
	{
		super(c);
		CELSIUS = isSet("CELSIUS");
		NARROW_BAND_EGO = isSet("NARROW_BAND_EGO");
		ZEITRONIX_NON_LINEAR = isSet("ZEITRONIX_NON_LINEAR");
		INNOVATE_LC1_DEFAULT = isSet("INNOVATE_LC1_DEFAULT");
		MPXH6300A = isSet("MPXH6300A");
		MPXH6400A = isSet("MPXH6400A");
		MPX4250 = isSet("MPX4250");
	}

	@Override
	public int getInterWriteDelay()
	{
		return 10;
	}

	@Override
	public int getPageActivationDelay()
	{
		return 100;
	}

	@Override
	public void calculate(byte[] ochBuffer) throws IOException
	{
		setupRuntime(ochBuffer);

		try
		{
			accDecEnrich = ((engine & 32) == 0) ? 100
					: ((pulseWidth1 - injOpen1) / (pulseWidth1 - (accelEnrich / 10) - injOpen1) * 100);
			batteryVoltage = batADC / 255.0 * 30.0;
			coolant = tempCvt(TableManager.INSTANCE.table(cltADC, "thermfactor.inc") - 40);
			egoVoltage = (egoADC / 255.0 * 5.0); // EGO sensor voltage.
			ego2Voltage = (fuelADC / 255.0 * 5.0); // EGO sensor voltage 2.
			mat = (tempCvt(TableManager.INSTANCE.table(matADC, "matfactor.inc") - 40));
			rpm = (rpm100 * 100); // True RPM.
			time = (timeNow()); // "timeNow" is a parameterless built-in
								// function.

			if (CELSIUS)
			{
				egttemp = (egtADC * 3.90625); // Setup for converting 0-5V = 0 -
												// 1000C
			}
			else
			{
				egttemp = (egtADC * 7.15625);
			}
			// ; Added for second O2 sensor
			if (NARROW_BAND_EGO)
			{
				afr2 = (TableManager.INSTANCE.table(fuelADC, "NBafr100.inc") / 100.0);
				lambda2 = (afr2 / 14.7);

			}
			else if (ZEITRONIX_NON_LINEAR)
			{
				lambda2 = (TableManager.INSTANCE.table(fuelADC, "WBafr100Zeit.inc") / 100.0);
				afr2 = (lambda2 * 14.7);
			}
			else if (INNOVATE_LC1_DEFAULT)
			{
				lambda2 = (fuelADC / 255.0 + 0.5);
				afr2 = (lambda2 * 14.7);
			}
			else
			{
				lambda2 = (TableManager.INSTANCE.table(fuelADC, "WBlambda100MOT.inc") / 100.0);
				afr2 = (lambda2 * 14.7);
			}

			if (MPXH6300A)
			{
				barometer = (int) ((baroADC + 1.53) * 1.213675);
				map = ((mapADC + 1.53) * 1.213675);
			}
			else if (MPXH6400A)
			{
				barometer = (int) ((baroADC + 2.147) * 1.6197783);
				map = ((mapADC + 2.147) * 1.6197783);

			}
			else if (MPX4250)
			{
				barometer = (TableManager.INSTANCE.table(baroADC, "kpafactor4250.inc"));
				// Manifold pressure in kPa.
				map = (TableManager.INSTANCE.table(mapADC, "kpafactor4250.inc"));
			}
			else
			{
				barometer = (TableManager.INSTANCE.table(baroADC, "kpafactor4115.inc"));
				map = (TableManager.INSTANCE.table(mapADC, "kpafactor4115.inc"));

			}
			throttle = (TableManager.INSTANCE.table(tpsADC, "throttlefactor.inc"));
			advSpark = ((advance * 0.352) - 10);
			// ; Enhanced Stuff
			KnockAng = ((KnockAngle * 90 / 256));
			KnockDeg = (-KnockAng);
			CltIatAng = (CltIatAngle * 90 / 256);
			fuelvolt = (fuelADC < 1 ? 0.0 : fuelADC * (5 / 255) - 0.5);
			fuelpress = (fuelADC < 1 ? 0.0 : fuelvolt / 0.04 + 1);
			altDiv1 = (alternate1 != 0 ? 2 : 1);
			altDiv2 = (alternate2 != 0 ? 2 : 1);
			cycleTime1 = (rpm < 100 ? 0 : 60000.0 / rpm * (2.0 - twoStroke1));
			nSquirts1 = (nCylinders1 / divider1);
			dutyCycle1 = (rpm < 100 ? 0 : 100.0 * nSquirts1 / altDiv1 * pulseWidth1 / cycleTime1);
			cycleTime2 = (rpm < 100 ? 0 : 60000.0 / rpm * (2.0 - twoStroke2));
			nSquirts2 = (nCylinders2 / divider2);
			dutyCycle2 = (rpm < 100 ? 0 : 100.0 * nSquirts2 / altDiv2 * pulseWidth2 / cycleTime2);

			// ; These next two are needed to make the runtime dialog look good.
			veCurr = (veCurr1);
			pulseWidth = (pulseWidth1);

			iTimefull = ((iTimeX * 65536) + iTime);
			RpmHitmp = (iTimefull > 0 ? (60000000 * (2.0 - twoStroke1)) / (iTimefull * nCylinders1) : 0);
			// ; get rid of the 1 or 2 rpm display that seems to worry some
			// users
			RpmHiRes = (RpmHitmp > 20 ? RpmHitmp : 0);

			// ; Vacuum and Boost Gauges
			vacuum = ((barometer - map) * 0.2953007);// ; Calculate vacuum in
														// in-Hg.
			boost = (map < barometer ? 0.0 : (map - barometer) * 0.1450377);// ;
																			// Calculate
																			// boost
																			// in
																			// PSIG.
			boostVac = (map < barometer ? -vacuum : (map - barometer) * 0.1450377);

			floodclear = (tpsADC > 200 ? 1 : 0);// ; For flood clear indicator
												// on main screen

			// Now change to 2dp
			boost = round(boost);
			batteryVoltage = round(batteryVoltage);
			coolant = round(coolant);
			egoVoltage = round(egoVoltage);
			ego2Voltage = round(ego2Voltage);
			mat = round(mat);
			time = round(time);
			egttemp = round(egttemp);
			lambda2 = round(lambda2);
			afr2 = round(afr2);
			advSpark = round(advSpark);
			fuelvolt = round(fuelvolt);
			fuelpress = round(fuelpress);
			cycleTime1 = round(cycleTime1);
			dutyCycle1 = round(dutyCycle1);
			cycleTime2 = round(cycleTime2);
			dutyCycle2 = round(dutyCycle2);
			RpmHitmp = round(RpmHitmp);
			RpmHiRes = round(RpmHiRes);
			vacuum = round(vacuum);
			boostVac = round(boostVac);
		}
		catch (Exception e)
		{
			Log.e(ApplicationSettings.TAG, "MS1Extra29y.calculate()", e);

			// If we've got an arithmetic error, we've probably got duff
			// constants.
			throw new IOException(e.getLocalizedMessage());
		}

	}

	private void setupRuntime(byte[] ochBuffer)
	{
		secl = MSUtils.getByte(ochBuffer, 0);
		squirt = MSUtils.getByte(ochBuffer, 1);
		engine = MSUtils.getByte(ochBuffer, 2);
		baroADC = MSUtils.getByte(ochBuffer, 3);
		mapADC = MSUtils.getByte(ochBuffer, 4);
		matADC = MSUtils.getByte(ochBuffer, 5);
		cltADC = MSUtils.getByte(ochBuffer, 6);
		tpsADC = MSUtils.getByte(ochBuffer, 7);
		batADC = MSUtils.getByte(ochBuffer, 8);
		egoADC = MSUtils.getByte(ochBuffer, 9);
		egoCorrection = MSUtils.getByte(ochBuffer, 10);
		airCorrection = MSUtils.getByte(ochBuffer, 11);
		warmupEnrich = MSUtils.getByte(ochBuffer, 12);
		rpm100 = MSUtils.getByte(ochBuffer, 13);
		pulseWidth1 = MSUtils.getByte(ochBuffer, 14);
		accelEnrich = MSUtils.getByte(ochBuffer, 15);
		baroCorrection = MSUtils.getByte(ochBuffer, 16);
		gammaEnrich = MSUtils.getByte(ochBuffer, 17);
		veCurr1 = MSUtils.getByte(ochBuffer, 18);
		pulseWidth2 = MSUtils.getByte(ochBuffer, 19);
		veCurr2 = MSUtils.getByte(ochBuffer, 20);
		idleDC = MSUtils.getByte(ochBuffer, 21);
		iTime = MSUtils.getWord(ochBuffer, 22);
		advance = MSUtils.getByte(ochBuffer, 24);
		afrtarget = MSUtils.getByte(ochBuffer, 25);
		fuelADC = MSUtils.getByte(ochBuffer, 26);
		egtADC = MSUtils.getByte(ochBuffer, 27);
		CltIatAngle = MSUtils.getByte(ochBuffer, 28);
		KnockAngle = MSUtils.getByte(ochBuffer, 29);
		egoCorrection2 = MSUtils.getByte(ochBuffer, 30);
		porta = MSUtils.getByte(ochBuffer, 31);
		portb = MSUtils.getByte(ochBuffer, 32);
		portc = MSUtils.getByte(ochBuffer, 33);
		portd = MSUtils.getByte(ochBuffer, 34);
		stackL = MSUtils.getByte(ochBuffer, 35);
		tpsLast = MSUtils.getByte(ochBuffer, 36);
		iTimeX = MSUtils.getByte(ochBuffer, 37);
		bcDC = MSUtils.getByte(ochBuffer, 38);

	}

	@Override
	public byte[] getOchCommand()
	{
		return ochCommand;
	}

	@Override
	public String getLogHeader()
	{

		return "Time\tSecL\tRPM/100\tMAP\tTP\tO2\tMAT\tCLT\tEngine\tGego\tGair\tGwarm\tGbaro\tGammae\tTPSacc\tGve\tPW\tGve2\tPW2\tDutyCycle1\tDutyCycle2\tidleDC\tBCDuty3\tSpark Angle\tEGT\tFuel Press\tKnock\tRPM\tbarometer\tporta\tportb\tportc\tportd\tNOS On\tbatt V\tiTime\tiTimeX\ttwoStroke1\tnCylinders1"
				+ MSUtils.getLocationLogHeader();
	}

	@Override
	public String getLogRow()
	{
		return time + "\t" + secl + "\t" + rpm100 + "\t" + map + "\t" + throttle + "\t" + egoVoltage + "\t" + mat + "\t" + coolant
				+ "\t" + engine + "\t" + egoCorrection + "\t" + airCorrection + "\t" + warmupEnrich + "\t" + baroCorrection + "\t"
				+ gammaEnrich + "\t" + accDecEnrich + "\t" + veCurr1 + "\t" + pulseWidth1 + "\t" + veCurr2 + "\t" + pulseWidth2
				+ "\t" + dutyCycle1 + "\t" + dutyCycle2 + "\t" + idleDC + "\t" + bcDC + "\t" + advSpark + "\t" + egttemp + "\t"
				+ fuelpress + "\t" + KnockDeg + "\t" + RpmHiRes + "\t" + barometer + "\t" + porta + "\t" + portb + "\t" + portc
				+ "\t" + portd + "\t" + 0 + "\t" + batteryVoltage + "\t" + iTime + "\t" + iTimeX + "\t" + twoStroke1 + "\t"
				+ nCylinders1 + MSUtils.getLocationLogRow();

	}

	@Override
	public int getBlockSize()
	{
		return 39;
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
			alternate1 = 1;
			twoStroke1 = 0;
			nCylinders1 = 8;
			divider1 = 1;
			alternate2 = 1;
			twoStroke2 = 0;
			nCylinders2 = 8;
			divider2 = 1;
		}
		else
		{
			byte[] pageBuffer1 = new byte[189];
			byte[] pageBuffer2 = new byte[189];

			byte[] selectPage1 = { 80, 1 };
			byte[] selectPage2 = { 80, 2 };
			byte[] readPage = { 86 };
			getPage(pageBuffer1, selectPage1, readPage);
			getPage(pageBuffer2, selectPage2, readPage);

			alternate1 = MSUtils.getBits(pageBuffer1, 150, 0, 0);
			twoStroke1 = MSUtils.getBits(pageBuffer1, 182, 2, 2);
			nCylinders1 = MSUtils.getBits(pageBuffer1, 182, 4, 7) + 1;
			divider1 = pageBuffer1[149];

			alternate2 = MSUtils.getBits(pageBuffer2, 150, 0, 0);
			twoStroke2 = MSUtils.getBits(pageBuffer2, 182, 2, 2);
			nCylinders2 = MSUtils.getBits(pageBuffer2, 182, 4, 7) + 1;
			divider2 = pageBuffer2[149];
		}
	}

	@Override
	public int getCurrentTPS()
	{
		return tpsADC;
	}
}
