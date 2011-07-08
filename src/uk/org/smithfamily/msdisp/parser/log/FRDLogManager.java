package uk.org.smithfamily.msdisp.parser.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import android.text.format.DateFormat;

public class FRDLogManager
{
    private static final MsDatabase mdb = MsDatabase.getInstance();
    private static final byte[] fileFormat = {0x46, 0x52, 0x44, 0x00, 0x00, 0x00};
    private static final byte[] formatVersion = {0x00,0x01};
    private byte[] timeStamp={0,0,0,0};
    private byte[] firmware={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private byte[] beginIndex={0,0,0,81};
    private byte[] outputLength = {0,0};
    private FileOutputStream os;
    private File logFile;
    private int outpc;
    private long startTime;
    private static FRDLogManager instance = new FRDLogManager();

    private FRDLogManager()
    {
    }
    
    public static FRDLogManager getInstance()
    {
        return instance;
    }
   
    public long getStartTime()
    {
        return startTime;
    }
    
    public void write() throws IOException
    {
        if(os == null)
        {
            startTime = System.currentTimeMillis();
            createLogFile();
            writeHeader();
        }
        os.write(1);
        os.write((byte)(outpc++));
        os.write(mdb.cDesc.ochBuffer());
        os.flush();
    }

    private void writeHeader() throws IOException
    {
        String sig = mdb.cDesc.getSignature();

        int now = (int) (System.currentTimeMillis()/1000l);
        os.write(fileFormat);
        os.write(formatVersion);
        timeStamp[0]=(byte)(now>>24);
        timeStamp[1]=(byte)(now>>16);
        timeStamp[2]=(byte)(now>>8);
        timeStamp[3]=(byte)(now);
        os.write(timeStamp);
        System.arraycopy(sig.getBytes(), 0, firmware, 0, sig.length());
        os.write(firmware);
        os.write(beginIndex);
        int blockSize = mdb.cDesc.ochBlockSize(0);
        outputLength[0]=(byte)(blockSize>>8);
        outputLength[1]=(byte)(blockSize);
        os.write(outputLength);
        os.flush();
        outpc = 0;
    }

    private void createLogFile() throws FileNotFoundException
    {
        File dir = new File("/sdcard/MSParser");
        dir.mkdirs();
        
        Date now = new Date();
        
        String fileName = DateFormat.format("yyyyMMddkkmmss", now).toString()+".frd";
        
        logFile = new File(dir,fileName);

        os = new FileOutputStream(logFile);
    }
}
