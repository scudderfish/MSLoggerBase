package uk.org.smithfamily.msdisp.parser.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class UserIndicatorList
{
    List<UserIndicator> _ui = new ArrayList<UserIndicator>();

    // CWnd *_window;

    public void add(Context c, String w, // Window name
            String e, // Expression
            String lf, String ln, // Labels, off and on
            String bgf, String fgf, // Off colors
            String bgn, String fgn) // On colors
    {
        _ui.add(new UserIndicator(c, e, lf, ln, bgf, fgf, bgn, fgn));

    }

    // void attach(CWnd *w, CString windowName);
    boolean enable()
    {
        boolean changed = false;
        for (final UserIndicator u : _ui)
        {
            changed = u.enable() || changed;
        }
        return changed;
    }

    UserIndicator get(int idx)
    {
        return _ui.get(idx);
    }

    int nIndicators(String windowName)
    {
        return _ui.size();
    }

    public void reset()
    {
        _ui.clear();
    }

    public void set(String n, // Built-in indicator name
            String bgf, String fgf, // Off colors
            String bgn, String fgn)// On colors
    {
    }

}
