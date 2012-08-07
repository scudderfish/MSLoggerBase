package uk.org.smithfamily.utils.normaliser;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import uk.org.smithfamily.mslogger.ecuDef.Constant;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import uk.org.smithfamily.mslogger.ecuDef.MenuDefinition;
import uk.org.smithfamily.utils.normaliser.curveeditor.CurveItem;
import uk.org.smithfamily.utils.normaliser.curveeditor.CurveTracker;
import uk.org.smithfamily.utils.normaliser.menu.MenuTracker;
import uk.org.smithfamily.utils.normaliser.tableeditor.TableItem;
import uk.org.smithfamily.utils.normaliser.tableeditor.TableTracker;
import uk.org.smithfamily.utils.normaliser.userdefined.DialogTracker;

public class Output
{
    static final String        TAB          = "    ";
    private static final int   MAX_LINES    = 100;
    private static Set<String> alwaysInt    = new HashSet<String>(Arrays.asList(new String[] {}));
    private static Set<String> alwaysDouble = new HashSet<String>(Arrays.asList(new String[] { "pulseWidth", "throttle",
            "accDecEnrich", "accDecEnrichPcnt", "accEnrichPcnt", "accEnrichMS", "decEnrichPcnt", "decEnrichMS", "time",
            "egoVoltage", "egoVoltage2", "egoCorrection", "veCurr", "lambda", "TargetLambda" }));

    static void outputGaugeDoc(ECUData ecuData, PrintWriter writer)
    {
        writer.println("/*");
        for (String gauge : ecuData.getGaugeDoc())
        {
            writer.println(gauge);
        }

        writer.println("*/");
    }

