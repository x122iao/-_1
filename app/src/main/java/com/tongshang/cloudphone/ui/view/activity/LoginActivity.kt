package com.tongshang.cloudphone.ui.view.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityLoginBinding
import com.tongshang.cloudphone.utils.CountDownTimerUtil
import com.tongshang.cloudphone.utils.TextWatcherUtil
import com.tongshang.cloudphone.utils.ToastUtil

/**
 * @author: z2660
 * @date: 2024/12/6
 *
 * 验证码登录
 */
class LoginActivity : BaseActivity() {

    private lateinit var _binding: ActivityLoginBinding

    // 验证码状态
    private var isTimerRunning = false
    private var isSelect = false

    private lateinit var countDownTimerUtil: CountDownTimerUtil

    override fun getLayoutView(): Int {
        return R.layout.activity_login
    }

    override fun initView() {


        _binding = ActivityLoginBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局


        // 设置标题和图标
        setTitleText("验证码登录")
        setExtraImage(R.mipmap.upperlevel)

        // 点击空白处隐藏键盘
        hideKeyboard(_binding.root)


        // 监听输入框变化
        TextWatcherUtil.setTextChangedListener(_binding.editText5) {
            val drawable: Drawable? = if (it.isEmpty()) {
                ContextCompat.getDrawable(this, R.drawable.rounded_background_my_5)
            } else {
                ContextCompat.getDrawable(this, R.drawable.rounded_background_my_6)
            }
            _binding.next.background = drawable
        }


        setAgreementTextView()

        countDownTimerUtil = CountDownTimerUtil.getInstance()
        // 获取验证码按钮点击事件
        _binding.verify.setOnClickListener {
            if (!isTimerRunning) {
                startCountDown()
            }
        }
        _binding.imageView34.setOnClickListener {
            if (_binding.editText4.text.isNotEmpty()) {
                _binding.editText4.text = null
            } else {
                ToastUtil.showShortToast("已经没有内容了")
            }
        }
        _binding.next.setOnClickListener {
            if (!isSelect) {
                ToastUtil.showShortToast("请阅读并同意协议")
                return@setOnClickListener
            }
            if (_binding.editText4.text.isEmpty()) {
                ToastUtil.showShortToast("请输入手机号")
                return@setOnClickListener
            }
            if (_binding.editText5.text.isEmpty()) {
                ToastUtil.showShortToast("请输入验证码")
                return@setOnClickListener
            }
            finish()
        }

        _binding.ivSelect.setOnClickListener {
            if (isSelect) {
                isSelect = false
                _binding.ivSelect.setImageResource(R.mipmap.ic_unselected)
            } else {
                isSelect = true
                _binding.ivSelect.setImageResource(R.mipmap.ic_selected)
            }
        }

        _binding.textView40.setOnClickListener {
            startActivity(Intent(this, LoginActivity_1::class.java))
            finish()

        }


    }

    //    我已阅读并同意《天翼账号服务与隐私协议》、闪电云加速器《用户协议》与《隐私协议》注册并登录。
    private fun setAgreementTextView() {
        val HEAD = "我已阅读并同意"
        val URL = "《天翼账号服务与隐私协议》"
        val AND = "、闪电云加速器"
        val URL2 = "《用户协议》"
        val URL3 = "与"
        val URL4 = "《隐私协议》"
        val FOOT = "注册并登录。"
        val TEXT = HEAD + URL + AND + URL2 + URL3 + URL4 + FOOT

        val builder = SpannableStringBuilder()
        builder.append(TEXT)

        // 设置 "天翼账号服务与隐私协议" 为可点击
        builder.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, AgreementActivity::class.java)
                intent.putExtra("type", 3)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = resources.getColor(R.color.main_color)
                ds.isUnderlineText = false
            }
        }, HEAD.length, HEAD.length + URL.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 设置 "用户协议" 为可点击
        builder.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@LoginActivity, AgreementActivity::class.java)
                    intent.putExtra("type", 1)
                    startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = resources.getColor(R.color.main_color)
                    ds.isUnderlineText = false
                }
            },
            HEAD.length + URL.length + AND.length,
            HEAD.length + URL.length + AND.length + URL2.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 设置 "隐私协议" 为可点击
        builder.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@LoginActivity, AgreementActivity::class.java)
                    intent.putExtra("type", 2)
                    startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = resources.getColor(R.color.main_color)
                    ds.isUnderlineText = false
                }
            },
            HEAD.length + URL.length + AND.length + URL2.length + URL3.length,
            HEAD.length + URL.length + AND.length + URL2.length + URL3.length + URL4.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 设置 TextView 允许点击
        _binding.tvAgreement.setMovementMethod(LinkMovementMethod.getInstance())
        _binding.tvAgreement.setText(builder)
        // 去掉点击后文字的背景色
        _binding.tvAgreement.setHighlightColor(Color.parseColor("#00000000"))
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

    override fun onDestroy() {
        super.onDestroy()
        // 停止倒计时
        countDownTimerUtil.clear()  // 清理资源
        // 移除点击事件
        _binding.tvAgreement.setMovementMethod(null)
    }
}