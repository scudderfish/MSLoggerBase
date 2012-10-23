package uk.org.smithfamily.mslogger.activity;

import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.dialog.DialogHelper;
import uk.org.smithfamily.mslogger.dialog.EditCurveDialog;
import uk.org.smithfamily.mslogger.dialog.EditDialog;
import uk.org.smithfamily.mslogger.dialog.EditDialog.OnEditDialogResult;
import uk.org.smithfamily.mslogger.dialog.EditTableDialog;
import uk.org.smithfamily.mslogger.ecuDef.CurveEditor;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.Menu;
import uk.org.smithfamily.mslogger.ecuDef.SubMenu;
import uk.org.smithfamily.mslogger.ecuDef.TableEditor;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class TuningActivity extends Activity
{
    private LinearLayout firstColumn;
    private LinearLayout secondColumn;
    private final String[] EXLUDED_MENUS = new String[] {"Communications","Help"};
    
    private int currentIndexMenu = 0;
    
    private Megasquirt ecu;
    private List<Menu> menus;

    private Dialog currentDialog;

    private BroadcastReceiver updateReceiver = new Reciever();
    private boolean registered;
    
    private boolean powerCycleRequired = false;
    
    /**
     * 
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.tuning);
        
        setTitle(R.string.tuning);
        
        firstColumn = (LinearLayout) findViewById(R.id.firstColumn);
        secondColumn = (LinearLayout) findViewById(R.id.secondColumn);
        
        ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        menus = ecu.getMenusForDialog("main");
        
        // Make the power cycle required text view persistent when activity get destroy
        // ex: device orientation change
        if (savedInstanceState != null && savedInstanceState.getBoolean("power_cycle_required")) 
        {
            powerCycleRequired = true;
            
            TextView requiredPowerCycle = (TextView) findViewById(R.id.requiredPowerCycle);
            requiredPowerCycle.setVisibility(View.VISIBLE);
        }
        
        registerMessages();
        
        drawTuningButtons();
    }
    
    /**
     * Save the power cycle required flag when the device is rotated
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        
        outState.putBoolean("power_cycle_required", powerCycleRequired);
    }
    
    /**
     * Register the receiver with the message to receive from the Megasquirt connection
     */
    private void registerMessages()
    {
        IntentFilter disconnectedFilter = new IntentFilter(Megasquirt.DISCONNECTED);
        registerReceiver(updateReceiver, disconnectedFilter);

        registered = true;
    }
    
    /**
     * Unregister receiver
     */
    private void deRegisterMessages()
    {
        if (registered)
        {
            unregisterReceiver(updateReceiver);
        }
    }
    
    /**
     * Unregister the events
     */
    @Override
    protected void onDestroy()
    {        
        deRegisterMessages();

        super.onDestroy();
    }
    
    /**
     * Close the currently open dialog so the activity doesn't blow up with an "Activity has leaked window ..."
     */
    @Override
    public void onPause()
    {
        super.onPause();
          
        if (currentDialog != null)
        {
            currentDialog.dismiss();
        }
    }
    
    /**
     * Draw the main section of the tuning menus
     */
    private void drawTuningButtons()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        final List<Menu> menus = ecu.getMenusForDialog("main");
        
        int nbButtons = 0;
        
        for (int i = 0; i < menus.size(); i++)
        {            
            Menu menu = menus.get(i);
            String menuLabel = menu.getLabel();
            
            boolean found = false;
            for (int j = 0; j < EXLUDED_MENUS.length; j++)
            {
                if (EXLUDED_MENUS[j].equals(menuLabel))
                {
                    found = true;
                    break;
                }
            }
            
            if (!found) 
            {
                Button b = new Button(this);
                b.setText(menuLabel);
                b.setTag(i);
                registerForContextMenu(b);
                
                b.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {    
                        v.showContextMenu();
                    }
                });
                
                if (nbButtons % 2 == 0)
                {
                    firstColumn.addView(b);
                }
                else
                {
                    secondColumn.addView(b);
                }
                
                nbButtons++;
            }
        }
    }
    
    /**
     * 
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {  
        super.onCreateContextMenu(menu, v, menuInfo);  
        
        Button b = (Button) v;
        int indexMenu = Integer.parseInt(b.getTag().toString());
        
        Menu menuDef = menus.get(indexMenu);
        List<SubMenu> subMenus = menuDef.getSubMenus();
        
        menu.setHeaderTitle(menuDef.getLabel());  
        
        currentIndexMenu = indexMenu;
        
        // Refresh menu visibility flags
        ecu.setMenuVisibilityFlags();
        
        for (int i = 0; i < subMenus.size(); i++)
        {
            SubMenu subMenu = subMenus.get(i);
            
            // Don't display separator
            if (!subMenu.getName().equals("std_separator"))
            {
                menu.add(0, i, 0, subMenu.getLabel());
                menu.getItem(menu.size() - 1).setEnabled(ecu.getMenuVisibilityFlagsByName(subMenu.getName()));
            }
        }
    }  
    
    /**
     * 
     * @param item
     */
    @Override      
    public boolean onContextItemSelected(MenuItem item) { 
        // Correspond to sub menu index
        int itemId = item.getItemId();
        
        SubMenu subMenu = menus.get(currentIndexMenu).getSubMenuAt(itemId);
        
        String name = subMenu.getName();
        
        TableEditor table = ecu.getTableEditorByName(name);
        
        // It's a table!
        if (table != null)
        {
            EditTableDialog tableDialog = new EditTableDialog(TuningActivity.this, table);
            tableDialog.show();
            
            this.currentDialog = tableDialog;
        }
        else
        {
            // Refresh user defined flags
            ecu.setUserDefinedVisibilityFlags();
            
            CurveEditor curve = ecu.getCurveEditorByName(name);

            // It's a curve!
            if (curve != null)
            {
                EditCurveDialog curveDialog = new EditCurveDialog(TuningActivity.this, curve);
                curveDialog.show();
                
                this.currentDialog = curveDialog;
            }
            else
            {
                MSDialog dialog = ecu.getDialogByName(name);
                
                // It's a dialog!
                if (dialog != null)
                {
                    EditDialog editDialog = new EditDialog(TuningActivity.this, dialog);
                    editDialog.setDialogResult(new OnEditDialogResult()
                    {
                        public void finish(boolean isPowerCycle)
                        {
                            
                            // Show power cycle required if needed
                            if (isPowerCycle)
                            {
                                TextView requiredPowerCycle = (TextView) findViewById(R.id.requiredPowerCycle);
                                requiredPowerCycle.setVisibility(View.VISIBLE);
                                
                                powerCycleRequired = true;;
                            }
                        }
                    });
                    editDialog.show();

                    this.currentDialog = editDialog;
                }
                else
                {
                    // Not a regular dialog, but maybe it's an std_* dialog
                    dialog = DialogHelper.getStdDialog(name);
                    
                    if (dialog != null)
                    {
                        EditDialog editDialog = new EditDialog(TuningActivity.this, dialog);
                        editDialog.show();

                        this.currentDialog = editDialog;
                    }
                    
                    else
                    {
                        Dialog customDialog = DialogHelper.getStdCustomDialog(TuningActivity.this, name);
                        
                        if (customDialog != null)
                        {
                            customDialog.show();
                            
                            this.currentDialog = customDialog;
                        }
                        // No idea what it is, we're screwed!
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(TuningActivity.this);
                            builder.setMessage("Sorry, \"" + name + "\" is not currently supported.")
                                    .setIcon(android.R.drawable.ic_dialog_info)
                                    .setTitle("Unsupported")
                                    .setCancelable(true)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int id)
                                        {}
                                    });
                            
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }
                }
            }
        }
        
        
        return true;
    }
    
    /**
     * Receiver that get events from other activities about Megasquirt status and activities
     */
    private final class Reciever extends BroadcastReceiver
    {
        /**
         * When an event is received
         * 
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            if (action.equals(Megasquirt.DISCONNECTED))
            {
                powerCycleRequired = false;
                
                // Make sure power cycle required text view is gone when MegaSquirt get disconnected
                TextView requiredPowerCycle = (TextView) findViewById(R.id.requiredPowerCycle);
                requiredPowerCycle.setVisibility(View.GONE);
            } 
        }
    }
    
}
