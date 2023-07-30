package uk.org.smithfamily.mslogger.dashboards;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.widgets.Indicator;

/**
 * Dashboard class that contain a list of indicators
 */
public class Dashboard
{
    private List<Indicator> portraitDash = null;
    private List<Indicator> landscapeDash = null;

    /**
     * Add an indicator to the dashboard
     * 
     * @param i The indicator to add
     * @param landscape true = landspace, false = portrait
     */
    public void add(final Indicator i, final boolean landscape)
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

    /**
     * Remove an indicator from the dashboard
     * 
     * @param i The indicator to remove
     * @param landscape = true = landscape, false = portrait
     */

    public void remove(final Indicator i, final boolean landscape)
    {
        if (landscape)
        {
            getLandscape().remove(i);
        }
        else
        {
            getPortrait().remove(i);
        }
    }

    public List<Indicator> getPortrait()
    {
        if (portraitDash != null)
        {
            return portraitDash;
        }
        portraitDash = copyOf(landscapeDash);
        return portraitDash;
    }

    public List<Indicator> getLandscape()
    {
        if (landscapeDash != null)
        {
            return landscapeDash;
        }
        else
        {
            landscapeDash = new ArrayList<>();
            return landscapeDash;
        }
    }

    /**
     * Take a list of indicators and return a copy of it
     * 
     * @param source The list of indicators to copy
     * @return The copied list of indicators
     */
    private List<Indicator> copyOf(final List<Indicator> source)
    {
        final List<Indicator> dest = new ArrayList<>();
        for (final Indicator i : source)
        {
            dest.add(i.copy());
        }
        return dest;
    }

}
