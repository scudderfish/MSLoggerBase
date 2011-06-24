package uk.org.smithfamily.msdisp.parser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.res.AssetManager;

public class INIController
{
    public static Charset charset = Charset.forName("UTF-8");
    public static CharsetDecoder decoder = charset.newDecoder();
    
    private static final INIController instance = new INIController();
    private Map<String, String>        inis     = new HashMap<String, String>();

    private String[] probeCommands={"S","Q"};
    
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
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initialised = true;
    }

    private void summarizeIni(String path, AssetManager assetManager) throws IOException
    {
        BufferedReader rd = new BufferedReader(new InputStreamReader(assetManager.open(path)));

        String line;
        String pattern = "\\s*signature\\s*=\\s*\"(.*)\"";
        Pattern sig = Pattern.compile(pattern);
        int lineCount = 0;

        while (((line = rd.readLine()) != null) && lineCount++ < 30)
        {
            Matcher m = sig.matcher(line);
            if (m.find())
            {
                String actualSig = m.group(1);
                inis.put(actualSig, path);
                break;
            }
        }
    }

    public String probe(String defaultResult)
    {
        String result = defaultResult;
        MsComm comm = CommsFactory.getInstance().getComInstance();
        if(!comm.openConnection())
        	return null;
        
        int nBytes = 1024;
        boolean found = false;
        String response = null;
        for(String probeCommand : probeCommands)
        {
            for (int x=0; x < 3 && !found ; x++)
            {
                comm.write(probeCommand.getBytes());
                
                response = comm.read(nBytes);
                
                
                if(inis.containsKey(response))
                {
                    found = true;
                    result = inis.get(response);
                }
            }
        }
    
    
        return result;
    
    }
}
