package com.zxy.dialogdemo

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.zxy.zxydialog.BottomSheetDialogUtils
import com.zxy.zxydialog.PopWindowUtils
import com.zxy.zxydialog.TToast
import com.zxy.zxydialog.snackbar.ZToast
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



    }
}