package uk.org.smithfamily.utils.normaliser.menu;

public class MenuPreProcessor extends MenuItem
{
    private String text;

    public MenuPreProcessor(String text)
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
