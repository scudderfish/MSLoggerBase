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
    private int lastPage;

    public ControllerDescriptor(MsComm io)
    {
        _userVar = new ArrayList<Double>();
    }

    public int varIndex(String currentStr)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Symbol lookup(String currentStr)
    {
        return symMap.get(currentStr);
    }

    int totalSpace()
    {
        int space = 0;
        for (int i = 0; i < _nPages; i++)
            space += _page.get(i).siz();
        return space;
    }

    void init()
    {
        _io.setChunking(_writeBlocks, _interWriteDelay);
        _io.setReadTimeouts(_blockReadTimeout);

        int n = totalSpace() < 257 ? 257 : totalSpace();
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
            for (String key : symMap.keySet())
            {
                Symbol s = symMap.get(key);
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

    public int nPages()
    {
       return _nPages;
    }

    public void changed(boolean b)
    {
       _changed = b;
    }

    public int pageSize(int n)
    {
       //TODO
        return 0;
    }

    public int pageOffset(int pageNo)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public void flush()
    {
        // TODO Auto-generated method stub
        
    }

    public void sendPageReadWhole(int pageNo)
    {
        // TODO Auto-generated method stub
        
    }

    public boolean read(ByteBuffer bytes, int nBytes)
    {
        boolean success = _io.read(bytes, nBytes);
        if (!success) lastPage = -99;
        return success;
    }
}
