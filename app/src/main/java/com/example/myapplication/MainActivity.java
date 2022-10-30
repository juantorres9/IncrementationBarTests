package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int progress_value=0;
    ProgressBar progress_bar;
    TextView progress_tv;
    Button progres_button_right;
    Button progres_button_left;
    Button button_go;
    public static int MAX_PROGRESS_BAR=200;
    public static String TAG_MAIN="MAIN_ACTIVITY JMJCH";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress_bar=findViewById(R.id.progress_bar);
        progress_tv=findViewById(R.id.progress_tv);
        progres_button_left=findViewById(R.id.progress_button_left);
        progres_button_left.setText("BUTTON LEFT");
        progres_button_right=findViewById(R.id.progress_button_right);
        progres_button_right.setText("RIGHT");
        button_go=findViewById(R.id.progress_button_go);
        progress_bar.setMax(MAX_PROGRESS_BAR);




        SensorClass sensor =new SensorClass(new OnLeftRightSensorListener() {
            @Override
            public void onLeftSensorDetected() {
                if (progress_value<=progress_bar.getMax()){
                    progress_value+=1;
                    progress_bar.setProgress(progress_value);
                    progress_bar.invalidate();
                }
                try {
                    Thread.sleep(40);
                }catch (Exception e){
                    System.out.println(e.toString());
                }

            }

            @Override
            public void onRightSensorDetected() {
                progress_value=0;
                progress_bar.setProgress(progress_value);
                progress_bar.invalidate();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        progres_button_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG_MAIN,"pressed left right button");
                boolean result=false;
                if (progress_value<=100){
                        progress_value+=5;
                        progress_bar.setProgress(progress_value);
                        result=true;

                }
                Log.d(TAG_MAIN,"pressed left right button progress value="+progress_value+ "result="+result);
                return result;
            }
        });

        progres_button_right.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean result=false;
                Log.d(TAG_MAIN,"pressed long right button");
                if (progress_value>0){
                    progress_value=0;
                    progress_bar.setProgress(progress_value);

                }
                Log.d(TAG_MAIN,"pressed left right button progress value="+progress_value+ "result="+result);

                return result;
            }
        });

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),Main3ButtonPressed.class);
                startActivity(i);
            }
        });

    }
}
