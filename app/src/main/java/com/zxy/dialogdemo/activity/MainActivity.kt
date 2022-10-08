package com.zxy.dialogdemo.activity

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.zxy.dialogdemo.R
import com.zxy.dialogdemo.startNewActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        //跳转到Dialog
        btnAlertDialog.setOnClickListener {
            startNewActivity<DialogActivity>()
        }

        //跳转到PopWindow
        btnPopWindow.setOnClickListener {
            startNewActivity<PopWindowActivity>()
        }
        //跳转到Toast
        btnToast.setOnClickListener {
            startNewActivity<ToastActivity>()
        }

        //跳转到Toast
        btnBottomSheetDialog.setOnClickListener {
            startNewActivity<BottomSheetDialogActivity>()
        }

        btnAnimatorShow.setOnClickListener {
            val animation: Animation =
                AnimationUtils.loadAnimation(this, R.anim.zxt_dialog_enter_anim_fold_right)
            vAnimator.visibility = View.VISIBLE
            vAnimator.startAnimation(animation)
        }
        btnAnimatorHide.setOnClickListener {
            val animation: Animation =
                AnimationUtils.loadAnimation(this, R.anim.zxt_dialog_exit_anim_fold_right)
            vAnimator.startAnimation(animation)
            vAnimator.visibility = View.GONE
        }


    }
}