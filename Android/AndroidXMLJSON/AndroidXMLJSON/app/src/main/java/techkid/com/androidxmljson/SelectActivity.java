package techkid.com.androidxmljson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by tulh2 on holy day.
 */
public class SelectActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);
        this.findViewById(R.id.btn_XML).setOnClickListener(this);
        this.findViewById(R.id.btn_JSON).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent nextIntent;
        switch (v.getId()) {
            case R.id.btn_JSON:
                nextIntent = new Intent(this, JSONActivity.class);
                startActivity(nextIntent);
                break;
            case R.id.btn_XML:
                nextIntent = new Intent(this, XMLActiviy.class);
                startActivity(nextIntent);
                break;
            default:
                break;
        }
    }
}
