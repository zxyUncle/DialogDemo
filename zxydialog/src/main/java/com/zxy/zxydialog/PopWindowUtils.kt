package com.zxy.zxydialog

import android.annotation.SuppressLint
import android.app.Activity
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import com.zxy.zxydialog.tools.AnimatorEnum
import android.graphics.drawable.BitmapDrawable
import android.R.attr.name




/**
 * Created by zxy on 2019/12/23 15:32
 * ******************************************
 * * 界面的上方弹出
 * ******************************************
 */
class PopWindowUtils {
    var layoutView: View? = null
    var mContext: Activity? = null
    var popupWindow: PopupWindow? = null
    var listView: MutableList<Int>? = null

    companion object {
        const val MATCH = ViewGroup.LayoutParams.MATCH_PARENT
        const val WRAP = ViewGroup.LayoutParams.WRAP_CONTENT
        fun build(mContext: Activity): Builder {
            return Builder(mContext)
        }
    }

    class Builder(mContext: Activity) {
        var mContext = mContext
        var popupWindowUtils = PopWindowUtils()
        var isTouchable = true
        var isFocusable = true
        var isOutsideTouchable = true
        var animator: Int? = AnimatorEnum.FOLD_B.VALUE
        var gravity: Int? = Gravity.TOP
        var offsetWidth: Int = 0
        var offsetHeight: Int = 0
        var transparency: Float = 0.8f
        var millisInFuture: Long? = null
        var popWidth = MATCH
        var popHeight = WRAP

        private val mTimer by lazy {
            object : CountDownTimer(millisInFuture ?: 0, 1000) {
                override fun onFinish() {
                    popupWindowUtils.dismiss()
                }

                override fun onTick(millisUntilFinished: Long) {

                }

            }
        }

        /**
         * 设置布局
         * @param layoutView View
         */
        fun setView(layoutId: Int): Builder {
            popupWindowUtils.layoutView = LayoutInflater.from(mContext).inflate(layoutId, null)
            return this
        }

        fun isTouchable(isTouchable: Boolean): Builder {
            this.isTouchable = isTouchable
            return this
        }

        fun isFocusable(isFocusable: Boolean): Builder {
            this.isFocusable = isFocusable
            return this
        }

        fun isOutsideTouchable(isOutsideTouchable: Boolean): Builder {
            this.isOutsideTouchable = isOutsideTouchable
            return this
        }

        fun setPopWidthHeight(popWidth: Int, popHeight: Int): Builder {
            this.popWidth = popWidth
            this.popHeight = popHeight
            return this
        }

        /**
         * 设置动画
         */
        fun setAnimator(animator: Int): Builder {
            this.animator = animator
            return this
        }

        /**
         * 设置窗口透明度，默认0.5    0为全黑  1全透明
         * @param transparency Float
         * @return Builder
         */
        fun setTransparency(transparency: Float): Builder {
            this.transparency = transparency
            return this
        }

        /**
         * 设置方向及宽高偏移值
         */
        fun setGravity(
            gravity: Int,
            offsetWidth: Int = 0,
            offsetHeight: Int = 0
        ): Builder {
            this.gravity = gravity
            this.offsetWidth = offsetWidth
            this.offsetHeight = offsetHeight
            return this
        }

        /**
         * 设置点击事件
         * @param viewId IntArray
         */
        fun setOnClick(vararg viewId: Int): Builder {
            popupWindowUtils.listView = viewId.toTypedArray().toMutableList()
            return this
        }

        fun setTimer(millisInFuture: Long): Builder {
            this.millisInFuture = millisInFuture
            return this
        }


