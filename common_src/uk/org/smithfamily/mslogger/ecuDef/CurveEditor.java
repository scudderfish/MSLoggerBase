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
    private double[]        xBins;
    private double          xBins2;
    private String          xLabel;
    private boolean         xReadOnly;
    private double[]        yBins;
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
    
    public void setXBins(double[] bins, double bins2, boolean b)
    {
        this.xBins = bins;
        this.xBins2 = bins2;
        this.xReadOnly = b;
    }

    public void setYBins(double[] bins)
    {
        this.yBins = bins;
    }
    
    public double[] getxBins()
    {
        return xBins;
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

    public double[] getyBins()
    {
        return yBins;
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
        return "CurveEditor [name=" + name + ", label=" + label + ", xAxis=" + Arrays.toString(xAxis) + ", yAxis=" + Arrays.toString(yAxis) + ", xBins=" + Arrays.toString(xBins) + ", xBins2=" + xBins2 + ", xLabel=" + xLabel + ", xReadOnly="
                + xReadOnly + ", yBins=" + Arrays.toString(yBins) + ", yLabel=" + yLabel + ", gauge=" + gauge + ", lineLabel=" + lineLabel + ", yLabel2=" + yLabel2 + "]";
    }
    
}
