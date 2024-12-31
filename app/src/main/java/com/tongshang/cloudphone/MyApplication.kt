package com.tongshang.cloudphone

import android.app.Application
import android.content.Context
import com.moor.imkf.utils.YKFUtils
import com.sq.sdk.cloudgame.CloudSdkSuper
import com.tongshang.cloudphone.data.service.RetrofitInstance

class MyApplication : Application() {

    companion object {
        private lateinit var instance: MyApplication

        // 获取应用的上下文
        fun getApplication(): Context {
            return instance.applicationContext // 返回应用上下文
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this // 初始化应用实例

        // TODO:  是否同意协议弹窗 
//        if(){}
        
        RetrofitInstance.init(this) // 初始化 Retrofit 实例


        // TODO:  云手机 
        CloudSdkSuper.onAppCreate(this,BuildConfig.IS_DEBUG);//是否开启日志

        /**
         * 客服
         */
        YKFUtils.init(this)


    }
}
