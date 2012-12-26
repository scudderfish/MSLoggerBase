package uk.org.smithfamily.utils.normaliser;

import java.util.regex.Pattern;

/**
 * RegEx patterns used to parse MegaSquirt INI firmware file
 */
public class Patterns
{

    public static Pattern bits                = Pattern.compile("(\\w*)\\s*=\\s*bits\\s*,\\s*(.*?),\\s*(.*?),*\\s*\\[(\\d):(.*?)\\](\\s*,\\s*(\".*\"))*.*");
    public static Pattern scalar              = Pattern.compile("(\\w*)\\s*=\\s*scalar\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*\"(.*)\"\\s*,\\s*(.*?)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*");
    public static Pattern expr                = Pattern.compile("(\\w*)\\s*=\\s*\\{\\s*(.*)\\s*\\}.*");
    public static Pattern ternary             = Pattern.compile("(.*?)\\?(.*)");
    public static Pattern log                 = Pattern.compile("\\s*entry\\s*=\\s*(\\w+)\\s*,\\s*\"(.*?)\",\\s*(.*?),\\s*\"(.*?)\"");
    public static Pattern binary              = Pattern.compile("(.*)0b([01]{8})(.*)");
    
    /*
     * Comms
     */
    public static Pattern queryCommand        = Pattern.compile("\\s*queryCommand\\s*=\\s*\"(.*)\".*");
    public static Pattern signature           = Pattern.compile("\\s*signature\\s*=\\s*\"(.*)\".*");
    public static Pattern byteSignature       = Pattern.compile("\\s*signature\\s*=\\s*(\\d*).*");
    public static Pattern ochGetCommand       = Pattern.compile("\\s*ochGetCommand\\s*=\\s*\"(.*)\".*");
    public static Pattern ochBlockSize        = Pattern.compile("\\s*ochBlockSize\\s*=\\s*(\\d*).*");
    public static Pattern pageActivationDelay = Pattern.compile("\\s*pageActivationDelay\\s*=\\s*(\\d*).*");
    public static Pattern interWriteDelay     = Pattern.compile("\\s*interWriteDelay\\s*=\\s*(\\d*).*");
    
