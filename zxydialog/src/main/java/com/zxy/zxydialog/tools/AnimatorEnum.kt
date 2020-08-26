package com.zxy.zxydialog.tools

import com.zxy.zxydialog.R

/**
 * Created by zxy on 2020/8/25 17:04
 * ******************************************
 * * 动画枚举
 * ******************************************
 */
enum class AnimatorEnum(val VALUE: Int) {
    FOLD_B(R.style.zxy_fold_bottom),  //折叠动画Bottom
    FOLD_T(R.style.zxy_fold_top),  //折叠动画Top
    FOLD_T_NO_B(R.style.zxy_fold_top_no_bottom),  //折叠动画Top,没有退出动画
    ZOOM(R.style.zxy_zoom),  //中心缩放动画
    TRAN_B(R.style.zxy_translate_bottom),  //底部弹出动画
    TRAN_T(R.style.zxy_translate_top),  //顶部弹出动画
    TRAN_T_NO_B(R.style.zxy_translate_top_no_bottom)  //顶部弹出动画,没有退出动画
}