package com.tongshang.cloudphone.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.tongshang.cloudphone.data.model.*
import com.tongshang.cloudphone.data.service.RetrofitInstance
import com.tongshang.cloudphone.ui.view.fragment.DeviceListBean
import com.tongshang.cloudphone.utils.NetworkUtils

/**
 * 产品仓库类，负责处理与产品相关的数据操作。
 * 主要通过 Retrofit 请求 API 获取数据，并处理网络异常。
 */
class ProductRepository {

    /**
     * 获取当前版本信息。
     *
     * @param context 上下文对象，用于检查网络连接状态。
     * @param params 请求参数，通常包含需要传递给接口的 JSON 数据。
     * @return 返回一个 `CurVersion` 对象，表示当前的版本信息。
     * @throws Exception 如果网络不可用或请求失败，抛出异常。
     */
    suspend fun getVersion(context: Context, params: JsonObject): CurVersion {
        // 检查网络是否可用
        if (!NetworkUtils.isNetworkAvailable(context)) {
            throw Exception("网络不可用")
        }

        return try {
            // 从 Retrofit 接口获取 BaseResponse 对象
            val response: BaseResponse<CurVersion> = RetrofitInstance.apiService.getVersion(params)
            // 使用扩展函数获取数据，若获取失败则抛出异常
            response.getDataOrThrow()
        } catch (e: Exception) {
            // 打印错误日志
            Log.e("ProductRepository", "获取下方横幅失败: ${e.message}")
            // 重新抛出异常
            throw e
        }
    }
    suspend fun getshowSpeed(context: Context): ShowSpeed {
        // 检查网络是否可用
        if (!NetworkUtils.isNetworkAvailable(context)) {
            throw Exception("网络不可用")
        }

        return try {
            // 从 Retrofit 接口获取 BaseResponse 对象
            val response: BaseResponse<ShowSpeed> = RetrofitInstance.apiService.showSpeed()
            // 使用扩展函数获取数据，若获取失败则抛出异常
            response.getDataOrThrow()
        } catch (e: Exception) {
            // 打印错误日志
            Log.e("ProductRepository", "获取下方横幅失败: ${e.message}")
            // 重新抛出异常
            throw e
        }
    }



    suspend fun getDeviceList(context: Context, params: JsonObject): DeviceListBean {
        // 检查网络是否可用
        if (!NetworkUtils.isNetworkAvailable(context)) {
            throw Exception("网络不可用")
        }

        return  try {
            val response : BaseResponse<DeviceListBean> = RetrofitInstance.apiService.DeviceList(params)
            response.getDataOrThrow()
        }catch (e : Exception){
            // 打印错误日志
            Log.e("ProductRepository", "获取下方横幅失败: ${e.message}")
            // 重新抛出异常
            throw e
        }
    }
}
