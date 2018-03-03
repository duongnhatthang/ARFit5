package com.techkids.thangduong.alarmmanagerdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAlarm=(Button)findViewById(R.id.bt_alarm);
        btAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
    }

    private void setAlarm() {
        AlarmManager alarmManager=(AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(getApplicationContext(),AlarmManagerBroadcastReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getBaseContext(),1,intent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+10000,pendingIntent);
    }
}
