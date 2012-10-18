package uk.org.smithfamily.mslogger.dashboards;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import uk.org.smithfamily.mslogger.widgets.Indicator;

public class DashboardParser
{
    public List<Indicator> parse() throws XmlPullParserException, IOException
    {
        List<Indicator> results = new ArrayList<Indicator>();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(new StringReader("<foo>Hello World!</foo>"));
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            switch (eventType)
            {
            case XmlPullParser.START_DOCUMENT:
                break;

            case XmlPullParser.END_DOCUMENT:
                break;
            case XmlPullParser.START_TAG:
                break;
            case XmlPullParser.END_TAG:
                break;
            case XmlPullParser.TEXT:
                break;
            }
            eventType = xpp.next();
        }

        return results;

    }
}
