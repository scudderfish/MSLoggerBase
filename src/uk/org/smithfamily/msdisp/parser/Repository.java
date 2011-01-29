package uk.org.smithfamily.msdisp.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Stack;

import android.util.Log;

class ifBlock
{
    String fileName;
    int    lineNo;

    enum readState
    {
        yetToRead, nowReading, doneReading
    };

    readState reading;
    readState restoreState;

    ifBlock(String fn, int ln, readState curRd)
    {
        lineNo = ln;
        reading = readState.yetToRead;
        fileName = fn;
    }

    static String sReading(readState s)
    {
        return s == readState.nowReading ? "true" : "false";
    }
};

public class Repository
{
    enum blockType
    {
        blockNone, blockAccelerationWizard, blockAutoTune, blockBurstMode, blockColorMap, blockConstants, blockCurveEditor, blockDatalog, blockDefaults, blockFrontPage, blockGaugeColors, blockGaugeConfigurations, blockMegaTune, blockMenu, blockOutputChannels, blockRunTime, blockTableEditor, blockTuning, blockUserDefined, blockUnits
    };

    private static final String TAG     = "REPOSITORY";
    Stack<ifBlock>              ifStack = new Stack<ifBlock>();
    public static int           Uundefined;
    public static int           UegoVoltage;
    public static int           UveTuneLodIdx;                 // Zero-based
                                                                // index of
                                                                // load bin
                                                                // tuning
                                                                // point.
    public static int           UveTuneRpmIdx;                 // Index of rpm
                                                                // bin.
    public static int           UveTuneValue;                  // Value
                                                                // contained in
                                                                // VE[veTuneLodIdx,
                                                                // veTuneRpmIdx].

    // Controller versioning.
    double                      lofEGO, hifEGO, rdfEGO;

    double                      loaTPD, hiaTPD;
    double                      loaMAD, hiaMAD;

    double                      lorEGO, hirEGO;
    double                      lorCT, hirCT;
    double                      lorBAT, hirBAT;
    double                      lorTR, hirTR;
    double                      lorGE, hirGE;
    double                      lorMAP, hirMAP;
    double                      lorMAT, hirMAT;
    double                      lorRPM, hirRPM;
    double                      lorPW, hirPW;
    double                      lorDC, hirDC;
    double                      lorEGC, hirEGC;
    double                      lorBC, hirBC;
    double                      lorWC, hirWC;
    double                      lorADC, hirADC;
    double                      lorVE, hirVE;
    double                      lorACC, hirACC;

    double                      lotEGO, hitEGO, rdtEGO;        // The LED bar.

    double                      lotVEB, hitVEB;

    int                         vatSD;                         // spotDepth
    int                         vatCD;                         // cursorDepth
    int                         gaugeColumns;
    private MsDatabase          mdb;

    void aeTpsDot(Tokenizer t)
    {
        loaTPD = t.v(1);
        hiaTPD = t.v(2);
    }

    void aeMapDot(Tokenizer t)
    {
        loaMAD = t.v(1);
        hiaMAD = t.v(2);
    }

    void runEGO(Tokenizer t)
    {
        lorEGO = t.v(1);
        hirEGO = t.v(2);
    }

    void runCT(Tokenizer t)
    {
        lorCT = t.v(1);
        hirCT = t.v(2);
    }

    void runBAT(Tokenizer t)
    {
        lorBAT = t.v(1);
        hirBAT = t.v(2);
    }

    void runTR(Tokenizer t)
    {
        lorTR = t.v(1);
        hirTR = t.v(2);
    }

    void runGE(Tokenizer t)
    {
        lorGE = t.v(1);
        hirGE = t.v(2);
    }

    void runMAP(Tokenizer t)
    {
        lorMAP = t.v(1);
        hirMAP = t.v(2);
    }

    void runMAT(Tokenizer t)
    {
        lorMAT = t.v(1);
        hirMAT = t.v(2);
    }

