package uk.org.smithfamily.mslogger.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ExtGPSManager;
import uk.org.smithfamily.mslogger.GPSLocationManager;
import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.comms.Connection;
import uk.org.smithfamily.mslogger.comms.ConnectionFactory;
import uk.org.smithfamily.mslogger.dashboards.DashboardIO;
import uk.org.smithfamily.mslogger.dashboards.DashboardPagerAdapter;
import uk.org.smithfamily.mslogger.dashboards.DashboardViewPager;
import uk.org.smithfamily.mslogger.dashboards.pageindicators.CirclePageIndicator;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DatalogManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.log.EmailManager;
import uk.org.smithfamily.mslogger.log.FRDLogManager;

/**
 * Main activity class where the main window (gauges) are and where the bottom menu is handled
 */
public class MSLoggerActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener, OnClickListener
{
    private final BroadcastReceiver updateReceiver = new Reciever();
    private TextView messages;
    private TextView rps;
    private static final Boolean ready = null;

    private boolean dashboardEditEnabled;

    private static final int SHOW_PREFS = 124230;

    private boolean registered;
    private DashboardViewPager pager;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final Connection conn = ConnectionFactory.INSTANCE.getConnection();

        // Bluetooth is not supported on this Android device
        if (!conn.connectionPossible())
        {
            finishDialogNoBluetooth();
            return;
        }
        checkSDCard();

        setGaugesOrientation();

        setContentView(R.layout.main);
        pager = findViewById(R.id.dashboardpager);

        final DashboardPagerAdapter dashAdapter = new DashboardPagerAdapter(this, pager);
        pager.setAdapter(dashAdapter);

        // Bind the title indicator to the adapter
        final CirclePageIndicator circleIndicator = findViewById(R.id.separator);
        circleIndicator.setViewPager(pager);
        circleIndicator.setOnPageChangeListener(dashAdapter);
        messages = findViewById(R.id.messages);
        rps = findViewById(R.id.RPS);

        /*
         * Get status message from saved instance, for example when switching from landscape to portrait mode
         */
        if (savedInstanceState != null)
        {
            if (!Objects.equals(savedInstanceState.getString("status_message"), ""))
            {
                messages.setText(savedInstanceState.getString("status_message"));
            }

            if (!Objects.equals(savedInstanceState.getString("rps_message"), ""))
            {
                rps.setText(savedInstanceState.getString("rps_message"));
            }

            if (savedInstanceState.getBoolean("gauge_edit"))
            {
                dashboardEditEnabled = true;
            }
        }

        final SharedPreferences prefsManager = PreferenceManager.getDefaultSharedPreferences(MSLoggerActivity.this);
        prefsManager.registerOnSharedPreferenceChangeListener(MSLoggerActivity.this);

        ApplicationSettings.INSTANCE.setDefaultAdapter(BluetoothAdapter.getDefaultAdapter());
        GPSLocationManager.INSTANCE.start();
        ApplicationSettings.INSTANCE.setAutoConnectOverride(null);

