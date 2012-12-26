package uk.org.smithfamily.utils.normaliser;

import java.util.*;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;

import uk.org.smithfamily.mslogger.ecuDef.*;
import uk.org.smithfamily.utils.normaliser.controllercommand.ControllerCommand;
import uk.org.smithfamily.utils.normaliser.curveeditor.*;
import uk.org.smithfamily.utils.normaliser.menu.*;
import uk.org.smithfamily.utils.normaliser.tableeditor.*;
import uk.org.smithfamily.utils.normaliser.userdefined.*;

public class Process
{
    private static String currentMenuDialog = "";

    private static String deBinary(final String group)
    {
        final Matcher binNumber = Patterns.binary.matcher(group);
        if (!binNumber.matches())
        {
            return group;
        }
        else
        {
            final String binNum = binNumber.group(2);
            final int num = Integer.parseInt(binNum, 2);
            final String expr = binNumber.group(1) + num + binNumber.group(3);
            return deBinary(expr);
        }
    }

    private static String removeComments(String line)
    {
        line += "; junk";
        line = StringUtils.trim(line).split(";")[0];
        line = line.trim();
        return line;
    }

    private static String convertC2JavaBoolean(String expression)
    {
        final Matcher matcher = Patterns.booleanConvert.matcher(expression);
        final StringBuffer result = new StringBuffer(expression.length());
        while (matcher.find())
        {
            matcher.appendReplacement(result, "");
            result.append(matcher.group(1) + " ? 1 : 0)");
        }
        matcher.appendTail(result);
        expression = result.toString();
        return expression;

    }

    private static boolean isFloatingExpression(final ECUData ecuData, final String expression)
    {
        boolean result = expression.contains(".");
        if (result)
        {
            return result;
        }
        for (final String var : ecuData.getRuntimeVars().keySet())
        {
            if (expression.contains(var) && ecuData.getRuntimeVars().get(var).equals("double"))
            {
                result = true;
            }
        }
        for (final String var : ecuData.getEvalVars().keySet())
        {
            if (expression.contains(var) && ecuData.getEvalVars().get(var).equals("double"))
            {
                result = true;
            }
        }

        return result;
    }

    public static void processExpr(final ECUData ecuData, String line)
    {
        String definition = null;
        line = removeComments(line);

        line = StringUtils.replace(line, "timeNow", "timeNow()");
        final Matcher bitsM = Patterns.bits.matcher(line);
        final Matcher scalarM = Patterns.scalar.matcher(line);
        final Matcher exprM = Patterns.expr.matcher(line);
        final Matcher ochGetCommandM = Patterns.ochGetCommand.matcher(line);
        final Matcher ochBlockSizeM = Patterns.ochBlockSize.matcher(line);
        if (bitsM.matches())
        {
            final String name = bitsM.group(1);
            final String offset = bitsM.group(3);
            final String start = bitsM.group(4);
            String end = bitsM.group(5);
            String ofs = "0";
            if (end.contains("+"))
            {
                final String[] parts = end.split("\\+");
                end = parts[0];
                ofs = parts[1];
            }
            definition = (name + " = utils.getBits(ochBuffer," + offset + "," + start + "," + end + "," + ofs + ");");
            ecuData.getRuntime().add(definition);
            ecuData.getRuntimeVars().put(name, "int");
        }
        else if (scalarM.matches())
        {
            String name = scalarM.group(1);
            if (constantDefined(ecuData, name))
            {
                name += "RT";
            }
            final String dataType = scalarM.group(2);
            final String offset = scalarM.group(3);
            final String units = scalarM.group(4);
            String scale = scalarM.group(5);
            final String numOffset = scalarM.group(6);

            try
            {
                if (Double.parseDouble(scale) != 1)
                {
                    ecuData.getRuntimeVars().put(name, "double");
                }
                else
                {
                    ecuData.getRuntimeVars().put(name, "int");
                }
            }
            // If we have a NumberFormatException at this point, most likely scale is an expression so assume it's a double
            catch (final NumberFormatException e)
            {
                // Replace curly brackets by round brackets
                scale = "(" + removeCurlyBrackets(scale) + ")";

                ecuData.getRuntimeVars().put(name, "double");
            }

            definition = Output.getScalar("ochBuffer", ecuData.getRuntimeVars().get(name), name, dataType, offset, scale, numOffset);
            ecuData.setFingerprintSource(ecuData.getFingerprintSource() + definition);
            ecuData.getRuntime().add(definition);

            final int offsetOC = Integer.parseInt(offset);
            final String scaleOC = StringUtils.isEmpty(scale) ? "0" : scale;
            final double translateOC = !StringUtils.isEmpty(numOffset) ? Double.parseDouble(numOffset) : 0;

            final OutputChannel outputChannel = new OutputChannel(name, dataType, offsetOC, units, scaleOC, translateOC, null);
            ecuData.getOutputChannels().add(outputChannel);
        }
        else if (exprM.matches())
        {
            final String name = exprM.group(1);
            if ("pwma_load".equals(name))
            {
                // Hook to hang a break point on
                @SuppressWarnings("unused")
                final int x = 1;
            }
            String expression = deBinary(exprM.group(2).trim());
            final Matcher ternaryM = Patterns.ternary.matcher(expression);
            if (ternaryM.matches())
            {
                // System.out.println("BEFORE : " + expression);
                final String test = ternaryM.group(1);
                final String values = ternaryM.group(2);
                if (StringUtils.containsAny(test, "<>!="))
                {
                    expression = "(" + test + ") ? " + values;
                }
                else
                {
                    expression = "((" + test + ") != 0 ) ? " + values;
                }
                // System.out.println("AFTER  : " + expression + "\n");
            }

            String dataType = addRuntimeExpression(ecuData, name, expression);

            final OutputChannel outputChannel = new OutputChannel(name, dataType, -1, "", "1", 0, null);
            ecuData.getOutputChannels().add(outputChannel);

        }
        else if (ochGetCommandM.matches())
        {
            String och = ochGetCommandM.group(1);
            if (och.length() > 1)
            {
                final int[] value = { 0 };
                och = MSUtilsShared.HexStringToBytes(ecuData.getPageIdentifiers(), och, 0, 0, value, 0);
            }
            else
            {
                och = "'" + och + "'";
            }
            ecuData.setOchGetCommandStr("byte [] ochGetCommand = new byte[]{" + och + "};");
        }
        else if (ochBlockSizeM.matches())
        {
            ecuData.setOchBlockSizeStr("int ochBlockSize = " + ochBlockSizeM.group(1) + ";");
        }
        else if (line.startsWith("#"))
        {
            final String preproc = processPreprocessor(ecuData, line);
            ecuData.getRuntime().add(preproc);

            final OutputChannel oc = new OutputChannel(preproc, "PREPROC", 0, "", "0", 0, null);
            ecuData.getOutputChannels().add(oc);
        }
        else if (!StringUtils.isEmpty(line))
        {
            System.out.println("WARNING! Not sure what to do with this line: " + line);
        }
    }

