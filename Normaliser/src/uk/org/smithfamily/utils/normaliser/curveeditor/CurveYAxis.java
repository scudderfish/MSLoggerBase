package uk.org.smithfamily.utils.normaliser.curveeditor;

public class CurveYAxis  extends CurveItem
{
    private String yAxis1;
    private String yAxis2;
    private String yAxis3;

    public CurveYAxis(String xAxis1, String xAxis2, String xAxis3)
    {
        this.yAxis1 = xAxis1;
        this.yAxis2 = xAxis2;
        this.yAxis3 = xAxis3;
    }

    public String getxAxis1()
    {
        return yAxis1;
    }

    public void setxAxis1(String xAxis1)
    {
        this.yAxis1 = xAxis1;
    }

    public String getxAxis2()
    {
        return yAxis2;
    }

    public void setxAxis2(String xAxis2)
    {
        this.yAxis2 = xAxis2;
    }

    public String getxAxis3()
    {
        return yAxis3;
    }

    public void setxAxis3(String xAxis3)
    {
        this.yAxis3 = xAxis3;
    }

    @Override
    public String toString()
    {
        return String.format("c.setyAxis(new double[] {%s,%s,%s});",yAxis1,yAxis2,yAxis3);
    }  
}
