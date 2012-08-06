package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.TableEditor;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.FloatMath;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

/**
 *
 */
public class EditTableDialog extends Dialog implements android.view.View.OnClickListener
{    
    private TableEditor table;
    private int tableNbX = 0;
    private int tableNbY = 0;
    
    /**
     * 
     * @param context
     * @param tableNbX
     * @param tableNbY
     */
    public EditTableDialog(Context context, TableEditor table, int tableNbX, int tableNbY)
    {
        super(context);
        
        System.out.println("bleh" + table);
        
        this.table = table;
        this.tableNbX = tableNbX;
        this.tableNbY = tableNbY;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.edittable);
     
        setTitle("Edit " + table.getLabel());
        
        final int tableNbX = 12;
        final int tableNbY = 12;
        
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
                
        LayoutParams rowHeaderLayout = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        rowHeaderLayout.setMargins(0, 0, 10, 0);
        
        TableRow tableRow;     
        
        /*
         *  Fake VE table
         */
        
        double veTable[][] = {{48.5,49.8,53.8,57.6,57.4,63.2,58.7,34.4,20.3,16.8,22.2,36.7},
                                {50.2,50.2,47.9,48.8,49.7,50.2,41.9,34.3,18.5,17.9,31.3,39.4},
                                {50.2,50.2,47.1,48.0,49.2,51.9,50.7,52.6,35.3,32.9,37.5,50.4},
                                {50.2,50.2,49.7,50.5,51.4,52.7,53.0,53.0,47.1,34.4,43.1,52.4},
                                {51.3,53.1,51.4,52.3,53.0,54.6,54.4,55.9,48.3,39.5,46.5,53.2},
                                {53.1,53.2,53.3,54.5,55.1,56.6,55.9,57.6,54.5,48.8,53.4,54.7},
                                {53.8,54.3,54.7,55.7,56.0,58.1,57.1,58.0,58.4,53.9,60.9,66.3},
                                {54.0,53.1,56.4,57.3,58.1,59.9,58.4,60.7,59.7,57.2,64.0,66.5},
                                {55.2,54.0,56.9,57.5,58.3,60.2,58.9,59.8,60.4,58.3,64.5,70.6},
                                {55.5,55.5,57.3,58.8,58.3,60.1,58.8,61.0,61.4,62.9,68.2,70.8},
                                {55.8,57.1,58.0,58.0,58.1,60.0,60.0,61.2,62.3,64.0,68.6,70.7},
                                {56.8,57.4,57.9,59.1,59.0,60.4,60.7,59.7,60.6,62.0,65.6,69.5}};
        
        int xBins[] = {600, 1000, 1300, 1600, 2000, 2700, 3000, 3800, 4500, 4900, 5500, 6000};
        int yBins[] = {20, 30, 35, 40, 50, 55, 70, 85, 100, 140, 170, 250};
        
        for (int y = 1; y <= tableNbX; y++)
        {
            tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(lp);
            
            // Row header
            TextView rowHeader = new TextView(getContext());
            rowHeader.setText(Integer.toString(yBins[tableNbY - y]));
            rowHeader.setLayoutParams(rowHeaderLayout);
            tableRow.addView(rowHeader);
            
            for (int x = 1; x <= tableNbY; x++)
            {
                EditText cell = new EditText(getContext());
                cell.setText(Double.toString(veTable[tableNbY - y][x - 1]));
                cell.setId(getCellId(x,y));
                cell.setLayoutParams(lp);
                cell.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                cell.setSingleLine(true);
                
                // Refresh background color when one edit text lost focus
                cell.addTextChangedListener(new TextWatcher()
                {
                    @Override
                    public void afterTextChanged(Editable s)
                    {
                        refreshCellsBackgroundColor();        
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}                     
                });
                
                // Add EditText to row
                tableRow.addView(cell);
            }
            
            tableLayout.addView(tableRow,lp);
        }
        
        // Column headers
        tableRow = new TableRow(getContext());
        tableRow.setLayoutParams(lp);
        
        for (int x = 1; x <= tableNbX + 1; x++)
        {
            TextView columnHeader = new TextView(getContext());
            
            if (x > 1)
            {
                columnHeader.setText(Integer.toString(xBins[x - 2]));
                columnHeader.setGravity(Gravity.CENTER);
            }          
                        
            columnHeader.setLayoutParams(lp);
            
            tableRow.addView(columnHeader);   
        }       
        
        tableLayout.addView(tableRow,lp);
                
        TextView xBinsLabel = (TextView) findViewById(R.id.xBinsLabel);
        xBinsLabel.setText("RPM");
        
        // Apply animation on text view so it's vertical (90 degree rotation with 0 duration animation)
        final TextView yBinsLabel = (TextView) findViewById(R.id.yBinsLabel);
        yBinsLabel.setText("MAP");        
        
        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(getContext(), R.anim.verticaltextview);
        ranim.setFillAfter(true);
        yBinsLabel.setAnimation(ranim);
        
        refreshCellsBackgroundColor();
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(this);
        
        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
     */
    private void refreshCellsBackgroundColor()
    {
        EditText firstCell = (EditText) findViewById(getCellId(1,1));
        
        float min = Float.parseFloat(firstCell.getText().toString());
        float max = min;
        
        // Find min/max
        for (int y = 1; y <= tableNbX; y++)
        {
            for (int x = 1; x <= tableNbY; x++)
            {
                EditText currentCell = (EditText) findViewById(getCellId(x,y));
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
        
        // Change background colors
        for (int y = 1; y <= tableNbX; y++)
        {
            for (int x = 1; x <= tableNbY; x++)
            {
                EditText currentCell = (EditText) findViewById(getCellId(x,y));
                
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
    
    /**
     * 
     * @param value
     * @return
     */
    private int getHeatMapColor(float value)
    {
        final int NUM_COLORS = 5;
        
        // An array of 5 colors:  (blue, cyan, green, yellow, red) using {R,G,B} for each
        float color[][] = {{0, 0, 255}, {0, 255, 255}, {0, 255, 0}, {255, 255, 0}, {255, 0, 0}};
       
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
        
        int red = (int) ((color[idx2][0] - color[idx1][0]) * fractBetween + color[idx1][0]);
        int green = (int) ((color[idx2][1] - color[idx1][1]) * fractBetween + color[idx1][1]);
        int blue = (int) ((color[idx2][2] - color[idx1][2]) * fractBetween + color[idx1][2]);
        
        return Color.rgb(red, green, blue);
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
