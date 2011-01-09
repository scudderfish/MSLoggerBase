package uk.org.smithfamily.msdisp.parser;

import java.util.*;

public class ControllerDescriptor
{
    Map<String, Symbol> symMap = new HashMap<String, Symbol>();
    MsComm _io;
    boolean       _changed;       // MT and MS memory are different.
    boolean       _verifying;     // We should read and compare, if possible.

    boolean       _bigEnd;
    ByteString _versionInfo;    // Command to get title string.
    ByteString _queryCommand;   // Command to return signature.
    ByteString _signature;
    String    _sigFile;

    int        _nPages;
    ArrayList<PageInfo>   _page;
    ArrayList<Symbol> _wholePage; // Internal symbols pointing to whole page.
    int        _interWriteDelay;     // Milliseconds between writes.
    boolean       _writeBlocks;         // Use a single write or multi?
    int        _pageActivationDelay; // Milliseconds after page activate command or burn command is sent.
    int        _blockReadTimeout;    // Total comm block timeout for a single read.

    ByteString _ochGetCommand; // ??? ARRAY, too.
    ByteString _ochBurstCommand;
    int        _ochBlockSize;
    short[]      _ochBuffer;

    Double _userVar;
    int        _userVarSize;
    private short[] _const;
    private Expression _exprs;

    
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
        for (int i = 0; i < _nPages; i++) space += _page.get(i).siz();
        return space;
    }
    void init()
    {
        _io.setChunking    (_writeBlocks, _interWriteDelay);
        _io.setReadTimeouts(_blockReadTimeout);

        int n = totalSpace() < 257 ? 257 : totalSpace();
        _const = new short[n];
        
        _ochBuffer = new short  [_ochBlockSize];
         int i;
        for (i = 0; i < _nPages; i++) {
           _page.get(i)._pp._pageNo   = i;
           _page.get(i)._pp._bigEnd   = _bigEnd;
           _page.get(i)._pp._modified = false;
        }

        for (i = 1; i < _nPages; i++) {
           _page.get(i).fillFrom(_page.get(i-1)); // ??? this is wack
        }

        for (i = 0; i < _nPages; i++) {
           _wholePage.add(i, new Symbol());
           String shape;
           shape="["+ _page.get(i).siz()+"]";
           _wholePage.get(i).setCArray ("__page__", "U08", i, 0, "", shape, 1.0, 0.0, 0.0, 1.0, 0);
        }

       
       
        // Parse the expressions now that we know where they all go.

        // this needs to parse the expression in the same order in which they are entered...

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
                       _exprs.addExpr(s.varIndex(), s.exprText(), s.exprFile(), s.exprLine());
                    }
                 }
              }
           }
        }
        _exprs.setOutputBuffer(_userVar);

    }
}
