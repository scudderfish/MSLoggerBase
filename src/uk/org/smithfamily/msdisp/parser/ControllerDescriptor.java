package uk.org.smithfamily.msdisp.parser;

import java.nio.ByteBuffer;
import java.util.*;

public class ControllerDescriptor
{
    Map<String, Symbol> symMap = new HashMap<String, Symbol>();
    MsComm              _io;
    // MT and MS memory are different.
    boolean             _changed;
    // We should read and compare, if possible.
    boolean             _verifying;
    boolean             _bigEnd;
    ByteString          _versionInfo;
    ByteString          _queryCommand;
    ByteString          _signature;
    String              _sigFile;

    int                 _nPages;
    ArrayList<PageInfo> _page;
    // Internal symbols pointing to whole page.
    ArrayList<Symbol>   _wholePage;
    // Milliseconds between writes.
    int                 _interWriteDelay;
    // Use a single write or multi?
    boolean             _writeBlocks;
    // Milliseconds after page activate command or burn command is sent.
    int                 _pageActivationDelay;
    // Total comm block timeout for a single read.
    int                 _blockReadTimeout;
    ByteString          _ochGetCommand;
    ByteString          _ochBurstCommand;
    int                 _ochBlockSize;
    short[]             _ochBuffer;

    List<Double>        _userVar;
    int                 _userVarSize;
    private short[]     _const;
    private Expression  _exprs;
    private int         lastPage;
    private boolean     force;

    public ControllerDescriptor(MsComm io)
    {
        _userVar = new ArrayList<Double>();
    }

    public void addSymbol(Symbol s)
    {
        this.symMap.put(s.name(), s);
    }

    public void changed(boolean b)
    {
        _changed = b;
    }

    public void flush()
    {
        _io.flush();
    }

    void init()
    {
        _io.setChunking(_writeBlocks, _interWriteDelay);
        _io.setReadTimeouts(_blockReadTimeout);

        final int n = totalSpace() < 257 ? 257 : totalSpace();
        _const = new short[n];

        _ochBuffer = new short[_ochBlockSize];
        int i;
        for (i = 0; i < _nPages; i++)
        {
            _page.get(i)._pp._pageNo = i;
            _page.get(i)._pp._bigEnd = _bigEnd;
            _page.get(i)._pp._modified = false;
        }

        for (i = 1; i < _nPages; i++)
        {
            _page.get(i).fillFrom(_page.get(i - 1)); // ??? this is wack
        }

        for (i = 0; i < _nPages; i++)
        {
            _wholePage.add(i, new Symbol());
            String shape;
            shape = "[" + _page.get(i).siz() + "]";
            _wholePage.get(i).setCArray("__page__", "U08", i, 0, "", shape,
                    1.0, 0.0, 0.0, 1.0, 0);
        }

        // Parse the expressions now that we know where they all go.

        // this needs to parse the expression in the same order in which they
        // are entered...

        _exprs = new Expression();

        for (int index = 0; index < symMap.size(); index++)
        {
            for (final String key : symMap.keySet())
            {
                final Symbol s = symMap.get(key);
                if (s._sequence == index)
                {
                    if (s.isExpr())
                    {
                        if (!"---".equals(s.exprText()))
                        {
                            _exprs.addExpr(s.varIndex(), s.exprText(),
                                    s.exprFile(), s.exprLine());
                        }
                    }
                }
            }
        }
        _exprs.setOutputBuffer(_userVar);

    }

    public Symbol lookup(String currentStr)
    {
        return symMap.get(currentStr);
    }

    int mxi(int pageNo)
    {
        return pageNo < _nPages ? pageNo : _nPages - 1;
    }

    public int nPages()
    {
        return _nPages;
    }

    public int ochBlockSize(int i)
    {
        return _ochBlockSize;
    }

    private ByteString pageActivate(int pageNo)
    {
        pageNo = mxi(pageNo);
        return _page.get(pageNo)._activate.buildCmd(_page.get(pageNo)._pp, 0,
                null, _page.get(pageNo).siz());
    }

    private void pageModified(int pageNo, boolean state)
    {
        _page.get(mxi(pageNo)).modified(state);
    }

    public int pageOffset(int pageNo)
    {
        return _page.get(mxi(pageNo)).ofs(0);
    }

    private ByteString pageReadWhole(int pageNo)
    {
        pageNo = mxi(pageNo);
        return _page.get(pageNo)._readWhole.buildCmd(_page.get(pageNo)._pp, 0,
                null, _page.get(pageNo).siz());
    }

