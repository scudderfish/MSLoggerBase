package uk.org.smithfamily.mslogger.log;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * FRD log file class including an header and body
 * <p>
 * See <a href="http://www.efianalytics.com/TunerStudio/formattedRawDatalog.html">...</a>
 */
public class FRDLogFile
{
    private final FRDLogFileHeader header;
    private final FRDLogFileBody body;

    FRDLogFile()
    {
        header = new FRDLogFileHeader(this);
        body = new FRDLogFileBody(this);
    }

    FRDLogFile(FileInputStream is) throws IOException
    {
        header = new FRDLogFileHeader(this, is);
        body = new FRDLogFileBody(this, is);
    }

    /**
     * @return An instance of FRD header
     */
    public FRDLogFileHeader getHeader()
    {
        return header;
    }

    /**
     * @return An instance of FRD body
     */
    public FRDLogFileBody getBody()
    {
        return body;
    }
    
    public FRDLogFileRecord getNextRecord()
    {
        return body.getNextRecord();
    }
}
