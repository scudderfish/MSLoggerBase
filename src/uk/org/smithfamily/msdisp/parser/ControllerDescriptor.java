package uk.org.smithfamily.msdisp.parser;

import java.nio.ByteBuffer;
import java.util.*;

public class ControllerDescriptor
{
    private static final int maxPages   = 50;
    Map<String, Symbol>      symMap     = new HashMap<String, Symbol>();
    MsComm                   _io;
    // MT and MS memory are different.
    boolean                  _changed;
    // We should read and compare, if possible.
    boolean                  _verifying;
    boolean                  _bigEnd;
    byte[]                   _versionInfo;
    byte[]                   _queryCommand;
    ByteString               _signature;
    String                   _sigFile;

    int                      _nPages;
    ArrayList<PageInfo>      _page      = new ArrayList<PageInfo>(maxPages);
    // Internal symbols pointing to whole page.
    ArrayList<Symbol>        _wholePage = new ArrayList<Symbol>(maxPages);
    // Milliseconds between writes.
    int                      _interWriteDelay;
    // Use a single write or multi?
    boolean                  _writeBlocks;
    // Milliseconds after page activate command or burn command is sent.
    int                      _pageActivationDelay;
    // Total comm block timeout for a single read.
    int                      _blockReadTimeout;
    byte[]                   _ochGetCommand;
    byte[]                   _ochBurstCommand;
    int                      _ochBlockSize;
    byte[]                   _ochBuffer;

    public List<Double>      _userVar;
    int                      _userVarSize;
    private byte[]           _const;
    private Expression       _exprs;
    private int              lastPage;
    private boolean          force;

    public ControllerDescriptor(MsComm io)
    {
        this._io = io;
        _userVar = new ArrayList<Double>();
        for (int x = 0; x < maxPages; x++)
        {
            _page.add(new PageInfo());
            _wholePage.add(new Symbol());
        }
        _const = null;
        _changed = false;
        _interWriteDelay = 0;
        _writeBlocks = true;
        _pageActivationDelay = 0;
        _blockReadTimeout = 25;
        _userVarSize = 0;
        _sigFile = null;
        _verifying = false;
        // B&G MS-I V 2.xx and 3.00
        setEndianness("big");
        setVersionInfo("");
        setQueryCommand("Q");
        setSignature("", null);

        setOchBlockSize(22, 0);
        setOchGetCommand("A", 0);
        setOchBurstCommand("A", 0);

        setNPages(1);
        init();

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
        // _io.flush();
    }

