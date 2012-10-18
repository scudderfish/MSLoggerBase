package uk.org.smithfamily.utils.normaliser;

import java.util.regex.Pattern;

/**
 * RegEx patterns used to parse MegaSquirt INI firmware file
 */
public class Patterns
{

    static Pattern bits                = Pattern.compile("(\\w*)\\s*=\\s*bits\\s*,\\s*(.*?),\\s*(.*?),*\\s*\\[(\\d):(.*?)\\](\\s*,\\s*(\".*\"))*.*");
    static Pattern scalar              = Pattern.compile("(\\w*)\\s*=\\s*scalar\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*\"(.*)\"\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*");
    static Pattern expr                = Pattern.compile("(\\w*)\\s*=\\s*\\{\\s*(.*)\\s*\\}.*");
    static Pattern ternary             = Pattern.compile("(.*?)\\?(.*)");
    static Pattern log                 = Pattern.compile("\\s*entry\\s*=\\s*(\\w+)\\s*,\\s*\"(.*?)\",\\s*(.*?),\\s*\"(.*?)\"");
    static Pattern binary              = Pattern.compile("(.*)0b([01]{8})(.*)");
    
    /*
     * Comms
     */
    static Pattern queryCommand        = Pattern.compile("\\s*queryCommand\\s*=\\s*\"(.*)\".*");
    static Pattern signature           = Pattern.compile("\\s*signature\\s*=\\s*\"(.*)\".*");
    static Pattern byteSignature       = Pattern.compile("\\s*signature\\s*=\\s*(\\d*).*");
    static Pattern ochGetCommand       = Pattern.compile("\\s*ochGetCommand\\s*=\\s*\"(.*)\".*");
    static Pattern ochBlockSize        = Pattern.compile("\\s*ochBlockSize\\s*=\\s*(\\d*).*");
    static Pattern pageActivationDelay = Pattern.compile("\\s*pageActivationDelay\\s*=\\s*(\\d*).*");
    static Pattern interWriteDelay     = Pattern.compile("\\s*interWriteDelay\\s*=\\s*(\\d*).*");
    
