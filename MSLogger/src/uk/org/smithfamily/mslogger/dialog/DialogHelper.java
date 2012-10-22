package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.CurveEditor;
import uk.org.smithfamily.mslogger.ecuDef.DialogField;
import uk.org.smithfamily.mslogger.ecuDef.DialogPanel;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * Helper class used for dialogs
 */
public class DialogHelper
{
    /**
     * This function verify if the user entered value is in range for the specifing constant
     * Show an alert dialog if it's not with indication to the user on how to fix it
     *
     * @param context The context the function was called
     * @param constant The constant to look for min and max value
     * @param cell The cell the user was typing in
     */
    public static void verifyOutOfBoundValue(Context context, Constant constant, final EditText cell)
    {
        double currentValue = Double.parseDouble(cell.getText().toString());
        
        double minValue = Double.parseDouble(constant.getLow());
        double maxValue = Double.parseDouble(constant.getHigh());
        
        String message = "";
        
        if (currentValue < minValue)
        {
            message = "Constant " + constant.getName() + " value is lower then the minimum allowed (" + currentValue + " < " + minValue + ")";
        }
        
        if (currentValue > maxValue)
        {
            message = "Constant " + constant.getName() + " value is higher then the maximum allowed (" + currentValue + " > " + maxValue + ")";
        }
        
        if (!message.equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Value is out of bound")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            cell.requestFocus();
                        }
                    });
            
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
    
    /**
     * This function build the custom std_* dialog that aren't defined in the ini
     * 
     * @param context The context the dialog will be used in
     * @param dialogName The name of the dialog to build
     * 
     * @return The dialog 
     */
    public static MSDialog getStdDialog(Context context, String dialogName)
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        // We already build that dialog, just return it
        if (ecu.getDialogByName(dialogName) != null)
        {
            return ecu.getDialogByName(dialogName);
        }
        
        // Accel Enrichment Wizard
        if (dialogName.equals("std_accel"))
        {
            return buildStdAccel();
        }
        else if (dialogName.equals("std_injection"))
        {
            return buildStdInjection();
        }
        // Warmup Enrichment
        else if (dialogName.equals("std_warmup"))
        {
            return buildStdWarmup();
        }
        // Real-time Display
        else if (dialogName.equals("std_realtime"))
        {
            return buildStdRealtime();
        }
        // Constants
        else if (dialogName.equals("std_constants"))
        {
            return buildStdConstants();
        }
        // MS3 Real-time Clock
        else if (dialogName.equals("std_ms3Rtc"))
        {
            return buildStdRtc();
        }
        // MS3 SDCard Console
        else if (dialogName.equals("std_ms3SdConsole"))
        {
            return buildStdSdConsole();
        }
        // Trigger wizard
        else if (dialogName.equals("std_trigwiz"))
        {
            return buildStdTriggerWizard();
        }
        
        return null;
    }
    
    /**
     * Helper function that will build the std_accel dialog
     * 
     * @return An instance of MSDialog with all fields in them
     */
    private static MSDialog buildStdAccel()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        // MAP curve
        CurveEditor curve = new CurveEditor("std_accel_mae_curve", "MAP Based AE");
        ecu.addCurve(curve);
        
        curve.setxLabel("Rate");
        curve.setyLabel("PW Adder");
        curve.setxAxis(new double[] {0, 288, 57.6});
        curve.setyAxis(new double[] {0, 14.4, 4.8});
        
        String xConstant = "";
        String yConstant = "";
        
        // MS2, MS3
        if (ecu.isConstantExists("maeRates"))
        {
            xConstant = "maeRates";
            yConstant = "maeBins";
        }
        // MS1
        else
        {
            xConstant = "maeRates4";
            yConstant = "maeBins4";
        }
        
        curve.setXBins(ecu.getVector(xConstant), xConstant, 0, false);
        curve.setYBins(ecu.getVector(yConstant), yConstant);
        
        // TPS Curve
        curve = new CurveEditor("std_accel_tae_curve", "TPS Based AE");
        ecu.addCurve(curve);
        
        curve.setxLabel("Rate");
        curve.setyLabel("PW Adder");
        curve.setxAxis(new double[] {0, 1848, 369.6});
        curve.setyAxis(new double[] {0, 12, 4});
        
        // MS2, MS3
        if (ecu.isConstantExists("taeBins"))
        {
            xConstant = "taeRates";
            yConstant = "taeBins";
        }
        // MS1
        else
        {
            xConstant = "taeRates4";
            yConstant = "taeBins4";
        }
        
        curve.setXBins(ecu.getVector(xConstant), xConstant, 0, false);
        curve.setYBins(ecu.getVector(yConstant), yConstant);
        
        // std_accel dialog
        MSDialog dialogAccel = new MSDialog("std_accel", "Accel Enrichment Wizard", "yAxis");
        ecu.addDialog(dialogAccel);
        
        dialogAccel.addPanel(new DialogPanel("std_accel_north", ""));
        //dialogAccel.addPanel(new DialogPanel("std_accel_seek_bar_panel", ""));
        dialogAccel.addPanel(new DialogPanel("std_accel_south", ""));
        
        // North panel with the two curves
        MSDialog dialogNorth = new MSDialog("std_accel_north","","");
        ecu.addDialog(dialogNorth);
        
        dialogNorth.addPanel(new DialogPanel("std_accel_mae_curve", "West"));
        dialogNorth.addPanel(new DialogPanel("std_accel_tae_curve", "East"));
        
        // South panel with the two columns with all the constants for TPS and MAP
        MSDialog dialogSouth = new MSDialog("std_accel_south", "", "");
        ecu.addDialog(dialogSouth);
        
        dialogSouth.addPanel(new DialogPanel("std_accel_map", "West"));
        dialogSouth.addPanel(new DialogPanel("std_accel_tps", "East"));
        
        // Seek bar section
        MSDialog dialogCenterSeekBar = new MSDialog("std_accel_seek_bar_panel", "", "");
        ecu.addDialog(dialogCenterSeekBar);
        
        dialogCenterSeekBar.addField(new DialogField("std_accel_seek_bar", "null", false, false, ""));

        // MAP section
        MSDialog dialogMap = new MSDialog("std_accel_map","","null");
        ecu.addDialog(dialogMap);
        
        dialogMap.addField(new DialogField("MAPdot Threshold", "mapThresh", false, false, ""));
        dialogMap.addField(new DialogField("Accel Time", "taeTime", false, false, ""));
        
        // Not all MS version have support for taper time
        if (ecu.getConstantByName("aeTaperTime") != null)
        {
            dialogMap.addField(new DialogField("Accel Taper Time", "aeTaperTime", false, false, ""));
        }
        
        // Not all MS version have support for end pulsewidth
        if (ecu.getConstantByName("aeEndPW") != null)
        {
            dialogMap.addField(new DialogField("End Pulsewidth", "aeEndPW", false, false, ""));
        }
        
        // TPS section
        MSDialog dialogSouthTps = new MSDialog("std_accel_tps","","null");
        ecu.addDialog(dialogSouthTps);
        
        dialogSouthTps.addField(new DialogField("TPSdot Threshold", "tpsThresh", false, false, ""));
        dialogSouthTps.addField(new DialogField("Decel Fuel Amount", "tdePct", false, false, ""));
        dialogSouthTps.addField(new DialogField("Cold Accel Enrichment", "taeColdA", false, false, ""));
        dialogSouthTps.addField(new DialogField("Cold Accel Multiplier", "taeColdM", false, false, ""));
        
        return dialogAccel;
    }
    
    /**
     * 
     * @return
     */
    private static MSDialog buildStdInjection()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        MSDialog dialog = new MSDialog("std_injection", "Calculate Required Fuel", "");
        ecu.addDialog(dialog);
        
        dialog.addField(new DialogField("std_required_fuel", "null", false, false, ""));
        dialog.addField(new DialogField("Control Algorithm", "algorithm", false, false, ""));
        
        // Custom constant for number of squirts per engine cycle since this should modify
        // the divider constant but save the result as "divider = nCylinders / MSLogger_nSquirts"
        Constant constant = new Constant(1,"MSLogger_nSquirts","bits","",0,"[0:4]","",1.000000,0.000000,0,0,0,new String[] {"1","2","3","4","5","6","7","8"});
        ecu.addConstant(constant);
        
        dialog.addField(new DialogField("Squirts Per Engine Cycle", "MSLogger_nSquirts", false, false, ""));
        
        dialog.addField(new DialogField("Injector Staging", "alternate", false, false, ""));
        dialog.addField(new DialogField("Engine Stroke/Rotary", "twoStroke", false, false, ""));
        dialog.addField(new DialogField("No. Cylinders/Rotors", "nCylinders", false, false, ""));
        dialog.addField(new DialogField("Injector Port Type", "injType", false, false, ""));
        dialog.addField(new DialogField("Number of Injectors", "nInjectors", false, false, ""));
        dialog.addField(new DialogField("Engine Type", "engineType", false, false, ""));
        
        return dialog;
    }
    
    /**
     * 
     * @return
     */
    private static MSDialog buildStdWarmup()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        MSDialog dialog = new MSDialog("std_warmup", "", "");
        ecu.addDialog(dialog);
        
        CurveEditor curve = new CurveEditor("std_warmup_curve", "Warmup Wizard");
        ecu.addCurve(curve);
        
        curve.setxLabel("Coolant Temp");
        curve.setyLabel("Enrichment");
        curve.setxAxis(new double[] {-60, 200, 20});
        curve.setyAxis(new double[] {0, 240, 20});
        
        String units = "°F";
        int[] temp = { 103, 301, 500, 698, 896, 1094, 1292, 1508, 1706, 1797 };
        double scale = 0.1;
        double translate = 0;
        
        if (ecu.isSet("CELCIUS"))
        {
            units = "°C";
            scale = 0.05555;
            translate = -320;
        }
        
        Constant xConstant = new Constant(1, "MSLogger_temp", "array", "", 0, "[   10]", units, scale, translate, 0, 0, 0);
        ecu.addConstant(xConstant);
        
        curve.setXBins(temp, "MSLogger_temp", 0, false);
        
        curve.setYBins(ecu.getVector("wueBins9"), "wueBins9");
        
        dialog.addPanel(new DialogPanel("std_warmup_curve", ""));
        
        return dialog;
    }
    
    /**
     * 
     * @return
     */
    private static MSDialog buildStdConstants()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        MSDialog dialog = new MSDialog("std_constants", "", "");
        ecu.addDialog(dialog);
        
        MSDialog weastPanel = new MSDialog("std_constants_west", "", "");
        
        weastPanel.addField(new DialogField("std_required_fuel", "null", false, false, ""));
        weastPanel.addField(new DialogField("Injector Opening Time", "injOpen1", false, false, ""));
        weastPanel.addField(new DialogField("Battery Voltage Correction", "battFac1", false, false, ""));
        weastPanel.addField(new DialogField("PWM Current Limit", "injPwmP1", false, false, ""));
        weastPanel.addField(new DialogField("PWM Time", "injPwmT1", false, false, ""));
        weastPanel.addField(new DialogField("Fast Idle Threshold", "fastIdleT1", false, false, ""));
        weastPanel.addField(new DialogField("Barometric Correction", "baroCorr1", false, false, ""));
        
        MSDialog eastPanel = new MSDialog("std_constants_east", "", "");
        
        eastPanel.addField(new DialogField("Control Algorithm", "", false, false, ""));
        
        // Custom constant for number of squirts per engine cycle since this should modify 
        // the divider constant but save the result as "divider = nCylinders / MSLogger_nSquirts"
        Constant constant = new Constant(1,"MSLogger_nSquirts","bits","",0,"[0:4]","",1.000000,0.000000,0,0,0,new String[] {"1","2","3","4","5","6","7","8"});
        ecu.addConstant(constant);
        
        eastPanel.addField(new DialogField("Squirts Per Engine Cycle", "MSLogger_nSquirts", false, false, ""));
        
        eastPanel.addField(new DialogField("Injector Staging", "alternate1", false, false, ""));
        eastPanel.addField(new DialogField("Engine Stroke", "twoStroke1", false, false, ""));
        eastPanel.addField(new DialogField("Number of Cylinders", "nCylinders1", false, false, ""));
        eastPanel.addField(new DialogField("Number of Injectors", "nInjectors1", false, false, ""));
        eastPanel.addField(new DialogField("MAP Type", "mapType1", false, false, ""));
        eastPanel.addField(new DialogField("Engine Type", "engineType1", false, false, ""));
        
        dialog.addPanel(new DialogPanel("std_constants_west", "West"));
        dialog.addPanel(new DialogPanel("std_constants_east", "East"));
        
        return dialog;
    }
    
    /**
     * 
     * @return
     */
    private static MSDialog buildStdRealtime()
    {
        Megasquirt ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
        
        MSDialog dialog = new MSDialog("std_realtime", "", "");
        ecu.addDialog(dialog);
        
        return dialog;
    }
    
    /**
     * 
     * @return
     */
    private static MSDialog buildStdRtc()
    {
        MSDialog dialog = new MSDialog("std_ms3Rtc", "MS3 Real-time Clock", "");
        
        return dialog;
    }
    
    /**
     * 
     * @return
     */
    private static MSDialog buildStdSdConsole()
    {
        MSDialog dialog = new MSDialog("std_ms3SdConsole", "MS3 SD Console", "");
        
        return dialog;
    }
    
    /**
     * 
     * @return
     */
    private static MSDialog buildStdTriggerWizard()
    {
        MSDialog dialog = new MSDialog("std_trigwiz", "Trigger Wizard", "");
        
        return dialog;
    }
}
