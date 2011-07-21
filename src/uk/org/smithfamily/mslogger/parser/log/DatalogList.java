package uk.org.smithfamily.mslogger.parser.log;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.parser.MsDatabase;
import uk.org.smithfamily.mslogger.parser.Symbol;

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
        List<Symbol> outputChannels = mdb.cDesc.getOutputChannels();
        for (int i = 0; i < columns(); i++)
        {
            if (i > 0)
                of.write(DatalogOptions.INSTANCE._delimiter); // fputc('\t',
                                                              // of);
            
            DatalogEntry datalogEntry = ll.get(i);
            int ochIdx = datalogEntry.ochIdx();
            
            switch (datalogEntry.type())
            {
            case eInt:
                of.write("" + (int)outputChannels.get(ochIdx).getValue());
                break;
            case eFloat:
                of.write(String.format(datalogEntry.format(),  outputChannels.get(ochIdx).getValue()));
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
