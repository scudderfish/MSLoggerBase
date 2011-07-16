package uk.org.smithfamily.mslogger.parser.log;

import java.io.*;

import uk.org.smithfamily.mslogger.parser.MsDatabase;
import uk.org.smithfamily.mslogger.parser.Repository;

public enum Datalog
{
	INSTANCE;
    boolean                _recording     = false;
    File                   _recordFile    = null;
    FileWriter             out            = null;

    int                    _markerNumber  = 0;
    boolean                _lastMarkState = false;
    private MsDatabase     mdb            = MsDatabase.INSTANCE;
    private DatalogOptions lop;

   
    public boolean open(String fileName)
    {
        try
        {
            close();
            _recordFile = new File(fileName);

            if (_recordFile == null || !_recordFile.canWrite())
                return false;
            out = new FileWriter(_recordFile);

            out.write("\"" + mdb.cDesc.getSignature() + "\"\n");

            // if (lop._dumpBefore) mdb.dAll(_recordFile, true);
            _recording = true;
            Repository.INSTANCE.getLogFormat().header(out);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void close()
    {
        if (out != null)
			try
			{
				out.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        out = null;
        _recordFile = null;
    }

    public void write() throws IOException
    {
        if (_recording && out != null)
        {
   
            Repository.INSTANCE.getLogFormat().write(out);
            out.flush();
        }
    }
}
