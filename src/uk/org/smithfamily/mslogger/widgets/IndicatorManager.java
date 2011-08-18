package uk.org.smithfamily.mslogger.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum IndicatorManager
{
    INSTANCE;
    private Map<String, List<Indicator>> indicatorMap = new HashMap<String, List<Indicator>>();
    private List<Indicator> indicatorList = new ArrayList<Indicator>();
    private boolean disabled = false;
    public void setDisabled(boolean d)
    {
    	for(Indicator i : indicatorList)
    	{
    		i.setDisabled(d);
    	}
    	disabled = d;
    }
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

    public List<Indicator> getIndicators(String channel)
    {
        List<Indicator> indicators = indicatorMap.get(channel);
        return indicators;
    }
    public List<Indicator> getIndicators()
    {
    	return indicatorList;
    }

}
