package uk.org.smithfamily.mslogger.widgets;

import java.text.NumberFormat;

import uk.org.smithfamily.mslogger.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class NumericIndicator extends AutoResizeTextView implements Indicator

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
    private String       channel;
    private boolean disabled;

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
        disabled = a.getBoolean(R.styleable.Dial_disabled, false);
        
        
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
        this.font = Typeface.createFromAsset(context.getAssets(), "fonts/7segment.ttf");
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
        if (value < warningPoint)
            setTextColor(Color.GREEN);
        else if (value < errorPoint)
            setTextColor(Color.YELLOW);
        else
            setTextColor(Color.RED);
        if(disabled)
        {
            setTextColor(Color.LTGRAY);
            this.setText("---");
        }
        else
        {
            this.setText(formatter.format(this.value));
        }
        super.onDraw(canvas);
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        
        IndicatorManager.INSTANCE.registerIndicator(this);

    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        
        IndicatorManager.INSTANCE.deregisterIndicator(this);

    }
}
