package uk.org.smithfamily.mslogger.ecuDef;

public interface MSControllerInterface
{

    boolean isSet(String string);

    byte[] loadPage(int i, int j, int k, byte[] bs, byte[] bs2);

    int[] loadByteVector(byte[] pageBuffer, int offset, int width, boolean signed);

    int[][] loadByteArray(byte[] pageBuffer, int offset, int width, int height, boolean signed);

    int[] loadWordVector(byte[] pageBuffer, int offset, int width, boolean signed);

    int[][] loadWordArray(byte[] pageBuffer, int offset, int width, int height, boolean signed);

    double round(double x);

    int table(double x, String t);

    double timeNow();

    double tempCvt(double x);
}
