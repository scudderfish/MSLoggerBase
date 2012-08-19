package uk.org.smithfamily.mslogger.comms;

public enum ConnectionFactory
{
    INSTANCE;

    public IConnection getConn()
    {
        return BluetoothConnection.INSTANCE;
    }
    
}
