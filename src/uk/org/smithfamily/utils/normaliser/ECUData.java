package uk.org.smithfamily.utils.normaliser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.utils.normaliser.curveeditor.CurveTracker;
import uk.org.smithfamily.utils.normaliser.menu.MenuTracker;
import uk.org.smithfamily.utils.normaliser.tableeditor.TableTracker;
import uk.org.smithfamily.utils.normaliser.userdefined.UserDefinedTracker;

public class ECUData
{
	private List<String> runtime = new ArrayList<String>();
	private List<String> logHeader = new ArrayList<String>();
	private List<String> logRecord = new ArrayList<String>();
	private List<String> gaugeDef = new ArrayList<String>();
	private List<TableTracker> tableDefs = new ArrayList<TableTracker>();
	private List<CurveTracker> curveDefs = new ArrayList<CurveTracker>();
	private List<MenuTracker> menuDefs = new ArrayList<MenuTracker>();
	private List<UserDefinedTracker> dialogDefs = new ArrayList<UserDefinedTracker>();
	private Map<String, String> fieldControlExpressions;
	private Map<String, String> menuControlExpressions;
	private Map<String, String> runtimeVars;
	private Map<String, String> evalVars;
	private Map<String, String> constantVars;
	private List<String> defaults;
	private List<String> requiresPowerCycle;
	private Set<String> flags;
	private String fingerprintSource;
	private ArrayList<String> gaugeDoc;
	private ArrayList<Constant> constants;
	private ArrayList<String> pageSizes;
	private ArrayList<String> pageIdentifiers;
	private ArrayList<String> pageActivateCommands;
	private ArrayList<String> pageReadCommands;
	private String signatureDeclaration;
	private String queryCommandStr;
	private String ochGetCommandStr;
	private String ochBlockSizeStr;
	private ArrayList<String> defaultGauges;
	private boolean isCRC32Protocol;
	private int currentPage = 0;
	private int interWriteDelay;
	private int pageActivationDelayVal;
	private String classSignature;

	public List<String> getRuntime()
	{
		return runtime;
	}

	public void setRuntime(List<String> runtime)
	{
		this.runtime = runtime;
	}

	public List<String> getLogHeader()
	{
		return logHeader;
	}

	public void setLogHeader(List<String> logHeader)
	{
		this.logHeader = logHeader;
	}

	public List<String> getLogRecord()
	{
		return logRecord;
	}

	public void setLogRecord(List<String> logRecord)
	{
		this.logRecord = logRecord;
	}

	public List<String> getGaugeDef()
	{
		return gaugeDef;
	}

	public void setGaugeDef(List<String> gaugeDef)
	{
		this.gaugeDef = gaugeDef;
	}

	public void reset()
	{
		runtime = new ArrayList<String>();
		logHeader = new ArrayList<String>();
		logRecord = new ArrayList<String>();
		fieldControlExpressions = new HashMap<String, String>();
		menuControlExpressions = new HashMap<String, String>();
		runtimeVars = new HashMap<String, String>();
		evalVars = new HashMap<String, String>();
		constantVars = new HashMap<String, String>();
		defaults = new ArrayList<String>();
		requiresPowerCycle = new ArrayList<String>();
		constants = new ArrayList<Constant>();
		flags = new HashSet<String>();
		gaugeDef = new ArrayList<String>();
		gaugeDoc = new ArrayList<String>();
		defaultGauges = new ArrayList<String>();
		pageActivateCommands = new ArrayList<String>();
		pageIdentifiers = new ArrayList<String>();
		tableDefs = new ArrayList<TableTracker>();
		curveDefs = new ArrayList<CurveTracker>();
		menuDefs = new ArrayList<MenuTracker>();
		dialogDefs = new ArrayList<UserDefinedTracker>();
		fingerprintSource = "";
		currentPage = 0;
		isCRC32Protocol = false;
		constants = new ArrayList<Constant>();
		// Default for those who don't define it. I'm looking at you
		// megasquirt-I.ini!
		ochGetCommandStr = "byte [] ochGetCommand = new byte[]{'A'};";
		evalVars.put("veTuneValue", "int");
	}

	public Map<String, String> getRuntimeVars()
	{
		return runtimeVars;
	}

	public void setRuntimeVars(Map<String, String> runtimeVars)
	{
		this.runtimeVars = runtimeVars;
	}

	public Map<String, String> getEvalVars()
	{
		return evalVars;
	}

	public void setEvalVars(Map<String, String> evalVars)
	{
		this.evalVars = evalVars;
	}

	public Map<String, String> getConstantVars()
	{
		return constantVars;
	}

	public void setConstantVars(Map<String, String> constantVars)
	{
		this.constantVars = constantVars;
	}

	public List<String> getDefaults()
	{
		return defaults;
	}

	public void setDefaults(List<String> defaults)
	{
		this.defaults = defaults;
	}
	
