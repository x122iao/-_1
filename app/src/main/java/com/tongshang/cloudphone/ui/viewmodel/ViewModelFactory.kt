package com.tongshang.cloudphone.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tongshang.cloudphone.data.repository.NetworkManager

/**
 * ViewModelFactory 用于创建 ProductViewModel 的实例。
 * ViewModelFactory 是用于创建 ViewModel 的工厂类，它需要为 ViewModel 提供必要的参数。
 *
 * @param networkManager 网络管理类的实例，用于提供网络请求功能。
 */
class ProductViewModelFactory(private val networkManager: NetworkManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CertificationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CertificationViewModel(networkManager) as T
        }
        if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AboutViewModel(networkManager) as T
        }
        if (modelClass.isAssignableFrom(EquipmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EquipmentViewModel(networkManager) as T
        }

        // 如果 ViewModel 类型未知，抛出异常
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

