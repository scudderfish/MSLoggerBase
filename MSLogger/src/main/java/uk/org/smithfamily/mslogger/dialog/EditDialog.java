package uk.org.smithfamily.mslogger.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.DialogField;
import uk.org.smithfamily.mslogger.ecuDef.DialogPanel;
import uk.org.smithfamily.mslogger.ecuDef.InjectedCommand;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import uk.org.smithfamily.mslogger.ecuDef.MSUtils;
import uk.org.smithfamily.mslogger.ecuDef.MSUtilsShared;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.log.DebugLogManager;

/**
 *
 */
public class EditDialog extends Dialog implements android.view.View.OnClickListener
{
    private final MSDialog dialog;
    private RelativeLayout content;
    private final Megasquirt ecu;
    private int nbPanels = 1;
    
    // Used on label in table row with no field beside
    // Those are usually used as separator so add top and bottom margins
    private final LayoutParams lpSpanWithMargins;
    
    // Same as lpSpanWithMargins but without the span
    private final LayoutParams lpWithTopBottomMargins;
    
    // Used on label in table row with field beside label, add a margin right
    // so the label and field are separated
    private final LayoutParams lpWithMargins;
    
    // Regular layout params for dialog row with label and constant
    private final LayoutParams lp;


    private String panelLabel = "";
    
    private BroadcastReceiver yourReceiver;
    
    /**
     * Constructor for dialog which set the current dialog and ECU object
     *
     */
    public EditDialog(Context context, MSDialog dialog)
    {
        super(context);
        
        this.ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        this.dialog = dialog;
        
        // Initialise some layout params
        lpSpanWithMargins = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lpSpanWithMargins.setMargins(0, 10, 0, 15);
        lpSpanWithMargins.span = 2;
        
        lpWithMargins = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lpWithMargins.setMargins(0, 0, 8, 0);
        
        lpWithTopBottomMargins = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lpWithTopBottomMargins.setMargins(0, 10, 8, 15);
        
        lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }
       
    /**
     * 
     *
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.editdialog);
        
        content = findViewById(R.id.content);
        
        setTitle(dialog.getLabel());
        
        Button buttonBurn = findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonClose = findViewById(R.id.close);
        buttonClose.setOnClickListener(this);
        
        drawDialogFields(null, dialog, dialog, false, "", null);
        
        final IntentFilter injectCommandResultsFilter = new IntentFilter();
        injectCommandResultsFilter.addAction(Megasquirt.INJECTED_COMMAND_RESULTS);
        
        this.yourReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String action = intent.getAction();
                
                if (Megasquirt.INJECTED_COMMAND_RESULTS.equals(action))
                {
                    int resultId = intent.getIntExtra(Megasquirt.INJECTED_COMMAND_RESULT_ID, 0);
                    
                    if (resultId == Megasquirt.CONTROLLER_COMMAND)
                    {
                        DebugLogManager.INSTANCE.log("Refreshing fields visibility after controller command", Log.DEBUG);
                       
                        // Re-evaluate the expressions with the data updated
                        //ecu.setUserDefinedVisibilityFlags();
                        
                        }
                }
            }
        };
        
        // Registers the receiver so that your service will listen for broadcasts
        getContext().registerReceiver(this.yourReceiver, injectCommandResultsFilter);
        
        // Hide keyboard
        Objects.requireNonNull(getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    
    /**
     * Unregister the broadcast receiver when the dialog is closed
     */
    public void onStop()
    {
        getContext().unregisterReceiver(this.yourReceiver);
    }
    
