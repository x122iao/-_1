package com.tongshang.cloudphone.ui.view.activity

import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityAuthorizationBinding

/**
 * @author: z2660
 * @date: 2024/12/6
 * 云机授权
 */
class AuthorizationActivity : BaseActivity() {

    private lateinit var _binding: ActivityAuthorizationBinding

    override fun getLayoutView(): Int {
       return R.layout.activity_authorization
    }

    override fun initView() {
        _binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局


        // 设置标题和图标
        setTitleText("云机授权")
        setExtraImage(R.mipmap.auth_1)
        setAuth(true,"授权记录")

        // 点击空白处隐藏键盘
        hideKeyboard(_binding.root)


    }
}