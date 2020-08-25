package com.zxy.zxydialog

import android.annotation.SuppressLint
import android.app.Activity
import android.os.CountDownTimer
import android.view.*
import android.widget.PopupWindow


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
        fun build(mContext: Activity): PopWindowUtils.Builder {
            return PopWindowUtils.Builder(mContext)
        }
    }

    class Builder(mContext: Activity) {
        var mContext = mContext
        var popupWindowUtils = PopWindowUtils()
        var isTouchable = true
        var isFocusable = true
        var isOutsideTouchable = false
        var animator: Int? = null
        var gravity: Int? = null
        var transparency: Float = 0.8f
        var millisInFuture: Long? = null

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
        fun setView(layoutId: Int): PopWindowUtils.Builder {
            popupWindowUtils.layoutView = LayoutInflater.from(mContext).inflate(layoutId, null)
            return this
        }

        fun isTouchable(isTouchable: Boolean): PopWindowUtils.Builder {
            this.isTouchable = isTouchable
            return this
        }

        fun isFocusable(isFocusable: Boolean): PopWindowUtils.Builder {
            this.isFocusable = isFocusable
            return this
        }

        fun isOutsideTouchable(isOutsideTouchable: Boolean): PopWindowUtils.Builder {
            this.isOutsideTouchable = isOutsideTouchable
            return this
        }

        fun setAnimator(animator: Int): PopWindowUtils.Builder {
            this.animator = animator
            return this
        }

        /**
         * 设置窗口透明度，默认0.5    0为全透明  1全黑
         * @param transparency Float
         * @return Builder
         */
        fun setTransparency(transparency: Float): PopWindowUtils.Builder {
            this.transparency = transparency
            return this
        }

        fun setGravity(gravity: Int): PopWindowUtils.Builder {
            this.gravity = gravity
            return this
        }

        /**
         * 设置点击事件
         * @param viewId IntArray
         */
        fun setOnClick(vararg viewId: Int): PopWindowUtils.Builder {
            popupWindowUtils.listView = viewId.toTypedArray().toMutableList()
            return this
        }

        /**
         * 外部点击事件
         */
        @SuppressLint("ClickableViewAccessibility")
        fun setExternalListener(block: () -> Unit) {
            popupWindowUtils.popupWindow?.setTouchInterceptor { v, event ->
                if (event.y >= 0) {//PopupWindow内部的事件
                    false
                } else {//PopupWindow外部的事件
                    block()
                }
                false
            }

        }

        fun setTimer(millisInFuture: Long): PopWindowUtils.Builder {
            this.millisInFuture = millisInFuture
            return this
        }


        @SuppressLint("ClickableViewAccessibility")
        fun create(viewLocation: View, block: (View, PopWindowUtils) -> Unit) {
            popupWindowUtils.mContext = mContext
            popupWindowUtils.popupWindow = PopupWindow(
                popupWindowUtils.layoutView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            popupWindowUtils.popupWindow!!.isTouchable = isTouchable
            popupWindowUtils.popupWindow!!.isFocusable = isFocusable
            popupWindowUtils.popupWindow!!.animationStyle = animator ?: AnimatorEnum.TRAN_B.VALUE

            popupWindowUtils.popupWindow!!.isOutsideTouchable = isOutsideTouchable

            popupWindowUtils.popupWindow!!.showAsDropDown(
                viewLocation,
                0,
                0,
                gravity !!
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
            }

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