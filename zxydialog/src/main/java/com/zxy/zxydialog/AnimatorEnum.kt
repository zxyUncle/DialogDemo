package com.zxy.zxydialog

/**
 * Created by zxy on 2020/8/25 17:04
 * ******************************************
 * * 动画枚举
 * ******************************************
 */
enum class AnimatorEnum(val VALUE: Int) {
    FOLD(R.style.zxy_fold),  //折叠动画
    ZOOM(R.style.zxy_zoom),  //中心缩放动画
    TRAN_B(R.style.zxy_translate_bottom),  //底部弹出动画
    TRAN_T(R.style.zxy_translate_top)  //顶部弹出动画
}