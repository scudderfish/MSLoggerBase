package uk.org.smithfamily.mslogger.activity;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.widgets.GaugeDetails;
import uk.org.smithfamily.mslogger.widgets.GaugeRegister;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditGaugeDialog extends Dialog implements android.view.View.OnClickListener
{

	private GaugeDetails	gd;

	public EditGaugeDialog(Context context, String name)
	{
		super(context);
		this.gd = GaugeRegister.INSTANCE.getGaugeDetails(name);
	}

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
		Button buttonOK = (Button) findViewById(R.id.editOK);
		Button buttonReset = (Button) findViewById(R.id.editReset);
		Button buttonCancel = (Button) findViewById(R.id.editCancel);

		buttonOK.setOnClickListener(this);
		buttonReset.setOnClickListener(this);
		buttonCancel.setOnClickListener(this);

	}

	private void setValue(int id, String value)
	{
		View v = findViewById(id);
		if (v instanceof TextView)
		{
			((TextView) v).setText(value);
		}
	}

	public void saveDetails()
	{
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

		GaugeRegister.INSTANCE.persistDetails(gd);

		dismiss();

	}

	public void resetDetails()
	{
		GaugeRegister.INSTANCE.reset(gd.getName());
		dismiss();
	}

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

	@Override
	public void onClick(View v)
	{
		int which = v.getId();
		switch (which)
		{
		case R.id.editOK:
			saveDetails();
			break;
		case R.id.editReset:
			resetDetails();
			break;
		default:
			cancel();
		}
	}

}