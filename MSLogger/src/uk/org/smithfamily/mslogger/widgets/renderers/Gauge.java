package uk.org.smithfamily.mslogger.widgets.renderers;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Size;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.Log;

/**
 *
 */
public class Gauge extends Renderer
{
    public Gauge(Indicator parent, Context c)
    {
        super(parent, c);
    }

    private double pi = Math.PI;
    private double epsilon = 1E-5;
    private Paint titlePaint;
    private Paint valuePaint;
    private Paint pointerPaint;
    private Paint scalePaint;
    private RectF rimRect;
    private Paint rimPaint;
    private Paint rimCirclePaint;
    private RectF faceRect;
    private Paint facePaint;
    private Paint backgroundPaint;
    private Bitmap background;
    private double currentValue;

    private int lastBGColour;
    private static final float rimSize = 0.02f;
    private static final double FULL_SWEEP_TIME = 1000;

    private long lastPointerMoveTime = System.currentTimeMillis();
    private long logTime = System.currentTimeMillis();

    @Override
    protected void init(Context c)
    {
        initDrawingTools(c);
    }

    private void regenerateBackground()
    {
        int height = parent.getHeight();
        int width = parent.getWidth();

        if (width == 0 || height == 0)
        {// We're not ready to do this yet
            return;
        }

        // free the old bitmap
        if (background != null)
        {
            background.recycle();
        }

        background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas backgroundCanvas = new Canvas(background);

        float scale = (float) Math.min(height, width);
        backgroundCanvas.save(Canvas.MATRIX_SAVE_FLAG);
        backgroundCanvas.scale(scale, scale);

        drawFace(backgroundCanvas);
        drawScale(backgroundCanvas);
        drawTitle(backgroundCanvas);
    }

    private void drawBackground(Canvas canvas)
    {
        if (background == null || getBgColour() != lastBGColour)
        {
            regenerateBackground();
            lastBGColour = getBgColour();
        }
        canvas.drawBitmap(background, 0, 0, backgroundPaint);
    }

    /**
     * 
     * @param canvas
     */
    @Override
    public void paint(Canvas canvas)
    {

        drawBackground(canvas);

        int height = parent.getHeight();
        int width = parent.getWidth();

        float scale = (float) Math.min(height, width);
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(scale, scale);

//        drawFace(canvas);

//        drawScale(canvas);
        drawPointer(canvas);

        if (!parent.isDisabled())
        {
            drawValue(canvas);
        }
        else
        {
            parent.setValue(parent.getMin());
        }

        drawTitle(canvas);
        canvas.restore();

        if (pointerStale())
        {
            moveHand();
        }
    }

    private void moveHand()
    {
        double actualValue = parent.getValue();

        if (!pointerStale())
        {
            currentValue = actualValue;
            lastPointerMoveTime = System.currentTimeMillis();
            return;
        }

        double range = parent.getMax() - parent.getMin();
        double incPerMilli = range / FULL_SWEEP_TIME;

        long currentTime = System.currentTimeMillis();
        long delay = currentTime - lastPointerMoveTime;

        if(currentTime - logTime > 1000)
        {
            Log.e("GaugeFPS",this.toString()+":"+(1000/delay)+":"+delay);
            logTime = currentTime;
        }
       
        double direction = Math.signum(actualValue - currentValue);

        double delta = Math.abs(actualValue - currentValue);
        double increment = Math.min(delta, delay * incPerMilli);

        currentValue += (increment * direction);
        lastPointerMoveTime = System.currentTimeMillis();
        parent.invalidate();

    }

    private boolean pointerStale()
    {
        double actualValue = parent.getValue();
        return (Math.abs(actualValue - currentValue) > epsilon);
    }

