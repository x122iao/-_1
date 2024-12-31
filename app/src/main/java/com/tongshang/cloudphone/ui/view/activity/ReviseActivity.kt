package com.tongshang.cloudphone.ui.view.activity

import android.content.Intent
import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityReviseBinding

/**
 * @author: z2660
 * @date: 2024/12/4
 *
 * 修改登录密码
 */
class ReviseActivity : BaseActivity() {

    private lateinit var _binding: ActivityReviseBinding


    override fun getLayoutView(): Int {
        return R.layout.activity_revise
    }

    override fun initView() {


        _binding = ActivityReviseBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局


        // 设置标题和图标
        setTitleText("修改登录密码")
        setLayAllBackgroundColor_1(R.color.grey)


        _binding.constraintLayout3.setOnClickListener {
            startActivity(Intent(this,UpdateActivity::class.java))
        }

        _binding.constraintLayout4.setOnClickListener {
            startActivity(Intent(this,VerifyUpdateActivity::class.java))
        }


    }
}