package com.zxy.zxydialog.tools

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.zxy.zxydialog.R
import com.zxy.zxydialog.tools.Applications
import java.lang.Exception

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
    private lateinit var toast: Toast
    private lateinit var tvContent: TextView
    private lateinit var  initView:View

    //zxy 单例模式
    @SuppressLint("WrongConstant")
    private constructor() {
        initView()
    }

    @SuppressLint("WrongConstant")
    private fun initView(){
        initView = LayoutInflater.from(Applications.context()).inflate(R.layout.zxy_toast, null)
        tvContent = initView.findViewById(R.id.tvToastContent)
        toast = Toast(Applications.context())
        toast.view = initView
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
    fun show(message: String?) {
        if (message!=null) {
            toast.view = initView
            tvContent.text = message
            toast.show()
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
        toast.show()
    }

    @SuppressLint("WrongConstant")
    fun show(message: String, time: Int) {
        if (message!=null) {
            toast.view = initView
            tvContent.text = message
            toast.duration = time
            toast.show()
        }
    }

    private fun cancel() {
        toast.cancel()
    }

}