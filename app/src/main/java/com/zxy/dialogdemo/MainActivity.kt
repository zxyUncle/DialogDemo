package com.zxy.dialogdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.zxy.zxydialog.snackbar.ZToast
import com.zxy.zxydialog.AlertDialogUtils
import com.zxy.zxydialog.BottomSheetDialogUtils
import com.zxy.zxydialog.PopWindowUtils
import com.zxy.zxydialog.TToast
import com.zxy.zxydialog.tools.AnimatorEnum
import com.zxy.zxydialog.tools.Applications
import com.zxy.zxydialog.tools.LoadingTool
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btnDialog.setOnClickListener {
            AlertDialogUtils.build(this)
                .setValues("Title", "Content")
                .create { view, alertDialogUtils ->
                    when (view.id) {
                        com.zxy.zxydialog.R.id.tvDialogConfig -> {
                        }
                        com.zxy.zxydialog.R.id.tvDialogCancel -> {
                        }
                    }
                    alertDialogUtils.dismiss()
                }
        }
        btnDialog1.setOnClickListener {
            AlertDialogUtils.build(this)
                .setView(R.layout.dialog_curse)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(true) //可选                                   默认true
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel) //可选  Dialog中的点击事件
                .create { view, alertDialogUtils -> //必选                    点击事件的回调
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                            alertDialogUtils.dismiss()
                            Toast.makeText(this, "tvDialogConfig", Toast.LENGTH_LONG).show()
                        }
                        R.id.tvDialogCancel -> {
                            alertDialogUtils.dismiss()
                            Toast.makeText(this, "tvDialogCancel", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }

        btnPop2.setOnClickListener {
            PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.TOP, 0, 0)//可选      设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//可选：                                      默认0.5    0为全黑  1全透明
                .setPopWidthHeight(PopWindowUtils.MATCH, PopWindowUtils.WRAP)//可选 默认w=Math，h=wrap
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_T_NO_B.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
//                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAtLocation { view, popWindowUtils ->  //点击事件的回调：       popWindowUtils：通过他可以拿到任何东西

                }
        }

        btnPop3.setOnClickListener {
            TToast.show("请输入正确的手机号")
        }
        btnPop4.setOnClickListener {
            ZToast.setColorI("#000000")
            ZToast.showI(this, "网路错误")
        }
        btnPop5.setOnClickListener {
            LoadingTool.instance().show(this)
//            LoadingTool.instance().hide()
        }

        btnPopWindows.setOnClickListener {
            var mPop = PopWindowUtils.build(this)
                .setView(R.layout.pop_select) //设置布局 -必选
                .setGravity(Gravity.BOTTOM, 0, 0)//设置方向 -可选：设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//设置窗口透明度  -可选：默认0.5    0为全黑  1全透明
                .setAnimator(AnimatorEnum.FOLD_T.VALUE)//可选:默认AnimatorEnum.FOLD_B.VALUE
                //外部点击事件的监听
//                .setOnClick(R.id.tvDialgTitle)//点击控件的ID -可选：默认没有点击事件
                .showAsDropDown(btnPopWindows) { view, popWindowUtils ->  //点击事件的回调：view是点击控件的ID，popWindowUtils：通过他可以拿到任何东西
                    if (view.id == R.id.tvDialgTitle) {
                        ZToast.showI(this, "点击事件")
                    }
                }
            //点击外部销毁Pop的监听
            mPop.setExternalListener {
                ZToast.showI(this, "外部点击")
            }

        }

        btnBottom.setOnClickListener {
            BottomSheetDialogUtils.build(this)
                .setView(R.layout.zxy_bottom_sheet_dialog)
                .setMinHeight(0.8f)
                .show()
        }
        btnTToast.setOnClickListener {
            var layoutView =
                LayoutInflater.from(Applications.context()).inflate(R.layout.toast_course, null)
            TToast.show(layoutView)
        }
    }
}