    /**
     * @param ecuData
     * @param name The name of the expression to create
     * @param expression The expression itself
     * 
     * @return The data type of the created runtime expression
     */
    private static String addRuntimeExpression(final ECUData ecuData, String name, String expression)
    {
        if (expression.contains("*") && expression.contains("=="))
        {
            expression = convertC2JavaBoolean(expression);
        }
        
        String definition = name + " = (" + expression + ");";

        // If the expression contains a division, wrap it in a try/catch to
        // avoid division by zero
        if (expression.contains("/"))
        {
            definition = "try\n" +
                            Output.TAB + Output.TAB + "{\n" +
                            Output.TAB + Output.TAB + Output.TAB + definition + "\n" +
                            Output.TAB + Output.TAB + "}\n" +
                            Output.TAB + Output.TAB + "catch (ArithmeticException e) {\n" +
                            Output.TAB + Output.TAB + Output.TAB + name + " = 0;\n" +
                            Output.TAB + Output.TAB + "}";
        }

        ecuData.getRuntime().add(definition);
        
        String dataType;
        if (isFloatingExpression(ecuData, expression))
        {
            dataType = "double";
        }
        else
        {
            dataType = "int";
        }
        ecuData.getEvalVars().put(name, dataType);
        
        return dataType;
    }
    
