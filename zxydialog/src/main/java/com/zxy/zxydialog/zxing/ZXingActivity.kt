package com.zxy.zxydialog.zxing

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.zxing.Result
import com.google.zxing.client.result.ParsedResult
import com.google.zxing.client.result.ParsedResultType
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.mylhyl.zxing.scanner.OnScannerCompletionListener
import com.mylhyl.zxing.scanner.decode.QRDecode
import com.permissionx.guolindev.PermissionX
import com.zxy.zxydialog.R
import com.zxy.zxydialog.TToast
import kotlinx.android.synthetic.main.activity_zxing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Created by zxy on 2020/7/26 0026 13:37
 * ******************************************
 * * 二维码扫描-https://github.com/mylhyl/Android-Zxing
 * ******************************************
 */
open class ZXingActivity : AppCompatActivity(), OnScannerCompletionListener {
    var permissList = mutableListOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    override fun onResume() {
        mScannerView.onResume()
        super.onResume()
    }

    override fun onPause() {
        mScannerView.onPause()
        super.onPause()
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zxing)
        initView();
    }

    private fun initView() {
        requestPermission(permissList, {
            mScannerView.onResume()
        }, {
            Toast.makeText(this, "不同意的: $it", Toast.LENGTH_LONG).show()
        })
        //闪光灯
        mCheckBox.setOnClickListener {
            if ((it as CheckBox).isChecked) {
                mScannerView.toggleLight(true)//开
            } else {
                mScannerView.toggleLight(false)//关
            }
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }

        //扫描结果
        mScannerView.setOnScannerCompletionListener(this)
        //进入相册
        btnPhoto.setOnClickListener {
            PictureSelector.create(ZXingActivity@ this)
                .openGallery(PictureMimeType.ofImage()) //全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .selectionMode(PictureConfig.SINGLE) // 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(false) // 是否可预览图片 true or false
                .isCamera(false) // 是否显示拍照按钮 true or false
                .isZoomAnim(true) // 图片列表点击 缩放效果 默认true
                .enableCrop(false) // 是否裁剪 true or false
                .loadImageEngine(GlideEngine.createGlideEngine())
                .isCompress(true) // 是否压缩 true or false
                .compressQuality(60)
                .hideBottomControls(false) // 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false) // 是否显示gif图片 true or false
                .freeStyleCropEnabled(false) // 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false) // 是否圆形裁剪 true or false
                .showCropFrame(false) // 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false) // 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(false) // 是否开启点击声音 true or false
                .minimumCompressSize(50) // 小于50kb的图片不压缩
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true) // 裁剪是否可放大缩小图片 true or false
                .isDragFrame(false) // 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST) //结果回调onActivityResult code

        }
    }

    /**
     * 重新扫码
     */
    private fun backScan(delay: Long = 2000) {
        mScannerView.restartPreviewAfterDelay(delay)
    }

    /**
     * 解析图片二维码，条形码
     */
    protected fun decodeQR(imagePath: String) {
        //成功取得照片
        QRDecode.decodeQR(imagePath) { rawResult, parsedResult, barcode ->
            executereslt(rawResult, parsedResult, barcode)
        }
    }

    /**
     * 扫描成功后将调用
     *
     * @param rawResult    扫描结果
     * @param parsedResult 结果类型
     * @param barcode      扫描后的图像
     */
    override fun onScannerCompletion(
        rawResult: Result?,
        parsedResult: ParsedResult?,
        barcode: Bitmap?
    ) {
        executereslt(rawResult, parsedResult, barcode)
    }

    /**
     * 处理结果
     * 需要重写的对象
     */
    protected open fun executereslt(
        rawResult: Result?,
        parsedResult: ParsedResult?,
        barcode: Bitmap?
    ) {
        backScan()
        when (parsedResult?.type) {
            ParsedResultType.ADDRESSBOOK -> {

            }
            ParsedResultType.URI -> {

            }
            ParsedResultType.TEXT -> {

            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片选择结果回调
                    val localMedia = PictureSelector.obtainMultipleResult(data)
                    if (localMedia != null && localMedia.size > 0) {
                        val localMedia1 = localMedia[0]
                        val compressPath = localMedia1.compressPath
                        decodeQR(compressPath)
                    }
                }
            }
        }
    }

    fun FragmentActivity.requestPermission(
        permissions: MutableList<String>,
        onSuccess: () -> Unit,
        onFailed: (MutableList<String>) -> Unit
    ) {
        var mContext = this
        PermissionX.init(mContext)
            .permissions(permissions)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "请同意这些权限，用于我们接下来的操作",
                    "确定",
                    "取消"
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "您需要在设置中手动允许必要的权限，否则不能使用这部分功能哦!",
                    "确定",
                    "取消"
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {//全部同意
                    onSuccess()
                } else {//
                    onFailed(deniedList)
                }
            }
    }

}