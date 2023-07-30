package uk.org.smithfamily.utils.normaliser;

import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import uk.org.smithfamily.mslogger.ecuDef.*;
import uk.org.smithfamily.utils.normaliser.controllercommand.ControllerCommand;
import uk.org.smithfamily.utils.normaliser.curveeditor.CurveItem;
import uk.org.smithfamily.utils.normaliser.curveeditor.CurveTracker;
import uk.org.smithfamily.utils.normaliser.menu.*;
import uk.org.smithfamily.utils.normaliser.tableeditor.TableItem;
import uk.org.smithfamily.utils.normaliser.tableeditor.TableTracker;
import uk.org.smithfamily.utils.normaliser.userdefined.*;

public class Output
{
    public static final String TAB = "    ";
    private static final int MAX_LINES = 100;
    private static Set<String> alwaysInt = new HashSet<String>(Arrays.asList(new String[] {}));
    private static Set<String> alwaysDouble = new HashSet<String>(Arrays.asList(new String[] { "pulseWidth", "throttle", "accDecEnrich", "accDecEnrichPcnt", "accEnrichPcnt", "accEnrichMS", "decEnrichPcnt", "decEnrichMS", "time", "egoVoltage",
            "egoVoltage2", "egoCorrection", "veCurr", "lambda", "TargetLambda" }));

    private static List<String> menuDialogs;

    public static void outputGaugeDoc(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println("/*");
        for (final String gauge : ecuData.getGaugeDoc())
        {
            writer.println(gauge);
        }

        writer.println("*/");
    }

