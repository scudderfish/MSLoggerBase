package bsh.commands;

import uk.org.smithfamily.mslogger.parser.MsDatabase;
import bsh.CallStack;
import bsh.Interpreter;

public class tempCvt
{
    public static double invoke(Interpreter env, CallStack callstack, double value)
    {
        double temp = MsDatabase.INSTANCE.tempFromDb(value);

        return temp;
    }
}
