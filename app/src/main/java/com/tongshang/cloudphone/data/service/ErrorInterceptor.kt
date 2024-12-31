package com.tongshang.cloudphone.data.service

import okhttp3.Interceptor
import okhttp3.Response
import com.tongshang.cloudphone.data.exception.ApiException

/**
 * 自定义拦截器，用于捕获 HTTP 请求中的错误响应。
 * 如果服务器返回的响应状态码不成功，则抛出自定义的 `ApiException` 异常。
 */
class ErrorInterceptor : Interceptor {

    /**
     * 拦截每一个请求，检查响应的状态码，如果请求失败则抛出异常。
     *
     * @param chain 请求链，允许继续执行请求。
     * @return 返回原始响应，若请求成功。
     * @throws ApiException 如果响应不成功（状态码不是 2xx），则抛出 ApiException 异常。
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // 执行请求并获取响应
        val response = chain.proceed(chain.request())

        // 检查响应是否成功，如果状态码不是 2xx 范围内，则认为请求失败
        if (!response.isSuccessful) {
            // 抛出自定义异常，包含状态码和错误信息
            throw ApiException(response.code, "网络错误: ${response.code}")
        }

        // 如果响应成功，返回原始响应
        return response
    }
}
