package com.example.myapplication.alerts_classes;

import android.content.Context;
import android.content.res.Resources;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class ViewUtils {
    private static Resources sRes;

    /**
     * Initialize class, in order to get resources files.
     * @param context context of the app.
     */
    public static void init( Context context) {
        if(context == null) {
            throw new IllegalArgumentException("init() : context is null");
        }
        sRes = context.getResources();
    }

    public static void setVisibility(final View pView, final int pVisibility) {
        if (pView == null) {
            return;
        }
        pView.post(new Runnable() {
            @Override
            public void run() {
                pView.setVisibility(pVisibility);
            }
        });
    }

    public static void setText(final TextView pTextView, final String pText) {
        if (pTextView == null) {
            return;
        }
        pTextView.post(new Runnable() {
            @Override
            public void run() {
                pTextView.setText(pText);
            }
        });
    }

    public static void setText(final TextView pTextView, final int pTextId) {
        if (pTextView == null) {
            return;
        }
        pTextView.post(new Runnable() {
            @Override
            public void run() {
                pTextView.setText(sRes.getString(pTextId));
            }
        });
    }

    public static void setImageDrawable(final ImageView pImageView, final int pImageId) {
        if (pImageView == null) {
            return;
        }
        pImageView.post(new Runnable() {
            @Override
            public void run() {
                pImageView.setImageDrawable(sRes.getDrawable(pImageId));
            }
        });
    }

    public static void addView(final LinearLayout pLayout, final View pViewToAdd) {
        if (pLayout == null || pViewToAdd == null) {
            return;
        }
        pLayout.post(new Runnable() {
            @Override
            public void run() {
                pLayout.addView(pViewToAdd);
            }
        });
    }

    public static void removeView(final LinearLayout pLayout, final View pViewToRemove) {
        if (pLayout == null || pViewToRemove == null) {
            return;
        }
        pLayout.post(new Runnable() {
            @Override
            public void run() {
                pLayout.removeView(pViewToRemove);
            }
        });
    }

    public static void setProgress(final SeekBar pView, final int pProgress) {
        if (pView == null || pProgress < 0) {
            return;
        }
        pView.post(new Runnable() {
            @Override
            public void run() {
                pView.setProgress(pProgress);
            }
        });
    }

    /**
     * Animate a view from alpha 0 to alpha 1.
     * @param pView view to animate.
     * @param pTime duration of the animation.
     */
    public static void fadeInTransition(final ViewGroup pView, final int pTime) {
        if (pView == null || pTime < 0) {
            return;
        }
        pView.post(new Runnable() {
            @Override
            public void run() {
                pView.setAlpha(0);
                pView.animate().alpha(1).setDuration(pTime);
            }
        });
    }

    /**
     * Animate a view from alpha 1 to alpha 0.
     * @param pView view to animate.
     * @param pTime duration of the animation.
     */
    public static void fadeOutTransition(final ViewGroup pView, final int pTime) {
        if (pView == null || pTime < 0) {
            return;
        }
        pView.post(new Runnable() {
            @Override
            public void run() {
                pView.setAlpha(1);
                pView.animate().alpha(0).setDuration(pTime);
            }
        });
    }
}
