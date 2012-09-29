package uk.org.smithfamily.mslogger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.org.smithfamily.mslogger.comms.ExtGPSConnectionManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import android.util.Log;

public enum ExternalGPSManager
{
    INSTANCE;

    Location location;
    private LocationManager locationManager;
    private String providerName = "ExternalGPS";
    private int providerStatus = LocationProvider.OUT_OF_SERVICE;
    private String lastLocationTime = null;
    private long locationTime;
    private float precision = 10f;
    private boolean hasGGA = false;
    private boolean hasRMC = false;
    private boolean mockGpsEnabled = false;

    private volatile ExtGPSThread extGPSThread;
    private volatile boolean running;
    private static volatile ExtGPSThread watch;
    private Handler handler;

    private void notifyLocationChange(Location newLoc) throws SecurityException
    {
        lastLocationTime = null;
        hasGGA = false;
        hasRMC = false;
        if (newLoc != null)
        {
            DebugLogManager.INSTANCE.log("New Location: " + System.currentTimeMillis() + " " + newLoc, Log.INFO);
            if (locationManager != null && mockGpsEnabled)
            {
                locationManager.setTestProviderLocation(providerName, newLoc);
                DebugLogManager.INSTANCE.log("New Location notified to Location Manager: " + providerName, Log.INFO);
            }
            this.location = null;
        }
    }

    private void notifyStatusChanged(int status, Bundle extras, long updateTime)
    {
        lastLocationTime = null;
        hasGGA = false;
        hasRMC = false;
        if (this.providerStatus != status)
        {
            DebugLogManager.INSTANCE.log("New status: " + System.currentTimeMillis() + " " + status, Log.INFO);
            if (locationManager != null && mockGpsEnabled)
            {
                locationManager.setTestProviderStatus(providerName, status, extras, updateTime);
                DebugLogManager.INSTANCE.log("New status notified to Location Manager: " + providerName, Log.INFO);
            }
            this.location = null;
            this.providerStatus = status;
        }
    }