    /**
     * Add the panel to the layout by looking at the orientation to insert the panel at the right place
     * 
     * @param parentLayout The parent layout the panel to add will be inserted into
     * @param relativeLayoutToAdd The layout to be added with all the dialog fields already there
     * @param orientation The orientation of the new panel
     * @param dialogName The name of the dialog (for debugging purpose only)
     * @param previousPanelLayout An instance of the previous layout added since the new one will be added in relation to the previous one
     */
    private void addPanel(RelativeLayout parentLayout, RelativeLayout relativeLayoutToAdd, String orientation, String dialogName, String dialogAxis, RelativeLayout previousPanelLayout)
    {
        RelativeLayout.LayoutParams tlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        
        DebugLogManager.INSTANCE.log("PANEL relativeLayoutToAdd id (" + dialogName + ") is " + relativeLayoutToAdd.getId(), Log.DEBUG);
        
        // This is not the first panel we add on this dialog
        if (previousPanelLayout != null)
        {
            switch (orientation) {
                case "North":
                    tlp.addRule(RelativeLayout.ABOVE, previousPanelLayout.getId());
                    relativeLayoutToAdd.setPadding(0, 0, 0, 15);

                    DebugLogManager.INSTANCE.log("PANEL " + dialogName + " above " + previousPanelLayout.getTag() + " (" + previousPanelLayout.getId() + ")", Log.DEBUG);
                    break;
                case "South":
                    tlp.addRule(RelativeLayout.BELOW, previousPanelLayout.getId());
                    relativeLayoutToAdd.setPadding(0, 15, 0, 0);

                    DebugLogManager.INSTANCE.log("PANEL " + dialogName + " below " + previousPanelLayout.getTag() + " (" + previousPanelLayout.getId() + ")", Log.DEBUG);
                    break;
                case "West":
                    tlp.addRule(RelativeLayout.LEFT_OF, previousPanelLayout.getId());
                    relativeLayoutToAdd.setPadding(0, 0, 15, 0);
                    DebugLogManager.INSTANCE.log("PANEL " + dialogName + " at the left of " + previousPanelLayout.getTag() + " (" + previousPanelLayout.getId() + ")", Log.DEBUG);
                    break;
                default:
                    // For yAxis orientation, add panel one under the other
                    if (dialogAxis.equals("yAxis")) {
                        tlp.addRule(RelativeLayout.BELOW, previousPanelLayout.getId());
                        tlp.addRule(RelativeLayout.ALIGN_LEFT, previousPanelLayout.getId());
                        relativeLayoutToAdd.setPadding(15, 15, 0, 0);
                        DebugLogManager.INSTANCE.log("PANEL " + dialogName + " (Dialog axis: " + dialogAxis + ") below " + previousPanelLayout.getTag() + " (" + previousPanelLayout.getId() + ")", Log.DEBUG);
                    }
                    // For xAxis orientation, add panel at the right of the last one
                    else {
                        tlp.addRule(RelativeLayout.RIGHT_OF, previousPanelLayout.getId());
                        tlp.addRule(RelativeLayout.ALIGN_TOP, previousPanelLayout.getId());
                        relativeLayoutToAdd.setPadding(15, 0, 0, 0);
                        DebugLogManager.INSTANCE.log("PANEL " + dialogName + " (Dialog axis: " + dialogAxis + ") at the right of " + previousPanelLayout.getTag() + " (" + previousPanelLayout.getId() + ")", Log.DEBUG);
                    }
                    break;
            }

            relativeLayoutToAdd.setLayoutParams(tlp);
        }
        else
        {
            DebugLogManager.INSTANCE.log("PANEL " + dialogName + " previousPanelLayout is null!", Log.DEBUG);
        }
        
        // Panel to add have a parent layout, add view to it
        if (parentLayout != null)
        {
            DebugLogManager.INSTANCE.log("PANEL parentLayout is " + parentLayout.getTag(), Log.DEBUG);
            parentLayout.addView(relativeLayoutToAdd);
        }
        // No parent layout, default to main layout
        else
        {

            DebugLogManager.INSTANCE.log("PANEL parentLayout is null, adding to main layout", Log.DEBUG);
            content.addView(relativeLayoutToAdd);
        }
        
        nbPanels++;
    }
    
    /**
     * Helper function to wrap a table layout into a relative layout
     * 
     * @param tl The table layout that will be wrapped into a relative layout
     * @param dialogName The dialog name of the panel we want to wrap
     */
    private RelativeLayout wrapTableLayoutIntoRelativeLayout(TableLayout tl, String dialogName)
    {
        RelativeLayout containerPanelLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams tlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        containerPanelLayout.setLayoutParams(tlp);
        containerPanelLayout.setId(nbPanels);
        containerPanelLayout.setTag(dialogName);
        containerPanelLayout.addView(tl);
        
        return containerPanelLayout;
    }
    
    /**
     * Used to figure out if there is at least one complete row in a dialog/panel.
     * This is useful because we use span=2 for row with just a label and if all
     * rows of the dialog end up being span=2, TableLayout doesn't like it and the
     * whole layout dissapear.
     * 
     * @param dialogFields The fields of the dialog
     * 
     * @return true is there is at least one complete row in the dialog, false otherwise
     */
    private boolean atLeastOneCompleteRow(List<DialogField> dialogFields)
    {
        boolean atLeastOne = false;
        for (DialogField df : dialogFields)
        {
            if (!((df.getLabel().equals("") && df.getName().equals("null")) || df.getName().equals("null") ))
            {
                atLeastOne = true;
                break;
            }
        }
        
        return atLeastOne;
    }
    

