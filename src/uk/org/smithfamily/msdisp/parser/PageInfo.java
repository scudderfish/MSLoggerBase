package uk.org.smithfamily.msdisp.parser;

public class PageInfo
{
    PageParameters _pp          = new PageParameters();
    CommandFormat  _activate    = new CommandFormat();
    CommandFormat  _burnCommand = new CommandFormat();
    CommandFormat  _readWhole   = new CommandFormat();
    CommandFormat  _readChunk   = new CommandFormat();
    CommandFormat  _readValue   = new CommandFormat();
    CommandFormat  _writeWhole  = new CommandFormat();
    CommandFormat  _writeChunk  = new CommandFormat();
    CommandFormat  _writeValue  = new CommandFormat();

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
