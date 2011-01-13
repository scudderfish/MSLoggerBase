package uk.org.smithfamily.msdisp.parser;

public class MsDatabase
{
    public ControllerDescriptor cDesc;

    Datalog                     log;
    MsComm                      io;
    int                         _pageNo;
    boolean                     _loaded;              // Memory image is valid
                                                       // from either file or
                                                       // MS.
    boolean                     _burned;              // MS RAM and flash are
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

    boolean                     controllerReset;      // Reset detected.
    String                      m_logFileName;

    double[]                    wwuX = new double[10];

    enum thermType
    {
        Celsius, Fahrenheit
    };

    static thermType therm;

    static boolean   rawTPS;

    double tempFromDb(double t)
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

    public MsDatabase()
    {
        log = null;
        io = new MsComm();
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
        Repository.UveTuneLodIdx = cDesc
                .varIndex(StringConstants.S_veTuneLodIdx);
        Repository.UveTuneRpmIdx = cDesc
                .varIndex(StringConstants.S_veTuneRpmIdx);
        Repository.UveTuneValue = cDesc.varIndex(StringConstants.S_veTuneValue);

        cDesc._userVar.add(Repository.UveTuneLodIdx, 0.0); // Defaults until the
                                                           // tuning dialog is
                                                           // displayed.
        cDesc._userVar.add(Repository.UveTuneRpmIdx, 0.0);
        cDesc._userVar.add(Repository.UveTuneValue, 0.0);

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
        load(); // Attempt to grab data from controller.

        return status;
    }

    private void load()
    {
        // TODO Auto-generated method stub
        
    }

    boolean readConfig()
    {
       boolean existed = false;

       FILE *msConfig = cfgOpen("megatune.cfg", "r");
       commPortNumber =    1;
       commPortRate   = 9600;

       if (msConfig != NULL) {
          existed = true;

          char cbuf[100];
          if (fgets(cbuf, sizeof(cbuf), msConfig) != NULL) {
             commPortNumber = strtol(cbuf+3, NULL, 10);
             if (commPortNumber < 1 || commPortNumber > 99) commPortNumber = 1;
          }

          if (fgets(cbuf, sizeof(cbuf), msConfig) != NULL) {
             int igtemp;
             sscanf(cbuf, "%d", &igtemp);
             timerInterval = igtemp;
             userTimerInt  = timerInterval;
          }

          if (fgets(cbuf, sizeof(cbuf), msConfig) != NULL) {
             sscanf(cbuf, "%d", &commPortRate);
             if (commPortRate !=   9600 
              && commPortRate !=  19200
              && commPortRate !=  38400
              && commPortRate !=  56000
              && commPortRate !=  57600
              && commPortRate != 115200) commPortRate = 9600;
          }

          io.reset(commPortNumber, commPortRate);

          if (fgets(cbuf, sizeof(cbuf), msConfig) != NULL) {

          }

          if (fgets(cbuf, sizeof(cbuf), msConfig) != NULL) {

          }
          cfgLog("   Comm port           = %6d", commPortNumber);
          cfgLog("   Comm rate           = %6d", commPortRate);
          cfgLog("   Timer interval      = %6d", timerInterval);
          cfgClose(msConfig);
       }

       return existed;
    }

}
