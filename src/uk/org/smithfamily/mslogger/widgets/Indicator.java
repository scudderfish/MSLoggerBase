package uk.org.smithfamily.mslogger.widgets;


public interface Indicator
{
    public void setName(String name);
    public void setChannel(String channelName);
    public void setTitle(String title);
    public void setUnits(String units);
    public void setMin(float min);
    public void setMax(float max);
    public void setLowD(float lowD);
    public void setLowW(float lowW);
    public void setHiW(float hiW);
    public void setHiD(float hiD);
    public void setVD(int vd);
    public void setLD(int ld);
    
     
    public void setCurrentValue(double value);
    
    
    public String getChannel();
    
    public void setDisabled(boolean disabled);
}
