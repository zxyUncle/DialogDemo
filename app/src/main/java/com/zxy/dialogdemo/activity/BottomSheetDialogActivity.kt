package com.zxy.dialogdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zxy.dialogdemo.R
import com.zxy.zxydialog.BottomSheetDialogUtils
import com.zxy.zxydialog.TToast
import kotlinx.android.synthetic.main.activity_bottom_sheet_dialog.*

class BottomSheetDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_dialog)
        initView()
    }

    private fun initView() {
        btnBottomSheetDialog.setOnClickListener {
            BottomSheetDialogUtils.build(this)
                .setView(R.layout.bottom_sheet_dialog)
                .setMinHeightValue(0.7f)
                .show()
        }

        btnBottomSheetDialog1.setOnClickListener {
            BottomSheetDialogUtils.build(this)
                .setView(R.layout.popwindow_qrcode)
                .setMinHeightValue(0.7f)
                .show {
                    TToast.show("销毁监听")
                }
        }
    }
}