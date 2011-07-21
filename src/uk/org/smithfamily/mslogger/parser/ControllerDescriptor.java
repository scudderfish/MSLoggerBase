package uk.org.smithfamily.mslogger.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.org.smithfamily.mslogger.parser.log.DebugLogManager;

import bsh.EvalError;
import bsh.Interpreter;

public class ControllerDescriptor
{
    private static final int maxPages        = 50;
    Map<String, Symbol>      symMap          = new HashMap<String, Symbol>();
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
    ArrayList<PageInfo>      _page           = new ArrayList<PageInfo>(maxPages);
    // Internal symbols pointing to whole page.
    ArrayList<Symbol>        _wholePage      = new ArrayList<Symbol>(maxPages);
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

    int                      _userVarSize;
    byte[]                   _const;
    private int              lastPage;
    private boolean          force;
    List<Symbol>             outputChannels  = new ArrayList<Symbol>();
    List<Symbol>             constantSymbols = new ArrayList<Symbol>();
    private List<Expression> expressions     = new ArrayList<Expression>();
    Interpreter              interpreter     = new Interpreter();
    private boolean          functionDefined;

    public ControllerDescriptor(MsComm io)
    {
        this._io = io;

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
        try
        {
            _io.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void init()
    {
        _io.setChunking(_writeBlocks, _interWriteDelay);
        _io.setReadTimeouts(_blockReadTimeout);

        final int n = totalSpace() < 257 ? 257 : totalSpace();
        _const = new byte[n];

        _ochBuffer = new byte[_ochBlockSize];
        int i = 0;

        for (PageInfo pi : this._page)
        {
            PageParameters pp = pi._pp;
            pp._pageNo = i++;
            pp._bigEnd = _bigEnd;
            pp._modified = false;

        }

        /*
         * for (i = 0; i < _nPages; i++) { PageInfo p = new PageInfo(); p._pp._pageNo = i; p._pp._bigEnd = _bigEnd; p._pp._modified = false; _page.add(i, p); }
         * 
         * for (i = 1; i < _nPages; i++) { _page.get(i).fillFrom(_page.get(i - 1)); // ??? this is wack }
         */
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

        List<Symbol> sortedSymbols = new ArrayList<Symbol>();
        sortedSymbols.addAll(symMap.values());

        int varIndex =0;
        for(Symbol s : getOutputChannels())
        {
            s.varIndex(varIndex++);
        }
        Collections.sort(sortedSymbols, new Comparator<Symbol>()
        {
            @Override
            public int compare(Symbol arg0, Symbol arg1)
            {
                if (arg0._sequence > arg1._sequence)
                    return 1;
                if (arg0._sequence < arg1._sequence)
                    return -1;
                return 0;
            }
        });
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
        PageInfo pageInfo = _page.get(i);
        pageInfo._burnCommand.parse(cmd);
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
        PageInfo pageInfo = _page.get(i);
        pageInfo._activate.parse(cmd);

    }

    public void setPageActivationDelay(int i)
    {
        _pageActivationDelay = i;

    }

    public void setPageIdentifier(String cmd, int i)
    {
        PageInfo pageInfo = _page.get(i);
        pageInfo._pp._pageIdentifier = xlate(cmd);

    }

    public void setPageReadChunk(String cmd, int i) throws CommandException
    {
        PageInfo pageInfo = _page.get(i);
        pageInfo._readChunk.parse(cmd);

    }

    public void setPageReadValue(String cmd, int i) throws CommandException
    {
        PageInfo pageInfo = _page.get(i);

        pageInfo._readValue.parse(cmd);

    }

    public void setPageReadWhole(String cmd, int i) throws CommandException
    {
        PageInfo pageInfo = _page.get(i);

        pageInfo._readWhole.parse(cmd);
    }

    public void setPageSize(double size, int i)
    {
        PageInfo currentPage = _page.get(i);
        currentPage.siz((int) size);
        if (i > 0)
        {
            PageInfo previousPage = _page.get(i - 1);
            int offset = previousPage.ofs(0) + previousPage.siz();
            currentPage.ofs(offset);
        }
        else
        {
            currentPage.ofs(0);
        }
    }

    public void setPageWriteChunk(String cmd, int i) throws CommandException
    {
        PageInfo pageInfo = _page.get(i);
        pageInfo._writeChunk.parse(cmd);

    }

    public void setPageWriteValue(String cmd, int i) throws CommandException
    {
        PageInfo pageInfo = _page.get(i);
        pageInfo._writeValue.parse(cmd);

    }

    public void setPageWriteWhole(String cmd, int i) throws CommandException
    {
        PageInfo pageInfo = _page.get(i);
        pageInfo._writeWhole.parse(cmd);

    }

    public void addConstSymbol(Symbol s, int pageNo)
    {
        PageInfo pageInfo = _page.get(pageNo);

        pageInfo.addConstSymbol(s);
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
        Symbol symbol = symMap.get(name);
        if (symbol == null)
        {
            // msgOk("Symbol Lookup", CString("Couldn't find "+name));
            return -1;
        }
        // assert(!symbolTable[name]->isConst()); // This is often false, since
        // this function is called from expression parsing to find out if indeed
        // the symbol is a variable or not.
        return symbol.varIndex();
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
        for (int i = 0; i < numBytes; i++)
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

    public void addOutput(Symbol s)
    {
        outputChannels.add(s);

        if (!s.isVar())
            expressions.add(new Expression(s));
    }

    public List<Symbol> getOutputChannels()
    {
        return outputChannels;
    }

    public List<Expression> getExpressions()
    {
        return expressions;

    }

    public void populateUserVars()
    {
        int x = 1;
        for (Symbol s : outputChannels)
        {
            if (s.isVar() && !s.isExpr())
            {
                int value = s.valueFromRaw();
                String name = s.name();
                // System.out.println(name + "=" + value);
                try
                {
                    interpreter.set(name, value);
                }
                catch (EvalError e)
                {
                    DebugLogManager.INSTANCE.log(
                            "Error tyrying to set " + name + "=" + value + " : " + e.getLocalizedMessage());
                }
            }
        }

    }

    public void recalc()
    {
        if (functionDefined)
        {
            try
            {
                interpreter.eval("runtimeExpressions()");
            }
            catch (EvalError e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public double getValue(String name)
    {
        double value = 0;
        try
        {
            value = getValue(interpreter.get(name));
        }
        catch (EvalError e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

    private double getValue(Object o)
    {
        if (o instanceof Integer)
            return (Integer) o;
        if (o instanceof Double)
            return (Double) o;
        if (o instanceof Float)
            return (Float) o;

        return 1.0;
    }

    public void addConstantSymbol(Symbol s, int pageNo)
    {
        this.addSymbol(s);
        constantSymbols.add(s);
        PageInfo pi = _page.get(pageNo);
        pi.addConstSymbol(s);
        // DebugLogManager.INSTANCE.log("Added " + s + " to page " + pageNo);
    }

    public void updateConstPage(int i)
    {
        PageInfo pi = _page.get(i);
        for (Symbol constSym : pi.constSymbols)
        {
            String cmd = constSym._name + " = " + constSym.valueFromRaw() + ";";
            try
            {
                // DebugLogManager.INSTANCE.log("Update const : " + cmd);
                interpreter.set(constSym._name, constSym.valueFromRaw());
            }
            catch (EvalError e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void generateRuntimeFunction()
    {
        for (int iPage = 0; iPage < nPages(); iPage++)
        {
            updateConstPage(iPage);
        }
        Set<String> needfulThings = new HashSet<String>();
        needfulThings.addAll(Arrays.asList(new String[] { "afr2", "lambda2", "rpm", "coolant", "mat", "advance", "map" }));

        populateUserVars();
        List<Expression> duff = new ArrayList<Expression>();
        List<Expression> runTime = new ArrayList<Expression>();
        do
        {
            duff.clear();
            for (Expression expr : expressions)
            {
                try
                {
                    String shellExpression = expr.getShellExpression();
                    interpreter.eval(shellExpression);
                }
                catch (EvalError e)
                {
                    // TODO Auto-generated catch block
                    DebugLogManager.INSTANCE.log("Error evaluating " + expr.getShellExpression() + " :: " + e.getErrorText());
                    e.printStackTrace();
                    duff.add(expr);
                }
                if (needfulThings.contains(expr.getName()) && !runTime.contains(expr.getName()))
                {
                    runTime.add(expr);
                }
            }
            if (duff.size() > 0)
            {
                expressions.removeAll(duff);
            }
        }
        while (duff.size() > 0);

        String func = "void runtimeExpressions(){\n";
        for (Expression expr : runTime)
        {
            func += expr.getShellExpression() + ";\n";
        }
        func += "}\n";
        DebugLogManager.INSTANCE.log(func);
        try
        {
            interpreter.eval(func);
        }
        catch (EvalError e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        functionDefined = true;

        func = "void logExpressions(){\n";
        for (Expression expr : expressions)
        {
            func += expr.getShellExpression() + ";\n";
        }
        func += "}\n";
        DebugLogManager.INSTANCE.log(func);
        try
        {
            interpreter.eval(func);
        }
        catch (EvalError e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
