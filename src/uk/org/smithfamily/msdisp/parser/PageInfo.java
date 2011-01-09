package uk.org.smithfamily.msdisp.parser;

public class PageInfo
{
    PageParameters _pp;
    CommandFormat  _activate;
    CommandFormat  _burnCommand;
    CommandFormat  _readWhole;
    CommandFormat  _readChunk;
    CommandFormat  _readValue;
    CommandFormat  _writeWhole;
    CommandFormat  _writeChunk;
    CommandFormat  _writeValue;

    int siz(int s)
    {
        if (s > 0)
            _pp._size = s;
        return _pp._size;
    }

    int ofs(int o)
    {
        if (o > 0)
            _pp._offset = o;
        return _pp._offset;
    }

    boolean modified()
    {
        return _pp._modified;
    }

    void modified(boolean m)
    {
        _pp._modified = m;
    }

    void fillFrom(PageInfo rhs)
    {

    }

    public int siz()
    {

        return siz(0);
    }

}
