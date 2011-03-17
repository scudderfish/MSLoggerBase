package uk.org.smithfamily.msparser.widgets;

import java.text.NumberFormat;
import java.text.ParseException;

import uk.org.smithfamily.msparser.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class NumericIndicator extends TextView implements Indicator

{

    private float        min;
    private float        max;
    private String       title;
    private float        warningPoint;
    private float        errorPoint;
    private float        value;
    private Typeface     font;
    private int          dp;
    private NumberFormat formatter;
    private float        defaultSize = 12;
    private String       channel;

    public NumericIndicator(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setFont(context);
        setupDefaults(context);

    }

    public NumericIndicator(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setFont(context);
        setupDefaults(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Dial);
        value = a.getFloat(R.styleable.Dial_numValue, value);
        max = a.getFloat(R.styleable.Dial_scaleMaxValue, max);
        min = a.getFloat(R.styleable.Dial_scaleMinValue, min);
        warningPoint = a.getFloat(R.styleable.Dial_rangeWarningMinValue, warningPoint);
        errorPoint = a.getFloat(R.styleable.Dial_rangeErrorMinValue, errorPoint);
        channel = a.getString(R.styleable.Dial_channel);
        
        setupFormat();
    }

    public NumericIndicator(Context context)
    {
        super(context);
        setFont(context);
        setupDefaults(context);
        setupFormat();
    }

    private void setupDefaults(Context context)
    {
        min = 10;
        max = 20;
        value = 14.7f;
        title = "AFR";
        warningPoint = 16;
        errorPoint = 17;

        defaultSize = getTextSize();

    }

    private void setupFormat()
    {
        double range = Math.abs(this.max - this.min);
        if (range <= 12)
            this.dp = 2;
        else if (range < 100)
            this.dp = 1;
        else
            this.dp = 0;

        if (dp == 0)
        {
            formatter = NumberFormat.getIntegerInstance();
        }
        else
        {
            formatter = NumberFormat.getNumberInstance();
        }

        formatter.setGroupingUsed(false);
        formatter.setMaximumFractionDigits(dp);
        formatter.setMinimumFractionDigits(dp);
        this.setText(formatter.format(this.value));
    }

    private void setFont(Context context)
    {
        this.font = Typeface.createFromAsset(context.getAssets(), "fonts/ziska.ttf");
        this.setTypeface(font);

    }

    public void setMin(float min)
    {
        this.min = min;
        setupFormat();

    }

    public void setMax(float max)
    {
        this.max = max;
        setupFormat();

    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setWarningPoint(float warn)
    {
        this.warningPoint = warn;
    }

    public void setErrorPoint(float err)
    {
        this.errorPoint = err;
    }

    public void setCurrentValue(float value)
    {
        this.value = value;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int h = getHeight();
        int w = getWidth();
        int padding = getPaddingTop() + getPaddingBottom();
        setTextSize(TypedValue.COMPLEX_UNIT_PX, h - padding);

        if (value < warningPoint)
            setTextColor(Color.GREEN);
        else if (value < errorPoint)
            setTextColor(Color.YELLOW);
        else
            setTextColor(Color.RED);
        this.setText(formatter.format(this.value));

        super.onDraw(canvas);
    }

    public void setDefaultSize(float size)
    {
        defaultSize = size;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize);

        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * Gets the width in pixels of the text
     * 
     * @return
     */
    private float getTextWidth()
    {
        return getPaint().measureText(getText().toString());
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }
}
