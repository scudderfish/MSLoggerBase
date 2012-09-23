package uk.org.smithfamily.utils.normaliser.userdefined;

public class UserDefinedPreProcessor extends UserDefinedItem
{
    private String text;
    
    public UserDefinedPreProcessor(String text)
    {
        this.text = text;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    @Override
    public String toString()
    {
        return text;
    }
}
