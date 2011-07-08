package uk.org.smithfamily.msparser.location;

import uk.org.smithfamily.msparser.MSControlService;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

class GeneralLocationListener implements LocationListener, GpsStatus.Listener
{

	private static MSControlService mainActivity;

	GeneralLocationListener(MSControlService activity)
	{
		mainActivity = activity;
	}

	/**
	 * Event raised when a new fix is received.
	 */
	public void onLocationChanged(Location loc)
	{
		try
		{
			if (loc != null)
			{
			    bsh.commands.LocationManager.getInstance().log(loc.getLatitude(), loc.getLongitude());
//				mainActivity.OnLocationChanged(loc);
			}

		}
		catch (Exception ex)
		{
//			mainActivity.SetStatus(ex.getMessage());
		}

	}

	public void onProviderDisabled(String provider)
	{
//		mainActivity.RestartGpsManagers();
	}

	public void onProviderEnabled(String provider)
	{
//		mainActivity.RestartGpsManagers();
	}


	public void onGpsStatusChanged(int event)
	{
/*
		switch (event)
		{
			case GpsStatus.GPS_EVENT_FIRST_FIX:
//				mainActivity.SetStatus(mainActivity.getString(R.string.fix_obtained));
				break;

			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:

//				GpsStatus status = mainActivity.gpsLocationManager.getGpsStatus(null);

				int maxSatellites = status.getMaxSatellites();

				Iterator<GpsSatellite> it = status.getSatellites().iterator();
				int count = 0;
				//while (it.hasNext() && count <= maxSatellites)
				while (it.hasNext() && count <= maxSatellites)
				{
					@SuppressWarnings("unused")
					GpsSatellite s = it.next();
//					if (s.usedInFix())
//					{
						count++;
//					}
					//GpsSatellite oSat = (GpsSatellite) it.next();

					// Log.i("Main",
					// "LocationActivity - onGpsStatusChange: Satellites:"
					// + oSat.getSnr());
				}

				break;

			case GpsStatus.GPS_EVENT_STARTED:
				mainActivity.SetStatus(mainActivity.getString(R.string.started_waiting));
				break;

			case GpsStatus.GPS_EVENT_STOPPED:
				mainActivity.SetStatus(mainActivity.getString(R.string.gps_stopped));
				break;

		}
		*/
	}

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2)
    {
        // TODO Auto-generated method stub
        
    }
}
