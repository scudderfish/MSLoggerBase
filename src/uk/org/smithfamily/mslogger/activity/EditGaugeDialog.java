package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
import uk.org.smithfamily.mslogger.widgets.MSGauge;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * 
 */
public class EditGaugeDialog extends Dialog implements android.view.View.OnClickListener
{

    private GaugeDetails gd;
    private MSGauge      gauge;

    /**
     * 
     * @param context
     * @param gauge
     */
    public EditGaugeDialog(Context context, MSGauge gauge)
    {
        super(context);
        this.gauge = gauge;
        this.gd = gauge.getDetails();
    }

    /**
     * 
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.editgauge);

        setTitle("Edit Gauge Properties");

        setValue(R.id.editName, gd.getName());
        setValue(R.id.editChannel, gd.getChannel());
        setValue(R.id.editTitle, gd.getTitle());
        setValue(R.id.editUnits, gd.getUnits());
        setValue(R.id.editHi, Double.toString(gd.getMax()));
        setValue(R.id.editLo, Double.toString(gd.getMin()));
        setValue(R.id.editHiD, Double.toString(gd.getHiD()));
        setValue(R.id.editHiW, Double.toString(gd.getHiW()));
        setValue(R.id.editLoD, Double.toString(gd.getLoD()));
        setValue(R.id.editLoW, Double.toString(gd.getLoW()));
        setValue(R.id.editVD, Integer.toString(gd.getVd()));
        setValue(R.id.editLD, Integer.toString(gd.getLd()));
        setValue(R.id.editoffsetAngle, Double.toString(gd.getOffsetAngle()));
        Button buttonOK = (Button) findViewById(R.id.editOK);
        Button buttonReset = (Button) findViewById(R.id.editReset);
        Button buttonCancel = (Button) findViewById(R.id.editCancel);

        buttonOK.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

    }

    /**
     * 
     * @param id
     * @param value
     */
    private void setValue(int id, String value)
    {
        View v = findViewById(id);
        if (v instanceof TextView)
        {
            ((TextView) v).setText(value);
        }
    }

    /**
     * 
     */
    public void saveDetails()
    {
        gd.setName(getValue(R.id.editName));
        gd.setChannel(getValue(R.id.editChannel));
        gd.setTitle(getValue(R.id.editTitle));
        gd.setUnits(getValue(R.id.editUnits));
        gd.setMax(getValueD(R.id.editHi));
        gd.setMin(getValueD(R.id.editLo));
        gd.setHiD(getValueD(R.id.editHiD));
        gd.setHiW(getValueD(R.id.editHiW));
        gd.setLoD(getValueD(R.id.editLoD));
        gd.setLoW(getValueD(R.id.editLoW));
        gd.setVd(getValueI(R.id.editVD));
        gd.setLd(getValueI(R.id.editLD));
        gd.setOffsetAngle(getValueD(R.id.editoffsetAngle));
        GaugeRegister.INSTANCE.persistDetails(gd);
        gauge.initFromGD(gd);
        gauge.invalidate();
        dismiss();

    }

    /**
     * 
     */
    public void resetDetails()
    {
        GaugeRegister.INSTANCE.reset(gd.getName());
        gd = GaugeRegister.INSTANCE.getGaugeDetails(gd.getName());
        gauge.initFromGD(gd);
        gauge.invalidate();
        dismiss();
    }

    /**
     * 
     * @param id
     * @return
     */
    private int getValueI(int id)
    {
        int val = 0;
        View v = findViewById(id);
        if (v instanceof TextView)
        {
            String txt = ((TextView) v).getText().toString();
            val = Integer.parseInt(txt);
        }
        return val;
    }

    /**
     * 
     * @param id
     * @return
     */
    private double getValueD(int id)
    {
        double val = 0;
        View v = findViewById(id);
        if (v instanceof TextView)
        {
            String txt = ((TextView) v).getText().toString();
            val = Double.parseDouble(txt);
        }
        return val;
    }

    /**
     * 
     * @param id
     * @return
     */
    private String getValue(int id)
    {
        String val = "";
        View v = findViewById(id);
        if (v instanceof TextView)
        {
            String txt = ((TextView) v).getText().toString();
            val = txt;
        }
        return val;
    }

    /**
     * 
     * @param v
     */
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        if (which == R.id.editOK)
        {
            saveDetails();
        }
        else if (which == R.id.editReset)
        {
            resetDetails();
        }
        else
        {
            cancel();
        }
    }

}
