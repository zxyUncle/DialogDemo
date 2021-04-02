package com.zxy.dialogdemo

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.zxy.zxydialog.PopWindowUtils
import com.zxy.zxydialog.tools.AnimatorEnum
import kotlinx.android.synthetic.main.activity_pop_window.*

/**
 * Created by zsf on 2021/4/1 14:38
 * ******************************************
 * *
 * ******************************************
 */
class PopWindowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_window)
        initView()
    }

    private fun initView() {
        btnTop.setOnClickListener {
            var popWindowUtils = PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.TOP, 0, 0)//可选     设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//可选：                                      默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_T_NO_B.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAtLocation({ view, pop ->

                }, {

                })

        }

        btnBottom.setOnClickListener {
            var popWindowUtils = PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.BOTTOM, 0, 0)//可选  设置方向及宽高偏移值,默认TOP
                .setTransparency(0.8f)//可选：                                     默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_B.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
//                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAtLocation({ view, pop ->

                }, {

                })

        }
        btnLeft.setOnClickListener {
            var popWindowUtils = PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.LEFT, 0, 0)//可选    设置方向及宽高偏移值,默认TOP
                .setTransparency(0.8f)//可选：                                     默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_L.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
//                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAtLocation({ view, pop ->

                }, {

                })

        }
        btnRight.setOnClickListener {
            var popWindowUtils = PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.RIGHT, 0, 0)//可选      设置方向及宽高偏移值,默认TOP
                .setTransparency(0.8f)//可选：                                      默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_R.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
//                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAtLocation({ view, pop ->

                }, {

                })

        }



        btnPopViewTop.setOnClickListener {
            var popWindowUtils = PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.TOP, 0, 0)//可选      设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//可选：                                      默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_T.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAsDropDown(it, { view, pop ->
                    {

                    }
                }, { //点击外部销毁Pop的监听

                })
        }
        btnPopViewBottom.setOnClickListener {
            var popWindowUtils = PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.BOTTOM, 0, 0)//可选      设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//可选：                                      默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_B.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
//                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAsDropDown(it, { view, pop ->
                    {

                    }
                }, {

                })
        }
        btnPopViewLeft.setOnClickListener {
            var popWindowUtils = PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.LEFT, 0, 0)//可选      设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//可选：                                      默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_L.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
//                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAsDropDown(it, { view, pop ->
                    {

                    }
                }, {

                })
        }

        btnPopViewRight.setOnClickListener {
            var popWindowUtils = PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.RIGHT, 0, 0)//可选      设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//可选：                                      默认0.5    0为全黑  1全透明
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_R.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
//                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAsDropDown(it, { view, pop ->
                    {

                    }
                }, {

                })

        }
    }
}