package com.tongshang.cloudphone.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tongshang.cloudphone.data.repository.NetworkManager
import kotlinx.coroutines.launch

/**
 * @author: z2660
 * @date: 2024/12/11
 */
class CertificationViewModel(private val networkManager: NetworkManager) : ViewModel() {

    private val _speedData = MutableLiveData<List<Int>>()
    val speedData: LiveData<List<Int>> get() = _speedData

    // 调用 showSpeed API
    fun fetchSpeedData(context: Context) {
        viewModelScope.launch {
            try {
                val response = networkManager.showSpeedAsync(context)
                _speedData.value = response.speed // 将获取的速度数据传递给 UI
            } catch (e: Exception) {
                _speedData.value = emptyList() // 如果失败，传递空列表
            }
        }
    }
}