    void runRPM(Tokenizer t)
    {
        lorRPM = t.v(1);
        hirRPM = t.v(2);
    }

    void runPW(Tokenizer t)
    {
        lorPW = t.v(1);
        hirPW = t.v(2);
    }

    void runDC(Tokenizer t)
    {
        lorDC = t.v(1);
        hirDC = t.v(2);
    }

    void runEGC(Tokenizer t)
    {
        lorEGC = t.v(1);
        hirEGC = t.v(2);
    }

    void runBC(Tokenizer t)
    {
        lorBC = t.v(1);
        hirBC = t.v(2);
    }

    void runWC(Tokenizer t)
    {
        lorWC = t.v(1);
        hirWC = t.v(2);
    }

    void runADC(Tokenizer t)
    {
        lorADC = t.v(1);
        hirADC = t.v(2);
    }

    void runVE(Tokenizer t)
    {
        lorVE = t.v(1);
        hirVE = t.v(2);
    }

    void runACC(Tokenizer t)
    {
        lorACC = t.v(1);
        hirACC = t.v(2);
    }

    public void readInit()
    {
        ochInit(StringConstants.S_UNDEFINED, Uundefined); // Put a guard in
                                                          // place to make sure
                                                          // this isn't changed.
        ochInit(StringConstants.S_veTuneLodIdx, UveTuneLodIdx);
        ochInit(StringConstants.S_veTuneRpmIdx, UveTuneRpmIdx);
        ochInit(StringConstants.S_veTuneValue, UveTuneValue);

        String defaultFilename = "megatune.ini";
        try
        {

            File iniFile = new File(defaultFilename);
            if (iniFile == null)
            {
                // globalMsg.send(msgInfo::mError | msgInfo::mExit,
                // "The file '%s' could not be opened.\n\n"
                // "MegaTune is terminating.",
                // defaultFilename);
            }
            else
            {
                BufferedReader in = new BufferedReader(new FileReader(iniFile));
                doRead(in, defaultFilename, 0);
            }

            if (!ifStack.isEmpty())
            {
                // globalMsg.fileName = ifStack.top().fileName;
                // globalMsg.lineNo = ifStack.top().lineNo;
                // globalMsg.send(msgInfo::mWarning,
                // "Unterminated '#if'.\n\nThings are going to be goofed up.");
                // globalMsg.fileName = "";
                // globalMsg.lineNo = 0;
            }

            mdb.cDesc.init(); // Generates indexes, so must be done before the
                              // resolution passes.

            resolveVarReferences();
            resolveVarReferences();
            resolveVarReferences();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // logFormat.resolve();
        // lop.resolve();

    }

    private void resolveVarReferences()
    {
        // TODO Auto-generated method stub

    }

    private ifBlock.readState reading = ifBlock.readState.nowReading;

    private void doRead(BufferedReader in, String fileName, int depth)
    {
        MsgInfo msg = new MsgInfo(fileName, depth);

        String lineBuffer;

        blockType block = blockType.blockNone;

        Tokenizer t = new Tokenizer();

        try
        {
            while ((lineBuffer = in.readLine()) != null)
            {
                msg.lineNo++;
                if (t.parse(lineBuffer) == 0)
                    continue;
                int nT = t.size();

                if (t.t(0) == Tokenizer.type.Tdir)
                {
                    if (nT < 2)
                    {
                        msg.send(MsgInfo.mWarning, "'#' has no directive, ignored.\t");
                        continue;
                    }

                    msg.ssize = ifStack.size();
                    if ("group".equals(t.get(1)))
                    {
                        msg.send(MsgInfo.mInfo, MessageFormat.format("#group {0}", t.get(2)));
                        msg.level++;
                        continue;
                    }
                    if ("endgroup".equals(t.get(1)))
                    {
                        msg.level--;
                        continue;
                    }
                    if ("if".equals(t.get(1)))
                    {
                        checkFor2nd("'#if' has no setting value, ignored.\t");
                        isVarDef(t.get(2), msg);

                        ifStack.push(new ifBlock(msg.fileName, msg.lineNo, reading));

                        if (reading == ifBlock.readState.nowReading)
                        {
                            if (isVarSet(t.get(2)))
                                reading = ifBlock.readState.nowReading;
                            else
                                reading = ifBlock.readState.yetToRead;
                        }
                        else
                        {
                            reading = ifBlock.readState.doneReading;
                        }
                        ifStack.peek().reading = reading;
                        msg.send(MsgInfo.mInfo, MessageFormat.format("#if {0}, including={1}", t.get(2), ifBlock.sReading(reading)));
                        continue;
                    }
                    else if ("ifnot".equals(t.get(1)))
                    { // ------------------------------
                        checkFor2nd("'#ifnot' has no setting value, ignored.\t");
                        isVarDef(t.get(2), msg);

                        ifStack.push(new ifBlock(msg.fileName, msg.lineNo, reading));
                        if (reading == ifBlock.readState.nowReading)
                        {
                            if (!isVarSet(t.get(2)))
                                reading = ifBlock.readState.nowReading;
                            else
                                reading = ifBlock.readState.yetToRead;
                        }
                        else
                        {
                            reading = ifBlock.readState.doneReading;
                        }
                        ifStack.peek().reading = reading;
                        msg.send(MsgInfo.mInfo,
                                MessageFormat.format("#ifnot {0}, including={1}", t.get(2), ifBlock.sReading(reading)));
                        continue;
                    }

                    else if ("elif".equals(t.get(1)))
                    { // -------------------------------
                        checkFor2nd("'#elif' has no setting value, ignored.\t");
                        isVarDef(t.get(2), msg);

                        if (msg.ssize == 0)
                        {
                            msg.send(MsgInfo.mError | MsgInfo.mExit,
                                    "'#elif' without '#if'.  Look for an extra '#endif' somewhere.\n\n MegaTune is terminating.");
                        }

                        if (reading == ifBlock.readState.yetToRead)
                        {
                            if (isVarSet(t.get(2)))
                                reading = ifBlock.readState.nowReading;
                            else
                                reading = ifBlock.readState.yetToRead;
                        }
                        else
                        {
                            reading = ifBlock.readState.doneReading;
                        }
                        ifStack.peek().reading = reading;
                        msg.send(MsgInfo.mInfo,
                                MessageFormat.format("#elif {0}, including={1}", t.get(2), ifBlock.sReading(reading)));
                        continue;
                    }

                    else if ("else".equals(t.get(1)))
                    { // -------------------------------
                        if (reading == ifBlock.readState.yetToRead)
                            reading = ifBlock.readState.nowReading;
                        else
                            reading = ifBlock.readState.doneReading;
                        ifStack.peek().reading = reading;
                        msg.send(MsgInfo.mInfo, MessageFormat.format("#else, including={0}", ifBlock.sReading(reading)));
                        continue;
                    }

                    else if ("endif".equals(t.get(1)))
                    { // ------------------------------
                        if (msg.ssize == 0)
                        {
                            msg.send(MsgInfo.mWarning, "Extra '#endif'.\n\nThings are going to be goofed up.\t");
                        }
                        else
                        {
                            reading = ifStack.peek().restoreState;
                            ifStack.pop();
                            // msg.send(msgInfo.mInfo, "#endif, including={0}",
                            // ifBlock.sReading(reading ?
                            // ifBlock.readState.nowReading:ifBlock.readState.yetToRead));
                        }
                        continue;
                    }

                }
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {

        }
    }

    private boolean isVarSet(String string)
    {
        // TODO Auto-generated method stub
        return false;
    }

    private void isVarDef(String string, MsgInfo msg)
    {
        // TODO Auto-generated method stub

    }

    private void checkFor2nd(String string)
    {
        // TODO Auto-generated method stub

    }

    private void ochInit(String sVetunevalue, int uveTuneValue2)
    {
        // TODO Auto-generated method stub

    }

}
