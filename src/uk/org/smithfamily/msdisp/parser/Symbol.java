package uk.org.smithfamily.msdisp.parser;

import java.util.ArrayList;
import java.util.List;

class symErr
{
    String msg;
    String txt;
    int    pos;

    symErr(String m, String t, int p)
    {
        msg = m;
        txt = t;
        pos = p;
    }
}

public class Symbol
{
    int                  _sequence;                            // Order in
                                                                // which they
                                                                // were defined.

    String               _name;
    String               _units;
    boolean              _;                                    // True means in
                                                                // ants, false
                                                                // in output
                                                                // channels/variables.

    int                  _pageNo;                              // Zero-based
                                                                // index into
                                                                // tables.
    int                  _offset;                              // Offset of
                                                                // first byte in
                                                                // this table.
    int                  _sizeOf;                              // Number of
                                                                // bytes in one
                                                                // value, 1, 2
                                                                // or 4 are
                                                                // valid.
    boolean              _signed;                              // Is data
                                                                // signed or
                                                                // unsigned?

    // -- User-units formatting information. -----------------------------------
    double               _scale;                               // Scale of raw
                                                                // value.
    double               _trans;                               // Translation
                                                                // of raw value.
    int                  _digits;                              // Display
                                                                // digits to
                                                                // right of
                                                                // decimal.
    double               _round;                               // Fudge factor
                                                                // based upon
                                                                // digits count.
    double               _lo, _hi;                             // Limit values.
    long                 _loRaw, _hiRaw;                       // Limits in
                                                                // internal
                                                                // format.

    // -- Data for array types. ------------------------------------------------
    boolean              _xOrder;                              // Is table in
                                                                // x- or
                                                                // y-order?
    int                  _order;                               // Tensor order,
                                                                // 0=scalar.
    int[]                _shape      = new int[2];             // Size in up to
                                                                // two
                                                                // dimensions.

    // -- Data for bit fields. -------------------------------------------------
    int                  _bitLo;                               // Starting bit
                                                                // in a
                                                                // byte-base
                                                                // bitfield,
                                                                // zero-based.
    int                  _bitHi;                               // Ending bit in
                                                                // bitfield.
    int                  _bitOfs;                              // Offset for
                                                                // user value
                                                                // when
                                                                // extracted
                                                                // from bits.
                                                                // For instance,
                                                                // MS-I stores
                                                                // nCylinders in
                                                                // 0:3 as 1..16,
                                                                // so valueUser
                                                                // returns
                                                                // 0+1==1 for
                                                                // the first
                                                                // value.

    // -- Data for output channels. --------------------------------------------
    int                  _index;                               // Index into
                                                                // userVars data
                                                                // block,
                                                                // different
                                                                // than
                                                                // _offset,
                                                                // which indexes
                                                                // into
                                                                // _ochRaw...
    String               _expr;
    String               _file;
    int                  _line;
    public List<String>  userStrings = new ArrayList<String>();
    private final double noRange     = 123E45;
    private boolean      _const;
    char                 U08min      = (0);
    short                U16min      = (0);
    long                 U32min      = (0);
    char                 U08max      = (0xff);
    int                  U16max      = (0xffff);
    long                 U32max      = (0xffffffff);

    char                 S08min      = (0x80);
    int                  S16min      = (0x8000);
    long                 S32min      = (0x80000000);
    char                 S08max      = (0x7f);
    short                S16max      = (0x7fff);
    long                 S32max      = (0x7fffffff);

    String name()
    {
        return _name;
    }

    String units()
    {
        return _units;
    }

    double lo()
    {
        return _lo;
    }

    double hi()
    {
        return _hi;
    }

    int digits()
    {
        return _digits;
    }

    double scale()
    {
        return _scale;
    }

    double trans()
    {
        return _trans;
    }

    int bitOfs()
    {
        return _bitOfs;
    }

    int sizeOf()
    {
        return _sizeOf;
    }

    int page()
    {
        return _pageNo;
    }

    int varIndex()
    {
        return _index;
    } // Index into och userVars.

    String exprText()
    {
        return _expr;
    }

