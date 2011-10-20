package uk.org.smithfamily.mslogger.log;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class EmailManager
{
	public static void email(Context context, String emailTo, String emailCC, String subject, String emailText,
			List<String> filePaths)
	{
		// need to "send multiple" to get more than one attachment
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("application/zip");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { emailTo });
		emailIntent.putExtra(android.content.Intent.EXTRA_CC, new String[] { emailCC });
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
        
		File zipFile = new File(filePaths.get(0)+".zip");
		Compress c = new Compress(filePaths,zipFile.getAbsolutePath());
		
		c.zip();
	
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(zipFile));
		context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}
}
