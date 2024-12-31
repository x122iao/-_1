package com.tongshang.cloudphone.ui.view.activity

import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityAuthorizationrecordsBinding

/**
 * @author: z2660
 * @date: 2024/12/6
 * 授权记录
 */
class AuthorizationRecordsActivity : BaseActivity() {

    private lateinit var _binding: ActivityAuthorizationrecordsBinding

    override fun getLayoutView(): Int {
        return R.layout.activity_authorizationrecords
    }

    override fun initView() {
        _binding = ActivityAuthorizationrecordsBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局

        setTitleText("授权记录")
        setLayAllBackgroundColor_1(R.color.grey)





    }
}