    /**
     * Recursive function that will draw fields and panels of a dialog.
     * It's called recursively until all fields and panels were drawn.
     * 
     * @param parentLayout The parent layout in which the dialog fields should be added
     * @param dialog The dialog object
     * @param parentDialog If it's a panel, this is the instance of the parent dialog
     * @param isPanel true if the dialog object is a panel, false otherwise
     * @param orientation The orientation of the panel
     * @param previousDialogPanelLayout An instance of the previous layout added since the new one will be added in relation to the previous one
     * 
     * @return A relative layout with all fields in it
     */
    private RelativeLayout drawDialogFields(RelativeLayout parentLayout, MSDialog dialog, MSDialog parentDialog, boolean isPanel, String orientation, RelativeLayout previousDialogPanelLayout)
    {
        TableLayout panelLayout = new TableLayout(getContext());
        
        // If it's a panel or at least a dialog with one field or more, we can display its label
        if ((isPanel || dialog.getFieldsList().size() > 0) && !dialog.getLabel().equals(""))
        {
            panelLabel = dialog.getLabel();
        }
        
        // For each dialog field, add a row in the table layout
        for (DialogField df : dialog.getFieldsList())
        {
            Constant constant = ecu.getConstantByName(df.getName());

            if (constant == null &&  !df.getName().equals("null"))
            {
                showConstantDoesntExists(df.getName());
            }
            else 
            {
                // If we have a panel label to add, add it!
                if (!panelLabel.equals(""))
                {
                    panelLayout.addView(showPanelLabel(panelLabel, dialog.getFieldsList()));
                    
                    panelLabel = "";
                }
                
                TableRow tableRow = new TableRow(getContext());
                
                // Dialog field is a command button
                {
                    // For empty label or empty field name, we just insert an empty text view as second column of the row
                    if ((df.getLabel().equals("") && df.getName().equals("null")) || df.getName().equals("null"))
                    {
                        // Special label used to identify hard coded required fuel panel in dialog
                        if (df.getLabel().equals("std_required_fuel"))
                        {
                        }
                        // Special label used to identify hard coded seek bar in std_accel dialog
                        else if (df.getLabel().equals("std_accel_seek_bar"))
                        {
                            RelativeLayout accelSeekBar = getAccelSeekBar();
                            accelSeekBar.setLayoutParams(lpWithTopBottomMargins);
                            tableRow.addView(accelSeekBar);
                        }
                        else
                        {
                            TextView label = getLabel(df, constant);
                            tableRow.addView(label);
                            
                            // No second column so label is used to separate so make it bold and merge columns
                            label.setTypeface(null, Typeface.BOLD);
                            
                            // If it's not an empty label and not , add some top and bottom margins
                            if (!df.getLabel().equals(""))
                            {
                                if (atLeastOneCompleteRow(dialog.getFieldsList()))
                                {
                                    label.setLayoutParams(lpSpanWithMargins); 
                                }
                                else
                                {
                                    label.setLayoutParams(lpWithTopBottomMargins); 
                                }
                            }
                        }
                    }
                    // Regular row with label and constant fields
                    else 
                    {
                        TextView label = getLabel(df, constant);
                        tableRow.addView(label, lp);
                        
                        // Multi-choice constant
                        if (constant.getClassType().equals("bits"))
                        {
                            Spinner spin = buildMultiValuesConstantField(dialog.getName(), df, constant);
                            
                            tableRow.addView(spin);
                        }
                        // Single value constant
                        else
                        {
                            EditText edit = buildSingleValueConstantField(dialog.getName(), df, constant);
    
                            tableRow.addView(edit);
                        }
                    }
                }
                
                panelLayout.addView(tableRow);
            }           
        }        
        
        // Wrap panel layout into a relative layout so it can be used as parent
        RelativeLayout containerPanelLayout = wrapTableLayoutIntoRelativeLayout(panelLayout, dialog.getName());        
        addPanel(parentLayout, containerPanelLayout, orientation, dialog.getName(), parentDialog.getAxis(), previousDialogPanelLayout); 
      
        RelativeLayout sameDialogPreviousLayoutPanel = null;
        
        // If we added at least one row to the previous layout (AKA there is at least one field)
        if (dialog.getFieldsList().size() > 0)
        {
            sameDialogPreviousLayoutPanel = containerPanelLayout;
        }        
        // When we are in a panel, the parent layout is not the R.id.content layout but the parent panel layout
        else if (isPanel)
        {
            parentLayout = containerPanelLayout;
        }
        
        // For each dialog panel, add a layout to the dialog
        for (DialogPanel dp : dialog.getPanelsList())
        {
            MSDialog dialogPanel = ecu.getDialogByName(dp.getName());
            
            if (dialogPanel != null)
            {
                sameDialogPreviousLayoutPanel = drawDialogFields(parentLayout, dialogPanel, dialog, true, dp.getOrientation(), sameDialogPreviousLayoutPanel);
            }


        }
        
        return containerPanelLayout;
    }
    