    public int pageSize(int n)
    {
        return _page.get(mxi(n)).siz();
    }

    public boolean read(ByteBuffer bytes, int nBytes)
    {
        final boolean success = _io.read(bytes, nBytes);
        if (!success)
        {
            lastPage = -99;
        }
        return success;
    }

    private boolean sendPageActivate(int thisPage, boolean b)
    {
        if (pageActivate(thisPage).empty())
        {
            return true;
        }

        if ((thisPage != lastPage) || force)
        {
            lastPage = thisPage;
            final boolean success = _io.write(pageActivate(thisPage));
            if (success && (_pageActivationDelay != 0))
            {
                Sleep(_pageActivationDelay);
            }
            return success;
        }
        return true;

    }

    public boolean sendPageReadWhole(int page)
    {
        pageModified(page, false);
        sendPageActivate(page, true);
        return _io.write(pageReadWhole(page));

    }

    public void setBlockReadTimeout(int i)
    {
        _blockReadTimeout = i;
    }

    public void setBurnCommand(String cmd, int i)
    {
        _page.get(i)._burnCommand.parse(cmd);
    }

    public void setDelay(int delay)
    {
        _interWriteDelay = delay;
    }

    public void setEndianness(String string)
    {
        this._bigEnd = string.toLowerCase().startsWith("big");

    }

    public void setNPages(double v)
    {
        _nPages = (int) v;

    }

    public void setOchBlockSize(double nBytes, int i)
    {
        _ochBlockSize = (int) nBytes;
    }

    public void setOchBurstCommand(String cmd, int i)
    {
        _ochBurstCommand = xlate(cmd);

    }

    public void setOchGetCommand(String cmd, int i)
    {
        _ochGetCommand = xlate(cmd);

    }

    public void setPageActivate(String cmd, int i)
    {
        _page.get(i)._activate.parse(cmd);

    }

    public void setPageActivationDelay(int i)
    {
        _pageActivationDelay = i;

    }

    public void setPageIdentifier(String cmd, int i)
    {
        _page.get(i)._pp._pageIdentifier = xlate(cmd);

    }

    public void setPageReadChunk(String cmd, int i)
    {
        _page.get(i)._readChunk.parse(cmd);

    }

    public void setPageReadValue(String cmd, int i)
    {
        _page.get(i)._readValue.parse(cmd);

    }

    public void setPageReadWhole(String cmd, int i)
    {
        _page.get(i)._readWhole.parse(cmd);
    }

    public void setPageSize(double size, int i)
    {
        _page.get(i).siz((int) size);
        _page.get(i).ofs(
                i <= 0 ? 0 : _page.get(i - 1).ofs(0) + _page.get(i - 1).siz());

    }

    public void setPageWriteChunk(String cmd, int i)
    {
        _page.get(i)._writeChunk.parse(cmd);

    }

    public void setPageWriteValue(String cmd, int i)
    {
        _page.get(i)._writeValue.parse(cmd);

    }

    public void setPageWriteWhole(String cmd, int i)
    {
        _page.get(i)._writeWhole.parse(cmd);

    }

    public void setQueryCommand(String cmd)
    {
        _queryCommand = xlate(cmd);

    }

    public void setSignature(String sig, String fileName)
    {
        _signature = new ByteString(sig);
        _signature.xlate();

        _sigFile = fileName;

    }

    public void setVerify(boolean eq)
    {
        _verifying = eq;
    }

    public void setVersionInfo(String cmd)
    {
        _versionInfo = xlate(cmd);
    }

    public void setWriteBlocks(boolean blocks)
    {
        _writeBlocks = blocks;
    }

    private void Sleep(int period)
    {
        try
        {
            Thread.sleep(period);
        } catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    int totalSpace()
    {
        int space = 0;
        for (int i = 0; i < _nPages; i++)
        {
            space += _page.get(i).siz();
        }
        return space;
    }

    public int varIndex(String name)
    {
        if (symMap.get(name) == null)
        {
            // msgOk("Symbol Lookup", CString("Couldn't find "+name));
            return -1;
        }
        // assert(!symbolTable[name]->isConst()); // This is often false, since
        // this function is called from expression parsing to find out if indeed
        // the symbol is a variable or not.
        return symMap.get(name).varIndex();
    }

    private ByteString xlate(String s)
    {
        return new ByteString(s).xlate(); // Translate \nnn into chars and all
                                          // that.
    }
}
