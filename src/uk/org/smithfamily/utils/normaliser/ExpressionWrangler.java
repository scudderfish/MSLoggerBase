package uk.org.smithfamily.utils.normaliser;
import java.util.HashMap;
import java.util.Map;

public class ExpressionWrangler
{
	static String[] exprs = { 
		"v == 0x0e","RevLimCLTbased & 1"," pwmIdle & (pwmidlewhen >1) ","a &  (b >1)","a && !b"
	/*	,
		"(a & 0x7) != 5", "staged_first_param && (staged_first_param  & 0x7) != 5",
			"staged_first_param && staged_transition_on && ((staged_first_param & 0x7) != 5)", "feature3_3 ", "injctl && extrainj", "(spk_mode0 > 3) && seq_inj",
			"(spk_mode0 > 3) && (seq_inj == 3)", "(spk_mode0 > 3) && (seq_inj == 3) && (injusetable == 0) && (injdualvalue)", "f5_0_tsf && f5_0_tsf_opt == 3",
			"testmode == 2 && testop_pwm && !extrainj" 
			
		*/	
			};
	static Map<String,String> translations = new HashMap<String,String>();
	static
	{
		translations.put("0", "false");
		translations.put("RevLimCLTbased & 1", "(RevLimCLTbased & 1) != 0");
		translations.put("RevLimOption & 4", "(RevLimOption & 4) != 0"); 
		translations.put("RevLimOption & 1", "(RevLimOption & 1) != 0"); 
		translations.put("secondtrigopts & 0x1", "(secondtrigopts & 0x1) != 0"); 
		translations.put("(launchlimopt & 1) && (launch_opt_on >0)", "(launchlimopt & 1) !=0 && (launch_opt_on >0)"); 
		translations.put("spk_config_trig2 & 0x2", "(spk_config_trig2 & 0x2) != 0"); 
		translations.put("((dualfuel_opt_out || dualfuel_opt_mode) && staged_extended_opts_use_v3) || ((nCylinders > 4) && (dualfuel_opt_out || dualfuel_opt_mode) && hardware_fuel && !(sequential & 0x1))", "((dualfuel_opt_out !=0 || dualfuel_opt_mode !=0) && staged_extended_opts_use_v3 != 0) || ((nCylinders > 4) && (dualfuel_opt_out !=0 || dualfuel_opt_mode !=0) && hardware_fuel !=0 && !((sequential & 0x1)!=0))");	
		translations.put("(staged_extended_opts_use_v3 && staged_first_param) || ((nCylinders > 4) && (staged_first_param) && (hardware_fuel) && !(sequential & 0x1))", "(staged_extended_opts_use_v3!=0 && staged_first_param!=0) || ((nCylinders > 4) && (staged_first_param!=0) && (hardware_fuel!=0) && !((sequential & 0x1)!=0))");
		translations.put("(spk_config_trig2 & 0x2) && spk_mode0 == 4", "(spk_config_trig2 & 0x2)!=0 && spk_mode0 == 4");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		for (String e : exprs)
		{
			String result = convertExpr(e);
			System.out.println(String.format("%s  :  %s", e, result));
		}
	}

	public static String convertExpr(String e)
	{
		e=e.trim();
		e=e.replaceAll("  ", " ");
		
		if(translations.containsKey(e))
		{
			return translations.get(e);
		}
		StringBuffer b = new StringBuffer();

		boolean inIdentifier = false;
		boolean inConstant = false;
		boolean pendingEqualityTest = false;
		boolean seenComparator = false;
		boolean seenLogical = false;
		boolean negate = false;
		boolean needAnAmp = false;
		for (int i = 0; i < e.length(); i++)
		{
			char c = e.charAt(i);

			if (c == '(')
			{
				int x = 1;
				boolean f = (x & 1) != 0;
			}

			if (c == '!' && e.charAt(i + 1) != '=')
			{
				negate = true;
				continue;
			}
			if (!seenLogical && (c == '&' || c == '|') && c == e.charAt(i + 1))
			{
				seenLogical = true;
			}

			//No fixup some dodgy bitwise vs logical anding in the INIs
			if(!seenLogical && c=='&' && e.charAt(i+1) != '&' && e.charAt(i+2)!='0')
			{
				needAnAmp = true;
				seenLogical = true;
			}
			if (c == '=' || c == '>' || c == '<' || (!seenLogical && (c == '&' || c == '|') && c != e.charAt(i + 1)))
			{
				seenComparator = true;
			}
			if(!inConstant && Character.isDigit(c))
			{
				inConstant = true;
			}
			if (inConstant && Character.isDigit(c) || c == 'x' || (c>='a' && c<='f'))
			{
				inConstant = true;
			}
			if (inConstant && !(Character.isDigit(c) || c == 'x'|| (c>='a' && c<='f')))
			{
				inConstant = false;
			}
			if (!inIdentifier && !inConstant && (Character.isLetterOrDigit(c) || c == '_'))
			{
				inIdentifier = true;
				pendingEqualityTest = true;
				seenLogical = false;
				seenComparator = false;
			}
			if (inIdentifier && !Character.isJavaIdentifierPart(c))
			{
				inIdentifier = false;
			}
			if (pendingEqualityTest && !inIdentifier && !seenComparator && (seenLogical || c == ')'))
			{
				if (negate)
					b.append("==0 ");
				else
					b.append("!=0 ");
				pendingEqualityTest = false;
				negate = false;
				if(needAnAmp)
					b.append('&');
				needAnAmp=false;
			}
			b.append(c);
		}
		if (pendingEqualityTest && !seenComparator)
		{
			if (negate)
				b.append("==0 ");
			else
				b.append("!=0 ");
		}
		String result = b.toString();

		return result;
	}

}
