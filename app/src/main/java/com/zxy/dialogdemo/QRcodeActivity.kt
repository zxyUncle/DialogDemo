package com.zxy.dialogdemo

import android.graphics.Bitmap
import android.os.Bundle
import com.google.zxing.Result
import com.google.zxing.client.result.ParsedResult
import com.google.zxing.client.result.ParsedResultType
import com.zxy.zxydialog.TToast
import com.zxy.zxydialog.zxing.ZXingActivity

/**
 * Created by zsf on 2021/7/30 11:27
 * ******************************************
 * * 二维码
 * ******************************************
 */
class QRcodeActivity : ZXingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {

    }

    override fun executereslt(rawResult: Result?, parsedResult: ParsedResult?, barcode: Bitmap?) {
        super.executereslt(rawResult, parsedResult, barcode)
        TToast.show(rawResult?.text?:"没有拿到数据")
        when (parsedResult?.type) {
            ParsedResultType.ADDRESSBOOK -> {

            }
            ParsedResultType.URI -> {

            }
            ParsedResultType.TEXT -> {

            }
        }
    }

}