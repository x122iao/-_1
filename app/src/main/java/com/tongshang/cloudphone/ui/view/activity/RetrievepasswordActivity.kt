package com.tongshang.cloudphone.ui.view.activity

import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityRetrievepasswordBinding
import com.tongshang.cloudphone.utils.CountDownTimerUtil
import com.tongshang.cloudphone.utils.PasswordToggleUtils
import com.tongshang.cloudphone.utils.TextWatcherUtil
import com.tongshang.cloudphone.utils.ToastUtil

/**
 * @author: z2660
 * @date: 2024/12/6
 *
 * 找回密码
 */
class RetrievepasswordActivity : BaseActivity() {

    private lateinit var _binding: ActivityRetrievepasswordBinding


    private lateinit var countDownTimerUtil: CountDownTimerUtil
    private var isTimerRunning = false


    override fun getLayoutView(): Int {
        return R.layout.activity_retrievepassword
    }

    override fun initView() {


        _binding = ActivityRetrievepasswordBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局


        // 设置标题和图标
        setTitleText("找回密码")
        setExtraImage(R.mipmap.rebin_1)
        // 点击空白处隐藏键盘
        hideKeyboard(_binding.root)

        countDownTimerUtil = CountDownTimerUtil.getInstance()
        // 获取验证码按钮点击事件
        _binding.verify.setOnClickListener {
            if (!isTimerRunning) {
                startCountDown()
            }
        }


        // 监听输入框变化
        TextWatcherUtil.setTextChangedListener(_binding.editText6) {
            val drawable: Drawable? = if (it.isEmpty()) {
                ContextCompat.getDrawable(this, R.drawable.rounded_background_my_5)
            } else {
                ContextCompat.getDrawable(this, R.drawable.rounded_background_my_6)
            }
            _binding.next.background = drawable
        }


        // 设置密码可见性切换
        setPasswordVisibilityToggle(_binding.editText6, _binding.imageView29, R.mipmap.update_3, R.mipmap.update_2)

        _binding.imageView34.setOnClickListener {
            if (_binding.editText4.text.isNotEmpty()) {
                _binding.editText4.text = null
            } else {
                ToastUtil.showShortToast("已经没有内容了")
            }
        }

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

    // 密码可见性切换的封装方法
    private fun setPasswordVisibilityToggle(editText: EditText, imageView: ImageView, showIconRes: Int, hideIconRes: Int) {
        PasswordToggleUtils.setPasswordVisibilityToggle(editText, imageView, showIconRes, hideIconRes)
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimerUtil.clear()
    }

}