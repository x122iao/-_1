package com.tongshang.cloudphone.utils

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import java.lang.ref.WeakReference

/**
 * 使用 Handler 和 Runnable 实现的倒计时工具类（单例模式）
 * @param totalTime 倒计时总时长，单位：秒
 * @param onFinish 倒计时结束时回调
 * @param onTick 每秒触发的回调
 */
class CountDownTimerUtil private constructor() {

    private var handler: Handler? = null
    private var remainingTime: Long = 0
    private var isRunning = false
    private var textViewRef: WeakReference<TextView>? = null

    companion object {
        @Volatile
        private var INSTANCE: CountDownTimerUtil? = null

        // 单例获取方法
        fun getInstance(): CountDownTimerUtil {
            return INSTANCE ?: synchronized(this) {
                val instance = CountDownTimerUtil()
                INSTANCE = instance
                instance
            }
        }
    }

    // 初始化
    fun init(textView: TextView, totalTime: Long, onFinish: () -> Unit, onTick: (Long) -> Unit) {
        // 保持对 TextView 的弱引用，避免内存泄漏
        this.textViewRef = WeakReference(textView)
        this.remainingTime = totalTime
        this.handler = Handler(Looper.getMainLooper())  // 创建Handler，用于更新UI

        val runnable = object : Runnable {
            override fun run() {
                val textView = textViewRef?.get()
                if (textView != null) {
                    if (remainingTime > 0) {
                        onTick(remainingTime)  // 每秒更新UI
                        textView.text = "$remainingTime"  // 更新 TextView
                        remainingTime--
                        handler?.postDelayed(this, 1000)  // 每秒执行一次
                    } else {
                        onFinish()  // 倒计时结束
                        textView.text = "获取验证码"  // 更新显示为“获取验证码”
                        isRunning = false
                    }
                }
            }
        }

        // 启动倒计时
        handler?.post(runnable)
    }

    // 启动倒计时
    fun start() {
        if (isRunning) return  // 防止重复启动

        isRunning = true
        remainingTime = 60  // 默认设置60秒
    }

    // 取消倒计时
    fun cancel() {
        handler?.removeCallbacksAndMessages(null)  // 清除所有待处理的消息和回调
        isRunning = false
    }

    // 清理掉 TextView 的引用，避免内存泄漏
    fun clear() {
        textViewRef?.clear()  // 清除弱引用
        handler?.removeCallbacksAndMessages(null)  // 移除所有消息
    }
}
