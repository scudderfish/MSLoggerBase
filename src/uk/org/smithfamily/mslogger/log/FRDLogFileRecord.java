package uk.org.smithfamily.mslogger.log;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 */
public class FRDLogFileRecord
{
    private byte[] buffer;
    private FRDLogFileBody body;

    /**
     * 
     * @param body
     * @param ochBuffer
     */
    public FRDLogFileRecord(FRDLogFileBody body, byte[] ochBuffer)
    {
        this.body = body;
        buffer = new byte[ochBuffer.length + 2];
        buffer[0] = 1;
        buffer[1] = (byte) (body.outpc++);
        System.arraycopy(ochBuffer, 0, buffer, 2, ochBuffer.length);

    }

    /**
     * 
     * @param body
     * @param is
     * @throws IOException
     */
    public FRDLogFileRecord(FRDLogFileBody body, FileInputStream is) throws IOException
    {
        this.body = body;
        int blockSize = body.getParent().getHeader().getBlockSize();
        buffer = new byte[blockSize + 2];
        is.read(buffer, 0, blockSize + 2);
    }

    /**
     * 
     * @return
     */
    public byte[] getBytes()
    {
        return buffer;
    }
    
    /**
     * 
     * @return
     */
    public byte[] getOchBuffer()
    {
        int blockSize = body.getParent().getHeader().getBlockSize();
        byte[] ochBuffer = new byte[blockSize];
        System.arraycopy(buffer, 2, ochBuffer, 0,blockSize);
        
        return ochBuffer;
    }
}
