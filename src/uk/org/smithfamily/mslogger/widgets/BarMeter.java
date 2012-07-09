package uk.org.smithfamily.mslogger.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 *
 */
public class BarMeter extends Indicator
{
 
    final float             scale       = getResources().getDisplayMetrics().density;
    
    private int             diameter;
    
    private Paint           barPaint;
    private Paint           titlePaint;
    private Paint           valuePaint;
    
    public enum Orientation
    {
        HORIZONTAL, VERTICAL
    }
    
    private Orientation orientation     = Orientation.HORIZONTAL;
 
 
    /**
     * 
     * @param context
     */
    public BarMeter(Context context)
    {
        super(context);
        init(context);
    }

    /**
     * 
     * @param c
     * @param s
     */
    public BarMeter(Context c, AttributeSet s)
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
    public BarMeter(Context context, AttributeSet attr, int defaultStyles)
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
         * After obtaining the height, width of your view and performing some changes you need to set the processed value as your view dimension by using the method setMeasuredDimension
         */

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
        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setTextSize(0.06f);
        titlePaint.setFlags(anti_alias_flag);
        titlePaint.setAntiAlias(true);

        valuePaint = new Paint();
        valuePaint.setColor(Color.WHITE);
        valuePaint.setTextSize(0.06f);
        valuePaint.setTextAlign(Paint.Align.RIGHT);
        valuePaint.setFlags(anti_alias_flag);
        valuePaint.setAntiAlias(true);
        
        barPaint = new Paint();
        barPaint.setColor(Color.BLACK);
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
        
        drawBars(canvas);
        
        canvas.translate(dx, dy);       
        
        if (!isDisabled())
        {
            drawValue(canvas);
        }

        drawTitle(canvas);
        canvas.restore();     
    }
    
    public void drawValue(Canvas canvas)
    {
        valuePaint.setColor(getFgColour());

        float displayValue = (float) (Math.floor(getValue() / Math.pow(10, -getVd()) + 0.5) * Math.pow(10, -getVd()));

        String text;

        if (getVd() <= 0)
        {
            text = Integer.toString((int) displayValue);
        }
        else
        {
            text = Float.toString(displayValue);
        }

        canvas.drawText(text, 0.9f, 0.95f, valuePaint);
    }
    
    public void drawTitle(Canvas canvas)
    {
        titlePaint.setColor(getFgColour());
        
        String text = getTitle();
        if (!getUnits().equals(""))
        {
            text += " (" + getUnits() + ")";
        }
        
        canvas.drawText(text, 0.05f, 0.95f, titlePaint);
    }
    
    public void drawBars(Canvas canvas)
    {
        double percent = ((getValue() - getMin()) / (getMax() - getMin())) * 100;
        
        barPaint.setColor(Color.WHITE);
        barPaint.setColor(getBarColour((((getMax() - getMin()) / 100) * percent) + getMin()));
                
        final float barWidth = 0.025f;
        final float barSpacing = 0.02f;        
        
        if (orientation == Orientation.VERTICAL)
        {       
            final int nbBars = 21;
            
            for (int i = 1; i <= nbBars; i++)
            {
                if ((i * 100 / nbBars) <= percent) 
                {            
                    canvas.drawRect((i * (barWidth + barSpacing)) + 0.03f,0.05f,(i * (barWidth + barSpacing)) + barWidth + 0.03f,0.85f,barPaint);
                }
            }    
        }
        else
        {
            final int nbBars = 19;
            
            for (int i = 1; i <= nbBars; i++)
            {
                if ((i * 100 / nbBars) <= percent) 
                {            
                    canvas.drawRect(0.05f,0.9f - (i * (barWidth + barSpacing)),0.9f,0.9f - (i * (barWidth + barSpacing)) + barWidth,barPaint);
                }
            }
        }
    }
    
    /**
     * 
     * @return
     */
    private int getFgColour()
    {
        int c = Color.WHITE;
        
        if (isDisabled())
        {
            return Color.DKGRAY;
        }
        
        if (getValue() > getLowW() && getValue() < getHiW())
        {
            return Color.WHITE;
        }
        else if (getValue() <= getLowW() || getValue() >= getHiW())
        {
            c = Color.YELLOW;
        }
        if (getValue() <= getLowD() || getValue() >= getHiD())
        {
            c = Color.RED;
        }
        
        return c;
    }
    
    /**
     * 
     * @return
     */
    private int getBarColour(double value)
    {
        int c = Color.GRAY;
        if (isDisabled())
        {
            return c;
        }
        if (value > getLowW() && value < getHiW())
        {
            return Color.WHITE;
        }
        else if (value <= getLowW() || value >= getHiW())
        {
            c = Color.YELLOW;
        }
        if (value <= getLowD() || value >= getHiD())
        {
            c = Color.RED;
        }
        
        return c;
    }
       
    /**
     * @param orientation
     */
    public void setOrientation(Orientation orientation)
    {
       this.orientation = orientation; 
    }
    
    public Orientation getOrientation()
    {
        return orientation;
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
}
