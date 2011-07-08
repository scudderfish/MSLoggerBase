package uk.org.smithfamily.mslogger.parser;

import java.util.Arrays;

public class PageParameters
{
    int     _pageNo;
    int     _size;
    int     _offset;
    byte[]  _pageIdentifier;
    boolean _bigEnd;
    boolean _modified;
    @Override
    public String toString()
    {
        return "PageParameters [_pageNo=" + _pageNo + ", _size=" + _size + ", _offset=" + _offset + ", _pageIdentifier="
                + Arrays.toString(_pageIdentifier) + ", _bigEnd=" + _bigEnd + ", _modified=" + _modified + "]";
    }

}
