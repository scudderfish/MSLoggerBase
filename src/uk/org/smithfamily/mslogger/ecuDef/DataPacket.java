package uk.org.smithfamily.mslogger.ecuDef;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataPacket implements Serializable
{
	private static final long serialVersionUID = -4644371046540826870L;
	private Map<String, Double> fields;
	private byte[] rawBuffer;

	public DataPacket(Map<String, Double> fields, byte[] rawBuffer)
	{
		this.fields = new HashMap<String, Double>();
		this.fields.putAll(fields);
		this.rawBuffer = rawBuffer.clone();
	}

	public Map<String, Double> getFields()
	{
		return fields;
	}

	public byte[] getRawBuffer()
	{
		return rawBuffer;
	}
}
