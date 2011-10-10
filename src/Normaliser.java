import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Normaliser
{
	private static Pattern	bits	= Pattern.compile("(\\w*)\\s*=\\s*bits,\\s*(.*),\\s*(.*),\\s*\\[(\\d):(\\d)\\].*");
	private static Pattern	scalar	= Pattern.compile("(\\w*)\\s*=\\s*scalar,\\s*([U|S]\\d+),\\s*(\\d+),\\s*.*,.*");
	private static Pattern	expr	= Pattern.compile("(\\w*)\\s*=\\s*\\{\\s*(.*)\\s*\\}.*");
	private static Pattern	ternary	= Pattern.compile("(.*)\\((.*)\\?(.*)");

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
	}

	private static void processText(String line)
	{
		line = StringUtils.trim(line).split(";")[0];
		if (StringUtils.isEmpty(line))
		{
			return;
		}
		Matcher bitsM = bits.matcher(line);
		Matcher scalarM = scalar.matcher(line);
		Matcher exprM = expr.matcher(line);
		if(bitsM.matches())
		{
			String name = bitsM.group(1);
			String a = bitsM.group(2);
			String b = bitsM.group(3);
			String c = bitsM.group(4);
			String d = bitsM.group(5);
			System.out.println(name+" = getBits(ochBuffer,"+b+","+c+","+d+");");
			}
		else if(scalarM.matches())
		{
			
		}
		else if (exprM.matches())
		{
			
		}
		else
		{
			System.out.println("// " + line);	
		}
		
	}

}
