package com.tongshang.cloudphone.data.service

import android.content.Context
import com.tongshang.cloudphone.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Retrofit 实例管理类，负责初始化 Retrofit 和 OkHttpClient。
 * 提供网络请求所需的配置，包括缓存、请求超时、头部设置、错误拦截等。
 */
object RetrofitInstance {

    // 默认连接超时、写入超时、读取超时（秒）
    private const val DEFAULT_CONNECT_TIME = 10
    private const val DEFAULT_WRITE_TIME = 30
    private const val DEFAULT_READ_TIME = 10

    // 基础 URL，根据是否是 Debug 模式决定不同的 URL
    private const val BASE_URL = "http://192.168.1.88:8091/"
    private const val DEBUG_PATH = "http://192.168.1.88:8091/"

    /**
     * 创建缓存对象，用于存储 HTTP 响应的缓存数据。
     *
     * @param context 上下文对象，用于访问应用的缓存目录。
     * @return 返回缓存对象。
     */
    private fun createCache(context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MiB
        return Cache(File(context.cacheDir, "http_cache"), cacheSize)
    }

    // OkHttpClient 对象，延迟初始化
    private lateinit var client: OkHttpClient

    /**
     * 初始化 OkHttpClient，配置连接超时、写入超时、读取超时、缓存等。
     * 同时添加了拦截器：头部拦截器、错误拦截器、日志拦截器。
     *
     * @param context 上下文对象，用于创建缓存和其他配置。
     */
    fun init(context: Context) {
        client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true) // 连接失败时重试
            .connectTimeout(DEFAULT_CONNECT_TIME.toLong(), TimeUnit.SECONDS) // 设置连接超时
            .writeTimeout(DEFAULT_WRITE_TIME.toLong(), TimeUnit.SECONDS) // 设置写入超时
            .readTimeout(DEFAULT_READ_TIME.toLong(), TimeUnit.SECONDS) // 设置读取超时
            .cache(createCache(context)) // 设置缓存
            .addInterceptor(HeaderInterceptor(mapOf(
                "Authorization" to "Bearer your_token", // 认证 Token
                "appVersion" to "1.0.0", // 应用版本
                "oaid" to "your_oaid", // OAID
                "language" to "zh", // 语言
                "Content-Type" to "application/json" // 内容类型
            ))) // 添加自定义头部拦截器
            .addInterceptor(ErrorInterceptor()) // 添加错误拦截器
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // 添加日志拦截器
            .build()
    }

    // Retrofit 实例，延迟初始化
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(getBaseUrl()) // 设置基础 URL，根据环境选择不同的 URL
            .client(client) // 设置 OkHttpClient
            .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析器
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // 可选，支持 RxJava
            .build()
    }

    // ApiService 实例，延迟初始化，提供网络请求的接口方法
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java) // 创建 ApiService 接口
    }

    /**
     * 根据构建配置判断返回哪个基础 URL。
     *
     * @return 返回基础 URL。开发环境使用 DEBUG_PATH，生产环境使用 BASE_URL。
     */
    private fun getBaseUrl(): String? {
        return if (BuildConfig.IS_DEBUG) {
            DEBUG_PATH // 调试环境 URL
        } else {
            BASE_URL // 生产环境 URL
        }
    }
}
