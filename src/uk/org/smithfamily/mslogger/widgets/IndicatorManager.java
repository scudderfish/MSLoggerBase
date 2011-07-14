package uk.org.smithfamily.mslogger.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum IndicatorManager
{
    INSTANCE;
    private Map<String, List<Indicator>> indicatorMap = new HashMap<String, List<Indicator>>();

    void registerIndicator(Indicator i)
    {
        List<Indicator> indicators = indicatorMap.get(i.getChannel());
        if (indicators == null)
        {
            indicators = new ArrayList<Indicator>();
            indicatorMap.put(i.getChannel(), indicators);
        }
        indicators.add(i);
    }

    void deregisterIndicator(Indicator i)
    {
        List<Indicator> indicators = indicatorMap.get(i.getChannel());
        if (indicators == null)
        {
            // Ignore
            return;
        }
        indicators.remove(i);
    }

    public List<Indicator> getIndicators(String channel)
    {
        List<Indicator> indicators = indicatorMap.get(channel);
        return indicators;
    }

}
