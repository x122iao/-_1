package com.tongshang.cloudphone.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.sq.sdk.cloudgame.Log
import com.tongshang.cloudphone.data.repository.NetworkManager
import com.tongshang.cloudphone.ui.view.fragment.DeviceListBean
import kotlinx.coroutines.launch

/**
 * @author: z2660
 * @date: 2024/12/31
 */
class EquipmentViewModel(private val networkManager: NetworkManager) : ViewModel() {

    private val _deviceList  = MutableLiveData<List<DeviceListBean>>()
    val deviceList: LiveData<List<DeviceListBean>> get() = _deviceList

    fun getDeviceList(context: Context, params: JsonObject) {
        viewModelScope.launch {
            try {
                val data = networkManager.getDeviceList(context, params)
                _deviceList.value = listOf(data)
            } catch (e: Exception) {
                Log.e("EquipmentViewModel", "获取设备列表：${e.message}")
            }
        }
    }
}
