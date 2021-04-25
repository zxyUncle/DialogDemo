package com.zxy.zxydialog

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog


/**
 * Created by zxy on 2020/4/22 0022 11:23
 * ******************************************
 * * BottomSheetDialog底部弹出框
 * ******************************************
 */
class BottomSheetDialogUtils {
    var bottomSheetDialog: BottomSheetDialog? = null
    var layoutView: View? = null
    var bottomSheetBehavior: BottomSheetBehavior<View>? = null

    //zxy 单例模式
    private constructor() {}

    companion object {
        @JvmStatic
        fun build(mContext: Activity): Builder {
            return Builder(mContext)
        }
    }

    class Builder(mContext: Activity) {
        var stateMode: Int = BottomSheetBehavior.STATE_EXPANDED
        var mContext = mContext
        var minHeight: Float = 0.8f//最小高度百分比
        var myStyle = R.style.bottomSheetDialog
        var bottomSheetDialogUtils = BottomSheetDialogUtils()

        /**
         * 设置布局
         * @param layoutView View
         */
        fun setView(layoutId: Int): Builder {
            bottomSheetDialogUtils.layoutView =
                LayoutInflater.from(mContext).inflate(layoutId, null)
            return this
        }

        fun setStyle(myStyle: Int): Builder {
            this.myStyle = myStyle
            return this
        }

        /**
         * 所占窗口百分比
         */
        fun setMinHeightValue(minHeight: Float): Builder {
            this.minHeight = minHeight
            return this
        }

        fun setState(mode: Int = BottomSheetBehavior.STATE_EXPANDED): Builder {
            this.stateMode = mode
            return this
        }

        /**
         * @param close 被关闭的回调
         */
        @JvmOverloads
        fun show(closeBack:(()->Unit)={}): BottomSheetDialogUtils {
            bottomSheetDialogUtils.run {
                if (layoutView == null)
                    setView(R.layout.zxy_bottom_sheet_dialog)

                bottomSheetDialog = BottomSheetDialog(mContext, myStyle)
                var heigit = if (minHeight > 0 && minHeight <= 1) {
                    getPeekHeight(mContext, minHeight).toInt()
                } else {
                    getPeekHeight(mContext, 0.8f).toInt()
                }
                //外部包裹一层LinearLayout防止按比例不成功
                layoutView?.rootView?.minimumHeight = heigit
                bottomSheetDialog?.setContentView(bottomSheetDialogUtils.layoutView!!)

                bottomSheetBehavior =
                    BottomSheetBehavior.from(layoutView?.parent as View)
                bottomSheetBehavior?.peekHeight = heigit
                bottomSheetBehavior?.state = stateMode
                bottomSheetBehavior?.addBottomSheetCallback(object : BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            closeDialog()
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        if (slideOffset == -1.0f) {
                        }
                    }

                })

                bottomSheetDialog?.setOnDismissListener {
                    closeBack()
                }

                //设置透明背景
                bottomSheetDialog?.window?.findViewById<View>(R.id.design_bottom_sheet)
                    ?.setBackgroundResource(android.R.color.transparent)


                val bottomSheet =
                    bottomSheetDialog?.delegate?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
                bottomSheet?.setBackgroundColor(Color.parseColor("#00000000"))

//                mContext.window.setWindowAnimations(myStyle)//设置动画效果
                bottomSheetDialog?.show()
            }
            return bottomSheetDialogUtils
        }


    }


    fun setCancelable(isCancel:Boolean) {
        bottomSheetDialog?.setCancelable(isCancel)
        bottomSheetDialog?.setCanceledOnTouchOutside(isCancel)
    }

    /**
     * 关闭、销毁
     */
    fun closeDialog(){
        bottomSheetDialog?.dismiss()
    }

    /**
     * 弹窗高度，
     * @return height
     */
    private fun getPeekHeight(mContext: Context, minimumHeight: Float): Float {
        val windowManager = mContext
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val peekHeight = mContext.resources.displayMetrics.heightPixels
        val peekHeight = windowManager.defaultDisplay.height
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight * minimumHeight
    }


}