    /**
     */
    private void clickCmdButton(String controllerCommandName)
    {
        String command = ecu.getControllerCommands().get(controllerCommandName);
        command = MSUtilsShared.HexStringToBytes(command);

        byte[] byteCommand = MSUtils.INSTANCE.commandStringtoByteArray(command);

        InjectedCommand writeToRAM = new InjectedCommand(byteCommand, 300, true, Megasquirt.CONTROLLER_COMMAND);
        ecu.injectCommand(writeToRAM);
    }
    

    /** 
     * Build a seek bar used to choose between MAP/TPS based accel enrichement
     * 
     * @return The relative layout containing the seek bar
     */
    private RelativeLayout getAccelSeekBar()
    {
        RelativeLayout seekBarLayout = new RelativeLayout(getContext());

        SeekBar sb = new SeekBar(getContext());
        sb.setMax(100);
        sb.setProgress(50);
        sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
        {
            @Override
            public void onProgressChanged(SeekBar v, int progress, boolean fromUser)
            {
                if (fromUser)
                {
                
                }
            }
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });       
        
        seekBarLayout.addView(sb);
        
        return seekBarLayout;
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
        
        TextView label = new TextView(getContext());
        label.setText(labelText);
        
        // If the first character of the label is a #, we need to highlight that label
        // It means it is used as a section separator
        if (labelText.length() > 0 && labelText.substring(0,1).equals("#"))
        {
            label.setText(" " + label.getText().toString().substring(1)); // Replace the # by a space
            label.setBackgroundColor(Color.rgb(110, 110, 110));
            label.setPadding(2, 8, 2, 8);
        }
        
        label.setLayoutParams(lpWithMargins);
        
