package uk.org.smithfamily.mslogger.widgets;

import java.util.*;

/**
 *
 */
public enum IndicatorManager
{
    INSTANCE;
    private Map<String, List<Indicator>> indicatorMap  = new HashMap<String, List<Indicator>>();
    private List<Indicator>              indicatorList = new ArrayList<Indicator>();
    private boolean                      disabled      = false;

    /**
     * 
     * @param d
     */
    public void setDisabled(boolean d)
    {
        if (d != disabled)
        {
            for (Indicator i : indicatorList)
            {
                i.setDisabled(d);
            }
        }
        disabled = d;
    }

    /**
     * 
     * @param i
     */
    void registerIndicator(Indicator i)
    {
        indicatorList.add(i);
        List<Indicator> indicators = indicatorMap.get(i.getChannel());
        if (indicators == null)
        {
            indicators = new ArrayList<Indicator>();
            indicatorMap.put(i.getChannel(), indicators);
        }
        indicators.add(i);
        i.setDisabled(disabled);
    }

    /**
     * 
     * @param i
     */
    void deregisterIndicator(Indicator i)
    {
        indicatorList.remove(i);
        List<Indicator> indicators = indicatorMap.get(i.getChannel());
        if (indicators == null)
        {
            // Ignore
            return;
        }
        indicators.remove(i);
    }

    /**
     * 
     * @param channel
     * @return
     */
    public List<Indicator> getIndicators(String channel)
    {
        List<Indicator> indicators = indicatorMap.get(channel);
        return indicators;
    }

    /**
     * @return
     */
    public List<Indicator> getIndicators()
    {
        return indicatorList;
    }

}
