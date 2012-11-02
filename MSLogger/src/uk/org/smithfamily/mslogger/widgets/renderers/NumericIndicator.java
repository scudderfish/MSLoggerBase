package uk.org.smithfamily.mslogger.widgets.renderers;

import uk.org.smithfamily.mslogger.dashboards.DashboardView;
import uk.org.smithfamily.mslogger.widgets.Indicator;
import uk.org.smithfamily.mslogger.widgets.Indicator.DisplayType;
import android.content.Context;
import android.graphics.*;

/**
 * 
 */
public class NumericIndicator extends Painter
{

    public NumericIndicator(final DashboardView parent, final Indicator model, final Context c)
    {
        super(parent, model, c);
    }

    final float scale = getResources().getDisplayMetrics().density;

    private Paint backgroundPaint;
    private Paint titlePaint;
    private Paint valuePaint;

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
    private void drawBackground(final Canvas canvas)
    {
        backgroundPaint.setColor(getBgColour());

        canvas.drawRect(0.05f, 0.30f, 0.9f, 0.72f, backgroundPaint);
    }

    /**
     * 
     * @param canvas
     */
    private void drawTitle(final Canvas canvas)
    {
        titlePaint.setColor(getFgColour());

        String text = model.getTitle();
        if (!model.getUnits().equals(""))
        {
            text += " (" + model.getUnits() + ")";
        }

        canvas.drawText(text, 0.48f, 0.65f, titlePaint);
    }

    /**
     * 
     * @param canvas
     */
    private void drawValue(final Canvas canvas)
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

        canvas.drawText(text, 0.5f, 0.5f, valuePaint);
    }

    @Override
    protected int getFgColour()
    {
        if (model.isDisabled())
        {
            return Color.DKGRAY;
        }
        if ((model.getValue() > model.getLowW()) && (model.getValue() < model.getHiW()))
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
    public void renderFrame(final Canvas canvas)
    {
        final int height = parent.getMeasuredHeight();

        final int width = parent.getMeasuredWidth();

        final float scale = parent.getWidth();
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

        if (!model.isDisabled())
        {
            drawValue(canvas);
        }

        drawTitle(canvas);
        canvas.restore();
    }

    @Override
    public DisplayType getType()
    {
        return DisplayType.NUMERIC;
    }
}
