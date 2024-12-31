package com.tongshang.cloudphone.ui.view.activity

import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityQuickloginBinding

/**
 * @author: z2660
 * @date: 2024/12/6
 * 一键登录
 */
class QuickloginActivity : BaseActivity() {

    private lateinit var _binding: ActivityQuickloginBinding

    override fun getLayoutView(): Int {
        return R.layout.activity_quicklogin
    }

    override fun initView() {


        _binding = ActivityQuickloginBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局


        // 设置标题和图标
        setTitleText("一键登录")
        setExtraImage(R.mipmap.upperlevel)




    }
}