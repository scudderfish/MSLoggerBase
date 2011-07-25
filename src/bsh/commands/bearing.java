package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;

public class bearing
{
    public static double invoke(Interpreter env, CallStack callstack)
    {
        return LocationController.INSTANCE.getCurrentBearing();
    }
}
