package uk.org.smithfamily.mslogger.widgets.renderers;

import uk.org.smithfamily.mslogger.dashboards.DashboardView;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Indicator.DisplayType;
import android.content.Context;
import android.graphics.*;
import android.graphics.Region.Op;

public class Histogram extends Painter
{

    private Paint backgroundPaint;
    private Paint linePaint;
    private Paint valuePaint;
    private RectF borderRect;
    private static final int NB_VALUES = 100;
    private final float[] values = new float[NB_VALUES];

    private int indexValue = 0;
    Path linePath;

    public Histogram(final DashboardView parent, final Indicator model, final Context c)
    {
        super(parent, model, c);

    }

    @Override
    protected void init(final Context c)
    {
        initDrawingTools(c);
    }

    /**
     * 
     * @param context
     */
    private void initDrawingTools(final Context context)
    {
        int anti_alias_flag = Paint.ANTI_ALIAS_FLAG;
        if (parent.isInEditMode())
        {
            anti_alias_flag = 0;
        }

        linePath = new Path();

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setTextSize(0.06f);
        backgroundPaint.setTextAlign(Paint.Align.LEFT);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setFlags(anti_alias_flag);
        backgroundPaint.setAntiAlias(true);

        valuePaint = new Paint();
        valuePaint.setColor(Color.DKGRAY);
        valuePaint.setTextSize(0.06f);
        valuePaint.setTextAlign(Paint.Align.RIGHT);
        valuePaint.setFlags(anti_alias_flag);
        valuePaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setFlags(anti_alias_flag);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        borderRect = new RectF();
        borderRect.set(0.05f, 0.05f, 0.94f, 0.83f);
    }

    /**
     * Add a value to display on the histogram
     * 
     * @param value
     */
    public void addValue(final double value)
    {
        values[indexValue] = (float) value;
        indexValue++;
        indexValue = indexValue % NB_VALUES;
    }

    /**
     * @param canvas
     */
    @Override
    public void renderFrame(final Canvas canvas)
    {
        final int height = (int) (bottom - top);
        final int width = (int) (right - left);

        if ((width == 0) || (height == 0))
        {// We're not ready to do this yet
            return;
        }

        drawBackground(canvas);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.translate(left, top);
        canvas.scale(width, height);

        drawBackground(canvas);

        addValue(model.getValue());
        drawTitle(canvas);

        drawLines(canvas, width, height);
        // this.drawValue(canvas);

        canvas.restore();
    }

    private void drawLines(final Canvas canvas, final float width, final float height)
    {
        linePath.reset();
        // linePath.setFillType(Path.FillType.WINDING);
        final double min = model.getMin();
        final double max = model.getMax();
        final double range = model.getMax() - min;
        canvas.clipRect(borderRect, Op.INTERSECT);
        float x;
        float y;
        float value;
        int idx;
        for (int i = 1; i <= NB_VALUES; i++)
        {
            idx = (indexValue + i) % NB_VALUES;
            value = values[idx];
            x = (float) i / (float) NB_VALUES;

            y = (float) (1.0f - ((value - min) / range));

            linePath.lineTo(x, y);
        }
        canvas.drawPath(linePath, linePaint);
    }

    public void drawBackground(final Canvas canvas)
    {
        canvas.drawRect(borderRect, backgroundPaint);
    }

    public void drawValue(final Canvas canvas)
    {
        valuePaint.setColor(getFgColour());

        final double min = model.getMin();
        final double max = model.getMax();

        final double value = model.getValue();
        final int valueDigits = model.getVd();
        final float displayValue = (float) (Math.floor((value / Math.pow(10, -valueDigits)) + 0.5) * Math.pow(10, -valueDigits));

        String text;

        if (valueDigits <= 0)
        {
            text = Integer.toString((int) displayValue);
        }
        else
        {
            text = Float.toString(displayValue);
        }

        canvas.drawText(text, 0.94f, 0.90f, valuePaint);

        // We need at least two pair of coords to draw a line
        if (indexValue > 1)
        {
            final float x = 0.040f;
            final float y = 0.06f;
            final float height = 0.76f;
            final float pixelsBetweenValue = 0.009f;

            for (int i = 1; i < indexValue; i++)
            {
                final double oldValue = values[i - 1];
                final double currentValue = values[i];
                double currentPercent = (1 - ((currentValue - min) / (max - min)));
                if (currentPercent < 0)
                {
                    currentPercent = 0;
                }
                if (currentPercent > 100)
                {
                    currentPercent = 100;
                }

                final double currentY = y + (height * currentPercent);

                double oldPercent = (1 - ((oldValue - min) / (max - min)));
                if (oldPercent < 0)
                {
                    oldPercent = 0;
                }
                if (oldPercent > 100)
                {
                    oldPercent = 100;
                }

                final double oldX = x + (pixelsBetweenValue * i);
                final double oldY = y + (height * oldPercent);

                // Draw one value
                canvas.drawLine((float) oldX, (float) oldY, (float) oldX + pixelsBetweenValue, (float) currentY, linePaint);
            }
        }
    }

    public void drawTitle(final Canvas canvas)
    {
        backgroundPaint.setColor(getFgColour());

        String text = model.getTitle();
        if (!model.getUnits().equals(""))
        {
            text += " (" + model.getUnits() + ")";
        }

        canvas.drawText(text, 0.05f, 0.90f, backgroundPaint);
    }

    @Override
    public DisplayType getType()
    {
        return DisplayType.HISTOGRAM;
    }
}