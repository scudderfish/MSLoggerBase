package uk.org.smithfamily.mslogger.comms;

public enum ConnectionFactory
{
    INSTANCE;
    public Connection getConnection()
    {

            return new BluetoothConnection();
    }

}
