package uk.org.smithfamily.msdisp.parser;

import java.io.*;

import android.content.Context;
import android.content.res.AssetManager;

public class INIController
{
    private static final INIController instance = new INIController();

    public static final INIController getInstance()
    {
        return instance;
    }

    private INIController()
    {
    }

    private boolean initialised = false;

    public void initialise(Context c)
    {
        if (initialised)
            return;

        AssetManager assetManager = c.getResources().getAssets();
        try
        {
            String[] iniFilenames = assetManager.list("ecuDef");

            for (String iniFilename : iniFilenames)
            {
                String path = "ecuDef" + File.separator + iniFilename;

                summarizeIni(path, assetManager);
            }
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initialised = true;
    }

    private void summarizeIni(String path, AssetManager assetManager)
            throws IOException
    {
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                assetManager.open(path)));

        String line;
        
        while((line=rd.readLine())!= null)
        {
            
        }
    }

}
