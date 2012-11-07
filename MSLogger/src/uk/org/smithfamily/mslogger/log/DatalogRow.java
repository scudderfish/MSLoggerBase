package uk.org.smithfamily.mslogger.log;

/**
 *  Class that define the components of a datalog row
 */
public class DatalogRow
{
    private String datalogName = "";
    private String datalogSize = "";
    private boolean selected = false;
    
    public String getDatalogName()
    {
        return datalogName;
    }

    public void setDatalogName(String datalogName)
    {
        this.datalogName = datalogName;
    }
    
    public String getDatalogSize()
    {
        return datalogSize;
    }
    
    public void setDatalogSize(String datalogSize)
    {
        this.datalogSize = datalogSize;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}