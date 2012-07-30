package uk.org.smithfamily.utils.normaliser.tableeditor;

import java.util.ArrayList;
import java.util.List;

public class TableTracker
{
	private String name;
	private String map3DName;
	private String label;
	private String page;
	
	List<TableItem>	items = new ArrayList<TableItem>();
	public TableTracker()
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMap3DName()
	{
		return map3DName;
	}

	public void setMap3DName(String map3dName)
	{
		map3DName = map3dName;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getPage()
	{
		return page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

	public void addItem(TableItem x)
	{
		items.add(x);
	}

}
