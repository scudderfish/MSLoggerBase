import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Normaliser
{
	private static Pattern		bits		= Pattern.compile("(\\w*)\\s*=\\s*bits,\\s*(.*),\\s*(.*),\\s*\\[(\\d):(\\d)\\].*");
	private static Pattern		scalar		= Pattern.compile("(\\w*)\\s*=\\s*scalar,\\s*([U|S]\\d+),\\s*(\\d+),\\s*.*,.*");
	private static Pattern		expr		= Pattern.compile("(\\w*)\\s*=\\s*\\{\\s*(.*)\\s*\\}.*");
	private static Pattern		ternary		= Pattern.compile("(.*)\\((.*)\\?(.*)");
	private static List<String>	runtimeVars	= new ArrayList<String>();
	private static List<String>	expressions	= new ArrayList<String>();
	private static List<String>	logEntries	= new ArrayList<String>();

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
		runtimeVars = new ArrayList<String>();
		expressions = new ArrayList<String>();
		logEntries = new ArrayList<String>();

		boolean processing = false;
		BufferedReader br = new BufferedReader(new FileReader(filename));

		String line;

		while ((line = br.readLine()) != null)
		{
			if (line.trim().equals("[OutputChannels]"))
			{
				processing = true;
				continue;
			}
			if (line.trim().equals("[Datalog]"))
			{
				processing = false;
				continue;
			}
			if (processing)
			{
				processText(line);

			}
		}
		PrintWriter writer = new PrintWriter(new FileWriter(filename + ".jd"));

		for (String defn : runtimeVars)
		{
			writer.println(defn);
			// System.out.println(defn);
		}
		for (String defn : expressions)
		{
			writer.println(defn);
			// System.out.println(defn);
		}
		writer.close();
	}

	private static void processText(String line)
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
			definition = (name + " = getBits(ochBuffer," + offset + "," + start + "," + end + ");");
			runtimeVars.add(definition);
		}
		else if (scalarM.matches())
		{
			String name = scalarM.group(1);
			String dataType = scalarM.group(2);
			String offset = scalarM.group(3);
			definition = getScalar(name, dataType, offset);
			runtimeVars.add(definition);
		}
		else if (exprM.matches())
		{
			String name = exprM.group(1);
			String expression = exprM.group(2).trim();
			Matcher ternaryM = ternary.matcher(expression);
			if (ternaryM.matches())
			{
				expression = "((" + ternaryM.group(2) + " != 0 ) ? " + ternaryM.group(3);
			}
			definition = name + " = (" + expression + ")";
			expressions.add(definition);
		}
		else
		{
			// System.out.println("// " + line);
		}
	}

	private static String getScalar(String name, String dataType, String offset)
	{
		String definition = name + " = get";
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
		definition += "(ochBuffer," + offset + ");";
		return definition;
	}

}
