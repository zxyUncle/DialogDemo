package com.zxy.zxydialog.tools

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by zsf on 2022/9/5 12:36
 * ******************************************
 * *
 * ******************************************
 */
class MyLifecycleActImp : LifecycleObserver {
    var lifecycleListener: LifecycleListener
    constructor(lifecycleListener: LifecycleListener){
        this.lifecycleListener = lifecycleListener
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDistroy() {
        lifecycleListener.onResult()
    }

    interface LifecycleListener{
        fun onResult()
    }
}