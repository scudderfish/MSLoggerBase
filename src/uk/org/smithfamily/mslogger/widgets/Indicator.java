package uk.org.smithfamily.mslogger.widgets;

public interface Indicator
{
    public void setMin(float min);

    public void setMax(float max);

    public void setTitle(String title);

    public void setWarningPoint(float warn);

    public void setErrorPoint(float err);
    
    public void setCurrentValue(float value);
    
    public void setChannel(String channelName);
    
    public String getChannel();
}
