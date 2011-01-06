package uk.org.smithfamily.msdisp.parser.ve;

import java.util.ArrayList;

public class VeTable
{
    ArrayList<Double> loadBins;
    ArrayList<Double> rpmBins;
    ArrayList<Double> veBins;
    int               nLoad;
    int               nRPM;
    String            loadType;
    double            _scale;
    double            _trans;

    void setScale(double s)
    {
        _scale = s;
    }

    double scale()
    {
        return _scale;
    }

    void setTranslate(double t)
    {
        _trans = t;
    }

    double translate()
    {
        return _trans;
    }

    int nLoads()
    {
        return nLoad;
    }

    int nRPMs()
    {
        return nRPM;
    }

    double load(int index)
    {
        return (index >= 0 && index < nLoad) ? loadBins.get(index) : 0;
    }
}
