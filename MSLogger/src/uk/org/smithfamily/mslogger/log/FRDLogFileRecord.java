package uk.org.smithfamily.mslogger.log;


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
