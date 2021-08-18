package com.zxy.dialogdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        initView()
    }

    private fun initView() {
        btnDialog.setOnClickListener {
            var alertDialogUtils = AlertDialogUtils.build(this)
                .setValues("Title", "Content")
                .create { view, alertDialogUtils ->
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                        }
                        R.id.tvDialogCancel -> {
                        }
                    }
                    alertDialogUtils.dismiss()
                }
        }

        btnDialog1.setOnClickListener {
            val alertDialogUtils = AlertDialogUtils.build(this)
                .setView(R.layout.dialog_curse)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(true) //可选                                   默认true
                .setEditFocus(R.id.tvDialgContent)
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel) //可选  Dialog中的点击事件
                .create { view, alertDialogUtils -> //必选                    点击事件的回调
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
        }
        btnDialog2.setOnClickListener {
            val alertDialogUtils = AlertDialogUtils.build(this)
                .setView(R.layout.dialog_scrollview)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(true) //可选                                   默认true
                .isShowKeyboard(R.id.tvDialgContent)
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfirm) //可选  Dialog中的点击事件
                .create { view, alertDialogUtils -> //必选                    点击事件的回调
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                            alertDialogUtils.dismiss()

                        }
                    }
                }
        }
        btnDialog3.setOnClickListener {
            val alertDialogUtils = AlertDialogUtils.build(this)
                .setView(R.layout.dialog_test)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(true) //可选                                   默认true
                .isShowKeyboard(R.id.tvDialgContent)
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfirm) //可选  Dialog中的点击事件
                .create { view, alertDialogUtils -> //必选                    点击事件的回调
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                            alertDialogUtils.dismiss()

                        }
                    }
                }
        }
    }
}