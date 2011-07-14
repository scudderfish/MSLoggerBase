package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;

public class latitude
{
    public static double invoke(Interpreter env, CallStack callstack, long time)
    {
        return LocationManager.INSTANCE.get(time).getLat();
    }
}
