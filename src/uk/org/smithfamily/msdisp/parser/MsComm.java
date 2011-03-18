package uk.org.smithfamily.msdisp.parser;


public abstract class MsComm
{

    void setChunking(boolean _writeBlocks, int _interWriteDelay)
    {
        
    }

    public void setReadTimeouts(int _blockReadTimeout)
    {
        
    }

    public int port()
    {
        return 0;
    }

    public int rate()
    {
        return 0;
    }

    public abstract boolean read(byte[] pBytes, int nBytes);

    public void flush()
    {
        
    }

    public abstract boolean write(byte[] bs)
    ;

    public abstract String read(int nBytes);
}