        @SuppressLint("ClickableViewAccessibility")
        fun showAsDropDown(
            viewLocation: View,
            block: (View, PopWindowUtils) -> Unit,
            onDismiss: (PopWindowUtils) -> Unit
        ): PopWindowUtils {
            if (popupWindowUtils.layoutView == null) {
                Log.e("", mContext.resources.getString(R.string.zxy_no_view))
            } else {
                if (popupWindowUtils.popupWindow != null) {
                    popupWindowUtils.dismiss()
                }
                popupWindowUtils.mContext = mContext
                popupWindowUtils.popupWindow = PopupWindow(
                    popupWindowUtils.layoutView,
                    popWidth,
                    popHeight
                )
                popupWindowUtils.popupWindow!!.isTouchable = isTouchable
                popupWindowUtils.popupWindow!!.isFocusable = isFocusable
                popupWindowUtils.popupWindow!!.isOutsideTouchable = isOutsideTouchable
                //4.0/5.外部点击关闭处理
                if(isOutsideTouchable)  popupWindowUtils.popupWindow!!.setBackgroundDrawable(BitmapDrawable())

                popupWindowUtils.popupWindow!!.animationStyle =
                    animator ?: AnimatorEnum.TRAN_B.VALUE
                var height = 0
                var width = 0
                popupWindowUtils.layoutView?.measure(0, 0)
                when (gravity) {
                    Gravity.TOP -> {
                        height =
                            -popupWindowUtils.layoutView?.measuredHeight!! - viewLocation.height + offsetHeight
                        width = 0 + offsetWidth
                    }
                    Gravity.BOTTOM -> {
                        height = offsetHeight
                        width = offsetWidth
                    }
                    Gravity.LEFT -> {
                        height = -popupWindowUtils.layoutView?.measuredHeight!! + offsetHeight
                        width = -popupWindowUtils.layoutView?.measuredWidth!! + offsetHeight
                    }
                    Gravity.RIGHT -> {
                        height = -popupWindowUtils.layoutView?.measuredHeight!! + offsetHeight
                        width = viewLocation.width + offsetHeight
                    }
                }
                popupWindowUtils.popupWindow!!.showAsDropDown(viewLocation, width, height)
                if (millisInFuture != null)
                    mTimer.start()
                if (popupWindowUtils.listView != null) {
                    for (index in 0 until popupWindowUtils.listView!!.size step 1) {
                        popupWindowUtils.layoutView!!.findViewById<View>(popupWindowUtils.listView!![index])
                            .setOnClickListener {
                                block(it, popupWindowUtils)
                            }
                    }
                }
                //设置遮罩层
                popupWindowUtils.backgroundAlpha(transparency)
                popupWindowUtils.popupWindow!!.setOnDismissListener {
                    popupWindowUtils.backgroundAlpha(1f)
                    //**重点，清理掉浮动的遮罩层，否则使用转场动画的时候会有黑色背景，因为dismiss是隐藏不是销毁
                    mContext?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    onDismiss(popupWindowUtils)
                }

            }
            return popupWindowUtils

        }

        @SuppressLint("ClickableViewAccessibility")
        fun showAtLocation(block: (View, PopWindowUtils) -> Unit,
                           onDismiss: (PopWindowUtils) -> Unit): PopWindowUtils {
            if (popupWindowUtils.layoutView == null) {
                Log.e("", mContext.resources.getString(R.string.zxy_no_view))
            } else {
                if (popupWindowUtils.popupWindow != null) {
                    popupWindowUtils.dismiss()

                }
                popupWindowUtils.mContext = mContext
                popupWindowUtils.popupWindow = PopupWindow(
                    popupWindowUtils.layoutView,
                    popWidth,
                    popHeight
                )
                popupWindowUtils.popupWindow!!.isTouchable = isTouchable
                popupWindowUtils.popupWindow!!.isFocusable = isFocusable
                popupWindowUtils.popupWindow!!.animationStyle =
                    animator ?: AnimatorEnum.TRAN_B.VALUE

                popupWindowUtils.popupWindow!!.isOutsideTouchable = isOutsideTouchable

                var height = 0
                var width = 0
                popupWindowUtils.popupWindow!!.showAtLocation(
                    mContext.window.decorView,
                    width,
                    height,
                    gravity ?: Gravity.TOP
                )
                if (millisInFuture != null)
                    mTimer.start()
                if (popupWindowUtils.listView != null) {
                    for (index in 0 until popupWindowUtils.listView!!.size step 1) {
                        popupWindowUtils.layoutView!!.findViewById<View>(popupWindowUtils.listView!![index])
                            .setOnClickListener {
                                block(it, popupWindowUtils)
                            }
                    }
                }
                //设置遮罩层
                popupWindowUtils.backgroundAlpha(transparency)
                popupWindowUtils.popupWindow!!.setOnDismissListener {
                    popupWindowUtils.backgroundAlpha(1f)
                    //**重点，清理掉浮动的遮罩层，否则使用转场动画的时候会有黑色背景，因为dismiss是隐藏不是销毁
                    mContext?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    onDismiss(popupWindowUtils)
                }
            }
            return popupWindowUtils
        }
    }


    /**
     * 外部点击事件
     */
    @SuppressLint("ClickableViewAccessibility")
    fun setExternalListener(block: (PopupWindow?) -> Unit) {
        popupWindow?.setTouchInterceptor { v, event ->
            if (event.y <= v.height && event.y > 0) {//PopupWindow内部的事件
                false
            } else {//PopupWindow外部的事件
                block(popupWindow)
            }
            false
        }
    }

    fun dismiss() {
        popupWindow?.dismiss()
        popupWindow = null
    }

    private fun backgroundAlpha(f: Float) {
        val attributesNew = mContext?.window?.attributes
        attributesNew?.alpha = f
        mContext?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        mContext?.window?.attributes = attributesNew
    }


}