    void init()
    {
        // _io.setChunking(_writeBlocks, _interWriteDelay);
        // _io.setReadTimeouts(_blockReadTimeout);

        final int n = totalSpace() < 257 ? 257 : totalSpace();
        _const = new byte[n];

        _ochBuffer = new byte[_ochBlockSize];
        int i;
        for (i = 0; i < _nPages; i++)
        {
            PageInfo p = new PageInfo();
            p._pp._pageNo = i;
            p._pp._bigEnd = _bigEnd;
            p._pp._modified = false;
            _page.add(i, p);
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
            try
            {
                _wholePage.get(i).setCArray("__page__", "U08", i, 0, "", shape, 1.0, 0.0, 0.0, 1.0, 0);
            }
            catch (SymbolException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
                            _exprs.addExpr(s.varIndex(), s.exprText(), s.exprFile(), s.exprLine());
                        }
                    }
                }
            }
        }
        try
        {
            _exprs.setOutputBuffer(_userVar);
            setBurnCommand("B", 0);
            setPageSize(125, 0);
            setPageActivate("P%1p", 0);
            setPageIdentifier("", 0);
            setPageReadWhole("V", 0);
            setPageWriteValue("W%1o%1v", 0);
        }
        catch (CommandException e)
        {
            e.printStackTrace();
        }
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

    private byte[] pageActivate(int pageNo)
    {
        pageNo = mxi(pageNo);
        PageInfo pageInfo = _page.get(pageNo);
        CommandFormat activate = pageInfo._activate;
        int siz = pageInfo.siz();
        PageParameters pp = pageInfo._pp;
        byte[] buildCmd = activate.buildCmd(pp, 0, null, siz);
        return buildCmd;
    }

    private void pageModified(int pageNo, boolean state)
    {
        _page.get(mxi(pageNo)).modified(state);
    }

    public int pageOffset(int pageNo)
    {
        return _page.get(mxi(pageNo)).ofs(0);
    }

    private byte[] pageReadWhole(int pageNo)
    {
        pageNo = mxi(pageNo);
        final PageInfo pageInfo = _page.get(pageNo);
        final CommandFormat _readWhole = pageInfo._readWhole;
        final int siz = pageInfo.siz();
        final byte[] buildCmd = _readWhole.buildCmd(pageInfo._pp, 0, null, siz);
        return buildCmd;
    }

    public int pageSize(int n)
    {
        return _page.get(mxi(n)).siz();
    }

    public boolean read(byte[] pBytes, int nBytes)
    {
        final boolean success = _io.read(pBytes, nBytes);
        if (!success)
        {
            lastPage = -99;
        }
        return success;
    }

    private boolean sendPageActivate(int thisPage, boolean b)
    {
        if (pageActivate(thisPage).length == 0)
        {
            return true;
        }

        if ((thisPage != lastPage) || force || b)
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
        final byte[] data = pageReadWhole(page);
        return _io.write(data);

    }

    public void setBlockReadTimeout(int i)
    {
        _blockReadTimeout = i;
    }

    public void setBurnCommand(String cmd, int i) throws CommandException
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

    public void setPageActivate(String cmd, int i) throws CommandException
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

    public void setPageReadChunk(String cmd, int i) throws CommandException
    {
        _page.get(i)._readChunk.parse(cmd);

    }

    public void setPageReadValue(String cmd, int i) throws CommandException
    {
        _page.get(i)._readValue.parse(cmd);

    }

    public void setPageReadWhole(String cmd, int i) throws CommandException
    {
        _page.get(i)._readWhole.parse(cmd);
    }

    public void setPageSize(double size, int i)
    {
        _page.get(i).siz((int) size);
        _page.get(i).ofs(i <= 0 ? 0 : _page.get(i - 1).ofs(0) + _page.get(i - 1).siz());

    }

    public void setPageWriteChunk(String cmd, int i) throws CommandException
    {
        _page.get(i)._writeChunk.parse(cmd);

    }

    public void setPageWriteValue(String cmd, int i) throws CommandException
    {
        _page.get(i)._writeValue.parse(cmd);

    }

    public void setPageWriteWhole(String cmd, int i) throws CommandException
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

    public String getSignature()
    {
        return _signature.toString();
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
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    int totalSpace()
    {
        int space = 0;
        for (PageInfo p : _page)
        {
            space += p.siz();
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

    public static byte[] xlate(String s)
    {
        ByteString bs = new ByteString(s).xlate(); // Translate \nnn into chars
                                                   // and all
        // that.
        return bs.bytes();
    }

    public void sendOchBurstCommand(int i)
    {
        _io.write(getOchBurstCommand(i));

    }

    private byte[] getOchBurstCommand(int i)
    {
        return _ochBurstCommand;
    }

    public void sendOchGetCommand(int i)
    {
        _io.write(ochGetCommand(i));

    }

    private byte[] ochGetCommand(int i)
    {
        return _ochGetCommand;
    }

    public void setOch(byte b, int i)
    {
        _ochBuffer[i] = b;
    }

    public byte[] ochBuffer()
    {
        return _ochBuffer;
    }

    public void populateUserVars()
    {

        for (String key : symMap.keySet())
        {
            Symbol s = symMap.get(key);
            if (s.isVar() && !s.isExpr())
            {
                _userVar.add(s.varIndex(), s.valueUser(0));
            }
        }

    }

    public void recalc()
    {
        _exprs.recalc();
    }

    public long getB(int _pageNo, int ofs, int db)
    {
        byte[] d = db == 0 ? _const : _ochBuffer;
        return d[ofs + pageOffset(mxi(_pageNo))];
    }

    public int getW(int pNo, int ofs, int db)
    {
        byte[] d = db == 0 ? _const : _ochBuffer;
        int v = 0;
        int po = pageOffset(mxi(pNo));
        byte[] b = new byte[2];
        b[0] = d[po + ofs + 0];
        b[1] = d[po + ofs + 1];
        if (_bigEnd)
            v = (int) bigEndIt(b, 2);
        return v;
    }

    private long bigEndIt(byte[] b, int numBytes)
    {
       long val = 0;
       for(int i = 0; i < numBytes;i++)
       {
           val = val * 256 + b[i];
       }
       return val;
    }

    public long getD(int _pageNo, int ofs, int db)
    {
        byte[] d = db == 0 ? _const : _ochBuffer;
        long v = 0;
        int po = pageOffset(mxi(_pageNo));
        byte[] b = new byte[4];
        b[0] = d[po + ofs + 0];
        b[1] = d[po + ofs + 1];
        b[2] = d[po + ofs + 2];
        b[3] = d[po + ofs + 3];
        if (_bigEnd)
            v = bigEndIt(b, 4);
        return v;
    }
}
