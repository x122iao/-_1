package com.tongshang.cloudphone.data.service

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 自定义的头部拦截器，用于在每个请求中添加自定义的 HTTP 头部。
 * 可以用于动态地为每个请求添加认证信息、语言设置、用户代理等自定义头部。
 *
 * @param headers 要添加的头部键值对的集合。例如：{"Authorization" to "Bearer token", "User-Agent" to "App/1.0"}。
 */
class HeaderInterceptor(private val headers: Map<String, String>) : Interceptor {

    /**
     * 拦截 HTTP 请求并在请求中添加自定义头部。
     *
     * @param chain 请求链，允许继续执行请求。
     * @return 返回添加了自定义头部的响应。
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // 获取原始请求对象
        val originalRequest = chain.request()

        // 创建一个新的请求构建器，基于原始请求
        val builder = originalRequest.newBuilder()

        // 遍历并添加所有的自定义头部
        headers.forEach { (key, value) ->
            builder.addHeader(key, value)  // 为每个头部添加键值对
        }

        // 构建新的请求并发送，返回响应
        return chain.proceed(builder.build())
    }
}
