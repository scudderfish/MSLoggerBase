package uk.org.smithfamily.mslogger;

import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MSLoggerService extends Service
{

	public class MSLoggerBinder extends Binder
	{
		MSLoggerService getService()
		{
			return MSLoggerService.this;
		}
	}

	private final IBinder	mBinder		= new MSLoggerBinder();
	private Megasquirt		ecuDefinition;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0)
	{
		return mBinder;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();

		startLogging();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		stopLogging();
	}

	private void connect()
	{
		ecuDefinition.start();
	}

	public double getValue(String channelName)
	{
		return ecuDefinition.getValue(channelName);
	}

	public void startLogging()
	{
		Toast.makeText(this, "Connecting to MS...", Toast.LENGTH_SHORT).show();
		connect();
		ecuDefinition.start();
	}

	public void stopLogging()
	{
		Toast.makeText(this, "Disconnecting from MS...", Toast.LENGTH_LONG).show();
		ecuDefinition.stop();
	}
}
