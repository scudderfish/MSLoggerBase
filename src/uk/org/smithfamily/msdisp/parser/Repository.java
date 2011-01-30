package uk.org.smithfamily.msdisp.parser;

import java.io.*;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;
import java.util.Stack;

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

    MsgInfo            globalMsg = new MsgInfo("", 0);

    double             MTVersion = 0.0;
    double             VERSION   = 0.0;
    Stack<ifBlock>     ifStack   = new Stack<ifBlock>();
    public static int  Uundefined;
    public static int  UegoVoltage;
    public static int  UveTuneLodIdx;                   // Zero-based
                                                         // index of
                                                         // load bin
                                                         // tuning
                                                         // point.
    public static int  UveTuneRpmIdx;                   // Index of
                                                         // rpm
                                                         // bin.
    public static int  UveTuneValue;                    // Value
                                                         // contained
                                                         // in
                                                         // VE[veTuneLodIdx,
                                                         // veTuneRpmIdx].

    // Controller versioning.
    double             lofEGO, hifEGO, rdfEGO;

    double             loaTPD, hiaTPD;
    double             loaMAD, hiaMAD;

    double             lorEGO, hirEGO;
    double             lorCT, hirCT;
    double             lorBAT, hirBAT;
    double             lorTR, hirTR;
    double             lorGE, hirGE;
    double             lorMAP, hirMAP;
    double             lorMAT, hirMAT;
    double             lorRPM, hirRPM;
    double             lorPW, hirPW;
    double             lorDC, hirDC;
    double             lorEGC, hirEGC;
    double             lorBC, hirBC;
    double             lorWC, hirWC;
    double             lorADC, hirADC;
    double             lorVE, hirVE;
    double             lorACC, hirACC;

    double             lotEGO, hitEGO, rdtEGO;          // The LED
                                                         // bar.

    double             lotVEB, hitVEB;

    int                vatSD;                           // spotDepth
    int                vatCD;                           // cursorDepth
    int                gaugeColumns;
    private MsDatabase mdb;
    boolean            writeXML;

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
            if (!iniFile.canRead())
            {
                globalMsg.send(MsgInfo.mError | MsgInfo.mExit, MessageFormat
                        .format("The file '{0}' could not be opened.\n\n"
                                + "MegaTune is terminating.", defaultFilename));
            } else
            {
                BufferedReader in = new BufferedReader(new FileReader(iniFile));
                doRead(in, defaultFilename, 0);
            }

            if (!ifStack.isEmpty())
            {
                globalMsg.fileName = ifStack.peek().fileName;
                globalMsg.lineNo = ifStack.peek().lineNo;
                globalMsg
                        .send(MsgInfo.mWarning,
                                "Unterminated '#if'.\n\nThings are going to be goofed up.");
                globalMsg.fileName = "";
                globalMsg.lineNo = 0;
            }

            mdb.cDesc.init(); // Generates indexes, so must be done before the
                              // resolution passes.

            resolveVarReferences();
            resolveVarReferences();
            resolveVarReferences();
        } catch (FileNotFoundException e)
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
    private boolean           _fieldColoring;
    private int               currentCP;
    private double            _barHysteresis;
    private String            t_fontFace;
    private int               t_fontSize;

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
                        msg.send(MsgInfo.mWarning,
                                "'#' has no directive, ignored.\t");
                        continue;
                    }

                    msg.ssize = ifStack.size();
                    if ("group".equals(t.get(1)))
                    {
                        msg.send(MsgInfo.mInfo,
                                MessageFormat.format("#group {0}", t.get(2)));
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
                        if (nT < 3)
                        {
                            msg.send(MsgInfo.mWarning,
                                    "'#if' has no setting value, ignored.\t");
                            continue;
                        }
                        ;
                        isVarDef(t.get(2), msg);

                        ifStack.push(new ifBlock(msg.fileName, msg.lineNo,
                                reading));

                        if (reading == ifBlock.readState.nowReading)
                        {
                            if (isVarSet(t.get(2)))
                                reading = ifBlock.readState.nowReading;
                            else
                                reading = ifBlock.readState.yetToRead;
                        } else
                        {
                            reading = ifBlock.readState.doneReading;
                        }
                        ifStack.peek().reading = reading;
                        msg.send(MsgInfo.mInfo, MessageFormat.format(
                                "#if {0}, including={1}", t.get(2),
                                ifBlock.sReading(reading)));
                        continue;
                    } else if ("ifnot".equals(t.get(1)))
                    { // ------------------------------
                        if (nT < 3)
                        {
                            msg.send(MsgInfo.mWarning,
                                    "'#ifnot' has no setting value, ignored.\t");
                            continue;
                        }
                        ;
                        isVarDef(t.get(2), msg);

                        ifStack.push(new ifBlock(msg.fileName, msg.lineNo,
                                reading));
                        if (reading == ifBlock.readState.nowReading)
                        {
                            if (!isVarSet(t.get(2)))
                                reading = ifBlock.readState.nowReading;
                            else
                                reading = ifBlock.readState.yetToRead;
                        } else
                        {
                            reading = ifBlock.readState.doneReading;
                        }
                        ifStack.peek().reading = reading;
                        msg.send(MsgInfo.mInfo, MessageFormat.format(
                                "#ifnot {0}, including={1}", t.get(2),
                                ifBlock.sReading(reading)));
                        continue;
                    }

                    else if ("elif".equals(t.get(1)))
                    { // -------------------------------
                        if (nT < 3)
                        {
                            msg.send(MsgInfo.mWarning,
                                    "'#elif' has no setting value, ignored.\t");
                            continue;
                        }
                        ;
                        isVarDef(t.get(2), msg);

                        if (msg.ssize == 0)
                        {
                            msg.send(
                                    MsgInfo.mError | MsgInfo.mExit,
                                    "'#elif' without '#if'.  Look for an extra '#endif' somewhere.\n\n MegaTune is terminating.");
                        }

                        if (reading == ifBlock.readState.yetToRead)
                        {
                            if (isVarSet(t.get(2)))
                                reading = ifBlock.readState.nowReading;
                            else
                                reading = ifBlock.readState.yetToRead;
                        } else
                        {
                            reading = ifBlock.readState.doneReading;
                        }
                        ifStack.peek().reading = reading;
                        msg.send(MsgInfo.mInfo, MessageFormat.format(
                                "#elif {0}, including={1}", t.get(2),
                                ifBlock.sReading(reading)));
                        continue;
                    }

                    else if ("else".equals(t.get(1)))
                    { // -------------------------------
                        if (reading == ifBlock.readState.yetToRead)
                            reading = ifBlock.readState.nowReading;
                        else
                            reading = ifBlock.readState.doneReading;
                        ifStack.peek().reading = reading;
                        msg.send(MsgInfo.mInfo, MessageFormat.format(
                                "#else, including={0}",
                                ifBlock.sReading(reading)));
                        continue;
                    }

                    else if ("endif".equals(t.get(1)))
                    { // ------------------------------
                        if (msg.ssize == 0)
                        {
                            msg.send(MsgInfo.mWarning,
                                    "Extra '#endif'.\n\nThings are going to be goofed up.\t");
                        } else
                        {
                            reading = ifStack.peek().restoreState;
                            ifStack.pop();
                            // msg.send(msgInfo.mInfo, "#endif, including={0}",
                            // ifBlock.sReading(reading ?
                            // ifBlock.readState.nowReading:ifBlock.readState.yetToRead));
                        }
                        continue;
                    }
                    if (reading != ifBlock.readState.nowReading)
                        continue;

                    if (t.t(0) == Tokenizer.type.Tdir)
                    {
                        if ("include".equals(t.get(1)))
                        {

                            if (nT < 3)
                            {
                                msg.send(MsgInfo.mWarning,
                                        "'#include' has no file specified, ignored.");
                                continue;
                            }
                            ;
                            File incFile = new File(t.get(2));
                            BufferedReader incRead = new BufferedReader(
                                    new FileReader(incFile));
                            if (incRead != null)
                            {
                                doRead(incRead, t.get(2), msg.level + 1);
                            } else
                            {
                                msg.send(
                                        MsgInfo.mError | MsgInfo.mExit,
                                        MessageFormat
                                                .format("The included file '{0} could not be opened.\n\nMegaTune is terminating.",
                                                        t.get(2)));
                            }
                        } else if ("alert".equals(t.get(1)))
                        {
                            if (nT < 3)
                            {
                                msg.send(MsgInfo.mWarning,
                                        "'#alert' has no message specified, ignored.");
                                continue;
                            }
                            ;
                            msg.send(MsgInfo.mAlert,
                                    MessageFormat.format("{0}", t.get(2)));
                        } else if ("log".equals(t.get(1)))
                        {
                            if (nT < 3)
                            {
                                msg.send(MsgInfo.mWarning,
                                        "'#log' has no message specified, ignored.");
                                continue;
                            }
                            ;
                            msg.send(MsgInfo.mInfo,
                                    MessageFormat.format("{0}", t.get(2)));
                        } else if ("set".equals(t.get(1)))
                        {
                            if (nT < 3)
                            {
                                msg.send(MsgInfo.mWarning,
                                        "'#set' has no setting value, ignored.");
                                continue;
                            }
                            ;
                            varSet(t.get(2), true);
                            msg.send(
                                    MsgInfo.mInfo,
                                    MessageFormat.format("  set {0} (true)",
                                            t.get(2)));
                        } else if ("unset".equals(t.get(1)))
                        {
                            if (nT < 3)
                            {
                                msg.send(MsgInfo.mWarning,
                                        "'#unset' has no setting value, ignored.");
                                continue;
                            }
                            ;
                            varSet(t.get(2), false);
                            msg.send(
                                    MsgInfo.mInfo,
                                    MessageFormat.format("unset {0} (false)",
                                            t.get(2)));
                        } else if ("error".equals(t.get(1)))
                        {
                            if (nT < 3)
                            {
                                msg.send(MsgInfo.mWarning,
                                        "'#error' has no message specified, ignored.");
                                continue;
                            }
                            ;
                            msg.send(MsgInfo.mError,
                                    MessageFormat.format("{0}", t.get(2)));
                        } else if ("exit".equals(t.get(1)))
                        {
                            msg.send(MsgInfo.mExit, "#exit");
                        } else
                        {
                            msg.send(MsgInfo.mWarning, MessageFormat.format(
                                    "Unrecognized directive '{0}', ignored.",
                                    t.get(1)));
                        }
                        continue;
                    }


                    if (t.eq(StringConstants.S__AccelerationWizard_, 0))
                    {
                        block = blockType.blockAccelerationWizard;
                        continue;
                    }
                    if (t.eq(StringConstants.S__AutoTune_, 0))
                    {
                        block = blockType.blockAutoTune;
                        continue;
                    }
                    if (t.eq(StringConstants.S__BurstMode_, 0))
                    {
                        block = blockType.blockBurstMode;
                        continue;
                    }
                    if (t.eq(StringConstants.S__ColorMap_, 0))
                    {
                        block = blockType.blockColorMap;
                        continue;
                    }
                    if (t.eq(StringConstants.S__Constants_, 0))
                    {
                        block = blockType.blockConstants;
                        continue;
                    }
                    if (t.eq(StringConstants.S__CurveEditor_, 0))
                    {
                        block = blockType.blockCurveEditor;
                        continue;
                    }
                    if (t.eq(StringConstants.S__Datalog_, 0))
                    {
                        block = blockType.blockDatalog;
                        continue;
                    }
                    if (t.eq(StringConstants.S__Defaults_, 0))
                    {
                        block = blockType.blockDefaults;
                        continue;
                    }
                    if (t.eq(StringConstants.S__FrontPage_, 0))
                    {
                        block = blockType.blockFrontPage;
                        continue;
                    }
                    if (t.eq(StringConstants.S__GaugeColors_, 0))
                    {
                        block = blockType.blockGaugeColors;
                        continue;
                    }
                    if (t.eq(StringConstants.S__GaugeConfigurations_, 0))
                    {
                        block = blockType.blockGaugeConfigurations;
                        continue;
                    }
                    if (t.eq(StringConstants.S__MegaTune_, 0))
                    {
                        block = blockType.blockMegaTune;
                        continue;
                    }
                    if (t.eq(StringConstants.S__Menu_, 0))
                    {
                        block = blockType.blockMenu;
                        continue;
                    }
                    if (t.eq(StringConstants.S__RunTime_, 0))
                    {
                        block = blockType.blockRunTime;
                        continue;
                    }
                    if (t.eq(StringConstants.S__OutputChannels_, 0))
                    {
                        block = blockType.blockOutputChannels;
                        continue;
                    }
                    if (t.eq(StringConstants.S__TableEditor_, 0))
                    {
                        block = blockType.blockTableEditor;
                        continue;
                    }
                    if (t.eq(StringConstants.S__Tuning_, 0))
                    {
                        block = blockType.blockTuning;
                        continue;
                    }
                    if (t.eq(StringConstants.S__Units_, 0))
                    {
                        block = blockType.blockUnits;
                        continue;
                    }
                    if (t.eq(StringConstants.S__UserDefined_, 0))
                    {
                        block = blockType.blockUserDefined;
                        continue;
                    }
                    if (t.get(0).charAt(0) == ']')
                    {
                        block = blockType.blockNone;
                        continue;
                    }
                }

                switch (block)
                {
                // ---------------------------------------------------------------------
                case blockNone: // -----------------------------------------------------
                    // Nothing yet...
                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockMegaTune: // -------------------------------------------------
                    if (t.get(0).equals(StringConstants.S_writeXML))
                    {
                        writeXML = t.eq(StringConstants.S_true, 1);
                        break;
                    }

                    if (t.get(0).equals(StringConstants.S_MTversion))
                    {
                        // MTversion = t.v(1);
                        // if (MTversion != VERSION) {
                        // msg.send(MsgInfo.mWarning,MessageFormat.format(
                        // "The file '{0}' is version {1,number}, but was expected to be {2,number}.\n\nStrange behavior may result from this, update '{3}' as soon as possible.",
                        // msg.fileName, MTversion, VERSION, msg.fileName));
                        // }
                        break;
                    }

                    if (t.get(0).equals(StringConstants.S_queryCommand))
                    {
                        mdb.cDesc.setQueryCommand(t.get(1));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_versionInfo))
                    {
                        mdb.cDesc.setVersionInfo(t.get(1));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_signature))
                    {
                        switch (t.t(1))
                        {
                        case Tstr:
                            mdb.cDesc.setSignature(t.get(1), msg.fileName);
                            break;
                        case Tnum:
                            break;
                        }
                        break;
                    }
                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockBurstMode: // ------------------------------------------------
                    if (t.get(0).equals(StringConstants.S_commRate))
                    {
                        mdb.burstCommRate = (int) t.v(1);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_commPort))
                    {
                        mdb.burstCommPort = (int) t.v(1);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_getCommand))
                    {
                        mdb.cDesc.setOchBurstCommand(t.get(1), 0);
                        break;
                    }
                    // if (t.get(0).equals(StringConstants.S_initialDeltaT)) {
                    // burstLog::suggestedDeltaT(t.v(1)); break; }
                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockColorMap: // -------------------------------------------------
                    if (t.get(0).equals(StringConstants.S_fieldColoring))
                    {
                        _fieldColoring = t.eq(StringConstants.S_on, 1);
                        break;
                    }
                    // setColor(t.get(0), RGB(t.i(1), t.i(2), t.i(3)));
                    break;

                // ---------------------------------------------------------------------
                case blockConstants: // ------------------------------------------------
                    if (t.get(0).equals(StringConstants.S_nPages))
                    {
                        mdb.cDesc.setNPages(t.v(1));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_endianness))
                    {
                        mdb.cDesc.setEndianness(t.get(1));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_pageSize))
                    {
                        checkPageCount(StringConstants.S_pageSize, nT - 1, msg);
                        // Array of int, one per page.
                        for (int i = 0; i < nT - 1; i++)
                        {
                            mdb.cDesc.setPageSize(t.v(i + 1), i);
                        }
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_verify))
                    {
                        mdb.cDesc.setVerify(t.eq(StringConstants.S_on, 1));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_burnCommand))
                    {
                        // checkPageCount(StringConstants.S_burnCommand, nT-1,
                        // msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setBurnCommand(t.get(i + 1), i);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_pageActivate))
                    {
                        checkPageCount(StringConstants.S_pageActivate, nT - 1,
                                msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setPageActivate(t.get(i + 1), i);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_pageIdentifier))
                    {
                        checkPageCount(StringConstants.S_pageIdentifier,
                                nT - 1, msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setPageIdentifier(t.get(i + 1), i);
                        break;
                    }

                    if (t.get(0).equals(StringConstants.S_pageReadCommand))
                    { // == Read commands ==
                        checkPageCount(StringConstants.S_pageReadCommand,
                                nT - 1, msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setPageReadWhole(t.get(i + 1), i);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_pageChunkRead))
                    {
                        checkPageCount(StringConstants.S_pageChunkRead, nT - 1,
                                msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setPageReadChunk(t.get(i + 1), i);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_pageValueRead))
                    {
                        checkPageCount(StringConstants.S_pageValueRead, nT - 1,
                                msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setPageReadValue(t.get(i + 1), i);
                        break;
                    }

                    if (t.get(0).equals(StringConstants.S_pageWriteCommand))
                    { // == Write commands ==
                        checkPageCount(StringConstants.S_pageWriteCommand,
                                nT - 1, msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setPageWriteWhole(t.get(i + 1), i);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_pageChunkWrite))
                    {
                        checkPageCount(StringConstants.S_pageChunkWrite,
                                nT - 1, msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setPageWriteChunk(t.get(i + 1), i);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_pageValueWrite))
                    {
                        checkPageCount(StringConstants.S_pageValueWrite,
                                nT - 1, msg);
                        for (int i = 0; i < nT - 1; i++)
                            mdb.cDesc.setPageWriteValue(t.get(i + 1), i);
                        break;
                    }

                    if (t.get(0).equals(StringConstants.S_interWriteDelay))
                    {
                        mdb.cDesc.setDelay(t.i(1));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_writeBlocks))
                    {
                        mdb.cDesc.setWriteBlocks(t.eq(StringConstants.S_on, 1));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_pageActivationDelay))
                    {
                        mdb.cDesc.setPageActivationDelay(t.i(1));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_blockReadTimeout))
                    {
                        mdb.cDesc.setBlockReadTimeout(t.i(1));
                        break;
                    }

                    if (t.get(0).equals(StringConstants.S_page))
                    {
                        currentCP = t.i(1) - 1;

                        // Validate it is 0...nPages-1
                        if (currentCP < 0 || currentCP >= mdb.cDesc.nPages())
                        {
                            msg.send(
                                    MsgInfo.mError | MsgInfo.mExit,
                                    MessageFormat
                                            .format("The \"page = {0,number}\" setting is outside the range of \"nPages = {1,number}\"     \n\tin the [Constants] definitions.\n\nMegaTune is terminating.",
                                                    currentCP + 1,
                                                    mdb.cDesc.nPages()));
                        }
                        break;
                    }

                    // ------------------------------------------------------------------
                    // -- We've got a new symbol, check page number and then
                    // store it. --
                    {
                        if (currentCP < 0 || currentCP > mdb.cDesc.nPages())
                        {
                            throw new RepositoryException(
                                    "current page out of range");
                        }

                        // ??? check number of values...
                        Symbol s = new Symbol();
                        if (t.eq(StringConstants.S_scalar, 1))
                        {
                            if (nT > 10)
                            {
                                msg.send(
                                        MsgInfo.mWarning,
                                        MessageFormat
                                                .format("Constant definition for \"{0}\" has too many parameters {1}, 10 expected.\t\n\n",
                                                        t.get(0), nT));

                            }

                            s.setCScalar(t.get(0), t.get(2), currentCP, t.v(3),
                                    t.get(4), t.v(5), t.v(6), t.v(7), t.v(8),
                                    t.v(9));
                        } else if (t.eq(StringConstants.S_array, 1))
                        {
                            if (nT > 11)
                                msg.send(
                                        MsgInfo.mWarning,
                                        MessageFormat
                                                .format("Constant definition for \"{0}\" has too many parameters %{1}, 11 expected.\t\n\n",
                                                        t.get(0), nT));
                            s.setCArray(t.get(0), t.get(2), currentCP, t.v(3),
                                    t.get(5), t.get(4), t.v(6), t.v(7), t.v(8),
                                    t.v(9), t.v(10));
                        } else if (t.eq(StringConstants.S_bits, 1))
                        {
                            s.setCBits(t.get(0), t.get(2), currentCP, t.v(3),
                                    t.get(4));
                            for (int i = 5; i < nT; i++)
                            {
                                s.userStrings.add(t.get(i));
                            }
                            int ofs = s.bitOfs();
                            for (int i = nT - 5; i < s.nValues(); i++)
                            {
                                String autoLbl = "" + (i + ofs);
                                s.userStrings.add(autoLbl);
                            }
                        } else
                        {
                            msg.send(
                                    MsgInfo.mWarning,
                                    MessageFormat
                                            .format("Constant definition for \"{0}\" is corrupted, starts with \"{0}\"     \n\nThis entry is ignored.",
                                                    t.get(0), t.get(1)));
                            break;
                        }
                        mdb.cDesc.addSymbol(s);

                        // Validate page boundaries.
                        if (s.offset() + s.size() > mdb.cDesc
                                .pageSize(currentCP))
                        {
                            msg.send(
                                    MsgInfo.mError | MsgInfo.mExit,
                                    MessageFormat
                                            .format("The byte range of {0} values ({1}-{2}) overwrite the     \n\tpage boundaries of 0-{3} in the [Constants] definitions.\n\nMegaTune is terminating.",
                                                    t.get(0),
                                                    s.offset(),
                                                    s.offset() + s.size() - 1,
                                                    mdb.cDesc
                                                            .pageSize(currentCP) - 1));
                        }
                        break;
                    }

                    // ---------------------------------------------------------------------
                case blockOutputChannels: // -------------------------------------------
                    // ??? grab the expressions and store them in order.
                    if (nT < 2)
                    {
                        msg.send(MsgInfo.mWarning, MessageFormat.format(
                                "Missing argument to '%s'", t.get(0)));
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_ochBlockSize))
                    {
                        mdb.cDesc.setOchBlockSize(t.v(1), 0);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_ochGetCommand))
                    {
                        mdb.cDesc.setOchGetCommand(t.get(1), 0);
                        break;
                    }

                    {
                        Symbol s = new Symbol();
                        if (t.eq(StringConstants.S_scalar, 1))
                        {
                            // name hunk type ofs units scale trans
                            // rpm = scalar, S16, 7, "RPM", 1.000, 0.000
                            s.setOScalar(t.get(0), t.get(2), 0, t.v(3),
                                    t.get(4), t.v(5), t.v(6));
                        } else if (t.eq(StringConstants.S_bits, 1))
                        {
                            // name hunk type ofs bitSpec
                            // sch = bits, U08, 11, [2:2]
                            s.setOBits(t.get(0), t.get(2), 0, t.v(3), t.get(4));
                        } else if (t.t(1) == Tokenizer.type.Texp)
                        {
                            // var = { expr } [, "units"]
                            s.setExpr(t.get(0), t.stripped(1), t.get(2),
                                    msg.fileName, msg.lineNo);
                        } else
                        {
                            msg.send(
                                    MsgInfo.mWarning,
                                    MessageFormat
                                            .format("OutputChannel definition for \"{0}\" is corrupted, starts with \"{1}\"     \n\n"
                                                    + "This entry is ignored.",
                                                    t.get(0), t.get(1)));
                            break;
                        }
                        mdb.cDesc.addSymbol(s);

                        // Validate page boundaries.
                        if (s.offset() + s.size() > mdb.cDesc.ochBlockSize(0))
                        {
                            msg.send(
                                    MsgInfo.mError | MsgInfo.mExit,
                                    MessageFormat
                                            .format("The byte range of %s values (%d-%d) overwrite the     \n"
                                                    + "\tmemory boundaries of 0-%d in the [OutputChannels] definitions.\n\n"
                                                    + "MegaTune is terminating.",
                                                    t.get(0),
                                                    s.offset(),
                                                    s.offset() + s.size() - 1,
                                                    mdb.cDesc.ochBlockSize(0) - 1));
                        }
                        break;
                    }

                    // ---------------------------------------------------------------------
                case blockUnits: // ----------------------------------------------------
                    if (t.get(0).equals(StringConstants.S_temperature))
                    {
                        if (nT == 2)
                        {
                            if (t.get(1).charAt(0) == 'C')
                                MsDatabase.therm = MsDatabase.thermType.Celsius;
                            if (t.get(1).charAt(0) == 'F')
                                MsDatabase.therm = MsDatabase.thermType.Fahrenheit;
                        }
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_TPS))
                    {
                        if (nT == 2)
                        {
                            if (t.get(1).charAt(0) == 'R')
                                MsDatabase.rawTPS = true;
                            if (t.get(1).charAt(0) == '%')
                                MsDatabase.rawTPS = false;
                        }
                        break;
                    }
                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockGaugeConfigurations: // --------------------------------------
                    // setLimits(StringConstants.S_GaugeConfigurations,
                    // t.get(0),
                    // t);
                    break;

                // ---------------------------------------------------------------------
                case blockFrontPage: // ------------------------------------------------
                    // New style from gauge1 through gauge8.
                    if (t.match(StringConstants.S_gauge, 0))
                    {
                        // setGaugeRef(StringConstants.S_FrontPage, t, 8);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_egoLEDs))
                    {
                        frontEGO(t);
                        break;
                    } // =0,1.0,0.5

                    // Built-in indicators name -bg -fg +bg +fg
                    // if (t.get(0).equals(StringConstants.S_saved )) {
                    // uil.set(StringConstants.S_saved, t.get(1), t.get(2),
                    // t.get(3), t.get(4)); break; }
                    // if (t.get(0).equals(StringConstants.S_logging )) {
                    // uil.set(StringConstants.S_logging, t.get(1), t.get(2),
                    // t.get(3), t.get(4)); break; }
                    // if (t.get(0).equals(StringConstants.S_connected )) {
                    // uil.set(StringConstants.S_connected, t.get(1), t.get(2),
                    // t.get(3), t.get(4)); break; }

                    // User-defined indicators
                    if (t.get(0).equals(StringConstants.S_indicator))
                    {
                        // if (t.eq(StringConstants.S_restart,1)) { uil.reset();
                        // break; }
                        // expression -Txt +Txt -bg -fg +bg +fg
                        // uil.add("main", t.stripped(1), t.get(2), t.get(3),
                        // t.get(4), t[5], t[6], t[7]);
                        break;
                    }

                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockAccelerationWizard: // ---------------------------------------
                    if (t.get(0).equals(StringConstants.S_mapDotBar))
                    {
                        aeMapDot(t);
                        break;
                    } // =0,1.0
                    if (t.get(0).equals(StringConstants.S_tpsDotBar))
                    {
                        aeTpsDot(t);
                        break;
                    } // =0,1.0
                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockRunTime: // --------------------------------------------------
                    if (t.get(0).equals(StringConstants.S_barHysteresis))
                    {
                        _barHysteresis = t.v(1);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_egoBar))
                    {
                        runEGO(t);
                        break;
                    } // =0,1.0
                    if (t.get(0).equals(StringConstants.S_coolantBar))
                    {
                        runCT(t);
                        break;
                    } // =-40,215
                    if (t.get(0).equals(StringConstants.S_batteryBar))
                    {
                        runBAT(t);
                        break;
                    } // =6,15
                    if (t.get(0).equals(StringConstants.S_throttleBar))
                    {
                        runTR(t);
                        break;
                    } // =0,100
                    if (t.get(0).equals(StringConstants.S_gammaEBar))
                    {
                        runGE(t);
                        break;
                    } // =0,100
                    if (t.get(0).equals(StringConstants.S_mapBar))
                    {
                        runMAP(t);
                        break;
                    } // =0,255
                    if (t.get(0).equals(StringConstants.S_matBar))
                    {
                        runMAT(t);
                        break;
                    } // =-40,215
                    if (t.get(0).equals(StringConstants.S_rpmBar))
                    {
                        runRPM(t);
                        break;
                    } // =0,8000
                    if (t.get(0).equals(StringConstants.S_pulseWidthBar))
                    {
                        runPW(t);
                        break;
                    } // =0,25.5
                    if (t.get(0).equals(StringConstants.S_dutyCycleBar))
                    {
                        runDC(t);
                        break;
                    } // =0,100
                    if (t.get(0).equals(StringConstants.S_egoCorrBar))
                    {
                        runEGC(t);
                        break;
                    } // =0,200
                    if (t.get(0).equals(StringConstants.S_baroCorrBar))
                    {
                        runBC(t);
                        break;
                    } // =0,200
                    if (t.get(0).equals(StringConstants.S_warmupCorrBar))
                    {
                        runWC(t);
                        break;
                    } // =0,200
                    if (t.get(0).equals(StringConstants.S_airdenCorrBar))
                    {
                        runADC(t);
                        break;
                    } // =0,200
                    if (t.get(0).equals(StringConstants.S_veCorrBar))
                    {
                        runVE(t);
                        break;
                    } // =0,200
                    if (t.get(0).equals(StringConstants.S_accCorrBar))
                    {
                        runACC(t);
                        break;
                    } // =0,200
                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockTuning: // ---------------------------------------------------
                    // New style from gauge1 through gauge6.
                    if (t.get(0).equals(StringConstants.S_gaugeColumns))
                    {
                        gaugeColumns = (int) t.v(1);
                        if (gaugeColumns < 1)
                            gaugeColumns = 1;
                        if (gaugeColumns > 2)
                            gaugeColumns = 2;
                        break; // Skip over next line which would match.
                    }
                    if (t.match(StringConstants.S_gauge, 0))
                    {
                        // setGaugeFam(StringConstants.S_Tuning, t, 6);
                        break;
                    }

                    // if (t.get(0).equals(StringConstants.S_pageButtons )) {
                    // if (nT == 5) {
                    // for (int i = 1; i < 5; i++) {
                    // strncpy(_pageLbl[i-1], t[i], sizeof(_pageLbl[0])-1);
                    // }
                    // }
                    // break;
                    // }

                    if (t.get(0).equals(StringConstants.S_egoLEDs))
                    {
                        tuneEGO(t);
                        break;
                    } // =0,1.0,0.5
                    if (t.get(0).equals(StringConstants.S_veBar))
                    {
                        tuneVEB(t);
                        break;
                    } // =0,120
                    if (t.get(0).equals(StringConstants.S_spotDepth))
                    {
                        tuneSD(t);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_cursorDepth))
                    {
                        tuneCD(t);
                        break;
                    }
                    if (t.get(0).equals(StringConstants.S_font))
                    {
                        t_fontFace = t.get(1);
                        t_fontSize = t.i(2);
                        break;
                    }
                    // if (t.get(0).equals(StringConstants.S_backgroundColor)) {
                    // t_backgroundColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_cursorColor )) {
                    // t_cursorColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_cursorText )) {
                    // t_cursorText = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_gridColor )) {
                    // t_gridColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_spotColor )) {
                    // t_spotColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_spotText )) {
                    // t_spotText = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_textColor )) {
                    // t_textColor = getColor(t); break; }
                    // msg.send(MsgInfo.mWarning,
                    // MessageFormat.format("Unexpected or unsupported token '{0}'",
                    // t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockGaugeColors: // ----------------------------------------------
                    // if (t.get(0).equals(StringConstants.S_alertColor )) {
                    // g_alertColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_ledColor )) {
                    // g_ledColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_ledAlertColor )) {
                    // g_ledAlertColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_needleColor )) {
                    // g_needleColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_rangeColor )) {
                    // g_rangeColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_scaleColor )) {
                    // g_scaleColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_titleColor )) {
                    // g_titleColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_valueColor )) {
                    // g_valueColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_backgroundColor)) {
                    // g_backgroundColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_faceColor )) {
                    // g_faceColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_bgWarningColor )) {
                    // g_bgWarningColor = getColor(t); break; }
                    // if (t.get(0).equals(StringConstants.S_bgDangerColor )) {
                    // g_bgDangerColor = getColor(t); break; }
                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockMenu:
                    break;

                // ---------------------------------------------------------------------
                case blockUserDefined: // ----------------------------------------------
                    break;

                // ---------------------------------------------------------------------
                case blockCurveEditor: // ----------------------------------------------
                    break;

                // ---------------------------------------------------------------------
                case blockTableEditor: // ----------------------------------------------
                    break;

                // ---------------------------------------------------------------------
                case blockAutoTune: // -------------------------------------------------
                    // if (t.get(0).equals(StringConstants.S_table)) {
                    // currentUT = ud.id(t.get(1)) - ID_USER_TUNING_00;
                    // if (currentUT < 0 || currentUT >= tuningDialog::nUT) {
                    // msg.send(MsgInfo.mWarning,
                    // "Unknown table id '%s', ignored.", t.get(1));
                    // currentUT = 0;
                    // }
                    // break;
                    // }
                    // if (t.get(0).equals(StringConstants.S_xLimits )) {
                    // ut[currentUT].atp.xMin = t.v(1); ut[currentUT].atp.xMax =
                    // t.v(2); break; }
                    // if (t.get(0).equals(StringConstants.S_yLimits )) {
                    // ut[currentUT].atp.yMin = t.v(1); ut[currentUT].atp.yMax =
                    // t.v(2); break; }
                    // if (t.get(0).equals(StringConstants.S_zLimits )) {
                    // ut[currentUT].atp.zMin = t.v(1); ut[currentUT].atp.zMax =
                    // t.v(2); break; }
                    // if (t.get(0).equals(StringConstants.S_allowAutoTune )) {
                    // ut[currentUT].atp.setAutoTune(t.eq(StringConstants.S_on,
                    // 1)); break; }
                    // if (t.get(0).equals(StringConstants.S_corrector )) {
                    // ut[currentUT].atp.correctorName = t.get(1); break; }
                    // if (t.get(0).equals(StringConstants.S_xRadius )) {
                    // ut[currentUT].atp.xRadius = t.v(1); break; }
                    // if (t.get(0).equals(StringConstants.S_yRadius )) {
                    // ut[currentUT].atp.yRadius = t.v(1); break; }
                    // if
                    // (t.get(0).equals(StringConstants.S_initialStartupInterval))
                    // { ut[currentUT].atp.t0 = t.v(1); break; }
                    // if (t.get(0).equals(StringConstants.S_updateInterval )) {
                    // ut[currentUT].atp.dT = t.v(1); break; }
                    // if (t.get(0).equals(StringConstants.S_proportionalGain ))
                    // { ut[currentUT].atp.pGain = t.v(1); break; }
                    // if (t.get(0).equals(StringConstants.S_lumpiness )) {
                    // ut[currentUT].atp.lumpiness = t.v(1); break; }
                    // msg.send(MsgInfo.mWarning,
                    // MessageFormat.format("Unexpected or unsupported token '{0}'",
                    // t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockDatalog: // --------------------------------------------------
                    // if (t.get(0).equals(StringConstants.S_enableWrite )) {
                    // lop._enableVar = t.get(1); break; }
                    // if (t.get(0).equals(StringConstants.S_markOnTrue )) {
                    // lop._markerVar = t.get(1); break; }
                    // if (t.get(0).equals(StringConstants.S_delimiter )) {
                    // lop._delimiter = (char
                    // *)byteString(t.get(1)).xlate().ptr(); break; }
                    // if (t.get(0).equals(StringConstants.S_defaultExtension))
                    // { lop._defaultLogExtension = t.get(1); break; }
                    // if (t.get(0).equals(StringConstants.S_entry)) {
                    // // entry = time, "Time", float, "%.3f"
                    // logFormat.add(datalogEntry(t.get(1), t.get(2), t.get(3),
                    // t.get(4)));
                    // break;
                    // }
                    // msg.send(MsgInfo.mWarning,
                    // MessageFormat.format("Unexpected or unsupported token '{0}'",
                    // t.get(0)));
                    break;

                // ---------------------------------------------------------------------
                case blockDefaults: // -------------------------------------------------
                    if (t.get(0).equals("engineDisplacement"))
                    { /* Dreqfuel::cid = t.v(1); */
                        break;
                    }
                    if (t.get(0).equals("injectorFlow"))
                    { /* Dreqfuel::injectorFlow = t.v(1); */
                        break;
                    }
                    msg.send(MsgInfo.mWarning, MessageFormat.format(
                            "Unexpected or unsupported token '{0}'", t.get(0)));
                    break;
                

                }

                // End while
            }
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RepositoryException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {

        }
    }
    

    Map<String, Boolean> setList = new HashMap<String, Boolean>();

    private void varSet(String key, boolean value)
    {
        setList.put(key, value);
    }

    private boolean isVarSet(String key)
    {
        Boolean b = setList.get(key);
        if (b == null)
            return false;
        else
            return b;
    }

    private boolean isVarDef(String setName, MsgInfo msg)
    {

        Boolean b = setList.get(setName);

        if (b == null)
        {
            msg.send(MsgInfo.mWarning,
                    MessageFormat.format(
                            "Conditional check references undefined value '{0}'.     \n\n"
                                    + "Only first reference will be reported.",
                            setName));
            varSet(setName, false);
            return false;

        }
        return true;
    }

    private void ochInit(String sVetunevalue, int uveTuneValue2)
    {
        // TODO Auto-generated method stub

    }

    void checkPageCount(String item, int n, MsgInfo msg)
    {
        if (n != mdb.cDesc.nPages())
        {
            msg.send(
                    MsgInfo.mError | MsgInfo.mExit,
                    MessageFormat
                            .format("The number of {0} values ({1}) isn't consistent with     \n"
                                    + "\t\"nPages = {2}\" in the [Constants] definitions.\n\n"
                                    + "MegaTune is terminating.", item, n,
                                    mdb.cDesc.nPages()));
        }
    }

    void frontEGO(Tokenizer t)
    {
        lofEGO = t.v(1);
        hifEGO = t.v(2);
        rdfEGO = t.v(3);
    } // LED meter

    void tuneVEB(Tokenizer t)
    {
        lotVEB = t.v(1);
        hitVEB = t.v(2);
    }

    void tuneCD(Tokenizer t)
    {
        vatCD = (int) t.v(1);
    }

    void tuneSD(Tokenizer t)
    {
        vatSD = (int) t.v(1);
    }

    void tuneEGO(Tokenizer t)
    {
        lotEGO = t.v(1);
        hitEGO = t.v(2);
        rdtEGO = t.v(3);
    } // LED meter

}
