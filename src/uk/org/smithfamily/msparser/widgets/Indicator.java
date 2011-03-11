package uk.org.smithfamily.msparser.widgets;

public interface Indicator
{
    void setMin(float min);

    void setMax(float max);

    void setTitle(String title);

    void setWarningPoint(float warn);

    void setErrorPoint(float err);
    
    void setCurrentValue(float value);
}
