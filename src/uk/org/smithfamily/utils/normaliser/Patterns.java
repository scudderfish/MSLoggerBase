package uk.org.smithfamily.utils.normaliser;

import java.util.regex.Pattern;

public class Patterns
{

    static Pattern bits                = Pattern.compile("(\\w*)\\s*=\\s*bits\\s*,\\s*(.*?),\\s*(.*?),*\\s*\\[(\\d):(.*)\\].*");
    static Pattern scalar              = Pattern.compile("(\\w*)\\s*=\\s*scalar\\s*,\\s*([U|S]\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(.*,.*)");
    static Pattern expr                = Pattern.compile("(\\w*)\\s*=\\s*\\{\\s*(.*)\\s*\\}.*");
    static Pattern ternary             = Pattern.compile("(.*?)\\?(.*)");
    static Pattern log                 = Pattern.compile("\\s*entry\\s*=\\s*(\\w+)\\s*,\\s*\"(.*?)\",.*");
    static Pattern binary              = Pattern.compile("(.*)0b([01]{8})(.*)");
    static Pattern gauge               = Pattern
                                               .compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*,\\s*\"(.*?)\"\\s*,\\s*\"(.*?)\"\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)");

    static Pattern queryCommand        = Pattern.compile("\\s*queryCommand\\s*=\\s*\"(.*)\".*");
    static Pattern signature           = Pattern.compile("\\s*signature\\s*=\\s*\"(.*)\".*");
    static Pattern byteSignature       = Pattern.compile("\\s*signature\\s*=\\s*(\\d*).*");
    static Pattern ochGetCommand       = Pattern.compile("\\s*ochGetCommand\\s*=\\s*\"(.*)\".*");
    static Pattern ochBlockSize        = Pattern.compile("\\s*ochBlockSize\\s*=\\s*(\\d*).*");
    static Pattern pageActivationDelay = Pattern.compile("\\s*pageActivationDelay\\s*=\\s*(\\d*).*");
    static Pattern interWriteDelay     = Pattern.compile("\\s*interWriteDelay\\s*=\\s*(\\d*).*");
    static Pattern defaultGauge        = Pattern.compile("\\s*gauge\\d\\s*=\\s*(\\w*)");
    static Pattern page                = Pattern.compile("\\s*page\\s*=\\s*(\\d*)");
    // private static Pattern nPages =
    // Pattern.compile("\\s*nPages\\s*=\\s*(\\d*)");
    static Pattern pageSize            = Pattern.compile("\\s*pageSize\\s*=\\s*(.*)");
    static Pattern pageIdentifier      = Pattern.compile("\\s*pageIdentifier\\s*=\\s*(.*)");
    static Pattern pageActivate        = Pattern.compile("\\s*pageActivate\\s*=\\s*(.*)");
    static Pattern pageReadCommand     = Pattern.compile("\\s*pageReadCommand\\s*=\\s*(.*)");

    static Pattern constantScalar      = Pattern
                                               .compile("\\s*(\\w*)\\s*=\\s*(scalar)\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*\\\"(.*)\\\"\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*).*");
    static Pattern constantSimple      = Pattern
                                               .compile("\\s*(\\w*)\\s*=\\s*(scalar)\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*\\\"(.*)\\\"\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*).*");
    // private static Pattern constantArray = Pattern
    // .compile("\\s*(\\w*)\\s*=\\s*(\\w*)\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*(.*)\\s*,\\s*\\\"(.*)\\\"\\s*,\\s*([-+]?\\d*.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)");

    static Pattern booleanConvert      = Pattern.compile("(.*?==\\s*\\d+)\\s*(\\).*?)");
    static Pattern boolAsInt           = Pattern.compile("(.*\\(.*?[<>=].*?\\))(.*?[\\*/].*)");
}
