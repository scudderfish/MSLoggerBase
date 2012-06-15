package uk.org.smithfamily.mslogger.log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 *
 */
public class EmailManager
{
    /**
     * 
     * @param context
     * @param emailTo
     * @param emailCC
     * @param subject
     * @param emailText
     * @param filePaths
     */
    public static void email(Context context, String emailTo, String emailCC, String subject, String emailText, List<String> filePaths)
    {
        List<String> actualFiles = new ArrayList<String>();

        if (filePaths != null)
        {
            for (String name : filePaths)
            {
                if (name != null)
                {
                    File f = new File(name);

                    if (f.exists() && f.canRead())
                        actualFiles.add(name);
                }
            }
        }
        // need to "send multiple" to get more than one attachment
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { emailTo });
        if (emailCC != null)
        {
            emailIntent.putExtra(android.content.Intent.EXTRA_CC, new String[] { emailCC });
        }
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);

        if (actualFiles.size() > 0)
        {
            emailIntent.setType("application/zip");
            File zipFile = new File(actualFiles.get(0) + ".zip");
            Compress c = new Compress(actualFiles, zipFile.getAbsolutePath());

            c.zip();

            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(zipFile));
        }
        else
        {
            emailIntent.setType("plain/text");
        }
        context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}
