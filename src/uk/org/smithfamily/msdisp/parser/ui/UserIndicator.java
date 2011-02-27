package uk.org.smithfamily.msdisp.parser.ui;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import uk.org.smithfamily.msparser.R;
import android.content.Context;
import android.content.res.ColorStateList;

public class UserIndicator
{
    static int nId = 9997;

    public UserIndicator(Context c, String e, // Expression
            String lf, String ln, // Labels, off and on
            String bgf, String fgf, // Off colors
            String bgn, String fgn)// On colors
    {
        _expr = e;
        _val = -1;
        _on = true;
        _label[0] = lf;
        _fgName[0] = fgf;
        _bgName[0] = bgf;
        _fg[0] = c.getResources().getColorStateList(R.color.IndicatorFG);
        _fg[1] = c.getResources().getColorStateList(R.color.IndicatorBG);
        _label[1] = ln;
        _fgName[1] = fgn;
        _bgName[1] = bgn;
        _bg[0] = c.getResources().getColorStateList(R.color.IndicatorBG);
        _bg[1] = c.getResources().getColorStateList(R.color.IndicatorWarn);
        _id = nId++;
    }

    int id()
    {
        return _id;
    }

    ColorStateList fg()
    {
        if (_on)
            return _fg[0];
        else
            return _fg[1];
    }

    ColorStateList bg()
    {
        if (_on)
            return _bg[0];
        else
            return _bg[1];
    }

    // void attach(CWnd *p);
    boolean enable()
    {
        if (_val == -1)
            return false;
        boolean on = (MsDatabase.getInstance().cDesc._userVar.get(_val) != 0.0);
        // SetWindowText(_label[on]);
        if (on == _on)
            return false;
        // EnableWindow(on);
        Invalidate();
        _on = on;
        return true;
    }

    private void Invalidate()
    {
        // TODO Auto-generated method stub

    }

    // bool setBrush(CWnd *pWnd, CDC *pDC, CBrush &brush);

    private String[]         _label  = new String[2];
    // CWnd *_parent;
    private String           _expr;                          // Evaluate to see
                                                              // if we should
                                                              // disable this
                                                              // entry.
    private int              _val;                           // Pointer to
                                                              // expression's
                                                              // value.
    private boolean          _on;
    private int              _id;
    private ColorStateList[] _fg     = new ColorStateList[2];
    private ColorStateList[] _bg     = new ColorStateList[2];
    private String[]         _fgName = new String[2];
    private String[]         _bgName = new String[2];

}
