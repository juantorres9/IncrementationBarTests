package com.example.myapplication;



import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {

    public static String TAG_4="MainActivity4 hi";
    ProgressCusto progressBarLeft;
    public static int MAX_PROGRESS_BAR4=200;
    public int progress_value4=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        progressBarLeft=findViewById(R.id.progress_bar4);
        progressBarLeft.setMax(MAX_PROGRESS_BAR4);




    }

    @Override
    protected  void onResume(){
        super.onResume();
        //IMPLEMENTATION CUSTO DU  ACCELEROMETER LISTENER
        progressBarLeft.setSensorListener(new OnLeftRightSensorListener() {
            @Override
            public void onLeftSensorDetected() {
                Log.d(TAG_4,"LEFT SENSOR CALLBACK is detected now");
            }

            @Override
            public void onRightSensorDetected() {
                Log.d(TAG_4,"RIGHT SENSOR CALLBACK is detected now");

            }

            @Override
            public void onNothingDetected() {
                Log.d(TAG_4,"NOTHING SENSOR CALLBACK is detected now");

            }
        });


        //IMPLEMENTATION NATIVE DU LISTENER ONTOUCH
        progressBarLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG_4,"ACTION_DOWN is detected now");
                        progressBarLeft.launchLeftSensorAction();
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.d(TAG_4,"ACTION_UP is detected now");
                        progressBarLeft.launchRightSensorAction();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        float current_x=event.getX();
                        float current_y=event.getY();
                        progressBarLeft.launchNothinSensorAction();
                        Log.d(TAG_4,"ACTION_MOVE is detected now with coordinates X="+current_x+" coordinates Y="+current_y);

                        if (current_x>0 && current_y>0 && current_x<=progressBarLeft.getWidth() && current_y<=progressBarLeft.getHeight()){
                            Log.d(TAG_4,"ACTION_MOVE VOUS ETES DEDANS  dans l'image OK width max x="+progressBarLeft.getWidth()+" et height Y="+progressBarLeft.getHeight());
                        }else{
                            Log.d(TAG_4,"ACTION_MOVE VOUS ETES DEHORS l'IMAGE desole ");

                        }
                        break;
                        }
                return true ;
                }

            });
    }

}
