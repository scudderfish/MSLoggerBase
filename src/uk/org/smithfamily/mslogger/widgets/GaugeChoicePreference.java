package uk.org.smithfamily.mslogger.widgets;

import java.util.*;

import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;

/**
 *
 */
public class GaugeChoicePreference extends ListPreference
{
    /**
     * 
     * @param context
     * @param attrs
     */
	public GaugeChoicePreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		Set<String> nameSet = GaugeRegister.INSTANCE.getGaugeNames();
		List<String> names = asSortedList(nameSet);
		CharSequence[] entries = new CharSequence[names.size()];
		CharSequence[] entryValues = new CharSequence[names.size()];
		int i = 0;
		for (String name : names)
		{
			GaugeDetails gd = GaugeRegister.INSTANCE.getGaugeDetails(name);

			entries[i] = gd.getTitle();
			entryValues[i] = name;
			i++;
		}
		setEntries(entries);
		setEntryValues(entryValues);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c)
	{
		List<T> list = new ArrayList<T>(c);
		java.util.Collections.sort(list);
		return list;
	}

	/**
	 * 
	 * @param context
	 */
	public GaugeChoicePreference(Context context)
	{
		this(context, null);
	}
}
