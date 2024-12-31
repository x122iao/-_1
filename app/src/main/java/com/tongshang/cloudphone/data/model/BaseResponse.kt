package com.tongshang.cloudphone.data.model

/**
 * 通用的 API 响应数据类，用于封装 API 的响应结果。
 *
 * @param T 响应中数据的类型
 * @property code 响应状态码
 * @property msg 响应消息，通常用于描述错误或状态信息
 * @property data 响应数据，类型为 T，可能为 null
 */
data class BaseResponse<T>(
    val code: Int,            // 响应状态码
    val msg: String?,        // 响应消息（可选）
    val data: T?             // 响应数据（可选）
)

fun <T> BaseResponse<T>.getDataOrThrow(): T {
    return this.data ?: throw Exception("响应数据为空: ${this.msg}")
}


