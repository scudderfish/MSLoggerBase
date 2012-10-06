package uk.org.smithfamily.mslogger.widgets;

import uk.org.smithfamily.mslogger.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * 
 */
public class TableIndicator extends GroupIndicator
{
    final float scale = getResources().getDisplayMetrics().density;

    private Paint backgroundPaint;
    private Paint titlePaint;
    private Paint valuePaint;
    private boolean flipColor;

    /**
     * 
     * @param context
     */
    public TableIndicator(Context context)
    {
        super(context);
        init(context);
    }

    /**
     * 
     * @param c
     * @param s
     */
    public TableIndicator(Context c, AttributeSet s)
    {
        super(c, s);
        init(c);
    }

    /**
     * 
     * @param context
     * @param attr
     * @param defaultStyles
     */
    public TableIndicator(Context context, AttributeSet attr, int defaultStyles)
    {
        super(context, attr, defaultStyles);
        init(context);
    }

    /**
     * 
     * @param c
     */
    private void init(Context c)
    {
        initDrawingTools(c);
    }

    /**
     * 
     * @param context
     */
    private void initDrawingTools(Context context)
    {
        int anti_alias_flag = Paint.ANTI_ALIAS_FLAG;
        if (this.isInEditMode())
        {
            anti_alias_flag = 0;
        }

        titlePaint = new Paint();
        titlePaint.setColor(Color.WHITE);
        titlePaint.setTextSize(0.07f);
        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setFlags(anti_alias_flag);
        titlePaint.setAntiAlias(true);

        valuePaint = new Paint();
        valuePaint.setColor(Color.WHITE);
        valuePaint.setTextSize(0.07f);
        valuePaint.setTextAlign(Paint.Align.RIGHT);
        valuePaint.setFlags(anti_alias_flag);
        valuePaint.setAntiAlias(true);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);
    }

    /**
     * @param widthSpec
     * @param heightSpec
     */
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

        /*
         * After obtaining the height, width of your view and performing some changes you need to set the processed value as your view dimension by
         * using the method setMeasuredDimension
         */

        int diameter = Math.min(measuredHeight, measuredWidth);
        setMeasuredDimension(diameter, diameter);

        /*
         * If you consider drawing circle as an example, you need to select the minimum of height and width and set that value as your screen
         * dimensions
         * 
         * int d=Math.min(measuredWidth, measuredHeight);
         * 
         * setMeasuredDimension(d,d);
         */

    }

    /**
     * 
     * @param canvas
     */
    private void drawBackground(Canvas canvas)
    {
        // backgroundPaint.setColor(getBgColour());

        canvas.drawRect(0.05f, 0.30f, 0.9f, 0.72f, backgroundPaint);
    }

    private void drawTitle(Canvas canvas, String gaugeName, float y)
    {
        if (flipColor)
        {
            titlePaint.setColor(Color.WHITE);
        }
        else
        {
            titlePaint.setColor(Color.LTGRAY);
        }
        GaugeDetails gd = GaugeRegister.INSTANCE.getGaugeDetails(gaugeName);

        if(gd != null)
        {
            canvas.drawText(gd.getTitle() + "(" + gd.getUnits() + ")", 0.02f, y, titlePaint);
        }
    }

    /**
     * 
     * @param canvas
     */
    private void drawValue(Canvas canvas, String gaugeName, float y)
    {
        if (flipColor)
        {
            valuePaint.setColor(Color.WHITE);
            flipColor = false;
        }
        else
        {
            valuePaint.setColor(Color.LTGRAY);
            flipColor = true;
        }

        GaugeDetails gd = GaugeRegister.INSTANCE.getGaugeDetails(gaugeName);
        
        String text="unkown";

        if(gd != null)
        {
            if (gd.getVd() <= 0)
            {
                text = Integer.toString((int) getValue(gaugeName));
            }
            else
            {
                text = Double.toString(Math.floor(getValue(gaugeName) / Math.pow(10, -gd.getVd()) + 0.5) * Math.pow(10, -gd.getVd()));
            }
        }canvas.drawText(text, 1f, y, valuePaint);
    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        int height = getMeasuredHeight();

        int width = getMeasuredWidth();

        float scale = (float) getWidth();
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(scale, scale);
        float dx = 0.0f;
        float dy = 0.0f;
        if (width > height)
        {
            dx = (width - height) / 2.0f;
        }
        if (height > width)
        {
            dy = (height - width) / 2.0f;
        }
        canvas.translate(dx, dy);

        drawBackground(canvas);

        int tableRows = numOfGauges();
        float rowHeight = 0.96f / tableRows;
        float y = 0.01f;
        flipColor = true;
        for (String name : getGaugeNames())
        {
            y += rowHeight;
            drawTitle(canvas, name, y);
            drawValue(canvas, name, y);
        }
        canvas.restore();
    }

    /**
	 * 
	 */
    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        IndicatorManager.INSTANCE.registerIndicator(this);
    }

    /**
	 * 
	 */
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();

        IndicatorManager.INSTANCE.deregisterIndicator(this);
    }

    @Override
    public String getType()
    {
        return getContext().getString(R.string.table_indicator);
    }
}
