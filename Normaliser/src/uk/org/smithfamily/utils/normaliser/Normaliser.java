package uk.org.smithfamily.utils.normaliser;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Normaliser
{
    private enum Section
    {
        None, Header, Expressions, Gauges, Logs, FrontPage, Constants, PcVariables, ConstantsExtensions, Menu, TableEditor, CurveEditor, ControllerCommands, UserDefined, SettingGroups
    }

    private static final String TAB = "    ";
    private static ECUData ecuData = new ECUData();
    private static Map<String, String> classList = new TreeMap<String, String>();
    private static Section currentSection;
    private static String className;
    private static File outputDirectory;

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(final String[] args) throws IOException
    {
        final File f = new File(args[0]);
        outputDirectory = new File(args[1]);
        outputDirectory.mkdirs();

        final BufferedReader br = new BufferedReader(new FileReader(f));

        String line;

        while ((line = br.readLine()) != null)
        {
            if (!line.startsWith("#"))
            {
                preProcess(f.getParent(), line);
            }
        }
        outputRegistry(f.getParentFile());
        br.close();
    }

    private static void outputRegistry(final File baseLocation) throws IOException
    {
        final File srcDirectory = new File(outputDirectory, "gen_src/uk/org/smithfamily/mslogger/ecuDef/gen");
        final File or = new File(srcDirectory, "ECURegistry.java");
        final PrintWriter w = new PrintWriter(new FileWriter(or));

        final File template = new File(baseLocation, "ECURegistry.java");
        final BufferedReader br = new BufferedReader(new FileReader(template));
        String line;

        while ((line = br.readLine()) != null)
        {
            if (line.trim().equals("<LIST>"))
            {
                for (final String name : classList.keySet())
                {
                    final String sig = classList.get(name);
                    w.println(String.format("        registerEcu(%s.class,%s);", name, sig));
                }
            }
            else
            {
                w.println(line);
            }
        }
        br.close();
        w.close();
    }

    /**
     * Initialise our stores to start a new class definition
     * 
     * @param filename
     * @param line
     * @throws IOException
     */
    private static void preProcess(final String directory, final String filename) throws IOException
    {
        ecuData.setSignatureDeclaration("");
        ecuData.setQueryCommandStr("");
        ecuData.reset();
        currentSection = Section.None;

        final File f = new File(directory, filename);
        if (f.isDirectory())
        {
            return;
        }

        className = f.getName();
        process(f, false);
    }

    /**
     * Iterate over the file to read it
     * 
     * @param f
     * @param subRead
     * @throws IOException
     */
    private static void process(final File f, final boolean subRead) throws IOException
    {
        final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "CP1252"));

        String line;

        while ((line = br.readLine()) != null)
        {
            if (line.startsWith("#include "))
            {
                handleImport(line, f);
                continue;
            }

            if (line.trim().equals("[MegaTune]"))
            {
                currentSection = Section.Header;
                continue;
            }
            else if (line.trim().equals("[OutputChannels]"))
            {
                currentSection = Section.Expressions;
                continue;
            }
            else if (line.trim().equals("[Datalog]"))
            {
                currentSection = Section.Logs;
                continue;

            }
            else if (line.trim().equals("[GaugeConfigurations]"))
            {
                currentSection = Section.Gauges;
                continue;
            }
            else if (line.trim().equals("[Menu]"))
            {
                currentSection = Section.Menu;
                continue;
            }
            else if (line.trim().equals("[TableEditor]"))
            {
                currentSection = Section.TableEditor;
                continue;
            }
            else if (line.trim().equals("[CurveEditor]"))
            {
                currentSection = Section.CurveEditor;
                continue;
            }
            else if (line.trim().equals("[UserDefined]"))
            {
                currentSection = Section.UserDefined;
                continue;
            }
            else if (line.trim().equals("[FrontPage]"))
            {
                currentSection = Section.FrontPage;
            }
            else if (line.trim().equals("[Constants]"))
            {
                currentSection = Section.Constants;
            }
            else if (line.trim().equals("[PcVariables]"))
            {
                currentSection = Section.PcVariables;
            }
            else if (line.trim().equals("[ConstantsExtensions]"))
            {
                currentSection = Section.ConstantsExtensions;
            }
            else if (line.trim().equals("[SettingGroups]"))
            {
                currentSection = Section.SettingGroups;
            }
            else if (line.trim().equals("[ControllerCommands]"))
            {
                currentSection = Section.ControllerCommands;
            }
            else if (line.trim().startsWith("["))
            {
                currentSection = Section.None;
                continue;
            }

            switch (currentSection)
            {
            case Expressions:
                Process.processExpr(ecuData, line);
                break;
            case Logs:
                Process.processLogEntry(ecuData, line);
                break;
            case Gauges:
                Process.processGaugeEntry(ecuData, line);
                break;
            case Header:
                Process.processHeader(ecuData, line);
                break;
            case FrontPage:
                Process.processFrontPage(ecuData, line);
                break;
            case Constants:
                Process.processConstants(ecuData, line);
                break;
            case PcVariables:
                Process.processPcVariables(ecuData, line);
                break;
            case ConstantsExtensions:
                Process.processConstantsExtensions(ecuData, line);
                break;
            case Menu:
                Process.processMenu(ecuData, line);
                break;
            case TableEditor:
                Process.processTableEditor(ecuData, line);
                break;
            case CurveEditor:
                Process.processCurveEditor(ecuData, line);
                break;
            case ControllerCommands:
                Process.processControllerCommands(ecuData, line);
                break;
            case UserDefined:
                Process.processUserDefined(ecuData, line);
                break;
            case SettingGroups:
                Process.processSettingGroups(ecuData, line);
            case None:
                break;
            default:
                break;
            }

        }
        br.close();
        if (!subRead)
        {
            writeFile(className);
        }
    }

    /**
     * Process an included/imported file
     * 
     * @param line
     * @param parentFile
     * @throws IOException
     */
    private static void handleImport(final String line, final File parentFile) throws IOException
    {
        final Pattern importFile = Pattern.compile(".*\\\"(.*)\\\"");

        final Matcher importFileM = importFile.matcher(line);
        if (importFileM.matches())
        {
            final String fileName = importFileM.group(1);
            final File imported = new File(parentFile.getAbsoluteFile().getParentFile(), fileName);
            process(imported, true);
        }

    }

    /**
     * Write all the firmware definition to a Java class
     * 
     * @param className The class name to generate
     * @throws IOException
     */
    private static void writeFile(String className) throws IOException
    {
        className = StringUtils.capitalize(className);
        className = StringUtils.remove(className, ".");
        className = StringUtils.remove(className, "ini");
        className = StringUtils.replace(className, " ", "_");
        className = StringUtils.replace(className, "-", "_");
        className = StringUtils.replace(className, "&", "_");
        String classFile = "";

        classList.put(className, ecuData.getClassSignature());
        final String directory = outputDirectory.getAbsolutePath() + File.separator + "gen_src/uk/org/smithfamily/mslogger/ecuDef/gen/";
        new File(directory).mkdirs();
        classFile = directory + className + ".java";
        System.out.println("Writing to " + classFile);
        final PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(classFile), "CP1252"));
        final String fingerprint = getFingerprint();
        System.out.println(fingerprint + " : " + className);

        Output.outputPackageAndIncludes(ecuData, writer);

        writer.println("/*");
        writer.println("Fingerprint : " + fingerprint);
        writer.println("*/");

        writer.println("@SuppressWarnings(\"unused\")");
        writer.println("public class " + className + " implements MSECUInterface, DataSource\n{");
        Output.outputConstructor(ecuData, writer, className);
        Output.outputOutputChannels(ecuData, writer);
        writer.println(TAB + "private Map<String,Double> fields = new HashMap<String,Double>();");
        writer.println(TAB + ecuData.getQueryCommandStr());
        writer.println(TAB + ecuData.getSignatureDeclaration());
        writer.println(TAB + ecuData.getOchGetCommandStr());
        writer.println(TAB + ecuData.getOchBlockSizeStr());
        Output.outputRequiresPowerCycle(ecuData, writer);
        Output.outputRTCalcs(ecuData, writer);
        Output.outputLogInfo(ecuData, writer);
        Output.outputMenus(ecuData, writer);
        Output.outputUserDefined(ecuData, writer);
        Output.outputTableEditors(ecuData, writer);
        Output.outputCurves(ecuData, writer);
        Output.outputUserDefinedVisibilityFlags(ecuData, writer);
        Output.outputMenuVisibilityFlags(ecuData, writer);
        Output.outputSettingGroups(ecuData, writer);
        Output.outputControllerCommands(ecuData, writer);

        Output.outputOverrides(ecuData, writer);
        Output.outputGauges(ecuData, writer);
        Output.outputLoadConstants(ecuData, writer);
        Output.outputFlagsAndConstants(ecuData, writer);
        Output.outputGlobalVars(ecuData, writer);
        writer.println("\n}\n");

        writer.close();
    }

    /**
     * Get the finger print of a class
     * 
     * @return The finger print as an MD5
     */
    private static String getFingerprint()
    {
        final StringBuffer b = new StringBuffer();
        try
        {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(ecuData.getFingerprintSource().getBytes());
            for (int i = 0; i < array.length; i++)
            {
                b.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
        }
        catch (final NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return b.toString();
    }

}
