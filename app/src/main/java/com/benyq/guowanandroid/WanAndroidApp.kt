package com.benyq.guowanandroid

import android.app.Application
import com.benyq.guowanandroid.net.RetrofitFactory
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.tencent.mmkv.MMKV

class WanAndroidApp : Application() {

    companion object {
        private lateinit var sInstance: WanAndroidApp
        fun getInstance() = sInstance
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        Logger.addLogAdapter(object : AndroidLogAdapter(
            PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .methodCount(3)
            .tag("benyq")
            .build()) {
            override fun isLoggable(priority: Int, tag: String?) = BuildConfig.DEBUG
        })

        MMKV.initialize(this)
        RetrofitFactory.init()
    }
}