    /**
     * 
     * @param context
     */
    private void initDrawingTools(Context context)
    {
        int anti_alias_flag = Paint.ANTI_ALIAS_FLAG;
        if (parent.isInEditMode())
        {
            anti_alias_flag = 0;
        }
        rimRect = new RectF(0.0f, 0.0f, 1.0f, 1.0f);

        faceRect = new RectF();
        if (!parent.isInEditMode())
        {
            faceRect.set(rimRect.left + rimSize, rimRect.top + rimSize, rimRect.right - rimSize, rimRect.bottom - rimSize);
        }
        else
            faceRect = rimRect;

        // the linear gradient is a bit skewed for realism
        rimPaint = new Paint();
        if (!parent.isInEditMode())
        {
            rimPaint.setFlags(anti_alias_flag);
            rimPaint.setShader(new LinearGradient(0.40f, 0.0f, 0.60f, 1.0f, Color.rgb(0xf0, 0xf5, 0xf0), Color.rgb(0x30, 0x31, 0x30), Shader.TileMode.CLAMP));
        }
        rimCirclePaint = new Paint();
        if (!parent.isInEditMode())
        {
            rimCirclePaint.setAntiAlias(true);
            rimCirclePaint.setStyle(Paint.Style.STROKE);
            rimCirclePaint.setColor(Color.argb(0x4f, 0x33, 0x36, 0x33));
            rimCirclePaint.setStrokeWidth(0.005f);
        }
        facePaint = new Paint();
        facePaint.setFilterBitmap(true);
        facePaint.setStyle(Paint.Style.FILL);
        facePaint.setColor(Color.BLACK);
        facePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        titlePaint = new Paint();
        titlePaint.setColor(Color.WHITE);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setFlags(anti_alias_flag);
        titlePaint.setAntiAlias(true);

        valuePaint = new Paint();
        valuePaint.setColor(Color.WHITE);
        valuePaint.setTextSize(0.1f);
        valuePaint.setTextAlign(Paint.Align.CENTER);
        valuePaint.setFlags(anti_alias_flag);
        valuePaint.setAntiAlias(true);

        pointerPaint = new Paint();
        pointerPaint.setColor(Color.WHITE);
        pointerPaint.setAntiAlias(true);
        pointerPaint.setStrokeWidth((0.5f / 48.0f));
        pointerPaint.setStyle(Style.FILL_AND_STROKE);
        pointerPaint.setFlags(anti_alias_flag);
        pointerPaint.setAntiAlias(true);

        scalePaint = new Paint();
        scalePaint.setColor(Color.WHITE);
        scalePaint.setAntiAlias(true);
        scalePaint.setTextSize(0.05f);
        scalePaint.setTextAlign(Paint.Align.CENTER);
        scalePaint.setFlags(anti_alias_flag);
        scalePaint.setAntiAlias(true);

        backgroundPaint = new Paint();
        backgroundPaint.setFilterBitmap(true);
    }

    /**
     * 
     * @param canvas
     */
    private void drawTitle(Canvas canvas)
    {
        titlePaint.setTextSize(0.07f);
        titlePaint.setColor(getFgColour());
        canvas.drawText(parent.getTitle(), 0.5f, 0.25f, titlePaint);

        titlePaint.setTextSize(0.05f);
        canvas.drawText(parent.getUnits(), 0.5f, 0.32f, titlePaint);
    }

    /**
     * 
     * @param canvas
     */
    private void drawValue(Canvas canvas)
    {
        valuePaint.setColor(getFgColour());

        float displayValue = (float) (Math.floor(parent.getValue() / Math.pow(10, -parent.getVd()) + 0.5) * Math.pow(10, -parent.getVd()));

        String text;

        if (parent.getVd() <= 0)
        {
            text = Integer.toString((int) displayValue);
        }
        else
        {
            text = Float.toString(displayValue);
        }

        canvas.drawText(text, 0.5f, 0.65f, valuePaint);
    }

