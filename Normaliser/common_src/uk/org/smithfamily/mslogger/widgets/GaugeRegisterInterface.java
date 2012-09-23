package uk.org.smithfamily.mslogger.widgets;

import java.util.Set;

public interface GaugeRegisterInterface
{
    public void resetAll();

    /**
     * Add an indicator to te list
     * 
     * @param gaugeDetails All the indicator details
     */
    public void addGauge(GaugeDetails gaugeDetails);

    /**
     * Reset the specific indicator name to firmware INI file specifications
     * 
     * @param name The name of the indicator to reset
     */
    public void reset(String name);

    /**
     * Get details for a specific indicator
     * 
     * @param name Name of the indicator to get details for
     * @return An instance of GaugeDetails
     */
    public GaugeDetails getGaugeDetails(String name);

    /**
     * @return All the indicators names
     */
    public Set<String> getGaugeNames();

    /**
     * Flush all the gauge details from the hash map
     */
    public void flush();


    /**
     * Save the current gauge details into a file
     * 
     * @param gd The GaugeDetails to save details for
     */
    public void persistDetails(GaugeDetails gd);

}
