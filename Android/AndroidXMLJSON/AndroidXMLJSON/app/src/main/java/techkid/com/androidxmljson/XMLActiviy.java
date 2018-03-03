package techkid.com.androidxmljson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by tulh2 on holy day.
 */
public class XMLActiviy extends Activity {
    private final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=2de143494c0b295cca9337e1e96b00e0&mode=xml";
    private EditText mEdtLocation, mEdtCountry, mEdtTemperature, mEdtHumidity, mEdtPressure;
    private Button mBtnLoadWeather;
    private HandleXML mObjectHandleXML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_activity);
        mEdtLocation = (EditText) findViewById(R.id.edtURL);
        mEdtCountry = (EditText) findViewById(R.id.edtCountry);
        mEdtTemperature = (EditText) findViewById(R.id.edtTemperature);
        mEdtHumidity = (EditText) findViewById(R.id.edtHumidity);
        mEdtPressure = (EditText) findViewById(R.id.edtPressure);
        mBtnLoadWeather = (Button) findViewById(R.id.btnLoadContent);
        mEdtLocation.setSelected(true);
        mBtnLoadWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkConnection.isNetworkAvailable(getApplicationContext())) {
                    String local = mEdtLocation.getText().toString();
                    String contentURL = String.format(OPEN_WEATHER_URL, local);
                    new DownloadWeatherContent().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                            contentURL);
                } else {
                    Toast.makeText(getApplicationContext(), "Network not available!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Uses AsyncTask to create a task away from the main UI thread. This task
    // takes a
    // URL string and uses it to create an HttpUrlConnection.
    private class DownloadWeatherContent extends AsyncTask<String, Void, HandleXML> {
        @Override
        protected HandleXML doInBackground(String... params) {
            mObjectHandleXML = new HandleXML(params[0]);
            mObjectHandleXML.fetchXML();
            return mObjectHandleXML;
        }

        @Override
        protected void onPostExecute(HandleXML handleXML) {
            super.onPostExecute(handleXML);
            mEdtCountry.setText(mObjectHandleXML.getCountry());
            mEdtTemperature.setText(mObjectHandleXML.getTemperature());
            mEdtHumidity.setText(mObjectHandleXML.getHumidity());
            mEdtPressure.setText(mObjectHandleXML.getPressure());
        }
    }
}
