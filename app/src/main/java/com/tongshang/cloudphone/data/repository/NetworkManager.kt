package com.tongshang.cloudphone.data.repository

import android.content.Context
import com.google.gson.JsonObject
import com.tongshang.cloudphone.data.model.CurVersion
import com.tongshang.cloudphone.data.model.ShowSpeed
import com.tongshang.cloudphone.ui.view.fragment.DeviceListBean

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
/**
 * 网络请求管理类，负责调用数据仓库中的方法进行网络请求。
 * 使用协程实现异步操作，确保网络请求在后台线程执行，避免阻塞主线程。
 *
 * @param productRepository 提供产品相关数据操作的仓库，
 */
class NetworkManager(private val productRepository: ProductRepository) {



    // 获取下方横幅的 suspend 函数
    suspend fun fetchCurVersionAsync(context: Context,params: JsonObject): CurVersion {
        return withContext(Dispatchers.IO) {
            productRepository.getVersion(context,params )
        }
    }
//    测试
    suspend fun showSpeedAsync(context: Context): ShowSpeed {
        return withContext(Dispatchers.IO) {
            productRepository.getshowSpeed(context)
        }
    }

    suspend fun getDeviceList(context: Context,params: JsonObject) : DeviceListBean{
        return withContext(Dispatchers.IO) {
            productRepository.getDeviceList(context,params)
        }

    }


}