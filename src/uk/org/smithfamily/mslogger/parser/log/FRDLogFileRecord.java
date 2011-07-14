package uk.org.smithfamily.mslogger.parser.log;

import java.io.FileInputStream;
import java.io.IOException;

public class FRDLogFileRecord
{
    private byte[] buffer;
    private FRDLogFileBody body;

    public FRDLogFileRecord(FRDLogFileBody body, byte[] ochBuffer)
    {
        this.body = body;
        buffer = new byte[ochBuffer.length + 2];
        buffer[0] = 1;
        buffer[1] = (byte) (body.outpc++);
        System.arraycopy(ochBuffer, 0, buffer, 2, ochBuffer.length);

    }

    public FRDLogFileRecord(FRDLogFileBody body, FileInputStream is) throws IOException
    {
        this.body = body;
        int blockSize = body.getParent().getHeader().getBlockSize();
        buffer = new byte[blockSize + 2];
        is.read(buffer, 0, blockSize + 2);
    }

    public byte[] getBytes()
    {
        return buffer;
    }
    public byte[] getOchBuffer()
    {
        int blockSize = body.getParent().getHeader().getBlockSize();
        byte[] ochBuffer = new byte[blockSize];
        System.arraycopy(buffer, 2, ochBuffer, 0,blockSize);
        
        return ochBuffer;
    }
}
