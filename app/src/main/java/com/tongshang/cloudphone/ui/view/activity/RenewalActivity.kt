package com.tongshang.cloudphone.ui.view.activity

import android.content.Intent
import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.base.BaseActivity_buy
import com.tongshang.cloudphone.databinding.ActivityHelpBinding
import com.tongshang.cloudphone.databinding.ActivityRenewalBinding

/**
 * @author: z2660
 * @date: 2024/12/16
 */
class RenewalActivity : BaseActivity(){

    private lateinit var _binding: ActivityRenewalBinding

    override fun getLayoutView(): Int {
        return R.layout.activity_renewal
    }

    override fun initView() {

        _binding = ActivityRenewalBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局

        setTitleText("续费")

        setLayAllBackgroundColor_1(R.color.white)

        _binding.button.setOnClickListener {
            startActivity(Intent(this,BaseActivity_buy::class.java))
        }

    }
}