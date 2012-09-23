package uk.org.smithfamily.utils.normaliser.curveeditor;

public class CurveDefinition extends CurveItem
{
    private String name;
    private String label;

    public CurveDefinition(CurveTracker parent,String name, String label)
    {
        this.name = name;
        this.label = label;
        parent.setName(name);
    }

    public String getName()
    {
        return name;
    }

    public String getLabel()
    {
        return label;
    }

    @Override
    public String toString()
    {
        return String.format("c = new CurveEditor(\"%s\",\"%s\"); curveEditors.put(\"%s\",c);",name,label,name);
    }

}
