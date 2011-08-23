package uk.org.smithfamily.mslogger.ecuDef;

import uk.org.smithfamily.mslogger.comms.LostCommsException;
import android.content.Context;

public class MS1Extra29y extends Megasquirt
{

	public MS1Extra29y(Context c)
	{
		super(c);
	}

	// Page constants START
	int			alternate1;
	int			alternate2;
	int			twoStroke1;
	int			nCylinders1;
	int			divider1;
	int			twoStroke2;
	int			nCylinders2;
	int			divider2;
	// Page constants END

	// Runtime vars START
	int			secl;
	int			squirt;
	int			engine;
	int			baroADC;
	int			mapADC;
	int			matADC;
	int			cltADC;
	int			tpsADC;
	int			batADC;
	int			egoADC;
	int			egoCorrection;
	int			airCorrection;
	int			warmupEnrich;
	int			rpm100;
	int			pulseWidth1;
	int			accelEnrich;
	int			baroCorrection;
	int			gammaEnrich;
	int			veCurr1;
	int			pulseWidth2;
	int			veCurr2;
	int			idleDC;
	int			iTime;
	int			advance;
	int			afrtarget;
	int			fuelADC;
	int			egtADC;
	int			CltIatAngle;
	int			KnockAngle;
	int			egoCorrection2;
	int			porta;
	int			portb;
	int			portc;
	int			portd;
	int			stackL;
	int			tpsLast;
	int			iTimeX;
	int			bcDC;
	// Runtime vars END

	// Eqns store START
	int			injOpen1;
	double		boost;
	int			accDecEnrich;
	double		batteryVoltage;
	double		coolant;
	double		egoVoltage;
	double		ego2Voltage;
	double		mat;
	int			rpm;
	double		time;
	double		egttemp;
	double		lambda2;
	double		afr2;
	int			barometer;
	double		map;
	int			throttle;
	double		advSpark;
	int			KnockAng;
	int			KnockDeg;
	int			CltIatAng;
	double		fuelvolt;
	double		fuelpress;
	int			altDiv1;
	int			altDiv2;
	double		cycleTime1;
	int			nSquirts1;
	double		dutyCycle1;
	double		cycleTime2;
	int			nSquirts2;
	double		dutyCycle2;
	int			veCurr;
	int			pulseWidth;
	int			iTimefull;
	double		RpmHitmp;
	double		RpmHiRes;
	double		vacuum;
	double		boostVac;
	int			floodclear;
	byte[]		sigCommand		= { 83 };																								// 'S'
	byte[]		ochCommand		= { 82 };																								// 'R'
	String[]	logFieldHeaders	= { "Time", "SecL", "RPM/100", "MAP", "TP", "O2", "MAT", "CLT", "Engine", "Gego", "Gair", "Gwarm",
			"Gbaro", "Gammae", "TPSacc", "Gve", "PW", "Gve2", "PW2", "DutyCycle1", "DutyCycle2", "idleDC", "BCDuty3",
			"Spark Angle", "EGT", "Fuel Press", "Knock", "RPM", "barometer", "porta", "portb", "portc", "portd", "NOS On", "batt V" };
	String[]	logFields		= {};

	@Override
	public int getInterWriteDelay()
	{
		return 5;
	}

	@Override
	public int getPageActivationDelay()
	{
		return 50;
	}

	@Override
	public void calculate(byte[] ochBuffer) throws LostCommsException

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
			egttemp = (egtADC * 3.90625); // Setup for converting 0-5V = 0 -
											// 1000C

			// ; Added for second O2 sensor
			lambda2 = (fuelADC / 255.0 + 0.5);
			afr2 = (lambda2 * 14.7);

