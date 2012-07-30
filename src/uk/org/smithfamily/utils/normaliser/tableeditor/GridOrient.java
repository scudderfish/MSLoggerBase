package uk.org.smithfamily.utils.normaliser.tableeditor;

public class GridOrient extends TableItem
{

	private String x;
	private String y;
	private String z;

	public GridOrient(String x,String y, String z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}

	public String getX()
	{
		return x;
	}

	public void setX(String x)
	{
		this.x = x;
	}

	public String getY()
	{
		return y;
	}

	public void setY(String y)
	{
		this.y = y;
	}

	public String getZ()
	{
		return z;
	}

	public void setZ(String z)
	{
		this.z = z;
	}

}