    String exprFile()
    {
        return _file;
    }

    int exprLine()
    {
        return _line;
    }

    void setBasic(String name, String type, int page, int offset, String units,
            boolean isConst) throws SymbolException
    {
        _sequence = -1;
        _name = name;
        _units = units;
        _pageNo = page;
        _offset = offset;
        _const = isConst;
        parseTypeSpec(type);
    }

    void parseTypeSpec(String ss) throws SymbolException
    {
        _signed = false;
        _sizeOf = 1;

        int i = 0;
        while (ss.charAt(i) == ' ')
            i++;

        if (ss.charAt(i) == 'S' || ss.charAt(i) == 'U')
            _signed = (ss.charAt(i) == 'S');
        else
            throw new SymbolException(
                    "Missing 'S' or 'U' at start of type specification", ss);
        i++;
        char c = ss.charAt(i);
        if (!Character.isDigit(c))
            throw new SymbolException("Missing number in type specification",
                    ss);
        int value = Integer.parseInt(ss.substring(i));
        switch (value)
        {
        case 8:
            _sizeOf = 1;
            break;
        case 16:
            _sizeOf = 2;
            break;
        case 32:
            _sizeOf = 4;
            break;
        default:
            throw new SymbolException(
                    "Data size incorrect in type specification, must be 8, 16 or 32",
                    ss);
        }
    }
    
    void setScalar(String name, String type, int page, int offset,
            String units, double scale, double trans, double lo, double hi,
            int digits, boolean isConst) throws SymbolException
    {
        setBasic(name, type, page, offset, units, isConst);
        _scale = scale;
        _trans = trans;
        _lo = lo;
        _hi = hi;
        if (digits > 32)
            digits = 32; // Try to make sure we don't make too-long strings.
        _digits = digits;
        _round = Math.abs(_scale / 2.0);
        setRawLimits();
    }

    void setRawLimits()
    {
        if (_signed)
        {
            switch (_sizeOf)
            {
            case 1:
                _loRaw = _lo == noRange ? S08min : toIntr(_lo,
                        Math.abs(_scale), _trans);
                _hiRaw = _hi == noRange ? S08max : toIntr(_hi,
                        Math.abs(_scale), _trans);
                break;
            case 2:
                _loRaw = _lo == noRange ? S16min : toIntr(_lo,
                        Math.abs(_scale), _trans);
                _hiRaw = _hi == noRange ? S16max : toIntr(_hi,
                        Math.abs(_scale), _trans);
                break;
            case 4:
                _loRaw = _lo == noRange ? S32min : toIntr(_lo,
                        Math.abs(_scale), _trans);
                _hiRaw = _hi == noRange ? S32max : toIntr(_hi,
                        Math.abs(_scale), _trans);
                break;
            }
        } else
        {
            switch (_sizeOf)
            {
            case 1:
                _loRaw = _lo == noRange ? U08min : toIntr(_lo, _scale, _trans);
                _hiRaw = _hi == noRange ? U08max : toIntr(_hi, _scale, _trans);
                break;
            case 2:
                _loRaw = _lo == noRange ? U16min : toIntr(_lo, _scale, _trans);
                _hiRaw = _hi == noRange ? U16max : toIntr(_hi, _scale, _trans);
                break;
            case 4:
                _loRaw = _lo == noRange ? U32min : toIntr(_lo, _scale, _trans);
                _hiRaw = _hi == noRange ? U32max : toIntr(_hi, _scale, _trans);
                break;
            }
        }
    }

    private char toIntr(double v, double s, double t)
    {
        
        return (char) ((Math.round(v)/s)-t);
    }

    void setCScalar(String name, String type, int page, int offset,
            String units, double scale, double trans, double lo, double hi,
            int digits) throws SymbolException
    {
        setScalar(name, type, page, offset, units, scale, trans, lo, hi,
                digits, true);
    }

    void setOScalar(String name, String type, int page, int offset,
            String units, double scale, double trans) throws SymbolException
    {
        int d = 0;
        double s = scale;
        while (s > 0.0 && s < 1.0)
        {
            d++;
            s *= 10.0;
        }
        setScalar(name, type, page, offset, units, scale, trans, noRange,
                noRange, d, false);
    }

