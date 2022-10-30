package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ProgressCusto extends ProgressBar {
OnLeftRightSensorListener sensorListener;


    public ProgressCusto(Context context, AttributeSet attrs) {
        super(context,attrs);

    }

    public ProgressCusto(Context context,int Maxsize) {
        super(context);
        setMax(Maxsize);
    }
    public ProgressCusto(Context context,OnLeftRightSensorListener listener) {
        super(context);
        sensorListener=listener;

    }


    public void setSensorListener(OnLeftRightSensorListener listener){
        sensorListener=listener;
        
    }



    @Override
    public synchronized int getProgress(){
        //int progress_value=this.getProgress();
        return super.getProgress();
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
    }

    @Override
    public boolean performClick() {
        // Calls the super implementation, which generates an AccessibilityEvent
        // and calls the onClick() listener on the view, if any
        super.performClick();

        // Handle the action for the custom click here

        return true;
    }


    //TRIGGER CALLBACK ACTIONS
    public void launchLeftSensorAction(){
        sensorListener.onLeftSensorDetected();
    }
    public void launchRightSensorAction(){
        sensorListener.onRightSensorDetected();
    }



//listener


}
