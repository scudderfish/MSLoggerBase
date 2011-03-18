package uk.org.smithfamily.msdisp.parser;

public class ByteString
{
    private String contents;
    public ByteString(String s)
    {
        contents = s;
    }

    public boolean empty()
    {
        return contents.length()==0;
    }

    public ByteString xlate()
    {
        
        return new ByteString(contents);
    }
    public byte[] bytes()
    {
        return contents.getBytes();
    }
    public String toString()
    {
        return new String(bytes());
    }
}
