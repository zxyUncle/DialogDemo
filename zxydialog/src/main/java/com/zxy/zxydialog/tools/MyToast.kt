package com.zxy.zxydialog.tools

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.zxy.zxydialog.R
import com.zxy.zxydialog.tools.Applications

/**
 * Created by zxy on 2020/9/2 11:17
 * ******************************************
 * *
 * ******************************************
 */
class MyToast {
    private var taktMsg = ""
    private var taktTime = 500
    private var oldTime = 0
    private var toast: Toast
    private var tvContent: TextView

    //zxy 单例模式
    @SuppressLint("WrongConstant")
    private constructor() {
        var view = LayoutInflater.from(Applications.context()).inflate(R.layout.zxy_toast, null)
        tvContent = view.findViewById(R.id.tvToastContent)
        toast = Toast(Applications.context())
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.duration = taktTime
    }

    companion object {
        @JvmStatic
        val INSTANCE: MyToast by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MyToast()
        }
    }


    @SuppressLint("WrongConstant")
    fun show(message: String) {
        tvContent.text = message
        if (System.currentTimeMillis() - oldTime > taktTime) {//500秒外
            toast.show()
        } else {//500秒内
            if (message != taktMsg) {//内容不相同
                cancel()
                toast.show()
            }
        }
    }

    @SuppressLint("WrongConstant")
    fun show(layoutView: View) {
        toast.view = layoutView
        if (System.currentTimeMillis() - oldTime > taktTime) {//500秒外
            toast.show()
        }
    }

    @SuppressLint("WrongConstant")
    fun show(layoutView: View, time: Int) {
        toast.view = layoutView
        toast.duration = time
        if (System.currentTimeMillis() - oldTime > taktTime) {//500秒外
            toast.show()
        } else {//500秒内
            cancel()
            toast.show()
        }
    }

    @SuppressLint("WrongConstant")
    fun show(message: String, time: Int) {
        tvContent.text = message
        toast.duration = time
        if (System.currentTimeMillis() - oldTime > time) {//X秒外
            toast.show()
        } else {//X秒内
            if (message != taktMsg) {//内容不相同
                cancel()
                toast.show()
            }
        }
    }

    private fun cancel() {
        toast.cancel()
    }

}