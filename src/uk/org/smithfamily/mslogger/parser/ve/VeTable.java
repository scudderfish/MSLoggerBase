package uk.org.smithfamily.mslogger.parser.ve;

import java.util.ArrayList;
import java.util.List;

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

    boolean setSize(int nrpm, int nload, List<Double> rpmbins, List<Double> loadbins, List<Double> vebins)
    {
        nRPM = nrpm;
        nLoad = nload;
        rpmBins.clear();
        rpmBins.addAll(rpmbins);
        loadBins.clear();
        loadBins.addAll(loadbins);
        veBins.clear();
        veBins.addAll(vebins);

        return true;
    }

    double interp(double x, double x0, double y0, double x1, double y1)
    {
        return y0 + (x - x0) / (x1 - x0) * (y1 - y0);
    }

    double round(double x, double roundTo)
    {
        return Math.floor(((x + (roundTo / 2)) / roundTo) * roundTo);
    }

    List<Double> interpRow(List<Double> Y, int oldN, int newN, double roundTo)
    {
        List<Double> newY = new ArrayList<Double>(newN);
        int newI = 0;

        for (int i = 0; i < newN; i++)
        {
            double newX = (double) i / (newN - 1) * (oldN - 1);
            while (newX > newI)
                newI++;
            double newValue = interp(newX, newI - 1, Y.get(newI - 1), newI, Y.get(newI));
            newValue = round(newValue, roundTo);
            newY.add(newI == 0 ? Y.get(0) : newValue);
        }

        return newY;
    }

    double ve(int r, int l)
    {
        return (r >= 0 && r < nRPM && l >= 0 && l < nLoad) ? veBins.get(l * nRPM + r) : 0;
    }

    void ve(double value, int r, int l)
    {
        if (r >= 0 && r < nRPM && l >= 0 && l < nLoad)
            veBins.add(l * nRPM + r, value);
    }

    double interpolate(double rpm, double load)
    {
        if (load < loadBins.get(0))
            load = loadBins.get(0);
        if (load > loadBins.get(nLoad - 1))
            load = loadBins.get(nLoad - 1);
        if (rpm < rpmBins.get(0))
            rpm = rpmBins.get(0);
        if (rpm > rpmBins.get(nRPM - 1))
            rpm = rpmBins.get(nRPM - 1);

        int irpm;
        double rpm1 = 0, rpm2 = 0;
        for (irpm = 0; irpm < nRPM - 1; irpm++)
        {
            if (rpm <= rpmBins.get(irpm + 1))
            {
                rpm1 = rpmBins.get(irpm);
                rpm2 = rpmBins.get(irpm + 1);
                break;
            }
        }

        int iload;
        double load1 = 0, load2 = 0;
        for (iload = 0; iload < nLoad - 1; iload++)
        {
            if (load <= loadBins.get(iload + 1))
            {
                load1 = loadBins.get(iload);
                load2 = loadBins.get(iload + 1);
                break;
            }
        }

        double ve11 = ve(irpm + 0, iload + 0);
        double ve21 = ve(irpm + 1, iload + 0);
        double ve12 = ve(irpm + 0, iload + 1);
        double ve22 = ve(irpm + 1, iload + 1);

        double loMapVE = interp(rpm, rpm1, ve11, rpm2, ve21);
        double hiMapVE = interp(rpm, rpm1, ve12, rpm2, ve22);
        return interp(load, load1, loMapVE, load2, hiMapVE);
    }

    void resize(int newNRPM, int newNLOD, double roundRPM, double roundLOD, double roundVE)
    {
        List<Double> newRPM = interpRow(rpmBins, nRPM, newNRPM, roundRPM);
        List<Double> newLOD = interpRow(loadBins, nLoad, newNLOD, roundLOD);
        List<Double> newVE = new ArrayList<Double>(newNRPM * newNLOD);
        for (int r = 0; r < newNRPM; r++)
        {
            for (int l = 0; l < newNLOD; l++)
            {
                newVE.add(l * newNRPM + r, round(interpolate(newRPM.get(r), newLOD.get(l)), roundVE));
            }
        }
        setSize(newNRPM, newNLOD, newRPM, newLOD, newVE);
    }
    public VeTable(int NRPM,int NLoad,List<Double>RpmBins,List<Double> LoadBins, List<Double> VeBins)
    {
        _scale = 1.0;
        _trans =0.0;
        loadType = "MAP";
        setSize(NRPM,NLoad,RpmBins,LoadBins,VeBins);
    }

    public VeTable()
    {
       this(0,0,new ArrayList<Double>(),new ArrayList<Double>(),new ArrayList<Double>());
    }
}
