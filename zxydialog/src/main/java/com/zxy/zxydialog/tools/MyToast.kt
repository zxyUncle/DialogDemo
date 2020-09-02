package com.zxy.zxydialog.tools

import android.annotation.SuppressLint
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
        toast.duration = taktTime
    }

    companion object {
        @Volatile
        private var instance: MyToast? = null

        fun instance(): MyToast {
            if (instance == null) {
                synchronized(MyToast::class.java) {
                    if (instance == null) {
                        instance = MyToast()
                    }
                }
            }
            return instance!!
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