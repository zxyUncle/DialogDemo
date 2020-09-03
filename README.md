# 弹出框-pop-dialog-toast

[![](https://jitpack.io/v/zxyUncle/DialogDemo.svg)](https://jitpack.io/#zxyUncle/DialogDemo)       
Gradle
-----
Step 1


     allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	dependencies {
	  implementation 'com.github.zxyUncle:DialogDemo:TAG'
	}
1、普通的Toast
-----   

     TToast.show("请输入正确的手机号")

[![](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/TToast.gif)](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/TToast.gif)

2、顶部弹出的Toast
-----
      ZToast.setColorI("#000000")//可选 设置弹出的颜色
      ZToast.showI(this,"网路错误")

[![](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/ZToast.gif)](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/ZToast.gif)

3、Dialog
-----
1）、系统自带的Dialog：

      AlertDialogUtils.build(this)
                .setValues("Title", "Content")
                .create { view, alertDialogUtils ->
                    when (view.id) {
                        com.zxy.zxydialog.R.id.tvDialogConfig -> {
                        }
                        com.zxy.zxydialog.R.id.tvDialogCancel -> {
                        }
                    }
                    alertDialogUtils.dismiss()
                }

[![](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/自带Dialog.gif)](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/自带Dialog.gif)

2）、自定义ViewDialog

      AlertDialogUtils.build(this)
                .setView(R.layout.dialog_curse)
                .create { view, alertDialogUtils ->}

[![](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/自定义ViewDialog.gif)](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/自定义ViewDialog.gif)

3）、Dialog的全部属性

      .setView(R.layout.dialog_curse)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(true) //可选                                   默认true
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel) //可选  Dialog中的点击事件
                .create { view, alertDialogUtils -> //必选                    点击事件的回调

4）可选的动画

    FOLD_B(R.style.zxy_fold_bottom),  //折叠动画Bottom
    FOLD_T(R.style.zxy_fold_top),  //折叠动画Top
    FOLD_T_NO_B(R.style.zxy_fold_top_no_bottom),  //折叠动画Top,没有退出动画
    ZOOM(R.style.zxy_zoom),  //中心缩放动画
    TRAN_B(R.style.zxy_translate_bottom),  //底部弹出动画
    TRAN_T(R.style.zxy_translate_top),  //顶部弹出动画
    TRAN_T_NO_B(R.style.zxy_translate_top_no_bottom)  //顶部弹出动画,没有退出动画


4、Popwindow
-----
1）最简单的使用相对于窗口的位置

      PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选
                .showAtLocation {view, popWindowUtils -> }

[![](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/窗口Pop.gif)](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/窗口Pop.gif)

2）最简单的使用相对于View的位置

     PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选
                .showAsDropDown(locationView) {view, popWindowUtils -> }

[![](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/ViewPop.gif)](https://github.com/zxyUncle/DialogDemo/tree/master/dialog/ViewPop.gif)

3)Popwindow的全部属性

     var mPop=PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选                              设置布局
                .setGravity(Gravity.TOP, 0, 0)//可选      设置方向及宽高偏移值,默认TOP
                .setTransparency(1f)//可选：                                      默认0.5    0为全黑  1全透明
                .setPopWidthHeight(PopWindowUtils.MATCH,PopWindowUtils.WRAP)//可选 默认w=Math，h=wrap
                .isFocusable(false)//可选：                            默认true
                .isTouchable(true)//可选：                             默认true
                .setAnimator(AnimatorEnum.FOLD_T_NO_B.VALUE)//可选:               默认AnimatorEnum.FOLD_B.VALUE
                .setTimer(3000)//可选                                             默认不倒计时自动销毁
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel)//可选：    默认没有点击事件
                .showAtLocation { view, popWindowUtils ->  //点击事件的回调：       popWindowUtils：通过他可以拿到任何东西

                }
                 //点击外部销毁Pop的监听
            mPop.setExternalListener {
                ZToast.showI(this,"外部点击")
            }

4）可选的动画

    FOLD_B(R.style.zxy_fold_bottom),  //折叠动画Bottom
    FOLD_T(R.style.zxy_fold_top),  //折叠动画Top
    FOLD_T_NO_B(R.style.zxy_fold_top_no_bottom),  //折叠动画Top,没有退出动画
    ZOOM(R.style.zxy_zoom),  //中心缩放动画
    TRAN_B(R.style.zxy_translate_bottom),  //底部弹出动画
    TRAN_T(R.style.zxy_translate_top),  //顶部弹出动画
    TRAN_T_NO_B(R.style.zxy_translate_top_no_bottom)  //顶部弹出动画,没有退出动画
