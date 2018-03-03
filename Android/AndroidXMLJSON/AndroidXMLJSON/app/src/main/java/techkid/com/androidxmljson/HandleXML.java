
package techkid.com.androidxmljson;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class HandleXML {
    private static final String TAG = HandleXML.class.getSimpleName();
    private String country = "empty";
    private String temperature = "empty";
    private String humidity = "empty";
    private String pressure = "empty";
    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;

    public HandleXML(String url) {
        this.urlString = url;
    }

    public String getCountry() {
        return country;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    // Parse XML and write value data to HandleXML Object
    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text = null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (!name.equals("country"))
                            text = myParser.getText();

                        if (name.equals("humidity")) {
                            humidity = myParser.getAttributeValue(null, "value");
                        } else if (name.equals("pressure")) {
                            pressure = myParser.getAttributeValue(null, "value");
                        } else if (name.equals("temperature")) {
                            temperature = myParser.getAttributeValue(null, "value");
                        } else {
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (name.equals("country")) {
                            country = text;
                        } else {

                        }
                        break;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get XML data from Open Weather API
    public void fetchXML() {
        try {
            InputStream stream = NetworkConnection.downloadUrlInputStream(urlString);
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                    , false);
            myparser.setInput(stream, null);
            parseXMLAndStoreIt(myparser);
            stream.close();
        } catch (MalformedURLException e) {
            Log.i(TAG, "" + e.getMessage());
        } catch (IOException e) {
            Log.i(TAG, "" + e.getMessage());
        } catch (XmlPullParserException e) {
            Log.i(TAG, "" + e.getMessage());
        }

    }
}