    /**
     * Occasionally we get a collision between the name of a constant and an expression. Test for that here.
     * 
     * @param ecuData
     * @param name
     * @return
     */
    private static boolean constantDefined(final ECUData ecuData, final String name)
    {
        for (final Constant c : ecuData.getConstants())
        {
            if (c.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    static void processLogEntry(final ECUData ecuData, String line)
    {
        line = removeComments(line);

        final Matcher logM = Patterns.log.matcher(line);
        if (logM.matches())
        {
            final String header = logM.group(2);
            String variable = logM.group(1);
            if ("double".equals(ecuData.getRuntimeVars().get(variable)))
            {
                variable = "round(" + variable + ")";
            }
            ecuData.getLogHeader().add("b.append(\"" + header + "\").append(\"\\t\");");
            ecuData.getLogRecord().add("b.append(" + variable + ").append(\"\\t\");");
        }
        else if (line.startsWith("#"))
        {
            final String directive = processPreprocessor(ecuData, line);
            ecuData.getLogHeader().add(directive);
            ecuData.getLogRecord().add(directive);
        }
    }

    static String processPreprocessor(final ECUData ecuData, String line)
    {
        String filtered;
        boolean stripped = false;

        filtered = line.replace("  ", " ");
        stripped = filtered.equals(line);
        while (!stripped)
        {
            line = filtered;
            filtered = line.replace("  ", " ");
            stripped = filtered.equals(line);
        }
        final String[] components = line.split(" ");
        final String flagName = components.length > 1 ? sanitize(components[1]) : "";
        if (components[0].equals("#if") || components[0].equals("#ifdef"))
        {
            ecuData.getFlags().add(flagName);
            return ("if (" + flagName + ")\n        {");
        }
        if (components[0].equals("#elif"))
        {
            ecuData.getFlags().add(flagName);
            return ("}\n        else if (" + flagName + ")\n        {");
        }
        if (components[0].equals("#else"))
        {
            return ("}\n        else\n        {");
        }
        if (components[0].equals("#endif"))
        {
            return ("}");
        }

        return "";
    }

    private static String sanitize(final String flagName)
    {
        return StringUtils.replace(flagName, "!", "n");
    }

    public static void processFrontPage(final ECUData ecuData, String line)
    {
        line = removeComments(line);

        final Matcher dgM = Patterns.defaultGauge.matcher(line);
        if (dgM.matches())
        {
            ecuData.getDefaultGauges().add(dgM.group(1));
        }
    }

    public static void processHeader(final ECUData ecuData, final String line)
    {
        final Matcher queryM = Patterns.queryCommand.matcher(line);
        if (queryM.matches())
        {
            ecuData.setQueryCommandStr("byte[] queryCommand = new byte[]{'" + queryM.group(1) + "'};");
            return;
        }

        final Matcher sigM = Patterns.signature.matcher(line);
        final Matcher sigByteM = Patterns.byteSignature.matcher(line);
        if (sigM.matches())
        {
            String tmpsig = sigM.group(1);
            if (line.contains("null"))
            {
                tmpsig += "\\0";
            }
            ecuData.setClassSignature("\"" + tmpsig + "\"");
            ecuData.setSignatureDeclaration("String signature = \"" + tmpsig + "\";");
        }
        else if (sigByteM.matches())
        {
            final String b = sigByteM.group(1).trim();
            ecuData.setClassSignature("new String(new byte[]{" + b + "})");
            ecuData.setSignatureDeclaration("String signature = \"\"+(byte)" + b + ";");
        }

    }

    /**
     * Process the [Constants] section of the ini file
     * 
     * @param line
     */
    public static void processConstants(final ECUData ecuData, String line)
    {
        line = removeComments(line);
        if (StringUtils.isEmpty(line))
        {
            return;
        }

        if (line.contains("DI_rpm"))
        {
            // Break point hook
            @SuppressWarnings("unused")
            final int x = 1;
        }
        if (line.contains("messageEnvelopeFormat"))
        {
            ecuData.setCRC32Protocol(line.contains("msEnvelope_1.0"));
        }
        final Matcher pageM = Patterns.page.matcher(line);
        if (pageM.matches())
        {
            ecuData.setCurrentPage(Integer.parseInt(pageM.group(1).trim()));
            return;
        }
        final Matcher pageSizesM = Patterns.pageSize.matcher(line);
        if (pageSizesM.matches())
        {
            final String values = StringUtils.remove(pageSizesM.group(1), ' ');
            final String[] list = values.split(",");
            ecuData.setPageSizes(new ArrayList<String>(Arrays.asList(list)));
        }
        final Matcher pageIdentifersM = Patterns.pageIdentifier.matcher(line);
        if (pageIdentifersM.matches())
        {
            String values = StringUtils.remove(pageIdentifersM.group(1), ' ');
            values = StringUtils.remove(values, '"');
            final String[] list = values.split(",");
            ecuData.setPageIdentifiers(new ArrayList<String>(Arrays.asList(list)));
        }

        final Matcher pageActivateM = Patterns.pageActivate.matcher(line);
        if (pageActivateM.matches())
        {
            String values = StringUtils.remove(pageActivateM.group(1), ' ');
            values = StringUtils.remove(values, '"');
            final String[] list = values.split(",");
            ecuData.setPageActivateCommands(new ArrayList<String>(Arrays.asList(list)));
        }

        final Matcher pageReadCommandM = Patterns.pageReadCommand.matcher(line);
        if (pageReadCommandM.matches())
        {
            String values = StringUtils.remove(pageReadCommandM.group(1), ' ');
            values = StringUtils.remove(values, '"');
            final String[] list = values.split(",");
            ecuData.setPageReadCommands(new ArrayList<String>(Arrays.asList(list)));
        }

        final Matcher pageValueWriteM = Patterns.pageValueWrite.matcher(line);
        if (pageValueWriteM.matches())
        {
            final String values = StringUtils.remove(pageValueWriteM.group(1), ' ');
            final String[] list = values.replace("\\", "\\\\").split(",");
            ecuData.setPageValueWrites(new ArrayList<String>(Arrays.asList(list)));
        }

        final Matcher pageChunkWriteM = Patterns.pageChunkWrite.matcher(line);
        if (pageChunkWriteM.matches())
        {
            final String values = StringUtils.remove(pageChunkWriteM.group(1), ' ');
            final String[] list = values.replace("\\", "\\\\").split(",");
            ecuData.setPageChunkWrites(new ArrayList<String>(Arrays.asList(list)));
        }

        final Matcher interWriteDelayM = Patterns.interWriteDelay.matcher(line);
        if (interWriteDelayM.matches())
        {
            ecuData.setInterWriteDelay(Integer.parseInt(interWriteDelayM.group(1).trim()));
            return;
        }
        final Matcher pageActivationDelayM = Patterns.pageActivationDelay.matcher(line);
        if (pageActivationDelayM.matches())
        {
            ecuData.setPageActivationDelayVal(Integer.parseInt(pageActivationDelayM.group(1).trim()));
            return;
        }

        final Matcher bitsM = Patterns.bits.matcher(line);
        final Matcher constantM = Patterns.constantScalar.matcher(line);
        final Matcher constantSimpleM = Patterns.constantSimple.matcher(line);
        final Matcher constantArrayM = Patterns.constantArray.matcher(line);
        if (constantM.matches())
        {
            final String name = constantM.group(1);
            final String classtype = constantM.group(2);
            final String type = constantM.group(3);
            final int offset = Integer.parseInt(constantM.group(4).trim());
            final String units = constantM.group(5);
            final String scaleText = constantM.group(6);
            final String translateText = constantM.group(7);
            final String lowText = constantM.group(8);
            final String highText = constantM.group(9);
            final String digitsText = constantM.group(10);
            final String scale = StringUtils.isEmpty(scaleText) ? "0" : scaleText;
            String translate = StringUtils.isEmpty(translateText) ? "0" : translateText;

            final int digits = !StringUtils.isEmpty(digitsText) ? (int) Double.parseDouble(digitsText) : 0;

            if (!ecuData.getConstants().contains(name))
            {
                if (ExpressionWrangler.isExpression(translate))
                {
                    final String runtimeVarExpressionName = "msl_exp_" + name;

                    String definition = (runtimeVarExpressionName + " = " + removeCurlyBrackets(translateText).trim() + ";");

                    definition = "try\n" + Output.TAB + Output.TAB + "{\n" + Output.TAB + Output.TAB + Output.TAB + definition + "\n" + Output.TAB + Output.TAB + "}\n" + Output.TAB + Output.TAB + "catch (ArithmeticException e) {\n" + Output.TAB
                            + Output.TAB + Output.TAB + runtimeVarExpressionName + " = 0;\n" + Output.TAB + Output.TAB + "}";

                    ecuData.getRuntime().add(definition);
                    ecuData.getRuntimeVars().put(runtimeVarExpressionName, "double");

                    translate = runtimeVarExpressionName;
                }

                final Constant c = new Constant(ecuData.getCurrentPage(), name, classtype, type, offset, "", units, scale, translate, lowText, highText, digits);

                ecuData.getConstantVars().put(name, "int");
                ecuData.getConstants().add(c);
            }
        }
        else if (constantArrayM.matches())
        {
            final String name = constantArrayM.group(1);
            final String classtype = constantArrayM.group(2);
            final String type = constantArrayM.group(3);
            final int offset = Integer.parseInt(constantArrayM.group(4).trim());
            final String shape = constantArrayM.group(5);
            final String units = constantArrayM.group(6);
            String scaleText = StringUtils.isEmpty(constantArrayM.group(7)) ? "0" : constantArrayM.group(7);
            String translateText = StringUtils.isEmpty(constantArrayM.group(8)) ? "0" : constantArrayM.group(8);
            String lowText = constantArrayM.group(9);
            String highText = constantArrayM.group(10);
            final String digitsText = constantArrayM.group(11);
            highText = removeCurlyBrackets(highText);

            final int digits = !StringUtils.isEmpty(digitsText) ? (int) Double.parseDouble(digitsText) : 0;
            
            if (ExpressionWrangler.isExpression(scaleText))
            {
                // scale is an expression, add it to runtime expression
                scaleText = removeCurlyBrackets(scaleText);
                addRuntimeExpression(ecuData, "MSLoggerExp" + name, scaleText);
                
                scaleText = "MSLoggerExp" + name;
            }
            
            if (ExpressionWrangler.isExpression(translateText))
            {
                // scale is an expression, add it to runtime expression
                translateText = removeCurlyBrackets(translateText);
                addRuntimeExpression(ecuData, "MSLoggerExp" + name, translateText);
                
                translateText = "MSLoggerExp" + name;
            }
            
            if (ExpressionWrangler.isExpression(lowText))
            {
                // low is an expression, add it to runtime expression
                lowText = ExpressionWrangler.convertExpr(removeCurlyBrackets(lowText));
                addRuntimeExpression(ecuData, "MSLoggerExp" + name, lowText);
                
                lowText = "MSLoggerExp" + name;
            }
            
            if (ExpressionWrangler.isExpression(highText))
            {
                highText = ExpressionWrangler.convertExpr(removeCurlyBrackets(highText));
                addRuntimeExpression(ecuData, "MSLoggerExp" + name, highText);
                
                highText = "MSLoggerExp" + name;
            }
            
            if (!ecuData.getConstants().contains(name))
            {
                final Constant c = new Constant(ecuData.getCurrentPage(), name, classtype, type, offset, shape, units, scaleText, translateText, lowText, highText, digits);
                if (shape.contains("x"))
                {
                    ecuData.getConstantVars().put(name, "int[][]");
                }
                else
                {
                    ecuData.getConstantVars().put(name, "int[]");
                }
                ecuData.getConstants().add(c);
            }
        }
        else if (constantSimpleM.matches())
        {
            final String name = constantSimpleM.group(1);
            final String classtype = constantSimpleM.group(2);
            final String type = constantSimpleM.group(3);
            final int offset = Integer.parseInt(constantSimpleM.group(4).trim());
            final String units = constantSimpleM.group(5);
            final String scale = constantSimpleM.group(6);
            final String translate = constantSimpleM.group(7);

            final Constant c = new Constant(ecuData.getCurrentPage(), name, classtype, type, offset, "", units, scale, translate, "0", "0", 0);
            ecuData.getConstantVars().put(name, "int");
            ecuData.getConstants().add(c);
        }
        else if (bitsM.matches())
        {
            final String name = bitsM.group(1);
            final String offset = bitsM.group(3);
            final String start = bitsM.group(4);
            final String end = bitsM.group(5);

            final String strBitsValues = bitsM.group(7);

            String[] bitsValues = new String[] {};
            if (strBitsValues != null)
            {
                bitsValues = Patterns.bitsValues.split(strBitsValues);
            }

            final Constant c = new Constant(ecuData.getCurrentPage(), name, "bits", "", Integer.parseInt(offset.trim()), "[" + start + ":" + end + "]", "", "1", "0", "0", "0", 0, bitsValues);
            ecuData.getConstantVars().put(name, "int");
            ecuData.getConstants().add(c);

        }
        else if (line.startsWith("#"))
        {
            final String preproc = (processPreprocessor(ecuData, line));
            final Constant c = new Constant(ecuData.getCurrentPage(), preproc, "", "PREPROC", 0, "", "", "0", "0", "0", "0", 0);
            ecuData.getConstants().add(c);
        }
    }

    private static String removeCurlyBrackets(final String line)
    {
        return line.replaceAll("\\{", "").replaceAll("\\}", "");
    }

    /**
     * Process the [Menu] section of the ini file
     * 
     * @param line
     */
    public static void processMenu(final ECUData ecuData, String line)
    {
        line = removeComments(line);
        if (StringUtils.isEmpty(line))
        {
            return;
        }

        final Matcher menuDialog = Patterns.menuDialog.matcher(line);
        final Matcher menu = Patterns.menu.matcher(line);
        final Matcher subMenu = Patterns.subMenu.matcher(line);

        final List<MenuTracker> menuDefs = ecuData.getMenuDefs();
        MenuTracker m = null;
        if (menuDefs.size() > 0)
        {
            m = menuDefs.get(menuDefs.size() - 1);
        }

        if (m == null)
        {
            m = new MenuTracker();
            menuDefs.add(m);
        }

        if (menuDialog.matches())
        {
            currentMenuDialog = menuDialog.group(1);
        }
        else if (menu.matches())
        {
            final MenuDefinition x = new MenuDefinition(currentMenuDialog, menu.group(1));
            m.addItem(currentMenuDialog, x);
        }
        else if (subMenu.matches())
        {
            final String name = subMenu.group(1);
            final String label = subMenu.group(3);
            final String randomNumber = subMenu.group(5);
            String expression = subMenu.group(7);

            final SubMenuDefinition x = new SubMenuDefinition(name, label, randomNumber);
            m.addItem(currentMenuDialog, x);

            // Add the expression too
            if (expression != null && !StringUtils.isEmpty(expression))
            {
                expression = expression.trim();
                expression = removeCurlyBrackets(expression);
                expression = ExpressionWrangler.convertExpr(expression);
                
                ecuData.getMenuControlExpressions().put(name, expression);
            }
        }
        else
        {
            final MenuPreProcessor p = new MenuPreProcessor(processPreprocessor(ecuData, line));
            m.addItem(currentMenuDialog, p);
        }
    }

    /**
     * Process the [TablEditor] section of the ini file
     * 
     * @param line
     */
    public static void processTableEditor(final ECUData ecuData, String line)
    {
        line = removeComments(line);
        if (StringUtils.isEmpty(line))
        {
            return;
        }

        final Matcher table = Patterns.tablEditorTable.matcher(line);
        final Matcher xBins = Patterns.tablEditorXBins.matcher(line);
        final Matcher yBins = Patterns.tablEditorYBins.matcher(line);
        final Matcher zBins = Patterns.tablEditorZBins.matcher(line);
        final Matcher upDownLabel = Patterns.tablEditorUpDownLabel.matcher(line);
        final Matcher gridHeight = Patterns.tablEditorGridHeight.matcher(line);
        final Matcher gridOrient = Patterns.tablEditorGridOrient.matcher(line);

        final List<TableTracker> tableDefs = ecuData.getTableDefs();
        TableTracker t = null;
        if (tableDefs.size() > 0)
        {
            t = tableDefs.get(tableDefs.size() - 1);
        }
        if (t == null)
        {
            t = new TableTracker();
            tableDefs.add(t);
        }
        if (table.matches())
        {
            if (t.isDefinitionCompleted())
            {
                t = new TableTracker();
                tableDefs.add(t);
            }
            final TableDefinition td = new TableDefinition(t, table.group(1), table.group(2), table.group(3), table.group(4));
            t.addItem(td);
        }
        else if (xBins.matches())
        {
            final XBins x = new XBins(xBins.group(1), xBins.group(2), xBins.group(4));
            t.addItem(x);
        }
        else if (yBins.matches())
        {
            final YBins y = new YBins(yBins.group(1), yBins.group(2), yBins.group(4));
            t.addItem(y);
        }
        else if (zBins.matches())
        {
            final ZBins z = new ZBins(zBins.group(1));
            t.addItem(z);
        }
        else if (upDownLabel.matches())
        {
            final UpDownLabel l = new UpDownLabel(upDownLabel.group(1), upDownLabel.group(2));
            t.addItem(l);
        }
        else if (gridHeight.matches())
        {
            final GridHeight g = new GridHeight(gridHeight.group(1));
            t.addItem(g);
        }
        else if (gridOrient.matches())
        {
            final GridOrient g = new GridOrient(gridOrient.group(1), gridOrient.group(2), gridOrient.group(3));
            t.addItem(g);
        }
        else
        {
            final PreProcessor p = new PreProcessor(processPreprocessor(ecuData, line));
            if ((t != null) && !t.isDefinitionCompleted())
            {
                t.addItem(p);
            }
            else
            {
                t = new TableTracker();
                tableDefs.add(t);
                t.addItem(p);
            }
        }
    }

    /**
     * Process the [CurveEditor] section of the ini file
     * 
     * @param line
     */
    public static void processCurveEditor(final ECUData ecuData, String line)
    {
        line = removeComments(line);
        if (StringUtils.isEmpty(line))
        {
            return;
        }

        line = removeCurlyBrackets(line);

        final Matcher curve = Patterns.curve.matcher(line);
        final Matcher columnLabel = Patterns.curveColumnLabel.matcher(line);
        final Matcher xAxis = Patterns.curveXAxis.matcher(line);
        final Matcher yAxis = Patterns.curveYAxis.matcher(line);
        final Matcher xBins = Patterns.curveXBins.matcher(line);
        final Matcher yBins = Patterns.curveYBins.matcher(line);
        final Matcher gauge = Patterns.curveGauge.matcher(line);
        final Matcher lineLabel = Patterns.curveLineLabel.matcher(line);

        final List<CurveTracker> curveDefs = ecuData.getCurveDefs();
        CurveTracker c = null;
        if (line.contains("cltlowlim"))
        {
            // Break point hook
            @SuppressWarnings("unused")
            final int x = 1;
        }
        if (curveDefs.size() > 0)
        {
            c = curveDefs.get(curveDefs.size() - 1);
        }
        if (c == null)
        {
            c = new CurveTracker();
            curveDefs.add(c);
        }

        if (curve.matches())
        {
            if (c.isDefinitionCompleted())
            {
                c = new CurveTracker();
                curveDefs.add(c);
            }
            final CurveDefinition cd = new CurveDefinition(c, curve.group(1), curve.group(2));
            c.addItem(cd);
        }
        else if (columnLabel.matches())
        {
            final CurveColumnLabel x = new CurveColumnLabel(columnLabel.group(1), columnLabel.group(2));
            c.addItem(x);
        }
        else if (xAxis.matches())
        {
            final CurveXAxis x = new CurveXAxis(xAxis.group(1), xAxis.group(2), xAxis.group(3));
            c.addItem(x);
        }
        else if (yAxis.matches())
        {
            final CurveYAxis x = new CurveYAxis(yAxis.group(1), yAxis.group(2), yAxis.group(3));
            c.addItem(x);
        }
        else if (xBins.matches())
        {
            String xBins2 = "0";
            if (xBins.group(3) != null)
            {
                xBins2 = xBins.group(3);
            }

            final CurveXBins x = new CurveXBins(xBins.group(1), xBins2, xBins.group(5));
            c.addItem(x);
        }
        else if (yBins.matches())
        {
            final CurveYBins x = new CurveYBins(yBins.group(1));
            c.addItem(x);
        }
        else if (gauge.matches())
        {
            final CurveGauge x = new CurveGauge(gauge.group(1));
            c.addItem(x);
        }
        else if (lineLabel.matches())
        {
            final CurveLineLabel x = new CurveLineLabel(lineLabel.group(1));
            c.addItem(x);
        }
        else
        {
            final CurvePreProcessor p = new CurvePreProcessor(processPreprocessor(ecuData, line));
            if ((c != null) && !c.isDefinitionCompleted())
            {
                c.addItem(p);
            }
            else
            {
                c = new CurveTracker();
                curveDefs.add(c);
                c.addItem(p);
            }
        }
    }

    /**
     * Process the [ControllerCommands] section of the ini file
     * 
     * @param line
     */
    public static void processControllerCommands(final ECUData ecuData, String line)
    {
        line = removeComments(line);
        if (StringUtils.isEmpty(line))
        {
            return;
        }

        final Matcher controllerCommands = Patterns.controllerCommands.matcher(line);
        if (controllerCommands.matches())
        {
            final String name = controllerCommands.group(1);
            final String command = controllerCommands.group(2).replace("\\", "\\\\");

            final ControllerCommand controllerCommand = new ControllerCommand(name, command);
            ecuData.getControllerCommands().add(controllerCommand);
        }
    }

    /**
     * Process the [UserDefined] section of the ini file
     * 
     * @param line
     */
    public static void processUserDefined(final ECUData ecuData, String line)
    {
        line = removeComments(line);
        if (StringUtils.isEmpty(line))
        {
            return;
        }

        final Matcher dialog = Patterns.dialog.matcher(line);
        final Matcher dialogField = Patterns.dialogField.matcher(line);
        final Matcher dialogDisplayOnlyField = Patterns.dialogDisplayOnlyField.matcher(line);
        final Matcher dialogPanel = Patterns.dialogPanel.matcher(line);
        final Matcher commandButton = Patterns.commandButton.matcher(line);

        final List<UserDefinedTracker> dialogDefs = ecuData.getDialogDefs();
        UserDefinedTracker d = null;
        if (dialogDefs.size() > 0)
        {
            d = dialogDefs.get(dialogDefs.size() - 1);
        }

        if (dialog.matches())
        {
            final String name = dialog.group(1);
            final String label = dialog.group(3);
            final String axis = dialog.group(5);

            d = new UserDefinedTracker();
            dialogDefs.add(d);
            final UserDefinedDefinition x = new UserDefinedDefinition(d, name, label, axis);

            d.addItem(x);
            d.setName(name);
        }
        else if (dialogField.matches())
        {
            createDialogField(ecuData, dialogField, d, false, false);
        }
        else if (dialogDisplayOnlyField.matches())
        {
            createDialogField(ecuData, dialogDisplayOnlyField, d, true, false);
        }
        else if (commandButton.matches())
        {
            createDialogField(ecuData, commandButton, d, false, true);
        }
        else if (dialogPanel.matches())
        {
            final String name = dialogPanel.group(1);
            final String orientation = dialogPanel.group(3);

            String expression = dialogPanel.group(5);
            final String visibilityFlag = d.getName() + "_" + name;

            if (expression != null && !StringUtils.isEmpty(expression))
            {
                expression = expression.trim();
                expression = removeCurlyBrackets(expression);
                expression = ExpressionWrangler.convertExpr(expression);
                
                ecuData.getFieldControlExpressions().put(visibilityFlag, expression);
            }

            final UserDefinedPanel x = new UserDefinedPanel(name, orientation);
            d.addItem(x);
        }
        else
        {
            final String preproc = processPreprocessor(ecuData, line);
            if (!preproc.equals(""))
            {
                final UserDefinedPreProcessor p = new UserDefinedPreProcessor(preproc);
                if (d != null)
                {
                    d.addItem(p);
                }
                else
                {
                    d = new UserDefinedTracker();
                    dialogDefs.add(d);
                    d.addItem(p);
                }
            }
        }
    }

    private static void createDialogField(final ECUData ecuData, final Matcher dialogField, final UserDefinedTracker d, final boolean readOnly, final boolean isCommandButton)
    {
        final String label = dialogField.group(1).trim();
        String name = dialogField.group(3);
        if (name != null)
        {
            name = name.trim();
        }
        String expression = dialogField.group(5);
        final String visibilityFlag = d.getName() + "_" + name;
        if (visibilityFlag.equals("null_staged_extended_opts_simult"))
        {
            // Break point hook
            @SuppressWarnings("unused")
            final int x = 1;
        }
        if (expression != null && !StringUtils.isEmpty(expression))
        {
            expression = expression.trim();
            expression = removeCurlyBrackets(expression);
            expression = ExpressionWrangler.convertExpr(expression);
        
            ecuData.getFieldControlExpressions().put(visibilityFlag, expression);
        }
        
        String commandOnClose = "";
        if (isCommandButton && (dialogField.group(7) != null))
        {
            commandOnClose = dialogField.group(7);
        }

        final UserDefinedField x = new UserDefinedField(label, name, readOnly, isCommandButton, commandOnClose);
        d.addItem(x);
    }

    public static void processConstantsExtensions(final ECUData ecuData, String line)
    {
        line = removeComments(line);

        if (line.contains("defaultValue"))
        {
            String statement = "";
            final String[] definition = line.split("=")[1].split(",");
            if (definition[1].contains("\""))
            {
                statement = "String ";
            }
            else
            {
                statement = "int ";
            }

            statement += definition[0] + " = " + definition[1] + ";";
            ecuData.getDefaults().add(statement);
        }
        else if (line.contains("requiresPowerCycle"))
        {
            final String field = line.split("=")[1];
            ecuData.getRequiresPowerCycle().add(field.trim());
        }
    }

    public static void processPcVariables(final ECUData ecuData, final String line)
    {

    }

    public static void processSettingGroups(final ECUData ecuData, String line)
    {
        line = removeComments(line);
        final ArrayList<SettingGroup> groups = ecuData.getSettingGroups();
        if (line.contains("settingGroup"))
        {
            final String data = line.split("=")[1];
            final int commaIndex = data.indexOf(',');
            final String groupName = data.substring(0, commaIndex).trim();
            final String description = data.substring(commaIndex + 1).replaceAll("\"", "").trim();
            final SettingGroup group = new SettingGroup(groupName, description);
            groups.add(group);
        }
        else if (line.contains("settingOption"))
        {
            final SettingGroup currentGroup = groups.get(groups.size() - 1);
            final String data = line.split("=")[1];
            final int commaIndex = data.indexOf(',');
            final String flagName = data.substring(0, commaIndex).trim();
            final String description = data.substring(commaIndex + 1).replaceAll("\"", "").trim();
            currentGroup.addOption(flagName, description);
        }
    }

    static void processGaugeEntry(final ECUData ecuData, String line)
    {
        line = removeComments(line);

        final Matcher m = Patterns.gauge.matcher(line);
        if (m.matches())
        {
            final String name = m.group(1);
            final String channel = m.group(2);
            final String title = m.group(3);
            final String units = m.group(4);
            final String lo = m.group(5);
            final String hi = m.group(6);
            final String loD = m.group(7);
            final String loW = m.group(8);
            final String hiW = m.group(9);
            final String hiD = m.group(10);
            final String vd = m.group(11);
            final String ld = m.group(12);

            String g = String.format("gauges.put(\"%s\",new GaugeDetails(\"Gauge\",\"\",\"%s\",\"%s\",%s,\"%s\",\"%s\",%s,%s,%s,%s,%s,%s,%s,%s,45));", name, name, channel, channel, title, units, lo, hi, loD, loW, hiW, hiD, vd, ld);

            g = g.replace("{", "").replace("}", "");
            final String gd = String.format("<tr><td>Gauge</td><td></td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", name, channel, title, units, lo, hi,
                    loD, loW, hiW, hiD, vd, ld);
            ecuData.getGaugeDoc().add(gd);
            ecuData.getGaugeDef().add(g);
        }
        else if (line.startsWith("#"))
        {
            ecuData.getGaugeDef().add(processPreprocessor(ecuData, line));
            ecuData.getGaugeDoc().add(String.format("<tr><td colspan=\"12\" id=\"preprocessor\">%s</td></tr>", line));
        }

    }

}
