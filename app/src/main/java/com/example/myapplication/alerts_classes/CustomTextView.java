package com.example.myapplication.alerts_classes;

import android.content.Context;
import android.graphics.Typeface;

import android.util.AttributeSet;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.myapplication.R;

/**
 * Custom TextView which simply instantiate B612 font.
 */
public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setTypeface(Typeface tf, int style) {
        if (style == Typeface.BOLD) {
            this.setTypeface(ResourcesCompat.getFont(getContext(), R.font.b612_bold)/*, -1*/);
        } else if (style == Typeface.ITALIC){
            this.setTypeface(ResourcesCompat.getFont(getContext(), R.font.b612_italic)/*, -1*/);
        } else if (style == Typeface.BOLD_ITALIC){
            this.setTypeface(ResourcesCompat.getFont(getContext(), R.font.b612_bolditalic)/*, -1*/);
        } else {
            this.setTypeface(ResourcesCompat.getFont(getContext(), R.font.b612)/*, -1*/);
        }
    }
}
