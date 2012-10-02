package uk.org.smithfamily.mslogger;

import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.content.Context;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.util.Log;

/**
 * Helper class used for GPS location
 */
public enum GPSLocationManager implements LocationListener
{
    INSTANCE;

    private Location lastLocation = new Location("null");
    private LocationManager locationManager = null;
    private int freshFlag;

    /**
     * @return Last known location object
     */
    public synchronized Location getLastKnownLocation()
    {
        return lastLocation;
    }

    /**
     * Indicates freshness of the update. Set to 1 if this is the first call since the last update
     * 
     * @return
     */
    public synchronized int getFreshness()
    {
        int value = freshFlag;
        freshFlag = 0;
        return value;
    }

    /**
     * Start the location service
     */
    public synchronized void start()
    {
        if (ApplicationSettings.INSTANCE.isExternalGPSEnabled())
        {
            ExtGPSManager.INSTANCE.addListener(this);
            if (ApplicationSettings.INSTANCE.isExtGPSActive())
            {
                ExtGPSManager.INSTANCE.start();
            }
        }
        else
        {
            locationManager = (LocationManager) ApplicationSettings.INSTANCE.getContext().getSystemService(Context.LOCATION_SERVICE);
            Criteria c = new Criteria();
            c.setAccuracy(Criteria.ACCURACY_FINE);
            String providerName = locationManager.getBestProvider(c, false);

            if (ApplicationSettings.INSTANCE.logLevel < 8) DebugLogManager.INSTANCE.log("Using location provider " + providerName, Log.INFO);

            if (providerName != null)
            {
                locationManager.requestLocationUpdates(providerName, 100, 0, this);
            }
            else
            {
                sendMessage("No location provider available");
            }
        }
    }

    /**
     * Stop the location service
     */
    public synchronized void stop()
    {
        ExtGPSManager.INSTANCE.removeListener(this);
        ExtGPSManager.INSTANCE.stop();
        ApplicationSettings.INSTANCE.setExtGPSActive(false);

        if (locationManager != null)
        {
            locationManager.removeUpdates(this);
        }
        locationManager = null;

    }

    /**
     * Broadcast a message to the application
     * 
     * @param msg Message to be broadcasted
     */
    protected void sendMessage(String msg)
    {
        Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.GENERAL_MESSAGE);
        broadcast.putExtra(ApplicationSettings.MESSAGE, msg);
        ApplicationSettings.INSTANCE.getContext().sendBroadcast(broadcast);
    }

    /**
     * Triggered when the location change
     * 
     * @param location The new location
     */
    @Override
    public synchronized void onLocationChanged(Location location)
    {
        lastLocation = location;
        freshFlag = 1;
    }

    /**
     * @param provider The provider that was disabled
     */
    @Override
    public void onProviderDisabled(String provider)
    {

    }

    /**
     * @param provider The provider that was enabled
     */
    @Override
    public void onProviderEnabled(String provider)
    {
    }

    /**
     * Triggered when a status change
     * 
     * @param provider The provider
     * @param status The new status
     * @param extras Other useful information
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        if (status == LocationProvider.OUT_OF_SERVICE)
        {
            sendMessage(provider + " is out of service");
        }

        if (status == LocationProvider.AVAILABLE)
        {
            sendMessage(provider + " is available");
        }

        if (status == LocationProvider.TEMPORARILY_UNAVAILABLE)
        {
            sendMessage(provider + " is temporarily unavailable");
        }

    }
}