    public String parseNmeaSentence(String gpsSentence) throws SecurityException
    {
        String nmeaSentence = null;

        Pattern xx = Pattern.compile("\\$([^*$]*)\\*([0-9A-F][0-9A-F])?\r\n");
        Matcher m = xx.matcher(gpsSentence);
        if (m.matches())
        {
            nmeaSentence = m.group(0);
            String sentence = m.group(1);
            // String checkSum = m.group(2);

            SimpleStringSplitter splitter = new TextUtils.SimpleStringSplitter(',');
            splitter.setString(sentence);
            String command = splitter.next();
            if (command.equals("GPGGA"))
            {
                /*
                 * $GPGGA,123519,4807.038,N,01131.000,E,1,08,0.9,545.4,M,46.9,M,,*47
                 * 
                 * Where: GGA Global Positioning System Fix Data 123519 Fix taken at 12:35:19 UTC 4807.038,N Latitude 48 deg 07.038' N 01131.000,E
                 * Longitude 11 deg 31.000' E 1 Fix quality: 0 = invalid 1 = GPS fix (SPS) 2 = DGPS fix 3 = PPS fix 4 = Real Time Kinematic 5 = Float
                 * RTK 6 = estimated (dead reckoning) (2.3 feature) 7 = Manual input mode 8 = Simulation mode 08 Number of satellites being tracked
                 * 0.9 Horizontal dilution of position 545.4,M Altitude, Meters, above mean sea level 46.9,M Height of geoid (mean sea level) above
                 * WGS84 ellipsoid (empty field) time in seconds since last DGPS update (empty field) DGPS station ID number47 the checksum data,
                 * always begins with *
                 */
                // UTC time of fix HHmmss.S
                String time = splitter.next();
                // latitude ddmm.M
                String lat = splitter.next();
                // direction (N/S)
                String latDir = splitter.next();
                // longitude dddmm.M
                String lon = splitter.next();
                // direction (E/W)
                String lonDir = splitter.next();
                /*
                 * fix quality: 0= invalid 1 = GPS fix (SPS) 2 = DGPS fix 3 = PPS fix 4 = Real Time Kinematic 5 = Float RTK 6 = estimated (dead
                 * reckoning) (2.3 feature) 7 = Manual input mode 8 = Simulation mode
                 */
                String quality = splitter.next();
                // Number of satellites being tracked
                String nbSat = splitter.next();
                // Horizontal dilution of position (float)
                String hdop = splitter.next();
                // Altitude, Meters, above mean sea level
                String alt = splitter.next();
                // Height of geoid (mean sea level) above WGS84 ellipsoid
                // String geoAlt = splitter.next();
                // time in seconds since last DGPS update
                // DGPS station ID number
                if (quality != null && !quality.equals("") && !quality.equals("0"))
                {
                    if (this.providerStatus != LocationProvider.AVAILABLE)
                    {
                        long updateTime = parseNmeaTime(time);
                        notifyStatusChanged(LocationProvider.AVAILABLE, null, updateTime);
                    }
                    if (!time.equals(lastLocationTime))
                    {
                        notifyLocationChange(location);
                        location = new Location(providerName);
                        lastLocationTime = time;
                        locationTime = parseNmeaTime(time);
                        location.setTime(locationTime);
                        DebugLogManager.INSTANCE.log("Location: " + location, Log.INFO);
                    }
                    if (lat != null && !lat.equals(""))
                    {
                        location.setLatitude(parseNmeaLatitude(lat, latDir));
                    }
                    if (lon != null && !lon.equals(""))
                    {
                        location.setLongitude(parseNmeaLongitude(lon, lonDir));
                    }
                    if (hdop != null && !hdop.equals(""))
                    {
                        location.setAccuracy(Float.parseFloat(hdop) * precision);
                    }
                    if (alt != null && !alt.equals(""))
                    {
                        location.setAltitude(Double.parseDouble(alt));
                    }
                    if (nbSat != null && !nbSat.equals(""))
                    {
                        Bundle extras = new Bundle();
                        extras.putInt("satellites", Integer.parseInt(nbSat));
                        location.setExtras(extras);
                    }
                    DebugLogManager.INSTANCE.log("Location: " + System.currentTimeMillis() + " " + location, Log.INFO);
                    hasGGA = true;
                    if (hasGGA && hasRMC)
                    {
                        notifyLocationChange(location);
                    }
                }
                else if (quality.equals("0"))
                {
                    if (this.providerStatus != LocationProvider.TEMPORARILY_UNAVAILABLE)
                    {
                        long updateTime = parseNmeaTime(time);
                        notifyStatusChanged(LocationProvider.TEMPORARILY_UNAVAILABLE, null, updateTime);
                    }
                }
            }
            else if (command.equals("GPRMC"))
            {
                /*
                 * $GPRMC,123519,A,4807.038,N,01131.000,E,022.4,084.4,230394,003.1,W*6A
                 * 
                 * Where: RMC Recommended Minimum sentence C 123519 Fix taken at 12:35:19 UTC A Status A=active or V=Void. 4807.038,N Latitude 48 deg
                 * 07.038' N 01131.000,E Longitude 11 deg 31.000' E 022.4 Speed over the ground in knots 084.4 Track angle in degrees True 230394 Date
                 * - 23rd of March 1994 003.1,W Magnetic Variation6A The checksum data, always begins with *
                 */
                // UTC time of fix HHmmss.S
                String time = splitter.next();
                // fix status (A/V)
                String status = splitter.next();
                // latitude ddmm.M
                String lat = splitter.next();
                // direction (N/S)
                String latDir = splitter.next();
                // longitude dddmm.M
                String lon = splitter.next();
                // direction (E/W)
                String lonDir = splitter.next();
                // Speed over the ground in knots
                String speed = splitter.next();
                // Track angle in degrees True
                String bearing = splitter.next();
                // UTC date of fix DDMMYY
                // String date = splitter.next();
                // Magnetic Variation ddd.D
                // String magn = splitter.next();
                // Magnetic variation direction (E/W)
                // String magnDir = splitter.next();
                // for NMEA 0183 version 3.00 active the Mode indicator field is added
                // Mode indicator, (A=autonomous, D=differential, E=Estimated, N=not valid, S=Simulator )
                if (status != null && !status.equals("") && status.equals("A"))
                {
                    if (this.providerStatus != LocationProvider.AVAILABLE)
                    {
                        long updateTime = parseNmeaTime(time);
                        notifyStatusChanged(LocationProvider.AVAILABLE, null, updateTime);
                    }
                    if (!time.equals(lastLocationTime))
                    {
                        notifyLocationChange(location);
                        location = new Location(providerName);
                        lastLocationTime = time;
                        locationTime = parseNmeaTime(time);
                        location.setTime(locationTime);
                        DebugLogManager.INSTANCE.log("Location: " + location, Log.INFO);
                    }
                    if (lat != null && !lat.equals(""))
                    {
                        location.setLatitude(parseNmeaLatitude(lat, latDir));
                    }
                    if (lon != null && !lon.equals(""))
                    {
                        location.setLongitude(parseNmeaLongitude(lon, lonDir));
                    }
                    if (speed != null && !speed.equals(""))
                    {
                        location.setSpeed(parseNmeaSpeed(speed, "N"));
                    }
                    if (bearing != null && !bearing.equals(""))
                    {
                        location.setBearing(Float.parseFloat(bearing));
                    }
                    DebugLogManager.INSTANCE.log("Location: " + System.currentTimeMillis() + " " + location, Log.INFO);
                    hasRMC = true;
                    if (hasGGA && hasRMC)
                    {
                        notifyLocationChange(location);
                    }
                }
                else if (status.equals("V"))
                {
                    if (this.providerStatus != LocationProvider.TEMPORARILY_UNAVAILABLE)
                    {
                        long updateTime = parseNmeaTime(time);
                        notifyStatusChanged(LocationProvider.TEMPORARILY_UNAVAILABLE, null, updateTime);
                    }
                }
            }
        }
        return nmeaSentence;
    }

