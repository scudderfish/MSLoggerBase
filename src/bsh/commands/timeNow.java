package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;

public class timeNow
{
    private static long startTicks = System.currentTimeMillis();
    public static int invoke(Interpreter env, CallStack callstack)
    {
        return (int) ((System.currentTimeMillis() - startTicks)/1000);
    }
}
