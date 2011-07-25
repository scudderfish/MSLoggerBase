package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;

public class speed
{
    public static double invoke(Interpreter env, CallStack callstack)
    {
        return LocationController.INSTANCE.getCurrentSpeed();
    }
}
