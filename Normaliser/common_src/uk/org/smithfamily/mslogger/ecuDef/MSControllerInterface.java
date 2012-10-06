package uk.org.smithfamily.mslogger.ecuDef;

public interface MSControllerInterface
{

    boolean isSet(String string);

    byte[] loadPage(int i, int j, int k, byte[] bs, byte[] bs2);

    double[] loadByteVector(byte[] pageBuffer, int i, int j, double d, double e, int k, boolean b);

    double[][] loadByteArray(byte[] pageBuffer, int i, int j, int k, double d, double e, int l, boolean b);

    double[] loadWordVector(byte[] pageBuffer, int i, int j, double d, double e, int k, boolean b);

    double[][] loadWordArray(byte[] pageBuffer, int i, int j, int k, double d, double e, int l, boolean b);

    double round(double x);

    int table(double x, String t);

    double timeNow();

    double tempCvt(double x);
}
