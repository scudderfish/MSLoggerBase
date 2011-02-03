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

    void setBasic(String name, String type, int page, int offset, String units, boolean isConst) throws SymbolException
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
            throw new SymbolException("Missing 'S' or 'U' at start of type specification", ss);
        i++;
        char c = ss.charAt(i);
        if (!Character.isDigit(c))
            throw new SymbolException("Missing number in type specification", ss);
        int value = Integer.parseInt(ss.substring(i).trim());
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
            throw new SymbolException("Data size incorrect in type specification, must be 8, 16 or 32", ss);
        }
    }

    void setScalar(String name, String type, int page, double d, String units, double scale, double trans, double lo, double hi,
            double e, boolean isConst) throws SymbolException
    {
        setBasic(name, type, page, (int) d, units, isConst);
        _scale = scale;
        _trans = trans;
        _lo = lo;
        _hi = hi;
        if (e > 32)
            e = 32; // Try to make sure we don't make too-long strings.
        _digits = (int) e;
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
                _loRaw = _lo == noRange ? S08min : toIntr(_lo, Math.abs(_scale), _trans);
                _hiRaw = _hi == noRange ? S08max : toIntr(_hi, Math.abs(_scale), _trans);
                break;
            case 2:
                _loRaw = _lo == noRange ? S16min : toIntr(_lo, Math.abs(_scale), _trans);
                _hiRaw = _hi == noRange ? S16max : toIntr(_hi, Math.abs(_scale), _trans);
                break;
            case 4:
                _loRaw = _lo == noRange ? S32min : toIntr(_lo, Math.abs(_scale), _trans);
                _hiRaw = _hi == noRange ? S32max : toIntr(_hi, Math.abs(_scale), _trans);
                break;
            }
        }
        else
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

        return (char) ((Math.round(v) / s) - t);
    }

    void setCScalar(String name, String type, int page, double d, String units, double scale, double trans, double lo, double hi,
            double e) throws SymbolException
    {
        setScalar(name, type, page, d, units, scale, trans, lo, hi, e, true);
    }

    void setOScalar(String name, String type, int page, double e, String units, double scale, double trans)
            throws SymbolException
    {
        int d = 0;
        double s = scale;
        while (s > 0.0 && s < 1.0)
        {
            d++;
            s *= 10.0;
        }
        setScalar(name, type, page, e, units, scale, trans, noRange, noRange, d, false);
    }

    public void setArray(String name, String type, int page, double d, String units, String shape, double scale, double trans,
            double lo, double hi, double e, boolean isConst) throws SymbolException
    {
        setScalar(name, type, page, d, units, scale, trans, lo, hi, e, isConst);
        parseArraySpec(shape);
    }

    private void parseArraySpec(String shape)
    {
        _xOrder = true;
        _order = 0;
        _shape[0] = 1;
        _shape[1] = 1;

        char order = 'X';
        int colonpos = shape.indexOf(':');
        int xpos = shape.lastIndexOf('x');
        int openpos = shape.indexOf('[');
        int closepos = shape.indexOf(']');
        int x = 0, y = 0;
        if (colonpos > 0)
        {
            order = shape.charAt(colonpos - 1);
        }
        if (closepos < 0 || openpos < 0)
        {
            System.out.println("Cannot parse " + shape);
        }
        else
        {
            x = Integer.parseInt(shape.substring(Math.max(1, colonpos + 1), xpos < 0 ? closepos : xpos).trim());

            if (xpos > 0)
            {
                y = Integer.parseInt(shape.substring(xpos + 1, closepos).trim());
            }

            System.out.println("x=" + x + ", y=" + y + ", order=" + order);
        }
        _xOrder = order == 'X';
        _shape[0] = x;
        _order = 1;
        if (xpos > 0)
        {
            _order = 2;
            _shape[1] = y;
        }

    }

    public void setCArray(String name, String type, int page, double d, String units, String shape, double scale, double trans,
            double lo, double hi, double e) throws SymbolException
    {
        setArray(name, type, page, d, units, shape, scale, trans, lo, hi, e, true);

    }

    public void varIndex(int varIndex)
    {
        _index = varIndex;

    }

    public boolean isExpr()
    {
        return !(_expr == null || "".equals(_expr.trim()));
    }

    public void setCBits(String name, String type, int page, double offset, String bitSpec) throws SymbolException
    {
        setBits(name, type, page, offset, bitSpec, true);

    }

    private void setBits(String name, String type, int page, double offset, String bitSpec, boolean isConst) throws SymbolException
    {
        setBasic(name, type, page, (int) offset, "", isConst);
        parseBitSpec(bitSpec);
        _lo = 0;
        _hi = nValues() - 1;

    }

    // The "bitSpec" parameter takes the form '[N:N+O]' where 'N' are integer
    // bit offset values and O is a bit offset for computation of user values.

    private void parseBitSpec(String bitSpec) throws SymbolException
    {

        int openPos = bitSpec.indexOf('[');
        int closePos = bitSpec.indexOf(']');
        int colonPos = bitSpec.indexOf(':');
        int plusPos = bitSpec.indexOf('+');

        _bitLo = -1;
        _bitHi = -1;
        _bitOfs = 0;

        if (openPos < 0 || closePos < 0 || colonPos < 0 || plusPos < 0)
            throw new SymbolException("Cannot parse bitspec", bitSpec);

        _bitLo = Integer.parseInt(bitSpec.substring(openPos + 1, colonPos).trim());
        _bitHi = Integer.parseInt(bitSpec.substring(colonPos + 1, plusPos).trim());
        _bitOfs = Integer.parseInt(bitSpec.substring(plusPos + 1, closePos).trim());
    }

    public int nValues()
    {
        return isBits() ? (1 << (_bitHi - _bitLo + 1)) : (_shape[0] * _shape[1]);
    }

    private boolean isBits()
    {
        return _bitLo != -1 && _bitHi != -1;
    }

    public int offset(int index)
    {
        if (index < 0)
            index = 0;
        return _offset + index * _sizeOf;
    }

    public int size()
    {
        return _sizeOf * _shape[0] * _shape[1];
    }

    public void setOBits(String name, String type, int page, double d, String bitSpec) throws SymbolException
    {
        setBits(name, type, page, d, bitSpec, false);
    }

    static int exprIndex = 0;

    public void setExpr(String name, String expr, String units, String file, int line)
    {

        _sequence = exprIndex++;
        _name = name;
        _expr = expr;
        _const = false;
        _units = units;
        _file = file;
        _line = line;
    }

}