    static void outputConstructor(ECUData ecuData, PrintWriter writer, String className)
    {
        writer.println(TAB + "public " + className + "(Context c)");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "super(c);");
        writer.println(TAB + "}");
    }

    /**
     * This is nasty. We need to have a set of methods to init the constants as there is a hard limit of 64K on the size of a method, and MS3 will break that. We also need to ensure that if there is
     * any preprocessor type logic that we don't stop and start the next method in the middle of it.
     * 
     * @param ecuData
     * @param writer
     */
    static void outputFlagsAndConstants(ECUData ecuData, PrintWriter writer)
    {
        int constantMethodCount = 0;
        int lineCount = 0;
        int bracketNesting = 0;
        boolean needDeclaration = true;
        int lookahead = 3;
        for (Constant c : ecuData.getConstants())
        {
            if (needDeclaration)
            {
                constantMethodCount++;
                writer.println(TAB + "private void initConstants" + constantMethodCount + "()\n" + TAB + "{\n");
                needDeclaration = false;
            }
            if (bracketNesting == 0 && lookahead > 0)
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

            if (lineCount > MAX_LINES && bracketNesting == 0 && lookahead == 0)
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
        writer.println(TAB + "public void refreshFlags()");
        writer.println(TAB + "{");
        for (String flag : ecuData.getFlags())
        {
            writer.println(TAB + TAB + flag + " = isSet(\"" + flag + "\");");
        }
        for (int i = 1; i <= constantMethodCount; i++)
        {
            writer.println(TAB + TAB + "initConstants" + i + "();\n");
        }
        writer.println(TAB + TAB + "createTableEditors();");
        writer.println(TAB + TAB + "createCurveEditors();");
        writer.println(TAB + TAB + "createMenus();");
        writer.println(TAB + TAB + "createDialogs();");
        writer.println(TAB + "}");

    }

    static void outputPackageAndIncludes(ECUData ecuData, PrintWriter writer)
    {
        writer.println("package uk.org.smithfamily.mslogger.ecuDef.gen;");
        writer.println("");
        writer.println("import java.io.IOException;");
        writer.println("import java.util.*;");
        writer.println("");
        writer.println("import android.content.Context;");
        writer.println("");
        writer.println("import uk.org.smithfamily.mslogger.ecuDef.*;");
        writer.println("import uk.org.smithfamily.mslogger.widgets.GaugeDetails;");
        writer.println("import uk.org.smithfamily.mslogger.widgets.GaugeRegister;");

    }

    private static String getType(String name, Map<String, String> vars)
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

    static void outputGlobalVars(ECUData ecuData, PrintWriter writer)
    {
        writer.println("//Flags");
        for (String name : ecuData.getFlags())
        {
            writer.println(TAB + "public boolean " + name + ";");
        }
        writer.println("//Defaults");
        for (String d : ecuData.getDefaults())
        {
            writer.println(TAB + "public " + d);
        }
        Map<String, String> vars = new TreeMap<String, String>();
        vars.putAll(ecuData.getRuntimeVars());
        vars.putAll(ecuData.getEvalVars());
        for (String v : vars.keySet())
        {
            ecuData.getConstantVars().remove(v);
        }
        writer.println("//Variables");
        for (String name : vars.keySet())
        {
            String type = getType(name, vars);
            writer.println(TAB + "public " + type + " " + name + ";");
        }
        writer.println("\n//Constants");
        for (String name : ecuData.getConstantVars().keySet())
        {
            String type = getType(name, ecuData.getConstantVars());
            writer.println(TAB + "public " + type + " " + name + ";");
        }
        writer.println("\n");
        writer.println(TAB + "private String[] defaultGauges = {");
        boolean first = true;
        for (String dg : ecuData.getDefaultGauges())
        {
            if (!first)
                writer.println(",");
            first = false;
            writer.print(TAB + TAB + "\"" + dg + "\"");
        }
        writer.println("\n" + TAB + "};");

    }

    static void outputRTCalcs(ECUData ecuData, PrintWriter writer)
    {
        writer.println("    @Override");
        writer.println("    public void calculate(byte[] ochBuffer)");
        writer.println("    {");
        for (String defn : ecuData.getRuntime())
        {
            writer.println(TAB + TAB + defn);
            // System.out.println(defn);
        }
        writer.println(TAB + "}");
    }

    static void outputLogInfo(ECUData ecuData, PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public String getLogHeader()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "StringBuffer b = new StringBuffer();");
        for (String header : ecuData.getLogHeader())
        {
            writer.println(TAB + TAB + header);
        }
        writer.println(TAB + TAB + "b.append(MSUtils.getLocationLogHeader());");
        writer.println(TAB + TAB + "return b.toString();\n" + TAB + "}\n");
        writer.println(TAB + "@Override");
        writer.println(TAB + "public String getLogRow()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "StringBuffer b = new StringBuffer();");

        for (String record : ecuData.getLogRecord())
        {
            writer.println(TAB + TAB + record);
        }
        writer.println(TAB + TAB + "b.append(MSUtils.getLocationLogRow());");
        writer.println(TAB + TAB + "return b.toString();\n" + TAB + "}\n");
    }

    static void outputGauges(ECUData ecuData, PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void initGauges()");
        writer.println(TAB + "{");
        for (String gauge : ecuData.getGaugeDef())
        {
            boolean okToWrite = true;
            if (gauge.contains("GaugeRegister"))
            {
                String[] parts = gauge.split(",");
                String channel = parts[4];
                okToWrite = ecuData.getRuntimeVars().containsKey(channel) || ecuData.getEvalVars().containsKey(channel);
            }
            if (okToWrite)
            {
                writer.println(TAB + TAB + gauge);
            }
        }
        writer.println(TAB + "}\n");
    }

    static void outputMenus(ECUData ecuData, PrintWriter writer)
    {
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createMenus()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "menus = new HashMap<String,List<MenuDefinition>>();");
        writer.println(TAB + TAB + "MenuDefinition m;");
        
        for (MenuTracker m : ecuData.getMenuDefs())
        {
            for (Entry<String, List<MenuDefinition>> menuDialog : m.getItems())
            {
                String key = menuDialog.getKey();
                List<MenuDefinition> value = menuDialog.getValue();
                
                for (int i = 0; i < value.size(); i++)
                {
                    writer.println(TAB + TAB + value.get(i).generateCode());                    
                    
                    writer.println(TAB + TAB + "if (menus.containsKey(\"" + key + "\"))");
                    writer.println(TAB + TAB + "{");
                    writer.println(TAB + TAB + TAB + "menus.get(\"" + key + "\").add(m);");
                    writer.println(TAB + TAB + "}");
                    writer.println(TAB + TAB + "else ");
                    writer.println(TAB + TAB + "{");
                    writer.println(TAB + TAB + TAB + "List<MenuDefinition> listMenus = new ArrayList<MenuDefinition>();");
                    writer.println(TAB + TAB + TAB + "listMenus.add(m);");
                    writer.println(TAB + TAB + TAB + "menus.put(\"" + key + "\", listMenus);");
                    writer.println(TAB + TAB + "}");
                }
            }
        }
        
        writer.println(TAB + "}\n");
    }

    static void outputUserDefined(ECUData ecuData, PrintWriter writer)
    {        
        for (DialogTracker d : ecuData.getDialogDefs())
        {
            for (Entry<String, MSDialog> dialog : d.getItems())
            {                
                String key = dialog.getKey();
                MSDialog value = dialog.getValue();
                
                writer.println(TAB + "public void createDialog_" + key + "()");
                writer.println(TAB + "{");
                writer.println(TAB + TAB + "MSDialog d;");

                writer.println(TAB + TAB + value.generateCode());
                writer.println(TAB + TAB + "dialogs.put(\"" + key + "\",d);");
                
                writer.println(TAB + "}\n");
            }
        }       
        
        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createDialogs()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "dialogs = new HashMap<String,MSDialog>();");

        for (DialogTracker d : ecuData.getDialogDefs())
        {            
            for (Entry<String, MSDialog> dialog : d.getItems())
            {        
                String name = dialog.getKey();
                
                writer.println(TAB + TAB + "createDialog_" + name + "();");
            }
        }

        writer.println(TAB + "}");
    }

    static void outputTableEditors(ECUData ecuData, PrintWriter writer)
    {
        for (TableTracker t : ecuData.getTableDefs())
        {
            writer.println(TAB + "private void createTableEditor_" + t.getName() + "()");
            writer.println(TAB + "{");
            writer.println(TAB + TAB + "TableEditor t = null;");

            for (TableItem i : t.getItems())
            {
                writer.println(TAB + TAB + i);
            }
            writer.println(TAB + "}");
        }

        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createTableEditors()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "tableEditors = new HashMap<String,TableEditor>();");

        for (TableTracker t : ecuData.getTableDefs())
        {
            writer.println(TAB + TAB + "createTableEditor_" + t.getName() + "();");
        }

        writer.println(TAB + "}");
    }

    static void outputCurves(ECUData ecuData, PrintWriter writer)
    {
        for (CurveTracker c : ecuData.getCurveDefs())
        {
            if (c.getName() != null && !c.getName().trim().equals(""))
            {
                writer.println(TAB + "private void createCurveEditor_" + c.getName() + "()");
                writer.println(TAB + "{");
                writer.println(TAB + TAB + "CurveEditor c = null;");

                for (CurveItem i : c.getItems())
                {
                    writer.println(TAB + TAB + i);
                }
                writer.println(TAB + "}");
            }
        }

        writer.println(TAB + "@Override");
        writer.println(TAB + "public void createCurveEditors()");
        writer.println(TAB + "{");
        writer.println(TAB + TAB + "curveEditors = new HashMap<String,CurveEditor>();");

        for (CurveTracker c : ecuData.getCurveDefs())
        {
            if (c.getName() != null && !c.getName().trim().equals(""))
            {
                writer.println(TAB + TAB + "createCurveEditor_" + c.getName() + "();");
            }
        }

        writer.println(TAB + "}");
    }

    static void outputLoadConstants(ECUData ecuData, PrintWriter writer)
    {
        int pageNo = 0;

        List<Integer> pageNumbers = new ArrayList<Integer>();

        for (Constant c : ecuData.getConstants())
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

                int pageSize = Integer.parseInt(ecuData.getPageSizes().get(pageNo - 1).trim());
                String activateCommand = null;
                if (pageNo - 1 < ecuData.getPageActivateCommands().size())
                {
                    activateCommand = ecuData.getPageActivateCommands().get(pageNo - 1);
                }
                String readCommand = null;
                if (pageNo - 1 < ecuData.getPageReadCommands().size())
                {
                    readCommand = ecuData.getPageReadCommands().get(pageNo - 1);
                }

                outputLoadPage(ecuData, pageNo, 0, pageSize, activateCommand, readCommand, writer);
            }
            // getScalar(String bufferName,String name, String dataType, String
            // offset, String scale, String numOffset)
            String name = c.getName();

            if (!"PREPROC".equals(c.getType()))
            {
                String def;
                if ("bits".equals(c.getClassType()))
                {
                    String bitspec = StringUtils.remove(StringUtils.remove(c.getShape(), '['), ']');
                    String[] bits = bitspec.split(":");
                    int offset = c.getOffset();
                    String start = bits[0];
                    String end = bits[1];
                    String ofs = "0";
                    if (end.contains("+"))
                    {
                        String[] parts = end.split("\\+");
                        end = parts[0];
                        ofs = parts[1];
                    }
                    def = (name + " = MSUtils.getBits(pageBuffer," + offset + "," + start + "," + end + "," + ofs + ");");
                }
                else if ("array".equals(c.getClassType()))
                {
                    def = generateLoadArray(ecuData, c);
                }
                else
                {
                    def = getScalar("pageBuffer", ecuData.getConstantVars().get(name), name, c.getType(), "" + c.getOffset(), ""
                            + c.getScale(), "" + c.getTranslate());
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
        for (int i : pageNumbers)
        {
            writer.println(TAB + TAB + "loadConstantsPage" + i + "(simulated);");
        }
        writer.println(TAB + TAB + "refreshFlags();");
        writer.println(TAB + "}");
    }

    private static String generateLoadArray(ECUData ecuData, Constant c)
    {
        String loadArray = "";
        String arraySpec = StringUtils.remove(StringUtils.remove(c.getShape(), '['), ']');
        String[] sizes = arraySpec.split("x");
        int width = Integer.parseInt(sizes[0].trim());
        int height = sizes.length == 2 ? Integer.parseInt(sizes[1].trim()) : -1;
        String functionName = "loadByte";
        String signed = "false";
        if (c.getType().contains("16"))
        {
            functionName = "loadWord";
        }
        if (c.getType().contains("S"))
        {
            signed = "true";
        }
        if (height == -1)
        {
            functionName += "Vector";
            loadArray = String.format("%s = %s(pageBuffer, %d, %d, %s, %s, %s);", c.getName(), functionName, c.getOffset(), width,
                    c.getScale(), c.getTranslate(), signed);

        }
        else
        {
            functionName += "Array";
            loadArray = String.format("%s = %s(pageBuffer, %d, %d, %d, %s, %s, %s);", c.getName(), functionName, c.getOffset(),
                    width, height, c.getScale(), c.getTranslate(), signed);
        }
        return loadArray;
    }

    static void outputLoadPage(ECUData ecuData, int pageNo, int pageOffset, int pageSize, String activate, String read,
            PrintWriter writer)
    {
        if (activate != null)
        {

            activate = processStringToBytes(ecuData, activate, pageOffset, pageSize, pageNo);
        }
        if (read != null)
        {
            read = processStringToBytes(ecuData, read, pageOffset, pageSize, pageNo);
        }
        writer.println(TAB + TAB
                + String.format("pageBuffer = loadPage(%d,%d,%d,%s,%s);", pageNo, pageOffset, pageSize, activate, read));

    }

    static void outputOverrides(ECUData ecuData, PrintWriter writer)
    {
        String overrides = TAB + "@Override\n" + TAB + "public String getSignature()\n" + TAB + "{\n" + TAB + TAB
                + "return signature;\n" + "}\n" + TAB + "@Override\n" + TAB + "public byte[] getOchCommand()\n" + TAB + "{\n" + TAB
                + TAB + "return this.ochGetCommand;\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public byte[] getSigCommand()\n" + TAB + "{\n" + TAB + TAB
                + "return this.queryCommand;\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getBlockSize()\n" + TAB + "{\n" + TAB + TAB + "return this.ochBlockSize;\n"
                + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getSigSize()\n" + TAB + "{\n" + TAB + TAB + "return signature.length();\n"
                + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getPageActivationDelay()\n" + TAB + "{\n" + TAB + TAB + "return "
                + ecuData.getPageActivationDelayVal() + ";\n" + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getInterWriteDelay()\n" + TAB + "{\n" + TAB + TAB + "return "
                + ecuData.getInterWriteDelay() + ";\n" + TAB + "}\n" + TAB + "@Override\n" + TAB
                + "public boolean isCRC32Protocol()\n" + TAB + "{\n" + TAB + TAB + "return " + ecuData.isCRC32Protocol() + ";\n"
                + TAB + "}\n" +

                TAB + "@Override\n" + TAB + "public int getCurrentTPS()\n" + TAB + "{\n";
        if (ecuData.getRuntimeVars().containsKey("tpsADC"))
        {
            overrides += TAB + TAB + "return (int)tpsADC;\n";
        }
        else
        {
            overrides += TAB + TAB + "return 0;\n";
        }

        overrides += TAB + "}\n" +

        TAB + "@Override\n" + TAB + "public String[] defaultGauges()\n" + TAB + "{\n" + TAB + TAB + "return defaultGauges;\n" + TAB
                + "}\n";

        writer.println(overrides);
    }

    private static String processStringToBytes(ECUData ecuData, String s, int offset, int count, int pageNo)
    {
        String ret = "new byte[]{";

        ret += HexStringToBytes(ecuData, s, offset, count, pageNo);

        ret += "}";
        return ret;
    }

    private static String bytes(int val)
    {
        int hi = val / 256;
        int low = val % 256;
        if (hi > 127)
            hi -= 256;
        if (low > 127)
            low -= 256;
        return "" + hi + "," + low;
    }

    static String HexStringToBytes(ECUData ecuData, String s, int offset, int count, int pageNo)
    {
        String ret = "";
        boolean first = true;
        s = s.replace("$tsCanId", "x00");
        for (int p = 0; p < s.length(); p++)
        {
            if (!first)
                ret += ",";

            char c = s.charAt(p);
            switch (c)
            {
            case '\\':
                ret += HexByteToDec(s.substring(p));
                p = p + 3;
                ;
                break;

            case '%':
                p++;
                c = s.charAt(p);

                assert c == '2';
                p++;
                c = s.charAt(p);
                if (c == 'o')
                {
                    ret += bytes(offset);
                }
                else if (c == 'c')
                {
                    ret += bytes(count);
                }
                else if (c == 'i')
                {
                    String identifier = ecuData.getPageIdentifiers().get(pageNo - 1);

                    ret += HexStringToBytes(ecuData, identifier, offset, count, pageNo);
                }
                break;

            default:
                ret += Byte.toString((byte) c);
                break;
            }
            first = false;
        }
        return ret;
    }

    private static int HexByteToDec(String s)
    {
        String digits = "0123456789abcdef";
        int i = 0;
        char c = s.charAt(i++);
        assert c == '\\';
        c = s.charAt(i++);
        assert c == 'x';
        c = s.charAt(i++);
        c = Character.toLowerCase(c);
        int val = 0;
        int digit = digits.indexOf(c);
        val = digit * 16;
        c = s.charAt(i++);
        c = Character.toLowerCase(c);
        digit = digits.indexOf(c);
        val = val + digit;
        return val;

    }

    static String getScalar(String bufferName, String javaType, String name, String dataType, String offset, String scale,
            String numOffset)
    {
        if (javaType == null)
        {
            javaType = "int";
        }
        String definition = name + " = (" + javaType + ")((MSUtils.get";
        if (dataType.startsWith("S"))
        {
            definition += "Signed";
        }
        int size = Integer.parseInt(dataType.substring(1).trim());
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

}
