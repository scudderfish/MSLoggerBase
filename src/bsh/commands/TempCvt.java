package bsh.commands;

import uk.org.smithfamily.msdisp.parser.MsDatabase;
import bsh.CallStack;
import bsh.Interpreter;

public class TempCvt
{
    public static double invoke(Interpreter env, CallStack callstack, double value)
    {
        double temp = MsDatabase.getInstance().tempFromDb(value);

        return temp;
    }
}
