package com.zxy.zxydialog;

import android.view.View;

import com.zxy.zxydialog.tools.MyToast;

public class TToast {
    public static void show(String message) {
        MyToast.Companion.instance().show(message);
    }

    public static void show(String message, int time) {
        MyToast.Companion.instance().show(message, time);
    }

    public static void show(View layoutView) {
        MyToast.Companion.instance().show(layoutView);
    }

    public static void show(View layoutView, int time) {
        MyToast.Companion.instance().show(layoutView, time);
    }
}