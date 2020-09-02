package com.zxy.zxydialog

import android.annotation.SuppressLint
import android.widget.Toast
import com.zxy.zxydialog.tools.Applications
import com.zxy.zxydialog.tools.MyToast

/**
 * Created by zxy on 2020/9/2 11:17
 * ******************************************
 * *
 * ******************************************
 */
class TToast {
    companion object {
        fun show(message: String) {
            MyToast.instance().show(message)
        }

        fun show(message: String, time: Int) {
            MyToast.instance().show(message, time)
        }
    }

}