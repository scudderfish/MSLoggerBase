package uk.org.smithfamily.mslogger.activity;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.log.DatalogManager;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import uk.org.smithfamily.mslogger.log.EmailManager;
import uk.org.smithfamily.mslogger.log.FRDLogManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

final class LogButtonListener implements OnClickListener
{
    /**
     * 
     */
    private final MSLoggerActivity msLoggerActivity;
    private ToggleButton button;

    LogButtonListener(MSLoggerActivity msLoggerActivity, ToggleButton button)
    {
        this.msLoggerActivity = msLoggerActivity;
        this.button = button;
    }

    @Override
    public void onClick(View arg0)
    {
        DebugLogManager.INSTANCE.log("LogButton:" + button.isChecked());
        if (System.currentTimeMillis() > 1322697601000L)
        {
            this.msLoggerActivity.messages.setText("This beta version has expired");
            button.setChecked(false);
        }
        else
        {
//            this.msLoggerActivity.markButton.setEnabled(this.msLoggerActivity.logButton.isChecked());

            if (this.msLoggerActivity.service != null)
            {
                if (button.isChecked())
                {
                    this.msLoggerActivity.service.startLogging();
                }
                else
                {
                    this.msLoggerActivity.service.stopLogging();
                }
            }
        }
    }

}