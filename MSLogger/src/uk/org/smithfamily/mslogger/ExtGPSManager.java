package uk.org.smithfamily.mslogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import uk.org.smithfamily.mslogger.comms.ExtGPSConnectionManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import android.util.Log;

public enum ExtGPSManager
{
    INSTANCE;

    Location location;
    private String locationTime = null;
    private long locationTimestamp;
    private int lastNumSatellites = 0;
    private String providerName = "ExternalGPS";
    private float precision = 10f;
    private int locStatus = LocationProvider.OUT_OF_SERVICE;
    private boolean hasGGA = false;
    private boolean hasRMC = false;
    private double readCounter = 0;
    private long lastRpsTime = 0;

    private volatile ExtGPSThread extGPSThread;
    private volatile boolean running;
    private static volatile ExtGPSThread watch;
    private List<LocationListener> listeners = new ArrayList<LocationListener>();

    private Location parseNmeaSentence(String sentence) throws SecurityException
    {
        SimpleStringSplitter splitter = new TextUtils.SimpleStringSplitter(',');
        splitter.setString(sentence);

        DebugLogManager.INSTANCE.log(sentence, Log.VERBOSE);

        String command = splitter.next();
        if (command.equals("$GPGGA"))
        {
            /*
             * $GPGGA,123519,4807.038,N,01131.000,E,1,08,0.9,545.4,M,46.9,M,,*47
             * 
             * Where: GGA Global Positioning System Fix Data 123519 Fix taken at 12:35:19 UTC 4807.038,N Latitude 48 deg 07.038' N 01131.000,E
             * Longitude 11 deg 31.000' E 1 Fix quality: 0 = invalid 1 = GPS fix (SPS) 2 = DGPS fix 3 = PPS fix 4 = Real Time Kinematic 5 = Float RTK
             * 6 = estimated (dead reckoning) (2.3 feature) 7 = Manual input mode 8 = Simulation mode 08 Number of satellites being tracked 0.9
             * Horizontal dilution of position 545.4,M Altitude, Meters, above mean sea level 46.9,M Height of geoid (mean sea level) above WGS84
             * ellipsoid (empty field) time in seconds since last DGPS update (empty field) DGPS station ID number47 the checksum data, always begins
             * with *
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
             * fix quality: 0= invalid 1 = GPS fix (SPS) 2 = DGPS fix 3 = PPS fix 4 = Real Time Kinematic 5 = Float RTK 6 = estimated (dead reckoning)
             * (2.3 feature) 7 = Manual input mode 8 = Simulation mode
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
                long updateTime = parseNmeaTime(time);
                if (this.locStatus != LocationProvider.AVAILABLE)
                {
                    notifyStatusChanged(LocationProvider.AVAILABLE, null, updateTime);
                }
                if (!time.equals(locationTime))
                {
                    locationTime = time;
                    locationTimestamp = parseNmeaTime(time);
                    location.setTime(locationTimestamp);
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
                    int sats = Integer.parseInt(nbSat);
                    extras.putInt("satellites", sats);
                    location.setExtras(extras);
                    if (lastNumSatellites != sats)
                    {
                        lastNumSatellites = sats;
                        notifyStatusChanged(locStatus, extras, updateTime);
                    }
                }
                hasGGA = true;
                if (hasGGA && hasRMC)
                {
                    notifyLocationChanged(location);
                }
            }
            else if (quality != null && quality.equals("0"))
            {
                if (locStatus != LocationProvider.TEMPORARILY_UNAVAILABLE)
                {
                    long updateTime = parseNmeaTime(time);
                    notifyStatusChanged(LocationProvider.TEMPORARILY_UNAVAILABLE, null, updateTime);
                }
            }
        }
        else if (command.equals("$GPRMC"))
        {
            /*
             * $GPRMC,123519,A,4807.038,N,01131.000,E,022.4,084.4,230394,003.1,W*6A
             * 
             * Where: RMC Recommended Minimum sentence C 123519 Fix taken at 12:35:19 UTC A Status A=active or V=Void. 4807.038,N Latitude 48 deg
             * 07.038' N 01131.000,E Longitude 11 deg 31.000' E 022.4 Speed over the ground in knots 084.4 Track angle in degrees True 230394 Date -
             * 23rd of March 1994 003.1,W Magnetic Variation6A The checksum data, always begins with *
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
            if (status != null)
            {
                if (!status.equals("") && status.equals("A"))
                {
                    if (this.locStatus != LocationProvider.AVAILABLE)
                    {
                        long updateTime = parseNmeaTime(time);
                        notifyStatusChanged(LocationProvider.AVAILABLE, null, updateTime);
                    }
                    if (!time.equals(locationTime))
                    {
                        locationTime = time;
                        locationTimestamp = parseNmeaTime(time);
                        location.setTime(locationTimestamp);
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
                    hasRMC = true;
                    if (hasGGA && hasRMC)
                    {
                        notifyLocationChanged(location);
                    }
                }
                else if (status.equals("V"))
                {
                    if (this.locStatus != LocationProvider.TEMPORARILY_UNAVAILABLE)
                    {
                        long updateTime = parseNmeaTime(time);
                        notifyStatusChanged(LocationProvider.TEMPORARILY_UNAVAILABLE, null, updateTime);
                    }
                }
            }
        }
        return location;
    }

    /**
     * 
     * @param lat
     * @param orientation
     * @return
     */
    private double parseNmeaLatitude(String lat, String orientation)
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

    /**
     * 
     * @param lon
     * @param orientation
     * @return
     */
    private double parseNmeaLongitude(String lon, String orientation)
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

    /**
     * 
     * @param speed
     * @param metric
     * @return
     */
    private float parseNmeaSpeed(String speed, String metric)
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

    /**
     * 
     * @param time
     * @return
     */
    private long parseNmeaTime(String time)
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

    /**
     * 
     * @param s
     * @return
     */
    private byte computeChecksum(String s)
    {
        byte checksum = 0;
        for (char c : s.toCharArray())
        {
            checksum ^= (byte) c;
        }
        return checksum;
    }

    /**
     * 
     * @param sentence
     */
    private void sendNmeaCmd(String sentence)
    {
        final String command = String.format((Locale) null, "$%s*%X\r\n", sentence, computeChecksum(sentence));

        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if (running && (extGPSThread != null))
                {
                    extGPSThread.write(command);
                    DebugLogManager.INSTANCE.log("sendNmeaEnableCommand()", Log.DEBUG);
                }
            }
        });
        t.start();

    }

    /**
     * 
     * @param status
     * @param extras
     * @param updateTime
     */
    private void notifyStatusChanged(int status, Bundle extras, long updateTime)
    {
        locationTime = null;
        hasGGA = false;
        hasRMC = false;
        notifyStatusChangedNoClear(status, extras, updateTime);
    }

    /**
     * 
     * @param status
     * @param extras
     * @param updateTime
     */
    private void notifyStatusChangedNoClear(int status, Bundle extras, long updateTime)
    {
        synchronized (listeners)
        {
            for (LocationListener ll : listeners)
            {
                ll.onStatusChanged(providerName, status, extras);
            }
        }
        DebugLogManager.INSTANCE.log("notifyStatusChanged() " + status + " " + extras, Log.DEBUG);

        if (this.locStatus != status)
        {
            this.location = new Location(providerName);
            this.locStatus = status;
        }
    }

    /**
     * 
     * @param loc
     */
    private void notifyLocationChanged(Location loc)
    {
        locationTime = null;
        hasGGA = false;
        hasRMC = false;
        if (this.location != null)
        {
            synchronized (listeners)
            {
                for (LocationListener ll : listeners)
                {
                    ll.onLocationChanged(loc);
                }
            }
            this.location = new Location(providerName);
            DebugLogManager.INSTANCE.log("notifyLocationChanged() " + loc, Log.VERBOSE);

            readCounter++;

            long delay = System.currentTimeMillis() - lastRpsTime;
            if (delay > 1000)
            {
                double RPS = readCounter / delay * 1000;
                lastRpsTime = System.currentTimeMillis();
                readCounter = 0;

                if (RPS > 0)
                {
                    Bundle extras = new Bundle();
                    extras.putInt("satellites", lastNumSatellites);
                    extras.putDouble("rps", RPS);
                    notifyStatusChangedNoClear(locStatus, extras, locationTimestamp);
                }
            }
        }
    }

    /**
     * Add LocationListener listeners
     */
    public void addListener(LocationListener listener)
    {
        synchronized (listeners)
        {
            listeners.add(listener);
        }
    }

    /**
     * Remove LocationListener listeners
     */
    public void removeListener(LocationListener listener)
    {
        synchronized (listeners)
        {
            listeners.remove(listener);
        }
    }

    /**
     * Notify all listeners of the current status
     */
    public void requestStatusUpdate()
    {
        Bundle extras = new Bundle();
        long lastUpdateTime = locationTimestamp;
        if (location != null)
        {
            lastUpdateTime = location.getTime();
            extras = location.getExtras();
        }
        notifyStatusChangedNoClear(locStatus, extras, lastUpdateTime);
    }

    /**
     * Start down the ExtGPS thread
     */
    public synchronized void start()
    {
        if (extGPSThread == null)
        {
            extGPSThread = new ExtGPSThread();
            extGPSThread.setDaemon(true);
            extGPSThread.start();            
            sendNmeaCmd("PSRF100,1,38400,8,1,0"); // NMEA only on
            sendNmeaCmd("PSRF103,00,00,01,01"); // GGA on
            sendNmeaCmd("PSRF103,01,00,00,01"); // GLL off
            sendNmeaCmd("PSRF103,02,00,00,01"); // GSA off
            sendNmeaCmd("PSRF103,03,00,00,01"); // GSV off
            sendNmeaCmd("PSRF103,04,00,01,01"); // RMC on
            sendNmeaCmd("PSRF103,05,00,00,01"); // VTG off
            locStatus = LocationProvider.OUT_OF_SERVICE;
            locationTime = null;
            hasGGA = false;
            hasRMC = false;
            lastNumSatellites = 0;
            location = null;
            lastRpsTime = System.currentTimeMillis();
        }
        synchronized (listeners)
        {
            for (LocationListener ll : listeners)
            {
                ll.onProviderEnabled(providerName);
            }
        }
        DebugLogManager.INSTANCE.log("ExtGPSManager.start() " + providerName, Log.DEBUG);
    }

    /**
     * Shut down the ExtGPS thread
     */
    public synchronized void stop()
    {
        if (extGPSThread != null)
        {
            extGPSThread.halt();
            extGPSThread = null;
            locStatus = LocationProvider.OUT_OF_SERVICE;
        }
        running = false;
        synchronized (listeners)
        {
            for (LocationListener ll : listeners)
            {
                ll.onProviderDisabled(providerName);
            }
        }
        DebugLogManager.INSTANCE.log("ExtGPSManager.stop() " + providerName, Log.DEBUG);
    }

    /**
     * The thread that handles all communications with the External GPS. This must be done in it's own thread as Android gets very picky about
     * unresponsive UI threads
     */
    private class ExtGPSThread extends Thread
    {
        private InputStreamReader in;
        private PrintStream out2;
        private boolean canWrite;

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
         * The main loop of the connection to the ECU
         */
        public void run()
        {
            try
            {
                ExtGPSConnectionManager.getInstance().init(null, ApplicationSettings.INSTANCE.getExtGPSBluetoothMac());

                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    DebugLogManager.INSTANCE.logException(e);
                }

                try
                {
                    ExtGPSConnectionManager.getInstance().connect();

                    in = new InputStreamReader(ExtGPSConnectionManager.getInstance().getInputStream(), "US-ASCII");
                    out2 = new PrintStream(ExtGPSConnectionManager.getInstance().getOutputStream(), false, "US-ASCII");

                    BufferedReader reader = new BufferedReader(in);
                    String s;
                    long delay = 100;

                    running = true;
                    canWrite = false;

                    // This is the actual work. Outside influences will toggle 'running' when we want this to stop
                    while (running)
                    {
                        if (reader.ready())
                        {
                            s = reader.readLine();
                            parseNmeaSentence(s);
                            canWrite = true;
                            if (locStatus == LocationProvider.AVAILABLE)
                            {
                                delay = 50;
                            }
                            else
                            {
                                // no location available, wait for more satellites
                                delay = 1000;
                            }
                        }
                        else
                        {
                            delay = 100;
                        }
                        SystemClock.sleep(delay);
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
            }
            finally
            {
                watch = null;
            }
        }

        /**
         * Write to the connected OutStream.
         * 
         * @param buffer The data to write
         */
        public void write(String buffer)
        {
            try
            {
                do
                {
                    Thread.sleep(100);
                } while ((running) && (!canWrite));
                if ((running) && (canWrite))
                {
                    out2.print(buffer);
                    out2.flush();
                }
            }
            catch (InterruptedException e)
            {
                DebugLogManager.INSTANCE.logException(e);
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
