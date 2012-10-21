package uk.org.smithfamily.mslogger.widgets.Renderers;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Indicator.Orientation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 */
public class BarGraph extends Renderer
{    
    
    public BarGraph(Indicator parent,Context c)
    {
        super(parent,c);
    }
    private Paint           barPaint;
    private Paint           titlePaint;
    private Paint           valuePaint;
    
    private Orientation     orientation    = Orientation.HORIZONTAL;
 
    @Override
    protected void init(Context c)
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
        if (parent.isInEditMode())
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
    public void paint(Canvas canvas)
    {
        int height = parent.getMeasuredHeight();

        int width = parent.getMeasuredWidth();

        float scale = (float) parent.getWidth();
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
        
        if (!parent.isDisabled())
        {
            drawValue(canvas);
        }

        drawTitle(canvas);
        canvas.restore();     
    }
    
    public void drawValue(Canvas canvas)
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

        canvas.drawText(text, 0.9f, 0.95f, valuePaint);
    }
    
    public void drawTitle(Canvas canvas)
    {
        titlePaint.setColor(getFgColour());
        
        String text = parent.getTitle();
        if (!parent.getUnits().equals(""))
        {
            text += " (" + parent.getUnits() + ")";
        }
        
        canvas.drawText(text, 0.05f, 0.95f, titlePaint);
    }
    
    public void drawBars(Canvas canvas)
    {
        double percent = ((parent.getValue() - parent.getMin()) / (parent.getMax() - parent.getMin())) * 100;
        
        barPaint.setColor(Color.WHITE);
        barPaint.setColor(getBarColour((((parent.getMax() - parent.getMin()) / 100) * percent) + parent.getMin()));
                
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
    private int getBarColour(double value)
    {
        int c = Color.GRAY;
        if (parent.isDisabled())
        {
            return c;
        }
        if (value > parent.getLowW() && value < parent.getHiW())
        {
            return Color.WHITE;
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
       
    
    
    @Override
    public String getType()
    {
        return parent.getContext().getString(R.string.bargraph);
    }

}
