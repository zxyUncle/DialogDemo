package com.zxy.dialogdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.zxy.ztoast.snackbar.ZToast
import com.zxy.zxydialog.AlertDialogUtils
import com.zxy.zxydialog.PopWindowUtils
import com.zxy.zxydialog.TToast
import com.zxy.zxydialog.tools.AnimatorEnum
import com.zxy.zxydialog.tools.LoadingTool
import com.zxy.zxydialog.tools.MyToast
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
                            alertDialogUtils.dismiss()
                            Toast.makeText(this, "tvDialogConfig", Toast.LENGTH_LONG).show()
                        }
                        com.zxy.zxydialog.R.id.tvDialogCancel -> {
                            alertDialogUtils.dismiss()
                            Toast.makeText(this, "tvDialogCancel", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }
        btnDialog1.setOnClickListener {
            AlertDialogUtils.build(this)
                .setView(R.layout.dialog_curse)
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)
                .create { view, alertDialogUtils ->
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
            LoadingTool.instance().show(this)
            PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //设置布局 -必选
                .setGravity(Gravity.TOP, 0, 0)//设置方向 -可选：设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//设置窗口透明度  -可选：默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：默认true
                .setAnimator(AnimatorEnum.FOLD_T_NO_B.VALUE)//可选:默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选：默认不倒计时
                .setExternalListener { } //外部点击事件的监听
//                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//点击控件的ID -可选：默认没有点击事件
                .showAtLocation { view, popWindowUtils ->  //点击事件的回调：view是点击控件的ID，popWindowUtils：通过他可以拿到任何东西

                }
        }

        btnPop3.setOnClickListener {
            TToast.show("请输入正确的手机号")
        }
        btnPop4.setOnClickListener {
            ZToast.setColorI("#000000")
            ZToast.showI(this,"网路错误")
        }

    }
}