package uk.org.smithfamily.mslogger.dialog;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.chart.ChartFactory;
import uk.org.smithfamily.mslogger.chart.GraphicalView;
import uk.org.smithfamily.mslogger.chart.chart.PointStyle;
import uk.org.smithfamily.mslogger.chart.model.TimeSeries;
import uk.org.smithfamily.mslogger.chart.model.XYMultipleSeriesDataset;
import uk.org.smithfamily.mslogger.chart.renderer.XYMultipleSeriesRenderer;
import uk.org.smithfamily.mslogger.chart.renderer.XYSeriesRenderer;
import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.CurveEditor;
import uk.org.smithfamily.mslogger.ecuDef.MSUtils;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

/**
 *
 */
public class CurveHelper
{
    private GraphicalView mChartView;
    private CurveEditor curve;
    private Context context;
    
    private LinearLayout containerLayout;
    private LinearLayout curveLayout;
    private TableLayout tableLayout;
    
    private int tableNbX = 0;
    private int tableNbY = 0;
    
    // The curve dataset
    private XYMultipleSeriesDataset dataset;
    
    private boolean isCurveDialog;
    
    /**
     * 
     * @param context
     * @param curve
     */
    public CurveHelper(Context context, CurveEditor curve, boolean isCurveDialog)
    {
        this.curve = curve;
        this.context = context;
        
        this.dataset = new XYMultipleSeriesDataset();
        
        this.isCurveDialog = isCurveDialog;
        
        buildLayout();
        createTable();
        drawCurve();
    }
    
    /**
     * Get the curve editor associated with the curve
     * 
     * @return
     */
    public CurveEditor getCurveEditor()
    {
        return this.curve;
    }
    
    /**
     * Build the layout for the curve
     */
    private void buildLayout()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout containerLayout = (LinearLayout) inflater.inflate(R.layout.curve, null);
        
        this.containerLayout = containerLayout;
        this.curveLayout = (LinearLayout) containerLayout.findViewById(R.id.curve);
        this.tableLayout = (TableLayout) containerLayout.findViewById(R.id.table);
        
        LinearLayout.LayoutParams lpCurve;
        if (isCurveDialog)
        {
            lpCurve = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        else
        {
            Resources r = context.getResources();
            int pxCurve = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics());
            lpCurve = new LinearLayout.LayoutParams(pxCurve, pxCurve);
           
            int pxTableAndGauge = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
            LinearLayout.LayoutParams lpTableAndGauge = new LinearLayout.LayoutParams(pxTableAndGauge, LayoutParams.WRAP_CONTENT);
            
            LinearLayout tableAndGauge = (LinearLayout) containerLayout.findViewById(R.id.tableAndGauge);
            curveLayout.setLayoutParams(lpCurve);
            tableAndGauge.setLayoutParams(lpTableAndGauge);
        }
        
