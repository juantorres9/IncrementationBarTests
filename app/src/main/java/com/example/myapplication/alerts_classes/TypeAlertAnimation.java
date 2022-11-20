package com.example.myapplication.alerts_classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;

public class TypeAlertAnimation {
    private String mContent;

    private long mFirstTime;

    private int mTimeAlert;

    private int mAlertType;

    private boolean mIsShown = false;

    private RelativeLayout mLayout;

    TypeAlertAnimation(String pContent, int pTimeAlert, int pAlertType) {
        this.mContent = pContent;
        this.mTimeAlert = pTimeAlert;
        this.mAlertType = pAlertType;

      // updateLayout();
    }
/**
    public void updateLayout() {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }

        mLayout = new RelativeLayout(getContext());
        switch (mAlertType) {
            case CustomAlerts.ALERT_INFO:
                mLayout.addView(
                        inflater.inflate(R.layout.layout_alerts_warning, mLayout, false));
                break;

            case CustomAlerts.ALERT_WARNING:
                mLayout.addView(
                        inflater.inflate(R.layout.layout_alerts_warning, mLayout, false));
                break;

            case CustomAlerts.ALERT_DANGER:
                mLayout.addView(
                        inflater.inflate(R.layout.layout_alerts_danger, mLayout, false));
                break;

            default:
                mLayout.addView(
                        inflater.inflate(R.layout.layout_alerts_warning, mLayout, false));
                break;
        }

        ((TextView) mLayout.findViewById(R.id.text_alert)).setText(mContent);
    }

    boolean isShown() {
        return this.mIsShown;
    }

    void show() {
        if (!mIsShown) {
            mIsShown = true;
            mListAlerts.add(this);
            ViewUtils.addView(mInstance, this.getLayout());
            appearAnimation();
            this.mFirstTime = System.currentTimeMillis();
            if (mTimeAlert > 0) {
                final TypeAlertAnimation instance = this;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!instance.isExpired()) {
                            try {
                                Thread.sleep(mTimeThreadSleep);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        instance.hide();
                    }
                }).start();
            }
        }
    }

    void hide() {
        mLayout.post(new Runnable() {
            @Override
            public void run() {
                mLayout.animate().alpha(0).setDuration(mAnimationTime);
            }
        });
        synchronized (mListAlerts) {
            mListAlerts.remove(this);
        }
        ViewUtils.removeView(mInstance, getLayout());
    }

    String getContent() {
        return mContent;
    }

    public void setContent(String pContent) {
        this.mContent = pContent;
        ViewUtils.setText(((TextView) mLayout.findViewById(R.id.text_alert)), mContent);
    }

    void setFirstTime(long mFirstTime) {
        this.mFirstTime = mFirstTime;
    }

    void setTimeAlert(int pTimeAlert) {
        this.mTimeAlert = pTimeAlert;
    }

    private void appearAnimation() {
        Log.d(VIEW_LOG_TAG,"Start animation call +appearAnimation");
        mLayout.post(new Runnable() {
            @Override
            public void run() {
                if (mTimeAlert == mLongAlertTime) {
                    mLayout.setAlpha(0);
                    final TextView tv = mLayout.findViewById(R.id.text_alert);
                    mLayout.animate().alpha(1).setDuration(mAnimationTime);
                    tv.startAnimation(mAnimatorLeftToRight);
                }
            }
        });
    }

    private boolean isExpired() {
        return isShown() && (System.currentTimeMillis() - mFirstTime > mTimeAlert);
    }

    public View getLayout() {
        return mLayout;
    }

    public void setAlertType(int pAlertType) {
        this.mAlertType = pAlertType;
        this.updateLayout();
    }**/
}