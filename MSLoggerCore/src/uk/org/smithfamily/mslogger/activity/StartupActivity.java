package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.MSLoggerApplication;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.ECUFingerprint;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.EmailManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartupActivity extends Activity
{
    private static final int REQUEST_ENABLE_BT = 0;

    private class StartupHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
            case MSLoggerApplication.GOT_SIG:
                checkSig((String) msg.obj);
                break;
            case MSLoggerApplication.MESSAGE_TOAST:
                showMessage(msg);
            }
        }
    }

    private Handler          mHandler = null;
    private BluetoothAdapter mBluetoothAdapter;
    private TextView msgBox;
    private Button quitButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mHandler = new StartupHandler();
        setContentView(R.layout.startup);
        msgBox = (TextView)findViewById(R.id.identify_progress_msg);
        quitButton = (Button)findViewById(R.id.startup_quit_button);
        
        quitButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v)
            {
                finish();
            }});
        
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null)
        {
            finishDialogNoBluetooth();
            return;
        }
        if (ApplicationSettings.INSTANCE.btDeviceSelected())
        {
            startFingerprint();
        }
        else
        {
            Intent serverIntent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(serverIntent, MSLoggerApplication.REQUEST_CONNECT_DEVICE);
        }
        
    }

    public void showMessage(Message msg)
    {
        String text = msg.getData().getString(MSLoggerApplication.MSG_ID);
        msgBox.setText(text);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {

        case MSLoggerApplication.REQUEST_CONNECT_DEVICE:

            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK)
            {
                // Get the device MAC address
                String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

                ApplicationSettings.INSTANCE.setBluetoothMac(address);
                startFingerprint();
            }
            break;

        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (resultCode != Activity.RESULT_OK)
            {
                finishDialogNoBluetooth();
            }
        }
    }

    public void checkSig(String sig)
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuForSig(sig);
        if (ecu == null)
        {
            unrecognisedEcu(sig);
        }
        else
        {
            ApplicationSettings.INSTANCE.setEcu(ecu);

            Intent loggerIntent = new Intent(this, MSLoggerActivity.class);
            startActivity(loggerIntent);

        }
    }

    private void unrecognisedEcu(final String sig)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("I don't recognise your ECU, would you like to email the developer about this?").setTitle(R.string.app_name)
                .setPositiveButton(R.string.bt_ok, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        constructEmail(sig);
                    }

                }).setNegativeButton(R.string.cancel_buy_button, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void constructEmail(String sig)
    {
        EmailManager.email(this, "dave@mslogger.co.uk", null, "Unrecognised firmware signature",
                "An unknown firmware was detected with a signature of '" + sig + "'.\n\nPlease consider this for the next release.", null);

    }

    private void startFingerprint()
    {
        Thread t = new Thread(new ECUFingerprint(this, mHandler, mBluetoothAdapter));
        t.start();
    }

    public void finishDialogNoBluetooth()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.no_bt).setIcon(android.R.drawable.ic_dialog_info).setTitle(R.string.app_name).setCancelable(false)
                .setPositiveButton(R.string.bt_ok, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
