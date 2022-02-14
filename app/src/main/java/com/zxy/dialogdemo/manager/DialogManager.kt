package com.zxy.dialogdemo.manager

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import com.zxy.dialogdemo.R
import com.zxy.zxydialog.AlertDialogUtils

object DialogManager {
    /**
     *
     */
    fun showCustom(mContext:Activity){
        AlertDialogUtils.build(mContext)
            .setValues("Title", "Content")
            .setFullScreen(true)
            .OnDispatchTouchEvent(object : AlertDialogUtils.OnDispatchTouchEvent{
                override fun dispatchTouchEvent(ev: MotionEvent) {

                }
            })
            .OnClickListener { view, alertDialogUtils ->
                when (view.id) {
                    R.id.tvDialogConfig -> {

                    }
                    R.id.tvDialogCancel -> {
                    }
                }
                alertDialogUtils.dismiss()
            }.show()
    }
}