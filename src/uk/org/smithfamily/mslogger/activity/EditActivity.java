package uk.org.smithfamily.mslogger.activity;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.dialog.CurveHelper;
import uk.org.smithfamily.mslogger.dialog.DialogHelper;
import uk.org.smithfamily.mslogger.dialog.TableHelper;
import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.CurveEditor;
import uk.org.smithfamily.mslogger.ecuDef.DialogField;
import uk.org.smithfamily.mslogger.ecuDef.DialogPanel;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.TableEditor;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class EditActivity extends Activity implements android.view.View.OnClickListener
{
    private MSDialog dialog;
    private RelativeLayout content;
    private Megasquirt ecu;
    private int nbPanels = 0;
    
    // Used on label in table row with no field beside
    // Those are usually used as separator so add top and bottom margins
    private LayoutParams lpSpanWithMargins;
    
    // Used on label in table row with field beside label, add a margin right
    // so the label and field are separated
    private LayoutParams lpSpan;
    
    // Regular layout params for dialog row with label and constant
    private LayoutParams lp;
    
    private List<CurveHelper> curveHelpers = new ArrayList<CurveHelper>();
    private List<TableHelper> tableHelpers = new ArrayList<TableHelper>();
    
    
    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        MSDialog dialog = ecu.getDialogByName("CrankTble");
        this.dialog = dialog;
        
        // Initialise some layout params
        lpSpanWithMargins = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lpSpanWithMargins.setMargins(0, 10, 0, 15);
        lpSpanWithMargins.span = 2;
        
        lpSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lpSpan.setMargins(0, 0, 8, 0);
        
        lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        
        setContentView(R.layout.editdialog);
        
        content = (RelativeLayout) findViewById(R.id.content);
        
        setTitle(dialog.getLabel());
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(this);
        
        drawDialogFields(dialog, false, "");
        
        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        
    }
    private void createCurvePanel(CurveEditor curvePanel, String orientation)
    {
        CurveHelper curveHelper = null;//new CurveHelper(this, curvePanel);
        
        TableLayout tl = new TableLayout(this);

        tl.setId(nbPanels);
        
        if (nbPanels > 0) 
        {
            addPanel(tl, orientation); 
        }

        TableRow tableRow = new TableRow(this);
        
        LinearLayout curveLayout = curveHelper.getLayout();
        curveLayout.setLayoutParams(lpSpan);
        
        tableRow.addView(curveLayout);
        
        tl.addView(tableRow);

        curveHelpers.add(curveHelper);
    }
    
    /**
     * 
     * @param tablePanel
     */
    private void createTablePanel(TableEditor tablePanel, String orientation)
    {
        TableHelper tableHelper = new TableHelper(this, tablePanel, false);
        
        TableLayout tl = new TableLayout(this);

        tl.setId(nbPanels);
        
        if (nbPanels > 0) 
        {
            addPanel(tl, orientation);
        }
        
        TableRow tableRow = new TableRow(this);
        
        LinearLayout curveLayout = tableHelper.getLayout();
        curveLayout.setLayoutParams(lpSpan);
        
        tableRow.addView(curveLayout);
        
        tl.addView(tableRow);
        
        tableHelpers.add(tableHelper);
    }
    
    private void addPanel(TableLayout tl, String orientation)
    {
        // This is not the first panel we add on this dialog
        if (nbPanels > 0)
        {
            RelativeLayout.LayoutParams tlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    
            /*
            if (orientation.equals("North"))
            {
                tlp.addRule(RelativeLayout.ABOVE, nbPanels - 1);
                tlp.addRule(RelativeLayout.ALIGN_LEFT, nbPanels - 1);
                tl.setPadding(0, 0, 0, 10);
                System.out.println("PANEL " + dialog.getName() + " + above " + (nbPanels - 1) + ": ");
            }
            else if (orientation.equals("South"))
            {
                tlp.addRule(RelativeLayout.BELOW, nbPanels - 1);
                tlp.addRule(RelativeLayout.ALIGN_LEFT, nbPanels - 1);
                tl.setPadding(0, 10, 0, 0);
                System.out.println("PANEL " + dialog.getName() + " + below " + (nbPanels - 1) + ": ");
            }
            else if (orientation.equals("West"))
            {
                tlp.addRule(RelativeLayout.LEFT_OF, nbPanels - 1);
                tl.setPadding(0, 0, 10, 0);
                System.out.println("PANEL " + dialog.getName() + " + at the left of " + (nbPanels - 1) + ": ");
            }
            else
            {
                tlp.addRule(RelativeLayout.RIGHT_OF, nbPanels - 1);
                tl.setPadding(10, 0, 0, 0);
                System.out.println("PANEL " + dialog.getName() + " + at the right of " + (nbPanels - 1) + ": ");
            }*/
            
            tlp.addRule(RelativeLayout.RIGHT_OF, nbPanels - 1);
            tl.setPadding(10, 0, 0, 0);
            
            tl.setLayoutParams(tlp);
        }
        
        content.addView(tl);
        nbPanels++;
    }
    private void showPanelLabel(String title, TableLayout tl)
    {
        LayoutParams lpSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lpSpan.span = 2;
        
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(lpSpan);
        
        TextView label = new TextView(this);
        label.setText(title);
        label.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        label.setPadding(0, 0, 0, 10);
        label.setLayoutParams(lpSpan);
        
        tableRow.addView(label);
        
        tl.addView(tableRow);
    }
    /**
    * Show the constant doesn't exists alert dialog
    * 
    * @param constantName The name of the constant that doesn't exists
    */
   private void showConstantDoesntExists(String constantName)
   {
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setMessage("Uh oh, It looks like constant \"" + constantName + "\" is missing!")
               .setIcon(android.R.drawable.ic_dialog_info)
               .setTitle("Missing constant")
               .setCancelable(true)
               .setPositiveButton("OK", new DialogInterface.OnClickListener()
               {
                   public void onClick(DialogInterface dialog, int id){}
               });
       
       AlertDialog alert = builder.create();
       alert.show();  
   }
       
   /**


    /**
     * Recursive function that will draw fields and panels of a dialog.
     * It's called recursively until all fields and panels were drawn.
     * 
     * @param dialog
     * @param isPanel
     * @param orientation
     */
    private void drawDialogFields(MSDialog dialog, boolean isPanel, String orientation)
    {
        TableLayout tl = new TableLayout(this);

        tl.setId(nbPanels);
        
        addPanel(tl, orientation); 
        
        TableRow tableRow;
        
        // If it's a panel or at least a dialog with one field or more, we can display its label
        if (isPanel || dialog.getFieldsList().size() > 0)
        {
            showPanelLabel(dialog.getLabel(), tl);
        }
        
        // For each dialog field, add a row in the table layout
        for (DialogField df : dialog.getFieldsList())
        {
            Constant constant = ecu.getConstantByName(df.getName());

            if (constant == null && !df.getName().equals("null"))
            {
                showConstantDoesntExists(df.getName());
            }
            else 
            {
                tableRow = new TableRow(this);
                
                TextView label = getLabel(df, constant);
                
                tableRow.addView(label);
                
                // For empty label or empty field name, we just insert an empty text view as second column of the row
                if ((df.getLabel().equals("") && df.getName().equals("null")) || df.getName().equals("null"))
                {
                    // No second column so label is used to separate so make it bold and merge columns
                    label.setTypeface(null, Typeface.BOLD);
                    
                    // If it's not an empty label and not , add some top and bottom margins
                    if (!df.getLabel().equals(""))
                    {
                        label.setLayoutParams(lpSpanWithMargins);
                    }
                }
                // Regular row with label and constant fields
                else 
                {
                    tableRow.setLayoutParams(lp);
                    
                    // Multi-choice constant
                    if (constant.getClassType().equals("bits"))
                    {
                        Spinner spin = buildMultiValuesConstantField(df, constant);
                        
                        tableRow.addView(spin);
                    }
                    // Single value constant
                    else
                    {
                        EditText edit = buildSingleValueConstantField(df, constant);

                        tableRow.addView(edit);
                    }
                }
                
                tl.addView(tableRow);
            }           
        }        
        
        // For each dialog panel, add a layout to the dialog
        for (DialogPanel dp : dialog.getPanelsList())
        {
            MSDialog dialogPanel = ecu.getDialogByName(dp.getName());
            
            if (dialogPanel != null)
            {
                drawDialogFields(dialogPanel, true, dp.getOrientation());
            }
            else
            {
                // Maybe it's a curve panel
                CurveEditor curvePanel = ecu.getCurveEditorByName(dp.getName());
                if (curvePanel != null)
                {
                    createCurvePanel(curvePanel, dp.getOrientation());
                }
                else
                {
                    // Maybe it's a table panel
                    TableEditor tablePanel = ecu.getTableEditorByName(dp.getName());
                    
                    if (tablePanel != null)
                    {
                        createTablePanel(tablePanel, dp.getOrientation());
                    }
                }
            }
        }
    }
    /**
     * Take information from a dialog field and constant and build the label for the field
     * 
     * @param df The DialogField to build the label for
     * @param constant The Constant to build the label for
     * 
     * @return The TextView object
     */
    private TextView getLabel(DialogField df, Constant constant)
    {
        String labelText = df.getLabel();
        
        // Add units to label
        if (constant != null && !constant.getUnits().equals(""))
        {
            labelText += " (" + constant.getUnits() + ")";
        }
        
        TextView label = new TextView(this);
        label.setText(labelText);
        
        // If the first character of the label is a #, we need to highlight that label
        // It means it is used as a section separator
        if (labelText.length() > 0 && labelText.substring(0,1).equals("#"))
        {
            label.setText(" " + label.getText().toString().substring(1)); // Replace the # by a space
            label.setBackgroundColor(Color.rgb(110, 110, 110));
        }
        
        label.setLayoutParams(lpSpan);
        
        return label;
    }
    
    /**
     * Build an EditText for displaying single value constant
     * 
     * @param df The dialog field to build the display for
     * @param constant The constant associated with the dialog field
     * @return The EditText that can be displayed
     */
    private EditText buildSingleValueConstantField(DialogField df, Constant constant)
    {
        double constantValue = ecu.roundDouble(ecu.getField(df.getName()),constant.getDigits());
        String displayedValue = "";
        
        if (constant.getDigits() == 0)
        {
            displayedValue = String.valueOf((int) constantValue);
        }
        else
        {
            displayedValue = String.valueOf(constantValue);
        }
        
        EditText edit = new EditText(this);
        edit.setText(displayedValue);
        edit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edit.setSingleLine(true);
        edit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        edit.setPadding(8, 5, 8, 5);
        edit.setTag(df.getName());
        edit.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
        edit.setOnFocusChangeListener(new OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    Constant constant = ecu.getConstantByName(((EditText) v).getTag().toString());
                    DialogHelper.verifyOutOfBoundValue(EditActivity.this, constant, (EditText) v);
                }
            }
        });
        edit.addTextChangedListener(new TextWatcher()
        {
            /**
             * Set the constant to modified when value is changed
             * 
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s)
            {
                EditText edit = (EditText) getCurrentFocus();
                String constantName = edit.getTag().toString();
                
                Constant constant = ecu.getConstantByName(constantName);
                constant.setModified(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        
        // Field is ready only or disabled
        if (df.isDisplayOnly() || !ecu.getUserDefinedVisibilityFlagsByName(df.getExpression()))
        {
            edit.setEnabled(false);
        }
        
        return edit;
    }

    /**
     *  Build a Spinner for displaying multi values constant
     *
     * @param df The dialog field to build the display for
     * @param constant The constant associated with the dialog field
     * @return The Spinner that can be displayed
     */    
    private Spinner buildMultiValuesConstantField(DialogField df, Constant constant)
    {
        Spinner spin = new Spinner(this);
        
        // Field is ready only or disabled
        if (df.isDisplayOnly() || !ecu.getUserDefinedVisibilityFlagsByName(df.getExpression()))
        {
            spin.setEnabled(false);
        }

        final List<MultiValuesSpinnerData> spinnerData = new ArrayList<MultiValuesSpinnerData>();
        
        int selectedValue = (int) ecu.getField(df.getName());
        int selectedIndex = 0;
        int invalidCount = 0;
        
        // Remove INVALID from values
        for (int i = 0; i < constant.getValues().length; i++)
        {
            String value = constant.getValues()[i];
            
            if (value.equals("INVALID"))
            {
                invalidCount++;
            }
            else
            {
                spinnerData.add(new MultiValuesSpinnerData(i + 1, value));
            }
            
            /*
             *  When we reach the currently selected valid, we need to keep track of how many
             *  invalid value there was before that, because those won't be displayed in the
             *  spinner and we need to know which index to select
             */
            if (selectedValue == i)
            {
                selectedIndex = i - invalidCount;
            }
        }
 
        ArrayAdapter<MultiValuesSpinnerData> spinAdapter = new ArrayAdapter<MultiValuesSpinnerData>(this, android.R.layout.simple_spinner_item, spinnerData);
        
        // Specify the layout to use when the list of choices appears
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spin.setAdapter(spinAdapter);
        spin.setSelection(selectedIndex);
        spin.setTag(df.getName());
        
        final MSDialog msDialog = this.dialog;
        
        spin.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            boolean ignoreEvent = true;
            
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                // First onItemSelected event of the spinner come from populating it, ignore it!
                if (ignoreEvent) {
                    ignoreEvent = false;
                }
                else 
                {
                    String constantName = parentView.getTag().toString();
                    
                    int value = spinnerData.get(position).getId();
                    
                    // Value changed, update field in ECU class
                    if (ecu.getField(constantName) != value)
                    {
                        // Constant has been modified and will need to be burn to ECU
                        Constant constant = ecu.getConstantByName(constantName);
                        constant.setModified(true);
                        
                        // Update ecu field with new value
                        ecu.setField(constantName, value); 
                        
                        // Re-evaluate the expressions with the data updated
                        ecu.setUserDefinedVisibilityFlags();
    
                        // Refresh the UI
                        refreshFieldsVisibility(msDialog);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView){}
        });
        
        return spin;
    }
    /**
     * Helper class for multi values spinner which specify an id and text for each row of the spinner
     */
    class MultiValuesSpinnerData
    {
        private int id = 0;
        private String text = "";
        
        public MultiValuesSpinnerData(int id, String text)
        {
            this.id = id;
            this.text = text;
        }
        
        public int getId()
        {
            return id;
        }
        
        public String getText()
        {
            return text;
        }
        
        public String toString()
        {
            return text;
        }
    }
    /**
     * When value are changed, it's possible dialog fields change state
     * so we need to refresh fields visibility and re-apply them recursivly 
     * on all the panels
     */
    private void refreshFieldsVisibility(MSDialog dialog)
    {
        for (DialogField df : dialog.getFieldsList())
        {
            Constant constant = ecu.getConstantByName(df.getName());
            
            if (constant != null)
            {
                // Field is not ready only and not disabled
                boolean isFieldEnabled = !df.isDisplayOnly() && ecu.getUserDefinedVisibilityFlagsByName(df.getExpression());
                
                if (constant.getClassType().equals("bits"))
                {
                    Spinner spin = (Spinner) content.findViewWithTag(df.getName());
                    spin.setEnabled(isFieldEnabled);
                }
                else
                {
                    EditText edit = (EditText) content.findViewWithTag(df.getName());
                    edit.setEnabled(isFieldEnabled);
                }
            }
        }
        
        for (DialogPanel dp : dialog.getPanelsList())
        {
            MSDialog dialogPanel = ecu.getDialogByName(dp.getName());
            
            if (dialogPanel != null)
            {
                refreshFieldsVisibility(dialogPanel);
            }
        }
    }
  

}