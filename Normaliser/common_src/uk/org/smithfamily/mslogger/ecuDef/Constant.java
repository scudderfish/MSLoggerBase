package uk.org.smithfamily.mslogger.ecuDef;

public class Constant
{
    private int        digits;
    private String     high;
    private int        page;
    private String     classType;
    private String     type;
    private int        offset;
    private String     shape;
    private String     units;
    private String     scale;
    private String     translate;
    private String     low;
    private String     name;
    private String[]   values;
    
    private boolean    modified;

	
    /**
     * Used into the generated MegaSquirt firmware classes to initialize all the firmware constants
     * 
     * @param page
     * @param name
     * @param classType
     * @param type
     * @param offset
     * @param shape
     * @param units
     * @param scale
     * @param translate
     * @param low
     * @param high
     * @param digits
     * @param values
     */
    public Constant(int page, String name, String classType, String type, int offset, String shape, String units, double scale,
            double translate, double low, double high, int digits, String[] values)
    {
        this.page = page;
        this.name = name;
        this.classType = classType;
        this.type = type;
        this.offset = offset;
        this.shape = shape;
        this.units = units;
        this.scale = Double.toString(scale);
        this.translate = Double.toString(translate);
        this.low = Double.toString(low);
        this.high = Double.toString(high);
        this.digits = digits;
        this.values = values;
    }
    
    /**
     * Used for adding Constant instance to ECUData
     * 
     * @param page
     * @param name
     * @param classType
     * @param type
     * @param offset
     * @param shape
     * @param units
     * @param scale
     * @param translate
     * @param low
     * @param high
     * @param digits
     * @param values
     */
    public Constant(int page, String name, String classType, String type, int offset, String shape, String units, String scale,
            String translate, String low, String high, int digits, String[] values)
    {
        this.page = page;
        this.name = name;
        this.classType = classType;
        this.type = type;
        this.offset = offset;
        this.shape = shape;
        this.units = units;
        this.scale = scale;
        this.translate = translate;
        if (low == null || low.trim().equals(""))
        {
            low = "0";
        }
        this.low = low;
        if (high == null || high.trim().equals(""))
        {
            high="0";
        }
        this.high = high;
        this.digits = digits;
        this.values = values;
    }
    
    /**
     * Used for adding Constant instance to ECUData
     * 
     * @param page
     * @param name
     * @param classType
     * @param type
     * @param offset
     * @param shape
     * @param units
     * @param scale
     * @param translate
     * @param low
     * @param high
     * @param digits
     */
    public Constant(int page, String name, String classType, String type, int offset, String shape, String units, String scale,
            String translate, double low, double high, int digits)
    {
        this.page = page;
        this.name = name;
        this.classType = classType;
        this.type = type;
        this.offset = offset;
        this.shape = shape;
        this.units = units;
        this.scale = scale;
        this.translate = translate;
        this.low = Double.toString(low);
        this.high = Double.toString(high);
        this.digits = digits;
    }

	public int getDigits()
	{
		return digits;
	}

	public String getHigh()
	{
		return high;
	}

	public int getPage()
	{
		return page;
	}

	public String getClassType()
	{
		return classType;
	}

	public String getType()
	{
		return type;
	}

	public int getOffset()
	{
		return offset;
	}

	public String getShape()
	{
		return shape;
	}

	public String getUnits()
	{
		return units;
	}

	public double getScale()
	{
		return Double.parseDouble(scale);
	}

	public double getTranslate()
	{
		return Double.parseDouble(translate);
	}

	public String getLow()
	{
		return low;
	}

	public String getName()
	{
		return name;
	}
	
	public String[] getValues()
	{
	    return values;
	}
	
    @Override
    public String toString()
    {
        String valuesOutput = "";
        
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                valuesOutput += values[i];

                if (i < values.length - 1)
                {
                    valuesOutput += ",";
                }
            }
        }
      
        return String.format("Constant(%d,\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\",%s,%s,%s,%s,%d,new String[] {%s})", 
                page ,
                name,
                classType,
                type,
                offset,
                shape,
                units,
                scale,
                translate,
                low,
                high,
                digits,
                valuesOutput);
                
    }

    public boolean isModified()
    {
        return modified;
    }

    public void setModified(boolean modified)
    {
        this.modified = modified;
    }
}
