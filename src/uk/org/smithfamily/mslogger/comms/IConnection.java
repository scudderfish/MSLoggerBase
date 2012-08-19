package uk.org.smithfamily.mslogger.comms;

import java.io.*;

public interface IConnection
{

    void init();

    boolean isInitialised();

    void connect() throws IOException;

    void disconnect() throws IOException;

    void switchSettings();

    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws IOException;

    void tearDown();

    boolean isConnected();

    boolean connectionPossible();

    boolean connectionEnabled();

}
