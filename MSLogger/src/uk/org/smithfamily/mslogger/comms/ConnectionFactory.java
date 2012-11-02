package uk.org.smithfamily.mslogger.comms;

public enum ConnectionFactory
{
    INSTANCE;

    /**
     * Return the Connection instance to use. This should be switchable somehow but as I'm in a hotel, I'm hardwiring it to a simulator as my ECU is
     * >1000 miles away
     * 
     * @return
     */
    public Connection getConnection()
    {
        // return new BluetoothConnection();
        return SocketConnection.INSTANCE;
    }

}