    /**
     * 
     * @param canvas
     */
    private void drawPointer(Canvas canvas)
    {
        float back_radius = 0.042f;

        double angularRange = 270.0 / (parent.getMax() - parent.getMin());
        double pointerValue = currentValue;
        if (pointerValue < parent.getMin())
        {
            pointerValue = parent.getMin();
        }
        if (pointerValue > parent.getMax())
        {
            pointerValue = parent.getMax();
        }

        pointerPaint.setColor(getFgColour());

        canvas.drawCircle(0.5f, 0.5f, back_radius / 2.0f, pointerPaint);

        Path pointerPath = new Path(); // X Y
        pointerPath.setFillType(FillType.EVEN_ODD);

        pointerPath.moveTo(0.5f, 0.1f); // 0.500, 0.100
        pointerPath.lineTo(0.5f + 0.010f, 0.5f + 0.05f); // 0.501, 0.505
        pointerPath.lineTo(0.5f - 0.010f, 0.5f + 0.05f); // 0.499, 0.505
        pointerPath.lineTo(0.5f, 0.1f); // 0.500, 0.100
        canvas.save(Canvas.MATRIX_SAVE_FLAG);

        double angle = ((pointerValue - parent.getMin()) * angularRange + parent.getOffsetAngle()) - 180;
        canvas.rotate((float) angle, 0.5f, 0.5f);
        canvas.drawPath(pointerPath, pointerPaint);
        canvas.restore();
    }

    /**
     * 
     * @param canvas
     */
    private void drawScale(Canvas canvas)
    {
        float radius = 0.42f;
        scalePaint.setColor(getFgColour());
        double range = (parent.getMax() - parent.getMin());
        double tenpower = Math.floor(Math.log10(range));
        double scalefactor = Math.pow(10, tenpower);

        double gaugeMax = parent.getMax();
        double gaugeMin = parent.getMin();

        double gaugeRange = gaugeMax - gaugeMin;

        double step = scalefactor;

        while ((gaugeRange / step) < 10)
        {
            step = step / 2;
        }

        for (double val = gaugeMin; val <= gaugeMax; val += step)
        {
            float displayValue = (float) (Math.floor(val / Math.pow(10, -parent.getLd()) + 0.5) * Math.pow(10, -parent.getLd()));

            String text;

            if (parent.getLd() <= 0)
            {
                text = Integer.toString((int) displayValue);
            }
            else
            {
                text = Float.toString(displayValue);
            }

            double anglerange = 270.0 / gaugeRange;
            double angle = (val - gaugeMin) * anglerange + parent.getOffsetAngle();
            double rads = angle * pi / 180.0;
            float x = (float) (0.5f - radius * Math.cos(rads - pi / 2.0));
            float y = (float) (0.5f - radius * Math.sin(rads - pi / 2.0));
            canvas.drawText(text, x, y, scalePaint);
        }
    }

    @Override
    protected int getFgColour()
    {
        if (parent.isDisabled())
        {
            return Color.DKGRAY;
        }
        if (parent.getValue() > parent.getLowW() && parent.getValue() < parent.getHiW())
        {
            return Color.WHITE;
        }
        else
        {
            return Color.BLACK;
        }
    }

    /**
     * 
     * @return
     */
    private int getBgColour()
    {
        int c = Color.GRAY;
        if (parent.isDisabled())
        {
            c = Color.GRAY;
        }
        double value = parent.getValue();
        if (value > parent.getLowW() && value < parent.getHiW())
        {
            c = Color.BLACK;
        }
        else if (value <= parent.getLowW() || value >= parent.getHiW())
        {
            c = Color.YELLOW;
        }
        if (value <= parent.getLowD() || value >= parent.getHiD())
        {
            c = Color.RED;
        }

        return c;
    }

    /**
     * 
     * @param canvas
     */
    private void drawFace(Canvas canvas)
    {
        if (parent.isInEditMode())
        {
            facePaint.setColor(Color.RED);

            facePaint.setStyle(Style.FILL);
            canvas.drawOval(rimRect, facePaint);
            return;
        }

        canvas.drawOval(rimRect, rimPaint);
        // now the outer rim circle
        canvas.drawOval(rimRect, rimCirclePaint);
        facePaint.setColor(getBgColour());
        canvas.drawOval(faceRect, facePaint);
    }

    /**
     * 
     */

    @Override
    public String getType()
    {
        return parent.getContext().getString(R.string.gauge);
    }

    @Override
    public Size getSize(int width, int height)
    {
        int diameter = Math.min(width, height);
        return new Size(diameter, diameter);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        regenerateBackground();
    }

}
