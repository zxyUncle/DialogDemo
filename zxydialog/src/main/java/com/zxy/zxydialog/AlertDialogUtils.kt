package com.zxy.zxydialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.zxy.zxydialog.tools.AnimatorEnum
import kotlinx.android.synthetic.main.zxy_alert_dialog.view.*


/**
 * Created by zxy on 2019/8/24-10:13
 * Class functions
 * ******************************************
 * * 建造者模式
 * * 自定义万能布局AletDialog
 * ******************************************
 */
class AlertDialogUtils private constructor() {
    var layoutView: View? = null                           //Dialog的布局文件
    private var cancelable: Boolean = true                          //是否可以取消  true可以
    lateinit var dialog: AlertDialog                        // AlertDilaog
    var alertDilaogBuilder: AlertDialog.Builder? = null    // AlertDilaog.Builder
    var listView: MutableList<Int>? = null
    var transparency: Float = 0.5f                              // 透明度


    companion object {
        @JvmStatic
        fun build(mContext: Context): Builder {
            return Builder(mContext)
        }
    }

    class Builder(mContext: Context) {
        var mContext = mContext
        var alertDialogUtils = AlertDialogUtils()
        var animator: Int? = null
        var title: String? = null
        var content: String? = null
        var editTextId: Int? = null

        /**
         * 设置布局
         * @param layoutView View
         */
        fun setView(layoutId: Int): Builder {
            alertDialogUtils.layoutView = LayoutInflater.from(mContext).inflate(layoutId, null)
            return this
        }

        /**
         * 设置布局
         * @param layoutView View
         */
        fun setView(layoutView: View): Builder {
            alertDialogUtils.layoutView = layoutView
            return this
        }

        /**
         *
         * 是否弹出键盘
         * @param editTextId 光标位置
         */
        @Deprecated("过期，新方法setEditFocus")
        fun isShowKeyboard(editTextId: Int): Builder {
            this.editTextId = editTextId
            return this
        }

        /**
         * 是否弹出键盘
         * @param editTextId 光标位置
         */
        fun setEditFocus(editTextId: Int): Builder {
            this.editTextId = editTextId
            return this
        }



        /**
         * 设置点击事件
         * @param viewId IntArray
         */
        fun setOnClick(vararg viewId: Int): Builder {
            alertDialogUtils.listView = viewId.toTypedArray().toMutableList()
            return this
        }

        /**
         * 设置销毁的事件
         * @param viewId IntArray
         */
        fun setOnDismissListener(listener: DialogInterface.OnDismissListener ): Builder {
           alertDialogUtils.dialog.setOnDismissListener(listener)
            return this
        }

        /**
         * 设置销毁的事件
         * @param viewId IntArray
         */
        fun setOnCancelListener(listener: DialogInterface.OnCancelListener ): Builder {
            alertDialogUtils.dialog.setOnCancelListener(listener)
            return this
        }

        /**
         * 是否可以取消  默认true可以
         * @param cancelable Boolean
         */
        fun setCancelable(cancelable: Boolean): Builder {
            alertDialogUtils.cancelable = cancelable
            return this
        }

        /**
         * 设置窗口透明度，默认0.5    0为全透明  1全黑
         * @param transparency Float
         * @return Builder
         */
        fun setTransparency(transparency: Float): Builder {
            alertDialogUtils.transparency = transparency
            return this
        }

        fun setAnimator(animator: Int): Builder {
            this.animator = animator
            return this
        }

        fun setValues(title: String, content: String): Builder {
            this.title = title
            this.content = content
            return this
        }

        /**
         * 创建自定义布局的AlertDialog
         */
        fun create(callBack:( (View, AlertDialogUtils) -> Unit)={ _: View, _: AlertDialogUtils -> }): AlertDialogUtils {
            if (alertDialogUtils.alertDilaogBuilder != null) {
                alertDialogUtils.dismiss()
            }
            alertDialogUtils.alertDilaogBuilder = AlertDialog.Builder(mContext, R.style.zxy_MyDilog)
            if (alertDialogUtils.layoutView == null) {//自带的dialog
                setView(R.layout.zxy_alert_dialog)
                setOnClick(R.id.tvDialogCancel, R.id.tvDialogConfig)
                alertDialogUtils.layoutView?.tvDialgTitle?.text = title
                alertDialogUtils.layoutView?.tvDialgContent?.text = content
            }
            alertDialogUtils?.alertDilaogBuilder?.setView(alertDialogUtils.layoutView)
            alertDialogUtils.dialog = alertDialogUtils?.alertDilaogBuilder?.create()!!
            alertDialogUtils.dialog.setCancelable(alertDialogUtils.cancelable)
            //设置动画
            var window = alertDialogUtils.dialog.window
            var layoutParams = window?.attributes
            layoutParams?.windowAnimations = animator ?: AnimatorEnum.ZOOM.VALUE
            window?.attributes = layoutParams
            alertDialogUtils.dialog.show()

            if (editTextId != null) {
                alertDialogUtils.layoutView?.postDelayed({
                    showKeyboard(alertDialogUtils.layoutView?.findViewById(editTextId!!))
                }, 100)
            }

            val lp = alertDialogUtils.dialog.window!!.attributes
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            alertDialogUtils.dialog.window!!.setDimAmount(alertDialogUtils.transparency)//设置黑色遮罩层的透明度
            alertDialogUtils.dialog.window!!.attributes = lp

            if (alertDialogUtils.listView != null) {
                for (index in alertDialogUtils.listView!!) {
                    alertDialogUtils.layoutView!!.findViewById<View>(index)
                        .setOnClickListener {
                            callBack(it, alertDialogUtils)
                        }
                }
            }
            return alertDialogUtils
        }

        fun cancel() {
            if (alertDialogUtils.dialog != null)
                alertDialogUtils.dialog!!.cancel()
        }

        //弹出软键盘
        private fun showKeyboard(editText: EditText?) {
            //其中editText为dialog中的输入框的 EditText
            if (editText != null) {
                //设置可获得焦点
                editText.isFocusable = true
                editText.isFocusableInTouchMode = true
                //请求获得焦点
                editText.requestFocus()
                //调用系统输入法
                val inputManager: InputMethodManager =
                    mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(editText, 0)
            }
        }
    }


    fun dismiss() {
        if (dialog != null)
            dialog!!.cancel()
        if (alertDilaogBuilder != null)
            alertDilaogBuilder = null
    }


}