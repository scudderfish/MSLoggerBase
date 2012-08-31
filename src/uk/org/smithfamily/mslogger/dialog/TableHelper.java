package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.OutputChannel;
import uk.org.smithfamily.mslogger.ecuDef.TableEditor;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.FloatMath;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

/**
 *
 */
public class TableHelper
{
    private TableEditor table;
    private Context context;
    
    private int tableNbX = 0;
    private int tableNbY = 0;
    
    // An array of 5 colors:  (blue, cyan, green, yellow, red) using {R,G,B} for each
    final float COLORS[][] = {{0, 0, 255}, {0, 255, 255}, {0, 255, 0}, {255, 255, 0}, {255, 0, 0}};
    final int NUM_COLORS = 5;
    
    private float currentTableMin = 0;
    private float currentTableMax = 0;
    
    private boolean isModified = false;
    
    private LinearLayout containerLayout;
    private TextView xBinsLabel;
    private TextView yBinsLabel;
    private TextView tableLabel;
    private TableLayout tableLayout;
    
    private boolean isTableDialog;
    
    /**
     * 
     * @param context
     * @param table
     */
    public TableHelper(Context context, TableEditor table, boolean isTableDialog)
    {
        this.table = table;
        this.context = context;
        this.isTableDialog = isTableDialog;
        
        buildLayout();
        drawTable();
    }
    
    /**
     * Get the table editor associated with the table
     * 
     * @return
     */
    public TableEditor getTableEditor()
    {
        return this.table;
    }
    
    /**
     * Build the layout for the table
     */
    private void buildLayout()
    {        
        LinearLayout.LayoutParams containerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout containerLayout = new LinearLayout(context);    
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        containerLayout.setLayoutParams(containerLayoutParams);
        
        TextView tableLabel = new TextView(context);;
        if (!isTableDialog)
        {
            LayoutParams tablelabelLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            tablelabelLayoutParams.setMargins(0, 0, 0, 25);

            tableLabel.setLayoutParams(tablelabelLayoutParams);
            tableLabel.setTextAppearance(context, android.R.style.TextAppearance_Medium);
        }
        
        LinearLayout tableContainerLayout = new LinearLayout(context);
        tableContainerLayout.setOrientation(LinearLayout.HORIZONTAL);
        tableContainerLayout.setLayoutParams(containerLayoutParams);
        
        TextView xBinsLabel = new TextView(context);
        LayoutParams xBinslabelLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        xBinsLabel.setLayoutParams(xBinslabelLayoutParams);
        xBinsLabel.setGravity(Gravity.CENTER_HORIZONTAL);
        xBinsLabel.setTextAppearance(context, android.R.style.TextAppearance_Medium);
        
        TextView yBinsLabel = new TextView(context);
        LayoutParams yBinsLabelLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        yBinsLabel.setLayoutParams(yBinsLabelLayoutParams);
        yBinsLabel.setTextAppearance(context, android.R.style.TextAppearance_Medium);
        yBinsLabel.setGravity(Gravity.CENTER_VERTICAL);
        
        TableLayout tableLayout = new TableLayout(context);
        TableLayout.LayoutParams tableLayoutLayoutParams = new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tableLayout.setLayoutParams(tableLayoutLayoutParams);
        
        tableContainerLayout.addView(yBinsLabel);
        tableContainerLayout.addView(tableLayout);
        
        if (!isTableDialog) {
            containerLayout.addView(tableLabel);
        }
        
        containerLayout.addView(tableContainerLayout);
        containerLayout.addView(xBinsLabel);
        
        this.containerLayout = containerLayout;
        this.xBinsLabel = xBinsLabel;
        this.yBinsLabel = yBinsLabel;
        this.tableLabel = tableLabel;
        this.tableLayout = tableLayout;
    }
    
    /**
     * 
     */
    public LinearLayout getLayout()
    {
        return containerLayout;
    }
    
    /**
     * 
     */
    public boolean isModified()
    {
        return isModified;
    }
    
