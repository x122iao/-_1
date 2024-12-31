package com.tongshang.cloudphone.ui.view.activity

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityVerifyupdateBinding
import com.tongshang.cloudphone.utils.CountDownTimerUtil
import com.tongshang.cloudphone.utils.PasswordToggleUtils
import com.tongshang.cloudphone.utils.TextWatcherUtil
import com.tongshang.cloudphone.utils.ToastUtil

/**
 * @author: z2660
 * @date: 2024/12/6
 *
 * 修改登录密码
 */
class VerifyUpdateActivity : BaseActivity() {

    private lateinit var _binding: ActivityVerifyupdateBinding

    // 验证码状态
    private var isTimerRunning = false
    private lateinit var countDownTimerUtil: CountDownTimerUtil

    override fun getLayoutView(): Int {
        return R.layout.activity_verifyupdate
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        _binding = ActivityVerifyupdateBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局


        // 设置标题和图标
        setTitleText("修改登录密码")
        setExtraImage(R.mipmap.rebin_1)
        // 点击空白处隐藏键盘
        hideKeyboard(_binding.root)

        // 监听输入框变化
        TextWatcherUtil.setTextChangedListener(_binding.editText6) {
            val drawable: Drawable? = if (it.isEmpty()) {
                ContextCompat.getDrawable(this, R.drawable.rounded_background_my_5)
            } else {
                ContextCompat.getDrawable(this, R.drawable.rounded_background_my_6)
            }
            _binding.next.background = drawable
        }

        // 获取验证码按钮点击事件
         countDownTimerUtil = CountDownTimerUtil.getInstance()
        _binding.verify.setOnClickListener {
            if (!isTimerRunning) {
                startCountDown()
            }
        }

        // 下一步按钮点击事件
        _binding.next.setOnClickListener {
            validateAndUpdatePassword()
        }

        // 设置密码可见性切换
        setPasswordVisibilityToggle(_binding.editText5, _binding.imageView, R.mipmap.update_3, R.mipmap.update_2)
        setPasswordVisibilityToggle(_binding.editText6, _binding.imageView1, R.mipmap.update_3, R.mipmap.update_2)


    }

    // 密码可见性切换的封装方法
    private fun setPasswordVisibilityToggle(editText: EditText, imageView: ImageView, showIconRes: Int, hideIconRes: Int) {
        PasswordToggleUtils.setPasswordVisibilityToggle(editText, imageView, showIconRes, hideIconRes)
    }

    // 倒计时逻辑
    private fun startCountDown() {
        isTimerRunning = true
        _binding.verify.isEnabled = false  // 禁用按钮，防止重复点击

        countDownTimerUtil.init(
            textView = _binding.verify,
            totalTime = 60,  // 60秒倒计时
            onFinish = {
                _binding.verify.isEnabled = true  // 启用按钮
                isTimerRunning = false
            },
            onTick = { remainingTime ->
                _binding.verify.text = "$remainingTime"
            }
        )

        countDownTimerUtil.start()
    }
    // 验证和更新密码的方法
    private fun validateAndUpdatePassword() {
        val verificationCode = _binding.editText4.text.trim().toString()
        val newPassword = _binding.editText5.text.trim().toString()
        val confirmPassword = _binding.editText6.text.trim().toString()

        // 验证输入的验证码
        if (verificationCode.isEmpty()) {
            ToastUtil.showShortToast("验证码不能为空")
            return
        }

        // 验证新密码是否为空
        if (newPassword.isEmpty()) {
            ToastUtil.showShortToast("新密码不能为空")
            return
        }

        // 验证两次输入的密码是否一致
        if (newPassword != confirmPassword) {
            ToastUtil.showShortToast("两次密码不一致")
            return
        }

        // 如果所有条件都满足，则可以进行修改操作
        ToastUtil.showShortToast("修改成功")
        // TODO: 执行密码更新操作
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimerUtil.clear()  // 清理资源
    }
}
