package uk.org.smithfamily.mslogger.comms;

public enum ConnectionFactory
{
    INSTANCE;
    private static final boolean useSim = false;

    /**
     * Return the Connection instance to use. This should be switchable somehow but as I'm in a hotel, I'm hardwiring it to a simulator as my ECU is
     * >1000 miles away
     * 
     * @return
     */
    public Connection getConnection()
    {
        if (useSim)
        {
            return SocketConnection.INSTANCE;
        }
        else
        {

            return new BluetoothConnection();
        }
    }

}
