package uk.org.smithfamily.msdisp.parser;

public class MsDatabase
{
    public static ControllerDescriptor cDesc;

    enum thermType
    {
        Celsius, Fahrenheit
    };

    static thermType therm;

    static boolean   rawTPS;

    double tempFromDb(double t)
    {
        if (therm == thermType.Celsius)
        {
            t = (t - 32.0) * 5.0 / 9.0;
        }
        return t;

    }
    String thermlabel()
    {
        if(therm == thermType.Celsius)
            return "C";
        else
            return "F";
    }
}
