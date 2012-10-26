package uk.org.smithfamily.mslogger.dashboards;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.widgets.Indicator;

public class Dashboard
{
    private List<Indicator> portraitDash = null;
    private List<Indicator> landscapeDash = null;

    public void add(Indicator i, boolean landscape)
    {
        if (landscape)
        {
            getLandscape().add(i);
        }
        else
        {
            getPortrait().add(i);
        }
    }

    public List<Indicator> getIndicators(boolean landscape)
    {
        if (landscape)
        {
            return getLandscape();
        }
        else
        {
            return getPortrait();

        }
    }

    List<Indicator> getPortrait()
    {
        if (portraitDash != null)
        {
            return portraitDash;
        }
        if (portraitDash != null)
        {
            portraitDash = copyOf(landscapeDash);
            return portraitDash;
        }
        else
        {
            portraitDash = new ArrayList<Indicator>();
            return portraitDash;
        }
    }

    List<Indicator> getLandscape()
    {
        if (landscapeDash != null)
        {
            return landscapeDash;
        }
        if (portraitDash != null)
        {
            landscapeDash = copyOf(portraitDash);
            return landscapeDash;
        }
        else
        {
            landscapeDash = new ArrayList<Indicator>();
            return landscapeDash;
        }
    }

    private List<Indicator> copyOf(List<Indicator> source)
    {
        List<Indicator> dest = new ArrayList<Indicator>();
        for (Indicator i : source)
        {
            dest.add(i.copy());
        }
        return dest;
    }

}
