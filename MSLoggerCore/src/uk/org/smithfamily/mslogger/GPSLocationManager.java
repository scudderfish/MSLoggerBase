package uk.org.smithfamily.mslogger;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

public enum GPSLocationManager implements LocationListener
{
    INSTANCE;

    private Location        lastLocation = new Location("null");
    private LocationManager locationManager = null;

    synchronized public Location getLastKnownLocation()
    {
        return lastLocation;
    }

    synchronized public void start()
    {
        
        locationManager = (LocationManager) ApplicationSettings.INSTANCE.getContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        
    }

    synchronized public void stop()
    {
        
        locationManager.removeUpdates(this);
        locationManager = null;
        
    }

    protected void sendMessage(String msg)
    {
        Intent broadcast = new Intent();
        broadcast.setAction(ApplicationSettings.GENERAL_MESSAGE);
        broadcast.putExtra(ApplicationSettings.MESSAGE, msg);
        ApplicationSettings.INSTANCE.getContext().sendBroadcast(broadcast);

    }

    @Override
    public void onLocationChanged(Location location)
    {
        lastLocation = location;

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {
    }

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
