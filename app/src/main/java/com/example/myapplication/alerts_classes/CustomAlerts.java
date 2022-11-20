package com.example.myapplication.alerts_classes;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.myapplication.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Manage alerts displayed by defining a layout.
 * Possibility to add short, long or infinite alert.
 */
public class CustomAlerts extends LinearLayout {
    public static String TAG = CustomAlerts.class.toString();
    /**
     * Informative alert.
     */
    public static final int ALERT_INFO = 0;

    /**
     * Basic alert, where an action is required by surgeon.
     */
    public static final int ALERT_WARNING = 1;

    /**
     * Critical alert.
     */
    public static final int ALERT_DANGER = 2;

    /**
     * Default time for thread checking.
     */
    private static final int TIME_DEFAULT_THREAD_SLEEP = 100;

    /**
     * Default time for text effects.
     */
    private static final int TIME_DEFAULT_ANIMATION = 200;

    /**
     * Default time during which alert is shown.
     */
    private static final int TIME_DEFAULT_VISIBILITY = 250;

    /**
     * Left to right animation.
     */
    private final Animation mAnimatorLeftToRight;

    /**
     * Animation time for text effects.
     */
    private int mAnimationTime = TIME_DEFAULT_ANIMATION;

    /**
     * Time during which alert is shown.
     */
    private int mTimeVisible = TIME_DEFAULT_VISIBILITY;

    /**
     * Time during which a long alert is shown.
     */
    private int mLongAlertTime = TIME_DEFAULT_VISIBILITY;

    private int mTimeThreadSleep = TIME_DEFAULT_THREAD_SLEEP;

    /**
     * Contains all actual alerts.
     */
    private List<TypeAlertAnimation> mListAlerts;

    private boolean mAlertsDisabled = false;

    private CustomAlerts mInstance;

    public CustomAlerts(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInstance = this;
        mListAlerts = new ArrayList<>();
        mAnimatorLeftToRight = AnimationUtils.loadAnimation(getContext(), R.anim.alert);
    }

    /**
     * Show a new alert which is hidden only by calling }.
     * @param pContent text to show.
     */
    public void addInfiniteAlert(String pContent, int pAlertType) {
        this.addAlert(pContent, 0, pAlertType);
    }

    /**
     * Hide an alert containing given content.
     * @param pContent text alert to hide.
     */
    public void hideInfiniteAlert(String pContent) {
        if (mAlertsDisabled) {
            return;
        }
        synchronized (mListAlerts) {
            int indexAlert = findAlertByContent(pContent);
            if (indexAlert != -1) {
                // if alert already exists, just update it
                TypeAlertAnimation alert = mListAlerts.get(indexAlert);
                alert.hide();
            }
        }
    }

    /**
     * Add a new alert that will stay a long time.
     * @param pContent text to show.
     */
    public void addLongAlert(String pContent) {
        this.addAlert(pContent, mLongAlertTime, ALERT_WARNING);
    }

    /**
     * A new short alert.
     * @param pContent text to show.
     */
    public void addTempAlert(String pContent) {
        this.addAlert(pContent, mTimeVisible, ALERT_WARNING);
    }

    /**
     * Add a new alert OR update if content is already displayed.
     * @param pContent     content of the alarm.
     * @param pTimeVisible alarm duration.
     * @param pAlertType   type of the alert (info, warning...).
     */
    private void addAlert(String pContent, int pTimeVisible, int pAlertType) {
        if (mAlertsDisabled) {
            return;
        }
        synchronized (mListAlerts){
            int indexAlert = findAlertByContent(pContent);
            if (indexAlert != -1) {
                // if alert already exists, just update it
                TypeAlertAnimation alert = mListAlerts.get(indexAlert);
                alert.setFirstTime(System.currentTimeMillis());
                if (alert.mAlertType != pAlertType || alert.mTimeAlert != pTimeVisible) {
                    alert.setTimeAlert(pTimeVisible);
                    alert.setAlertType(pAlertType);
                }
            } else {
                // if alert does not already exist, create it
                if(this.getVisibility() == GONE) {
                    ViewUtils.setVisibility(this, VISIBLE);
                }
                TypeAlertAnimation alert = new TypeAlertAnimation(pContent, pTimeVisible, pAlertType);
                Log.d("TAG_hey","showing alert animation now");
                alert.show();
            }
        }
    }

    /**
     * Update an alert content.
     * @param pContentToFind content to find.
     * @param pNewContent    new alert content.
     */
    public void updateTextAlert(String pContentToFind, String pNewContent) {
        synchronized (mListAlerts) {
            int indexAlert = findAlertByContent(pContentToFind);
            if (indexAlert != -1) {
                // if alert already exists, just update it
                TypeAlertAnimation alert = mListAlerts.get(indexAlert);
                alert.setContent(pNewContent);
            }
        }
    }

    /**
     * Find an alert with the same string. It avoid showing twice time the same alert.
     * @param pContent text of the alert.
     * @return true if same text is already display.
     */
    private int findAlertByContent(String pContent) {
        Log.d(TAG,"hey size of mLisAlerts is ="+mListAlerts.size());
        for (int i = 0; i < mListAlerts.size(); i++) {
            Log.d(TAG," mLisAlerts string iteration is ="+mListAlerts.get(i).getContent());
            if (mListAlerts.get(i).getContent().toLowerCase().contains(pContent.toLowerCase())) {
                Log.d(TAG," mLisAlerts string FOUND! iteration is ="+mListAlerts.get(i).getContent());
                return i;
            }
        }

        return -1;
    }

    public boolean alertIsWarning(String content){
        int alert = findAlertByContent(content);
        if(alert == -1){
            return false;
        }
        return mListAlerts.get(alert).mIsShown
                && mListAlerts.get(alert).mAlertType == ALERT_WARNING;
    }

    public boolean alertIsDanger(String content){
        int alert = findAlertByContent(content);
        if(alert == -1){
            return false;
        }
        return mListAlerts.get(alert).mIsShown
                && mListAlerts.get(alert).mAlertType == ALERT_DANGER;
    }

    public boolean alertIsHidden(String content){
        int alert = findAlertByContent(content);
        if(alert == -1) {
            return true;
        }
        return !mListAlerts.get(alert).mIsShown;
    }

    /**
     * Set time for long alert.
     * @param pLongAlertTime time in ms.
     */
    public void setLongAlertTime(int pLongAlertTime) {
        this.mLongAlertTime = pLongAlertTime;
    }

    /**
     * Represents an animation.
     */
    private class TypeAlertAnimation {
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

            updateLayout();
        }

        private void updateLayout() {
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
        }
    }
}
