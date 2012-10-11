package uk.org.smithfamily.mslogger.ecuDef;

public interface MSControllerInterface
{

    boolean isSet(String string);

    byte[] loadPage(int i, int j, int k, byte[] bs, byte[] bs2);

    double[] loadByteVector(byte[] pageBuffer, int offset, int width, double scale, double translate, boolean signed);

    double[][] loadByteArray(byte[] pageBuffer, int offset, int width, int height, double scale, double translate, boolean signed);

    double[] loadWordVector(byte[] pageBuffer, int offset, int width, double scale, double translate, boolean signed);

    double[][] loadWordArray(byte[] pageBuffer, int offset, int width, int height, double scale, double translate, boolean signed);

    double round(double x);

    int table(double x, String t);

    double timeNow();

    double tempCvt(double x);
}
