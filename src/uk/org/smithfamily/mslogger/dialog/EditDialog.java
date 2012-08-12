package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.DialogField;
import uk.org.smithfamily.mslogger.ecuDef.DialogPanel;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class EditDialog extends Dialog implements android.view.View.OnClickListener
{
    private MSDialog dialog;
    private LinearLayout content;
    private Megasquirt ecu;
    
    /**
     * Constructor for dialog which set the current dialog and ECU object
     * 
     * @param context
     * @param dialog
     */
    public EditDialog(Context context, MSDialog dialog)
    {
        super(context);
        
        this.ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        this.dialog = dialog;
    }
    
    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.editdialog);
        
        content = (LinearLayout) findViewById(R.id.content);
        
        setTitle(dialog.getLabel());
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(this);
        
        drawDialogFields(dialog, false);
        
        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    
    /**
     * Recursive function that will draw fields and panels of a dialog.
     * It's called recursively until all fields and panels were drawn.
     * 
     * @param dialog
     * @param isPanel
     */
    private void drawDialogFields(MSDialog dialog, boolean isPanel)
    {
        TableLayout tl = new TableLayout(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        
        LayoutParams lpSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lpSpan.span = 2;
        
        content.addView(tl);
        
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
                tableRow = new TableRow(getContext());
                
                String labelText = df.getLabel();
                
                // Add units to label
                if (constant != null && !constant.getUnits().equals(""))
                {
                    labelText += " (" + constant.getUnits() + ")";
                }
                
                TextView label = new TextView(getContext());
                label.setText(labelText);
                tableRow.addView(label);
                
                // For empty label or empty field name, we just insert an empty text view as second column of the row
                if (df.getLabel().equals("") || df.getName().equals("null"))
                {
                    // No second column so label is used to separate so make it bold and merge columns
                    label.setTypeface(null, Typeface.BOLD);
                    label.setLayoutParams(lpSpan);
                }
                else 
                {
                    tableRow.setLayoutParams(lp);
                    
                    // Multi-choice constant
                    if (constant.getClassType().equals("bits"))
                    {
                        Spinner spin = new Spinner(getContext());
                        
                        // Field is ready only or disabled
                        if (df.isDisplayOnly() || !ecu.getVisibilityFlagsByName(df.getExpression()))
                        {
                            spin.setEnabled(false);
                        }
                        
                        /*
                        // Remove INVALID from values
                        List<String> valuesWithoutInvalid = new ArrayList<String>();
                        for (int i = 0; i < constant.getValues().length; i++)
                        {
                            String value = constant.getValues()[i];
                            if (!value.equals("INVALID"))
                            {
                                valuesWithoutInvalid.add(value);
                            }
                        }
                        
                        valuesWithoutInvalid.toArray(new String[valuesWithoutInvalid.size()])
                        */
                        
                        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, constant.getValues());
                        
                        // Specify the layout to use when the list of choices appears
                        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        
                        spin.setAdapter(spinAdapter);
                        
                        int selectedValue = (int) ecu.getField(df.getName());                        
                        spin.setSelection(selectedValue);
                        spin.setTag(df.getName());
                        
                        spin.setOnItemSelectedListener(new OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
                            {
                                refreshFieldsVisibility();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView){}
                        });
                        
                        tableRow.addView(spin);
                    }
                    // Single value constant
                    else
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

                        EditText edit = new EditText(getContext());
                        edit.setText(displayedValue);
                        edit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        edit.setSingleLine(true);
                        edit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                        edit.setPadding(8, 5, 8, 5);
                        edit.setTag(df.getName());
                        
                        // Field is ready only or disabled
                        if (df.isDisplayOnly() || !ecu.getVisibilityFlagsByName(df.getExpression()))
                        {
                            edit.setEnabled(false);
                        }
                        
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
              drawDialogFields(dialogPanel, true);
            }
            else
            {
              // Maybe it's a curve panel
              //
              // CurveEditor curvePanel = ecu.getCurveEditorByName(dp.getName());
              // if (curvePanel)
              // {
              // 
              // }
              //
            }
        }
    }
    
    /**
     * Add a label at the top of a panel
     * 
     * @param title
     * @param tl
     */
    private void showPanelLabel(String title, TableLayout tl)
    {
        LayoutParams lpSpan = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lpSpan.span = 2;     
        
        TableRow tableRow = new TableRow(getContext());
        tableRow.setLayoutParams(lpSpan);
        
        TextView label = new TextView(getContext());
        label.setText(title);
        label.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
     * When value are changed, it's possible dialog fields change state
     * so we need to refresh fields visibility and reply them
     */
    private void refreshFieldsVisibility()
    {
       
    }
    
    /**
     * Triggered when the two bottoms button are clicked ("Burn" and "Cancel") 
     * 
     * @param v The view that was clicked on
     */
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
