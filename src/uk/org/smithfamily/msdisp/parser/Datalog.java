package uk.org.smithfamily.msdisp.parser;

import java.io.File;

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

    }
};

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
