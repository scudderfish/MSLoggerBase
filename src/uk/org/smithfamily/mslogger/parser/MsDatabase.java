package uk.org.smithfamily.mslogger.parser;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Debug;

import uk.org.smithfamily.mslogger.parser.log.Datalog;
import uk.org.smithfamily.mslogger.parser.log.DebugLogManager;
import uk.org.smithfamily.mslogger.parser.log.FRDLogManager;

public class MsDatabase
{
    private static final int    N_RETRIES    = 3;
    public static final String GENERAL_MESSAGE = "uk.org.smithfamily.mslogger.parser.MsDatabase.GENERAL_MESSAGE";
    public static final String MESSAGE = "MESSAGE";
    private static MsDatabase   instance     = new MsDatabase();
    public ControllerDescriptor cDesc;

    Datalog                     log;
    MsComm                      io;
    int                         _pageNo;
    boolean                     _loaded;                        // Memory image
                                                                 // is
                                                                 // valid
                                                                 // from either
                                                                 // file
                                                                 // or
                                                                 // MS.
    boolean                     _burned;                        // MS RAM and
                                                                 // flash
                                                                 // are
                                                                 // different.
    String                      signature;
    String                      title;
    static String               settingsFile;
    boolean                     bursting;
    int                         commPortNumber;
    int                         commPortRate;
    int                         controllerResetCount;
    int                         userTimerInt;
    int                         timerInterval;
    int                         burstCommPort;
    int                         burstCommRate;

    boolean                     controllerReset;                // Reset
                                                                 // detected.
    String                      m_logFileName;

    double[]                    wwuX         = new double[10];
    private Context             context;
    static double               previousSecl = 255;

    enum thermType
    {
        Celsius, Fahrenheit
    };

    static thermType therm;

    static boolean   rawTPS;

    public static MsDatabase getInstance()
    {
        return instance;
    }

    public double tempFromDb(double t)
    {
        if (therm == thermType.Celsius)
        {
            t = (t - 32.0) * 5.0 / 9.0;
        }
        return t;

    }

    String thermlabel()
    {
        if (therm == thermType.Celsius)
            return "C";
        else
            return "F";
    }

    private MsDatabase()
    {
        log = null;
        io = CommsFactory.getInstance().getComInstance();
        cDesc = new ControllerDescriptor(io);
        _pageNo = 0;
        _loaded = false;
        _burned = true; // Until we send something out.
        bursting = false;
        controllerResetCount = 0;
        userTimerInt = 200;
        timerInterval = 200;

        commPortNumber = io.port();
        commPortRate = io.rate();
        burstCommPort = commPortNumber;
        burstCommRate = 115200;
    }

    public boolean init()
    {
        Repository.Uundefined = cDesc.varIndex(StringConstants.S_UNDEFINED);

        // This is a friggin' hack for 2.20 to allow
        // user-defined channels, but will be replaced
        // by the full expression language someday...
        Repository.UegoVoltage = cDesc.varIndex(StringConstants.S_egoVoltage);
        Repository.UveTuneLodIdx = cDesc.varIndex(StringConstants.S_veTuneLodIdx);
        Repository.UveTuneRpmIdx = cDesc.varIndex(StringConstants.S_veTuneRpmIdx);
        Repository.UveTuneValue = cDesc.varIndex(StringConstants.S_veTuneValue);

        // cDesc._userVar.add(Repository.UveTuneLodIdx, 0.0); // Defaults until the
        // tuning dialog is
        // displayed.
        // cDesc._userVar.add(Repository.UveTuneRpmIdx, 0.0);
        // cDesc._userVar.add(Repository.UveTuneValue, 0.0);

        wwuX[0] = -40.0;
        wwuX[1] = -20.0;
        wwuX[2] = 0.0;
        wwuX[3] = 20.0;
        wwuX[4] = 40.0;
        wwuX[5] = 60.0;
        wwuX[6] = 80.0;
        wwuX[7] = 100.0;
        wwuX[8] = 130.0;
        wwuX[9] = 160.0;

        boolean status = readConfig();

        return status;
    }

    public boolean load()
    {
        getVersion();
        for (int iPage = 0; iPage < cDesc.nPages(); iPage++)
        {
            if (!getConst(iPage))
                return false;
        }
        _loaded = true;
        cDesc.changed(false);
        return true;
    }

    private boolean getConst(int pageNo)
    {
        if (bursting)
            return true;

        if (pageNo == -1)
            pageNo = _pageNo;
        int nBytes = cDesc.pageSize(pageNo);
        int ofs = cDesc.pageOffset(pageNo);

        byte[] pBytes = new byte[nBytes];

        boolean getOk = false;
        for (int i = 0; i < N_RETRIES && !getOk; i++)
        {
            cDesc.flush(); // Flush the comm input.
            cDesc.sendPageReadWhole(pageNo);
            getOk = cDesc.read(pBytes, nBytes); // Don't read directly into
                                                // database, in case we don't
                                                // get data.

        }
        if (getOk)
        {
            System.arraycopy(pBytes, 0, cDesc._const, ofs, nBytes);
            // memcpy(cDesc._const + ofs, pBytes, nBytes);
            cDesc.updateConstPage(pageNo);
        }
        return getOk;
    }

