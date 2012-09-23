package uk.org.smithfamily.mslogger.comms;

import java.util.zip.CRC32;

/**
 * Helper class used for communication with MS3 ECU that support CRC32 validated protocol.
 * 
 * See http://www.msextra.com/doc/ms3/files/ms3_serial_protocol_0.05.pdf
 */
public class CRC32ProtocolHandler
{
    private final static int PAYLOAD_LENGTH = 2;
    private final static int TYPE_LENGTH    = 1;
    private final static int CRC32_LENGTH   = 4;

    /**
     * Wrap an array of bytes into a CRC32 validation
     * 
     * @param naked
     * @return
     */
    public static byte[] wrap(byte[] naked)
    {
        byte[] wrapped = new byte[naked.length + PAYLOAD_LENGTH + CRC32_LENGTH]; // Add 2 bytes for payload size and 4 bytes for CRC32
        wrapped[0] = 0;
        wrapped[1] = (byte) naked.length;
        System.arraycopy(naked, 0, wrapped, 2, naked.length); // Copy wrapped into naked
        CRC32 check = new CRC32();

        check.update(naked);

        long crc32value = check.getValue();
        int crcIndex = wrapped.length - CRC32_LENGTH;
        wrapped[crcIndex] = (byte) ((crc32value >> 24) & 0xff);
        wrapped[crcIndex + 1] = (byte) ((crc32value >> 16) & 0xff);
        wrapped[crcIndex + 2] = (byte) ((crc32value >> 8) & 0xff);
        wrapped[crcIndex + 3] = (byte) ((crc32value >> 0) & 0xff);

        return wrapped;
    }

    /**
     * Check if the wrapped array of bytes is valid
     * 
     * @param wrapped
     * @return
     */
    public static boolean check(byte[] wrapped)
    {

        // The type is included in the CRC calculation
        int notDataLength = PAYLOAD_LENGTH + CRC32_LENGTH;

        // Not enough data to do a check
        if (wrapped.length < notDataLength)
        {
            return true;
        }

        // Extract crc32
        byte[] crc32 = new byte[CRC32_LENGTH];
        System.arraycopy(wrapped, wrapped.length - CRC32_LENGTH, crc32, 0, CRC32_LENGTH); // Copy crc32 from wrapped into crc32 bytes array

        // Remove payload size, only keep the data
        byte[] data = new byte[wrapped.length - notDataLength];
        System.arraycopy(wrapped, 2, data, 0, wrapped.length - notDataLength);

        // Generate CRC32 on data
        CRC32 check = new CRC32();
        check.update(data);

        long crc32value = check.getValue();
        // Copy the value into a byte buffer as sign changes can cause weirdness, just dodge that bullet.
        byte[] crcBytes = new byte[4];
        crcBytes[0] = (byte) ((crc32value >> 24) & 0xff);
        crcBytes[1] = (byte) ((crc32value >> 16) & 0xff);
        crcBytes[2] = (byte) ((crc32value >> 8) & 0xff);
        crcBytes[3] = (byte) ((crc32value >> 0) & 0xff);

        // Compare crc32 we generated from the data with what we got to see if it match
        if (crc32[0] == crcBytes[0] && crc32[1] == crcBytes[1] && crc32[2] == crcBytes[2] && crc32[3] == crcBytes[3])
        {
            return true;
        }

        return false;
    }

    /**
     * Take a wrapped array of bytes and unwrap it
     * 
     * @param wrapped
     * @return
     */
    public static byte[] unwrap(byte[] wrapped)
    {
        int notDataLength = PAYLOAD_LENGTH + TYPE_LENGTH + CRC32_LENGTH;

        if (wrapped.length < notDataLength)// Bail out
        {
            return wrapped;
        }

        byte[] naked = new byte[wrapped.length - notDataLength];
        System.arraycopy(wrapped, 3, naked, 0, wrapped.length - notDataLength);
        return naked;
    }
}
