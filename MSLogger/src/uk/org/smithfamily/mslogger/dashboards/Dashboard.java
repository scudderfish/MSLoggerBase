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
    
    /**
     * Remove an indicator from the dashboard
     * 
     * @param i The indicator to remove
     * @param landscape = true = landscape, false = portrait
     */
    
    public void remove(Indicator i, boolean landscape)
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

    /**
     * Return the list of indicators of the dashboard
     * 
     * @param landscape true = landspace, false = portrait
     * @return The list of Indicators of the Dashboard
     */
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

    /**
     * @return The list of indicators for the portrait dashboard
     */
    public List<Indicator> getPortrait()
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

    /** 
     * @return The list of indicators for the landscape dashboard
     */
    public List<Indicator> getLandscape()
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

    /**
     * Take a list of indicators and return a copy of it
     * 
     * @param source The list of indicators to copy
     * @return The copied list of indicators
     */
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
