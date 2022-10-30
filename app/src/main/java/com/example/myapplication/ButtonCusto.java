package com.example.myapplication;

import android.content.Context;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.widget.AppCompatButton;

public class ButtonCusto extends AppCompatButton {


    public ButtonCusto(Context context, int Maxsize) {
        super(context);

    }
    public ButtonCusto(Context context) {
        super(context);

    }



    @Override
    public boolean performClick() {
        // Calls the super implementation, which generates an AccessibilityEvent
        // and calls the onClick() listener on the view, if any
        super.performClick();

        // Handle the action for the custom click here

        return true;
    }



//listener


}