    public double parseNmeaLatitude(String lat, String orientation)
    {
        double latitude = 0.0;
        if (lat != null && orientation != null && !lat.equals("") && !orientation.equals(""))
        {
            double temp1 = Double.parseDouble(lat);
            double temp2 = Math.floor(temp1 / 100);
            double temp3 = (temp1 / 100 - temp2) / 0.6;
            if (orientation.equals("S"))
            {
                latitude = -(temp2 + temp3);
            }
            else if (orientation.equals("N"))
            {
                latitude = (temp2 + temp3);
            }
        }
        return latitude;
    }

    public double parseNmeaLongitude(String lon, String orientation)
    {
        double longitude = 0.0;
        if (lon != null && orientation != null && !lon.equals("") && !orientation.equals(""))
        {
            double temp1 = Double.parseDouble(lon);
            double temp2 = Math.floor(temp1 / 100);
            double temp3 = (temp1 / 100 - temp2) / 0.6;
            if (orientation.equals("W"))
            {
                longitude = -(temp2 + temp3);
            }
            else if (orientation.equals("E"))
            {
                longitude = (temp2 + temp3);
            }
        }
        return longitude;
    }

    public float parseNmeaSpeed(String speed, String metric)
    {
        float meterSpeed = 0.0f;
        if (speed != null && metric != null && !speed.equals("") && !metric.equals(""))
        {
            float temp1 = Float.parseFloat(speed) / 3.6f;
            if (metric.equals("K"))
            {
                meterSpeed = temp1;
            }
            else if (metric.equals("N"))
            {
                meterSpeed = temp1 * 1.852f;
            }
        }
        return meterSpeed;
    }

    public long parseNmeaTime(String time)
    {
        long timestamp = 0;
        SimpleDateFormat fmt = new SimpleDateFormat("HHmmss.SSS");
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        try
        {
            if (time != null && time != null)
            {
                long now = System.currentTimeMillis();
                long today = now - (now % 86400000L);
                long temp1;
                // sometime we don't have millisecond in the time string, so we have to reformat it
                temp1 = fmt.parse(String.format((Locale) null, "%010.3f", Double.parseDouble(time))).getTime();
                long temp2 = today + temp1;
                // if we're around midnight we could have a problem...
                if (temp2 - now > 43200000L)
                {
                    timestamp = temp2 - 86400000L;
                }
                else if (now - temp2 > 43200000L)
                {
                    timestamp = temp2 + 86400000L;
                }
                else
                {
                    timestamp = temp2;
                }
            }
        }
        catch (ParseException e)
        {
            DebugLogManager.INSTANCE.log("Error while parsing NMEA time: " + e, Log.ERROR);
        }
        return timestamp;
    }

    public void changeRunningState(boolean active)
    {
        // TODO Auto-generated method stub

    }

    public synchronized void start()
    {
        DebugLogManager.INSTANCE.log("ExternalGPSManager.start()", Log.INFO);

        if (extGPSThread == null)
        {
            extGPSThread = new ExtGPSThread();
            extGPSThread.start();
        }
    }

    /**
     * Shut down the ECU thread
     */
    public synchronized void stop()
    {
        if (extGPSThread != null)
        {
            extGPSThread.halt();
            extGPSThread = null;
        }

        running = false;

        DebugLogManager.INSTANCE.log("ExternalGPSManager.stop()", Log.INFO);
    }

    /**
     * The thread that handles all communications with the External GPS. This must be done in it's own thread as Android gets very picky about
     * unresponsive UI threads
     */
    private class ExtGPSThread extends Thread
    {
        /**
         * 
         */
        public ExtGPSThread()
        {
            if (watch != null)
            {
                DebugLogManager.INSTANCE.log("Attempting to create second connection!", Log.ASSERT);
            }
            watch = this;
            setName("ECUThread:" + System.currentTimeMillis());
        }

        /**
         * Kick the connection off
         */
        public void initialiseConnection()
        {
            // sendMessage("Launching connection");
            ExtGPSConnectionManager.getInstance().init(handler, ApplicationSettings.INSTANCE.getExtGPSBluetoothMac());
        }

        /**
         * The main loop of the connection to the ECU
         */
        public void run()
        {
            try
            {
                // sendMessage("");
                DebugLogManager.INSTANCE.log("BEGIN connectedThread", Log.INFO);
                initialiseConnection();

                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    DebugLogManager.INSTANCE.logException(e);
                }

                running = true;

                try
                {
                    ExtGPSConnectionManager.getInstance().connect();
                    ExtGPSConnectionManager.getInstance().flushAll();

                    long lastRpsTime = System.currentTimeMillis();

                    // This is the actual work. Outside influences will toggle 'running' when we want this to stop
                    while (running)
                    {

                    }
                }
                catch (IOException e)
                {
                    DebugLogManager.INSTANCE.logException(e);
                }
                catch (RuntimeException t)
                {
                    DebugLogManager.INSTANCE.logException(t);
                    throw (t);
                }
                // We're on our way out, so drop the connection
                ExtGPSConnectionManager.getInstance().disconnect();
                DebugLogManager.INSTANCE.log("Disconnect", Log.INFO);
            }
            finally
            {
                watch = null;
            }
        }

        /**
         * Called by other threads to stop the comms
         */
        public void halt()
        {
            running = false;
        }

    }

}
