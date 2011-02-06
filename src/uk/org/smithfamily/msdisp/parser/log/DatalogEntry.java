package uk.org.smithfamily.msdisp.parser.log;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import uk.org.smithfamily.msdisp.parser.Repository;

class DatalogEntry
{
    public enum entryType
    {
        eFloat, eInt
    };

    private String    _ochName; // time
    private int       _och;    // 0
    private String    _label;  // "Time"
    private entryType _type;   // Above
    private String    _format; // "%.3f"

    public DatalogEntry(String nm, String lb, String tp, String fm)
    {
        _ochName = nm;
        _och = 0;
        _label = lb;
        _format = fm;

        if (tp.equalsIgnoreCase("float"))
            _type = entryType.eFloat;
        else if (tp.equalsIgnoreCase("int"))
            _type = entryType.eInt;
        else
        {
            // msgOk("Custom Datalog",
            // "Datatype '%s' should be either 'float' or 'int'", tp);
            _type = entryType.eInt;
        }

    }

    String name()
    {
        return _ochName;
    }

    String label()
    {
        return _label;
    }

    int ochIdx()
    {
        return _och;
    }

    String format()
    {
        return _format;
    }

    entryType type()
    {
        return _type;
    }

    void resolve()
    {
        final MsDatabase mdb = MsDatabase.getInstance();
        _och = mdb.cDesc.varIndex(name());
        if (_och == -1)
        {
            // msgOk("Custom Datalog",
            // "Output channel '%s' cannot be found for datalog entry labeled '%s'",
            // name(), label());
            _och = Repository.Uundefined;
        }
    }
}