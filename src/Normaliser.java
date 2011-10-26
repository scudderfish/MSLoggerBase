import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Normaliser
{
    private static Pattern             bits      = Pattern.compile("(\\w*)\\s*=\\s*bits,\\s*(.*),\\s*(.*),\\s*\\[(\\d):(\\d)\\].*");
    private static Pattern             scalar    = Pattern
                                                         .compile("(\\w*)\\s*=\\s*scalar\\s*,\\s*([U|S]\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(.*,.*)");
    private static Pattern             expr      = Pattern.compile("(\\w*)\\s*=\\s*\\{\\s*(.*)\\s*\\}.*");
    private static Pattern             ternary   = Pattern.compile("(.*)\\?(.*)");
    private static Pattern             log       = Pattern.compile("\\s*entry\\s*=\\s*(\\w+)\\s*,\\s*\"(.*)\",.*");
    private static Pattern             binary    = Pattern.compile("(.*)0b([01]{8})(.*)");
    private static Pattern             gauge     = Pattern
                                                         .compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*,\\s*\"(.*?)\"\\s*,\\s*\"(.*?)\"\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*?)\\s*,\\s*(.*)");
    private static List<String>        runtime   = new ArrayList<String>();
    private static List<String>        logHeader = new ArrayList<String>();
    private static List<String>        logRecord = new ArrayList<String>();
    private static List<String>        gaugeDef  = new ArrayList<String>();
    private static Map<String, String> runtimeVars;
    private static Map<String, String> evalVars;
    private static Set<String>         flags;
    private static String              fingerprintSource;

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException
    {
        for (String filename : args)
        {
            process(filename);
        }
    }

    private static void process(String filename) throws IOException
    {
        runtime = new ArrayList<String>();
        logHeader = new ArrayList<String>();
        logRecord = new ArrayList<String>();
        runtimeVars = new HashMap<String, String>();
        evalVars = new HashMap<String, String>();
        flags = new HashSet<String>();
        gaugeDef = new ArrayList<String>();
        fingerprintSource = "";

        boolean processingExpr = false;
        boolean processingLogs = false;
        boolean processingGauges = false;
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;

        while ((line = br.readLine()) != null)
        {
            if (line.trim().equals("[OutputChannels]"))
            {
                processingExpr = true;
                processingLogs = false;
                processingGauges = false;
                continue;
            }
            else if (line.trim().equals("[Datalog]"))
            {
                processingExpr = false;
                processingLogs = true;
                processingGauges = false;
                continue;
            }
            else if (line.trim().equals("[GaugeConfigurations]"))
            {
                processingExpr = false;
                processingLogs = false;
                processingGauges = true;
                continue;

            }
            else if (line.trim().startsWith("["))
            {
                processingExpr = false;
                processingLogs = false;
                processingGauges = false;
                continue;
            }
            if (processingExpr)
            {
                processExpr(line);
            }
            if (processingLogs)
            {
                processLogEntry(line);
            }
            if (processingGauges)
            {
                processGaugeEntry(line);
            }
        }
        writeFile(filename);
    }

    private static void processGaugeEntry(String line)
    {
        System.out.println("Converting " + line);
        Matcher m = gauge.matcher(line);
        if (m.matches())
        {
            String name = m.group(1);
            String channel = m.group(2);
            String title = m.group(3);
            String units = m.group(4);
            String lo = m.group(5);
            String hi = m.group(6);
            String loD = m.group(7);
            String loW = m.group(8);
            String hiW = m.group(9);
            String hiD = m.group(10);
            String vd = m.group(11);
            String ld = m.group(12);

            String g = String.format(
                    "GaugeRegister.INSTANCE.addGauge(new GaugeDetails(\"%s\",\"%s\",\"%s\",\"%s\",%s,%s,%s,%s,%s,%s,%s,%s,0));", name,
                    channel, title, units, lo, hi, loD, loW, hiW, hiD, vd, ld);
            gaugeDef.add(g);

        }
        else if (line.startsWith("#"))
        {
            gaugeDef.add(processPreprocessor(line));
        }

    }

    private static void writeFile(String filename) throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(filename + ".jd"));

        writer.println("public class ECU extends Megasquirt\n{");
        writer.println("//Flags");
        for (String name : flags)
        {
            writer.println("boolean " + name + ";");
        }
        writer.println("//Runtime vars");
        for (String name : runtimeVars.keySet())
        {
            writer.println(runtimeVars.get(name) + " " + name + ";");
        }
        writer.println("\n//eval vars");
        for (String name : evalVars.keySet())
        {
            writer.println(evalVars.get(name) + " " + name + ";");
        }
        writer.println("\n");
        writer.println("	@Override");
        writer.println("	public void calculate(byte[] ochBuffer) throws IOException");
        writer.println("{");
        for (String defn : runtime)
        {
            writer.println(defn);
            // System.out.println(defn);
        }
        writer.println("}");
        writer.println("@Override");
        writer.println("public String getLogHeader()");
        writer.println("{");
        writer.println("	StringBuffer b = new StringBuffer();");
        for (String header : logHeader)
        {
            writer.println(header);
        }
        writer.println("b.append(MSUtils.getLocationLogHeader());");
        writer.println("    return b.toString();\n}\n");
        writer.println("@Override");
        writer.println("public String getLogRow()");
        writer.println("{");
        writer.println("	StringBuffer b = new StringBuffer();");

        for (String record : logRecord)
        {
            writer.println(record);
        }
        writer.println("b.append(MSUtils.getLocationLogRow());");
        writer.println("    return b.toString();\n}\n");
        writer.println("@Override");
        writer.println("public void initGauges()");
        writer.println("{");
        for(String gauge : gaugeDef)
        {
            writer.println(gauge);
        }
        
        writer.println("\n}\n");
        
        writer.close();
        System.out.println(getFingerprint() + " : " + filename);
    }

    private static String getFingerprint()
    {
        StringBuffer b = new StringBuffer();
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(fingerprintSource.getBytes());
            for (int i = 0; i < array.length; i++)
            {
                b.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return b.toString();
    }

    private static void processLogEntry(String line)
    {
        Matcher logM = log.matcher(line);
        if (logM.matches())
        {
            String header = logM.group(2);
            String variable = logM.group(1);
            if ("double".equals(runtimeVars.get(variable)))
            {
                variable = "round(" + variable + ")";
            }
            logHeader.add("b.append(\"" + header + "\").append(\"\\t\");");
            logRecord.add("b.append(" + variable + ").append(\"\\t\");");
        }
        else if (line.startsWith("#"))
        {
            String directive = processPreprocessor(line);
            logHeader.add(directive);
            logRecord.add(directive);
        }
    }

    private static void processExpr(String line)
    {
        String definition = null;
        line = StringUtils.trim(line).split(";")[0];
        if (StringUtils.isEmpty(line))
        {
            return;
        }
        Matcher bitsM = bits.matcher(line);
        Matcher scalarM = scalar.matcher(line);
        Matcher exprM = expr.matcher(line);
        if (bitsM.matches())
        {
            String name = bitsM.group(1);
            String offset = bitsM.group(3);
            String start = bitsM.group(4);
            String end = bitsM.group(5);
            definition = (name + " = MSUtils.getBits(ochBuffer," + offset + "," + start + "," + end + ");");
            runtime.add(definition);
            runtimeVars.put(name, "int");
        }
        else if (scalarM.matches())
        {
            String name = scalarM.group(1);
            String dataType = scalarM.group(2);
            String offset = scalarM.group(3);
            String scalingRaw = scalarM.group(4);
            String[] scaling = scalingRaw.split(",");
            String scale = scaling[1].trim();
            String numOffset = scaling[2].trim();
            if (Double.parseDouble(scale) != 1)
            {
                runtimeVars.put(name, "double");
            }
            else
            {
                runtimeVars.put(name, "int");
            }
            definition = getScalar(name, dataType, offset, scale, numOffset);
            runtime.add(definition);
        }
        else if (exprM.matches())
        {
            String name = exprM.group(1);
            String expression = deBinary(exprM.group(2).trim());
            Matcher ternaryM = ternary.matcher(expression);
            if (ternaryM.matches())
            {
                // System.out.println("BEFORE : " + expression);
                String test = ternaryM.group(1);
                String values = ternaryM.group(2);
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
            definition = name + " = (" + expression + ");";
            runtime.add(definition);
            if (isFloatingExpression(expression))
            {
                evalVars.put(name, "double");
            }
            else
            {
                evalVars.put(name, "int");
            }
        }
        else if (line.startsWith("#"))
        {
            runtime.add(processPreprocessor(line));
        }
        else
        {
            System.out.println(line);
        }
    }

    private static String deBinary(String group)
    {
        Matcher binNumber = binary.matcher(group);
        if (!binNumber.matches())
        {
            return group;
        }
        else
        {
            String binNum = binNumber.group(2);
            int num = Integer.parseInt(binNum, 2);
            String expr = binNumber.group(1) + num + binNumber.group(3);
            return deBinary(expr);
        }
    }

    private static boolean isFloatingExpression(String expression)
    {
        return expression.contains(".");
    }

    private static String processPreprocessor(String line)
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
        String[] components = line.split(" ");
        if (components[0].equals("#if"))
        {
            flags.add(components[1]);
            return ("if (" + components[1] + ")\n{");
        }
        if (components[0].equals("#elif"))
        {
            flags.add(components[1]);
            return ("}\nelse if (" + components[1] + ")\n{");
        }
        if (components[0].equals("#else"))
        {
            return ("}\nelse\n{");
        }
        if (components[0].equals("#endif"))
        {
            return ("}");
        }

        return "";
    }

    private static String getScalar(String name, String dataType, String offset, String scale, String numOffset)
    {
        String definition = name + " = (" + runtimeVars.get(name) + ")((MSUtils.get";
        if (dataType.startsWith("S"))
        {
            definition += "Signed";
        }
        int size = Integer.parseInt(dataType.substring(1));
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
        definition += "(ochBuffer," + offset + ") + " + numOffset + ") * " + scale + ");";
        fingerprintSource += definition;
        return definition;
    }

}
