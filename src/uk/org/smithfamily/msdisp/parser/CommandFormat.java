package uk.org.smithfamily.msdisp.parser;

import java.util.List;

public class CommandFormat
{
    boolean _empty;

    int _dSiz; // Number of bytes in raw data part.

    int _pOfs; // Location of bytes in command.
    int _pSiz; // Size of data for this part of command.
    int _iOfs; // Page ID, might be different than page number.
    int _iSiz;
    int _cOfs; // Byte count, used in "chunk" write commands.
    int _cSiz;
    int _oOfs; // Offset part
    int _oSiz;
    int _vOfs; // Address part
    int _vSiz; // Size of one value in bytes.
    int _vCnt; // Number of values (not bytes).

    ByteString _raw = new ByteString("");
    ByteString _blt = new ByteString("");

 public

    CommandFormat()
     {
        _empty = true;
        _pOfs = -1;
        _pSiz=0;
        _iOfs=-1;
        _iSiz=0;
        _cOfs=-1;
        _cSiz=0;
        _oOfs=-1;
        _oSiz=0;
        _vOfs=-1;
        _vSiz=0;
     }
     

    void        parse   (String rawCmd)
    {
        
    }
    ByteString buildCmd(PageParameters pp, Integer ofs, List<Short>val, int cnt)
    {
        return null;
    }

    boolean empty    (boolean chkDash/*=true*/) { return _empty || (chkDash && _raw.equals("-")); }
    int  valueSize()                  { return _vSiz;  }

}
