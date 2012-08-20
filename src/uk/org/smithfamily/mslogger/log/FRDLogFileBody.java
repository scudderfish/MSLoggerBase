package uk.org.smithfamily.mslogger.log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Body of a FRD log file
 * 
 * See http://www.efianalytics.com/TunerStudio/formattedRawDatalog.html
 */
public class FRDLogFileBody
{
    int outpc = 0;
    boolean readOnly = false;
    int recordPointer = 0;
    List<FRDLogFileRecord> readRecords = new ArrayList<FRDLogFileRecord>();
    private FRDLogFileRecord currentRecord;
    private FRDLogFile parent;

    /**
     * 
     * @param p
     */
    public FRDLogFileBody(FRDLogFile p)
    {
        this.parent = p;
    }

    /**
     * We are being constructed from a file, so load it into memory as a list of records
     * @param p
     * @param is
     * @throws IOException
     */
    public FRDLogFileBody(FRDLogFile p, FileInputStream is) throws IOException
    {
        this.parent = p;
        byte[] buffer = new byte[parent.getHeader().getBlockSize() + 2];
        int numRead = 0;
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

    /**
     * 
     * @param ochBuffer
     */
    public void addRecord(byte[] ochBuffer)
    {
        currentRecord = new FRDLogFileRecord(this, ochBuffer,false);
    }

    /**
     * 
     * @return
     */
    public FRDLogFileRecord getCurrentRecord()
    {
        return currentRecord;
    }

    /**
     * 
     * @return
     */
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
