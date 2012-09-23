package uk.org.smithfamily.mslogger.ecuDef;

public interface MSUtilsInterface
{
    public int getLong(byte[] ochBuffer, int i);

    public int getWord(byte[] ochBuffer, int i);

    public int getByte(byte[] ochBuffer, int i);

    public int getSignedLong(byte[] ochBuffer, int i);

    public int getSignedByte(byte[] ochBuffer, int i);

    public int getSignedWord(byte[] ochBuffer, int i);

    public int getBits(byte[] pageBuffer, int i, int _bitLo, int _bitHi, int j);

    public double getLatitude();

    public double getLongitude();

    public double getSpeed();

    public double getBearing();

    public double getAccuracy();

    public long getTime();

    public String getLocationLogHeader();

    public String getLocationLogRow();

}