    /*
     * Gauge
     */
    static Pattern defaultGauge        = Pattern.compile("\\s*gauge\\d\\s*=\\s*(\\w*)");
    static Pattern gauge               = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*,\\s*\"(.*?)\"\\s*,\\s*\"(.*?)\"\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)");
    
    /*
     * Page
     */
    static Pattern page                = Pattern.compile("\\s*page\\s*=\\s*(\\d*)");
    // static Pattern nPages           = Pattern.compile("\\s*nPages\\s*=\\s*(\\d*)");
    static Pattern pageSize            = Pattern.compile("\\s*pageSize\\s*=\\s*(.*)");
    static Pattern pageIdentifier      = Pattern.compile("\\s*pageIdentifier\\s*=\\s*(.*)");
    static Pattern pageActivate        = Pattern.compile("\\s*pageActivate\\s*=\\s*(.*)");
    static Pattern pageReadCommand     = Pattern.compile("\\s*pageReadCommand\\s*=\\s*(.*)");
    static Pattern pageValueWrite      = Pattern.compile("\\s*pageValueWrite\\s*=\\s*(.*)");
    static Pattern pageChunkWrite      = Pattern.compile("\\s*pageChunkWrite\\s*=\\s*(.*)");
    
    /*
     * Constant
     */
    static Pattern constantScalar      = Pattern.compile("\\s*(\\w*)\\s*=\\s*(scalar)\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*\\\"(.*)\\\"\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*(.*?)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*).*");
    static Pattern constantSimple      = Pattern.compile("\\s*(\\w*)\\s*=\\s*(scalar)\\s*,\\s*(.*?)\\s*,\\s*(\\d*)\\s*,\\s*\\\"(.*)\\\"\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*).*");
    static Pattern constantArray       = Pattern.compile("\\s*(\\w*)\\s*=\\s*(array)\\s*,\\s*(.*?)\\s*,\\s*(\\d*),\\s*(\\[.*?\\])\\s*,\\s*\\\"(.*?)\\\"\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*([-+]?\\d*\\.?\\d*)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*([-+]?\\d*\\.?\\d*)");
    static Pattern booleanConvert      = Pattern.compile("(.*?==\\s*\\d+)\\s*(\\).*?)");
    static Pattern boolAsInt           = Pattern.compile("(.*\\(.*?[<>=].*?\\))(.*?[\\*/].*)");

    /*
     *  Menu
     */
    static Pattern menuDialog          = Pattern.compile("\\s*menuDialog\\s*=\\s*(.*)");
    static Pattern menu                = Pattern.compile("\\s*menu\\s*=\\s*\"(.*)\"");
    static Pattern subMenu             = Pattern.compile("\\s*subMenu\\s*=\\s*(.*?)\\s*(,\\s*\"(.*?)\"\\s*)?(,\\s*(\\d*)\\s*)?(,\\s*(\\{.*\\})\\s*)?");

    /*
     * Controller Commands
     */
    
    static Pattern controllerCommands       = Pattern.compile("\\s*(.*?)\\s*=\\s*\"(.*)\"");
    
    /*
     * User Defined
     */    
    static Pattern dialog                   = Pattern.compile("\\s*dialog\\s*=\\s*(.*?)\\s*(,\\s*\"(.*?)\")?(,\\s*(.*?)\\s*)?");
    static Pattern dialogField              = Pattern.compile("\\s*field\\s*=\\s*\"(.*)\"(,\\s*(.*?))?(,\\s*(\\{.*\\})\\s*)?");
    static Pattern dialogDisplayOnlyField   = Pattern.compile("\\s*displayOnlyField\\s*=\\s*\"(.*)\"(,\\s*(.*?))?(,\\s*(\\{.*\\})\\s*)?");
    static Pattern dialogPanel              = Pattern.compile("\\s*panel\\s*=\\s*(.*?)\\s*(,\\s*(.*?)\\s*)?(,\\s*(.*?)\\s*)?");

    /*
     * Table Editor
     */
    static Pattern tablEditorTable          = Pattern.compile("\\s*table\\s*=\\s*(.*)\\s*,\\s*(.*)\\s*,\\s*\"(.*)\"\\s*,\\s*(\\d+)\\s*");
    static Pattern tablEditorXBins          = Pattern.compile("\\s*xBins\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*(,\\s*(.*))?");
    static Pattern tablEditorYBins          = Pattern.compile("\\s*yBins\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*(,\\s*(.*))?");
    static Pattern tablEditorZBins          = Pattern.compile("\\s*zBins\\s*=\\s*(.*)\\s*");
    static Pattern tablEditorUpDownLabel    = Pattern.compile("\\s*upDownLabel\\s*=\\s*\"(.*?)\"\\s*,\\s*\"(.*)\"\\s*");
    static Pattern tablEditorGridHeight     = Pattern.compile("\\s*gridHeight\\s*=\\s*(.*?)\\s*");
    static Pattern tablEditorGridOrient     = Pattern.compile("\\s*gridOrient\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)\\s*");

    /*
     * Curve Editor
     */
    static Pattern curve                    = Pattern.compile("\\s*curve\\s*=\\s*(.*?)\\s*,\\s*\"(.*)\".*");
    static Pattern curveColumnLabel         = Pattern.compile("\\s*columnLabel\\s*=\\s*\"(.*?)\"\\s*,\\s*\"(.*)\"");
    static Pattern curveXAxis               = Pattern.compile("\\s*xAxis\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)\\s*");
    static Pattern curveYAxis               = Pattern.compile("\\s*yAxis\\s*=\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)\\s*");
    static Pattern curveXBins               = Pattern.compile("\\s*xBins\\s*=\\s*(.*?)\\s*(,\\s*(.*?))?\\s*(,\\s*(.*))?\\s*");
    static Pattern curveYBins               = Pattern.compile("\\s*yBins\\s*=\\s*(.*)\\s*");
    static Pattern curveGauge               = Pattern.compile("\\s*gauge\\s*=\\s*(.*)\\s*");
    static Pattern curveLineLabel           = Pattern.compile("\\s*lineLabel\\s*=\\s*\"(.*)\"");


    static Pattern int2boolean              = Pattern.compile("(\\w*)");
    
    // Used to split by comma (or space for incorrect INI) when comma or space are not between double quotes
    static Pattern bitsValues               = Pattern.compile("[\\s,]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
}
