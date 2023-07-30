package uk.org.smithfamily.mslogger.log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 *  Helper class that is used to send an email 
 */
public class EmailManager
{
    /**
     * Function used to send an email. Can take multiple files in parameter, they will be compressed (zipped) and added as an
     * attachment.
     * 
     * @param context   Current context where the email will be send
     * @param emailTo   Email address to send the email to
     * @param emailCC   Email address to send carbon copy
     * @param subject   Subject of the email
     * @param emailText Text of the email
     * @param filePaths Files to send
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
                    {
                        actualFiles.add(name);
                    }
                }
            }
        }
        
        // Need to "send multiple" to get more than one attachment
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