    public void setArray(String name, String type, int page, int offset,
            String units, String shape, double scale, double trans, double lo,
            double hi, int digits, boolean isConst) throws SymbolException
    {
        setScalar(name, type, page, offset, units, scale, trans, lo, hi,
                digits, isConst);
        parseArraySpec(shape);
    }

    private void parseArraySpec(String shape)
    {
        _xOrder   = true;
        _order    = 0;
        _shape[0] = 1;
        _shape[1] = 1;

        char order = 'X';
        int colonpos = shape.indexOf(':');
        int xpos = shape.lastIndexOf('x');
        int openpos = shape.indexOf('[');
        int closepos = shape.indexOf(']');
        int x,y;
        if(colonpos > 0)
        {
            order = shape.charAt(colonpos-1);
        }
        if (closepos < 0 || openpos < 0)
        {
            System.out.println("Cannot parse " + shape);
        } else
        {
            x = Integer.parseInt(shape.substring(Math.max(1, colonpos+1), xpos));

            y = Integer.parseInt(shape.substring(xpos + 1, closepos));

            System.out.println("x=" + x + ", y=" + y+", order="+order);
        }
        _xOrder = order == 'X';
        _shape[0] = x;
        _order = 1;
        _shape[1] = y;
        int i = 0;
        while (shape.charAt(i) == ' ')
            i++;

        if (shape.charAt(i) !='[') throw new SymbolException("Missing opening '['",shape);
        i++;
        
        if ( shape.charAt(i) == 'Y' || shape.charAt(i) == 'X') 
        {
           _xOrder = shape.charAt(i) == 'X';
           i++; // Skip the X or Y.
           if (shape.charAt(i) != ':') throw new SymbolException("Missing ':' after order specifier",shape);
           i++; // Skip the colon.
        }

        if (notDigit(*s)) throw symErr("Missing number in first dimension", o, s-o);
        _shape[0] = strtol(s, const_cast<char **>(&s), 10);
        _order    = 1;
        skipSp; // Past any trailing blanks.

        if (isChar(*s, ']')) {
           next; // Past the closing ].
        }
        else {
           if (notChar(*s, 'X')) throw symErr("Missing 'x' or ']' after first dimension", o, s-o);
           next; // Skip the x.
           if (notDigit(*s)) throw symErr("Missing number in second dimension", o, s-o);
           _shape[1] = strtol(s, const_cast<char **>(&s), 10);
           _order    = 2;
           if (notChar(*s, ']')) throw symErr("Missing closing bracket ']' after second dimension", o, s-o);
           next; // Past the closing ].
        }
        if (*s) throw symErr("Useless junk in array specification", o, s-o);
        
    }

    public void setCArray(String name, String type, int page, int offset,
            String units, String shape, double scale, double trans, double lo,
            double hi, int digits) throws SymbolException
    {
        setArray(name, type, page, offset, units, shape, scale, trans, lo, hi,
                digits, true);

    }

    public void varIndex(int varIndex)
    {
        // TODO Auto-generated method stub

    }

    public boolean isExpr()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public void setCScalar(String string, String string2, int currentCP,
            double v, String string3, double v2, double v3, double v4,
            double v5, double v6)
    {
        // TODO Auto-generated method stub

    }

    public void setCArray(String string, String string2, int currentCP,
            double v, String string3, String shape, double v2, double v3,
            double v4, double v5, double v6)
    {
        // TODO Auto-generated method stub

    }

    public void setCBits(String string, String string2, int currentCP,
            double v, String string3)
    {
        // TODO Auto-generated method stub

    }

    public int nValues()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public int offset()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public int size()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setOScalar(String string, String string2, int i, double v,
            String string3, double v2, double v3)
    {
        // TODO Auto-generated method stub

    }

    public void setOBits(String string, String string2, int i, double v,
            String string3)
    {
        // TODO Auto-generated method stub

    }

    public void setExpr(String string, String stripped, String string2,
            String fileName, int lineNo)
    {
        // TODO Auto-generated method stub

    }

}
