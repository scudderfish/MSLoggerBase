package uk.org.smithfamily.utils.normaliser.curveeditor;

public class CurveXAxis extends CurveItem
{
    private String xAxis1;
    private String xAxis2;
    private String xAxis3;

    public CurveXAxis(String xAxis1, String xAxis2, String xAxis3)
    {
    	this.xAxis1 = xAxis1;
        this.xAxis2 = xAxis2;
        this.xAxis3 = xAxis3;
    }

    public String getxAxis1()
    {
        return xAxis1;
    }
    public String getxAxis2()
    {
        return xAxis2;
    }

    public String getxAxis3()
    {
        return xAxis3;
    }
    @Override
    public String toString()
    {
        return String.format("c.setxAxis(new double[] {%s,%s,%s});",xAxis1,xAxis2,xAxis3);
    }  
}
