package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {


    int progress_value=0;
    ProgressBar progress_bar;
    TextView progress_tv;
    Button progress_button_right;
    Button progres_button_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress_bar=findViewById(R.id.progress_bar);
        progress_tv=findViewById(R.id.progress_tv);
        progres_button_left=findViewById(R.id.progress_button_left);
        progres_button_left=findViewById(R.id.progress_button_right);
        progress_bar.setMax(100);






    }

    @Override
    protected void onResume() {
        super.onResume();

        progres_button_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean result=false;
                if (progress_value<=100){
                        progress_value+=1;
                        progress_bar.setProgress(progress_value);
                        result=true;
                }
                return result;
            }
        });

        progres_button_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (progress_value>0){
                    progress_value=0;
                    progress_bar.setProgress(progress_value);

                }

                return false;
            }
        });

    }
}
