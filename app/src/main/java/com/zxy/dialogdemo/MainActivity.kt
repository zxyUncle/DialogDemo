package com.zxy.dialogdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.zxy.zxydialog.AlertDialogUtils
import com.zxy.zxydialog.AnimatorEnum
import com.zxy.zxydialog.PopWindowUtils
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
                            alertDialogUtils.cancel()
                            Toast.makeText(this, "tvDialogConfig", Toast.LENGTH_LONG).show()
                        }
                        com.zxy.zxydialog.R.id.tvDialogCancel -> {
                            alertDialogUtils.cancel()
                            Toast.makeText(this, "tvDialogCancel", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }

        btnDialog1.setOnClickListener {
            AlertDialogUtils.build(this)
                .setView(R.layout.dialog_curse)
                .setAnimator(AnimatorEnum.TRAN_B.VALUE)
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)
                .create { view, alertDialogUtils ->
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                            alertDialogUtils.cancel()
                            Toast.makeText(this, "tvDialogConfig", Toast.LENGTH_LONG).show()
                        }
                        R.id.tvDialogCancel -> {
                            alertDialogUtils.cancel()
                            Toast.makeText(this, "tvDialogCancel", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }

        btnPop2.setOnClickListener {
            PopWindowUtils.build(this)
                .setView(R.layout.dialog_curse)
                .setGravity(Gravity.TOP)
                .setTransparency(0.5f)
                .setAnimator(AnimatorEnum.FOLD.VALUE)
                .setTimer(2000)
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)
                .create(btnPop2) { view, popupWindowUtils ->
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                            popupWindowUtils.dismiss()
                            Toast.makeText(this, "tvDialogConfig", Toast.LENGTH_LONG).show()
                        }
                        R.id.tvDialogCancel -> {
                            popupWindowUtils.dismiss()
                            Toast.makeText(this, "tvDialogCancel", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }


    }
}