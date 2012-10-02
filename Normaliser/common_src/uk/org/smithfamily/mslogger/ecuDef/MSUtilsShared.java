package uk.org.smithfamily.mslogger.ecuDef;

import java.util.List;
/**
 *
 */
public class MSUtilsShared
{
    
    /**
     * Method that return the width and height of an array from it's shape (from the INI)
     * 
     * @param shape Shape defined in the INI
     * @return width and height
     */
    public static int[] getArraySize(String shape)
    {
        String arraySpec = shape.replace("[", "").replace("]", "");
        String[] sizes = arraySpec.split("x");
        int width = Integer.parseInt(sizes[0].trim());
        int height = sizes.length == 2 ? Integer.parseInt(sizes[1].trim()) : -1;
        
        int[] size = {width, height};
        
        return size;
    }
    
    /**
     * Used with pageReadCommand, pageValueWrite and pageChunkWrite
     * 
     * @param listPageCommand
     * @param s
     * @param offset
     * @param count
     * @param value
     * @param pageNo
     * @return
     */
    public static String HexStringToBytes(List<String> listPageCommand, String s, int offset, int count, int value, int pageNo)
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
                break;

            case '%':
                p++;
                c = s.charAt(p);

                if (c == '2')
                {
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
                        String identifier = listPageCommand.get(pageNo - 1);
    
                        ret += HexStringToBytes(listPageCommand, identifier, offset, count, value, pageNo);
                    }
                }
                else if (c == 'v')
                {
                    ret += bytes(value);
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
    
    /**
     * 
     * @param s
     * @return
     */
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
    
    /**
     * 
     * @param val
     * @return
     */
    private static String bytes(int val)
    {
        int hi = val / 256;
        int low = val % 256;
        if (hi > 127)
            hi -= 256;
        return "" + hi + "," + low;
    }
}
