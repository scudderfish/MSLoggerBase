package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;

public class timeNow
{
    private static long startTicks = System.currentTimeMillis();
    public static double invoke(Interpreter env, CallStack callstack)
    {
        return (double) ((System.currentTimeMillis() - startTicks)/1000.0);
    }
}
