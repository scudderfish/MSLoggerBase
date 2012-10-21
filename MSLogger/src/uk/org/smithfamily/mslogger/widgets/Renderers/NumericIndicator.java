package uk.org.smithfamily.mslogger.widgets.Renderers;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * 
 */
public class NumericIndicator extends Renderer
{
    public NumericIndicator(Indicator parent, Context c)
    {
        super(parent, c);
    }

    final float scale = getResources().getDisplayMetrics().density;

    private Paint backgroundPaint;
    private Paint titlePaint;
    private Paint valuePaint;

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
        titlePaint.setTextSize(0.08f);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setFlags(anti_alias_flag);
        titlePaint.setAntiAlias(true);

        valuePaint = new Paint();
        valuePaint.setColor(Color.WHITE);
        valuePaint.setTextSize(0.2f);
        valuePaint.setTextAlign(Paint.Align.CENTER);
        valuePaint.setFlags(anti_alias_flag);
        valuePaint.setAntiAlias(true);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);
    }

    /**
     * 
     * @param canvas
     */
    private void drawBackground(Canvas canvas)
    {
        backgroundPaint.setColor(getBgColour());

        canvas.drawRect(0.05f, 0.30f, 0.9f, 0.72f, backgroundPaint);
    }

    /**
     * 
     * @param canvas
     */
    private void drawTitle(Canvas canvas)
    {
        titlePaint.setColor(getFgColour());

        String text = parent.getTitle();
        if (!parent.getUnits().equals(""))
        {
            text += " (" + parent.getUnits() + ")";
        }

        canvas.drawText(text, 0.48f, 0.65f, titlePaint);
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

        canvas.drawText(text, 0.5f, 0.5f, valuePaint);
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
        return super.getFgColour();
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
        canvas.translate(dx, dy);

        drawBackground(canvas);

        if (!parent.isDisabled())
        {
            drawValue(canvas);
        }

        drawTitle(canvas);
        canvas.restore();
    }

    @Override
    public String getType()
    {
        return parent.getContext().getString(R.string.numeric_indicator);
    }
}
