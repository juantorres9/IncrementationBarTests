package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.alerts_classes.CustomAlerts;

public class MainActivity6 extends AppCompatActivity {
    public CustomAlerts mCustomAlerts;
    public static final int TIME_LONG_ALERT = 6000;
    public ImageView animation_head;
    public AnimatedVectorDrawableCompat animatedViewDoctor;
    private Handler hdlr = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        //ANIMATION
        animation_head = findViewById(R.id.image_view_waitting_start_anim);
//BATTERY LEVEL
        int pBatteryLevelPct = 40;
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
        Log.d("ACTIVITY6", "startting THREAD  POST!");
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

    @Override
    protected void onResume() {
        super.onResume();
        //Image view animation

        animatedViewDoctor = AnimatedVectorDrawableCompat.create(this, R.drawable.ic_head_tilt_anim_2);
        animation_head.setImageDrawable(animatedViewDoctor);
        //animatedViewDoctor.start();
        animatedViewDoctor.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                hdlr.post(new Runnable() {
                    public void run() {
                        animatedViewDoctor.start();
                    }
                });
            }

        });
        animatedViewDoctor.start();



    }
}