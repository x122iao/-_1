package com.tongshang.cloudphone.data.exception

/**
 * 自定义异常类，用于 API 请求错误的处理。
 * @param errorCode 错误码，通常由后端返回，用于标识具体的错误类型。
 * @param message 错误信息，描述该异常的具体信息。
 */
class ApiException(val errorCode: Int, message: String) : Exception(message) {
    // 通过继承 Exception 类，实现自定义异常，包含错误码和错误信息
}
