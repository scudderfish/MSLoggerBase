package uk.org.smithfamily.mslogger.parser.log;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.parser.MsDatabase;

public class DatalogList
{
    private List<DatalogEntry> ll  = new ArrayList<DatalogEntry>();
    final MsDatabase           mdb = MsDatabase.INSTANCE;

    public void add(DatalogEntry de)
    {
        ll.add(de);
    }

    public void resolve()
    {
        for (DatalogEntry l : ll)
        {
            l.resolve();
        }
    }

    void header(Writer of) throws IOException
    {
        boolean needDelim = false;
        for (DatalogEntry l : ll)
        {
            if (needDelim)
            {
                of.write(DatalogOptions.INSTANCE._delimiter);
            }
            needDelim = true;
            of.write(l.label());
        }
        of.write("\n");
    }

    void write(Writer of) throws IOException
    {
        for (int i = 0; i < columns(); i++)
        {
            if (i > 0)
                of.write(DatalogOptions.INSTANCE._delimiter); // fputc('\t',
                                                              // of);
            switch (ll.get(i).type())
            {
            case eInt:
                of.write("" + mdb.cDesc.getOutputChannels().get(ll.get(i).ochIdx()));
                break;
            case eFloat:
                of.write("" + mdb.cDesc.getOutputChannels().get(ll.get(i).ochIdx()));
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
