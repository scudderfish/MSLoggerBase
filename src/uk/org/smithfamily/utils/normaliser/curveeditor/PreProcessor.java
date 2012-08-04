package uk.org.smithfamily.utils.normaliser.curveeditor;

public class PreProcessor extends CurveItem
{

    private String text;

    public PreProcessor(String text)
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