			barometer = (TableManager.INSTANCE.table(baroADC, "kpafactor4250.inc"));
			map = (TableManager.INSTANCE.table(mapADC, "kpafactor4250.inc")); // Manifold
																				// pressure
																				// in
			// kPa.

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
			throw new LostCommsException(e);
		}

	}

	private void setupRuntime(byte[] ochBuffer)
	{
		secl = getByte(ochBuffer, 0);
		squirt = getByte(ochBuffer, 1);
		engine = getByte(ochBuffer, 2);
		baroADC = getByte(ochBuffer, 3);
		mapADC = getByte(ochBuffer, 4);
		matADC = getByte(ochBuffer, 5);
		cltADC = getByte(ochBuffer, 6);
		tpsADC = getByte(ochBuffer, 7);
		batADC = getByte(ochBuffer, 8);
		egoADC = getByte(ochBuffer, 9);
		egoCorrection = getByte(ochBuffer, 10);
		airCorrection = getByte(ochBuffer, 11);
		warmupEnrich = getByte(ochBuffer, 12);
		rpm100 = getByte(ochBuffer, 13);
		pulseWidth1 = getByte(ochBuffer, 14);
		accelEnrich = getByte(ochBuffer, 15);
		baroCorrection = getByte(ochBuffer, 16);
		gammaEnrich = getByte(ochBuffer, 17);
		veCurr1 = getByte(ochBuffer, 18);
		pulseWidth2 = getByte(ochBuffer, 19);
		veCurr2 = getByte(ochBuffer, 20);
		idleDC = getByte(ochBuffer, 21);
		iTime = getWord(ochBuffer, 22);
		advance = getByte(ochBuffer, 24);
		afrtarget = getByte(ochBuffer, 25);
		fuelADC = getByte(ochBuffer, 26);
		egtADC = getByte(ochBuffer, 27);
		CltIatAngle = getByte(ochBuffer, 28);
		KnockAngle = getByte(ochBuffer, 29);
		egoCorrection2 = getByte(ochBuffer, 30);
		porta = getByte(ochBuffer, 31);
		portb = getByte(ochBuffer, 32);
		portc = getByte(ochBuffer, 33);
		portd = getByte(ochBuffer, 34);
		stackL = getByte(ochBuffer, 35);
		tpsLast = getByte(ochBuffer, 36);
		iTimeX = getByte(ochBuffer, 37);
		bcDC = getByte(ochBuffer, 38);

	}

	@Override
	public String getSignature()
	{
		return "MS1/Extra format 029y3 *********";
	}

	@Override
	public byte[] getOchCommand()
	{
		return ochCommand;
	}

	@Override
	public String getLogHeader()
	{

		return "Time\tSecL\tRPM/100\tMAP\tTP\tO2\tMAT\tCLT\tEngine\tGego\tGair\tGwarm\tGbaro\tGammae\tTPSacc\tGve\tPW\tGve2\tPW2\tDutyCycle1\tDutyCycle2\tidleDC\tBCDuty3\tSpark Angle\tEGT\tFuel Press\tKnock\tRPM\tbarometer\tporta\tportb\tportc\tportd\tNOS On\tbatt V";
	}

	@Override
	public String getLogRow()
	{
		return time + "\t" + secl + "\t" + rpm100 + "\t" + map + "\t" + throttle + "\t" + egoVoltage + "\t" + mat + "\t" + coolant
				+ "\t" + engine + "\t" + egoCorrection + "\t" + airCorrection + "\t" + warmupEnrich + "\t" + baroCorrection + "\t"
				+ gammaEnrich + "\t" + accDecEnrich + "\t" + veCurr1 + "\t" + pulseWidth1 + "\t" + veCurr2 + "\t" + pulseWidth2
				+ "\t" + dutyCycle1 + "\t" + dutyCycle2 + "\t" + idleDC + "\t" + bcDC + "\t" + advSpark + "\t" + egttemp + "\t"
				+ fuelpress + "\t" + KnockDeg + "\t" + RpmHiRes + "\t" + barometer + "\t" + porta + "\t" + portb + "\t" + portc
				+ "\t" + portd + "\t" + 0 + "\t" + batteryVoltage;
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
	public void loadConstants(boolean simulated) throws LostCommsException
	{
		if (simulated)
		{
			alternate1=1;
			twoStroke1=0;
			nCylinders1=8;
			divider1=1;
			alternate2=1;
			twoStroke2=0;
			nCylinders2=8;
			divider2=1;
		}
		else
		{
			byte[] pageBuffer = new byte[189];

			byte[] selectPage1 = { 80, 1 };
			byte[] selectPage2 = { 80, 2 };
			byte[] readPage = { 86 };
			getPage(pageBuffer, selectPage1, readPage);
			alternate1 = getBits(pageBuffer, 150, 0, 0);
			twoStroke1 = getBits(pageBuffer, 182, 2, 2);
			nCylinders1 = getBits(pageBuffer, 182, 4, 7) + 1;
			divider1 = pageBuffer[149];

			getPage(pageBuffer, selectPage2, readPage);
			alternate2 = getBits(pageBuffer, 150, 0, 0);
			twoStroke2 = getBits(pageBuffer, 182, 2, 2);
			nCylinders2 = getBits(pageBuffer, 182, 4, 7) + 1;
			divider2 = pageBuffer[149];
		}
	}

	@Override
	public int getCurrentTPS()
	{
		return tpsADC;
	}
}
