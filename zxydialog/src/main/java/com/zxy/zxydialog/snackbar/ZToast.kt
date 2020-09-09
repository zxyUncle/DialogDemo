package com.zxy.zxydialog.snackbar

import android.app.Activity

/**
 * Created by zxy on 2020/1/7 0007 13:40
 * ******************************************
 * *
 * ******************************************
 */
class ZToast {
    companion object {
        private var colorI: Int? = null
        private var colorS: Int? = null
        private var colorE: Int? = null

        fun setColorI(color: String) {
            colorI = android.graphics.Color.parseColor(color)
        }

        fun setColorS(color: String) {
            colorS = android.graphics.Color.parseColor(color)
        }

        fun setColorE(color: String) {
            colorE = android.graphics.Color.parseColor(color)
        }

        fun getColorI(): Int {
            return colorI ?: Prompt.WARNING.backgroundColor
        }

        fun getColorE(): Int {
            return colorE ?: Prompt.ERROR.backgroundColor
        }

        fun getColorS(): Int {
            return colorS ?: Prompt.SUCCESS.backgroundColor
        }


        fun showI(mContext: Activity, message: String) {
            val rootView = mContext.window.decorView.rootView
            var snackBar = TSnackbar.make(
                rootView, message, TSnackbar.LENGTH_SHORT,
                TSnackbar.APPEAR_FROM_TOP_TO_DOWN
            )
            snackBar.setPromptThemBackground(Prompt.WARNING)
            snackBar.show()
        }

        fun showS(mContext: Activity, message: String) {
            val rootView = mContext.window.decorView.rootView
            var snackBar = TSnackbar.make(
                rootView, message, TSnackbar.LENGTH_SHORT,
                TSnackbar.APPEAR_FROM_TOP_TO_DOWN
            )
            snackBar.setPromptThemBackground(Prompt.SUCCESS)
            snackBar.show()
        }

        fun showE(mContext: Activity, message: String) {
            val rootView = mContext.window.decorView.rootView
            var snackBar = TSnackbar.make(
                rootView, message, TSnackbar.LENGTH_SHORT,
                TSnackbar.APPEAR_FROM_TOP_TO_DOWN
            )
            snackBar.setPromptThemBackground(Prompt.ERROR)
            snackBar.show()
        }
    }
}