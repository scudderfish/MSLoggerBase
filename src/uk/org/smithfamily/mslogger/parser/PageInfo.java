package uk.org.smithfamily.mslogger.parser;

import java.util.ArrayList;
import java.util.List;

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
    List<Symbol>   constSymbols = new ArrayList<Symbol>();

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

    @Override
    public String toString()
    {
        return "PageInfo [_pp=" + _pp + ", _activate=" + _activate + ", _burnCommand=" + _burnCommand + ", _readWhole="
                + _readWhole + ", _readChunk=" + _readChunk + ", _readValue=" + _readValue + ", _writeWhole=" + _writeWhole
                + ", _writeChunk=" + _writeChunk + ", _writeValue=" + _writeValue + "]";
    }

    public void addConstSymbol(Symbol s)
    {
        constSymbols.add(s);
    }
}
