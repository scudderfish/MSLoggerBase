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

    int     _enableWrite         = -1;
    int     _markOnTrue          = -1;
    String  _markerVar           = "";
    String  _enableVar           = "";

    String  _delimiter           = ",";
    String  _defaultLogExtension = ".csv";

    private DatalogOptions()
    {

    }

    void resolve()
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