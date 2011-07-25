package bsh.commands;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public enum LocationController implements LocationListener
{
    INSTANCE;
    
    private Location	currentLocation;
    
	@Override
	public void onLocationChanged(Location location)
	{
		currentLocation = location;
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		// TODO Auto-generated method stub
		
	}

	public double getCurrentLat()
	{
		if(currentLocation != null)
		{
			return currentLocation.getLatitude();
		}
		return 0.0;
	}

	public double getCurrentLon()
	{
		if(currentLocation != null)
		{
			return currentLocation.getLongitude();
		}
		return 0.0;
	}

	public double getCurrentSpeed()
	{
		if(currentLocation != null)
		{
			return currentLocation.getSpeed();
		}
		return 0.0;
	}

	public double getCurrentBearing()
	{
		if(currentLocation != null)
		{
			return currentLocation.getBearing();
		}
		return 0.0;
		}

}