    /**
     * 
     */
    private void drawTable()
    {
        this.tableNbX = table.getzBins().length;
        this.tableNbY = table.getzBins()[0].length;
        
        // Apply animation on text view so it's vertical (-90 degree rotation with 0ms duration animation)
        RotateAnimation rotAnim = new RotateAnimation(0, -90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotAnim.setDuration(0);
        rotAnim.setFillAfter(true); // stay at the last position of the animation
        rotAnim.setFillEnabled(true);
        
        yBinsLabel.setAnimation(rotAnim);
        
        // X and Y axis labels
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        OutputChannel xOutputChannel = ecu.getOutputChannelByName(table.getxOutputChannel());
        OutputChannel yOutputChannel = ecu.getOutputChannelByName(table.getyOutputChannel());
        
        xBinsLabel.setText(table.getxOutputChannel() + " (" + xOutputChannel.getUnits() + ")");
        yBinsLabel.setText(table.getyOutputChannel() + " (" + yOutputChannel.getUnits() + ")");
        
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
                
        LayoutParams rowHeaderLayout = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        rowHeaderLayout.setMargins(0, 0, 10, 0);
        
        // Add label on top of table if this table won't be used as a dialog with a single table in it,
        // otherwise it looks weird.
        if (!isTableDialog)
        {
            tableLabel.setText(table.getLabel());
        }
        
        TableRow tableRow;     
        
        double xBins[] = table.getxBins();
        double yBins[] = table.getyBins();
        
        double zBins[][] = table.getzBins();
        
        int xDigits = getDigitsFromScale(xOutputChannel.getScale());
        int yDigits = getDigitsFromScale(yOutputChannel.getScale());
        
        for (int y = 1; y <= tableNbY; y++)
        {
            tableRow = new TableRow(context);
            tableRow.setLayoutParams(lp);
            
            // Row header
            TextView rowHeader = new TextView(context);
            
            String headerLabel = "";
            if (yDigits == 0)
            {
                headerLabel = String.valueOf((int) yBins[tableNbY - y]);
            }
            else
            {
                headerLabel = String.valueOf(ecu.roundDouble(yBins[tableNbY - y], yDigits));
            }
            
            rowHeader.setText(headerLabel);
            rowHeader.setLayoutParams(rowHeaderLayout);
            tableRow.addView(rowHeader);
            
            for (int x = 1; x <= tableNbX; x++)
            {
                EditText cell = new EditText(context);
                cell.setText(Double.toString(zBins[x - 1][tableNbY - y]));
                cell.setId(getCellId(x,y));
                cell.setLayoutParams(lp);
                cell.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                cell.setSingleLine(true);
                cell.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                cell.setPadding(8, 5, 8, 5);
                cell.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                
                // Refresh background color when one edit text lost focus
                cell.addTextChangedListener(new CustomTextWatcher(cell));
                
                // Add EditText to row
                tableRow.addView(cell);
            }
            
            tableLayout.addView(tableRow,lp);
        }
        
        // Column headers
        tableRow = new TableRow(context);
        tableRow.setLayoutParams(lp);
        
        for (int x = 1; x <= tableNbX + 1; x++)
        {
            TextView columnHeader = new TextView(context);
            
            if (x > 1)
            {
                String headerLabel = "";
                if (xDigits == 0)
                {
                    headerLabel = String.valueOf((int) xBins[x - 2]);
                }
                else
                {
                    headerLabel = String.valueOf(ecu.roundDouble(xBins[x - 2], xDigits));
                }
                
                columnHeader.setText(headerLabel);
                columnHeader.setGravity(Gravity.CENTER);
            }          
                        
            columnHeader.setLayoutParams(lp);
            
            tableRow.addView(columnHeader);   
        }       
        
        tableLayout.addView(tableRow,lp);
        
        refreshCellsBackgroundColor(null);
    }
    
    /**
     * The INI define digits as scale in the following annoying format: 1.0, 0.1, etc. We need to extract how many digits that represents
     * 
     * @param scale
     * @return
     */
    private int getDigitsFromScale(double scale)
    {
        String sScale = Double.toString(scale);
        
        int digits = 0, separatorPosition = sScale.indexOf(".");
        
        if (separatorPosition > -1)
        {
            int i = 0;
            for (i = separatorPosition + 1; i < sScale.length(); i++)
            {
                if (sScale.substring(i, i + 1).equals("0"))
                {
                    break;
                }
            }
            
            digits = i - 1 - separatorPosition;
        }
        
        return digits;
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
            isModified = true;
            refreshCellsBackgroundColor(mEditText);
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
     * This method goes over all the table cells and apply the right background color
     * so all the cells together looks like  a heat map
     * 
     * @param cell The cell that triggered the refresh, if any
     */
    private void refreshCellsBackgroundColor(EditText cell)
    {
        EditText firstCell = (EditText) tableLayout.findViewById(getCellId(1,1));
        
        float min = Float.parseFloat(firstCell.getText().toString());
        float max = min;
        
        // Find min/max
        for (int y = 1; y <= tableNbY; y++)
        {
            for (int x = 1; x <= tableNbX; x++)
            {
                EditText currentCell = (EditText) tableLayout.findViewById(getCellId(x,y));
                float currentValue = 0;
                
                try
                {
                    currentValue = Float.parseFloat(currentCell.getText().toString());
                }
                catch (NumberFormatException e) {}

                if (min > currentValue)
                {
                    min = currentValue;
                }
                
                if (max < currentValue)
                {
                    max = currentValue;
                }
            }
        }
        
        // Min and/or maximum value of the table changed, we need redraw all background colors
        if (cell == null || currentTableMin != min || currentTableMax != currentTableMax)
        {
            // Change background colors
            for (int y = 1; y <= tableNbY; y++)
            {
                for (int x = 1; x <= tableNbX; x++)
                {
                    EditText currentCell = (EditText) tableLayout.findViewById(getCellId(x,y));
                    
                    float currentValue = 0;
                    try
                    {
                        currentValue = Float.parseFloat(currentCell.getText().toString());
                    }
                    catch (NumberFormatException e) {}
    
                    float currentPercent = (currentValue - min) / (max - min);
                    
                    currentCell.setBackgroundColor(getHeatMapColor(currentPercent));
                }
            }
        }
        // Just redraw the specific cell that the value changed
        else
        {
            float currentValue = 0;
            try
            {
                currentValue = Float.parseFloat(cell.getText().toString());
            }
            catch (NumberFormatException e) {}
            
            float currentPercent = (currentValue - min) / (max - min);
            cell.setBackgroundColor(getHeatMapColor(currentPercent));
        }
    }
    
    /**
     * 
     * @param value
     * @return
     */
    private int getHeatMapColor(float value)
    {
        int idx1, idx2;
        
        // Fraction between "idx1" and "idx2" where our value is
        float fractBetween = 0;
       
        if (value <= 0)
        {
            idx1 = idx2 = 0;
        } 
        else if (value >= 1)
        {
            idx1 = idx2 = NUM_COLORS - 1;
        }
        else
        {
          value = value * (NUM_COLORS - 1);
          idx1  = (int) FloatMath.floor(value);
          idx2  = idx1 + 1;
          
          // Distance between the two indexes (0-1)
          fractBetween = value - (float) idx1;
        }
        
        int red = (int) ((COLORS[idx2][0] - COLORS[idx1][0]) * fractBetween + COLORS[idx1][0]);
        int green = (int) ((COLORS[idx2][1] - COLORS[idx1][1]) * fractBetween + COLORS[idx1][1]);
        int blue = (int) ((COLORS[idx2][2] - COLORS[idx1][2]) * fractBetween + COLORS[idx1][2]);
        
        return Color.rgb(red, green, blue);
    }
}
