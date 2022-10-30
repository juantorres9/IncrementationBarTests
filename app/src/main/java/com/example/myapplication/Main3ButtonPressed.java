package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main3ButtonPressed extends AppCompatActivity {

    Intent i;
    public int progress_value3=0;
    ProgressCusto progress_bar3;


    Button progres_button_left3,progress_button_right3;
    public static int MAX_PROGRESS_BAR3=200;
    public static String TAG_MAIN3="MAIN_ACTIVITY3 VJMJCH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_button_pressed);
        //PROGRESSBAR CUSTO
        progress_bar3 = findViewById(R.id.progress_bar3);
        progres_button_left3 = findViewById(R.id.progress_button_left3);
        progress_button_right3=findViewById(R.id.progress_button_right3);

        //CUSTO_PROGRESS ATTRIBUTES
        progress_bar3.setMax(MAX_PROGRESS_BAR3);
        progress_bar3.setProgress(getParentProgress());
        progress_bar3.setBackground(getDrawable(R.drawable.progressbar_horizontal_yellow));

    }

    @Override
    protected void onResume() {
        super.onResume();

        //CALL BACK UI THREAD RECEIVER ACTION TO PROCESS LEFT/RIGHT SENSOR DETECTION
        progress_bar3.setSensorListener(new OnLeftRightSensorListener() {
            @Override
            public void onLeftSensorDetected() {
                Log.d(TAG_MAIN3,"onResume() Detector of sensor left from callback !!!!!! ");
                progress_bar3.setProgress(getParentProgress());
            }

            @Override
            public void onRightSensorDetected() {
                Log.d(TAG_MAIN3,"onResume() Detector of sensor RIGHT from callback !!!!!! ");
                if(getParentProgress()>1){
                    Log.d(TAG_MAIN3, "PROGRESS BAR has been detected >1 so we need to RESET IT TO ZERO .");
                    setParentProgress(0);
                    progress_bar3.setProgress(getParentProgress());
                }else{
                    Log.d(TAG_MAIN3, "PROGRESS BAR has been detected BASICALLY ZERO  so we DO NOTHING.");
                }

            }
        });


        //BUTTON CALLBACK LEFT TRIGGER DELEGATION TO  PROGESS INNER CLASS
        progres_button_left3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG_MAIN3, "pressed left button Ok execute new thread!");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (getParentProgress() <= progress_bar3.getMax()) {
                            Thread tt=Thread.currentThread();
                            try {
                                tt.sleep(500);
                            }catch (Exception e){}

                            int temp_value=incrementParentProgress(5);
                            Log.d(TAG_MAIN3,"pressed LEFT button progress value="+temp_value);
                            progress_bar3.launchLeftSensorAction();
                        }
                        Log.d(TAG_MAIN3,"FINISH THREAD");
                    }
                }).start();


            }
        });
        //BUTTON CALLBACK RIGHT TRIGGER DELEGATION TO  PROGESS INNER CLASS
        progress_button_right3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG_MAIN3, "pressed RIGHT button Ok execute new thread!");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG_MAIN3, "pressed RIGHT TRIGGERING RIGHT ACTIONS THROUGH INNER PROGRESS CLASS ");
                        progress_bar3.launchRightSensorAction();
                    }
                }).start();
            }
        });


    }

//MEMBER SYNCHRONIZED METHODS FOR PROGRESS CLASS VARIABLES
    public synchronized int getParentProgress(){
        return progress_value3;
    }

    public synchronized void setParentProgress(int current_progress){
        this.progress_value3=current_progress;
    }

    public synchronized int incrementParentProgress(int incrementValue){
        if(getParentProgress()<=progress_bar3.getMax()){
            Log.d(TAG_MAIN3,"SYNCRONIZED IS LOWER VALUE="+getParentProgress());
            progress_value3+=incrementValue;
        }else{
            Log.d(TAG_MAIN3,"SYNCRONIZED IS NOT LOWER VALUE DO NOTHING ="+getParentProgress());
        }

        return progress_value3;
    }

}
