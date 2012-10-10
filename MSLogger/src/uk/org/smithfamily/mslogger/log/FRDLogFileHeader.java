package uk.org.smithfamily.mslogger.log;

import java.io.FileInputStream;
import java.io.IOException;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;

/**
 * Header of a FRD log file
 *
 * See http://www.efianalytics.com/TunerStudio/formattedRawDatalog.html
 */
public class FRDLogFileHeader
{

	private final byte[]			fileFormat		= { 0x46, 0x52, 0x44, 0x00, 0x00, 0x00 };
	private final byte[]			formatVersion	= { 0x00, 0x01 };

	private final byte[]			timeStamp		= { 0, 0, 0, 0 };
	private final byte[]			firmware		= { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private final byte[]			beginIndex		= { 0, 0, 0, 81 };
	private final byte[]			outputLength	= { 0, 0 };

    private FRDLogFile parent;

    private int blockSize;

    /**
     * 
     * @param frdLogFile
     */
	public FRDLogFileHeader(FRDLogFile frdLogFile)
	{
	    this.parent = frdLogFile;
		Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
		String sig = "NOECU";
		if (ecu != null && ecu.isConnected())
		{
		    sig=ecu.getTrueSignature();
		    blockSize = ecu.getBlockSize();
	    }
		System.arraycopy(sig.getBytes(), 0, firmware, 0, sig.length());

		int now = (int) (System.currentTimeMillis() / 1000l);
		timeStamp[0] = (byte) (now >> 24);
		timeStamp[1] = (byte) (now >> 16);
		timeStamp[2] = (byte) (now >> 8);
		timeStamp[3] = (byte) (now);

		outputLength[0] = (byte) (blockSize >> 8);
		outputLength[1] = (byte) (blockSize);

	}

	/**
	 * 
	 * @param frdLogFile
	 * @param is
	 * @throws IOException
	 */
	public FRDLogFileHeader(FRDLogFile frdLogFile,FileInputStream is) throws IOException
    {
        this.parent = frdLogFile;
	    is.read(fileFormat);
	    is.read(formatVersion);
	    is.read(timeStamp);
	    is.read(firmware);
	    is.read(beginIndex);
	    is.read(outputLength);
	    this.blockSize = outputLength[1];
	    this.blockSize += outputLength[0] * 256;
    }

	/**
	 * 
	 * @return
	 */
    public byte[] getHeaderRecord()
	{
		byte[] result = concatAll(fileFormat, formatVersion, timeStamp, firmware, beginIndex, outputLength);

		return result;

	}

    /**
     * 
     * @return
     */
	public byte[] getFileformat()
	{
		return fileFormat;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] getFormatversion()
	{
		return formatVersion;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] getTimeStamp()
	{
		return timeStamp;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] getFirmware()
	{
		return firmware;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] getBeginindex()
	{
		return beginIndex;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] getOutputlength()
	{
		return outputLength;
	}

	/**
	 * 
	 * @param first
	 * @param rest
	 * @return
	 */
	public static byte[] concatAll(byte[] first, byte[]... rest)
	{
		int totalLength = first.length;
		for (byte[] array : rest)
		{
			totalLength += array.length;
		}
		byte[] result = new byte[totalLength];
		System.arraycopy(first, 0, result, 0, first.length);
		int offset = first.length;
		for (byte[] array : rest)
		{
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * 
	 * @return
	 */
    public FRDLogFile getParent()
    {
        return parent;
    }

    /**
     * 
     * @return
     */
    public int getBlockSize()
    {
        return blockSize;
    }   
}
