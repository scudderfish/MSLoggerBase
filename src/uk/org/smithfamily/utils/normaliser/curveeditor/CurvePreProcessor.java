package uk.org.smithfamily.utils.normaliser.curveeditor;

public class CurvePreProcessor extends CurveItem
{

    private String text;

    public CurvePreProcessor(String text)
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
