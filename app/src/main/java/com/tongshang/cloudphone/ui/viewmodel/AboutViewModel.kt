package com.tongshang.cloudphone.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.sq.sdk.cloudgame.Log
import com.tongshang.cloudphone.data.model.CurVersion
import com.tongshang.cloudphone.data.repository.NetworkManager
import kotlinx.coroutines.launch

/**
 * AboutViewModel 用于处理关于页面的业务逻辑，主要是获取当前版本的信息。
 *
 * @param networkManager 网络管理类实例，用于发起网络请求。
 */
class AboutViewModel(private val networkManager: NetworkManager) : ViewModel() {

    // 用于存储当前版本信息的 LiveData
    private val _productList = MutableLiveData<CurVersion>()

    // 对外暴露的不可变 LiveData
    val curVersion: LiveData<CurVersion> get() = _productList


    /**
     * 获取当前版本信息的函数。
     * 使用 `viewModelScope.launch` 来启动协程进行异步操作。
     *
     * @param context 上下文，用于检查网络连接等。
     * @param params 请求的参数，包含必要的参数信息。
     */
    fun fetchCurVersion(context: Context, params: JsonObject) {
        // 启动协程
        viewModelScope.launch {
            try {
                // 发起网络请求，获取版本数据
                val data = networkManager.fetchCurVersionAsync(context, params)
                // 请求成功，更新 LiveData 的值
                _productList.value = data
            } catch (e: Exception) {
                // 错误处理，通常这里会记录日志或通知用户出错信息
                Log.e("AboutViewModel", "获取当前版本失败: ${e.message}")
            }
        }
    }
}
