package uk.org.smithfamily.mslogger.log;


/**
 * 
 */
public class FRDLogFileRecord
{
    private final byte[] buffer;
    private final FRDLogFileBody body;

    public FRDLogFileRecord(FRDLogFileBody body, byte[] ochBuffer, boolean fromFile)
    {
        this.body = body;
        if(!fromFile)
        {
        buffer = new byte[ochBuffer.length + 2];
        buffer[0] = 1;
        buffer[1] = (byte) (body.outpc++);
        System.arraycopy(ochBuffer, 0, buffer, 2, ochBuffer.length);
        }
        else
        {
            buffer = ochBuffer;
        }
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
