package com.tongshang.cloudphone.ui.view.activity

import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityUpdateBinding
import com.tongshang.cloudphone.utils.PasswordToggleUtils
import com.tongshang.cloudphone.utils.TextWatcherUtil

/**
 * @author: z2660
 * @date: 2024/12/6
 *
 * 修改登录密码
 */
class UpdateActivity : BaseActivity() {

    private lateinit var _binding: ActivityUpdateBinding

    override fun getLayoutView(): Int {
        return R.layout.activity_update
    }

    override fun initView() {


        _binding = ActivityUpdateBinding.inflate(layoutInflater)
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

        // 设置密码可见性切换
        setPasswordVisibilityToggle(_binding.editText6, _binding.imageView28, R.mipmap.update_3, R.mipmap.update_2)
        setPasswordVisibilityToggle(_binding.editText5, _binding.imageView29, R.mipmap.update_3, R.mipmap.update_2)


    }

    // 密码可见性切换的封装方法
    private fun setPasswordVisibilityToggle(editText: EditText, imageView: ImageView, showIconRes: Int, hideIconRes: Int) {
        PasswordToggleUtils.setPasswordVisibilityToggle(editText, imageView, showIconRes, hideIconRes)
    }

}