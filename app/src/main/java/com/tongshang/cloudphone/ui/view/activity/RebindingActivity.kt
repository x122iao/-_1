package com.tongshang.cloudphone.ui.view.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityRebindingBinding
import com.tongshang.cloudphone.utils.CountDownTimerUtil
import com.tongshang.cloudphone.utils.TextWatcherUtil
import com.tongshang.cloudphone.utils.ToastUtil

/**
 * @author: z2660
 * @date: 2024/12/6
 *
 * 手机号换绑
 */
class RebindingActivity : BaseActivity() {

    private lateinit var _binding: ActivityRebindingBinding

    // 验证码状态
    private var isTimerRunning = false

    override fun getLayoutView(): Int {
        return R.layout.activity_rebinding
    }

    override fun initView() {
        _binding = ActivityRebindingBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局

        // 设置标题和图标
        setTitleText("手机号换绑")
        setExtraImage(R.mipmap.rebin_1)

        // 点击空白处隐藏键盘
        hideKeyboard(_binding.root)


        // 监听输入框变化
        TextWatcherUtil.setTextChangedListener(_binding.editText) {
            val drawable: Drawable? = if (it.isEmpty()) {
                ContextCompat.getDrawable(this, R.drawable.rounded_background_my_5)
            } else {
                ContextCompat.getDrawable(this, R.drawable.rounded_background_my_6)
            }
            _binding.next.background = drawable
        }

        // 获取验证码按钮点击事件
        _binding.verify.setOnClickListener {
            if (!isTimerRunning) {
                startCountDown()
            }
        }

        // 下一步按钮点击事件
        _binding.next.setOnClickListener {
            if (_binding.editText.text.trim().isNotEmpty()) {
                CountDownTimerUtil.getInstance().cancel()
                startActivity(Intent(this, RebindingActivity_1::class.java))
                finish()
            } else {
                ToastUtil.showShortToast("验证码不能为空")
            }
        }
    }

    // 启动倒计时
    private fun startCountDown() {
        isTimerRunning = true
        _binding.verify.isEnabled = false  // 禁用按钮，防止重复点击

        // 使用 CountDownTimerUtil 单例并启动倒计时
        val countDownTimerUtil = CountDownTimerUtil.getInstance()
        countDownTimerUtil.init(
            textView = _binding.verify,
            totalTime = 60, // 60秒倒计时
            onFinish = {
                // 倒计时结束的回调
                _binding.verify.isEnabled = true  // 启用按钮
                isTimerRunning = false
            },
            onTick = { remainingTime ->
                // 每秒更新一次的回调
                _binding.verify.text = "$remainingTime"
            }
        )

        countDownTimerUtil.start()
    }
}


