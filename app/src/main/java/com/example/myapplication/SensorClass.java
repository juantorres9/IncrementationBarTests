package com.example.myapplication;

import android.content.Context;
import android.widget.ProgressBar;

public class SensorClass {
    OnLeftRightSensorListener sensorlistener;
    boolean isMovLeft=false;
    boolean isMovRight=false;

    public SensorClass(OnLeftRightSensorListener listener) {
        this.sensorlistener = listener;
    }

    public boolean sensorDetectLeft() {
        isMovRight=false;
        System.out.println("detected left");
        sensorlistener.onLeftSensorDetected();
        this.isMovLeft=true;
        return isMovLeft ;
    }

    public boolean sensorDetectRight() {
        isMovLeft=false;
        System.out.println("detected right");
        sensorlistener.onRightSensorDetected();
        this.isMovRight=true;
        return isMovRight ;
    }

    public void nothingHappens(){
        boolean isMovLeft=false;
        boolean isMovRight=false;
    }





}
