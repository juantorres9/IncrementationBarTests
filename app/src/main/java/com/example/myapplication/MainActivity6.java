package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.myapplication.alerts_classes.CustomAlerts;

public class MainActivity6 extends AppCompatActivity {
    public CustomAlerts mCustomAlerts;
    public static final int TIME_LONG_ALERT = 6000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
//BATTERY LEVEL
        int pBatteryLevelPct=40;
        //Linear layout instantiation
        mCustomAlerts = findViewById(R.id.llayout_alert);
        mCustomAlerts.setLongAlertTime(TIME_LONG_ALERT);

        String alertConnected = getResources().getString(R.string.connect_battery);
        String alertMaybeConnected = getResources().getString(R.string.check_battery);
        String alert = getResources().getString(R.string.battery_level);
        //mCustomAlerts.addInfiniteAlert(alertMaybeConnected, CustomAlerts.ALERT_DANGER);
        //mCustomAlerts.addInfiniteAlert(alertConnected, CustomAlerts.ALERT_DANGER);

       // mCustomAlerts.addInfiniteAlert(alert, CustomAlerts.ALERT_DANGER);

        mCustomAlerts.addInfiniteAlert(alert, CustomAlerts.ALERT_WARNING);
        Log.d("ACTIVITY6","startting THREAD  POST!");
        mCustomAlerts.updateTextAlert(alert, alert + pBatteryLevelPct + "%");
        //mCustomAlerts.updateTextAlert("Alert BATTERY", "Allert BATTERY" + 40 + "%");

       /** Handler alt_thread = new Handler();
        alt_thread.post(new Runnable() {


            public void run() {
              try {
                  Thread.sleep(10000);

              }catch (Exception e){

              }
              Log.d("ACTIVITY6","hey launch view update after 4000ms NOW!");
                mCustomAlerts.updateTextAlert("Alert BATTERY", "Allert BATTERY" + 40 + "%");
            }
        });**/


    }
}
