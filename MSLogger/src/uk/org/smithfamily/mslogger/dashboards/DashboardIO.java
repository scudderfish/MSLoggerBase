package uk.org.smithfamily.mslogger.dashboards;

import java.io.*;
import java.util.*;

import org.json.*;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.widgets.*;
import uk.org.smithfamily.mslogger.widgets.Indicator.DisplayType;
import android.content.res.AssetManager;

public enum DashboardIO
{
    INSTANCE;
    private static final String TOP = "top";
    private static final String LEFT = "left";
    private static final String BOTTOM = "bottom";
    private static final String RIGHT = "right";
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
    private final Map<String, List<Dashboard>> dashCache = new HashMap<String, List<Dashboard>>();

    public void saveDash()
    {
        saveDash(DEFAULT);
    }

    public void saveDash(final String dashName)
    {
        final JSONObject root = new JSONObject();
        final JSONArray jDashes = new JSONArray();
        try
        {
            for (final Dashboard d : activeDashboardDefinitions)
            {
                final JSONObject jDash = generateJDash(d);
                jDashes.put(jDash);
            }
            root.put(DASHBOARDS, jDashes);
            final String definition = root.toString(2);
            writeDefinition(dashName, definition);
        }
        catch (final Exception e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
        dashCache.put(dashName, activeDashboardDefinitions);
    }

    private JSONObject generateJDash(final Dashboard d) throws JSONException
    {
        final JSONObject jDash = new JSONObject();
        JSONArray jIndicators = new JSONArray();
        for (final Indicator i : d.getPortrait())
        {
            final JSONObject jIndicator = generateJIndicator(i);
            jIndicators.put(jIndicator);
        }
        jDash.put(INDICATORS, jIndicators);
        jIndicators = new JSONArray();
        for (final Indicator i : d.getLandscape())
        {
            final JSONObject jIndicator = generateJIndicator(i);
            jIndicators.put(jIndicator);
        }
        jDash.put(INDICATORS_LANDSCAPE, jIndicators);

        return jDash;
    }

    private JSONObject generateJIndicator(final Indicator i) throws JSONException
    {
        final JSONObject j = new JSONObject();

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

    private JSONArray getJLocation(final Location l) throws JSONException
    {
        final JSONArray jLocation = new JSONArray();
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
                final String dashDefinition = readDefinition(dashName);

                final JSONObject jsonObject = new JSONObject(dashDefinition);

                final JSONArray dashes = jsonObject.getJSONArray(DASHBOARDS);

                for (int dashIndex = 0; dashIndex < dashes.length(); dashIndex++)
                {
                    final JSONObject jDash = dashes.getJSONObject(dashIndex);
                    activeDashboardDefinitions.add(createDash(jDash));
                }
            }
            catch (final JSONException e)
            {
                DebugLogManager.INSTANCE.logException(e);
            }
            dashCache.put(dashName, activeDashboardDefinitions);
        }
        return activeDashboardDefinitions;
    }

    private Dashboard createDash(final JSONObject jDash) throws JSONException
    {
        final Dashboard d = new Dashboard();
        JSONArray indicators = jDash.getJSONArray(INDICATORS);
        for (int indIndex = 0; indIndex < indicators.length(); indIndex++)
        {
            final JSONObject jIndicator = indicators.getJSONObject(indIndex);
            final Indicator i = createIndicator(jIndicator);

            d.add(i, false);
        }
        indicators = jDash.optJSONArray(INDICATORS_LANDSCAPE);
        if (indicators != null)
        {
            for (int indIndex = 0; indIndex < indicators.length(); indIndex++)
            {
                final JSONObject jIndicator = indicators.getJSONObject(indIndex);
                final Indicator i = createIndicator(jIndicator);

                d.add(i, true);
            }
        }
        return d;
    }

    private Indicator createIndicator(final JSONObject jIndicator) throws JSONException
    {
        final Indicator i = new Indicator();
        final String channel = jIndicator.optString(CHANNEL, RPM);
        final IndicatorDefault id = IndicatorDefaults.defaults.get(channel);
        final String type = jIndicator.optString(TYPE, GAUGE).toUpperCase();
        final JSONObject jLocation = jIndicator.getJSONObject(LOCATION);
        final Location loc = createLocation(jLocation);
        final String units = jIndicator.optString(UNITS, id != null ? id.getUnits() : "");
        final double min = jIndicator.optDouble(MIN, id != null ? id.getMin() : 0.0);
        final double max = jIndicator.optDouble(MAX, id != null ? id.getMax() : 7000);
        final double lowD = jIndicator.optDouble(LOW_D, id != null ? id.getLoD() : 0);
        final double lowW = jIndicator.optDouble(LOW_W, id != null ? id.getLoW() : 0);
        final double hiW = jIndicator.optDouble(HI_W, id != null ? id.getHiW() : 5000);
        final double hiD = jIndicator.optDouble(HI_D, id != null ? id.getHiD() : 7000);
        final int vd = jIndicator.optInt(VALUE_DIGITS, id != null ? id.getVd() : 0);
        final int ld = jIndicator.optInt(LABEL_DIGITS, id != null ? id.getLd() : 0);
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

    private Location createLocation(final JSONObject jLocation) throws JSONException
    {
        return new Location(jLocation.optDouble(LEFT, 0.0), jLocation.optDouble(TOP, 0.0), jLocation.optDouble(RIGHT, 0.5), jLocation.optDouble(BOTTOM, 0.5));
    }

    private void writeDefinition(String name, final String definition) throws FileNotFoundException
    {
        name += ".json";
        final File output = new File(ApplicationSettings.INSTANCE.getDashDir(), name);
        final PrintWriter p = new PrintWriter(output);
        p.println(definition);
        p.close();
    }

    private String readDefinition(final String fileName)
    {
        final StringBuilder sb = new StringBuilder();
        final String assetFileName = DASHBOARDS + File.separator + fileName;
        final File override = new File(ApplicationSettings.INSTANCE.getDashDir(), fileName);

        final AssetManager assetManager = ApplicationSettings.INSTANCE.getContext().getResources().getAssets();

        BufferedReader input = null;
        InputStream data = null;

        try
        {
            try
            {
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
                {
                    input.close();
                }
                if (data != null)
                {
                    data.close();
                }
            }

        }
        catch (final IOException e)
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
