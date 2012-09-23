package uk.org.smithfamily.mslogger.ecuDef;

public class OutputChannel
{
    private String name;
    private String type;
    private int offset;
    private String units;
    private double scale;
    private double translate;
    
    public OutputChannel(String name, String type, int offset, String units, double scale, double translate)
    {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.units = units;
        this.scale = scale;
        this.translate = translate;
    }
    
    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public int getOffset()
    {
        return offset;
    }

    public String getUnits()
    {
        return units;
    }

    public double getScale()
    {
        return scale;
    }

    public double getTranslate()
    {
        return translate;
    }
    
    public String toString()
    {
        return String.format("OutputChannel(\"%s\",\"%s\",%d,\"%s\",%f,%f)",
                name,
                type,
                offset,
                units,
                scale,
                translate);
    }
}
