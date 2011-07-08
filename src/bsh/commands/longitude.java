package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;

public class longitude
{
    public static double invoke(Interpreter env, CallStack callstack, long time)
    {
        return LocationManager.getInstance().get(time).getLon();
    }
}
