package uk.org.smithfamily.msdisp.parser.ui;

import java.io.File;

import uk.org.smithfamily.msdisp.parser.Repository;
import uk.org.smithfamily.msdisp.parser.Tokenizer;

public class GaugeConfiguration
{
    public String label;    // My name.
    public String ref;      // The gauge from which I was (or will be) derived,
                             // if
    // any.
    public String title;
    public String units;
    public String cName;
    public int    och;      // Output channel index (I liken it to a machine
                             // address).
    public double lo, hi;
    public double loD, hiD;
    public double loW, hiW;
    public int    vDecimals;
    public int    rDecimals;

    public GaugeConfiguration(String rf, String l, String t, String u,
            double lv, double hv, double lvD, double lvW, double hvW,
            double hvD, int vd, int rd, String rtN)
    {
        och = Repository.Uundefined;
        lo = (lv);
        hi = (hv);
        loD = (lvD);
        loW = (lvW);
        hiW = (hvW);
        hiD = (hvD);
        vDecimals = (vd);
        rDecimals = (rd);
        cName = rtN;
        ref = rf;
        label = l;
        title = t;
        units = u;

    }

    public GaugeConfiguration()
    {
        this("", "", "", "", 0.0, 1.0, -0.1, -0.1, 1.1, 1.1, 0, 0, "");
    }

    void set(double loL, double hiL, double redline)
    {
        lo = loL;
        hi = hiL;
        loD = lo - 1.0;
        loW = lo - 1.0;
        hiW = hi + 1.0;
        hiD = redline;
    }

    public void set(Tokenizer t)
    {
        if (t.size() == 2)
        { // Reference: gauge1 = rpmGauge
          // Note that "rpmGauge" may or may not exist at this time.
            ref = t.get(1);
        } else if (t.size() == 4)
        { // Old style : rpmGauge = 0, 8000, 6700
            set(t.v(1), t.v(2), t.v(3));
        } else if (t.size() == 7)
        {
            // 2.16 style: rpmGauge = 0, 8000, 300, 500, 6500, 6700
            lo = t.v(1);
            hi = t.v(2);
            loD = t.v(3);
            loW = t.v(4);
            hiW = t.v(5);
            hiD = t.v(6);
        } else
        {
            // This is a true gauge configuration, not a displayed gauge.
            // accelEnrichGauge=accelEnrich,"Acceleration Enrichment","%",100,150,-1,-1,999,999,0,0
            ref = "";
            label = t.get(0);
            cName = t.get(1);
            // och - Resolved later by owner of och list.
            title = t.get(2);
            units = t.get(3);
            lo = t.v(4);
            hi = t.v(5);
            loD = t.v(6);
            loW = t.v(7);
            hiW = t.v(8);
            hiD = t.v(9);
            vDecimals = (int) t.v(10);
            rDecimals = (int) t.v(1);
        }
    }

    void print(File f)
    {

    }

    @Override
    public boolean equals(Object o)
    {
        // TODO Auto-generated method stub
        return super.equals(o);
    }

    public boolean getShowRange()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean getShowHand()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean getShowGauge()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public int getRangeSegmentDegrees()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getRangeSegmentOffset()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public float getIncrementPerLargeNotch()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public float getIncrementPerSmallNotch()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
