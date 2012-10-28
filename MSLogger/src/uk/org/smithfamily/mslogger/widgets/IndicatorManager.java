package uk.org.smithfamily.mslogger.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public enum IndicatorManager
{
    INSTANCE;
    private final Map<String, List<Indicator>> indicatorMap = new HashMap<String, List<Indicator>>();
    private final List<Indicator> indicatorList = new ArrayList<Indicator>();
    private boolean disabled = false;
    private boolean editing;

    /**
     * 
     * @param d
     */
    public void setDisabled(final boolean d)
    {
        if (d != disabled)
        {
            for (final Indicator i : indicatorList)
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
    void registerIndicator(final Indicator i)
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
    void deregisterIndicator(final Indicator i)
    {
        indicatorList.remove(i);
        final List<Indicator> indicators = indicatorMap.get(i.getChannel());
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
    public List<Indicator> getIndicators(final String channel)
    {
        final List<Indicator> indicators = indicatorMap.get(channel);
        return indicators;
    }

    /**
     * @return
     */
    public List<Indicator> getIndicators()
    {
        return indicatorList;
    }

    public void setEditing(final boolean gaugeEditEnabled)
    {

        if (gaugeEditEnabled != editing)
        {
            for (final Indicator i : indicatorList)
            {
                // i.setEditMode(gaugeEditEnabled);
                // i.invalidate();
            }
        }
        this.editing = gaugeEditEnabled;
    }

}
