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


    Button progres_button_left3;
    public static int MAX_PROGRESS_BAR3=200;
    public static String TAG_MAIN3="MAIN_ACTIVITY3 VJMJCH";
//    public Handler handler;
 //   boolean semaphore=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_button_pressed);
        //PROGRESSBAR CUSTO
        progress_bar3 = findViewById(R.id.progress_bar3);
        progres_button_left3 = findViewById(R.id.progress_button_left3);

        //CUSTO_PROGRESS ATTRIBUTES
        progress_bar3.setMax(MAX_PROGRESS_BAR3);
        progress_bar3.setProgress(progress_value3);
        progress_bar3.setBackground(getDrawable(R.drawable.progressbar_horizontal_yellow));


        //CALL BACK UI RECEIVER ACTION TO PROCESS
        progress_bar3.setSensorListener(new OnLeftRightSensorListener() {
            @Override
            public void onLeftSensorDetected() {
                Log.d(TAG_MAIN3," onCreate() Detector of sensor left from callback !!!!!! ");
                progress_bar3.setProgress(getParentProgress());
            }

            @Override
            public void onRightSensorDetected() {
                Log.d(TAG_MAIN3,"onCreate() Detector of sensor right from callback !!!!!! ");
                progress_bar3.setProgress(getParentProgress());
            }
        });

        //Instantiate handler
        //handler=new Handler(Looper.getMainLooper());

    }

    @Override
    protected void onResume() {
        super.onResume();
        //CALL BACK UI RECEIVER ACTION TO PROCESS
        progress_bar3.setSensorListener(new OnLeftRightSensorListener() {
            @Override
            public void onLeftSensorDetected() {
                Log.d(TAG_MAIN3,"onResume() Detector of sensor left from callback !!!!!! ");
                progress_bar3.setProgress(getParentProgress());
            }

            @Override
            public void onRightSensorDetected() {
                Log.d(TAG_MAIN3,"onResume() Detector of sensor right from callback !!!!!! ");
                progress_bar3.setProgress(getParentProgress());
            }
        });

        /**
        progress_bar3.setOnTouchListener(  new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG_MAIN3,"ACTION DETECTED IS== "+event.getAction());
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    Log.d(TAG_MAIN3,"pressed left right button");
                    while(progress_value3<=100){
                        progress_value3+=5;
                        try{
                            Thread.sleep(500);
                        }catch (Exception e){

                        }

                        Log.d(TAG_MAIN3,"pressed left right button progress value="+progress_value3);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progress_bar3.setProgress(progress_value3);
                            }
                        });
                        progress_bar3.setProgress(progress_value3);
                    }
                }
                if(event.getAction()== MotionEvent.ACTION_UP){
                    Log.d(TAG_MAIN3,"pressed long right button");
                    if (progress_value3>0 ){
                        progress_value3=0;
                        progress_bar3.setProgress(progress_value3);
                    }
                    Log.d(TAG_MAIN3,"pressed left right button progress value="+progress_value3);
                }
                return true;
            }


        });

        **/
/**

        while(semaphore){
            try{
                Thread.sleep(1000);
            }catch (Exception e){}

            Log.d(TAG_MAIN3,"SET WITH NEW VALUE  progress value="+progress_value3);
            progress_bar3.setProgress(progress_value3);
       //     progress_bar3.setProgress(0);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progress_bar3.requestLayout();
                }
            });



        }**/
        //-------------------------------------------------------------------------------------------------button
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
                            Log.d(TAG_MAIN3,"pressed left right button progress value="+temp_value);
                            progress_bar3.launchLeftSensorAction();
                        }

                        Log.d(TAG_MAIN3,"FINISH THREAD");
                    }
                }).start();


            }
        });


    }


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