    private void getVersion()
    {
        // TODO Auto-generated method stub

    }

    boolean readConfig()
    {
        boolean existed = false;

        commPortNumber = 1;
        commPortRate = 9600;

        return existed;
    }

    public boolean calculateRuntime()
    {
        Debug.startMethodTracing("calculateRuntime");
        try
        {
            long start = System.currentTimeMillis();
            boolean success = getRuntime();
            DebugLogManager.getInstance().log("getRuntime() : " + (System.currentTimeMillis() - start));

            if (success)
            {
                start = System.currentTimeMillis();
                cDesc.populateUserVars();
                DebugLogManager.getInstance().log("populateUserVars() : " + (System.currentTimeMillis() - start));

                start = System.currentTimeMillis();
                cDesc.recalc();
                DebugLogManager.getInstance().log("recalc() : " + (System.currentTimeMillis() - start));
                // uml.enable();
                // uil.enable();
                return true;
            }
            return false;
        }
        finally
        {
            Debug.stopMethodTracing();

        }
    }

    public boolean getRuntime()
    {
        controllerReset = false;

        int nBytes = cDesc.ochBlockSize(0);

        boolean getOk = false;
        byte[] pBytes = new byte[nBytes];
        for (int i = 0; i < N_RETRIES && !getOk; i++)
        {
            cDesc.flush(); // Flush the comm input.

            if (bursting)
                cDesc.sendOchBurstCommand(0);
            else
                cDesc.sendOchGetCommand(0);

            getOk = cDesc.read(pBytes, nBytes);
        }

        if (getOk)

        {
            System.arraycopy(pBytes, 0, cDesc._ochBuffer, 0, nBytes);
        }
        else
        {
            for (int i = 0; i < nBytes; i++)
                cDesc.setOch((byte) 0, i);
        }

        byte[] rBuf = cDesc.ochBuffer();

        if (getOk)
        {
            try
            {
                FRDLogManager.getInstance().write();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return getOk;
    }

    // ------------------------------------------------------------------------------
    // There are three input messages handled here.
    //
    // Signature (response to the "S" request);
    // VE/constants tables (response to the "V" request);
    // Run-time variables (response to the "A" request).
    //
    // The signature is arbitrary length, you must check it
    // on input. The VE/constants table is 128 bytes long and
    // the run-time variables are 22 bytes.
    // ------------------------------------------------------------------------------

    public long number(String s)
    {

        // Prefixes: % = binary, $ = hexadecimal, ! = decimal.
        // Suffixes: Q = binary, H = hexadecimal, T = decimal.

        int base = 10;
        char ch = s.charAt(0);

        switch (ch)
        {
        case '%':
            base = 2;
            s = ' ' + s.substring(1);
            break;
        case '$':
            base = 16;
            s = ' ' + s.substring(1);
            break;
        case '!':
            base = 10;
            s = ' ' + s.substring(1);
            break;

        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
            ch = s.charAt(s.length() - 1);
            switch (ch)
            {
            case 'Q':
            case 'q':
                base = 2;
                break;
            case 'H':
            case 'h':
                base = 16;
                break;
            case 'T':
            case 't':
                base = 10;
                break;

            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
                // Everthing is ok so far.
                break;

            default:
                throw new NumberFormatException(s);

            }
            break;
        default:
            throw new NumberFormatException(s);

        }

        long n = Long.valueOf(s, base);
        return n;
    }

    public int numberW(String s)
    {
        long nn = number(s);
        if (nn > 65536 || nn < 0)
            throw new NumberFormatException(s);
        return (int) nn;

    }

    public int numberB(String s)
    {
        long nn = number(s);
        if (nn > 255 || nn < 0)
            throw new NumberFormatException(s);

        return (int) nn;
    }

    public void readTable(String fileName, List<Integer> values)
    {
        values.clear();
        Pattern p = Pattern.compile("\\s*[Dd][BbWw]\\s*(\\d*).*");

        fileName = "tables" + File.separator + fileName;
        AssetManager assetManager = context.getResources().getAssets();

        BufferedReader input = null;
        try
        {
            try
            {
                input = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
                String line;

                while ((line = input.readLine()) != null)
                {
                    Matcher matcher = p.matcher(line);
                    if (matcher.matches())
                    {
                        String num = matcher.group(1);

                        if (num != null)
                        {
                            values.add(Integer.valueOf(num));
                        }
                    }
                }

            }
            finally
            {
                if (input != null)
                    input.close();
            }

        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }

    public void setContext(Context c)
    {
        this.context = c;
    }

    public Context getContext()
    {
        return context;

    }
    public void broadcastMessage(String s)
    {
        Intent broadcast = new Intent();
        broadcast.setAction(GENERAL_MESSAGE);
        broadcast.putExtra(MESSAGE, s);
        context.sendBroadcast(broadcast);

    }
    
 }
