package com.zxy.zxydialog;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class BaseDialog extends Dialog {
    private AlertDialogUtils alertDialogUtils;

    public BaseDialog(@NonNull Context context, int themeResId, AlertDialogUtils alertDialogUtils) {
        super(context,themeResId);
        this.alertDialogUtils = alertDialogUtils;
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        if (alertDialogUtils.getOnDispatchTouchEvent()!=null){
            alertDialogUtils.getOnDispatchTouchEvent().dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    public void show() {
        super.show();
        bottomNavInVisible();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        bottomNavInVisible();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottomNavInVisible();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bottomNavInVisible();
    }

    private void bottomNavInVisible() {
        try {
            if (alertDialogUtils.getFullScreen())
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE
                );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}