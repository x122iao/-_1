package com.tongshang.cloudphone.data.service

import com.google.gson.JsonObject
import com.tongshang.cloudphone.data.model.BaseResponse
import com.tongshang.cloudphone.data.model.CurVersion
import com.tongshang.cloudphone.data.model.ShowSpeed
import com.tongshang.cloudphone.ui.view.fragment.DeviceListBean
import retrofit2.http.Body

import retrofit2.http.POST

/**
 * ApiService 接口定义了与服务器进行交互的 API 方法。
 */
interface ApiService {

//    /**
//     * 获取产品列表的 API 方法。
//     * @param params 请求参数，通常为产品筛选或搜索条件。
//     * @return BaseResponse<ProductListResponse> 包含产品列表的响应。
//     */
//    @POST("api/product/list")
//    suspend fun getProductList(@Body params: JsonObject): BaseResponse<ProductListResponse>
//



    @POST("api/versionManager/platform")
    suspend fun getVersion(@Body params: JsonObject): BaseResponse<CurVersion>


//    测试
    @POST("api/routeLink/showSpeed")
    suspend fun showSpeed(): BaseResponse<ShowSpeed>



    @POST("CloudDevice/fengzhushou/getDeviceList")
    suspend fun DeviceList(@Body params: JsonObject) : BaseResponse<DeviceListBean>
}