    public static void outputConstructor(final ECUData ecuData, final PrintWriter writer, final String className)
    {
        writer.println(TAB + "private MSControllerInterface parent;");
        writer.println(TAB + "private MSUtilsInterface utils;");
        writer.println(TAB + "public " + className + "(MSControllerInterface parent,MSUtilsInterface utils)");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "this.parent = parent;");
        writer.println(TAB + TAB + "this.utils  = utils;");
        writer.println(TAB + TAB + "initOutputChannels();");
        writer.println(TAB + "}");
        writer.println(TAB + "private double table(double x,String t)");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "return parent.table(x,t);");
        writer.println(TAB + "}");
        writer.println(TAB + "private double round(double x)");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "return parent.round(x);");
        writer.println(TAB + "}");
        writer.println(TAB + "private double tempCvt(double x)");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "return parent.tempCvt(x);");
        writer.println(TAB + "}");
        writer.println(TAB + "private double timeNow()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "return parent.timeNow();");
        writer.println(TAB + "}");
    }

    /**
     * This is nasty. We need to have a set of methods to init the constants as there is a hard limit of 64K on the size of a method, and MS3 will
     * break that. We also need to ensure that if there is any preprocessor type logic that we don't stop and start the next method in the middle of
     * it.
     * 
     * @param ecuData
     * @param writer
     */
    public static void outputFlagsAndConstants(final ECUData ecuData, final PrintWriter writer)
    {
        int constantMethodCount = 0;
        int lineCount = 0;
        int bracketNesting = 0;
        boolean needDeclaration = true;
        int lookahead = 3;
        for (final Constant c : ecuData.getConstants())
        {
            if (needDeclaration)
            {
                constantMethodCount++;
                writer.println(TAB + "private void initConstants" + constantMethodCount + "()\n" + TAB + "{\n");
                needDeclaration = false;
            }
            if ((bracketNesting == 0) && (lookahead > 0))
            {
                lookahead--;
            }
            lineCount++;

            if (c.getName().contains("{"))
            {
                bracketNesting++;
            }
            if (c.getName().contains("}"))
            {
                bracketNesting--;
            }

            if ("PREPROC".equals(c.getType()))
            {
                writer.println(TAB + TAB + c.getName());
                lookahead = 3;
            }
            else
            {
                writer.println(TAB + TAB + "constants.put(\"" + c.getName() + "\", new " + c.toString() + ");");
            }

            if ((lineCount > MAX_LINES) && (bracketNesting == 0) && (lookahead == 0))
            {
                writer.println(TAB + "}\n");
                needDeclaration = true;
                lineCount = 0;
            }
        }
        if (!needDeclaration)
        {
            writer.println(TAB + "}\n");
        }

        writer.println(TAB + "@Override");
        writer.println(TAB + "public void setFlags()");
        writer.println(TAB + "{");

        final Map<String, String> vars = ecuData.getConstantVars();

        for (final String flag : ecuData.getFlags())
        {
            // INI_VERSION_2 should always be true
            if (flag.equals("INI_VERSION_2"))
            {
                writer.println(TAB + TAB + "INI_VERSION_2 = true;");
            }
            // MEMPAGES, LOGPAGES and MSLVV_COMPATIBLE should always be false
            else if (flag.equals("MEMPAGES") || flag.equals("LOGPAGES") || flag.equals("MSLVV_COMPATIBLE"))
            {
                writer.println(TAB + TAB + flag + " = false;");
            }
            else if (flag.equals("SPEED_DENSITY"))
            {
                String varName = "algorithm1";

                // MS1 B&G
                if (vars.containsKey("algorithm"))
                {
                    varName = "algorithm";
                }

                writer.println(TAB + TAB + "SPEED_DENSITY = (" + varName + " == 1);");
            }
            else if (flag.equals("ALPHA_N"))
            {
                String varName = "algorithm1";

                // MS1 B&G
                if (vars.containsKey("algorithm"))
                {
                    varName = "algorithm";
                }

                writer.println(TAB + TAB + "ALPHA_N = (" + varName + " == 2);");
            }
            else if (flag.equals("AIR_FLOW_METER"))
            {

                if (vars.containsKey("AFMUse"))
                {
                    writer.println(TAB + TAB + "AIR_FLOW_METER = (AFMUse == 2);");
                }
                // MS1 B&G doesn't support MAF
                else
                {
                    writer.println(TAB + TAB + "AIR_FLOW_METER = false;");
                }
            }
            else
            {
                writer.println(TAB + TAB + flag + " = parent.isSet(\"" + flag + "\");");
            }
        }
        writer.println(TAB + "}");

        writer.println(TAB + "@Override");
        writer.println(TAB + "public void refreshFlags()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "setFlags();");
        writer.println(TAB + TAB + "initOutputChannels();");
        for (int i = 1; i <= constantMethodCount; i++)
        {
            writer.println(TAB + TAB + "initConstants" + i + "();");
        }
        writer.println(TAB + TAB + "createTableEditors();");
        writer.println(TAB + TAB + "createCurveEditors();");
        writer.println(TAB + TAB + "createMenus();");
        writer.println(TAB + TAB + "createDialogs();");
        writer.println(TAB + "}");

    }

    public static void outputOutputChannels(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println(TAB + "public void initOutputChannels()");
        writer.println(TAB + "{");

        for (final OutputChannel op : ecuData.getOutputChannels())
        {
            if (op.getType().equals("PREPROC"))
            {
                writer.println(TAB + TAB + op.getName());
            }
            else
            {
                writer.println(TAB + TAB + "parent.registerOutputChannel(new " + op.toString() + ");");
            }
        }

        writer.println(TAB + "}");
    }

    public static void outputPackageAndIncludes(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println("package uk.org.smithfamily.mslogger.ecuDef.gen;");
        writer.println("");
        writer.println("import java.io.IOException;");
        writer.println("import java.util.*;");
        writer.println("");
        writer.println("");
        writer.println("import uk.org.smithfamily.mslogger.ecuDef.*;");

    }

    private static String getType(final String name, final Map<String, String> vars)
    {
        String type = vars.get(name);
        if (alwaysInt.contains(name))
        {
            type = "int";
        }
        else if (alwaysDouble.contains(name))
        {
            type = "double";
        }
        return type;
    }

    public static void outputGlobalVars(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println("//Flags");

        final List<String> flags = new ArrayList<String>();
        for (final String flag : ecuData.getFlags())
        {
            if (!flag.equals("INI_VERSION_2") && !flag.equals("MEMPAGES") && !flag.equals("LOGPAGES") && !flag.equals("SPEED_DENSITY") && !flag.equals("ALPHA_N") && !flag.equals("MSLVV_COMPATIBLE") && !flag.equals("AIR_FLOW_METER"))
            {
                flags.add("\"" + flag + "\"");
            }
        }

        writer.println(TAB + "public String[] flags = {" + StringUtils.join(flags, ",") + "};");
        for (final String name : ecuData.getFlags())
        {
            writer.println(TAB + "public boolean " + name + ";");
        }
        writer.println("//Defaults");
        for (final String d : ecuData.getDefaults())
        {
            writer.println(TAB + "public " + d);
        }
        final Map<String, String> vars = new TreeMap<String, String>();
        vars.putAll(ecuData.getRuntimeVars());
        vars.putAll(ecuData.getEvalVars());
        for (final String v : vars.keySet())
        {
            ecuData.getConstantVars().remove(v);
        }
        writer.println("//Variables");
        for (final String name : vars.keySet())
        {
            String type = getType(name, vars);

            // Force secl to be double
            if (name.equals("secl"))
            {
                type = "double";
            }

            writer.println(TAB + "public " + type + " " + name + ";");
        }
        writer.println("\n//Constants");
        for (final String name : ecuData.getConstantVars().keySet())
        {
            final String type = getType(name, ecuData.getConstantVars());
            writer.println(TAB + "public " + type + " " + name + ";");
        }
        writer.println("\n");
        writer.println(TAB + "Map<String,GaugeDetails> gauges = new HashMap<String,GaugeDetails>();\n");
        writer.println(TAB + "private String[] defaultGauges = {");
        boolean first = true;
        for (final String dg : ecuData.getDefaultGauges())
        {
            if (!first)
            {
                writer.println(",");
            }
            first = false;
            writer.print(TAB + TAB + "\"" + dg + "\"");
        }
        writer.println("\n" + TAB + "};");
        writer.println("\n" + TAB + "@Override");
        writer.println(TAB + "public String[] getControlFlags()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "return flags;");
        writer.println(TAB + "}");

    }

    public static void outputRequiresPowerCycle(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println("\n    //Fields that requires power cycle");

        writer.println("    public List<String> getRequiresPowerCycle()");
        writer.println("    {");
        writer.println(TAB + TAB + "List<String> requiresPowerCycle = new ArrayList<String>();");

        for (final String field : ecuData.getRequiresPowerCycle())
        {
            writer.println(TAB + TAB + "requiresPowerCycle.add(\"" + field + "\");");
        }

        writer.println(TAB + TAB + "return requiresPowerCycle;");
        writer.println(TAB + "}\n");
    }

    public static void outputRTCalcs(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println("    @Override");
        writer.println("    public void calculate(byte[] ochBuffer)");
        writer.println("    {");
        for (final String defn : ecuData.getRuntime())
        {
            writer.println(TAB + TAB + defn);
            // System.out.println(defn);
        }
        writer.println(TAB + "}");
    }

    public static void outputLogInfo(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public String getLogHeader()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "StringBuffer b = new StringBuffer();");
        for (final String header : ecuData.getLogHeader())
        {
            writer.println(TAB + TAB + header);
        }
        writer.println(TAB + TAB + "b.append(utils.getLocationLogHeader());");
        writer.println(TAB + TAB + "return b.toString();\n" + TAB + "}\n");
        writer.println(TAB + "@Override");
        writer.println(TAB + "public String getLogRow()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "StringBuffer b = new StringBuffer();");

        for (final String record : ecuData.getLogRecord())
        {
            writer.println(TAB + TAB + record);
        }
        writer.println(TAB + TAB + "b.append(utils.getLocationLogRow());");
        writer.println(TAB + TAB + "return b.toString();\n" + TAB + "}\n");
    }

    static void outputGauges(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void initGauges()");
        writer.println(TAB + "{");
        for (final String gauge : ecuData.getGaugeDef())
        {
            boolean okToWrite = true;
            if (gauge.contains("gauges."))
            {
                final String[] parts = gauge.split(",");
                final String channel = parts[5];
                okToWrite = ecuData.getRuntimeVars().containsKey(channel) || ecuData.getEvalVars().containsKey(channel);
            }
            if (okToWrite)
            {
                writer.println(TAB + TAB + gauge);
            }
        }
        writer.println(TAB + "}\n");
    }

    public static void outputMenus(final ECUData ecuData, final PrintWriter writer)
    {
        menuDialogs = new ArrayList<String>();

        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createMenus()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "Menu m;");
        writer.println(TAB + TAB + "List<Menu> listMenus;");

        boolean isFirstMenu = true;

        String key = "";

        for (final MenuTracker m : ecuData.getMenuDefs())
        {
            for (final Entry<String, List<MenuItem>> menuDialog : m.getItems())
            {
                key = menuDialog.getKey();
                final List<MenuItem> value = menuDialog.getValue();

                for (int i = 0; i < value.size(); i++)
                {
                    if (MenuDefinition.class.isInstance(value.get(i)))
                    {
                        if (!isFirstMenu)
                        {
                            if (menuDialogs.contains(key))
                            {
                                writer.println(TAB + TAB + "menus.get(\"" + key + "\").add(m);");
                            }
                            else
                            {
                                writer.println(TAB + TAB + "listMenus = new ArrayList<Menu>();");
                                writer.println(TAB + TAB + "listMenus.add(m);");
                                writer.println(TAB + TAB + "menus.put(\"" + key + "\", listMenus);");

                                menuDialogs.add(key);
                            }
                        }
                        else
                        {
                            isFirstMenu = false;
                        }
                    }

                    writer.println(TAB + TAB + value.get(i).toString());
                }
            }
        }

        if (menuDialogs.contains(key))
        {
            writer.println(TAB + TAB + "menus.get(\"" + key + "\").add(m);");
        }
        else
        {
            writer.println(TAB + TAB + "listMenus = new ArrayList<Menu>();");
            writer.println(TAB + TAB + "listMenus.add(m);");
            writer.println(TAB + TAB + "menus.put(\"" + key + "\", listMenus);");

            menuDialogs.add(key);
        }

        writer.println(TAB + "}\n");
    }

    public static void outputUserDefined(final ECUData ecuData, final PrintWriter writer)
    {
        // Keep track of indent of preprocessor
        int preprocessorIndent = 0;

        // Keep all the dummy dialogs methods and dump them at the end. Some dialog might get merged with other because
        // they are in the same preproc
        String dummyDialogsMethods = "";

        // Keep track if it's the first dialog of the method or if we already added one
        boolean isFirstDialog = false;

        for (int i = 0; i < ecuData.getDialogDefs().size(); i++)
        {
            final UserDefinedTracker d = ecuData.getDialogDefs().get(i);

            // We are not in a preproc so write the method signature
            if (preprocessorIndent == 0)
            {
                isFirstDialog = true;

                writer.println(TAB + "private void createDialog_" + d.getName() + "()");
                writer.println(TAB + "{");
                writer.println(TAB + TAB + "MSDialog d = null;");
            }
            else
            {
                isFirstDialog = false;
            }

            boolean ignorePreproc = false;

            // If it's the last dialog and we are not in preproc,
            // ignore the preproc, it most likely belong to the other section after [UserDefined] (usually [PortEditor])
            if ((i == (ecuData.getDialogDefs().size() - 1)) && (preprocessorIndent == 0))
            {
                ignorePreproc = true;
            }

            for (final UserDefinedItem j : d.getItems())
            {
                // If it's a preproc, we need to keep track of indent
                if (UserDefinedPreProcessor.class.isInstance(j) && !ignorePreproc)
                {
                    if (j.toString().contains("{"))
                    {
                        preprocessorIndent++;
                    }

                    if (j.toString().contains("}"))
                    {
                        preprocessorIndent--;
                    }
                }

                // Make sure it's not a preproc, or that it's one but we don't ignore them
                if (!UserDefinedPreProcessor.class.isInstance(j) || (UserDefinedPreProcessor.class.isInstance(j) && !ignorePreproc))
                {
                    writer.println(TAB + TAB + StringUtils.repeat(TAB, preprocessorIndent) + j);
                }
            }

            // We are out of the preproc so we won't add the dialog to the same function
            // We can close the current function
            if (preprocessorIndent == 0)
            {
                writer.println(TAB + "}");
            }

            // It wasn't the first dialog of this method, so we will create a dummy method
            if (!isFirstDialog)
            {
                dummyDialogsMethods += TAB + "private void createDialog_" + d.getName() + "(){}\n";
            }
        }

        writer.println(dummyDialogsMethods);

        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createDialogs()");
        writer.println(TAB + "{");

        for (final UserDefinedTracker d : ecuData.getDialogDefs())
        {

            writer.println(TAB + TAB + "createDialog_" + d.getName() + "();");
        }
        writer.println(TAB + "}");
    }

    public static void outputUserDefinedVisibilityFlags(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void setUserDefinedVisibilityFlags()");
        writer.println(TAB + "{");

        for (final String key : ecuData.getFieldControlExpressions().keySet())
        {
            final String expr = ecuData.getFieldControlExpressions().get(key);
            writer.println(String.format(TAB + TAB + "userDefinedVisibilityFlags.put(\"%s\",%s);", key, expr));
        }
        writer.println(TAB + "}");
    }

    public static void outputMenuVisibilityFlags(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void setMenuVisibilityFlags()");
        writer.println(TAB + "{");

        for (final String key : ecuData.getMenuControlExpressions().keySet())
        {
            final String expr = ecuData.getMenuControlExpressions().get(key);
            writer.println(String.format(TAB + TAB + "menuVisibilityFlags.put(\"%s\",%s);", key, expr));
        }
        writer.println(TAB + "}");
    }

    public static void outputTableEditors(final ECUData ecuData, final PrintWriter writer)
    {
        for (final TableTracker t : ecuData.getTableDefs())
        {
            writer.println(TAB + "private void createTableEditor_" + t.getName() + "()");
            writer.println(TAB + "{");
            writer.println(TAB + TAB + "TableEditor t = null;");

            for (final TableItem i : t.getItems())
            {
                writer.println(TAB + TAB + i);
            }
            writer.println(TAB + "}");
        }

        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createTableEditors()");
        writer.println(TAB + "{");

        for (final TableTracker t : ecuData.getTableDefs())
        {
            writer.println(TAB + TAB + "createTableEditor_" + t.getName() + "();");
        }

        writer.println(TAB + "}");
    }

    public static void outputCurves(final ECUData ecuData, final PrintWriter writer)
    {
        for (final CurveTracker c : ecuData.getCurveDefs())
        {
            if ((c.getName() != null) && !c.getName().trim().equals(""))
            {
                writer.println(TAB + "private void createCurveEditor_" + c.getName() + "()");
                writer.println(TAB + "{");
                writer.println(TAB + TAB + "CurveEditor c = null;");

                for (final CurveItem i : c.getItems())
                {
                    writer.println(TAB + TAB + i);
                }
                writer.println(TAB + "}");
            }
        }

        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createCurveEditors()");
        writer.println(TAB + "{");

        for (final CurveTracker c : ecuData.getCurveDefs())
        {
            if ((c.getName() != null) && !c.getName().trim().equals(""))
            {
                writer.println(TAB + TAB + "createCurveEditor_" + c.getName() + "();");
            }
        }

        writer.println(TAB + "}");
    }

    public static void outputLoadConstants(final ECUData ecuData, final PrintWriter writer)
    {
        int pageNo = 0;

        final List<Integer> pageNumbers = new ArrayList<Integer>();

        for (final Constant c : ecuData.getConstants())
        {
            if (c.getPage() != pageNo)
            {
                if (pageNo > 0)
                {
                    writer.println(TAB + "}");
                }
                pageNo = c.getPage();
                pageNumbers.add(pageNo);
                writer.println(TAB + "public void loadConstantsPage" + pageNo + "(boolean simulated)");
                writer.println(TAB + "{");
                writer.println(TAB + TAB + "byte[] pageBuffer = null;");

                final int pageSize = Integer.parseInt(ecuData.getPageSizes().get(pageNo - 1).trim());
                String activateCommand = null;
                if ((pageNo - 1) < ecuData.getPageActivateCommands().size())
                {
                    activateCommand = ecuData.getPageActivateCommands().get(pageNo - 1);
                }
                String readCommand = null;
                if ((pageNo - 1) < ecuData.getPageReadCommands().size())
                {
                    readCommand = ecuData.getPageReadCommands().get(pageNo - 1);
                }

                outputLoadPage(ecuData, pageNo, 0, pageSize, activateCommand, readCommand, writer);
            }
            // getScalar(String bufferName,String name, String dataType, String
            // offset, String scale, String numOffset)
            final String name = c.getName();

            if (!"PREPROC".equals(c.getType()))
            {
                String def;
                if ("bits".equals(c.getClassType()))
                {
                    final String bitspec = StringUtils.remove(StringUtils.remove(c.getShape(), '['), ']');
                    final String[] bits = bitspec.split(":");
                    final int offset = c.getOffset();
                    final String start = bits[0];
                    String end = bits[1];
                    String ofs = "0";
                    if (end.contains("+"))
                    {
                        final String[] parts = end.split("\\+");
                        end = parts[0];
                        ofs = parts[1];
                    }
                    def = (name + " = utils.getBits(pageBuffer," + offset + "," + start + "," + end + "," + ofs + ");");
                }
                else if ("array".equals(c.getClassType()))
                {
                    def = generateLoadArray(ecuData, c);
                }
                else
                {
                    def = getScalar("pageBuffer", ecuData.getConstantVars().get(name), name, c.getType(), "" + c.getOffset(), "1", "0"); // scale of 1
                                                                                                                                         // and
                                                                                                                                         // translate
                                                                                                                                         // of 0
                }
                writer.println(TAB + TAB + def);
            }
            else
            {
                if (pageNo > 0)
                {
                    writer.println(TAB + TAB + name);
                }
            }

        }
        writer.println(TAB + "}");
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void loadConstants(boolean simulated)");
        writer.println(TAB + "{");
        for (final int i : pageNumbers)
        {
            writer.println(TAB + TAB + "loadConstantsPage" + i + "(simulated);");
        }
        writer.println(TAB + TAB + "refreshFlags();");
        writer.println(TAB + "}");
    }

    private static String generateLoadArray(final ECUData ecuData, final Constant c)
    {
        String loadArray = "";

        final int[] size = MSUtilsShared.getArraySize(c.getShape());
        final int width = size[0];
        final int height = size[1];

        String functionName = "parent.loadByte";
        String signed = "false";
        if (c.getType().contains("16"))
        {
            functionName = "parent.loadWord";
        }
        if (c.getType().contains("S"))
        {
            signed = "true";
        }
        if (height == -1)
        {
            functionName += "Vector";
            loadArray = String.format("%s = %s(pageBuffer, %d, %d, %s);", c.getName(), functionName, c.getOffset(), width, signed);

        }
        else
        {
            functionName += "Array";
            loadArray = String.format("%s = %s(pageBuffer, %d, %d, %d, %s);", c.getName(), functionName, c.getOffset(), width, height, signed);
        }
        return loadArray;
    }

    public static void outputLoadPage(final ECUData ecuData, final int pageNo, final int pageOffset, final int pageSize, String activate, String read, final PrintWriter writer)
    {
        if (activate != null)
        {
            activate = processStringToBytes(ecuData, activate, pageOffset, pageSize, pageNo);
        }
        if (read != null)
        {
            read = processStringToBytes(ecuData, read, pageOffset, pageSize, pageNo);
        }

        writer.println(TAB + TAB + String.format("pageBuffer = parent.loadPage(%d,%d,%d,%s,%s);", pageNo, pageOffset, pageSize, activate, read));
    }

    public static void outputOverrides(final ECUData ecuData, final PrintWriter writer)
    {
        String pageIdentifierOutput = "";
        for (final String pageIdentifier : ecuData.getPageIdentifiers())
        {
            pageIdentifierOutput += TAB + TAB + "pageIdentifiers.add(\"" + pageIdentifier.replace("\\", "\\\\") + "\");\n";
        }

        String pageActivateOutput = "";
        final int[] value = { 0 };
        for (final String pageActivate : ecuData.getPageActivateCommands())
        {
            pageActivateOutput += TAB + TAB + "pageActivates.add(new byte[] {" + MSUtilsShared.HexStringToBytes(new ArrayList<String>(), pageActivate, 0, 0, value, 0) + "});\n";
        }

        String pageValueWriteOutput = "";
        for (final String pageValueWrite : ecuData.getPageValueWrites())
        {
            pageValueWriteOutput += TAB + TAB + "pageValueWrites.add(" + pageValueWrite + ");\n";
        }

        String pageChunkWriteOutput = "";

        for (final String pageChunkWrite : ecuData.getPageChunkWrites())
        {
            pageChunkWriteOutput += TAB + TAB + "pageChunkWrites.add(" + pageChunkWrite + ");\n";
        }

        String overrides = TAB + "@Override\n" + TAB + "public String getSignature()\n" + TAB + "{\n" + TAB + TAB + "return signature;\n" + "}\n" + TAB + "@Override\n" + TAB + "public byte[] getOchCommand()\n" + TAB + "{\n" + TAB + TAB
                + "return this.ochGetCommand;\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public byte[] getSigCommand()\n" + TAB + "{\n" + TAB + TAB + "return this.queryCommand;\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getBlockSize()\n" + TAB + "{\n" + TAB + TAB + "return this.ochBlockSize;\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getSigSize()\n" + TAB + "{\n" + TAB + TAB + "return signature.length();\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getPageActivationDelay()\n" + TAB + "{\n" + TAB + TAB + "return " + ecuData.getPageActivationDelayVal() + ";\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public List<String> getPageValueWrites()\n" + TAB + "{\n" + TAB + TAB + "List<String> pageValueWrites = new ArrayList<String>();\n\n" + pageValueWriteOutput + "\n" + TAB + TAB
                + "return pageValueWrites;\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public List<String> getPageChunkWrites()\n" + TAB + "{\n" + TAB + TAB + "List<String> pageChunkWrites = new ArrayList<String>();\n\n" + pageChunkWriteOutput + "\n" + TAB + TAB
                + "return pageChunkWrites;\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public List<String> getPageIdentifiers()\n" + TAB + "{\n" + TAB + TAB + "List<String> pageIdentifiers = new ArrayList<String>();\n\n" + pageIdentifierOutput + "\n" + TAB + TAB
                + "return pageIdentifiers;\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public List<byte[]> getPageActivates()\n" + TAB + "{\n" + TAB + TAB + "List<byte[]> pageActivates = new ArrayList<byte[]>();\n\n" + pageActivateOutput + "\n" + TAB + TAB + "return pageActivates;\n" + TAB
                + "}\n" +

                TAB + "@Override\n" + TAB + "public int getInterWriteDelay()\n" + TAB + "{\n" + TAB + TAB + "return " + ecuData.getInterWriteDelay() + ";\n" + TAB + "}\n" + TAB + "@Override\n" + TAB + "public boolean isCRC32Protocol()\n" + TAB
                + "{\n" + TAB + TAB + "return " + ecuData.isCRC32Protocol() + ";\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getCurrentTPS()\n" + TAB + "{\n";
        if (ecuData.getRuntimeVars().containsKey("tpsADC"))
        {
            overrides += TAB + TAB + "return (int)tpsADC;\n";
        }
        else
        {
            overrides += TAB + TAB + "return 0;\n";
        }

        overrides += TAB + "}\n";
        overrides += TAB + "@Override\n" + TAB + "public String[] defaultGauges()\n" + TAB + "{\n" + TAB + TAB + "return defaultGauges;\n" + TAB + "}\n";

        writer.println(overrides);
    }

    private static String processStringToBytes(final ECUData ecuData, final String s, final int offset, final int count, final int pageNo)
    {
        String ret = "new byte[]{";

        final int[] value = { 0 };
        ret += MSUtilsShared.HexStringToBytes(ecuData.getPageIdentifiers(), s, offset, count, value, pageNo);

        ret += "}";
        return ret;
    }

    static String getScalar(final String bufferName, String javaType, final String name, final String dataType, final String offset, final String scale, final String numOffset)
    {
        if (javaType == null)
        {
            javaType = "int";
        }
        String definition = name + " = (" + javaType + ")((utils.get";
        if (dataType.startsWith("S"))
        {
            definition += "Signed";
        }
        final int size = Integer.parseInt(dataType.substring(1).trim());
        switch (size)
        {
        case 8:
            definition += "Byte";
            break;
        case 16:
            definition += "Word";
            break;
        case 32:
            definition += "Long";
            break;
        default:
            definition += dataType;
            break;
        }
        definition += "(" + bufferName + "," + offset + ") + " + numOffset + ") * " + scale + ");";
        return definition;
    }

    public static void outputSettingGroups(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createSettingGroups()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "settingGroups.clear();");
        writer.println(TAB + TAB + "SettingGroup g;");

        for (final SettingGroup group : ecuData.getSettingGroups())
        {
            final String desc = group.getDescription();
            if (desc.trim().length() > 1)
            {
                writer.println(TAB + TAB + String.format("g = new SettingGroup(\"%s\",\"%s\");", group.getName(), group.getDescription()));
                for (final SettingGroup.SettingOption settingOption : group.getOptions())
                {
                    writer.println(TAB + TAB + String.format("g.addOption(\"%s\",\"%s\");", settingOption.getFlag(), settingOption.getDescription()));
                }
                writer.println(TAB + TAB + "settingGroups.add(g);");
            }
        }
        writer.println(TAB + "}");

        writer.println(TAB + "@Override");
        writer.println(TAB + "public List<SettingGroup> getSettingGroups()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "return settingGroups;");
        writer.println(TAB + "}");

    }

    public static void outputControllerCommands(final ECUData ecuData, final PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createControllerCommands()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "controllerCommands.clear();");

        for (final ControllerCommand command : ecuData.getControllerCommands())
        {
            final String name = command.getName();
            final String controllerCommand = command.getCommand();

            writer.println(TAB + TAB + String.format("controllerCommands.put(\"%s\", \"%s\");", name, controllerCommand));
        }

        writer.println(TAB + "}");

        writer.println(TAB + "@Override");
        writer.println(TAB + "public Map<String,String> getControllerCommands()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "return controllerCommands;");
        writer.println(TAB + "}");

    }
}
