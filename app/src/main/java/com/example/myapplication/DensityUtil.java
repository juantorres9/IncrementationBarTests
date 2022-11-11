package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class DensityUtil {

    public static String PADDING_HORIZONTAL_KEY="padding_horizontal";
    public static String PADDING_VERTICAL_KEY="padding_vertical";
    public static String PROGRESS_DRAWABLE_KEY="progress_drawable";
    public static String BOOLEAN_DEMO="boolean_demo";
    public static String STRING_LAYOUT_ID="layout_id";
    /**
     * dip to px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px to dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale =  context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
/**
    public static void setFormatText(Activity activity, TextView textviewTarget, String text ){
        textviewTarget.setText(text);
        Typeface typeface = activity.getResources().getFont( R.font.b612_bold);
        textviewTarget.setTypeface(typeface);
      //  textviewTarget.setMaxWidth(dip2px(activity,50));
    }

    public static void setFormatText(Context context, TextView textviewTarget, String text ){
        textviewTarget.setText(text);
        Typeface typeface = context.getResources().getFont( R.font.b612_bold);
        textviewTarget.setTypeface(typeface);
    }
    public static void setFormatTextOnly(TextView textviewTarget, String text ){
        textviewTarget.setText(text);

    }


    public static void setFormatImage(Activity activity, ImageView imageTarget, int imageResId ){
        Drawable d= activity.getDrawable(imageResId);
        imageTarget.setBackground(d);

    }**/

}