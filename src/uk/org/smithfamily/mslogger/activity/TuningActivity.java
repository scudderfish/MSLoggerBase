package uk.org.smithfamily.mslogger.activity;

import java.util.List;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.dialog.EditCurveDialog;
import uk.org.smithfamily.mslogger.dialog.EditDialog;
import uk.org.smithfamily.mslogger.dialog.EditTableDialog;
import uk.org.smithfamily.mslogger.ecuDef.CurveEditor;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import uk.org.smithfamily.mslogger.ecuDef.MenuDefinition;
import uk.org.smithfamily.mslogger.ecuDef.SubMenuDefinition;
import uk.org.smithfamily.mslogger.ecuDef.TableEditor;
import uk.org.smithfamily.mslogger.log.DebugLogManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 *
 */
public class TuningActivity extends Activity
{
    private LinearLayout firstColumn;
    private LinearLayout secondColumn;
    private final String[] EXLUDED_MENUS = new String[] {"Tools","Data Logging","Communications","Help"};
    
    private int currentIndexMenu = 0;
    
    private Megasquirt ecu;
    private List<MenuDefinition> menus;
    
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
        
        drawTuningButtons();
    }
    
    /**
     * Draw the main section of the tuning menus
     */
    private void drawTuningButtons()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        final List<MenuDefinition> menus = ecu.getMenusForDialog("main");
        
        int nbButtons = 0;
        
        for (int i = 0; i < menus.size(); i++)
        {            
            MenuDefinition menu = menus.get(i);
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

        
        MenuDefinition menuDef = menus.get(indexMenu);
        List<SubMenuDefinition> subMenus = menuDef.getSubMenus();
        
        menu.setHeaderTitle(menuDef.getLabel());  
        
        currentIndexMenu = indexMenu;
        
        for (int i = 0; i < subMenus.size(); i++)
        {
            SubMenuDefinition subMenu = subMenus.get(i);     
            
            // Don't display separator
            if (!subMenu.getName().equals("std_separator"))
            {
                menu.add(0, i, 0, subMenu.getLabel());
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
        
        SubMenuDefinition subMenu = menus.get(currentIndexMenu).getSubMenuAt(itemId);
        
        String name = subMenu.getName();
        
        TableEditor table = ecu.getTableEditorByName(name);
        
        // It's a table!
        if (table != null)
        {
            EditTableDialog tableDialog = new EditTableDialog(TuningActivity.this, table);
            tableDialog.show();
        }
        else
        {
            CurveEditor curve = ecu.getCurveEditorByName(name);
            
            // It's a curve!
            if (curve != null)
            {
                EditCurveDialog curveDialog = new EditCurveDialog(TuningActivity.this, curve);
                curveDialog.show();
            }
            else {
                MSDialog dialog = ecu.getDialogByName(name);
                
                // It's a dialog!
                if (dialog != null)
                {
                    EditDialog editDialog = new EditDialog(TuningActivity.this, dialog);
                    editDialog.show();
                }
                // No idea what it is, we're screwed!
                else
                {
                    DebugLogManager.INSTANCE.log("Unsupported tuning object: " + name, Log.ERROR);
                }
            }
        }
        
        
        return true;  
    }  
}
