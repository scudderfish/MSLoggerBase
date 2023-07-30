package uk.org.smithfamily.mslogger.log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Body of a FRD log file
 * <p>
 * See <a href="http://www.efianalytics.com/TunerStudio/formattedRawDatalog.html">...</a>
 */
public class FRDLogFileBody
{
    int outpc = 0;
    int recordPointer = 0;
    List<FRDLogFileRecord> readRecords = new ArrayList<>();
    private FRDLogFileRecord currentRecord;
    private final FRDLogFile parent;

    public FRDLogFileBody(FRDLogFile p)
    {
        this.parent = p;
    }

    public FRDLogFileBody(FRDLogFile p, FileInputStream is) throws IOException
    {
        this.parent = p;
        byte[] buffer = new byte[parent.getHeader().getBlockSize() + 2];
        int numRead;
        do
        {
            numRead = is.read(buffer);
            if(numRead == buffer.length)
            {
                currentRecord = new FRDLogFileRecord(this,buffer,true);
                readRecords.add(currentRecord);
            }
        } while (numRead == buffer.length);
    }

    public void addRecord(byte[] ochBuffer)
    {
        currentRecord = new FRDLogFileRecord(this, ochBuffer,false);
    }

    public FRDLogFileRecord getCurrentRecord()
    {
        return currentRecord;
    }

    public FRDLogFile getParent()
    {
        return parent;
    }

    public FRDLogFileRecord getNextRecord()
    {
        currentRecord = readRecords.get(recordPointer);
        recordPointer = (recordPointer+1) % readRecords.size();
        
        return currentRecord;
    }
}
