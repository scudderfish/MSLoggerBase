package uk.org.smithfamily.mslogger.widgets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum GaugeRegister
{
    INSTANCE;
    private Map<String, GaugeDetails> details = new HashMap<String, GaugeDetails>();

    public void addGauge(GaugeDetails gaugeDetails)
    {
        details.put(gaugeDetails.getName(), gaugeDetails);
    }

    public GaugeDetails getGaugeDetails(String nme)
    {

        return details.get(nme);
    }
    public Set<String> getGaugeNames()
    {
        return details.keySet();
    }
    public void flush()
    {
        details = new HashMap<String, GaugeDetails>();
    }

}
