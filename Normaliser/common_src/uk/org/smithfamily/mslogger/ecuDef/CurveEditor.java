package uk.org.smithfamily.mslogger.ecuDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurveEditor
{
    private String          name;
    private String          label;
    private double[]        xAxis;
    private double[]        yAxis;
    private int[]        xBins;
    private String          xBinsName;
    private double          xBins2;
    private String          xLabel;
    private boolean         xReadOnly;
    private int[]        yBins;
    private String          yBinsName;
    private String          yLabel;
    private String          gauge;
    private List<String>    lineLabel = new ArrayList<String>();
	private String yLabel2;
    
    public CurveEditor(String name, String label)
    {
        this.name = name;
        this.label = label;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public double[] getxAxis()
    {
        return xAxis;
    }

    public void setxAxis(double[] xAxis)
    {
        this.xAxis = xAxis;
    }

    public double[] getyAxis()
    {
        return yAxis;
    }

    public void setyAxis(double[] yAxis)
    {
        this.yAxis = yAxis;
    }
    
    public void setXBins(int[] bins, String binsName, double bins2, boolean b)
    {
        this.xBins = bins;
        this.xBinsName = binsName;
        this.xBins2 = bins2;
        this.xReadOnly = b;
    }

    public void setYBins(int[] bins, String binsName)
    {
        this.yBins = bins;
        this.yBinsName = binsName;
    }
    
    public int[] getxBins()
    {
        return xBins;
    }
    
    public String getxBinsName()
    {
        return xBinsName;
    }
    
    public double getxBins2()
    {
        return xBins2;
    }

    public void setxBins2(double xBins2)
    {
        this.xBins2 = xBins2;
    }
    
    public String getxLabel()
    {
        return xLabel;
    }

    public void setxLabel(String xLabel)
    {
        this.xLabel = xLabel;
    }
    
    public boolean isxReadOnly()
    {
        return xReadOnly;
    }

    public int[] getyBins()
    {
        return yBins;
    }
    
    public String getyBinsName()
    {
        return yBinsName;
    }

    public void setyLabel(String yLabel)
    {
        this.yLabel = yLabel;
    }

    public void setyLabel(String yLabel1,String yLabel2)
    {
    	this.yLabel = yLabel1;
    	this.yLabel2 = yLabel2;
    }

    public String getyLabel()
    {
        return yLabel;
    }
    
    public String getyLabel2()
    {
        return yLabel2;
    }
    
    public String getGauge()
    {
        return gauge;
    }

    public void setGauge(String gauge)
    {
        this.gauge = gauge;
    }

    public List<String> getLineLabel()
    {
        return lineLabel;
    }

    public void addLineLabel(String lineLabel)
    {
        this.lineLabel.add(lineLabel);
    }

    @Override
    public String toString()
    {
        return "CurveEditor [name=" + name + ", label=" + label + ", xAxis=" + Arrays.toString(xAxis) + ", yAxis=" + Arrays.toString(yAxis) + ", xBins=" + Arrays.toString(xBins) + ", xBinsName=" + xBinsName + ", xBins2=" + xBins2 + ", xLabel="
                + xLabel + ", xReadOnly=" + xReadOnly + ", yBins=" + Arrays.toString(yBins) + ", yBinsName=" + yBinsName + ", yLabel=" + yLabel + ", gauge=" + gauge + ", lineLabel=" + lineLabel + ", yLabel2=" + yLabel2 + "]";
    }
    
}
