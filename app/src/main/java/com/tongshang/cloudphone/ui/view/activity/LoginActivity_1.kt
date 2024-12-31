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
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityLogin1Binding
import com.tongshang.cloudphone.utils.CountDownTimerUtil
import com.tongshang.cloudphone.utils.PasswordToggleUtils
import com.tongshang.cloudphone.utils.TextWatcherUtil
import com.tongshang.cloudphone.utils.ToastUtil

/**
 * @author: z2660
 * @date: 2024/12/6
 *
 * 账号密码登录
 */
class LoginActivity_1 : BaseActivity() {

    private lateinit var _binding: ActivityLogin1Binding

    // 验证码状态
    private var isTimerRunning = false
    private var isSelect = false
    private lateinit var countDownTimerUtil: CountDownTimerUtil


    override fun getLayoutView(): Int {
        return R.layout.activity_login_1
    }

    override fun initView() {


        _binding = ActivityLogin1Binding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局


        // 设置标题和图标
        setTitleText("账号密码登录")
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


        // 获取验证码按钮点击事件
        countDownTimerUtil = CountDownTimerUtil.getInstance()
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
            if (_binding.editText6.text.isEmpty()) {
                ToastUtil.showShortToast("请输入密码")
                return@setOnClickListener
            }
            // 检查密码长度和字符组合
            if (!isValidPassword(_binding.editText6.text.toString())) {
                ToastUtil.showShortToast("密码必须为8-16位字符，并包含字母和数字")
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
        setPasswordVisibilityToggle(_binding.editText6, _binding.imageView29, R.mipmap.update_3, R.mipmap.update_2)

        _binding.finsh.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        _binding.wangji.setOnClickListener {
            startActivity(Intent(this, RetrievepasswordActivity::class.java))
            finish()
        }

    }

    // 密码可见性切换的封装方法
    private fun setPasswordVisibilityToggle(editText: EditText, imageView: ImageView, showIconRes: Int, hideIconRes: Int) {
        PasswordToggleUtils.setPasswordVisibilityToggle(editText, imageView, showIconRes, hideIconRes)
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
                val intent = Intent(this@LoginActivity_1, AgreementActivity::class.java)
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
                    val intent = Intent(this@LoginActivity_1, AgreementActivity::class.java)
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
                    val intent = Intent(this@LoginActivity_1, AgreementActivity::class.java)
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

    // 密码验证函数：检查密码是否符合8-16位且包含字母和数字的规则
    fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$"  // 至少包含一个字母和一个数字，长度为8-16
        return password.matches(passwordPattern.toRegex())
    }


    override fun onDestroy() {
        super.onDestroy()
        // 停止倒计时
        countDownTimerUtil.clear()  // 清理资源
        // 移除点击事件
        _binding.tvAgreement.setMovementMethod(null)
    }
}