	public List<String> getRequiresPowerCycle()
	{
	    return requiresPowerCycle;
	}
	
	public void setRequiresPowerCycle(List<String> requiresPowerCycle)
	{
	    this.requiresPowerCycle = requiresPowerCycle;
	}

	public Set<String> getFlags()
	{
		return flags;
	}

	public void setFlags(Set<String> flags)
	{
		this.flags = flags;
	}

	public String getFingerprintSource()
	{
		return fingerprintSource;
	}

	public void setFingerprintSource(String fingerprintSource)
	{
		this.fingerprintSource = fingerprintSource;
	}

	public ArrayList<String> getGaugeDoc()
	{
		return gaugeDoc;
	}

	public void setGaugeDoc(ArrayList<String> gaugeDoc)
	{
		this.gaugeDoc = gaugeDoc;
	}

	public ArrayList<Constant> getConstants()
	{
		return constants;
	}

	public void setConstants(ArrayList<Constant> constants)
	{
		this.constants = constants;
	}

	public ArrayList<String> getPageSizes()
	{
		return pageSizes;
	}

	public void setPageSizes(ArrayList<String> pageSizes)
	{
		this.pageSizes = pageSizes;
	}

	public ArrayList<String> getPageIdentifiers()
	{
		return pageIdentifiers;
	}

	public void setPageIdentifiers(ArrayList<String> pageIdentifiers)
	{
		this.pageIdentifiers = pageIdentifiers;
	}

	public ArrayList<String> getPageActivateCommands()
	{
		return pageActivateCommands;
	}

	public void setPageActivateCommands(ArrayList<String> pageActivateCommands)
	{
		this.pageActivateCommands = pageActivateCommands;
	}

	public ArrayList<String> getPageReadCommands()
	{
		return pageReadCommands;
	}

	public void setPageReadCommands(ArrayList<String> pageReadCommands)
	{
		this.pageReadCommands = pageReadCommands;
	}

	public String getSignatureDeclaration()
	{
		return signatureDeclaration;
	}

	public void setSignatureDeclaration(String signatureDeclaration)
	{
		this.signatureDeclaration = signatureDeclaration;
	}

	public String getQueryCommandStr()
	{
		return queryCommandStr;
	}

	public void setQueryCommandStr(String queryCommandStr)
	{
		this.queryCommandStr = queryCommandStr;
	}

	public String getOchGetCommandStr()
	{
		return ochGetCommandStr;
	}

	public void setOchGetCommandStr(String ochGetCommandStr)
	{
		this.ochGetCommandStr = ochGetCommandStr;
	}

	public String getOchBlockSizeStr()
	{
		return ochBlockSizeStr;
	}

	public void setOchBlockSizeStr(String ochBlockSizeStr)
	{
		this.ochBlockSizeStr = ochBlockSizeStr;
	}

	public ArrayList<String> getDefaultGauges()
	{
		return defaultGauges;
	}

	public void setDefaultGauges(ArrayList<String> defaultGauges)
	{
		this.defaultGauges = defaultGauges;
	}

	public boolean isCRC32Protocol()
	{
		return isCRC32Protocol;
	}

	public void setCRC32Protocol(boolean isCRC32Protocol)
	{
		this.isCRC32Protocol = isCRC32Protocol;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getInterWriteDelay()
	{
		return interWriteDelay;
	}

	public void setInterWriteDelay(int interWriteDelay)
	{
		this.interWriteDelay = interWriteDelay;
	}

	public int getPageActivationDelayVal()
	{
		return pageActivationDelayVal;
	}

	public void setPageActivationDelayVal(int pageActivationDelayVal)
	{
		this.pageActivationDelayVal = pageActivationDelayVal;
	}

	public String getClassSignature()
	{
		return classSignature;
	}

	public void setClassSignature(String classSignature)
	{
		this.classSignature = classSignature;
	}

	public List<TableTracker> getTableDefs()
	{
		return tableDefs;
	}

	public void setTableDefs(List<TableTracker> tableDefs)
	{
		this.tableDefs = tableDefs;
	}

	public List<CurveTracker> getCurveDefs()
	{
		return curveDefs;
	}

	public void setCurveDefs(List<CurveTracker> curveDefs)
	{
		this.curveDefs = curveDefs;
	}

	public List<MenuTracker> getMenuDefs()
	{
		return menuDefs;
	}

	public void setMenuDefs(List<MenuTracker> menuDefs)
	{
		this.menuDefs = menuDefs;
	}

	public List<UserDefinedTracker> getDialogDefs()
	{
		return dialogDefs;
	}

	public void setDialogDefs(List<UserDefinedTracker> dialogDefs)
	{
		this.dialogDefs = dialogDefs;
	}

    public Map<String, String> getFieldControlExpressions()
    {
        return fieldControlExpressions;
    }
    
    public Map<String, String> getMenuControlExpressions()
    {
        return menuControlExpressions;
    }
}
