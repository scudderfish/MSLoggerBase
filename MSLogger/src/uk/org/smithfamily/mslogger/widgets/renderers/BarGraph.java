package uk.org.smithfamily.mslogger.widgets.renderers;

import uk.org.smithfamily.mslogger.dashboards.DashboardView;
import uk.org.smithfamily.mslogger.widgets.*;
import uk.org.smithfamily.mslogger.widgets.Indicator.DisplayType;
import uk.org.smithfamily.mslogger.widgets.Indicator.Orientation;
import android.content.Context;
import android.graphics.*;

/**
 *
 */
public class BarGraph extends Renderer
{

    public BarGraph(final DashboardView parent, final Indicator model, final Context c)
    {
        super(parent, model, c);
    }

    private Paint barPaint;
    private Paint titlePaint;
    private Paint valuePaint;

    private final Orientation orientation = Orientation.HORIZONTAL;

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
    public void renderFrame(final Canvas canvas)
    {
        final int height = parent.getHeight();

        final int width = parent.getWidth();

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(width, height);

        drawBars(canvas);

        if (!model.isDisabled())
        {
            drawValue(canvas);
        }

        drawTitle(canvas);
        canvas.restore();
    }

    public void drawValue(final Canvas canvas)
    {
        valuePaint.setColor(getFgColour());

        final float displayValue = (float) (Math.floor((model.getValue() / Math.pow(10, -model.getVd())) + 0.5) * Math.pow(10, -model.getVd()));

        String text;

        if (model.getVd() <= 0)
        {
            text = Integer.toString((int) displayValue);
        }
        else
        {
            text = Float.toString(displayValue);
        }

        canvas.drawText(text, 0.9f, 0.95f, valuePaint);
    }

    public void drawTitle(final Canvas canvas)
    {
        titlePaint.setColor(getFgColour());

        String text = model.getTitle();
        if (!model.getUnits().equals(""))
        {
            text += " (" + model.getUnits() + ")";
        }

        canvas.drawText(text, 0.05f, 0.95f, titlePaint);
    }

    public void drawBars(final Canvas canvas)
    {
        final double percent = ((model.getValue() - model.getMin()) / (model.getMax() - model.getMin())) * 100;

        barPaint.setColor(Color.WHITE);
        barPaint.setColor(getBarColour((((model.getMax() - model.getMin()) / 100) * percent) + model.getMin()));

        final float barWidth = 0.025f;
        final float barSpacing = 0.02f;

        if (orientation == Orientation.VERTICAL)
        {
            final int nbBars = 21;

            for (int i = 1; i <= nbBars; i++)
            {
                if (((i * 100) / nbBars) <= percent)
                {
                    canvas.drawRect((i * (barWidth + barSpacing)) + 0.03f, 0.05f, (i * (barWidth + barSpacing)) + barWidth + 0.03f, 0.85f, barPaint);
                }
            }
        }
        else
        {
            final int nbBars = 19;

            for (int i = 1; i <= nbBars; i++)
            {
                if (((i * 100) / nbBars) <= percent)
                {
                    canvas.drawRect(0.05f, 0.9f - (i * (barWidth + barSpacing)), 0.9f, (0.9f - (i * (barWidth + barSpacing))) + barWidth, barPaint);
                }
            }
        }
    }

    /**
     * 
     * @return
     */
    private int getBarColour(final double value)
    {
        int c = Color.GRAY;
        if (model.isDisabled())
        {
            return c;
        }
        if ((value > model.getLowW()) && (value < model.getHiW()))
        {
            return Color.WHITE;
        }
        else if ((value <= model.getLowW()) || (value >= model.getHiW()))
        {
            c = Color.YELLOW;
        }
        if ((value <= model.getLowD()) || (value >= model.getHiD()))
        {
            c = Color.RED;
        }

        return c;
    }

    @Override
    public DisplayType getType()
    {
        return DisplayType.BAR;
    }

}
