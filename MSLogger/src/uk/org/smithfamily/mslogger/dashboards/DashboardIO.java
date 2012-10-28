package uk.org.smithfamily.mslogger.dashboards;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Indicator.DisplayType;
import uk.org.smithfamily.mslogger.widgets.IndicatorDefault;
import uk.org.smithfamily.mslogger.widgets.IndicatorDefaults;
import uk.org.smithfamily.mslogger.widgets.Location;
import android.content.res.AssetManager;

public enum DashboardIO
{
    INSTANCE;
    private static final String DEFAULT = "default";
    private static final String LABEL_DIGITS = "labelDigits";
    private static final String VALUE_DIGITS = "valueDigits";
    private static final String HI_D = "hiD";
    private static final String HI_W = "hiW";
    private static final String LOW_W = "lowW";
    private static final String LOW_D = "lowD";
    private static final String MAX = "max";
    private static final String MIN = "min";
    private static final String UNITS = "units";
    private static final String GAUGE = "gauge";
    private static final String RPM = "rpm";
    private static final String DASHBOARDS = "dashboards";
    private static final String INDICATORS = "indicators";
    private static final String INDICATORS_LANDSCAPE = "indicators_landscape";
    private static final String LOCATION = "location";
    private static final String TYPE = "type";
    private static final String CHANNEL = "channel";

    private List<Dashboard> activeDashboardDefinitions = new ArrayList<Dashboard>();
    private Map<String, List<Dashboard>> dashCache = new HashMap<String, List<Dashboard>>();

    public void saveDash()
    {
        saveDash(DEFAULT);
    }

