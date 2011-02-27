package uk.org.smithfamily.msdisp.parser.log;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import android.text.TextUtils;

public class DatalogOptions
{
    private static DatalogOptions instance = new DatalogOptions();

    public static DatalogOptions getInstance()
    {
        return DatalogOptions.instance;
    };

    String  _commentText         = "";
    boolean _dumpBefore          = false;
    boolean _dumpAfter           = false;

    public int     _enableWrite         = -1;
    public int     _markOnTrue          = -1;
    public String  _markerVar           = "";
    public String  _enableVar           = "";

    public String  _delimiter           = ",";
    public String  _defaultLogExtension = ".csv";

    private DatalogOptions()
    {

    }

    public void resolve()
    {
        final MsDatabase mdb = MsDatabase.getInstance();
        if (!TextUtils.isEmpty(_markerVar))
        {
            _markOnTrue = mdb.cDesc.varIndex(_markerVar);
            if (_markOnTrue == -1)
            {
                // msgOk("Custom Datalog",
                // "Could not find \"markOnTrue\" variable named \"%s\"",
                // _markerVar);
            }
        }
        if (!TextUtils.isEmpty(_enableVar))
        {
            _enableWrite = mdb.cDesc.varIndex(_enableVar);
            if (_enableWrite == -1)
            {
                // msgOk("Custom Datalog",
                // "Could not find \"enableWrite\" variable named \"%s\"",
                // _enableVar);
            }
        }

    }

}