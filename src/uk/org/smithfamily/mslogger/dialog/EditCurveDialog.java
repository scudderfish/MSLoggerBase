package uk.org.smithfamily.mslogger.dialog;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.chart.ChartFactory;
import uk.org.smithfamily.mslogger.chart.GraphicalView;
import uk.org.smithfamily.mslogger.chart.chart.PointStyle;
import uk.org.smithfamily.mslogger.chart.model.TimeSeries;
import uk.org.smithfamily.mslogger.chart.model.XYMultipleSeriesDataset;
import uk.org.smithfamily.mslogger.chart.renderer.XYMultipleSeriesRenderer;
import uk.org.smithfamily.mslogger.chart.renderer.XYSeriesRenderer;
import uk.org.smithfamily.mslogger.ecuDef.CurveEditor;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class EditCurveDialog extends Dialog implements android.view.View.OnClickListener
{
    private CurveEditor curve;
    private GraphicalView mChartView;
    
    public EditCurveDialog(Context context, CurveEditor curve)
    {
        super(context);
        
        this.curve = curve;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.editcurve);
        
        setTitle("Edit " + curve.getLabel());
        
        drawCurve();
        createTable();
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(this);
        
        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    
    private void createTable()
    {
        TableLayout table = (TableLayout) findViewById(R.id.table);
        
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        
        final int tableNbX = 2;
        final int tableNbY = 10;
        
        // Column headers
        TableRow tableRow = new TableRow(getContext());
        tableRow.setLayoutParams(lp);
        
        for (int x = 1; x <= tableNbX; x++)
        {
            TextView columnHeader = new TextView(getContext());

            columnHeader.setText("bleh");
            columnHeader.setGravity(Gravity.CENTER);       
                    
            columnHeader.setLayoutParams(lp);
            
            tableRow.addView(columnHeader);
        }       
        
        table.addView(tableRow,lp);
        
        for (int x = 1; x <= tableNbX; x++)
        {
            tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(lp);
            
            for (int y = 1; y <= tableNbY; y++)
            {               
                EditText cell = new EditText(getContext());
                
                cell.setText("10");
                cell.setId(getCellId(x,y));
                cell.setLayoutParams(lp);
                cell.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                cell.setSingleLine(true);
                
                // Add EditText to row
                tableRow.addView(cell);
            }
            
            table.addView(tableRow,lp);
        }
        
        // Change background colors
        for (int x = 1; x <= tableNbX; x++)
        {
            for (int y = 1; y <= tableNbY; y++)
            {
                EditText currentCell = (EditText) findViewById(getCellId(x,y));
                currentCell.setBackgroundColor(Color.rgb(255,255,255));
            }
        }
    }
    
    /**
     * Generate on the fly ID based on the cell position
     * 
     * @param x
     * @param y
     * @return
     */
    private int getCellId(int x, int y)
    {
        return x + (y * 1000);
    }
    
    /**
     * 
     */
    private void drawCurve()
    {
        String[] titles = {"Curve"};
        int colors[] = {Color.rgb(255, 0, 0)};
        
        int minXaxis = -50;
        int maxXaxis = 260;
        
        int minYaxis = 90;
        int maxYaxis = 190;
        
        List<double[]> x = new ArrayList<double[]>();
        List<double[]> values = new ArrayList<double[]>();       
        
        x.add(new double[] { -40, -10, 30, 70, 105, 140, 180, 220, 250 });        
        values.add(new double[] { 171, 165, 156, 145, 135, 127, 118, 110, 100 });
        
        XYMultipleSeriesRenderer renderer = buildRenderer(titles.length, colors);
        setChartSettings(renderer, "", "", "", minXaxis, maxXaxis, minYaxis, maxYaxis, Color.GRAY, Color.LTGRAY);
        
        renderer.setXTitle(curve.getxLabel());
        renderer.setYTitle(curve.getyLabel(), 0);
        
        renderer.setPanLimits(new double[] { minXaxis, maxXaxis, minYaxis, maxYaxis });
        renderer.setShowLabels(true);
        renderer.setClickEnabled(false);
        renderer.setShowGrid(true);
        renderer.setZoomEnabled(true);
        renderer.setShowLegend(false);
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.curve);

        if (mChartView == null)
        {
            mChartView = ChartFactory.getLineChartView(getContext(), buildDateDataset(titles, x, values), renderer);     
            
            layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        }
        else
        {
            mChartView.repaint();
        }
    }

    /**
     * Builds an XY multiple time dataset using the provided values.
     * 
     * @param titles
     *            the series titles
     * @param xValues
     *            the values for the X axis
     * @param yValues
     *            the values for the Y axis
     * @return the XY multiple time dataset
     */
    protected XYMultipleSeriesDataset buildDateDataset(String[] titles, List<double[]> xValues, List<double[]> yValues)
    {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        
        int length = titles.length;
        for (int i = 0; i < length; i++)
        {
            TimeSeries series = new TimeSeries(titles[i]);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++)
            {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
        
        return dataset;
    }
    
    /**
     * Builds an XY multiple series renderer.
     * 
     * @param nbLines Number of lines
     * @param colors Array of colors for each point
     * @return The XY multiple series renderers
     */
    protected XYMultipleSeriesRenderer buildRenderer(int nbLines, int[] colors)
    {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, nbLines, colors);
        return renderer;
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int nbLines, int[] colors)
    {
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setPointSize(5f);
        renderer.setMargins(new int[] { 20, 30, 15, 20 });

        for (int i = 0; i < nbLines; i++)
        {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(PointStyle.POINT);
            renderer.addSeriesRenderer(r);
        }
    }

    /**
     * Sets a few of the series renderer settings.
     * 
     * @param renderer
     *            the renderer to set the properties to
     * @param title
     *            the chart title
     * @param xTitle
     *            the title for the X axis
     * @param yTitle
     *            the title for the Y axis
     * @param xMin
     *            the minimum value on the X axis
     * @param xMax
     *            the maximum value on the X axis
     * @param yMin
     *            the minimum value on the Y axis
     * @param yMax
     *            the maximum value on the Y axis
     * @param axesColor
     *            the axes color
     * @param labelsColor
     *            the labels color
     */
    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle, String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor)
    {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }
    
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.burn)
        {
            
        }
        else if (which == R.id.cancel)
        {
            cancel();
        }
    }
}
