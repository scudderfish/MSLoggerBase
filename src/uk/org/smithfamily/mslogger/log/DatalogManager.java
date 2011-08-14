package uk.org.smithfamily.mslogger.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

import android.text.format.DateFormat;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;

public enum DatalogManager
{
    INSTANCE;

    PrintWriter writer = null;
    
    public void write(String logRow)
    {
        if(writer == null)
        {
            String fileName = DateFormat.format("yyyyMMddkkmmss", new Date()).toString() + ".msl";

            File logFile = new File(ApplicationSettings.INSTANCE.getDataDir(),fileName);
            try
            {
                writer = new PrintWriter(logFile);
                Megasquirt ecuDefinition = ApplicationSettings.INSTANCE.getEcuDefinition();
                String signature = ecuDefinition.getSignature();
                writer.println("\""+signature+"\"");
                writer.println(ecuDefinition.getLogHeader());
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        writer.println(logRow);
    }
    public void stopLog()
    {
        writer.close();
        writer = null;
    }
    
}
