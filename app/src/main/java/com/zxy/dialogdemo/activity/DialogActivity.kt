package com.zxy.dialogdemo.activity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleObserver
import com.zxy.dialogdemo.R
import com.zxy.dialogdemo.manager.DialogManager
import com.zxy.zxydialog.AlertDialogUtils
import com.zxy.zxydialog.TToast
import com.zxy.zxydialog.tools.AnimatorEnum
import kotlinx.android.synthetic.main.activity_dialog.*

/**
 * Created by zsf on 2021/4/1 14:30
 * ******************************************
 * *
 * ******************************************
 */
class DialogActivity : ComponentActivity() , LifecycleObserver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
        initView()
    }

    private fun initView() {
        btnDialog.setOnClickListener {
            DialogManager.showCustom(this)
        }

        var alertDialogUtils :AlertDialogUtils?=null
        btnDialog1.setOnClickListener {
            alertDialogUtils = AlertDialogUtils.build(this)
                .setView(R.layout.dialog_curse)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(false) //可选                                   默认true
                .setCanceledOnTouchOutside(false)                              //触摸外部区域
                .setEditFocus(R.id.tvDialgContent)
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel) //可选  Dialog中的点击事件
                .setOnDismissListeners(DialogInterface.OnDismissListener {
                    Log.e("zxy", "setOnDismissListener")
                })
                .OnClickListener { view, alertDialogUtils -> //必选                    点击事件的回调
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                            alertDialogUtils.dismiss()

                        }
                        R.id.tvDialogCancel -> {
                            alertDialogUtils.dismiss()
                            TToast.show("tvDialogCancel")
                        }
                    }
                }
                .setTimer(10) { time, alertDialogUtils ->
                        var tvDialgContent =
                            alertDialogUtils.layoutView!!.findViewById<EditText>(R.id.tvDialgContent)
                        tvDialgContent.setText("$time")
                        Log.e("zxy", "$time")
                        if (time == 7) {
                            finish()
                        }

                }
                .show {
                    Log.e("zxy", "onShow")
                }
        }

        btnDialog2.setOnClickListener {
            val alertDialogUtils = AlertDialogUtils.build(this)
                .setView(R.layout.dialog_scrollview)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(true) //可选                                   默认true
                .setFullScreen(true)
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfirm) //可选  Dialog中的点击事件
                .setOnDismissListeners { Log.e("zxy", "onDismiss") }
                .OnClickListener { view, alertDialogUtils -> //必选                    点击事件的回调
                    when (view.id) {
                        R.id.tvDialogConfirm -> {
                            alertDialogUtils.dismiss()
                        }
                    }
                }.show()
        }
        btnDialog3.setOnClickListener {
            val alertDialogUtils = AlertDialogUtils.build(this)
                .setView(R.layout.dialog_test)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(true) //可选                                   默认true
                .isShowKeyboard(R.id.tvDialgContent)
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfirm) //可选  Dialog中的点击事件
                .OnClickListener { view, alertDialogUtils -> //必选                    点击事件的回调
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                            alertDialogUtils.dismiss()

                        }
                    }
                }.show()
        }
    }

    private fun bottomNavInVisible() {
        try {
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}