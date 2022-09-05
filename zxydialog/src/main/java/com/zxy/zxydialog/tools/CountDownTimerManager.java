package com.zxy.zxydialog.tools;

import android.os.CountDownTimer;

public class CountDownTimerManager {

    private CountDownTimer mCountDownTimer;

    private CountDownTimerManager() {
    }

    private static class SingletonHolder {
        private static final CountDownTimerManager INSTANCE = new CountDownTimerManager();
    }

    //获取单例
    public static CountDownTimerManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void endCountDownTimer() {
        try {
            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
                mCountDownTimer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 60秒倒计时
    public void startCountDownTimer(CountDownTimerListener listener) {
        startCountDownTimer(60, listener);
    }

    public void startCountDownTimer(long millisInFuture, final CountDownTimerListener listener) {
        try {
            endCountDownTimer();
            mCountDownTimer = new CountDownTimer(millisInFuture * 1000 + 200, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    try {
                        float time = millisUntilFinished / 1000F;
                        int round = Math.round(time);
                        listener.onTick(round);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        listener.onFinish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    endCountDownTimer();
                }
            };
            mCountDownTimer.start();
            listener.onTick((int) millisInFuture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface CountDownTimerListener {
        void onTick(int second);

        void onFinish();
    }

}