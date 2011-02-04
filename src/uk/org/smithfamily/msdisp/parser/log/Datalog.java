package uk.org.smithfamily.msdisp.parser.log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.msdisp.parser.MsDatabase;

class DatalogList
{
    private List<DatalogEntry> ll = new ArrayList<DatalogEntry>();

    void add(DatalogEntry de)
    {
        ll.add(de);
    }

    void resolve()
    {
        for (DatalogEntry l : ll)
        {
            l.resolve();
        }
    }

    void header(BufferedWriter of) throws IOException
    {
        boolean needDelim = false;
        for (DatalogEntry l : ll)
        {
            if (needDelim)
            {
                of.write(DatalogOptions.getInstance()._delimiter);
            }
            needDelim = true;
            of.write(l.label());
        }
        of.write("\n");
    }

    void write(BufferedWriter of) throws IOException
    {
        for (int i = 0; i < columns(); i++)
        {
            if (i > 0)
                of.write(DatalogOptions.getInstance()._delimiter); // fputc('\t',
                                                                   // of);
            switch (ll.get(i).type())
            {
            case eInt:
                of.write(""
                        + MsDatabase.getInstance().cDesc._userVar.get(ll.get(i)
                                .ochIdx()));
                break;
            case eFloat:
                of.write(""
                        + MsDatabase.getInstance().cDesc._userVar.get(ll.get(i)
                                .ochIdx()));
                break;
            }
        }
        of.write("\n");
    }

    int columns()
    {
        return ll.size();
    }

}

public class Datalog
{
    boolean _recording;
    File    _recordFile;
    int     _markerNumber;
    boolean _lastMarkState;

    public Datalog()
    {

    }

    boolean open(String fileName)
    {

        return false;
    }

    void close()
    {

    }

    void write()
    {

    }

    void mark(String label)
    {

    }

}
