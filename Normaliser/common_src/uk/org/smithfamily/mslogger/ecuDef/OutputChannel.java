package uk.org.smithfamily.mslogger.ecuDef;

public class OutputChannel
{
    private String name;
    private String type;
    private int offset;
    private String units;
    private double scale;
    private double translate;
    private DataSource source;
    
    public OutputChannel(String name, String type, int offset, String units, double scale, double translate,DataSource source)
    {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.units = units;
        this.scale = scale;
        this.translate = translate;
        this.source = source;
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
    
    public DataSource getSource()
    {
        return source;
    }
    public String toString()
    {
        return String.format("OutputChannel(\"%s\",\"%s\",%d,\"%s\",%f,%f,this)",
                name,
                type,
                offset,
                units,
                scale,
                translate);
    }
}