        return label;
    }
    
    /**
     * Build an EditText for displaying single value constant
     * 
     * @param dialogName Name of the dialog the field is included in
     * @param df The dialog field to build the display for
     * @param constant The constant associated with the dialog field
     * 
     * @return The EditText that can be displayed
     */
    private EditText buildSingleValueConstantField(String dialogName, DialogField df, Constant constant)
    {
        double constantValue = MSUtils.INSTANCE.roundDouble((ecu.getField(df.getName()) + constant.getTranslate()) * constant.getScale(), constant.getDigits());
        String displayedValue = "";
        
        if (constant.getDigits() == 0)
        {
            displayedValue = String.valueOf((int) constantValue);
        }
        else
        {
            displayedValue = String.valueOf(constantValue);
        }
        
        EditText edit = new EditText(getContext());
        edit.setText(displayedValue);
        edit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edit.setSingleLine(true);
        edit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        edit.setPadding(8, 5, 8, 5);
        edit.setTag(df.getName());
        edit.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
        edit.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
            {
                Constant constant1 = ecu.getConstantByName(v.getTag().toString());
                DialogHelper.verifyOutOfBoundValue(getContext(), constant1, (EditText) v);
            }
        });
        edit.addTextChangedListener(new TextWatcher()
        {
            /**
             * Set the constant to modified when value is changed
             *
             */
            @Override
            public void afterTextChanged(Editable s)
            {
                EditText edit = (EditText) getCurrentFocus();
                String constantName = edit.getTag().toString();
                
                // Constant has been modified and will need to be burn to ECU
                Constant constant = ecu.getConstantByName(constantName);
                constant.setModified(true);
                
                int value = 0;
                try
                {
                    value = (int) Math.round(Double.parseDouble(edit.getText().toString()) / constant.getScale() - constant.getTranslate());
                }
                catch (NumberFormatException e){}
                
                // Update ecu field with new value
                ecu.setField(constantName, value);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        

        return edit;
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
     *  Build a Spinner for displaying multi values constant
     *
     * @param dialogName Name of the dialog the field is included in
     * @param df The dialog field to build the display for
     * @param constant The constant associated with the dialog field
     * @return The Spinner that can be displayed
     */    
    private Spinner buildMultiValuesConstantField(String dialogName, DialogField df, Constant constant)
    {
        Spinner spin = new Spinner(getContext());
        

        final List<MultiValuesSpinnerData> spinnerData = new ArrayList<>();
        
        int selectedValue;
        
        // Special case for custom constant build at runtime
        if (df.getName().equals("MSLogger_nSquirts"))
        {            
            selectedValue = ecu.getCylindersCount() / ecu.getDivider() - 1;
        }
        else
        {
            selectedValue = (int) ecu.getField(df.getName());
        }
        
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
                spinnerData.add(new MultiValuesSpinnerData(i, value));
            }
            
            /*
             *  When we reach the currently selected valid, we need to keep track of how many
             *  invalid values there was before that, because those won't be displayed in the
             *  spinner and we need to know which index to select
             */
            if (selectedValue == i)
            {
                selectedIndex = i - invalidCount;
            }
        }
 
        ArrayAdapter<MultiValuesSpinnerData> spinAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, spinnerData);
        
        // Specify the layout to use when the list of choices appears
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spin.setAdapter(spinAdapter);
        spin.setSelection(selectedIndex);
        spin.setTag(df.getName());

        spin.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            boolean ignoreEvent = true;
            
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                // First onItemSelected event of the spinner come from populating it, ignore it!
                if (ignoreEvent)
                {
                    ignoreEvent = false;
                }
                else 
                {
                    String constantName = parentView.getTag().toString();
                    
                    int value = spinnerData.get(position).getId();
                    
                    // Special case for this constant which should do some extra validation
                    if (constantName.equals("MSLogger_nSquirts"))
                    {
                        int nCylinders = ecu.getCylindersCount();
                        
                        // nCylinders should divide by value without remainder
                        if (nCylinders % value > 0)
                        {
                            showInvalidNumberOfSquirts(nCylinders + " cylinders is not valid with " + value + " squirts (Number of cylinders / number of squirts should divide without remainder)");
                        }
                        // If injector staging is alternating
                        else if (ecu.getInjectorStating() == 1 && !(nCylinders / value < nCylinders && nCylinders / (value * 2) == 0))
                        {
                            showInvalidNumberOfSquirts("Cannot alternate this Squirts per engine cycle with this number of cylinders.");
                        }
                    }
                    else
                    {
                        // Value changed, update field in ECU class
                        if (ecu.getField(constantName) != value)
                        {
                            // Constant has been modified and will need to be burn to ECU
                            Constant constant = ecu.getConstantByName(constantName);
                            constant.setModified(true);
                            
                            // Update ecu field with new value
                            ecu.setField(constantName, value); 
                            
                            // Re-evaluate the expressions with the data updated
                            //ecu.setUserDefinedVisibilityFlags();
        
                            // Refresh the UI
                            }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView){}
        });
        
        return spin;
    }
    
    private TableRow showPanelLabel(String title, List<DialogField> fields)
    {        
        TableRow tableRow = new TableRow(getContext());
        
        if (!title.equals(""))
        {
            LayoutParams lpSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            
            if (atLeastOneCompleteRow(fields))
            {
                lpSpan.span = 2;
            }
            
            TextView label = new TextView(getContext());
            label.setText(title);
            label.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
            label.setPadding(0, 0, 0, 10);
            label.setLayoutParams(lpSpan);

            tableRow.addView(label);
        }
        
        return tableRow;
    }
    
    /**
     * Display an alert dialog whenever the number of squirts is invalid
     * 
     * @param message The error message to display
     */
    private void showInvalidNumberOfSquirts(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Invalid number of squirts per engine cycle")
                .setCancelable(true)
                .setPositiveButton("OK", (dialog, id) -> {});
        
        AlertDialog alert = builder.create();
        alert.show();
    }
    
    /**
     * Show the constant doesn't exists alert dialog
     * 
     * @param constantName The name of the constant that doesn't exists
     */
    private void showConstantDoesntExists(String constantName)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Uh oh, It looks like constant \"" + constantName + "\" is missing!")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Missing constant")
                .setCancelable(true)
                .setPositiveButton("OK", (dialog, id) -> {});
        
        AlertDialog alert = builder.create();
        alert.show();
    }
        


    /**
     * Triggered when one of the two bottoms button are clicked ("Burn" and "Cancel")
     * 
     * @param v The view that was clicked on
     */
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
if (which == R.id.close)
        {

            cancel();
        }
    }
    

    /**
     * Interface used to send the data back to the dialog's parent
     */
    public interface OnEditDialogResult
    {
       /** @noinspection unused*/
       void finish(boolean isPowerCycleRequired);
    }
}