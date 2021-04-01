package com.zxy.zxydialog;

import android.view.View;

import com.zxy.zxydialog.tools.Applications;
import com.zxy.zxydialog.tools.MyToast;

public class TToast {
    public static void show(String message) {
        MyToast.getINSTANCE().show(message);
    }

    public static void show(int message) {
        MyToast.getINSTANCE().show(Applications.context().getResources().getString(message));
    }

    public static void show(String message, int time) {
        MyToast.getINSTANCE().show(message, time);
    }

    public static void show(int message, int time) {
        MyToast.getINSTANCE().show(Applications.context().getResources().getString(message), time);
    }

    public static void show(View layoutView) {
        MyToast.getINSTANCE().show(layoutView);
    }

    public static void show(View layoutView, int time) {
        MyToast.getINSTANCE().show(layoutView, time);
    }
}
