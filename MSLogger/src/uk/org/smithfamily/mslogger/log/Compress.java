package uk.org.smithfamily.mslogger.log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import android.util.Log;

/**
 * Utility class that compress a list of datalog into a zip format file
 */
public class Compress
{
    private static final int BUFFER = 2048;

    private List<String>     _files;
    private String           _zipFile;

    /**
     * Constructor
     * 
     * @param files     The array of file names to compress
     * @param zipFile   The resulting zip file name
     */
    public Compress(List<String> files, String zipFile)
    {
        _files = files;
        _zipFile = zipFile;
    }

    /**
     * Main method that compress the datalogs into a zip file
     * It will create the zip file at the specified destination
     * Datalogs file will stay intact
     */
    public void zip()
    {
        try
        {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(_zipFile);

            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

            byte data[] = new byte[BUFFER];

            for (String fileName : _files)
            {
                Log.v("Compress", "Adding: " + fileName);
                FileInputStream fi = new FileInputStream(fileName);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(fileName.substring(fileName.lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1)
                {
                    out.write(data, 0, count);
                }
                origin.close();
            }

            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