    /*
     * Gauge
     */
    public static Pattern defaultGauge        = Pattern.compile("\\s*gauge\\d\\s*=\\s*(\\w*)");
    public static Pattern gauge               = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*,\\s*\"(.*?)\"\\s*,\\s*\"(.*?)\"\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)");
    
    /*
     * Page
     */
    public static Pattern page                = Pattern.compile("\\s*page\\s*=\\s*(\\d*)");
    // public static Pattern nPages           = Pattern.compile("\\s*nPages\\s*=\\s*(\\d*)");
    public static Pattern pageSize            = Pattern.compile("\\s*pageSize\\s*=\\s*(.*)");
    public static Pattern pageIdentifier      = Pattern.compile("\\s*pageIdentifier\\s*=\\s*(.*)");
    public static Pattern pageActivate        = Pattern.compile("\\s*pageActivate\\s*=\\s*(.*)");
    public static Pattern pageReadCommand     = Pattern.compile("\\s*pageReadCommand\\s*=\\s*(.*)");
    public static Pattern pageValueWrite      = Pattern.compile("\\s*pageValueWrite\\s*=\\s*(.*)");
    public static Pattern pageChunkWrite      = Pattern.compile("\\s*pageChunkWrite\\s*=\\s*(.*)");
    
    /*
     * Constant
     */
    public static Pattern constantScalar      = Pattern.compile("\\s*(\\w*)\\s*=\\s*(scalar)\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*\\\"(.*)\\\"\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*(.*?)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*).*");
    public static Pattern constantSimple      = Pattern.compile("\\s*(\\w*)\\s*=\\s*(scalar)\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*\\\"(.*)\\\"\\s*,\\s*(.*?)\\s*,\\s*([-+]?\\d*\\.?\\d*).*");
    public static Pattern constantArray       = Pattern.compile("\\s*(\\w*)\\s*=\\s*(array)\\s*,\\s*(.*?)\\s*,\\s*(\\d*),\\s*(\\[.*?\\])\\s*,\\s*\\\"(.*?)\\\"\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*([-+]?\\d*\\.?\\d*)");
    public static Pattern booleanConvert      = Pattern.compile("(.*?==\\s*\\d+)\\s*(\\).*?)");
    public static Pattern boolAsInt           = Pattern.compile("(.*\\(.*?[<>=].*?\\))(.*?[\\*/].*)");

    /*
     *  Menu
     */
    public static Pattern menuDialog          = Pattern.compile("\\s*menuDialog\\s*=\\s*(.*)");
    public static Pattern menu                = Pattern.compile("\\s*menu\\s*=\\s*\"(.*)\"");
    public static Pattern subMenu             = Pattern.compile("\\s*subMenu\\s*=\\s*(.*?)\\s*(,\\s*\"(.*?)\"\\s*)?(,\\s*(\\d*)\\s*)?(,\\s*(\\{.*\\})\\s*)?");

    /*
     * Controller Commands
     */
    
    public static Pattern controllerCommands       = Pattern.compile("\\s*(.*?)\\s*=\\s*\"(.*)\"");
    
    /*
     * User Defined
     */    
    public static Pattern dialog                   = Pattern.compile("\\s*dialog\\s*=\\s*(.*?)\\s*(,\\s*\"(.*?)\")?(,\\s*(.*?)\\s*)?");
    public static Pattern dialogField              = Pattern.compile("\\s*field\\s*=\\s*\"(.*)\"(,\\s*(.*?))?(,\\s*(\\{.*\\})\\s*)?");
    public static Pattern dialogDisplayOnlyField   = Pattern.compile("\\s*displayOnlyField\\s*=\\s*\"(.*)\"(,\\s*(.*?))?(,\\s*(\\{.*\\})\\s*)?");
    public static Pattern dialogPanel              = Pattern.compile("\\s*panel\\s*=\\s*(.*?)\\s*(,\\s*(.*?)\\s*)?(,\\s*(.*?)\\s*)?");
    public static Pattern commandButton            = Pattern.compile("\\s*commandButton\\s*=\\s*\"(.*)\"(,\\s*(.*?))?(,\\s*(\\{.*\\})\\s*)?(,\\s*(.*?)\\s*)?");
    
    /*
     * Table Editor
     */
    public static Pattern tablEditorTable          = Pattern.compile("\\s*table\\s*=\\s*(.*)\\s*,\\s*(.*)\\s*,\\s*\"(.*)\"\\s*,\\s*(\\d+)\\s*");
    public static Pattern tablEditorXBins          = Pattern.compile("\\s*xBins\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*(,\\s*(.*))?");
    public static Pattern tablEditorYBins          = Pattern.compile("\\s*yBins\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*(,\\s*(.*))?");
    public static Pattern tablEditorZBins          = Pattern.compile("\\s*zBins\\s*=\\s*(.*)\\s*");
    public static Pattern tablEditorUpDownLabel    = Pattern.compile("\\s*upDownLabel\\s*=\\s*\"(.*?)\"\\s*,\\s*\"(.*)\"\\s*");
    public static Pattern tablEditorGridHeight     = Pattern.compile("\\s*gridHeight\\s*=\\s*(.*?)\\s*");
    public static Pattern tablEditorGridOrient     = Pattern.compile("\\s*gridOrient\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)\\s*");

    /*
     * Curve Editor
     */
    public static Pattern curve                    = Pattern.compile("\\s*curve\\s*=\\s*(.*?)\\s*,\\s*\"(.*)\".*");
    public static Pattern curveColumnLabel         = Pattern.compile("\\s*columnLabel\\s*=\\s*\"(.*?)\"\\s*,\\s*\"(.*)\"");
    public static Pattern curveXAxis               = Pattern.compile("\\s*xAxis\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)\\s*");
    public static Pattern curveYAxis               = Pattern.compile("\\s*yAxis\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)\\s*");
    public static Pattern curveXBins               = Pattern.compile("\\s*xBins\\s*=\\s*(.*?)\\s*(,\\s*(.*?))?\\s*(,\\s*(.*))?\\s*");
    public static Pattern curveYBins               = Pattern.compile("\\s*yBins\\s*=\\s*(.*)\\s*");
    public static Pattern curveGauge               = Pattern.compile("\\s*gauge\\s*=\\s*(.*)\\s*");
    public static Pattern curveLineLabel           = Pattern.compile("\\s*lineLabel\\s*=\\s*\"(.*)\"");


    public static Pattern int2boolean              = Pattern.compile("(\\w*)");
    
    // Used to split by comma (or space for incorrect INI) when comma or space are not between double quotes
    public static Pattern bitsValues               = Pattern.compile("[\\s,]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
}
