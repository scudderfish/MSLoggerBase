package uk.org.smithfamily.mslogger.log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public enum EmailManager
{
	INSTANCE;

	public void sendLogs()
	{

		
		
	}

	public static void email(Context context, String emailTo, String emailCC, String subject, String emailText,
			List<String> filePaths)
	{
		// need to "send multiple" to get more than one attachment
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { emailTo });
		emailIntent.putExtra(android.content.Intent.EXTRA_CC, new String[] { emailCC });
		// has to be an ArrayList
		ArrayList<Uri> uris = new ArrayList<Uri>();
		// convert from paths to Android friendly Parcelable Uri's
		for (String file : filePaths)
		{
			File fileIn = new File(file);
			Uri u = Uri.fromFile(fileIn);
			uris.add(u);
		}
		emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
		context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}
}
