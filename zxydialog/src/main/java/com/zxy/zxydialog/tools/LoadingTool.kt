package com.zxy.zxydialog.tools

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD
import com.zxy.zxydialog.R

/**
 * Created by zxy on 2020/8/6 16:06
 * ******************************************
 * * 加载中动画
 * ******************************************
 */
class LoadingTool {
    var kProgressHUD: KProgressHUD? = null
    var isAlowShow: Boolean = true
    var title: String? = null

    //zxy 单例模式
    private constructor() {}

    companion object {
        @Volatile
        private var instance: LoadingTool? = null

        fun instance(): LoadingTool {
            if (instance == null) {
                synchronized(LoadingTool::class.java) {
                    if (instance == null) {
                        instance = LoadingTool()
                    }
                }
            }
            return instance!!
        }
    }

    fun show(mContext: Context) {
        if (kProgressHUD == null && isAlowShow) {
            if (title == null)
                title = mContext.resources.getString(R.string.zxy_loading)
            kProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(title)
                .setCancellable(true)
                .setAutoDismiss(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.1f)
                .show()
        } else {
            if (!kProgressHUD!!.isShowing && isAlowShow) {
                hide()
            }
            if (title == null)
                title = mContext.resources.getString(R.string.zxy_loading)
            kProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(title)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.1f)
                .show()
        }


    }

    fun hide() {
        if (kProgressHUD != null && isAlowShow) {
            kProgressHUD?.dismiss()
            kProgressHUD = null
        }
    }


    fun showMultistage(mContext: Context) {
        isAlowShow = false
        if (kProgressHUD == null && mContext != null) {
            if (title == null)
                title = mContext.resources.getString(R.string.zxy_loading)
            kProgressHUD = KProgressHUD.create(mContext!!)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(title)
//                .setDetailsLabel("Downloading data")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.1f)
                .show()
        } else {
            if (!kProgressHUD!!.isShowing) {
                hideMultistage()
            }
            if (title == null)
                title = mContext.resources.getString(R.string.zxy_loading)
            kProgressHUD = KProgressHUD.create(mContext!!)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(title)
//                .setDetailsLabel("Downloading data")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.1f)
                .show()
        }
    }

    fun hideMultistage() {
        isAlowShow = true
        if (kProgressHUD != null) {
            kProgressHUD?.dismiss()
            kProgressHUD = null
        }
    }

}