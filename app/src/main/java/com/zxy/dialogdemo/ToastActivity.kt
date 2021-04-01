package com.zxy.dialogdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.zxy.zxydialog.TToast
import com.zxy.zxydialog.snackbar.ZToast
import com.zxy.zxydialog.tools.Applications
import com.zxy.zxydialog.tools.LoadingTool
import kotlinx.android.synthetic.main.activity_toast.*

class ToastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)
        initView()
    }

    private fun initView() {
        btnToast.setOnClickListener {
            TToast.show("请输入正确的手机号")
        }

        btnCoustToast.setOnClickListener {
            var layoutView =
                LayoutInflater.from(Applications.context()).inflate(R.layout.toast_course, null)
            TToast.show(layoutView)
        }

        btnZToast.setOnClickListener {
            ZToast.setColorI("#000000")
            ZToast.showI(this, "网路错误")
        }


        btnLoad.setOnClickListener {
            LoadingTool.show(this,"加载中...");
        }
    }
}