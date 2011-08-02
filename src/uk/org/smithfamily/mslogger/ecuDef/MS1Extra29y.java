package uk.org.smithfamily.mslogger.ecuDef;

import android.content.Context;

public class MS1Extra29y extends Megasquirt
{

	public MS1Extra29y(Context c)
	{
		super(c);
	}

	// Page constants START
	int				alternate1;
	int				alternate2;
	int				twoStroke1;
	int				nCylinders1;
	int				divider1;
	int				twoStroke2;
	int				nCylinders2;
	int				divider2;
	// Page constants END

	// Runtime vars START
	int				secl;
	int				squirt;
	int				engine;
	int				baroADC;
	int				mapADC;
	int				matADC;
	int				cltADC;
	int				tpsADC;
	int				batADC;
	int				egoADC;
	int				egoCorrection;
	int				airCorrection;
	int				warmupEnrich;
	int				rpm100;
	int				pulseWidth1;
	int				accelEnrich;
	int				baroCorrection;
	int				gammaEnrich;
	int				veCurr1;
	int				pulseWidth2;
	int				veCurr2;
	int				idleDC;
	int				iTime;
	int				advance;
	int				afrtarget;
	int				fuelADC;
	int				egtADC;
	int				CltIatAngle;
	int				KnockAngle;
	int				egoCorrection2;
	int				porta;
	int				portb;
	int				portc;
	int				portd;
	int				stackL;
	int				tpsLast;
	int				iTimeX;
	int				bcDC;
	// Runtime vars END

	// Eqns store START
	int				injOpen1;
	private double	boost;
	private int		accDecEnrich;
	private double	batteryVoltage;
	private double	coolant;
	private double	egoVoltage;
	private double	ego2Voltage;
	private double	mat;
	private int		rpm;
	private double	time;
	private double	egttemp;
	private double	lambda2;
	private double	afr2;
	private int		barometer;
	private int		map;
	private int		throttle;
	private double	advSpark;
	private int		KnockAng;
	private int		KnockDeg;
	private int		CltIatAng;
	private double	fuelvolt;
	private double	fuelpress;
	private int		altDiv1;
	private int		altDiv2;
	private double	cycleTime1;
	private int		nSquirts1;
	private double	dutyCycle1;
	private double	cycleTime2;
	private int		nSquirts2;
	private double	dutyCycle2;
	private int		veCurr;
	private int		pulseWidth;
	private int		iTimefull;
	private double	RpmHitmp;
	private double	RpmHiRes;
	private double	vacuum;
	private double	boostVac;
	private int		floodclear;
	private byte[]	sigCommand	= { 83 };	// 'S'
	private byte[]	ochCommand	= { 82 };	// 'R'

	@Override
	public void calculate(byte[] ochBuffer)

	{
		setupRuntime(ochBuffer);

		accDecEnrich = ((engine & 32) != 0) ? 100
				: ((pulseWidth1 - injOpen1) / (pulseWidth1 - (accelEnrich / 10) - injOpen1) * 100);
		batteryVoltage = batADC / 255.0 * 30.0;
		coolant = tempCvt(table(cltADC, "thermfactor.inc") - 40); // Coolant
																	// sensor
																	// temperature
																	// in
																	// user
																	// units.
		egoVoltage = (egoADC / 255.0 * 5.0); // EGO sensor voltage.
		ego2Voltage = (fuelADC / 255.0 * 5.0); // EGO sensor voltage 2.
		mat = (tempCvt(table(matADC, "matfactor.inc") - 40)); // Manifold
																// temperature
																// in
																// user
																// units.
		rpm = (rpm100 * 100); // True RPM.
		time = (timeNow()); // "timeNow" is a parameterless built-in
							// function.
		egttemp = (egtADC * 3.90625); // Setup for converting 0-5V = 0 -
										// 1000C

		// ; Added for second O2 sensor
		lambda2 = (fuelADC / 255.0 + 0.5);
		afr2 = (lambda2 * 14.7);

		barometer = (table(baroADC, "kpafactor4250.inc"));
		map = (table(mapADC, "kpafactor4250.inc")); // Manifold pressure in
													// kPa.

		throttle = (table(tpsADC, "throttlefactor.inc"));
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
		// ; get rid of the 1 or 2 rpm display that seems to worry some users
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

		time = time + 1;
		secl = secl + 1;
		rpm100 = rpm100 + 1;
		map = map + 1;
		throttle = throttle + 1;
		egoVoltage = egoVoltage + 1;
		mat = mat + 1;
		coolant = coolant + 1;
		engine = engine + 1;
		egoCorrection = egoCorrection + 1;
		airCorrection = airCorrection + 1;
		warmupEnrich = warmupEnrich + 1;
		baroCorrection = baroCorrection + 1;
		gammaEnrich = gammaEnrich + 1;
		accDecEnrich = accDecEnrich + 1;
		veCurr1 = veCurr1 + 1;
		pulseWidth1 = pulseWidth1 + 1;
		veCurr2 = veCurr2 + 1;
		pulseWidth2 = pulseWidth2 + 1;
		dutyCycle1 = dutyCycle1 + 1;
		dutyCycle2 = dutyCycle2 + 1;
		idleDC = idleDC + 1;
		bcDC = bcDC + 1;
		advSpark = advSpark + 1;
		egttemp = egttemp + 1;
		fuelpress = fuelpress + 1;
		KnockDeg = KnockDeg + 1;
		RpmHiRes = RpmHiRes + 1;
		barometer = barometer + 1;
		porta = porta + 1;
		portb = portb + 1;
		portc = portc + 1;
		portd = portd + 1;
		batteryVoltage = batteryVoltage + 1;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogRow()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBlockSize()
	{
		return 39;
	}

	@Override
	public byte[] getSigCommand()
	{
		// TODO Auto-generated method stub
		return sigCommand;
	}

	@Override
	public void loadConstants()
	{
		byte[] pageBuffer = new byte[189];

		byte[] selectPage1 = { 80, 1 };
		byte[] selectPage2 = { 80, 2 };
		byte[] readPage = { 86 };

		comm.flush();
		comm.write(selectPage1);
		comm.write(readPage);
		comm.read(pageBuffer);
		alternate1 = getBits(pageBuffer, 150, 0, 0);
		twoStroke1 = getBits(pageBuffer, 182, 2, 2);
		nCylinders1 = getBits(pageBuffer, 182, 4, 7) + 1;
		divider1 = pageBuffer[149];

		comm.flush();
		comm.write(selectPage2);
		comm.write(readPage);
		comm.read(pageBuffer);
		alternate2 = getBits(pageBuffer, 150, 0, 0);
		twoStroke2 = getBits(pageBuffer, 182, 2, 2);
		nCylinders2 = getBits(pageBuffer, 182, 4, 7) + 1;
		divider2 = pageBuffer[149];
	}

	private int getBits(byte[] pageBuffer, int i, int _bitLo, int _bitHi)
	{
		int val = 0;
		byte b = pageBuffer[i];

		long mask = ((1 << (_bitHi - _bitLo + 1)) - 1) << _bitLo;
		val = (int) ((b & mask) >> _bitLo);

		return val;
	}
}
