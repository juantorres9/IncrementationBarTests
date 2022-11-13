package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Main5Activity extends AppCompatActivity {
public static String TAG5=Main5Activity.class.toString();
    ViewGroup mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        int width_dp=150;
        int height_dp=80;
        int width_pixels=DensityUtil.dip2px(this,width_dp);
        int height_pixels=DensityUtil.dip2px(this,height_dp);
        Log.d(TAG5,"hi the width DP is="+width_dp+ " but the width in PX is ="+width_pixels);
        Log.d(TAG5,"hi the height DP is="+height_dp+ " but the height in PX is ="+height_pixels);


        int width_px=120;
        int height_px=400;
        int width_dips=DensityUtil.px2dip(this,width_px);
        int height_dips=DensityUtil.px2dip(this,height_px);
        Log.d(TAG5,"hi the width_PX is="+width_px+ " but the width in DIPS is ="+width_dips);
        Log.d(TAG5,"hi the height_PX is="+height_px+ " but the height in DIPS is ="+height_dips);
//OK CODE
        LayoutInflater inflater = (LayoutInflater)      this.getSystemService(LAYOUT_INFLATER_SERVICE);
      //  RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(150  ,200);  //PIXELS PARAMETERS
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(width_pixels  ,height_pixels);


        imageParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        imageParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        View childLayout = inflater.inflate(R.layout.composed_button0,(ViewGroup)findViewById(R.id.layout_button0));
        childLayout.setBackgroundColor(Color.parseColor("#004455"));
        childLayout.setLayoutParams(imageParams);
        RelativeLayout rl=findViewById(R.id.layout_5);
        rl.setBackgroundColor(Color.parseColor("#004455"));

        rl.addView(childLayout);
    }
}
