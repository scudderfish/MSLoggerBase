package uk.org.smithfamily.msdisp.parser;

class symErr
{
	String msg;
	String txt;
	int pos;
	symErr(String m,String t,int p){msg=m;txt=t;pos=p;}
}
public class Symbol
{
    int         _sequence; // Order in which they were defined.

	String _name;
	String _units;
	boolean _; // True means in ants, false in output
				// channels/variables.

	int _pageNo; // Zero-based index into tables.
	int _offset; // Offset of first byte in this table.
	int _sizeOf; // Number of bytes in one value, 1, 2 or 4 are valid.
	boolean _signed; // Is data signed or unsigned?

	// -- User-units formatting information. -----------------------------------
	double _scale; // Scale of raw value.
	double _trans; // Translation of raw value.
	int _digits; // Display digits to right of decimal.
	double _round; // Fudge factor based upon digits count.
	double _lo, _hi; // Limit values.
	long _loRaw, _hiRaw; // Limits in internal format.

	// -- Data for array types. ------------------------------------------------
	boolean _xOrder; // Is table in x- or y-order?
	int _order; // Tensor order, 0=scalar.
	int[] _shape = new int[2]; // Size in up to two dimensions.

	// -- Data for bit fields. -------------------------------------------------
	int _bitLo; // Starting bit in a byte-base bitfield, zero-based.
	int _bitHi; // Ending bit in bitfield.
	int _bitOfs; // Offset for user value when extracted from bits.
					// For instance, MS-I stores nCylinders in 0:3 as 1..16,
					// so valueUser returns 0+1==1 for the first value.

	
	// -- Data for output channels. --------------------------------------------
	int _index; // Index into userVars data block, different than
				// _offset, which indexes into _ochRaw...
	String _expr;
	String _file;
	int _line;

	
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

    public void setCArray(String string, String string2, int i, int j,
            String string3, String shape, double d, double e, double f,
            double g, int k)
    {
        // TODO Auto-generated method stub
        
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

}