    public void saveDash(String dashName)
    {
        JSONObject root = new JSONObject();
        JSONArray jDashes = new JSONArray();
        try
        {
            for (Dashboard d : activeDashboardDefinitions)
            {
                JSONObject jDash = generateJDash(d);
                jDashes.put(jDash);
            }
            root.put(DASHBOARDS, jDashes);
            String definition = root.toString(2);
            writeDefinition(dashName, definition);
        }
        catch (Exception e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
        dashCache.put(dashName, activeDashboardDefinitions);
    }

    private JSONObject generateJDash(Dashboard d) throws JSONException
    {
        JSONObject jDash = new JSONObject();
        JSONArray jIndicators = new JSONArray();
        for (Indicator i : d.getPortrait())
        {
            JSONObject jIndicator = generateJIndicator(i);
            jIndicators.put(jIndicator);
        }
        jDash.put(INDICATORS, jIndicators);
        jIndicators = new JSONArray();
        for (Indicator i : d.getLandscape())
        {
            JSONObject jIndicator = generateJIndicator(i);
            jIndicators.put(jIndicator);
        }
        jDash.put(INDICATORS_LANDSCAPE, jIndicators);

        return jDash;
    }

    private JSONObject generateJIndicator(Indicator i) throws JSONException
    {
        JSONObject j = new JSONObject();

        j.put(CHANNEL, i.getChannel());
        j.put(TYPE, i.getDisplayType().name());
        j.put(UNITS, i.getUnits());
        j.put(MIN, i.getMin());
        j.put(MAX, i.getMax());
        j.put(LOW_D, i.getLowD());
        j.put(LOW_W, i.getLowW());
        j.put(HI_D, i.getHiD());
        j.put(HI_W, i.getHiW());
        j.put(VALUE_DIGITS, i.getVd());
        j.put(LABEL_DIGITS, i.getLd());
        j.put(LOCATION, getJLocation(i.getLocation()));
        return j;
    }

    private JSONArray getJLocation(Location l) throws JSONException
    {
        JSONArray jLocation = new JSONArray();
        jLocation.put(l.getTop()).put(l.getLeft()).put(l.getBottom()).put(l.getRight());
        return jLocation;
    }

    public synchronized List<Dashboard> loadDash(String dashName)
    {
        activeDashboardDefinitions = dashCache.get(dashName);

        if (activeDashboardDefinitions == null)
        {
            activeDashboardDefinitions = new ArrayList<Dashboard>();
            try
            {
                dashName += ".json";
                String dashDefinition = readDefinition(dashName);

                JSONObject jsonObject = new JSONObject(dashDefinition);

                JSONArray dashes = jsonObject.getJSONArray(DASHBOARDS);

                for (int dashIndex = 0; dashIndex < dashes.length(); dashIndex++)
                {
                    JSONObject jDash = dashes.getJSONObject(dashIndex);
                    activeDashboardDefinitions.add(createDash(jDash));
                }
            }
            catch (JSONException e)
            {
                DebugLogManager.INSTANCE.logException(e);
            }
            dashCache.put(dashName, activeDashboardDefinitions);
        }
        return activeDashboardDefinitions;
    }

    private Dashboard createDash(JSONObject jDash) throws JSONException
    {
        Dashboard d = new Dashboard();
        JSONArray indicators = jDash.getJSONArray(INDICATORS);
        for (int indIndex = 0; indIndex < indicators.length(); indIndex++)
        {
            JSONObject jIndicator = indicators.getJSONObject(indIndex);
            Indicator i = createIndicator(jIndicator);

            d.add(i, false);
        }
        indicators = jDash.optJSONArray(INDICATORS_LANDSCAPE);
        if (indicators != null)
        {
            for (int indIndex = 0; indIndex < indicators.length(); indIndex++)
            {
                JSONObject jIndicator = indicators.getJSONObject(indIndex);
                Indicator i = createIndicator(jIndicator);

                d.add(i, true);
            }
        }
        return d;
    }

    private Indicator createIndicator(JSONObject jIndicator) throws JSONException
    {
        Indicator i = new Indicator(ApplicationSettings.INSTANCE.getContext());
        String channel = jIndicator.optString(CHANNEL, RPM);
        IndicatorDefault id = IndicatorDefaults.defaults.get(channel);
        String type = jIndicator.optString(TYPE, GAUGE).toUpperCase();
        JSONArray jLocation = jIndicator.getJSONArray(LOCATION);
        Location loc = createLocation(jLocation);
        String units = jIndicator.optString(UNITS, id != null ? id.getUnits() : "");
        double min = jIndicator.optDouble(MIN, id != null ? id.getMin() : 0.0);
        double max = jIndicator.optDouble(MAX, id != null ? id.getMax() : 7000);
        double lowD = jIndicator.optDouble(LOW_D, id != null ? id.getLoD() : 0);
        double lowW = jIndicator.optDouble(LOW_W, id != null ? id.getLoW() : 0);
        double hiW = jIndicator.optDouble(HI_W, id != null ? id.getHiW() : 5000);
        double hiD = jIndicator.optDouble(HI_D, id != null ? id.getHiD() : 7000);
        int vd = jIndicator.optInt(VALUE_DIGITS, id != null ? id.getVd() : 0);
        int ld = jIndicator.optInt(LABEL_DIGITS, id != null ? id.getLd() : 0);
        i.setChannel(channel);
        i.setDisplayType(DisplayType.valueOf(type));
        i.setLocation(loc);
        i.setUnits(units);
        i.setMin(min);
        i.setMax(max);
        i.setLowD(lowD);
        i.setLowW(lowW);
        i.setHiW(hiW);
        i.setHiD(hiD);
        i.setVd(vd);
        i.setLd(ld);
        if (id == null)
        {
            IndicatorDefaults.defaults.put(channel, new IndicatorDefault(channel, "", units, min, max, lowD, lowW, hiW, hiD, vd, ld));
        }
        return i;
    }

    private Location createLocation(JSONArray jLocation) throws JSONException
    {
        return new Location(jLocation.optDouble(0, 0.0), jLocation.optDouble(1, 0.0), jLocation.optDouble(2, 0.5), jLocation.optDouble(3, 0.5));
    }

    private void writeDefinition(String name, String definition) throws FileNotFoundException
    {
        name += ".json";
        File output = new File(ApplicationSettings.INSTANCE.getDashDir(), name);
        PrintWriter p = new PrintWriter(output);
        p.println(definition);
        p.close();
    }

    private String readDefinition(String fileName)
    {
        StringBuilder sb = new StringBuilder();
        String assetFileName = DASHBOARDS + File.separator + fileName;
        File override = new File(ApplicationSettings.INSTANCE.getDashDir(), fileName);

        AssetManager assetManager = ApplicationSettings.INSTANCE.getContext().getResources().getAssets();

        BufferedReader input = null;
        try
        {
            try
            {
                InputStream data = null;
                if (override.canRead())
                {
                    data = new FileInputStream(override);
                }
                else
                {
                    data = assetManager.open(assetFileName);
                }

                input = new BufferedReader(new InputStreamReader(data));
                String line;

                while ((line = input.readLine()) != null)
                {
                    sb.append(line);
                }
                data.close();
            }
            finally
            {
                if (input != null)
                    input.close();
            }

        }
        catch (IOException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
        return sb.toString();
    }

    public List<Dashboard> loadDash()
    {
        return loadDash(DEFAULT);
    }
}
