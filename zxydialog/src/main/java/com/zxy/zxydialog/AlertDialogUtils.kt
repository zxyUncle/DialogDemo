package com.zxy.zxydialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.zxy.zxydialog.tools.AnimatorEnum
import com.zxy.zxydialog.tools.Applications
import com.zxy.zxydialog.tools.CountDownTimerManager
import com.zxy.zxydialog.tools.MyLifecycleActImp
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
    var dialog: MyDialog? = null                        // AlertDilaog
    var listView: MutableList<Int>? = null
    var transparency: Float = 0.5f                              // 透明度
    var fullScreen: Boolean = false
    var touchOutside: Boolean = false                     //是否可以触摸外部
    var onDispatchTouchEvent: OnDispatchTouchEvent? = null
    lateinit var mContext: ComponentActivity

    companion object {
        /**
         * 必须是ComponentActivity 子类
         * 可以是AppCompatActivity、FragmentActivity等
         */
        @JvmStatic
        fun build(mContext: ComponentActivity): Builder {
            return Builder(mContext)
        }
    }

    interface OnDispatchTouchEvent {
        fun dispatchTouchEvent(ev: MotionEvent)
    }

    class MyDialog : Dialog {
        var alertDialogUtils: AlertDialogUtils

        constructor(context: Context, themeResId: Int, alertDialogUtils: AlertDialogUtils) : super(
            context, themeResId
        ) {
            this.alertDialogUtils = alertDialogUtils
        }

        override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
            alertDialogUtils.onDispatchTouchEvent.let {
                alertDialogUtils.onDispatchTouchEvent?.dispatchTouchEvent(ev)
            }
            fullScreenShow()
            return super.dispatchTouchEvent(ev)
        }

        override fun show() {
            if (alertDialogUtils.fullScreen)
                this.window?.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                )
            super.show()
            fullScreenShow()
        }

        fun fullScreenShow() {
            if (alertDialogUtils.fullScreen)
                alertDialogUtils.bottomNavInVisible()
        }
    }

    class Builder {
        var mContext: ComponentActivity
        var alertDialogUtils: AlertDialogUtils
        var animator: Int? = null
        var title: String? = null
        var content: String? = null
        var editTextId: Int? = null

        var onDismissListener: DialogInterface.OnDismissListener? = null
        var onCancelListener: DialogInterface.OnCancelListener? = null


        constructor(context: ComponentActivity) {
            alertDialogUtils = AlertDialogUtils()
            alertDialogUtils.mContext = context
            mContext = context
            onLifecycleListener()
        }

        private fun onLifecycleListener() {
            val myLocationListener =
                MyLifecycleActImp(object : MyLifecycleActImp.LifecycleListener {
                    override fun onResult() {
                        alertDialogUtils!!.dismiss()
                    }
                })
            mContext.lifecycle.addObserver(myLocationListener)
        }

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

        fun setFullScreen(fullScreen: Boolean): Builder {
            alertDialogUtils.fullScreen = fullScreen
            return this
        }

        //是否可以触摸外部
        fun setCanceledOnTouchOutside(touchOutside: Boolean): Builder {
            alertDialogUtils.touchOutside = touchOutside
            return this
        }

        /**
         * 扫码、全屏等监听
         */
        fun OnDispatchTouchEvent(onDispatchTouchEvent: OnDispatchTouchEvent): Builder {
            alertDialogUtils.onDispatchTouchEvent = onDispatchTouchEvent
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
        fun setOnDismissListeners(listener: DialogInterface.OnDismissListener): Builder {
            onDismissListener = listener
            return this
        }

        /**
         * 设置销毁的事件
         * @param viewId IntArray
         */
        fun setOnCancelListeners(listener: DialogInterface.OnCancelListener): Builder {
            onCancelListener = listener
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

        fun setValues(title: Int, content: Int): Builder {
            this.title = Applications.context().resources.getString(title)
            this.content = Applications.context().resources.getString(content)
            return this
        }

        /**
         * 倒计时
         * @param time 单位秒
         */
        fun setTimer(time: Long, callBack: (Int, AlertDialogUtils) -> Unit): Builder {
            CountDownTimerManager.getInstance()
                .startCountDownTimer(time, object : CountDownTimerManager.CountDownTimerListener {
                    override fun onTick(second: Int) {
                        callBack(second, alertDialogUtils)
                    }

                    override fun onFinish() {
                        alertDialogUtils.dismiss()
                    }
                })
            return this
        }

        /**
         * Dilaog 创建完成显示
         */
        fun show(callBack: ((AlertDialogUtils) -> Unit) = {}): AlertDialogUtils {
            if (alertDialogUtils.dialog == null) {
                OnClickListener()
            }
            callBack(alertDialogUtils)
            return alertDialogUtils
        }

        fun show(): AlertDialogUtils {
            if (alertDialogUtils.dialog == null) {
                OnClickListener()
            }
            return alertDialogUtils
        }


        /**
         * 创建自定义布局的AlertDialog
         */
        fun OnClickListener(callBack: ((View, AlertDialogUtils) -> Unit) = { _: View, _: AlertDialogUtils -> }): Builder {
            callBack(callBack)
            return this
        }

        /**
         * 创建自定义布局的AlertDialog
         */
        @Deprecated("过期，使用OnClickListener")
        fun create(callBack: ((View, AlertDialogUtils) -> Unit) = { _: View, _: AlertDialogUtils -> }): AlertDialogUtils {
            callBack(callBack)
            return alertDialogUtils
        }

        private fun callBack(callBack: ((View, AlertDialogUtils) -> Unit) = { _: View, _: AlertDialogUtils -> }) {
            if (!mContext.isDestroyed) {
                if (alertDialogUtils.dialog != null) {
                    alertDialogUtils.dismiss()
                }
                alertDialogUtils.dialog = MyDialog(
                    mContext,
                    R.style.zxy_MyDilog,
                    alertDialogUtils
                )
                if (alertDialogUtils.layoutView == null) {//自带的dialog
                    setView(R.layout.zxy_alert_dialog)
                    setOnClick(R.id.tvDialogCancel, R.id.tvDialogConfig)
                    alertDialogUtils.layoutView?.tvDialgTitle?.text = title
                    alertDialogUtils.layoutView?.tvDialgContent?.text = content
                }
                alertDialogUtils.dialog?.setContentView(alertDialogUtils.layoutView!!)
                alertDialogUtils.dialog?.setCancelable(alertDialogUtils.cancelable)
                alertDialogUtils.dialog?.setCanceledOnTouchOutside(alertDialogUtils.touchOutside)
                //设置动画
                val window = alertDialogUtils.dialog?.window
                val layoutParams = window?.attributes
                layoutParams?.windowAnimations = animator ?: AnimatorEnum.ZOOM.VALUE
                window?.attributes = layoutParams

                alertDialogUtils.dialog?.fullScreenShow()
                alertDialogUtils.dialog?.show()

                if (editTextId != null) {
                    alertDialogUtils.layoutView?.postDelayed({
                        showKeyboard(alertDialogUtils.layoutView?.findViewById(editTextId!!))
                    }, 100)
                }

                val lp = alertDialogUtils.dialog?.window!!.attributes
                lp.width = WindowManager.LayoutParams.MATCH_PARENT
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT
                alertDialogUtils.dialog?.window!!.setDimAmount(alertDialogUtils.transparency)//设置黑色遮罩层的透明度
                alertDialogUtils.dialog?.window!!.attributes = lp

                if (alertDialogUtils.listView != null) {
                    for (index in alertDialogUtils.listView!!) {
                        alertDialogUtils.layoutView!!.findViewById<View>(index)
                            .setOnClickListener {
                                if (!mContext.isDestroyed) {
                                    callBack(it, alertDialogUtils)
                                } else {
                                    Log.e("", "当前activity已销毁")
                                }
                            }
                    }
                }

                if (onCancelListener != null) {
                    alertDialogUtils.dialog?.setOnCancelListener(onCancelListener)
                }
                if (onDismissListener != null) {
                    alertDialogUtils.dialog?.setOnDismissListener(onDismissListener)
                }
            } else {
                Log.e("", "当前activity已销毁")
            }
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

    fun bottomNavInVisible() {
        //隐藏虚拟按键，并且全屏
        var decorView = dialog?.window?.decorView
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            var decorView = dialog?.window?.decorView
            decorView?.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)
        }
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }


    fun dismiss() {
        if (dialog != null) {
            dialog!!.cancel()
            dialog = null
        }
    }


}