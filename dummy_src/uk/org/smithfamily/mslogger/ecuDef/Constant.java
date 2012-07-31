package uk.org.smithfamily.utils.normaliser;
public class Constant
{

	@Override
    public String toString()
    {
        return "Constant [page=" + page + ", classType=" + classType + ", type=" + type + ", offset=" + offset + ", shape=" + shape + ", units=" + units
                + ", name=" + name + "]";
    }

    private int		digits;
	private double	high;
	private int		page;
	private String	classType;
	private String	type;
	private int		offset;
	private String	shape;
	private String	units;
	private double	scale;
	private double	translate;
	private double	low;
	private String	name;

	public Constant(int page,String name, String classType, String type, int offset, String shape, String units, double scale,
			double translate, double low, double high, int digits)
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
		this.low = low;
		this.high = high;
		this.digits = digits;
	}

	public int getDigits()
	{
		return digits;
	}

	public double getHigh()
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
		return scale;
	}

	public double getTranslate()
	{
		return translate;
	}

	public double getLow()
	{
		return low;
	}

	public String getName()
	{
		return name;
	}
}
