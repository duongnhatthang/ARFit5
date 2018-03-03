
package techkid.com.androidxmljson;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HandleJSON {
    private static final String TAG = HandleJSON.class.getSimpleName();
    private String country = "empty";
    private String temperature = "empty";
    private String humidity = "empty";
    private String pressure = "empty";
    private String urlString = null;

    public HandleJSON(String url) {
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

    // Parse JSON 
    private void parseJSONAndStoreIt(String jsonString) {
        if (jsonString != null) {
            try {
                // Format example:
                // http://api.openweathermap.org/data/2.5/weather?q=han&appid=2de143494c0b295cca9337e1e96b00e0&mode=json
                JSONObject jsonWeatherObject = new JSONObject(jsonString);
                if (jsonWeatherObject != null) {
                    JSONObject jsonSysObject = jsonWeatherObject.getJSONObject("sys");
                    if (jsonSysObject != null) {
                        country = jsonSysObject.getString("country");
                    }
                    JSONObject jsonMainObject = jsonWeatherObject.getJSONObject("main");
                    if (jsonMainObject != null) {
                        temperature = jsonMainObject.getString("temp");
                        humidity = jsonMainObject.getString("humidity");
                        pressure = jsonMainObject.getString("pressure");
                    }
                }
            } catch (JSONException e) {
                Log.i(TAG, "" + e.getMessage());
            }
        }
    }

    // Get XML data from Open Weather API
    public void fetchXML() {
        try {
            String jsonString = NetworkConnection.downloadUrl(urlString);
            parseJSONAndStoreIt(jsonString);
        } catch (IOException e) {
            Log.i(TAG, "" + e.getMessage());
        }
    }
}