        registerMessages();

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        setGaugesOrientation();
    }

    /**
     * Force a screen orientation if specified by the user, otherwise look at the sensor and rotate screen as needed
     */
    @SuppressLint("SourceLockedOrientationActivity")
    private void setGaugesOrientation()
    {
        final String activityOrientation = ApplicationSettings.INSTANCE.getGaugesOrientation();

        // Force landscape
        if (activityOrientation.equals("FORCE_LANDSCAPE"))
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        // Force portrait
        else if (activityOrientation.equals("FORCE_PORTRAIT"))
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
    }

    /**
     * Save the bottom text views content so they can keep their state while device is rotated
     */
    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putString("status_message", messages.getText().toString());
        outState.putString("rps_message", rps.getText().toString());
        outState.putBoolean("gauge_edit", dashboardEditEnabled);
    }

    /**
     * Check if the SD card is present
     */
    private void checkSDCard()
    {
        final boolean cardOK = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        ApplicationSettings.INSTANCE.setWritable(cardOK);
        if (!cardOK)
        {
            showDialog(2);
        }
    }

    /**
     * Clean up messages and GPS when the app is stopped
     */
    @Override
    protected void onDestroy()
    {
        deRegisterMessages();
        GPSLocationManager.INSTANCE.stop();
        super.onDestroy();
    }

    /**
     * Save the indicators when the app is stopped
     */
    @Override
    public void onStop()
    {
        super.onStop();
    }

    /**
     * Register the receiver with the message to receive from the Megasquirt connection
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private void registerMessages()
    {
        final IntentFilter connectedFilter = new IntentFilter(Megasquirt.CONNECTED);
        registerReceiver(updateReceiver, connectedFilter);

        final IntentFilter disconnectedFilter = new IntentFilter(Megasquirt.DISCONNECTED);
        registerReceiver(updateReceiver, disconnectedFilter);

        final IntentFilter dataFilter = new IntentFilter(Megasquirt.NEW_DATA);
        registerReceiver(updateReceiver, dataFilter);

        final IntentFilter msgFilter = new IntentFilter(ApplicationSettings.GENERAL_MESSAGE);
        registerReceiver(updateReceiver, msgFilter);

        final IntentFilter rpsFilter = new IntentFilter(ApplicationSettings.RPS_MESSAGE);
        registerReceiver(updateReceiver, rpsFilter);

        final IntentFilter toastFilter = new IntentFilter(ApplicationSettings.TOAST);
        registerReceiver(updateReceiver, toastFilter);

        final IntentFilter unknownEcuFilter = new IntentFilter(Megasquirt.UNKNOWN_ECU);
        registerReceiver(updateReceiver, unknownEcuFilter);

        final IntentFilter unknownEcuBTFilter = new IntentFilter(Megasquirt.UNKNOWN_ECU_BT);
        registerReceiver(updateReceiver, unknownEcuBTFilter);

        final IntentFilter probeEcuFilter = new IntentFilter(Megasquirt.PROBE_ECU);
        registerReceiver(updateReceiver, probeEcuFilter);

        registered = true;
    }

    /**
     * Unregister receiver
     */
    private void deRegisterMessages()
    {
        if (registered)
        {
            unregisterReceiver(updateReceiver);
        }
    }

    /**
     *
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu)
    {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Triggered just before the bottom menu are displayed, used to update the state of some menu items
     *
     */
    @Override
    public boolean onPrepareOptionsMenu(final Menu menu)
    {
        final MenuItem editItem = menu.findItem(R.id.dashboardEditing);
        final Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();

        editItem.setEnabled(ecuDefinition != null);
        if (dashboardEditEnabled)
        {
            editItem.setIcon(R.drawable.ic_menu_disable_gauge_editing);
            editItem.setTitle(R.string.disable_dashboard_edit);
        }
        else
        {
            editItem.setIcon(R.drawable.ic_menu_enable_gauge_editing);
            editItem.setTitle(R.string.enable_dashboard_edit);
        }
        final MenuItem connectionItem = menu.findItem(R.id.forceConnection);
        if ((ecuDefinition != null) && ecuDefinition.isConnected())
        {
            connectionItem.setIcon(R.drawable.ic_menu_disconnect);
            connectionItem.setTitle(R.string.disconnect);
        }
        else
        {
            connectionItem.setIcon(R.drawable.ic_menu_connect);
            connectionItem.setTitle(R.string.connect);
        }

        final MenuItem loggingItem = menu.findItem(R.id.forceLogging);
        loggingItem.setEnabled((ecuDefinition != null) && ecuDefinition.isConnected());

        if ((ecuDefinition != null) && ecuDefinition.isLogging())
        {
            loggingItem.setIcon(R.drawable.ic_menu_stop_logging);
            loggingItem.setTitle(R.string.stop_logging);
        }
        else
        {
            loggingItem.setIcon(R.drawable.ic_menu_start_logging);
            loggingItem.setTitle(R.string.start_logging);
        }

        final MenuItem tuningItem = menu.findItem(R.id.tuning);
        tuningItem.setEnabled((ecuDefinition != null) && ecuDefinition.isConnected());

        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Triggered when a bottom menu item is clicked
     * 
     * @param item The clicked menu item
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item)
    {
        final int itemId = item.getItemId();
        switch (itemId)
        {

//        case R.id.forceConnection:
//            toggleConnection();
//            return true;
//        case R.id.dashboardEditing:
//            toggleEditing();
//            return true;
//        case R.id.forceLogging:
//            toggleLogging();
//            return true;
//        case R.id.manageDatalogs:
//            openManageDatalogs();
//            return true;
//        case R.id.tuning:
//            openTuning();
//            return true;
//        case R.id.preferences:
//            openPreferences();
//            return true;
//        case R.id.resetIndicators:
//            resetIndicators();
//            return true;
//        case R.id.calibrate:
//            openCalibrateTPS();
//            return true;
//        case R.id.about:
//            showAbout();
//            return true;
//        case R.id.quit:
//            quit();
//            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Start the background task to reset the indicators
     */
    public void resetIndicators()
    {
    }

    /**
     * Toggle the gauge editing
     */
    private void toggleEditing()
    {
        // Dashboard editing is enabled
        if (dashboardEditEnabled)
        {
            DashboardIO.INSTANCE.saveDash();
        }
        // Dashboard editing is not enabled
        else
        {
            Toast.makeText(getApplicationContext(), R.string.edit_dashboard_instructions, Toast.LENGTH_LONG).show();
        }

        dashboardEditEnabled = !dashboardEditEnabled;
        pager.setEditMode(dashboardEditEnabled);
    }

    /**
     * Toggle data logging of the Megasquirt
     */
    private void toggleLogging()
    {
        final Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();

        if ((ecu != null) && ecu.isConnected())
        {
            if (ecu.isLogging())
            {
                ecu.stopLogging();
                sendLogs();
            }
            else
            {
                ecu.startLogging();
            }
        }
    }

    /**
     * If the user opted in to get logs sent to his email, we prompt him
     */
    private void sendLogs()
    {
        if (ApplicationSettings.INSTANCE.emailEnabled())
        {
            DatalogManager.INSTANCE.close();
            FRDLogManager.INSTANCE.close();
            final List<String> paths = new ArrayList<String>();
            paths.add(DatalogManager.INSTANCE.getAbsolutePath());
            paths.add(FRDLogManager.INSTANCE.getAbsolutePath());
            paths.add(DebugLogManager.INSTANCE.getAbsolutePath());
            final String emailText = getString(R.string.email_body);

            final String subject = String.format(getString(R.string.email_subject), System.currentTimeMillis());
            EmailManager.email(this, ApplicationSettings.INSTANCE.getEmailDestination(), null, subject, emailText, paths);
        }

    }

    /**
     * Quit the application cleanly by stopping the connection to the Megasquirt if it exists
     */
    private void quit()
    {
        ApplicationSettings.INSTANCE.setAutoConnectOverride(false);
        ExtGPSManager.INSTANCE.stop();
        final Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();

        if ((ecu != null) && ecu.isConnected())
        {
            ecu.stop();
        }

        sendLogs();

        if ((ecu != null) && ecu.isConnected())
        {
            ecu.reset();
        }

        this.finish();
    }

    /**
     * Toggle the connection to the Megasquirt
     */
    private void toggleConnection()
    {
        final Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        if ((ecu != null) && ecu.isConnected())
        {
            ecu.stop();
        }
        else
        {
            ecu.start();
        }
    }

    /**
     * Open an about dialog
     */
    private void showAbout()
    {
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.about);

        final TextView text = dialog.findViewById(R.id.text);
        String title = "";
        try
        {
            final PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
            final ApplicationInfo ai = pInfo.applicationInfo;

            final String applicationName = (ai != null) ? getPackageManager().getApplicationLabel(ai).toString() : "(unknown)";
            title = applicationName + " " + pInfo.versionName;
        }
        catch (final NameNotFoundException e)
        {
            DebugLogManager.INSTANCE.logException(e);
        }
        dialog.setTitle(title);

        text.setText(R.string.about_text);
        final ImageView image = dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.icon);

        dialog.show();
    }

    /**
     * Open the calibrate TPS activity
     */
    private void openCalibrateTPS()
    {
        final Intent launchCalibrate = new Intent(this, CalibrateActivity.class);
        startActivity(launchCalibrate);
    }

    /**
     * Open the manage datalogs activity
     */
    private void openManageDatalogs()
    {
        final Intent lauchManageDatalogs = new Intent(this, ManageDatalogsActivity.class);
        startActivity(lauchManageDatalogs);
    }

    /**
     * Open the preferences activity
     */
    private void openPreferences()
    {
        final Intent launchPrefs = new Intent(this, PreferencesActivity.class);
        startActivityForResult(launchPrefs, SHOW_PREFS);
    }

    /**
     *
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
            case SHOW_PREFS:
                final Boolean dirty = (Boolean) Objects.requireNonNull(data.getExtras()).get(PreferencesActivity.DIRTY);
                if (Boolean.TRUE.equals(dirty))
                {
                    final Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();
                    if (ecuDefinition != null)
                    {
                        ecuDefinition.refreshFlags();
                    }
                }
                break;

            case MSLoggerApplication.REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                // Get the device MAC address
                final String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

                ApplicationSettings.INSTANCE.setECUBluetoothMac(address);
                break;
            }
        }
    }

    /**
     * Called when a preference change
     *
     */
    @Override
    public void onSharedPreferenceChanged(final SharedPreferences prefs, final String key)
    {
        if ((ready == null) || !ready)
        {
            return;
        }
        final Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();

        if (ApplicationSettings.INSTANCE.btDeviceSelected() && (ecuDefinition != null))
        {
            ecuDefinition.refreshFlags();
        }

        // When the TEMP/MAP/EGO are changed in preferences, we refresh the ECU flags if we are online
        if ((key.equals("temptype") || key.equals("maptype") || key.equals("egotype")) && (ecuDefinition != null))
        {
            ecuDefinition.refreshFlags();
        }
    }

    /**
     *
     */
    @Override
    public void onClick(final View v)
    {
    }

    /**
     * Receiver that get events from other activities about Megasquirt status and activities
     */
    private final class Reciever extends BroadcastReceiver
    {
        /**
         * When an event is received
         *
         */
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(final Context context, final Intent intent)
        {
            final String action = intent.getAction();
            final boolean autoLoggingEnabled = ApplicationSettings.INSTANCE.getAutoLogging();

            switch (Objects.requireNonNull(action)) {
                case Megasquirt.CONNECTED: {
                    final Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();

                    DebugLogManager.INSTANCE.log(action, Log.INFO);
                    if (autoLoggingEnabled && (ecu != null) && ecu.isConnected()) {
                        ecu.startLogging();
                    }
                    break;
                }
                case Megasquirt.DISCONNECTED: {
                    DebugLogManager.INSTANCE.log(action, Log.INFO);
                    if (autoLoggingEnabled) {
                        DatalogManager.INSTANCE.mark("Connection lost");
                    }

                    messages.setText(R.string.disconnected_from_ms);
                    rps.setText("");

                    final Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
                    if ((ecu != null) && ecu.isConnected()) {
                        ecu.stop();
                    }
                    break;
                }
                case ApplicationSettings.GENERAL_MESSAGE: {
                    final String msg = intent.getStringExtra(ApplicationSettings.MESSAGE);

                    messages.setText(msg);
                    DebugLogManager.INSTANCE.log("Message : " + msg, Log.INFO);
                    break;
                }
                case ApplicationSettings.RPS_MESSAGE:
                    final String RPS = intent.getStringExtra(ApplicationSettings.RPS);

                    rps.setText(RPS + " reads / second");
                    break;
                case ApplicationSettings.TOAST: {
                    final String msg = intent.getStringExtra(ApplicationSettings.TOAST_MESSAGE);

                    // The toast is called in a loop so it can be displayed longer (Toast.LENGTH_LONG = 3.5 seconds)
                    for (int j = 0; j < 2; j++) {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                case Megasquirt.UNKNOWN_ECU:
                    if (isFinishing()) {
                        return;
                    }
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MSLoggerActivity.this);
                    builder.setMessage(R.string.unrecognised_ecu).setTitle(R.string.app_name).setPositiveButton(R.string.bt_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, final int id) {
                            constructEmail(ApplicationSettings.INSTANCE.getEcuDefinition().getTrueSignature());
                        }

                        private void constructEmail(final String trueSignature) {
                            EmailManager.email(MSLoggerActivity.this, "mslogger.android@gmail.com", null, "Unrecognised firmware signature", "An unknown firmware was detected with a signature of '" + trueSignature
                                    + "'.\n\nPlease consider this for the next release.", null);
                        }

                    }).setNegativeButton(R.string.bt_cancel, (dialog, which) -> dialog.cancel());

                    final AlertDialog alert = builder.create();
                    if (!isFinishing()) {
                        alert.show();
                    }

                    break;
                case Megasquirt.UNKNOWN_ECU_BT:
                    final Intent serverIntent = new Intent(MSLoggerActivity.this, DeviceListActivity.class);
                    startActivityForResult(serverIntent, MSLoggerApplication.REQUEST_CONNECT_DEVICE);
                    break;
            }
        }
    }

    /**
     * It was determinated that the android device don't support Bluetooth, so we tell the user
     */
    public void finishDialogNoBluetooth()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.no_bt).setIcon(android.R.drawable.ic_dialog_info).setTitle(R.string.app_name).setCancelable(false).setPositiveButton(R.string.bt_ok, (dialog, id) -> finish());
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
