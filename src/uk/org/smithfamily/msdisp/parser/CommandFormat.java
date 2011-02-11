package uk.org.smithfamily.msdisp.parser;

import java.util.List;

public class CommandFormat
{
    boolean _empty;

    int     _dSiz;                // Number of bytes in raw data part.

    int     _pOfs;                // Location of bytes in command.
    int     _pSiz;                // Size of data for this part of command.
    int     _iOfs;                // Page ID, might be different than page
                                   // number.
    int     _iSiz;
    int     _cOfs;                // Byte count, used in "chunk" write
                                   // commands.
    int     _cSiz;
    int     _oOfs;                // Offset part
    int     _oSiz;
    int     _vOfs;                // Address part
    int     _vSiz;                // Size of one value in bytes.
    int     _vCnt;                // Number of values (not bytes).

   
    public CommandFormat()
    {
        _empty = true;
        _pOfs = -1;
        _pSiz = 0;
        _iOfs = -1;
        _iSiz = 0;
        _cOfs = -1;
        _cSiz = 0;
        _oOfs = -1;
        _oSiz = 0;
        _vOfs = -1;
        _vSiz = 0;
    }

    static void bigEndIt(byte[] dw, int nBytes)
    {
        byte sb;
        switch (nBytes)
        {
        case 1:
            // Do nothing, first byte already holds value.
            break;
        case 2:
            // Swap first two bytes.
            sb = dw[0];
            dw[0] = dw[1];
            dw[1] = sb;
            break;
        case 4:
            // Reverse all four bytes.
            sb = dw[0];
            dw[0] = dw[3];
            dw[3] = sb;
            sb = dw[1];
            dw[1] = dw[2];
            dw[2] = sb;
            break;
        }
    }

    void parse(String rawCmd) throws CommandException
    {
        byte[] _raw = ControllerDescriptor.xlate(rawCmd);
        _dSiz = 0; // Keep track of data characters.
        _oOfs = _cOfs = _pOfs = _vOfs = _iOfs = -1; // In case we are
                                                    // re-parsing.
        int n;
        for (int i = 0, iBlt = 0; i < _raw.length; i++)
        {
            if (_raw[i] != '%')
            {
                _blt[iBlt++] = _raw[i];
                _blt = _blt + _raw.charAt(i);
                _dSiz++;
            }
            else
            {
                // Parse out optional "n" value.
                i++;
                n = 0;
                while (Character.isDigit(_raw[i]))
                {
                    n = 10 * n + (_raw[i++] - '0');
                }
                if (n == 0)
                    n = 1;

                switch (_raw[i])
                {
                case 'o':
                    _oOfs = iBlt;
                    _oSiz = n;
                    iBlt += n;
                    break;
                case 'p':
                    _pOfs = iBlt;
                    _pSiz = n;
                    iBlt += n;
                    break;
                case 'v':
                    _vOfs = iBlt;
                    _vSiz = n;
                    iBlt += n;
                    break;
                case 'c':
                    _cOfs = iBlt;
                    _cSiz = n;
                    iBlt += n;
                    break;
                case 'i':
                    _iOfs = iBlt;
                    _iSiz = n;
                    iBlt += n;
                    break;

                default:
                    throw new CommandException("Invalid format '%c' in '%s'" + rawCmd);
                }
            }
        }
        _empty = _dSiz + _oSiz + _pSiz + _vSiz + _cSiz == 0;
    }

    public static final byte[] intToByteArray(int value)
    {
        return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
    }

    private String xlate(String rawCmd)
    {
        // TODO Auto-generated method stub
        return null;
    }

    byte[] buildCmd(PageParameters pp, Integer ofs, List<Short> val, int cnt)
    {
        int n = _vSiz * cnt;
        byte[] blt = new byte[_dSiz + _oSiz + _pSiz + _cSiz + n];
        if (_pOfs >= 0)
        {
            // if (pp._bigEnd) bigEndIt(reinterpret_cast<BYTE *>(&pp._pageNo),
            // _pSiz);
            byte[] tmp = intToByteArray(pp._pageNo);
            cpDWord(tmp, _pSiz, blt, _pOfs);
        }
        if (_iOfs >= 0)
        { // Copy verbatim, assume user knows endian issues.
            for (int i = 0; i < _iSiz; i++)
            {
                blt[_iOfs + i] = pp._pageIdentifier[i];
            }
        }
        if (_oOfs >= 0)
        {
            // if (pp._bigEnd) bigEndIt(reinterpret_cast<BYTE *>(&ofs), _oSiz);
            byte[] tmp = intToByteArray(ofs);
            cpDWord(tmp, _oSiz, blt, _oOfs);
        }
        if (_cOfs >= 0)
        {
            // if (pp._bigEnd) bigEndIt(reinterpret_cast<BYTE *>(&cnt), _cSiz);
            byte[] tmp = intToByteArray(cnt);
            cpDWord(tmp, _cSiz, blt, _cOfs);
        }
        if (_vOfs >= 0)
        {
            for (int i = 0; i < n; i++)
            {
                blt[_vOfs + i] = val.get(i).byteValue();
            }
        }
        return blt;
    }

    private void cpDWord(byte[] src, int size, byte[] dst, int offset)
    {
        for (int i = 0; i < size; i++)
        {
            dst[i + offset] = src[i];
        }
    }

    boolean empty(boolean chkDash/* =true */)
    {
        return _empty || (chkDash && _raw.equals("-"));
    }

    int valueSize()
    {
        return _vSiz;
    }

}
