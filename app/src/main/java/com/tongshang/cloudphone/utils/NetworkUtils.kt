package com.tongshang.cloudphone.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    // 检查网络状态
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }
}