        containerLayout.setWeightSum(1.0f);
        containerLayout.setBaselineAligned(false);
        containerLayout.setLayoutParams(lpCurve);
    }    
    
    /**
     * Return the main layout that contains the curve and the bins
     */
    public LinearLayout getLayout()
    {
        return containerLayout;
    }
    
    /**
     * Create the table of data that will be added beside the graph
     */
    private void createTable()
    {        
        LayoutParams lp = new LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        
        tableNbX = 2;
        tableNbY = curve.getyBins().length;
        
        // Column headers
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(lp);
        
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        final Constant xBinsConstant = ecu.getConstantByName(curve.getxBinsName());
        final Constant yBinsConstant = ecu.getConstantByName(curve.getyBinsName());
        
        for (int x = 1; x <= tableNbX; x++)
        {
            TextView columnHeader = new TextView(context);

            String label = "";
            
            if (x == 1)
            {
                label = curve.getxLabel();
                
                // Add units, if they exists
                if (xBinsConstant != null && !xBinsConstant.getUnits().equals("")) 
                {
                    label += " (" + xBinsConstant.getUnits() + ")";
                }
            }
            else
            {
                label = curve.getyLabel();
                
                // Add units, if they exists
                if (!yBinsConstant.getUnits().equals(""))
                {
                    label += " (" + yBinsConstant.getUnits() + ")";
                }
            }
            
            columnHeader.setText(label);
            columnHeader.setLayoutParams(lp);
            columnHeader.setTypeface(null, Typeface.BOLD);
            
            tableRow.addView(columnHeader);
        }
        
        // Add first row with the X and Y labels
        tableLayout.addView(tableRow,lp);
        
        // Add all the X and Y values in two columns
        for (int y = 1; y <= tableNbY; y++)
        {
            tableRow = new TableRow(context);
            tableRow.setLayoutParams(lp);
            
            for (int x = 1; x <= tableNbX; x++)
            {               
                EditText cell = new EditText(context);
                
                double value;
                
                // x = 1 is the X column
                // x = 2 is the Y column
                if (x == 1)
                {
                    value = MSUtils.INSTANCE.roundDouble((curve.getxBins()[y - 1] + xBinsConstant.getTranslate()) * xBinsConstant.getScale(), xBinsConstant.getDigits());
                }
                else
                {
                    value = MSUtils.INSTANCE.roundDouble((curve.getyBins()[y - 1] + yBinsConstant.getTranslate()) * yBinsConstant.getScale(), yBinsConstant.getDigits());
                }
                
                cell.setText(String.valueOf(value));
                cell.setId(getCellId(x,y));
                cell.setLayoutParams(lp);
                cell.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                cell.setSingleLine(true);
                cell.setPadding(10, 10, 10, 10);
                cell.setTag(new int[] {x - 1, y - 1});
                
                
                // X is not really a constant, used for MS1 for Warmup Wizard, see DialogHelper.getStdDialog
                // Make the cell read only
                if (x == 1 && xBinsConstant == null)
                {
                    cell.setEnabled(false);
                }
                else 
                {
                    cell.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    cell.setOnFocusChangeListener(new OnFocusChangeListener()
                    {
                        public void onFocusChange(View v, boolean hasFocus)
                        {
                            if (!hasFocus)
                            {
                                int[] xy = (int[]) ((EditText) v).getTag();
                                
                                Constant constant;
                                
                                if (xy[0] == 0)
                                {
                                    constant = xBinsConstant;
                                }
                                else 
                                {
                                    constant = yBinsConstant;
                                }
                                
                                DialogHelper.verifyOutOfBoundValue(context, constant, (EditText) v);
                            }
                        }
                    });
                    cell.addTextChangedListener(new CustomTextWatcher(cell));
                }
                
                // Add EditText to row
                tableRow.addView(cell);
            }
            
            tableLayout.addView(tableRow,lp);
        }
    }
    
    /**
     * Private class that implements TextWatcher. This makes which EditText firing the event way less painful
     */
    private class CustomTextWatcher implements TextWatcher
    {
        private EditText mEditText;

        public CustomTextWatcher(EditText e)
        {
            mEditText = e;
        }
        
        public void afterTextChanged(Editable s)
        {
            Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
            
            final Constant xBinsConstant = ecu.getConstantByName(curve.getxBinsName());
            final Constant yBinsConstant = ecu.getConstantByName(curve.getyBinsName());
            
            int[] xy = (int[]) mEditText.getTag();
            
            // X-bins
            if (xy[0] == 0)
            {
                xBinsConstant.setModified(true);
                
                int[] xBins = curve.getxBins();
                
                try
                {
                    xBins[xy[1]] = (int) (Integer.parseInt(mEditText.getText().toString()) / xBinsConstant.getScale() - xBinsConstant.getTranslate());
                    
                    ecu.setVector(xBinsConstant.getName(), xBins);
                }
                catch (NumberFormatException e) {}
            }
            // Y-bins
            else
            {
                yBinsConstant.setModified(true);
                
                int[] yBins = curve.getyBins();
                
                try
                {
                    yBins[xy[1]] = (int) (Integer.parseInt(mEditText.getText().toString()) / yBinsConstant.getScale() - yBinsConstant.getTranslate());
                    
                    ecu.setVector(yBinsConstant.getName(), yBins);
                }
                catch (NumberFormatException e) {}
            }
        }
        
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        public void onTextChanged(CharSequence s, int start, int before, int count) {}
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
     * Draw the actual curve
     */
    private void drawCurve()
    {
        // Curve color
        int colors[] = {Color.rgb(255, 0, 0)};
        
        double[] xAxis = curve.getxAxis();
        double[] yAxis = curve.getyAxis();
        
        double minXaxis = xAxis[0];
        double maxXaxis = xAxis[1];
        
        double minYaxis = yAxis[0];
        double maxYaxis = yAxis[1];
        
        XYMultipleSeriesRenderer renderer = buildRenderer(1, colors);
        setChartSettings(renderer, "", "", "", minXaxis, maxXaxis, minYaxis, maxYaxis, Color.GRAY, Color.LTGRAY);
        
        renderer.setXTitle(curve.getxLabel());
        renderer.setYTitle(curve.getyLabel(), 0);
        
        renderer.setPanLimits(new double[] { minXaxis, maxXaxis, minYaxis, maxYaxis });
        renderer.setShowLabels(true);
        renderer.setClickEnabled(false);
        renderer.setShowGrid(true);
        renderer.setZoomEnabled(true);
        renderer.setShowLegend(false);
        
        // Make sure the curve points are filled
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++)
        {
          ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
        }
        
        if (mChartView == null)
        {
            updateDataset();
            mChartView = ChartFactory.getLineChartView(context, dataset, renderer);     
            
            curveLayout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        }
        else
        {
            mChartView.repaint();
        }
    }
    
    /**
     * Function called to update the chart dataset with values that are in the table
     */
    private void updateDataset()
    {
        boolean isUpdate = false;
        
        // If it's not the first time we're building the dataset, remove the old one first
        if (dataset.getSeries().length > 0)
        {
            dataset.removeSeries(0);
            
            isUpdate = true;
        }
        
        List<double[]> x = new ArrayList<double[]>();
        List<double[]> values = new ArrayList<double[]>();
        
        double[] xBins = new double[curve.getxBins().length];
        double[] yBins = new double[curve.getyBins().length];
        
        // Build the arrays of values from the EditTexts
        for (int i = 0; i < curve.getxBins().length; i++)
        {
            EditText edit = (EditText) tableLayout.findViewById(getCellId(1,i + 1));
            xBins[i] = Double.valueOf(edit.getText().toString());
            
            edit = (EditText) tableLayout.findViewById(getCellId(2,i + 1));
            yBins[i] = Double.valueOf(edit.getText().toString());
        }
        
        x.add(xBins);
        values.add(yBins);
        
        // Create the new dataset
        buildDateDataset(new String[]{""}, x, values);
        
        // If it's not the first time we're doing this, trigger a refresh of the curve
        if (isUpdate)
        {
            mChartView.refreshDrawableState();
            mChartView.repaint();
        }
    }

    /**
     * Builds an XY multiple time dataset using the provided values.
     * 
     * @param titles the series titles
     * @param xValues the values for the X axis
     * @param yValues the values for the Y axis
     * @return the XY multiple time dataset
     */
    private void buildDateDataset(String[] titles, List<double[]> xValues, List<double[]> yValues)
    {
        TimeSeries series = new TimeSeries(titles[0]);
        double[] xV = xValues.get(0);
        double[] yV = yValues.get(0);
        int seriesLength = xV.length;
        for (int k = 0; k < seriesLength; k++)
        {
            series.add(xV[k], yV[k]);
        }
        
        dataset.addSeries(series);
    }
    
    /**
     * Builds an XY multiple series renderer.
     * 
     * @param nbLines Number of lines
     * @param colors Array of colors for each point
     * @return The XY multiple series renderers
     */
    private XYMultipleSeriesRenderer buildRenderer(int nbLines, int[] colors)
    {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, nbLines, colors);
        return renderer;
    }

    /**
     * Set the renderer parameters for the line chart
     * 
     * @param renderer
     * @param nbLines
     * @param colors
     */
    private void setRenderer(XYMultipleSeriesRenderer renderer, int nbLines, int[] colors)
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
            r.setPointStyle(PointStyle.CIRCLE);
            renderer.addSeriesRenderer(r);
        }
    }

    /**
     * Sets a few of the series renderer settings.
     * 
     * @param renderer the renderer to set the properties to
     * @param title the chart title
     * @param xTitle the title for the X axis
     * @param yTitle the title for the Y axis
     * @param xMin the minimum value on the X axis
     * @param xMax the maximum value on the X axis
     * @param yMin the minimum value on the Y axis
     * @param yMax the maximum value on the Y axis
     * @param axesColor the axes color
     * @param labelsColor the labels color
     */
    private void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle, String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor)
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
    
    /**
     * Set all cells of curve table to enabled or disabled
     * 
     * @param isPanelEnabled
     */
    public void refreshFieldsVisibility(boolean isPanelEnabled)
    {
        for (int i = 0; i < getCellsCount(); i++)
        {
            EditText cell = getCellAt(i);
            cell.setEnabled(isPanelEnabled);
        }
    }
    
    /**
     * @param index Index of the cell
     * @return The cell (EditText) associated with the index
     */
    private EditText getCellAt(int index)
    {
        int x = index % tableNbX + 1;
        int y = index / tableNbX + 1;
        
        return (EditText) tableLayout.findViewById(getCellId(x,y));
    }
    
    /**
     * @return The number of cells for the table
     */
    private int getCellsCount()
    {
        return tableNbX * tableNbY;
    }
    
    /**
     * Write the constants of the curve to the ECU
     */
    public void writeChangesToEcu()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        Constant xBinsConstant = ecu.getConstantByName(curve.getxBinsName());
        Constant yBinsConstant = ecu.getConstantByName(curve.getyBinsName());
        
        if (xBinsConstant.isModified())
        {
            DebugLogManager.INSTANCE.log("Constant \"" + xBinsConstant.getName() + "\" was modified, need to write change to ECU", Log.DEBUG);
            
            xBinsConstant.setModified(false);
            
            ecu.writeConstant(xBinsConstant);
        }
        
        if (yBinsConstant.isModified())
        {
            DebugLogManager.INSTANCE.log("Constant \"" + yBinsConstant.getName() + "\" was modified, need to write change to ECU", Log.DEBUG);
            
            yBinsConstant.setModified(false);
            
            ecu.writeConstant(yBinsConstant);
        }
    }
}
