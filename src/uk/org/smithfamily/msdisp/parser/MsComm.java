package uk.org.smithfamily.msdisp.parser;

import java.nio.ByteBuffer;

public abstract class MsComm
{

    void setChunking(boolean _writeBlocks, int _interWriteDelay)
    {
        
    }

    public void setReadTimeouts(int _blockReadTimeout)
    {
        // TODO Auto-generated method stub
        
    }

    public int port()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public int rate()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean read(ByteBuffer bytes, int nBytes)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public void flush()
    {
        // TODO Auto-generated method stub
        
    }

    public abstract boolean write(byte[] bs)
    ;

}
