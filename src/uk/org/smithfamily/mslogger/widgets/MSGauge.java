package uk.org.smithfamily.mslogger.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MSGauge extends View implements Indicator
{
    private int    diameter;
    private String name    = "RPM";
    private String title   = "RPM";
    private String channel = "rpm";
    private String units   = "";
    private double min     = 0;
    private double max     = 7000;
    private double lowD    = 0;
    private double lowW    = 0;
    private double hiW     = 5000;
    private double hiD     = 7000;
    private int    vd      = 0;
    private int    ld      = 0;
    private double value   = 2500;
    private double pi      = Math.PI;
    final float    scale   = getResources().getDisplayMetrics().density;

    public MSGauge(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MSGauge(Context c, AttributeSet s)
    {
        super(c, s);
    }

    public MSGauge(Context context, AttributeSet attr, int defaultStyles)
    {

        super(context, attr, defaultStyles);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec)
    {

        int measuredWidth = MeasureSpec.getSize(widthSpec);

        int measuredHeight = MeasureSpec.getSize(heightSpec);

        /*
         * measuredWidth and measured height are your view boundaries. You need to change these values based on your requirement E.g.
         * 
         * if you want to draw a circle which fills the entire view, you need to select the Min(measuredWidth,measureHeight) as the radius.
         * 
         * Now the boundary of your view is the radius itself i.e. height = width = radius.
         */

        /* After obtaining the height, width of your view and performing some changes you need to set the processed value as your view dimension by using the method setMeasuredDimension */

        diameter = Math.min(measuredHeight, measuredWidth);
        setMeasuredDimension(diameter, diameter);

        /*
         * If you consider drawing circle as an example, you need to select the minimum of height and width and set that value as your screen dimensions
         * 
         * int d=Math.min(measuredWidth, measuredHeight);
         * 
         * setMeasuredDimension(d,d);
         */

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int height = getMeasuredHeight();

        int width = getMeasuredWidth();

        // Find the center

        int px = width / 2;

        int py = height / 2;

        // Draw the string in the center of the control
        drawRing(canvas, px, py);

        drawScale(canvas, px, py);

        drawPointer(canvas, px, py);

        drawValue(canvas, px, py);
        drawTitle(canvas, px, py);

        // canvas.drawText(displayText, px - textWidth / 2, py, mTextPaint);

    }

    private void drawTitle(Canvas canvas, int cx, int cy)
    {
        Paint titlePaint = new Paint();
        titlePaint.setColor(Color.WHITE);
        titlePaint.setTextSize((cx / 5) / scale);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(title, cx, (float) (cy - (cy * 0.5)), titlePaint);

    }

    private void drawValue(Canvas canvas, int cx, int cy)
    {
        Paint valuePaint = new Paint();
        valuePaint.setColor(Color.WHITE);
        valuePaint.setTextSize((cx / 4) / scale);
        valuePaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(Integer.toString((int) value), cx, (float) (cy + (cy * 0.65)), valuePaint);
    }

    private void drawPointer(Canvas canvas, int cx, int cy)
    {
        int radius = (int) (cx * 0.85);

        double range = 270.0 / (max - min);
        double angle = value * range;
        double rads = angle * pi / 180.0;
        int x = (int) (cx - radius * Math.cos(rads - pi / 2.0));
        int y = (int) (cy - radius * Math.sin(rads - pi / 2.0));

        Paint pointerPaint = new Paint();
        pointerPaint.setColor(Color.WHITE);
        pointerPaint.setAntiAlias(true);
        pointerPaint.setStrokeWidth(cx / 48);
        pointerPaint.setStyle(Style.FILL_AND_STROKE);

        canvas.drawLine(cx, cy, x, y, pointerPaint);
        // canvas.drawText(Integer.toString((int) val), x, y, scalePaint);
    }

    private void drawScale(Canvas canvas, int cx, int cy)
    {
        int radius = (int) (cx * 0.85);
        Paint scalePaint = new Paint();
        // scalePaint.setStyle(Paint.Style.STROKE);
        scalePaint.setColor(Color.WHITE);
        // scalePaint.setStrokeWidth(0.005f);
        // scalePaint.setAntiAlias(true);
        scalePaint.setTextSize((cx / 8) / scale);
        // scalePaint.setTypeface(Typeface.SANS_SERIF);
        // scalePaint.setTextScaleX(0.8f);
        scalePaint.setTextAlign(Paint.Align.CENTER);

        double step = (max - min) / 10;
        for (double val = min; val <= max; val += step)
        {
            double range = 270.0 / (max - min);
            double angle = val * range;
            double rads = angle * pi / 180.0;
            int x = (int) (cx - radius * Math.cos(rads - pi / 2.0));
            int y = (int) (cy - radius * Math.sin(rads - pi / 2.0));
            canvas.drawText(Integer.toString((int) val), x, y, scalePaint);
        }
    }

    private void drawRing(Canvas canvas, int px, int py)
    {
        Paint ringPaint = new Paint();

        ringPaint.setColor(Color.LTGRAY);
        canvas.drawCircle(px, py, diameter / 2, ringPaint);
        ringPaint.setColor(Color.BLACK);
        canvas.drawCircle(px, py, (float) ((diameter * 0.98) / 2.0), ringPaint);
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public void setChannel(String channelName)
    {
        this.channel = channelName;

    }

    @Override
    public void setTitle(String title)
    {
        this.title = title;

    }

    @Override
    public void setUnits(String units)
    {
        this.units = units;

    }

    @Override
    public void setMin(float min)
    {
        this.min = min;
    }

    @Override
    public void setMax(float max)
    {
        this.max = max;

    }

    @Override
    public void setLowD(float lowD)
    {
        this.lowD = lowD;

    }

    @Override
    public void setLowW(float lowW)
    {
        this.lowW = lowW;

    }

    @Override
    public void setHiW(float hiW)
    {
        this.hiW = hiW;

    }

    @Override
    public void setHiD(float hiD)
    {
        this.hiD = hiD;

    }

    @Override
    public void setVD(int vd)
    {
        this.vd = vd;

    }

    @Override
    public void setLD(int ld)
    {
        this.ld = ld;

    }

    @Override
    public void setCurrentValue(double value)
    {
        this.value = value;
        invalidate();
    }

    @Override
    public String getChannel()
    {
        return channel;
    }

    @Override
    public void setDisabled(boolean disabled)
    {

    }

    public void initFromName(String nme)
    {
        GaugeDetails gd = GaugeRegister.INSTANCE.getGaugeDetails(nme);

        name = gd.getName();
        title = gd.getTitle();
        channel = gd.getChannel();
        units = gd.getUnits();
        min = gd.getMin();
        max = gd.getMax();
        lowD = gd.getLoD();

        lowW = gd.getLoW();
        hiW = gd.getHiW();

        hiD = gd.getHiD();
        vd = gd.getVd();
        ld = gd.getLd();
        value = (max - min) / 2.0;
    }

    public String getName()
    {
        return name;
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        IndicatorManager.INSTANCE.registerIndicator(this);

    }
}
