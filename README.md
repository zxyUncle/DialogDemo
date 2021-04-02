# Dialog+BottomSheetDialog+加载中动画+Toast+Popwindow
## 效果图
[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/78dbf64ec74943b9b8319670b5dca969~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/全部.gif)

[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e2ac4260224f41f89c9545211c654936~tplv-k3u1fbpfcp-zoom-1.image)](https://jitpack.io/#zxyUncle/DialogDemo)
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
	  implementation 'com.github.zxyUncle:DialogDemo:latest.release'
	}
1、普通的Toast
-----

     TToast.show("请输入正确的手机号")

     TToast.show(layoutView)//自定布局

[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/78dbf64ec74943b9b8319670b5dca969~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/TToast.gif)

2、顶部弹出的Toast
-----
      ZToast.setColorI("#000000")//可选 设置弹出的颜色
      ZToast.showI(this,"网路错误")

[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/bf9d695a3e534625a0c7516aef7d6e2b~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/ZToast.gif)

3、加载中弹出框
-----


        LoadingTool.show(this,"加载中...") //显示
        LoadingTool.hide()//隐藏


[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1855d2890d694b388ecbb273b8128a1b~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/loading.gif)

4、Dialog
-----
1）、系统自带的Dialog：

      var alertDialogUtils = AlertDialogUtils.build(this)
                .setValues("Title", "Content")
                .create { view, alertDialogUtils ->
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                        }
                        R.id.tvDialogCancel -> {
                        }
                    }
                    alertDialogUtils.dismiss()
                }

[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/546331953fbc405cbb0e4f85bcd95345~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/自带Dialog.gif)

2）、自定义ViewDialog最简单写法

      AlertDialogUtils.build(this)
                .setView(R.layout.dialog_curse)
                .create { view, alertDialogUtils ->}

[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/45d14747f2fb4a42aa9b3f0e0eba48a2~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/自定义ViewDialog.gif)

3）、自定义ViewDialog全部属性

     val alertDialogUtils = AlertDialogUtils.build(this)
                .setView(R.layout.dialog_curse)//必选                         自定义布局的View
                .setTransparency(0.2f)//可选                                  默认0.2f
                .setCancelable(true) //可选                                   默认true
                .isShowKeyboard(R.id.tvDialgContent)
                .setAnimator(AnimatorEnum.TRAN_T.VALUE)//可选，               默认AnimatorEnum.ZOOM.VALUE
                .setOnClick(R.id.tvDialogConfig, R.id.tvDialogCancel) //可选  Dialog中的点击事件
                .create { view, alertDialogUtils -> //必选                    点击事件的回调
                    when (view.id) {
                        R.id.tvDialogConfig -> {
                            alertDialogUtils.dismiss()

                        }
                        R.id.tvDialogCancel -> {
                            alertDialogUtils.dismiss()
                            TToast.show("tvDialogCancel")
                        }
                    }
                }

4）可选的动画

    FOLD_B(R.style.zxy_fold_bottom),  //折叠动画Bottom
    FOLD_T(R.style.zxy_fold_top),  //折叠动画Top
    FOLD_L(R.style.zxy_fold_left),  //折叠动画Left
    FOLD_R(R.style.zxy_fold_right),  //折叠动画Right
    FOLD_T_NO_B(R.style.zxy_fold_top_no_bottom),  //折叠动画Top,没有退出动画
    ZOOM(R.style.zxy_zoom),  //中心缩放动画
    TRAN_B(R.style.zxy_translate_bottom),  //底部弹出动画
    TRAN_T(R.style.zxy_translate_top),  //顶部弹出动画
    TRAN_T_NO_B(R.style.zxy_translate_top_no_bottom)  //顶部弹出动画,没有退出动画


5、Popwindow
-----
`注意点：如果要对Popwindow的布局进行固定的dp大小设置，就必须在外层再包裹一层布局如：`

![image.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b17b376fac2441428690f697389adc71~tplv-k3u1fbpfcp-watermark.image)

1）相对于窗口的位置的最简单的使用

      PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选
                .showAtLocation({ view, popWindowUtils ->
                    //点击事件的回调：       popWindowUtils：通过他可以拿到任何东西

                }, {//销毁回调


                })

[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c2196cd041c54b58acaf9fefeabaf016~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/窗口Pop.gif)

相对于窗口的位置的全部的属性

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

                }, {//外部关闭回调

                })




2）相对于View的位置的最简单的使用

     PopWindowUtils.build(this)
                .setView(R.layout.pop_curse) //必选
                 .showAsDropDown(btnPopWindows, { view: View, popWindowUtils: PopWindowUtils ->//点击事件的回调

                }, {//外部销毁的回调

                })

[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e5adddab2ece4490b5ba1f5a4d38f08e~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/ViewPop.gif)

3)相对于View的位置的全部属性

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

4）可选的动画

    FOLD_B(R.style.zxy_fold_bottom),  //折叠动画Bottom
    FOLD_T(R.style.zxy_fold_top),  //折叠动画Top
    FOLD_L(R.style.zxy_fold_left),  //折叠动画Left
    FOLD_R(R.style.zxy_fold_right),  //折叠动画Right
    FOLD_T_NO_B(R.style.zxy_fold_top_no_bottom),  //折叠动画Top,没有退出动画
    ZOOM(R.style.zxy_zoom),  //中心缩放动画
    TRAN_B(R.style.zxy_translate_bottom),  //底部弹出动画
    TRAN_T(R.style.zxy_translate_top),  //顶部弹出动画
    TRAN_T_NO_B(R.style.zxy_translate_top_no_bottom)  //顶部弹出动画,没有退出动画

6、BottomSheetDialog底部弹出框，可以滑动（弹出框的根布局必须是-线性布局）
-----
       BottomSheetDialogUtils.build(this)
                .setView(R.layout.zxy_bottom_sheet_dialog)
                .setMinHeight(0.8f)//弹出框的占屏幕的高度比例
                .show()
[![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/aad5105b37464849b8d32f37fcfbffd6~tplv-k3u1fbpfcp-zoom-1.image)](https://raw.githubusercontent.com/zxyUncle/DialogDemo/master/dialog/bottomdialog.gif)




